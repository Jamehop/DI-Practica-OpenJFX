package App;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import errores.*;
import Model.*;

public class Bbdd {
	public static Connection conexCreate() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria?serverTimezone=UTC", "root", "admin");
		System.out.println("No he tirado excepcion");
		return conn;
	}

	public static List<Cliente> allClientes(Connection c) {
		Statement stm = null;
		ResultSet rs = null;

		try {
			stm = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
		if (stm != null) {
			try {
				rs = stm.executeQuery("SELECT * FROM jardineria.cliente");
				while (rs.next()) {
					System.out.println(rs.getString("nombre_cliente"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void login(Connection c, String username, String password) throws ClienteNoExiste {
		Statement stm = null;
		ResultSet rs = null;
		boolean encontrado = false;
		try {
			stm = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
		if (stm != null) {
			try {
				rs = stm.executeQuery("SELECT * FROM jardineria.cliente");
				while (rs.next()) {

					if (rs.getString("nombre_cliente").equals(username)
							&& rs.getString("username").equals(username)) {
						encontrado = true;
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (!encontrado) {
			username = null;
			password = null;
			throw new ClienteNoExiste();
		}
	}

}
