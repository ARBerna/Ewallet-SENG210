package fileFix;

public class DatabaseUser {
    private int userID;
    private String username;
    private String passwordHash;
    private String email;
    private String createdAt;

    public DatabaseUser(int userID, String username, String passwordHash, String email, String createdAt) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.createdAt = createdAt;
    }

    public DatabaseUser(String username, String passwordHash, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public int getUserID()
    { 
    	return userID; 
    }
    
    public String getUsername() 
    { 
    	return username;
    }
    
    public String getPasswordHash()
    { 
    	return passwordHash; 
    }
    
    public String getEmail() 
    { 
    	return email; 
    }
    
    public String getCreatedAt() 
    { 
    	return createdAt; 
    }

    public void setUserID(int userID) 
    { 
    	this.userID = userID; 
    }
}
