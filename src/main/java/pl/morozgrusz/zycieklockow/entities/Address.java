package pl.morozgrusz.zycieklockow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotNull(message = "This field is required")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "This field is required")
    private String lastName;

    @Column(name = "street")
    @NotNull(message = "This field is required")
    private String street;

    @Column(name = "building_number")
    @NotNull(message = "This field is required")
    private Short buildingNumber;

    @Column(name = "apartment_number")
    private Short apartmentNumber;

    @Column(name = "zip_code", length = 6)
    @NotNull(message = "This field is required")
    private String zipCode;

    @Column(name = "city")
    @NotNull(message = "This field is required")
    private String city;

    @Column(name = "country")
    @NotNull(message = "This field is required")
    private String country;

    @Column(name = "phone_number", length = 11)
    @NotNull(message = "This field is required")
    private String phoneNumber;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_fk")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY,
               cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders;

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

    public Short getBuildingNumber()
    {
        return buildingNumber;
    }

    public void setBuildingNumber(Short buildingNumber)
    {
        this.buildingNumber = buildingNumber;
    }

    public Short getApartmentNumber()
    {
        return apartmentNumber;
    }

    public void setApartmentNumber(Short apartmentNumber)
    {
        this.apartmentNumber = apartmentNumber;
    }

    public String getZipCode()
    {
        String zipCodeFormatted = "";

        if (zipCode != null)
        {
            zipCodeFormatted = zipCode.substring(0, 2)
                    + (zipCode.contains("-") ? "" : '-')
                    + zipCode.substring(2);
        }

        return zipCodeFormatted;
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
        String phoneNumberFormatted = "";

        if (phoneNumber != null)
        {
            phoneNumberFormatted = '+' + phoneNumber.substring(0, 2) + ' '
                    + phoneNumber.substring(2, 5) + ' '
                    + phoneNumber.substring(5, 8) + ' '
                    + phoneNumber.substring(8);
        }
        else
        {
            phoneNumberFormatted += "+48";
        }

        return phoneNumberFormatted;
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

    public String getFullAddress()
    {
        String addressFormatted = "";

        addressFormatted += "ul. "
                + street
                + " "
                + buildingNumber;

        if (apartmentNumber != null)
        {
            addressFormatted += ", m. " + apartmentNumber;
        }

        return addressFormatted;
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
