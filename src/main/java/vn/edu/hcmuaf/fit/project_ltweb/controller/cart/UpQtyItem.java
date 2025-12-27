package vn.edu.hcmuaf.fit.project_ltweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.cart.CartItem;

import java.io.IOException;

@WebServlet(name = "UpQtyItem", value = "/up-qty-item")
public class UpQtyItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int qty =  Integer.parseInt(request.getParameter("qty"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }
        CartItem ci = cart.getItem(id);
        if(ci != null){
            int newQty = ci.upQty(qty);
            if(newQty <= 0){
                cart.delItem(id);
            }
        }
        session.setAttribute("cart",cart);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}