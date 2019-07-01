/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����03:39:19 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.cxdj.CxdjForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������-��������-����ά��-������������
 * @�๦������: 
 * @���ߣ� ��־��[����:982]
 * @ʱ�䣺 2014-4-23 ����03:39:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlwtlxwhDao extends SuperDAOImpl<XlwtlxwhForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLJK_XLWJYJ_XLWTLX");
		setKey("lxdm");
		setClass(XlwtlxwhForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlwtlxwhForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc ")
		   .append("from XG_XLJK_XLWJYJ_XLWTLX t ")
		   .append("where 1=1 order by t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlwtlxwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/** 
	 * @����:(�����������ʹ����Ƿ����)
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����06:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 */
	public boolean xlwtlxIsExist(XlwtlxwhForm model) {
		String sql="select count(t.lxdm) num from XG_XLJK_XLWJYJ_XLWTLX t where t.lxdm=?";
		String num = dao.getOneRs(sql.toString(), new String[]{model.getLxdm()}, "num");
		return !num.equals("0");
	}


}
