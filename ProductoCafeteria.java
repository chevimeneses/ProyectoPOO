/**
 * Representa un producto del menú de la cafetería
 * Contiene la información de los productos (alimentos y bebidas)
 * 
 * @author Equipo 4
 * @version 1.0
 * @since 2025
 */
public class ProductoCafeteria{
    
    private String producto;
    private int id;
    private double precio;
    private String categoria;
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear nuevos productos de la cafetería
     * Asigna un ID único de forma automática
     *
     * @param producto  El nombre del producto
     * @param precio    El precio de venta del producto
     * @param categoria La categoría a la que pertenece
     */
    public ProductoCafeteria(String producto, double precio, String categoria){
        this.producto = producto;
        this.id = contadorIds++;
        this.precio = precio;
        this.categoria = categoria;
    }
    
    /**
     * Devuelve el nombre del producto
     * @return El nombre del producto
     */
    public String getProducto(){
        return producto;
    }
    
    /**
     * Actualiza el nombre del producto
     * @param producto El nuevo nombre para el producto
     */
    public void setProducto(String producto){
        this.producto = producto;
    }
    
    /**
     * Devuelve el ID único del producto
     * @return El ID numérico
     */
    public int getId(){
        return id;
    }
    
    /**
     * Devuelve el precio del producto
     * @return El precio de venta
     */
    public double getPrecio(){
        return precio;
    }
    
    /**
     * Actualiza el precio del producto
     * @param precio El nuevo precio para el producto
     */
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    /**
     * Devuelve la categoría del producto
     * @return La categoría del producto
     */
    public String getCategoria(){
        return categoria;
    }
    
    /**
     * Actualiza la categoría del producto
     * @param categoria La nueva categoría para el producto
     */
    public void setCategoria(String categoria){
        this.categoria = categoria; 
    }
    
    /**
     * Genera una representación String con la información del producto
     *
     * @return Un String con los detalles e información del producto
     */
    public String toString(){
        return "| Producto: " + producto + 
        " | ID: " + id + 
        " | Precio: " + precio + 
        " | Categoría: " + categoria;
    }

}