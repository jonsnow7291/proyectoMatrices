package com.mycompany.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.proyecto.model.Cliente;
import com.mycompany.proyecto.model.Registrador;

public class SistemaEnergia {
    private final List<Cliente> clientes;

    public SistemaEnergia() {
        clientes = new ArrayList<>();
    }

    // Crear cliente
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Buscar cliente por ID
    public Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    // Editar cliente (excepto ID)
    public boolean editarCliente(String id, String nuevoNombre, String nuevaDireccion, String nuevoCorreo, String nuevaCiudad) {
        Cliente c = buscarCliente(id);
        if (c != null) {
            c.setNombre(nuevoNombre);
            c.setDireccion(nuevaDireccion);
            c.setCiudad(nuevaCiudad);
            c.SetCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    // Agregar registrador a cliente
    public boolean agregarRegistradorACliente(String idCliente, Registrador registrador) {
        Cliente c = buscarCliente(idCliente);
        if (c != null) {
            c.agregarRegistrador(registrador);
            return true;
        }
        return false;
    }

    // Editar registrador (excepto ID)
    public boolean editarRegistrador(String idCliente, String idRegistrador, String nuevaUbicacion) {
        Cliente c = buscarCliente(idCliente);
        if (c != null) {
            Registrador r = c.buscarRegistrador(idRegistrador);
            if (r != null) {
                r.setUbicacion(nuevaUbicacion);
                return true;
            }
        }
        return false;
    }

    // Cargar consumo de todos los clientes para todo el año
    public void cargarConsumoTodosAnual() {
        for (Cliente c : clientes) {
            for (Registrador r : c.getRegistradores()) {
                r.getConsumo().generarDatos();
            }
        }
    }

    // Cargar consumo de todos los clientes para un mes específico
    public void cargarConsumoTodosMes(int mes) {
        for (Cliente c : clientes) {
            for (Registrador r : c.getRegistradores()) {
                r.getConsumo().generarDatosMes(mes);
            }
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
