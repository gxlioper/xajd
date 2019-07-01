/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 下午01:46:02 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-费用发放-基础数据维护-发放项目
 * @类功能描述: 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 下午01:46:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffxmService extends SuperServiceImpl<FyffxmForm, FyffxmDao> {

	private FyffxmDao dao = new FyffxmDao();
	
	public FyffxmService(){
		super.setDao(dao);
	}
	

	
	/**
	 * 
	 * @描述:获取下一级项目代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午03:17:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getNextFfxmdm() throws SQLException{
		
		int maxFfxmdm=0;
		maxFfxmdm = dao.getMaxXmdm()+1;
		return maxFfxmdm;
	}
	
	
	 
	 /**
	  * 
	  * @描述:判断发放结果当中是否存在
	  * @作者：cq [工号：785]
	  * @日期：2014-4-2 下午05:35:22
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param value
	  * @return
	  * @throws Exception
	  * String 返回类型 
	  * @throws
	  */
	 public String checkFfxmdmForFfjg(String value) throws Exception{
		 
		 String resultFFxmmc = "";
		 String[] ffxmdm = dao.xmdmCheckExistForFfjg(value);
		 for(int i=0; i<ffxmdm.length; i++){
			 if(i==ffxmdm.length-1){
				 resultFFxmmc+=ffxmdm[i];
			 }else{
				 resultFFxmmc+=ffxmdm[i];
			 }
		 }
		 return resultFFxmmc;
	 }
	 
	 /**
	  * 
	  * @描述:保存判断项目名称是否存在
	  * @作者：cq [工号：785]
	  * @日期：2014-4-2 下午05:41:38
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param form
	  * @param type
	  * @return
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean isExistByFfxmmc(FyffxmForm form){
		 
		 boolean flag = false;
		 
		 String num = dao.xmmcCheckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;
	 }
	
	 
	 /**
	  * 
	  * @描述:获取发放项目list
	  * @作者：cq [工号：785]
	  * @日期：2014-4-10 下午03:29:38
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> 返回类型 
	  * @throws
	  */
	 public List<HashMap<String, String>> getFyffxm() throws Exception {
		return dao.getFyffxm();
	 }
}
