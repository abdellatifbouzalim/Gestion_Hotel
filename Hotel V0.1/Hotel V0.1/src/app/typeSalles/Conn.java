package app.typeSalles;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conn {
	// Get Connection to database
	public Connection conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Cn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/gestion_hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			return Cn;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null; }
	}
	
	// get table content
	public ResultSet getTableContent() {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `type_salle`");
			return rs;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
	public int setType(String name, int prix, int salles , int tables) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int x = st.executeUpdate("INSERT INTO `type_salle`(`nom_typeS`, `prix_typeS`, `nbr_salles`, `nbr_tables`) VALUES ('"+name+"',"+prix+","+salles+","+tables+")");
			return x;
		} catch (Exception ex){JOptionPane.showMessageDialog(null, "Error occured while executing query !");
			return 0;
		}
	}
	
	// DELETE SALLE
	public int removeTypeSalle(int num) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int x = st.executeUpdate("DELETE FROM `type_salle` WHERE `id_typeS`= "+num+";");
			return x;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, "Error occured while executing query !");
			return 0;
		}
	}
	
}
