/**
 * @部门:学工产品事业部
 * @日期：2016-3-18 下午02:16:06 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.jg;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-18 下午02:16:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsqkjgService extends SuperServiceImpl<TsqkjgForm, TsqkjgDao>{
	private TsqkjgDao dao = new TsqkjgDao();
	
	/** 
	 * @描述:判断结果表中是否有数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-18 下午02:24:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecordForjg(TsqkjgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	/** 
	 * @描述:删除结果表中的数据(用于申请通过)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-18 下午02:24:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteForSq(TsqkjgForm form) throws Exception{
		return dao.deleteForSq(form);
	}
	
	
	/** 
	 * @描述:根据来源流程id删除结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-21 下午01:39:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lclyywid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delByLclyywid(String lclyywid) throws Exception{
		return dao.delByLclyywid(lclyywid);
	}
	
	/** 
	 * @描述:保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午09:50:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveTsqkjg(TsqkjgForm model) throws Exception {
		boolean result = false;
		if ("save".equals(model.getType())) {
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	/** 
	 * @描述:获取结果信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午09:50:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getTbjgxx(TsqkjgForm form){
		return dao.getTbjgxx(form);
	}
}
