/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 下午06:52:32 
 */
package com.zfsoft.xgxt.axcs.wpjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 下午06:52:32
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpjgService extends SuperServiceImpl<WpjgForm, WpjgDao> {
	
	private WpjgDao dao = new WpjgDao();
	public static String _BCZSCID="-1";
	
	/**
	 * 
	 * 根据学年、学号、项目代码删除结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午07:21:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean delWpjgxx(String xh, String xn, String xmdm) throws Exception {
		return dao.delWpjgxx(xh, xn, xmdm);
	}
	
	/**
	 * 
	 * @描述:判断该学年结果表中是否已经存在该学生
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:45:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistBysqjg(WpjgForm model)
	throws Exception {
		boolean flag = false;
		String shzt = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(shzt)) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @描述:保存结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:45:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSqjg(WpjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @描述:申请结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:46:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSqjg(WpjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:46:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteSqjg(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getWpjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?sqjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:50:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int sqjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}


	/**
	 * 
	 * @描述:查看结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:51:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbbjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> viewOneWpjgList(String jgId) throws Exception {
		return dao.viewOneWpjgList(jgId);
	}
	
	
	/**
	 * 
	 * @描述:根据学号、学年、学期
	 * @作者：cq [工号：785]
	 * @日期：2014-12-4 上午11:21:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delForXhxnxq(String xh, String xn, String xmdm) throws Exception{
		
		return dao.delForXhxnxmdm(xh,xn,xmdm);
		
	}
	
	
}
