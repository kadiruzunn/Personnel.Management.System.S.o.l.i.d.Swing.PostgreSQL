package com.pms.presentationlayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class MainWindow {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtSifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("E-mail:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(75, 33, 114, 34);
		frame.getContentPane().add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setText("ali@gmail.com");
		txtEmail.setBounds(199, 42, 86, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblifre.setBounds(75, 78, 114, 34);
		frame.getContentPane().add(lblifre);
		
		JButton btnGiris = new JButton("Giriş");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEmail.getText();
				String sifre = txtSifre.getText();
				
				
				if("ali@gmail.com".equals(email) && "123".equals(sifre)) { 
					System.out.println("doğru");
					
					PersonelYonetimEkrani personelYonetim = new PersonelYonetimEkrani();
					personelYonetim.setVisible(true);
					frame.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(btnGiris, "Hatalı email ve/veya şifre");
					System.out.println("yanlış");
				}
			}
			
		});
		btnGiris.setBounds(199, 134, 89, 23);
		frame.getContentPane().add(btnGiris);
		
		txtSifre = new JPasswordField();
		txtSifre.setBounds(199, 87, 86, 20);
		txtSifre.setText("123");
		frame.getContentPane().add(txtSifre);
	}
}
