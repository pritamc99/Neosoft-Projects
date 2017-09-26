package mypacks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mypacks.dao.LoginDAO;
import mypacks.model.Login;
import mypacks.model.Register;

@Service
@Transactional
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private LoginDAO loginDAOImpl;
	
	@Override
	public boolean checkLogin(Login login) 
	{
		boolean flag=false;
		String encodedPassword=loginDAOImpl.getEncodedPassword(login);
		/*Login saltedLogin=loginDAOImpl.getEncryptedLogin(login);
		Register register=loginDAOImpl.checkUser(saltedLogin);
		flag=loginDAOImpl.checkLogin(register);*/
		flag=new BCryptPasswordEncoder().matches(login.getPassword(),encodedPassword);
		System.out.println(encodedPassword);
		System.out.println(login.getPassword());
		System.out.println(flag);
		return flag;
	}

	@Override
	public Register getUser(Login login) 
	{
		Register register=loginDAOImpl.checkUser(login);
		return register;
	}

	@Override
	public Register getSocialUser(String email) 
	{
		Register socialUser=loginDAOImpl.getSocialUser(email);
		return socialUser;
	}

	
}
