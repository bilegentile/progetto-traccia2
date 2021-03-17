package gui;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;

public class LoginProjectManager extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private JButton btnNewButton_1;
	Controller IlControllore;
	public LoginProjectManager(Controller c) {
		IlControllore =c;
		
		setTitle("Azienda- Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice Fiscale");
		lblNewLabel.setBounds(30, 48, 163, 55);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(208, 66, 153, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaBenvenutoPM(textField.getText());
			}
		});
		btnNewButton.setBounds(242, 183, 119, 41);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=1;
				IlControllore.TornaPresentazione(i);
			}
		});
		btnNewButton_1.setBounds(34, 183, 127, 41);
		contentPane.add(btnNewButton_1);
	}
}
