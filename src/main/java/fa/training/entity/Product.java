package fa.training.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_name")
    @NotEmpty(message = "Please enter productName")
    private String productName;
    @Column(name = "desc")
    @NotEmpty(message = "Please enter desc")
    private String desc;
    @Column(name = "price")
    @NotNull(message = "Please enter price")
    private Long price;
    @Column(name = "color")
    @NotEmpty(message = "Please enter color")
    private String color;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false, referencedColumnName = "categoryId")
//    @JsonIgnore
//    @JsonBackReference
    private Category category;
    public Product() {
    }

    public Product(Long id, String productName, String desc, Long price, String color, Category category) {
        this.id = id;
        this.productName = productName;
        this.desc = desc;
        this.price = price;
        this.color = color;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
