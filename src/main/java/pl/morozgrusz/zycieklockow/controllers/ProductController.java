package pl.morozgrusz.zycieklockow.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morozgrusz.zycieklockow.entities.Product;
import pl.morozgrusz.zycieklockow.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") int id)
    {
        Product product = productService.findById(id);

        if (product == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(product);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> allProducts = productService.findAll();

        return ResponseEntity
                .ok()
                .body(allProducts);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") int categoryId)
    {
        List<Product> products = productService.findByCategoryId(categoryId);

        return ResponseEntity
                .ok()
                .body(products);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        Product savedProduct = productService.save(product);

        if (savedProduct == null)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(savedProduct);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(name = "id") int id)
    {
        Product deletedProduct = productService.deleteById(id);

        if (deletedProduct == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(deletedProduct);
        }
    }
}
