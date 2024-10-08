package com.ecommerce.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth") !=null) {
			request.getSession().removeAttribute("auth");
			request.getSession().removeAttribute("name");
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}

	
}
