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

    public void saveByEmail(Address address, String email)
    {
        User user = userRepository.findByEmail(email);

        address.setUser(user);
        addressRepository.save(address);
    }

    public void deleteById(int id)
    {
        addressRepository.deleteById(id);
    }
}
