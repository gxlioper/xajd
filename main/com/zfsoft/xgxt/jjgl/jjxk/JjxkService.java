/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 下午02:43:59 
 */  
package com.zfsoft.xgxt.jjgl.jjxk;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.SfbzService;

import java.util.HashMap;
import java.util.List;

/** 
 * @类功能描述: 家教学科
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 下午02:43:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjxkService extends SuperServiceImpl<JjxkForm, JjxkDao> {

	/**
	 * @throws Exception 
	 * 
	 * @描述:保存操作
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午05:03:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean add(JjxkForm model) throws Exception{
		//生成主键
		String dm = dao.getPrimayKey();
		//保存
		model.setJjxkdm(dm);
		
		return dao.runInsert(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除操作
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午05:03:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delete(String delIds) throws Exception{
		if(StringUtils.isBlank(delIds)){
			return false;
		}
		String deleteId = delIds.split(",")[0];
		//check
		int rs = Integer.valueOf(dao.checkReference(deleteId));
		if(rs > 0){
			return false;
		}
		
		dao.runDelete(delIds.split(","));
		
		new SfbzService().deleteSfbzByJJxkdm(deleteId);
		
		return true;
	}

	public List<HashMap<String,String>> getJjxkList() {
		return dao.getJjxkList();
	}
}
