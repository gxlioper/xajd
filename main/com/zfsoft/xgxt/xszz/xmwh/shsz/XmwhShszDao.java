/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.shsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-��˵�����������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhShszDao extends SuperDAOImpl<XmwhShszForm> {

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhShszForm model)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @����:�߼���ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhShszForm model,
			User user) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @����:����xmdm��ѯ���õļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-24 ����10:03:45
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		String sql = "select * from xg_xszz_new_zzxmshtzb where xmdm=?";
		String[] input = {xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}	
	
	/**
	 * 
	 * @����:����xmdm������Ŀ���롢����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-24 ����10:03:45
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKshxm(String xmdm) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		String sql = " select a.kshxmdm dm,b.xmmc mc,b.sqxn sqxn,b.sqxq sqxq  from  xg_xszz_new_zzxmshtzb a,xg_xszz_new_zzxmdmb b" ;
		sql += 	" where a.xmdm=? and a.kshxmdm=b.xmdm ";
		String[] input = {xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-22 ����07:20:02
	 * @�޸ļ�¼: 
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runShsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;
		
		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_xszz_new_zzxmshtzb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_xszz_new_zzxmshtzb(xmdm,kshxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
				}
			}
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmshtzb");
		super.setKey("xmdm");
	}

}
