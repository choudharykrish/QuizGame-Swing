package p1;

import java.awt.EventQueue;

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
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class Admin_Home extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup_diffLevel = new ButtonGroup();
	private final ButtonGroup buttonGroup_options = new ButtonGroup();
	static private QuestionsDAO qdao;
	static private boolean add;
	static private boolean isNewSession;
	static private boolean isOptionSelected;
	static private boolean isDiffLevelSelected;
	static private ArrayList<Questions> questionList;
	static private boolean isQuestionValid;
	static private int index;
	static private int maxIndex;
	private String correctOption;
	private char diffLevel;
	private Questions tempForUpdate;
	static private Questions tempForAdd;
	static private int qNumber;
	
	private JRadioButton radioA,radioB,radioC,radioD;
	private JRadioButton radioEasy,radioModerate,radioDifficult;
	private JTextField textField_op1,textField_op2,textField_op3,textField_op4;

	static
	{
		index = 0;
		qNumber = 1;
		qdao = new QuestionsDAO();
		isNewSession = true;
		add = false;
		isOptionSelected = false;
		isDiffLevelSelected = false;
		isQuestionValid = true;
	}
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
			isOptionSelected = true;
		}
		else if(radioB.isSelected())
		{
			correctOption = textField_op2.getText();
			isOptionSelected = true;
		}
		else if(radioC.isSelected())
		{
			correctOption = textField_op3.getText();
			isOptionSelected = true;
		}
		else if(radioD.isSelected())
		{
			correctOption = textField_op4.getText();
			isOptionSelected = true;
		}
		else
		{
			//System.out.println("Correct Option not Selected");
			if(isQuestionValid)
				JOptionPane.showMessageDialog(null, "Please select correct option!");
			isQuestionValid = false;
			isOptionSelected = false;
		}
	}
	
	//Function to fetch difficulty level
	private void fetchDifficultyLevel()
	{
		if(radioEasy.isSelected())
		{
			diffLevel = 'e';
			isDiffLevelSelected = true;
		}
		else if(radioModerate.isSelected())
		{
			diffLevel = 'm';
			isDiffLevelSelected = true;
		}
		else if(radioDifficult.isSelected())
		{
			diffLevel = 'd';
			isDiffLevelSelected = true;
		}
		else
		{
			//System.out.println("Difficulty Level not selected");
			if(isQuestionValid)
				JOptionPane.showMessageDialog(null, "Please select difficulty level!");
			isQuestionValid = false;
			isDiffLevelSelected = false;
		}
	}

	/**
	 * Create the frame.
	 */
	public Admin_Home() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//System.out.println("Constructor");
		
		//Home Button
		JButton btnBack = new JButton("Home");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				isNewSession = true;
				index = 0;
				qNumber = 1;
				add = false;
				new Index().setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(22, 22, 107, 45);
		contentPane.add(btnBack);
		
		//Change ID Button
		JButton button = new JButton("Change ID/Password");
		button.setBounds(422, 22, 150, 45);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isNewSession = true;
				index = 0;
				qNumber = 1;
				add = false;
				new AdminChangeID().setVisible(true);
				dispose();
			}
		});
		contentPane.add(button);
		
		//Fetching all questions
		if(isNewSession)
		{
			questionList = new ArrayList<>();
			questionList.addAll(qdao.read('e'));
			questionList.addAll(qdao.read('m'));
			questionList.addAll(qdao.read('d'));
			isNewSession = false;
			maxIndex = questionList.size();
		}		
	
		//Q. No. Label
		JLabel label_Q_No = new JLabel();
		label_Q_No.setBounds(45, 149, 52, 29);
		if(!add)
			label_Q_No.setText(qNumber+"");
		else
			label_Q_No.setText((qNumber+1)+"");
		contentPane.add(label_Q_No);
		
		//Question 
		JTextField question_text = new JTextField();
		question_text.setBounds(107, 123, 389, 63);
		if(!add&&index<maxIndex)
			question_text.setText(questionList.get(index).getQues());
		else if(add)
			question_text.setText(tempForAdd.getQues());
		contentPane.add(question_text);
		
		textField_op1 = new JTextField();
		textField_op1.setBounds(118, 214, 100, 35);
		if(!add&&index<maxIndex)
			textField_op1.setText(questionList.get(index).getOp1());
		else if(add)
			textField_op1.setText(tempForAdd.getOp1());
		contentPane.add(textField_op1);
		
		textField_op2 = new JTextField();
		textField_op2.setBounds(370, 214, 100, 35);
		if(!add&&index<maxIndex)
			textField_op2.setText(questionList.get(index).getOp2());
		else if(add)
			textField_op2.setText(tempForAdd.getOp2());
		contentPane.add(textField_op2);
		
		textField_op3 = new JTextField();
		textField_op3.setBounds(118, 271, 100, 35);
		if(!add&&index<maxIndex)
			textField_op3.setText(questionList.get(index).getOp3());
		else if(add)
			textField_op3.setText(tempForAdd.getOp3());
		contentPane.add(textField_op3);
		
		textField_op4 = new JTextField();
		textField_op4.setBounds(370, 271, 100, 35);
		if(!add&&index<maxIndex)
			textField_op4.setText(questionList.get(index).getOp4());
		else if(add)
			textField_op4.setText(tempForAdd.getOp4());
		contentPane.add(textField_op4);
		
		
		JLabel lblDifficultyLevel = new JLabel("Difficulty Level:");
		lblDifficultyLevel.setBounds(118, 327, 105, 14);
		contentPane.add(lblDifficultyLevel);
		
		radioEasy = new JRadioButton("Easy");
		buttonGroup_diffLevel.add(radioEasy);
		radioEasy.setBounds(211, 323, 52, 23);
		
		radioModerate = new JRadioButton("Moderate");
		buttonGroup_diffLevel.add(radioModerate);
		radioModerate.setBounds(276, 323, 81, 23);
		
		radioDifficult = new JRadioButton("Difficult");
		buttonGroup_diffLevel.add(radioDifficult);
		radioDifficult.setBounds(359, 323, 87, 23);
		
		if(!add&&index<maxIndex)
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
		else if(add&&isDiffLevelSelected)
			switch(tempForAdd.getDiffLevel())
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
									
		//Delete (bin) Button
		if(!add)
		{
			JButton btnBin = new JButton();
														//action listener by lambda expression
			btnBin.addActionListener(e ->
			{
				if(qdao.delete(questionList.get(index))>0)
				{
					JOptionPane.showMessageDialog(null, "Question Deleted Successfully");
					questionList.remove(index);
					qNumber--;
					index--;
					maxIndex--;
					new Admin_Home().setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Unexpected Error Occured while deleting the question");
				}
			});
			btnBin.setBounds(501, 123, 57, 63);
			btnBin.setIcon(new ImageIcon("bin.png"));
			contentPane.add(btnBin);
		}
		radioA = new JRadioButton("A:");
		buttonGroup_options.add(radioA);
		radioA.setBounds(75, 220, 37, 23);
		
		radioB = new JRadioButton("B:");
		buttonGroup_options.add(radioB);
		radioB.setBounds(327, 220, 37, 23);
						
		radioC = new JRadioButton("C:");
		buttonGroup_options.add(radioC);
		radioC.setBounds(75, 277, 37, 23);
						
		radioD = new JRadioButton("D:");
		buttonGroup_options.add(radioD);
		radioD.setBounds(327, 277, 37, 23);
		
		if(!add&&index<maxIndex)
		{
			if(questionList.get(index).getCorrectOption().equals(textField_op1.getText()))
				radioA.setSelected(true);
			else if(questionList.get(index).getCorrectOption().equals(textField_op2.getText()))
				radioB.setSelected(true);
			else if(questionList.get(index).getCorrectOption().equals(textField_op3.getText()))
				radioC.setSelected(true);
			else if(questionList.get(index).getCorrectOption().equals(textField_op4.getText()))
				radioD.setSelected(true);
		}
		else if(add&&isOptionSelected)
		{
			if(tempForAdd.getCorrectOption().equals(textField_op1.getText()))
				radioA.setSelected(true);
			else if(tempForAdd.getCorrectOption().equals(textField_op2.getText()))
				radioB.setSelected(true);
			else if(tempForAdd.getCorrectOption().equals(textField_op3.getText()))
				radioC.setSelected(true);
			else if(tempForAdd.getCorrectOption().equals(textField_op4.getText()))
				radioD.setSelected(true);
		}
		
		contentPane.add(radioA);
		contentPane.add(radioB);
		contentPane.add(radioC);
		contentPane.add(radioD);
		
		if(add)
		{
			JLabel lblAddNewQuestion = new JLabel("Add New Question!");
			lblAddNewQuestion.setForeground(new Color(204, 0, 0));
			lblAddNewQuestion.setFont(new Font("Century Gothic", Font.BOLD, 18));
			lblAddNewQuestion.setBounds(190, 50, 185, 35);
			contentPane.add(lblAddNewQuestion);
		}
		
		if(index<maxIndex&&!add)
		{
			JButton button_Save = new JButton("Update");
			button_Save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					isQuestionValid = true;
					//fetching correct option
					fetchCorrectOption();
					
					//fetching difficulty level
					fetchDifficultyLevel();
					
					if(isQuestionValid)
					{
						//saving in database
						tempForUpdate = questionList.get(index);
						tempForUpdate.setCorrectOption(correctOption);
						tempForUpdate.setDiffLevel(diffLevel);
						tempForUpdate.setQues(question_text.getText());
						tempForUpdate.setOp1(textField_op1.getText());
						tempForUpdate.setOp2(textField_op2.getText());
						tempForUpdate.setOp3(textField_op3.getText());
						tempForUpdate.setOp4(textField_op4.getText());
						if(qdao.update(tempForUpdate)>0)
						{
							JOptionPane.showMessageDialog(null, "Question Updated Successfully");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Unexpected Error Occured while saving the question");
						}
					}
				}
			});
			button_Save.setBounds(247, 386, 107, 45);
			contentPane.add(button_Save);
		}		
		
		if(index>0&&!add)
		{
			JButton button_prev = new JButton("Previous");
			button_prev.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(!add)
					{
						index--;
						qNumber--;
					}
					add = false;
					
					new Admin_Home().setVisible(true);
					dispose();
				}
			});
			button_prev.setBounds(40, 386, 107, 45);
			contentPane.add(button_prev);
		}
		else if(add)
		{
			JButton button_back = new JButton("Back");
			button_back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					add = false;
					
					new Admin_Home().setVisible(true);
					dispose();
				}
			});
			button_back.setBounds(40, 386, 107, 45);
			contentPane.add(button_back);
		}
		
		if(index<maxIndex-1)
		{
			JButton button_next = new JButton("Next");
			button_next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					qNumber++;
					index++;
					
					new Admin_Home().setVisible(true);
					dispose();
				}
			});
			button_next.setBounds(446, 386, 107, 45);
			contentPane.add(button_next);
		}
		else if(!add&&index==maxIndex-1)		//Add more question Button
		{
			JButton button_addNew = new JButton("Add new Question");
			button_addNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					tempForAdd = new Questions();
					add = true;
					isOptionSelected = false;
					isDiffLevelSelected = false;
					
					new Admin_Home().setVisible(true);
					dispose();
				}
			});
			button_addNew.setBounds(446, 386, 107, 45);
			contentPane.add(button_addNew);
		}
		else		//Add the question to database wala button
		{
			JButton button_add = new JButton("Add");
			button_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					isQuestionValid = true;
					if(question_text.getText().trim().isEmpty()&&isQuestionValid)		//trim removes leading and trailing white spaces
					{
						
						JOptionPane.showMessageDialog(null, "Question cannot be blank");
						isQuestionValid = false;
					}
					else
						tempForAdd.setQues(question_text.getText());
					if(isQuestionValid&&(textField_op1.getText().trim().isEmpty()||textField_op2.getText().trim().isEmpty()||textField_op3.getText().trim().isEmpty()||textField_op4.getText().trim().isEmpty()))
					{
						JOptionPane.showMessageDialog(null, "Options cannot be blank");
						isQuestionValid = false;
					}
					else
					{
						tempForAdd.setOp1(textField_op1.getText());
						tempForAdd.setOp2(textField_op2.getText());
						tempForAdd.setOp3(textField_op3.getText());
						tempForAdd.setOp4(textField_op4.getText());
					}
					//fetching correct option
					fetchCorrectOption();
					
					//fetching difficulty level
					fetchDifficultyLevel();
					
					if(isQuestionValid)
					{
						add = false;
						
						//saving in database
						tempForAdd.setCorrectOption(correctOption);
						tempForAdd.setDiffLevel(diffLevel);
						
						if(qdao.save(tempForAdd)>0)
						{
							JOptionPane.showMessageDialog(null, "Question Added Successfully");
							
							tempForAdd.setQid(qdao.get_ID_OfLastInsertedRow());
							questionList.add(tempForAdd);
							maxIndex = questionList.size();
							
							if(index>0)
							{
								index++;
								qNumber++;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Unexpected Error Occured while adding the question");
						}						
					}					
					new Admin_Home().setVisible(true);
					dispose();
				}
			});
			button_add.setBounds(446, 386, 107, 45);
			contentPane.add(button_add);				
		}
	}
}