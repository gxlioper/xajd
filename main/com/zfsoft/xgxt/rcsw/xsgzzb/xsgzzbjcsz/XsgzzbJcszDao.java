package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ܱ���������
 */
public class XsgzzbJcszDao extends SuperDAOImpl<XsgzzbJcszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbJcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbJcszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() { 
//		super.setTableName("xg_rcsw_xsgzzb_jcszb");// ��Ϊ���Ƕ�̬�ģ��˴�������TableName,ֻ����Class
		super.setClass(XsgzzbJcszForm.class);
	}
	/**
	 * ��̬ȡ��
	 */
	protected void setTableInfo(XsgzzbJcszForm t) {
		String table = "xg_rcsw_xsgzzb_jcszb";
		if("bj".equals(t.getGzzblx())){
			table = "xg_rcsw_bjgzzb_jcszb";
		}
		super.setTableName(table);
	}

	/**
	 * ��ѯ����������Ϣ
	 */
	public XsgzzbJcszForm getModel(String gzzblx) throws Exception {
		String table = "xg_rcsw_xsgzzb_jcszb";
		if("bj".equals(gzzblx)){
			table = "xg_rcsw_bjgzzb_jcszb";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from "+table+" a ");
		
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * ɾ������������Ϣ
	 */
	public boolean deleteXszbbJcsz(XsgzzbJcszForm t) throws Exception {
		String table = "xg_rcsw_xsgzzb_jcszb";
		if("bj".equals(t.getGzzblx())){
			table = "xg_rcsw_bjgzzb_jcszb";
		}
		boolean flag = false;
		String sql = " delete from " + table;
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
