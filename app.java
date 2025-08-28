import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; // Es una buena práctica manejar SQLException específicamente
import java.util.Scanner;

public class app {

    static String url = "jdbc:mysql://localhost:3306/universidad";
    static String userName = "javadev";
    static String password = "";

    public static void main(String[] args) {
    
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            
            
            estudianteservice estudianteService = new estudianteservice();
            
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                // Mostramos el menú de opciones al usuario
                System.out.println("\n===== MENÚ ESTUDIANTES =====");
                System.out.println("1. Contar cuántos estudiantes hay");
                System.out.println("2. Insertar estudiante (con datos de prueba)");
                System.out.println("3. Actualizar estudiante (con datos de prueba)");
                System.out.println("4. Eliminar estudiante (con datos de prueba)");
                System.out.println("5. Insertar estudiante (pidiendo datos)");
                System.out.println("6. Actualizar estudiante (pidiendo datos)");
                System.out.println("7. Eliminar estudiante (pidiendo datos)");
                System.out.println("8. Mostrar lista de estudiantes");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiamos el buffer para evitar problemas con futuras lecturas

                // Usamos if-else if para manejar la opción del usuario
                if (opcion == 1) {
                    estudianteService.obtenerNumeroEstudiantes(conn);
                } else if (opcion == 2) {
                    estudianteService.insertarEstudiante(conn);
                } else if (opcion == 3) {
                    estudianteService.actualizarEstudiante(conn);
                } else if (opcion == 4) {
                    estudianteService.eliminarEstudiante(conn);
                } else if (opcion == 5) {
                    estudianteService.insertarEstudianteConValores(conn);
                } else if (opcion == 6) {
                    estudianteService.actualizarEstudianteConValores(conn);
                } else if (opcion == 7) {
                    estudianteService.eliminarEstudianteConValores(conn);
                } else if (opcion == 8) {
                    estudianteService.obtenerEstudiantesConValores(conn);
                } else if (opcion == 0) {
                    System.out.println(" Saliendo del programa...");
                } else {
                    System.out.println("Opcion no válida. Intente de nuevo.");
                }

            } while (opcion != 0);

            sc.close(); // Cerramos el scanner al final para liberar recursos
        
        // Capturamos específicamente errores de SQL para dar un mensaje más claro
        } catch (SQLException e) {
            System.out.println("¡Error de base de datos!");
            System.out.println("Mensaje: " + e.getMessage());
        // Capturamos cualquier otro error que pueda ocurrir
        } catch (Exception e) {
            System.out.println("¡Ha ocurrido un error inesperado!");
            System.out.println("Mensaje: " + e.getMessage());
        }
    }
}