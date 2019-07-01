/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:54:47 
 */  
package com.zfsoft.xgxt.znxgl.znxgl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  yxy[工号:1206]
 * @时间： 2015-8-27 下午06:54:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZnxglDao extends SuperDAOImpl<ZnxglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZnxglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZnxglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
	//	String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.xjbh,");
		sql.append(" t.jsrbh,");
		sql.append(" t.fprbh,");
		sql.append(" t.jsrydbj,");
		sql.append(" t.jsrscbj,");
		sql.append(" t1.fsrbh,");
		sql.append(" t1.fssj,");
		sql.append(" t1.ztlb,");
		sql.append(" t1.xjzt,");
		sql.append(" t1.fsrydbj,");
		sql.append(" t1.fsrscbj,");
		sql.append(" t2.xm fsrxm");
		sql.append(" from XG_ZNX_NEW_SXB t");
		sql.append(" left join XG_ZNX_NEW_FXB t1");
		sql.append(" on t.xjbh = t1.xjbh");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.fsrbh = t2.xh");
		sql.append(" where t.jsrbh = '系统管理员'  and t.jsrscbj = '0'");
		sql.append(" )t where 1= 1  ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(ZnxglForm.class);
		super.setKey("xjbh");
		super.setTableName("XG_ZNX_NEW_SXB");
	}
	
	//管理员信件查看
	public HashMap<String, String> glyXjckMap(ZnxglForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xjbh,");
		sql.append(" t.jsrbh,");
		sql.append(" t.fprbh,");
		sql.append(" t.jsrydbj,");
		sql.append(" t.jsrscbj,");
		sql.append(" t1.fsrbh,");
		sql.append(" t1.fssj,");
		sql.append(" t1.ztlb,");
		sql.append(" t1.xjzt,");
		sql.append(" t1.fsrydbj,");
		sql.append(" t1.fsrscbj,");
		sql.append(" t2.xm fsrxm");
		sql.append(" from XG_ZNX_NEW_SXB t");
		sql.append(" left join XG_ZNX_NEW_FXB t1");
		sql.append(" on t.xjbh = t1.xjbh");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.fsrbh = t2.xh");
		sql.append(" where t.jsrbh = ? ");
		sql.append(" and t.xjbh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJsrbh(),t.getXjbh()});
	}
	
	//获取clob字段（信件发送内容）
	public String getFsnr_Clob(String xjbh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select fsnr from XG_ZNX_NEW_FXB t where t.xjbh = ?");
		CLOB clob  = dao.getOneClob(sql.toString(), new String[]{xjbh}, "fsnr");
		return clob.getSubString(1L, (int) clob.length());
	}
	
	//信件分配时删除分配人原有的接受记录
	public boolean delFprJsjl(ZnxglForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_ZNX_NEW_SXB where jsrbh = ? and xjbh = ? ");
		int result = dao.runDelete(sql.toString(), new String[]{t.getJsrbh(),t.getXjbh()});
		return result>0 ? true : false;
	}
	
	//站内信提醒
	public String getZnxTx(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) wdyjs from xg_znx_new_sxb t where t.jsrbh = ?  and t.jsrydbj = '0' and t.jsrscbj = '0'");
		return dao.getOneRs(sql.toString(), new String[]{username}, "wdyjs");
	}
	
	public boolean runUpate_sxbjl(ZnxglForm sx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrydbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
		
	}
    
    public boolean runUpate_sxbscjl(ZnxglForm sx)throws Exception{
    	StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrscbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
	}
    /**
     * 
     * @描述:回复保存时原收到的信件更新jsrbh为当前操作人真实登陆账号
     * @作者：yxy[工号：1206]
     * @日期：2015-10-16 下午02:36:19
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param sx
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean runUpdate_znxsjx(ZnxglForm sx) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append("update XG_ZNX_NEW_SXB set jsrbh=? where xjbh= ? and jsrbh = '系统管理员'");
    	return dao.runUpdate(sql.toString(), new String[]{sx.getJsrbh(),sx.getXjbh()});
    }
    
    /**
     * 
     * @描述:保存
     * @作者：喻鑫源[工号：1206]
     * @日期：2015-12-7 下午03:19:04
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param sx
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean save(ZnxglForm sx) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into XG_ZNX_NEW_SXB (xjbh,jsrbh,fprbh) values(?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh(),sx.getFprbh()});
    }
}
