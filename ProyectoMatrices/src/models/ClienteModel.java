package models;

public class ClienteModel {
    private int numeroIdentificacion;
    private String tipoIdentificacion;
    private String direccionFisica;
    private String nombreCompleto;
    private String correoElectronico;
    private int IdRegistrador;

    public ClienteModel(int nIdentificacion,String tIdentificacion,String direccion,String nombre,String correo){
        this.numeroIdentificacion = nIdentificacion;
        this.tipoIdentificacion = tIdentificacion;
        this.direccionFisica = direccion;
        this.nombreCompleto = nombre;
        this.correoElectronico = correo;
    }
    

}
