/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:09:52 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:09:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjDao extends SuperDAOImplExtend<FbglGzpzTjForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append(" select * from (select t.*,t2.gzmc gzmc, decode(t.qyzt,'0','停用','1','启用',t.qyzt) qyztmc, decode(t.sfnz, '0', '否', '1', '是', t.qyzt) sfnzmc,");
		sql.append(" (case when pzgzid in (select pzgzid from XG_XSXX_FBBXHGL_BJDM_LSB) then '已使用'");
		sql.append("  when pzgzid in(select fbgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '已使用'");
		sql.append("  when pzgzid in(select bxhgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '已使用'");
		sql.append("  else '未使用' end) sfysy");
		sql.append(" ,(case when pzgzid in (select pzgzid from XG_XSXX_FBBXHGL_BJDM_LSB) then '1'");
		sql.append("  when pzgzid in(select fbgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '1'");
		sql.append("  when pzgzid in(select bxhgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '1'");
		sql.append("  else '0' end) syzt");
		sql.append(",t.gzdm gzlx");
		sql.append(" from xg_xsxx_fbbxhgl_tjgzpzb t ");
		sql.append(" left join XG_XSXX_FBBXHGL_GZDMB t2 on t.gzdm=t2.gzdm ) a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述: 删除规则配置对应信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-14 上午09:55:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzpzid
	 * @return
	 * int 返回类型 
	 */
	public int delGzpzXxtj(String gzpzid){
		StringBuffer sb=new StringBuffer();
		sb.append("delete xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=?");
		try {
			return dao.runDelete(sb.toString(), new String[]{gzpzid});
		} catch (Exception e) {
			throw new RuntimeException("删除失败!");
		}
	}
	/**
	 * 
	 * @描述: 规则配置内容
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午11:55:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getGzpznr(String pzgzid,String tjgzid){
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*, b.tjgzmc tjgzmc");
		sb
				.append(" (select * from xg_xsxx_fbbxhgl_tjgzpzsxb");
		sb.append(" where a.pzgzid = ?");
		sb.append(" and a.tjgzid = ?");
		sb.append(") a left join xg_xsxx_fbbxhgl_tjgzszb b");
		sb.append(" on a.tjgzid = b.tjgzid");
		return dao
				.getListNotOut(sb.toString(), new String[] { pzgzid, tjgzid });
	}
	/**
	 * 
	 * @描述: 是否启用
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午11:56:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean sfQy(String gzdm){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_tjgzpzb where gzdm=? and qyzt=?");
		 List<HashMap<String, String>> list=dao.getListNotOut(sb.toString(),new String[]{gzdm,"1"});
		return (null==list||list.size()<=0)?false:true;
	}
	/**
	 * 
	 * @描述: 获取条件内容列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午11:57:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getTjnrList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select pzgzid,pzgzmc from xg_xsxx_fbbxhgl_tjgzpzb");
		return dao.getListNotOut(sb.toString(),new String[]{});
	}
	/**
	 * 
	 * @描述: 获取条件内容列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 上午09:18:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key 唯一编码 gzdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getTjnrList(String key){
		StringBuffer sb = new StringBuffer();
		sb.append("select pzgzid,pzgzmc from xg_xsxx_fbbxhgl_tjgzpzb");
		sb.append(" where gzdm=?");
		return dao.getListNotOut(sb.toString(),new String[]{key});
	}
	/**
	 * 
	 * @描述: 获取条件内容列表(已经启用的)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 上午09:18:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key 唯一编码 gzdm
	 * @param sfqy 是否已经启用
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getTjnrList(String key,String sfqy){
		StringBuffer sb = new StringBuffer();
		sb.append("select pzgzid,pzgzmc from xg_xsxx_fbbxhgl_tjgzpzb");
		sb.append(" where gzdm=?");
		sb.append(" and qyzt=?");
		return dao.getListNotOut(sb.toString(),new String[]{key,sfqy});
	}
	@Override
	protected void setTableInfo() {
		this.setKey("pzgzid");
		this.setTableName("xg_xsxx_fbbxhgl_tjgzpzb");
		this.setClass(FbglGzpzTjForm.class);
	}

}
