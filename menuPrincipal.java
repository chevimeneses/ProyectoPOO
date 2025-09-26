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
            System.out.println("1. Soy cliente");
            System.out.println("2. Soy empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            if (!escaner.hasNextInt()) {
            System.out.println("Ingrese un número válido");
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
                    menuEmpleado();
                    break;
                case 3:
                    System.out.println("\nVuelva pronto");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }


    public static void InventarioInicial() {

        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela");
        catalogoLibros.put(libro1.getId(), libro1);
        
        System.out.println("Inventario de la tienda inicializado");
    }


    public static void menuCliente() {
        System.out.println("\nMENÚ DE CLIENTES");
        System.out.print("Por favor, ingrese su ID de cliente (o escriba '0' para registrarse): ");

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
            System.out.println("Bienvenido de nuevo, " + clienteActual.getNombre());
            iniciarVisita(clienteActual);
        } else {
            System.out.println("Cliente no encontrado. Haz un nuevo registro");
        }
    }



    public static void administrarTienda() {
        System.out.println("\nMENÚ DE EMPLEADOS");
    }
}
