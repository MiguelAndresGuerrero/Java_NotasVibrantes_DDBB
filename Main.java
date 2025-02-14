import java.sql.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/notasVibrantes";
        String usuario = "root";
        String contraseña = "0098";
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            Statement stmt = conexion.createStatement();
            Scanner sc = new Scanner(System.in);
            boolean boleanito = true;
            while (boleanito) {
                int eleccion = menuPrincipal(sc);
                
                switch (eleccion) {
                    case 1:
                        ConsultarTickets(stmt);
                        break;
                    case 2:
                        ConsultarClientes(stmt);
                        break;
                    case 3:
                        EliminarClientes(stmt, sc);
                        break;
                    case 4:
                        AgregarClientes(stmt, sc);
                        break;
                    case 5:
                        ModificarCliente(stmt, sc);
                        break;
                    case 6:
                        ConsultarConciertos(stmt);
                        break;
                    case 7:
                        AgregarTicket(stmt, sc);
                        break;
                    case 8:
                        ModificarTicket(stmt, sc);
                        break;
                    case 9:
                        EliminarTicket(stmt, sc);
                        break;
                    case 10:
                        AgregarConcierto(stmt, sc);
                        break;
                    case 11:
                        EliminarConcierto(stmt, sc);
                        break;
                    case 12:
                        System.out.println("Saliendo del programa, Vuelve pronto");
                        boleanito = false;
                        break;
                    default:
                        System.out.println("Opción invalida");
                }
            }
            stmt.close();
            conexion.close();
            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public static int menuPrincipal(Scanner sc) {
        System.out.println("Menu Principal");
        System.out.println("1. Ver clientes con tickets");
        System.out.println("2. Ver lista de clientes");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Agregar cliente");
        System.out.println("5. Modificar cliente");
        System.out.println("6. Ver conciertos disponibles");
        System.out.println("7. Comprar ticket");
        System.out.println("8. Modificar ticket");
        System.out.println("9. Eliminar ticket");
        System.out.println("10. Agregar concierto");
        System.out.println("11. Eliminar concierto");
        System.out.println("12. Salir del programa");
        System.out.print("Elige una opcion: ");
        return sc.nextInt();
    };
    
    public static void ConsultarTickets(Statement stmt) throws SQLException {
        String consulta = """
            SELECT c.ID, c.Nombre, c.Apellido, COUNT(t.ID) as cantidadTickets
            FROM Clientes c
            LEFT JOIN Ticket t ON c.ID = t.ID_Cliente
            GROUP BY c.ID, c.Nombre, c.Apellido;
        """;
        ResultSet rs = stmt.executeQuery(consulta);
        System.out.println("Clientes con tickets: ");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " - " + rs.getString("Nombre") + " " + rs.getString("Apellido") +
                    " - Tickets: " + rs.getInt("cantidadTickets"));
        } rs.close();
    };
    public static void ConsultarClientes(Statement stmt) throws SQLException {
        String consulta = "SELECT * FROM Clientes";
        ResultSet rs = stmt.executeQuery(consulta);
        System.out.println("Lista de clientes: ");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " - " + rs.getString("Nombre") + " " +
                    rs.getString("Apellido") + " - " + rs.getString("Correo") + " - Tel: " + rs.getInt("Telefono"));
        } rs.close();
    };
    public static void EliminarClientes(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = sc.nextInt();
        String consulta = "DELETE FROM Clientes WHERE ID=" + id;
        int filasAfectadas = stmt.executeUpdate(consulta);
        if (filasAfectadas > 0) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("No se encontro el cliente");
        }
    };
    public static void AgregarClientes(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingrese el ID del nuevo cliente: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Telefono: ");
        int telefono = sc.nextInt();
        String consulta = "INSERT INTO Clientes (ID, Nombre, Apellido, Correo, Telefono) VALUES (" +
                        id + ", '" + nombre + "', '" + apellido + "', '" + correo + "', " + telefono + ")";
        stmt.executeUpdate(consulta);
        System.out.println("Cliente agregado correctamente");
    };
    public static void ModificarCliente(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingrese el ID del cliente a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Nuevo correo: ");
        String correo = sc.nextLine();
        System.out.print("Nuevo telefono: ");
        int telefono = sc.nextInt();
        String consulta = "UPDATE Clientes SET Nombre='" + nombre + "', Apellido='" + apellido + "', " +
                        "Correo='" + correo + "', Telefono=" + telefono + " WHERE ID=" + id;
        int filasAfectadas = stmt.executeUpdate(consulta);
        if (filasAfectadas > 0) {
            System.out.println("Cliente actualizado correctamente");
        } else {
            System.out.println("No se encontro el cliente");
        }
    };
    public static void ConsultarConciertos(Statement stmt) throws SQLException {
        String consulta = "SELECT * FROM Concierto";
        ResultSet rs = stmt.executeQuery(consulta);
        System.out.println("Lista de conciertos: ");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " - " + rs.getString("Nombre") + " - " +
                    rs.getString("Artista") + " - Fecha: " + rs.getDate("Fecha") + 
                    " - Lugar: " + rs.getString("Lugar") + " - Precio: " + rs.getInt("PrecioBase"));
        } rs.close();
    };
    public static void AgregarTicket(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingrese el ID del ticket: ");
        int id = sc.nextInt();
        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = sc.nextInt();
        System.out.print("Ingrese el ID del concierto: ");
        int idConcierto = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la zona: ");
        String zona = sc.nextLine();
        System.out.print("Ingrese el precio final: ");
        int precioFinal = sc.nextInt();
        System.out.print("Ingrese la fecha de compra (YYYY-MM-DD): ");
        sc.nextLine();
        String fechaCompra = sc.nextLine();
        String consulta = "INSERT INTO Ticket (ID, ID_Cliente, ID_Concierto, Zona, PrecioFinal, FechaCompra) VALUES (" +
                        id + ", " + idCliente + ", " + idConcierto + ", '" + zona + "', " + precioFinal + ", '" + fechaCompra + "')";
        stmt.executeUpdate(consulta);
        System.out.println("Ticket comprado correctamente");
    };
    public static void ModificarTicket(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingresa el ID del ticket a modificar: ");
        int idTicket = sc.nextInt();
        sc.nextLine();
        System.out.print("Nueva zona: ");
        String nuevaZona = sc.nextLine();
        System.out.print("Nuevo precio final: ");
        int nuevoPrecio = sc.nextInt();
        String consulta = "UPDATE Ticket SET Zona='" + nuevaZona + "', PrecioFinal=" + nuevoPrecio + " WHERE ID=" + idTicket;
        int filasAfectadas = stmt.executeUpdate(consulta);
        if (filasAfectadas > 0) {
            System.out.println("Ticket actualizado correctamente");
        } else {
            System.out.println("No se encontro el ticket");
        }
    };
    public static void EliminarTicket(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingresa el ID del ticket a eliminar: ");
        int idTicket = sc.nextInt();
        String consulta = "DELETE FROM Ticket WHERE ID=" + idTicket;
        int filasAfectadas = stmt.executeUpdate(consulta);
        if (filasAfectadas > 0) {
            System.out.println("Ticket eliminado correctamente");
        } else {
            System.out.println("No se encontro el ticket");
        }
    };
    public static void AgregarConcierto(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingresa el ID del concierto: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre del concierto: ");
        String nombre = sc.nextLine();
        System.out.print("Artista: ");
        String artista = sc.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Lugar: ");
        String lugar = sc.nextLine();
        System.out.print("Precio base: ");
        int precio = sc.nextInt();
        String consulta = "INSERT INTO Concierto (ID, Nombre, Artista, Fecha, Lugar, PrecioBase) VALUES (" +
                          id + ", '" + nombre + "', '" + artista + "', '" + fecha + "', '" + lugar + "', " + precio + ")";
        stmt.executeUpdate(consulta);
        System.out.println("Concierto agregado correctamente");
    };
    public static void EliminarConcierto(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Ingresa el ID del concierto a eliminar: ");
        int id = sc.nextInt();
        String consulta = "DELETE FROM Concierto WHERE ID=" + id;
        int filasAfectadas = stmt.executeUpdate(consulta);
        if (filasAfectadas > 0) {
            System.out.println("Concierto eliminado correctamente");
        } else {
            System.out.println("No se encontro el concierto");
        }
    };
};