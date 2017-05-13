package com.ahmedehab.patternmining.dao;

import java.util.List;

import com.ahmedehab.patternmining.domain.Pattern;

public interface PatternDAO extends CRUDDAO<Pattern> {

	boolean saveBulk(List<Pattern> patterns);
}
