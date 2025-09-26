public class Libro{

    //Atributos de la clase
    //Datos del libro
    private String titulo;
    private String autor;
    private String genero;
    private int id;
    private double precio;
    private boolean estado; //true = disponible, false = no disponible
    private String devolucion;
    
    //Control para IDs automáticos
    private static int contadorIds = 1;
    
    //Constructor completo para registrar un nuevo libro
    public Libro(String titulo, String autor, String genero, double precio, boolean estado){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.id = contadorIds++;
        this.precio = precio;
        this.estado = estado;  //Usa el valor del parámetro
        this.devolucion = "";
    }

    //Segundo Constructor (para usar valor por defecto al precio y estado disponible)

    public Libro(String titulo, String autor, String genero){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.id = contadorIds++;
        this.precio = 149.99;  //Por defecto $149.99
        this.estado = true;  //Por defecto disponible
        this.devolucion = "";
    }
    

        //Getters (para ver la información)
    
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
    
    //Setters (para actualizar información)
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

    //Métodos especiales del negocio
    //Para marcar libro como disponible
    public void marcarDisponible(){
        this.estado = true;
    }

    //Para marcar libro como no disponible
    public void marcarNoDisponible(){
        this.estado = false;
    }

    //Para verificar si el libro está disponible
    public boolean estaDisponible(){
        return estado;
    }

    //Para mostrar la información del libro
    @Override
    public String toString() {
        String disponibilidad = estado ? "Disponible" : "No disponible";
        
        String infoLibro = "Libro #" + id + ": " + titulo +
            " | Autor: " + autor +
            " | Género: " + genero +
            " | Precio: $" + precio +
            " | Estado: " + disponibilidad;

    if (!estado && !devolucion.isEmpty()) {
        infoLibro += " | Fecha de devolución: " + devolucion;
    }

    return infoLibro;
}
}
