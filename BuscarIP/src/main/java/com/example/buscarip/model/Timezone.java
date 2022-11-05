package com.example.buscarip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Timezone  implements Serializable {

	@JsonProperty("offset")
	private int offset;

	@JsonProperty("utc")
	private String utc;

	@JsonProperty("id")
	private String id;

	@JsonProperty("abbr")
	private String abbr;

	@JsonProperty("is_dst")
	private boolean isDst;

	@JsonProperty("current_time")
	private String currentTime;

	public int getOffset(){
		return offset;
	}

	public String getUtc(){
		return utc;
	}

	public String getId(){
		return id;
	}

	public String getAbbr(){
		return abbr;
	}

	public boolean isIsDst(){
		return isDst;
	}

	public String getCurrentTime(){
		return currentTime;
	}

	@Override
 	public String toString(){
		return 
			"Timezone{" + 
			"offset = '" + offset + '\'' + 
			",utc = '" + utc + '\'' + 
			",id = '" + id + '\'' + 
			",abbr = '" + abbr + '\'' + 
			",is_dst = '" + isDst + '\'' + 
			",current_time = '" + currentTime + '\'' + 
			"}";
		}
}