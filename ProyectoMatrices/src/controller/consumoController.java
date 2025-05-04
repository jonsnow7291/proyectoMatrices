package controller;
import models.ConsumoModel;

public class consumoController{

    public String RevisarConsumo(){
        int años = 1;
        int meses = 1;
        int dias = 1;
        int horas = 1;
        ConsumoModel  ConsumoCliente = new ConsumoModel(años,meses,dias,horas);
        
        System.out.println("");

        return "s";
    }


}