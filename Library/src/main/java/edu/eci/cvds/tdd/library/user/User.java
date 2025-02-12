package edu.eci.cvds.tdd.library.user;
/*
import edu.eci.cvds.tdd.library.exceptions.InvalidUserException;
*/

public class User {
    private String name;
	private String id;
	
    public User(String name, String id) {
        setName(name);
        setId(id);     
    }
	
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
        }
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
        }
        this.id = id;
    }
}
