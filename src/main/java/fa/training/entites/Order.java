package fa.training.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column
    private LocalDate orderDate;
    @Column
    private double amount;

    @Column
    private String deliveryDddress;
    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users user;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    Set<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(long orderId, LocalDate orderDate, double amount, String deliveryDddress, boolean status, Users user,
                 Set<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.deliveryDddress = deliveryDddress;
        this.status = status;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public String getDeliveryDddress() {
        return deliveryDddress;
    }

    public void setDeliveryDddress(String deliveryDddress) {
        this.deliveryDddress = deliveryDddress;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
