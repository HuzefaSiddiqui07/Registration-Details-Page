package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String address = request.getParameter("address");
		String country = request.getParameter("country");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "root");
			
			PreparedStatement pStmnt = con.prepareStatement("insert into user(firstname,lastname,address,country)values(?,?,?,?)");
			
			pStmnt.setString(1, firstName);
			pStmnt.setString(2, lastName);
			pStmnt.setString(3, address);
			pStmnt.setString(4, country);
			
			int i = pStmnt.executeUpdate();
			
			if(i > 0) {
				out.print("You are Sucessfully Register...");
			}
			
		} catch (Exception e) {
			
			System.out.println(e);
		} 
	}
 }
