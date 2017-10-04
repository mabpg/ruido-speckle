package py.com.mabpg.ruidospeckle.models;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import static py.com.mabpg.ruidospeckle.models.Constant.CATALOG;
import static py.com.mabpg.ruidospeckle.models.Constant.SCHEMA;

/**
 *
 * @author Derlis Argüello
 */
@Entity
@Table(name = "rgb_image_data_channel_blue", catalog = CATALOG, schema = SCHEMA, uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_rgb_image"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RgbImageDataChannelBlue.findAll", query = "SELECT r FROM RgbImageDataChannelBlue r"),
    @NamedQuery(name = "RgbImageDataChannelBlue.findByIdRgbImageDataChannelBlue", query = "SELECT r FROM RgbImageDataChannelBlue r WHERE r.idRgbImageDataChannelBlue = :idRgbImageDataChannelBlue"),
    @NamedQuery(name = "RgbImageDataChannelBlue.findByMean", query = "SELECT r FROM RgbImageDataChannelBlue r WHERE r.mean = :mean"),
    @NamedQuery(name = "RgbImageDataChannelBlue.findByStdDev", query = "SELECT r FROM RgbImageDataChannelBlue r WHERE r.stdDev = :stdDev")})
public class RgbImageDataChannelBlue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rgb_image_data_channel_blue", nullable = false)
    private Integer idRgbImageDataChannelBlue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mean", precision = 17, scale = 17)
    private Double mean;
    @Column(name = "std_dev", precision = 17, scale = 17)
    private Double stdDev;
    @JoinColumn(name = "id_rgb_image", referencedColumnName = "id_rgb_image", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private RgbImage idRgbImage;

    public RgbImageDataChannelBlue() {
    }

    public RgbImageDataChannelBlue(Integer idRgbImageDataChannelBlue) {
        this.idRgbImageDataChannelBlue = idRgbImageDataChannelBlue;
    }

    public Integer getIdRgbImageDataChannelBlue() {
        return idRgbImageDataChannelBlue;
    }

    public void setIdRgbImageDataChannelBlue(Integer idRgbImageDataChannelBlue) {
        this.idRgbImageDataChannelBlue = idRgbImageDataChannelBlue;
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
        hash += (idRgbImageDataChannelBlue != null ? idRgbImageDataChannelBlue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RgbImageDataChannelBlue)) {
            return false;
        }
        RgbImageDataChannelBlue other = (RgbImageDataChannelBlue) object;
        if ((this.idRgbImageDataChannelBlue == null && other.idRgbImageDataChannelBlue != null) || (this.idRgbImageDataChannelBlue != null && !this.idRgbImageDataChannelBlue.equals(other.idRgbImageDataChannelBlue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.daas.RgbImageDataChannelBlue[ idRgbImageDataChannelBlue=" + idRgbImageDataChannelBlue + " ]";
    }
    
}
