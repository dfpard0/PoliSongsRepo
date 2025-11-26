package co.edu.poli.PoliSongsMarketPlace.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSupabase2 {

    private static final String URL = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.thkbguhczorjccgnsqrc";
    private static final String PASSWORD = "UNuj2OhSdHjbEvaQ";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error conectando a Supabase: " + e.getMessage());
            return null;
        }
    }
}