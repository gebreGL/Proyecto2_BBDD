package com.example.buscarip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Connection  implements Serializable {

	@JsonProperty("org")
	private String org;

	@JsonProperty("isp")
	private String isp;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("asn")
	private int asn;

	public String getOrg(){
		return org;
	}

	public String getIsp(){
		return isp;
	}

	public String getDomain(){
		return domain;
	}

	public int getAsn(){
		return asn;
	}

	@Override
 	public String toString(){
		return 
			"Connection{" + 
			"org = '" + org + '\'' + 
			",isp = '" + isp + '\'' + 
			",domain = '" + domain + '\'' + 
			",asn = '" + asn + '\'' + 
			"}";
		}
}