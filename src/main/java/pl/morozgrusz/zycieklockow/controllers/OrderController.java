package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morozgrusz.zycieklockow.entities.*;
import pl.morozgrusz.zycieklockow.services.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController
{
    private OrderService orderService;
    private UserService userService;
    private DeliveryMethodService deliveryMethodService;
    private AddressService addressService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, DeliveryMethodService deliveryMethodService, AddressService addressService)
    {
        this.orderService = orderService;
        this.userService = userService;
        this.deliveryMethodService = deliveryMethodService;
        this.addressService = addressService;
    }

    @GetMapping("/")
    public String getAllOrders(Model model,
                               Principal principal)
    {
        User user = userService.findUserByEmail(principal.getName());

        model.addAttribute("orders", user.getOrders());

        return "orders/list-orders";
    }

    @GetMapping("/selectDeliveryMethod/")
    public String createOrder(Model model)
    {
        List<DeliveryMethod> deliveryMethods = deliveryMethodService.findAll();

        model.addAttribute("deliveryMethods", deliveryMethods);

        return "orders/select-delivery-method";
    }

    @PostMapping("/selectAddress/")
    public String submitDeliveryMethod(Model model,
                                       Principal principal,
                                       @RequestParam(name = "selectedDeliveryMethodId") int selectedDeliveryMethodId)
    {
        User user = userService.findUserByEmail(principal.getName());
        List<Address> usersAddresses = user.getAddresses();

        model.addAttribute("usersAddresses", usersAddresses);
        model.addAttribute("selectedDeliveryMethodId", selectedDeliveryMethodId);

        return "orders/select-address";
    }

    @PostMapping("/orderSummary/")
    public String orderSummary(Model model,
                               Principal principal,
                               @RequestParam(name = "selectedDeliveryMethodId") int selectedDeliveryMethodId,
                               @RequestParam(name = "selectedAddressId") int selectedAddressId)
    {
        Order order = new Order();
        User user = userService.findUserByEmail(principal.getName());
        List<ProductWithQuantity> usersCart = user.getProductsWithQuantities();
        DeliveryMethod selectedDeliveryMethod = deliveryMethodService.findById(selectedDeliveryMethodId);
        Address selectedAddress = addressService.findById(selectedAddressId);

        order.setUser(user);
        order.setDeliveryMethod(selectedDeliveryMethod);
        order.setProductsWithQuantities(usersCart);
        order.setAddress(selectedAddress);

        model.addAttribute("order", order);

        return "orders/order-summary";
    }

    @PostMapping("/submitOrder/")
    public String submitOrder(RedirectAttributes redirectAttributes,
                              Principal principal,
                              @RequestParam(name = "selectedDeliveryMethodId") int selectedDeliveryMethodId,
                              @RequestParam(name = "selectedAddressId") int selectedAddressId)
    {
        // reconstruct the order
        Order order = new Order();
        User user = userService.findUserByEmail(principal.getName());
        List<ProductWithQuantity> usersCart = user.getProductsWithQuantities();
        DeliveryMethod selectedDeliveryMethod = deliveryMethodService.findById(selectedDeliveryMethodId);
        Address selectedAddress = addressService.findById(selectedAddressId);

        order.setUser(user);
        order.setDeliveryMethod(selectedDeliveryMethod);
        order.setProductsWithQuantities(usersCart);
        order.setAddress(selectedAddress);

        orderService.submitOrder(order);

        redirectAttributes.addFlashAttribute("orderSubmitted", order.getTotalValue());

        return "redirect:/orders/";
    }
}
