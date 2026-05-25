package cadastroee.controller;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Facade genérico — base para todos os Session Beans do projeto.
 * Procedimento 1 — Passo 3d/3f: gerado pelo NetBeans via Session Beans for Entity Classes.
 * Importações javax → jakarta (Passo 4c).
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq =
            getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery<Long> cq =
            getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        return getEntityManager().createQuery(cq).getSingleResult().intValue();
    }
}
