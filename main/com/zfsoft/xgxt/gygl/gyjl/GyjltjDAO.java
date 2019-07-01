/**
 * @部门:学工产品事业部
 * @日期：2013-4-27 下午04:11:23 
 */  
package com.zfsoft.xgxt.gygl.gyjl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 公寓纪律信息DAO
 * @作者： zhangjw
 * @时间： 2013-4-27 下午04:09:18 
 * @版本：V5.9.17
 * @修改记录:   
 */

public class GyjltjDAO   extends SuperDAOImpl<GyjltjForm>{

	/*
	 * 描述: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjltjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: 
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjltjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述:  
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	/**
	 * @描述:获取违纪处分大类列表
	 * @作者：zhangjw
	 * @日期：2013-4-27 下午05:25:44
	 * @修改记录: 
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDllbxx(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.gyjllbdldm pk ,t.gyjllbdlmc val from xg_gygl_new_gyjllbdlb t  ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**

	 * @描述:获取学生违纪列表
	 * @作者：zhangjw
	 * @日期：2013-4-27 下午05:22:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<HashMap<String, String>> getXsWjlb(User user,SearchModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(model);
		String[] inputV = SearchService.getTjInput(model);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");	
		//sql.append(" select rownum ,c.* from (select  a.xymc,b.zymc,b.bjmc,b.xh,b.xm,b.xb,b.rxrq,a.qsh,a.xydm,a.zydm,a.bjdm ,  a.wjxn,a.wjxq,a.nj,a.shzt,a.lddm,a.cljg,a.gyjllbdldm,a.gyjllbdm,a.wjsj wjrq from XG_GYGL_NEW_GYJLB a left join  view_xsxxb  b on a.xh = b.xh   ");
		//sql.append(" group by a.xymc,b.xm,b.xb,b.rxrq,b.zymc,b.bjmc,b.xh,a.qsh,a.xydm,a.zydm,a.bjdm ,  a.wjxn,a.wjxq,a.nj,a.shzt,a.lddm,a.cljg,a.gyjllbdldm,a.gyjllbdm,a.wjsj) c ");
		//sql.append(" where 1=1  and cljg !='wcl'");
		sql.append(" select rownum ,c.* from (select  a.xymc,b.zymc,b.bjmc,b.xh,b.xm,b.xb,b.rxrq,a.qsh,a.xydm,a.zydm,a.bjdm from (select * from (select a.xymc,b.zymc,b.bjmc,b.xh,b.xm,b.xb,b.rxrq,a.qsh,a.xydm,a.zydm,a.bjdm ,  a.wjxn,a.wjxq,a.nj,a.shzt,a.lddm,a.cljg,a.gyjllbdldm,a.gyjllbdm,a.wjsj wjrq from XG_GYGL_NEW_GYJLB a left join view_xsxxb b  on a.xh = b.xh where a.shzt='tg')a where 1=1 ");
		sql.append(searchTj);
		sql.append(" ) a left join  view_xsxxb  b on a.xh = b.xh  ");
		sql.append(" group by a.xymc,b.xm,b.xb,b.rxrq,b.zymc,b.bjmc,b.xh,a.qsh,a.xydm,a.zydm,a.bjdm) c ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	/**
	 * @描述:获取学生违纪统计
	 * @作者：zhangjw
	 * @日期：2013-4-27 下午05:26:24
	 * @修改记录: 
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
/*	public Map<String,String> getXsWjTj(){
		StringBuffer sql = new StringBuffer();
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || substr(a.wjsj, 6, 2) || ',' || b.gyjllbdldm pk,	");
		sql.append("	               wm_concat(b.gyjllbmc) || ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	");
		sql.append("	         group by a.xh, substr(a.wjsj, 6, 2), b.gyjllbdldm)	");
		sql.append("	union all	");
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || 'qn' || ',' || b.gyjllbdldm pk,	");
		sql.append("	               ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	");
		sql.append("	         group by a.xh || ',' || 'qn' || ',' || b.gyjllbdldm)	");
		
		String[] outValues = {"pk","val"};
		Map<String,String> resultMap = new HashMap<String,String>();
		List<HashMap<String, String>> xswjTjList = dao.getListNotOut(sql.toString(), new String[]{},outValues);
		for(Map<String,String> map : xswjTjList){
			Iterator<Entry<String,String>> ite = map.entrySet().iterator();
			String pk = "";
			String val = "";
			while(ite.hasNext()){
				Entry<String,String> en = ite.next();
				if(en.getKey().equals("pk")){
					pk =en.getValue();
				}else if(en.getKey().equals("val")){
					val =en.getValue();
				}
			}
			resultMap.put(pk, val);
		}

		return resultMap;
	}*/
	public List<HashMap<String, String>> getXsWjTj(){
		StringBuffer sql = new StringBuffer();
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || substr(a.wjsj, 6, 2) || ',' || b.gyjllbdldm pk,	");
		sql.append("	               wm_concat(b.gyjllbmc) || ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	and a.shzt = 'tg' ");
		sql.append("	         group by a.xh, substr(a.wjsj, 6, 2), b.gyjllbdldm)	");
		sql.append("	union all	");
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || 'qn' || ',' || b.gyjllbdldm pk,	");
		sql.append("	               ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	and a.shzt = 'tg'");
		sql.append("	         group by a.xh || ',' || 'qn' || ',' || b.gyjllbdldm)	");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

}
