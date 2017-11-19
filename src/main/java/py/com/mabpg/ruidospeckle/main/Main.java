package py.com.mabpg.ruidospeckle.main;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.slf4j.LoggerFactory;
import py.com.mabpg.ruidospeckle.models.RgbImage;
import py.com.mabpg.ruidospeckle.utils.RgbImageJpaController;

import py.com.mabpg.ruidospeckle.utils.TestConfig;
import py.com.mabpg.ruidospeckle.utils.TestConstants;
import py.com.mabpg.ruidospeckle.utils.WindowMgr;

public class Main {

    static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        TestConfig config = new TestConfig();
        RgbImage image;
        RgbImageJpaController rgbImageJpaController = new RgbImageJpaController();
        String noiseProbability;

        /**
         * *************************************
         */
        for (int i = config.INDEX_IMAGENES_FROM; i <= config.INDEX_IMAGENES_TO; i++) {
            //leemos la imagen original
            String originalName = "/imagenSpeckle." + config.EXTENSION;
            ImagePlus imgOriginal = new ImagePlus(config.PATH_ORIGINAL_IMAGE + originalName);
            ColorProcessor colImgOriginal = (ColorProcessor) imgOriginal.getProcessor();

            image = new RgbImage(colImgOriginal, config.EXTENSION);
            image.setNoiseName("original");
            image.setNoiseProbability(null);
            image.setDescription((double) i);
            rgbImageJpaController.create(image);

            //por cada ruido
            for (Class noise : config.RUIDOS) {
                String noiseName = "";
                noiseName = (String) noise.getField("NAME").get(null);
                String basePathNoisyImg = config.BASE_PATH + noiseName + config.NOISY_PATH_SUFFIX;
                String imgName = "img_ruido_" + noiseName;

                BigDecimal probDesde = new BigDecimal(0).setScale(3, RoundingMode.FLOOR);
                int probRuidoCant = 1;
                if (!noiseName.equals(TestConstants.Ruidos.Poisson.NAME)) {
                    probDesde = new BigDecimal(config.PROBABILIDAD_RUIDO_FROM).setScale(3, RoundingMode.HALF_UP);
                    probRuidoCant = config.PROBABILIDAD_RUIDO_CANT;
                }

                String s;

                for (int k = 0; k < probRuidoCant; k++) {
                    s = probDesde.toString();
                    noiseProbability = s;
                    if (s.length() >= 5) {
                        s = s.substring(0, 5);
                    }
                    s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");

                    String underscore = "_";
                    if (s.equals("0")) {
                        s = "";
                        underscore = "";
                    }

                    ImagePlus imgNoise = new ImagePlus(basePathNoisyImg + "/" + imgName + "_" + i + underscore + s + "." + config.EXTENSION);
                    ColorProcessor colImgNoise = (ColorProcessor) imgNoise.getProcessor().convertToRGB();
                    image = new RgbImage(colImgNoise, config.EXTENSION);
                    image.setNoiseName(noiseName);
                    image.setNoiseProbability(Double.valueOf(noiseProbability));
                    image.setDescription(Double.valueOf(i));
                    WindowMgr windowMgr = new WindowMgr(image, config.WINDOWSLIST);
                    windowMgr.setWindowsList();
                    rgbImageJpaController.create(image);
                    probDesde = probDesde.add(new BigDecimal(config.PROBABILIDAD_RUIDO_STEP));
                }
            }
        }
        /**
         * ************************************************************************************************
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
