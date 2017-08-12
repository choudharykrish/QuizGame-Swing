package p1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartGame extends JFrame {

	private JPanel contentPane;
	private final String QUIT_CONFORMATION_MESSAGE = "Do you want to quit the game?";
	private final String QUIT_CONFORMATION_TITLE = "Confirmation";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGame frame = new StartGame();
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
	public StartGame() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 int reply = JOptionPane.showConfirmDialog(null,QUIT_CONFORMATION_MESSAGE, QUIT_CONFORMATION_TITLE, JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) 
			        {
			          dispose();
			          Index i = new Index();
			          i.setVisible(true);
			        }
			        else 
			        {
			           
			        }
			}
		});
		btnQuit.setBounds(480, 13, 89, 23);
		contentPane.add(btnQuit);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBounds(30, 13, 89, 23);
		contentPane.add(btnRestart);
		
		JLabel lblCorrectQuestions = new JLabel("Correct Questions: ");
		lblCorrectQuestions.setBounds(215, 15, 108, 19);
		contentPane.add(lblCorrectQuestions);
		
		JLabel lblScore = new JLabel("Score: ");
		lblScore.setBounds(277, 45, 46, 14);
		contentPane.add(lblScore);
		
		JLabel label = new JLabel("CQ");
		label.setBounds(317, 17, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("S");
		label_1.setBounds(317, 45, 46, 14);
		contentPane.add(label_1);
		
		JLabel lblQno = new JLabel("Q.No.");
		lblQno.setBounds(59, 191, 52, 29);
		contentPane.add(lblQno);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(121, 182, 389, 46);
		contentPane.add(lblQuestion);
		
		JButton btnOp = new JButton("Op1");
		btnOp.setBounds(132, 256, 89, 23);
		contentPane.add(btnOp);
		
		JButton button = new JButton("Op2");
		button.setBounds(384, 256, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Op3");
		button_1.setBounds(132, 313, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Op4");
		button_2.setBounds(384, 313, 89, 23);
		contentPane.add(button_2);
		
		JLabel label_2 = new JLabel("A:");
		label_2.setBounds(106, 265, 17, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("B:");
		label_3.setBounds(357, 265, 17, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("D:");
		label_4.setBounds(357, 322, 17, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("C:");
		label_5.setBounds(106, 322, 17, 14);
		contentPane.add(label_5);
	}
}
