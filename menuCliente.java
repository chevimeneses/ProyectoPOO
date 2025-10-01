import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

/**
 * Gestiona la interfaz de usuario para las operaciones de clientes
 * Controla el flujo de atención al cliente y procesamiento de visitas
 * 
 * @author Equipo 4
 * @version 1.0
 * @since 2025
 */
public class menuCliente{

    private Map<Integer, Libro> catalogoLibros;
    private Map<Integer, ProductoCafeteria> menuCafeteria;
    private Map<Integer, Cliente> listaClientes;
    private List<Visita> visitas;
    private Set<String> clientesEmail;
    private Set<String> clientesTele;

    private Scanner escaner = new Scanner(System.in);
    private Visita visitaActual;

    /**
     * Constructor que inicializa el menú de cliente con todas las colecciones necesarias para su correcto funcionamiento
     * 
     * @param catalogoLibros  Mapa del catálogo de libros disponibles
     * @param menuCafeteria   Mapa del menú de productos de cafetería
     * @param listaClientes   Mapa de clientes registrados
     * @param visitas         Lista del historial de visitas
     * @param clientesEmail   Set de emails únicos para validación
     * @param clientesTele    Set de teléfonos únicos para validación
     */
    public menuCliente(Map<Integer, Libro> catalogoLibros, 
                      Map<Integer, ProductoCafeteria> menuCafeteria,
                      Map<Integer, Cliente> listaClientes,
                      List<Visita> visitas,
                      Set<String> clientesEmail,
                      Set<String> clientesTele){
        this.catalogoLibros = catalogoLibros;
        this.menuCafeteria = menuCafeteria;
        this.listaClientes = listaClientes;
        this.visitas = visitas;
        this.clientesEmail = clientesEmail;
        this.clientesTele = clientesTele;
    }

    /**
     * Muestra las opciones principales para clientes y gestiona el acceso
     * Permite login de clientes existentes o registro de nuevos clientes
     */
    public void opcionesCliente(){
        System.out.println("\nMenú de Clientes");
        System.out.println("- Clientes existentes -\n");
        for(Cliente clientesActuales : listaClientes.values()){
            System.out.println("ID: " + clientesActuales.getId() + ", " + clientesActuales.getNombre());
        }

        System.out.print("Ingrese su ID (o escriba '0' para registrarse): ");
        
        int idCliente = escaner.nextInt();
        escaner.nextLine();

        if(idCliente == 0){
            registrarNuevoCliente();
            return;
        }

        if(listaClientes.containsKey(idCliente)){
            Cliente clienteActual = listaClientes.get(idCliente);
            System.out.println("Bienvenido: " + clienteActual.getNombre());
            iniciarVisita(clienteActual);
        } else {
            System.out.println("Cliente no registrado.");
        }
    }

    /**
     * Registra un nuevo cliente en el sistema con validación de datos únicos.
     * Valida que email y teléfono no estén previamente registrados.
     */
    public void registrarNuevoCliente(){
        System.out.print("\nNombre: ");
        String nombre = escaner.nextLine();

        String correo;
        do{
            System.out.print("Correo: ");
            correo = escaner.nextLine();

            if(clientesEmail.contains(correo)){
                System.out.println("Ya hay un cliente asociado al correo. Ingrese otro.");
            }
        } while(clientesEmail.contains(correo));
        clientesEmail.add(correo);

        String telefono;
        do{
            System.out.print("Teléfono: ");
            telefono = escaner.nextLine();

            if(clientesTele.contains(telefono)){
                System.out.println("Ya hay un cliente asociado al telefono. Ingrese otro.");
            }
        } while(clientesTele.contains(telefono));
        clientesTele.add(telefono);

        Cliente nuevo = new Cliente(nombre,correo,telefono);
        listaClientes.put(nuevo.getId(),nuevo);
        System.out.println("Registro exitoso. Su ID es: " + nuevo.getId());
    }

