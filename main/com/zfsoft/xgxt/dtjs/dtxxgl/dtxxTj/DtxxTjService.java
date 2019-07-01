/**
 * @部门:学工产品事业部
 * @日期：2016-3-30 上午10:16:13 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设
 * @类功能描述: 统计查询功能  
 * @作者： 沈晓波[工号：1123]
 * @时间： 2016-3-30 上午10:16:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DtxxTjService extends SuperServiceImpl<DtxxTjForm, DtxxTjDao> {
	
	public DtxxTjService(){
		setDao(new DtxxTjDao());
	}
	
	/**
	 * 
	 * @描述: 学生党团备忘录信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-30 上午10:13:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDtxxList(DtxxTjForm myForm,HttpServletRequest request) throws Exception{
		return dao.getDtxxList(myForm,request);
	}
	
	/**
	 * 
	 * @描述: excel学生发展备忘录打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-31 下午03:33:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nd
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxExcList(String nd) throws Exception{
		return dao.getDtxxExcList(nd);
	}
}
