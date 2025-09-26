import java.util.*;
import java.util.Scanner;

public class menuPrincipal {

    private static Map<Integer, Libro> catalogoLibros = new HashMap<>();
    private static Map<Integer, Cliente> listaClientes = new HashMap<>();
    private static Map<Integer, ProductoCafeteria> menuCafeteria = new HashMap<>();

    private static Set<Integer> idRegistrados = new TreeSet<>();

    private static List<Visita> Visitas = new ArrayList<>();

    private static Scanner escaner = new Scanner(System.in);


    public static void main(String[] args) {

        productosIniciales();

        while (true) {
            System.out.println("BIENVENIDO");
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
                    administrarTienda();
                    break;
                case 3:
                    System.out.println("Gracias por visitarnos");
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
        menuCafeteria.put(prod1.getID(), prod1);

        ProductoCafeteria prod2 = new ProductoCafeteria("Panini de Pavo", 60.0, "Comida");
        menuCafeteria.put(prod2.getID(), prod2);
    }


    public static void menuCliente() {
        System.out.println("\nMENÚ DE CLIENTES");
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
            System.out.println("Bienvenido, " + clienteActual.getNombre());
            iniciarVisita(clienteActual);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }



    public static void administrarTienda() {
        System.out.println("\nMENÚ PARA ADMINISTRAR LA TIENDA");
    }
}
