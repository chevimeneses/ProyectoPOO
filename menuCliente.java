import java.util.Map;
import java.util.Scanner;

public class menuCliente {

    private Map<Integer, Libro> catalogoLibros;
    private Map<Integer, ProductoCafeteria> menuCafeteria;
    private Scanner escaner;
    private Visita visitaActual;  // Nuevo atributo: referencia a la visita en curso

    // Constructor modificado: ahora recibe la visita activa además de los catálogos
    public menuCliente(Map<Integer, Libro> catalogoLibros, 
                      Map<Integer, ProductoCafeteria> menuCafeteria, 
                      Scanner escaner, 
                      Visita visitaActual) {
        this.catalogoLibros = catalogoLibros;
        this.menuCafeteria = menuCafeteria;
        this.escaner = escaner;
        this.visitaActual = visitaActual;  // Guardar la visita actual para usarla en todos los métodos
    }

    // Método sin cambios: muestra todos los libros disponibles
    public void mostrarLibros() {
        System.out.println("\nCatálogo de Libros");
        for (Libro libro : catalogoLibros.values()) {
            System.out.println(libro);
        }
    }

    // MÉTODO MODIFICADO: Ahora usa el sistema de visitas en lugar de cambiar estado directamente
    public void comprarLibro() {
        mostrarLibros();
        System.out.print("Ingrese el ID del libro a comprar: ");

        // Validación de entrada numérica
        if (!escaner.hasNextInt()) {
            System.out.println("ID inválido");
            escaner.nextLine();
            return;
        }

        int idLibro = escaner.nextInt();
        escaner.nextLine();

        // Verificar si el libro existe en el catálogo
        if (catalogoLibros.containsKey(idLibro)) {
            Libro libro = catalogoLibros.get(idLibro);
            if (libro.estaDisponible()) {
                // CAMBIO PRINCIPAL: Usar el sistema de visita en lugar de modificar estado manualmente
                visitaActual.agregarLibroComprado(libro);
                System.out.println("✓ Libro agregado a tu visita - Se procesará al finalizar");
            } else {
                System.out.println("El libro no está disponible");
            }
        } else {
            System.out.println("ID no encontrado");
        }
    }

    // Método sin cambios: muestra el menú de cafetería
    public void mostrarCafeteria() {
        System.out.println("\nMenú de Cafetería");
        for (ProductoCafeteria producto : menuCafeteria.values()) {
            System.out.println(producto);
        }
    }

    // MÉTODO MODIFICADO: Ahora agrega productos a la visita actual
    public void comprarProducto() {
        mostrarCafeteria();
        System.out.print("Ingrese el ID del producto a comprar: ");

        // Validación de entrada numérica
        if (!escaner.hasNextInt()) {
            System.out.println("ID inválido");
            escaner.nextLine();
            return;
        }

        int idProd = escaner.nextInt();
        escaner.nextLine();

        // Verificar si el producto existe en el menú
        if (menuCafeteria.containsKey(idProd)) {
            ProductoCafeteria prod = menuCafeteria.get(idProd);
            // CAMBIO PRINCIPAL: Agregar producto a la visita actual
            visitaActual.agregarProducto(prod);
            System.out.println("✓ Producto agregado a tu visita");
        } else {
            System.out.println("ID no encontrado");
        }
    }

    // NUEVO MÉTODO: Permite rentar libros usando el sistema de visitas
    public void rentarLibro() {
        mostrarLibros();
        System.out.print("Ingrese el ID del libro a rentar: ");

        // Validación de entrada numérica
        if (!escaner.hasNextInt()) {
            System.out.println("ID inválido");
            escaner.nextLine();
            return;
        }

        int idLibro = escaner.nextInt();
        escaner.nextLine();

        // Verificar si el libro existe y está disponible
        if (catalogoLibros.containsKey(idLibro)) {
            Libro libro = catalogoLibros.get(idLibro);
            if (libro.estaDisponible()) {
                // USAR EL SISTEMA DE RENTA DE LA VISITA
                visitaActual.agregarLibroRentado(libro);
                System.out.println("Libro rentado - Devolver en 7 días");
            } else {
                System.out.println("El libro no está disponible para renta");
            }
        } else {
            System.out.println("ID no encontrado");
        }
    }

    // NUEVO MÉTODO: Muestra un resumen de la visita actual con todos los detalles
    public void verResumenVisita() {
        System.out.println("\n--- RESUMEN DE TU VISITA ACTUAL ---");
        System.out.println("Cliente: " + visitaActual.getCliente().getNombre());
        System.out.println("Fecha: " + visitaActual.getFecha());
        System.out.println("Libros comprados: " + visitaActual.getCantidadLibrosComprados());
        System.out.println("Libros rentados: " + visitaActual.getCantidadLibrosRentados());
        System.out.println("Productos consumidos: " + visitaActual.getCantidadProductosConsumidos());
        System.out.println("Total parcial: $" + visitaActual.getTotal());
        
        // Mostrar detalles específicos si hay items en la visita
        if (visitaActual.getCantidadLibrosComprados() > 0 || 
            visitaActual.getCantidadLibrosRentados() > 0 || 
            visitaActual.getCantidadProductosConsumidos() > 0) {
            System.out.println("\n--- Detalles de tu visita ---");
            visitaActual.mostrarDetalles();  // Usar el método de la clase Visita
        } else {
            System.out.println("Aún no has agregado items a tu visita.");
        }
    }
}