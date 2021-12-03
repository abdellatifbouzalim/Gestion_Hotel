package app.salles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.fonts.Fonts;
import app.includes.*;

/**
* @Title	: Gestion des salles d'un Hotel !
* @author	: Mouaad BOULHANA
* */

public class Salles extends JFrame implements ActionListener{
	
	Style style = new Style();
	Conn cn = new Conn();
	
	/*--
	 * Initializing components !
	 *-- */
	JButton		b1, b2, b3, b4, b5,b6, search, ajouter, delete, reserve, makeEmpty, refresh;
	JComboBox	comboBox;
	JCheckBox	reserved, empty;
	JTable		table;
	JScrollPane	tableContainer;
	JPanel		container;

	public Salles() {		
		/*--
		 * SideBar Panel !
		 *-- */
		b1 = style.navButton("app/icons/room.png", "Rooms");
		b2 = style.navButton("app/icons/chambreType.png", "Categories");
		b3 = style.navButton("app/icons/salle.png", "Salles");
		b4 = style.navButton("app/icons/typeSalle.png", "Salle Types");
		b5 = style.navButton("app/icons/stock.png", "Stock");
		b6 = style.navButton("app/icons/room.png", "statistics");
		GridBagConstraints c = new GridBagConstraints();
		JPanel nav = new JPanel(new GridBagLayout());
		nav.setPreferredSize(new Dimension(250,350));
		nav.setBackground(null);
		
		JButton[] buttonsList = {b1,b2,b3,b4,b5,b6};
		c.gridx = 0; c.gridy = 0;
		for(JButton b : buttonsList) {
			b.addActionListener(this);
			nav.add(b,c);
			c.gridy++;
		}

		JPanel sidebar = new JPanel(new GridBagLayout());
		sidebar.setBackground(new Color(0x24245C));
		sidebar.setPreferredSize(new Dimension(250,600));
		sidebar.add(nav,c);
		
		/*--
		 * Top Panel Components !
		 *-- */
		JLabel label = new JLabel("Select Type :");
		label.setFont(new app.fonts.Fonts().mulish(14));
		label.setForeground(new Color(0x24245C));
		
		cn.conn(); 
		ResultSet results = cn.getTypesSalles();
		comboBox = style.comboBox();
		try {
			while(results.next()) {
				String type = results.getString("nom_typeS");
				comboBox.addItem(type);	// Add Data To COMBOBOX !
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		reserved = style.checkBox("Reserved");
		empty = style.checkBox("Empty");
		search = style.darkBlueButton("search");
		search.addActionListener(this);
		
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
		
		/*--
         * Bottom Panel Components !
         *-- */
        JPanel bottomPane = new JPanel(new GridBagLayout());
        GridBagConstraints bottomPaneConstraints = new GridBagConstraints();
        bottomPane.setPreferredSize(new Dimension(700,75));
        bottomPane.setBackground(Color.white);
        bottomPaneConstraints.insets = new Insets(0,5,0,5);
        ajouter = style.darkBlueButton("new");
        ajouter.addActionListener(this);
		
        reserve = style.darkBlueButton("reserve");
        reserve.addActionListener(this);
        
        delete = style.darkBlueButton("delete");
        delete.addActionListener(this);
        
        makeEmpty = style.darkBlueButton("empty");
        makeEmpty.addActionListener(this);
        
        refresh = style.darkBlueButton("refresh");
        refresh.addActionListener(this);
        
        bottomPane.add(ajouter, bottomPaneConstraints);
        bottomPane.add(reserve, bottomPaneConstraints);
        bottomPane.add(delete, bottomPaneConstraints);
        bottomPane.add(makeEmpty, bottomPaneConstraints);
		
        /*--
		 * Add Top & Bottom Panel to the Container !
		 *-- */		
		container = new JPanel(new BorderLayout());
		container.setBackground(new Color(0xFFFFFFF));
		container.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		container.add(wrapper, BorderLayout.NORTH);
		container.add(bottomPane, BorderLayout.SOUTH);
		
		/*--
		 * Frame & Panels Position !
		 *-- */
		this.getContentPane().add(sidebar, BorderLayout.WEST);
		this.getContentPane().add(container, BorderLayout.CENTER);
		
		this.setTitle("Salles");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(1000,600));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == delete) {
			try {
				int rowValue = (int)table.getSelectedRow();
				int numSalle = (int)(table.getValueAt(rowValue, 0));
				int resultOK = cn.removeSalle(numSalle);
				if(resultOK == 1)
					JOptionPane.showMessageDialog(null, "Room "+numSalle+" is deleted successfully !");
			} catch(ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred! Select room first !");
			}
		}
		
		if (e.getSource() == makeEmpty) {
			try {
				int rowValue = (int)table.getSelectedRow();
				int numSalle = (int)(table.getValueAt(rowValue, 0));
				int resultOK = cn.makeEmpty(numSalle);
				if(resultOK == 1) JOptionPane.showMessageDialog(null, "Room "+numSalle+" is empty!");
			} catch(ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred! Select room first !");
			}
		}
		
		if(e.getSource() == search) {
			cn.conn();
			ResultSet rs = cn.infoSalles((String)comboBox.getSelectedItem());
			String[] columnNames = {"Num","Etage","Type","Date Debut","Date Fin","CIN"};
			
			// Table Initializing & Styling !
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
			table = new JTable(tableModel);
	        style.table(table);
	        // Fetching Data From DB !
			try {
				while (rs.next()) {	
					// Reserved Room Only !
					if(reserved.isSelected() && (rs.getString(6) != null)) {
						Object[] row = {rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getString(6)};
						tableModel.addRow(row);
					}		
					// Empty Room Only !
					if(empty.isSelected() && (rs.getString(6) == null)) {
						Object[] row = {rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getString(6)};
						tableModel.addRow(row);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
	        // Table Panel
	        tableContainer = new JScrollPane(table);
	        tableContainer.setBackground(Color.white);
	        tableContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	        
	        // Add Table To Panel & Revalidate !
	        container.add(tableContainer, BorderLayout.CENTER);
	        container.revalidate();
	        // Close Connection !
	        try {
				cn.conn().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == ajouter) { new NewSalle(); }
		if(e.getSource() == reserve) { new ReserveSalle(); }
		
		
		/** SideBar Buttons !**/
		if(e.getSource() == b1) {new app.ROOM.ROOM();this.dispose(); }
		if(e.getSource() == b2) {new app.type.RoomTypes(); this.dispose();}
		if(e.getSource() == b3) {new app.salles.Salles();this.dispose();}
		if(e.getSource() == b4) {new app.typeSalles.SalleType(); this.dispose();}
		if(e.getSource() == b5) {new app.stock.STOCK() ;this.dispose();}
		if(e.getSource() == b6) {new app.Statistiques.Stastiques(); this.dispose();}
		
	}

}
