package vn.edu.hcmuaf.fit.project_ltweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReOrder", value = "/re-order")
public class ReOrder extends HttpServlet {
    OrderService service = new OrderService();
    ProductService pService =  new ProductService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("orderId"));
        List<OrderItem> items = service.getOrderItems(id);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }
        for(OrderItem item:items){
            Product p = pService.getProduct(item.getProduct_id());
            cart.addItem(p,item.getQuantity());
        }
        session.setAttribute("cart",cart);
        response.sendRedirect("cart");
    }
}