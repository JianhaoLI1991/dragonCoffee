package au.edu.uts.aip.dragoncoffee.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 
 *
 * @author Jianhao Li
 */
@Entity
public class Product implements Serializable{
    private int id;
    private String name;
    private double price;
    private String description;
    private byte[] image;
    private List<UserOrder> orders = new ArrayList<>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotNull(message = "Product name is required")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "Prodcut price is required")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @NotNull(message = "Prodcut description is required")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    public List<UserOrder> getUserOrder() {
        return orders;
    }

    public void setUserOrder(List<UserOrder> orders) {
        this.orders = orders;
    }
    
}
