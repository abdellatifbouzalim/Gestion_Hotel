package app.ROOM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import app.fonts.Fonts;
import app.includes.Conn;
import app.includes.Style;


 
/**************************************************
*                                                 *
* @Title	: Gestion des Chambres d'un Hotel !   *
* @author	: Mohamed SOUIDENA                    *
*                                                 *
* ************************************************/

public class ROOM extends JFrame implements ActionListener {
	
	// déclaration objet  pour utiliser les fonctions crée dans class - FsRoom -
	FsRoom FsRoomObjet = new FsRoom();
	
	// déclaration objet  pour utiliser les Fonts crée dans class - Fonts -
	Fonts f = new Fonts();
	
	// déclaration objet  pour utiliser les styles crée dans class - Style -
	StyleRoom style = new StyleRoom();
	
	// déclaration GridBagConstraints
	GridBagConstraints c = new GridBagConstraints();
	
	// déclaration les élements 
	JButton		b1, b2, b3, b4, b5,b6, search, ajouter ,Modifier , Supprimer,Refresh;
	DefaultTableModel tableModel ;
	JComboBox	comboBox;
	JCheckBox	reserved, empty;
	JTable		table ;
	JScrollPane	tableContainer;
	JPanel		container;
	JPanel		Tab = new JPanel();
	
	//Constructeur
	public  ROOM(){
		
		/*--------------------------------- left pannel ----------------------------------*/
		
		JPanel nav = new JPanel(new GridBagLayout());
		nav.setPreferredSize(new Dimension(250,350));
		nav.setBackground(null);
		
		//Style  navButton
		b1 = style.navButton("app/icons/room.png", "Rooms");
		b2 = style.navButton("app/icons/chambreType.png", "Categories");
		b3 = style.navButton("app/icons/salle.png", "Salles");
		b4 = style.navButton("app/icons/typeSalle.png", "Salle Types");
		b5 = style.navButton("app/icons/stock.png", "Stock");
		b6 = style.navButton("app/icons/room.png", "statistics");
		
		// Action Listener 
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		//ajouter les buttons dans pannel NAV 
		JButton[] buttonsList = {b1,b2,b3,b4,b5,b6};
		c.gridx = 0; c.gridy = 0;
		for(JButton b : buttonsList) {
			nav.add(b,c);
			c.gridy++;
		}

		//l'emplacement de left pannel
		JPanel sidebar = new JPanel(new GridBagLayout());
		sidebar.setBackground(new Color(0x24245C));
		sidebar.setPreferredSize(new Dimension(250,600));
		sidebar.add(nav,c);
		
		/*----------------------------------------- END ----------------------------------------*/
		
		
		
		/*--------------------------------- Right pannel "Top" ----------------------------------*/
		
		//Top Panel  
		JLabel label = new JLabel("Select Type ROOM :");
		label.setFont(f.mulish(14));
		label.setForeground(new Color(0x24245C));
		
		// object  Connection de class - FsRoomObjet-
		Connection CnnFsRoom =FsRoomObjet.ConnFsRoom();
		
		//ResultSet = Types Room
		ResultSet results = FsRoomObjet.getTypesRoom();
		
		//changer style comboBox
		comboBox = style.comboBox();
		
		//remplir comboBox a partir DATABASE
		try {
			while(results.next()) {
				String type = results.getString("nom_type");
				comboBox.addItem(type);	// Add Data To COMBOBOX !
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//changer style checkBox
		reserved = style.checkBox("Reserved");
		empty = style.checkBox("Empty");
		search = style.darkBlueButton("search");
		reserved.addActionListener(this);
		empty.addActionListener(this);
		
		// Action Listener pour SEARCH
		search.addActionListener(this);

		//Ajouter les elements 
		JPanel wrapper = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 10, 0, 10);
		wrapper.setPreferredSize(new Dimension(700,75));
		wrapper.setBackground(Color.white);
		wrapper.add(label,gbc);
		wrapper.add(comboBox,gbc);
		wrapper.add(reserved,gbc);
		wrapper.add(empty,gbc);
		wrapper.add(search,gbc);
		
		
		/*---------------------------------------- END ----------------------------------------------*/
		
		
		
		
		/*--------------------------------- Right pannel "Bottom" ----------------------------------*/
		
		
		//Bottom pannel
        JPanel bottomPane = new JPanel(new GridBagLayout());
        bottomPane.setPreferredSize(new Dimension(700,75));
        bottomPane.setBackground(Color.white);
        
        //changer style 
        ajouter = style.darkBlueButton("Add"); 
        Modifier=style.darkBlueButton("Edit");
        Supprimer=style.darkBlueButton("Delete");
        Refresh=style.darkBlueButton("Refresh");
        
        // Action Listener pour ADD EDIT DELETE REFRESH
        ajouter.addActionListener(this);
    	Supprimer.addActionListener(this);
    	Modifier.addActionListener(this);
    	Refresh.addActionListener(this);
    	
    	
    	//Ajouter les elements  dans bottomPane
		bottomPane.add(ajouter);
		bottomPane.add(Modifier);
		bottomPane.add(Supprimer);
		bottomPane.add(Refresh);
		
		/*---------------------------------------- END -------------------------------------------------*/
		
		
		
		
		
		/*-------------------------Top & Bottom Panel Container ! --------------------------------------- */		
		container = new JPanel(new BorderLayout());
		container.setBackground(new Color(0xFFFFFFF));
		container.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		container.add(wrapper, BorderLayout.NORTH);
		container.add(bottomPane, BorderLayout.SOUTH);
		container.add(Tab, BorderLayout.CENTER);
		
		/*--------------------------------------- END ----------------------------------------------------*/
		
		
		
		
		/*-------------------------------- Window & Panels Position !------------------------------------ */
		
		this.getContentPane().add(sidebar, BorderLayout.WEST);
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setTitle("ROOM");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(1000, 600));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/*--------------------------------------- END -----------------------------------------*/
			
	}

	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		Connection CnnFsRoom =FsRoomObjet.ConnFsRoom();
		ResultSet rs = FsRoomObjet.infoRoom((String)comboBox.getSelectedItem());
		ResultSet rs2 = FsRoomObjet.infoRoomClien((String)comboBox.getSelectedItem());
		
