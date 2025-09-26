import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class administrarTienda{
	private Map<Integer, Libro> catalogoLibros = new HashMap<>();
	Map<Integer, ProductoCafeteria> menuCafeteria;
	Scanner sc = new Scanner(System.in);

	public void menuAdmin(){
		int opcEmpleado;
		do{
			System.out.println("1)Agregar Libro\n2)Retirar Libro\n3)Ver Libros"
				+"\n4)Cambiar información de libro");
			System.out.println("5)Agregar Productos de Cafetería\n6)Eliminar Producto"
				+ "\n7)Ver Menú de Cafetería\n8)Cambiar Producto");
			System.out.println("8)Salir");
			System.out.print("Escoja la acción a modificar: ");
			opc = sc.nextInt();

			switch(opcEmpleado){
				case 1 -> anidarLibro();
				case 2 -> retirarLibro();
				case 3 -> verLibros();
				case 4 -> editarLibro();
				case 5 -> anidarProducto();
				case 6 -> eliminarProducto();
				case 7 -> verProductos();
				case 8 -> System.out.println("Saliendo.");
				default -> System.out.println("Error...");
			}
		} while(opcEmpleado != 8);
	}

	public void anidarLibro(){
		System.out.print("Ingrese el nombre del libro: ");
		String nombreLibro = sc.nextLine();

		System.out.print("Ingrese el autor: ");
		String autorLibro = sc.nextLine();

		System.out.print("Ingrese el género del libro: ");
		String generoLibro = sc.nextLine();

		int precioLibro;
		do{
			System.out.print("Ingrese el precio del libro: ");
			precioLibro = sc.nextInt();

			if(precioLibro <= 0){
				System.out.println("Precio no válido. Inserte otro.");
			}
		} while(precioLibro <= 0);


		int idLibro;
		do{
			System.out.println("Ingrese el ID del libro: ");
			idLibro = sc.nextInt();
			if(catalogoLibros.containsKey(idLibro)){
				System.out.println("ID existente, ingrese otro.");
			}
		} while(catalogoLibros.containsKey(idLibro));

		Libro libroNuevo = new Libro(nombreLibro, autorLibro, generoLibro, precioLibro, true);

		catalogoLibros.put(idLibro, libroNuevo);
		System.out.println("Libro añadido correctamente.");
	}

	public void retirarLibro(){
		if(catalogoLibros.isEmpty()){
			System.out.println("La colección de libros se encuentra vacía.");
			return;
		}

		int idBorrar;
		do{
			System.out.print("Ingrese el ID del libro a retirar: ");
			idBorrar = sc.nextInt();
			if(catalogoLibros.containsKey()){
				System.out.println("Clave existente.");
			}
		} while(!catalogoLibros.containsKey());

		catalogoLibros.remove(idBorrar);
		System.out.println("Libro retirado exitosamente.");
	}

	public void verLibros(){
		if(catalogoLibros.isEmpty()){
			System.out.println("La colección de libros se encuentra vacía.");
			return;
		}

		System.out.println("Lista de Libros: ");
		for(Libro libro : catalogoLibros){
			System.out.println(libro.toString());
		}
	}

	public void editarLibro(){
		if(catalogoLibros.isEmpty()){
			System.out.println("La colección de libros se encuentra vacía.");
			return;
		}

		int idBuscar;
		do{
			System.out.print("Ingrese la clave del libro a editar: ");
			idBuscar = sc.nextInt();

			if(catalogoLibros.containsKey()){
				System.out.println("Libro existente.");
			}
		} while(!catalogoLibros.containsKey());

		Libro libroEditar = catalogoLibros.get(idBuscar);

		int opcEditar;
		do{
			System.out.println("1)Titulo\n2)Autor\n3)Genero"
				+"\n4)Precio\n5)Salir");
			System.out.print("¿Qué desea editar?: ");
			opcEditar = sc.nextInt;
			switch(opcEditar){
				case 1 -> {
					System.out.print("Ingrese el nuevo título: ");
					String nuevoNombre =  sc.nextLine();
					libroEditar.setTitulo(nuevoNombre);
				}
				case 2 -> {
					System.out.print("Ingrese el nuevo autor: ");
					String nuevoAutor =  sc.nextLine();
					libroEditar.setAutor(nuevoAutor);
				}
				case 3 -> {
					System.out.print("Ingrese el género: ");
					String nuevoGenero =  sc.nextLine();
					libroEditar.setGenero(nuevoGenero);
				}
				case 4 -> {
					System.out.println("Ingrese el nuevo precio: ");
					String nuevoPrecio =  sc.nextLine();
					libroEditar.setPrecio(nuevoPrecio);
				}
				case 5 -> System.out.println("Saliendo de la edición de libros...");
				default -> System.our.println("Error...");
			}
		} while(opcEditar != 5);

	}
}
