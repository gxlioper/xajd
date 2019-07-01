/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����03:39:19 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������-��������-����ά��-��������
 * @�๦������: 
 * @���ߣ� ��־��[����:982]
 * @ʱ�䣺 2014-4-23 ����03:39:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdlxwhDao extends SuperDAOImpl<FdlxwhForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLJK_XLWJYJ_FDLX");
		setKey("fdlxdm");
		setClass(FdlxwhForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdlxwhForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.fdlxdm,t.fdlxmc ")
		   .append("from XG_XLJK_XLWJYJ_FDLX t ")
		   .append("where 1=1 order by t.fdlxdm ");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdlxwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/** 
	 * @����:(�������ʹ����Ƿ����)
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����06:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 */
	public boolean fdlxIsExist(FdlxwhForm model) {
		String sql="select count(t.fdlxdm) num from XG_XLJK_XLWJYJ_FDLX t where t.fdlxdm=?";
		String num = dao.getOneRs(sql.toString(), new String[]{model.getFdlxdm()}, "num");
		return !num.equals("0");
	}

}
