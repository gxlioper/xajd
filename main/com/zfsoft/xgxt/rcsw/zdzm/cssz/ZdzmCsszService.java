/**
 * @部门:学工产品事业部
 * @日期：2014-3-3 下午03:42:35 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明 SERVICE类 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-3 下午03:42:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmCsszService extends SuperServiceImpl<ZdzmCsszForm, ZdzmCsszDao> {
	
	
	
	/**
	 * @描述 ：初始化DAO
	 */
	public ZdzmCsszService() {
		super();
		super.setDao(new ZdzmCsszDao());
	}

	/**
	 * 
	 * @描述:获取申请开关列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午10:23:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getSqkgList(){
		/**
		 *  固定选项工具类
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> sqkgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return sqkgList;
	}
	
	/**
	 * 
	 * @描述:获取下载开关列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午10:23:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getXzkgList(){
		/**
		 *  固定选项工具类
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return xzkgList;
	}
	
	
	/**
	 * 
	 * @描述:获取下载控制列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午10:23:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getXzkzList(){
		/**
		 *  固定选项工具类
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkzList = optionUtil.getOptions(OptionUtil.RCSW_XZKG);
		
		return xzkzList;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午10:53:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 *  返回类型 ZdzmCsszForm
	 * @throws
	 */
	public ZdzmCsszForm getCssz() throws Exception{
		return dao.getCssz();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午11:48:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCssz(ZdzmCsszForm model) throws Exception{
		return dao.saveCssz(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:查询审核流可修改？
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 下午01:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getShlcxx() throws Exception{
		return dao.getShlcxx();
	}

	/**
	 * 
	 * @描述:关闭审核流操作
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 下午02:38:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean closeCssz(ZdzmCsszForm model) throws Exception{
		return dao.closeCssz(model);
	}
}
