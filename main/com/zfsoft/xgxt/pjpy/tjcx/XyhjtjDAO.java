/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 下午05:06:37 
 */  
package com.zfsoft.xgxt.pjpy.tjcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优 统计查询 学院获奖统计查询
 * @作者： zhangjw 
 * @时间： 2013-5-16 下午05:09:00 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class XyhjtjDAO extends SuperDAOImpl<XyhjtjForm>{
	/**
	 * @描述:评奖结果历史表
	 * @作者：zhangjw
	 * @日期：2013-5-16 下午05:19:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getPjlsxxb(){
		List<String> resultList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmmc  from xg_pjpy_pjlsxxb group by xmmc ");
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), new String[]{});
		Iterator<HashMap<String, String>> ite = result.iterator();
		while(ite.hasNext()){
			HashMap<String, String> map = ite.next();
			Iterator<Entry<String, String>> mapListIterator= map.entrySet().iterator();
			while(mapListIterator.hasNext()){
				Entry<String, String> entry = mapListIterator.next();
				if(entry.getKey().equals("xmmc")){
					resultList.add(entry.getValue());
				}
			}
		}
		return resultList;
	}
	/**
	 * @描述:评奖结果历史表 统计
	 * @作者：zhangjw
	 * @日期：2013-5-16 下午05:19:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<List<String>> 返回类型 
	 * @throws
	 */
	public List<List<String>> getPjlsxxbTj(String [] st,User user){
		List<List<String>> resultList = new ArrayList<List<String>>();
		StringBuffer sql = new StringBuffer();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		//查询需要执行的sql 行转列
		String[] str = dao.getOneRs(" select fun_xg_pjpy_shxjxy() str from dual ", new String[]{}, new String[]{"str"});
		sql.append(str[0]);
		sql.append(" where 1=1 ");
		sql.append(" and pjsj= '"+Base.currXn+"'");
		sql.append(searchTjByUser);
		List<HashMap<String, String>> result = dao.getArrayList(sql.toString(),new String[]{}, st);
		Iterator<HashMap<String, String>> ite = result.iterator();
		while(ite.hasNext()){
			List<String> inList = new ArrayList<String>();
			HashMap<String, String> map = ite.next();
			for (int i = 0; i < st.length; i++) {
				inList.add(map.get(st[i]));
			}
			resultList.add(inList);
		}
		return resultList;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyhjtjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyhjtjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
}
