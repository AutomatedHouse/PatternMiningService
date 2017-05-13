package com.ahmedehab.patternmining.service;

import java.sql.Timestamp;

import com.ahmedehab.patternmining.domain.Consumption;

public interface ConsumptionService extends CRUDService<Consumption> {

	Consumption getByTimestamp(Timestamp t);

}
