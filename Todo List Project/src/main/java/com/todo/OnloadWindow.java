package com.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.dao.DatabaseDao;

import jakarta.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class OnloadWindow
 */
@WebServlet("/onloadWindow")
@MultipartConfig
public class OnloadWindow extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("onload");
		try {
			DatabaseDao db = new DatabaseDao();
			PrintWriter out = response.getWriter();	
			
			out.print(db.getList());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
