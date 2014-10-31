package com.cityplat;

import helper.FileHelper;


public class Method {

	/**
	 * 添加
	 * 
	 * @param model
	 * @param activity
	 */
	public void Add(Model model) {

		FileHelper helper = new FileHelper();

		helper.writeFile(model.getTable(),
				model.getField() + "&" + model.getModelString());
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param activity
	 */
	public void Delete() {
		Model model = new Model();

		FileHelper helper = new FileHelper();

		helper.writeFile(model.getTable(), "");
		
		
	}

	/**
	 * 获取实体
	 * 
	 * @param activity
	 * @return
	 */
	public Model getModel() {

		Model model = new Model();

		FileHelper helper = new FileHelper();

		String readString = helper.readFile(model.getTable());

		String[] tmpList = readString.split("&");

		if (tmpList.length > 1) {

			String[] tmp = tmpList[1].split("\\|");

			model.setLoginname(tmp[0]);
			model.setLoginpwd(tmp[1]);
			model.setUserid(tmp[2]);
			model.setUsername(tmp[3]);
			model.setToken(tmp[4]);
			model.setOrgid(tmp[5]);
			model.setFarmid(tmp[6]);
			model.setOrgname(tmp[7]);
			model.setOrglevel(tmp[8]);
			model.setLongitude(tmp[9]);
			model.setLatitude(tmp[10]);
		}

		return model;
	}

	/**
	 * 是否存在用户登录信息
	 * 
	 * @param activity
	 * @return
	 */
	public Boolean IsHave() {

		Model model = new Model();

		FileHelper helper = new FileHelper();

		String readString = helper.readFile(model.getTable());

		if (readString.trim().length() > 0) {

			String[] arrString = readString.split("&");

			if (arrString.length > 1) {

				if (arrString[1].trim().length() > 0) {
					return true;
				}
			}
		}

		return false;

	}
}
