package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;
import pl.morozgrusz.zycieklockow.repositories.ProductWithQuantityRepository;

import java.util.List;

@Service
public class ProductWithQuantityService
{
    private ProductWithQuantityRepository productWithQuantityRepository;

    @Autowired
    public ProductWithQuantityService(ProductWithQuantityRepository productWithQuantityRepository)
    {
        this.productWithQuantityRepository = productWithQuantityRepository;
    }

    public List<ProductWithQuantity> findByUserEmail(String email)
    {
        return productWithQuantityRepository.findByUserEmail(email);
    }

    public void deleteById(int id)
    {
        productWithQuantityRepository.deleteById(id);
    }
}
