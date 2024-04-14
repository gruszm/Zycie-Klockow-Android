package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService
{
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product save(Product product)
    {
        return productRepository.save(product);
    }

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

    public Product deleteById(int id)
    {
        return productRepository.deleteById(id);
    }

    public Product findById(int id)
    {
        return productRepository.findById(id);
    }

    public List<Product> findByCategoryId(int categoryId)
    {
        return productRepository.findByCategoryId(categoryId);
    }
}
