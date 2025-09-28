import java.util.ArrayList;
import java.util.List;

public class Visita{

	private int idVisita;
	private Cliente cliente;
	private String fecha;
	private double total;
	private List<Libro> librosRentados;
	private List<Libro> librosComprados;
	private List<ProductoCafeteria> productosConsumidos;
	private static int contadorIds = 1;

	public Visita(Cliente cliente, String fecha) {
		this.idVisita = contadorIds++;
		this.cliente = cliente;
    	this.fecha = fecha;
		this.total = 0.0;
		this.librosRentados = new ArrayList<>();
		this.librosComprados = new ArrayList<>();
		this.productosConsumidos = new ArrayList<>();
		this.cliente.registrarVisita();
	}

	public void agregarLibroRentado(Libro libro) {
	    librosRentados.add(libro);
	    libro.marcarNoDisponible();
	    System.out.println("Libro rentado: " + libro.getTitulo());
 	}

	public void agregarLibroComprado(Libro libro) {
		librosComprados.add(libro); 
		libro.marcarNoDisponible();  
		System.out.println("Libro comprado: " + libro.getTitulo());  
	}

	public void agregarProducto(ProductoCafeteria producto) {
		productosConsumidos.add(producto);
		System.out.println("Producto consumido: " + producto.getProducto());
	}
    
	public void calcularTotal(){
		this.total = 0.0;
		for(Libro libro : librosComprados){
			total += libro.getPrecio();
		}
		for(ProductoCafeteria producto : productosConsumidos){
			total += producto.getPrecio();
		}
		for(Libro libro : librosRentados){
			double costoRenta = libro.getPrecio() * 0.10;
			total += costoRenta;
		}
		System.out.println("Total calculado: $%.2f%n" + total);
	}

	public void aplicarPuntosCliente(){
	    cliente.ganarPuntos(total);
    	System.out.println("Puntos aplicados al cliente: " + (int)(total / 50) + " puntos");
	}
    
	public void finalizarVisita(){
    	calcularTotal();
    	aplicarPuntosCliente();
    	System.out.println("Visita #" + idVisita + " finalizada. Total: $" + total);
	}
    
	public int getIdVisita() {
		return idVisita;
	}
    
	public Cliente getCliente() {
		return cliente;
	}
    
	public String getFecha() {
        	return fecha;
	}
    
	public double getTotal() {
		return total;
	}
    
	public List<Libro> getLibrosRentados() {
		return librosRentados;
	}
    
	public List<Libro> getLibrosComprados() {
		return librosComprados;
	}
    
	public List<ProductoCafeteria> getProductosConsumidos() {
		return productosConsumidos;
	}
    
	public int getCantidadLibrosRentados() {
		return librosRentados.size();
	}
    
	public int getCantidadLibrosComprados() {
		return librosComprados.size();
	}
    
	public int getCantidadProductosConsumidos() {
		return productosConsumidos.size();
	}
    
	public void mostrarDetalles(){
    System.out.println("\n-Visita #" + idVisita + "-");
    System.out.println("Cliente: " + cliente.getNombre());
    System.out.println("Fecha: " + fecha);
    System.out.println("Total: $" + total);
    System.out.println("\n- Libros Rentados -");
    
    if(librosRentados.isEmpty()){
			System.out.println("No hay libros rentados");
		} else {
			for (Libro libro : librosRentados){
				System.out.println("|" + libro.getTitulo() + " - $" + libro.getPrecio() * 0.1);
     		}
		}
        
		System.out.println("\n- Libros Comprados -");
		if(librosComprados.isEmpty()){
			System.out.println("No hay libros comprados");
		} else {
			for(Libro libro : librosComprados){
				System.out.println("|" + libro.getTitulo() + " - $" + libro.getPrecio());
			}
		}
        
		System.out.println("\n- Productos Consumidos -");
		if(productosConsumidos.isEmpty()) {
			System.out.println("No hay productos consumidos");
		} else {
			for(ProductoCafeteria producto : productosConsumidos){
				System.out.println("|" + producto.getProducto() + " - $" + producto.getPrecio());
			}
		}
	}

	public String toString() {
		return "Visita #" + idVisita + 
		    " | Cliente: " + cliente.getNombre() + 
			" | Fecha: " + fecha + 
			" | Total: $" + total +
			" | Rentados: " + getCantidadLibrosRentados() +
			" | Comprados: " + getCantidadLibrosComprados() +
			" | Productos: " + getCantidadProductosConsumidos();
	}

}
