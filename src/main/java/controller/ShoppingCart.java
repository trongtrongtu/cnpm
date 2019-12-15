/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import entity.ShoppingCartItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ShoppingCart {

    List<ShoppingCartItem> items;
    int numberOfItems;
    double total;

    public ShoppingCart() {
        items = new ArrayList<ShoppingCartItem>();
        numberOfItems = 0;
        total = 0;
    }

    public synchronized void addItem(Product product) {
        boolean newItem = true;
        for (ShoppingCartItem scItem : items) {
            if (scItem.getProduct().getProductID() == product.getProductID()) {
                newItem = false;
                scItem.incrementQuantity((short)1);
            }
        }
        if (newItem) {
            ShoppingCartItem scItem = new ShoppingCartItem(product);
            items.add(scItem);
        }
    }

        public synchronized void addItem(Product product,String quantity) {
        boolean newItem = true;
        short qty = (short) Integer.parseInt(quantity);
        for (ShoppingCartItem scItem : items) {
            if (scItem.getProduct().getProductID() == product.getProductID()) {
                newItem = false;
                scItem.setQuantity(qty);
            }
        }
        if (newItem) {
            ShoppingCartItem scItem = new ShoppingCartItem(product, qty);
            items.add(scItem);
        }
    }
    public synchronized void update(Product product, String quantity) {
        short qty = -1;
        qty = Short.parseShort(quantity);
        if (qty >= 0) {
            ShoppingCartItem item = null;
            for (ShoppingCartItem scItem : items) {
                if (scItem.getProduct().getProductID() == product.getProductID()) {
                    if (qty != 0) {
                        scItem.setQuantity(qty);
                    } else {
                        item = scItem;
                        break;
                    }
                }
            }
            if (item != null) {
                items.remove(item);
            }
        }
    }

    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }

    public synchronized int getNumberOfItems() {
        numberOfItems = 0;
        for (ShoppingCartItem scItem : items) {
            numberOfItems += scItem.getQuantity();
        }
        return numberOfItems;
    }

    public synchronized double getSubtotal() {
        double amount = 0;
        for (ShoppingCartItem scItem : items) {
            Product product = (Product) scItem.getProduct();
            amount += (scItem.getQuantity() * product.getPrice());
        }
        return amount;
    }
    public synchronized double calculateTotal() {
        double amount = 0;
        amount = this.getSubtotal();
        double shippingFee = getRandomDoubleBetweenRange(0,200000);
        amount += shippingFee;
        this.total = amount;
        return shippingFee;
    }
    public synchronized double getTotal() {
        return total;
    }
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }
  public synchronized static int getRandomDoubleBetweenRange(int min, int max){
    int x = (int) ((Math.random()*((max-min)+1))+min);
    return x;
};
}
