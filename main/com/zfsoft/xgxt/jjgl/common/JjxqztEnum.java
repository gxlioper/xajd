package com.zfsoft.xgxt.jjgl.common;

/**
 *  
 * @���ƣ��ҽ�����״̬ö��
 * <br/>
 * @author 1036 ��С��
 * <br/>
 * @version 1.0
 * <br/>
 * @since 2014-07-23
 * <br/>
 * @��Ȩ zfsoft corp.
 * <br/>
 * @����
 * <ol>
 * 
 * 	<li><b>DJ:	���Խ�,�ҽ��������ͨ��������״̬,ѧ�����������״̬�ļҽ�����</b>
 * 
 *  <li><b>SJ�� �Խ̣���ʾ�üҽ������Ѿ���ѧ�����룬����ѧ����������������ҽ�����</b>
 *  
 *  <li><b>JX��������ѧ����ʾѧ���Խ�ͨ��������״̬��Ŀǰֻ����ʦά�������</b>
 *  
 *  <li><b>JS����������ʾ��ѧ�������ҽ̵�״̬��ѧ�����߹���Ա���Ա����</b>
 *  
 *  <li><b>WX����Ч����ʾ�����ҽ��������ϣ�ѧ����������ҽ̣�</b>
 *  
 * </ol>
 */
public enum JjxqztEnum {
	
	WPC("0","δ�ɳ�"),
	
	DJXYS("1" , "����Э����"),
	
	YCQ("2" , "�ѳ���"),
	
	YJXYS("3" , "�ѽ�Э����"),
	
	YGB("4" , "�ѹر�");
	
	private String code;
	
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private JjxqztEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "�ҽ�����״̬��[����="+this.code+",����=" + this.desc +"]";
	}
}
