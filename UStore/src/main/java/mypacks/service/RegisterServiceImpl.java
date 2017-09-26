package mypacks.service;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mypacks.dao.RegisterDAO;
import mypacks.model.Register;
import mypacks.model.Role;
import mypacks.security.RegisterUserSecurity;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService
{
	@Autowired
	RegisterDAO registerDAO;

	@Autowired
	RegisterUserSecurity registerUserSecurty;
	
	@Override
	public boolean saveUser(Register user) 
	{
		Role role=registerDAO.getRole(2);
		user.setRoleName(role);
		boolean flag=registerDAO.insert(user);
		return flag;
	}

	@Override
	public Register encryptUserPassword(Register user) 
	{
		String encryptedPassword,hashPassword,salt;
		Register encryptedUser=user;
	
		try 
		{
			hashPassword=registerUserSecurty.hashUserPassword(user.getPassword());
			salt=registerUserSecurty.getSalt();
			
			encryptedPassword=hashPassword;
			encryptedUser.setPassword(encryptedPassword);
			encryptedUser.setSalt(salt);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		
		
		return encryptedUser;
	}

	@Override
	public boolean checkSocialUserRegistered(Register user) 
	{
		boolean flag=false;
		List<Register> userList=registerDAO.getUsers();
		for(Register user1:userList)
		{
			if(user1.getEmailId().equals(user.getEmailId()))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}

	@Override
	public boolean saveSocialUser(Register user) 
	{
		Role role=registerDAO.getRole(3);
		user.setRoleName(role);
		boolean flag=registerDAO.insert(user);
		return flag;
	}

	
}
