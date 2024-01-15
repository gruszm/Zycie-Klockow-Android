package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.DeliveryMethod;
import pl.morozgrusz.zycieklockow.repositories.DeliveryMethodRepository;

import java.util.List;

@Service
public class DeliveryMethodService
{
    private DeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    public DeliveryMethodService(DeliveryMethodRepository deliveryMethodRepository)
    {
        this.deliveryMethodRepository = deliveryMethodRepository;
    }

    public void save(DeliveryMethod deliveryMethod)
    {
        deliveryMethodRepository.save(deliveryMethod);
    }

    public List<DeliveryMethod> findAll()
    {
        return deliveryMethodRepository.findAll();
    }

    public DeliveryMethod findById(int id)
    {
        return deliveryMethodRepository.findById(id);
    }
}
