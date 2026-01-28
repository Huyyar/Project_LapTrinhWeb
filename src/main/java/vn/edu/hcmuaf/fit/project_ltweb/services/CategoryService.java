package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.CategoryDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    CategoryDao dao =  new CategoryDao();
    ProductDao pDao = new ProductDao();
    public List<Category> getCategories(){
        List<Category> list =  dao.getCategories();
        for(Category c : list){
            c.setTotalProduct(pDao.getTotalProductByCategory(c.getId()));
        }
        return list;
    }
    public List<Category> getPagedCategories(int offset, int pageSize, String search){
        List<Category> list =  new ArrayList<>();
        if(search.isEmpty()){
            list = dao.getPagedCategories(offset, pageSize);
        }else{
            list = dao.getPagedCategoriesBySearch(offset, pageSize, search);
        }
        for(Category c : list){
            c.setTotalProduct(pDao.getTotalProductByCategory(c.getId()));
        }
        return list;
    }
    public int getTotalCat(String search){
        if(search.isEmpty()){
            return dao.getTotalCat();
        }
        return dao.getTotalCatBySearch(search);
    }
}
