/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:40:08 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ά��_��Ŀ����
 * @���ߣ�Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:39:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmlxService extends SuperServiceImpl<XmlxForm, XmlxDao>{
	private XmlxDao dao = new XmlxDao();
	
	public XmlxService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: �����ж��Ƿ�����Ŀ���ƴ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����08:13:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistLxmc(XmlxForm model) {
		boolean flag = false;
		String num = dao.isExistLxmc(model);
		if(!"0".equalsIgnoreCase(num)){
			flag = true;
		}
    	return  flag;
	}
	
	/** 
	 * @����: �ж�����������������������Ƿ����
	 * @���ߣ�Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����08:14:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkLxForPjjg(String value)throws Exception{
    	String resultLxmc = "";
    	String[] lxmc = dao.lxCheckExistForPjjg(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * @����: �ж�����������������Ŀ�����Ƿ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����08:15:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkLxForPjxm(String value)throws Exception{
    	String resultLxmc = "";
    	String[] lxmc = dao.lxCheckExistForPjxm(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * @����: ȡ��Ŀ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-10 ����11:33:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		return dao.getXmlx();
	}
}
