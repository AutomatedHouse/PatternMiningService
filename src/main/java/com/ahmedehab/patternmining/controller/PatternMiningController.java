package com.ahmedehab.patternmining.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahmedehab.patternmining.domain.Consumption;
import com.ahmedehab.patternmining.domain.DevicePerConsumption;
import com.ahmedehab.patternmining.domain.Metadata;
import com.ahmedehab.patternmining.service.ConsumptionService;
import com.ahmedehab.patternmining.service.PatternMiningService;
import com.ahmedehab.patternmining.spmf.tools.MemoryLogger;

@Controller
public class PatternMiningController {

	@Autowired
	ConsumptionService consumptionService;

	@Autowired
	PatternMiningService patternMiningService;
	
	public ConsumptionService getConsumptionService() {
		return consumptionService;
	}

	public void setConsumptionService(ConsumptionService consumptionService) {
		this.consumptionService = consumptionService;
	}

	public PatternMiningService getPatternMiningService() {
		return patternMiningService;
	}

	public void setPatternMiningService(PatternMiningService patternMiningService) {
		this.patternMiningService = patternMiningService;
	}

	@RequestMapping(value="/run",produces=("application/json"))
	public @ResponseBody Metadata test(){
		long startTime = System.currentTimeMillis();
		@SuppressWarnings("unchecked")
		List<Consumption> consumptions=(List<Consumption>) consumptionService.listAll();
		for(Consumption c:consumptions){
			List<String> ids=new ArrayList<String>();
			for(DevicePerConsumption d:c.getDeviceSequence()){
				ids.add(d.getDevice().getId().toString());
			}
			System.out.println(c.getTimestamp()+"\t"+ids);
		}
		Metadata metadata=patternMiningService.runAlgoritm(consumptions);
		long endTime=System.currentTimeMillis();
		metadata.setRuntime(endTime-startTime);
		MemoryLogger.getInstance().checkMemory();
		double memory = MemoryLogger.getInstance().getMaxMemory();
		metadata.setMemory(memory);
		System.out.println("---------------\n\n\n\n\n\nSuccess:"+metadata.getSuccess()+" "+"Runtime:"+metadata.getRuntime()+"\n\n\n\n\n\n---------------");
		return metadata;
	}
	@RequestMapping(value={"/","/index"})
	public String index(){
		return "index";
	}

}
