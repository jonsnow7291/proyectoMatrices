package controller;
import models.RegistradorModel;


public class RegistradorController {
    private RegistradorModel registrador;
    public String[] CreateRegistrador(String direccionCompleta,String municipio,String departamento)
    {
        registrador = new RegistradorModel(direccionCompleta,municipio,departamento);
        String[] arrayRegistrador = registrador.RegistradorComoArreglo();
        return arrayRegistrador;
    }

}
