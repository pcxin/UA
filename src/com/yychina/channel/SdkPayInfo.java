package com.yychina.channel;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

public class SdkPayInfo implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = -7508280930197401474L;
	
	public String order_id;
	public String token;
	public String userId;
	public String money;
	public String rate;
	public String productName;
	public String productId;
	public String notify_uri;
	public String appUserName;
	public String appUserId;
	public String majorId;
	public String app_ext;

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
