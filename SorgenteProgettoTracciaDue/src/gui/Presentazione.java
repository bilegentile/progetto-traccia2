package gui;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;

public class Presentazione extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	
	public Presentazione(Controller c) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		IlControllore =c;

		setTitle("Azienda- Benvenuto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benvenuto nell'ufficiale app dell'azienda,");
				lblNewLabel.setBounds(23, 35, 348, 42);
				contentPane.add(lblNewLabel);
				
				JButton btnNewButton = new JButton("Sviluppatore");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IlControllore.AvviaLoginSviluppatore();
					}
				});
				btnNewButton.setBounds(250, 177, 121, 51);
				contentPane.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("Project Manager");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IlControllore.LoginProjectManager();
					}
				});
				btnNewButton_1.setBounds(39, 174, 139, 51);
				contentPane.add(btnNewButton_1);
				
				JLabel lblNewLabel_1 = new JLabel("Clicca sul tuo ruolo:");
				lblNewLabel_1.setBounds(23, 75, 155, 13);
				contentPane.add(lblNewLabel_1);
				
	}
}

