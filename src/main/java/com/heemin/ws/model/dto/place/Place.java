package com.ssafy.ssafit.model.dto.place;

public class Place {
	private long id;
	private String placeName;
	private String roadAddressName;
	private String phone;
	private String url;
	
	public Place() {
	}

	public Place(long id, String placeName, String roadAddressName, String phone, String url) {
		super();
		this.id = id;
		this.placeName = placeName;
		this.roadAddressName = roadAddressName;
		this.phone = phone;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getRoadAddressName() {
		return roadAddressName;
	}

	public void setRoadAddressName(String roadAddressName) {
		this.roadAddressName = roadAddressName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", placeName=" + placeName + ", roadAddressName=" + roadAddressName + ", phone="
				+ phone + ", url=" + url + "]";
	}
	
	
}
