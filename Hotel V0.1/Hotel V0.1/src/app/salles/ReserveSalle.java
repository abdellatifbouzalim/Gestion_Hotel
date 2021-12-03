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
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import app.fonts.Fonts;
import app.includes.*;

public class ReserveSalle extends JFrame implements ActionListener{
	
	Fonts f = new Fonts();
	Style style = new Style();
	Conn cn = new Conn();
	
	JTextField numeroTF, cinTF, nomTF, prenomTF;
	JDateChooser dateDebut, dateFin;
	JButton submit, cancel;
	
	
	public ReserveSalle() {
		/*--
		 * Room Data From !
		 *-- */	
		JLabel roomInfo = style.inputLabel("Salle Information");
		roomInfo.setFont(f.mulish(14));
		
		JLabel  numeroLabel = style.inputLabel("Numéro Salle :");
		numeroTF = style.textField();
		numeroTF.setPreferredSize(new Dimension(380,40));
		JPanel numSalleInput = new JPanel(new BorderLayout());
		numSalleInput.setBackground(null);
		numSalleInput.add(numeroLabel, BorderLayout.NORTH);
		numSalleInput.add(numeroTF, BorderLayout.CENTER);	
		
		/*--
		 * Client Data From !
		 *-- */	
		JLabel clientInfo = style.inputLabel("Client Data");
		clientInfo.setFont(f.mulish(14));
		
		JLabel nomLabel = style.inputLabel("Nom :");
		nomTF = style.textField();
		JPanel nomInput = new JPanel(new BorderLayout());
		nomInput.setBackground(null);
		nomInput.add(nomLabel, BorderLayout.NORTH);
		nomInput.add(nomTF, BorderLayout.CENTER);
		
		JLabel prenomLabel = style.inputLabel("Prénom :");
		prenomTF = style.textField();
		JPanel prenomInput = new JPanel(new BorderLayout());
		prenomInput.setBackground(null);
		prenomInput.add(prenomLabel, BorderLayout.NORTH);
		prenomInput.add(prenomTF, BorderLayout.CENTER);
		
		JLabel cinLabel = style.inputLabel("CIN :");
		cinTF = style.textField();
		cinTF.setPreferredSize(new Dimension(380,40));
		JPanel cinInput = new JPanel(new BorderLayout());
		cinInput.setBackground(null);
		cinInput.add(cinLabel, BorderLayout.NORTH);
		cinInput.add(cinTF, BorderLayout.CENTER);
		
		/*--
		 * Reservation Section !
		 *-- */
		JLabel ReservationInfo = style.inputLabel("Resérvation");
		ReservationInfo.setFont(f.mulish(14));
		
		dateDebut = style.dateChooserStyle();
		JLabel DDLabel = style.inputLabel("Date Début :");
		JPanel dateDebutInput = new JPanel(new BorderLayout());
		dateDebutInput.setBackground(null);
		dateDebutInput.add(DDLabel, BorderLayout.NORTH);
		dateDebutInput.add(dateDebut, BorderLayout.CENTER);
		
		dateFin = style.dateChooserStyle() ;
		JLabel  DFLabel = style.inputLabel("Date Fin :");
		JPanel dateFinInput = new JPanel(new BorderLayout());
		dateFinInput.setBackground(null);
		dateFinInput.add(DFLabel, BorderLayout.NORTH);
		dateFinInput.add(dateFin, BorderLayout.CENTER);
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(0xF3F3FB));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		formPanel.setPreferredSize(new Dimension(600,340));
		
		GridBagConstraints formConstraints = new GridBagConstraints();
		formConstraints.insets =  new Insets(10,0,0,20);
		formConstraints.gridx = 0;
		formConstraints.gridy = 0;
		formConstraints.gridwidth = 2;
		
		formPanel.add(roomInfo,formConstraints);
		formConstraints.gridy = 1;
		formPanel.add(numSalleInput, formConstraints);
		formConstraints.gridy = 2;
		formPanel.add(clientInfo, formConstraints);
		formConstraints.gridy = 3;
		formConstraints.gridwidth = 1;
		formPanel.add(nomInput, formConstraints);
		formConstraints.gridx = 1;
		formConstraints.gridwidth = 1;
		formPanel.add(prenomInput, formConstraints);
		formConstraints.gridx = 0;
		formConstraints.gridy = 4;
		formConstraints.gridwidth = 2;
		formPanel.add(cinInput, formConstraints);
		formConstraints.gridx = 0;
		formConstraints.gridy = 5;
		formConstraints.gridwidth = 2;
		formPanel.add(ReservationInfo, formConstraints);
		formConstraints.gridy = 6;
		formConstraints.gridwidth = 1;
		formPanel.add(dateDebutInput, formConstraints);
		formConstraints.gridx = 1;
		formPanel.add(dateFinInput, formConstraints);
		
		JPanel control = new JPanel(new GridBagLayout());
		GridBagConstraints bottomPaneConstraints = new GridBagConstraints();
		bottomPaneConstraints.insets = new Insets(0,0,0,5);
		control.setPreferredSize(new Dimension(600,60));
		control.setBackground(new Color(0xF3F3FB));
		
		submit = style.darkBlueButton("Submit");
		cancel = style.lightGrayButton("Cancel");
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
		control.add(submit,bottomPaneConstraints);
		control.add(cancel,bottomPaneConstraints);
		
		this.getContentPane().add(formPanel, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		this.setTitle("Edit Salle");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(700, 550));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel) { this.dispose(); }
		if(e.getSource() == submit) {
			if(numeroTF.getText().equals("") || nomTF.getText().equals("") || prenomTF.getText().equals("") || cinTF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Fill in all the information !");
			} else {
				try {
					int numero = Integer.parseInt(numeroTF.getText());
					String nom = nomTF.getText().toString();
					String prenom = prenomTF.getText().toString();
					String cin = cinTF.getText().toString();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateD = format.format(dateDebut.getDate());
					String dateF = format.format(dateFin.getDate());
					int resultOK = cn.setNewSalleData(nom, prenom, cin, numero, dateD, dateF);
					if(resultOK == 1)
						JOptionPane.showMessageDialog(null, "Added successfully");	
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error Occured ! Something's wrong !");
				}
			}
			
		}
		
	}
}