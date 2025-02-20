package edu.eci.cvds.tdd.library.user;

/**
 * Representa un usuario de la biblioteca con nombre e identificación.
 */
public class User {
    private String name;
    private String id;

    /**
     * Constructor para inicializar un usuario con nombre e identificación.
     *
     * @param name Nombre del usuario.
     * @param id   Identificación del usuario.
     */
    public User(String name, String id) {
        setName(name);
        setId(id);
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Asigna un nombre al usuario.
     *
     * @param name El nombre del usuario.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            // Se podría manejar una excepción aquí (comentado en el código original).
        }
        this.name = name;
    }

    /**
     * Obtiene la identificación del usuario.
     *
     * @return La identificación del usuario.
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna una identificación al usuario.
     *
     * @param id La identificación del usuario.
     */
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            // Se podría manejar una excepción aquí (comentado en el código original).
        }
        this.id = id;
    }

    /**
     * Representación en cadena del usuario.
     *
     * @return Una cadena con el nombre e identificación del usuario.
     */
    @Override
    public String toString() {
        return "Name: " + name + "\n" + "ID: " + id;
    }
}
