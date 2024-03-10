package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;
import pl.morozgrusz.zycieklockow.security.JwtUtils;
import pl.morozgrusz.zycieklockow.security.UserDetails;
import pl.morozgrusz.zycieklockow.services.ProductService;
import pl.morozgrusz.zycieklockow.services.ProductWithQuantityService;
import pl.morozgrusz.zycieklockow.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController
{
    private ProductWithQuantityService productWithQuantityService;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public CartController(ProductWithQuantityService productWithQuantityService, UserService userService, ProductService productService)
    {
        this.productWithQuantityService = productWithQuantityService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductWithQuantity>> getCart(@RequestHeader(name = "Auth") String jwt)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        List<ProductWithQuantity> userCart;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        else
        {
            userCart = productWithQuantityService.findByUserEmail(ud.getEmail());

            return ResponseEntity
                    .ok()
                    .body(userCart);
        }
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<ProductWithQuantity> addToCart(@RequestHeader(name = "Auth") String jwt, @PathVariable(name = "productId") int productId)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        ProductWithQuantity cartElement;
        Product product;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }

        product = productService.findById(productId);

        if (product == null)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        cartElement = productWithQuantityService.addProductToCartByEmail(ud.getEmail(), productId);

        if (cartElement == null)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(cartElement);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFromCart(@RequestHeader(name = "Auth") String jwt, @PathVariable(name = "id") int id)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        ProductWithQuantity cartElement;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }

        cartElement = productWithQuantityService.findById(id);

        if (cartElement == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        productWithQuantityService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(null);
    }
}
