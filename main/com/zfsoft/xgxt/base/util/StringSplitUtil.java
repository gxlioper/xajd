/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-22 ����11:42:01 
 */
package com.zfsoft.xgxt.base.util;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: String���ͷָ�
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-22 ����11:42:01
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StringSplitUtil {
	/**
	 * 
	 * @����:����ת�����ַ�������splitFlag�ָ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-22 ����11:45:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param input
	 * @param splitFlag �ָ���
	 * @return String ��������
	 */
	public static String arrayToStr(String input[], String splitFlag) {
		if (null == input || input.length <= 0) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			str.append(input[i]);
			if (i + 1 != input.length) {
				str.append(splitFlag);
			}
		}
		return str.toString();
	}

	/**
	 * 
	 * @����:����ת�����ַ���,�Զ��ŷָ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-22 ����11:47:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param input
	 * @return String ��������
	 */
	public static String arrayToStr(String input[]) {
		return arrayToStr(input, ",");
	}

	/**
	 * 
	 * @����:�ַ���ת��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-22 ����11:45:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @param splitFlag �ָ���
	 * @return String[] ��������
	 */
	public static String[] strToArray(String str, String splitFlag) {
		if (null == str) {
			return null;
		}
		String[] inputs = str.split(splitFlag);
		return inputs;
	}

	/**
	 * 
	 * @����:�ַ���ת��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-22 ����11:47:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return String[] ��������
	 */
	public static String[] strToArray(String str) {
		return strToArray(str, ",");
	}
}
