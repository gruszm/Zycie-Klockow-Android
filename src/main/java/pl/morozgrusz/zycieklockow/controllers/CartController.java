package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;
import pl.morozgrusz.zycieklockow.entities.User;
import pl.morozgrusz.zycieklockow.services.ProductWithQuantityService;
import pl.morozgrusz.zycieklockow.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController
{
    private UserService userService;
    private ProductWithQuantityService productWithQuantityService;

    @Autowired
    public CartController(UserService userService, ProductWithQuantityService productWithQuantityService)
    {
        this.userService = userService;
        this.productWithQuantityService = productWithQuantityService;
    }

    @GetMapping("/")
    public String getCart(Model model, Principal principal)
    {
        User user = userService.findUserByEmail(principal.getName());
        List<ProductWithQuantity> productWithQuantityList = productWithQuantityService.findByUserEmail(principal.getName());

        model.addAttribute("totalCartValue", user.getTotalCartValue());
        model.addAttribute("productWithQuantityList", productWithQuantityList);

        return "products/cart";
    }

    @PostMapping("/deleteCartElement/")
    public String deleteCartElement(@RequestParam(name = "cartElementId") int id)
    {
        productWithQuantityService.deleteById(id);

        return "redirect:/cart/";
    }

    @PostMapping("/addCartElement/")
    public String addCartElement(@RequestParam(name = "productIdToAdd") int id, Principal principal)
    {
        productWithQuantityService.addProductToCartByEmail(principal.getName(), id);

        return "redirect:/products/";
    }
}
