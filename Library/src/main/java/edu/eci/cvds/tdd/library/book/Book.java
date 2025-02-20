package edu.eci.cvds.tdd.library.book;

/**
 * Representa un libro con título, autor e ISBN.
 */
public class Book {
    private final String title;
    private final String author;
    private final String isbn;

    /**
     * Constructor para inicializar un libro con su título, autor e ISBN.
     *
     * @param title  El título del libro.
     * @param author El autor del libro.
     * @param isbn   El número ISBN del libro.
     */
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Obtiene el número ISBN del libro.
     *
     * @return El número ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Compara este libro con otro basado en su ISBN.
     *
     * @param obj El objeto a comparar.
     * @return {@code true} si los ISBN son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        return isbn.equals(((Book) obj).isbn);
    }

    /**
     * Representación en cadena del libro.
     *
     * @return Una cadena con el título, autor e ISBN del libro.
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
               "Author: " + author + "\n" +
               "ISBN: " + isbn;
