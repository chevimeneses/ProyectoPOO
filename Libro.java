public class Libro{

    private String titulo;
    private String autor;
    private String genero;
    private int id;
    private double precio;
    private boolean estado;
    private String devolucion;
    
    private static int contadorIds = 1;
    
    public Libro(String titulo, String autor, String genero, double precio, boolean estado){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.id = contadorIds++;
        this.precio = precio;
        this.estado = estado;
        this.devolucion = "";
    }

    public Libro(String titulo, String autor, String genero){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.id = contadorIds++;
        this.precio = 149.99;
        this.estado = true;
        this.devolucion = "";
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public int getId(){
        return id;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public boolean getEstado(){
        return estado;
    }

    public String getDevolucion(){
        return devolucion;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }
    
    public void setPrecio(double precio){
        this.precio = precio;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public void setDevolucion(String devolucion){
        this.devolucion = devolucion;
    }

    public void marcarDisponible(){
        this.estado = true;
    }

    public void marcarNoDisponible(){
        this.estado = false;
    }

    public boolean estaDisponible(){
        return estado;
    }

    public String toString() {
        String disponibilidad = estado ? "Disponible" : "No disponible";
        
        String infoLibro = "Libro ID#" + id + ": " + titulo +
            " | Autor: " + autor +
            " | Género: " + genero +
            " | Precio: $" + precio +
            " | Estado: " + disponibilidad;

        if(estado == false && devolucion.isEmpty() == false) {
            infoLibro += " | Fecha de devolución: " + devolucion;
        }
        return infoLibro;
    }
}
