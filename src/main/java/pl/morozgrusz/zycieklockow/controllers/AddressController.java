package pl.morozgrusz.zycieklockow.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @GetMapping("/addForm/")
    public String addAddressForm(Model model)
    {
        Address address = new Address();

        model.addAttribute("address", address);

        return "addresses/address-form";
    }

    @PostMapping("/processAddressForm/")
    public String processAddAddressForm(@Valid @ModelAttribute(name = "address") Address address,
                                        BindingResult bindingResult,
                                        Principal principal,
                                        RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            return "addresses/address-form";
        }
        else
        {
            addressService.saveByEmail(address, principal.getName());

            redirectAttributes.addFlashAttribute("addedAddress", "");

            return "redirect:/addresses/";
        }
    }

    @PostMapping("/delete/")
    public String deleteProduct(@RequestParam(name = "addressIdToDelete") int id, RedirectAttributes redirectAttributes)
    {
        addressService.deleteById(id);

        redirectAttributes.addFlashAttribute("addressDeleted", "");

        return "redirect:/addresses/";
    }
}
