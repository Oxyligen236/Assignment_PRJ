/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Cart {
//      CartID INT IDENTITY(1,1) PRIMARY KEY,
//  UserID INT NOT NULL UNIQUE,
//  CreatedAt DATETIME DEFAULT GETDATE(),
//  FOREIGN KEY (UserID) REFERENCES [User](UserID)
    private int cartId , userId;
    private String createAt;

    public Cart(int cartId, int userId, String createAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.createAt = createAt;
    }

    public Cart() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    
    
}
