package app.type;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import app.fonts.Fonts;
import app.includes.Conn;
import app.includes.Style;

public class RoomTypes extends JFrame implements ActionListener{
	
	Fonts f=new Fonts();
	Style style=new Style();
	Conn cn=new Conn();
	GridBagConstraints c=new GridBagConstraints();
	
	JButton b1,b2,b3,b4,b5,b6,Add,Remove;
	DefaultTableModel tableModel;
	JTable tapleType;
	JScrollPane tableContainer;
	JPanel container;
	FsRoomTypes FsRoomTypesObject = new FsRoomTypes();
	JPanel		Tab = new JPanel();
	
	public RoomTypes() {
		
		/*------------------ SideBar Section & Navigation Buttons -----------------*/
		JPanel nav=new JPanel(new GridBagLayout());
		nav.setPreferredSize(new Dimension(250,350));
		nav.setBackground(null);
		
		b1=style.navButton("app/icons/room.png","Rooms");
		b2=style.navButton("app/icons/chambreType.png","Categories");
		b3=style.navButton("app/icons/salle.png","Salles");
		b4=style.navButton("app/icons/typeSalle.png","Salle Types");
		b5=style.navButton("app/icons/stock.png","Stock");
		b6 = style.navButton("app/icons/room.png", "statistics");
		
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		JButton[] buttonsList= {b1,b2,b3,b4,b5,b6};
		c.gridx=0;
		c.gridy=0;
		for(JButton b : buttonsList) {
			
			nav.add(b,c);
			c.gridy++;
			
		}
		
		JPanel sidebar=new JPanel(new GridBagLayout());
		sidebar.setBackground(new Color(0x24245C));
		sidebar.setPreferredSize(new Dimension(250,600));
		sidebar.add(nav,c);
		
/*-------------------------------------- END -------------------------------*/
		
		/*-----------------------------Bottom Panel Components !---------------------------*/
		
		JPanel bottomPane = new JPanel(new GridBagLayout());
		bottomPane.setPreferredSize(new Dimension(700,75));
		bottomPane.setBackground(Color.white);
		
		Add=style.darkBlueButton("Add");
		Add.addActionListener(this);
		
		Remove=style.darkBlueButton("Delete");
		Remove.addActionListener(this);
		

		
		
		
		bottomPane.add(Add);
		bottomPane.add(Remove);

	
		
		/*--------------------------------------- END -----------------------------------------*/
		
		/*------------------------- Bottom Panel Container ! -------------------------------- */	
	   
		container= new JPanel(new BorderLayout());
	    container.setBackground(new Color(0xFFFFFFF));
	    container.add(bottomPane,BorderLayout.SOUTH);
	    
        /*--------------------------------------- END -----------------------------------------*/
	    //JTable
	    
	    try {
		ResultSet rs = FsRoomTypesObject.infoRoomTypes();
		String[] columnNames = {"Name", "Price", "Number Rooms", "Number lits","Number Man","WC","Bath"};
		
		// Table Initializing & Styling !
		tableModel = new DefaultTableModel(columnNames, 0) {
			 @Override
			 public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;  }
	};
		tapleType = new JTable(tableModel);
        style.table(tapleType);
        while (rs.next()) {	
				Object[] row = {rs.getString(2),rs.getInt(3)+" DH",rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8)};
				tableModel.addRow(row);
		    }
        
        
        // Table Panel
        tableContainer = new JScrollPane(tapleType);
        tableContainer.setBackground(Color.white);
        tableContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        tableContainer.setPreferredSize(new Dimension(900,400));
        Tab.add(tableContainer)	;	
        Tab.setBackground(Color.white);
        // Add Table To Panel & Revalidate !
      
        container.add(Tab, BorderLayout.CENTER);
        container.revalidate();
        
        
        
        
     	
	    

	    }catch(Exception e1) {JOptionPane.showMessageDialog(null,e1.getMessage());}
	    
	  
	    
        
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		/*-------------------------------- Window & Panels Position !------------------------------------ */
	   
	    this.getContentPane().add(sidebar, BorderLayout.WEST);
	    this.getContentPane().add(container, BorderLayout.CENTER);
	    this.setTitle("Categories");
	    style.loadFrameIcon(this);
	    this.setMinimumSize(new Dimension(1000,600));
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    /*--------------------------------------- END -----------------------------------------*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		
	//Add Room Types
		if(e.getSource() == Add ) { new AddRoomTypes();}
		

		
	//Remove Room Types 
		if(e.getSource() == Remove) { 
			try {
			int r =tapleType.getSelectedRow();
			int c =tapleType.getSelectedColumn();
            String nom=(String) tapleType.getValueAt(r, c);
            int x=FsRoomTypes.RemoveRoomTypes(nom);
			if(x==1) {JOptionPane.showMessageDialog(null, "DELETE Successfully");}
			else {JOptionPane.showMessageDialog(null, "ERROR");}
			}
			catch(Exception e5) {JOptionPane.showMessageDialog(null, " Select the type name ");}
		}
		
		
		if(e.getSource() == b1) {new app.ROOM.ROOM();this.dispose(); }
		if(e.getSource() == b2) {new app.type.RoomTypes(); this.dispose();}
		if(e.getSource() == b3) {new app.salles.Salles();this.dispose();}
		if(e.getSource() == b4) {new app.typeSalles.SalleType(); this.dispose();}
		if(e.getSource() == b5) {new app.stock.STOCK() ;this.dispose();}
		if(e.getSource() == b6) {new app.Statistiques.Stastiques(); this.dispose();}
		
		
		
		
}
}


