package hiber.dao;


import hiber.model.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

   private  SessionFactory sessionFactory;
   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;

   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> list() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }



   @Override
   @SuppressWarnings("unchecked")
   public User getFrom(String model, int series) {
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
