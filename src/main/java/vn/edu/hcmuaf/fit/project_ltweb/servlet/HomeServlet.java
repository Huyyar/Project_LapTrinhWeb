package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.model.Slide;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;
import vn.edu.hcmuaf.fit.project_ltweb.services.SlideService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    private ProductService service = new ProductService();
    private SlideService slideService = new SlideService();
    private int PAGE_SIZE = 6;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang chủ");
        info.setName("home");
        info.setContent("/WEB-INF/views/userpages/home.jsp");
        info.setCss(new String[]{
                "user/home.css",
                "product.css",
                "slideshow.css",
                "pagination.css"
        });
        info.setJs(new String[]{
                "home.js",
                "slideshow.js",
                "wishlist.js"
        });
        request.setAttribute("info",info);
        int totalProduct, totalPage;
        totalProduct = service.getTotalFeaturedProduct();
        totalPage = (int) Math.ceil((double) totalProduct / PAGE_SIZE);
        request.setAttribute("totalPage", totalPage);
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (currentPage > totalPage || totalPage < 0) {
            currentPage = 1;
        }
        request.setAttribute("currentPage", currentPage);
        int offset = (currentPage - 1) * PAGE_SIZE;
        List<Product> products = service.getFeaturedProducts(offset, PAGE_SIZE);
        request.setAttribute("products", products);
        
        // Get active slides for homepage slideshow
        List<Slide> slides = slideService.getActiveSlides();
        request.setAttribute("slides", slides);

        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}