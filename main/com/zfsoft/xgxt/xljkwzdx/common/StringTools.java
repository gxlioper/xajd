/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����02:23:52 
 */  
package com.zfsoft.xgxt.xljkwzdx.common;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @�๦������: 
 * @���ߣ���־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����02:23:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class StringTools {
	
	static public String strOutNull(String str){
		if(str == null){
			str = "";
		}
		return str;
	}
	/**
	 * 
	 * @����: ��Textarea�������ת��Ϊhtml��ʽ
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-8 ����05:20:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * String �������� 
	 */
	static public String StringToText(String str){
		if(str == null){
			str = "";
		}
		return str.replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp");
	}
	/**
	 * 
	 * @����: ��html��ʽ������ת��Ϊtextarea
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-8 ����05:20:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * String �������� 
	 */
	static public String TextToString(String str){
		if(str == null){
			str = "";
		}
		return str.replaceAll("<br/>", "\\n").replaceAll("&nbsp", "");
	}
	/**
	 * 
	 * @����: ȥ��hmtl��ǩ��<br/>,&nbsp��
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-8 ����05:20:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * String �������� 
	 */
	static public String TextToString1(String str){
		if(str == null){
			str = "";
		}
		return str.replaceAll("<br/>", "").replaceAll("&nbsp", "");
	}
}
