/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-8 ����02:22:35 
 */  
package com.zfsoft.extend.dataFormat;

/**
 * �ֶθ�ʽ�ӿ�
 * 
 * @author 982
 * 
 */
public interface IField {
	/**
	 * ��ʽ���
	 */
	public boolean check(String value);

	/**
	 * ��ȡ������Ϣ
	 *
	 */
	public String getErrorMessage(String value);

}
