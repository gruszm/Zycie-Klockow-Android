package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Category;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.repositories.CategoryRepository;
import pl.morozgrusz.zycieklockow.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService
{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void save(Product product)
    {
        productRepository.save(product);
    }

    public void saveWithCategoryId(Product product, int categoryId)
    {
        Category category = categoryRepository.findById(categoryId);
        product.setCategory(category);
        productRepository.save(product);
    }

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

    public void deleteById(int id)
    {
        productRepository.deleteById(id);
    }
}
