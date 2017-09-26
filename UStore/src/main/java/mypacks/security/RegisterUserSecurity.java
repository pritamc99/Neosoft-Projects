package mypacks.security;

import java.security.NoSuchAlgorithmException;


public interface RegisterUserSecurity 
{
	public String hashUserPassword(String password)throws NoSuchAlgorithmException ;
	public String getSalt()throws NoSuchAlgorithmException ;
}
