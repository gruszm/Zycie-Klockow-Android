package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.Address;

import java.util.List;

public interface AddressDAO
{
    List<Address> findAll();
    Address save(Address address);
    List<Address> findByUserId(int id);
    List<Address> findByUserEmail(String email);
    void deleteById(int id);
    Address findById(int id);
}
