package com.ahmedehab.patternmining.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahmedehab.patternmining.domain.Consumption;

public class ConsumptionDAOImpl implements ConsumptionDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Override
	public List<Consumption> listAll() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Consumption> consumption = (List<Consumption>) session.createCriteria(Consumption.class).list();
		return consumption;
	}

	public Consumption getByTimestamp(Timestamp t){
		Session session = this.sessionFactory.getCurrentSession();
		Consumption consumption = (Consumption) session.createCriteria(Consumption.class)
				.add(Restrictions.eq("timestamp", t));
		return consumption;
	}

//	@Override
//	public boolean save(Consumption domainObject) {
//		try{
//		Session session = this.sessionFactory.getCurrentSession();
//		session.save(domainObject);
//		}
//		catch(Exception ex){
//			return false;
//		}
//		return true;
//	}

	@Override
	public Consumption update(Consumption domainObject) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(domainObject);
		Consumption consumption = (Consumption) session.createCriteria(Consumption.class)
				.add(Restrictions.eq("timestamp", domainObject.getTimestamp()));
		return consumption;
	}

	public boolean delete(Consumption consumption) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("delete from consumption where timestamp= :t ");
		query.setTimestamp("t", consumption.getTimestamp());
		int result=query.executeUpdate();
		if(result==0){
			return false;
		}
		return true;
	}

	@Override
	public Consumption getById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Consumption consumption = (Consumption) session.get(Consumption.class, id);
		return consumption;
	}

	@Override
	public Consumption save(Consumption domainObject) {		
		Session session = this.sessionFactory.getCurrentSession();
		Integer newId = (Integer) session.save(domainObject);
		Consumption consumption = (Consumption) session.get(Consumption.class, newId);
		return consumption;
	}

	@Override
	public boolean delete(Integer id) {
		try{
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(session.get(Consumption.class, id));
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

}
