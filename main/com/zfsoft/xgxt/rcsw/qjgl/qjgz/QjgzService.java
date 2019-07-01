/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述:请假规则service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjgzService extends SuperServiceImpl<QjgzForm, QjgzDao> {
	QjgzDao dao = new QjgzDao();
	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID="-1";
	/*
	 * 使用中
	 */
	public static String _SYZ="-1";
	public QjgzService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @描述：获取页面显示
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-22 下午02:36:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kssj
	 * @param jssj
	 * @param detail 是否显示详细信息
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String getShow(String kssj,String jssj,boolean isDetail){
		String show;
	/*	//对相等得时间，设置开始减1
		if(kssj.equals(jssj)){
			Integer newInt=(Integer.parseInt(kssj)-1);
			kssj=newInt.toString();
		}*/
		show= "大于<font color=\"#FF8040\">"+kssj+"</font>天小于等于<font color=\"#FF8040\">"+jssj+"</font>天";
		if(isDetail){
			show+="(<font color='red'>不包括"+kssj+"天</font>)";
		}
		return show;
	}
	private String getShow(String kssj,String jssj){
		return getShow(kssj, jssj,true);
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean save(QjgzForm qf) throws Exception {
		setKssjForJssj(qf);
		return this.runInsert(qf);
	}
	/**
	 * 
	 * @描述:修改请假规则
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-22 上午11:12:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean update(QjgzForm qf) throws Exception {
		setKssjForJssj(qf);
		return this.runUpdate(qf);
	}
	/**
	 * 
	 * @描述:根据结束时间设置开始时间
	 * 		 如果开始时间等于结束时间，开始时间自动减1
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-22 上午11:10:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private void setKssjForJssj(QjgzForm qf){
		if(StringUtils.isNull(qf.getKssj())||StringUtils.isNull(qf.getJssj())){
			logger.error("请假开始时间:"+qf.getKssj()+"请假结束时间:"+qf.getJssj()+"为非法区间!");
		}
		if(qf.getKssj().equals(qf.getJssj())){
			Integer kssj=(Integer.parseInt(qf.getKssj())-1);
			qf.setKssj(kssj.toString());
		}
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取冲突信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:19:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String getClash(QjgzForm model) throws Exception {
		//if(dao.isUse(qf.getQjgzid())){
			//return _SYZ;//代表使用中
		//}
		List<HashMap<String,String>> list=this.getAllList(new QjgzForm());
		for(HashMap<String, String> hm:list){
			//如果是自己本身不进行判断
			if(hm.get("qjgzid").equals(model.getQjgzid())){
				continue;
			}
			String kssj=hm.get("kssj");
			String jssj=hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			String ssxydm = hm.get("ssxydm");
			//如果开始时间和结束时间都想等直接返回
			if(( Float.parseFloat(jssj)==Float.parseFloat(model.getJssj())
					|| Float.parseFloat(kssj)==Float.parseFloat(model.getKssj()) )
				&& ( (StringUtils.isNull(qjlxid) && StringUtils.isNull(model.getQjlxid()))
					|| ( (StringUtils.isNotNull(qjlxid) && StringUtils.isNotNull(model.getQjlxid()))
					&& qjlxid.equals(model.getQjlxid())) ) && ssxydm.equals(model.getSsxydm()) ){
				return getShow(kssj, jssj,false);
			}
			//新增区间起始时间大于其结束时间
			//或
			//行政区间结束时间小于等于其 起始时间
			//则认为此区间与其不冲突 4 4
			//否则则认为冲突
			if((!(Float.parseFloat(jssj)<=Float.parseFloat(model.getKssj())||
				Float.parseFloat(model.getJssj())<=Float.parseFloat(kssj))) &&
					((StringUtils.isNull(qjlxid) && StringUtils.isNull(model.getQjlxid())) ||
							( (StringUtils.isNotNull(qjlxid) && StringUtils.isNotNull(model.getQjlxid()))
									&& qjlxid.equals(model.getQjlxid())
									) ) && ssxydm.equals(model.getSsxydm()) ){
				return getShow(kssj, jssj,false);
			}
		}
		return null;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:31:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * String[] 返回类型   String数组 0为成功删除条数为不能删除的
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception{
		//StringBuffer del=new StringBuffer();
		List<String> delId=new ArrayList<String>();
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			//暂时不再判断是否是否使用，全部可以进行删除
			//if(!dao.isUse(str)){
				delId.add(str);//记录删除id
				//del.append(str);
				//del.append(",");
		/*	}else{
				//获得不能删除的请假区间
				QjgzForm qf=getModel(str);
				noDel.append("("+qf.getKssj());
				noDel.append("~");
				noDel.append(qf.getJssj()+")");
				noDel.append(",");
				isHaveNoId=true;
			}*/
		}
		int i=delId.size()>0?runDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str.substring(0,str.length()-1):_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述: 获取请假类型List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-29 上午11:19:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlxList(){
		return dao.getQjlxList();
	}

	public HashMap<String, String> getInfo(QjgzForm model) throws Exception {
		return dao.getInfo(model);
	}
}
