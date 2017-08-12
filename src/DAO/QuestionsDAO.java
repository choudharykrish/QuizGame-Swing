package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Questions;

public class QuestionsDAO 
{
	Connection con;
	public QuestionsDAO()
	{
		con = getConnection();
	}
	public Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","12345");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return con;
		
	}
	
	//Create
	public int save(Questions q)
	{
		int x = 0;
		try 
		{
			PreparedStatement ps = con.prepareStatement("Insert into questions values(default,?,?,?,?,?,?,'"+q.getDiffLevel()+"')");
			ps.setString(1, q.getQues());
			ps.setString(2, q.getOp1());
			ps.setString(3, q.getOp2());
			ps.setString(4, q.getOp3());
			ps.setString(5, q.getOp4());
			ps.setString(6, q.getCorrectOption());
			x = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return x;
	}
	
	//Get ID of last inserted row
	public int get_ID_OfLastInsertedRow()
	{
		int x = 0;
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			x = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return x;
	}
	
	//Read on basis of difficulty level		
	//a: all questions
	//e: easy
	//m: moderate
	//d: difficult
	public ArrayList<Questions> read(char diffLevel)
	{
		ArrayList<Questions> list = new ArrayList<>();
		
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs;
			if(diffLevel=='a') // all questions
				rs = st.executeQuery("Select * from questions order by rand()");
			else
				rs = st.executeQuery("Select * from questions where diffLevel = '"+diffLevel+"' order by rand()");
			while(rs.next())
			{
				Questions temp = new Questions();
				temp.setQid(rs.getInt(1));
				temp.setQues(rs.getString(2));
				temp.setOp1(rs.getString(3));
				temp.setOp2(rs.getString(4));
				temp.setOp3(rs.getString(5));
				temp.setOp4(rs.getString(6));
				temp.setCorrectOption(rs.getString(7));
				temp.setDiffLevel(rs.getString(8).charAt(0));
				list.add(temp);
				//temp.display();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//System.out.println("Returning List");
		return list;
	}
	
	//Update
	public int update(Questions u)
	{
		int x=0;
		try 
		{
			PreparedStatement ps = con.prepareStatement("update questions set question = ?, op1 = ?,op2 = ?,op3 = ?,op4 = ?,correctOption = ?,diffLevel = '"+u.getDiffLevel()+"' where ID = ?");
			ps.setString(1, u.getQues());
			ps.setString(2, u.getOp1());
			ps.setString(3, u.getOp2());
			ps.setString(4, u.getOp3());
			ps.setString(5, u.getOp4());
			ps.setString(6, u.getCorrectOption());
			ps.setInt(7, u.getQid());
			x = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return x;
	}
	
	//Delete
	public int delete(Questions u)
	{
		int x=0;
		try 
		{
			Statement st = con.createStatement();
			x = st.executeUpdate("delete from questions where id='"+u.getQid()+"'");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return x;
	}
}