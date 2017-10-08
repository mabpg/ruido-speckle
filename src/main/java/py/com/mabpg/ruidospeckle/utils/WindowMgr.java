/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mabpg.ruidospeckle.utils;

import ij.gui.Roi;
import ij.process.ByteProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import py.com.mabpg.ruidospeckle.models.RgbImage;
import py.com.mabpg.ruidospeckle.models.RgbWindow;

/**
 *
 * @author Derlis Argüello
 */
public class WindowMgr {
    static final Logger logger = LoggerFactory.getLogger(WindowMgr.class);

    private RgbImage rgbImage;
    private List<Integer> windowCount = new ArrayList<>();
    private List<RgbWindow> windowsList = new ArrayList<>();

    public WindowMgr(RgbImage rgbImage, List<Integer> windowCount) {
        this.rgbImage = rgbImage;
        this.windowCount = windowCount;
    }
    
    public void setWindowsList() {
        //variables
        Roi roi;
        int totalPixels;
        int roiWidthAux;
        int roiHeightAux;
        int xStart = 0, yStart = 0;
        int roiWidth;
        int roiHeight;
        int windowNumber = 1;
        int width = rgbImage.getWidth();
        int height = rgbImage.getHeight();
        RgbWindow window;
        
        for (Integer wRoi: windowCount){
            roiWidth = (int)Math.ceil((double)width / (double)wRoi);
            roiHeight = (int)Math.ceil((double)height / (double)wRoi);
            for (int yr = 0; yr < wRoi; yr++) { // Y
                roiWidthAux = roiWidth;
                roiHeightAux = roiHeight;
                for (int xr = 0; xr < wRoi; xr++) { // X
                    if(xStart + roiWidth > width){
                        roiWidthAux = width - xStart;
                    }
                    if(yStart + roiHeight > height){
                        roiHeightAux = height - yStart;
                    }
                    roi = new Roi(xStart, yStart, roiWidthAux, roiHeightAux);
                    totalPixels = roiWidthAux * roiHeightAux;
                    
                    window = new RgbWindow(rgbImage, roi, xStart, yStart, roiWidthAux, roiHeightAux, 
                            totalPixels, wRoi, windowNumber);
                    
                    windowNumber = setWindowData(window);
                    //logger.debug("window = {}", window.toString());
                    
                    xStart += roiWidthAux; // SHIFT STARTING X
                }
                xStart = 0;
                yStart += roiHeightAux; // SHIFT STARTING Y
            }
            
            xStart = 0;
            yStart = 0;
        }
        
        rgbImage.setWindowList(windowsList);
    }
    
