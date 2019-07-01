/**
 * @部门:学工产品事业部
 * @日期：2013-11-21 上午09:22:54 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.bjdldm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 班级大类
 * @作者：CQ [工号：785]
 * @时间： 2013-11-21 上午09:22:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjdldmService extends SuperServiceImpl<BjdldmForm, BjdldmDao> {
	
	private BjdldmDao dao = new BjdldmDao();
	
	public BjdldmService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:操作唯一性验证
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 上午11:10:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByBjdldm(BjdldmForm model)throws Exception{
		
	     boolean flag = false;
	     
	     String num = dao.checkExistForSave(model);
	     if(!"0".equalsIgnoreCase(num)){
	    	 flag = true;
	     }
	     
	     return  flag;
  		
	}
	
	/**
	 * 
	 * @描述:判断评奖结果是否已经被使用
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 下午03:26:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String  checkLbForBjdl(String value)throws Exception{
    	String resultLbmc="";
    	value=fomartStr(value,",");
    	String[] lbmc=dao.checkLbForBjdl(value);
    	for(int i=0;i<lbmc.length;i++){
			if(i==lbmc.length-1){
				resultLbmc+=lbmc[i];
			}else{
				resultLbmc+=lbmc[i]+",";
			}
			
		}
		return resultLbmc;
	}
	
	
	private String fomartStr(String str,String flag){
		StringBuilder sb=new StringBuilder();
		String[] ids=str.split(flag);
		for(int i=0;i<ids.length;i++){
			sb.append("'");
			sb.append(ids[i]);
			sb.append("'");
			if(i+1!=ids.length){
				sb.append(",");
			}
		}
		return null==sb?null:sb.toString();
	}
}
