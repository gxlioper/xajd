/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:59:15 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

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
 * @类功能描述: 档案转出-结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:54:21 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcjgDao extends SuperDAOImpl<DazcjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,zymc,a.bjdm,a.bjmc,");
		sql.append("b.guid,b.zcfs,b.yjdz,b.yzbm,b.sjr,b.sjrdh,b.dwmc,b.dwdz,b.kdfs,b.kddh,b.yjsj,");
		sql.append("b.yjzt,b.zddacn,b.yqtdrq,b.sjtdrq,b.sjtdr,b.dazcxx,b.sjly,b.ywid,b.sjlrr,b.sjlrsj,");
		sql.append("decode(b.zcfs, '1', '邮寄', '2', '自带', b.zcfs) zcfsmc,");
		sql.append("decode(b.yjzt, '1', '已邮寄', '2', '未邮寄', b.yjzt) yjztmc,");
		sql.append("case b.dazcxx when '1' then '已登记' when '2' then '已转出' else '未登记' end dazcxxmc ");
		sql.append("from view_xsjbxx a left join xg_xsxx_dagl_dazcjgb b on a.xh = b.xh");
		sql.append(")t where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(DazcjgForm.class);
		this.setKey("guid");
		this.setTableName("xg_xsxx_dagl_dazcjgb");
	}
	
	/**
	 * @描述: 验证唯一性，学号(xh)
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午04:06:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xsxx_dagl_dazcjgb where xh = ?  ");
		paraList.add(model.getXh());
		if(StringUtils.isNotNull(model.getGuid())){
			sql.append(" and guid <> ? ");
			paraList.add(model.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*存在数据时返回false，不存在时返回true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @描述: 通过guid查看学生档案转出相关信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午05:24:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,zymc,a.bjdm,a.bjmc,");
		sql.append("b.guid,b.zcfs,b.yjdz,b.yzbm,b.sjr,b.sjrdh,b.dwmc,b.dwdz,b.kdfs,b.kddh,b.yjsj,");
		sql.append("b.yjzt,b.zddacn,b.yqtdrq,b.sjtdrq,b.sjtdr,b.dazcxx,b.sjly,b.ywid,b.sjlrr,b.sjlrsj,");
		sql.append("decode(b.zcfs, '1', '邮寄', '2', '自带', b.zcfs) zcfsmc,");
		sql.append("decode(b.yjzt, '1', '已邮寄', '2', '未邮寄', b.yjzt) yjztmc,");
		sql.append("case b.dazcxx when '1' then '已登记' when '2' then '已转出' else '未登记' end dazcxxmc ");
		sql.append("from view_xsbfxx a left join xg_xsxx_dagl_dazcjgb b on a.xh = b.xh");
		sql.append(") where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 根据学号查看结果表是否有数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 上午09:17:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getDazcjgRs(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)cnt from xg_xsxx_dagl_dazcjgb where xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "cnt");
	}
	
	/**
	 * @描述: 通过申请id删除结果
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:37:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delShjgById(String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_xsxx_dagl_dazcjgb where ywid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{sqid});
	}

}
