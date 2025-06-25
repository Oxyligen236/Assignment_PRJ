/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ProductOption {
//     CREATE TABLE ProductOption (
//   OptionID INT IDENTITY(1,1) PRIMARY KEY,
//   ProductID INT NOT NULL,
//   OptionName VARCHAR(100) NOT NULL, -- Ví dụ: "RAM 8GB, SSD 512GB"
//   AdditionalPrice MONEY DEFAULT 0, -- giá cộng thêm nếu có
//   Quantity INT NOT NULL CHECK (Quantity >= 0),
//   FOREIGN KEY (ProductID) REFERENCES Product(ProductID)

    private int optionID, productID, quantity;
    private String optionName;
    private double additionalPrice;

    //

    public ProductOption() {
    }

    public ProductOption(int optionID, int productID, int quantity, String optionName, double additionalPrice) {
        this.optionID = optionID;
        this.productID = productID;
        this.quantity = quantity;
        this.optionName = optionName;
        this.additionalPrice = additionalPrice;
    }

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
    

}

