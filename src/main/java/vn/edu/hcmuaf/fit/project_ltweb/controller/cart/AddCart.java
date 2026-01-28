package vn.edu.hcmuaf.fit.project_ltweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.io.PrintWriter;

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
        
        // Kiểm tra nếu là AJAX request
        String ajaxRequest = request.getParameter("ajax");
        if("true".equals(ajaxRequest)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": true, \"message\": \"Đã thêm " + qty + " sản phẩm vào giỏ hàng\", \"totalQty\": " + cart.getTotalQty() + "}");
            out.flush();
            return;
        }
        
        String page = request.getParameter("page");
        response.sendRedirect(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}