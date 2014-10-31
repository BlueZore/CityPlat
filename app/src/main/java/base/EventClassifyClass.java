package base;

import helper.CommonMethod;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

public class EventClassifyClass {

	List<EventClassifyInfo> list;

	/**
	 * 初始化集合
	 * 
	 * @param soapObject
	 */
	public EventClassifyClass(SoapObject soapObject) {
		list = new ArrayList<EventClassifyInfo>();
		for (int i = 0; i < soapObject.getPropertyCount(); i++) {
			EventClassifyInfo model = new EventClassifyInfo();
			model.setEventClassifyID(((SoapObject) soapObject.getProperty(i))
					.getProperty("EventClassifyID").toString());
			model.setClassifyName(((SoapObject) soapObject.getProperty(i))
					.getProperty("ClassifyName").toString());
			model.setParentCode(((SoapObject) soapObject.getProperty(i))
					.getProperty("ParentCode").toString());
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
	 * 大类集合
	 * 
	 * @return
	 */
	public List<EventClassifyInfo> getBigEventClassify() {
		List<EventClassifyInfo> tmpList = new ArrayList<EventClassifyInfo>();
		for (EventClassifyInfo model : list) {
			if (model.getParentCode().equals(
					"00000000-0000-0000-0000-000000000000")) {
				tmpList.add(model);
			}
		}
		return tmpList;
	}

	/**
	 * 小类集合
	 * 
	 * @param parentCode
	 * @return
	 */
	public List<EventClassifyInfo> getSmallEventClassify(String parentCode) {
		List<EventClassifyInfo> tmpList = new ArrayList<EventClassifyInfo>();
		for (EventClassifyInfo model : list) {
			if (model.getParentCode().equals(parentCode)) {
				tmpList.add(model);
			}
		}
		return tmpList;
	}

	/**
	 * 实体
	 * 
	 * @author Kma
	 * 
	 */
	public class EventClassifyInfo {
		String EventClassifyID;
		String ClassifyName;
		String ParentCode;
		String ConditionOfCase;
		String ConditionOfClose;

		public String getParentCode() {
			return ParentCode;
		}

		public void setParentCode(String parentCode) {
			ParentCode = parentCode;
		}

		public String getEventClassifyID() {
			return EventClassifyID;
		}

		public void setEventClassifyID(String eventClassifyID) {
			EventClassifyID = eventClassifyID;
		}

		public String getClassifyName() {
			return ClassifyName;
		}

		public void setClassifyName(String classifyName) {
			ClassifyName = classifyName;
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
