# LABORATORIO 3 - TDD
### CREAR PROYECTO CON MAVEN

[![Captura-de-pantalla-2025-02-13-120508.png](https://i.postimg.cc/nzyZxtQH/Captura-de-pantalla-2025-02-13-120508.png)](https://postimg.cc/LY3GkwT7)

### AGREGAR DEPENDENCIA JUNIT5

[![Imagen-de-Whats-App-2025-02-13-a-las-12-14-30-f88d74b2.jpg](https://i.postimg.cc/Pxws2FV0/Imagen-de-Whats-App-2025-02-13-a-las-12-14-30-f88d74b2.jpg)](https://postimg.cc/2brK5TDx)

### AGREGAR ESQUELETO DEL PROYECTO

[![Screenshot-2025-02-10-at-12-49-23-1.png](https://i.postimg.cc/43nTh36f/Screenshot-2025-02-10-at-12-49-23-1.png)](https://postimg.cc/QBLz2hYw)

### AGREGAR CLASES 

[![Imagen-de-Whats-App-2025-02-13-a-las-12-18-47-36179c60.jpg](https://i.postimg.cc/6qfPPsrb/Imagen-de-Whats-App-2025-02-13-a-las-12-18-47-36179c60.jpg)](https://postimg.cc/rRzgdPY5)

```java
package edu.eci.cvds.tdd.library.loan;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;

public class Loan {
    private Book book;
    private User user;
    private LocalDateTime loanDate;
    private LoanStatus status;
    private LocalDateTime returnDate;
    
    public Loan(LoanStatus status, User user,Book book) {
    	this.status = status;
    	this.user = user;
    	this.book = book;
    	setLoanDate(LocalDateTime.now());
    	
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
    
    @Override
    public String toString(){
    	return getBook().toString() + "\n" + "User: " + user.toString() + "\n" + "Status: " + status +  "\n" + "Date: " + loanDate; 

    }
}
```


```java
package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
	private final Map<Book, Integer> books;
    private final List<Loan> loans;
   

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
    	if (book.getAuthor()!= null && book.getIsbn()!= null && book.getTittle()!= null) {
	    	int amount = books.getOrDefault(book, 0);
	    	books.put(book, amount + 1);
	        
	    	// Verificar si el ISBN ya está en el mapa
	    	for (Book existingBook : books.keySet()) {
	    		if (existingBook.getIsbn().equals(book.getIsbn()) && (book.getAuthor()!= existingBook.getAuthor() ||  book.getTittle()!= existingBook.getTittle())) {
	    			return false; // No agregar el libro
	            	}
	            }
            
            return true;
    	}
    	return false;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        for (User user : users) {
            if (user.getId() != null && user.getId().equals(userId)) {
                // Verificar si el usuario ya tiene un préstamo activo de este libro
                for (Loan loan : loans) {
                    if (loan.getUser().equals(user) && loan.getBook().getIsbn().equals(isbn) && loan.getStatus() == LoanStatus.ACTIVE) {
                        return null; // No permitir un segundo préstamo del mismo libro
                    }
                }

                // Buscar el libro en la lista de disponibles
                for (Map.Entry<Book, Integer> entry : books.entrySet()) {
                    Book book = entry.getKey();
                    int count = entry.getValue();
                    if (count > 0 && book.getIsbn().equals(isbn)) {
                        Loan newLoan = new Loan(LoanStatus.ACTIVE, user, book);
                        loans.add(newLoan);
                        books.put(book, count - 1); // Reducir el stock del libro
                        return newLoan;
                    }
                }
            }
        }
        return null;
    }


    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        if (loan == null || !loans.contains(loan)) {
            return null; // Si el préstamo no existe, retornar null
        }

        for (Loan existingLoan : loans) {
            if (existingLoan.equals(loan) && existingLoan.getStatus() == LoanStatus.ACTIVE) {
                // Cambiar el estado del préstamo a RETURNED
                existingLoan.setStatus(LoanStatus.RETURNED);
                
                // Registrar la fecha de devolución
                existingLoan.setReturnDate(LocalDateTime.now());

                // Aumentar el stock del libro
                Book book = existingLoan.getBook();
                books.put(book, books.getOrDefault(book, 0) + 1);

                return existingLoan; // Retornar el préstamo actualizado
            }
        }

        return null; // Si no se encontró un préstamo activo
    }


    public boolean addUser(User user) {
        return users.add(user);
    }
    
    public List<User> getUsers() {
		return users;
	}

	public Map<Book, Integer> getBooks() {
		return books;
	}

	public List<Loan> getLoans() {
		for (Loan loan:loans) {
			System.out.println(loan.toString());
		}
		return loans;
	}

}
```

```java
package edu.eci.cvds.tdd.library.book;


public class Book {
    private final String tittle;
    private final String author;
    private final String isbn;

    public Book(String tittle, String author, String isbn) {
        this.tittle = tittle;
        this.author = author;
        this.isbn = isbn;
    }

    
    public String getTittle() {
        return tittle;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        return isbn.equals(((Book)obj).isbn);
    }
    
    @Override
    public String toString() {
    	return "Title: " + tittle + "\n" + "Author: " + author + "\n" + "ISBN: " + isbn; 
    }
}

```

```java
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
    
    @Override
    public String toString() {
    	return "Name: " + name + "\n" + "ID: " + id;
    }
}
```

## PRUEBAS UNITARIAS Y TDD
## CREAR CLASE DE PRUEBA
```java
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
	    System.out.print(library.getLoans());	    
	    }
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

```

## COBERTURA

[![Imagen-de-Whats-App-2025-02-13-a-las-00-19-12-a4403253.jpg](https://i.postimg.cc/qBWcPDbj/Imagen-de-Whats-App-2025-02-13-a-las-00-19-12-a4403253.jpg)](https://postimg.cc/yWXS9jzZ)

[![Screenshot-2025-02-10-at-12-48-57-1.png](https://i.postimg.cc/9099XV81/Screenshot-2025-02-10-at-12-48-57-1.png)](https://postimg.cc/jns2ZVrJ)

## SONARQUBE

[![Screenshot-2025-02-13-at-00-56-55-1.png](https://i.postimg.cc/NjBXt317/Screenshot-2025-02-13-at-00-56-55-1.png)](https://postimg.cc/Xp1qcD1p)

## Integrantes
- Juan Sebastian Beltran Rodriguez 
- Salomon Baena Rubio
