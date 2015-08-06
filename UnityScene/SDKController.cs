using UnityEngine;
using System.Collections;


public class SDKController : MonoBehaviour 
{
	private AndroidJavaObject activity; // current active androidActivity
	private static AndroidJavaClass jc; // java class


	private static GameController _gameCtl;

	private static SDKController _instance;
	public static SDKController Instance() {
		if(_instance == null) {
			GameObject go = new GameObject("SDKController");
			_instance = go.AddComponent<SDKController>();

			_gameCtl = GameObject.Find("Main Camera").GetComponent<GameController>();
		}
		return _instance;
	}

	void Start(){
		try{
			jc = new AndroidJavaClass("com.yychina.channel.SdkFactory");
			jc.CallStatic("init");
		}catch(System.Exception e){
			Debug.LogError(e.Message);
		}
	}

	void Awake()
	{
		DontDestroyOnLoad(this.gameObject);
	}

	public void login(){
		Debug.Log("login");

		try{
			jc.CallStatic("login");
		}catch(System.Exception e){
			Debug.LogError(e.Message);
		}
	}

	public void pay(){
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
		Debug.Log("messgaecallback:"+str);
		_gameCtl.InputFieldData.text = str;
	}

	/// <summary>
	/// android 方法调用 method
	/// </summary>
	public void InitCallBack()
	{
		Debug.Log("InitCallBack:");
		_gameCtl.InputFieldData.text = "InitCallBack";
	}

	public void InitCallBack(string str)
	{
		Debug.Log("InitCallBack1:");
		_gameCtl.InputFieldData.text = "InitCallBack1";
	}


	/// <summary>
	/// android 方法调用 method
	/// </summary>
	/// <param name="str">String.</param>
	public void LoginCallBack(string str)
	{
		Debug.Log("LoginCallBack:"+str);
		_gameCtl.InputFieldData.text = str;
	}
}
