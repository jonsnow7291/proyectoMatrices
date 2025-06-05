package com.mycompany.proyecto.model;

import java.util.Arrays;
import java.util.Random;


public class Consumo {
    private final int[][][] consumo; // [mes][día][hora] en kWh
    private final int[][][] costo;   // [mes][día][hora] en pesos
    private final int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Consumo() {
        consumo = new int[12][][];
        costo = new int[12][][];
        Random random = new Random();

        for (int mes = 0; mes < 12; mes++) {
            int dias = diasPorMes[mes];
            consumo[mes] = new int[dias][24];
            costo[mes] = new int[dias][24];

            for (int dia = 0; dia < dias; dia++) {
                for (int hora = 0; hora < 24; hora++) {
                    int kw = 1 + random.nextInt(5); // consumo aleatorio entre 1 y 5 kWh
                    consumo[mes][dia][hora] = kw;
                    costo[mes][dia][hora] = calcularCosto(hora, kw);
                }
            }
        }
    }

    private int calcularCosto(int hora, int kw) {
        Random random = new Random();
        int precioPorKw;
        if (hora >= 0 && hora <= 6) {
            precioPorKw = 10 + random.nextInt(91); // 10–100
        } else if (hora >= 7 && hora <= 17) {
            precioPorKw = 500 + random.nextInt(1001); // 500–1500
        } else {
            precioPorKw = 300 + random.nextInt(701); // 300–1000
        }
        return kw * precioPorKw;
    }
    public int[] getDiasPorMes() {
        return diasPorMes;
    }

    public int[] getConsumoPorHora(int mes, int dia) {
        return consumo[mes - 1][dia];
    }

    public int[] getCostoPorHora(int mes, int dia) {
        return costo[mes - 1][dia];
    }

    public int getConsumoDia(int mes, int dia) {
        return Arrays.stream(consumo[mes - 1][dia]).sum();
    }

    public int getCostoDia(int mes, int dia) {
        return Arrays.stream(costo[mes - 1][dia]).sum();
    }

    public int getConsumoMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        int total = 0;
        for (int dia = 0; dia < consumo[mes - 1].length; dia++) {
            total += getConsumoDia(mes, dia);
        }
        return total;
    }

    public int getCostoMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        int total = 0;
        for (int dia = 0; dia < costo[mes - 1].length; dia++) {
            total += getCostoDia(mes, dia);
        }
        return total;
    }

    public int getConsumoAnual() {
        int total = 0;
        for (int mes = 1; mes <= 12; mes++) {
            total += getConsumoMes(mes);
        }
        return total;
    }

    public int getCostoAnual() {
        int total = 0;
        for (int mes = 1; mes <= 12; mes++) {
            total += getCostoMes(mes);
        }
        return total;
    }

    public void verConsumoDia(int mes, int dia) {
        for (int hora = 0; hora < 24; hora++) {
            System.out.printf("Hora %02d: %d kWh\n", hora, consumo[mes - 1][dia][hora]);
        }
    }

    public int getDiasDelMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        return diasPorMes[mes - 1];
    }

    public void modificarConsumoHora(int mes, int dia, int hora, int nuevoKw) {
        consumo[mes - 1][dia][hora] = nuevoKw;
        costo[mes - 1][dia][hora] = calcularCosto(hora, nuevoKw);
    }
public int[][] getConsumoMensual(int mes) {
    if (mes < 1 || mes > 12) {
        throw new IllegalArgumentException("El mes debe estar entre 1 y 12.");
    }
    return consumo[mes - 1]; // Retorna el consumo del mes (ajustado al índice 0)
}
    public void generarDatos() {
        Random random = new Random();
        for (int mes = 0; mes < 12; mes++) {
            int dias = diasPorMes[mes];
            for (int dia = 0; dia < dias; dia++) {
                for (int hora = 0; hora < 24; hora++) {
                    int kw = 1 + random.nextInt(5);
                    consumo[mes][dia][hora] = kw;
                    costo[mes][dia][hora] = calcularCosto(hora, kw);
                }
            }
        }
    }
    public void generarDatosMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: debe estar entre 1 y 12");
        }
        Random random = new Random();
        int dias = diasPorMes[mes - 1];
        for (int dia = 0; dia < dias; dia++) {
            for (int hora = 0; hora < 24; hora++) {
                int kw = 1 + random.nextInt(5);
                consumo[mes - 1][dia][hora] = kw;
                costo[mes - 1][dia][hora] = calcularCosto(hora, kw);
            }
        }
    }
    
}