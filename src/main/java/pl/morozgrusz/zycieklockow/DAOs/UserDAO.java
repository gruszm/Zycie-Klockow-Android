package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.User;

public interface UserDAO
{
    User findByEmail(String email);
    void save(User user);
}
