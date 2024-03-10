package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;

import java.util.List;

public interface ProductWithQuantityDAO
{
    List<ProductWithQuantity> findByUserEmail(String email);
    void deleteById(int id);
    void save(ProductWithQuantity productWithQuantity);
    ProductWithQuantity findById(int id);
    ProductWithQuantity delete(ProductWithQuantity productWithQuantity);
}
