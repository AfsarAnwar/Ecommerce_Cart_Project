package com.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.entities.CartModel;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html: charset=UTF-8");
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<CartModel> cart_list = (ArrayList<CartModel>) request.getSession().getAttribute("cart-list");
		
		try(PrintWriter out = response.getWriter()) {
				if(action != null && id >= 1) {
					if(action.equals("inc")) {
						for(CartModel c : cart_list) {
							if(c.getProductId() == id) {
								int quantity = c.getQuantity();
								quantity++;
								c.setQuantity(quantity);
								response.sendRedirect("cart.jsp");
							}
						}
					}
					
					if(action.equals("dec")) {
						for(CartModel c : cart_list) {
							if(c.getProductId() == id && c.getQuantity() > 1) {
								int quantity = c.getQuantity();
								quantity--;
								c.setQuantity(quantity);
								break;
							} 
						}
						response.sendRedirect("cart.jsp");
					}
				}else {
					response.sendRedirect("cart.jsp");
				}
		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
}
