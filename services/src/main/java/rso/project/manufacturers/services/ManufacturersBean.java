package rso.project.manufacturers.services;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import rso.project.manufacturers.Manufacturer;
import rso.project.manufacturers.Product;
import rso.project.manufacturers.services.config.RestProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ManufacturersBean {

    //private Logger log = LogManager.getLogger(ManufacturersBean.class.getName());

    @Inject
    private RestProperties restProperties;

    @Inject
    private EntityManager em;

    @Inject
    private ManufacturersBean manufacturersBean;

    private Client httpClient;

    @Inject
    @DiscoverService("product")
    //private Optional<String> baseUrl;


    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        //baseUrl = "http://192.168.99.100:8081"; // only for demonstration
    }

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
