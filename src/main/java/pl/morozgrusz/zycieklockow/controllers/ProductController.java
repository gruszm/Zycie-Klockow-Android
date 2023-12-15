package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morozgrusz.zycieklockow.entities.Category;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.services.CategoryService;
import pl.morozgrusz.zycieklockow.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController
{
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService = categoryService;
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

    @GetMapping("/addForm/")
    public String addProductForm(Model model)
    {
        Product product = new Product();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "products/product-form";
    }

    @PostMapping("/processProductForm/")
    public String processAddProductForm(@ModelAttribute(name = "product") Product product, @RequestParam(name = "categoryId") int categoryId, RedirectAttributes redirectAttributes)
    {
        productService.saveWithCategoryId(product, categoryId);

        redirectAttributes.addFlashAttribute("addedProductName", product.getName());
        redirectAttributes.addFlashAttribute("addedProductPrice", product.getPrice());
        redirectAttributes.addFlashAttribute("addedProductQuantity", product.getQuantity());

        return "redirect:/products/";
    }
}
