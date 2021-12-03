package app.salles;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conn {
	// Get Connection to database
	public Connection conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Cn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/gestion_hotel?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			return Cn;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null; }
	}
	
	// Get Table
	public ResultSet infoSalles(String typeSalle) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM salle WHERE nom_typeS = '"+typeSalle+"'");
			return rs;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
	// Get SALLES Types !
	public ResultSet getTypesSalles() {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT `nom_typeS` FROM `type_salle`");
			return rs;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
	// ADD SALLE
	public int setNewSalle(int numSalle, int etage, String typeSalle) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int x = st.executeUpdate("INSERT INTO `salle`(`num_salle`, `etage_salle`, `nom_typeS`) VALUES ("+numSalle+","+etage+",'"+typeSalle+"');");
			return x;
		} catch (Exception ex){JOptionPane.showMessageDialog(null, "Error occured while executing query !");
			return 0;
		}
	}
	
	// DELETE SALLE
	public int removeSalle(int numSalle) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int x = st.executeUpdate("DELETE FROM `salle` WHERE `num_salle`= "+numSalle+";");
			return x;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, "Error occured while executing query !");
			return 0;
		}
	}
			
	// UPDATE SALLE
	public int setNewSalleData(String lname, String fname, String cin, int numSalle ,String dateDebut , String dateFin ) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int a = st.executeUpdate("INSERT INTO `client`(`nom_client`, `prenom_client`, `cin`) VALUES ('"+lname+"','"+fname+"','"+cin+"');");
			int b = st.executeUpdate("UPDATE `salle` SET `cin`= '"+cin+"',`dateS_debut`='"+dateDebut+"' ,`dateS_fin`= '"+dateFin+"'  WHERE `num_salle`= "+numSalle+";");
			if (a==1 && b==1) {
				return 1;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error Occured ! Something's wrong !");
			}
			return 1;
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error occured while executing query !");
			return 0;
		}
	}
	// UPDATE SALLE
	public int makeEmpty(int numSalle) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int a = st.executeUpdate("UPDATE `salle` SET `cin`= null,`dateS_debut`=null ,`dateS_fin`= null WHERE `num_salle`= "+numSalle+";");
			if (a==1) return 1;
			else JOptionPane.showMessageDialog(null, "Error Occured ! Something's wrong !");
			return 1;
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error occured while executing query !");
				return 0;
			}
		}
	
}
