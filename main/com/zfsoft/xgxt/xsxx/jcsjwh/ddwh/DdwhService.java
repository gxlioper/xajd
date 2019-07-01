/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午09:24:34 
 */  
package com.zfsoft.xgxt.xsxx.jcsjwh.ddwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息-大队维护
 * @类功能描述: 浙江警察学院个性化大队维护功能 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-12 上午08:59:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DdwhService extends SuperServiceImpl<DdwhForm, DdwhDAO> {
   private DdwhDAO dao = new DdwhDAO();
   
	@SuppressWarnings("deprecation")
	public DdwhService() {
		super.setDao(dao);
	}
   
	/**
	 * 
	 * @描述:验证大队代码是否存在
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-12 上午11:28:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHasExists(DdwhForm model){
		return dao.isHasExists(model);
	}
	
	public String runDelete(DdwhForm model) {
		boolean flag =dao.runDelete(model);
		String message= flag ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
		return message ;
	}
	
	public List<HashMap<String,String>> getQdList(DdwhForm model) throws Exception{
		return dao.getQdList(model);
	}
		
	public String saveQd(DdwhForm model) throws Exception{
		String[] qdlist = model.getQdAll();
		String messageKey =  MessageKey.SYS_SAVE_SUCCESS;
		for(int i=0;i<qdlist.length;i++){
				boolean flag = dao.saveQd(model.getDddm(), qdlist[i]);
				if(!flag){
					messageKey =  MessageKey.SYS_SAVE_FAIL;
					break;
				}
		}
		return messageKey;
	}
	
	public String delQd(DdwhForm model) throws Exception{
		String[] qdlist = model.getQdAll();
		String messageKey =  MessageKey.SYS_DEL_SUCCESS;
		for(int i=0;i<qdlist.length;i++){
			boolean flag = dao.delQd(model.getDddm(), qdlist[i]);
			if(!flag){
				messageKey =  MessageKey.SYS_DEL_FAIL;
				break;
			}
		}
		return messageKey;
	}
}

