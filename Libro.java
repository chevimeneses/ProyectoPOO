/**
 * Representa un libro en el inventario de la librería
 * Contiene detalles de su información como título, autor, género, precio y su estado de disponibilidad
 * 
 * @author Equipo 4
 * @version 1.0
 * @since 2025
 */
public class Libro{

    private String titulo;
    private String autor;
    private String genero;
    private int id;
    private double precio;
    private boolean estado;
    
    private static int contadorIds = 1;
    
    /**
     * Constructor completo para crear un libro con todos sus atributos definidos
     * Asigna un ID único de forma automática
     *
     * @param titulo El título del libro
     * @param autor El autor del libro
     * @param genero El género literario del libro
     * @param precio El precio de venta del libro
     * @param estado El estado de disponibilidad (true si está disponible, false si no)
     */
    public Libro(String titulo, String autor, String genero, double precio, boolean estado){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.id = contadorIds++;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     * Constructor simplificado para crear un libro nuevo
     * Por defecto, el precio se establece en $149.99 y su estado como disponible
     *
     * @param titulo El título del libro
     * @param autor El autor del libro
     * @param genero El género literario del libro
     */
    public Libro(String titulo, String autor, String genero){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.id = contadorIds++;
        this.precio = 149.99;
        this.estado = true;
    }
    
    /**
     * Devuelve el título del libro
     * @return El título
     */
    public String getTitulo(){
        return titulo;
    }
    
    /**
     * Devuelve el autor del libro
     * @return El autor
     */
    public String getAutor(){
        return autor;
    }
    
    /**
     * Devuelve el género del libro
     * @return El género literario
     */
    public String getGenero(){
        return genero;
    }
    
    /**
     * Devuelve el ID único del libro
     * @return El ID numérico
     */
    public int getId(){
        return id;
    }
    
    /**
     * Devuelve el precio del libro
     * @return El precio
     */
    public double getPrecio(){
        return precio;
    }
    
    /**
     * Devuelve el estado de disponibilidad actual del libro
     * @return {@code true} si está disponible, {@code false} si no lo está
     */
    public boolean getEstado(){
        return estado;
    }

    /**
     * Actualiza el título del libro
     * @param titulo El nuevo título
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    /**
     * Actualiza el autor del libro
     * @param autor El nuevo autor
     */
    public void setAutor(String autor){
        this.autor = autor;
    }

    /**
     * Actualiza el género del libro
     * @param genero El nuevo género
     */
    public void setGenero(String genero){
        this.genero = genero;
    }
    
    /**
     * Actualiza el precio del libro. Se recomienda usar valores positivos
     * @param precio El nuevo precio
     */
    public void setPrecio(double precio){
        this.precio = precio;
    }

    /**
     * Actualiza el estado de disponibilidad del libro
     * @param estado El nuevo estado (true para disponible, false para no disponible)
     */
    public void setEstado(boolean estado){
        this.estado = estado;
    }

    /**
     * Cambia el estado del libro a disponible para venderse o rentarse (true)
     */
    public void marcarDisponible(){
        this.estado = true;
    }

    /**
     * Cambia el estado del libro a no disponible cuando se vende o se renta (false)
     */
    public void marcarNoDisponible(){
        this.estado = false;
    }

    /**
     * Verifica si el libro está disponible para poder venderlo o rentarlo
     *
     * @return {@code true} si el libro está disponible, {@code false} en caso contrario
     */
    public boolean estaDisponible(){
        return estado;
    }

    /**
     * Devuelve una representación String con la información completa del libro
     *
     * @return Un String con los detalles del libro
     */
    public String toString() {
        String disponibilidad = estado ? "Disponible" : "No disponible";
        
        String infoLibro = "| Libro ID#" + id + ": " + titulo +
            " | Autor: " + autor +
            " | Género: " + genero +
            " | Precio: $" + precio +
            " | Estado: " + disponibilidad;

        return infoLibro;
    }
}