package com.ecommerce.servlet;

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.entities.*;
import com.ecommerce.helpers.ConnectionProvider;

import java.util.Date;
/**
 * Servlet implementation class BuyNowServlet
 */
@WebServlet("/buy-now")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			UserModel auth = (UserModel) request.getSession().getAttribute("auth");
			if(auth != null) {
				String id = request.getParameter("productId");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				System.out.print("id :" + id);
				System.out.print("Product Quantity :" + productQuantity );
				OrderModel order = new OrderModel();
				order.setProductId(Integer.parseInt(id));
				order.setUserId(auth.getUserId());
				order.setOrderQuantity(productQuantity);
				order.setOrderDate(formatter.format(date));
				OrderDao oDao = new OrderDao(ConnectionProvider.getConnection());
				boolean result = oDao.insertOrder(order);
				if(result) {
					 ArrayList<CartModel> cart_list = (ArrayList<CartModel>) request.getSession().getAttribute("cart-list");
	                    
	                    if(cart_list != null) {
	                        for(CartModel c : cart_list) {
	                            if(c.getProductId() == Integer.parseInt(id)) {
	                                cart_list.remove(c); // Remove the product from the cart
	                                break; // Once the product is found and removed, break the loop
	                            }
	                        }
	                        response.sendRedirect("cart.jsp");
	                    }
					response.sendRedirect("order.jsp");
					
				}else {
					out.println("order fail");
				}
				
			}else {
				response.sendRedirect("login.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
