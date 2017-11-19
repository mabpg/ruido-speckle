package py.com.mabpg.ruidospeckle.utils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import py.com.mabpg.ruidospeckle.models.RgbImage;
import py.com.mabpg.ruidospeckle.utils.exceptions.PreexistingEntityException;

/**
 *
 * @author Derlis Argüello
 */
public class RgbImageJpaController implements Serializable {

    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("py.com.mabpg_ruido-speckle_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RgbImage rgbImage) throws PreexistingEntityException, Exception {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rgbImage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRgbImage(rgbImage.getIdRgbImage()) != null) {
                throw new PreexistingEntityException("RgbImage " + rgbImage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public RgbImage findRgbImage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RgbImage.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<RgbImage> getImageTest(int minIndex, int maxIndex){
        EntityManager em = getEntityManager();
        return (List<RgbImage>)em.createNamedQuery("RgbImage.getImageTest")
                .setParameter("minIndex", Double.valueOf(minIndex))
                .setParameter("maxIndex", Double.valueOf(maxIndex))
                .getResultList();
    }
    
    public List<RgbImage> getNoiseImageByNoise(String noiseName, Double description){
        EntityManager em = getEntityManager();
        return (List<RgbImage>)em.createNamedQuery("RgbImage.getNoiseImageByNoise")
                .setParameter("noiseName", noiseName)
                .setParameter("description", description)
                .getResultList();
    }
}
