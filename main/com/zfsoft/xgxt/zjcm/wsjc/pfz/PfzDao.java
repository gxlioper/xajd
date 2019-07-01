/**
 * @部门:学工产品事业部
 * @日期：2016-2-26 下午04:57:37 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 传媒卫生分_评分组
 * @作者： cq [工号:785]
 * @时间： 2016-2-26 下午04:57:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfzDao extends SuperDAOImpl<PfzForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PfzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PfzForm t, User user)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,decode(ssxq,'001','下沙','002','桐乡') ssxqmc, nvl(b.yhCount,0) yhCount ");
		sql.append("from xg_zjcm_gygl_wsjc_pfz a left join ");
		sql.append("(select pfzid,count(1) yhCount from xg_zjcm_gygl_wsjc_pfz_pfcy group by pfzid) b ");
		sql.append("on a.pfzid=b.pfzid where 1=1 ");
		
		if(!StringUtils.isBlank(t.getPfzmc())){
			params.add(t.getPfzmc());
			sql.append(" and a.pfzmc like '%'||?||'%'");
		}
		
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(PfzForm.class);
		super.setKey("pfzid");
		super.setTableName("xg_zjcm_gygl_wsjc_pfz");

	}
	
	
	
	/**
	 * 
	 * @描述:评分成员列表
	 * @作者：cq [工号：785]
	 * @日期：2016-2-25 下午07:43:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfcyList(PfzForm model, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.zgh,a.xm,decode(a.xb,'1','男','2','女',a.xb) xbmc, ");
		sql.append("decode(a.xb,'男','1','女','2',a.xb) xbdm,a.bmdm, ");
		sql.append("(select b.bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc from fdyxxb a) where 1=1 ");
		
		if("1".equals(model.getFpzt())){
			sql.append("and zgh in (select cyzgh from xg_zjcm_gygl_wsjc_pfz_pfcy where pfzid = ? )");
		}else{
			sql.append("and zgh not in (select cyzgh from xg_zjcm_gygl_wsjc_pfz_pfcy where pfzid = ? )");
		}
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{model.getPfzid()}, inputV);
		
		return getPageList(model, sql.toString(), both);
		
	}
	
	
	/**
	 * 
	 * @描述:查看名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2016-2-26 上午08:58:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pfzmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String ChkmcExist(PfzForm model){
		
		List<String> params = new ArrayList<String>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from xg_zjcm_gygl_wsjc_pfz where pfzmc = ? ");
		params.add(model.getPfzmc());
		
		if(null!=model.getPfzid()){
			sql.append(" and pfzid <> ? ");
			params.add(model.getPfzid());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	/**
	 * 
	 * @描述:判断评分组是否有被使用
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午09:14:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUsed(String value) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from ( ");
		sql.append("select pfzid from xg_zjcm_gygl_wsjc_dfsz_dfz where pfzid = ? ");
		sql.append("union all select pfzid from xg_zjcm_gygl_wsjc_wsf where pfzid = ? )");
 		String num = dao.getOneRs(sql.toString(), new String[]{value,value}, "count");
		return Integer.parseInt(num)>0;
	}

	/**
	 * @throws Exception  
	 * @描述:删除评分组相关成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:00:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delPfcy(String[] ids) throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_ZJCM_GYGL_WSJC_PFZ_PFCY where pfzid in (");
		for (int i = 0; i < ids.length; i++) {
			if(i==0){
				sql.append(" ? ");
			}else{
				sql.append(" , ? ");
			}
			params.add(ids[i]);
		}
		
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * @throws Exception  
	 * @描述:保存评分成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:41:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param value
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean savePfcy(PfzForm model, String value) throws Exception {
		
		List<String> params = new ArrayList<String>();
		params.add(model.getPfzid());
		String[] values = value.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_ZJCM_GYGL_WSJC_PFZ_PFCY(cyzgh,Pfzid) select zgh,? from fdyxxb where zgh in ( ");
		for (int i = 0; i < values.length; i++) {
			if(i==0){
				sql.append(" ? ");
			}else{
				sql.append(" ,? ");
			}
			params.add(values[i]);
		}
		
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @描述:取消评分成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午11:08:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param value
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean savePfcyQx(PfzForm model, String value) throws Exception {
		String[] values = value.split(",");
		List<String> params = new ArrayList<String>();
		params.add(model.getPfzid());
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_ZJCM_GYGL_WSJC_PFZ_PFCY where pfzid = ? and cyzgh in ( ");
		for (int i = 0; i < values.length; i++) {
			if(i==0){
				sql.append(" ? ");
			}else{
				sql.append(" ,? ");
			}
			params.add(values[i]);
		}
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @描述:用户查看
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午03:22:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfzList(PfzForm model, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.zgh,a.xm,decode(a.xb,'1','男','2','女',a.xb) xbmc, ");
		sql.append("(select b.bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc from fdyxxb a ");
		sql.append("where zgh in (select cyzgh from XG_ZJCM_GYGL_WSJC_PFZ_PFCY where pfzid = ? )) where 1=1 ");
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{model.getPfzid()}, inputV);
		
		return getPageList(model, sql.toString(), both);
	}
	
	/**
	 * 
	 * @描述:查询组信息
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午07:28:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pfzid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZxx(String pfzid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,decode(ssxq,'001','下沙','002','桐乡') ssxqmc, nvl(b.yhCount,0) yhCount ");
		sql.append("from xg_zjcm_gygl_wsjc_pfz a left join ");
		sql.append("(select pfzid,count(1) yhCount from xg_zjcm_gygl_wsjc_pfz_pfcy group by pfzid) b ");
		sql.append("on a.pfzid=b.pfzid where a.pfzid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pfzid});
		
	}

}
