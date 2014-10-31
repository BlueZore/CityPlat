package base;

import helper.CommonMethod;

import java.util.ArrayList;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import base.EventClassifyClass.EventClassifyInfo;

/**
 * ������
 * 
 * @author Kma
 * 
 */
public class CityPartClassifyClass {
	List<CityPartClassifyInfo> list;

	/**
	 * ��ʼ������
	 * 
	 * @param soapObject
	 */
	public CityPartClassifyClass(SoapObject soapObject) {
		list = new ArrayList<CityPartClassifyInfo>();
		for (int i = 0; i < soapObject.getPropertyCount(); i++) {
			CityPartClassifyInfo model = new CityPartClassifyInfo();
			model.setPartClassifyID(((SoapObject) soapObject.getProperty(i))
					.getProperty("PartClassifyID").toString());
			model.setClassifyName(((SoapObject) soapObject.getProperty(i))
					.getProperty("ClassifyName").toString());
			model.setParentClassifyID(((SoapObject) soapObject.getProperty(i))
					.getProperty("ParentClassifyID").toString());
			model.setConditionOfCase(CommonMethod
					.ToNullOrEmptyForStirng(((SoapObject) soapObject
							.getProperty(i)).getProperty("ConditionOfCase")
							.toString()));
			model.setConditionOfClose(CommonMethod
					.ToNullOrEmptyForStirng(((SoapObject) soapObject
							.getProperty(i)).getProperty("ConditionOfClose")
							.toString()));
			list.add(model);
		}
	}

	/**
	 * ���༯��
	 * 
	 * @return
	 */
	public List<CityPartClassifyInfo> getBigCityPartClassify() {
		List<CityPartClassifyInfo> tmpList = new ArrayList<CityPartClassifyInfo>();
		for (CityPartClassifyInfo model : list) {
			if (model.getParentClassifyID().equals(
					"00000000-0000-0000-0000-000000000000")) {
				tmpList.add(model);
			}
		}
		return tmpList;
	}

	/**
	 * С�༯��
	 * 
	 * @param parentCode
	 * @return
	 */
	public List<CityPartClassifyInfo> getSmallCityPartClassify(String parentCode) {
		List<CityPartClassifyInfo> tmpList = new ArrayList<CityPartClassifyInfo>();
		for (CityPartClassifyInfo model : list) {
			if (model.getParentClassifyID().equals(parentCode)) {
				tmpList.add(model);
			}
		}
		return tmpList;
	}

	/**
	 * ʵ��
	 * 
	 * @author Kma
	 * 
	 */
	public class CityPartClassifyInfo {
		String PartClassifyID;
		String ClassifyName;
		String ParentClassifyID;
		String ConditionOfCase;
		String ConditionOfClose;

		public String getPartClassifyID() {
			return PartClassifyID;
		}

		public void setPartClassifyID(String partClassifyID) {
			PartClassifyID = partClassifyID;
		}

		public String getClassifyName() {
			return ClassifyName;
		}

		public void setClassifyName(String classifyName) {
			ClassifyName = classifyName;
		}

		public String getParentClassifyID() {
			return ParentClassifyID;
		}

		public void setParentClassifyID(String parentClassifyID) {
			ParentClassifyID = parentClassifyID;
		}

		public String getConditionOfCase() {
			return ConditionOfCase;
		}

		public void setConditionOfCase(String conditionOfCase) {
			ConditionOfCase = conditionOfCase;
		}

		public String getConditionOfClose() {
			return ConditionOfClose;
		}

		public void setConditionOfClose(String conditionOfClose) {
			ConditionOfClose = conditionOfClose;
		}
	}
}
