package com.mycompany.proyecto.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private final String id; // Número único de identificación
    private String nombre;
    private String direccion;
    private List<Registrador> registradores;

    public Cliente(String id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.registradores = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Registrador> getRegistradores() {
        return registradores;
    }

    public void agregarRegistrador(Registrador r) {
        this.registradores.add(r);
    }

    public Registrador buscarRegistrador(String idReg) {
        for (Registrador r : registradores) {
            if (r.getId().equals(idReg)) return r;
        }
        return null;
    }
}