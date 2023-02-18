package fa.training.entites;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "product")
@SQLDelete(sql = "update product set is_deleted = 1 where id = ?")
@Where(clause = "is_deleted=0")
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

    @Column(name = "prices_sale")
    @NotNull(message = "Please enter pricesSale")
    private Long pricesSale;
    @Column(name = "color")
    @NotEmpty(message = "Please enter color")
    private String color;

    @Column(name = "image")
    @NotEmpty(message = "Please enter image")
    private String image;
    @Column
    private boolean isDeleted = Boolean.FALSE;
    @Column
    private LocalDate createDay = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false, referencedColumnName = "categoryId")
//    @JsonIgnore
//    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
    public Product() {
    }

    public Product(Long id, String productName, String desc, Long price, Long pricesSale, String color, String image,
                   boolean isDeleted, LocalDate createDay, Category category) {
        this.id = id;
        this.productName = productName;
        this.desc = desc;
        this.price = price;
        this.pricesSale = pricesSale;
        this.color = color;
        this.image = image;
        this.isDeleted = isDeleted;
        this.createDay = createDay;
        this.category = category;
    }

    public Product(@NotEmpty(message = "Please enter productName") String productName,
                   @NotEmpty(message = "Please enter color") String color,
                   @NotNull(message = "Please enter price") String desc,
                   @NotNull(message = "Please enter price") long price) {
        this.productName = productName;
        this.color = color;
        this.desc = desc;
        this.price = price;

    }

    public Product(String productName, String desc, String color, Long price, Long pricesSale, String image,
                   Category cayegory) {
        this.productName = productName;
        this.desc = desc;
        this.color = color;
        this.price = price;
        this.pricesSale = pricesSale;
        this.image = image;
        this.category = cayegory;
    }

    public Long getPricesSale() {
        return pricesSale;
    }

    public void setPricesSale(Long pricesSale) {
        this.pricesSale = pricesSale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getCreateDay() {
        return createDay;
    }

    public void setCreateDay(LocalDate createDay) {
        this.createDay = createDay;
    }
}
