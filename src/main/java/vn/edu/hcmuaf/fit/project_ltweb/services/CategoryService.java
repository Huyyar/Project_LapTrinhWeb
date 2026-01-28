package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.CategoryDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Category;

import java.util.List;

public class CategoryService {
    CategoryDao dao =  new CategoryDao();
    public List<Category> getCategories(){
        return dao.getCategories();
    }
    public int getTotalCat(){
        return dao.getTotalCat();
    }
}
