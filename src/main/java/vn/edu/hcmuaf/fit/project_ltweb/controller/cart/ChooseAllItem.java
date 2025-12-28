package vn.edu.hcmuaf.fit.project_ltweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;

import java.io.IOException;

@WebServlet(name = "ChooseAllItem", value = "/choose-all-item")
public class ChooseAllItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Cart cart =  (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }else{
            cart.chooseAllItem();
        }
        session.setAttribute("cart",cart);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}