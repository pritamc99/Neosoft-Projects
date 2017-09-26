package mypacks.dao;

import java.util.List;

import mypacks.model.Register;
import mypacks.model.Role;

public interface RegisterDAO 
{
	public boolean insert(Register register);
	public Role getRole(int id);
	public List<Register> getUsers();
} 
