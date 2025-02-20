package edu.eci.cvds.tdd.library.loan;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;

/**
 * Representa un préstamo de un libro en la biblioteca.
 */
public class Loan {
    private Book book;
    private User user;
    private LocalDateTime loanDate;
    private LoanStatus status;
    private LocalDateTime returnDate;

    /**
     * Constructor para inicializar un préstamo con su estado, usuario y libro asociado.
     * 
     * @param status Estado del préstamo.
     * @param user   Usuario que solicita el préstamo.
     * @param book   Libro que se presta.
     */
    public Loan(LoanStatus status, User user, Book book) {
        this.status = status;
        this.user = user;
        this.book = book;
        setLoanDate(LocalDateTime.now());
    }

    /**
     * Obtiene el libro asociado al préstamo.
     *
     * @return El libro prestado.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Asigna un libro al préstamo.
     *
     * @param book El libro a asociar al préstamo.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Obtiene el usuario que realizó el préstamo.
     *
     * @return El usuario asociado al préstamo.
     */
    public User getUser() {
        return user;
    }

    /**
     * Asigna un usuario al préstamo.
     *
     * @param user El usuario que solicita el préstamo.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene la fecha en la que se realizó el préstamo.
     *
     * @return La fecha del préstamo.
     */
    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    /**
     * Establece la fecha en la que se realizó el préstamo.
     *
     * @param loanDate La fecha del préstamo.
     */
    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Obtiene el estado actual del préstamo.
     *
     * @return El estado del préstamo.
     */
    public LoanStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado del préstamo.
     *
     * @param status El nuevo estado del préstamo.
     */
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    /**
     * Obtiene la fecha de devolución del préstamo.
     *
     * @return La fecha de devolución.
     */
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    /**
     * Establece la fecha de devolución del préstamo.
     *
     * @param returnDate La fecha de devolución.
     */
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Representación en cadena del préstamo.
     *
     * @return Una cadena con la información del libro, usuario, estado y fecha del préstamo.
     */
    @Override
    public String toString() {
        return getBook().toString() + "\n" +
               "User: " + user.toString() + "\n" +
               "Status: " + status + "\n" +
               "Date: " + loanDate;
    }
}
