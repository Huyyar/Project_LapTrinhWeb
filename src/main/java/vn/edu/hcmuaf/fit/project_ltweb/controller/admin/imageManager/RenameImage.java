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
    public void init() { this.service = new ImageManagerService(getServletContext().getRealPath("/")); }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        service.renameImage(req.getParameter("name"), req.getParameter("newName"));
        resp.sendRedirect("image-manager");
    }
}
