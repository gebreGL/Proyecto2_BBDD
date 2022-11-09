package com.example.buscarip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Flag implements Serializable {

	@JsonProperty("img")
	private String img;

	@JsonProperty("emoji")
	private String emoji;



	@JsonProperty("emoji_unicode")
	private String emojiUnicode;
	public Flag(String img, String emoji, String emojiUnicode) {
		this.img = img;
		this.emoji = emoji;
		this.emojiUnicode = emojiUnicode;
	}
	public Flag(){

	}

	public String getImg(){
		return img;
	}

	public String getEmoji(){
		return emoji;
	}

	public String getEmojiUnicode(){
		return emojiUnicode;
	}

	@Override
 	public String toString(){
		return 
			"Flag{" + 
			"img = '" + img + '\'' + 
			",emoji = '" + emoji + '\'' + 
			",emoji_unicode = '" + emojiUnicode + '\'' + 
			"}";
		}
}