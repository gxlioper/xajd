package xgxt.xszz;

import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ������ѧУҵ��-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class XszzYw {

	// ��ѵ����_�ɼ���������ά������Ҫ����ͬ�����ݣ�
	// ĿǰѧУ:
	// �㽭��ý�����ݴ�ѧ
	public static final String[] JXCJ_JWTB = new String[] { Globals.XXDM_ZJCMXY,
			Globals.XXDM_GUIZHDX };
	
	/**
	 * �жϳɼ������Ƿ���Ҫ��������ͬ��
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsJwTb(String xxdm) {

		boolean flag = false;

		if (JXCJ_JWTB != null && JXCJ_JWTB.length > 0) {
			for (String xx : JXCJ_JWTB) {
				if (xxdm.equalsIgnoreCase(xx)) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

}
