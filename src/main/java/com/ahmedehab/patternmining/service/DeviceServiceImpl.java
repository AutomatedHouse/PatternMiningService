package com.ahmedehab.patternmining.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmedehab.patternmining.dao.DeviceDAO;
import com.ahmedehab.patternmining.domain.Device;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceDAO deviceDAO;
	
	public DeviceDAO getDeviceDAO() {
		return deviceDAO;
	}

	public void setDeviceDAO(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> listAll() {
		return (List<Device>) deviceDAO.listAll();
	}

	@Override
	public Device getById(Integer id) {
		return deviceDAO.getById(id);
	}

	@Override
	public Device save(Device domainObject) {
		return deviceDAO.save(domainObject);
	}

	@Override
	public Device update(Device domainObject) {
		return deviceDAO.update(domainObject);
	}

	@Override
	public boolean delete(Integer id) {
		return deviceDAO.delete(id);
	}

}
