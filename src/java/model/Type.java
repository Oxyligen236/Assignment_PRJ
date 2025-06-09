
package model;


public class Type {
//    TypeID INT IDENTITY(1,1) PRIMARY KEY,
//  TypeName VARCHAR(50) NOT NULL
    private int typeID;
    private String typeName;

    public Type() {
    }

    public Type(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

   
    
}
