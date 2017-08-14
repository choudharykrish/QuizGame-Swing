package p1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.QuestionsDAO;
import DTO.Questions;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
	static private boolean isQuestionValid,isFirstQuestion,isTempCreated;
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
	private JPanel panelBlueHead;
	private JSeparator separator;
	private JPanel panelPrevious;
	private JLabel prevIconLabel;
	private JLabel label_1;
	private JPanel panelUpdate;
	private JLabel updateIconLabel;
	private JLabel label_3;
	private JPanel panelNext;
	private JLabel nextIconLabel;
	private JLabel label_5;
	private JPanel panelDeleteQues;
	private JLabel binIconLabel;
	private JSeparator separator_4;
	private int xx,xy,x,y;

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
		isTempCreated = false;
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
	
	
	
	public Admin_Home(int x, int y) throws HeadlessException {
		this();
		this.x = x;
		this.y = y;
		setLocation(x, y);
	}

	/**
	 * Create the frame.
	 */
	public Admin_Home() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel Blue Head
		panelBlueHead = new JPanel();
		panelBlueHead.setLayout(null);
		panelBlueHead.setBackground(new Color(52, 140, 250));
		panelBlueHead.setBounds(0, 0, 600, 120);
		contentPane.add(panelBlueHead);
		
		//Question 
		JTextField questionTextField = new JTextField();
		questionTextField.setBackground(new Color(52, 140, 250));
		questionTextField.setBorder(BorderFactory.createEmptyBorder());
		questionTextField.setBounds(93, 43, 379, 35);
		panelBlueHead.add(questionTextField);
		questionTextField.setForeground(new Color(255, 255, 255));
		questionTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		//Q. No. Label
		JLabel label_Q_No = new JLabel();		
		label_Q_No.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_Q_No.setForeground(new Color(255, 255, 255));
		label_Q_No.setBounds(45, 45, 26, 29);
		panelBlueHead.add(label_Q_No);
		
		//Separator
		separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(255, 255, 255));
		separator.setBounds(93, 78, 379, 18);
		panelBlueHead.add(separator);
		
		//Home Button
		JPanel panelHome = new JPanel();
		panelHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panelHome);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panelHome);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panelHome);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				isNewSession = true;
				index = 0;
				qNumber = 1;
				add = false;
				new Index(x,y).setVisible(true);
				dispose();
			}
		});
		panelHome.setBounds(10, 410, 100, 50);
		contentPane.add(panelHome);
		panelHome.setLayout(null);
		
		JLabel homeIconLabel = new JLabel("");
		homeIconLabel.setIcon(new ImageIcon("Home30.png"));
		homeIconLabel.setBounds(10, 11, 30, 30);
		panelHome.add(homeIconLabel);
		
		JLabel label_2 = new JLabel("Home");
		label_2.setForeground(new Color(0, 102, 255));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(47, 9, 43, 30);
		panelHome.add(label_2);
		
		//Change ID Button
		JPanel panelChangeId = new JPanel();
		panelChangeId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(panelChangeId);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(panelChangeId);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setLightColor(panelChangeId);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				isNewSession = true;
				index = 0;
				qNumber = 1;
				add = false;
				new AdminChangeID(x,y).setVisible(true);
				dispose();
			}
		});
		panelChangeId.setLayout(null);
		panelChangeId.setBounds(466, 410, 113, 50);
		contentPane.add(panelChangeId);
		
		JLabel changeIdIconLabel = new JLabel("");
		changeIdIconLabel.setIcon(new ImageIcon("ChangeID.png"));
		changeIdIconLabel.setBounds(10, 11, 30, 30);
		panelChangeId.add(changeIdIconLabel);
		
		JLabel label_6 = new JLabel("Change");
		label_6.setForeground(new Color(0, 102, 255));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(50, 5, 53, 17);
		panelChangeId.add(label_6);
		
		JLabel label_7 = new JLabel("ID/Pass");
		label_7.setForeground(new Color(0, 102, 255));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(50, 24, 63, 17);
		panelChangeId.add(label_7);
		
		//Fetching all questions
		if(isNewSession)
		{
			questionList = new ArrayList<>();
			questionList.addAll(qdao.read('e'));
			questionList.addAll(qdao.read('m'));
			questionList.addAll(qdao.read('d'));
			if(questionList.isEmpty())
			{
				isFirstQuestion = true;
				index = -1;
				add = true;
				qNumber = 0;
			}
			isNewSession = false;
			maxIndex = questionList.size();
		}		
		if(!add)
			label_Q_No.setText(qNumber+"");
		else
			label_Q_No.setText((qNumber+1)+"");
		if(!add&&index<maxIndex&&index>=0)
			questionTextField.setText(questionList.get(index).getQues());
		else if(add&&isTempCreated)
			questionTextField.setText(tempForAdd.getQues());
		
		textField_op1 = new JTextField();
		textField_op1.setForeground(new Color(0, 102, 255));
		textField_op1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_op1.setBounds(116, 156, 100, 25);
		textField_op1.setBorder(BorderFactory.createEmptyBorder());
		if(!add&&index<maxIndex&&index>=0)
			textField_op1.setText(questionList.get(index).getOp1());
		else if(add&&isTempCreated)
			textField_op1.setText(tempForAdd.getOp1());
		contentPane.add(textField_op1);
		
		textField_op2 = new JTextField();
		textField_op2.setForeground(new Color(0, 102, 255));
		textField_op2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_op2.setBounds(368, 156, 100, 25);
		textField_op2.setBorder(BorderFactory.createEmptyBorder());
		if(!add&&index<maxIndex&&index>=0)
			textField_op2.setText(questionList.get(index).getOp2());
		else if(add&&isTempCreated)
			textField_op2.setText(tempForAdd.getOp2());
		contentPane.add(textField_op2);
		
		textField_op3 = new JTextField();
		textField_op3.setForeground(new Color(0, 102, 255));
		textField_op3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_op3.setBounds(116, 213, 100, 25);
		textField_op3.setBorder(BorderFactory.createEmptyBorder());
		if(!add&&index<maxIndex&&index>=0)
			textField_op3.setText(questionList.get(index).getOp3());
		else if(add&&isTempCreated)
			textField_op3.setText(tempForAdd.getOp3());
		contentPane.add(textField_op3);
		
		textField_op4 = new JTextField();
		textField_op4.setForeground(new Color(0, 102, 255));
		textField_op4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_op4.setBounds(368, 213, 100, 25);
		textField_op4.setBorder(BorderFactory.createEmptyBorder());
		if(!add&&index<maxIndex&&index>=0)
			textField_op4.setText(questionList.get(index).getOp4());
		else if(add&&isTempCreated)
			textField_op4.setText(tempForAdd.getOp4());
		contentPane.add(textField_op4);
		
		
		JLabel lblDifficultyLevel = new JLabel("Difficulty Level:");
		lblDifficultyLevel.setForeground(new Color(0, 102, 255));
		lblDifficultyLevel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		lblDifficultyLevel.setBackground(new Color(255, 255, 255));
		lblDifficultyLevel.setBounds(103, 276, 113, 19);
		contentPane.add(lblDifficultyLevel);
		
		radioEasy = new JRadioButton("Easy");
		radioEasy.setForeground(new Color(0, 102, 255));
		radioEasy.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		radioEasy.setBackground(new Color(255, 255, 255));
		buttonGroup_diffLevel.add(radioEasy);
		radioEasy.setBounds(209, 275, 63, 21);
		
		radioModerate = new JRadioButton("Moderate");
		radioModerate.setForeground(new Color(0, 102, 255));
		radioModerate.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		radioModerate.setBackground(new Color(255, 255, 255));
		buttonGroup_diffLevel.add(radioModerate);
		radioModerate.setBounds(274, 274, 87, 23);
		
		radioDifficult = new JRadioButton("Difficult");
		radioDifficult.setForeground(new Color(0, 102, 255));
		radioDifficult.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		radioDifficult.setBackground(new Color(255, 255, 255));
		buttonGroup_diffLevel.add(radioDifficult);
		radioDifficult.setBounds(368, 274, 87, 23);
		
		if(!add&&index<maxIndex&&index>=0)
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
			panelDeleteQues = new JPanel();
			panelDeleteQues.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						panelDeleteQues.setBackground(new Color(42,100,186));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelDeleteQues.setBackground(new Color(52,140,250));						
					}
					@Override
					public void mousePressed(MouseEvent e) {
						panelDeleteQues.setBackground(new Color(107,166,255));
					}
				@Override
				public void mouseClicked(MouseEvent e) {
					if(qdao.delete(questionList.get(index))>0)
					{
						JOptionPane.showMessageDialog(null, "Question Deleted Successfully");
						questionList.remove(index);
						
						//If the removed question is first question but list is not empty
						if(!questionList.isEmpty()&&index==0)
						{
							maxIndex--;
						}
						else
						{
							qNumber--;
							index--;
							maxIndex--;
							isDiffLevelSelected = false;
							isOptionSelected = false;
							if(index==-1)
							{
								isFirstQuestion = true;
								add = true;
								isTempCreated = false;
								tempForAdd = new Questions();
							}
						}
						
						new Admin_Home(x,y).setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Unexpected Error Occured while deleting the question");
					}
				}
			});
			panelDeleteQues.setBackground(new Color(52, 140, 250));
			panelDeleteQues.setBounds(497, 43, 35, 35);
			panelBlueHead.add(panelDeleteQues);
			panelDeleteQues.setLayout(null);
			
			binIconLabel = new JLabel("");
			binIconLabel.setIcon(new ImageIcon("bin.png"));
			binIconLabel.setBounds(2, 0, 35, 35);
			panelDeleteQues.add(binIconLabel);
		}
		radioA = new JRadioButton("A:");
		radioA.setForeground(new Color(0, 102, 255));
		radioA.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioA.setBackground(new Color(255, 255, 255));
		buttonGroup_options.add(radioA);
		radioA.setBounds(58, 157, 52, 29);
		
		radioB = new JRadioButton("B:");
		radioB.setForeground(new Color(0, 102, 255));
		radioB.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioB.setBackground(new Color(255, 255, 255));
		buttonGroup_options.add(radioB);
		radioB.setBounds(310, 157, 52, 29);
						
		radioC = new JRadioButton("C:");
		radioC.setForeground(new Color(0, 102, 255));
		radioC.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioC.setBackground(new Color(255, 255, 255));
		buttonGroup_options.add(radioC);
		radioC.setBounds(58, 214, 52, 29);
						
		radioD = new JRadioButton("D:");
		radioD.setForeground(new Color(0, 102, 255));
		radioD.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioD.setBackground(new Color(255, 255, 255));
		buttonGroup_options.add(radioD);
		radioD.setBounds(310, 217, 52, 23);
		
		if(!add&&index<maxIndex&&index>=0)
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
			if(tempForAdd.getCorrectOption().equals(textField_op1.getText()))			//Null Ptr Exception. Why?
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
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 102, 255));
		separator_1.setBackground(new Color(0, 102, 255));
		separator_1.setBounds(116, 180, 100, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 102, 255));
		separator_2.setBackground(new Color(0, 102, 255));
		separator_2.setBounds(368, 180, 100, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(0, 102, 255));
		separator_3.setBackground(new Color(0, 102, 255));
		separator_3.setBounds(116, 238, 100, 1);
		contentPane.add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setForeground(new Color(0, 102, 255));
		separator_4.setBackground(new Color(0, 102, 255));
		separator_4.setBounds(368, 238, 100, 1);
		contentPane.add(separator_4);
		
		if(add)
		{
			JLabel label = new JLabel("Add New Question");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 36));
			label.setBounds(121, 11, 319, 29);
			panelBlueHead.add(label);
		}
		
		if(index<maxIndex&&!add)
		{
			panelUpdate = new JPanel();
			panelUpdate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelUpdate);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelUpdate);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelUpdate);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
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
						tempForUpdate.setQues(questionTextField.getText());
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
							JOptionPane.showMessageDialog(null, "Unexpected Error Occured while updating the question");
						}
					}
					
				}
			});
			panelUpdate.setLayout(null);
			panelUpdate.setBounds(258, 316, 65, 65);
			contentPane.add(panelUpdate);
			
			updateIconLabel = new JLabel("");
			updateIconLabel.setIcon(new ImageIcon("UpdateQues.png"));
			updateIconLabel.setBounds(17, 11, 30, 30);
			panelUpdate.add(updateIconLabel);
			
			label_3 = new JLabel("Update");
			label_3.setForeground(new Color(0, 102, 255));
			label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_3.setBounds(13, 45, 43, 14);
			panelUpdate.add(label_3);
		}		
		
		if(index>0&&!add)
		{
			panelPrevious = new JPanel();
			panelPrevious.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelPrevious);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelPrevious);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelPrevious);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					if(!add)
					{
						index--;
						qNumber--;
					}
					add = false;
					
					new Admin_Home(x,y).setVisible(true);
					dispose();
					
				}
			});
			panelPrevious.setLayout(null);
			panelPrevious.setBounds(116, 316, 65, 65);
			contentPane.add(panelPrevious);
			
			prevIconLabel = new JLabel("");
			prevIconLabel.setIcon(new ImageIcon("Previous.png"));
			prevIconLabel.setBounds(17, 11, 30, 30);
			panelPrevious.add(prevIconLabel);
			
			label_1 = new JLabel("Previous");
			label_1.setForeground(new Color(0, 102, 255));
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_1.setBounds(13, 45, 43, 14);
			panelPrevious.add(label_1);
		}
		else if(add&&!isFirstQuestion)
		{
			//Back Button (in case of adding new question)
			JPanel panelBack = new JPanel();
			panelBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelBack);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelBack);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelBack);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					add = false;
					isDiffLevelSelected = false;
					isOptionSelected = false;
					isTempCreated = false;
					
					new Admin_Home(x,y).setVisible(true);
					dispose();
					
				}
			});
			panelBack.setLayout(null);
			panelBack.setBounds(116, 316, 65, 65);
			contentPane.add(panelBack);
			
			JLabel backIconLabel = new JLabel("");
			backIconLabel.setIcon(new ImageIcon("Back.png"));
			backIconLabel.setBounds(11, 11, 30, 30);
			panelBack.add(backIconLabel);
			
			JLabel label_back = new JLabel("Back");
			label_back.setForeground(new Color(0, 102, 255));
			label_back.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_back.setBounds(20, 45, 43, 14);
			panelBack.add(label_back);
		}
		
		
		if(index<maxIndex-1)
		{
			//Next Button
			panelNext = new JPanel();
			panelNext.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelNext);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelNext);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelNext);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					qNumber++;
					index++;
					
					new Admin_Home(x,y).setVisible(true);
					dispose();
					
				}
			});
			panelNext.setLayout(null);
			panelNext.setBounds(403, 316, 65, 65);
			contentPane.add(panelNext);
			
			nextIconLabel = new JLabel("");
			nextIconLabel.setIcon(new ImageIcon("Next.png"));
			nextIconLabel.setBounds(17, 11, 30, 30);
			panelNext.add(nextIconLabel);
			
			label_5 = new JLabel("Next");
			label_5.setForeground(new Color(0, 102, 255));
			label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_5.setBounds(18, 45, 37, 14);
			panelNext.add(label_5);
		}
		else if(!add&&index==maxIndex-1)		//Add new question Button
		{
			System.out.println("Add More");
			JPanel panelAddNewQues = new JPanel();
			panelAddNewQues.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelAddNewQues);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelAddNewQues);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelAddNewQues);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					tempForAdd = new Questions();
					add = true;
					isTempCreated = true;
					isOptionSelected = false;
					isDiffLevelSelected = false;
					
					new Admin_Home(x,y).setVisible(true);
					dispose();
					
				}
			});
			panelAddNewQues.setLayout(null);
			panelAddNewQues.setBounds(403, 316, 65, 65);
			contentPane.add(panelAddNewQues);
			
			JLabel addNewQuesIconLabel = new JLabel("");
			addNewQuesIconLabel.setIcon(new ImageIcon("AddNew.png"));
			addNewQuesIconLabel.setBounds(17, 11, 30, 30);
			panelAddNewQues.add(addNewQuesIconLabel);
			
			JLabel labelAddNewQues = new JLabel("Add Ques");
			labelAddNewQues.setForeground(new Color(0, 102, 255));
			labelAddNewQues.setFont(new Font("Tahoma", Font.PLAIN, 11));
			labelAddNewQues.setBounds(11, 45, 47, 14);
			panelAddNewQues.add(labelAddNewQues);
		}
		else		//Add the question to database wala button
		{
			JPanel panelAddToDatabase = new JPanel();
			panelAddToDatabase.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setColor(panelAddToDatabase);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					resetColor(panelAddToDatabase);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setLightColor(panelAddToDatabase);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					isQuestionValid = true;
					
					
					if(questionTextField.getText().trim().isEmpty()&&isQuestionValid)		//trim removes leading and trailing white spaces
					{
						
						JOptionPane.showMessageDialog(null, "Question cannot be blank");
						isQuestionValid = false;
					}
					else 
					{
						if(isQuestionValid)
						{
							if(isFirstQuestion&&!isTempCreated)
							{
								tempForAdd = new Questions();
								System.out.println("Temp created");
								tempForAdd.setQues(questionTextField.getText());
								isTempCreated = true;
							}
							else 
								tempForAdd.setQues(questionTextField.getText());
						}
					}		
					
					
					if(isQuestionValid&&(textField_op1.getText().trim().isEmpty()||textField_op2.getText().trim().isEmpty()||textField_op3.getText().trim().isEmpty()||textField_op4.getText().trim().isEmpty()))
					{
						JOptionPane.showMessageDialog(null, "Options cannot be blank");
						isQuestionValid = false;
					}
					else if(isQuestionValid)
					{						
						if(isFirstQuestion&&!isTempCreated)
						{
							tempForAdd = new Questions();
							System.out.println("Temp created");
							tempForAdd.setOp1(textField_op1.getText());
							tempForAdd.setOp2(textField_op2.getText());
							tempForAdd.setOp3(textField_op3.getText());
							tempForAdd.setOp4(textField_op4.getText());
							isTempCreated = true;
						}
						else
						{
							tempForAdd.setOp1(textField_op1.getText());
							tempForAdd.setOp2(textField_op2.getText());
							tempForAdd.setOp3(textField_op3.getText());
							tempForAdd.setOp4(textField_op4.getText());
						}
					}
					//fetching correct option
					fetchCorrectOption();
					
					//fetching difficulty level
					fetchDifficultyLevel();
					
					System.out.println("isQuesValid before adding: "+isQuestionValid);
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
							
							index++;
							isFirstQuestion = false;
							isDiffLevelSelected = false;
							isOptionSelected = false;
							isTempCreated = false;
							add = false;
							qNumber++;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Unexpected Error Occured while adding the question");
						}
					}										
										
					new Admin_Home(x,y).setVisible(true);
					dispose();
				}
			});
			
			panelAddToDatabase.setLayout(null);
			panelAddToDatabase.setBounds(403, 316, 65, 65);
			contentPane.add(panelAddToDatabase);
			
			JLabel addToDatabaseIconLabel = new JLabel("");
			addToDatabaseIconLabel.setIcon(new ImageIcon("AddToDB.png"));
			addToDatabaseIconLabel.setBounds(17, 11, 30, 30);
			panelAddToDatabase.add(addToDatabaseIconLabel);
			
			JLabel labelAddToDatabase = new JLabel("Add");
			labelAddToDatabase.setForeground(new Color(0, 102, 255));
			labelAddToDatabase.setFont(new Font("Tahoma", Font.PLAIN, 11));
			labelAddToDatabase.setBounds(20, 45, 47, 14);
			panelAddToDatabase.add(labelAddToDatabase);
							
			
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