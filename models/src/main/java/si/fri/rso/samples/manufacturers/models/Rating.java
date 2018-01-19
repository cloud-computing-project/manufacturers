package si.fri.rso.samples.manufacturers.models;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@Entity(name = "ratings")
@NamedQueries(value =
        {
                @NamedQuery(name = "Rating.getAll", query = "SELECT o FROM ratings o"),
                @NamedQuery(name = "Rating.findByTitle", query = "SELECT o FROM ratings o WHERE o.productId = " +
                        ":productId")
        })
@UuidGenerator(name = "idGenerator")
public class Rating {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "product_id")
    private String productId;

    private String rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){return productId;}

    public void setName(String name) {this.productId = productId;}

    public String getDescription(){return rating;}

    public void setDescription(String description){this.rating = rating;}

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
