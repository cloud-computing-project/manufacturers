package si.fri.rso.samples.manufacturers.services;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.samples.manufacturers.models.Manufacturer;
import si.fri.rso.samples.manufacturers.models.Product;
import si.fri.rso.samples.manufacturers.services.config.RestProperties;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
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
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

@RequestScoped
public class ManufacturersBean {

    private Logger log = LogManager.getLogger(ManufacturersBean.class.getName());

    @Inject
    private RestProperties restProperties;

    @Inject
    private EntityManager em;

    @Inject
    private ManufacturersBean manufacturersBean;

    private Client httpClient;

    @Inject
    @DiscoverService("products")
    private Optional<String> baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        //baseUrl = "http://192.168.99.100:8081"; // only for demonstration
    }


    public List<Manufacturer> getManufacturers() {

        TypedQuery<Manufacturer> query = em.createNamedQuery("Manufacturer.getAll", Manufacturer.class);

        return query.getResultList();

    }

    public List<Manufacturer> getManufacturersFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Manufacturer.class, queryParameters);
    }

    public Manufacturer getManufacturer(String manufacturerId) {

        Manufacturer manufacturer = em.find(Manufacturer.class, manufacturerId);

        if (manufacturer == null){
            throw new NotFoundException();
        }
        log.info("checking service" + restProperties.isProductServiceEnabled());
        if (restProperties.isProductServiceEnabled()) {
            List<Product> products = manufacturersBean.getProducts(manufacturerId);
            log.info("lista products: " + products);
            manufacturer.setProducts(products);
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

    @CircuitBreaker(requestVolumeThreshold = 2)
    @Fallback(fallbackMethod = "getProductsFallback")
    @Timeout(value=2, unit= ChronoUnit.SECONDS)
    public List<Product> getProducts(String manufacturerId) {
        log.info("baseurl" + baseUrl);
        if (baseUrl.isPresent()) {
        try {
            return httpClient
                    .target(baseUrl.get() + "/v1/products?where=manufacturerId:EQ:" + manufacturerId)
                    .request().get(new GenericType<List<Product>>() {
                    });
        } catch (WebApplicationException | ProcessingException e) {
            log.error(e);
            throw new InternalServerErrorException(e);
        }

    }
    return new ArrayList<>();
    }

    public List<Product> getProductsFallback(String manufacturerId) {
        List<Product> products = new ArrayList<>();

        Product product = new Product();

        product.setTitle("N/A");

        products.add(product);
        log.info("fallback method");
        System.out.println("Fallback Method.");
        return products;
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

    public void loadOrder(Integer n) {


    }
}
