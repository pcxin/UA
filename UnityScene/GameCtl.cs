using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GameCtl : MonoBehaviour 
{
	private AndroidJavaObject activity; // current active androidActivity
	private AndroidJavaClass jc; // java class

	public Button btnLogin;
	public Button btnPay;
	public InputField InputFieldData;

	// Use this for initialization
	void Start () {
		try{
			jc = new AndroidJavaClass("com.yychina.channel.SdkFactory");
		}catch(System.Exception e){
			Debug.LogError(e.Message);
		}

		btnLogin.onClick.AddListener(delegate {
			Debug.Log("login");
			try{
				jc.CallStatic("login");
			}catch(System.Exception e){
				Debug.LogError(e.Message);
			}
		});
		btnPay.onClick.AddListener(delegate {
			pay();
		});
	}

	private void pay(){
		Debug.Log("pay");
		try{
			jc.CallStatic("pay","{\"order_id\":\"32sdse2232\",\"token\":\"dssdd232sdds\"}");
		}catch(System.Exception e){
			Debug.LogError(e.Message);
		}
	}

	/// <summary>
	/// android 方法调用 method
	/// </summary>
	/// <param name="str">String.</param>
	public void messgae(string str)
	{
		Debug.Log("loginBack:"+str);
		InputFieldData.text = str;
	}
}
