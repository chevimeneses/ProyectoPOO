public class ProductoCafeteria{
    
    private String producto;
    private int id;
    private double precio;
    private String categoria;
    private static int contadorIds = 1;
    
    public ProductoCafeteria(String producto, double precio, String categoria){
        this.producto = producto;
        this.id = contadorIds++;
        this.precio = precio;
        this.categoria = categoria;
    }
    
    public String getProducto(){
        return producto;
    }
    
    public void setProducto(String producto){
        this.producto = producto;
    }
    
    public int getId(){
        return id;
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
        return "| Producto: " + producto + 
        " | ID: " + id + 
        " | Precio: " + precio + 
        " | Categoría: " + categoria;
    }

}