package app.stock;

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

	// Process the resultSet
	public ResultSet GetCombobox() {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom_category FROM category ");
			return rs;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
// remove produit bd
	public int SupprimerStock(String nom) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int x =st.executeUpdate("delete from stock where nom_produit='"+nom+"' ");
			return x ;
			
		}catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
		return 0;
		}
	}
// pin up in table bd
	public ResultSet Gettable(String  category_produit ) {
		try {
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM stock where category_produit='"+category_produit+"'");
			return rs;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
// add produit  bd
	public int setproduct(String nom_produit , int quantite_produit , String category_produit,int prix_unite) {
		try {
			Connection cn = conn();
			Statement stf = (Statement) cn.createStatement();
			int x = stf.executeUpdate("INSERT INTO `stock`( `nom_produit`, `quantite_produit`, `category_produit`, `prix_unite`) VALUES ('"+nom_produit+"',"+quantite_produit+",'"+category_produit+"',"+prix_unite+");");
			return x;
		} catch (Exception ex){JOptionPane.showMessageDialog(null, "Error occured while executing query !");
			return 0;
		}
	}
// edit produit
	public int EditP(String nom, int QTT) {
		try {
			
			Connection cn = conn();
			Statement st = (Statement) cn.createStatement();
			int x =st.executeUpdate("UPDATE `stock` SET `quantite_produit`="+QTT+" WHERE `nom_produit`='"+nom+"' ");
			return x ;
			
		}catch(Exception ex){JOptionPane.showMessageDialog(null,"ERROR INFOS");
		return 0;
		}
	}
	
	
	
	
}
