public class ProductoCafeteria{
    private String producto;
    private int id;
    private double precio;
    private String categoria;
    
    //Constructores
    public ProductoCafeteria(String producto, int id, double precio, String categoria){
        this.producto = producto;
        this.id = contadorIds++;
        this.precio = precio;
        this.categoria = categoria;
    }
    
    //Getters y Setters
    
    public String getProducto(){
        return producto;
    }
    
    public void setProducto(String producto){
        this.producto = producto;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    public void setCategoria(String categoria){
        this.categoria = categoria; 
    }
    
    public String toString(){
        return "[Producto: " + producto + 
        ", ID: " + id + 
        ", Precio: " + precio + 
        ", Categoría: " + "]";
    }
}