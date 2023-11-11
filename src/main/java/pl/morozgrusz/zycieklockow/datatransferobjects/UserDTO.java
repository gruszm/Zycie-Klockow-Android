package pl.morozgrusz.zycieklockow.datatransferobjects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDTO
{
    @NotNull(message = "This field is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "This field is required")
    private String password;

    @NotNull(message = "This field is required")
    private String repeatPassword;

    @NotNull(message = "This field is required")
    private String firstName;

    private String middleName;

    @NotNull(message = "This field is required")
    private String lastName;

    public UserDTO()
    {

    }

    public UserDTO(String email, String password, String repeatPassword, String firstName, String middleName, String lastName)
    {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
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

    public String getRepeatPassword()
    {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
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

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
