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
	public String money;
	public String rate;
	public String productName;
	public String productId; 
	public String notify_uri; // 回调Url 有的平台是传这个告知服务器的
	public String majorName;// 区服角色名称 
	public String userId; // 平台id
	public String appUserId; // 游戏id
	public String majorId; // 区服id
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
