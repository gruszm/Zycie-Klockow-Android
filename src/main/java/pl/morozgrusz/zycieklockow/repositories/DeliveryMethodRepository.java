package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void save(DeliveryMethod deliveryMethod)
    {
        entityManager.merge(deliveryMethod);
    }

    @Override
    public List<DeliveryMethod> findAll()
    {
        TypedQuery<DeliveryMethod> query = entityManager.createQuery("FROM DeliveryMethod", DeliveryMethod.class);

        return query.getResultList();
    }

    @Override
    public DeliveryMethod findById(int id)
    {
        try
        {
            return entityManager.find(DeliveryMethod.class, id);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    @Override
    @Transactional
    public DeliveryMethod deleteById(int id)
    {
        DeliveryMethod deliveryMethod = entityManager.find(DeliveryMethod.class, id);

        if (deliveryMethod == null)
        {
            return null;
        }
        else
        {
            entityManager.remove(deliveryMethod);
            return deliveryMethod;
        }
    }
}
