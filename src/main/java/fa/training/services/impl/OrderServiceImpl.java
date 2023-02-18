package fa.training.services.impl;

import fa.training.entites.Order;
import fa.training.repositories.OrderRepository;
import fa.training.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(Order order) {
        if(order !=null) {
            return orderRepository.save(order);

        }
        return null;
    }
}
