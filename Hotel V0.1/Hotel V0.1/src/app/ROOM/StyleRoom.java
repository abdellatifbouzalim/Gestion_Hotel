package app.ROOM;

import java.awt.Color;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import app.fonts.*;

/**************************************************
*                                                 *
* @Title	: Gestion des Chambres d'un Hotel !   *
* @author	: Mohamed SOUIDENA                    *
*                                                 *
* ************************************************/
public class StyleRoom {
    
    Fonts f = new Fonts();

    public JButton navButton(String path, String name) {
        JButton button = new JButton();
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource(path));
        button.setText(name);
        button.setIcon(img);
        button.setPreferredSize(new Dimension(170,50));
		button.setFocusable(false);
		button.setFont(f.mulish(13));
		button.setIconTextGap(15);
        button.setForeground(new Color(0xFFFFFF));
		button.setBackground(null);
        button.setBorder(null);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        return button;
    }
    
    public JCheckBox checkBox(String text) {
    	JCheckBox checkBox = new JCheckBox();
    	checkBox.setText(text);
    	checkBox.setFocusable(false);
    	checkBox.setBackground(null);
    	checkBox.setFont(f.mulish(14));
    	checkBox.setForeground(new Color(0x24245C));
    	checkBox.setIconTextGap(10);
    	return checkBox;
    }
    
    public JButton darkBlueButton(String name) {
        JButton button = new JButton();
        button.setText(name);
        button.setPreferredSize(new Dimension(100,35));
        button.setFocusable(false);
        button.setFont(f.mulish(13));
        button.setIconTextGap(15);
        button.setForeground(new Color(0xFFFFFF));
        button.setBackground(new Color(0x24245C));
        button.setBorder(null);
        return button;
    }
    
    public JComboBox comboBox() {
    	JComboBox comboBox = new JComboBox();
    	comboBox.setPreferredSize(new Dimension(180,35));
		comboBox.setForeground(new Color(0x24245C));
		comboBox.setBackground(new Color(0xEBF3FB));
		comboBox.setFocusable(false);
		comboBox.setFont(f.mulish(14));
    	return comboBox;
    }
    public void table(JTable table) {
    	table.setBackground(Color.decode("#FFFFFF"));
        table.setFont(f.mulish(12));
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setFont(f.mulish(13));
        table.getTableHeader().setBackground(Color.decode("#24245C"));
        table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
    }
    public void loadFrameIcon(JFrame frame) {
    	ImageIcon dashIcon = new ImageIcon(ClassLoader.getSystemResource("app/icons/dashIcon.png"));
    	frame.setIconImage(dashIcon.getImage());
    }
    
    public JTextField textField() {
    	JTextField textField = new JTextField();
    	textField.setPreferredSize(new Dimension(180,40));
    	textField.setForeground(new Color(0x24245C));
		textField.setBackground(new Color(0xFFFFFF));
		textField.setFont(f.poppins(12));
		textField.setBorder(BorderFactory.createLineBorder(Color.decode("#D0DDE5"), 1));
		// let the UI set a border, then squeeze in another border of your own
		textField.setBorder(BorderFactory.createCompoundBorder(
			textField.getBorder(),
			BorderFactory.createEmptyBorder(0, 15, 0, 0)));
    	return textField;
    }
    
    public JLabel inputLabel(String text) {
    	JLabel label = new JLabel(text);
		label.setFont(f.poppins(12));
		label.setForeground(new Color(0x24245C));
		label.setIconTextGap(25);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setBorder(BorderFactory.createEmptyBorder(0,2,2,0));
		return label;
    }
    
	
	 public JDateChooser JDateChooserStyle() {
		 JDateChooser dtt = new JDateChooser() ;
	        dtt.setPreferredSize(new Dimension(170,50));
	        dtt.setFocusable(false);
	        dtt.setFont(f.mulish(13));
	        dtt.setForeground(new Color(0xFFFFFF));
	        dtt.setBackground(null);
	        dtt.setBorder(null);
	        
	        return dtt;
	    }
} 
