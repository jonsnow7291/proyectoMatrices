package controller;
import view.ClienteView;
import models.ClienteModel;
import models.RegistradorModel;

public class ClienteController {
    
    private ClienteModel cliente;
    private RegistradorModel registrador;
    
    public void CreateClient (int nIdentificacion,String tIdentificacion,String direccion,String nombre,String correo) {
        cliente = new ClienteModel(nIdentificacion, tIdentificacion, direccion, nombre, correo);
    }



    
}
