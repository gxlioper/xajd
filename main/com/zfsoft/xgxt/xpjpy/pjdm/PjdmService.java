/**
 * @部门:学工产品事业部
 * @日期：2013-8-16 上午09:08:02 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优_代码维护（项目类型和性质）
 * @作者：CQ [工号：785]
 * @时间： 2013-8-16 上午09:08:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjdmService extends SuperServiceImpl<PjdmModel, PjdmDao>  implements Constants{
	
	private PjdmDao dao = new PjdmDao();
	
	public PjdmService() {
		super.setDao(dao);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:获取下一个项目类型代码
	 * @作者：cq [工号：785]
	 * @日期：2013-8-17 下午02:56:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int getNextXmlxdm() throws SQLException {
		
		int maxXmlxdm=0;
		maxXmlxdm=dao.getMaxXmlxdm()+1;
		return maxXmlxdm;
	}

	/**
	 * 
	 * @描述:判断评奖类型在评奖结果当中是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 下午04:13:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	  public String checkLxForPjjg(String value)throws Exception{
	    	String resultLxmc="";
	    	String[] lxmc=dao.lxCheckExistForPjjg(value);
	    	for(int i=0;i<lxmc.length;i++){
				if(i==lxmc.length-1){
					resultLxmc+=lxmc[i];
				}else{
					resultLxmc+=lxmc[i]+",";
				}
			}
			return resultLxmc;
		}
	  
	  /**
	   * 
	   * @描述:判断评奖性质在评奖结果当中是否存在
	   * @作者：cq [工号：785]
	   * @日期：2013-8-19 下午04:52:06
	   * @修改记录: 修改者名字-修改日期-修改内容
	   * @param value
	   * @return
	   * @throws Exception
	   * String 返回类型 
	   * @throws
	   */
	  public String checkXzForPjjg(String value)throws Exception{
		  String resultXzmc="";
		  String [] xzmc=dao.xzCheckExistForPjjg(value);
		  for(int i=0;i<xzmc.length;i++){
			  if(i==xzmc.length-1){
				  resultXzmc+=xzmc[i];
			  }else{
				  resultXzmc+=xzmc[i]+",";
			  }
		  }
		  return resultXzmc;
	  }
	  

	  /**
	   * 
	   * @描述:判断评奖类型在评奖项目当中是否存在
	   * @作者：cq [工号：785]
	   * @日期：2013-8-19 下午05:11:49
	   * @修改记录: 修改者名字-修改日期-修改内容
	   * @param value
	   * @return
	   * @throws Exception
	   * String 返回类型 
	   * @throws
	   */
	  public String checkLxForPjxm(String value)throws Exception{
	    	String resultLxmc="";
	    	String[] lxmc=dao.lxCheckExistForPjxm(value);
	    	for(int i=0;i<lxmc.length;i++){
				if(i==lxmc.length-1){
					resultLxmc+=lxmc[i];
				}else{
					resultLxmc+=lxmc[i]+",";
				}
			}
			return resultLxmc;
		}
	
	  /**
	   * 
	   * @描述:判断评奖性质在评奖项目当中是否存在
	   * @作者：cq [工号：785]
	   * @日期：2013-8-19 下午05:12:56
	   * @修改记录: 修改者名字-修改日期-修改内容
	   * @param value
	   * @return
	   * @throws Exception
	   * String 返回类型 
	   * @throws
	   */
	  public String checkXzForPjxm(String value)throws Exception{ 
		  String resultXzmc="";
		  String [] xzmc=dao.xzCheckExistForPjxm(value);
		  for(int i=0;i<xzmc.length;i++){
			  if(i==xzmc.length-1){
				  resultXzmc+=xzmc[i];
			  }else{
				  resultXzmc+=xzmc[i]+",";
			  }
		  }
		  return resultXzmc;
	  }

	/** 
	 * @描述:判断项目类型名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-17 下午03:50:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param update
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistByXmlxmc(PjdmModel model, String type) {
		
		boolean flag = false;
		
		if("save".equalsIgnoreCase(type)){
			String num=dao.lxCheckExistForSave(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String num=dao.lxCheckExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}	
		}
	    
    	return  flag;
	}
	
	/**
	 * 
	 * @描述:判断项目性质名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 上午11:37:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByXmXzmc(PjdmModel model, String type){
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)){
			String num = dao.xzCheckExistForSave(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String num = dao.xzCheckExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}
		
		return flag;
	}
	
	
	/**
	 * 
	 * @描述:根据类型代码获得名称
	 * @作者：cq [工号：785]
	 * @日期：2015-5-6 上午09:23:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlxdm
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getXmlxmc(String[] xmlxdm) throws SQLException{
		
		return dao.getXmlxmc(xmlxdm);
	}


}
