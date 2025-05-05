package models;

public class RegistradorModel{
    private final int codigoIdReg;
    private String direccionCompletaReg;
    private String municipioReg;
    private String departamentoReg;
    private int clientePropietario;

    public RegistradorModel(String direccionCompleta,String municipio,String departamento){
        int codigoReg = (int)Math.floor(Math.random()*100000 + 100000);
        this.codigoIdReg = codigoReg;
        this.direccionCompletaReg = direccionCompleta;
        this.municipioReg = municipio;
        this.departamentoReg = departamento;
    }
    public void setClientePropietario(int identificacionCliente){
        this.clientePropietario = identificacionCliente;
    }

    public String[] RegistradorComoArreglo(){
        String[] registradorArreglo = new String[5];
        registradorArreglo[0] = String.valueOf(codigoIdReg);
        registradorArreglo[1] = direccionCompletaReg;
        registradorArreglo[2] = municipioReg;
        registradorArreglo[3] = departamentoReg;
        registradorArreglo[4] = String.valueOf(clientePropietario );
        return registradorArreglo; 
    } 
    public void UpdateRegistrador (String direccionCompleta,String municipio,String departamento, Integer identificacionCliente){
        if(identificacionCliente != null )this.clientePropietario = identificacionCliente;
        if(direccionCompleta != null)this.direccionCompletaReg = direccionCompleta;
        if(municipio != null)this.municipioReg = municipio;
        if(departamento != null)this.departamentoReg = departamento;
    }   
} 