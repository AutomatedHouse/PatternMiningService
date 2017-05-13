package com.ahmedehab.patternmining.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmedehab.patternmining.dao.PatternDAO;
import com.ahmedehab.patternmining.domain.Pattern;

@Transactional
@Service
public class PatternServiceImpl implements PatternService {


	@Autowired
	PatternDAO patternDAO;

	public PatternDAO getPatternDAO() {
		return patternDAO;
	}

	public void setPatternDAO(PatternDAO patternDAO) {
		this.patternDAO = patternDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pattern> listAll() {
		return (List<Pattern>) this.patternDAO.listAll();
	}

	@Override
	public Pattern getById(Integer id) {
		return this.patternDAO.getById(id);
	}

	@Override
	public Pattern save(Pattern domainObject) {
		return this.patternDAO.save(domainObject);
	}

	@Override
	public Pattern update(Pattern domainObject) {
		return this.patternDAO.update(domainObject);
	}

	@Override
	public boolean delete(Integer id) {
		return this.patternDAO.delete(id);
	}
	
	@Override
	public boolean saveBulk(List<Pattern> patterns) {
		return this.patternDAO.saveBulk(patterns);
	}
}
