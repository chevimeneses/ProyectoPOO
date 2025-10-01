import java.util.ArrayList;
import java.util.List;

/**
 * Representa una visita de un cliente al establecimiento
 * Gestiona todas las transacciones realizadas durante la visita, incluyendo
 * compra y renta de libros, consumo de productos de cafetería, cálculo de totales
 * y aplicación del sistema de puntos de fidelidad
 * 
 * @author Equipo 4
 * @version 1.0
 * @since 2025
 */
public class Visita{

	private int idVisita;
	private Cliente cliente;
	private String fecha;
	private double total;
	private List<Libro> librosRentados;
	private List<Libro> librosComprados;
	private List<ProductoCafeteria> productosConsumidos;
	private static int contadorIds = 1;

	/**
	 * Constructor que crea una nueva visita para un cliente
	 * Inicializa las colecciones vacías y registra la visita en el historial del cliente
	 * 
	 * @param cliente Cliente que realiza la visita
	 * @param fecha Fecha de la visita en formato "dd/MM/yyyy"
	 */

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

	/**
	 * Agrega un libro a la lista de libros rentados durante la visita
	 * Cambia el estado del libro a no disponible 
	 * 
	 * @param libro Libro a rentar
	 */
	public void agregarLibroRentado(Libro libro) {
	    librosRentados.add(libro);
	    libro.marcarNoDisponible();
	    System.out.println("Libro rentado: " + libro.getTitulo());
 	}

	/**
 	 * Devuelve todos los libros rentados durante la visita, marcándolos como disponibles nuevamente
 	 * Se ejecuta automáticamente al finalizar la visita
 	 */
 	public void devolverRentados(){
		if(!librosRentados.isEmpty()){
			for(Libro libro : librosRentados){
				libro.marcarDisponible();
				System.out.println("Ha devuelto el libro: " + libro.getTitulo());
			}
			librosRentados.clear();
		}
	}

	/**
	 * Agrega un libro a la lista de libros comprados durante la visita
	 * Cambia el estado del libro a no disponible
	 * 
	 * @param libro Libro a comprar
	 */
	public void agregarLibroComprado(Libro libro) {
		librosComprados.add(libro); 
		libro.marcarNoDisponible();  
		System.out.println("Libro comprado: " + libro.getTitulo());  
	}

	/**
	 * Agrega un producto a la lista de productos consumidos durante la visita
	 * 
	 * @param producto Producto de cafetería a consumir
	 */
	public void agregarProducto(ProductoCafeteria producto) {
		productosConsumidos.add(producto);
		System.out.println("Producto consumido: " + producto.getProducto());
	}
    
