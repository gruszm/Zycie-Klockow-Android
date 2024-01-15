package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Order;
import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;
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

    public void submitOrder(Order order)
    {
        for (ProductWithQuantity cartElement : order.getProductsWithQuantities())
        {
            int productQuantity = cartElement.getProduct().getQuantity();
            productQuantity -= cartElement.getQuantity();
            cartElement.getProduct().setQuantity(productQuantity);
        }

        order.getProductsWithQuantities().forEach(cartElement -> cartElement.setUser(null));
        order.getProductsWithQuantities().forEach(cartElement -> cartElement.setOrder(order));

        orderRepository.save(order);
    }
}
