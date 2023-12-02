package pl.morozgrusz.zycieklockow.services;

import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService
{
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }
}
