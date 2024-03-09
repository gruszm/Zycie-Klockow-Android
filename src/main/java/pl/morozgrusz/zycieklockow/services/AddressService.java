package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.Address;
import pl.morozgrusz.zycieklockow.entities.User;
import pl.morozgrusz.zycieklockow.repositories.AddressRepository;
import pl.morozgrusz.zycieklockow.repositories.UserRepository;

import java.util.List;

@Service
public class AddressService
{
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository)
    {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<Address> findAll()
    {
        return addressRepository.findAll();
    }

    public List<Address> findByUserEmail(String email)
    {
        return addressRepository.findByUserEmail(email);
    }

    public Address saveForEmail(Address address, String email)
    {
        User user = userRepository.findByEmail(email);

        address.setUser(user);

        return addressRepository.save(address);
    }

    public void deleteById(int id)
    {
        addressRepository.deleteById(id);
    }

    public Address findById(int id)
    {
        return addressRepository.findById(id);
    }
}
