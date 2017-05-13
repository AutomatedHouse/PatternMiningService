package com.ahmedehab.patternmining.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ahmedehab.patternmining.domain.Pattern;

@Repository
public class PatternDAOImpl implements PatternDAO {

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
	public List<Pattern> listAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pattern.class).list();
	}

	@Override
	public Pattern getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Pattern pattern = (Pattern) session.get(Pattern.class, id);
		return pattern;
	}

	@Override
	public Pattern save(Pattern domainObject) {
		Session session = sessionFactory.getCurrentSession();
		Integer newId = (Integer) session.save(domainObject);
		Pattern returnedPattern = (Pattern) session.get(Pattern.class, newId);
		return returnedPattern;
	}

	@Override
	public Pattern update(Pattern domainObject) {
		Session session = sessionFactory.getCurrentSession();
		session.update(domainObject);
		Pattern returnedPattern = (Pattern) session.get(Pattern.class, domainObject.getId());
		return returnedPattern;
	}

	@Override
	public boolean delete(Integer id) {
		try{
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(session.get(Pattern.class, id));
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean saveBulk(List<Pattern> patterns){
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
			List<Pattern> oldPatterns=(List<Pattern>) session.createCriteria(Pattern.class).list();
		for(Pattern pattern:oldPatterns){
			session.delete(pattern);
		}
		
		for(Pattern pattern:patterns){
			session.save(pattern);
		}
		return true;
	}
	
	}
