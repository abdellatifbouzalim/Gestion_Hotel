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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import app.fonts.Fonts;
import app.includes.Style;

/**************************************************
*                                                 *
* @Title	: Gestion des Chambres d'un Hotel !   *
* @author	: Mohamed SOUIDENA                    *
*                                                 *
* ************************************************/

public class EditRoom extends JFrame implements ActionListener {
	
	StyleRoom style = new StyleRoom();
	Fonts f = new Fonts();
	FsRoom FsRoomObjet = new FsRoom();
	JButton edit = style.darkBlueButton("Eit");
	JTextField tf1 = style.textField();
	JTextField tf2 = style.textField();
	JTextField tf3 = style.textField();
	JTextField tf4 = style.textField();
	JDateChooser Dd = style.JDateChooserStyle() ;
	JDateChooser DF = style.JDateChooserStyle() ;
	JComboBox comboBoxTypeRoom = style.comboBox();
	
public EditRoom() {   
		
		/**********************************-***************************/
		/**********************************-***************************/
		
	
		edit.addActionListener(this);
		JLabel  infoSalle = style.inputLabel("Edit Room  :");
		infoSalle.setFont(f.mulish(14));
		
		/**********************************-***************************/
		
	
		JLabel  l1 = style.inputLabel(" ROOM Number :");
		JPanel numSalleInput = new JPanel(new BorderLayout());
		numSalleInput.setBackground(null);
		numSalleInput.add(l1, BorderLayout.NORTH);
		numSalleInput.add(tf1, BorderLayout.CENTER);
		
		/**********************************-***************************/

	
		JLabel  l2 = style.inputLabel(" NOM :");
		JPanel NomClienInput = new JPanel(new BorderLayout());
		NomClienInput.setBackground(null);
		NomClienInput.add(l2, BorderLayout.NORTH);
		NomClienInput.add(tf2, BorderLayout.CENTER);
		
		/**********************************-***************************/
		
		
		JLabel  l3 = style.inputLabel("Prenom :");
		JPanel PrenomClientInput = new JPanel(new BorderLayout());
		PrenomClientInput.setBackground(null);
		PrenomClientInput.add(l3, BorderLayout.NORTH);
		PrenomClientInput.add(tf3, BorderLayout.CENTER);
		
		
		/**********************************-***************************/
	
		
		JLabel  l4 = style.inputLabel("CIN :");
		JPanel CinClienInput = new JPanel(new BorderLayout());
		CinClienInput.setBackground(null);
		CinClienInput.add(l4, BorderLayout.NORTH);
		CinClienInput.add(tf4, BorderLayout.CENTER);
		
	/**********************************-***************************/
	
		
		JLabel  l5 = style.inputLabel("Date debut :");
		JPanel dateDebutInput = new JPanel(new BorderLayout());
		dateDebutInput.setBackground(null);
		dateDebutInput.add(l5, BorderLayout.NORTH);
		dateDebutInput.add(Dd, BorderLayout.CENTER);
		
		/**********************************-***************************/
	
		
		JLabel  l6 = style.inputLabel("Date Fin :");
		JPanel dateFintInput = new JPanel(new BorderLayout());
		dateFintInput.setBackground(null);
		dateFintInput.add(l6, BorderLayout.NORTH);
		dateFintInput.add(DF, BorderLayout.CENTER);
		
		
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
		salleInfosPane.add(CinClienInput,gbc);
		
		
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		salleInfosPane.add(NomClienInput,gbc);
		gbc.gridx = 1;
		salleInfosPane.add(PrenomClientInput,gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		salleInfosPane.add(dateDebutInput,gbc);
		gbc.gridy = 3;
		gbc.gridx = 1;
		salleInfosPane.add(dateFintInput,gbc);
	
		

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
		control.add(edit);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		this.getContentPane().add(form, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		

		/**********************************-***************************/
		/**********************************-***************************/
		
		this.setTitle("Edit Room");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(470, 470));
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
		if(e.getSource() == edit ) {
			if(tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("") || tf4.getText().equals("") ) {
				JOptionPane.showMessageDialog(null, "fill in all the information ");
			}else {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateD = sdf.format(Dd.getDate());
				String dateF = sdf.format(DF.getDate());
				int numRoom = Integer.parseInt(tf1.getText());
				Date dateSys = new Date();
				Date DateDebut =  Dd.getDate();
				Date DateFin =  DF.getDate();
				if(DateDebut.before(dateSys) || DateDebut.after(dateSys) ) { JOptionPane.showMessageDialog(null, "Date invalid"); }
				else {
				
				int w=FsRoomObjet.EditeRoom(tf4.getText(), tf2.getText(), tf3.getText(),numRoom, dateD, dateF);
				if(w==1) {JOptionPane.showMessageDialog(null, "Edit successfully");
				tf1.setText(null);tf2.setText(null);tf3.setText(null);tf4.setText(null);
				}
				}
				
			}
			}
		
		
		
		
	}

}
