package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morozgrusz.zycieklockow.datatransferobjects.OrderDTO;
import pl.morozgrusz.zycieklockow.entities.*;
import pl.morozgrusz.zycieklockow.security.JwtUtils;
import pl.morozgrusz.zycieklockow.security.UserDetails;
import pl.morozgrusz.zycieklockow.services.AddressService;
import pl.morozgrusz.zycieklockow.services.DeliveryMethodService;
import pl.morozgrusz.zycieklockow.services.OrderService;
import pl.morozgrusz.zycieklockow.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
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

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader(name = "Auth") String jwt)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        List<Order> orders;
        User user;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }

        user = userService.findUserByEmail(ud.getEmail());

        if (user == null)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        orders = user.getOrders();

        return ResponseEntity
                .ok()
                .body(orders);
    }

    @PostMapping("/submitOrder")
    public ResponseEntity submitOrder(@RequestHeader(name = "Auth") String jwt, @RequestBody OrderDTO orderDTO)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        Order order;
        User user;
        List<ProductWithQuantity> usersCart;
        Address address;
        DeliveryMethod deliveryMethod;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }

        user = userService.findUserByEmail(ud.getEmail());
        address = addressService.findById(orderDTO.getAddressId());
        deliveryMethod = deliveryMethodService.findById(orderDTO.getDeliveryMethodId());

        if ((user == null) || (address == null) || (deliveryMethod == null))
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        usersCart = user.getProductsWithQuantities();

        order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setDeliveryMethod(deliveryMethod);
        order.setProductsWithQuantities(usersCart);

        orderService.submitOrder(order);

        return ResponseEntity
                .ok()
                .build();
    }
}
