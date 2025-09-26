import java.util.ArrayList;
import java.util.List;

public class Visita {
  //ATRIBUTOS DE LA CLASE
  private int idVisita;  // Identificador único de la visita (se genera automáticamente)
	private Cliente cliente;  // Cliente que realiza la visita (OBJETO Cliente como atributo)
  private String fecha;  // Fecha de la visita en formato simple (dd/MM/yyyy)
	private double total;  // Total gastado en la visita (se calcula automáticamente)
    
	//COLECCIONES 
	private List<Libro> librosRentados;  // Lista de libros que el cliente RENTA durante la visita
	private List<Libro> librosComprados;  // Lista de libros que el cliente COMPRA durante la visita  
	private List<ProductoCafeteria> productosConsumidos;  // Lista de productos de cafetería que el cliente CONSUME

	private static int contadorIds = 1;  // Contador estático para generar IDs automáticos y únicos

	//CONSTRUCTOR - Para crear nuevas visitas
	/**
	* Constructor que inicializa una nueva visita
	* @param cliente El cliente que realiza la visita
	* @param fecha La fecha de la visita en formato "dd/MM/yyyy"
	*/
	public Visita(Cliente cliente, String fecha) {
		this.idVisita = contadorIds++;
		this.cliente = cliente;
    this.fecha = fecha;
		this.total = 0.0;
        
 		//INICIALIZACIÓN DE COLECCIONES
		this.librosRentados = new ArrayList<>();  // Crear la lista VACÍA para libros rentados
		this.librosComprados = new ArrayList<>();  // Crear la lista VACÍA para libros comprados  
		this.productosConsumidos = new ArrayList<>();  // Crear la lista VACÍA para productos consumidos
		this.cliente.registrarVisita();  // Registrar esta visita en el contador de visitas del cliente
	}
    
	//MÉTODOS PARA AGREGAR ELEMENTOS A LAS COLECCIONES
	/**
	* Agrega un libro a la lista de libros RENTADOS
	* @param libro El libro a rentar
	*/
	public void agregarLibroRentado(Libro libro) {
    librosRentados.add(libro); // AÑADIR el libro a la colección de libros rentados
    libro.marcarNoDisponible();  // Cambiar el estado del libro a NO DISPONIBLE
    libro.setDevolucion("Devolver en 7 días");
    System.out.println("Libro rentado: " + libro.getTitulo());
 	}
    
	/**
	* Agrega un libro a la lista de libros COMPRADOS  
	* @param libro El libro a comprar
	*/
	public void agregarLibroComprado(Libro libro) {
		librosComprados.add(libro); 
		libro.marcarNoDisponible();  
		System.out.println("Libro comprado: " + libro.getTitulo());  
	}
    
	/**
	* Agrega un producto a la lista de productos CONSUMIDOS
	* @param producto El producto de cafetería consumido
	*/
	public void agregarProducto(ProductoCafeteria producto) {
		productosConsumidos.add(producto);  // AÑADIR el producto a la colección de productos consumidos
		System.out.println("Producto consumido: " + producto.getProducto());
	}
    
	// MÉTODOS PARA CALCULAR Y GESTIONAR LA VISITA
    
	/**
	* Calcula el total de la visita sumando todos los items
	* DEMUESTRA ITERACIÓN SOBRE COLECCIONES
	*/
	public void calcularTotal() {
    	this.total = 0.0;  // Reiniciar el total a 0 antes de calcular
    	for (Libro libro : librosComprados) {  // ITERAR sobre la colección de libros COMPRADOS
			total += libro.getPrecio();  // SUMAR el precio de cada libro comprado al total
	    }
    for (ProductoCafeteria producto : productosConsumidos) {  // ITERAR sobre la colección de productos CONSUMIDOS
		total += producto.getPrecio();  // SUMAR el precio de cada producto al total 
    }
	for (Libro libro : librosRentados) {  // ITERAR sobre la colección de libros RENTADOS
		double costoRenta = libro.getPrecio() * 0.10;  // Calcular costo de renta (10% del precio del libro)
		total += costoRenta;  // SUMAR el costo de renta al total
    }
		System.out.println("Total calculado: $" + total);
	}
    
