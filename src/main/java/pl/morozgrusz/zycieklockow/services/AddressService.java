package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Address;
import pl.morozgrusz.zycieklockow.repositories.AddressRepository;

import java.util.List;

@Service
public class AddressService
{
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll()
    {
        return addressRepository.findAll();
    }

    public void save(Address address)
    {
        addressRepository.save(address);
    }

    public List<Address> findByUserId(int id)
    {
        return addressRepository.findByUserId(id);
    }

    public List<Address> findByUserEmail(String email)
    {
        return addressRepository.findByUserEmail(email);
    }
}
