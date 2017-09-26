package mypacks.dao;

import java.security.NoSuchAlgorithmException;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.Login;
import mypacks.model.Register;
import mypacks.security.RegisterUserSecurity;

@Repository
public class LoginDAOImpl implements LoginDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RegisterUserSecurity registerUserSecurityIMPL;
	
	
	public Register checkUser(Login login) 
	{
		Register user=null;
		try
		{
			System.out.println("before"+login.getUserName());
			user=(Register) sessionFactory.getCurrentSession().createCriteria(Register.class).add(Restrictions.eqOrIsNull("userName",login.getUserName())).uniqueResult();
			System.out.println("after");
		}
		catch (NoResultException e) 
		{
			System.out.println("No such user");
		}
		return user;
	}

	@Override
	
	public boolean checkLogin(Register register) 
	{
		if(register!=null)
			return true;
		else
		{		
			return false;
		}
	}

	@Override
	
	public Login getEncryptedLogin(Login login)
	{
		Login log=login;
		String salt=null;
		String password=null;
		String encodedpassword=null;
		
		try {
			Register user =(Register) sessionFactory.getCurrentSession().createQuery("from Register where username='"+login.getUserName()+"'").getSingleResult();
			/*password = registerUserSecurityIMPL.hashUserPassword(login.getPassword());*/
			/*salt=user.getSalt();*/
			encodedpassword=user.getPassword();
			System.out.println(login.getPassword());
			System.out.println(log.getPassword());
			System.out.println(encodedpassword);
			
		}
		/*catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}*/
		catch (NoResultException e) 
		{
			System.out.println("No such user");
		}
		log.setPassword(encodedpassword/*password+salt*/);
		return log;
	}

	@Override
	public String getEncodedPassword(Login login) 
	{
		Register user=(Register) sessionFactory.getCurrentSession().createCriteria(Register.class).add(Restrictions.eq("userName", login.getUserName())).uniqueResult();
		return user.getPassword();
	}

	@Override
	public Register getSocialUser(String email) 
	{
		Register socialUser=(Register) sessionFactory.getCurrentSession().createCriteria(Register.class).add(Restrictions.eq("emailId", email)).uniqueResult();
		return socialUser;
	}

	
}
