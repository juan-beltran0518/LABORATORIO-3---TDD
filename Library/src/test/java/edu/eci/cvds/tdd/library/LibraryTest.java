package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class LibraryTest {
	
	@Test
	public void ShouldAddBookWithUniqueISBN() {
    	Library library = new Library();
        library.addBook(new Book("100 años de soledad","Gabriel García Márqiez","9788497592208"));
        System.out.println(library.getBooks());
        assertTrue(true);
    }
	
	@Test
    public boolean ShouldNotAddBookWithSameISBN() {
    	Library library = new Library();
    	library.addBook(new Book("100 años de soledad","Gabriel García Márqiez","9788497592208"));
    	library.addBook(new Book("El caballero de la armadura oxidada","Robert Fisher","9788497592208"));
        return false;
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
