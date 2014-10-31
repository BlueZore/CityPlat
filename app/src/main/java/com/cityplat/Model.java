package com.cityplat;

public class Model {
	private String table = "UserString.dat";
	private String field = "loginname|loginpwd|userid|username|token|orgid|farmid|orgname|orglevel|longitude|latitude";
	private String loginname = "";
	private String loginpwd = "";
	private String userid = "";
	private String username = "";
	private String token = "";
	private String orgid = "";
	private String farmid = "";
	private String orgname = "";
	private String orglevel = "";
	private String longitude = "";
	private String latitude = "";

	public String getTable() {
		return table;
	}

	public String getField() {
		return field;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getFarmid() {
		return farmid;
	}

	public void setFarmid(String farmid) {
		this.farmid = farmid;
	}

	public String getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(String orglevel) {
		this.orglevel = orglevel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * model×ªÎª×Ö·û´®
	 * 
	 * @return
	 */
	public String getModelString() {
		return getLoginname() + "|" + getLoginpwd() + "|" + getUserid() + "|"
				+ getUsername() + "|" + getToken() + "|" + getOrgid() + "|"
				+ getFarmid() + "|" + getOrgname() + "|" + getOrglevel() + "|"
				+ getLongitude() + "|" + getLatitude();
	}

}
