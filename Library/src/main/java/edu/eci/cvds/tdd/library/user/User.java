package edu.eci.cvds.tdd.library.user;

public class User {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
    	if (id == null || id.trim().isEmpty() ) {
    		throw new IllegalArgumentException("User ID cannot be empty or null");
    	}
        this.id = id;
    }
    
}