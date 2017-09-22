package py.com.mabpg.ruidospeckle.models;

import org.slf4j.LoggerFactory;
import py.com.mabpg.ruidospeckle.main.Main;

public class SpeckleFilterMedian {

    public final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    public SpeckleFilterMedian() {

    }

    public int hallandoPesos(int[][] matrizEntrada) {
        double cantFilas = (double) matrizEntrada.length;
        double cantColumnas = (double) matrizEntrada[0].length;

        int elementoMediana = -1;

        if (cantFilas > 0 && cantColumnas > 0) {
            /*HALLAMOS LA MEDIA DE LA VENTANA*/
            int media = hallarMedia(matrizEntrada);

            /*Hallamos la varianza*/
            double varianza = hallarVarianza(media, matrizEntrada);

            Pixel posicionCentral = new Pixel((int) Math.round(cantFilas / 2), (int) Math.round(cantColumnas / 2));

            //armar un objeto que tenga dos valores, para guardar el elemento y el peso asociado
            FusionMatrices[][] matrizFusion = new FusionMatrices[(int) cantFilas][(int) cantColumnas];

            //HALLAMOS LA primera mitad DE LA FORMULA PARA HALLAR EL PESO
            int[] mitadFormulaPeso = new int[(int) cantFilas * (int) cantColumnas];
            int indice = 0;
            int max = -1;

            for (int i = 1; i <= matrizEntrada.length; i++) {
                double distancia = 0;
                for (int j = 1; j <= matrizEntrada[0].length; j++) {

                    distancia = Math.round(Math.sqrt(Math.pow((posicionCentral.getX() - i), 2) + Math.pow((posicionCentral.getY() - j), 2)));
                    mitadFormulaPeso[indice] = (int) Math.round(SpeckleConfigurations.c * distancia * varianza / media);
                    //System.out.println(" mitadFormula " + SpeckleConfigurations.c*distancia*varianza/media);
                    if (indice < matrizEntrada.length * matrizEntrada[0].length) {
                        if (mitadFormulaPeso[indice] > max) {
                            max = mitadFormulaPeso[indice];
                        }
                    }
                    indice++;
                }
            }
			//System.out.println();
			/*System.out.println(" mitadFormula ");
             for(int j=0;j<mitadFormulaPeso.length;j++) {
             System.out.print(mitadFormulaPeso[j] + " ");
             }
             System.out.println();*/
            //peso del elemento central se agrega directamente
            int pesoElementoCentral = max + 2;

            //se completan los demas pesos de cada elemento
            double sumaCantidadPesos = 0.0;
            int peso = 0;
            indice = 0;
            for (int i = 0; i < matrizEntrada.length; i++) {
                for (int j = 0; j < matrizEntrada[0].length; j++) {
                    matrizFusion[i][j] = new FusionMatrices();
                    matrizFusion[i][j].setElemento(matrizEntrada[i][j]);
                    peso = pesoElementoCentral - mitadFormulaPeso[indice];
                    sumaCantidadPesos = sumaCantidadPesos + peso;
                    matrizFusion[i][j].setPeso(peso);
                    indice++;
                }
            }
            //System.out.println();
            //System.out.println();
            //PARA HALLAR LA MEDIANA DE LOS ELEMENTOS DE LA MATRIZ DE ENTRADA
            sortMatriz(matrizFusion);
            //printMatrizFusion(matrizFusion);
            //System.out.println(" Mediana " + sumaCantidadPesos);
            sumaCantidadPesos = (int) Math.round(sumaCantidadPesos / 2);
            int i = 0;
            int j = 0;
            boolean salir = false;

            for (i = 0; i < matrizFusion.length; i++) {
                for (j = 0; j < matrizFusion[0].length; j++) {
                    if (sumaCantidadPesos - matrizFusion[i][j].getPeso() >= 0) {
                        sumaCantidadPesos = sumaCantidadPesos - matrizFusion[i][j].getPeso();
                    } else {
                        salir = true;
                        break;
                    }
                }
                if (salir) {
                    break;
                }
            }
            elementoMediana = matrizFusion[i][j].getElemento();
            System.out.println("media " + media + " varianza " + varianza + " mediana " + elementoMediana);
        }
        return elementoMediana;
    }

    public void printMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMatrizFusion(FusionMatrices[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j].getElemento() + "-" + matriz[i][j].getPeso() + " ");
            }
            System.out.println();
        }
    }

    public int hallarMedia(int[][] matriz) {
        int sumElementos = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sumElementos = sumElementos + matriz[i][j];
            }
        }
        //System.out.println("Media " + (int)Math.round(sumElementos/(matriz.length*matriz[0].length)));
        return (int) Math.round((sumElementos / (matriz.length * matriz[0].length)));
    }

    public double hallarVarianza(int media, int[][] matriz) {
        double sumaCuadrados = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sumaCuadrados = sumaCuadrados + Math.pow((matriz[i][j] - media), 2);
                //logger.debug(" {} op {}",(int)Math.pow((matriz[i][j]-media), 2),Math.pow((matriz[i][j]-media), 2));
            }
        }
        return Math.round(sumaCuadrados / (matriz.length * matriz[0].length));
    }

    public void sortMatriz(FusionMatrices[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {//ordena la matriz de abajo hacia arriba
            FusionMatrices t = new FusionMatrices();

            for (int j = 0; j < matriz[0].length; j++) {
                for (int x = 0; x < matriz.length; x++) {
                    for (int y = 0; y < matriz[0].length; y++) {
                        if (matriz[i][j].getElemento() < matriz[x][y].getElemento()) {
                            t.setElemento(matriz[i][j].getElemento());
                            t.setPeso(matriz[i][j].getPeso());
                            matriz[i][j].setElemento(matriz[x][y].getElemento());
                            matriz[i][j].setPeso((matriz[x][y].getPeso()));
                            matriz[x][y].setElemento(t.getElemento());
                            matriz[x][y].setPeso(t.getPeso());
                        }
                    }
                }
            }
        }

    }
}
