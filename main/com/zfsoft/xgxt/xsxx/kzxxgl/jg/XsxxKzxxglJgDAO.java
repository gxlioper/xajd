/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����09:34:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-19 ����09:34:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglJgDAO extends SuperDAOImpl<XsxxKzxxglJgForm> {
	/**
	 * @���ԣ� KEY_NAME ������
	 */
	private static final String KEY_NAME = "jgid";
	/**
	 * @���ԣ� TABLE_NAME ����
	 */
	private static final String TABLE_NAME = "ZFXG_XSXX_KZXX_JG";
	/**
	 * @���ԣ� MODEL_CLAZZ class ����
	 */
	private static final Class<XsxxKzxxglJgForm> MODEL_CLAZZ = XsxxKzxxglJgForm.class;
	
	public boolean deleteBySqid(String sqid) throws Exception{
		String sql = "delete from ZFXG_XSXX_KZXX_JG where sqid = ?";
		int runDelete = dao.runDelete(sql, new String[]{sqid});
		return runDelete>0;
	}
	
	public boolean deleteByXh(String xh) throws Exception{
		String sql = "delete from ZFXG_XSXX_KZXX_JG where xh = ?";
		dao.runDelete(sql, new String[]{xh});
		return true;
	}
	
	public XsxxKzxxglJgForm getModelBySqid(String sqid) throws Exception{
		String sql = "select * from ZFXG_XSXX_KZXX_JG where sqid = ? ";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{sqid});
		XsxxKzxxglJgForm model = null;
		for (HashMap<String, String> d : listNotOut) {
			model = new XsxxKzxxglJgForm();
			String jgid = d.get("jgid");
			String _sqid = d.get("sqid");
			String _xh = d.get("xh");
			String jrsj = d.get("jrsj");
			String sjly = d.get("sjly");
			model.setJgid(jgid);
			model.setJrsj(jrsj);
			model.setSjly(sjly);
			model.setSqid(_sqid);
			model.setXh(_xh);
		}
		return model;
	}
	
	public XsxxKzxxglJgForm getModelByXh(String xh) throws Exception{
		String sql = "select * from ZFXG_XSXX_KZXX_JG where xh = ? ";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{xh});
		XsxxKzxxglJgForm model = null;
		for (HashMap<String, String> d : listNotOut) {
			model = new XsxxKzxxglJgForm();
			String jgid = d.get("jgid");
			String sqid = d.get("sqid");
			String _xh = d.get("xh");
			String jrsj = d.get("jrsj");
			String sjly = d.get("sjly");
			model.setJgid(jgid);
			model.setJrsj(jrsj);
			model.setSjly(sjly);
			model.setSqid(sqid);
			model.setXh(_xh);
		}
		return model;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglJgForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append("  from (select a.jgid, a.sqid,");
		sql.append("               a.jrsj,");
		sql.append("               a.sjly,");
		sql.append("               b.xh,");
		sql.append("               b.xm,");
		sql.append("               b.xymc,");
		sql.append("               b.xydm,");
		sql.append("               b.zymc,");
		sql.append("               b.zydm,");
		sql.append("               b.bjdm,");
		sql.append("               b.bjmc,");
		sql.append("               b.nj,");
		sql.append("               b.xb");
		sql.append("          from ZFXG_XSXX_KZXX_JG a");
		sql.append("          left join view_xsjbxx b");
		sql.append("            on a.xh = b.xh");
		sql.append("         order by a.jrsj desc) a where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(MODEL_CLAZZ);
		super.setKey(KEY_NAME);
		super.setTableName(TABLE_NAME);
	}

}
