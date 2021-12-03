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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.fonts.Fonts;
import app.includes.Style;

public class NewSalle extends JFrame implements ActionListener{

	Style style = new Style();
	Fonts f = new Fonts();
	JButton submit = style.darkBlueButton("submit");
	JButton cancel = style.lightGrayButton("cancel");
	
	JTextField numeroTF, etageTF;
	JComboBox comboBox;
	
	public NewSalle() {

		/*--
		 * Form Initializing !
		 *-- */	
		JLabel  numeroLabel = style.inputLabel("Num�ro Salle:");
		numeroTF = style.textField();
		JPanel numSalleInput = new JPanel(new BorderLayout());
		numSalleInput.setBackground(null);
		numSalleInput.add(numeroLabel, BorderLayout.NORTH);
		numSalleInput.add(numeroTF, BorderLayout.CENTER);

		JLabel  etageLabel = style.inputLabel("Etage :");
		etageTF = style.textField();
		JPanel etageSalleInput = new JPanel(new BorderLayout());
		etageSalleInput.setBackground(null);
		etageSalleInput.add(etageLabel, BorderLayout.NORTH);
		etageSalleInput.add(etageTF, BorderLayout.CENTER);
		
		ResultSet results = new app.salles.Conn().getTypesSalles();
		comboBox = style.comboBox();
		comboBox.setBackground(Color.white);
		comboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		try {
			while(results.next()) {
				String type = results.getString("nom_typeS");
				comboBox.addItem(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel  typeLabel = style.inputLabel("Type Salle :");
		JPanel typeSalleInput = new JPanel(new BorderLayout());
		typeSalleInput.setBackground(null);
		typeSalleInput.add(typeLabel, BorderLayout.NORTH);
		typeSalleInput.add(comboBox, BorderLayout.CENTER);

		JTextField etatTF = style.textField();
		etatTF.setText("empty");
		etatTF.setEditable(false);
		JLabel  etatLabel = style.inputLabel("Etat Salle :");
		JPanel etatSalleInput = new JPanel(new BorderLayout());
		etatSalleInput.setBackground(null);
		etatSalleInput.add(etatLabel, BorderLayout.NORTH);
		etatSalleInput.add(etatTF, BorderLayout.CENTER);
		
		/*--
		 * ADD Panels to the Container !
		 *-- */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets =  new Insets(20,0,0,10);
		gbc.gridx = gbc.gridy = 0;
		
		JPanel form = new JPanel(new GridBagLayout());
		form.setBackground(Color.decode("#F3F3FB"));
		form.add(numSalleInput,gbc);
		gbc.gridx++;
		form.add(etageSalleInput,gbc);
		gbc.gridx--;
		gbc.gridy++;
		form.add(typeSalleInput,gbc);
		gbc.gridx++;
		form.add(etatSalleInput,gbc);

		/*--
		 * Buttons Panel !
		 *-- */ 
		JPanel control = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets =  new Insets(0,5,0,5);
		control.setPreferredSize(new Dimension(600,60));
		control.setBackground(new Color(0xF3F3FB));
		submit.addActionListener(this);
		cancel.addActionListener(this);
		control.add(submit,c);
		control.add(cancel,c);
		
		/*--
		 * Frame & Layout Position !
		 *-- */
		this.getContentPane().add(form, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		this.setTitle("ADD Salle");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(550, 400));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Conn cn = new Conn();
		cn.conn();
		if(e.getSource() == cancel) {this.dispose();}
		if(e.getSource() == submit) {
			if(numeroTF.getText().equals("") || etageTF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Fill in all the information !");
			} else {
				try {
					int numeroSalle = Integer.parseInt(numeroTF.getText());
					int numeroEtage = Integer.parseInt(etageTF.getText());
					String typeSalle = (String)comboBox.getSelectedItem();
					int resultOK = cn.setNewSalle(numeroSalle, numeroEtage, typeSalle);
					if(resultOK == 1)
						JOptionPane.showMessageDialog(null, "Added successfully");	
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error Occured ! Something's wrong !");
				}
			}
		}
	}
}
