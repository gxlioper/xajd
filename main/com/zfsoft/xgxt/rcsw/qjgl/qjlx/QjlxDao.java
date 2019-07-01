/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QjlxDao extends SuperDAOImpl<QjlxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjlxForm t)
			throws Exception {
		List<String> param=new ArrayList<String>();
		StringBuffer sb=new StringBuffer();
		sb.append("select t.* from xg_rcsw_qjgl_qjlx t");
		sb.append(" where 1=1 ");
		if(StringUtils.isNotNull(t.getQjlxid())){
			sb.append(" and qjlxid=?");
			param.add(t.getQjlxid());
		}
		if(StringUtils.isNotNull(t.getQjlxmc())){
			sb.append(" and qjlxmc=?");
			param.add(t.getQjlxmc());
		}
		return this.dao.getListNotOut(sb.toString(),param.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjlxForm t, User user)
			throws Exception {
/*		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());*/
		
		StringBuffer sb=new StringBuffer();
		sb.append("select t.*,decode(t.open,'1','开启','关闭') openzt from xg_rcsw_qjgl_qjlx t");
		sb.append(" where 1=1 ");
		return this.getPageList(t, sb.toString(), new String[]{});
	}
	/**
	 * 
	 * @描述：获取下一个请假类型id
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:54:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNextId(){
		String qjlxid=dao.getOneRs("select max(to_number(qjlxid)) qjlxid from xg_rcsw_qjgl_qjlx ",new String[]{},"qjlxid");
		//如果不存在数据，默认为00
		if(StringUtils.isNull(qjlxid)){qjlxid="00";}
		String newId=String.valueOf(Integer.parseInt(qjlxid)+1);
		if(newId.length()==1){
			newId="0"+newId;
		}
		return newId;
	}
	/**
	 * 
	 * @描述:是否可以删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:12:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String qjlxid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_qjgl_qjsq where qjlxid=?");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{qjlxid});
		return null==map||map.size()<=0?true:false;
	}
	/**
	 * 
	 * @描述:验证请假类型，如果已经添加过不允再进行添加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:18:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkQjlx(QjlxForm qf){
		String qjlxid=dao.getOneRs("select qjlxid from xg_rcsw_qjgl_qjlx where qjlxmc=?",new String[]{qf.getQjlxmc()},"qjlxid");
		//如果获取到对应的id则不运行再添加
		//不包括自身
		if(null!=qf.getQjlxid()&&qf.getQjlxid().equals(qjlxid)){return true;}
		return StringUtils.isNotNull(qjlxid)?false:true;
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjlxid");
		this.setTableName("xg_rcsw_qjgl_qjlx");
		this.setClass(QjlxForm.class);
	}
	

}
