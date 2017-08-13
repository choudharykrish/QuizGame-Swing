package p1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.QuestionsDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AdminChangeID extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private int xx,xy,x,y;

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
	
	
	
	public AdminChangeID(int x, int y) throws HeadlessException {
		this();
		this.x = x;
		this.y = y;
		setLocation(x, y);
	}

	/**
	 * Create the frame.
	 */
	public AdminChangeID() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTextField = new JTextField();
		usernameTextField.setForeground(new Color(0, 102, 204));
		usernameTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameTextField.setBorder(BorderFactory.createEmptyBorder());
		usernameTextField.setColumns(10);
		
		usernameTextField.setBounds(265, 209, 111, 20);
		contentPane.add(usernameTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(BorderFactory.createEmptyBorder());
		passwordField.setForeground(new Color(0, 102, 255));
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBounds(265, 253, 111, 20);
		contentPane.add(passwordField);
		
		JPanel panelBlueHead = new JPanel();
		panelBlueHead.setLayout(null);
		panelBlueHead.setBackground(new Color(52, 140, 250));
		panelBlueHead.setBounds(0, 0, 600, 126);
		contentPane.add(panelBlueHead);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(43, 26, 50, 50);
		panelBlueHead.add(label_2);
		
		JLabel label_3 = new JLabel("Change ID/password");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 36));
		label_3.setBounds(103, 35, 354, 41);
		panelBlueHead.add(label_3);
		
		JLabel label = new JLabel("Enter new username/password");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(128, 74, 278, 30);
		panelBlueHead.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("Change.png"));
		label_1.setBounds(43, 42, 50, 50);
		panelBlueHead.add(label_1);
		
		JLabel label_4 = new JLabel("Username:");
		label_4.setForeground(new Color(0, 102, 255));
		label_4.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		label_4.setBounds(151, 211, 104, 20);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Password:");
		label_5.setForeground(new Color(0, 102, 255));
		label_5.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		label_5.setBounds(151, 255, 104, 20);
		contentPane.add(label_5);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 102, 255));
		separator.setForeground(new Color(0, 102, 255));
		separator.setBounds(265, 228, 111, 5);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 102, 255));
		separator_1.setBackground(new Color(0, 102, 255));
		separator_1.setBounds(265, 272, 111, 5);
		contentPane.add(separator_1);
		
		JPanel panel_update = new JPanel();
		panel_update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panel_update);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panel_update);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_update);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				QuestionsDAO qdao = new QuestionsDAO();
				if(qdao.updateAdmin(usernameTextField.getText(), passwordField.getText())>0)
				{
					JOptionPane.showMessageDialog(null, "Username/Password updated successfully");
					new Admin_Home(x,y).setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Unexpected Error occured while updating username/password!");
				}
			}
		});
		panel_update.setLayout(null);
		panel_update.setBounds(129, 323, 100, 100);
		contentPane.add(panel_update);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon("Update.png"));
		label_6.setBounds(22, 11, 50, 50);
		panel_update.add(label_6);
		
		JLabel label_7 = new JLabel(" Update");
		label_7.setForeground(new Color(0, 102, 255));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(22, 72, 68, 19);
		panel_update.add(label_7);
		
		JPanel panel_back = new JPanel();
		panel_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panel_back);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panel_back);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_back);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new Admin_Home(x,y).setVisible(true);
				dispose();
			}
		});
		panel_back.setLayout(null);
		panel_back.setBounds(300, 323, 100, 100);
		contentPane.add(panel_back);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon("Back.png"));
		label_8.setBounds(22, 11, 50, 50);
		panel_back.add(label_8);
		
		JLabel label_9 = new JLabel("Back");
		label_9.setForeground(new Color(0, 102, 255));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_9.setBounds(32, 72, 40, 19);
		panel_back.add(label_9);
		
		//Adding Drag and positioning functionality
		panelBlueHead.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getXOnScreen()-xx;
				y = e.getYOnScreen()-xy;
				setLocation(x,y);
			}
		});
		
		panelBlueHead.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		
		//Close Panel Top Right
		JPanel panelClose = new JPanel();
		panelClose.setBackground(new Color(52, 140, 250));
		panelClose.setBounds(570, 0, 30, 30);
		panelBlueHead.add(panelClose);
		panelClose.setLayout(null);
		
		JLabel lblX = new JLabel("");
		lblX.setBounds(0, 0, 30, 30);
		panelClose.add(lblX);
		panelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelClose.setBackground(new Color(42,100,186));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelClose.setBackground(new Color(52,140,250));						
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelClose.setBackground(new Color(107,166,255));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.exit(0);
			}
		});
		lblX.setIcon(new ImageIcon("close.png"));
			
		
	}
}
