package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.UserDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.untils.HashUtil;

public class UserService {
    UserDao dao = new UserDao();
    HashUtil hashUtil = new HashUtil();
    public boolean registerUser(User u) {
        if(dao.isUserExists(u.getEmail())) {
            return false;
        }
        String hashedPassword = hashUtil.passwordHash(u.getPassword());
        u.setPassword(hashedPassword);
        int res = dao.insertUser(u);

        if(res>0){
            return true;
        }
        return false;

    }
    public User getUserByEmail(String email){
        return dao.getUserByEmail(email);
    }

}
