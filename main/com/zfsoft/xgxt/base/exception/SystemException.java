package com.zfsoft.xgxt.base.exception;

import net.sf.json.JSONObject;

import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * ѧ��ϵͳ�����쳣��<br>
 * �����ڴ���ҵ���߼�ʱ�׳�������ʱ���쳣<br>
 * �׳��������쳣ʱ��Ҫ�����쳣��Ϣkey,<br>
 * ��Ϣ������������property�ļ�������÷���ο����췽��
 * @author qph
 * @version 2.0
 * ���ڣ�2013-4-16
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 2386276707218879014L;
	
	private String key;//�쳣���
	private String message;//�쳣��Ϣ

	public String getKey() {
		return key;
	}


	public SystemException(){
		super();
	}

	
	/**
	 * �����쳣��Ϣ���<br>
	 * �Զ���ȡ��property�ļ������õ��쳣��Ϣ<br>
	 * ��֧��ֱ�����쳣��Ϣ
	 * @param key �쳣��Ϣ���
	 */
	public SystemException(String key){
		this.key = key;
		this.message = MessageUtil.getText(key);
	}
	
	
	/**
	 * key�������÷�ͬ���Ϲ��췽��<br>
	 * �ڴ˻�����֧�����쳣��Ϣ���ݲ���<br>
	 * ���쳣��Ϣ����÷�**{0}**,{0}�ʹ���value����ֵ
	 * @param key �쳣��Ϣ���
	 * @param value ���쳣��Ϣ���ݵĲ���
	 */
	public SystemException(String key,Object value){
		this.key = key;
		this.message = MessageUtil.getText(key,value);
	}
	
	
	/**
	 * key�������÷�ͬ���Ϲ��췽��<br>
	 * ֧�����쳣��Ϣ���ݲ�������<br>
	 * �쳣��Ϣ����÷�**{0}**{1}**��{0}{1}���������ﴫ�ݵĲ������Դ�����
	 * @param key �쳣��Ϣ���
	 * @param values ���쳣��Ϣ���ݵĲ�������
	 */
	public SystemException(String key,Object[] values){
		this.key = key;
		this.message = MessageUtil.getText(key,values);
	}


	@Override
	public String getMessage() {
		
		if (null != this.message){
			return this.message;
		}
		return super.getMessage();
	}
	
	/**
	 * 
	 * @���� �����Ի��쳣��Ϣ����
	 * @param json
	 */
	public  SystemException(JSONObject json){
		this.key = json.getString("message");
		this.message = json.getString("message");
	}
	
}
