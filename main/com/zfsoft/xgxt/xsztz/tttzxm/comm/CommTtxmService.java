/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-25 ����08:52:53 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.comm;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-25 ����08:52:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CommTtxmService extends SuperServiceImpl<TttzxmForm, CommTtxmDao> {
	/**
	 * 
	 * @����:��ѯѧ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����11:05:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxx(String xh,String xmdm,String[] xhs){
		return dao.getXsxx(xh,xmdm,xhs);
	}
	
	/**
	 * 
	 * @����:ѧ���б��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����05:39:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(TttzxmForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		return dao.getXsxxList(model, user);
	}
	
	/**
	 * @����:��������ظ� һ������һ����Ŀ��ֻ�ܲμ�һ���Ŷ�(һ����ֻ�ܲμ�һ����Ŀ)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����11:32:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public String checkIsNotExists(String[] xh,String xmdm,String ttsqid){
		return dao.checkIsNotExists(xh, xmdm, ttsqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����Ŷӳ�Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����01:49:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTtcy(String ttsqid,String xmdm,String[] xh) throws Exception{
		return dao.saveTtcy(ttsqid, xmdm, xh);
	}
	
	/**
	 * 
	 * @����: ����Ŷ������Ƿ��ظ�(ͬһ��Ŀ�¶������Ʋ������ظ�)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����02:19:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tdmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNameIsNotExists(String tdmc,String xmdm,String ttsqid,String flag){
		return dao.checkNameIsNotExists(tdmc, xmdm, ttsqid, flag);
	}
	
	/**
	 * 
	 * @����: ����Ƿ��ѽ���׶�ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����02:52:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotInJdwh(String jdcy){
		return dao.checkIsNotInJdwh(jdcy);
	}
	
	/**
	 * 
	 * @����:��ȡ�ų��ӳ�������ѧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-27 ����09:01:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ttsqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDyxxNotDz(String ttsqid,String dzxh){
		return dao.getDyxxNotDz(ttsqid, dzxh);
	}
	
	/**
	 * 
	 * @����: ��ȡ��Ŀ��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-27 ����09:41:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmxxMap(String xmdm){
		return dao.getXmxxMap(xmdm);
	}
	
	/**
	 * 
	 * @����:��ȡ�ӳ���Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-29 ����09:50:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xmdm
	 * @param xhs
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDzxx(String xh){
		return dao.getDzxx(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�������Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-29 ����11:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delTtcy(String[] ids) throws Exception{
		return dao.delTtcy(ids);
	}
	
}
