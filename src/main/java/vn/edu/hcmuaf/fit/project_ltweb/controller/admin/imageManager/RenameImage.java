package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.imageManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;

import java.io.IOException;
@WebServlet("/admin/rename-image")
public class RenameImage extends HttpServlet {
    private ImageManagerService service;
    @Override
    public void init() throws ServletException {
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =  request.getParameter("name");
        String newName = request.getParameter("newName");
        service.renameImage(name,newName);
        response.sendRedirect("image-manager");
    }
}
