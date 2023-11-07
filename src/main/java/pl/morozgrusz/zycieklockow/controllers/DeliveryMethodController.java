package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.morozgrusz.zycieklockow.services.DeliveryMethodService;

@Controller
@RequestMapping("/deliveryMethods")
public class DeliveryMethodController
{
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    public DeliveryMethodController(DeliveryMethodService deliveryMethodService)
    {
        this.deliveryMethodService = deliveryMethodService;
    }

    @GetMapping("/")
    public String getAllDeliveryMethods(Model model)
    {
        model.addAttribute("deliveryMethods", deliveryMethodService.findAll());

        return "deliveryMethods/list-delivery-methods";
    }
}
