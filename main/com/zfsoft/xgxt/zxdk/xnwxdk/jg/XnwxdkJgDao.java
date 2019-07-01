/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:41:25 
 */
package com.zfsoft.xgxt.zxdk.xnwxdk.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:41:25
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XnwxdkJgDao extends SuperDAOImpl<XnwxdkJgModel> {
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkJgModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkJgModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,t1.sfzh,t1.xz,t1.sjhm,t1.xmsj sjdh,t1.dzyx,");
//		sql.append(" decode(t.shzt,");
//		sql.append(" '0',");
//		sql.append(" '未提交',");
//		sql.append(" '1',");
//		sql.append(" '通过',");
//		sql.append(" '2',");
//		sql.append(" '不通过',");
//		sql.append(" '3',");
//		sql.append(" '已退回',");
//		sql.append(" '5',");
//		sql.append(" '审核中',");
//		sql.append(" t.shzt) shztmc,");
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjk_jgb t");
		sql.append(" left join view_xsbfxx t1");
		sql
				.append(" on t.xh = t1.xh "
						+ " left join xqdzb t2 on t.xq = t2.xqdm  order by t1.xydm asc"
						+ ") t where 1= 1   ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XnwxdkJgModel.class);
		super.setKey("jgid");
		super.setTableName("xg_zdgxh_wxjk_jgb");
	}


	/*
	 * 导出申请表的时候获取学生基本信息以及住宿申请信息
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql
				.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t3.xlmc,");
		sql
				.append(" substr(t2.sqsjstart, 0, 4) || '年' || substr(t2.sqsjstart, 5, 2) || '月' ||substr(t2.sqsjstart, 7, 2) || '日' ||'到'||substr(t2.sqsjend, 0, 4) || '年' || substr(t2.sqsjend, 5, 2) || '月' ||substr(t2.sqsjend, 7, 2) || '日' as sqsj ");
		sql
				.append("from xg_gygl_xyzsgl t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh join xldmb t3 on t2.xl = t3.xldm)");
		sql.append(" t where sqbh = ?  ");
		return dao.getMapNotOut(sql.toString(),
				new String[] { model.getSqbh() });
	}



	// 获取金额上限
	public String getJesx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select jesx from xg_zdgxh_wxjk_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "jesx");
	}
	
	//申请流过来覆盖原来结果表中的数据时先删除原来的数据，在进行插入
	public boolean delDkjg(String xh,String xn,String xq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjk_jgb where xh = ? and xn = ? and xq = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh,xn,xq});
	}
	
	public boolean delDkjgByID(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_zdgxh_wxjk_jgb where jgid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}

}
