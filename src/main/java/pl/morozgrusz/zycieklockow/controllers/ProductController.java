package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morozgrusz.zycieklockow.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController
{
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getAllProducts(Model model)
    {
        model.addAttribute("products", productService.findAll());

        return "products/list-products";
    }

    @PostMapping("/delete/")
    public String deleteProduct(@RequestParam(name = "productIdToDelete") int id, RedirectAttributes redirectAttributes)
    {
        productService.deleteById(id);

        redirectAttributes.addFlashAttribute("productDeleted", id);

        return "redirect:/products/";
    }
}
