/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ����Ϣ 
 * @�๦������: �޸��������
 * @���ߣ� ligl 
 * @ʱ�䣺 2013-12-11 ����01:35:38 
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ShlcDao extends SuperDAOImpl<ShlcModel> {

	@Override
	protected void setTableInfo() {
		this.setKey("");
		this.setTableName("");
		this.setClass(ShlcModel.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ShlcModel t)
			throws Exception {
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ShlcModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public List<HashMap<String,String>> getShYjForDjb(String ywid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xtwh_shztb where shzt = '1' and ywid = ? order by shsj asc");
		return dao.getListNotOut(sql.toString(), new String[]{ywid});
	}

}
