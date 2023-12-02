package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;
import pl.morozgrusz.zycieklockow.entities.User;
import pl.morozgrusz.zycieklockow.repositories.ProductRepository;
import pl.morozgrusz.zycieklockow.repositories.ProductWithQuantityRepository;
import pl.morozgrusz.zycieklockow.repositories.UserRepository;

import java.util.List;

@Service
public class ProductWithQuantityService
{
    private ProductWithQuantityRepository productWithQuantityRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductWithQuantityService(ProductWithQuantityRepository productWithQuantityRepository, UserRepository userRepository, ProductRepository productRepository)
    {
        this.productWithQuantityRepository = productWithQuantityRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<ProductWithQuantity> findByUserEmail(String email)
    {
        return productWithQuantityRepository.findByUserEmail(email);
    }

    public void deleteById(int id)
    {
        productWithQuantityRepository.deleteById(id);
    }

    public void addProductToCartByEmail(String email, int productId)
    {
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findById(productId);
        List<ProductWithQuantity> userCart = user.getProductsWithQuantities();
        boolean productNotInCart = true;

        for (ProductWithQuantity pwq : userCart)
        {
            if (pwq.getProduct().getId() == productId)
            {
                pwq.incrementQuantity();
                productWithQuantityRepository.save(pwq);

                productNotInCart = false;
                break;
            }
        }

        if (productNotInCart && (product.getQuantity() > 0))
        {
            ProductWithQuantity pwq = new ProductWithQuantity();
            pwq.setProduct(product);
            pwq.setUser(user);
            pwq.setQuantity(1);

            productWithQuantityRepository.save(pwq);
        }
    }
}
