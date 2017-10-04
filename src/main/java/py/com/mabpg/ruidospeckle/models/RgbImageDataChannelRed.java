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
@Table(name = "rgb_image_data_channel_red", catalog = CATALOG, schema = SCHEMA, uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_rgb_image"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RgbImageDataChannelRed.findAll", query = "SELECT r FROM RgbImageDataChannelRed r"),
    @NamedQuery(name = "RgbImageDataChannelRed.findByIdRgbImageDataChannelRed", query = "SELECT r FROM RgbImageDataChannelRed r WHERE r.idRgbImageDataChannelRed = :idRgbImageDataChannelRed"),
    @NamedQuery(name = "RgbImageDataChannelRed.findByMean", query = "SELECT r FROM RgbImageDataChannelRed r WHERE r.mean = :mean"),
    @NamedQuery(name = "RgbImageDataChannelRed.findByStdDev", query = "SELECT r FROM RgbImageDataChannelRed r WHERE r.stdDev = :stdDev")})
public class RgbImageDataChannelRed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rgb_image_data_channel_red", nullable = false)
    private Integer idRgbImageDataChannelRed;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mean", precision = 17, scale = 17)
    private Double mean;
    @Column(name = "std_dev", precision = 17, scale = 17)
    private Double stdDev;
    @JoinColumn(name = "id_rgb_image", referencedColumnName = "id_rgb_image", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private RgbImage idRgbImage;

    public RgbImageDataChannelRed() {
    }

    public RgbImageDataChannelRed(Integer idRgbImageDataChannelRed) {
        this.idRgbImageDataChannelRed = idRgbImageDataChannelRed;
    }

    public Integer getIdRgbImageDataChannelRed() {
        return idRgbImageDataChannelRed;
    }

    public void setIdRgbImageDataChannelRed(Integer idRgbImageDataChannelRed) {
        this.idRgbImageDataChannelRed = idRgbImageDataChannelRed;
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
        hash += (idRgbImageDataChannelRed != null ? idRgbImageDataChannelRed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RgbImageDataChannelRed)) {
            return false;
        }
        RgbImageDataChannelRed other = (RgbImageDataChannelRed) object;
        if ((this.idRgbImageDataChannelRed == null && other.idRgbImageDataChannelRed != null) || (this.idRgbImageDataChannelRed != null && !this.idRgbImageDataChannelRed.equals(other.idRgbImageDataChannelRed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.daas.RgbImageDataChannelRed[ idRgbImageDataChannelRed=" + idRgbImageDataChannelRed + " ]";
    }
    
}
