/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:42:04 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

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
public class YlbxjgService extends SuperServiceImpl<YlbxjgForm, YlbxjgDao> {

	private YlbxjgDao dao = new YlbxjgDao();
	public static String _BCZSCID="-1";
	
	public YlbxjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(判断医疗保险结果表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午08:39:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean isExistByYlbxjg(YlbxjgForm model, String type)
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
	 * @描述:TODO(保存医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午04:28:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveYlbxjg(YlbxjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @描述:TODO(更改医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午04:28:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxjg(YlbxjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @描述:TODO(删除医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 上午11:33:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteYlbxjg(String[] ids) throws Exception{
		
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
				HashMap<String, String> hm=dao.getYljg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		
		int i=delId.size()>0?ylbxjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 上午11:34:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int ylbxjgDelete(String[] ids) throws Exception {
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
	public Map<String, String> viewOneYlbxjgList(String xszbbjgId) throws Exception {
		return dao.viewOneYlbxjgList(xszbbjgId);
	}
	
	/**
	 * 
	 * @描述:TODO(当前学期名称)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 下午03:17:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxjgForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
	

}
