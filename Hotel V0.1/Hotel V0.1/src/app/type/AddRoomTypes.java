package app.type;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.fonts.Fonts;
import app.includes.Style;

public class AddRoomTypes extends JFrame implements ActionListener{
    
	
	Style style = new Style();
	Fonts f = new Fonts();
	FsRoomTypes FsRoomTypesObjet = new FsRoomTypes();
	JButton add = style.darkBlueButton("Add");
	JTextField tf1 = style.textField();
	JTextField tf2 = style.textField();
	JTextField tf3 = style.textField();
	JTextField tf4 = style.textField();
	JTextField tf5 = style.textField();
	JTextField tf6 = style.textField();
	JTextField tf7 = style.textField();
	
	public AddRoomTypes() {
		
		/**********************************-***************************/
		/**********************************-***************************/
		
		add.addActionListener(this);
		JLabel  infoRoomType = style.inputLabel("Add Categories  :");
		infoRoomType.setFont(f.mulish(14));
		
		/**********************************-***************************/
		
		
		JLabel  l1 = style.inputLabel("Name:");
		JPanel nomTypeInput = new JPanel(new BorderLayout());
		nomTypeInput.setBackground(null);
		nomTypeInput.add(l1, BorderLayout.NORTH);
		nomTypeInput.add(tf1, BorderLayout.CENTER);
		
		/**********************************-***************************/

		
		JLabel  l2 = style.inputLabel("Price :");
		JPanel prixTypeInput = new JPanel(new BorderLayout());
		prixTypeInput.setBackground(null);
		prixTypeInput.add(l2, BorderLayout.NORTH);
		prixTypeInput.add(tf2, BorderLayout.CENTER);
		
		/**********************************-***************************/
		

		JLabel  l3 = style.inputLabel("Number Rooms :");
		JPanel nbrChambreInput = new JPanel(new BorderLayout());
		nbrChambreInput.setBackground(null);
		nbrChambreInput.add(l3, BorderLayout.NORTH);
		nbrChambreInput.add(tf3, BorderLayout.CENTER);
		
		/**********************************-***************************/


		JLabel  l4 = style.inputLabel("Number lits :");
		JPanel nbrLitsInput = new JPanel(new BorderLayout());
		nbrLitsInput.setBackground(null);
		nbrLitsInput.add(l4, BorderLayout.NORTH);
		nbrLitsInput.add(tf4, BorderLayout.CENTER);
		
		/**********************************-***************************/


		JLabel  l5 = style.inputLabel("Number Man :");
		JPanel nbrPersInput = new JPanel(new BorderLayout());
		nbrPersInput.setBackground(null);
		nbrPersInput.add(l5, BorderLayout.NORTH);
		nbrPersInput.add(tf5, BorderLayout.CENTER);
		
		/**********************************-***************************/


		JLabel  l6 = style.inputLabel("WC :");
		JPanel WCInput = new JPanel(new BorderLayout());
	    WCInput.setBackground(null);
		WCInput.add(l6, BorderLayout.NORTH);
		WCInput.add(tf6, BorderLayout.CENTER);
		
		/**********************************-***************************/


		JLabel  l7 = style.inputLabel("Bath :");
		JPanel doucheInput = new JPanel(new BorderLayout());
		doucheInput.setBackground(null);
		doucheInput.add(l7, BorderLayout.NORTH);
		doucheInput.add(tf7, BorderLayout.CENTER);
		
		/**********************************-***************************/
        /**********************************-***************************/
		
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel infoRoomTypePane = new JPanel(new GridBagLayout());
		infoRoomTypePane.setBackground(null);
		gbc.insets =  new Insets(20,0,0,10);
		gbc.gridx = gbc.gridy = 0;
		infoRoomTypePane.add(infoRoomType,gbc);
		gbc.gridy = 1;
		infoRoomTypePane.add(nomTypeInput,gbc);
		gbc.gridx = 1;
		infoRoomTypePane.add(prixTypeInput,gbc);
		gbc.gridx = 2;
		infoRoomTypePane.add(nbrChambreInput,gbc);
	     gbc.gridy=2;	
		gbc.gridx = 0;
		infoRoomTypePane.add(nbrLitsInput,gbc);
		gbc.gridx = 1;
		infoRoomTypePane.add(nbrPersInput,gbc);
		gbc.gridx = 2;
		infoRoomTypePane.add(WCInput,gbc);
		gbc.gridy=3;	
		gbc.gridx = 1;
		infoRoomTypePane.add( doucheInput,gbc);
		
		  

		/**********************************-***************************/
		/**********************************-***************************/
		
		JPanel form = new JPanel();
		form.setBackground(new Color(0xF3F3FB));
		form.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		form.setPreferredSize(new Dimension(600,340));
		form.add(infoRoomTypePane);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		JPanel control = new JPanel(new GridBagLayout());
		control.setPreferredSize(new Dimension(600,60));
		control.setBackground(new Color(0xF3F3FB));
		control.add(add);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		this.getContentPane().add(form, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		this.setTitle("Add Categories");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(900, 420));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		/*--------------------------------------- END -----------------------------------------*/
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == add ) {
			if(tf1.getText().equals("") || tf2.getText().equals("")|| tf3.getText().equals("") || tf4.getText().equals("") || tf5.getText().equals("") || tf6.getText().equals("") || tf7.getText().equals("") ) {
				JOptionPane.showMessageDialog(null, "fill in all the information ");
			}else {
				try {
				int w=FsRoomTypesObjet.AddNewRoomTypes(tf1.getText(),Integer.parseInt(tf2.getText()),Integer.parseInt(tf3.getText()),Integer.parseInt(tf4.getText()),Integer.parseInt(tf5.getText()),tf6.getText(),tf7.getText());
				if(w==1) {JOptionPane.showMessageDialog(null, "Added successfully");
			}
				
				}catch(Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
				
			
				
			}
		
		
	}
	

}
}
