package app.ROOM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.fonts.Fonts;
import app.includes.Conn;
import app.includes.Style;

/**************************************************
*                                                 *
* @Title	: Gestion des Chambres d'un Hotel !   *
* @author	: Mohamed SOUIDENA                    *
*                                                 *
* ************************************************/

public class AddRoom extends JFrame implements ActionListener  {
	
	StyleRoom style = new StyleRoom();
	Fonts f = new Fonts();
	FsRoom FsRoomObjet = new FsRoom();
	JButton Add = style.darkBlueButton("Add");
	JTextField tf1 = style.textField();
	JTextField tf2 = style.textField();
	JTextField tf4 = style.textField();
	JComboBox comboBoxTypeRoom = style.comboBox();
	public AddRoom() {   
		
		/**********************************-***************************/
		/**********************************-***************************/
		
	
		Add.addActionListener(this);
		JLabel  infoSalle = style.inputLabel("Add Room  :");
		infoSalle.setFont(f.mulish(14));
		
		/**********************************-***************************/
		
	
		JLabel  l1 = style.inputLabel("Numéro :");
		JPanel numSalleInput = new JPanel(new BorderLayout());
		numSalleInput.setBackground(null);
		numSalleInput.add(l1, BorderLayout.NORTH);
		numSalleInput.add(tf1, BorderLayout.CENTER);
		
		/**********************************-***************************/

	
		JLabel  l2 = style.inputLabel("Etage :");
		JPanel etageSalleInput = new JPanel(new BorderLayout());
		etageSalleInput.setBackground(null);
		etageSalleInput.add(l2, BorderLayout.NORTH);
		etageSalleInput.add(tf2, BorderLayout.CENTER);
		
		/**********************************-***************************/
		
		Connection cn = FsRoomObjet.ConnFsRoom();
		ResultSet results = FsRoomObjet.getTypesRoom();
		
		comboBoxTypeRoom.setBackground(Color.white);
		comboBoxTypeRoom.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		try {
			while(results.next()) {
				String type = results.getString("nom_type");
				comboBoxTypeRoom.addItem(type);	// Add Data To COMBOBOX !
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/**********************************-***************************/
		JLabel  l3 = style.inputLabel("Type :");
		JPanel typeSalleInput = new JPanel(new BorderLayout());
		typeSalleInput.setBackground(null);
		typeSalleInput.add(l3, BorderLayout.NORTH);
		typeSalleInput.add(comboBoxTypeRoom, BorderLayout.CENTER);
		
		
		/**********************************-***************************/
	
		
		JLabel  l4 = style.inputLabel("Etat :");
		JPanel EtatRoomInput = new JPanel(new BorderLayout());
		EtatRoomInput.setBackground(null);
		tf4.setText("empty");
		tf4.setEditable(false);
		EtatRoomInput.add(l4, BorderLayout.NORTH);
		EtatRoomInput.add(tf4, BorderLayout.CENTER);
		
		
		/**********************************-***************************/
		/**********************************-***************************/
		
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel salleInfosPane = new JPanel(new GridBagLayout());
		salleInfosPane.setBackground(null);
		gbc.insets =  new Insets(20,0,0,10);
		gbc.gridx = gbc.gridy = 0;
		salleInfosPane.add(infoSalle,gbc);
		gbc.gridy = 1;
		salleInfosPane.add(numSalleInput,gbc);
		gbc.gridx = 1;
		salleInfosPane.add(etageSalleInput,gbc);
		gbc.gridx = 2;
		salleInfosPane.add(typeSalleInput,gbc);
		gbc.gridx = 3;
		salleInfosPane.add(EtatRoomInput,gbc);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		JPanel form = new JPanel();
		form.setBackground(new Color(0xF3F3FB));
		form.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		form.setPreferredSize(new Dimension(600,340));
		form.add(salleInfosPane);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		JPanel control = new JPanel(new GridBagLayout());
		control.setPreferredSize(new Dimension(600,60));
		control.setBackground(new Color(0xF3F3FB));
		control.add(Add);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		this.getContentPane().add(form, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		this.setTitle("Add Room");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(900, 270));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	

		
		/*--------------------------------------- END -----------------------------------------*/
			
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Connection CnnFsRoom =FsRoomObjet.ConnFsRoom();
		if(e.getSource() == Add ) {
			if(tf1.getText().equals("") || tf2.getText().equals("")  ) {
			
				JOptionPane.showMessageDialog(null, "fill in all the information ");
			
			}else {
				String typeR=(String) comboBoxTypeRoom.getSelectedItem();
				int w=FsRoomObjet.AddRoom(tf1.getText(), tf2.getText(), typeR);
				if(w==1) {JOptionPane.showMessageDialog(null, "Added successfully");tf1.setText(null);tf2.setText(null);}
				
			}
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
