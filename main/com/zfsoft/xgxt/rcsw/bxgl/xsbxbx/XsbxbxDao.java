/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 上午11:49:12 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsbxbx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 保险管理-学生保险报销
 * @作者： 沈晓波 [工号:1123]
 * @时间： 2015-1-26 上午11:49:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsbxbxDao extends SuperDAOImpl<XsbxbxForm> {
	
	/*
             描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XsbxbxForm.class);
		super.setKey("bxid");
		super.setTableName("xg_rcsw_bxgl_xsbxbxb");
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(XsbxbxForm t)
			throws Exception {
		
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(XsbxbxForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from (select decode(t1.clzb, '01', '齐全', '02', '缺损', t1.clzb) clzbmc, ");
		sql.append(" decode(t1.bxxz, '01', '商业报销', '02', '城镇医保', t1.bxxz) bxxzmc, ");
		sql.append(" decode(t1.lx, '01', '住院', '02', '门诊', '03', '意外', t1.lx) lxmc, ");
		sql.append(" t1.*, ");
		sql.append(" t2.XM, ");
		sql.append(" t2.XYMC, ");
		sql.append(" t2.XYDM, ");
		sql.append(" t2.ZYDM, ");
		sql.append(" t2.BJDM, ");
		sql.append(" t2.SFZH, ");
		sql.append(" t2.ZYMC, ");
		sql.append(" t2.BJMC, ");
		sql.append(" t2.XB, ");
		sql.append(" t2.NJ, ");
		sql.append(" t2.SJHM ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from XG_RCSW_BXGL_XSBXBXB t1 ");
		sql.append(" left join view_xsbfxx t2 ");
		sql.append(" on t1.xh = t2.xh ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
		}
		sql.append(" ) t where 1 = 1 ");
		
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 查看单条记录名称
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-28 上午09:19:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bxid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsbxbx(String bxid){
		String sql = " select t1.*,decode(t1.clzb, '01', '齐全', '02', '缺损', t1.clzb) clzbmc," +
				"decode(t1.bxxz, '01', '商业报销', '02', '城镇医保', t1.bxxz) bxxzmc," +
				"decode(t1.lx, '01', '住院', '02', '门诊', '03', '意外', t1.lx) lxmc from XG_RCSW_BXGL_XSBXBXB t1 " +
				" where  bxid = ? ";
		return dao.getMapNotOut(sql, new String[]{bxid});
	}
	
	/**
	 * 
	 * @描述: 保险证明打印
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-2-3 下午01:39:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bxid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> bxbxZm(String bxid) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.xh, a.zlbh, a.bxje, a.jbnr, a.jzyy, a.jzsj, a.bzqk, b.XYMC, b.ZYMC, b.BJMC, b.XM, b.XB, b.SFZH ");
		sql.append(" from XG_RCSW_BXGL_XSBXBXB a ");
		sql.append(" left join view_xsbfxx b ");
		sql.append(" on a.xh = b.xh where bxid = ? ");
		
		return dao.getMap(sql.toString(), new String[]{bxid}, new String[]{"xh","zlbh","bxje","jbnr","jzyy","jzsj","bzqk","xymc","zymc","bjmc","xm","xb","sfzh"});
	}
	/**
	 * 
	 * @描述:删除保险报销申请信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-2 下午03:14:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bxid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delBxbxjg(String bxid) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_rcsw_bxgl_xsbxbxb where bxid=?");
		return dao.runUpdate(sql.toString(), new String[]{bxid});
	}
	
	
	
}
