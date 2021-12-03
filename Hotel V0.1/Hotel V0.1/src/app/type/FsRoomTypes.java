package app.type;

import java.awt.Color;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

//import com.toedter.calendar.JDateChooser;

import app.fonts.Fonts;
import app.includes.Conn;

public class FsRoomTypes {
	
	Fonts f=new Fonts();
	
	public static Connection ConnFsRoomTypes() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Cn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/gestion_hotel?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			  return Cn;  
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage());
		  return null;
		}	
	}
	
	// select ROOM TYPES
	  public ResultSet getRoomTypes() {
		  try {
				Connection cn = ConnFsRoomTypes();
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT `nom_type` FROM type_chambre");
				return rs;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
				return null;
			}
		}
	  
	  //select Infos ROOM TYPES
	  public ResultSet infoRoomTypes() {
			try {
				Connection cn = ConnFsRoomTypes();
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM type_chambre ORDER by `prix_type` ");
				return rs;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
				return null;
			}
		}
	  
	  //Add ROOM TYPES
	  public int AddNewRoomTypes(String string1 , int string2, int string3 , int string4 , int string5 , String string6 , String string7) {
			try {
				Connection cn = ConnFsRoomTypes();
				Statement st = (Statement) cn.createStatement();
				int x = st.executeUpdate("INSERT INTO `type_chambre`(`nom_type`, `prix_type`, `nbr_chambre`, `nbr_lits`, `nbr_pers`, `wc`, `douche`) VALUES ('"+string1+"' ,"+string2+" ,"+string3+" ,"+string4+","+string5+",'"+string6+"','"+string7+"') ");
				return x;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, "An error in the information");
				return 0;
			}
		}
		
	  //Remove ROOM Types 
	  public static int RemoveRoomTypes(String nom ) {
			try {
				Connection cn = ConnFsRoomTypes();
				Statement st = (Statement) cn.createStatement();
				int y = st.executeUpdate(" DELETE FROM `chambre` WHERE `nom_type`='"+nom+"'");
				int x = st.executeUpdate(" DELETE FROM `type_chambre` WHERE `nom_type`= '"+nom+"'");
			
				return x;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, "An error in the information");
				return 0;
			}
		}
	  
	  //Edit ROOM TYPES 
	  
	  public int EditRoomTypes(String NOM , int PRIX,int NBR_CHAMBRE,int NBR_LITS, int NBR_PERS,String WC,String DOUCHE ) {
			try {
				Connection cn = ConnFsRoomTypes();
				Statement st = (Statement) cn.createStatement();
				int x = st.executeUpdate("UPDATE `type_chambre` SET `nom_type`= '"+NOM+"',`prix_type`='"+PRIX+"',`nbr_chambre`='"+NBR_CHAMBRE+"',`nbr_lits`='"+NBR_LITS+"',`nbr_pers`='"+NBR_PERS+"',`WC`='"+WC+"',`Douche`='"+DOUCHE+"'"); 
				return x;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, "An error in the information");
				return 0;
			}
		}
	  

		}
		


