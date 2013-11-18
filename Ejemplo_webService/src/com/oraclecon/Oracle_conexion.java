package com.oraclecon;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebService()
public class Oracle_conexion {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";            
	private static final String database = "jdbc:oracle:thin:@//localhost:1521/SID";
	private static final String usuario = "usuario";
	private static final String password = "password";
	private static String resultado; 
	
	 /**
	  * Web service operation
	  */
	
	@WebMethod(operationName = "obtieneDepto")
	public static String obtieneDepto(@WebParam(name = "empleado")
	String empleado) throws SQLException {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
	
		String query = "SELECT DEPARTAMENTO_ID FROM DEPARTAMENTO WHERE EMPLEADO ='" + empleado + "'";
	 
		try {
			conn = conexionbd();
			preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			resultado = rs.getString(1);
						
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		} finally {
 
			if (preparedStatement != null) 
				preparedStatement.close();
 
			if (conn != null) 
				conn.close();
 		}
		return resultado;
 	}
 
	private static Connection conexionbd() {
 
		Connection conexionbd = null;
 		try {
 			Class.forName(driver);
 		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
 
		try {
 			conexionbd = DriverManager.getConnection(database, usuario, password);
			return conexionbd;
 		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
 		return conexionbd;
 	}
}

