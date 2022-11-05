package com.example.buscarip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Root implements Serializable {

	@JsonProperty("continent")
	private String continent;

	@JsonProperty("country")
	private String country;

	@JsonProperty("calling_code")
	private String callingCode;

	@JsonProperty("capital")
	private String capital;

	@JsonProperty("flag")
	private Flag flag;

	@JsonProperty("city")
	private String city;

	@JsonProperty("borders")
	private String borders;

	@JsonProperty("timezone")
	private Timezone timezone;

	@JsonProperty("ip")
	private String ip;

	@JsonProperty("latitude")
	private double latitude;

	@JsonProperty("continent_code")
	private String continentCode;

	@JsonProperty("type")
	private String type;

	@JsonProperty("country_code")
	private String countryCode;

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("is_eu")
	private boolean isEu;

	@JsonProperty("connection")
	private Connection connection;
	@JsonProperty("postal")
	private String postal;

	@JsonProperty("region")
	private String region;

	@JsonProperty("region_code")
	private String regionCode;

	@JsonProperty("longitude")
	private double longitude;

	public Root( String ip, String countryCode, String city) {
		this.city = city;
		this.ip = ip;
		this.countryCode = countryCode;
	}


	public String getContinent(){
		return continent;
	}

	public String getCountry(){
		return country;
	}

	public String getCallingCode(){
		return callingCode;
	}

	public String getCapital(){
		return capital;
	}

	public Flag getFlag(){
		return flag;
	}

	public String getCity(){
		return city;
	}

	public String getBorders(){
		return borders;
	}

	public Timezone getTimezone(){
		return timezone;
	}

	public String getIp(){
		return ip;
	}

	public double getLatitude(){
		return latitude;
	}

	public String getContinentCode(){
		return continentCode;
	}

	public String getType(){
		return type;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public boolean isSuccess(){
		return success;
	}

	public boolean isIsEu(){
		return isEu;
	}

	public Connection getConnection(){
		return connection;
	}

	public String getPostal(){
		return postal;
	}

	public String getRegion(){
		return region;
	}

	public String getRegionCode(){
		return regionCode;
	}

	public double getLongitude(){
		return longitude;
	}

	

	public void setCity(String city) {
		this.city = city;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
 	public String toString(){
		return 
			"Root{" +
			"continent = '" + continent + "\n" +
			",country = '" + country + "\n" +
			",calling_code = '" + callingCode + "\n" +
			",capital = '" + capital + "\n" +
			",flag = '" + flag + "\n"+
			",city = '" + city + "\n" +
			",borders = '" + borders + "\n" +
			",timezone = '" + timezone + "\n" +
			",ip = '" + ip + "\n" +
			",latitude = '" + latitude + "\n" +
			",continent_code = '" + continentCode + "\n" +
			",type = '" + type + "\n" +
			",country_code = '" + countryCode + "\n" +
			",success = '" + success + "\n" +
			",is_eu = '" + isEu + "\n" +
			",connection = '" + connection + "\n" +
			",postal = '" + postal + "\n" +
			",region = '" + region + "\n" +
			",region_code = '" + regionCode + "\n" +
			",longitude = '" + longitude + "\n" +
			"}";
		}


	public Root() {
	}

}