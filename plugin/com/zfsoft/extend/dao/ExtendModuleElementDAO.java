/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-3 ����09:56:17 
 */  
package com.zfsoft.extend.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendModuleElement;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-3 ����09:56:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendModuleElementDAO extends SuperDAOImpl<ExtendModuleElement> {
	
	public boolean updateExtendModuleElement(List<ExtendModuleElement> extendModuleElements){
		String sql = "UPDATE ZFXG_EXTEND_MODULE_ELEMENT SET (SFSH,SHLC)=(?,?) WHERE ID = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (ExtendModuleElement ele : extendModuleElements) {
			params.add(new String[]{ele.getSfsh(),ele.getShlc(),ele.getId()});
		}
		boolean r = Boolean.TRUE;
		try {
			dao.runBatch(sql, params);
		} catch (SQLException e) {
			r = Boolean.FALSE;
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @����:����ģ��ID��ȡ��Ԫ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-4 ����11:23:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param moduleID
	 * @return
	 * @throws Exception
	 * List<ExtendModuleElement> �������� 
	 * @throws
	 */
	public List<ExtendModuleElement> getExtendModuleElements(String moduleID)throws Exception{
		String sql = "SELECT * FROM ZFXG_EXTEND_MODULE_ELEMENT WHERE MID = ? ORDER BY to_number(XSSX)";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{moduleID});
		if(listNotOut == null || listNotOut.size() == 0){
			return null;
		}
		List<ExtendModuleElement> list = new ArrayList<ExtendModuleElement>();
		
		for (HashMap<String, String> extendModuleElement : listNotOut) {
			String id = extendModuleElement.get("id");
			String mc = extendModuleElement.get("mc");
			String sfsh = extendModuleElement.get("sfsh");
			String shlc = extendModuleElement.get("shlc");
			String sm = extendModuleElement.get("sm");
			String mid = extendModuleElement.get("mid");
			String xssx = extendModuleElement.get("xssx");
			list.add(new ExtendModuleElement(id, mc, sfsh, shlc, sm, mid, xssx));
		}
		return list;
	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-4 ����09:35:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean updateModuleElementShlc(ExtendModuleElement target) throws Exception {
		String sql = "UPDATE ZFXG_EXTEND_MODULE_ELEMENT SET (SFSH, SHLC)=(?, ?) WHERE ID = ?";
		return dao.runUpdate(sql, new String[]{target.getSfsh(),target.getShlc(),target.getId()});
	}
	
	/**
	 * 
	 * @����:���������(������ʽ)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-4 ����09:43:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param targetList
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] updateModuleElementShlcBatch(List<ExtendModuleElement> targetList) throws Exception {
		String sql = "UPDATE ZFXG_EXTEND_MODULE_ELEMENT SET (SFSH, SHLC)=(?, ?) WHERE ID = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (ExtendModuleElement target : targetList) {
			params.add(new String[]{target.getSfsh(),target.getShlc(),target.getId()});
		}
		return dao.runBatch(sql, params);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ExtendModuleElement t,
			User user) throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendModuleElement t)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setClass(ExtendModuleElement.class);
		super.setKey("ID");
		super.setTableName("ZFXG_EXTEND_MODULE_ELEMENT");
	}

}
