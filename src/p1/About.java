package p1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	 */
	public About() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(52, 140, 250));
		panel.setBounds(0, 0, 584, 126);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("AboutUs.png"));
		label.setBounds(43, 26, 50, 50);
		panel.add(label);
		
		JLabel label_1 = new JLabel("About Us");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 36));
		label_1.setBounds(114, 35, 354, 41);
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(43, 42, 50, 50);
		panel.add(label_3);
		
		JLabel label_2 = new JLabel("Created by Krishna Choudhary");
		label_2.setBackground(new Color(255, 255, 255));
		label_2.setBounds(135, 174, 278, 30);
		contentPane.add(label_2);
		label_2.setForeground(new Color(0, 102, 255));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panel_1);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panel_1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_1);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new Index().setVisible(true);
				dispose();
			}
		});
		panel_1.setLayout(null);
		panel_1.setBounds(206, 297, 100, 100);
		contentPane.add(panel_1);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("Home.png"));
		label_4.setBounds(25, 15, 50, 50);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Home");
		label_5.setForeground(new Color(0, 102, 255));
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(32, 72, 40, 19);
		panel_1.add(label_5);
	}

}
