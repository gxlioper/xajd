/**
 * @部门:学工产品事业部
 * @日期：2016年12月27日 上午11:34:16 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-资助款发放管理模块
 * @类功能描述: 学生资助Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2016年12月27日 上午11:34:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzkffDao extends SuperDAOImpl<ZzkffForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzkff");
		super.setKey("id");
		super.setClass(ZzkffForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzkffForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzkffForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select t1.id,t2.xh,t2.xm,t2.xb,t2.sfzh,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,");
		sql.append("t1.xn,t2.zzmmmc,t2.mz,t2.nj,t2.yhmc,t2.yhkh,t1.wsyhzt,t1.yhfkxx,");
		sql.append("(select xqmc from xqdzb where xqdm = t1.xq) xqmc,t1.xq,");
		sql.append("t1.xmmc,t1.je from xg_xszz_new_zzkff t1 left join VIEW_XSJBXX t2 on t1.xh = t2.xh) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @描述:根据学号、学年、学期、项目名称查询资助款信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月28日 下午5:59:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Integer 返回类型 
	 * @throws 
	 */
	public boolean IsXmmcRepeat(ZzkffForm zzkffForm){
		String sql = "select count(1) count from xg_xszz_new_zzkff where xh = ? and xn = ? and xq = ? and xmmc = ? ";
		String xh = zzkffForm.getXh();
		String xn = zzkffForm.getXn();
		String xq = zzkffForm.getXq();
		String xmmc = zzkffForm.getXmmc();
		String id = zzkffForm.getId();
		List<String> inputList = new ArrayList<String>();
		inputList.add(xh);
		inputList.add(xn);
		inputList.add(xq);
		inputList.add(xmmc);
		
		if(!StringUtil.isNull(zzkffForm.getId())){
			sql += "and id != ?";
			inputList.add(id);
		}
		String [] inputValue = inputList.toArray(new String[]{});
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}
	
	/**
	 * @描述:重载getModel，自定义sql和入参
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月28日 下午5:59:05
	 * @throws Exception 
	 */
	public ZzkffForm getModel(String id) throws Exception{
		String sql = "select t1.*,t2.xqmc from  xg_xszz_new_zzkff t1 left join xqdzb t2 on t1.xq = t2.xqdm where id = ?";
		return super.getModel(sql, new String[]{id});
	}

}
