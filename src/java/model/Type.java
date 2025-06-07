/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Type {
//    TypeID INT IDENTITY(1,1) PRIMARY KEY,
//  TypeName VARCHAR(50) NOT NULL
    private int typeId;
    private String typeName;

    public Type() {
    }

    public Type(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }
    //

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
}
