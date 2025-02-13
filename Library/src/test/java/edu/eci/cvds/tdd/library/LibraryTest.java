package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
/*
import edu.eci.cvds.tdd.library.exceptions.InvalidUserException;
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



class LibraryTest {
	
	@Test
	void ShouldAddBookWithUniqueISBN() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
        assertTrue(library.addBook(book));
    }
	
	@Test
	void ShouldSomeBooksWithUniqueISBN() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
        assertTrue(library.addBook(book));
        assertTrue(library.addBook(book));
    }
	
	@Test
    void ShouldNotAddBookWithSameISBN() {
    	Library library = new Library();
    	Book book1 = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
    	Book book2 = new Book("El caballero de la armadura oxidada","Robert Fisher","9788497592208");
    	library.addBook(book1);
        assertFalse(library.addBook(book2));
    }
	
	@Test
    void ShouldNotAddBookWitNullISBN() {
    	Library library = new Library();
    	Book book = new Book("100 años de soledad","Gabriel García Márqiez", null);
        assertFalse(library.addBook(book));
    }
	@Test
	void ShouldNotAddBookWithNullAuthor() {
	    Library library = new Library();
	    Book book = new Book("100 años de soledad", null, "9788497592208");
	    assertFalse(library.addBook(book));
	}
	
	@Test
	void ShouldNotAddBookWithNullTitle() {
	    Library library = new Library();
	    Book book = new Book(null, "Gabriel García Márquez", "9788497592208");
	    assertFalse(library.addBook(book));
	}
	
	@Test
	void ShouldNotAddBookWithNullAtributes() {
	    Library library = new Library();
	    Book book = new Book(null, null, null);
	    assertFalse(library.addBook(book));
	}
	
	@Test
	void ShouldCompareBooksWithEqualISBN() {
	    Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
	    assertTrue(book.equals(book));
	}

		    
	@Test
	void shouldNotAllowEmptyUserId() {
	    User user = new User("Sebastian", "1032878954");
	    assertEquals("Sebastian", user.getName());
	    assertEquals("1032878954", user.getId());
	}
	
	@Test
	public void ShouldNotLoanBookWithNotUser() {
	    Library library = new Library();
	    User user = new User(null, null);
	    library.addUser(user);
	    assertNull(library.loanABook(null, null));
	    
	}
	
	@Test
	 void ShouldLoanBookWithValidUser() {
	    Library library = new Library();
	    User user = new User("Salomon Baena", "1001346737");
	    Book book1 = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
	    Book book2 = new Book("El coronel no tiene quien le escriba","Gabriel García Márqiez","9788497599208");
	    library.addUser(user);
	    library.addBook(book1);
	    library.addBook(book2);
	    library.loanABook("1001346737","9788497592208");
	    library.loanABook("1001346737","9788497599208");
	    library.getLoans();
	 }
	
	
	@Test
	 void ShouldLoanSameBooksWithExistingUser() {
	    Library library = new Library();
	    User user = new User("Salomon Baena", "1001346737");
	    Book book = new Book("100 años de soledad","Gabriel García Márqiez","9788497592208");
	    library.addUser(user);
	    library.addBook(book);
	    library.loanABook("1001346737","9788497592208");
	    library.loanABook("1001346737","9788497592208");
	    
	 }
	
	@Test
	 void ShouldReturnLoanSuccessfully() {
	    Library library = new Library();
	    User user = new User("Salomon Baena", "1001346737");
	    Book book = new Book("100 años de soledad", "Gabriel García Márquez", "9788497592208");

	    library.addUser(user);
	    library.addBook(book);

	    Loan loan = library.loanABook("1001346737", "9788497592208");
	    assertNotNull(loan);
	    assertEquals(LoanStatus.ACTIVE, loan.getStatus());

	    Loan returnedLoan = library.returnLoan(loan);
	    assertNotNull(returnedLoan);
	    assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
	    assertNotNull(returnedLoan.getReturnDate()); 

	    assertEquals(1, library.getBooks().get(book));
	}
	
	


    @Test
     void shouldNotAllowEmptyUserName() {
        User user = new User("Salomon", "1032878954");
        assertEquals("Salomon", user.getName());
        assertEquals("1032878954", user.getId());
    }
      
  
    @Test
     void shouldUpdateUserIdSuccessfully() {
        User user = new User("Sebastian", "100200300");
        user.setId("8952255");
        assertEquals("8952255", user.getId());
    }

    @Test
     void shouldGetUserName() {
        User user = new User("Carlos", "123456");
        assertEquals("Carlos", user.getName());
    }
    
    @Test
     void shouldGetUserId() {
        User user = new User("Carlos", "123456");
        assertEquals("123456", user.getId());
    }
       
   
    @Test
     void shouldSetUserNameSuccessfully() {
        User user = new User("Carlos", "123456");
        user.setName("Alejandro");
        assertEquals("Alejandro", user.getName());
    }
   
}
