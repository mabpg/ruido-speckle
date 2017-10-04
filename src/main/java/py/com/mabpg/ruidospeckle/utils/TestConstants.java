package py.com.mabpg.ruidospeckle.utils;

/**
 *
 * @author Thelma
 */
public class TestConstants {
    
    public static class Ruidos  {
        
        public static class Gaussian{
            public static final String NAME = "gaussian";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
        
        public static class Impulsive{
            public static final String NAME = "impulsive";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
        
        public static class Poisson{
            public static final String NAME  = "poisson";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
        
        public static class Speckle{
            public static final String NAME = "speckle";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
    
        public static class Salt{
            public static final String NAME = "salt";
            public static String[] ALLOWED_FILTERS = {"Min"};
        }
        
        public static class Pepper{
            public static final String NAME = "pepper";
            public static String[] ALLOWED_FILTERS = {"Max"};
        }
        
    }
    
    public static class Filters {
        
        public static class TesisRGB{
            
            public static class ConVentanas{
                public static final String TESIS_RGB_ENTROPY = "TesisRGBEntropy";
                //public static final String TESIS_RGB_ENTROPY_R = "TesisRGBEntropyR";
                public static final String TESIS_RGB_MEAN = "TesisRGBMean";
                public static final String TESIS_RGB_S_DEVIATION = "TesisRGBSdeviation";
                public static final String TESIS_RGB_ASIMETRIC = "TesisRGBAsimetric";
                public static final String TESIS_RGB_ENERGY = "TesisRGBEnergy";
                public static final String TESIS_RGB_COLOR_COUNTER = "TesisRGBColorCounter";
                public static final String TESIS_RGB_MODE = "TesisRGBMode";
                public static final String TESIS_RGB_MAX = "TesisRGBMax";
                public static final String TESIS_RGB_MIN = "TesisRGBMin";
                public static final String TESIS_RGB_SMOOTHNESS = "TesisRGBSmoothness";
                //public static final String TESIS_RGB_UNIFORMITY = "TesisRGBUniformity";
            }

            public static class SinVentanas{
                //public static final String TESIS_RGB_ENTROPY_R_WW = "TesisRGBEntropyRWW";
                public static final String TESIS_RGB_ENTROPY_WW = "TesisRGBEntropyWW";
                public static final String TESIS_RGB_MEAN_WW = "TesisRGBMeanWW";
                public static final String TESIS_RGB_S_DEVIATION_WW = "TesisRGBSdeviationWW";
                public static final String TESIS_RGB_ASIMETRIC_WW = "TesisRGBAsimetricWW";
                public static final String TESIS_RGB_ENERGY_WW = "TesisRGBEnergyWW";
                //public static final String TESIS_RGB_SMOOTHNESS_WW = "TesisRGBSmoothnessWW";
                //public static final String TESIS_RGB_UNIFORMITY_WW = "TesisRGBUniformityWW";
            }
        }

        public static class EstadoDelArte{
            public static final String ALPHA_LEX = "AlphaLexicographical";
            public static final String ALPHA_MODULUS_LEX = "AlphaModulusLexicographical";
            public static final String LEX = "Lexicographical";
            public static final String MARGINAL = "Marginal";
            public static final String BITMIXING = "MedianBitMixingImprovedFilter";
            public static final String EUCLIDEAN = "MedianEuclideanFilter";
            public static final String HSI_LEX = "HsiLexicographical";
            public static final String HSI_ALPHA_LEX = "HsiAlphaLexicographical";
            public static final String METRIC_OF_DISTANCE = "MetricOfDistance";
        }       
    }
    
}