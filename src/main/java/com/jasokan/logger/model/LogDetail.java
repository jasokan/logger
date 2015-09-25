package com.jasokan.logger.model;

import java.sql.Timestamp;

public class LogDetail {
	
	private Timestamp logTimestamp;
	private String action;
	private String actionJson;
	private int sellerId;
	private String skuId;
	
	
	public Timestamp getLogTimestamp() {
		return logTimestamp;
	}
	public void setLogTimestamp(Timestamp logTimestamp) {
		this.logTimestamp = logTimestamp;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionJson() {
		return actionJson;
	}
	public void setActionJson(String actionJson) {
		this.actionJson = actionJson;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	@Override
	public String toString() {
		return "LogDetail [logTimestamp=" + logTimestamp + ", action=" + action
				+ ", actionJson=" + actionJson + ", sellerId=" + sellerId
				+ ", skuId=" + skuId + "]";
	}
	
	
	

}
