package com.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class DatabaseDao {
	private static String URL = "jdbc:mysql://localhost:3306/todoList";
	private static String user = "root";
	private static String pass = "root";
	private Connection con;
	
	public DatabaseDao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(URL,user,pass);
	}
	
	public int  addList(String input) throws SQLException, ClassNotFoundException {
		int id =0;
		String query = "insert into list(todo) values(?)";
		System.out.println( "na dao input " + input);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, input);
		st.executeUpdate();
		return getJson();
	}
	
	public int getJson() throws SQLException {
		String query = "select id from list order by id desc limit 1";
		int id=0;
		PreparedStatement st = con.prepareStatement(query);
		ResultSet res = st.executeQuery();
		if(res.next()) {
			id = res.getInt(1);
		}
		return id;
	}
	
	public JSONObject getList() throws SQLException, ClassNotFoundException {
		System.out.println("dao");
		String query = "select * from list";
		PreparedStatement st = con.prepareStatement(query);
		ResultSet res = st.executeQuery();
		JSONObject json = new JSONObject();
		while(res.next()) {
			JSONObject temp = new JSONObject();
			temp.put("id", res.getInt(1));
			temp.put("todo", res.getString(2));
			temp.put("check", res.getBoolean(3));
			json.put(res.getInt(1), temp);
		}
		return json;
	}
	
	public void deleteList(int key) throws SQLException {
		String query = "delete from list where id = ?; ";
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, key);
		st.executeUpdate();
	}
	
	public int checkList(int key) throws SQLException {
		
		String query = "update list set checkbox = ? where id = ?; ";
		PreparedStatement st = con.prepareStatement(query);
		int check = findcheck(key);
		st.setInt(1, check);
		st.setInt(2, key);
		st.executeUpdate();
		System.out.println(check);
		return check;
	}

	private int findcheck(int key) throws SQLException {
		String query = "select checkbox from list where id = ?; ";
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, key);
		
		ResultSet res = st.executeQuery();
		res.next();
		int bit = res.getInt(1);
		System.out.println(bit);
		if (bit==1)
			return 0;
		else {
			return 1;
		}
	}	
	
		
}