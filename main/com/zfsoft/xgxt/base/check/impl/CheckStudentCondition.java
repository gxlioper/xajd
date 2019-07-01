/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-2 ����09:45:35 
 */  
package com.zfsoft.xgxt.base.check.impl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ϵͳ��У��ѧ���Ƿ��������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-2 ����09:45:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CheckStudentCondition implements CheckCondition {

	private Log logger = LogFactory.getLog(CheckStudentCondition.class);

	
	/*
	 * 
	 * ����: @see com.zfsoft.xgxt.base.check.CheckCondition#checkCondition(java.lang.String, java.util.List)
	 * @param xh
	 * @param conditions
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.check.CheckCondition#checkCondition(java.lang.String, java.util.List)
	 */
	public List<HashMap<String,String>> checkCondition(String xh, List<HashMap<String, String>> conditions) throws Exception {
	
		List<HashMap<String,String>> checkResult = new ArrayList<HashMap<String,String>>();
		
		if (conditions == null || conditions.size() == 0){
			logger.debug("����Ҫ��֤��������Ĭ��Ϊ����������");
			return null;
		}
		
		//ѭ����֤
		for (HashMap<String,String> condition : conditions){
			String ffm = condition.get("ffm");
			
			if (StringUtils.isNull(ffm)){
				logger.error("δ������֤������");
				continue;
			}
			
			String[] checkInfo = ffm.split("#");
			
			if (checkInfo == null || checkInfo.length != 2){
				logger.error("�����Ϲ������֤������");
			}
			
			Class<?> t = Class.forName(checkInfo[0]);
			Object o = t.newInstance();
			Method method = t.getMethod(checkInfo[1], new Class[]{String.class,HashMap.class});
			
			//���������֤������ ��ȡ��ֵ֤��
			String checkValue ="";
			if ("10466".equals(Base.xxdm)) {
				String[] param = xh.split("&&");
				String value = "";
				if ("jtpj_sfqsz".equals(condition.get("tjdm"))) {
					value=param[0]+"@@"+param[1];
				}else {
					value=param[0];
				}
				checkValue = (String) method.invoke(o, value,condition);//����֤������֤
			}else {
				checkValue = (String) method.invoke(o, xh,condition);
			}
			boolean success = compare(condition.get("tjz"), checkValue, condition.get("tjgx"));
			
			condition.put("result", String.valueOf(success));
			checkResult.add(condition);
		}
		
		return checkResult;
	}
	/**
	 * �Ƿ�����ȫ������
	 */
	public boolean checkConditionBoolean(String xh, List<HashMap<String,String>> conditions) throws Exception {
		if (conditions == null || conditions.size() == 0){
			logger.debug("����Ҫ��֤��������Ĭ��Ϊ����������");
			return true;
		}
		
		//ѭ����֤
		for (HashMap<String,String> condition : conditions){
			String ffm = condition.get("ffm");
			
			if (StringUtils.isNull(ffm)){
				logger.error("δ������֤������");
				continue;
			}
			
			String[] checkInfo = ffm.split("#");
			
			if (checkInfo == null || checkInfo.length != 2){
				logger.error("�����Ϲ������֤������");
			}
			
			Class<?> t = Class.forName(checkInfo[0]);
			Object o = t.newInstance();
			Method method = t.getMethod(checkInfo[1], new Class[]{String.class,HashMap.class});
			
			//���������֤������ ��ȡ��ֵ֤��
			String checkValue = (String) method.invoke(o, xh,condition);
			boolean success = compare(condition.get("tjz"), checkValue, condition.get("tjgx"));
			if(!success){
				return false;
			}
		}
		return true;
	}

	
	//�Ƚ���֤
	private boolean compare(String value ,String checkValue ,String tjgx){
		
		if (StringUtil.isNull(checkValue)){
			return false;
		}
		
		if(checkValue.contains("||")){
			String [] cv = checkValue.split("\\|\\|");

			if (StringUtil.isNull(cv[0])||StringUtil.isNull(cv[1])||"null".equals(cv[0])||"null".equals(cv[1])){
				return false;
			}
			
			//��ʱ��֧�ִ�������������
			if (EQUAL_TO.equals(tjgx)){
				//����
				return value.equals(cv[0])||value.equals(cv[1]);
			} else if (LESS_THAN.equals(tjgx)){
				//С��
				return (Double.valueOf(cv[0]) < Double.valueOf(value))||(Double.valueOf(cv[1]) < Double.valueOf(value));
			} else if (GREAT_THAN.equals(tjgx)){
				//����
				return (Double.valueOf(cv[0]) > Double.valueOf(value))||(Double.valueOf(cv[1]) > Double.valueOf(value));
			} else if (LESS_THAN_OR_EQUAL_TO.equals(tjgx)){
				//С�ڵ���
				return (Double.valueOf(cv[0]) <= Double.valueOf(value))||(Double.valueOf(cv[1]) <= Double.valueOf(value));
			} else if (GREAT_THAN_OR_EQUAL_TO.equals(tjgx)){
				//���ڵ���
				return (Double.valueOf(cv[0]) >= Double.valueOf(value))||(Double.valueOf(cv[1]) >= Double.valueOf(value));
			} else if (IN.equals(tjgx)){
				return (Integer.valueOf(cv[0]) >= 1)||(Integer.valueOf(cv[1])>= 1);
			} else if (NOT_IN.equals(tjgx)){
				return (Integer.valueOf(cv[0]) == 0)||(Integer.valueOf(cv[1]) == 0);
			} else if (LESS_THAN_OR_EQUAL_TO_IN.equals(tjgx)){
				return (Integer.valueOf(cv[0]) >= 1)||(Integer.valueOf(cv[1]) >= 1);
			} else if (EQUAL_PM.equals(tjgx)){
				//���ڱȽ�����
				return (Double.valueOf(cv[0]) <= Double.valueOf(value))||(Double.valueOf(cv[1]) <= Double.valueOf(value));
			}
		}else{
			if (EQUAL_TO.equals(tjgx)){
				//����
				return value.equals(checkValue);
			} else if (LESS_THAN.equals(tjgx)){
				//С��
				return Double.valueOf(checkValue) < Double.valueOf(value);
			} else if (GREAT_THAN.equals(tjgx)){
				//����
				return Double.valueOf(checkValue) > Double.valueOf(value);
			} else if (LESS_THAN_OR_EQUAL_TO.equals(tjgx)){
				//С�ڵ���
				return Double.valueOf(checkValue) <= Double.valueOf(value);
			} else if (GREAT_THAN_OR_EQUAL_TO.equals(tjgx)){
				//���ڵ���
				return Double.valueOf(checkValue) >= Double.valueOf(value);
			} else if (IN.equals(tjgx)){
				return Integer.valueOf(checkValue) >= 1;
			} else if (NOT_IN.equals(tjgx)){
				return Integer.valueOf(checkValue) == 0;
			} else if (LESS_THAN_OR_EQUAL_TO_IN.equals(tjgx)){
				return Integer.valueOf(checkValue) >= 1;
			} else if (EQUAL_PM.equals(tjgx)){
				//���ڱȽ�����
				return Double.valueOf(checkValue) <= Double.valueOf(value);
			}
		}
		
		return false;
	}
	
}
