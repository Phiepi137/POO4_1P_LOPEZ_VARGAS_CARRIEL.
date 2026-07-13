package com.example;

import com.example.enums.*;
import java.util.ArrayList;

public class Reporte {
    private int totalCompras;
    private int totalEntradas;
    private int totalKits;
    private double montoTotal;

    public Reporte(int totalCompras, int totalEntradas, int totalKits, double montoTotal) {
        this.totalCompras = totalCompras;
        this.totalEntradas = totalEntradas;
        this.totalKits = totalKits;
        this.montoTotal = montoTotal;
    }

    public int getTotalCompras() {
        return totalCompras;
    }

    public int getTotalEntradas() {
        return totalEntradas;
    }

    public int getTotalKits() {
        return totalKits;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void GenerarReporte(ArrayList<Compra> compras) {
        this.totalCompras = compras.size();
        this.totalEntradas = 0;
        this.totalKits = 0;
        this.montoTotal = 0;

        for (Compra c : compras) {
            if (c.getTipo() == TipoCompra.ENTRADA) {
                totalEntradas += c.getCantidad();
            } else if (c.getTipo() == TipoCompra.KIT) {
                totalKits += c.getCantidad();
            }
            montoTotal += c.getValorPagado();
        }
    }

    @Override
    public String toString() {
        return "===== REPORTE DE VENTAS =====\n" +
                "Total de compras: " + totalCompras +
                "\nEntradas vendidas: " + totalEntradas +
                "\nKits vendidos: " + totalKits +
                "\nMonto total: $" + montoTotal;
    }

}