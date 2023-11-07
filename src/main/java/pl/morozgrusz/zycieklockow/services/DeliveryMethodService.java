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

    public List<DeliveryMethod> findAll()
    {
        return deliveryMethodRepository.findAll();
    }
}
