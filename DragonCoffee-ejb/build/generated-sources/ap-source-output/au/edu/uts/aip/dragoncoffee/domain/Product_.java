package au.edu.uts.aip.dragoncoffee.domain;

import au.edu.uts.aip.dragoncoffee.domain.UserOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-30T17:17:30")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, byte[]> image;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile ListAttribute<Product, UserOrder> userOrder;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> id;

}