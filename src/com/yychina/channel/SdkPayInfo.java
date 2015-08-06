package com.yychina.channel;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

public class SdkPayInfo implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = -7508280930197401474L;
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRmb() {
		return rmb;
	}

	public void setRmb(String rmb) {
		this.rmb = rmb;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getNotify_uri() {
		return notify_uri;
	}

	public void setNotify_uri(String notify_uri) {
		this.notify_uri = notify_uri;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getApp_ext() {
		return app_ext;
	}

	public void setApp_ext(String app_ext) {
		this.app_ext = app_ext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String order_id; //订单号
	public String token;  //token
	public String money;  //单价
	public String rmb;    //配好商品编码的总价
	public String rate;   //兑换比率
	public String productName;  //商品名称
	public String productId; //商品编码
	public String notify_uri; // 回调Url 有的平台是传这个告知服务器的
	public String roleName;// 区服角色名称 
	public String platformId; // 平台id
	public String UserId; // 游戏id
	public String serverId; // 区服id
	public String app_ext;  // 保留字段 如果客户端传值，支付成功之后 会原样返回

	public SdkPayInfo converInfo(String data){
		String tempItem = null;
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			JSONObject jsonObject = new JSONObject(data);
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				try {
					tempItem = fields[i].getName();
					if(tempItem.equals("serialVersionUID") || jsonObject.isNull(tempItem) || jsonObject.get(tempItem) == JSONObject.NULL) continue;
					field.set(this, jsonObject.get(tempItem).toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
}
