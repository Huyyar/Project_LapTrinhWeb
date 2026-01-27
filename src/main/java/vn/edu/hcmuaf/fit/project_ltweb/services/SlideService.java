package vn.edu.hcmuaf.fit.project_ltweb.services;

import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.dao.SlideDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Slide;

public class SlideService {
    private SlideDao slideDao = new SlideDao();
    

    public List<Slide> getAllSlides() {
        return slideDao.getAllSlides();
    }
    

    public List<Slide> getActiveSlides() {
        return slideDao.getActiveSlides();
    }

    public long addSlide(Slide slide) {
        return slideDao.addSlide(slide);
    }
    

    public boolean updateSlide(Slide slide) {
        return slideDao.updateSlide(slide);
    }

    public boolean deleteSlide(long slideId) {
        return slideDao.deleteSlide(slideId);
    }

    public Slide getSlideById(long id) {
        return slideDao.getSlideById(id);
    }
    
    public int getTotalSlides() {
        return slideDao.getTotalSlides();
    }
    
    public List<Slide> getPagedSlides(int offset, int limit) {
        return slideDao.getPagedSlides(offset, limit);
    }
}
