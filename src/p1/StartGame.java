package p1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;

import DAO.QuestionsDAO;
import DTO.Questions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class StartGame extends JFrame {

	private JPanel contentPane;
	private final String QUIT_CONFORMATION_MESSAGE = "Do you want to quit the game?";
	private final String QUIT_CONFORMATION_TITLE = "Confirmation";
	static private ArrayList<Questions> list;
	private static boolean isNewSession;
	private static int index,correctAns, incorrectAns,maxIndex,qNo;
	private QuestionsDAO qdao;
	private JButton op1Button,op2Button,op3Button,op4Button;
	
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
	
	
	public void validate(JButton b)
	{
		//Correct Answer
		if(b.getText().equals(list.get(index).getCorrectOption()))
		{
			correctAns++;
			JOptionPane.showMessageDialog(null, "Correct Answer!");
			if(index<maxIndex-1)		//upto second last question
			{
				qNo++;
				index++;
				new StartGame().setVisible(true);
				dispose();
			}
			else if(index==maxIndex-1)		//last question
			{
				JOptionPane.showMessageDialog(null, "Quiz Complete!\nCorrect Answers: "+correctAns+"\nIncorrect Answers: "+incorrectAns);
				index = 0;
				correctAns = 0;
				incorrectAns = 0;
				qNo = 1;
				new Index().setVisible(true);
				dispose();
			}
		}
		
		//Incorrect Answer
		else
		{
			incorrectAns++;
			qNo++;
			index++;
			JOptionPane.showMessageDialog(null, "Incorrect Answer! Corect Answer is: "+list.get(index).getCorrectOption());
			new StartGame().setVisible(true);
			dispose();
		}
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
			list.addAll(qdao.read('e'));
			list.addAll(qdao.read('m'));
			list.addAll(qdao.read('d'));
			maxIndex = list.size();
			int i=0;
			while(i<maxIndex)
			{
				list.get(i++).display();
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				 int reply = JOptionPane.showConfirmDialog(null,QUIT_CONFORMATION_MESSAGE, QUIT_CONFORMATION_TITLE, JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) 
			        {
			          new Index().setVisible(true);
			          dispose();
			        }
			}
		});
		btnQuit.setBounds(480, 13, 89, 23);
		contentPane.add(btnQuit);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				isNewSession = true;
				new StartGame().setVisible(true);
				dispose();
			}
		});
		btnRestart.setBounds(30, 13, 89, 23);
		contentPane.add(btnRestart);
		
		JLabel lblCorrectQuestions = new JLabel("Correct Answers    :  ");
		lblCorrectQuestions.setBounds(193, 15, 129, 19);
		contentPane.add(lblCorrectQuestions);
		
		JLabel lblIncorrectQues = new JLabel("Incorrect Answers :");
		lblIncorrectQues.setBounds(193, 45, 129, 14);
		contentPane.add(lblIncorrectQues);
		
		JLabel correctAnswersLabel = new JLabel(correctAns+"");
		correctAnswersLabel.setBounds(332, 17, 17, 14);
		contentPane.add(correctAnswersLabel);
		
		JLabel incorrectAnswersLabel = new JLabel(incorrectAns+"");
		incorrectAnswersLabel.setBounds(332, 45, 17, 14);
		contentPane.add(incorrectAnswersLabel);
		
		JLabel lblQno = new JLabel(qNo+"");
		lblQno.setBounds(59, 191, 52, 29);
		contentPane.add(lblQno);
		
		//Question
		JLabel lblQuestion = new JLabel(list.get(index).getQues());
		lblQuestion.setBounds(121, 182, 389, 46);
		contentPane.add(lblQuestion);
		
		op1Button = new JButton(list.get(index).getOp1());
		op1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				validate(op1Button);
			}
		});
		op1Button.setBounds(132, 256, 89, 23);
		contentPane.add(op1Button);
		
		op2Button = new JButton(list.get(index).getOp2());
		op2Button.setBounds(384, 256, 89, 23);
		op2Button.addActionListener(e ->
		{
			validate(op2Button);
		});
		contentPane.add(op2Button);
		
		op3Button = new JButton(list.get(index).getOp3());
		op3Button.setBounds(132, 313, 89, 23);
		op3Button.addActionListener(e ->
		{
			validate(op3Button);
		});
		contentPane.add(op3Button);
		
		op4Button = new JButton(list.get(index).getOp4());
		op4Button.setBounds(384, 313, 89, 23);
		op4Button.addActionListener(e ->
		{
			validate(op4Button);
		});
		contentPane.add(op4Button);
		
		JLabel label_2 = new JLabel("A:");
		label_2.setBounds(106, 260, 17, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("B:");
		label_3.setBounds(357, 260, 17, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("D:");
		label_4.setBounds(357, 317, 17, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel(" C:");
		label_5.setBounds(102, 317, 17, 14);
		contentPane.add(label_5);
	}
	
}
