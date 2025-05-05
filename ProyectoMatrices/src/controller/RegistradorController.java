package controller;
import models.RegistradorModel;
import controller.ClienteController;


public class RegistradorController {
    private RegistradorModel registrador;
    public String[] CreateRegistrador(String direccionCompleta,String municipio,String departamentos)
    {
        registrador = new RegistradorModel(direccionCompleta,municipio,departamentos);
        
        
        String[] arrayRegistrador = registrador.RegistradorComoArreglo();
        return arrayRegistrador;
    }

}
