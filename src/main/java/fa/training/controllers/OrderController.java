package fa.training.controllers;

import fa.training.entites.Order;
import fa.training.model.DataRespose;
import fa.training.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<DataRespose> findAll() {
        List<Order> orders = orderService.getAllOrder();
        if(orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("erro","cannot find order")
            );
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(orders)

            );
        }

    }

    @GetMapping("/l")
    public List<Order> get() {
        List<Order> orders = orderService.getAllOrder();
        return orders;
    }

    @PostMapping("/add")
    public ResponseEntity<DataRespose> addOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataRespose("","Add success",orderService.addOrder(order))
        );

    }
}
