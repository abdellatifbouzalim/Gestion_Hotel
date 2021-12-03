package app.Statistiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import app.ROOM.FsRoom;
import app.ROOM.StyleRoom;
import app.Statistiques.Graph.Bar;
import app.fonts.Fonts;

/**************************************************
*                                                 *
* @Title	: Gestion des Statistiques d'un Hotel *
* @author	: Mohamed SOUIDENA                    *
*                                                 *
* ************************************************/
public class Stastiques extends JFrame implements ActionListener {
	
	    // déclaration objet  pour utiliser les fonctions crée dans class - FsRoom -
		FsStatistiques FsStatistiquesObjet = new FsStatistiques();
		
		// déclaration objet  pour utiliser les Fonts crée dans class - Fonts -
		Fonts f = new Fonts();
		
		// déclaration objet  pour utiliser les styles crée dans class - Style -
		StyleStatistiques style = new StyleStatistiques();
		
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
		
		
		//--------------------------------------------------
		

	    private int histogramHeight = 200;
	    private int barWidth = 50;
	    private int barGap = 10;

	    private JPanel barPanel;
	    private JPanel labelPanel;

	    private List<Bar> bars = new ArrayList<Bar>();	
	
	public Stastiques() {
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
		
		
		
		try {
    		Connection cnx = FsStatistiquesObjet.ConnFsStatistiques();
    		String str ="select mois,nbr  FROM statisqtiques ";
    		java.sql.Statement stm = cnx.createStatement();
    		
    		ResultSet rst = stm.executeQuery(str);
    		Graph panelGraphs = new Graph();
    		
    		int mois;
    		int nbr;
    		String moisstr = null;
    		while(rst.next()) {
    			
    			mois = rst.getInt(1);
    			nbr = rst.getInt(2);
    			switch(mois){
     		   case 1: moisstr = "January"; 
     		   break;
     		   case 2: moisstr = "February"; 
     		   break;
     		   case 3: moisstr = "March"; 
     		   break;
     		   case 4: moisstr = "April"; 
     		   break;
     		   case 5: moisstr = "May"; 
     		   break;
     		   case 6: moisstr = "June"; 
     		   break;
     		   case 7: moisstr = "July"; 
     		   break;
     		   case 8: moisstr = "August"; 
     		   break;
     		   case 9: moisstr = "September"; 
     		   break;
     		   case 10: moisstr = "October"; 
     		   break;
     		   case 11: moisstr = "November"; 
     		   break;
     		   case 12: moisstr = "December"; 
     		   break;
     		  }
     		
    			panelGraphs.addHistogramColumn(moisstr, nbr, new Color(0x24245C));
    			
    		}
    		panelGraphs.layoutHistogram();
    		container = panelGraphs;
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		/*-------------------------------- Window & Panels Position !------------------------------------ */
		
		this.getContentPane().add(sidebar, BorderLayout.WEST);
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setTitle("statistics");
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
		
		if(e.getSource() == b1) {new app.ROOM.ROOM();this.dispose(); }
		if(e.getSource() == b2) {new app.type.RoomTypes(); this.dispose();}
		if(e.getSource() == b3) {new app.salles.Salles();this.dispose();}
		if(e.getSource() == b4) {new app.typeSalles.SalleType(); this.dispose();}
		if(e.getSource() == b5) {new app.stock.STOCK() ;this.dispose();}
		if(e.getSource() == b6) {new app.Statistiques.Stastiques(); this.dispose();}
		
	}
	
	 
	
	
	
	
	
	
	

}
