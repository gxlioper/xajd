/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-3 ����10:02:15 
 */  
package com.zfsoft.extend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendGroupElement;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-3 ����10:02:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendGroupElementDAO extends SuperDAOImpl<ExtendGroupElement> {

	
	public List<ExtendGroupElement> getExtendGroupElementListByGID(String gid) throws Exception{
		if(StringUtils.isBlank(gid)){
			return null;
		}
		String sql = "select * from ZFXG_EXTEND_GROUP_ELEMENT where gid = ? order by to_number(xssx)";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{gid});
		List<ExtendGroupElement> ges = new ArrayList<ExtendGroupElement>();
		for (HashMap<String, String> hashMap : listNotOut) {
			String zd = hashMap.get("zd");
			String mc = hashMap.get("mc");
			String lx = hashMap.get("lx");
			String sj = hashMap.get("sj");
			String gs = hashMap.get("gs");
			String mrz = hashMap.get("mrz");
			String bt = hashMap.get("bt");
			String cd = hashMap.get("cd");
			String bz = hashMap.get("bz");
			String xssx = hashMap.get("xssx");
			String _gid = hashMap.get("gid");
			String sfbj = hashMap.get("sfbj");
			String elcd = hashMap.get("elcd");
			ExtendGroupElement ge = new ExtendGroupElement(zd, mc, lx, sj, gs, mrz, bt, cd, bz, xssx, _gid, sfbj, elcd);
			ges.add(ge);
		}
		return ges;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendGroupElement t){
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendGroupElement t,
			User user) throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ExtendGroupElement.class);
		super.setTableName("ZFXG_EXTEND_GROUP_ELEMENT");
	}

}
