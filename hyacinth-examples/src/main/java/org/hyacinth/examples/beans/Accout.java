package org.hyacinth.examples.beans;

import java.util.List;

public class Accout {
	
	private String uid;
	private String nickName;
	private String level;
	private List<String> favorates;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<String> getFavorates() {
		return favorates;
	}
	public void setFavorates(List<String> favorates) {
		this.favorates = favorates;
	}
	
	
}
