package vn.edu.hcmuaf.fit.project_ltweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;

@WebServlet(name = "AddCart", value = "/add-cart")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
       
        if(session.getAttribute("auth") == null) {
            
            response.sendRedirect("login");
            return;
        }
        
        int id  = Integer.parseInt(request.getParameter("id"));
        int qty  = Integer.parseInt(request.getParameter("qty"));
        ProductService ps = new ProductService();
        Product product = ps.getProduct(id);
        if(product==null){
            response.sendRedirect("home");
            return;
        }
        
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }
        cart.addItem(product,qty);
        session.setAttribute("cart",cart);
        String page = request.getParameter("page");
        response.sendRedirect(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}