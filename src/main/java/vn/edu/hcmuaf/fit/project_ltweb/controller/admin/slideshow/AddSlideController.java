package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.slideshow;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Slide;
import vn.edu.hcmuaf.fit.project_ltweb.services.SlideService;

@WebServlet("/admin/add-slide")
public class AddSlideController extends HttpServlet {
    private SlideService slideService = new SlideService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String imageUrl = request.getParameter("image_url");
            int priority = Integer.parseInt(request.getParameter("priority"));
            boolean active = request.getParameter("active").equals("1");
            

            Slide slide = new Slide(imageUrl, title, description, active, priority);
            

            long slideId = slideService.addSlide(slide);
            
            if (slideId > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?success=add");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=add_failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=exception");
        }
    }
}
