package app.includes;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conn  {
	
	// Get Connection to database
	public Connection conn()  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Cn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/gestion_hotel?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			return Cn;
		} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
			return null; }
	}
	
	
			public boolean getUserInfos(String username , String password) {
				try {
					Connection cn = conn();
					Statement st = (Statement) cn.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM `user` WHERE `username`='"+username+"' AND `password` ='"+password+"'  ");
					String user , pass;
					while(rs.next()) {
						user=rs.getString(1);pass=rs.getString(2);
						if(username.equals(user) && password.equals(pass) ) {return true;}
						}
					
				} catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				return false;

			}
	
	
	
	
	
	
	
}
