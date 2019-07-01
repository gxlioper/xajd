/**
 * @部门:学工产品事业部
 * @日期：2014-8-21 下午05:00:41 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-21 下午05:00:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService  extends SuperServiceImpl<CsszForm, CsszDao> {

	/**
	 * 
	 * @描述: 获取参数配置信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-21 下午05:06:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String , String> getConfigMap(){
		Map<String , String> config = dao.getConfigMap();
		return config == null ? new HashMap<String, String>() : config;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存参数设置
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 上午10:34:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCssz(CsszForm model) throws Exception{
		//先删除
		//再添加
		dao.runDelete(null);
		return dao.runInsert(model);
	}
	
	/**
	 * 获取发布资格列表
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-22 下午01:50:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFbzgList(){
		return dao.getFbzgList();
	}
	
	/**
	 * 
	 * @描述:获取开关列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-22 下午01:23:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getKgOptions(){
		/**
		 *  固定选项工具类
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> kgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return kgList;
	}
	
}
