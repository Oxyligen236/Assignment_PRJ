/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Admin
 */
public class CartItem {
//  CartID INT NOT NULL,
//  OptionID INT NOT NULL,
//  Quantity INT NOT NULL CHECK (Quantity > 0),
//  AddedAt DATETIME DEFAULT GETDATE(),
//  PRIMARY KEY (CartID, OptionID),
//  FOREIGN KEY (CartID) REFERENCES Cart(CartID),
//  FOREIGN KEY (OptionID) REFERENCES ProductOption(OptionID)
    private int cartId, optionId, quantity;
    private String adddedAt;

    public CartItem(int cartId, int optionId, int quantity, String adddedAt) {
        this.cartId = cartId;
        this.optionId = optionId;
        this.quantity = quantity;
        this.adddedAt = adddedAt;
    }

    public CartItem() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAdddedAt() {
        return adddedAt;
    }

    public void setAdddedAt(String adddedAt) {
        this.adddedAt = adddedAt;
    }
    
}
