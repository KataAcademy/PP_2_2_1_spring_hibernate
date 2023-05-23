package hiber.service;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Persistence;
import javax.security.auth.login.Configuration;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public List<User> getUserByCar(String model, int serial) {
        return userDao.getUserByCar(model, serial);
    }

}
