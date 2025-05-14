package com.mycompany.proyecto.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FacturaGenerator {

    public static void generarFactura(Cliente cliente, int mes, String archivoSalida, int diaMenorConsumo, int horaMenorConsumo, int menorConsumo) {
        if (archivoSalida == null || archivoSalida.isEmpty()) {
            throw new IllegalArgumentException("El archivo de salida no puede ser nulo o vacío.");
        }

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(archivoSalida));
            document.open();

            // Validación básica
            if (cliente == null) {
                document.add(new Paragraph("Cliente no encontrado."));
                return;
            }

            document.add(new Paragraph("Factura del Cliente", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD)));
            document.add(new Paragraph("ID: " + cliente.getId()));
            document.add(new Paragraph("Nombre: " + cliente.getNombre()));
            document.add(new Paragraph("Dirección: " + cliente.getDireccion()));
            document.add(new Paragraph("Mes: " + mes));
            document.add(Chunk.NEWLINE);

            List<Registrador> registradores = cliente.getRegistradores();

            if (registradores == null || registradores.isEmpty()) {
                document.add(new Paragraph("El cliente no tiene registradores."));
            } else {
                PdfPTable tabla = new PdfPTable(3); // ID, consumo, costo
                tabla.setWidthPercentage(100);
                tabla.setWidths(new int[] { 2, 2, 2 });
                tabla.addCell("Registrador");
                tabla.addCell("Consumo (kWh)");
                tabla.addCell("Costo ($)");

                int total = 0;

                for (Registrador r : registradores) {
                    if (r == null || r.getConsumo() == null)
                        continue;

                    int consumo = r.getConsumo().getConsumoMes(mes);
                    int costo = r.getConsumo().getCostoMes(mes);
                    total += costo;

                    tabla.addCell(r.getId() != null ? r.getId() : "N/A");
                    tabla.addCell(consumo > 0 ? String.valueOf(consumo) : "0");
                    tabla.addCell(costo > 0 ? "$" + costo : "$0");
                }

                document.add(tabla);
                document.add(Chunk.NEWLINE);

                Paragraph totalP = new Paragraph("Total a pagar: $" + total,
                        new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
                totalP.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalP);
            }

            // Agregar información del menor consumo
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Información del menor consumo:", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            document.add(new Paragraph("Día con menor consumo: " + diaMenorConsumo));
            document.add(new Paragraph("Hora con menor consumo: " + horaMenorConsumo));
            document.add(new Paragraph("Consumo mínimo: " + menorConsumo + " kWh"));

            System.out.println("Factura generada en: " + archivoSalida);
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