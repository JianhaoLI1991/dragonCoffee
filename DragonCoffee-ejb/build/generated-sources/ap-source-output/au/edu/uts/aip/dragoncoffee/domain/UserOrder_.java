package au.edu.uts.aip.dragoncoffee.domain;

import au.edu.uts.aip.dragoncoffee.domain.DragonUser;
import au.edu.uts.aip.dragoncoffee.domain.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-30T17:17:30")
@StaticMetamodel(UserOrder.class)
public class UserOrder_ { 

    public static volatile SingularAttribute<UserOrder, Product> product;
    public static volatile SingularAttribute<UserOrder, Integer> amount;
    public static volatile SingularAttribute<UserOrder, Integer> id;
    public static volatile SingularAttribute<UserOrder, DragonUser> user;
    public static volatile SingularAttribute<UserOrder, Character> status;

}