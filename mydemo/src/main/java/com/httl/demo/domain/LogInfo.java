package com.httl.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 * @Description:TODO
 * @author liu hui
 * @time:2015-9-24 上午09:31:09
 */
public class LogInfo implements Serializable{
	private String id; //主键
	private String clientIP; //客户端IP
	private String loginAccountId;//登录账号ID  
	private Date requestTime;//请求时间
	private String requestURL; //请求url  
	private String requestParams;//请求参数  
	private Date responseTime;//响应完成时间
	private String responseInfo;//响应信息描述  respMsg
	private String responseCode;//响应结果 respCode
	private Long operateTime;
	public Long getOperateTime() {
		return this.responseTime.getTime()-this.requestTime.getTime();
	}
	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getLoginAccountId() {
		return loginAccountId;
	}
	public void setLoginAccountId(String loginAccountId) {
		this.loginAccountId = loginAccountId;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public String getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(String responseInfo) {
		this.responseInfo = responseInfo;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	
}
