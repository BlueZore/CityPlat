package webservice;

import java.util.HashMap;

/**
 * WEB服务器连接参数
 * 
 * @author kam
 * 
 */
public class WEBServiceParam extends WEBServiceConfig {

	HashMap<String, String> ServiceParams;

	/**
	 * 绑定Web方法
	 * 
	 * @param WebMethod
	 * @return
	 */
	HashMap<String, String> ServiceParam(String WebMethod) {
		ServiceParams = new HashMap<String, String>();

		ServiceParams.put("NAMESPACE", namespaceString);

		ServiceParams.put("URL", uRLAddresString + "/" + webServiceString);

		ServiceParams.put("METHOD_NAME", WebMethod);

		ServiceParams.put("SOAP_ACTION", namespaceString + WebMethod);

		return ServiceParams;
	}
	
	/**
	 * 登录
	 * 
	 * @return
	 */
	public HashMap<String, String> DoSetLoginCheckingParam() {
		return ServiceParam("DoSetLoginChecking");
	}

	/**
	 * 获取类
	 * 
	 * @return
	 */
	public HashMap<String, String> GetEventClassifyListParam() {
		return ServiceParam("GetEventClassifyList");
	}

	/**
	 * 事件添加
	 * 
	 * @return
	 */
	public HashMap<String, String> AddCityEventParam() {
		return ServiceParam("AddCityEvent");
	}
	
	/**
	 * 获取已上传事件
	 * 
	 * @return
	 */
	public HashMap<String, String> GetMyCityEventModelParam() {
		return ServiceParam("GetMyCityEventModel");
	}

	/**
	 * 获取已上传事件
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventModelParam() {
		return ServiceParam("GetCityEventModel");
	}

	/**
	 * 获取事件Model
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventListParam() {
		return ServiceParam("GetCityEventList");
	}

	/**
	 * 获取办理
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventFlowListParam() {
		return ServiceParam("GetCityEventFlowList");
	}

	/**
	 * 办理
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventProcessModelParam() {
		return ServiceParam("GetCityEventProcessModel");
	}

	/**
	 * 办理事件
	 * 
	 * @return
	 */
	public HashMap<String, String> UpdateCityEventProcessParam() {
		return ServiceParam("UpdateCityEventProcess");
	}

	/**
	 * 获取办理内容
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventFlowModelParam() {
		return ServiceParam("GetCityEventFlowModel");
	}
}
