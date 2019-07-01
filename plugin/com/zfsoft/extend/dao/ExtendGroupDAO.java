/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-3 ����10:00:26 
 */  
package com.zfsoft.extend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendGroup;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-3 ����10:00:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendGroupDAO extends SuperDAOImpl<ExtendGroup> {

	public List<HashMap<String, String>> getExtendGroupListByMID(String mid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id as mid, b.id as meid, c.id as gid, c.lx, c.tsxz ");
		sql.append("from ZFXG_EXTEND_MODULE a ");
		sql.append("left join ZFXG_EXTEND_MODULE_ELEMENT b ");
		sql.append("on a.id = b.mid ");
		sql.append("left join ZFXG_EXTEND_GROUP c ");
		sql.append("on b.id = c.meid ");
		sql.append("where a.id = ? ");
		sql.append("order by to_number(b.xssx), to_number(c.xssx) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{mid});
	}
	
	public List<ExtendGroup> getExtendGroupListByMEID(String meid)throws Exception {
		String sql = "select * from ZFXG_EXTEND_GROUP where meid = ? order by to_number(xssx)";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{meid});
		List<ExtendGroup> egs = new ArrayList<ExtendGroup>();
		for (HashMap<String, String> hashMap : listNotOut) {
			String id = hashMap.get("id");
			String mc = hashMap.get("mc");
			String lx = hashMap.get("lx");
			String _meid = hashMap.get("meid");
			String xssx = hashMap.get("xssx");
			String tsxz = hashMap.get("tsxz");
			ExtendGroup eg = new ExtendGroup(id, mc, lx, xssx, _meid, tsxz);
			egs.add(eg);	
		}
		return egs;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendGroup t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendGroup t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ExtendGroup.class);
		super.setKey("ID");
		super.setTableName("ZFXG_EXTEND_GROUP");
	}

}
