package webservice;

import java.util.HashMap;

/**
 * WEB���������Ӳ���
 * 
 * @author kam
 * 
 */
public class WEBServiceParam extends WEBServiceConfig {

	HashMap<String, String> ServiceParams;

	/**
	 * ��Web����
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
	 * ��¼
	 * 
	 * @return
	 */
	public HashMap<String, String> DoSetLoginCheckingParam() {
		return ServiceParam("DoSetLoginChecking");
	}

	/**
	 * ��ȡ��
	 * 
	 * @return
	 */
	public HashMap<String, String> GetEventClassifyListParam() {
		return ServiceParam("GetEventClassifyList");
	}

	/**
	 * �¼����
	 * 
	 * @return
	 */
	public HashMap<String, String> AddCityEventParam() {
		return ServiceParam("AddCityEvent");
	}
	
	/**
	 * ��ȡ���ϴ��¼�
	 * 
	 * @return
	 */
	public HashMap<String, String> GetMyCityEventModelParam() {
		return ServiceParam("GetMyCityEventModel");
	}

	/**
	 * ��ȡ���ϴ��¼�
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventModelParam() {
		return ServiceParam("GetCityEventModel");
	}

	/**
	 * ��ȡ�¼�Model
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventListParam() {
		return ServiceParam("GetCityEventList");
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventFlowListParam() {
		return ServiceParam("GetCityEventFlowList");
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventProcessModelParam() {
		return ServiceParam("GetCityEventProcessModel");
	}

	/**
	 * �����¼�
	 * 
	 * @return
	 */
	public HashMap<String, String> UpdateCityEventProcessParam() {
		return ServiceParam("UpdateCityEventProcess");
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public HashMap<String, String> GetCityEventFlowModelParam() {
		return ServiceParam("GetCityEventFlowModel");
	}
}
