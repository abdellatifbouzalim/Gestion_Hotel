package app.includes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/* *
*                                                     
*                                                       
* @Title	: Gestion des Chmabres d'un Hotel !            
* @author	: Mohamed SOUIDENA                        
*                                                     
*                                              
* @Title	: Gestion des Types Chmabres d'un Hotel !
* @author	: Samira LAMRABT
* 
* 
* @Title	: Gestion des salles d'un Hotel !
* @author	: Mouaad BOULHANA
* 
* 
* @Title	: Gestion des Types salles d'un Hotel !
* @author	: Abdellatif BOUALIM
* 
* 
* @Title	: Gestion Stock d'un Hotel !
* @author	: Loubna AIT-HRA 
* 
* 
* */

public class Home extends JFrame implements ActionListener{
	
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

	public Home() {		
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
	
		/* -- Content Container -- */
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("app/icons/welcome.png"));		
		JLabel label = new JLabel();
		label.setIcon(img);
		label.setText("Welcome Back!");
		label.setForeground(new Color(0x24245C));
		label.setFont(new app.fonts.Fonts().mulish(25));
		label.setIcon(img);
		label.setIconTextGap(25);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		
		
		container = new JPanel(new GridBagLayout());
		container.setBackground(new Color(0xFFFFFFF));
		container.add(label);
		/*--
		 * Frame & Panels Position !
		 *-- */
		this.getContentPane().add(sidebar, BorderLayout.WEST);
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setTitle("Home");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(1000,600));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {new app.ROOM.ROOM();this.dispose(); }
		if(e.getSource() == b2) {new app.type.RoomTypes(); this.dispose();}
		if(e.getSource() == b3) {new app.salles.Salles();this.dispose();}
		if(e.getSource() == b4) {new app.typeSalles.SalleType(); this.dispose();}
		if(e.getSource() == b5) {new app.stock.STOCK() ;this.dispose();}
		if(e.getSource() == b6) {new app.Statistiques.Stastiques(); this.dispose();}
	}
//	public static void main(String[] args) {
//		new Home();
//	}
}
