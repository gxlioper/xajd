/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:42:04 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjsh.QjshForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办结果管理模块
 * @类功能描述: TODO(学生证补办结果) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-25 下午02:05:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbjgService extends SuperServiceImpl<XszbbjgForm, XszbbjgDao> {

	private XszbbjgDao dao = new XszbbjgDao();
	public static String _BCZSCID="-1";
	
	public XszbbjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(判断学生证补办结果表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午08:39:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean isExistByXszbbsqjg(XszbbjgForm model, String type)
	throws Exception {
		boolean flag = false;
		if ("save".equalsIgnoreCase(type)) {
			String shzt = dao.checkExistForSave(model);
			if (!"0".equalsIgnoreCase(shzt)) {
				flag = true;
			}
		} 
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(保存学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午08:46:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXszbbsqjg(XszbbjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @描述:TODO(更改学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午09:33:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXszbbsqjg(XszbbjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @描述:TODO(删除学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:07:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteXszbbjg(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getBbjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bbjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:07:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int bbjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}
	/**
	 * 
	 * @描述:TODO(查看学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:46:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> viewOneXszbbjgList(String xszbbjgId) throws Exception {
		return dao.viewOneXszbbjgList(xszbbjgId);
	}


}
