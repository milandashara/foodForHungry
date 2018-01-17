package com.foodForHungry.bo;

/**
 * Created by mashara on 1/12/17.
 */
public class FoodBO {

	private Long id;

	private String description;

	private String type;

	private Integer numPeople;

	private String userEmail;

	private String pickupDateStr;

	private String clientTimeZone;

	private String fromdateStr;

	private String todateStr;

	private Double latitude;

	private Double longitude;

	private String address;

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

	public String getFromdateStr() {
		return fromdateStr;
	}

	public void setFromdateStr(String fromdateStr) {
		this.fromdateStr = fromdateStr;
	}

	public String getTodateStr() {
		return todateStr;
	}

	public void setTodateStr(String todateStr) {
		this.todateStr = todateStr;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPickupDateStr() {
		return pickupDateStr;
	}

	public void setPickupDateStr(String pickupDateStr) {
		this.pickupDateStr = pickupDateStr;
	}

	public String getClientTimeZone() {
		return clientTimeZone;
	}

	public void setClientTimeZone(String clientTimeZone) {
		this.clientTimeZone = clientTimeZone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	@Override
	public String toString() {
		return "FoodBO{" +
				"id=" + id +
				", description='" + description + '\'' +
				", type='" + type + '\'' +
				", numPeople=" + numPeople +
				", userEmail='" + userEmail + '\'' +
				", pickupDateStr='" + pickupDateStr + '\'' +
				", clientTimeZone='" + clientTimeZone + '\'' +
				", fromdateStr='" + fromdateStr + '\'' +
				", todateStr='" + todateStr + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", address='" + address + '\'' +
				", countryCode='" + countryCode + '\'' +
				'}';
	}
}
