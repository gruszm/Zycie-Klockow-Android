package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morozgrusz.zycieklockow.entities.DeliveryMethod;
import pl.morozgrusz.zycieklockow.services.DeliveryMethodService;

import java.util.List;

@RestController
@RequestMapping("/api/deliveryMethods")
public class DeliveryMethodController
{
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    public DeliveryMethodController(DeliveryMethodService deliveryMethodService)
    {
        this.deliveryMethodService = deliveryMethodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryMethod> getDeliveryMethod(@PathVariable(name = "id") int id)
    {
        DeliveryMethod deliveryMethod = deliveryMethodService.findById(id);

        if (deliveryMethod == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(deliveryMethod);
        }
    }

    @GetMapping
    public ResponseEntity<List<DeliveryMethod>> getAllDeliveryMethods()
    {
        List<DeliveryMethod> deliveryMethods = deliveryMethodService.findAll();

        return ResponseEntity
                .ok()
                .body(deliveryMethods);
    }

    @PostMapping("/add")
    public ResponseEntity<DeliveryMethod> addDeliveryMethod(@RequestBody DeliveryMethod deliveryMethod)
    {
        deliveryMethodService.save(deliveryMethod);

        return ResponseEntity
                .ok()
                .body(deliveryMethod);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeliveryMethod> deleteDeliveryMethod(@PathVariable(name = "id") int id)
    {
        DeliveryMethod deletedDeliveryMethod = deliveryMethodService.deleteById(id);

        if (deletedDeliveryMethod == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else
        {
            return ResponseEntity
                    .ok()
                    .body(deletedDeliveryMethod);
        }
    }
}
