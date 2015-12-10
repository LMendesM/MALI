package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Station;

public class StationJpaController implements Serializable {

    public StationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Station station) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(station);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Station station) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            station = em.merge(station);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = station.getId();
                if (findStation(id) == null) {
                    throw new NonexistentEntityException("The station with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Station station;
            try {
                station = em.getReference(Station.class, id);
                station.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The station with id " + id + " no longer exists.", enfe);
            }
            em.remove(station);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Station> findStationEntities() {
        return findStationEntities(true, -1, -1);
    }

    public List<Station> findStationEntities(int maxResults, int firstResult) {
        return findStationEntities(false, maxResults, firstResult);
    }

    private List<Station> findStationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Station.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Station findStation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Station.class, id);
        } finally {
            em.close();
        }
    }

    public int getStationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Station> rt = cq.from(Station.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Station findStationByMac(String macAddress) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Station> query = cb.createQuery(Station.class);
        Root<Station> from = query.from(Station.class);
        TypedQuery<Station> typedQuery = em.createQuery(
                query.select(from).where(
                        cb.equal(from.get("mac"), macAddress)
                )
        );
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
