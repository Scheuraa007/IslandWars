package de.Scheuraa.IslandWars.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class API_MySQL {

	public static String HOST;
	public static String PORT;
	public static String DATABASE ;
	public static String USER;
	public static String PASSWORD;

	public static Connection con;
	
	public static void connect(){
		if(!isConnected()){
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE,USER,PASSWORD);
				System.out.println("[MySQL] Verbindung aufgebaut!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void disconnect(){
		if(isConnected()){
			try {
				con.close();
				System.out.println("[MySQL] Verbindung geschlossen!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void update(String qry) {

		try {		
			Statement st = con.createStatement();
			st.executeUpdate(qry);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isConnected(){
		return(con == null ? false : true);
		
	}
	public static Connection getConnection(){
		return con;
	}
}
