package com.heemin.ws.model.dto.place;

public class Place {
    private long id;
    private String placeName;
    private String roadAddressName;
    private String phone;
    private String url;
    private boolean liked;
    private boolean blocked;

    public Place() {
    }

    public Place(long id, String placeName, String roadAddressName, String phone, String url, boolean liked, boolean blocked) {
        super();
        this.id = id;
        this.placeName = placeName;
        this.roadAddressName = roadAddressName;
        this.phone = phone;
        this.url = url;
        this.liked = liked;
        this.blocked = blocked;
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }




}
