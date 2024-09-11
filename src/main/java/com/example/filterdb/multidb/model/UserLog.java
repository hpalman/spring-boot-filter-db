package com.example.filterdb.multidb.model;

public class UserLog {
    private String gid;
    private String uri;
    
    public UserLog(String gid, String uri) {
		super();
		this.gid = gid;
		this.uri = uri;
	}
	// Getters and setters
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
