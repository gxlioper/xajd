/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-24 ����03:58:44 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���--��Ϣ���ģ��
 * @�๦������: ��Ϣ���Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��10�� ����7:16:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XxjgService extends SuperServiceImpl<XxjgForm,XxjgDao>{

	/** 
	 * @����:�ж���Ϣ������Ƿ�����ĳѧ����¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����1:20:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(XxjgForm xxjgForm) {
		return dao.isExist(xxjgForm);
	}

	/** 
	 * @����:�ж���Ϣ����н����ű���Ƿ��ѱ�ʹ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����1:20:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isJsxbhRepeat(XxjgForm xxjgForm) {
		return dao.isJsxbhRepeat(xxjgForm);
	}
	
	/**
	 * @throws Exception  
	 * @����:��дgetModel��������ڵ�֧������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����1:20:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public XxjgForm getModel(String jgid) throws Exception{
		return dao.getModel(jgid);
	}


	/** 
	 * @����:����id�����ȡXxjgForm����List
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����5:06:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * List<XxjgForm> �������� 
	 * @throws 
	 */
	public List<XxjgForm> getXxjgFormList(String[] ids) {
		List<HashMap<String,String>> mapList = dao.getXxjgFormList(ids);
		List<XxjgForm> xxjgFormList = new ArrayList<XxjgForm>();
		for(HashMap<String,String> map:mapList){
			XxjgForm xxjgForm = new XxjgForm();
			xxjgForm.setXh(map.get("xh"));
			xxjgForm.setSzdzb(map.get("szdzb"));
			xxjgForm.setSzdzbmc(map.get("szdzbmc"));
			xxjgForm.setSfsn(map.get("sfsn"));
			xxjgForm.setJsdzz(map.get("jsdzz"));
			xxjgForm.setSqdw(map.get("sqdw"));
			xxjgForm.setDfjzrq(map.get("dfjzrq"));
			xxjgForm.setSfkjhyzm(map.get("sfkjhyzm"));
			xxjgForm.setJsxbh(map.get("jsxbh"));
			xxjgFormList.add(xxjgForm);
		}
		return xxjgFormList;
	}

	/**
	 * 
	 * @����:����֯��ϵ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-18 ����05:37:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getDzcgxJgMap(String jgid){
		return dao.getDzcgxJgMap(jgid);
	}
}
