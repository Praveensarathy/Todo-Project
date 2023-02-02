package com.todo;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.todo.dao.DatabaseDao;
@WebServlet("/delete")
@MultipartConfig
public class DeleteElement extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res  =Integer.parseInt(request.getParameter("id"));
		System.out.println("iam res"+res);
		try {
			DatabaseDao db = new DatabaseDao();
			db.deleteList(res);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
