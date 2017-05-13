package com.ahmedehab.patternmining.domain;

import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
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
@Table(name="pattern")
@AssociationOverrides({
	@AssociationOverride(name = "patternId.actionid",
		joinColumns = @JoinColumn(name = "actionid")),
	@AssociationOverride(name = "patternId.eventid",
		joinColumns = @JoinColumn(name = "eventid")) })
public class Pattern {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="patternid")
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="actionid")
	private Device action;
	
	@OneToMany(cascade=CascadeType.MERGE)  
    @JoinTable(name="event", joinColumns=@JoinColumn(name="patternid"), 
    	inverseJoinColumns=@JoinColumn(name="device"))  
	private List<Device> events;
	
	@Column(name="support")
	private Integer support = 0;
	
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

	public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

}
