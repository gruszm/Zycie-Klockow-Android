package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.morozgrusz.zycieklockow.DAOs.ProductWithQuantityDAO;
import pl.morozgrusz.zycieklockow.entities.ProductWithQuantity;

import java.util.List;

@Repository
public class ProductWithQuantityRepository implements ProductWithQuantityDAO
{
    private EntityManager entityManager;

    @Autowired
    public ProductWithQuantityRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProductWithQuantity> findByUserEmail(String email)
    {
        TypedQuery<ProductWithQuantity> query = entityManager.createQuery(
                "SELECT pwq FROM ProductWithQuantity pwq "
                        + "WHERE pwq.user.email = :email",
                ProductWithQuantity.class);

        query.setParameter("email", email);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(int id)
    {
        ProductWithQuantity productWithQuantity = entityManager.find(ProductWithQuantity.class, id);

        if (productWithQuantity != null)
        {
            entityManager.remove(productWithQuantity);
        }
    }
}
