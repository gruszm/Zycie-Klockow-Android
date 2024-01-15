package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.morozgrusz.zycieklockow.DAOs.AddressDAO;
import pl.morozgrusz.zycieklockow.entities.Address;

import java.util.List;

@Repository
public class AddressRepository implements AddressDAO
{
    private EntityManager entityManager;

    @Autowired
    public AddressRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Address> findAll()
    {
        TypedQuery<Address> query = entityManager.createQuery("FROM Address", Address.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Address address)
    {
        entityManager.merge(address);
    }

    @Override
    public List<Address> findByUserId(int id)
    {
        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM Address a "
                        + "WHERE a.user.id = :id",
                Address.class);

        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public List<Address> findByUserEmail(String email)
    {
        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM Address a "
                        + "WHERE a.user.email = :email",
                Address.class);

        query.setParameter("email", email);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(int id)
    {
        Address address = entityManager.find(Address.class, id);

        if (address != null)
        {
            entityManager.remove(address);
        }
    }

    @Override
    public Address findById(int id)
    {
        try
        {
            return entityManager.find(Address.class, id);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }
}
