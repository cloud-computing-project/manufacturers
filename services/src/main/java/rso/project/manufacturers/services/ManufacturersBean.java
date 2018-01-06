package rso.project.manufacturers.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import rso.project.manufacturers.Manufacturer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
public class ManufacturersBean {

    @Inject
    private EntityManager em;

    public List<Manufacturer> getManufacturers(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Manufacturer.class, queryParameters);

    }

    public Manufacturer getManufacturer(String manufacturerId) {

        Manufacturer manufacturer = em.find(Manufacturer.class, manufacturerId);

        if (manufacturer == null) {
            throw new NotFoundException();
        }

        return manufacturer;
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {

        try {
            beginTx();
            em.persist(manufacturer);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return manufacturer;
    }

    public Manufacturer putManufacturer(String manufacturerId, Manufacturer manufacturer) {

        Manufacturer c = em.find(Manufacturer.class, manufacturerId);

        if (c == null) {
            return null;
        }

        try {
            beginTx();
            manufacturer.setId(c.getId());
            manufacturer = em.merge(manufacturer);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return manufacturer;
    }

    public boolean deleteManufacturer(String manufacturerId) {

        Manufacturer manufacturer = em.find(Manufacturer.class, manufacturerId);

        if (manufacturer != null) {
            try {
                beginTx();
                em.remove(manufacturer);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
