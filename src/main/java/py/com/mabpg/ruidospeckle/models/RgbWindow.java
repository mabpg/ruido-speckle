package py.com.mabpg.ruidospeckle.models;

import ij.gui.Roi;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static py.com.mabpg.ruidospeckle.models.Constant.CATALOG;
import static py.com.mabpg.ruidospeckle.models.Constant.SCHEMA;

/**
 *
 * @author Derlis Argüello
 */
@Entity
@Table(name = "rgb_window", catalog = CATALOG, schema = SCHEMA)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RgbWindow.findAll", query = "SELECT w FROM RgbWindow w"),
    @NamedQuery(name = "RgbWindow.findByIdRgbWindow", query = "SELECT w FROM RgbWindow w WHERE w.idWindow = :idWindow"),
    @NamedQuery(name = "RgbWindow.findByPixelCount", query = "SELECT w FROM RgbWindow w WHERE w.pixelCount = :pixelCount"),
    @NamedQuery(name = "RgbWindow.findByRgbWindowCount", query = "SELECT w FROM RgbWindow w WHERE w.windowCount = :windowCount"),
    @NamedQuery(name = "RgbWindow.findByRgbWindowNumber", query = "SELECT w FROM RgbWindow w WHERE w.windowNumber = :windowNumber"),
    @NamedQuery(name = "RgbWindow.findByChannel", query = "SELECT w FROM RgbWindow w WHERE w.channel = :channel"),
    @NamedQuery(name = "RgbWindow.findByMean", query = "SELECT w FROM RgbWindow w WHERE w.mean = :mean"),
    @NamedQuery(name = "RgbWindow.findByStdDev", query = "SELECT w FROM RgbWindow w WHERE w.variance = :variance")})
