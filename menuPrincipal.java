import java.util.*;

public class menuPrincipal {

    private static Map<Integer, Libro> catalogoLibros = new HashMap<>();
    private static Map<Integer, Cliente> listaClientes = new HashMap<>();
    private static Map<Integer, ProductoCafeteria> menuCafeteria = new HashMap<>();

    private static Set<Integer> idRegistrados = new TreeSet<>();

    private static List<Visita> Visitas = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        while (true) {
            System.out.println("BIENVENIDO");
            System.out.println("1. Soy cliente");
            System.out.println("2. Soy empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido");
            scanner.nextLine();
            continue;
        }
        
        int opcion = scanner.nextInt();
        scanner.nextLine();

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

    public static void menuCliente() {
        System.out.println("\n[MENÚ DE CLIENTE]");
    }

    public static void menuEmpleado() {
        System.out.println("\n[MENÚ DE EMPLEADO]");
    }
}
