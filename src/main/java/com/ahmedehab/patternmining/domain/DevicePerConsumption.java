package com.ahmedehab.patternmining.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="consumption")
public class DevicePerConsumption {
	
	@Id
	@Column(name="idc")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="deviceid")
	private Device device;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
}
