package fa.training.services;


import fa.training.entites.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrder();
    public Order addOrder(Order order);
}
