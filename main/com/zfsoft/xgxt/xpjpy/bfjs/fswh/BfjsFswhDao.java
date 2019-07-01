/**
 * @部门:学工产品事业部
 * @日期：2016-4-23 下午01:35:20 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.fckeditor.tool.Utils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmModel;

/** 
 * @系统名称: 班级工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 竞赛分数
 * @作者： xiaxia [工号：1104]
 * @时间： 2016-4-23 下午01:35:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfjsFswhDao extends SuperDAOImpl<BfjsFswhModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(BfjsFswhModel.class);
		super.setTableName("xg_qzxy_bfjs_bjfs");
		super.setKey("id");

	}

	public List<HashMap<String, String>> getPageList(BfjsFswhModel t, List<HashMap<String, String>> JsxmList, User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		String[] ids=null;
		if(null!=t.getId()){
			ids = t.getId().split(",");	
		}
		sql.append("select id,bjdm,nj,xydm,zydm,");
		for (int i = 0; i < JsxmList.size(); i++) {
				sql.append("fs" + i + ",");
		}

		sql.append("(select bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm) bjmc from (");
		sql.append("select id,bjdm,nj,xydm,zydm");

		for (int i = 0; i < JsxmList.size(); i++) {
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}

		sql.append(" from (");
		sql.append("select t1.id,t1.bjdm,t1.nj,t1.xydm,t1.zydm ");
		for (int i = 0; i < JsxmList.size(); i++) {
			sql.append(",case when t1.xmdm='").append(JsxmList.get(i).get("xmdm")).append("' then fs else '' end fs").append(i);
		}
		sql.append(" from ( select t2.id,t3.xn,t3.xq,t3.fs,t3.xmdm,t1.*,t2.tjzt from view_njxyzybj_all t1 left join xg_bfjs_fstjjlb t2 on t1.bjdm=t2.bjdm");
		sql.append("  left join xg_qzxy_bfjs_bjfs t3 on t2.bjdm=t3.bjdm and t2.xn=t3.xn and t2.xq=t3.xq where  t2.xn||t2.xq = (select xn||xq from xg_qzxy_bfjs_cssz)");
		// 竞赛分只能录入未提交
		if (StringUtils.isNotNull(t.getEditType())) {
			sql.append(" and t2.tjzt <> '1' and t2.bjdm is not null ");
		}
		
		if (!StringUtils.isNull(t.getId()) && null != ids && 0 != ids.length) {
			sql.append("and t1.bjdm in (select bjdm from xg_bfjs_fstjjlb where id in ('");
			for (int i = 0; i < ids.length; i++) {
				if (0 == i) {
					sql.append(ids[i]);
				} else {
					sql.append("','" + ids[i]);
				}
			}
			sql.append("')) ");
		}
		sql.append(searchTj);
		sql.append(searchTjByUser);
		

		sql.append(")t1) group by id,nj,xydm,zydm,bjdm order by nj,xydm,zydm,bjdm) a  ");
		if(StringUtils.isNotNull(t.getCxlx())){
			return dao.getListNotOut(sql.toString(), inputV);
		}else{
			return super.getPageList(t, sql.toString(), inputV);
		}
		
	}
	
	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BfjsFswhModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 竞赛班级提交查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 上午10:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getJsbjList(BfjsFswhModel t,List<HashMap<String, String>> jsxmList, User user)
		throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,tjzt, decode(tjzt,'1','已提交','2','取消提交','未提交') tjztmc,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
		for (int i = 0 ; i < jsxmList.size() ; i++){
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}
		
		sql.append(" from ( select t1.*");
		for (int i = 0 ; i < jsxmList.size() ; i++){
			sql.append(",case when t2.xmdm='").append(jsxmList.get(i).get("xmdm"))
			   .append("' then fs else '' end fs").append(i);
		}
        sql.append(" from(select t1.id,t1.xn,t1.xq,t1.tjzt,t2.* from xg_bfjs_fstjjlb t1 left join ");
		sql.append("view_njxyzybj_all t2 on t1.bjdm=t2.bjdm where t1.xn||t1.xq =(select xn || xq from XG_QZXY_BFJS_CSSZ) and t2.bjdm is not null");
		sql.append(searchTjByUser);
		sql.append(" ) t1 left join xg_qzxy_bfjs_bjfs t2 on t1.bjdm = t2.bjdm and t1.xn = t2.xn and t1.xq = t2.xq)where 1=1 ");
		sql.append(searchTj);
		sql.append(" group by id,tjzt,bjdm,bjmc,zydm,zymc,xydm,xymc,nj order by decode(tjzt, '1','1','0'),nj,xydm,zydm,bjdm");
		return getPageList(t, sql.toString(), inputValue);
	}
	

	
	/**
	 * 
	 * @描述: 删除分数
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-23 下午02:16:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型
	 * @throws Exception 
	 */
	public boolean delBfjsFswh(String xn,String xq ,String xmdm) throws Exception{
		
		String sql = "delete from xg_qzxy_bfjs_bjfs where xn=? and xq=? and (xmdm=? or xmdm in (select fjdm from xg_qzxy_bfjs_jsxm where xmdm=?))";
		
		return dao.runUpdate(sql, new String[]{xn,xq,xmdm,xmdm});
	}
	
	
	/**
	 * 
	 * @描述: 删除指定周期的分数提交记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-23 下午04:17:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型
	 * @throws Exception 
	 */
	public boolean delTjjl(String xn,String xq,User user) throws Exception{
		StringBuilder sql = new StringBuilder("delete from xg_bfjs_fstjjlb t1 where t1.xn=? and t1.xq=? ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and exists (select 1 from view_njxyzybj t2 where t1.bjdm=t2.bjdm and t2.xydm='")
			   .append(user.getUserDep())
			   .append("')");
		}
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BfjsFswhModel t)
			throws Exception {
		return null;
	}
	public boolean isCanSubmit(String values, BfjsFswhModel model, User user) throws Exception{
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sql = new StringBuffer();
		String[] value = values.split(",");
		sql.append("select count(1) count from (");
		sql.append("select a.id,a.tjzt,b.* from xg_qzxy_bfjs_jsxm d left join xg_bfjs_fstjjlb a on a.xn=d.xn  and a.xq=d.xq ");
		sql.append("left join view_njxyzybj_all b on a.bjdm=b.bjdm left join xg_qzxy_bfjs_bjfs c on a.bjdm=c.bjdm and a.xn=c.xn and a.xq=c.xq and d.xmdm=c.xmdm where c.fs is null");
		sql.append(searchTjByUser);
		sql.append(" and d.xn||d.xq = (select xn||xq from xg_qzxy_bfjs_cssz where rownum=1) and d.xmdm not in (select fjdm from xg_qzxy_bfjs_jsxm) ) where 1=1 ");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTj);
		String num = dao.getOneRs(sql.toString(), inputV, "count");
		
		return Integer.parseInt(num)==0;
	}
	
	/**
	 * 
	 * @描述: 查询有某的项目分数
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 下午03:03:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型
	 */
	public HashMap<String, String> getFsid(BfjsFswhModel t){
		
		String sql = "select id,nvl(fs,0)fs from xg_qzxy_bfjs_bjfs where xmdm=? and bjdm=?";
		
		return dao.getMapNotOut(sql, new String[]{t.getXmdm(),t.getBjdm()});
	}
	
	
	
	
	/**
	 * 
	 * @描述: 提交班级竞赛分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 下午06:50:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param lrr
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean submitBfjsFswh(String values, String tjzt,BfjsFswhModel model, User user) throws Exception{
		//用户权限
		
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_bfjs_fstjjlb a set tjzt = ?,tjr=?,tjsj=to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') where id=?");
		
		return dao.runUpdate(sql.toString(), new String[]{tjzt,user.getUserName(),values});
	}


	/**
	 * 
	 * @描述: 将默认项目的分数插入分数记录表
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:41:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean insertDefaultJsxmf(BfjsJsxmModel t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_qzxy_bfjs_bjfs(bjdm,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.bjdm,?,?,?,?,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_bfjs_fstjjlb t1");
		sql.append(" where t1.xn=? and t1.xq=? ");
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getXn(),t.getXq(),t.getMrfs(),t.getXn(),t.getXq()});
	}
	
	public boolean updateDefaultJsxmf(BfjsJsxmModel t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_qzxy_bfjs_bjfs set fs=? ");
		sql.append(" where bjdm in (select bjdm  from xg_bfjs_fstjjlb where tjzt <>'1' and xn =? and xq=? ) and xmdm=?");
		return dao.runUpdate(sql.toString(), new String[]{t.getMrfs(),t.getXn(),t.getXq(),t.getXmdm()});
	}
	
	
	


	
	
	
	/**
	 * 
	 * @描述: 按ID取班级信息
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getXyxxById(String[] ids){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t2.xydm,t2.xymc,t1.xn,t1.xq from xg_bfjs_fstjjlb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where t1.id in(");
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), ids);
	}
	
	
	/**
	 * 
	 * @描述: 按ID取班级信息 ( 可以多个班级
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getBjxxById(BfjsFswhModel model, User user) throws Exception {
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		String[] ids = model.getId().split(",");
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select t1.bjdm,t2.bjmc,t1.xn,t1.xq from xg_bfjs_fstjjlb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where ");
		sql.append("xn||xq = (select xn||xq from xg_qzxy_bfjs_cssz where rownum = 1 ) and t1.bjdm is not null ");
		if(!Utils.isBlank(model.getId())){
			sql.append(" and t1.id in ( ");
			for (int i = 0; i < ids.length; i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(ids[i]);
			}
			sql.append(") ");
			
		}
		
		sql.append(searchTjByUser);
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	
	/**
	 * 
	 * @描述: 批量取班级信息 
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getBjxxByIds(BfjsFswhModel model, User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		String [] ids = model.getId().split(",");
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("select t1.* from (");
		sql.append("select t1.bjdm,t1.bjmc,nvl(tjzt, '0') tjzt,(select xm from yhb t3 where t1.lrr=t3.yhm) lrrxm, ");
		sql.append(" t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc from ");
		sql.append("(select t1.id,t1.xn,t1.xq,t1.cpzdm,t1.lrr,t1.tjsj,t1.tjzt,t2.* from xg_bfjs_fstjjlb t1 left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm ) t1 ");
		sql.append("where t1.xn='"+model.getXn()+"' and t1.xq= '"+model.getXq()+"' ");
		if(!StringUtil.isNull(model.getId())&&null!=ids&&0!=ids.length){
			sql.append(" and t1.id in ( '");
			 for (int i=0;  i<ids.length; i++) {
				if(0==i){
					sql.append(ids[i]);
				}else{
					sql.append("','"+ids[i]);
				}
			}
			sql.append("')");
		}
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);
		
	}
	
	/**
	 * 
	 * @描述: 按ID取班级信息
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String, String>> getBjxxByIdpl(BfjsFswhModel model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.bjdm,t2.bjmc,t1.xn,t1.xq from xg_bfjs_fstjjlb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where t1.id=?");
		return null;
		
	}
	

	
	/**
	 * 
	 * @描述: 分学院比例计算竞赛分
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-4-1 下午05:00:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean computeJspmByXy(String xn ,String xq ,String bjdm) throws Exception{
		return dao.runProcuder("{call pro_xg_qzxy_bfjs_jspm_xy(?,?,?)}", 
				new String[]{xn,xq,bjdm});
	}
	
	
	/**
	 * 
	 * @描述: 批量插入竞赛分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-29 下午03:27:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public boolean batchInsertBfjsFswh(List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_qzxy_bfjs_bjfs t1");
		sql.append(" USING (select ? bjdm, ? xn, ? xq, ? xmdm, ? fs , ? lrr , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
		sql.append(" ON (t1.bjdm=t2.bjdm and t1.xmdm=t2.xmdm)");
		sql.append(" WHEN MATCHED THEN");
		sql.append("   UPDATE");
		sql.append("     SET xn=t2.xn,xq=t2.xq,fs=t2.fs,lrr=t2.lrr,");
		sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
		sql.append("   WHERE bjdm=? and xmdm=?");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (bjdm, xn, xq, xmdm, fs, lrr, lrsj)");
		sql.append("  VALUES (t2.bjdm, t2.xn, t2.xq, t2.xmdm, t2.fs, t2.lrr, t2.lrsj)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 批量更新竞赛分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-29 下午03:28:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public boolean batchUpdateBfjsFswh(List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_bfjs_fstjjlb set bjdm=?,xn=?,xq=?,xmdm=?,fs=?,lrr=?,");
		sql.append("lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where bjdm=? and xmdm=?");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	
	
	/**
	 * 
	 * @描述:取消提交竞赛分查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-30 上午10:21:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJsfqxList(BfjsFswhModel t, User user)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.id,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.tjr, ");
		sql.append(" (select xm from yhb b where a.tjr=b.yhm)tjrxm,a.tjsj  from xg_bfjs_fstjjlb a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm  ");
		sql.append(" where tjzt = '1' and exists (select 1 from xg_qzxy_bfjs_cssz b where a.xn=b.xn and a.xq = b.xq and rownum = 1) ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}


	/** 
	 * @描述: 竞赛结果查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-31 下午04:16:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param Jsfpm
	 * @param JsxmList
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
		
	public List<HashMap<String, String>> getJsfjgList(BfjsFswhModel t, User user,List<HashMap<String,String>> JsxmList)
		throws Exception {
	
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.*");
		sql.append(",t1.xn||' '||(select xqmc from xqdzb b where t1.xq=b.xqdm) jszq,t3.bjmc,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.nj");
		sql.append(" from (select ");
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		sql.append(" a.bjdm,a.xn,a.xq from ( select ");
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			sql.append(" case when b.xmdm='").append(JsxmList.get(i).get("xmdm"))
			   .append("' then b.fs else '' end fs")
			   .append(i).append(",");
			sql.append(" case when b.xmdm='").append(JsxmList.get(i).get("xmdm"))
			   .append("' then b.xypm").append(" else '' end pm")
			   .append(i).append(",");
		}	
		sql.append("  a.bjdm,a.xn,a.xq from xg_bfjs_fstjjlb a left join xg_qzxy_bfjs_bjfs b on a.bjdm = b.bjdm  ");
		sql.append("  and a.xn=b.xn and a.xq=b.xq where a.tjzt='1' )a group by a.bjdm,a.xn,a.xq)t1 left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm ");
		sql.append(" order by ");
		if (JsxmList.size() > 0){
			sql.append(" t1.pm0 ");
		}
		sql.append(" ,t3.xydm ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{},inputValue));
	}
	public String getYtjZcfNum(String xn , String xq){
		
		String sql = "select count(1) num from xg_bfjs_fstjjlb where xn=? and xq=? and tjzt='1'";
		
		return dao.getOneRs(sql, new String[]{xn,xq}, "num");
	}
	
	 /**
	 * @throws Exception  
	 * @描述:更新
	 * @作者：xiaxia [工号：1104]
	 * @日期：2013-8-1 上午09:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateTjjL(String id, User user, String defaultQxtj) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" update xg_bfjs_fstjjlb set tjzt = '"+defaultQxtj+"',tjr = '',tjsj='' where id in ( ");
		String[] ids = id.split(",");
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}


	/** 
	 * @描述:根据ID查询班级信息
	 * @作者：xiaxia [工号：1104]
	 * @日期：2015-2-5 下午03:59:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getBjInfo(String id) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t3.nj, t3.xydm, t3.xymc, t3.zydm, t3.zymc, t3.bjdm, t3.bjmc from xg_bfjs_fstjjlb t1 ");
		sql.append("left join yhb t2 on t1.lrr = t2.yhm left join view_njxyzybj_all t3 on t1.bjdm = t3.bjdm ");
		sql.append("where t1.id = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
		
	}
	
	/**
	 * 
	 * 判断是否为空
	 * 
	 */
	public Boolean getIsNotblank(List<HashMap<String, String>> bjxxMap,BfjsFswhModel model){
		
		boolean isBlank = false;
		
		if(!org.apache.commons.lang.StringUtils.isBlank(model.getId())&&null!=bjxxMap&&0!=bjxxMap.size()){
			isBlank = true;
		}
		
		return isBlank;
	}

	/** 
	 * @描述:查看是否还有未提交记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2015-2-9 下午04:07:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String isSubmitInfo(BfjsFswhModel t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(1) count from (select t1.id,t1.bjdm,t1.lrr,t2.xm lrrxm,");
		sql.append("t1.tjsj,nvl(tjzt, '0') tjzt,decode(t1.tjzt, '0','未提交', '1', '已提交','2',");
		sql.append(" '取消提交', '','未提交') tjztmc,t3.nj,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjmc,t4.bjgs");
		sql.append(" from xg_bfjs_fstjjlb t1 left join yhb t2 on t1.lrr = t2.yhm");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm = t3.bjdm");
		sql.append(" left join (select bjdm, count(1) bjgs from xg_pjpy_new_cpmdb");
		sql.append(" where xn || xq in (select xn || xq from xg_qzxy_bfjs_cssz where rownum = 1) group by bjdm) t4 on t1.bjdm = t4.bjdm");
		sql.append(" where exists (select 1 from xg_qzxy_bfjs_cssz t5 where t1.xn || t1.xq = t5.xn || t5.xq) and t1.tjzt = '0') where 1=1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return dao.getOneRs(sql.toString(), inputValue, "count");
		
	}





	

	
	
}
