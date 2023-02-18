package fa.training.entites;

import javax.persistence.*;

@Entity
@Table
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderDetailId;
    @Column
    private int quantity;
    @Column
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderDetail() {
    }

    public OrderDetail(long orderDetailId, int quantity, double unitPrice, Product product, Order order) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.order = order;
    }

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
