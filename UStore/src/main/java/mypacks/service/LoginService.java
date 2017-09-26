package mypacks.service;

import mypacks.model.Login;
import mypacks.model.Register;

public interface LoginService 
{
	public boolean checkLogin(Login login);
	public Register getUser(Login login);
	public Register getSocialUser(String email);
}
