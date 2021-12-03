package app.typeSalles;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import app.includes.Style;

   public class Ajouter extends JFrame implements ActionListener{
	   	Conn cn = new Conn();
	   	Style style = new Style();
		JTextField nameTF,prixTF,nbrsTF,tblTF;
		JButton submit;
	
		public Ajouter(){
			
        JLabel  nameLabel = style.inputLabel("le type");
		nameTF = style.textField();
		JPanel nameInput = new JPanel(new BorderLayout());
		nameInput.setBackground(null);
		nameInput.add(nameLabel, BorderLayout.NORTH);
		nameInput.add(nameTF, BorderLayout.CENTER);
		
		JLabel  prixLabel = style.inputLabel("le prix");
		prixTF = style.textField();
		JPanel prixInput = new JPanel(new BorderLayout());
		prixInput.setBackground(null);
		prixInput.add(prixLabel, BorderLayout.NORTH);
		prixInput.add(prixTF, BorderLayout.CENTER);
		
		JLabel  nbrsLabel = style.inputLabel("Nombre des salles");
		nbrsTF = style.textField();
		JPanel nbrsInput = new JPanel(new BorderLayout());
		nbrsInput.setBackground(null);
		nbrsInput.add(nbrsLabel, BorderLayout.NORTH);
		nbrsInput.add(nbrsTF, BorderLayout.CENTER);
		
		JLabel  tblLabel = style.inputLabel("Nombre des tables");
		tblTF = style.textField();
		JPanel tblInput = new JPanel(new BorderLayout());
		tblInput.setBackground(null);
		tblInput.add(tblLabel, BorderLayout.NORTH);
		tblInput.add(tblTF, BorderLayout.CENTER);
      	
		submit = style.darkBlueButton("Ajouter");
		submit.addActionListener(this);
			
		JPanel container = new JPanel(new GridBagLayout());  
        container.setBackground(new Color(255,255,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5,0,5,0);
        gbc.gridx=0;
        gbc.gridy=0;
        container.add(nameInput,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        container.add(prixInput,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        container.add(nbrsInput,gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        container.add(tblInput,gbc);
        gbc.gridx=0;
        gbc.gridy=4;
        container.add(submit,gbc);
        

        this.getContentPane().add(container);
		this.setTitle("Types salle");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(500,450));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submit) {
				Conn cn = new Conn();
				cn.conn();
				if(e.getSource() == submit) {
					if(nameTF.getText().equals("") || prixTF.getText().equals("") || tblTF.getText().equals("") || nbrsTF.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Vous n'pas rempli tous les champs!");
					} else {
						try {
							int prixType = Integer.parseInt(prixTF.getText());
							int nTables = Integer.parseInt(tblTF.getText());
							int nSalles = Integer.parseInt(nbrsTF.getText());
							String typeSalle = nameTF.getText().toString();
							
							int resultOK = cn.setType(typeSalle, prixType, nSalles , nTables);
							if(resultOK == 1)
								JOptionPane.showMessageDialog(null, "le type a bien éte ajouté !");	
						} catch(NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Error, Il y a un problème !");
						}
					}
				}
				
			}
			
		}
}
