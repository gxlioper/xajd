/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:43:12 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ά��_��Ŀ����
 * @���ߣ�Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:43:12 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmxzService extends SuperServiceImpl<XmxzForm, XmxzDao>{
	private XmxzDao dao = new XmxzDao();
	
	public XmxzService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: �����ж��Ƿ�����Ŀ���ƴ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-10 ����11:42:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistXzmc(XmxzForm model) {
		boolean flag = false;
		String num = dao.isExistXzmc(model);
		if(!"0".equalsIgnoreCase(num)){
			flag = true;
		}
    	return  flag;
	}
	
	/** 
	 * @����: �ж�����������������������Ƿ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����08:14:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkXzForPjjg(String value)throws Exception{
    	String resultXzmc = "";
    	String[] xzmc = dao.xzCheckExistForPjjg(value);
    	for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXzmc+=xzmc[i];
			}else{
				resultXzmc+=xzmc[i]+",";
			}
		}
		return resultXzmc;
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
	public String checkXzForPjxm(String value)throws Exception{
    	String resultXzmc = "";
    	String[] xzmc = dao.xzCheckExistForPjxm(value);
    	for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXzmc+=xzmc[i];
			}else{
				resultXzmc+=xzmc[i]+",";
			}
		}
		return resultXzmc;
	}
}
