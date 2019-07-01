/**
 * @部门:学工产品事业部
 * @日期：2014年6月9日 下午1:53:50 
 */  
package com.zfsoft.xgxt.comm.shlc.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.CsszDao;
import com.zfsoft.xgxt.comm.shlc.model.CsszModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 通用申请审核-参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月9日 下午1:53:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<CsszModel, CsszDao> {

	//默认申请开关
	private static final boolean DEFAULT_SQKG = false;
	//默认审核开关
	private static final boolean DEFAULT_SHKG = false;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	
	
	/**
	 * 
	 * @描述: 判断是否可以申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午3:03:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public  boolean isAllowApply(String id){
		
		try {
			CsszModel model = dao.getModel(id);
			
			//未设置，按默认开关处理
			if (model == null){
				return DEFAULT_SQKG;
			}
			
			//未开放申请
			if (Constants.CLOSE.equals(model.getSqkg())){
				return false;
			}
			
			
			//申请开始时间不为空，结束时间为空
			if (!StringUtil.isNull(model.getSqkssj()) && StringUtil.isNull(model.getSqjssj())){
				Date kssj = sdf.parse(model.getSqkssj());
				return new Date().after(kssj);
			}
			
			//申请结束时间不为空，开始时间为空
			if (!StringUtil.isNull(model.getSqjssj()) && StringUtil.isNull(model.getSqkssj())){
				Date jssj = sdf.parse(model.getSqjssj());
				return new Date().before(jssj);
			}
			
			//比较当前日期是否在开始、结束时间内
			Date kssj = sdf.parse(model.getSqkssj());
			Date jssj = sdf.parse(model.getSqjssj());
			Date dqsj = new Date();
			
			return dqsj.after(kssj) && dqsj.before(jssj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return DEFAULT_SQKG;
	} 
	
	
	/**
	 * 
	 * @描述: 判断是否可以审核
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午3:04:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isAllowAudit(String id){
		
		try {
			CsszModel model = dao.getModel(id);
			
			//未设置，按默认开关处理
			if (model == null){
				return DEFAULT_SHKG;
			}
			
			//未开放审核
			if (Constants.CLOSE.equals(model.getShkg())){
				return false;
			}
			
			//审核开始时间不为空，审核时间为空
			if (!StringUtil.isNull(model.getShkssj()) && StringUtil.isNull(model.getShjssj())){
				Date kssj = sdf.parse(model.getShkssj());
				return new Date().after(kssj);
			}
			
			//审核结束时间不为空，开始时间为空
			if (!StringUtil.isNull(model.getShjssj()) && StringUtil.isNull(model.getShkssj())){
				Date jssj = sdf.parse(model.getShjssj());
				return new Date().before(jssj);
			}
			
			//比较当前日期是否在开始、结束时间内
			Date kssj = sdf.parse(model.getShkssj());
			Date jssj = sdf.parse(model.getShjssj());
			Date dqsj = new Date();
			
			return dqsj.after(kssj) && dqsj.before(jssj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return DEFAULT_SHKG;
	}
}
