/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午02:45:02 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class KqglService extends SuperServiceImpl<KqglForm, KqglDAO> {
	
	private KqglDAO dao = new KqglDAO();
	
	@SuppressWarnings("deprecation")
	public KqglService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:验证考勤结果是否存在
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-8-31 下午04:42:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isKqjgExists(KqglForm model){
		return dao.isKqjgExists(model);
	}
	
	/**
	 * 
	 * @描述:获取List
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-8-31 下午04:45:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tablename
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getList(String tablename){
		return dao.getList(tablename);
	}
}
