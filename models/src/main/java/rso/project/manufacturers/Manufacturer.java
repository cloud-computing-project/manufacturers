package rso.project.manufacturers;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "manufacturer")
@NamedQueries(value =
        {
                @NamedQuery(name = "Manufacturer.getAll", query = "SELECT c FROM manufacturer c")
        })

@UuidGenerator(name = "idGenerator")
public class Manufacturer {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String title;

    private String country;

    @Transient
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(){return title;}

    public void setTitle(String title) {this.title = title;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
