package xgxt.pjpy;

import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������ѧУҵ��-action��
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

public class PjpyYw {


	// �ʾ����汾1
	// ĿǰѧУ:
	// �������У����ݴ�ѧ
	public static final String[] WJDC_FIRST = new String[] { Globals.XXDM_NBCSZYJSXY,
			Globals.XXDM_GZDX };
	
	/**
	 * �ж��ʾ�����Ƿ�汾1
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean isWjdcFirst(String xxdm) {

		boolean flag = false;

		if (WJDC_FIRST != null && WJDC_FIRST.length > 0) {
			for (String xx : WJDC_FIRST) {
				if (xxdm.equalsIgnoreCase(xx)) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
	
	/**
	 * �ж��Ƿ��㽭��ý(����С��)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Cpxz(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý,���ݴ�ѧ(��������)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Tjsz(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,���ݴ�ѧ�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY, Globals.XXDM_GZDX };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(�������)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jdsz(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
	
	/**
	 * �ж��Ƿ��㽭��ý(������ά��)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Zyf(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(�ۺϷ�ά��)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Zhf(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(��ѧ������)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jxjsq(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(��ѧ������_У��)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jxjsq_xw(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
	
	/**
	 * �ж��Ƿ��㽭��ý(�����ƺ�����)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Rychsq(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(��ѧ����)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jxjjg(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(�����ƺŽ��)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Rychjg(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * �ж��Ƿ��㽭��ý(�����ƺŽ��)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Bbtj(String xxdm) {
		boolean flag = false;
		// ѧУ�������㽭��ý,�����ȣ�
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
}
