/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.domain;

import java.util.List;
import javax.ejb.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
/**
 *
 * @author Amber
 */
@Stateless
public class UserOrderMethod {
    @PersistenceContext
    private EntityManager em;
    
    
    /**
     * Retrieves all Order in the database.
     * the method is prepare for administrative user to check all orders
     * @return a list of orders instances in the database
     */
    public List<UserOrder> findAllOrder() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserOrder> query = builder.createQuery(UserOrder.class);
        return em.createQuery(query).getResultList();
    }
    /**
     * Find the list of order based on a specific user
     *
     * @param user the object of the user
     * @return a list of user's order
     */
    public List<UserOrder> findUserOrders(DragonUser user, char status) {
        // Criteria Query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserOrder> query = builder.createQuery(UserOrder.class);
        Root<UserOrder> from = query.from(UserOrder.class);
        Predicate paidOrder = builder.and(
                builder.equal(from.get(UserOrder_.user), user),
                builder.equal(from.get(UserOrder_.status), status)
        );
                query.where(builder.and(paidOrder));
                return em.createQuery(query).getResultList();
    }
    /**
     * Find a order by its primary key.
     *
     * @param id the unique primary key for the order
     * @return the corresponding order entity
     */
    public UserOrder findOrder(int id) {
        return em.find(UserOrder.class, id);
    }
    /**
     * Updates the order details. 
     *
     * @param updatedOrder the updated order details
     */
    public void updateOrderDetails(UserOrder updatedOrder) {
        em.merge(updatedOrder);
    }
    /**
     * Deletes a order from database.
     *
     * @param id the unique id of the order to delete
     */
    public void deleteOrder(int id) {
        UserOrder order = em.find(UserOrder.class, id);
        em.remove(order);
    }
    /**
     * Create a order in the database.
     *
     * @param newOrder the order to create in the UserOrder table
     */
    public void createOrder(UserOrder newOrder) {
        em.persist(newOrder);
    }


}
