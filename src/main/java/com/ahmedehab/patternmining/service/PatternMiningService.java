package com.ahmedehab.patternmining.service;

import java.util.List;

import com.ahmedehab.patternmining.domain.Consumption;
import com.ahmedehab.patternmining.domain.Metadata;

public interface PatternMiningService {

	Metadata runAlgoritm(List<Consumption> pattern);

	List<List<String>> extractIdList(List<Consumption> pattern);
}
