/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Customer;

/**
 *
 * @author Admin
 */
public interface CustomerDAO {
    public boolean loginUser(String username, String password); 
    public Customer getCustomerByUserName(String username);
    
}
