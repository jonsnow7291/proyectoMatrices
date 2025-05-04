package models;

public class ClienteModel {
    private final int numeroIdentificacion;
    private String tipoIdentificacion;
    private String departamentoCliente;
    private String municipioCliente;
    private String direccionFisica;
    private String nombreCompleto;
    private String correoElectronico;
    private int codigoRegistrador;

    public ClienteModel(int nIdentificacion, String tIdentificacion, String direccion, String nombre, String correo,
            String municipio, String departamento) {
        this.numeroIdentificacion = nIdentificacion;
        this.tipoIdentificacion = tIdentificacion;
        this.departamentoCliente = departamento;
        this.municipioCliente = municipio;
        this.direccionFisica = direccion;
        this.nombreCompleto = nombre;
        this.correoElectronico = correo;
    }

    public void setRegistrador(int registrador) {
        this.codigoRegistrador = registrador;
    }

    public void UpdateClient(String direccion, String nombre, String correo,String municipio, String departamento ) {
        this.direccionFisica = nombre;
        this.correoElectronico = correo;
        this.departamentoCliente = departamento;
        this.municipioCliente = municipio;
    }
    //TODO: programar los getters
    public String[] GetClienteComoArreglo(){
        String[] clienteArray =  new String[8];
        clienteArray[0] = String.valueOf(numeroIdentificacion);
        clienteArray[1] = tipoIdentificacion;
        clienteArray[2] = departamentoCliente;
        clienteArray[3] = municipioCliente;
        clienteArray[4] = direccionFisica;
        clienteArray[5] = nombreCompleto;
        clienteArray[6] = correoElectronico;
        clienteArray[7] = String.valueOf(codigoRegistrador);
        return clienteArray;
    }


}
