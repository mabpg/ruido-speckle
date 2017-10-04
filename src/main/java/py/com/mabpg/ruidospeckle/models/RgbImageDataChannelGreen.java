package py.com.mabpg.ruidospeckle.models;

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
@Table(name = "rgb_image_data_channel_green", catalog = CATALOG, schema = SCHEMA, uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_rgb_image"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RgbImageDataChannelGreen.findAll", query = "SELECT r FROM RgbImageDataChannelGreen r"),
    @NamedQuery(name = "RgbImageDataChannelGreen.findByIdRgbImageDataChannelGreen", query = "SELECT r FROM RgbImageDataChannelGreen r WHERE r.idRgbImageDataChannelGreen = :idRgbImageDataChannelGreen"),
    @NamedQuery(name = "RgbImageDataChannelGreen.findByMean", query = "SELECT r FROM RgbImageDataChannelGreen r WHERE r.mean = :mean"),
    @NamedQuery(name = "RgbImageDataChannelGreen.findByStdDev", query = "SELECT r FROM RgbImageDataChannelGreen r WHERE r.stdDev = :stdDev")})
public class RgbImageDataChannelGreen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rgb_image_data_channel_green", nullable = false)
    private Integer idRgbImageDataChannelGreen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mean", precision = 17, scale = 17)
    private Double mean;
    @Column(name = "std_dev", precision = 17, scale = 17)
    private Double stdDev;
    @JoinColumn(name = "id_rgb_image", referencedColumnName = "id_rgb_image", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private RgbImage idRgbImage;

    public RgbImageDataChannelGreen() {
    }

    public RgbImageDataChannelGreen(Integer idRgbImageDataChannelGreen) {
        this.idRgbImageDataChannelGreen = idRgbImageDataChannelGreen;
    }

    public Integer getIdRgbImageDataChannelGreen() {
        return idRgbImageDataChannelGreen;
    }

    public void setIdRgbImageDataChannelGreen(Integer idRgbImageDataChannelGreen) {
        this.idRgbImageDataChannelGreen = idRgbImageDataChannelGreen;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getStdDev() {
        return stdDev;
    }

    public void setStdDev(Double stdDev) {
        this.stdDev = stdDev;
    }

    public RgbImage getIdRgbImage() {
        return idRgbImage;
    }

    public void setIdRgbImage(RgbImage idRgbImage) {
        this.idRgbImage = idRgbImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRgbImageDataChannelGreen != null ? idRgbImageDataChannelGreen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RgbImageDataChannelGreen)) {
            return false;
        }
        RgbImageDataChannelGreen other = (RgbImageDataChannelGreen) object;
        if ((this.idRgbImageDataChannelGreen == null && other.idRgbImageDataChannelGreen != null) || (this.idRgbImageDataChannelGreen != null && !this.idRgbImageDataChannelGreen.equals(other.idRgbImageDataChannelGreen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.daas.RgbImageDataChannelGreen[ idRgbImageDataChannelGreen=" + idRgbImageDataChannelGreen + " ]";
    }
    
}
