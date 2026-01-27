package vn.edu.hcmuaf.fit.project_ltweb.servlet;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.Slide;
import vn.edu.hcmuaf.fit.project_ltweb.services.SlideService;

@WebServlet(name = "AdminSlideServlet", value = "/admin/slideshow")
public class AdminSlideServlet extends HttpServlet {
    private SlideService slideService = new SlideService();
    private int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get pagination parameters
        int totalSlides = slideService.getTotalSlides();
        int totalPages = (int) Math.ceil((double) totalSlides / PAGE_SIZE);
        
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = 1;
        }
        
        // Get paginated slides
        int offset = (currentPage - 1) * PAGE_SIZE;
        List<Slide> slides = slideService.getPagedSlides(offset, PAGE_SIZE);
        
        request.setAttribute("slides", slides);
        request.setAttribute("totalSlides", totalSlides);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        
        PageInfo info = new PageInfo();
        info.setName("slideshow");
        info.setTitle("Quản lý Slideshow | Admin");
        info.setContent("/WEB-INF/views/admin_pages/admin_slideshow.jsp");
        info.setCss(new String[]{
                "pagination.css",
                "admin/admin.css",
                "admin/admin_slideshow.css"
        });
        info.setJs(new String[]{
                "admin/admin_slideshow.js"
        });

        request.setAttribute("info", info);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
