/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1examenJavaGeorge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//deberia haber comentado a la par que hacia el ejercicio
public class Main {
    // Lista estática para almacenar todas las figuras coleccionables
    private static List<Figura> figuras = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;//para el bucle del programa

        while (run) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Aniadir figura");
            System.out.println("2. Aniadir nuevo accesorio a una figura de accion");
            System.out.println("3. Mostrar listado de figuras");
            System.out.println("4. Mostrar Funko Pops de edición limitada");
            System.out.println("5. Salir");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    añadirFigura(scanner);
                    break;
                case 2:
                    añadirAccesorio(scanner);
                    break;
                case 3:
                    mostrarListado();
                    break;
                case 4:
                    mostrarFunkoPopsEdicionLimitada();
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void añadirFigura(Scanner scanner) {
        System.out.println("Seleccione el tipo de figura:");
        System.out.println("1. Figura de Acción");
        System.out.println("2. Estatua");
        System.out.println("3. Funko Pop");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Solicita los datos para FIGURAS
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Anio de lanzamiento: ");
        int año = scanner.nextInt();
        System.out.print("Precio base: ");
        double precioBase = scanner.nextDouble();
        scanner.nextLine(); // crea nueva linea
        System.out.print("Estado (precintada/en su embalaje original/sin embalaje original): ");
        String estado = scanner.nextLine();

        switch (tipo) {
            case 1:
                //aniade figura de accion
                figuras.add(new FiguraAccion(nombre, año, precioBase, estado));
                break;
            case 2:
                System.out.print("Franquicia: ");
                String franquicia = scanner.nextLine();
                figuras.add(new Estatua(nombre, año, franquicia, precioBase, estado));//aniade una estatua
                break;
            case 3:
                System.out.print("¿Es edición limitada? (true/false): ");
                boolean esEdicionLimitada = scanner.nextBoolean();
                figuras.add(new FunkoPop(nombre, año, esEdicionLimitada, precioBase, estado));//aniade un funko
                break;
            default:
                System.out.println("Tipo de figura no válido.");
        }
    }

    private static void añadirAccesorio(Scanner scanner) {
        System.out.print("Nombre de la figura de acción: ");
        String nombreFigura = scanner.nextLine();
        FiguraAccion figura = null;

        //este coso de aqui busca una figura de accion en la lista de figuras
        for (Figura f : figuras) {  //figura f : figuras | Figura f es la variable que se usa para referisrse al elemento actual en cada iteracion
                                    // figuras es la coleccion sobre la que se esta iterando, el ARRAYLIST, por cada
            if (f instanceof FiguraAccion && f.getNombre().equalsIgnoreCase(nombreFigura)) { //esto comprueba si el objeto f es una instancia de la clase FiguraAccion e ignora si el nombre esta en mayuscula o no
                figura = (FiguraAccion) f;      //si ambas condiciones en la linea anterior se cumplen se reliza una conversion del objeto
                break;                          //la variable figura se asigna al objeto 'f' convertido a FiguraAccion, esto permite poder usar los metodos y propiedades especificos de FiguraAccion
            }
        }
        // Si se encuentra la figura, se piden los datos del accesorio y se añade
        if (figura != null) {   //figura != null quiere decir que mostrara lo siguiente siempre y cuando no este vacio, es decir
                                //tiene que haber una ifugra para pdoer aniadir un accesorio
            System.out.print("Nombre del accesorio: ");
            String nombreAccesorio = scanner.nextLine();
            System.out.print("Material del accesorio: ");
            String material = scanner.nextLine();
            System.out.print("Descripción del accesorio: ");
            String descripcion = scanner.nextLine();
            figura.aniadirAccesorio(new Accesorio(nombreAccesorio, material, descripcion));
        } else {
            System.out.println("Figura de acción no encontrada.");//mensaje por si no se encuentra la figura
        }
    }

    //metodo para mostrar todas las figuras
    private static void mostrarListado() {
        for (Figura figura : figuras) {
            System.out.println("Nombre: " + figura.getNombre() + ", Precio Base: " + figura.getPrecioBase() + ", Precio Real: " + figura.calcularPrecioReal());
            // lo de los instanceof es para comprobar si un objeto es una unstancia de una clase o sublase
        }
    }

    //metodo para mostrar los pops de edicion limitada
    private static void mostrarFunkoPopsEdicionLimitada() {
        for (Figura figura : figuras) {
            if (figura instanceof FunkoPop && ((FunkoPop) figura).isEsEdicionLimitada()) {
                System.out.println("Nombre: " + figura.getNombre());
            }
        }
    }
}