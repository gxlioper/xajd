/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 上午08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



/**'
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办类型管理模块
 * @类功能描述: TODO(学生证补办类型) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-17 上午08:54:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XszbblxwhService extends SuperServiceImpl<XszbblxwhForm, XszbblxwhDao>  {

	private XszbblxwhDao dao = new XszbblxwhDao();
	public static String _BCZSCID="-1";
	
	public XszbblxwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(获取学生证补办类型List)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:55:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getBblxPageList(XszbblxwhForm model) throws Exception{
		return dao.getBblxPageList(model);
	}
	
	/**
	 * 
	 * @描述:TODO(获得最大序号的学生证补办类型代码)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String changeXszbblxdm() {
		String max = dao.getMaxXszbblxdm();
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
	 * 
	 * @描述:TODO(增加，修改学生证补办类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:57:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBblxInfo(XszbblxwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String xszbblxdm = changeXszbblxdm();
			model.setXszbblxdm(xszbblxdm);
			return dao.addBblxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateBblxInfo(model);
		}
		return result;
	}
	/**
	 * 
	 * @描述:TODO(修改学生证补办类型 单个查询)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午10:58:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param xszbblxdm
	 * @return
	 * @throws Exception
	 * XszbblxwhForm 返回类型 
	 * @throws
	 */
	public XszbblxwhForm getXszbblxwhForm(XszbblxwhForm t ,String xszbblxdm) throws Exception{
		return dao.getXszbblxwhForm(t,xszbblxdm);
	}
	

	
	/**
	 * 
	 * @描述:TODO(判断补办类型名称是否已经被补办申请 和 补办结果应用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 上午11:22:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String  checkXszbbdmForbbsq(String values)throws Exception{
    	String resultBblxmc="";
    	String[] rcswBblxmc=dao.checkXszbbdmForbbsq(values);
    	for(int i=0;i<rcswBblxmc.length;i++){
			if(i==rcswBblxmc.length-1){
				resultBblxmc+=rcswBblxmc[i];
			}else{
				resultBblxmc+=rcswBblxmc[i]+",";
			}
			
		}
		return resultBblxmc;
	}
	
	/**
	 * 
	 * @描述:TODO(删除学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:11:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteXszbblxwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xszbblxmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bbsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @描述:TODO(删除学生证补办类型维护)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 下午02:04:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int bbsqDelete(String[] xszbblxdm) throws Exception {
		
		return runDelete(xszbblxdm);
	}

	
}
