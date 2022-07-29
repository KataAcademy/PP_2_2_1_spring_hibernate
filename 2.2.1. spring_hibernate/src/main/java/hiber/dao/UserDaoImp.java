package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
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
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserFromCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = null;
      try {
         query = sessionFactory.getCurrentSession().
                 createQuery("from User user where user.usersCar.model = :model and user.usersCar.series = :series");

      query.setParameter("model", model).setParameter("series", series);
         return query.setMaxResults(1).getSingleResult();
      }catch (NoResultException e){
         System.out.println("- - - - - - - - - - - - - - - - -");
         System.out.println("Пользователь с таким автомобилем не найден!");
         System.out.println("- - - - - - - - - - - - - - - - -");
         return null;
      }
   }


}
