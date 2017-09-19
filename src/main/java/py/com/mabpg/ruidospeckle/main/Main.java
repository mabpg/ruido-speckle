package py.com.mabpg.ruidospeckle.main;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import org.slf4j.LoggerFactory;

import py.com.mabpg.ruidospeckle.utils.TestConfig;

public class Main {

    static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        TestConfig config = new TestConfig();

        /****************************LEEMOS LA IMAGEN*************************/        
        String originalNameGray = "/imagenSpeckle." + config.EXTENSION;
        //ImagePlus imgOriginalGray = new ImagePlus( config.PATH_ORIGINAL_IMAGE + originalNameGray );
        //ImageProcessor imgGray = (ImageProcessor)imgOriginalGray.getProcessor();

        ImagePlus imgOriginal = new ImagePlus(config.PATH_ORIGINAL_IMAGE + originalNameGray);
        ColorProcessor colImgOriginal = (ColorProcessor) imgOriginal.getProcessor();
        /*******************************************************************/
               

        ListaEnlazada listaElementosCambiar = new ListaEnlazada();
        int medianaHallada = 0;
        //DIMENSION DE LA VENTANA
        int[][] porcionVentana = new int[5][5];
        //System.out.println("PIXEEEL " + " " + imgGray + " " + imgOriginalGray + " height " + imgGray.getHeight() + " width " + imgGray.getWidth());
        int[][] matrizImagenParcialCopia = new int[5][5];
        
        
        
        /*******RECORREMOS LA IMAGEN, QUITANDO LA PORCION DE LA VENTANA*******************/
        
        for (int i = 0; i < (colImgOriginal.getHeight() - config.TAMAÑO_VENTANA + 1); i++) {
            for (int j = 0; j < (colImgOriginal.getWidth() - config.TAMAÑO_VENTANA + 1); j++) {
                int d = 0;
                for (int k = i; k < i + config.TAMAÑO_VENTANA; k++) {
                    int e = 0;
                    for (int l = j; l < j + config.TAMAÑO_VENTANA; l++) {
                        porcionVentana[d][e] = colImgOriginal.getPixel(l, k);
                        e++;
                    }
                    d++;
                }
                System.out.println();
                System.out.println("PORCION VENTANA ORIGINAL ");
                printMatrizMain(porcionVentana);
                System.out.println();                
                
                //ENVIAMOS LA PORCION DE LA IMAGEN PARA HALLAR LOS PESOS Y LA MEDIANA
                SpeckleFilterMedian elem = new SpeckleFilterMedian();                
                medianaHallada = elem.hallandoPesos(porcionVentana);
                int filaCentro = i + (int) Math.ceil((config.TAMAÑO_VENTANA) / 2);
                int columnaCentro = j + (int) Math.ceil((config.TAMAÑO_VENTANA) / 2);
                listaElementosCambiar.insertarPrimero(medianaHallada, filaCentro, columnaCentro);
        	
                //break;
            }
            break;
        }               
        /******************************************************************/
        
        System.out.println(" CANTIDAD DE ELEMENTOS de la lista enlazada " + listaElementosCambiar.cuantosElementos());
        Nodo nodList = listaElementosCambiar.quitarPrimero();
        while (nodList != null) {
            colImgOriginal.set(nodList.getValor().getUbicacion().getY(), nodList.getValor().getUbicacion().getX(), nodList.getValor().getMediana());

            System.out.print(" " + nodList.getValor().getMediana() + " x= " + nodList.getValor().getUbicacion().getX() + " y= " + nodList.getValor().getUbicacion().getY());
            nodList = listaElementosCambiar.quitarPrimero();
            System.out.println();

            if (nodList == null) {
                System.out.println("ATENTI YA ES NULL");
            }
        }

        /**
         * ******************** IMPRIMIMOS LA IMAGEN CON LOS CAMBIOS *********************************
         */
        for (int i = 0; i < (colImgOriginal.getHeight() - config.TAMAÑO_VENTANA + 1); i++) {
            for (int j = 0; j < (colImgOriginal.getWidth() - config.TAMAÑO_VENTANA + 1); j++) {
                int d = 0;
                for (int k = i; k < i + config.TAMAÑO_VENTANA; k++) {
                    int e = 0;
                    for (int l = j; l < j + config.TAMAÑO_VENTANA; l++) {
                        matrizImagenParcialCopia[d][e] = colImgOriginal.get(l, k);
                        e++;
                    }
                    d++;
                }
                System.out.println();
                System.out.println(" COPIA ");
                printMatrizMain(matrizImagenParcialCopia);
                //break;
            }
            break;
        }

        /**
         * *****************************************************
         */
//        int [][]matriz = {{144, 144, 145, 147, 148},
//                          {151, 151, 152, 153, 154},
//                          {156, 157, 157, 157, 158},
//                          {157, 157, 157, 157, 158},
//                          {157, 157, 157, 157, 158}};
        //elem.hallandoPesos(matrizImagenParcial);
        /**
         * ************************************************************************
         */
    }

    public static void printMatrizMain(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
