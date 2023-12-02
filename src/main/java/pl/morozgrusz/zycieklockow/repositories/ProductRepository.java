package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.morozgrusz.zycieklockow.DAOs.ProductDAO;
import pl.morozgrusz.zycieklockow.entities.Product;

import java.util.List;

@Repository
public class ProductRepository implements ProductDAO
{
    private EntityManager entityManager;

    @Autowired
    public ProductRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll()
    {
        TypedQuery<Product> query = entityManager.createQuery("FROM Product", Product.class);

        return query.getResultList();
    }
}
