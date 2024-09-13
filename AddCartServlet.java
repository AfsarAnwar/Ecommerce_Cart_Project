package com.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import com.ecommerce.entities.CartModel;


@WebServlet("/add-to-cart")
public class AddCartServlet extends HttpServlet {
	

	private static final long serialVersionUID = -3769508656639704992L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			ArrayList<CartModel> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("productId"));
			CartModel cm = new CartModel();
			cm.setProductId(id);
			cm.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<CartModel> cart_list = (ArrayList<CartModel>) session.getAttribute("cart-list");
			if(cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
				
			}else {
				cartList = cart_list;
				boolean exist = false;
				for(CartModel c : cartList) {
					if(c.getProductId() == id) {
						exist = true;
						out.println("<h3 style= 'color:crimson; text-align:center'>Item already exist in Cart<a href='cart.jsp'> Go to the Cart Page</a></h3>");
					}
					
				}
				if(!exist) {
					cartList.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
		
		}

	}

}
