package pl.morozgrusz.zycieklockow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @Column(name = "content_type")
    private String contentType;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_fk")
    private Product product;

    public Image()
    {

    }

    public Image(Product product)
    {
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

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
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
