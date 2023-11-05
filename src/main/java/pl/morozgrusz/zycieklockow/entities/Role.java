package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role
{
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role")
    private String role;

    public Role()
    {

    }

    public Role(User user, String role)
    {
        this.user = user;
        this.role = role;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
