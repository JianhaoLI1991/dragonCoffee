/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
/**
 *
 * @author Amber
 */
@Entity
@NamedQueries({
    //find order list based on specific users
    @NamedQuery(name = "UserOrder.findUserOrder",
            query = "select orders from UserOrder orders where orders.user = :user")
})
public class UserOrder implements Serializable {
    
    private DragonUser user;
    private Product product;
    private int id;
    private int amount;
    private char status;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    @ManyToOne
    public DragonUser getUser() {
        return user;
    }

    public void setUser(DragonUser user) {
        this.user = user;
    }
    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