    private int setWindowData(RgbWindow dadWindow) {
        ByteProcessor[] channels = rgbImage.getChannels();
        int cSize = channels.length;
        int hSize = channels[0].getHistogramSize();
        int[][] channelHistogram = new int[cSize][hSize];
        
        double[] firstMoment  = new double[cSize];
        
        double[] variance = new double[cSize];
        int[] mode = new int[cSize];
        int[] min = new int[cSize];
        int[] max = new int[cSize];
        double[] energy = new double[cSize];
        double[] entropy = new double[cSize];
        double[] skewness = new double[cSize];
        double[] kurtosis = new double[cSize];
        double[] thirdCentralMoment = new double[cSize];
        double[] fourthCentralMoment = new double[cSize];
        double[] fifthCentralMoment = new double[cSize];
        double[] sixthCentralMoment = new double[cSize];
        double[] seventhCentralMoment = new double[cSize];
        double[] eighthCentralMoment = new double[cSize];
        double[] smoothness = new double[cSize];
        double p;
        
        int numOfPixels = dadWindow.getPixelCount();
        
        RgbWindow windowByChannel;
        int wCount = dadWindow.getRgbWindowNumber();
        
        for (int channel = 0; channel < cSize; channel++) {
            channels[channel].setRoi(dadWindow.getRoi());
            channelHistogram[channel] = channels[channel].getHistogram();
            
            for (int intensity = 0; intensity < hSize; intensity++) {
                //se calcula la sumatoria de intensidad por valor de histograma de esa intensidad
                firstMoment[channel] += (intensity) * channelHistogram[channel][intensity];
            }
            //media
            firstMoment[channel] = firstMoment[channel] / (double)numOfPixels;
            
            int modeValue = 0;
            min[channel] = Integer.MAX_VALUE;
            max[channel] = Integer.MIN_VALUE;
            for (int intensity = 0; intensity < hSize; intensity++) {
                //mode
                if ( channelHistogram[channel][intensity] > modeValue ) {
                    modeValue = channelHistogram[channel][intensity];
                    mode[channel] = intensity;
                }
                //min
                if ( (channelHistogram[channel][intensity] > 0) && (intensity < min[channel]) ) {
                    min[channel] = intensity;
                }
                //max
                if ( (channelHistogram[channel][intensity] > 0) && ( intensity > max[channel] ) ) {
                    max[channel] = intensity;
                }
                //energy
                p = (double)channelHistogram[channel][intensity]/(double)(numOfPixels);
                energy[channel] += Math.pow(p, 2);
                //entropy
                if(channelHistogram[channel][intensity] != 0){
                    entropy[channel] -= (p * (Math.log(p)/Math.log(2)));
                }
                double centeredDifference = (double)intensity - firstMoment[channel];
                //variance
                variance[channel] += Math.pow(centeredDifference, 2) * p;
                //third central moment
                thirdCentralMoment[channel] += Math.pow(centeredDifference, 3) * p;
                //fourth central moment
                fourthCentralMoment[channel] += Math.pow(centeredDifference, 4) * p;
                //de momento no haremos los momentos de orden superior
                /*
                fifthCentralMoment[channel] += Math.pow(centeredDifference, 5) * p;
                sixthCentralMoment[channel] += Math.pow(centeredDifference, 6) * p;
                seventhCentralMoment[channel] += Math.pow(centeredDifference, 7) * p;
                eighthCentralMoment[channel] += Math.pow(centeredDifference, 8) * p;
                */
                
            }
            
            skewness[channel] = thirdCentralMoment[channel]/Math.pow(variance[channel], 1.5);
            kurtosis[channel] = fourthCentralMoment[channel]/Math.pow(variance[channel], 2);
            smoothness[channel] = 1d - (1d/(1d + variance[channel]));
                        
            //add window
            windowByChannel = new RgbWindow(dadWindow.getIdRgbImage(), dadWindow.getRoi(), dadWindow.getX(), 
                    dadWindow.getY(), dadWindow.getWidth(), dadWindow.getHeight(), numOfPixels, dadWindow.getRgbWindowCount(), wCount);
            windowByChannel.setChannel(channel);
            windowByChannel.setMean(firstMoment[channel]);
            windowByChannel.setVariance(variance[channel]);
            windowByChannel.setModes(Double.valueOf(mode[channel]));
            windowByChannel.setMinimum(Double.valueOf(min[channel]));
            windowByChannel.setMaximum(Double.valueOf(max[channel]));
            windowByChannel.setEnergy(energy[channel]);
            windowByChannel.setEntropy(entropy[channel]);
            windowByChannel.setSkewness(skewness[channel]);
            windowByChannel.setKurtosis(kurtosis[channel]);
            windowByChannel.setThirdCentralMoment(thirdCentralMoment[channel]);
            windowByChannel.setFourthCentralMoment(fourthCentralMoment[channel]);
            windowByChannel.setFifthCentralMoment(fifthCentralMoment[channel]);
            windowByChannel.setSixthCentralMoment(sixthCentralMoment[channel]);
            windowByChannel.setSeventhCentralMoment(seventhCentralMoment[channel]);
            windowByChannel.setEighthCentralMoment(eighthCentralMoment[channel]);
            windowByChannel.setSmoothness(smoothness[channel]);
            windowsList.add(windowByChannel);
            
            wCount++;
        }
        
        return wCount;
    }
       
}