package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morozgrusz.zycieklockow.entities.DeliveryMethod;
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

    @GetMapping("/addForm/")
    public String addDeliveryMethodForm(Model model)
    {
        DeliveryMethod deliveryMethod = new DeliveryMethod();

        model.addAttribute("deliveryMethod", deliveryMethod);

        return "/deliveryMethods/delivery-method-form";
    }

    @PostMapping("/processDeliveryMethodForm/")
    public String processAddDeliveryMethodForm(@ModelAttribute(name = "deliveryMethod") DeliveryMethod deliveryMethod, RedirectAttributes redirectAttributes)
    {
        deliveryMethodService.save(deliveryMethod);

        redirectAttributes.addFlashAttribute("addedDeliveryMethodName", deliveryMethod.getName());
        redirectAttributes.addFlashAttribute("addedDeliveryMethodPrice", deliveryMethod.getPrice());

        return "redirect:/deliveryMethods/";
    }
}
