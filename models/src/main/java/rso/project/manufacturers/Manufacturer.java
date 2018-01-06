package rso.project.manufacturers;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "manufacturers")
@NamedQueries(value =
        {
                @NamedQuery(name = "Manufacturer.getAll", query = "SELECT o FROM manufacturers o"),
                @NamedQuery(name = "Manufacturer.findByCustomer", query = "SELECT o FROM manufacturers o WHERE o.customerId = " +
                        ":customerId")
        })
@UuidGenerator(name = "idGenerator")
public class Manufacturer {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    //private String title;

    //private String description;
    //@Column(name = "product_id")
    private String productId;

    private Date submitted;

    @Column(name = "customer_id")
    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getTitle() {
        return title;
    }*/

    /*public void setTitle(String title) {
        this.title = title;
    }*/

    /*public String getDescription() {
        return description;
    }*/

    /*public void setDescription(String description) {
        this.description = description;
    }*/

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }
}
