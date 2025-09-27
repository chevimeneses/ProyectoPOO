import java.util.Map;
import java.util.Scanner;


public class menuCliente {

    private Map<Integer, Libro> catalogoLibros;
    private Map<Integer, ProductoCafeteria> menuCafeteria;
    private Scanner escaner;

    public menuCliente(Map<Integer, Libro> catalogoLibros, Map<Integer, ProductoCafeteria> menuCafeteria, Scanner escaner) {
        this.catalogoLibros = catalogoLibros;
        this.menuCafeteria = menuCafeteria;
        this.escaner = escaner;
    }

    public void mostrarLibros() {
        System.out.println("\nCatálogo de Libros");
        for (Libro libro : catalogoLibros.values()) {
            System.out.println(libro);
        }
    }

    public void comprarLibro(Cliente cliente) {
        mostrarLibros();
        System.out.print("Ingrese el ID del libro a comprar: ");

        if (!escaner.hasNextInt()) {
            System.out.println("ID inválido");
            escaner.nextLine();
            return;
        }

        int idLibro = escaner.nextInt();
        escaner.nextLine();

        if (catalogoLibros.containsKey(idLibro)) {
            Libro libro = catalogoLibros.get(idLibro);
            if (libro.estaDisponible()) {
                libro.setEstado(false);
                System.out.println("Compra realizada: ");
            } else {
                System.out.println("El libro no está disponible");
            }
        } else {
            System.out.println("ID no encontrado");
        }
    }

    public void mostrarCafeteria() {
        System.out.println("\nMenú de Cafetería");
        for (ProductoCafeteria producto : menuCafeteria.values()) {
            System.out.println(producto);
        }
    }

    public void comprarProducto(Cliente cliente) {
        mostrarCafeteria();
        System.out.print("Ingrese el ID del producto a comprar: ");

        if (!escaner.hasNextInt()) {
            System.out.println("ID inválido");
            escaner.nextLine();
            return;
        }

        int idProd = escaner.nextInt();
        escaner.nextLine();

        if (menuCafeteria.containsKey(idProd)) {
            ProductoCafeteria prod = menuCafeteria.get(idProd);
            System.out.println("Compra realizada: " + prod.getProducto());
        } else {
            System.out.println("ID no encontrado");
        }
    }
}

