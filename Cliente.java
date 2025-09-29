public class Cliente{

	private String nombre;
	private int id;
	private String email;
	private String telefono;
	private int puntos;
	private int visitas;
	private static int contadorIds = 1;
	
	public Cliente(String nombre, String email, String telefono){
        this.nombre = nombre;
        this.id = contadorIds++;
        this.email = email;
        this.telefono = telefono;
        this.puntos = 0;
        this.visitas = 0;
    }
    
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
		if (puntos >= 0){
        	this.puntos = puntos;
    	}
	}

	public void ganarPuntos(double montoGastado){
		if(montoGastado >0){
			int puntosGanados = (int)(montoGastado / 50); 
			this.puntos += puntosGanados;
		}
	}

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
	
	public void registrarVisita(){
		this.visitas++;
	}
	
	public String toString(){
		return "Cliente: " + nombre + 
			" | ID: " + id + 
			" | Email: " + email +
			" | Visitas: " + visitas + 
			" | Puntos: " + puntos;
	}
}