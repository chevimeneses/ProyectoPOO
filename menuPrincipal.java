import java.util.*;
import java.util.Scanner;

public class menuPrincipal {

    private static Map<Integer, Libro> catalogoLibros = new HashMap<>();
    private static Map<Integer, Cliente> listaClientes = new HashMap<>();
    private static Map<Integer, ProductoCafeteria> menuCafeteria = new HashMap<>();

    private static Set<Integer> idRegistrados = new TreeSet<>();

    private static List<Visita> visitas = new ArrayList<>();

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
            System.out.println("Bienvenido");
            iniciarVisita(clienteActual);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public static void registrarNuevoCliente() {
    System.out.print("Nombre: ");
    String nombre = escaner.nextLine();

    System.out.print("Correo: ");
    String correo = escaner.nextLine();

    System.out.print("Teléfono: ");
    String telefono = escaner.nextLine();

    Cliente nuevo = new Cliente(nombre, correo, telefono);
    listaClientes.put(nuevo.getId(), nuevo);
    System.out.println("Su ID es: " + nuevo.getId());
}



    public static void iniciarVisita(Cliente cliente) {
    menuCliente accionesCliente = new menuCliente(catalogoLibros, menuCafeteria, escaner); 

    boolean continuar = true;

    while (continuar) {
        System.out.println("1. Ver catálogo de libros");
        System.out.println("2. Comprar libro");
        System.out.println("3. Ver menú de cafetería");
        System.out.println("4. Comprar producto de cafetería");
        System.out.println("5. Salir");
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
            case 2 -> accionesCliente.comprarLibro(cliente);
            case 3 -> accionesCliente.mostrarCafeteria();
            case 4 -> accionesCliente.comprarProducto(cliente);
            case 5 -> { 
                System.out.println("Gracias por sus compras <3");
                continuar = false;
            }
            default -> System.out.println("Opción inválida");
        }
    }
}



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
