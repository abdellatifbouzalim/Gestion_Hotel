package app.stock;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.fonts.Fonts;




public class STOCK extends JFrame implements ActionListener  {

		ImageIcon dashIcon = new ImageIcon(ClassLoader.getSystemResource("app/icons/dashIcon.png"));	
		Fonts font = new Fonts();
		
		Style navButtonStyle = new Style();
		GridBagConstraints c = new GridBagConstraints();
		JButton b1, b2, b3, b4, b5,b6,but1,but2,but3,but4,but5;
		Conn cn =new Conn();
		JPanel	container;
		JComboBox colorBox ;
		JPanel pan1 =new JPanel();
		JPanel pan2 =new JPanel();
		JPanel pan3 =new JPanel();
		Style style = new Style();
		JTable	 tbL;
		public STOCK() {
				/* -- Sidebar START -- */

			// Buttons Style
			b1 = navButtonStyle.navButton("app/icons/room.png", "Rooms");
			b2 = navButtonStyle.navButton("app/icons/chambreType.png", "Categories");
			b3 = navButtonStyle.navButton("app/icons/salle.png", "Salles");
			b4 = navButtonStyle.navButton("app/icons/typeSalle.png", "Salle Types");
			b5 = navButtonStyle.navButton("app/icons/stock.png", "Stock");
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
			sidebar.setPreferredSize(new Dimension(250,600));
			sidebar.add(nav,c);
				/* -- END Sidebar -- */
					 
				/* -- Content Container -- */
		//	ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("app/icons/welcome.png"));		
			JLabel label = new JLabel();
			//label.setIcon(img);
			//label.setText("Welcome Back!");
			label.setForeground(new Color(0x24245C));
			label.setFont(font.mulish(25));
		//	label.setIcon(img);
			label.setIconTextGap(25);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setVerticalTextPosition(JLabel.BOTTOM);
		


			container = new JPanel(new BorderLayout());
			container.setBackground(new Color(0xFFFFFFF));
	        container.setBorder(BorderFactory.createEmptyBorder(50, 20,20, 25));
			container.add(pan1,BorderLayout.NORTH);
			container.add(pan2,BorderLayout.SOUTH);
			pan3.setBackground(Color.WHITE);
         	container.add(pan3,BorderLayout.CENTER);
			pan1.setBackground(new Color(0xFFFFFFF));
			pan2.setBackground(new Color(0xFFFFFFF));
			pan1.setPreferredSize(new Dimension(700,80));
			pan2.setPreferredSize(new Dimension(700,100));
// --------Add Data To COMBOBOX !
			cn.conn(); 
			ResultSet results = cn.GetCombobox();
			colorBox = style.comboBox();
			
				try {
					while(results.next()) {
						String test = results.getString("nom_category");
						colorBox.addItem(test);	// Add Data To COMBOBOX !
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
//--------- add combobox to "pan1"
				pan1.add(colorBox);
//----------add search to "pan1"
				 but1= navButtonStyle.darkBlueButton("Search");
				pan1.add(but1);
				but1.addActionListener(this);
//----------add to "pan2"			  
				 but2= navButtonStyle.darkBlueButton("Add");
				pan2.add(but2);		
//----------remove to "pan2"	
				 but3= navButtonStyle.darkBlueButton("Remove");
				pan2.add(but3);
//----------event 
				but3.addActionListener(this);
//----------remove to "pan2"			
				 but4= navButtonStyle.darkBlueButton( "Edit");
				pan2.add(but4);
//----------event
				but4.addActionListener(this);
				
//  go to Ajouterstock 
			but2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					JFrame f1=new app.stock.Ajouterstock();
					f1.setVisible(true);
				}
			});
		
				/* -- END Container -- */

			// Layouts Position
			this.getContentPane().add(sidebar, BorderLayout.WEST);
			this.getContentPane().add(container, BorderLayout.CENTER);
			
			// Window
			this.setTitle("Stock");
			this.setIconImage(dashIcon.getImage());
			this.setSize(1000,600);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
//event
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == but1) {
				cn.conn();
				// table bd end edit
				ResultSet rs =cn.Gettable((String)colorBox.getSelectedItem());
				String[] titles={"Nom produit","Quantité"," Catégory","Prix"};
				DefaultTableModel tablstock=new DefaultTableModel(titles,0) {
		         //  empêche modification sur la table
					public boolean isCellEditable(int row ,int column) {
						return false;
					}	
				};
				try {
					while (rs.next()) {	
							Object[] row = {rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)};
							tablstock.addRow(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
					 tbL =new JTable(tablstock);
					  style.table(tbL);
	
		 JScrollPane tbssc =new JScrollPane(tbL);
		 tbssc.setPreferredSize(new Dimension(900,500));
		 tbssc.setBackground(new Color(0xFFFFFFF));
		 tbssc.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	        // Add Table To Panel
		 	pan3.removeAll();
			pan3.add(tbssc);
			pan3.validate();
			container.validate();
			}
// supprimer bd
	if(e.getSource() == but3) {
		try {
			int r=tbL.getSelectedRow();
			int c=tbL.getSelectedColumn();
			String nom=(String) tbL.getValueAt(r, c);
			int x=cn.SupprimerStock(nom);
			if(x==1) {
				JOptionPane.showMessageDialog(null, "DELETE Successfully");}
			else {
				JOptionPane.showMessageDialog(null, "Select the produit name cell ");
			}
		
		} catch(Exception e3) { JOptionPane.showMessageDialog(null, "Select the produit name cell ");}
		
			}
// modifier bd
	if(e.getSource() == but4) {
		new ModifierStock();		
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
	
		
	
	
	
	

