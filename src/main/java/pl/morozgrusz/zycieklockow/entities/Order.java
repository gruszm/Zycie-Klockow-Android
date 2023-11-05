package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date", updatable = false)
    private Timestamp orderDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "delivery_method_fk")
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductWithQuantity> productsWithQuantities;

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
}
