package com.mycompany.proyecto.model;


public class Registrador {
    private final String id; // Número único
    private String ubicacion;
    private Consumo consumo;

    public Registrador(String id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.consumo = new Consumo();
    }

    public String getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Consumo getConsumo() {
        return consumo;
    }
}
