package com.ahmedehab.patternmining.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmedehab.patternmining.domain.Consumption;
import com.ahmedehab.patternmining.domain.Device;
import com.ahmedehab.patternmining.domain.DevicePerConsumption;
import com.ahmedehab.patternmining.domain.Metadata;
import com.ahmedehab.patternmining.domain.Pattern;
import com.ahmedehab.patternmining.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import com.ahmedehab.patternmining.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRule;
import com.ahmedehab.patternmining.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import com.ahmedehab.patternmining.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import com.ahmedehab.patternmining.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import com.ahmedehab.patternmining.spmf.tools.MemoryLogger;

@Service
public class PatternMiningServiceImpl implements PatternMiningService {

	@Autowired
	PatternService patternService;
	
	@Autowired
	DeviceService deviceService;
	
	public PatternService getPatternService() {
		return patternService;
	}

	public void setPatternService(PatternService patternService) {
		this.patternService = patternService;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public Metadata runAlgoritm(List<Consumption> patternList) {
		
		AlgoFPGrowth algo = new AlgoFPGrowth();
		Itemsets items = null;
		List<List<String>> devicePatternList=new ArrayList<List<String>>();
		devicePatternList =	this.extractIdList(patternList);
		try {
			items = algo.runAlgorithm(devicePatternList, null, 0.1);
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int databaseSize = algo.getDatabaseSize();
		
		
		
		
		// STEP 2: Generating all rules from the set of frequent itemsets
		double minconf = 0.30;
		AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
		// the next line run the algorithm.
		// Note: we pass null as output file path, because we don't want
		// to save the result to a file, but keep it into memory.
		AssocRules assocRules = null;
		try {
			assocRules = algoAgrawal.runAlgorithm(items, null, databaseSize, minconf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(" ------- Association rules -------");
		
		List<Pattern> patterns=new ArrayList<Pattern>();
		for(AssocRule rule : assocRules.rules){
			System.out.println(rule.toString()+"\t"+"support :  " + rule.getRelativeSupport(databaseSize) 
			+ " (" + rule.getAbsoluteSupport() + "/" + 
			databaseSize + ") confidence : " + rule.getConfidence());
			if(rule.getItemset1().length==1){
			//Intialize new pattern
			Pattern pattern = new Pattern();
			//Set action
			Device actionDevice = deviceService.getById(rule.getItemset1()[0]);
			pattern.setAction(actionDevice);
			
			//Set events
			List<Device> eventDevices=new ArrayList<Device>();
			for(Integer i:rule.getItemset2()){
				eventDevices.add(deviceService.getById(i));
			}
			pattern.setEvents(eventDevices);
			//Set support and confidence
			pattern.setSupport(rule.getRelativeSupport(databaseSize));
			pattern.setConfidence(rule.getConfidence());
			//Add the new pattern to the list of patterns
			patterns.add(pattern);
			MemoryLogger.getInstance().checkMemory();

		}}
		
		boolean success= patternService.saveBulk(patterns);
		MemoryLogger.getInstance().checkMemory();
		
		return new Metadata(String.valueOf(success), 0);

	}

	@Override
	public ArrayList<List<String>> extractIdList(List<Consumption> patternList) {
		ArrayList<List<String>> preparedIdList = new ArrayList<List<String>>();
		for(Consumption pattern:patternList){
			List<String> idList=new ArrayList<String>();
			for(DevicePerConsumption device:pattern.getDeviceSequence()){
				idList.add(device.getDevice().getId().toString());
			}
			preparedIdList.add(idList);
		}
		return preparedIdList;
	}

	
	
}
