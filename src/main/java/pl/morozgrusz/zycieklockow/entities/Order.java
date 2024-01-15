package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date", nullable = false, updatable = false, insertable = false)
    private Timestamp orderDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "delivery_method_fk")
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductWithQuantity> productsWithQuantities;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_fk")
    private Address address;

    public Order()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Timestamp getOrderDate()
    {
        return orderDate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public DeliveryMethod getDeliveryMethod()
    {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod)
    {
        this.deliveryMethod = deliveryMethod;
    }

    public List<ProductWithQuantity> getProductsWithQuantities()
    {
        return productsWithQuantities;
    }

    public void setProductsWithQuantities(List<ProductWithQuantity> productsWithQuantities)
    {
        this.productsWithQuantities = productsWithQuantities;
    }

    public void addProductWithQuantity(ProductWithQuantity productWithQuantity)
    {
        if (productsWithQuantities == null)
        {
            productsWithQuantities = new ArrayList<>();
        }

        productsWithQuantities.add(productWithQuantity);
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", user=" + user +
                ", deliveryMethod=" + deliveryMethod +
                ", productsWithQuantities=" + productsWithQuantities +
                '}';
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public BigDecimal getTotalValue()
    {
        BigDecimal total = new BigDecimal(0.0);

        for (ProductWithQuantity pwq : productsWithQuantities)
        {
            BigDecimal quantity = new BigDecimal(pwq.getQuantity());
            BigDecimal price = pwq.getProduct().getPrice();
            BigDecimal value = quantity.multiply(price);

            total = total.add(value);
        }

        total = total.add(deliveryMethod.getPrice());

        return total;
    }

}
