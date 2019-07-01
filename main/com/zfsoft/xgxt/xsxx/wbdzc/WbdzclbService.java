/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:58:38 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
	/**
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 学生信息管理模块
	 * @类功能描述:  
	 * @作者： 孟威[工号:1186]
	 * @时间： 2016-3-16 上午09:38:51 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */

	public class WbdzclbService extends SuperServiceImpl<WbdzclbForm,WbdzclbDao>{
		private WbdzclbDao dao = new WbdzclbDao();
		public static String _BCZSCID="-1";
		
		public WbdzclbService() {
			super.setDao(dao);
		}
		
		
		/**
		 * 获得未报到注册类型list
		 * 孟威【1186】
		 */
		public List<HashMap<String, String>>  getPageList(WbdzclbForm model) throws Exception{
			return dao.getPageList(model);
		}
		
		
		/**
		 * @描述:	获取最大类型代码
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:40:08
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * String 返回类型 
		 * @throws
		 */
		public String changeWbdlbdm() {
			String max = dao.getWbdlbdm();
			if(Base.isNull(max)){
				return "001";
			}else{
				max = String.valueOf((Integer.parseInt(max)+1));
				String pre = "";
				for(int i = 0; i < 3-max.length();i ++){
					pre+="0";
				}
				return pre+max;
			}
		}
		
		
		/**
		 * @描述:	增加和修改类型
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:40:48
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param model
		 * @param type
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
			public boolean saveLxInfo(WbdzclbForm model,String type) throws Exception{
				boolean result = false;
				if ("add".equals(type)) {
					String wbdlbdm = changeWbdlbdm();
					model.setWbdlbdm(wbdlbdm);
					return dao.addLxInfo(model);
				} else if ("update".equals(type)) {
					return dao.updateLxInfo(model);
				}
				return result;
		}
			
			
		/**
		 * 判断是否存在结果应用
		 */
		public String pdsfsy(String value) throws Exception{
			String resultWbdlbmc = "";
			String[] wbdlbdm = dao.pdsfsy(value);
			for(int i=0; i<wbdlbdm.length; i++){
				 if(i==wbdlbdm.length-1){
					 resultWbdlbmc+=wbdlbdm[i];
				 }else{
					 resultWbdlbmc+=wbdlbdm[i];
				 }
			 }
			 return resultWbdlbmc;
		}
		
		
		/**
		 * @描述:	判断未报到类别名称是否存在
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:42:00
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param model
		 * @param type
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
		public boolean isExistByWbdlbdm(WbdzclbForm model, String type)throws Exception{
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
		}
