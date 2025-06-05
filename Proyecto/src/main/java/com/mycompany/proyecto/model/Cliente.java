package com.mycompany.proyecto.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private final String id;
    private String TipoId;
    private String nombre;
    private String direccion;
    private List<Registrador> registradores;
    private String CorreoElectronico;
    private String Ciudad;

    public Cliente(String id, String nombre, String direccion, String TipoId, String CorreoElectronico, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.TipoId = TipoId;
        this.CorreoElectronico = CorreoElectronico;
        this.direccion = direccion;
        this.Ciudad = ciudad;
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

    public String getTipoId() {
        return TipoId;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        this.Ciudad = ciudad;
    }

    public String getCorreo() {
        return CorreoElectronico;
    }

    public void SetCorreo(String correo) {
        this.CorreoElectronico = correo;
    }

    public Registrador buscarRegistrador(String idReg) {
        for (Registrador r : registradores) {
            if (r.getId().equals(idReg))
                return r;
        }
        return null;
    }
}