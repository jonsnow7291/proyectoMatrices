package models;

public class ClienteModel {
    private final int numeroIdentificacion;
    private String tipoIdentificacion;
    private String direccionFisica;
    private String nombreCompleto;
    private String correoElectronico;
    private int CodigoRegistrador;

    //TODO: atributos  para asignacion

    public ClienteModel(int nIdentificacion,String tIdentificacion,String direccion,String nombre,String correo){
        this.numeroIdentificacion = nIdentificacion;
        this.tipoIdentificacion = tIdentificacion;
        this.direccionFisica = direccion;
        this.nombreCompleto = nombre;
        this.correoElectronico = correo;
    }

    public void setRegistrador(int registrador){
        this.CodigoRegistrador = registrador;
    }

    public void putRegistrador(int registrador){
        this.CodigoRegistrador = registrador;
    }

    public void UpdateClient (String direccion,String nombre,String correo){
        this.direccionFisica = direccion;
        this.nombreCompleto = nombre;
        this.correoElectronico = correo;
    }


}
