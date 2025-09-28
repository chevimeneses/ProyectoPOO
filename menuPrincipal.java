import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.util.Scanner;

public class menuPrincipal{

    private static Map<Integer, Libro> catalogoLibros = new HashMap<>();
    private static Map<Integer, ProductoCafeteria> menuCafeteria = new HashMap<>();
    private static Map<Integer, Cliente> listaClientes = new HashMap<>();
    private static List<Visita> visitas = new ArrayList<>();
    private static Set<String> clientesEmail = new HashSet<>();
    private static Set<String> clientesTele = new HashSet<>();

    public static void main(String[] args){
        Scanner escaner = new Scanner(System.in);
        int opcion;

        if(catalogoLibros.isEmpty() && listaClientes.isEmpty()){
            productosIniciales();
        }

        if(listaClientes.isEmpty()){
            clientesFieles();
        }

        administrarTienda admin = null;
        menuCliente accionesCliente = null;

        do{
            System.out.println("\nBienvenido a Café-Libreria 'La Ingeniería Indefinida'");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = escaner.nextInt();
            escaner.nextLine();

            switch(opcion){
                case 1:
                    accionesCliente = new menuCliente(catalogoLibros, menuCafeteria, listaClientes, visitas, clientesEmail, clientesTele);
                    accionesCliente.opcionesCliente();
                    break;
                case 2:
                    System.out.print("Ingrese la clave de empleado: ");
                    String clave = escaner.nextLine();

                    if(clave.equals("CafeFI")){
                        admin = new administrarTienda(catalogoLibros, menuCafeteria);
                        admin.menuAdmin();
                    } else {
                        System.out.println("Clave incorrecta.");
                    }
                    break;
                case 3:
                    System.out.println("Gracias por visitarnos <3");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while(opcion != 3);
    }

    private static void clientesFieles(){
        Cliente cliente1 = new Cliente("Juan Pérez", "juan@email.com", "5512345678");
        listaClientes.put(cliente1.getId(), cliente1);
        clientesEmail.add(cliente1.getEmail());
        clientesTele.add(cliente1.getTelefono());

        Cliente cliente2 = new Cliente("María García", "maria@email.com", "5698765432");
        listaClientes.put(cliente2.getId(), cliente2);
        clientesEmail.add(cliente2.getEmail());
        clientesTele.add(cliente2.getTelefono());
    }

    public static void productosIniciales(){
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela");
        catalogoLibros.put(libro1.getId(),libro1); 

        Libro libro2 = new Libro("1984", "George Orwell", "Ciencia Ficción");
        catalogoLibros.put(libro2.getId(),libro2);

        ProductoCafeteria prod1 = new ProductoCafeteria("Café Americano", 35.00, "Bebidas");
        menuCafeteria.put(prod1.getId(),prod1);

        ProductoCafeteria prod2 = new ProductoCafeteria("Panini de Pavo", 60.0, "Comida");
        menuCafeteria.put(prod2.getId(),prod2);
    }
}
