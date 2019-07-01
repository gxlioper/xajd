/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:33:17 
 */
package com.zfsoft.xgxt.ystgl.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:33:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstglDmwhDao extends SuperDAOImpl<YstglDmwhForm> {

	
	public List<HashMap<String, String>> getYstlbList(YstglDmwhForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_YSTGL_YSTLB a where 1=1");
		if (!StringUtil.isNull(model.getYstlbmc())) {
			params.add(model.getYstlbmc());
			sql.append(" and a.ystlbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	public List<HashMap<String, String>> getYstlbListAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_YSTGL_YSTLB a where 1=1");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	public List<HashMap<String, String>> getXmlbListAll(String ystlbdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_YSTGL_Xmlb a where a.ystlbdm=?");
		return dao.getListNotOut(sql.toString(), new String[]{ystlbdm});
	}
	public List<HashMap<String, String>> getGkdwListAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_YSTGL_GKDW");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	public List<HashMap<String, String>> getGkdwList(YstglDmwhForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_YSTGL_GKDW a where 1=1");
		if (!StringUtil.isNull(model.getYstlbmc())) {
			params.add(model.getYstlbmc());
			sql.append(" and a.gkdwmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	
	
	public List<HashMap<String, String>> getXmlbList(YstglDmwhForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.ystlbmc from XG_YSTGL_Xmlb a left join XG_YSTGL_YSTLB b on a.ystlbdm=b.ystlbdm where 1=1");
		if (!StringUtil.isNull(model.getXmlbmc())) {
			params.add(model.getXmlbmc());
			sql.append(" and a.Xmlbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:增加艺术团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean addYstlb(YstglDmwhForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from XG_YSTGL_YSTLB where  ystlbdm=?  or  ystlbmc = ?";
		String num = dao.getOneRs(sql, new String[] { model.getYstlbdm(),model.getYstlbmc().trim() }, "num");
		if ("0".equals(num)) {
			sql="insert into XG_YSTGL_YSTLB(ystlbdm,ystlbmc) values(?,?)";
			flag=dao.runUpdate(sql, new String[]{model.getYstlbdm(),model.getYstlbmc().trim()});
		} else {
			throw new SystemException(MessageKey.YSTGL_JCSZ_YSTLB_REPEAT);
		}

		return flag;

	}
	public boolean updateYstlb(YstglDmwhForm model) throws Exception {
		boolean flag = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from XG_YSTGL_YSTLB where ystlbmc = ? and ystlbdm<>?  ";
		String num = dao.getOneRs(sql1, new String[] {model.getYstlbmc(),model.getYstlbdm()}, "num");
		if ("0".equals(num)) {
			sql="update XG_YSTGL_YSTLB set ystlbmc = ? where ystlbdm=?";
			flag=dao.runUpdate(sql, new String[]{model.getYstlbmc().trim(),model.getYstlbdm()});
		} else {
			throw new SystemException(MessageKey.YSTGL_JCSZ_YSTLB_REPEAT);
		}
		return flag;
	}
	public int deleteYstlb(String[] values) throws Exception {
		int num = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_YSTGL_YSTLB where ystlbdm in (");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		num = dao.runDelete(sql.toString(), values);
		return num;
	}
	
	public YstglDmwhForm getYstlb(YstglDmwhForm model) throws Exception{
		String sql = "select * from XG_YSTGL_YSTLB where ystlbdm=?";
		return this.getModel(model,sql,new String[]{model.getYstlbdm()});
	}


	/**
	 * @throws Exception
	 * 
	 * @描述:增加项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean addXmlb(YstglDmwhForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from XG_YSTGL_Xmlb where  Xmlbdm=?  or  Xmlbmc = ?";
		String num = dao.getOneRs(sql, new String[] { model.getXmlbdm(),model.getXmlbmc().trim() }, "num");
		if ("0".equals(num)) {
			sql="insert into XG_YSTGL_Xmlb(ystlbdm,Xmlbdm,Xmlbmc) values(?,?,?)";
			flag=dao.runUpdate(sql, new String[]{model.getYstlbdm(),model.getXmlbdm(),model.getXmlbmc().trim()});
		} else {
			throw new SystemException(MessageKey.YSTGL_JCSZ_XMLB_REPEAT);
		}

		return flag;

	}
	public boolean updateXmlb(YstglDmwhForm model) throws Exception {
		boolean flag = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from XG_YSTGL_Xmlb where Xmlbmc = ? and Xmlbdm<>?  ";
		String num = dao.getOneRs(sql1, new String[] {model.getXmlbmc(),model.getXmlbdm()}, "num");
		if ("0".equals(num)) {
			sql="update XG_YSTGL_Xmlb set ystlbdm=?,Xmlbmc = ? where Xmlbdm=?";
			flag=dao.runUpdate(sql, new String[]{model.getYstlbdm(),model.getXmlbmc().trim(),model.getXmlbdm()});
		} else {
			throw new SystemException(MessageKey.YSTGL_JCSZ_XMLB_REPEAT);
		}
		return flag;
	}
	public int deleteXmlb(String[] values) throws Exception {
		int num = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_YSTGL_Xmlb where Xmlbdm in (");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		num = dao.runDelete(sql.toString(), values);
		return num;
	}
	
	/**
	 * 
	 * @描述:增加挂靠单位
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-14 上午11:49:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addGkdw(YstglDmwhForm model) throws Exception {
			boolean flag = false;
			String sql;
			sql = "select count(1) num from XG_YSTGL_Gkdw where  Gkdwdm=?  or  Gkdwmc = ?";
			String num = dao.getOneRs(sql, new String[] { model.getGkdwdm(),model.getGkdwmc().trim() }, "num");
			if ("0".equals(num)) {
				sql="insert into XG_YSTGL_Gkdw(Gkdwdm,Gkdwmc) values(?,?)";
				flag=dao.runUpdate(sql, new String[]{model.getGkdwdm(),model.getGkdwmc().trim()});
			} else {
				throw new SystemException(MessageKey.YSTGL_JCSZ_GKDW_REPEAT);
			}

			return flag;

		}
	public boolean updateGkdw(YstglDmwhForm model) throws Exception {
		boolean flag = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from XG_YSTGL_Gkdw where Gkdwmc = ? and Gkdwdm<>?  ";
		String num = dao.getOneRs(sql1, new String[] {model.getGkdwmc(),model.getGkdwdm()}, "num");
		if ("0".equals(num)) {
			sql="update XG_YSTGL_Gkdw set Gkdwmc = ? where Gkdwdm=?";
			flag=dao.runUpdate(sql, new String[]{model.getGkdwmc().trim(),model.getGkdwdm()});
		} else {
			throw new SystemException(MessageKey.YSTGL_JCSZ_GKDW_REPEAT);
		}
		return flag;
	}
	public int deleteGkdw(String[] values) throws Exception {
		int num = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_YSTGL_Gkdw where Gkdwdm in (");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		num = dao.runDelete(sql.toString(), values);
		return num;
	}
	public YstglDmwhForm getGkdw(YstglDmwhForm model) throws Exception{
		String sql = "select * from XG_YSTGL_Gkdw where Gkdwdm=?";
		return this.getModel(model,sql,new String[]{model.getGkdwdm()});
	}
	
	public YstglDmwhForm getXmlb(YstglDmwhForm model) throws Exception{
		String sql = "select * from XG_YSTGL_Xmlb where Xmlbdm=?";
		return this.getModel(model,sql,new String[]{model.getXmlbdm()});
	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from(select ystlbdm from XG_YSTGL_Xmlb where ystlbdm=? ");
		sql.append(" union all select ystlbdm from  XG_YSTGL_YSTJGB where ystlbdm=?)");
	
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{lbdm,lbdm}, "num"))>0;
		
	}
	public boolean isExistsXmlbData(String xmlbdm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from XG_YSTGL_YSTJGB where xmlbdm=? ");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{xmlbdm}, "num"))>0;
	}
	public boolean isExistsGkdwData(String gkdwdm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from XG_YSTGL_YSTJGB where gkdwdm=? ");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{gkdwdm}, "num"))>0;
		
	}
	
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YstglDmwhForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(YstglDmwhForm model, User user) throws Exception {
		return null;
	}


}
