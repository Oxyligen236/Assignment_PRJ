/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    //CREATE TABLE Product (
//  ProductID INT IDENTITY(1,1) PRIMARY KEY,
//  ProductName VARCHAR(100) NOT NULL,
//  ProductQuantity INT NOT NULL CHECK (ProductQuantity > 0),
//  Description NVARCHAR(MAX) NOT NULL,
//  Price MONEY NOT NULL,
//  TypeID INT NOT NULL,
//  BrandID INT NOT NULL,
//  CreatedAt DATETIME DEFAULT GETDATE(),
//  UpdatedAt DATETIME,
//  FOREIGN KEY (TypeID) REFERENCES [Type](TypeID),
//  FOREIGN KEY (BrandID) REFERENCES Brand(BrandID)
//);
    private int productId;
    private String productName;
    private int productQuantity;
    private String description;
    private double price;
    private Type type;
    private Brand brand;
    private String createdAt;
    private String updatedAt;
    
    
}
