/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-9 ����02:48:35 
 */  
package com.zfsoft.xgxt.base.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������: ������־
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-9-9 ����02:48:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LogDao extends SuperDAOImpl<LogInfo> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(LogInfo.class);
		super.setKey("id");
		super.setTableName("xg_xtgl_log");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LogInfo t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder("select * from");
		sql.append("(select * from xg_xtgl_log a left join (select distinct yhm,kl,yhbzdms zdm,xm,");
		sql.append("szbm,dwdm,xssx,qx,zmc from view_yhz_yhxxb");
		sql.append( ") b on  a.username=b.yhm) where 1=1");
		
		if (!StringUtil.isNull(t.getZdm())){
			sql.append("and yhm in (select yhm from view_yhz_yhxxb  where zdm = ? )");
			params.add(t.getZdm());
		}
		
		if (!StringUtil.isNull(t.getDescription())){
			sql.append(" and description like '%'||?||'%'");
			params.add(t.getDescription());
		}
		
		if (!StringUtil.isNull(t.getUsername())){
			sql.append(" and username like '%'||?||'%'");
			params.add(t.getUsername());
		}
		
		if (!StringUtil.isNull(t.getCzkssj())){
			sql.append(" and to_date(dotime,'yyyy-MM-dd hh24:mi:ss') > to_date(?,'yyyy-MM-dd')");
			params.add(t.getCzkssj());
		}
		
		if (!StringUtil.isNull(t.getCzjssj())){
			sql.append(" and to_date(dotime,'yyyy-MM-dd hh24:mi:ss') < to_date(?,'yyyy-MM-dd')+1");
			params.add(t.getCzjssj());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LogInfo t, User user)
			throws Exception {
		return null;
	}
	
	public List<HashMap<String,String>> getAllList(LogInfo t) throws Exception {
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getPageList(t);
	}
	
	/**
	 * 
	 * @����:ͳ�Ƶ�¼��������������¼��־
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-9-12 ����03:19:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertDllog() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_xtgl_log_dl ");
		sql.append(" select * from xg_xtgl_log t where t.description like '%ϵͳ��¼%'");
		sql.append("   and not exists (select 1 from xg_xtgl_log_dl where id = t.id) ");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
}
