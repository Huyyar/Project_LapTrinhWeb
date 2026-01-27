package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.slideshow;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.services.SlideService;

@WebServlet("/admin/delete-slide")
public class DeleteSlideController extends HttpServlet {
    private SlideService slideService = new SlideService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {
            long slideId = Long.parseLong(request.getParameter("slideId"));
            boolean success = slideService.deleteSlide(slideId);
            
            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?success=delete");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=delete_failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=exception");
        }
    }
}
