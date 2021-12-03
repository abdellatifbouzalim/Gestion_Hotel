package app.stock;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ajouterstock   extends JFrame implements ActionListener {
	
	
	Style ST=new Style();
	JLabel r1 ,r2,r3,r4;
	JTextField tx1,tx2,tx4;
	JButton but1;
	JComboBox com1;
	
	
	
	Conn cn =new Conn();
	public  Ajouterstock () {
		// design 
		r1= ST.inputLabel("Nom produit");
		r2= ST.inputLabel("Quantité");
		r3= ST.inputLabel("Catégory");
		r4= ST.inputLabel("Prix");
		
		tx1=ST.textField();
		tx2=ST.textField();
		com1=ST.comboBox();
		tx4=ST.textField();
		 but1= ST.darkBlueButton("Add");
		 but1.addActionListener(this);
		// Add Data To COMBOBOX !
			cn.conn(); 
			ResultSet results = cn.GetCombobox();
			try {
				while(results.next()) {
					String type = results.getString("nom_category");
					com1.addItem(type);	// Add Data To COMBOBOX !
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		 
		JPanel navv = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets=new Insets(10,10,10,10);
	c.gridx=0;c.gridy=0;navv.add(r1,c);
	c.gridx=1;c.gridy=0;navv.add(tx1,c);
	c.gridx=0;c.gridy=1;navv.add(r2,c);
	c.gridx=1;c.gridy=1;navv.add(tx2,c);
	c.gridx=0;c.gridy=2;navv.add(r3,c);
	c.gridx=1;c.gridy=2;navv.add(com1,c);
	c.gridx=0;c.gridy=3;navv.add(r4,c);
	c.gridx=1;c.gridy=3;navv.add(tx4,c);
	c.gridx=1;c.gridy=4;navv.add(but1,c);

	// add panel 
		this.add(navv);
	 	this.setSize(600,450);
		this.setTitle("Add Produit");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);}
		// ajouter bd
		public void actionPerformed(ActionEvent e) {
	
			cn.conn();
				if(e.getSource() ==  but1) {
					if(tx1.getText().equals("") || tx2.getText().equals("")|| tx4.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill in all the information !");
					} else {
						try {
							String nom_produit = tx1.getText().toString();
							int quantite_produit = Integer.parseInt(tx2.getText());
							String category= (String)com1.getSelectedItem();
							int prix_unite =Integer.parseInt(tx4.getText());
							int resultOK =cn.setproduct(nom_produit,quantite_produit,category,prix_unite);
						if(resultOK == 1)
							JOptionPane.showMessageDialog(null, "Added successfully");	
						} catch(NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Error Occured ! Something's wrong !");
						}
					}
				}
			}
		

	public static void main(String[] args) {
		new  Ajouterstock();

	}

}
