package pl.morozgrusz.zycieklockow.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.morozgrusz.zycieklockow.DAOs.CategoryDAO;
import pl.morozgrusz.zycieklockow.entities.Category;

import java.util.List;

@Repository
public class CategoryRepository implements CategoryDAO
{
    private EntityManager entityManager;

    @Autowired
    public CategoryRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public Category findById(int id)
    {
        try
        {
            return entityManager.find(Category.class, id);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    @Override
    public List<Category> findAll()
    {
        TypedQuery<Category> query = entityManager.createQuery("FROM Category", Category.class);

        return query.getResultList();
    }
}
