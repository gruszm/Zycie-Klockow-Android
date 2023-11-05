package pl.morozgrusz.zycieklockow.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "path", unique = true)
    private String path;

    @Column(name = "main")
    private boolean main;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_fk")
    private Product product;

    public Image()
    {

    }

    public Image(String path, boolean main, Product product)
    {
        this.path = path;
        this.main = main;
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

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public boolean isMain()
    {
        return main;
    }

    public void setMain(boolean main)
    {
        this.main = main;
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
