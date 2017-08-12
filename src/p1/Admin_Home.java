package p1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.QuestionsDAO;
import DTO.Questions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class Admin_Home extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup_diffLevel = new ButtonGroup();
	private final ButtonGroup buttonGroup_options = new ButtonGroup();
	private QuestionsDAO qdao = new QuestionsDAO();
	private boolean next = true;
	private boolean add = false;
	private boolean leaveFrame = false;
	private int index = 0;
	private int maxIndex;
	private String correctOption;
	private char diffLevel;
	private Questions temp;
	private int qNumber = 1;
	
	private JRadioButton radioA;
	private JRadioButton radioB;
	private JRadioButton radioC;
	private JRadioButton radioD;
	private JRadioButton radioEasy;
	private JRadioButton radioModerate;
	private JRadioButton radioDifficult;
	private JTextField textField_op1;
	private JTextField textField_op2;
	private JTextField textField_op3;
	private JTextField textField_op4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Home frame = new Admin_Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//function to fetch correct option
	private void fetchCorrectOption()
	{
		if(radioA.isSelected())
		{
			correctOption = textField_op1.getText();
		}
		else if(radioB.isSelected())
		{
			correctOption = textField_op2.getText();
		}
		else if(radioC.isSelected())
		{
			correctOption = textField_op3.getText();
		}
		else if(radioD.isSelected())
		{
			correctOption = textField_op4.getText();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select correct option!");
		}
	}
	
	//Function to fetch difficulty level
	private void fetchDifficultyLevel()
	{
		if(radioEasy.isSelected())
		{
			diffLevel = 'e';
		}
		else if(radioModerate.isSelected())
		{
			diffLevel = 'm';
		}
		else if(radioDifficult.isSelected())
		{
			diffLevel = 'd';
		}
	}

	/**
	 * Create the frame.
	 */
	public Admin_Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		System.out.println("Constructor");
		while(!leaveFrame)
		{
			System.out.println("While loop");
			if(next)
			{
				System.out.println("While loop: if condition");
				getContentPane().removeAll();
				JButton btnBack = new JButton("Home");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						leaveFrame = true;
						new Index().setVisible(true);
						dispose();
					}
				});
				btnBack.setBounds(22, 22, 107, 45);
				contentPane.add(btnBack);
				
				JButton button = new JButton("Change ID/Password");
				button.setBounds(422, 22, 150, 45);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						leaveFrame = true;
						new AdminChangeID().setVisible(true);
						dispose();
					}
				});
				contentPane.add(button);
				
				//Fetching all questions
				ArrayList<Questions> questionList = qdao.read('a');
						
			
				next = false;
				maxIndex = questionList.size();
				
				//Q. No. Label
				JLabel label_Q_No = new JLabel();
				label_Q_No.setBounds(45, 149, 52, 29);
				if(!add)
					label_Q_No.setText(qNumber+"");
				contentPane.add(label_Q_No);
				
				//Question 
				JTextField question_text = new JTextField();
				question_text.setBounds(107, 140, 389, 46);
				if(!add)
					question_text.setText(questionList.get(index).getQues());
				contentPane.add(question_text);
				
				textField_op1 = new JTextField();
				textField_op1.setBounds(118, 214, 89, 23);
				if(!add)
					textField_op1.setText(questionList.get(index).getOp1());
				contentPane.add(textField_op1);
				
				textField_op2 = new JTextField();
				textField_op2.setBounds(370, 214, 89, 23);
				if(!add)
					textField_op2.setText(questionList.get(index).getOp2());
				contentPane.add(textField_op2);
				
				textField_op3 = new JTextField();
				textField_op3.setBounds(118, 271, 89, 23);
				if(!add)
					textField_op3.setText(questionList.get(index).getOp3());
				contentPane.add(textField_op3);
				
				textField_op4 = new JTextField();
				textField_op4.setBounds(370, 271, 89, 23);
				if(!add)
					textField_op4.setText(questionList.get(index).getOp4());
				contentPane.add(textField_op4);
							
				
				JLabel lblDifficultyLevel = new JLabel("Difficulty Level:");
				lblDifficultyLevel.setBounds(140, 318, 80, 14);
				contentPane.add(lblDifficultyLevel);
				
				JRadioButton radioEasy = new JRadioButton("Easy");
				buttonGroup_diffLevel.add(radioEasy);
				radioEasy.setBounds(226, 314, 52, 23);
				
				radioModerate = new JRadioButton("Moderate");
				buttonGroup_diffLevel.add(radioModerate);
				radioModerate.setBounds(280, 314, 74, 23);
				
				radioDifficult = new JRadioButton("Difficult");
				buttonGroup_diffLevel.add(radioDifficult);
				radioDifficult.setBounds(356, 314, 63, 23);
				
				switch(questionList.get(index).getDiffLevel())
				{
					case 'e':
						radioEasy.setSelected(true);
						break;
					case 'm':
						radioModerate.setSelected(true);
						break;
					case 'd':
						radioDifficult.setSelected(true);
						break;
				}
				
				contentPane.add(radioEasy);
				contentPane.add(radioModerate);
				contentPane.add(radioDifficult);
											
				JButton btnBin = new JButton();
				btnBin.setBounds(501, 140, 52, 46);
				btnBin.setIcon(new ImageIcon("bin.png"));
				contentPane.add(btnBin);
				
				radioA = new JRadioButton("A:");
				buttonGroup_options.add(radioA);
				radioA.setBounds(77, 214, 37, 23);
				
				radioB = new JRadioButton("B:");
				buttonGroup_options.add(radioB);
				radioB.setBounds(327, 214, 37, 23);
								
				radioC = new JRadioButton("C:");
				buttonGroup_options.add(radioC);
				radioC.setBounds(75, 271, 37, 23);
								
				radioD = new JRadioButton("D:");
				buttonGroup_options.add(radioD);
				radioD.setBounds(327, 271, 37, 23);
				
				
				if(questionList.get(index).getCorrectOption().equals(textField_op1.getText()))
					radioA.setSelected(true);
				else if(questionList.get(index).getCorrectOption().equals(textField_op2.getText()))
					radioB.setSelected(true);
				else if(questionList.get(index).getCorrectOption().equals(textField_op3.getText()))
					radioC.setSelected(true);
				else if(questionList.get(index).getCorrectOption().equals(textField_op4.getText()))
					radioD.setSelected(true);
				
				
				contentPane.add(radioA);
				contentPane.add(radioB);
				contentPane.add(radioC);
				contentPane.add(radioD);
				
				
				if(index>0)
				{
					JButton button_prev = new JButton("Previous");
					button_prev.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							next = true;
							index--;
							qNumber--;
							
							//fetching correct option
							fetchCorrectOption();
							
							//fetching difficulty level
							fetchDifficultyLevel();
							
							//saving in database
							temp = questionList.get(index);
							temp.setCorrectOption(correctOption);
							temp.setDiffLevel(diffLevel);
							temp.setQues(question_text.getText());
							temp.setOp1(textField_op1.getText());
							temp.setOp2(textField_op2.getText());
							temp.setOp3(textField_op3.getText());
							temp.setOp4(textField_op4.getText());
							qdao.update(temp);
						}
					});
					button_prev.setBounds(40, 386, 107, 45);
					contentPane.add(button_prev);
				}
				
				if(index<maxIndex)
				{
					JButton button_next = new JButton("Next");
					button_next.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							next = true;
							index++;
							qNumber++;
							
							//fetching correct option
							fetchCorrectOption();
							
							//fetching difficulty level
							fetchDifficultyLevel();
							
							//saving in database
							temp = questionList.get(index);
							temp.setCorrectOption(correctOption);
							temp.setDiffLevel(diffLevel);
							temp.setQues(question_text.getText());
							temp.setOp1(textField_op1.getText());
							temp.setOp2(textField_op2.getText());
							temp.setOp3(textField_op3.getText());
							temp.setOp4(textField_op4.getText());
							qdao.update(temp);
						}
					});
					button_next.setBounds(446, 386, 107, 45);
					contentPane.add(button_next);
				}
				else if(!add)		//Add more question Button
				{
					JButton button_addNew = new JButton("Add new Question");
					button_addNew.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							add = true;
							next = true;
							qNumber++;
						}
					});
					button_addNew.setBounds(446, 386, 107, 45);
					contentPane.add(button_addNew);
				}
				else		//Add the question to database wala button
				{
					add = false;
					JButton button_add = new JButton("Add");
					button_add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							//fetching correct option
							fetchCorrectOption();
							
							//fetching difficulty level
							fetchDifficultyLevel();
							
							//saving in database
							Questions temp = new Questions();
							temp.setCorrectOption(correctOption);
							temp.setDiffLevel(diffLevel);
							temp.setQues(question_text.getText());
							temp.setOp1(textField_op1.getText());
							temp.setOp2(textField_op2.getText());
							temp.setOp3(textField_op3.getText());
							temp.setOp4(textField_op4.getText());
							qdao.save(temp);
							temp.setQid(qdao.get_ID_OfLastInsertedRow());
							questionList.add(temp);
						}
					});
					button_add.setBounds(446, 386, 107, 45);
					contentPane.add(button_add);
					try {
						TimeUnit.MINUTES.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		}
	}
}
