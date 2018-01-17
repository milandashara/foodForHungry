package com.foodForHungry.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by mashara on 1/12/17.
 */
@Entity
@Table(name = "food")
public class Food implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private String description;

	private String type;

	private Integer numPeople;

	@ManyToOne
	@JoinColumn
	private UserDetail userDetail;

	private Timestamp createTimestamp;

	private Timestamp updateTimestamp;

	private Timestamp pickUpDate;

	private Timestamp fromTime;

	private Timestamp toTime;

	private Double latitude;

	private Double longitude;

	private String address;

	private String clientTimeZone;

	private String countryCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(Integer numPeople) {
		this.numPeople = numPeople;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public Timestamp getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Timestamp pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Timestamp getFromTime() {
		return fromTime;
	}

	public void setFromTime(Timestamp fromTime) {
		this.fromTime = fromTime;
	}

	public Timestamp getToTime() {
		return toTime;
	}

	public void setToTime(Timestamp toTime) {
		this.toTime = toTime;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClientTimeZone() {
		return clientTimeZone;
	}

	public void setClientTimeZone(String clientTimeZone) {
		this.clientTimeZone = clientTimeZone;

	}


	@Override
	public String toString() {
		return "Food{" +
				"id=" + id +
				", description='" + description + '\'' +
				", type='" + type + '\'' +
				", numPeople=" + numPeople +
				", userDetail=" + userDetail +
				", createTimestamp=" + createTimestamp +
				", updateTimestamp=" + updateTimestamp +
				", pickUpDate=" + pickUpDate +
				", fromTime=" + fromTime +
				", toTime=" + toTime +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", address='" + address + '\'' +
				", clientTimeZone='" + clientTimeZone + '\'' +
				", countryCode='" + countryCode + '\'' +
				'}';
	}
}
