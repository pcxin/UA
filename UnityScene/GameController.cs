using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GameController : MonoBehaviour {
	public Button btnLogin;
	public Button btnPay;
	public InputField InputFieldData;

	// Use this for initialization
	void Start () {
		SDKController.Instance();
		btnLogin.onClick.AddListener(delegate {
			SDKController.Instance().login();
		});
		btnPay.onClick.AddListener(delegate {
			SDKController.Instance().pay();
		});
	}
}