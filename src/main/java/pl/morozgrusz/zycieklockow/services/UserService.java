package pl.morozgrusz.zycieklockow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.morozgrusz.zycieklockow.datatransferobjects.UserDTO;
import pl.morozgrusz.zycieklockow.entities.Role;
import pl.morozgrusz.zycieklockow.entities.User;
import pl.morozgrusz.zycieklockow.repositories.UserRepository;

@Service
public class UserService
{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public void save(User user)
    {
        userRepository.save(user);
    }

    public void save(UserDTO userDTO)
    {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setActive(true);
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.addRole(new Role(user, "ROLE_CUSTOMER"));

        userRepository.save(user);
    }
}
