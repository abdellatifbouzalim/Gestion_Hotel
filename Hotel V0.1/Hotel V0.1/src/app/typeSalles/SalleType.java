package app.typeSalles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.fonts.Fonts;
import app.includes.Style;

public class SalleType extends JFrame implements ActionListener{

	Fonts font = new Fonts();
	Style style = new Style();
	GridBagConstraints c = new GridBagConstraints();
	JButton b1, b2, b3, b4, b5,b6, buttonajoute,buttonsupp;
   	Conn cn = new Conn();
   	JPanel container;
   	JTable table;
   	 	
	public SalleType() {
			/* -- Sidebar START -- */
		
		// Buttons Style
		b1 = style.navButton("app/icons/room.png", "Rooms");
		b2 = style.navButton("app/icons/chambreType.png", "Categories");
		b3 = style.navButton("app/icons/salle.png", "Salles");
		b4 = style.navButton("app/icons/typeSalle.png", "Salle Types");
		b5 = style.navButton("app/icons/stock.png", "Stock");
		b6 = style.navButton("app/icons/room.png", "statistics");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		JPanel nav = new JPanel(new GridBagLayout());
		nav.setPreferredSize(new Dimension(250,350));
		nav.setBackground(null);
		
		JButton[] buttonsList = {b1,b2,b3,b4,b5,b6};
		c.gridx = 0; c.gridy = 0;
		for(JButton b : buttonsList) {
			nav.add(b,c);
			c.gridy++;
		}

		// Sidebar Pan
		JPanel sidebar = new JPanel(new GridBagLayout());
		sidebar.setBackground(new Color(0x24245C));
		sidebar.setPreferredSize(new Dimension(250,600));  // size panel
		sidebar.add(nav,c);

				 
			/* -- Content Container -- */
		
        String[] titles = {"Name","Price","Number salles","Number tables"};
       
        DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
        table = new JTable(tableModel);
        style.table(table);
        
        cn.conn();
        ResultSet rs = cn.getTableContent();
        
        // Fetching Data From DB !
     	try {
     		while (rs.next()) {	
     			Object[] row = {rs.getString(2),rs.getInt(3)+" Dhs",rs.getInt(4),rs.getInt(5)};
 				tableModel.addRow(row);
     		}
     	} catch (SQLException e1) {
     		e1.printStackTrace();
     	    } 
     	
     	JPanel panel2 = new JPanel(new BorderLayout());
     	panel2.setPreferredSize(new Dimension(750,400));
     	panel2.setBackground(new Color(255,255,255));
     	panel2.add(table.getTableHeader(),BorderLayout.NORTH);
     	panel2.add(table,BorderLayout.CENTER);
     			
     			
     			
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(750,100));
		panel3.setBackground(new Color(255,255,255));
		
		/* --- BUTTONS --- */
		buttonajoute = style.darkBlueButton("Add");
		buttonajoute.addActionListener(this);
		buttonsupp = style.darkBlueButton("Delete");
		buttonsupp.addActionListener(this);
		panel3.add(buttonajoute);
		panel3.add(buttonsupp);
		/* --- end buttons ---*/
		
       
		container = new JPanel(new BorderLayout());
		container.setBackground(new Color(0xFFFFFFF));
		container.setBorder(BorderFactory.createEmptyBorder(25,10,10,10));
        container.add(panel2,BorderLayout.CENTER);
        container.add(panel3,BorderLayout.SOUTH);
      
		
			/* -- END Container -- */

		// Layouts Position
		this.getContentPane().add(sidebar, BorderLayout.WEST);
		this.getContentPane().add(container, BorderLayout.CENTER);
		
		// Window
		this.setTitle("Types salle");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(1000,600));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonajoute) {
			new Ajouter ();
		}
		if(e.getSource() == buttonsupp) {
			try {
				int rowValue = (int)table.getSelectedRow();
				int numType = (int)(table.getValueAt(rowValue, 0));
				int resultOK = cn.removeTypeSalle(numType);
				if(resultOK == 1)
					JOptionPane.showMessageDialog(null, "Room "+numType+" is deleted successfully !");
			} catch(ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred! Select room first !");
			}
		}
		
		//SideBar Buttons 
				if(e.getSource() == b1) {new app.ROOM.ROOM();this.dispose(); }
				if(e.getSource() == b2) {new app.type.RoomTypes(); this.dispose();}
				if(e.getSource() == b3) {new app.salles.Salles();this.dispose();}
				if(e.getSource() == b4) {new app.typeSalles.SalleType(); this.dispose();}
				if(e.getSource() == b5) {new app.stock.STOCK() ;this.dispose();}
				if(e.getSource() == b6) {new app.Statistiques.Stastiques(); this.dispose();}
	}


	
	
}
