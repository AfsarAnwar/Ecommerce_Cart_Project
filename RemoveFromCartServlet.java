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
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text;charset=UTF-8");
		String id = request.getParameter("productId");
		try {
				if(id != null) {
					ArrayList<CartModel> cart_list = (ArrayList<CartModel>) request.getSession().getAttribute("cart-list");
					if(cart_list != null) {
						for(CartModel c : cart_list) {
							if(c.getProductId() == Integer.parseInt(id)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						response.sendRedirect("cart.jsp");
					}
				}else {
					response.sendRedirect("cart.jsp");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
