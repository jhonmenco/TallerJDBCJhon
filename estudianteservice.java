import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class estudianteservice {

    public void obtenerNumeroEstudiantes(Connection conn) throws SQLException {
        System.out.println("Buscando estudiantes...");
        String miConsultaSQL = "SELECT * FROM universidad.estudiantes";

        Statement declaracion = conn.createStatement();
        ResultSet resultado = declaracion.executeQuery(miConsultaSQL);

        int contadorDeEstudiantes = 0;
        System.out.println("Contando los registros encontrados:");
        while (resultado.next()) {
            contadorDeEstudiantes = contadorDeEstudiantes + 1;
            System.out.print("Estudiante Verificado");
        }
        System.out.println("\n En total hay " + contadorDeEstudiantes + " estudiantes registrados.");
    }

    public void insertarEstudiante(Connection conn) throws SQLException {
        String nombre = "Juanito";
        String apellido = "Perez";
        String correo = "juanito.perez@example.com";
        int edad = 21;
        String estadoCivil = "soltero";

        String sql = "INSERT INTO universidad.estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES ('"
                + nombre + "', '" + apellido + "', '" + correo + "', " + edad + ", '" + estadoCivil + "')";

        System.out.println("Preparando para insertar el siguiente registro: " + sql);

        Statement declaracion = conn.createStatement();
        int filasAfectadas = declaracion.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println(" El estudiante fue guardado correctamente.");
        } else {
            System.out.println("No se pudo guardar el estudiante.");
        }
    }

    public void actualizarEstudiante(Connection conn) throws SQLException {
        String nuevoEstadoCivil = "casado";
        int idDelEstudiante = 2;

        String sql = "UPDATE universidad.estudiantes SET estado_civil = '" + nuevoEstadoCivil + "' WHERE id = " + idDelEstudiante;
        System.out.println("Se ejecutará la actualización: " + sql);

        Statement declaracion = conn.createStatement();
        int filasAfectadas = declaracion.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println(" Se actualizó la información del estudiante con ID " + idDelEstudiante);
        } else {
            System.out.println("No se encontró al estudiante o no se pudo actualizar.");
        }
    }

    public void eliminarEstudiante(Connection conn) throws SQLException {
        int idParaBorrar = 2;
        String sql = "DELETE FROM universidad.estudiantes WHERE id = " + idParaBorrar;

        System.out.println("CUIDADO: Se intentará borrar el estudiante con ID " + idParaBorrar);

        Statement declaracion = conn.createStatement();
        int filasAfectadas = declaracion.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("El estudiante fue eliminado para siempre.");
        } else {
            System.out.println("No se pudo eliminar.");
        }
    }

    public void insertarEstudianteConValores(Connection conn) throws SQLException {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese los siguientes datos.");
        System.out.print("Escribe el nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Escribe el apellido: ");
        String apellido = teclado.nextLine();
        System.out.print("Escribe el correo: ");
        String correo = teclado.nextLine();
        System.out.print("Escribe la edad: ");
        int edad = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Escribe el estado civil: ");
        String estadoCivil = teclado.nextLine();

        String sql = "INSERT INTO universidad.estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES ('"
                + nombre + "', '" + apellido + "', '" + correo + "', " + edad + ", '" + estadoCivil + "')";

        System.out.println("Enviando datos a la base de datos...");
        Statement declaracion = conn.createStatement();
        int filasAfectadas = declaracion.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("¡Muy bien! Estudiante registrado.");
        } else {
            System.out.println("Hubo un problema al registrar.");
        }
    }

    public void actualizarEstudianteConValores(Connection conn) throws SQLException {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Dime el ID del estudiante que quieres modificar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Ahora dime el nuevo estado civil: ");
        String estadoCivil = teclado.nextLine();

        String sql = "UPDATE universidad.estudiantes SET estado_civil = '" + estadoCivil + "' WHERE id = " + id;

        System.out.println("Intentando actualizar...");
        Statement declaracion = conn.createStatement();
        int filasAfectadas = declaracion.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("¡Actualización completada!");
        } else {
            System.out.println("No se pudo actualizar. Revisa si el ID es correcto.");
        }
    }

    public void eliminarEstudianteConValores(Connection conn) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Escribe el ID del estudiante que quieres eliminar: ");
        int id = teclado.nextInt();

        String sql = "DELETE FROM universidad.estudiantes WHERE id = " + id;
        System.out.println("Procediendo a eliminar...");

        Statement declaracion = conn.createStatement();
        int filasAfectadas = declaracion.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("Registro eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar, el ID no debe existir.");
        }
    }

    public void obtenerEstudiantesConValores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM universidad.estudiantes";
        Statement declaracion = conn.createStatement();
        ResultSet resultado = declaracion.executeQuery(sql);

        System.out.println("\n--- LISTA DE ESTUDIANTES REGISTRADOS ---");
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String apellido = resultado.getString("apellido");
            String correo = resultado.getString("correo");
            int edad = resultado.getInt("edad");
            String estadoCivil = resultado.getString("estado_civil");

            System.out.println("ID: " + id);
            System.out.println("  -> Nombre: " + nombre);
            System.out.println("  -> Apellido: " + apellido);
            System.out.println("  -> Correo: " + correo);
            System.out.println("  -> Edad: " + edad);
            System.out.println("  -> Estado Civil: " + estadoCivil);
            System.out.println("------------------------------------------");
        }
        System.out.println("Fin de la lista.");
    }
}
