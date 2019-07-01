/**
 * @部门:学工产品事业部
 * @日期：2013-12-4 下午03:11:43 
 */
package com.zfsoft.xgxt.base.export.business;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 导入业务扩展
 * @类功能描述: 业务扩展接口
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-4 下午03:11:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface IBaseBusiness {
	/**
	 * 
	 * @描述: 业务操作excel返回所需业务数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 上午11:20:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelObject
	 * @return List<List<ImportModel>> 返回类型
	 */
	public List<List<ImportModel>> businessExcute(
			HashMap<String, Object> excelObject);

	/**
	 * 
	 * @描述:业务扩展导入数据插入
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-9 上午10:15:38
	 * @param model 导入参数（用于设置对应表信息）
	 * @param importColumnList 实际导入表的具体列信息
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 */
	public List<ImportModel> businessInsertData(ImportModel model,
			List<ImportModel> importColumnList);
	
}
