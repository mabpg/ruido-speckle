package py.com.mabpg.ruidospeckle.main;

import ij.ImagePlus;
import ij.process.ImageProcessor;

import org.slf4j.LoggerFactory;

import py.com.mabpg.ruidospeckle.utils.TestConfig;

public class Main {
    static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        //imagen original
        TestConfig config = new TestConfig();	
        
        //LEEMOS LA IMAGEN        
        String originalNameGray = "/imagenSpeckle." + config.EXTENSION;
        ImagePlus imgOriginalGray = new ImagePlus( config.PATH_ORIGINAL_IMAGE + originalNameGray );
        ImageProcessor imgGray = (ImageProcessor)imgOriginalGray.getProcessor();
        
        //COPIAMOS LA IMAGEN
        ImageProcessor imgCopiaGray = imgGray;
        int medianaHallada = 0;
                
        //System.out.println("PIXEEEL " + " " + imgGray + " " + imgOriginalGray + " height " + imgGray.getHeight() + " width " + imgGray.getWidth());
        
        //DIMENSION DE LA VENTANA
        int[][] porcionVentana = new int [5][5];
        
        int[][] matrizImagenParcialCopia = new int [5][5];
        //RECORREMOS LA IMAGEN, QUITANDO LA PORCION DE LA VENTANA
        
        for (int i = 0; i < (imgGray.getHeight()-config.TAMAÑO_VENTANA+1); i++) {
        	for (int j = 0; j < (imgGray.getWidth()-config.TAMAÑO_VENTANA+1); j++) {
        		int d = 0;
        		for (int k = i; k < i+config.TAMAÑO_VENTANA; k++) {
        			int e = 0;
                	for (int l = j; l < j+config.TAMAÑO_VENTANA; l++) {                		
                		porcionVentana[d][e] = imgGray.getPixel(l,k);                		
                		e++;
                	}
                	d++;
        		}
        		System.out.println();
        		System.out.println("original ");
                printMatrizMain(porcionVentana);
                System.out.println();
                
        		SpeckleFilterMedian elem = new SpeckleFilterMedian();
        		
        		//ENVIAMOS LA PORCION DE LA IMAGEN PARA HALLAR LOS PESOS Y LA MEDIANA
        		medianaHallada = elem.hallandoPesos(porcionVentana);
        		
        		int filaCentro = i + (int) Math.ceil((config.TAMAÑO_VENTANA)/2);
        		int columnaCentro = j + (int) Math.ceil((config.TAMAÑO_VENTANA)/2);
        		
        		imgCopiaGray.set(columnaCentro, filaCentro, medianaHallada);
        		System.out.println("i= " + i +"j= " + j +" filaC " + filaCentro + " columnC " + columnaCentro + "imgCopia " + imgCopiaGray.get(filaCentro, columnaCentro) + " medianaH " + medianaHallada);
        		        		
        		d = 0;
        		for (int k = i; k < i+config.TAMAÑO_VENTANA; k++) {
        			int e = 0;
                	for (int l = j; l < j+config.TAMAÑO_VENTANA; l++) {
                		matrizImagenParcialCopia[d][e] = imgCopiaGray.get(l, k);                		
//                		if(k == filaCentro && l == columnaCentro ){
//                			System.out.println("CENTRO l=" + l + " k=" + k + " matrizParcial" + matrizImagenParcialCopia[d][e]+" imgCopia"+ imgCopiaGray.getPixel(l, k));
//                		}                		
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
        
//        int [][]matriz = {{144, 144, 145, 147, 148},
//                          {151, 151, 152, 153, 154},
//                          {156, 157, 157, 157, 158},
//                          {157, 157, 157, 157, 158},
//                          {157, 157, 157, 157, 158}};
        
        //elem.hallandoPesos(matrizImagenParcial);
        /***************************************************************************/
    }
    
    public static void printMatrizMain (int[][]matriz) {
		
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}
}