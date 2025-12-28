package vn.edu.hcmuaf.fit.project_ltweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;

import java.io.IOException;

@WebServlet(name = "DeleteItem", value = "/delete-item")
public class DeleteItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session =  request.getSession();
        Cart cart =  (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        cart.delItem(id);
        session.setAttribute("cart",cart);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}