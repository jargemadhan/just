package com.example.userapi1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApiController {
	
	@PostMapping("/save")
	public String save(@RequestBody User user) {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction t=session.beginTransaction();
        session.save(user);
        t.commit();
        sessionFactory.close();
        return "User details Saved Successfully";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable int id) {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction t=session.beginTransaction();
        User u=session.get(User.class, id);
        session.delete(u);
        t.commit();
        sessionFactory.close();
        return "User details of "+id+ " is Deleted Successfully";
	}
	
	@GetMapping("/getUserById/{id}")
	public User getUserByID(@PathVariable int id) {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction t=session.beginTransaction();
        User u=session.get(User.class, id);
        return u;
	}
	
	@GetMapping("/getList")
	public List<User> getList() {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
        Session session=sessionFactory.openSession();
        return session.createQuery("from User",User.class).getResultList();
	}
}
