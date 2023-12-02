package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.Product;

import java.util.List;

public interface ProductDAO
{
    Product findById(int id);
    List<Product> findAll();
    void deleteById(int id);
}
