import models.ConsumoAnualModel;

public class consumoController{

    public String RevisarConsumo(){
        int años = 1;
        int meses = 1;
        int dias = 1;
        int horas = 1;
        ConsumoAnualModel  ConsumoCliente = new ConsumoAnualModel(años,meses,dias,horas);
        
        System.out.println("");

        return "s";
    }


}