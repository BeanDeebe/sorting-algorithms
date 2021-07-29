package View;
/*
 * CS5004
 * Dean Beebe
 * Final Project
 * 
 * This file contains the View component of the project.
 */

import javax.swing.JFrame;
import java.sql.*;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;

import com.mysql.cj.Query;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class UserLogin extends JFrame {
	
	private JTextField txtEnterName;
	private JTextField txtEnterdigitPhone;
	
	public UserLogin() {
		getContentPane().setLayout(null);
		
		JPanel panel_login = new JPanel();
		panel_login.setBounds(0, -4, 450, 277);
		getContentPane().add(panel_login);
		SpringLayout sl_panel_login = new SpringLayout();
		panel_login.setLayout(sl_panel_login);
		
		JLabel lbl_LoginTitle = new JLabel("Register");
		sl_panel_login.putConstraint(SpringLayout.NORTH, lbl_LoginTitle, 10, SpringLayout.NORTH, panel_login);
		sl_panel_login.putConstraint(SpringLayout.WEST, lbl_LoginTitle, 179, SpringLayout.WEST, panel_login);
		panel_login.add(lbl_LoginTitle);
		lbl_LoginTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lbl_LoginTitle.setBackground(Color.WHITE);
		
		txtEnterName = new JTextField();
		sl_panel_login.putConstraint(SpringLayout.NORTH, txtEnterName, 22, SpringLayout.SOUTH, lbl_LoginTitle);
		sl_panel_login.putConstraint(SpringLayout.WEST, txtEnterName, 115, SpringLayout.WEST, panel_login);
		sl_panel_login.putConstraint(SpringLayout.SOUTH, txtEnterName, -191, SpringLayout.SOUTH, panel_login);
		sl_panel_login.putConstraint(SpringLayout.EAST, txtEnterName, -155, SpringLayout.EAST, panel_login);
		panel_login.add(txtEnterName);
		txtEnterName.setText("Enter Name");
		txtEnterName.setColumns(10);
		
		txtEnterdigitPhone = new JTextField();
		sl_panel_login.putConstraint(SpringLayout.NORTH, txtEnterdigitPhone, 24, SpringLayout.SOUTH, txtEnterName);
		sl_panel_login.putConstraint(SpringLayout.WEST, txtEnterdigitPhone, 115, SpringLayout.WEST, panel_login);
		sl_panel_login.putConstraint(SpringLayout.SOUTH, txtEnterdigitPhone, -136, SpringLayout.SOUTH, panel_login);
		sl_panel_login.putConstraint(SpringLayout.EAST, txtEnterdigitPhone, 0, SpringLayout.EAST, txtEnterName);
		panel_login.add(txtEnterdigitPhone);
		txtEnterdigitPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtEnterdigitPhone.setText("Enter Phone Number");
		txtEnterdigitPhone.setColumns(10);
		
		JButton btn_submitRegister = new JButton("Submit");
		
		/**
		 * mouse click event that - upon the user hitting submit - stores the name field and phone number field into a MySQL database, with the phone value acting
		 * as an ID. Closes the window and opens the MainPage class window view.
		 */
		//TODO should this code be in a controller file????
		btn_submitRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/applications?allowPublicKeyRetrieval=true&useSSL=false", "root", "Patriots2020!");
					String phone = txtEnterdigitPhone.getText();
					String name = txtEnterName.getText();
					String query = "INSERT INTO registration (phone, name) VALUES(?, ?)";
					
					PreparedStatement ps = connector.prepareStatement(query);
					ps.setString(1, phone);
					ps.setString(2, name);
					ps.executeUpdate();
					
					connector.close();
					
				
					MainPage main = new MainPage();
					main.setTitle("The Roux Golf Club");
					main.setSize(900, 600);
					main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					main.setVisible(true);
					closeLoginWindow();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		sl_panel_login.putConstraint(SpringLayout.NORTH, btn_submitRegister, 27, SpringLayout.SOUTH, txtEnterdigitPhone);
		sl_panel_login.putConstraint(SpringLayout.WEST, btn_submitRegister, 150, SpringLayout.WEST, panel_login);
		sl_panel_login.putConstraint(SpringLayout.SOUTH, btn_submitRegister, -69, SpringLayout.SOUTH, panel_login);
		sl_panel_login.putConstraint(SpringLayout.EAST, btn_submitRegister, 0, SpringLayout.EAST, lbl_LoginTitle);
		panel_login.add(btn_submitRegister);
	}
	public void closeLoginWindow() {
		this.setVisible(false);
	}
	
	
}
