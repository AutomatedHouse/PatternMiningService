package com.ahmedehab.patternmining.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ahmedehab.patternmining.domain.Device;

@Repository
public class DeviceDAOImpl implements DeviceDAO {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> listAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Device.class).list();
	}

	@Override
	public Device getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Device device = (Device) session.get(Device.class, id);
		return device;
	}

	@Override
	public Device save(Device domainObject) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(domainObject);
		return (Device) session.get(Device.class, id);
	}

	@Override
	public Device update(Device domainObject) {
		Session session = sessionFactory.getCurrentSession();
		session.update(domainObject);
		Device device = (Device) session.get(Device.class, domainObject.getId());
		return device;
	}

	@Override
	public boolean delete(Integer id) {
		try{
		Session session = sessionFactory.getCurrentSession();
		session.delete(id);
		}
		catch(Exception ex){
			return false;
		}
		return true;
	}

}
