/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����09:54:47 
 */  
package com.zfsoft.xgxt.xljkwzdx.common;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ�����ݴ�ѧ��
 * @�๦������: ��״̬����ת��Ϊ����
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-5-5 ����09:54:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZtToCnDesc {

	/** 
	 * @����:����ԤԼ״̬���뷵����������
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-5 ����09:53:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param String
	 * @return
	 */
	static public String yyztChange(String str){
		String returnstr = "";
		if("1".equals(str)){
			returnstr = "ԤԼ��";
		}else if("2".equals(str)){
			returnstr = "ԤԼ�ɹ�";
		}else if("3".equals(str)){
			returnstr = "ԤԼ�У�ѧ��ȡ����";
		}else if("4".equals(str)){
			returnstr = "ԤԼ�ɹ���ѧ��ȡ����";
		}else if("5".equals(str)){
			returnstr = "ԤԼʧ��";
		}
		return returnstr;
	}
	
	/** 
	 * @����:������ѯ״̬���뷵����������
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-7 ����01:36:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param String
	 * @return
	 */
	static public String zxztChange(String str){
		String returnstr = "";
		if("0".equals(str)){
			returnstr = "����ѯ";
		}else if("1".equals(str)){
			returnstr = "����ѯ";
		}else if("2".equals(str)){
			returnstr = "δ��ѯ";
		}
		return returnstr;
	}
	
	/** 
	 * @����:���ݽ��̶ܳȴ��뷵����������
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-9 ����10:53:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param String
	 * @return
	 */
	static public String jscdChange(String str){
		String returnstr = "";
		if("hph".equals(str)){
			returnstr = "�����";
		}else if("ybph".equals(str)){
			returnstr = "һ�����";
		}else if("za".equals(str)){
			returnstr = "�谭";
		}
		return returnstr;
	}
	
	/** 
	 * @����:���ݽ��̶ܳȴ��뷵����������
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-9 ����11:07:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param String
	 * @return
	 */
	static public String yzcdpgChange(String str){
		String returnstr = "";
		if("hyz".equals(str)){
			returnstr = "������";
		}else if("bjyz".equals(str)){
			returnstr = "�Ƚ�����";
		}else if("ybcd".equals(str)){
			returnstr = "һ��̶�";
		}else if("jq".equals(str)){
			returnstr = "����";
		}else if("bswt".equals(str)){
			returnstr = "��������";
		}
		return returnstr;
	}
	
	/** 
	 * @����:�Ƿ�ת����뷵����������
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-9 ����11:12:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param String
	 * @return
	 */
	static public String sfxyzjChange(String str){
		String returnstr = "";
		if("1".equals(str)){
			returnstr = "��";
		}else if("2".equals(str)){
			returnstr = "��";
		}
		return returnstr;
	}
	
	/** 
	 * @����:�Ƿ�ԤԼ�´���ѯ���뷵����������
	 * @���ߣ���־��[���ţ�1060]
	 * @���ڣ�2014-5-9 ����11:14:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param String
	 * @return
	 */
	static public String sfyyxczxChange(String str){
		String returnstr = "";
		if("1".equals(str)){
			returnstr = "��";
		}else if("2".equals(str)){
			returnstr = "��";
		}
		return returnstr;
	}
}
