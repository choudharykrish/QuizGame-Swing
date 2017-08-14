package p1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.scene.control.skin.LabeledText;

import DAO.QuestionsDAO;
import DTO.Questions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class StartGame extends JFrame {

	private JPanel contentPane;
	private final String QUIT_CONFORMATION_MESSAGE = "Do you want to quit the game?";
	private final String QUIT_CONFORMATION_TITLE = "Confirmation";
	private final static int TIME_FOR_EASY = 15,TIME_FOR_MOD = 20,TIME_FOR_DIFF = 25;
	static private ArrayList<Questions> list;
	private static boolean isNewSession,isListEmpty;
	private static int index,correctAns, incorrectAns,maxIndex,qNo;
	private int time;
	private QuestionsDAO qdao;
	private JLabel op1Label,op2Label,op3Label,op4Label,labelTime;
	private JPanel panelBlueHead;
	private int xx,xy,x,y;
	private MyThread threadForTimer;
	
	private class MyThread extends Thread
	{
			public void run() 
			{
				switch(Thread.currentThread().getName())
				{
				case "e":
					time = TIME_FOR_EASY;
					startTimer();
					break;
				case "m":
					time = TIME_FOR_MOD;
					startTimer();
					break;
				case "d":
					time = TIME_FOR_DIFF;
					startTimer();
					break;					
				}
			}	
	}
	
	private void setTimeToLabel(int time)
	{
		if(time>9)
			labelTime.setText("00:"+time);
		else
			labelTime.setText("00:0"+time);
	}
	
	private void startTimer()
	{
		while(time>0&&!Thread.currentThread().isInterrupted())
		{
			setTimeToLabel(time);
			time--;
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		//Time Up
		if(time==0&&!Thread.currentThread().isInterrupted())
		{
			incorrectAns++;
			qNo++;
			JOptionPane.showMessageDialog(null, "Time Up! Corect Answer is: "+list.get(index).getCorrectOption());
			index++;
			new StartGame(x,y).setVisible(true);
			dispose();
		}		
	}
	
	static
	{
		list = new ArrayList<>();
		isNewSession = true;
		index = 0;
		correctAns = 0;
		incorrectAns = 0;
		qNo = 1;
	}
	
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
	
	public StartGame(int x, int y) 
	{
		this();
		this.x = x;
		this.y = y;
		setLocation(x, y);
		if(isListEmpty)
		{
			Index i = new Index(x, y);
			i.setVisible(true);
			dispose();
		}
	}

	public void validate(JLabel label)
	{
		threadForTimer.interrupt();
		threadForTimer.stop();
		
		//Correct Answer
		if(label.getText().equals(list.get(index).getCorrectOption()))
		{
			correctAns++;
			JOptionPane.showMessageDialog(null, "Correct Answer!");
			if(index<maxIndex-1)		//upto second last question
			{
				qNo++;
				index++;
				new StartGame(x,y).setVisible(true);
				dispose();
			}
			else if(index==maxIndex-1)		//last question
			{
				JOptionPane.showMessageDialog(null, "Quiz Complete!\nCorrect Answers: "+correctAns+"\nIncorrect Answers: "+incorrectAns);
				index = 0;
				correctAns = 0;
				incorrectAns = 0;
				isNewSession = true;
				qNo = 1;
				new Index(x,y).setVisible(true);
				dispose();
			}
		}
		
		//Incorrect Answer
		else
		{
			incorrectAns++;
			qNo++;
			JOptionPane.showMessageDialog(null, "Incorrect Answer! Corect Answer is: "+list.get(index).getCorrectOption());
									
			if(index==maxIndex-1)		//last question
			{
				JOptionPane.showMessageDialog(null, "Quiz Complete!\nCorrect Answers: "+correctAns+"\nIncorrect Answers: "+incorrectAns);
				index = 0;
				correctAns = 0;
				incorrectAns = 0;
				isNewSession = true;
				qNo = 1;
				new Index(x,y).setVisible(true);
				dispose();
			}
			else
			{
				index++;
				new StartGame(x,y).setVisible(true);
				dispose();
			}
		}
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
	public StartGame() 
	{
		if(isNewSession)
		{
			isNewSession = false;
			System.out.println("New Session");
			index = 0;
			correctAns = 0;
			incorrectAns = 0;
			qNo = 1;
			qdao = new QuestionsDAO();
			list = new ArrayList<>();
			list.addAll(qdao.read('e'));
			list.addAll(qdao.read('m'));
			list.addAll(qdao.read('d'));
			maxIndex = list.size();
			if(list.isEmpty())
			{
				isListEmpty = true;
				isNewSession = true;
				setTitle("Start game");
				JOptionPane.showMessageDialog(null, "Quiz is empty! \nContact admin to add questions!!");
				
			}
			else
			{
				isListEmpty = false;
			}
			/*int i=0;
			while(i<maxIndex)
			{
				list.get(i++).display();
			}*/
		}
		
		
//		if(!isListEmpty)
//		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 600, 500);
			setResizable(false);
			setUndecorated(true);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 255, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.inactiveCaptionBorder);
			panel_1.setBounds(26, 377, 522, 83);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblCorrectQuestions = new JLabel("Correct Answers    :  ");
			lblCorrectQuestions.setForeground(new Color(0, 102, 255));
			lblCorrectQuestions.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblCorrectQuestions.setBounds(167, 11, 129, 19);
			panel_1.add(lblCorrectQuestions);
			
			JLabel lblIncorrectQues = new JLabel("Incorrect Answers :");
			lblIncorrectQues.setForeground(new Color(0, 102, 255));
			lblIncorrectQues.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIncorrectQues.setBounds(167, 41, 129, 14);
			panel_1.add(lblIncorrectQues);
			
			JLabel correctAnswersLabel = new JLabel(correctAns+"");
			correctAnswersLabel.setForeground(new Color(0, 102, 255));
			correctAnswersLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			correctAnswersLabel.setBounds(306, 13, 17, 14);
			panel_1.add(correctAnswersLabel);
			
			JLabel incorrectAnswersLabel = new JLabel(incorrectAns+"");
			incorrectAnswersLabel.setForeground(new Color(0, 102, 255));
			incorrectAnswersLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			incorrectAnswersLabel.setBounds(306, 41, 17, 14);
			panel_1.add(incorrectAnswersLabel);
			
			JPanel panelRestart = new JPanel();
			panelRestart.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelRestart);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelRestart);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelRestart);
				}
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					threadForTimer.interrupt();
					threadForTimer.stop();
					isNewSession = true;
					new StartGame(x,y).setVisible(true);
					dispose();
				}
			});
			panelRestart.setBounds(20, 9, 65, 65);
			panel_1.add(panelRestart);
			panelRestart.setLayout(null);
			
			JLabel labelRestart = new JLabel("");
			labelRestart.setIcon(new ImageIcon("Restart.png"));
			labelRestart.setBounds(17, 11, 30, 30);
			panelRestart.add(labelRestart);
			
			JLabel label_1 = new JLabel("Restart");
			label_1.setForeground(new Color(0, 102, 255));
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_1.setBounds(13, 45, 43, 14);
			panelRestart.add(label_1);
			
			JPanel panelQuit = new JPanel();
			panelQuit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelQuit);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelQuit);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelQuit);
				}
				@Override
				public void mouseClicked(MouseEvent e) 
				{
	/*				try 
					{
						synchronized (threadForTimer) 
						{
							threadForTimer.wait();
	*/						int reply = JOptionPane.showConfirmDialog(null,QUIT_CONFORMATION_MESSAGE, QUIT_CONFORMATION_TITLE, JOptionPane.YES_NO_OPTION);
					        if (reply == JOptionPane.YES_OPTION) 
					        {
					        	threadForTimer.interrupt();
					        	threadForTimer.stop();
					        	isNewSession = true;
					        	new Index(x,y).setVisible(true);
					        	dispose();
					        }
	/*				        else
					        {
					        	threadForTimer.notify();
					        }
						}
					} 
					catch (InterruptedException e1) 
					{
						e1.printStackTrace();
					}					
	*/			}
			});
			panelQuit.setBounds(436, 9, 65, 65);
			panel_1.add(panelQuit);
			panelQuit.setLayout(null);
			
			JLabel labelQuit = new JLabel("");
			labelQuit.setIcon(new ImageIcon("Quit.png"));
			labelQuit.setBounds(14, 11, 30, 30);
			panelQuit.add(labelQuit);
			
			JLabel label_7 = new JLabel("   Quit");
			label_7.setForeground(new Color(0, 102, 255));
			label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_7.setBounds(10, 45, 43, 14);
			panelQuit.add(label_7);
			
			panelBlueHead = new JPanel();
			panelBlueHead.setBackground(new Color(52, 140, 250));
			panelBlueHead.setBounds(0, 0, 600, 120);
			contentPane.add(panelBlueHead);
			panelBlueHead.setLayout(null);
			
			JLabel lblQno = new JLabel(qNo+"");
			lblQno.setForeground(new Color(255, 255, 255));
			lblQno.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblQno.setBounds(40, 40, 32, 29);
			panelBlueHead.add(lblQno);
			
			//Timer Label
			labelTime = new JLabel("00:00");
			labelTime.setForeground(Color.WHITE);
			labelTime.setFont(new Font("Tahoma", Font.BOLD, 18));
			labelTime.setBounds(540, 88, 66, 29);
			panelBlueHead.add(labelTime);
			
			
			//Starting Thread for timer
			threadForTimer = new MyThread();
			threadForTimer.setName(list.get(index).getDiffLevel()+"");
			threadForTimer.setDaemon(true);
			threadForTimer.start();
			
			//Question
			JLabel lblQuestion = new JLabel(list.get(index).getQues());
			lblQuestion.setForeground(new Color(255, 255, 255));
			lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblQuestion.setBounds(82, 20, 474, 68);
			panelBlueHead.add(lblQuestion);
			
			JPanel panel_op1 = new JPanel();
			panel_op1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setColor(panel_op1);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panel_op1);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panel_op1);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					validate(op1Label);
				}
			});
			panel_op1.setBounds(84, 145, 150, 90);
			contentPane.add(panel_op1);
			panel_op1.setLayout(null);
			
			op1Label = new JLabel(list.get(index).getOp1());
			op1Label.setForeground(new Color(0, 102, 255));
			op1Label.setFont(new Font("Tahoma", Font.BOLD, 12));
			op1Label.setBounds(44, 11, 96, 71);
			panel_op1.add(op1Label);
			
			JLabel label_2 = new JLabel("A:");
			label_2.setForeground(new Color(0, 102, 255));
			label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_2.setBounds(21, 39, 13, 15);
			panel_op1.add(label_2);
			
			JPanel panel_op2 = new JPanel();
			panel_op2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setColor(panel_op2);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panel_op2);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panel_op2);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					validate(op2Label);
				}
			});
			panel_op2.setLayout(null);
			panel_op2.setBounds(353, 145, 150, 90);
			contentPane.add(panel_op2);
			
			op2Label = new JLabel(list.get(index).getOp2());
			op2Label.setForeground(new Color(0, 102, 255));
			op2Label.setFont(new Font("Tahoma", Font.BOLD, 12));
			op2Label.setBounds(51, 11, 89, 78);
			panel_op2.add(op2Label);
			
			JLabel label_3 = new JLabel("B:");
			label_3.setForeground(new Color(0, 102, 255));
			label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_3.setBounds(23, 43, 17, 14);
			panel_op2.add(label_3);
			JPanel panel_op4 = new JPanel();
			panel_op4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setColor(panel_op4);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panel_op4);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panel_op4);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					validate(op4Label);
				}
			});
			panel_op4.setLayout(null);
			panel_op4.setBounds(353, 257, 150, 90);
			contentPane.add(panel_op4);
			
			op4Label = new JLabel(list.get(index).getOp4());
			op4Label.setForeground(new Color(0, 102, 255));
			op4Label.setFont(new Font("Tahoma", Font.BOLD, 12));
			op4Label.setBounds(53, 11, 87, 78);
			panel_op4.add(op4Label);
			
			JLabel label_4 = new JLabel("D:");
			label_4.setForeground(new Color(0, 102, 255));
			label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_4.setBounds(26, 43, 17, 14);
			panel_op4.add(label_4);
			
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
			
			JPanel panel_op3 = new JPanel();
			panel_op3.setBounds(84, 257, 150, 90);
			contentPane.add(panel_op3);
			panel_op3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setColor(panel_op3);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panel_op3);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panel_op3);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					validate(op3Label);
				}
			});
			panel_op3.setLayout(null);
			
			op3Label = new JLabel(list.get(index).getOp3());
			op3Label.setForeground(new Color(0, 102, 255));
			op3Label.setFont(new Font("Tahoma", Font.BOLD, 12));
			op3Label.setBounds(51, 11, 89, 78);
			panel_op3.add(op3Label);
			
			JLabel label_5 = new JLabel(" C:");
			label_5.setForeground(new Color(0, 102, 255));
			label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_5.setBounds(20, 43, 17, 14);
			panel_op3.add(label_5);
//		}
		setVisible(true);
		//System.out.println("End of default constructor");
	}
}
