/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:11:27 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

import java.sql.SQLException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-院设奖学金（浙江大学个性化！）
 * @类功能描述: TODO(Service) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:11:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhService extends SuperServiceImpl<DmwhForm, DmwhDao>{
	private DmwhDao dao = new DmwhDao();
	public DmwhService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述:TODO(判断途径名称是否存在)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午02:45:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByZjlymc(DmwhForm form){
		 boolean flag = false;
		 String num = dao.fftjCkeckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 return flag;
	 }
	
	/**
	 * @描述:TODO(获取下一个发放途径代码)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午02:47:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getNextZjlydm() throws SQLException{
		int maxZjlydm=0;
		maxZjlydm = dao.maxZjlydm()+1;
		return maxZjlydm;
	}
	
	/**
	 * @描述:TODO(判断资金来源名称在院设奖学金中是否使用)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午03:55:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkZjlymcForYsjxjjg(String value) throws Exception{
		String resultZjlymc = "";
		 String[] zjlydm = dao.tjcheckZjlymcForYsjxjjg(value);
		 for(int i=0; i<zjlydm.length; i++){
			 if(i==zjlydm.length-1){
				 resultZjlymc+=zjlydm[i];
			 }else{
				 resultZjlymc+=zjlydm[i];
			 }
		 }
		 return resultZjlymc;
	}
}
