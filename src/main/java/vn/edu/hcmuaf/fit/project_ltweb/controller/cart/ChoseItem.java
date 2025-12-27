package vn.edu.hcmuaf.fit.project_ltweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.cart.CartItem;

import java.io.IOException;

@WebServlet(name = "ChoseItem", value = "/chose-item")
public class ChoseItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
        HttpSession session =  request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }
        CartItem ci = cart.getItem(id);
        if(ci != null){
            ci.setIsChose(!ci.isIsChose());
            cart.checkIsChoseAll();
        }
        request.setAttribute("cart", cart);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}