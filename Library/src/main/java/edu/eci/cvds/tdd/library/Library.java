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