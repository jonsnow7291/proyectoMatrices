package models;

public class ConsumoModel {
    private int años;
    private int meses;
    private int dias;
    private int horas;
    int[][][][] consumoTotal;
    
    
    public ConsumoModel(int años, int meses,  int dias , int horas){
        this.años = años;
        this.meses = meses;
        this.dias = dias;
        this.horas = horas;
        this.consumoTotal = new int[this.años][this.meses][this.dias][this.horas];
    }

    public int[][][][] llenarCubo(){
        for (int i = 0 ; i == años; i++){
            for (int j = 0 ; j == meses; j++){
                for (int k = 0 ; k == dias; k++){
                    for (int l = 0 ; l == horas; l++){
                        consumoTotal[i][j][k][l] = i + j + k + l;
                    }
                }
            }
        }
        for (int i = 0 ; i < años; i++){
            for (int j = 0 ; j < meses; j++){
                for (int k = 0 ; k < dias; k++){
                    for (int l = 0 ; l < horas; l++){
                        System.out.println("cubo[" + i + "][" + j + "][" + k + "][" + l + "] = " + consumoTotal[i][j][k][l]);
                    }
                }
            }
        }
        return consumoTotal;
    }
}
