package com.breezevillepark.Kitchen.and.Bar.System.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepositoryImpl orderDao;
    private Order order;

    public OrderService(OrderRepositoryImpl orderDao) {
        this.orderDao = orderDao;
    }

    public Order createNewOrder(Order order){
        if (order == null){
            throw  new IllegalArgumentException("Order cannot be null");
        }
        return orderDao.saveOrder(order);
    }

    public List<Order> showAllOrders(){
        return orderDao.getAllOrders();
    }

    public Order updateOrder(Order order){
        return orderDao.editOrder(order);
    }

    public void deleteOrder(Order order){
        orderDao.removeOrder(order);
    }

   public List<Order> showPendingOrders(Order pendingOrder){
        if (pendingOrder.getStatus() != OrderStatus.IN_PROGRESS){
            return orderDao.getAllOrders();
        }
        return orderDao.getPendingOrders();
   }

   public void updateOrderStatus(){
        order.setStatus(order.getStatus());
   }
}
