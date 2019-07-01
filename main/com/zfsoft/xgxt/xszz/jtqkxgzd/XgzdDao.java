/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-4 ����10:35:48 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-4 ����10:35:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XgzdDao extends SuperDAOImpl<XgzdForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XgzdForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XgzdForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(XgzdForm.class);
		this.setKey("guid");
		this.setTableName("xg_xszz_new_zdcjpzb");
	}
	
	/**
	 * 
	 * @����: ��ͥ��������ֶ�������Ϣ��ѯ,��xssx�ֶ�˳������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-4 ����01:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnmk
	 * @param xxdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtqkdcZdxx(String gnmk,String xxdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid,zddm,zdmc,sfbt from xg_xszz_new_zdcjpzb");
		sql.append(" where gnmk = ?");
		sql.append(" and xxdm = ?");
		sql.append(" order by to_number(xssx)");
		return dao.getListNotOut(sql.toString(), new String[]{gnmk,xxdm});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����ֶα���Ǳ�����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-5 ����01:46:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveData(XgzdForm model) throws Exception{
		String[] guids = model.getGuids();
		String[] sfbts = model.getSfbts();
		List<String[]> paralist = new ArrayList<String[]>();
		for (int i=0;i<guids.length;i++) {
			List<String> paralslist = new ArrayList<String>();
			paralslist.add(sfbts[i]);
			paralslist.add(guids[i]);
			paralist.add(paralslist.toArray(new String[]{}));
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_new_zdcjpzb set sfbt = ? ");
		sql.append(" where guid = ?");
		int[] len = dao.runBatch(sql.toString(), paralist);
		return len.length > 0 ? true:false;
	}

}
