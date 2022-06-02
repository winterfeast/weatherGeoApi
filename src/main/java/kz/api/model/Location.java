package kz.api.model;

public class Location {
	private String zipCode;
	private String utc;
	private String code;

	public Location(String zipCode, String utc, String code) {
		this.zipCode = zipCode;
		this.utc = utc;
		this.code = code;
	}
	public Location() {
		
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getUtc() {
		return utc;
	}
	public void setUtc(String utc) {
		this.utc = utc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
