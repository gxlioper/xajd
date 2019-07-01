/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-2 ����09:41:16 
 */  
package com.zfsoft.xgxt.base.check;

import java.util.HashMap;
import java.util.List;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ��������֤����
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-2 ����09:41:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public interface CheckCondition {
	
	 static final String LESS_THAN = "<";//С��
	 static final String GREAT_THAN = ">";//����
	 static final String EQUAL_TO = "=";//����
	 static final String GREAT_THAN_OR_EQUAL_TO = ">=";//���ڵ���
	 static final String LESS_THAN_OR_EQUAL_TO = "<=";//С�ڵ���
	 static final String IN = "in";//����
	 static final String NOT_IN = "notin";//������
	 static final String EQUAL_PM = "!<";//���ڱȽ�����
	 static final String LESS_THAN_OR_EQUAL_TO_IN = "<=in";//С�ڵ���(���ж�����ֵ)
	
	
	
	/**
	 * 
	 * @����:У������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����09:44:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh �û�
	 * @param conditions ������
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> checkCondition(String xh, List<HashMap<String,String>> conditions) throws Exception;
	
	/**
	 * �Ƿ�����ȫ������
	 */
	public boolean checkConditionBoolean(String xh, List<HashMap<String,String>> conditions) throws Exception;
}
