package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.exceptions.InvalidUserException;
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
	
	@Test
    public void ShouldNotAddBookWithSameISBN() {
    	Library library = new Library();
    	Book book1 = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
    	Book book2 = new Book("El caballero de la armadura oxidada","Robert Fisher","9788497592208");
    	library.addBook(book1);
        assertFalse(library.addBook(book2));
    }
	
	@Test
    public void ShouldNotAddBookWitNullISBN() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez", null);
        assertFalse(library.addBook(book));
    }
	@Test
	public void ShouldNotAddBookWithNullAuthor() {
	    Library library = new Library();
	    Book book = new Book("100 años de soledad", null, "9788497592208");
	    assertFalse(library.addBook(book));
	}
	
	@Test
	public void ShouldNotAddBookWithNullTitle() {
	    Library library = new Library();
	    Book book = new Book(null, "Gabriel García Márquez", "9788497592208");
	    assertFalse(library.addBook(book));
	}
	
	@Test
	public void ShouldNotAddBookWithNullAtributes() {
	    Library library = new Library();
	    Book book = new Book(null, null, null);
	    assertFalse(library.addBook(book));
	}
	
	@Test
	public void ShouldCompareBooksWithEqualISBN() {
	    Library library = new Library();
	    Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
	    assertTrue(book.equals(book));
	}

	
	
    @Test
    public void shouldNotAllowEmptyUserId() {
        Exception exception = assertThrows(InvalidUserException.class, () -> {
            new User("Sebastian", ""); 
        });

        assertEquals("User ID cannot be empty or null", exception.getMessage());
    }

    @Test
    public void shouldNotAllowEmptyUserName() {
        Exception exception = assertThrows(InvalidUserException.class, () -> {
            new User("Salomon", ""); 
        });

        assertEquals("User name cannot be empty or null", exception.getMessage());
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
