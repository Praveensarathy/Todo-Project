package com.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.todo.dao.DatabaseDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/todoform")
@MultipartConfig
public class TodoServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String input = req.getParameter("todo");
		PrintWriter out = res.getWriter();	
		DatabaseDao db;
		try {
			db = new DatabaseDao();
			
			out.print(db.addList(input));
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}