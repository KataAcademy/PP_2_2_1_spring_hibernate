package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

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
   public void findUser(Car car) {
       String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
       Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
       query.setParameter("model", car.getModel());
       query.setParameter("series", car.getSeries());
       System.out.println(query.uniqueResult().toString());
   }

}
