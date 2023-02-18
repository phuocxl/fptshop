package fa.training.services.impl;

import fa.training.entites.OrderDetail;
import fa.training.repositories.OrderDetailRepository;
import fa.training.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceimpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        if(orderDetail != null) {
            orderDetailRepository.save(orderDetail);
        }
        return null;
    }
}
