package fa.training.controllers;

import fa.training.entites.OrderDetail;
import fa.training.model.DataRespose;
import fa.training.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/")
    public ResponseEntity<DataRespose> getAll() {
        List<OrderDetail> orderDetails = orderDetailService.getAll();
        if(orderDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose("erro","cannot find orderdetail")
            );
        }
        else {
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new DataRespose(orderDetails)
            );
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DataRespose> addOrderDetail(@RequestBody OrderDetail orderDetail) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataRespose("","add success",orderDetailService.addOrderDetail(orderDetail))
        );
    }

}
