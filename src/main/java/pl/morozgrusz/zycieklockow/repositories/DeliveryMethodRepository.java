package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.morozgrusz.zycieklockow.DAOs.DeliveryMethodDAO;
import pl.morozgrusz.zycieklockow.entities.DeliveryMethod;

import java.util.List;

@Repository
public class DeliveryMethodRepository implements DeliveryMethodDAO
{
    private EntityManager entityManager;

    @Autowired
    public DeliveryMethodRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<DeliveryMethod> findAll()
    {
        TypedQuery<DeliveryMethod> query = entityManager.createQuery("FROM DeliveryMethod", DeliveryMethod.class);

        return query.getResultList();
    }
}
