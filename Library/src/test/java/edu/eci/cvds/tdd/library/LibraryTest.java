package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



public class LibraryTest {
	
	@Test
	public void ShouldAddBookWithUniqueISBN() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
        assertTrue(library.addBook(book));
    }
	
	@Test
	public void ShouldSomeBooksWithUniqueISBN() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
        assertTrue(library.addBook(book));
        assertTrue(library.addBook(book));
    }
	
//	@Test
//    public void ShouldNotAddBookWithSameISBN() {
//    	Library library = new Library();
//    	library.addBook(new Book("100 años de soledad","Gabriel García Márqiez","9788497592208"));
//    	library.addBook(new Book("El caballero de la armadura oxidada","Robert Fisher","9788497592208"));
//        assertFalse(false);
//    }
	
	@Test
    public void ShouldNotAddBookNotValid() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez", null);
        assertFalse(library.addBook(book));
    }
	
	@Test
	public void shouldNotAllowEmptyUserId() {
	    User user = new User();
	    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        user.setId(""); // id Vacio 
	    });

	    assertEquals("User ID cannot be empty or null", exception.getMessage());
	}


    
    public Loan loanABook(String userId, String isbn) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    public Loan returnLoan(Loan loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    public boolean addUser(User user) {
        return false;
    }

}
