package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.User;

public interface UserDAO
{
    User findUserByEmail(String email);
    void save(User user);
}
