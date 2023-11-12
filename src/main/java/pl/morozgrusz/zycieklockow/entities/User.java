package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", length = 68)
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ProductWithQuantity> productsWithQuantities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
               cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders;

    public User()
    {

    }

    public User(String email, String password, boolean active, String firstName, String middleName, String lastName)
    {
        this.email = email;
        this.password = password;
        this.active = active;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public void addRole(Role role)
    {
        if (roles == null)
        {
            roles = new ArrayList<>();
        }

        roles.add(role);
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    public List<ProductWithQuantity> getProductsWithQuantities()
    {
        return productsWithQuantities;
    }

    public void setProductsWithQuantities(List<ProductWithQuantity> productsWithQuantities)
    {
        this.productsWithQuantities = productsWithQuantities;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }
}
