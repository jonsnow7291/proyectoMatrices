package models;

public class RegistradorModel{
    private int codigoIdReg;
    private String direccionCompletaReg;
    private String municipioReg;
    private String departamentoReg;

    public RegistradorModel(String direccionCompleta,String municipio,String departamento){
        int codigoReg = (int)Math.floor(Math.random()*100000 + 100000);
        this.codigoIdReg = codigoReg;
        this.direccionCompletaReg = direccionCompleta;
        this.municipioReg = municipio;
        this.departamentoReg = departamento;
    }
    public String[] RegistradorComoArreglo(){
        String[] clienteArreglo = new String[4];
        clienteArreglo[0] = String.valueOf(codigoIdReg);
        clienteArreglo[1] = direccionCompletaReg;
        clienteArreglo[2] = municipioReg;
        clienteArreglo[3] = departamentoReg;
        return clienteArreglo; 
    } 
} 