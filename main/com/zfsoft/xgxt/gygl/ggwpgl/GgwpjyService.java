/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 下午01:58:53 
 */  
package com.zfsoft.xgxt.gygl.ggwpgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.sbgl.jyjl.JyjlModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-19 下午01:58:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GgwpjyService extends SuperServiceImpl<GgwpjyForm, GgwpjyDao>{
	private GgwpjyDao dao = new GgwpjyDao();
	
	@Override
	public boolean runInsert(GgwpjyForm t) throws Exception {
		
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
	 * @描述:是否归还
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-20 上午10:21:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean sfgh(GgwpjyForm t){
		boolean result = true;
		if("update".equalsIgnoreCase(t.getType())){
			result = dao.sfgh(t);
		}else{
			String[] sbdmArr = t.getSbdmArr();			
			for(int i = 1;i<sbdmArr.length;i++){
				t.setSbdm(sbdmArr[i]);
				result = dao.sfgh(t);
				if(!result){
					break;
				}
			}
		}
		return result;
	}
	
	/** 
	 * @描述:归还
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-20 下午02:00:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean ghsb(GgwpjyForm t,String ids) throws Exception{
		
		String[] idArr = ids.split(",");
		
		if (idArr == null || idArr.length == 0){
			return false;
		}
		
		boolean result = true;
		
		for (String id : idArr){
			t.setId(id);
			t.setGhzt("1");
			result = super.runUpdate(t);
		}
		
		return result;
	}
}
