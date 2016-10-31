/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.domain;


import java.util.GregorianCalendar;
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 *
 * @author Amber
 */
@Stateless
public class DragonUserMethod {

    @PersistenceContext
    private EntityManager em;

    
    /**
     * This addSampleData method is going to insert 3 new rows in DragonUser table;
     * It is used for demonstrate purpose.
     */
    public void addSampleData() {
        DragonUser mike = new DragonUser();
        mike.setUsername("mike@example.com");
        mike.setPassword("1234");
        mike.setFirstName("mike");
        mike.setLastName("Brady");
        mike.setDateOfBirth(new GregorianCalendar(1932, 9, 19).getTime());
        em.persist(mike);
        DragonUser Amber = new DragonUser();
        Amber.setUsername("Amber@example.com");
        Amber.setPassword("1234");
        Amber.setFirstName("Amber");
        Amber.setLastName("Brady");
        Amber.setDateOfBirth(new GregorianCalendar(1932, 9, 19).getTime());
        em.persist(Amber);
        DragonUser jack = new DragonUser();
        jack.setUsername("jack@example.com");
        jack.setPassword("1234");
        jack.setFirstName("jack");
        jack.setLastName("Brady");
        jack.setDateOfBirth(new GregorianCalendar(1932, 9, 19).getTime());
        em.persist(jack);
    }

    /**
     * This method is going to find every rows from DragonUser table;
     * @return a list of DragonUser objects that exist in the DragonUser table.
     */
    public List<DragonUser> findAllUser() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DragonUser> query = builder.createQuery(DragonUser.class);
        return em.createQuery(query).getResultList();
    }
        /**
     * Deletes a user from database.
     *
     * @param id the unique id of the person to delete
     */
    public void deleteUser(int id) {
        DragonUser user = em.find(DragonUser.class, id);
        em.remove(user);
        em.flush();
    }
    
    /**
     * This method is going to insert a new single row on DragonUser table;
     * @param newUser is a DragonUser object.
     * @return ture when the newUser account signup successfully or false when the userName is already in the database table.
     */
    public boolean signUp (DragonUser newUser) {
        try{
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<DragonUser> query = builder.createQuery(DragonUser.class);
            Root<DragonUser> from = query.from(DragonUser.class);
            query.where(builder.equal(from.get(DragonUser_.username), newUser.getUsername()));
            em.createQuery(query).getSingleResult().getId();
            return false;
        }catch(NoResultException e){
            em.persist(newUser);
            return true;
        }
    }
    /**
     * This method is going to query a single row from DragonUser table using Username;
     * @param username is what user type in textbox.
     * @return a single row from data or null for not exist.
     */
    public DragonUser findUserWithUsername(String username){
        try{
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<DragonUser> query = builder.createQuery(DragonUser.class);
            Root<DragonUser> from = query.from(DragonUser.class);
            query.where(builder.equal(from.get(DragonUser_.username), username));
            return em.createQuery(query).getSingleResult();
        }catch(NoResultException e){
            DragonUser dg = new DragonUser();
            System.out.println(e);
            return dg;
        }
    }
}
