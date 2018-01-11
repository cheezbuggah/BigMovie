/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ducod_000
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class connector extends UI{
    public Connection connection = null;
    String textarea;
    public void connecting() throws FileNotFoundException {
                
		textarea = textarea + ("\n") + ("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			textarea = textarea + ("\n") + ("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		textarea = textarea + ("\n") + ("PostgreSQL JDBC Driver Registered!");

		

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/bigdata", "postgres",
					"kaasbaas123");
                                    
		} catch (SQLException e) {

			textarea = textarea + ("\n") + ("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			textarea = textarea + ("\n") + ("You made it, take control your database now!");
		} else {
			textarea = textarea + ("\n") + ("Failed to make connection!");
		}
                
        
	}
    public String testing(){
        return textarea;
    }
     
    public void ExecuteCommand(){
       
        
    }
                
               
}
