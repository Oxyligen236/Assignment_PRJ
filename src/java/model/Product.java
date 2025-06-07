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
    private int typeId;
    private int brandId;
    private String createdAt;
    private String updatedAt;
    
    //

    public Product() {
    }

    public Product(int productId, String productName, int productQuantity, String description, double price, int typeId, int brandId, String createdAt, String updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.description = description;
        this.price = price;
        this.typeId = typeId;
        this.brandId = brandId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
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
