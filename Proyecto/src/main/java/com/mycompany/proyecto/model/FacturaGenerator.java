package com.mycompany.proyecto.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FacturaGenerator {
    private static final String FACTURAS_DIR = "facturas";

    public static void generarFactura(Cliente cliente, int mes, String archivoSalida, int diaMenorConsumo, int horaMenorConsumo, int menorConsumo, int diaMayorConsumo, int horaMayorConsumo, int MayorConsumo) {
        // Crear directorio de facturas si no existe
        File directorio = new File(FACTURAS_DIR);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Construir la ruta completa del archivo
        String rutaCompleta = FACTURAS_DIR + File.separator + archivoSalida;

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
            document.open();

            // Encabezado estilizado compatible con iText 5
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.WHITE);
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Paragraph("Factura del Cliente", headerFont));
            cell.setBackgroundColor(new BaseColor(0, 102, 204));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cell);
            document.add(headerTable);

            document.add(Chunk.NEWLINE);

            // Información del cliente
            Font infoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            document.add(new Paragraph("ID: " + cliente.getId(), infoFont));
            document.add(new Paragraph("Nombre: " + cliente.getNombre(), infoFont));
            document.add(new Paragraph("Dirección: " + cliente.getDireccion(), infoFont));
            document.add(new Paragraph("Mes: " + mes, infoFont));
            document.add(Chunk.NEWLINE);

            List<Registrador> registradores = cliente.getRegistradores();

            if (registradores == null || registradores.isEmpty()) {
                document.add(new Paragraph("El cliente no tiene registradores.", infoFont));
            } else {
                // Tabla estilizada compatible
                PdfPTable tabla = new PdfPTable(3);
                tabla.setWidthPercentage(100);
                tabla.setWidths(new int[] { 2, 2, 2 });

                Font thFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
                BaseColor thBg = new BaseColor(0, 102, 204);

                String[] headers = {"Registrador", "Consumo (kWh)", "Costo ($)"};
                for (String h : headers) {
                    PdfPCell th = new PdfPCell(new Paragraph(h, thFont));
                    th.setBackgroundColor(thBg);
                    th.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(th);
                }

                Font tdFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

                int total = 0;
                for (Registrador r : registradores) {
                    if (r == null || r.getConsumo() == null)
                        continue;

                    int consumo = r.getConsumo().getConsumoMes(mes);
                    int costo = r.getConsumo().getCostoMes(mes);
                    total += costo;

                    PdfPCell td1 = new PdfPCell(new Paragraph(r.getId() != null ? r.getId() : "N/A", tdFont));
                    PdfPCell td2 = new PdfPCell(new Paragraph(consumo > 0 ? String.valueOf(consumo) : "0", tdFont));
                    PdfPCell td3 = new PdfPCell(new Paragraph(costo > 0 ? "$" + costo : "$0", tdFont));
                    td1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    td2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    td3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(td1);
                    tabla.addCell(td2);
                    tabla.addCell(td3);
                }

                document.add(tabla);
                document.add(Chunk.NEWLINE);

                Paragraph totalP = new Paragraph("Total a pagar: $" + total,
                        new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));
                totalP.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalP);
            }

            // Separador visual
            Paragraph sep = new Paragraph(" ");
            sep.setSpacingBefore(10f);
            sep.setSpacingAfter(10f);
            document.add(sep);

            // Información del menor y mayor consumo con estilo compatible
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, new BaseColor(0, 102, 204));
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);

            document.add(new Paragraph("Información del menor consumo:", sectionFont));
            document.add(new Paragraph("Día con menor consumo: " + diaMenorConsumo, labelFont));
            document.add(new Paragraph("Hora con menor consumo: " + horaMenorConsumo, labelFont));
            document.add(new Paragraph("Consumo mínimo: " + menorConsumo + " kWh", labelFont));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Información del mayor consumo:", sectionFont));
            document.add(new Paragraph("Día con mayor consumo: " + diaMayorConsumo, labelFont));
            document.add(new Paragraph("Hora con mayor consumo: " + horaMayorConsumo, labelFont));
            document.add(new Paragraph("Consumo máximo: " + MayorConsumo + " kWh", labelFont));

            System.out.println("Factura generada en: " + rutaCompleta);
        } catch (DocumentException | IOException e) {
            System.err.println("Error al generar la factura: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}