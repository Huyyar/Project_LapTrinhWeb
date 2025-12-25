package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.UserDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.untils.HashUtil;

public class UserService {
    UserDao dao = new UserDao();
    HashUtil hashUtil = new HashUtil();
    public boolean registerUser(String email, String password,String avatar_url) {
        if(dao.isUserExists(email)) {
            return false;
        }
        String hashedPassword = hashUtil.passwordHash(password);
        int res = dao.insertUser(email, hashedPassword,avatar_url);

        if(res>0){
            return true;
        }
        return false;

    }
    public User getUserByEmail(String email){
        return dao.getUserByEmail(email);
    }

}
