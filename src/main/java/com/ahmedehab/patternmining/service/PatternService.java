package com.ahmedehab.patternmining.service;

import java.util.List;

import com.ahmedehab.patternmining.domain.Pattern;

public interface PatternService extends CRUDService<Pattern> {

	boolean saveBulk(List<Pattern> patterns);

}
