package p1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.QuestionsDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminChangeID extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChangeID frame = new AdminChangeID();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminChangeID() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Change ID/password");
		label.setFont(new Font("Dialog", Font.PLAIN, 24));
		label.setBounds(155, 79, 253, 81);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Enter new username/password");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(171, 169, 237, 35);
		contentPane.add(label_1);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		usernameLabel.setBounds(169, 232, 82, 35);
		contentPane.add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(261, 240, 111, 20);
		contentPane.add(usernameTextField);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		passwordLabel.setBounds(169, 278, 82, 35);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(261, 286, 111, 20);
		contentPane.add(passwordField);
		
		JButton button_update = new JButton("Update");
		button_update.addActionListener(e ->
		{
					QuestionsDAO qdao = new QuestionsDAO();
					if(qdao.updateAdmin(usernameTextField.getText(), passwordField.getText())>0)
					{
						JOptionPane.showMessageDialog(null, "Username/Password updated successfully");
						new Admin_Home().setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Unexpected Error occured while updating username/password!");
					}
		});
		button_update.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		button_update.setBounds(194, 343, 82, 28);
		contentPane.add(button_update);
		
		JButton button_back = new JButton("Back");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new Admin_Home().setVisible(true);
				dispose();
			}
		});
		button_back.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		button_back.setBounds(297, 343, 82, 28);
		contentPane.add(button_back);
	}

}
