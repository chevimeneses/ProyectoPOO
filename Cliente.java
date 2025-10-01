/**
 * Representa un cliente del negocio, gestionando su información de contacto
 * sus puntos acumulados y las visitas realizadas
 *  @author Equipo 4
 * @version 1.0
 * @since 2025
 */
public class Cliente{

	private String nombre;
	private int id;
	private String email;
	private String telefono;
	private int puntos;
	private int visitas;
	private static int contadorIds = 1;

	/**
     * Constructor para crear un nuevo cliente
     * Asigna un ID único que va incrementado e inicializa los puntos y visitas en cero
     *
     * @param nombre   El nombre completo del cliente
     * @param email    El correo electrónico del cliente
     * @param telefono El número de teléfono del cliente
     */
	public Cliente(String nombre, String email, String telefono){
        this.nombre = nombre;
        this.id = contadorIds++;
        this.email = email;
        this.telefono = telefono;
        this.puntos = 0;
        this.visitas = 0;
    }
    
	/**
     * Devuelve el nombre del cliente
     * @return El nombre del cliente
     */
    public String getNombre(){
        return nombre;
    }
    
	/**
     * Devuelve el ID único del cliente
     * @return El ID numérico del cliente
     */
    public int getId(){
        return id;
    }
    
	/**
     * Devuelve el correo electrónico del cliente
     * @return El email del cliente
     */
    public String getEmail(){
        return email;
    }
    
	/**
     * Devuelve el número de teléfono del cliente
     * @return El teléfono del cliente
     */
    public String getTelefono(){
        return telefono;
   	}

	/**
     * Devuelve el saldo actual de puntos de lealtad
     * @return La cantidad de puntos acumulados
     */
 	public int getPuntos(){
        	return puntos;
	}

	/**
     * Devuelve el número total de visitas registradas
     * @return El contador de visitas
     */
	public int getVisitas(){
		return visitas;
	}
	
	/**
     * Actualiza el nombre del cliente
     * @param nombre El nuevo nombre para el cliente
     */
	public void setNombre(String nombre){
    		this.nombre = nombre;
    }

	/**
     * Actualiza el correo electrónico del cliente
     * @param email El nuevo email para el cliente
     */
	public void setEmail(String email){
		this.email = email;
	}

	/**
     * Actualiza el número de teléfono del cliente
     * @param telefono El nuevo teléfono para el cliente
     */
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}

	/**
     * Actualiza el saldo de puntos del cliente
     * El valor asignado no puede ser negativo
     * @param puntos La nueva cantidad de puntos
     */
	public void setPuntos(int puntos){
		if (puntos >= 0){
        	this.puntos = puntos;
    	}
	}

	/**
     * Calcula y añade puntos al cliente basado en el monto de su compra
     * La regla de conversión de los puntos es 1 punto por cada $50.00 gastados
     * No se añaden puntos por montos negativos o cero
     *
     * @param montoGastado El total de la compra realizada por el cliente
     */
	public void ganarPuntos(double montoGastado){
		if(montoGastado >0){
			int puntosGanados = (int)(montoGastado / 50); 
			this.puntos += puntosGanados;
		}
	}

	/**
     * Canjea una cantidad de puntos del saldo del cliente por un descuento en su compra 
     * La operación solo se realiza si el cliente tiene puntos suficientes
     * y la cantidad a usar es positiva
     *
     * @param puntosUsados La cantidad de puntos a canjear
     * @return {@code true} si los puntos se descontaron exitosamente,
     * o {@code false} si el saldo era insuficiente.
     */
	public boolean usarPuntos(int puntosUsados){
		if(puntosUsados <= this.puntos && puntosUsados > 0){
			this.puntos -= puntosUsados;
			System.out.println("Puntos usados correctamente.");
			return true;
		} else {
			System.out.println("Puntos insuficientes: " + puntos);
			return false;
		}
	}

	/**
     * Incrementa en uno el contador de visitas del cliente
     */
	public void registrarVisita(){
		this.visitas++;
	}

	/**
     * Genera una representación en String de toda la información actual del cliente
     *
     * @return Un String con los datos principales del cliente
     */
	public String toString(){
		return "Cliente: " + nombre + 
			" | ID: " + id + 
			" | Email: " + email +
			" | Visitas: " + visitas + 
			" | Puntos: " + puntos;
	}
}