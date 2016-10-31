/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.web;

import au.edu.uts.aip.dragoncoffee.domain.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *this is a method controller used by DragonUser
 * @author sylvesterxiao
 */

@Named
@SessionScoped
public class DragonUserController implements Serializable{
    
    @EJB
    private DragonUserMethod dm;
    private List<DragonUser> user;
    private DragonUser dragonUser = new DragonUser();
/**
 * return the current DragonUser
 * @return current DragonUser
 */
    public DragonUser getDragonUser() {
        return dragonUser;
    }
/**
 * set dragonUser details
 * @param dragonUser the DragonUser object to be set
 */
    public void setDragonUser(DragonUser dragonUser) {
        this.dragonUser = dragonUser;
    }
    /**
     * used to initial a new DragonUser instance
     */
    public void initial() {
        dragonUser = new DragonUser();
    }
    /**
     * add sample data to database
     */
    public void addSampleData(){
        dm.addSampleData();
    }
    /**
     * retrieve all users 
     * @return return the user list
     */
    public List<DragonUser> getUser() {
        if (user == null) {
            // Cache the database lookup
            user = dm.findAllUser();
        }
        //DragonUser existUser = new DragonUser();
        for (DragonUser existUser : user){
            String username=existUser.getUsername().trim();
            System.out.print(username);
                if(username.contains("Admin@example.com")) {
//                    existUser = new DragonUser();
                        user.remove(existUser);
                }
        }
        return user;
    }
    /**
     * used to remvoe user account. this function is only used by admin user
     * @param id the id of the user that going to be remove
     * @return return to user_list page
     */
    public String removeUser(int id){
        //id = dragonUser.getId();
        dm.deleteUser(id);
        return "user_list?faces-redirect=true";
    }
    /**
     * used to sign up a new user
     * @return directs page to login after after user signed up
     */
    public String signup(){
        if(dm.signUp(dragonUser)){
            return "login?faces-redirect=true";
        }else showErrorMessage("User already exist.");
        return "";
    }
    /**
     * This method is login function. get user object from database using username;
     * return username 
     * @return 
     */
    public String login(){
        String typedPassword = dragonUser.getPassword();
        String typedUsername = dragonUser.getUsername();
        dragonUser = dm.findUserWithUsername(typedUsername);
        System.out.println("Password in database is " +dragonUser.getPassword());
        if(typedPassword.equals(dragonUser.getPassword())){
            if(dragonUser.getUsername().equals("Admin@example.com")) return "/Admin/admin";
            else return "/secret/product_list?faces-redirect=true";
        }else if(dragonUser.getPassword() != null)showErrorMessage("Wrong Password");
        else showErrorMessage("User not exist."); 
        
        dragonUser.setPassword("");
        return "";
    }
    /**
     * this method is use to show error message when there error or validator happened.
     * @param msg is specific error message
     */
    public static void showErrorMessage(String msg){
        FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(msg));
    }
    /**
     * This method is logout method
     * clean up current user object
     * @return login page after log out
     */
    public String logout(){
        dragonUser = new DragonUser();
        return "/login?faces-redirect=true";
    }
    /**
     * This method is checking has user logged in, does user has access to this page.
     * @return login page if no user illegal access.
     */
    public String checkLogginStatu(){
        if(dragonUser.getUsername()!=null){
            System.out.println(dragonUser.getUsername()+" loggin.");
            return "";
        }else{
            System.out.println("no user loggin.");
            return "/login?faces-redirect=true";
        }
    }
    /**
     * to check if the logged in user is admin or common user and direct to relevant page.
     * @return the page that is directed to
     */
    public String checkAdminLogginStatu(){
        try{
            if(dragonUser.getUsername().trim().equals("Admin@example.com")){
                System.out.println("Admin " + dragonUser.getUsername() + " loggin.");
                return "";
                }
        }catch(NullPointerException e){
            return "/login?faces-redirect=true";
        }
        showErrorMessage("You have no access to admin page."); 
        return "/login?faces-redirect=true";
    }
}
