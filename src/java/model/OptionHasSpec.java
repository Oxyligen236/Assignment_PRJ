/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class OptionHasSpec {
//    CREATE TABLE OptionHasSpec (
//  OptionID INT NOT NULL,
//  SpecID INT NOT NULL,
//  SpecValue VARCHAR(255) NOT NULL, -- Ví dụ: "16GB", "Intel i7"
//  PRIMARY KEY (OptionID, SpecID),
//  FOREIGN KEY (OptionID) REFERENCES ProductOption(OptionID),
//  FOREIGN KEY (SpecID) REFERENCES Specifications(SpecID)
    
    private int OptionID, SpecID;
    private String SpecValue;
    private Specifications spec;

    public OptionHasSpec() {
    }

    public OptionHasSpec(int OptionID, int SpecID, String SpecValue, Specifications spec) {
        this.OptionID = OptionID;
        this.SpecID = SpecID;
        this.SpecValue = SpecValue;
        this.spec = spec;
    }

    public int getOptionID() {
        return OptionID;
    }

    public void setOptionID(int OptionID) {
        this.OptionID = OptionID;
    }

    public int getSpecID() {
        return SpecID;
    }

    public void setSpecID(int SpecID) {
        this.SpecID = SpecID;
    }

    public String getSpecValue() {
        return SpecValue;
    }

    public void setSpecValue(String SpecValue) {
        this.SpecValue = SpecValue;
    }

    public Specifications getSpec() {
        return spec;
    }

    public void setSpec(Specifications spec) {
        this.spec = spec;
    }
    
    
}
