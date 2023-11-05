package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.Order;

import java.util.List;

public interface OrderDAO
{
    List<Order> findAll();
}
