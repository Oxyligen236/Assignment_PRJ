/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class User {
//    UserID INT IDENTITY(1,1) PRIMARY KEY,
//  RoleID INT NOT NULL,
//  FullName NVARCHAR(50) NOT NULL,
//  Email VARCHAR(50) NOT NULL,
//  PhoneNumber CHAR(10) CHECK (PhoneNumber LIKE '0_________'),
//  Address VARCHAR(100),
//  Gender VARCHAR(10),
//  FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
    private int userID, roleID;
    private String fullName, email, phoneNumber, address, gender;

    public User() {
    }

    public User(int userID, int roleID, String fullName, String email, String phoneNumber, String address, String gender) {
        this.userID = userID;
        this.roleID = roleID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}
