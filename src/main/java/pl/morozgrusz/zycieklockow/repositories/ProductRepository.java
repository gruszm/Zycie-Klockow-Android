package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Product save(Product product)
    {
        return entityManager.merge(product);
    }

    @Override
    public List<Product> findByCategoryId(int categoryId)
    {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.category.id = :id", Product.class);

        query.setParameter("id", categoryId);

        return query.getResultList();
    }

    @Override
    public List<Product> findAll()
    {
        TypedQuery<Product> query = entityManager.createQuery("FROM Product", Product.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Product deleteById(int id)
    {
        Product product = entityManager.find(Product.class, id);

        if (product != null)
        {
            entityManager.remove(product);
        }

        return product;
    }

    @Override
    public Product findById(int id)
    {
        return entityManager.find(Product.class, id);
    }
}
