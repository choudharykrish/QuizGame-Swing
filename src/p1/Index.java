package p1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Index extends JFrame
{
	private JPanel contentPane;
	private int xx,xy,x,y;
	static private boolean isFirstTime;

	static
	{
		isFirstTime = true;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	
	public Index(int x, int y)
	{
		this();
		this.x = x;
		this.y = y;
		setLocation(x, y);
	}
	
	public Index() 
	{
		if(isFirstTime)
		{
			isFirstTime = false;
			x=100;
			y=100;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 600, 500);
		setResizable(false);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelBlueHead = new JPanel();
		panelBlueHead.setBackground(new Color(52, 140, 250));
		panelBlueHead.setBounds(0, 0, 600, 137);
		
		//Adding Drag and positioning functionality
		panelBlueHead.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getXOnScreen()-xx;				//getXOnScreen: absolute X of mouse
				y = e.getYOnScreen()-xy;
				setLocation(x,y);						//set location of top left corner of window as (x,y)
			}
		});
		
		panelBlueHead.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();							//getX: relative X of mouse wrt top left corner of window
				xy = e.getY();
				//System.out.println("Pressed: "+xx+" : "+xy);
			}
		});
		contentPane.add(panelBlueHead);
		panelBlueHead.setLayout(null);
		
		JLabel label_quizIcon = new JLabel("");
		label_quizIcon.setBounds(43, 26, 50, 50);
		label_quizIcon.setIcon(new ImageIcon("Quiz.png"));
		panelBlueHead.add(label_quizIcon);
		
		JLabel lblNewLabel = new JLabel("Quiz Time!");
		lblNewLabel.setBounds(118, 26, 182, 41);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 36));
		panelBlueHead.add(lblNewLabel);
		
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
		
		//Play
		JPanel panel_play = new JPanel();
		panel_play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				setColor(panel_play);
			}
			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				resetColor(panel_play);
			}
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				setLightColor(panel_play);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				new StartGame(x,y);						//set visible true done in constructor
														//Setting visible true after dispose(in constructor) was again making that frame
				dispose();
			}
		});
		panel_play.setBounds(132, 180, 100, 100);
		contentPane.add(panel_play);
		panel_play.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("Play.png"));
		label.setBounds(22, 11, 50, 50);
		panel_play.add(label);
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlay.setForeground(new Color(0, 102, 255));
		lblPlay.setBounds(32, 72, 40, 19);
		panel_play.add(lblPlay);
		
		//Admin Login
		JPanel panel_AdminLogin = new JPanel();
		panel_AdminLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panel_AdminLogin);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(panel_AdminLogin);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new Admin_Login(x,y).setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_AdminLogin);
			}
		});
		panel_AdminLogin.setLayout(null);
		panel_AdminLogin.setBounds(338, 180, 100, 100);
		contentPane.add(panel_AdminLogin);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("AdminLogin.png"));
		label_1.setBounds(22, 11, 50, 50);
		panel_AdminLogin.add(label_1);
		
		JLabel label_2 = new JLabel("Admin Login");
		label_2.setForeground(new Color(0, 102, 255));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 72, 82, 19);
		panel_AdminLogin.add(label_2);
		
		//About
		JPanel panel_About = new JPanel();
		panel_About.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				setColor(panel_About);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(panel_About);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new About(x,y).setVisible(true);
				dispose();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_About);
			}
		});
		panel_About.setLayout(null);
		panel_About.setBounds(132, 316, 100, 100);
		contentPane.add(panel_About);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(24, 11, 50, 50);
		label_3.setIcon(new ImageIcon("About.png"));
		panel_About.add(label_3);
		
		JLabel label_4 = new JLabel("About");
		label_4.setForeground(new Color(0, 102, 255));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(32, 72, 60, 19);
		panel_About.add(label_4);
		
		//Exit
		JPanel panel_Exit = new JPanel();
		panel_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panel_Exit);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(panel_Exit);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panel_Exit);
			}
		});
		panel_Exit.setLayout(null);
		panel_Exit.setBounds(338, 316, 100, 100);
		contentPane.add(panel_Exit);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon("Exit.png"));
		label_5.setBounds(20, 11, 50, 50);
		panel_Exit.add(label_5);
		
		JLabel label_6 = new JLabel("Exit");
		label_6.setForeground(new Color(0, 102, 255));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(32, 72, 50, 19);
		panel_Exit.add(label_6);
	}
}
