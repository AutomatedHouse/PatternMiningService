package com.ahmedehab.patternmining.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="paterns")

public class Pattern {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="device_id")
	private Device action;
	
	//Previously: name="event"
	@OneToMany(cascade=CascadeType.MERGE)  
    @JoinTable(name="device_pattern", joinColumns=@JoinColumn(name="pattern_id"), 
    	inverseJoinColumns=@JoinColumn(name="device_id"))  
	private List<Device> events;
	
	@Column(name="support")
	private double support = 0;
	
	@Column(name="confidence")
	private double confidence = 0.0;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Device getAction() {
		return action;
	}

	public void setAction(Device action) {
		this.action = action;
	}

	public List<Device> getEvents() {
		return events;
	}

	public void setEvents(List<Device> events) {
		this.events = events;
	}
	
	public double getSupport() {
		return support;
	}

	public void setSupport(double support) {
		this.support = support;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

}
