package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street")
    private String street;

    @Column(name = "building_number")
    private short buildingNumber;

    @Column(name = "apartment_number")
    private short apartmentNumber;

    @Column(name = "zip_code", length = 6)
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_fk")
    private User user;

    public Address()
    {

    }

    public Address(String firstName, String lastName, String street, short buildingNumber, short apartmentNumber,
                   String zipCode, String city, String country, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public short getBuildingNumber()
    {
        return buildingNumber;
    }

    public void setBuildingNumber(short buildingNumber)
    {
        this.buildingNumber = buildingNumber;
    }

    public short getApartmentNumber()
    {
        return apartmentNumber;
    }

    public void setApartmentNumber(short apartmentNumber)
    {
        this.apartmentNumber = apartmentNumber;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
