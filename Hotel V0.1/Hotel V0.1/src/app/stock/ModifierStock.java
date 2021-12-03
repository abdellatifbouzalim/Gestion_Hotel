package app.stock;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifierStock extends JFrame implements ActionListener{
	
	Style ST=new Style();
	JLabel r1 ,r2;
	JTextField tx1,tx2;
	JButton but1;
	Conn cn =new Conn();
public  ModifierStock () {
		// Style JLabel
		r1= ST.inputLabel("Nom produit");
		r2= ST.inputLabel("Quantité");	
		// Style JTextField  
		tx1=ST.textField();
		tx2=ST.textField();
		// 	Style JButton
		 but1= ST.darkBlueButton("Edit");
		 // event
		 but1.addActionListener(this);
		 // panel 
		JPanel navv = new JPanel(new GridBagLayout());
		// GridBag
		GridBagConstraints c = new GridBagConstraints();
		c.insets=new Insets(10,10,10,10);
	c.gridx=0;c.gridy=0;navv.add(r1,c);
	c.gridx=1;c.gridy=0;navv.add(tx1,c);
	c.gridx=0;c.gridy=1;navv.add(r2,c);
	c.gridx=1;c.gridy=1;navv.add(tx2,c);
	c.gridx=0;c.gridy=2;navv.add(but1,c);

		this.add(navv);
	 	this.setSize(400,250);
	 	this.setTitle("Edit Produit");
	    this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

}
	@Override
	// edit bd
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() ==  but1) {
			
			if(tx1.getText().equals("") || tx2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Fill in all the information !");
			} else {
				try {

					int resultOK =cn.EditP(tx1.getText(),Integer.parseInt(tx2.getText()));
				if(resultOK == 1) {
					JOptionPane.showMessageDialog(null, "fill button");
					}
				else {	JOptionPane.showMessageDialog(null, "Edit successfully");}
				
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error Occured ! Something's wrong !");
				}
			}	
		}
		}
		
	}




