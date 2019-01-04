package com.hugkuei.crud.model;

public class Institute {
    private Integer insId;

    private String insName;
    
    public Institute() {
		super();
	}

	public Institute(Integer insId, String insName) {
		super();
		this.insId = insId;
		this.insName = insName;
	}

	public Integer getInsId() {
        return insId;
    }

    public void setInsId(Integer insId) {
        this.insId = insId;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName == null ? null : insName.trim();
    }
}