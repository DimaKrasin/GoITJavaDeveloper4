package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="MANUFACTURER")
public class Manufacturer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "manufactur_name")
    private String manufactur_name;

    @OneToMany(mappedBy="manufacturer")
    private Set<Product> products;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getManufactur_name() {
        return manufactur_name;
    }

    public void setManufactur_name(String manufactur_name) {
        this.manufactur_name = manufactur_name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
