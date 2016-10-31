package au.edu.uts.aip.dragoncoffee.domain;

import au.edu.uts.aip.dragoncoffee.domain.UserOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-30T17:17:30")
@StaticMetamodel(DragonUser.class)
public class DragonUser_ { 

    public static volatile SingularAttribute<DragonUser, String> lastName;
    public static volatile SingularAttribute<DragonUser, String> firstName;
    public static volatile SingularAttribute<DragonUser, String> password;
    public static volatile ListAttribute<DragonUser, UserOrder> userOrder;
    public static volatile SingularAttribute<DragonUser, Date> dateOfBirth;
    public static volatile SingularAttribute<DragonUser, Integer> id;
    public static volatile SingularAttribute<DragonUser, String> username;

}