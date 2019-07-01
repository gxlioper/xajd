package xsgzgl.wjcf.general;

import xgxt.action.Base;

public class GlobalsValue {

	public static String xxpymc;// 学校拼音名称

	public static String[] xxdmValue = new String[] {};// 学校代码

	public static String[] xxmcValue = new String[] {};// 学校

	// ###########################end#####################################
	
	public static String getXxpymc(String xxdm) {

		for (int i = 0; i < xxdmValue.length; i++) {
			if (xxdm.equalsIgnoreCase(xxdmValue[i])) {
				xxpymc = xxmcValue[i];
				break;
			}
		}

		if (Base.isNull(xxpymc)) {
			xxpymc = "general";
		}

		return xxpymc;
	}
}
