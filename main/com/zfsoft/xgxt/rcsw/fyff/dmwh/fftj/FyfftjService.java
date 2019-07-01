/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 上午09:41:40 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-费用发放-基础数据维护-发放途径
 * @类功能描述: 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 上午09:41:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyfftjService extends SuperServiceImpl<FyfftjForm, FyfftjDao> {
	
	private FyfftjDao dao = new FyfftjDao();
	
	public FyfftjService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述:获取下一个发放途径代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午03:23:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getNextFftjdm() throws SQLException{
		
		int maxFftjdm=0;
		maxFftjdm = dao.getMaxTjdm()+1;
		return maxFftjdm;
		
	}
	
	/**
	 * 
	 * @描述:判断是发放途径在发放结果当中是否使用
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午09:02:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkFftjForFfjg(String value) throws Exception{
		
		String resultFFtjmc = "";
		 String[] ffxmdm = dao.tjCheckExistForFfjg(value);
		 for(int i=0; i<ffxmdm.length; i++){
			 if(i==ffxmdm.length-1){
				 resultFFtjmc+=ffxmdm[i];
			 }else{
				 resultFFtjmc+=ffxmdm[i];
			 }
		 }
		 return resultFFtjmc;
	}
	
	
	/**
	 * 
	 * @描述:判断途径名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午09:12:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByFftjmc(FyfftjForm form){
		 
		 boolean flag = false;
		 
		 String num = dao.fftjCkeckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;
	 }
	
	/**
	 * 
	 * @描述:获取发放途径list
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFyfftj() throws Exception {
		return dao.getFyfftj();
	}

}
