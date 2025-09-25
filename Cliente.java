public class Cliente{

	//Atributos de la clase
	//Datos personales (Obligatorios)
	private String nombre;
	private int id;
	private String email;
	private String telefono;
	//Sistema de fidelidad
	private int puntos;
	private int visitas;
	//Control para IDs automáticos
	private static int contadorIds = 1;
	
	//Constructor para registrar a un nuevo cliente    
	public Cliente(String nombre, String email, String telefono){
        	this.nombre = nombre;
        	this.id = contadorIds++;  //ID automático y único para cada cliente
        	this.email = email;
        	this.telefono = telefono;
        	this.puntos = 0;  //Comienza con 0 puntos
        	this.visitas = 0; //Comienza con 0 visitas
    	}
    
    	//Getters (para ver la información)
    
    	public String getNombre(){
        	return nombre;
    	}
    
    	public int getId(){
        	return id;
    	}
    
    	public String getEmail(){
        	return email;
    	}
    
    	public String getTelefono(){
        	return telefono;
    	}

 	public int getPuntos(){
        	return puntos;
	}

	public int getVisitas(){
		return visitas;
	}
	
	//Setters (para actualizar información)

	public void setNombre(String nombre){
    		this.nombre = nombre;
    	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setTelefono(String telefono){
		this.telefono = telefono;
	}

	public void setPuntos(int puntos){
		this.puntos = puntos;
	}

	//Métodos especiales del negocio
	//Para ganar puntos (1 puntos por cada $50 gastados)
	public void ganarPuntos(double montoGastado){
		int puntosGanados = (int) (montoGastado / 50); 
		this.puntos += puntosGanados;
	}

	//Para usar puntos (con validación)
	public boolean usarPuntos(int puntosUsados){
		if (puntosUsados <= this.puntos){
			this.puntos -= puntosUsados;
			return true;  //Puntos usados exitosamente
		}
		return false;  //Si no hay suficientes puntos
	}
	
	//Para registrar una visita
	public void registrarVisita(){
		this.visitas++;
	}
	
	//Para mostrar la información completa
	public String toString(){
		return "Cliente: " + nombre + 
			" | ID: " + id + 
			" | Email: " + email +
			" | Visitas: " + visitas + 
			" | Puntos: " + puntos;
	}
}