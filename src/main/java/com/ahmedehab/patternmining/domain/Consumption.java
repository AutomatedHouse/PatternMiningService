package com.ahmedehab.patternmining.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="timestamps")
public class Consumption {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="timestamp")
	private Timestamp timestamp;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="consumption_timestamp",joinColumns=@JoinColumn(name="timestamp_id")
			,inverseJoinColumns=@JoinColumn(name="consumption_id"))
	@OrderBy(value="id asc")
	private List<DevicePerConsumption> deviceSequence;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<DevicePerConsumption> getDeviceSequence() {
		return deviceSequence;
	}

	public void setDeviceSequence(List<DevicePerConsumption> deviceSequence) {
		this.deviceSequence = deviceSequence;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
