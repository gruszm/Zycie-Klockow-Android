package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morozgrusz.zycieklockow.entities.Address;
import pl.morozgrusz.zycieklockow.security.JwtUtils;
import pl.morozgrusz.zycieklockow.security.UserDetails;
import pl.morozgrusz.zycieklockow.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController
{
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses()
    {
        List<Address> addresses = addressService.findAll();

        return ResponseEntity
                .ok()
                .body(addresses);
    }

    @GetMapping("/specific")
    public ResponseEntity<List<Address>> getSpecificAddresses(@RequestHeader(name = "Authentication") String jwt)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        List<Address> listOfUsersAddresses;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }

        listOfUsersAddresses = addressService.findByUserEmail(ud.getEmail());

        return ResponseEntity
                .ok()
                .body(listOfUsersAddresses);
    }

    @GetMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestHeader(name = "Authentication") String jwt, @RequestBody Address address)
    {
        UserDetails ud = JwtUtils.readToken(jwt);
        Address savedAddress;

        if (ud == null)
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }

        savedAddress = addressService.saveForEmail(address, ud.getEmail());

        if (savedAddress == null)
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(savedAddress);
        }
    }
}
