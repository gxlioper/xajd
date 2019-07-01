/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:34:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:34:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglJgDAO extends SuperDAOImpl<XsxxKzxxglJgForm> {
	/**
	 * @属性： KEY_NAME 主键名
	 */
	private static final String KEY_NAME = "jgid";
	/**
	 * @属性： TABLE_NAME 表名
	 */
	private static final String TABLE_NAME = "ZFXG_XSXX_KZXX_JG";
	/**
	 * @属性： MODEL_CLAZZ class 类型
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
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
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
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(MODEL_CLAZZ);
		super.setKey(KEY_NAME);
		super.setTableName(TABLE_NAME);
	}

}