public class RgbWindow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_window", nullable = false)
    private Integer idWindow;
    @JoinColumn(name = "id_rgb_image", referencedColumnName = "id_rgb_image")
    @ManyToOne(fetch = FetchType.LAZY)
    private RgbImage idRgbImage;
    @Column(name = "x")
    private Integer x;
    @Column(name = "y")
    private Integer y;
    @Column(name = "height")
    private Integer height;
    @Column(name = "width")
    private Integer width;
    @Column(name = "pixel_count")
    private Integer pixelCount;
    @Column(name = "window_count")
    private Integer windowCount;
    @Column(name = "window_number")
    private Integer windowNumber;
    @Column(name = "channel")
    private Integer channel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mean", precision = 17, scale = 17)
    private Double mean;
    @Column(name = "variance", precision = 17, scale = 17)
    private Double variance;
    @Column(name = "modes", precision = 17, scale = 17)
    private Double modes;
    @Column(name = "minimum", precision = 17, scale = 17)
    private Double minimum;
    @Column(name = "maximum", precision = 17, scale = 17)
    private Double maximum;
    @Column(name = "energy", precision = 17, scale = 17)
    private Double energy;
    @Column(name = "entropy", precision = 17, scale = 17)
    private Double entropy;
    @Column(name = "skewness", precision = 17, scale = 17)
    private Double skewness;
    @Column(name = "kurtosis", precision = 17, scale = 17)
    private Double kurtosis;
    @Column(name = "third_central_moment", precision = 17, scale = 17)
    private Double thirdCentralMoment;
    @Column(name = "fourth_central_moment", precision = 17, scale = 17)
    private Double fourthCentralMoment;
    @Column(name = "fifth_central_moment", precision = 17, scale = 17)
    private Double fifthCentralMoment;  
    @Column(name = "sixth_central_moment", precision = 17, scale = 17)
    private Double sixthCentralMoment;  
    @Column(name = "seventh_central_moment", precision = 17, scale = 17)
    private Double seventhCentralMoment;  
    @Column(name = "eighth_central_moment", precision = 17, scale = 17)
    private Double eighthCentralMoment;
    @Column(name = "smoothness", precision = 17, scale = 17)
    private Double smoothness;

    @Transient
    private Roi roi;

    public RgbWindow() {
    }

    public Double getFifthCentralMoment() {
        return fifthCentralMoment;
    }

    public void setFifthCentralMoment(Double fifthCentralMoment) {
        this.fifthCentralMoment = fifthCentralMoment;
    }

    public Double getSixthCentralMoment() {
        return sixthCentralMoment;
    }

    public void setSixthCentralMoment(Double sixthCentralMoment) {
        this.sixthCentralMoment = sixthCentralMoment;
    }

    public Double getSeventhCentralMoment() {
        return seventhCentralMoment;
    }

    public void setSeventhCentralMoment(Double seventhCentralMoment) {
        this.seventhCentralMoment = seventhCentralMoment;
    }

    public Double getEighthCentralMoment() {
        return eighthCentralMoment;
    }

    public void setEighthCentralMoment(Double eighthCentralMoment) {
        this.eighthCentralMoment = eighthCentralMoment;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getWindowCount() {
        return windowCount;
    }

    public void setWindowCount(Integer windowCount) {
        this.windowCount = windowCount;
    }

    public Integer getWindowNumber() {
        return windowNumber;
    }

    public void setWindowNumber(Integer windowNumber) {
        this.windowNumber = windowNumber;
    }

    public Double getModes() {
        return modes;
    }

    public void setModes(Double modes) {
        this.modes = modes;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }
    
    public Roi getRoi() {
        setRoi();
        return roi;
    }
    
    public RgbWindow(Integer idWindow) {
        this.idWindow = idWindow;
    }

    public Integer getIdRgbWindow() {
        return idWindow;
    }

    public void setIdRgbWindow(Integer idWindow) {
        this.idWindow = idWindow;
    }

    public Integer getPixelCount() {
        return pixelCount;
    }

    public void setPixelCount(Integer pixelCount) {
        this.pixelCount = pixelCount;
    }

    public Integer getRgbWindowCount() {
        return windowCount;
    }

    public void setRgbWindowCount(Integer windowCount) {
        this.windowCount = windowCount;
    }

    public Integer getRgbWindowNumber() {
        return windowNumber;
    }

    public void setRgbWindowNumber(Integer windowNumber) {
        this.windowNumber = windowNumber;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public RgbImage getIdRgbImage() {
        return idRgbImage;
    }

    public void setIdRgbImage(RgbImage idRgbImage) {
        this.idRgbImage = idRgbImage;
    }

    public Integer getIdWindow() {
        return idWindow;
    }

    public void setIdWindow(Integer idWindow) {
        this.idWindow = idWindow;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWindow != null ? idWindow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RgbWindow)) {
            return false;
        }
        RgbWindow other = (RgbWindow) object;
        if ((this.idWindow == null && other.idWindow != null) || (this.idWindow != null && !this.idWindow.equals(other.idWindow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RgbWindow[ idWindow=" + roi.toString() + " ]";
    }
    
    public RgbWindow(RgbImage rgbImage, Roi roi, int x, int y, int width, int height, 
            int totalPixels, int windowCount, int windowNumber) {
        this.idRgbImage = rgbImage;
        this.roi = roi;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pixelCount = totalPixels;
        this.windowCount = windowCount;
        this.windowNumber = windowNumber;
    }

    public void setRoi() {
        this.roi = new Roi(x, y, width, height);
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getEntropy() {
        return entropy;
    }

    public void setEntropy(Double entropy) {
        this.entropy = entropy;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    public Double getSkewness() {
        return skewness;
    }

    public void setSkewness(Double skewness) {
        this.skewness = skewness;
    }

    public Double getKurtosis() {
        return kurtosis;
    }

    public void setKurtosis(Double kurtosis) {
        this.kurtosis = kurtosis;
    }

    public Double getThirdCentralMoment() {
        return thirdCentralMoment;
    }

    public void setThirdCentralMoment(Double thirdCentralMoment) {
        this.thirdCentralMoment = thirdCentralMoment;
    }

    public Double getFourthCentralMoment() {
        return fourthCentralMoment;
    }

    public void setFourthCentralMoment(Double fourthCentralMoment) {
        this.fourthCentralMoment = fourthCentralMoment;
    }

    public Double getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(Double smoothness) {
        this.smoothness = smoothness;
    }
}