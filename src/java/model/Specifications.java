/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Specifications {
//    CREATE TABLE Specifications (
//  SpecID INT IDENTITY(1,1) PRIMARY KEY,
//  SpecName VARCHAR(50) NOT NULL
//);
    
    int specID;
    String specName;

    public Specifications() {
    }

    public Specifications(int specID, String specName) {
        this.specID = specID;
        this.specName = specName;
    }

    public int getSpecID() {
        return specID;
    }

    public void setSpecID(int specID) {
        this.specID = specID;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
    
    
    
}
