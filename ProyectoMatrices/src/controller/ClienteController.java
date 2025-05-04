package controller;
import view.ClienteView;
import models.ClienteModel;
import controller.RegistradorController;

public class ClienteController {
    
    private ClienteModel cliente;
    private RegistradorController registrador;
    
    public void CreateClient (int nIdentificacion,String tIdentificacion,String direccion,String nombre,String correo, String municipio, String departamento ) {
        cliente = new ClienteModel(nIdentificacion, tIdentificacion, direccion, nombre, correo, municipio,departamento);
        registrador = new RegistradorController();
        String[] arrayRegistrador = registrador.CreateRegistrador(direccion, municipio, departamento);
        cliente.setRegistrador(Integer.parseInt(arrayRegistrador[0]));
    }





    
}
