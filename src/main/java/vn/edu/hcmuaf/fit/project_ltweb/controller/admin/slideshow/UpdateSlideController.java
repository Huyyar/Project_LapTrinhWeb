package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.slideshow;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.Slide;
import vn.edu.hcmuaf.fit.project_ltweb.services.SlideService;

@WebServlet("/admin/update-slide")
public class UpdateSlideController extends HttpServlet {
    private SlideService slideService = new SlideService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {

            long id = Long.parseLong(request.getParameter("id"));
            
            // Get existing slide from database
            Slide existingSlide = slideService.getSlideById(id);
            if (existingSlide == null) {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=not_found");
                return;
            }
            
            // Get parameters from form - preserve old values if empty
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String imageUrl = request.getParameter("image_url");
            String priorityParam = request.getParameter("priority");
            String activeParam = request.getParameter("active");
            
            // Update only non-empty fields (similar to UserProfile pattern)
            existingSlide.setTitle(title != null && !title.isEmpty() ? title : existingSlide.getTitle());
            existingSlide.setDescription(description != null && !description.isEmpty() ? description : existingSlide.getDescription());
            existingSlide.setImageUrl(imageUrl != null && !imageUrl.isEmpty() ? imageUrl : existingSlide.getImageUrl());
            
            if (priorityParam != null && !priorityParam.isEmpty()) {
                try {
                    existingSlide.setPriority(Integer.parseInt(priorityParam));
                } catch (NumberFormatException e) {
                    // Keep existing priority if invalid
                }
            }
            
            if (activeParam != null && !activeParam.isEmpty()) {
                existingSlide.setActive(activeParam.equals("1"));
            }

            boolean success = slideService.updateSlide(existingSlide);
            
            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?success=update");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=update_failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/slideshow?error=exception");
        }
    }
}
