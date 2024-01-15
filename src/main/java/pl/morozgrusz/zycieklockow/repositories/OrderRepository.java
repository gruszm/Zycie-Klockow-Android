package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.morozgrusz.zycieklockow.DAOs.OrderDAO;
import pl.morozgrusz.zycieklockow.entities.Order;

import java.util.List;

@Repository
public class OrderRepository implements OrderDAO
{
    private EntityManager entityManager;

    @Autowired
    public OrderRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> findAll()
    {
        TypedQuery<Order> query = entityManager.createQuery("FROM Order", Order.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Order order)
    {
        entityManager.merge(order);
    }
}
