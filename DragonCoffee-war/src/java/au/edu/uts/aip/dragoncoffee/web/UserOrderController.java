/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.web;

import au.edu.uts.aip.dragoncoffee.domain.*;
import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.net.*;

/**
 *this is a method controller used by UserOrder
 * @author Jianhao_LI
 */
@Named
@SessionScoped
public class UserOrderController implements Serializable {

    @EJB
    private UserOrderMethod uom;
    private List<UserOrder> orderList;
    private UserOrder currentOrder = new UserOrder();
    private double currentTotal;

    /**
     * get the current order
     *
     * @return return currentOrder
     */
    public UserOrder getCurrentOrder() {
        return currentOrder;
    }
    /**
     * retrieve the total price of the order list
     * @return the total price
     */
    public double getCurrentTotal() {
        return currentTotal;
    }
/**
 * set the value of total price
 * @param currentTotal 
 */
    public void setCurrentTotal(double currentTotal) {
        this.currentTotal = currentTotal;
    }
    /**
     * used to set currentOrder
     *
     * @param currentOrder info of order that will be set to current order
     */
    public void setCurrentOrder(UserOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * used to return all order for administrator
     *
     * @return a list of all orders
     */
    public List<UserOrder> getOrderList() {
        orderList = uom.findAllOrder();
        return orderList;
    }

    /**
     * used to calculate the total price of a order
     *
     * @param amount amount of the product
     * @param price a single price of the product
     * @return the total price
     */
    public double getTotalPrice(int amount, double price) {
        double sub_price = amount * price;
        currentTotal = currentTotal + sub_price;
        return sub_price;
    }

    /**
     * used to return the user's orders
     *
     * @return a list of all orders
     */
    public List<UserOrder> findUserOrders(DragonUser user, char status) {
        orderList = uom.findUserOrders(user,status);
        return orderList;
    }

    /**
     * this method will return a specific order based on the order id provided
     *
     * @param id the primary key of order
     * @return the order object
     */
    public UserOrder findOrder(int id) {
        currentOrder = uom.findOrder(id);
        return currentOrder;
    }

    /**
     * this method can used to add new order to database based on user and
     * product
     *
     * @param user the user who logged to add product to cart
     * @param product the product that is added to cart
     */
    public String newOrder(DragonUser user, Product product, int amount) {
        if (amount < 0 || amount == 0) {
            DragonUserController.showErrorMessage("the amount is invalid");
            return "";
        } else {
            currentOrder.setUser(user);
            currentOrder.setProduct(product);
            currentOrder.setStatus('N');
            uom.createOrder(currentOrder);
            currentOrder = new UserOrder();
            return "my_shoppingCart?faces-redirect=true";
        }
    }

    /**
     * this method is used to delete a specific order based on the order id
     * provided
     *
     * @param id id the primary key of order
     */
    public String deleteOrder(int id) {
        uom.deleteOrder(id);
        return "my_shoppingCart?faces-redirect=true";
    }

    /**
     * this method is used to update details of the current modifying order
     */
    public String updateOrder() {
        if (currentOrder.getAmount() < 0 || currentOrder.getAmount() == 0) {
            DragonUserController.showErrorMessage("the amount is invalid");
            return "";
        } else {
            uom.updateOrderDetails(currentOrder);
            return "my_shoppingCart?faces-redirect=true";
        }
    }

    /**
     * set the selected order that is going to be edited to current order
     *
     * @param order the selected order that is going to be edited
     * @return direct the page the order edit page
     */
    public String goEditPage(UserOrder order) {
        currentOrder = order;
        return "edit_order?faces-redirect=true";

    }

    /**
     * this method is used to send user confirmed order to customer
     * reference:http://www.itcuties.com/java/send-post-request/
     *
     * @throws Exception
     */
    public String sendEmail(String userMail) throws Exception {
        // This is the data that is going to be send to itcuties.com via POST request
        // 'e' parameter contains data to echo
        String postData = "to=" + URLEncoder.encode(userMail, "UTF-8");
        postData += "&subject=" + URLEncoder.encode("Coffee order", "UTF-8");
        double total = 0;
        String body = "Prodcut\t\tPrice\tAmount\tSub_total\n";
        for (int i = 0; i < orderList.size(); i++) {
            double sub_price = orderList.get(i).getAmount() * orderList.get(i).getProduct().getPrice();
            total += sub_price;
//            if (orderList.get(i).getProduct().getName().length() <= 8) {
                body += orderList.get(i).getProduct().getName() + "\t\t" + "A$" + orderList.get(i).getProduct().getPrice() + "\t" + orderList.get(i).getAmount() + "\t" + "A$" + sub_price + "\n";
//            } else {
//                body += orderList.get(i).getProduct().getName() + "\t" + orderList.get(i).getProduct().getPrice() + "\t" + orderList.get(i).getAmount() + "\t" + sub_price + "\n";
//            }
            System.out.print(orderList.get(i).getProduct().getName().length() + "\n");
        }
        body += "Total Price of the Order: A$" + total;
        postData += "&body=" + URLEncoder.encode(body, "UTF-8");

        // Connect to google.com
        URL url = new URL("http://localhost:8080/DragonCoffee-war/EmailServlet");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(postData.length()));

        // Write data
        OutputStream os = connection.getOutputStream();
        os.write(postData.getBytes());

        // Read response
        StringBuilder responseSB = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = br.readLine()) != null) {
            responseSB.append(line);
        }

        // Close streams
        br.close();
        os.close();
        DragonUserController.showErrorMessage("Email Already sent successfully");
        return "";
    }
    /**
     * used to set the status of the order. after paid, the order status will be set to "Y"
     * @return direct to page to order page
     */
    public String changeStatus(){
        for (UserOrder order : orderList){
            order.setStatus('Y');
            uom.updateOrderDetails(order);
        }
        return "my_order?faces-redirect=true";
    }
}
