package py.com.mabpg.ruidospeckle.models;

import ij.process.ByteProcessor;
import ij.process.ColorProcessor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static py.com.mabpg.ruidospeckle.models.Constant.CATALOG;
import static py.com.mabpg.ruidospeckle.models.Constant.CHANNEL_BLUE;
import static py.com.mabpg.ruidospeckle.models.Constant.CHANNEL_GREEN;
import static py.com.mabpg.ruidospeckle.models.Constant.CHANNEL_RED;
import static py.com.mabpg.ruidospeckle.models.Constant.SCHEMA;

/**
 *
 * @author Derlis Argüello
 */
@Entity
@Table(name = "rgb_image", catalog = CATALOG, schema = SCHEMA)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RgbImage.findAll", query = "SELECT r FROM RgbImage r"),
    @NamedQuery(name = "RgbImage.findByIdRgbImage", query = "SELECT r FROM RgbImage r WHERE r.idRgbImage = :idRgbImage"),
    @NamedQuery(name = "RgbImage.findByWidth", query = "SELECT r FROM RgbImage r WHERE r.width = :width"),
    @NamedQuery(name = "RgbImage.findByHeight", query = "SELECT r FROM RgbImage r WHERE r.height = :height"),
    //@NamedQuery(name = "RgbImage.getWindosList", query = "select w from RgbWindow w join RgbImage i where w.idRgbImage.idRgbImage = i.idRgbImage and i.idRgbImage = :idRgbImage and w.windowCount = :windowCount order by w.windowNumber"),
    @NamedQuery(name = "RgbImage.getImageTest", query = "select r from RgbImage r where r.noiseName = 'original' and r.description BETWEEN :minIndex AND :maxIndex order by r.description asc"),
    @NamedQuery(name = "RgbImage.getNoiseImageByNoise", query = "select r from RgbImage r where r.noiseName = :noiseName and r.description = :description order by r.description asc"),
    @NamedQuery(name = "RgbImage.getWindowListByImage", query = "select r from RgbImage r where r.noiseName = :noiseName and r.description = :description order by r.description asc"),
    @NamedQuery(name = "RgbImage.findByExtension", query = "SELECT r FROM RgbImage r WHERE r.extension = :extension")})
