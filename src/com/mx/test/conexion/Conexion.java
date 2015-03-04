package com.mx.test.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	// creando el objeto de conexion "con"
	private static Connection con = null;
	
	public static Connection getConexion(){
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			// hago la conexion con mi base de datos, especificando la url con el nombre de mi base de datos
			//despues el usuario que es "postgres" y por ultimo mi clave "123123"
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud", "postgres", "123123");
			
			System.out.println("Conexion existosa con Hibernate");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de instanciamiento");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("No se pudo conectar a la Base de Datos");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No existe el archivo JAR");
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("Error desconocido al intentar conectarse a la Base de Datos " + e.getCause());
		}
		return con;
	}
	
	public static void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al intentar cerrar la conexion de la Base de Datos");
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("Error desconocido al intentar cerrar la conexion a la Base de Datos " + e.getCause());
		}
	}
}
