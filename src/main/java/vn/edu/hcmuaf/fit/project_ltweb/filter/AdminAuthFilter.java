package vn.edu.hcmuaf.fit.project_ltweb.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;


@WebFilter(urlPatterns = {"/admin/*"})
public class AdminAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
   
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
       
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("auth");
        }
        
       
        if (user == null) {
           
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        
      
        if (!"admin".equalsIgnoreCase(user.getRole())) {
        
            session.setAttribute("error", "Bạn không có quyền truy cập trang này!");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            return;
        }
        
     
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
     
    }
}
