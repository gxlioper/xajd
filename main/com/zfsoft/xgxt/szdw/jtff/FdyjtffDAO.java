/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:14:23 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(辅导员津贴发放--山东潍坊) 
 * @作者： CMJ [工号：913]
 * @时间： 2013-8-5 上午11:14:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyjtffDAO extends SuperDAOImpl<FdyjtffForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyjtffForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyjtffForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		/*StringBuilder sql = new StringBuilder("select * from (select a.id,a.zgh,a.bzlx,decode(a.bzlx,'0','岗位补助','1','绩效考核补助')bzlxmc,a.kpdj,decode(a.kpdj,'0','优秀','1','良好','2','合格')kpdjmc,a.xn,a.xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,a.bzje,b.xm,b.bmdm,(select bmmc from zxbz_xxbmdm e where b.bmdm=e.bmdm)bmmc,b.xb,(select xb from dmk_xb e where b.xb=e.xbdm) xbmc,b.lxdh");
		sql.append(" from WFXY_FDYJTXXB a left join fdyxxb b on a.zgh=b.zgh) where 1=1 ");*/
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from VIEW_NEW_DC_SZDW_JTFF where 1=1 ");
		
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("WFXY_FDYJTXXB");
		super.setKey("Id");
		super.setClass(FdyjtffForm.class);
		
	}

	/** 
	 * @描述:TODO(判断辅导员津贴发放是否已录入)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午01:56:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(FdyjtffForm model) {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		sql.append("select *　from WFXY_FDYJTXXB where zgh=? and xn=? and xq=?");
		List<HashMap<String, String>> list=dao.getArrayList(sql.toString(), new String[]{model.getZgh(),model.getXn(),model.getXq()}, new String[]{"zgh"});
		return list.size()!=0;
	}

	/** 
	 * @描述:TODO(获取辅导员信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午02:39:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getFdyjbxx(String zgh) {
		// TODO 自动生成方法存根
		String sql="select zgh,xm,(case xb when '1' then '男' when '男' then '男' when '2' then '女' when  '女' then '女' else '' end) xbmc,lxdh,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc from fdyxxb a where zgh=?";
		return dao.getMap(sql, new String[]{zgh}, new String[]{"zgh","xm","xbmc","bmmc","lxdh"});
	}

	/** 
	 * @描述:TODO(获取辅导员List)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午02:47:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(FdyjtffForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.*,(select zcmc from zcb f where a.zc=f.zcdm)zcmc,(case xb when '1' then '男' when '男' then '男' when '2' then '女' when  '女' then '女' else '' end) xbmc");
		sql.append(" from view_fdyxx a) a where 1 = 1");
		sql.append(searchTj);
		
		if("zxswh".equals(model.getFdyjtfflx())){ // 心理健康咨询-咨询师管理-咨询师维护
			sql.append(" and (not exists (select 1 from xg_view_xlzx_zxsxx z where a.zgh=z.zgh and z.datazt='1' and z.status = '1')) ");
		}
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:获取辅导员【寝室导师】（不刷新父页面）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 上午08:46:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyQsdsPageList(FdyjtffForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select lxdh,zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(case xb when '1' then '男' when '男' then '男' when '2' then '女' when  '女' then '女' else '' end) xbmc");
		sql.append(" from fdyxxb a where exists (select 1 from XG_GYGL_QSDS qsdsb where a.zgh=qsdsb.zgh)) a where 1 = 1");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	public FdyjtffForm getModel(FdyjtffForm model)throws Exception{
		String sql="select id,zgh,bzlx,decode(bzlx,'0','岗位补助','1','绩效考核补助')bzlxmc,kpdj,decode(kpdj,'0','优秀','1','良好','2','合格')kpdjmc,xn,xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,bzje from wfxy_fdyjtxxb a where id=?";
		FdyjtffForm myForm=new FdyjtffForm();
		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{model.getId()});
		BeanUtils.copyProperties(myForm, map);
		return myForm;
	}

}
