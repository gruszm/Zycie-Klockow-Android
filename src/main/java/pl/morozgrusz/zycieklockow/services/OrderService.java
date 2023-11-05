package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Order;
import pl.morozgrusz.zycieklockow.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService
{
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll()
    {
        return orderRepository.findAll();
    }
}
