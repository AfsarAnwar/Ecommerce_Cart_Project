package com.ecommerce.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.dao.UserDao;
import com.ecommerce.entities.UserModel;
import com.ecommerce.helpers.ConnectionProvider;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
    public LoginServlet() {
       
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("user-email");
		String password = request.getParameter("user-password");
		if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
			response.sendRedirect("login.jsp?error = emptyField");
		}
		
		try {
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			UserModel user = userDao.userLogin(email, password);
			if(user != null) {
				request.getSession().setAttribute("auth", user);
				request.getSession().setAttribute("name", user.getName());
				response.sendRedirect("index.jsp");
				
			}else {
				
				response.sendRedirect("login.jsp");
			}
			
				
				 
			 
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
