package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.morozgrusz.zycieklockow.entities.Address;
import pl.morozgrusz.zycieklockow.services.AddressService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/addresses")
public class AddressController
{
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public String getAllAddresses(Model model, Principal principal)
    {
        List<Address> addressesOfLoggedUser = addressService.findByUserEmail(principal.getName());

        model.addAttribute("addresses", addressesOfLoggedUser);

        return "addresses/list-addresses";
    }
}
