package com.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.entities.CartModel;
import com.ecommerce.entities.UserModel;
import com.ecommerce.helpers.ConnectionProvider;
import com.ecommerce.entities.OrderModel;
import com.ecommerce.dao.OrderDao;

/**
 * Servlet implementation class CheckCartServlet
 */
@WebServlet("/check-out-cart")
public class CheckCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			
			//Retrieving all Cart Model
			@SuppressWarnings("unchecked")
			ArrayList<CartModel> cart_list = (ArrayList<CartModel>) request.getSession().getAttribute("cart-list");
			//user authentication
			UserModel auth = (UserModel) request.getSession().getAttribute("auth");
			//check user is login or not
			if(auth != null && cart_list != null) {
				for(CartModel c : cart_list) {
					OrderModel order = new OrderModel();
					
					order.setProductId(c.getProductId());
					order.setUserId(auth.getUserId());
					order.setOrderQuantity(c.getQuantity());
					order.setOrderDate(formatter.format(date));
					OrderDao oDao = new OrderDao(ConnectionProvider.getConnection());
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				
				cart_list.clear();
				response.sendRedirect("order.jsp");
				
			}else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
