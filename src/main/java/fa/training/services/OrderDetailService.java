package fa.training.services;

import fa.training.entites.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    public List<OrderDetail> getAll();
    public OrderDetail addOrderDetail(OrderDetail orderDetail);

}
