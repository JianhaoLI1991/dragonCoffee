package au.edu.uts.aip.dragoncoffee.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 *
 * @author Jianhao Li
 */
@Entity
public class DragonUser implements Serializable{

    
    
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String LastName;
    private Date dateOfBirth;
    private List<UserOrder> orders = new ArrayList<>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    //email address check
    @NotNull(message = "Username name is required")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address. e.g as@example.com")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password is required")
    @Size(min = 4, message = "Password at least 4 digtal")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "First name is required")
    @Size(min = 1, message = "First name is required")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "Last name is required")
    @Size(min = 1, message = "Last name is required")
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    @NotNull(message = "Date of birth is required")
    @Temporal(TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    public List<UserOrder> getUserOrder() {
        return orders;
    }

    public void setUserOrder(List<UserOrder> orders) {
        this.orders = orders;
    }
    
    @Embeddable
    class DragonUserID  implements java.io.Serializable{
            private String username;
            @Column(name="Id")
            private int id; 
    }

}
