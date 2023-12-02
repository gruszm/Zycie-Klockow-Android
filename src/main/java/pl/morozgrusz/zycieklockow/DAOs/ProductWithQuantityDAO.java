package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;

import java.util.List;

public interface ProductWithQuantityDAO
{
    List<ProductWithQuantity> findByUserEmail(String email);
}
