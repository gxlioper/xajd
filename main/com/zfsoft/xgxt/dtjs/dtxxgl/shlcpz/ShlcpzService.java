/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @类功能描述:请假规则service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcpzService extends SuperServiceImpl<ShlcpzForm, ShlcpzDao> {
	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID="-1";
	/**
	 * 是否可申请开关 默认值 不可申请
	 */
	public static String _SFKSQ_BKSQ="0";
	ShlcpzDao dao = new ShlcpzDao();

	public ShlcpzService() {
		this.setDao(dao);
	}
	public boolean save(ShlcpzForm sf) throws Exception {
		return update(sf);
	}

	public boolean update(ShlcpzForm sf) throws Exception {
		return this.runUpdate(sf);
	}
	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 上午11:33:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 */
	
	public String[] delete(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		int i=0;
		for(String str:ids){
				delId.add(str);//记录删除id
				//去掉对应审批流程
				ShlcpzForm zf=getModel(str);
				zf.setSplc("");
				zf.setKsqkg(_SFKSQ_BKSQ);
				zf.setKsqkssj("");
				zf.setKsqjssj("");
				if(runUpdate(zf)){i++;}
		}
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str.substring(0,str.length()-1):_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @描述:获得可以增加阶段代码
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-22 下午05:12:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getJdList() throws Exception {
		return dao.getJdList();
	}
	/**
	 * 
	 * @描述:获得可以修改的阶段代码
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 上午09:30:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jddm 当前阶段代码
	 * @param jdmc 当前阶段名称
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getJdList(String jddm,String jdmc) throws Exception {
		List<HashMap<String, String>> updateList=dao.getJdList();
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("jddm", jddm);
		hm.put("jdmc", jdmc);
		updateList.add(hm);
		return updateList;
	}
	/**
	 * 
	 * @描述:是否有对应审批流程
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 下午02:29:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jddm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 */
	public boolean isHaveSPlc(String jddm) throws Exception{
		ShlcpzForm sf=getModel(jddm);
		if(null!=sf&&StringUtils.isNotNull(sf.getSplc())){
			return true;
		}
		return false;
	}
}
