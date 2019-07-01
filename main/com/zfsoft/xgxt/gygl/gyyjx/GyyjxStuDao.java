/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 下午04:11:55 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 下午04:11:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxStuDao extends SuperDAOImpl<GyyjxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
 		//权限控制
		String searchTjQx="";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";				//公寓辅导员数据范围过滤条件
 		
		 /* 当用户为公寓管理员时，xg_gygl_new_gyfdyb 中 存在该用户数据
		 * 不考虑其作为学院或者辅导员的权限控制，只控制楼栋数据范围
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
 		
 		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t1.* from (select a.*, a.gyyjid||'@@'||a.fkqk gyyjid_fkqk, f.yjflmc, b.nj , b.xm, b.xb, b.xydm , b.xymc , b.zydm , b.zymc , b.bjdm , b.bjmc , d.lddm , d.ldmc,c.qsh ,c.cwh,e.ch,e.qsxb,")
		.append("  decode(a.fkqk ,'0' ,'未反馈' , '1' , '已反馈' , '未知') fkqkmc ")
		.append("  from XG_GYGL_GYYJX_GYYJ a ")
		.append("  left join view_xsjbxx b ")
		.append("    on a.xh = b.xh ")
		.append("  left join XG_GYGL_NEW_CWXXB c ")
		.append("    on a.xh = c.xh ")
		.append("  left join xg_gygl_new_ldxxb d on c.lddm = d.lddm ")
		.append("  left join xg_gygl_new_qsxxb e on c.lddm = e.lddm and c.qsh = e.qsh left join XG_GYGL_GYYJX_YJFL f on a.YJFLDM = f.YJFLDM ) t1 where 1=1 ")
		.append(searchTj).append("  ").append(searchTjQx);
		
		return getPageList(t, sql.toString(), inputV);
	}

	public HashMap<String , String> getModelMap(String gyyjid){
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*, a.gyyjid||'@@'||a.fkqk gyyjid_fkqk, f.yjflmc, b.nj , b.xm, b.xb, b.xydm , b.xymc , b.zydm , b.zymc , b.bjdm , b.bjmc , d.lddm , d.ldmc,c.qsh ,c.cwh,e.ch,e.qsxb,")
		.append("  decode(a.fkqk ,'0' ,'未反馈' , '1' , '已反馈' , '未知') fkqkmc ")
		.append("  from XG_GYGL_GYYJX_GYYJ a ")
		.append("  left join view_xsjbxx b ")
		.append("    on a.xh = b.xh ")
		.append("  left join XG_GYGL_NEW_CWXXB c ")
		.append("    on a.xh = c.xh ")
		.append("  left join xg_gygl_new_ldxxb d on c.lddm = d.lddm ")
		.append("  left join xg_gygl_new_qsxxb e on c.lddm = e.lddm and c.qsh = e.qsh left join XG_GYGL_GYYJX_YJFL f on a.YJFLDM = f.YJFLDM ) t1 where 1=1 and gyyjid = ? ");

		return dao.getMapNotOut(sql.toString(), new String[]{gyyjid});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(GyyjxForm.class);
		setKey("GYYJID");
		setTableName("XG_GYGL_GYYJX_GYYJ");
	}

}
