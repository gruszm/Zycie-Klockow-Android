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

    public ProductWithQuantity findById(int id)
    {
        return productWithQuantityRepository.findById(id);
    }

    public List<ProductWithQuantity> findByUserEmail(String email)
    {
        return productWithQuantityRepository.findByUserEmail(email);
    }

    public void deleteById(int id)
    {
        productWithQuantityRepository.deleteById(id);
    }

    public ProductWithQuantity save(ProductWithQuantity pwq)
    {
        productWithQuantityRepository.save(pwq);

        return pwq;
    }

    public ProductWithQuantity addProductToCartByEmail(String email, int productId)
    {
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findById(productId);
        List<ProductWithQuantity> userCart = user.getProductsWithQuantities();

        for (ProductWithQuantity pwq : userCart)
        {
            if (pwq.getProduct().getId() == productId)
            {
                pwq.incrementQuantity();
                productWithQuantityRepository.save(pwq);

                return pwq;
            }
        }

        if (product.getQuantity() > 0)
        {
            ProductWithQuantity pwq = new ProductWithQuantity();
            pwq.setProduct(product);
            pwq.setUser(user);
            pwq.setQuantity(1);

            productWithQuantityRepository.save(pwq);

            return pwq;
        }

        return null;
    }

    public ProductWithQuantity decrease(int id)
    {
        ProductWithQuantity pwq = productWithQuantityRepository.findById(id);

        if (pwq == null)
        {
            return null;
        }

        pwq.setQuantity(pwq.getQuantity() - 1);

        if (pwq.getQuantity() > 0)
        {
            save(pwq);
        }
        else
        {
            delete(pwq);
        }

        return pwq;
    }

    public ProductWithQuantity delete(ProductWithQuantity productWithQuantity)
    {
        return productWithQuantityRepository.delete(productWithQuantity);
    }
}
