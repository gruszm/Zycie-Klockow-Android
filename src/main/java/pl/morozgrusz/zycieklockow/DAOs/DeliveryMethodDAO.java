package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.DeliveryMethod;

import java.util.List;

public interface DeliveryMethodDAO
{
    List<DeliveryMethod> findAll();
    void save(DeliveryMethod deliveryMethod);
}
