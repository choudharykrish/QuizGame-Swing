package p1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.image.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Admin_Login extends JFrame {

	private JPanel contentPane;
	static Admin_Login frame;
	Connection con;
	private JTextField uname_TF;
	private JPasswordField password_TF;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Admin_Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setColor(JPanel panel)
	{
		panel.setBackground(new Color(156,156,156));
	}
	private void resetColor(JPanel panel)
	{
		panel.setBackground(new Color(240,240,240));
	}
	private void setLightColor(JPanel panel)
	{
		panel.setBackground(new Color(211,211,211));
	}
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Admin_Login() throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz","root","12345");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from Admin_login");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(52, 140, 250));
		panel.setBounds(0, 0, 594, 126);
		contentPane.add(panel);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("SignIn.png"));
		label_3.setBounds(60, 42, 50, 50);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Admin Login");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 36));
		label_4.setBounds(115, 35, 354, 41);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Please login to continue...");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(125, 73, 278, 30);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(43, 42, 50, 50);
		panel.add(label_6);
		
		uname_TF = new JTextField();
		uname_TF.setForeground(new Color(0, 102, 204));
		uname_TF.setFont(new Font("Tahoma", Font.BOLD, 14));
		uname_TF.setColumns(10);
		uname_TF.setBorder(BorderFactory.createEmptyBorder());
		uname_TF.setBounds(279, 185, 135, 20);
		contentPane.add(uname_TF);
		
		password_TF = new JPasswordField();
		password_TF.setForeground(new Color(0, 102, 255));
		password_TF.setFont(new Font("Tahoma", Font.BOLD, 14));
		password_TF.setBorder(BorderFactory.createEmptyBorder());
		password_TF.setBounds(279, 229, 135, 20);
		contentPane.add(password_TF);
		
		JLabel label = new JLabel("Username:");
		label.setForeground(new Color(0, 102, 255));
		label.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		label.setBounds(165, 183, 104, 20);
		contentPane.add(label);
		
		JLabel label_7 = new JLabel("Password:");
		label_7.setForeground(new Color(0, 102, 255));
		label_7.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		label_7.setBounds(165, 227, 104, 20);
		contentPane.add(label_7);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 102, 255));
		separator.setBackground(new Color(0, 102, 255));
		separator.setBounds(279, 204, 135, 5);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 102, 255));
		separator_1.setBackground(new Color(0, 102, 255));
		separator_1.setBounds(279, 248, 135, 5);
		contentPane.add(separator_1);
		
		JPanel panel_Login = new JPanel();
		panel_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(panel_Login);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panel_Login);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_Login);
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try {
					rs.beforeFirst();
					int flag = 0;
					@SuppressWarnings("deprecation")
					String pwd = password_TF.getText();
					String user = uname_TF.getText();
					System.out.println("Input Username : "+user+" \nInput Password: "+password_TF.getPassword().toString());
					while(rs.next())
					{
						System.out.println("Username: "+rs.getString(1));
						System.out.println("Password: "+rs.getString(2));
						if((user.equals(rs.getString(1)))&&(pwd.equals(rs.getString(2))))
						{
							flag = 1;
							break;
							
						}
					}
					
					//valid user
					if(flag==1)
					{
						JOptionPane.showMessageDialog(null, "Success!\n Welcome " + user);
						uname_TF.setText("");
						password_TF.setText("");
						//new Test().setVisible(true);
						dispose();
						new Admin_Home().setVisible(true);						
					}
					//Invalid User
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Username or password!");
						uname_TF.setText("");
						password_TF.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_Login.setLayout(null);
		panel_Login.setBounds(165, 297, 100, 100);
		contentPane.add(panel_Login);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon("Login.png"));
		label_8.setBounds(22, 11, 50, 50);
		panel_Login.add(label_8);
		
		JLabel label_9 = new JLabel("  Login");
		label_9.setForeground(new Color(0, 102, 255));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_9.setBounds(22, 72, 68, 19);
		panel_Login.add(label_9);
		
		JPanel panel_Back = new JPanel();
		panel_Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(panel_Back);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panel_Back);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_Back);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new Index().setVisible(true);
				dispose();
			}
		});
		panel_Back.setLayout(null);
		panel_Back.setBounds(314, 297, 100, 100);
		contentPane.add(panel_Back);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon("Back.png"));
		label_10.setBounds(22, 11, 50, 50);
		panel_Back.add(label_10);
		
		JLabel label_11 = new JLabel("Back");
		label_11.setForeground(new Color(0, 102, 255));
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(32, 72, 40, 19);
		panel_Back.add(label_11);
	}
}
