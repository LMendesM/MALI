package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Process;
import model.Station;

public class ProcessJpaController implements Serializable {

    public ProcessJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Process process) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(process);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Process process) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            process = em.merge(process);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = process.getId();
                if (findProcess(id) == null) {
                    throw new NonexistentEntityException("The process with id " + id + " no longer exists.");
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
            Process process;
            try {
                process = em.getReference(Process.class, id);
                process.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The process with id " + id + " no longer exists.", enfe);
            }
            em.remove(process);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Process> findProcessEntities() {
        return findProcessEntities(true, -1, -1);
    }

    public List<Process> findProcessEntities(int maxResults, int firstResult) {
        return findProcessEntities(false, maxResults, firstResult);
    }

    private List<Process> findProcessEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Process.class));
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

    public Process findProcess(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Process.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcessCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Process> rt = cq.from(Process.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Process> findProcessesByStation(Station station) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Process> query = cb.createQuery(Process.class);
        Root<Process> from = query.from(Process.class);
        TypedQuery<Process> typedQuery = em.createQuery(
                query.select(from).where(
                        cb.equal(from.join("station").get("id"), station.getId())
                )
        );
        List<Process> results = typedQuery.getResultList();
        return results;
    }

}
