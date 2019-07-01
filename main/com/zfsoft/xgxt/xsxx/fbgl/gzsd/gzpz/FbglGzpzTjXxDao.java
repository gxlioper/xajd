/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:10:37 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:10:37
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjXxDao extends SuperDAOImpl<FbglGzpzTjXxForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjXxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjXxForm t,
			User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * 
	 * @描述: 规则详细配置信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午11:17:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? order by sx");
		return dao.getListNotOut(sb.toString(), new String[]{pzgzid});
	}
	/**
	 * 
	 * @描述: 根据条件规则id获取配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午11:18:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid,String tjgzid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? and tjgzid=? order by sx");
		return dao.getListNotOut(sb.toString(), new String[]{pzgzid,tjgzid});
	}
	/**
	 * 
	 * @描述: 根据配置规则id获取配置的条件规则类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午10:59:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjgzxx(String pzgzid){
		StringBuffer sb=new StringBuffer();
		sb.append("select tjgzid from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? group by tjgzid");
		return dao.getListNotOut(sb.toString(), new String[]{pzgzid});
	}

	/**
	 * 
	 * @描述: 规则配置对应的条件规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 下午02:41:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzpzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getGzpzTjxxLx(String gzpzid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.tjgzid,b.tjgzmc from XG_XSXX_FBBXHGL_TJGZPZSXB a ");
		sql.append("left join XG_XSXX_FBBXHGL_TJGZSZB b on a.tjgzid=b.tjgzid where pzgzid=?");
		return dao.getListNotOut(sql.toString(), new String[]{gzpzid});
	}
	/**
	 * 
	 * @描述: 根据规则类型获取规则配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 下午02:49:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @param gzpzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getGzpzTjxxForLx(String tjgzid,String gzpzid){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,decode(wsbl,'0','不补零','1','补零','') wsblT");
		sql.append(",decode(sfkxg,'0','不可修改','1','可修改','') sfkxgT");
		sql.append(" from(select a.*, b.tjszmc,b.xxz xxzsz,b.xxlx,b.ylz ylzqz");
		sql.append(" from xg_xsxx_fbbxhgl_tjgzpzsxb a");
		sql.append(" left join xg_xsxx_fbbxhgl_tjgzszb b");
		sql.append(" on a.tjgzid = b.tjgzid");
		sql.append(" and a.tjszzd = b.tjszzd");
		sql.append(" where b.tjgzid =?");
		sql.append(" and a.pzgzid =?");
		sql.append(")a ");
		sql.append(" order by a.sx asc");
		return dao.getListNotOut(sql.toString(), new String[]{tjgzid,gzpzid});
	}
	/**
	 * 
	 * @描述: 获取是否可修改信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-21 下午02:36:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param sfkxg 1可修改/0不可修改
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String,String>> getXgXx(String pzgzid,String sfkxg){
		StringBuffer sb=new StringBuffer();
		sb.append("select b.tjszmc,b.ylz from xg_xsxx_fbbxhgl_tjgzpzsxb a left join XG_XSXX_FBBXHGL_TJGZSZB b ");
		sb.append(" on a.tjgzid=b.tjgzid and a.tjszzd=b.tjszzd");
		sb.append(" where a.pzgzid=? and a.sfkxg=?");
		return dao.getListNotOut(sb.toString(),new String[]{pzgzid,sfkxg});
	}
	/**
	 * 
	 * @描述: 获取对应序号的信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 下午03:06:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzpzid
	 * @param sx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public HashMap<String, String> getGzpzTjgz(String pzgzid,String tjgzid,String tjszzd,String sx){
		List<String> param=new ArrayList<String>();
		param.add(pzgzid);
		param.add(tjgzid);
		param.add(sx);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_XSXX_FBBXHGL_TJGZPZSXB where pzgzid=? and tjgzid=? and sx=?");
		if(StringUtils.isNotNull(tjszzd)){
			sql.append(" and tjszzd=?");
			param.add(tjszzd);
		}
		return dao.getMapNotOut(sql.toString(), param.toArray(new String[]{}));
	}
	@Override
	protected void setTableInfo() {
		this.setKey("pk");
		this.setTableName("xg_xsxx_fbbxhgl_tjgzpzsxb");
		this.setClass(FbglGzpzTjXxForm.class);
	}

}
