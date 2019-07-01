/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:13:22 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午03:13:09 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcsqDao extends SuperDAOImpl<DazcsqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs,'1','邮寄','2','自带',t.zcfs)zcfsmc,t.zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq, ");
		sql.append("t.sjlrr,t.sjlrsj,t.splcid, ");
		sql.append("t.shzt,decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append(")a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(DazcsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_xsxx_dagl_dazcsqb");
	}
	
	/**
	 * @描述: 判断唯一键，学号(xh)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:06:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcsqForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xsxx_dagl_dazcsqb where xh = ? ");
		paraList.add(model.getXh());
		if(StringUtils.isNotNull(model.getSqid())){
			sql.append(" and sqid <> ? ");
			paraList.add(model.getSqid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*存在数据时返回false，不存在时返回true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @描述: 删除时清理审核状态表中的垃圾数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-05-09 上午10:39:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xtwh_shztb where ywid in ( ");
		for (int i = 0; i < sqids.length; i++) {
			sql.append("?");
			if(i != sqids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), sqids);
	}
	
	/**
	 * @描述: 根据申请id获取相关信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:40:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_dagl_dazcsqb where");
		for(int i = 0; i < sqidArr.length; i++){
			sql.append(" sqid = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	
	/**
	 * @描述: 根据申请ID获得学生申请信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午05:47:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs,'1','邮寄','2','自带',t.zcfs)zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq, ");
		sql.append("t.sjlrr,t.sjlrsj,t.splcid, ");
		sql.append("t.shzt,decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append(")t where t.sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}

}
