import java.util.*;
import java.util.Scanner;

public class menuPrincipal {

    private static Map<Integer, Libro> catalogoLibros = new HashMap<>();
    private static Map<Integer, Cliente> listaClientes = new HashMap<>();
    private static Map<Integer, ProductoCafeteria> menuCafeteria = new HashMap<>();

    private static Set<Integer> idRegistrados = new TreeSet<>();
    private static List<Visita> visitas = new ArrayList<>();  // Lista para guardar el historial de visitas

    private static Scanner escaner = new Scanner(System.in);

    public static void main(String[] args) {
        productosIniciales();

        while (true) {
            System.out.println("\nBienvenido a Café-Libreria");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            if (!escaner.hasNextInt()) {
                System.out.println("Número inválido");
                escaner.nextLine();
                continue;
            }
            
            int opcion = escaner.nextInt();
            escaner.nextLine();

            switch (opcion) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    System.out.print("Ingrese la clave de empleado: ");
                    String clave = escaner.nextLine();
                    if (clave.equals("holapapu")) {
                        administrarTienda();
                    } else {
                        System.out.println("Clave incorrecta.");
                    }
                    break;

                case 3:
                    System.out.println("Gracias por visitarnos <3");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // Método sin cambios: inicializa algunos productos de ejemplo
    public static void productosIniciales() {
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela");
        catalogoLibros.put(libro1.getId(), libro1); 

        Libro libro2 = new Libro("1984", "George Orwell", "Ciencia Ficción");
        catalogoLibros.put(libro2.getId(), libro2);

        ProductoCafeteria prod1 = new ProductoCafeteria("Café Americano", 35.00, "Bebidas");
        menuCafeteria.put(prod1.getId(), prod1);

        ProductoCafeteria prod2 = new ProductoCafeteria("Panini de Pavo", 60.0, "Comida");
        menuCafeteria.put(prod2.getId(), prod2);
    }

    // Método sin cambios: menú inicial de cliente
    public static void menuCliente() {
        System.out.println("\nMenú de Clientes");
        System.out.print("Ingrese su ID (o escriba '0' para registrarse): ");

        if (!escaner.hasNextInt()) {
            System.out.println("El ID debe ser un número");
            escaner.nextLine();
            return;
        }
        
        int idCliente = escaner.nextInt();
        escaner.nextLine();

        if (idCliente == 0) {
            registrarNuevoCliente();
            return;
        }

        if (listaClientes.containsKey(idCliente)) {
            Cliente clienteActual = listaClientes.get(idCliente);
            System.out.println("Bienvenido " + clienteActual.getNombre());
            iniciarVisita(clienteActual);  // Pasar el cliente a iniciarVisita
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    // Método sin cambios: registrar nuevo cliente
    public static void registrarNuevoCliente() {
        System.out.print("Nombre: ");
        String nombre = escaner.nextLine();

        System.out.print("Correo: ");
        String correo = escaner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = escaner.nextLine();

        Cliente nuevo = new Cliente(nombre, correo, telefono);
        listaClientes.put(nuevo.getId(), nuevo);
        System.out.println("Registro exitoso. Su ID es: " + nuevo.getId());
    }

    // MÉTODO COMPLETAMENTE MODIFICADO: Ahora implementa el sistema de visitas
    public static void iniciarVisita(Cliente cliente) {
        // CREAR UNA NUEVA VISITA AL INICIAR LA SESIÓN DEL CLIENTE
        String fechaActual = obtenerFechaActual();  // Obtener fecha actual formateada
        Visita visitaActual = new Visita(cliente, fechaActual);
        
        System.out.println("Nueva visita creada - Fecha: " + fechaActual);
        
        // Pasar la visita actual al constructor de menuCliente
        menuCliente accionesCliente = new menuCliente(catalogoLibros, menuCafeteria, escaner, visitaActual);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver catálogo de libros");
            System.out.println("2. Comprar libro");
            System.out.println("3. Rentar libro");  // NUEVA OPCIÓN
            System.out.println("4. Ver menú de cafetería");
            System.out.println("5. Comprar producto de cafetería");
            System.out.println("6. Ver resumen de mi visita");  // NUEVA OPCIÓN
            System.out.println("7. Finalizar visita y salir");  // OPCIÓN MODIFICADA
            System.out.print("Elija una opción: ");

            if (!escaner.hasNextInt()) {
                System.out.println("Número inválido");
                escaner.nextLine();
                continue;
            }

            int opcion = escaner.nextInt();
            escaner.nextLine();

            switch (opcion) {
                case 1 -> accionesCliente.mostrarLibros();
                case 2 -> accionesCliente.comprarLibro();      // Ahora usa el sistema de visita
                case 3 -> accionesCliente.rentarLibro();       // Nueva funcionalidad de renta
                case 4 -> accionesCliente.mostrarCafeteria();
                case 5 -> accionesCliente.comprarProducto();   // Ahora usa el sistema de visita
                case 6 -> accionesCliente.verResumenVisita();  // Nueva funcionalidad
                case 7 -> { 
                    // FINALIZAR LA VISITA Y PROCESAR TODOS LOS PUNTOS
                    System.out.println("\n=== FINALIZANDO VISITA ===");
                    visitaActual.finalizarVisita();  // Calcular total y aplicar puntos
                    visitas.add(visitaActual);       // Guardar en el historial de visitas
                    
                    // Mostrar resumen final al cliente
                    System.out.println("Visita finalizada exitosamente");
                    System.out.println("Total gastado: $" + visitaActual.getTotal());
                    System.out.println("Puntos ganados en esta visita: " + 
                                     (int)(visitaActual.getTotal() / 50) + " puntos");
                    System.out.println("✓ Total puntos acumulados: " + cliente.getPuntos() + " puntos");
                    System.out.println("¡Gracias por tu visita " + cliente.getNombre() + "!");
                    
                    continuar = false;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    // NUEVO MÉTODO AUXILIAR: Obtiene la fecha actual en formato dd/MM/yyyy
    public static String obtenerFechaActual() {
        java.time.LocalDate hoy = java.time.LocalDate.now();
        return String.format("%02d/%02d/%d", 
                            hoy.getDayOfMonth(), 
                            hoy.getMonthValue(), 
                            hoy.getYear());
    }

    // Método sin cambios: menú de administración para empleados
    public static void administrarTienda() {
        System.out.println("\nMenú para empleados");
        administrarTienda admin = new administrarTienda(catalogoLibros, menuCafeteria);

        boolean continuar = true;

        while (continuar) {
            System.out.println("1. Agregar Libro");
            System.out.println("2. Retirar Libro");
            System.out.println("3. Ver Libros");
            System.out.println("4. Editar Libro");
            System.out.println("5. Agregar Producto de Cafetería");
            System.out.println("6. Eliminar Producto");
            System.out.println("7. Ver Productos de Cafetería");
            System.out.println("8. Editar Producto");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            if (!escaner.hasNextInt()) {
                System.out.println("Número inválido");
                escaner.nextLine();
                continue;
            }

            int opcion = escaner.nextInt();
            escaner.nextLine();

            switch (opcion) {
                case 1 -> admin.anidarLibro();
                case 2 -> admin.retirarLibro();
                case 3 -> admin.verLibros();
                case 4 -> admin.editarLibro();
                case 5 -> admin.anidarProducto();
                case 6 -> admin.eliminarProducto();
                case 7 -> admin.verProductos();
                case 8 -> admin.editarProducto();
                case 9 -> {
                    System.out.println("Gracias Empleado :)");
                    continuar = false;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }
}