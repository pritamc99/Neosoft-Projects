package mypacks.service;

import java.util.List;

import mypacks.model.Register;


public interface RegisterService 
{
	public boolean saveUser(Register user);
	public boolean saveSocialUser(Register user);
	public Register encryptUserPassword(Register user);
	public boolean checkSocialUserRegistered(Register user);
}
