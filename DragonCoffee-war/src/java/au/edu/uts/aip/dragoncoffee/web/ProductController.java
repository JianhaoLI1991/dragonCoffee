/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.web;

import au.edu.uts.aip.dragoncoffee.domain.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.*;
import javax.inject.Named;

/**
 *this is a method controller used by Product
 * @author Amber
 */
@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    //private Product Method pm;
    private ProductMethod pm;
    private List<Product> productList;
    private Product currentProduct = new Product();

    /**
     * get current product details
     *
     * @return the current product
     */
    public Product getCurrentProduct() {
        return currentProduct;
    }

    /**
     * used to set the info of current product
     *
     * @param currentProduct the updated product that will be set the current
     * product
     */
    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    /**
     * used to create sample products
     */
    public void addSampleData() {
        pm.addSampleData();
    }

    /**
     * used to Retrieves all products
     *
     * @return a list of product
     */
    public List<Product> getProductList() {
//        if (productList == null) {
//            // Cache the database lookup
        productList = pm.findAllProduct();
//        }
        return productList;
    }

    /**
     * used to add a new product
     */
    public void createProduct() {
        pm.createProduct(currentProduct);
    }

    /**
     * the method can find a specific product based on product id.
     *
     * @param id the id of a specific product
     * @return the details of the specific product
     */
    public void getProduct(int id) {
        currentProduct = pm.findProduct(id);
//        System.out.print(currentProduct.getName());
//        return currentProduct;
    }

    /**
     * this method used to update the details of specific product when user edit
     * the current product and update its info, the updated details will update
     * the instance currentProduct and sync with Entity as well as database
     */
    public String updateProduct(){
        pm.updateProductDetails(currentProduct);
        return "editProduct_list?faces-redirect=true";
    }

    /**
     * the method can remove the specific product based on product id form the
     * database
     *
     * @param id the id of a specific product
     */
    public String removeProduct(int id){
        id = currentProduct.getId();
        pm.deleteProduct(id);
        return "editProduct_list?faces-redirect=true";
    }

    public String selectedOrder(Product product) {
        currentProduct = product;
        return "selected_order?faces-redirect=true";
    }
}
