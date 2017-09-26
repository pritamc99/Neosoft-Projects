package mypacks.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.Register;
import mypacks.model.Role;

@Repository
public class RegisterDAOImpl implements RegisterDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	
	public boolean insert(Register register) 
	{
		sessionFactory.getCurrentSession().save(register);
		return true;
	}

	@Override
	public Role getRole(int id) 
	{
		Role role=(Role) sessionFactory.getCurrentSession().createCriteria(Role.class).add(Restrictions.idEq(id)).uniqueResult();
		return role;
	}

	@Override
	public List<Register> getUsers() 
	{
		List<Register> list=sessionFactory.getCurrentSession().createCriteria(Register.class).list();
		return list;
	}

	

}
