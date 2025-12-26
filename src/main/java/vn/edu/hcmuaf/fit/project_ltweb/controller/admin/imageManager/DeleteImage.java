package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.imageManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;

import java.io.IOException;
@WebServlet("/admin/delete-image")
public class DeleteImage extends HttpServlet {
    private ImageManagerService service;
    @Override
    public void init() throws ServletException {
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =  request.getParameter("name");
        service.deleteImage(name);
        response.sendRedirect("image-manager");
    }
    }
