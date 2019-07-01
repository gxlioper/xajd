/**
 * @部门:学工产品事业部
 * @日期： 2014-10-8 上午11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍-谈话记录维护-谈话类型
 * @类功能描述: 
 * @作者： 江水才[工号:1150]
 * @时间： 2014-10-8 上午11:40:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThlxDao extends SuperDAOImpl<SzdwThlxForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_SZDW_THJL_THLX");
		setKey("lxdm");
		setClass(SzdwThlxForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	/**
	 * @描述:谈话类型列表
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 9:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [t]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	@Override
	public List<HashMap<String, String>> getPageList(SzdwThlxForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc ")
		   .append("from XG_SZDW_THJL_THLX t ")
		   .append("where 1=1 order by t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SzdwThlxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/** 
	 * @描述: 谈话类型代码是否存在
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-8 上午11:40:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 */
	public boolean thlxIsExist(SzdwThlxForm model) {
		String sql="select count(t.lxdm) num from XG_SZDW_THJL_THLX t where t.lxdm=?";
		String num = dao.getOneRs(sql, new String[]{model.getLxdm()}, "num");
		return !num.equals("0");
	}

	public boolean thlxIsExist_10351(SzdwThlxForm model) throws Exception {
		int rs = 0;
		/*if(old)
		String sql="select count(t.lxdm) num from XG_SZDW_THJL_THLX t where (t.lxdm='";
		rs = dao.getOneRsint(sql);*/
		return rs == 0;
	}


	/**
	 * @描述:困惑和问题列表
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 9:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [t]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getKhwtPageList(SzdwThlxForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t1.lxmc ssthlxmc ");
		sql.append("from XG_SZDW_THJL_KHWTLX t ");
		sql.append("left join XG_SZDW_THJL_THLX t1 on t1.lxdm=t.ssthlx ");
		sql.append("where 1=1 order by t.ssthlx,t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
    }

    /**
     * @描述:问题描述列表（温州大学）
     * @作者：lgx [工号：1553]
     * @日期：2018/7/11 9:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [t]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getWtmsPageList(SzdwThlxForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t2.lxmc ssthlxmc,t1.lxmc sskhwtmc ");
		sql.append(" from XG_SZDW_THJL_KHWT t ");
		sql.append(" left join XG_SZDW_THJL_KHWTLX t1 on t.sskhwt=t1.lxdm ");
		sql.append(" left join XG_SZDW_THJL_THLX t2 on t2.lxdm=t1.ssthlx ");
		sql.append(" where 1=1 order by t2.lxdm,t.sskhwt,t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
    }

    /**
     * @描述:提供帮助列表
     * @作者：lgx [工号：1553]
     * @日期：2018/7/11 16:54
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [t]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
	public List<HashMap<String,String>> getTgbzPageList(SzdwThlxForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc ");
		sql.append("from XG_SZDW_THJL_TGBZ t ");
		sql.append("where 1=1 order by t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/**
	 * @描述:增加困惑和问题
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 16:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean addKhwt(SzdwThlxForm model) throws Exception {
    	String sql = "insert into XG_SZDW_THJL_KHWTLX (lxdm,lxmc,ssthlx) values(?,?,?)";
    	return dao.runUpdate(sql,new String[]{model.getLxdm(),model.getLxmc(),model.getSsthlx()});

	}

	/**
	 * @描述:增加问题描述
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 16:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean addWtms(SzdwThlxForm model) throws Exception {
		String sql = "insert into XG_SZDW_THJL_KHWT (lxdm,lxmc,sskhwt) values(?,?,?)";
		return dao.runUpdate(sql,new String[]{model.getLxdm(),model.getLxmc(),model.getSskhwt()});
	}

	/**
	 * @描述:增加提供帮助
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 16:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean addTgbz(SzdwThlxForm model) throws Exception {
		String sql = "insert into XG_SZDW_THJL_TGBZ (lxdm,lxmc) values(?,?)";
		return dao.runUpdate(sql,new String[]{model.getLxdm(),model.getLxmc()});
	}

	/**
	 * @描述:根据谈话类型获取困惑和问题列表
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 16:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [ssthlx]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public HashMap<String,String> getKhwtListByThlx(String ssthlx) {
    	String sql = "select * from XG_SZDW_THJL_THLX  where lxdm=?";
    	return dao.getMapNotOut(sql,new String[]{ssthlx});
	}

	/**
	 * @描述:获取困惑和问题信息
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getKhwtInfo(SzdwThlxForm model) {
		String sql = "select b.lxmc ssthlxmc,a.* from XG_SZDW_THJL_KHWTLX a " +
				" left join XG_SZDW_THJL_THLX b on b.lxdm=a.ssthlx where a.lxdm=?";
		return dao.getMapNotOut(sql,new String[]{model.getLxdm()});
	}
	/**
	 * @描述:获取问题描述信息
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getWtmsInfo(SzdwThlxForm model) {
		String sql = "select a.*,b.lxmc sskhwtmc,c.lxmc ssthlxmc from XG_SZDW_THJL_KHWT a" +
				" left join XG_SZDW_THJL_KHWTLX b on b.lxdm=a.sskhwt " +
				" left join XG_SZDW_THJL_THLX c on c.lxdm=b.ssthlx  where a.lxdm=?";
		return dao.getMapNotOut(sql,new String[]{model.getLxdm()});
	}
	/**
	 * @描述:获取提供帮助信息
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getTgbzInfo(SzdwThlxForm model) {
		String sql = "select * from XG_SZDW_THJL_TGBZ where lxdm=?";
		return dao.getMapNotOut(sql,new String[]{model.getLxdm()});
	}

	public boolean updateKhwt(SzdwThlxForm model) throws Exception {
		String sql = "update XG_SZDW_THJL_KHWTLX set lxmc=? where lxdm=?";
		return dao.runUpdate(sql,new String[]{model.getLxmc(),model.getLxdm()});
	}
	public boolean updateWtms(SzdwThlxForm model) throws Exception {
		String sql = "update XG_SZDW_THJL_KHWT set lxmc=? where lxdm=?";
		return dao.runUpdate(sql,new String[]{model.getLxmc(),model.getLxdm()});
	}
	public boolean updateTgbz(SzdwThlxForm model) throws Exception {
		String sql = "update XG_SZDW_THJL_TGBZ set lxmc=? where lxdm=?";
		return dao.runUpdate(sql,new String[]{model.getLxmc(),model.getLxdm()});
	}

	public int getCount(SzdwThlxForm model, String tableName) throws SQLException {
		String sql = "select count(*) s from "+tableName+" where lxmc='"
				+model.getLxmc()+"' and lxdm<>'"+model.getLxdm()+"'";
		return dao.getOneRsint(sql);
	}

	public int delete(String[] lxdms, String tableName) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from "+tableName+" where lxdm in( ");
		for(int i=0;i<lxdms.length-1;i++){
			sql.append("?,");
		}
		sql.append("?)");

		return  dao.runDelete(sql.toString(),lxdms);
	}

	public List<HashMap<String,String>> getWtmsListByKhwt(String sskhwt) {
		String sql = "select * from XG_SZDW_THJL_KHWT  where sskhwt=?";
		return dao.getListNotOut(sql,new String[]{sskhwt});
	}

	public List<HashMap<String,String>> getAllTgbz() {
		String sql = "select * from XG_SZDW_THJL_TGBZ order by lxdm";
		return dao.getListNotOut(sql,new String[]{});
	}

	public List<HashMap<String,String>> getAllBzjg() {
		String sql = "select * from XG_SZDW_THJL_BZJG order by dm";
		return dao.getListNotOut(sql,new String[]{});
	}

	public String[] getBzjgmcByDms(String[] dms) throws SQLException {
		StringBuffer sql = new StringBuffer();
		if(dms.length>0){
			sql.append("select mc from XG_SZDW_THJL_BZJG where dm in (");
			for(int i = 0;i < dms.length-1;i++){
				sql.append("?,");
			}
			sql.append("?)");
		}
		return dao.getArray(sql.toString(),dms,"mc");
	}
}
