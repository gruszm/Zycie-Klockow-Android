package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.morozgrusz.zycieklockow.DAOs.UserDAO;
import pl.morozgrusz.zycieklockow.entities.User;

@Repository
public class UserRepository implements UserDAO
{
    private EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public User findByEmail(String email)
    {
        TypedQuery<User> query = entityManager.createQuery("FROM User u where u.email = :email", User.class);

        query.setParameter("email", email);

        try
        {
            return query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    @Transactional
    public void save(User user)
    {
        entityManager.persist(user);
    }
}
