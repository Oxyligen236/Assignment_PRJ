
package model;


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

    public Product() {
    }

    public Product(int productId, String productName, int productQuantity, String description, double price, Type type, Brand brand, String createdAt, String updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.description = description;
        this.price = price;
        this.type = type;
        this.brand = brand;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    //

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
