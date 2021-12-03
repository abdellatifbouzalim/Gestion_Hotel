package app.ROOM;

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

import com.toedter.calendar.JDateChooser;

import app.fonts.Fonts;

/**************************************************
*                                                 *
* @Title	: Gestion des Chambres d'un Hotel !   *
* @author	: Mohamed SOUIDENA                    *
*                                                 *
* ************************************************/

public class FsRoom {

	
	
	public Connection ConnFsRoom() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Cn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/gestion_hotel?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			return Cn;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null; }
	}
	
	
	// select Types ROOM
		public ResultSet getTypesRoom() {
			try {
				Connection cn = ConnFsRoom();
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT `nom_type` FROM `type_chambre`");
				return rs;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
				return null;
			}
		}
		
		// select infos Room
		public ResultSet infoRoom(String typeRoom) {
			try {
				Connection cn = ConnFsRoom();
				Statement st = (Statement) cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM chambre WHERE nom_type = '"+typeRoom+"'");
				return rs;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
				return null;
			}
		}
		
		//Add ROOM
		public int AddRoom(String string , String string2,String type) {
			try {
				Connection cn = ConnFsRoom();
				Statement st = (Statement) cn.createStatement();
				int x = st.executeUpdate("INSERT INTO `chambre`(`num_chambre`, `etage_chambre`, `nom_type`, `etat_chambre`) VALUES ('"+string+"' ,'"+string2+"' ,'"+type+"' ,'empty') ");
				int y = st.executeUpdate("UPDATE `type_chambre` SET`nbr_chambre`=`nbr_chambre`+1  WHERE `nom_type`='"+type+"'");
				return x;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, "Room already exists");
				return 0;
			}
		}
		
		// DELETE ROOM
		
		public int DeleteRoom(int num ) {
			try {
				Connection cn = ConnFsRoom();
				Statement st = (Statement) cn.createStatement();
				
				ResultSet rs3 = st.executeQuery("SELECT  `nom_type` FROM `chambre` WHERE `num_chambre`= "+num+" ");
				
				if(rs3.next()) {
				String typee = rs3.getString(1);
				int y = st.executeUpdate("UPDATE `type_chambre` SET`nbr_chambre`=`nbr_chambre`-1  WHERE `nom_type`='"+typee+"'");
				}	
				
				
				int m = st.executeUpdate("DELETE FROM `client` WHERE `num_chambre`= "+num+" ");
				int x = st.executeUpdate(" DELETE FROM `chambre` WHERE `num_chambre`= "+num+" ");
				
				
				
				
				
				return x;
			} catch(Exception ex){JOptionPane.showMessageDialog(null, "An error in the information");
				return 0;
			}
		}
		
		// Edit ROOM
		
				public int EditeRoom(String CIN , String NOM ,String PRENOM , int NUMM ,String dd , String df ) {
					try {
						Connection cn = ConnFsRoom();
						Statement st = (Statement) cn.createStatement();
						int c=st.executeUpdate("DELETE FROM `client` WHERE `num_chambre`= "+NUMM+"  ");
						
						
						Date Q = new SimpleDateFormat("yyyy-MM-dd").parse(dd);   
						int mois = Q.getMonth()+1;
						int annee = Q.getYear()+1900;
						
						ResultSet testCount = st.executeQuery("SELECT count(*) FROM `statisqtiques` WHERE `Annee`= "+annee+" and `mois`="+mois+"");
						
						 if (testCount.next()) {
							 int NbrtestCount = testCount.getInt(1);
							 if(NbrtestCount==1) {
	
								int tt= st.executeUpdate("UPDATE `statisqtiques` SET `nbr`=`nbr`+1  WHERE `Annee`= "+annee+" and `mois`="+mois+"  ");
								 
							 }else {
								 
								 int ttt= st.executeUpdate("INSERT INTO `statisqtiques`(`mois`, `Annee`, `nbr`) VALUES ("+mois+" ,"+annee+",1 ) ");
						 }
							 
						 }
						
						
						
						
						
						int a = st.executeUpdate("INSERT INTO `client`( `cin`,`nom_client`, `prenom_client`,`num_chambre`) VALUES ('"+CIN+"','"+NOM+"','"+PRENOM+"',"+NUMM+")");
						int b = st.executeUpdate("UPDATE `chambre` SET `cin`= '"+CIN+"',`etat_chambre`='reserved' ,`date_debut`='"+dd+"' ,`date_fin`= '"+df+"'  WHERE `num_chambre`= "+NUMM+"  "); ;
						
						if (a==1 && b==1 ) { return 1;  }
						else{JOptionPane.showMessageDialog(null, "xxxxxx");}
						return 0;
					} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
						return 0;
					}
				}
				
				// select infos Room Client
				public ResultSet infoRoomClien(String typeRoom) {
					try {
						Connection cn = ConnFsRoom();
						Statement st = (Statement) cn.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM chambre , client where chambre.nom_type ='"+typeRoom+"' and chambre.num_chambre=client.num_chambre ");
						return rs;
					} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
						return null;
					}
				}
				
				
				//Refresh infos Room
				public void RefreshRoom() {
					try {
						Date dsys = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Connection cn = ConnFsRoom();
						Statement st = (Statement) cn.createStatement();
						int a = st.executeUpdate("UPDATE `chambre` SET `cin`=NULL,`etat_chambre`='empty',`date_debut`=NULL,`date_fin`=NULL WHERE `date_fin`='"+sdf.format(dsys)+"' ");
						 JOptionPane.showMessageDialog(null, "Refresh Successfully , + " +a +" ROOM empty");
					} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
						
					}
				}
		
		
		
				
		
	
}
