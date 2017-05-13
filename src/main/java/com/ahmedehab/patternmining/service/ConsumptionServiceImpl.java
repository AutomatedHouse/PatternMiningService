package com.ahmedehab.patternmining.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmedehab.patternmining.dao.ConsumptionDAO;
import com.ahmedehab.patternmining.domain.Consumption;

@Service
@Transactional
public class ConsumptionServiceImpl implements ConsumptionService {

	@Autowired
	ConsumptionDAO consumptionDAO;

	public ConsumptionDAO getConsumptionDAO() {
		return consumptionDAO;
	}

	public void setConsumptionDAO(ConsumptionDAO consumptionDAO) {
		this.consumptionDAO = consumptionDAO;
	}

	@Override
	public List<Consumption> listAll() {
		@SuppressWarnings("unchecked")
		List<Consumption> consumptions = (List<Consumption>) this.consumptionDAO.listAll();
		return consumptions;
	}

	@Override
	public Consumption getById(Integer id) {
		return this.consumptionDAO.getById(id);
	}

	@Override
	public Consumption save(Consumption domainObject) {
		return this.consumptionDAO.save(domainObject);
	}

	@Override
	public Consumption update(Consumption domainObject) {
		return this.consumptionDAO.update(domainObject);
	}

	@Override
	public boolean delete(Integer id) {
		return this.consumptionDAO.delete(id);
	}
	
	@Override
	public Consumption getByTimestamp(Timestamp t) {
		return this.consumptionDAO.getByTimestamp(t);
	}

}
