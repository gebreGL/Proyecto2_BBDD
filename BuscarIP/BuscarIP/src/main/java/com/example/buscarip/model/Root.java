package com.example.buscarip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Root implements Serializable {


	@JsonProperty("country")
	private String country;

	@JsonProperty("calling_code")
	private String callingCode;

	@JsonProperty("capital")
	private String capital;
	@JsonProperty("city")
	private String city;

	@JsonProperty("borders")
	private String borders;

	@JsonProperty("ip")
	private String ip;

	@JsonProperty("latitude")
	private double latitude;


	@JsonProperty("success")
	private boolean success;


	@JsonProperty("postal")
	private String postal;

	@JsonProperty("region")
	private String region;

	@JsonProperty("longitude")
	private double longitude;



	public String getCountry(){
		return country;
	}

	public String getCallingCode(){
		return callingCode;
	}

	public String getCapital(){
		return capital;
	}


	public String getCity(){
		return city;
	}

	public String getBorders(){
		return borders;
	}


	public String getIp(){
		return ip;
	}

	public double getLatitude(){
		return latitude;
	}



	public boolean isSuccess(){
		return success;
	}



	public String getPostal(){
		return postal;
	}

	public String getRegion(){
		return region;
	}


	public double getLongitude(){
		return longitude;
	}

	public Root(String country, String callinCode , String capital, String city, String ip, double latitude, String postal, String region, double longitude) {
		this.callingCode = callinCode;
		this.country = country;
		this.capital = capital;
		this.city = city;
		this.ip = ip;
		this.latitude = latitude;
		this.postal = postal;
		this.region = region;
		this.longitude = longitude;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
 	public String toString(){
		return 
			"Ip{" +
			",country = '" + country + "\n" +
			",calling_code = '" + callingCode + "\n" +
			",capital = '" + capital + "\n" +
			",city = '" + city + "\n" +
			",ip = '" + ip + "\n" +
			",latitude = '" + latitude + "\n" +
			",success = '" + success + "\n" +
			",postal = '" + postal + "\n" +
			",region = '" + region + "\n" +
			",longitude = '" + longitude + "\n" +
			"}";
		}

	public Root() {
	}

}