		if(e.getSource() == empty ) {
			reserved.setSelected(false);
		}

		if(e.getSource() == reserved  ) {
			empty.setSelected(false);
		}
		
		
		// search
		if(e.getSource() == search) {
			
			try {
				
				
				//empty
				if(empty.isSelected()) {
					
					//columnNames
					String[] columnNames = {"NUM","Etage","Type","Etat"};
					// Table Initializing & Styling !
					tableModel = new DefaultTableModel(columnNames, 0) {
							 @Override
							 public boolean isCellEditable(int row, int column) {
							       //all cells false
							       return false;  }
					};
					//JTable
				    table = new JTable(tableModel);
				    style.table(table);
				    while (rs.next()) {	
						// Empty Room Only !
						if( (rs.getString(6) == null)) {
						Object[] row = {rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(5)};
						tableModel.addRow(row);
					    }
				     }  
				  
				}
				
				
				//reserved
				else if(reserved.isSelected()) {
				
					//columnNames
					String[] columnNames = {"NUM","Etage","Type","CIN","NOM","PRENOM","Etat","Date Debut","Date Fin"};
					// Table Initializing & Styling !
					tableModel = new DefaultTableModel(columnNames, 0) {
						    @Override
						    public boolean isCellEditable(int row, int column) {
						       //all cells false
						       return false;  }	
					};
					//JTable
			        table = new JTable(tableModel);
			        style.table(table);
			         while (rs2.next()) {	
						// reserved Room Only !
						if( (rs2.getString(6) != null)) {
						Object[] row = {rs2.getInt(1),rs2.getInt(2),rs2.getString(3),rs2.getString(4),rs2.getString(9),rs2.getString(10),rs2.getString(5),rs2.getString(6),rs2.getString(7)};
						tableModel.addRow(row);
					    }
			         } 
			    
			      }
			

			} catch (SQLException e1) { JOptionPane.showMessageDialog(null,e1.getMessage());}
			
			
	        //Table Panel
			table.setBackground(Color.white);
			
	        tableContainer = new JScrollPane(table);
	        tableContainer.setBackground(Color.white);
	        tableContainer.setPreferredSize(new Dimension(900,500));
	        tableContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	        
	        // Add Table To Panel & Revalidate 
	        Tab.removeAll();
	        Tab.add(tableContainer)	;	
	        Tab.setBackground(Color.white);
	        Tab.validate();
	        
	        container.validate();
	     
	        
	        // Close Connection !
	        try { CnnFsRoom.close(); } 
	        catch (SQLException e2) { JOptionPane.showMessageDialog(null,e2.getMessage()); }
		}
		
		//Add
		if(e.getSource() == ajouter) { new AddRoom();}
		
		//Edit
		if(e.getSource() == Modifier) { new EditRoom();}
		
		//Delete
		if(e.getSource() == Supprimer) { 
			try {
			int r =table.getSelectedRow();
			int c =table.getSelectedColumn();
			int numm = (int) table.getValueAt(r,c);
			int x =FsRoomObjet.DeleteRoom(numm);
			if(x==1) {JOptionPane.showMessageDialog(null, "DELETED Successfully");}
			else {JOptionPane.showMessageDialog(null, "ERROR");}
			}
			catch(Exception e5) {JOptionPane.showMessageDialog(null, " Select the room number cell ");}
		}
		
	
		// Refresh
		if(e.getSource() == Refresh) { FsRoomObjet.RefreshRoom();}
		
		
		//SideBar Buttons 
		if(e.getSource() == b1) {new app.ROOM.ROOM();this.dispose(); }
		if(e.getSource() == b2) {new app.type.RoomTypes(); this.dispose();}
		if(e.getSource() == b3) {new app.salles.Salles();this.dispose();}
		if(e.getSource() == b4) {new app.typeSalles.SalleType(); this.dispose();}
		if(e.getSource() == b5) {new app.stock.STOCK() ;this.dispose();}
		if(e.getSource() == b6) {new app.Statistiques.Stastiques(); this.dispose();}
		
		
		
	}
	
	
	
	
	
	
	
	
}
