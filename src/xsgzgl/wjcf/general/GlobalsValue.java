package xsgzgl.wjcf.general;

import xgxt.action.Base;

public class GlobalsValue {

	public static String xxpymc;// ѧУƴ������

	public static String[] xxdmValue = new String[] {};// ѧУ����

	public static String[] xxmcValue = new String[] {};// ѧУ

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