	/**
	 * Calcula el total de la visita sumando el precio de todos los ítems
	 * Para los libros comprados: suma el precio completo
	 * Para los productos consumidos: suma el precio del producto
	 * Para los libros rentados: se cobra el 10% del precio de venta que tiene el libro
	 */
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
		System.out.println("Total calculado: $" + String.format("%.2f",total));
	}

	/**
	 * Aplica un descuento basado en puntos qur tiene el cliente
	 * 10 puntos equivalen a 10% de descuento sobre el total
	 * 
	 * @param puntosUsados Cantidad de puntos a canjear
	 * @return {@code true} si el descuento fue aplicado exitosamente, o {@code false} si no hay puntos suficientes
	 */
	public boolean puntosDescuento(int puntosUsados){
		calcularTotal();
		int puntosCliente = cliente.getPuntos();
		if(puntosCliente >= puntosUsados){
			double puntosDescuento = puntosUsados;
			double descuentoTotal = total * (puntosDescuento/100.0);
			total -= descuentoTotal;

			boolean puntosSuficientes = cliente.usarPuntos(puntosUsados);
			if(puntosSuficientes == true){
				System.out.println("Se aplicó un descuento del " + (int)puntosDescuento + "%");
				System.out.println("Se descontaron: " + String.format("%.2f",descuentoTotal) + "$");
				System.out.println("Total: " + String.format("%.2f",total) + "$");
			}

			return true;

		} else {
			System.out.println("Se necesitan " + puntosUsados + " puntos.");
			System.out.println("Usted tiene " + puntosCliente + " puntos.");
			return false;
		}
	}

	/**
	 * Aplica los puntos ganados al cliente basado en el total gastado durante su visita
	 * Calcula 1 punto por cada $50.00 gastados
	 */
	public void aplicarPuntosCliente(){
	    cliente.ganarPuntos(total);
    	System.out.println("Puntos aplicados al cliente: " + (int)(total / 50) + " puntos");
	}

	/**
	 * Finaliza la visita procesando todos los cálculos y aplicando puntos respectivos al cliente
	 * Si no se usaron puntos para descuento, aplica los puntos ganados al total del cliente
	 * Devuelve automáticamente los libros rentados
	 * 
	 * @param puntosUsados Indica si se aplicó descuento por puntos durante la visita
	 */
	public void finalizarVisita(boolean puntosUsados){
    	if(total > 0.0 && puntosUsados == false){
    		aplicarPuntosCliente();
    		calcularTotal();
    	}

    	devolverRentados();
    	System.out.println("Visita #" + idVisita + " finalizada. Total: $" + String.format("%.2f",total));
	}
    
	/**
	 * Devuelve el ID único de la visita
	 * @return El ID de la visita
	 */
	public int getIdVisita() {
		return idVisita;
	}
    
	/**
	 * Devuelve el objeto Cliente asociado a esta visita
	 * @return El cliente de la visita
	 */
	public Cliente getCliente() {
		return cliente;
	}
    
	/**
	 * Devuelve la fecha en que se realizó la visita
	 * @return La fecha de la visita
	 */
	public String getFecha() {
        	return fecha;
	}
    
	/**
	 * Devuelve el costo total calculado de la visita
	 * @return El total monetario de la visita
	 */
	public double getTotal() {
		return total;
	}
    
	/**
	 * @return Lista de libros rentados durante la visita
	 */
	public List<Libro> getLibrosRentados() {
		return librosRentados;
	}
    
	/**
	 * @return Lista de libros comprados durante la visita
	 */
	public List<Libro> getLibrosComprados() {
		return librosComprados;
	}
    
	/**
	 * @return Lista de productos de cafetería consumidos durante la visita
	 */
	public List<ProductoCafeteria> getProductosConsumidos() {
		return productosConsumidos;
	}
    
	/**
	 * @return Cantidad de libros rentados en la visita
	 */
	public int getCantidadLibrosRentados() {
		return librosRentados.size();
	}
    
	/**
	 * @return Cantidad de libros comprados en la visita
	 */
	public int getCantidadLibrosComprados() {
		return librosComprados.size();
	}
    
	/**
	 * @return Cantidad de productos consumidos en la visita
	 */
	public int getCantidadProductosConsumidos() {
		return productosConsumidos.size();
	}
    
	/**
	 * Muestra un reporte detallado de todos los ítems comprados, rentados y consumidos durante la visita
	 * Incluye los precios de todos estos mismos
	 */
	public void mostrarDetalles(){
    System.out.println("\n-Visita #" + idVisita + "-");
    System.out.println("Cliente: " + cliente.getNombre());
    System.out.println("Fecha: " + fecha);
    System.out.println("Total: $" + String.format("%.2f", total));
    System.out.println("\n- Libros Rentados -");
    
    if(librosRentados.isEmpty()){
			System.out.println("No hay libros rentados");
		} else {
			for (Libro libro : librosRentados){
				System.out.println("|" + libro.getTitulo() + " - $" + String.format("%.2f", libro.getPrecio() * 0.1));
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

	/**
	 * Genera una representación en String de toda la información resumida de la visita
	 *
	 * @return Un String con los datos principales de la visita
	 */
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

}