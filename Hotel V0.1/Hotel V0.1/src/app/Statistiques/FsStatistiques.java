package app.Statistiques;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class FsStatistiques {
	
	
	//Connection
	public Connection ConnFsStatistiques() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Cn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/gestion_hotel?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			return Cn;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null; }
	}
	
	
	//
	
	

}
