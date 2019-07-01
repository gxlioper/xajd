/**
 * @部门:学工产品事业部
 * @日期：2013-8-21 上午10:47:15 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-21 上午10:47:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxzdmService extends SuperServiceImpl<PjxzdmForm, PjxzdmDao>{
	
	private PjxzdmDao dao = new PjxzdmDao();
	
	public PjxzdmService() {
		super.setDao(dao);
	}
	
	/**
	 * 获取下一个项目性质代码
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int getNextXmxzdm() throws Exception{
		int maxXmxzdm=0;
		maxXmxzdm=dao.getMaxXmxzdm()+1;
		return maxXmxzdm;
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
	public boolean isExistByXmxzdm(PjxzdmForm model, String type)throws Exception{
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
	 * @描述:项目性质代码List
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 下午01:36:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxzdmList(){
		return dao.getXmxzdmList();
	}
	
	
	/**
	 * 
	 * @描述:判断性质在评奖结果是否已使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午11:36:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String  checkXzForPjjg(String value)throws Exception{
    	String resultXmxzmc="";
    	String[] xzmc=dao.checkXzForPjjg(value);
    	for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXmxzmc+=xzmc[i];
			}else{
				resultXmxzmc+=xzmc[i]+",";
			}
			
		}
		return resultXmxzmc;
	}
	
	
	/**
	 * 
	 * @描述:判断性质在评奖项目当中是否已使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午11:48:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkXzForPjxm(String value)throws Exception{
		String resultXmxzmc="";
		String[] xzmc=dao.checkXzForPjxm(value);
		for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXmxzmc+=xzmc[i];
			}else{
				resultXmxzmc+=xzmc[i]+",";
			}
		}
		return resultXmxzmc;
	}


}
