package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.morozgrusz.zycieklockow.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController
{
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getAllOrders(Model model)
    {
        model.addAttribute("orders", orderService.findAll());

        return "orders/list-orders";
    }
}