    /**
     * Inicia y gestiona una visita completa para un cliente
     * Crea una nueva visita y presenta el menú de opciones disponibles
     * 
     * @param cliente Cliente que realiza la visita
     */
    public void iniciarVisita(Cliente cliente){
        String fechaActual = obtenerFechaActual();
        visitaActual = new Visita(cliente,fechaActual);
        
        System.out.println("\nNueva visita creada - Fecha: " + fechaActual);
        
        boolean continuar = true;

        while(continuar){
            System.out.println("\nBienvenido a la Cafetería, estas son nuestras opciones:");
            System.out.println("1. Ver catálogo de libros");
            System.out.println("2. Comprar libro");
            System.out.println("3. Rentar libro");
            System.out.println("4. Ver menú de cafetería");
            System.out.println("5. Comprar producto de cafetería");
            System.out.println("6. Ver resumen de mi visita");
            System.out.println("7. Finalizar visita y salir");
            System.out.print("Elija una opción: ");

            int opcion = escaner.nextInt();
            escaner.nextLine();

            switch(opcion){
                case 1 -> mostrarLibros();
                case 2 -> comprarLibro();
                case 3 -> rentarLibro();
                case 4 -> mostrarCafeteria();
                case 5 -> comprarProducto();
                case 6 -> visitaActual.mostrarDetalles();
                case 7 -> { 
                    System.out.println("\n- Visita Terminada -");

                    boolean aplicarPuntos = false;
                    int puntosCliente = cliente.getPuntos();

                    if(puntosCliente >= 10){
                        System.out.println("Puntos actuales: " + puntosCliente);
                        System.out.println("Cada 10 puntos representa un 10% de descuento.");
                        System.out.print("1)Sí\n2)No\n¿Desea aplicar 10 puntos?: ");
                        int aplicar = escaner.nextInt();

                        if(aplicar == 1){
                            aplicarPuntos = visitaActual.puntosDescuento(10);
                        } 
                    }

                    visitaActual.finalizarVisita(aplicarPuntos);
                    visitas.add(visitaActual);
        
                    System.out.println("Gracias por su visita, " + cliente.getNombre() + ", vuelva pronto c:");
                    
                    continuar = false;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    /**
     * Muestra el catálogo completo de libros disponibles en el sistema
     * Incluye información de ID, título, autor, género, precio y estado
     */
    public void mostrarLibros(){
        System.out.println("\n- Catálogo de Libros -");
        for (Libro libro : catalogoLibros.values()){
            System.out.println(libro);
        }
    }

    /**
     * Procesa la compra de un libro por parte del cliente
     * Valida disponibilidad y agrega el libro a la visita actual
     */
    public void comprarLibro(){
        mostrarLibros();
        System.out.print("Ingrese el ID del libro a comprar: ");
        int idLibro = escaner.nextInt();

        escaner.nextLine();

        if(catalogoLibros.containsKey(idLibro)){
            Libro libro = catalogoLibros.get(idLibro);
            if(libro.estaDisponible()){
                libro.marcarNoDisponible();
                visitaActual.agregarLibroComprado(libro);
                visitaActual.calcularTotal();
                System.out.println("Libro agregado a tu carrito.");
            } else {
                System.out.println("El libro no está disponible.");
            }
        } else {
            System.out.println("ID no encontrado.");
        }
    }

    /**
     * Procesa la renta de un libro por parte del cliente
     * Aplica el costo del 10% del precio y gestiona la disponibilidad
     */
    public void rentarLibro() {
        mostrarLibros();
        System.out.print("Ingrese el ID del libro a rentar: ");
        int idLibro = escaner.nextInt();

        escaner.nextLine();

        if (catalogoLibros.containsKey(idLibro)) {
            Libro libro = catalogoLibros.get(idLibro);
            if(libro.estaDisponible()){
                libro.marcarNoDisponible();

                visitaActual.agregarLibroRentado(libro);
                visitaActual.calcularTotal();
            } else {
                System.out.println("El libro no está disponible para renta");
            }
        } else {
            System.out.println("ID no encontrado");
        }
    }

    /**
     * Muestra el menú completo de productos de cafetería disponibles
     * Incluye información de ID, nombre, precio y categoría de cada producto
     */
    public void mostrarCafeteria(){
        System.out.println("\n- Menú de Cafetería -");
        for (ProductoCafeteria producto : menuCafeteria.values()) {
            System.out.println(producto);
        }
    }

    /**
     * Procesa la compra de un producto de cafetería
     * Agrega el producto a la visita actual del cliente
     */
    public void comprarProducto(){
        mostrarCafeteria();
        System.out.print("Ingrese el ID del producto a comprar: ");

        int idProd = escaner.nextInt();
        escaner.nextLine();

        if (menuCafeteria.containsKey(idProd)) {
            ProductoCafeteria prod = menuCafeteria.get(idProd);
            visitaActual.agregarProducto(prod);
            visitaActual.calcularTotal();
            System.out.println("Producto añadido a tu carrito.");
        } else {
            System.out.println("ID no encontrado.");
        }
    }

    /**
     * Obtiene la fecha actual formateada para registrar visitas
     * 
     * @return String con la fecha actual en formato "dd/MM/yyyy"
     */
    public String obtenerFechaActual(){
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaRegistro = formato.format(hoy);
        return fechaRegistro;
    }
}