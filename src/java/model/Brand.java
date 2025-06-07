/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Brand {
    //      BrandID INT IDENTITY(1,1) PRIMARY KEY,
//  BrandName VARCHAR(50) NOT NULL
    private int brandId;
    private String brandName;
    private int imageId;
    private String ulr;
    
    //

    public Brand() {
    }

    public Brand(int brandId, String brandName, int imageId, String ulr) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.imageId = imageId;
        this.ulr = ulr;
    }
    //

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }
    
    

}
