/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:23:06 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-28 ����05:23:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddJgService extends SuperServiceImpl<CxddJgForm, CxddJgDao> {
	public List<HashMap<String, String>> getCxdjList(String cxdj){
		return dao.getCxdjList(cxdj);
	}
	
	public String getbjdm(String xh){
		return dao.getbjdm(xh);
	}
	
	/**
	 * 
	 * @����: �۲�����ɼ�Map
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getZcfsList(CxddJgForm t,String xmmc) {
		return dao.getZcfsList(t, xmmc);
	}
	
	/**
	 * @description	�� ��ȡƽ���ɼ�������ʡ����ҽҩ�ߵ�ְҵѧУ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-10 ����09:57:29
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getPjcjAndPm(CxddJgForm t) {
		return dao.getPjcjAndPm(t);
	}
	
	/**
	 * @description	�� ��ȡ�۲��ܷ֣�����ҽҩ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-10 ����02:30:06
	 * @param t
	 * @param xmmc
	 * @return
	 */
	public HashMap<String,String> getZcfsTotal(CxddJgForm t,String xmmc) {
		return dao.getZcfsTotal(t, xmmc);
	} 
	/**
	 * 
	 * @����:��ȡѧ��������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:05:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxx(String xh){
		return dao.getXsjbxx(xh);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:08:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjrs(String bjdm){
		return dao.getBjrs(bjdm);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:20:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXnxqStr(String xn,String xq){
		String xqmc = Base.getXqmcForXqdm(xq);
		xn = xn.replace("-", "/");
		return xn+xqmc;
	}
	
	/**
	 * 
	 * @����:��ȡ�ɼ�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:21:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjList(CxddJgForm t){
		return dao.getCjList(t);
	}
	
	/**
	 * 
	 * @����: ��ȡ�������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:17:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjjgList(CxddJgForm t){
		return dao.getPjjgList(t);
	}
	
	/**
	 * 
	 * @����: ��ȡΥ�ʹ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:40:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjcfList(CxddJgForm t){
		return dao.getWjcfList(t);
	}
	
	/**
	 * @description	�� ��ȡ�ۺϳɼ�����
	 * @author 		�� ������1282��
	 * @date 		��2017-11-13 ����01:52:37
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String getZhcjPm(String xh,String xn,String xq){
		return dao.getZhcjPm(xh,xn,xq);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���濪ѧ��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����03:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveKxxx(CxddJgForm form) throws Exception{
		dao.deleteSzb();
		boolean rs = dao.runInsertSzb(form);
		return rs;
	}
	
	/**
	 * 
	 * @����: ��ȡ��ѧ�������ñ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����04:11:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getKxCsszb(){
		return dao.getKxCsszb();
	}
	
	/**
	 * 
	 * @����: ��ȡѧ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����05:30:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXfsj(String xh){
		return dao.getXfsj(xh);
	}
}
