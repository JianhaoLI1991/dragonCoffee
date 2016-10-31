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

/**
 *
 * @author Amber
 */
@Stateless
public class ProductMethod {

    @PersistenceContext
    private EntityManager em;

    /**
     * Populate the database with sample data.
     */
    public void addSampleData() {
        Product MACCHIATO = new Product();
        MACCHIATO.setName("MACCHIATO");
        MACCHIATO.setPrice(3.5);
        MACCHIATO.setDescription("a single serving of espresso, stained with milky foam");
        em.persist(MACCHIATO);
        Product PICCOLO_LATTE = new Product();
        PICCOLO_LATTE.setName("PICCOLO_LATTE");
        PICCOLO_LATTE.setPrice(3.5);
        PICCOLO_LATTE.setDescription("a single shot of espresso with a small layer of foam");
        em.persist(PICCOLO_LATTE);
        Product AFFOGATO = new Product();
        AFFOGATO.setName("AFFOGATO");
        AFFOGATO.setPrice(3.5);
        AFFOGATO.setDescription("a single shot of espresso served with one scoop of vanilla ice-cream");
        em.persist(AFFOGATO);
        Product FLAVOURED_LATTE = new Product();
        FLAVOURED_LATTE.setName("FLAVOURED_LATTE");
        FLAVOURED_LATTE.setPrice(3.5);
        FLAVOURED_LATTE.setDescription("latte topped with sweetened flavoured syrup (flavours include Hazelnut, Caramel, Vanilla & Irish Cream)");
        em.persist(FLAVOURED_LATTE);
        Product LATTE = new Product();
        LATTE.setName("LATTE");
        LATTE.setPrice(3.5);
        LATTE.setDescription("a double shot of espresso with steamed milk and a small layer of foam");
        em.persist(LATTE);
        Product CAPPUCCINO = new Product();
        CAPPUCCINO.setName("CAPPUCCINO");
        CAPPUCCINO.setPrice(3.5);
        CAPPUCCINO.setDescription("a double shot of espresso with equal parts steamed milk and foam");
        em.persist(CAPPUCCINO);
        Product FLAT_WHITE = new Product();
        FLAT_WHITE.setName("FLAT_WHITE");
        FLAT_WHITE.setPrice(3.5);
        FLAT_WHITE.setDescription("a double shot of espresso with flat steamed milk");
        em.persist(FLAT_WHITE);
        Product LONG_BLACK = new Product();
        LONG_BLACK.setName("LONG_BLACK");
        LONG_BLACK.setPrice(3.5);
        LONG_BLACK.setDescription("a cup of hot water topped with a double shot of espresso");
        em.persist(LONG_BLACK);
        em.flush();
    }

    /**
     * Retrieves all product in the database.
     *
     * @return a list of product instances in the database
     */
    public List<Product> findAllProduct() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        return em.createQuery(query).getResultList();
    }

    /**
     * Find a product by their primary key.
     *
     * @param id the unique primary key for the person
     * @return the corresponding product entity
     */
    public Product findProduct(int id) {
        return em.find(Product.class, id);
    }

    /**
     * Updates the product details of a product. This method does not update the
     * list of UserOrder.
     *
     * @param updatedProduct a Product object containing the id of the person to
     * update and the personal details
     */
    public void updateProductDetails(Product updatedProduct) {
        em.merge(updatedProduct);
    }

    /**
     * Deletes a product from database.
     *
     * @param id the unique id of the person to delete
     */
    public void deleteProduct(int id) {
        Product person = em.find(Product.class, id);
        em.remove(person);
    }

    /**
     * Create a Product in the database.
     *
     * @param newProduct the product to create in the Product table
     */
    public void createProduct(Product newProduct) {
        em.persist(newProduct);
    }

}
