/**
 * @部门:学工产品事业部
 * @日期：2015-8-13 上午11:39:31 
 */
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;


import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核内容管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-13 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhnrglDao extends SuperDAOImpl<KhnrglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(KhnrglForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * 
	 * @描述:获取考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 上午10:30:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhnrList(String khbid)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,case when t1.fzlx ='0' then t1.fzzdf||'～'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append(" case when t1.pflx ='1' then '加分' else '减分' end pflxmc from xg_khgl_tk_zbx t1");
		sql.append(" where khbid=? order by to_number(xssx)");
		return dao.getListNotOut(sql.toString(), new String[]{khbid});
	}

	public List<HashMap<String, String>> getTeaList(KhnrglForm model, User user)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select rownum r,a.* from (select b.zgh yhm,b.xm,b.bmdm,c.bmmc from fdyxxb b ");
		sql.append(" left join zxbz_xxbmdm c on b.bmdm= c.bmdm");
		sql.append(")a where 1=1");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhnr(KhnrglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.fzlx,'0','区间','固定') fzlxmc,case when t1.fzlx ='0' then t1.fzzdf||'～'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append(" case when t1.pflx ='1' then '加分' else '减分' end pflxmc from xg_khgl_tk_zbx t1");
		sql.append(" where zbmxid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getZbmxid() });
	}


	/**
	 * 
	 * 删除考核内容
	 */
	public boolean delKhnrgl(String Zbmxid) throws Exception {
		String sql = "delete from xg_khgl_tk_zbx where Zbmxid=?";
		return dao.runUpdate(sql, new String[] { Zbmxid });
	}
	
	/**
	 * 
	 * @描述:考核内容列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午03:27:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhnrList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from xg_khgl_tk_zbx");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @描述:获取显示顺序
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-18 上午10:35:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXssx(String khbid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_number(count(1))+1 xssx from xg_khgl_tk_zbx t1 where khbid=?");
		return dao.getOneRs(sql.toString(), new String[]{khbid}, "xssx");
	}
	/**
	 * 
	 * @描述:判断考核内容是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-9-1 上午11:12:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHave(KhnrglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_khgl_tk_zbx where khnr=? and khbid=? ");
		if(null!=model.getKhbid()){
			sql.append(" and zbmxid<>'"+model.getZbmxid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getKhnr(),model.getKhbid()}, "num");
		return Integer.parseInt(num)>0;
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KhnrglForm.class);
		super.setKey("zbmxid");
		super.setTableName("xg_khgl_tk_zbx");
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KhnrglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
}
