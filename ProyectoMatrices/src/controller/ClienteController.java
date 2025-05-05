package controller;
import models.ClienteModel;

public class ClienteController {
    
    private ClienteModel cliente;
    private RegistradorController registrador;
    
    public String[] CreateClient (int nIdentificacion,String tIdentificacion,String direccion,String nombre,String correo, String municipio, String departamento ) {
        cliente = new ClienteModel(nIdentificacion, tIdentificacion, direccion, nombre, correo, municipio,departamento);
        registrador = new RegistradorController();
        String[] arrayRegistrador = registrador.CreateRegistrador(direccion, municipio, departamento);
        return arrayRegistrador;
    }
}
