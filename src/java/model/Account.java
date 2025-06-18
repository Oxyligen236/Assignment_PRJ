package model;

/**
 *
 * @author Admin
 */
public class Account {
//      AccountID INT IDENTITY(1,1) PRIMARY KEY,
//  UserName VARCHAR(50) NOT NULL UNIQUE,
//  PasswordHash VARCHAR(255) NOT NULL,
//  UserID INT NOT NULL UNIQUE,
//  FOREIGN KEY (UserID) REFERENCES [User](UserID)

    private int accountId;
    private String username, passwordHash;
    private int userId;

    public Account() {
    }

    public Account(int accountId, String username, String passwordHash, int userId) {
        this.accountId = accountId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
