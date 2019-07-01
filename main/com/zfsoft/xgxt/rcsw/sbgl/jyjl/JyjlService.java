/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 设备分类维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:48:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JyjlService extends SuperServiceImpl<JyjlModel, JyjlDao> {

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(JyjlModel t) throws Exception {
		
		String[] fldmArr = t.getFldmArr();
		String[] sbdmArr = t.getSbdmArr();
		String[] jysjArr = t.getJysjArr();
		String[] bzArr = t.getBzArr();
		
		boolean result = true;
		
		for (int i = 1 , j = fldmArr.length ; i < j ; i++){
			t.setFldm(fldmArr[i]);
			t.setSbdm(sbdmArr[i]);
			t.setJysj(jysjArr[i]);
			t.setBz(bzArr[i]);
			result = super.runInsert(t);
		}
		
		return result;
	}

	
	/**
	 * 
	 * @描述: 归还设备
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-12-18 上午09:45:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean ghsb(JyjlModel t,String ids) throws Exception{
		
		String[] idArr = ids.split(",");
		
		if (idArr == null || idArr.length == 0){
			return false;
		}
		
		boolean result = true;
		
		for (String id : idArr){
			t.setId(id);
			result = super.runUpdate(t);
		}
		
		return result;
	}
	
}
