package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_fk")
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
               cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ProductWithQuantity> productsWithQuantities;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    public Product()
    {

    }

    public Product(String name, BigDecimal price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public List<ProductWithQuantity> getProductsWithQuantities()
    {
        return productsWithQuantities;
    }

    public void setProductsWithQuantities(List<ProductWithQuantity> productsWithQuantities)
    {
        this.productsWithQuantities = productsWithQuantities;
    }

    public List<Rating> getRatings()
    {
        return ratings;
    }

    public void setRatings(List<Rating> ratings)
    {
        this.ratings = ratings;
    }

    public void addRating(Rating rating)
    {
        if (ratings == null)
        {
            ratings = new ArrayList<>();
        }

        ratings.add(rating);
    }

    public List<Image> getImages()
    {
        return images;
    }

    public void setImages(List<Image> images)
    {
        this.images = images;
    }

    public void addImage(Image image)
    {
        if (images == null)
        {
            images = new ArrayList<>();
        }

        images.add(image);
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", category=" + category.getName() +
                '}';
    }
}
