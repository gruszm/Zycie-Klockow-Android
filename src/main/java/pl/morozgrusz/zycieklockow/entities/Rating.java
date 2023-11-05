package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rating_date", updatable = false)
    private Timestamp ratingDate;

    @Column(name = "value")
    private byte value;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_fk")
    private Product product;

    public Rating()
    {

    }

    public Rating(byte value, String description, Product product)
    {
        this.value = value;
        this.description = description;
        this.product = product;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Timestamp getRatingDate()
    {
        return ratingDate;
    }

    public byte getValue()
    {
        return value;
    }

    public void setValue(byte value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }
}
