package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImp implements UserDao {
   Logger LOGGER = Logger.getLogger(UserDaoImp.class.getName());
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(Car car) {
      try {
         Query query = sessionFactory.getCurrentSession().createQuery("from User where car.model = :model and car.series = :series");
         query.setParameter("model", car.getModel());
         query.setParameter("series", car.getSeries());
         query.setMaxResults(1);
         return (User) query.getSingleResult();
      } catch (Exception ex) {
         LOGGER.warning(ex.getMessage());
      }
      return null;
   }
}
