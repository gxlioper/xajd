/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午09:43:40 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午09:43:40
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglTjgzDao extends SuperDAOImpl<FbglTjgzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FbglTjgzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglTjgzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("tjgzid");
		this.setTableName("xg_xsxx_fbbxhgl_tjgzszb");
		this.setClass(FbglTjgzForm.class);
	}

	/**
	 * @描述: 获取条件类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午05:05:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTjlx(String[] tjgzid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select distinct a.tjgzid,a.tjgzmc from XG_XSXX_FBBXHGL_TJGZSZB a");
		sql.append(" where a.tjgzid in(");
		int i = 0;
		for (String id : tjgzid) {
			sql.append("?");
			if (i + 1 < tjgzid.length) {
				sql.append(",");
			}
			i++;
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), tjgzid);
	}
	/**
	 * 
	 * @描述: 条件配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午05:06:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getTjpzXx(String tjgzid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_FBBXHGL_TJGZSZB where tjgzid=?");
		sb.append(" order by sx");
		return dao.getListNotOut(sb.toString(), new String[] { tjgzid });
	}
	/**
	 * 
	 * @描述:获取条件配置内容信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-10 下午02:14:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @param tjszzd
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public HashMap<String, String> getTjNrpzXx(String tjgzid,String tjszzd) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_FBBXHGL_TJGZSZB where tjgzid=? and tjszzd=?");
		return dao.getMapNotOut(sb.toString(), new String[] { tjgzid,tjszzd });
	}
	
	public String getMrylz(String[] tableMsg) {
		StringBuffer sql = new StringBuffer();
		sql.append("select "+tableMsg[1]+" from "+tableMsg[0]+" where rownum=1 and "+tableMsg[1]+" is not null");
		return dao.getOneRs(sql.toString(), new String[] {},tableMsg[2]);
	}
	/**
	 * 
	 * @描述:验证规则名称是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-9-15 下午02:08:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param gzmc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHave(String pzgzid,String gzmc){
		StringBuffer sql = new StringBuffer();
		String id=null==pzgzid?"-1":pzgzid;
		sql.append("select count(1) num from xg_xsxx_fbbxhgl_tjgzpzb where pzgzmc=? and pzgzid <> ?");
		String num =  dao.getOneRs(sql.toString(), new String[] {gzmc,id},"num");
		return Integer.parseInt(num)>0;
	}
}
