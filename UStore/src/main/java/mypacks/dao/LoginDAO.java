package mypacks.dao;

import java.security.NoSuchAlgorithmException;

import mypacks.model.Login;
import mypacks.model.Register;

public interface LoginDAO 
{
	public Register getSocialUser(String email);
	public Register checkUser(Login login);
	public boolean checkLogin(Register register);
	public Login getEncryptedLogin(Login login);
	public String getEncodedPassword(Login login);
}
