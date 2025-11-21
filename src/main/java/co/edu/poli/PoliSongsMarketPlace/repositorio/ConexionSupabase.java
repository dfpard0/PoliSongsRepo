/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSupabase {
	 private static final String URL = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres";//"jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres";
	    private static final String USER = "postgres.decmxzaclanqhfrdfpte";
	    private static final String PASSWORD = "tIjTHqvJ9iLABm7w";

	    public static Connection getConnection() {
	        Connection conn = null;
	        try {
	            // Cargar driver PostgreSQL
	            Class.forName("org.postgresql.Driver");

	            // Conexión a Supabase
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("✅ Conexión exitosa a Supabase");
	        } catch (ClassNotFoundException | SQLException e) {
	            System.out.println("❌ Error de conexión: " + e.getMessage());
	        }
	        return conn;
	    }

}
