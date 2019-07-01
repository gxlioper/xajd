/**
 * @部门:学工产品事业部
 * @日期：2013-8-20 上午09:33:39 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 特殊学生代码维护
 * @作者：CQ [工号：785]
 * @时间： 2013-8-20 上午09:33:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsDmService extends SuperServiceImpl<TsxsDmForm, TsxsDmDao>  {
	
	private TsxsDmDao dao = new TsxsDmDao();
	
	public TsxsDmService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取下一个档次代码
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int getNextTsxsDm() throws Exception{
		int maxTsxsDm=0;
		maxTsxsDm=dao.getMaxTsxsDm()+1;
		return maxTsxsDm;
		
	}
	
	
	/**
	 * 
	 * @描述:查询类型名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 下午01:34:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByTsxsDm(TsxsDmForm model, String type)throws Exception{
	     boolean flag = false;
		if("save".equalsIgnoreCase(type)){
			String num=dao.checkExistForSave(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String num=dao.checkExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}	
		}
	    
   	return  flag;
   		
   }
	
	/**
	 * 
	 * @描述:特殊学生代码list
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 下午01:36:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxsDmList(){
		return dao.getTsxsDmList();
	}
	
	
	public String  checkDcForTsxsb(String value)throws Exception{
    	String resultTslxmc="";
    	String[] lxmc=dao.checkDcForTsxsb(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultTslxmc+=lxmc[i];
			}else{
				resultTslxmc+=lxmc[i]+",";
			}
			
		}
		return resultTslxmc;
	}

}