	/**
	* Aplica los puntos ganados por esta visita al cliente
	* Según la regla: 1 punto por cada $50 gastados
	*/
	public void aplicarPuntosCliente() {  // El cliente gana puntos basado en el total gastado
	    cliente.ganarPuntos(total);
    	System.out.println("Puntos aplicados al cliente: " + (int)(total / 50) + " puntos");
	}
    
	/**
	* Finaliza la visita calculando totales y aplicando puntos
	*/
	public void finalizarVisita() {
    	calcularTotal();  // Paso 1: Calcular el total de la visita
    	aplicarPuntosCliente();  // Paso 2: Aplicar los puntos ganados al cliente
    	System.out.println("Visita #" + idVisita + " finalizada. Total: $" + total);
  	}
    
	// GETTERS (para acceder a la información)
    
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
    
	// MÉTODOS PARA OBTENER LAS COLECCIONES (GETTERS)
    
	public List<Libro> getLibrosRentados() {
		return librosRentados;
	}
    
	public List<Libro> getLibrosComprados() {
        	return librosComprados;
	}
    
	public List<ProductoCafeteria> getProductosConsumidos() {
        	return productosConsumidos;
	}
    
	// MÉTODOS PARA CONTAR ELEMENTOS EN COLECCIONES (GETTERS)
    
	public int getCantidadLibrosRentados() {
		return librosRentados.size();  // Usar método size() de la colección para contar elementos
	}
    
	public int getCantidadLibrosComprados() {
		return librosComprados.size();
	}
    
	public int getCantidadProductosConsumidos() {
		return productosConsumidos.size();
	}
    
	// MÉTODO toString() - Para mostrar información de la visita
    
	@Override
	public String toString() {
    	return "Visita #" + idVisita + 
		    " | Cliente: " + cliente.getNombre() + 
			" | Fecha: " + fecha + 
			" | Total: $" + total +
			" | Rentados: " + getCantidadLibrosRentados() +
			" | Comprados: " + getCantidadLibrosComprados() +
			" | Productos: " + getCantidadProductosConsumidos();
	}
    
	// MÉTODO PARA MOSTRAR DETALLES COMPLETOS DE LA VISITA
    
	/**
	* Muestra un reporte detallado de toda la visita
	* DEMUESTRA ITERACIÓN SOBRE MULTIPLES COLECCIONES
	*/
	public void mostrarDetalles() {
    	System.out.println("=== DETALLES DE VISITA #" + idVisita + " ===");
    	System.out.println("Cliente: " + cliente.getNombre());
    	System.out.println("Fecha: " + fecha);
    	System.out.println("Total: $" + total);
    	System.out.println("\n--- Libros Rentados ---");
    	if (librosRentados.isEmpty()) {
			System.out.println("No hay libros rentados");
		} else {
			for (Libro libro : librosRentados) {  // ITERAR sobre la colección de libros rentados
				System.out.println("• " + libro.getTitulo() + " - $" + libro.getPrecio());
     		}
		}
        
		System.out.println("\n--- Libros Comprados ---");
		if (librosComprados.isEmpty()) {
			System.out.println("No hay libros comprados");
		} else {
			for (Libro libro : librosComprados) {  // ITERAR sobre la colección de libros comprados
				System.out.println("• " + libro.getTitulo() + " - $" + libro.getPrecio());
			}
		}
        
		System.out.println("\n--- Productos Consumidos ---");
		if (productosConsumidos.isEmpty()) {
			System.out.println("No hay productos consumidos");
		} else {
			for (ProductoCafeteria producto : productosConsumidos) {  // ITERAR sobre la colección de productos consumidos
				System.out.println("• " + producto.getProducto() + " - $" + producto.getPrecio());
			}
		}
	}
}
