package com.ahmedehab.patternmining.dao;

import java.sql.Timestamp;

import com.ahmedehab.patternmining.domain.Consumption;

public interface ConsumptionDAO extends CRUDDAO<Consumption>{

	Consumption getByTimestamp(Timestamp t);

}
