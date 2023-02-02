package com.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.todo.dao.DatabaseDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/check")
@MultipartConfig
public class CheckBox extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res  =Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		try {
			DatabaseDao db = new DatabaseDao();
			out.print(db.checkList(res));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