public class RgbImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rgb_image", nullable = false)
    private Integer idRgbImage;
    @Lob
    @Column(name = "channel_red")
    private byte[] channelRed;
    @Lob
    @Column(name = "channel_green")
    private byte[] channelGreen;
    @Lob
    @Column(name = "channel_blue")
    private byte[] channelBlue;
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;
    @Column(name = "extension", length = 5)
    private String extension;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idRgbImage", fetch = FetchType.LAZY)
    private RgbImageDataChannelGreen rgbImageDataChannelGreen;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idRgbImage", fetch = FetchType.LAZY)
    private RgbImageDataChannelRed rgbImageDataChannelRed;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idRgbImage", fetch = FetchType.LAZY)
    //private List<RgbWindow> windowList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idRgbImage", fetch = FetchType.LAZY)
    private RgbImageDataChannelBlue rgbImageDataChannelBlue;
    @Column(name = "noise_name", length = 200)
    private String noiseName;
    @Column(name = "noise_probability", precision = 17, scale = 17)
    private Double noiseProbability;
    @Column(name = "description", length = 200)
    private Double description;
    
    @Transient
    private ByteProcessor[] channels;

    public RgbImage() {
    }
    
    public RgbImage(ColorProcessor colorProcessor, String extension) {
        
        this.width = colorProcessor.getWidth();
        this.height = colorProcessor.getHeight();
        this.extension = extension;
        setChannelData(colorProcessor);
        setDataChannels();
        
    }

    public RgbImage(Integer idRgbImage) {
        this.idRgbImage = idRgbImage;
    }

    public Integer getIdRgbImage() {
        return idRgbImage;
    }

    public void setIdRgbImage(Integer idRgbImage) {
        this.idRgbImage = idRgbImage;
    }

    public byte[] getChannelRed() {
        return channelRed;
    }

    public void setChannelRed(byte[] channelRed) {
        this.channelRed = channelRed;
    }

    public byte[] getChannelGreen() {
        return channelGreen;
    }

    public void setChannelGreen(byte[] channelGreen) {
        this.channelGreen = channelGreen;
    }

    public byte[] getChannelBlue() {
        return channelBlue;
    }

    public void setChannelBlue(byte[] channelBlue) {
        this.channelBlue = channelBlue;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public RgbImageDataChannelGreen getRgbImageDataChannelGreen() {
        return rgbImageDataChannelGreen;
    }

    public void setRgbImageDataChannelGreen(RgbImageDataChannelGreen rgbImageDataChannelGreen) {
        this.rgbImageDataChannelGreen = rgbImageDataChannelGreen;
    }

    public RgbImageDataChannelRed getRgbImageDataChannelRed() {
        return rgbImageDataChannelRed;
    }

    public void setRgbImageDataChannelRed(RgbImageDataChannelRed rgbImageDataChannelRed) {
        this.rgbImageDataChannelRed = rgbImageDataChannelRed;
    }

    /*@XmlTransient
    public List<RgbWindow> getWindowList() {
        return windowList;
    }
    
    public void setWindowList(List<RgbWindow> windowList) {
        this.windowList = windowList;
    }*/

    public RgbImageDataChannelBlue getRgbImageDataChannelBlue() {
        return rgbImageDataChannelBlue;
    }

    public void setRgbImageDataChannelBlue(RgbImageDataChannelBlue rgbImageDataChannelBlue) {
        this.rgbImageDataChannelBlue = rgbImageDataChannelBlue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRgbImage != null ? idRgbImage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RgbImage)) {
            return false;
        }
        RgbImage other = (RgbImage) object;
        if ((this.idRgbImage == null && other.idRgbImage != null) || (this.idRgbImage != null && !this.idRgbImage.equals(other.idRgbImage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RgbImage[ idRgbImage=" + idRgbImage + " ]";
    }
    
    private void setDataChannels() {
        int cSize = channels.length;
        int hSize = channels[0].getHistogramSize();
        int N = width*height;
        int[][] channelHistogram = new int[cSize][hSize];
        double[] mean_sum = {0, 0, 0};
        double[] std_dev_sum = {0, 0, 0};
        double mean = 0, std_dev = 0;

        for (int channel = 0; channel < channels.length; channel++) {
            channelHistogram[channel] = channels[channel].getHistogram();
            for (int intensity = 0; intensity < hSize; intensity++) {
                //se calcula la sumatoria de intensidad por valor de histograma de esa intensidad
                mean_sum[channel] += (intensity) * channelHistogram[channel][intensity];
            }
            //1) la media
            mean = (double)mean_sum[channel]/(double)(N);
            
            for (int intensity = 0; intensity < hSize; intensity++) {
                //se calcula la sumatoria de intensidad por valor de histograma de esa intensidad
                std_dev_sum[channel] += Math.pow((intensity - mean), 2) * channelHistogram[channel][intensity];
            }
            
            //2) la desviacion estandar
            std_dev = Math.sqrt((double)std_dev_sum[channel]/(double)(N));
            
            switch(channel+1){
                case CHANNEL_RED:
                    this.rgbImageDataChannelRed = new RgbImageDataChannelRed();
                    this.rgbImageDataChannelRed.setIdRgbImage(this);
                    this.rgbImageDataChannelRed.setMean(mean);
                    this.rgbImageDataChannelRed.setStdDev(std_dev);
                case CHANNEL_GREEN:
                    this.rgbImageDataChannelGreen = new RgbImageDataChannelGreen();
                    this.rgbImageDataChannelGreen.setIdRgbImage(this);
                    this.rgbImageDataChannelGreen.setMean(mean);
                    this.rgbImageDataChannelGreen.setStdDev(std_dev);
                case CHANNEL_BLUE:
                    this.rgbImageDataChannelBlue = new RgbImageDataChannelBlue();
                    this.rgbImageDataChannelBlue.setIdRgbImage(this);
                    this.rgbImageDataChannelBlue.setMean(mean);
                    this.rgbImageDataChannelBlue.setStdDev(std_dev);
            }
        }
    }

    public ColorProcessor getColorProcessor(){
        ColorProcessor colorProcessor = new ColorProcessor(width, height);
        colorProcessor.setRGB(channelRed, channelGreen, channelBlue);
        return colorProcessor;
    }

    public ByteProcessor[] getChannels() {
        return channels;
    }

    public String getNoiseName() {
        return noiseName;
    }

    public void setNoiseName(String noiseName) {
        this.noiseName = noiseName;
    }

    public Double getDescription() {
        return description;
    }

    public void setDescription(Double description) {
        this.description = description;
    }

    public Double getNoiseProbability() {
        return noiseProbability;
    }

    public void setNoiseProbability(Double noiseProbability) {
        this.noiseProbability = noiseProbability;
    }
    //when we store a new image
    private void setChannelData(ColorProcessor colorProcessor){
        channels = new ByteProcessor[colorProcessor.getNChannels()];
        for (int channel = 0; channel < channels.length ; channel++) {
            switch(channel+1){
                case CHANNEL_RED:
                    this.channels[channel] = colorProcessor.getChannel(channel + 1, this.channels[channel]);
                    this.channelRed = colorProcessor.getChannel(CHANNEL_RED);
                case CHANNEL_GREEN:
                    this.channels[channel] = colorProcessor.getChannel(channel + 1, this.channels[channel]);
                    this.channelGreen = colorProcessor.getChannel(CHANNEL_GREEN);
                case CHANNEL_BLUE:
                    this.channels[channel] = colorProcessor.getChannel(channel + 1, this.channels[channel]);
                    this.channelBlue = colorProcessor.getChannel(CHANNEL_BLUE);
            }
        }
    }
    //when we get the image from the database
    public void setChannelData(){
        ColorProcessor colorProcessor = new ColorProcessor(width, height);
        colorProcessor.setRGB(getChannelRed(), getChannelGreen(), getChannelBlue());
        setChannelData(colorProcessor);
    }
}