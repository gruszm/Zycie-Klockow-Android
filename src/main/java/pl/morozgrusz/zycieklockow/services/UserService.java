package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.entities.User;
import pl.morozgrusz.zycieklockow.repositories.UserRepository;

@Service
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }
}
