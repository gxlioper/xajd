/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-28 ����02:44:59 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-10-28 ����02:44:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwmarkService extends SuperServiceImpl<RcxwmarkForm, RcxwmarkDao> {
	/**
	 *
	 * @����: �Ѵ����ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-31 ����10:54:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYclList(RcxwmarkForm t, User user)
	throws Exception {
       // TODO �Զ����ɷ������
       return dao.getYclList(t, user);
    }
	
	/**
	 * 
	 * @����: ȡ�������Ͷ�дȨ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-1 ����02:20:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param username
	 * @param dyym
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	 public HashMap<String, String> getWriteAble(String username,String dyym){
		 return dao.getWriteAble(username, dyym);
	 }
	 
	 /**
	  * 
	  * @����:��ȡ�鿴����
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-11-1 ����06:56:53
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param id
	  * @return
	  * HashMap<String,String> �������� 
	  * @throws
	  */
	 public HashMap<String, String> getCkData(String id){
		 return dao.getCkData(id);
	 }
	 
	 /**
	  * 
	  * @����:��ȡ�鿴����
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-11-1 ����06:56:53
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param id
	  * @return
	  * HashMap<String,String> �������� 
	  * @throws
	  */
	 public HashMap<String, String> getCkDataWcl(String rcxwjgid){
		 return dao.getCkDataWcl(rcxwjgid);
	 }
	 
	 /**
	  * 
	  * @����: �������ñ���
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-11-1 ����07:00:03
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * boolean �������� 
	  * @throws
	  */
	 public boolean insertData(String[] rcxwjgids,String jxdm,String pjxn,String bz,String czr){
		 return dao.insertData(rcxwjgids, jxdm, pjxn, bz, czr);
	 }
}
