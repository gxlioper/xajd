/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:42:04 
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车乘车区间结果管理模块
 * @类功能描述: TODO(火车乘车区间结果) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:34:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjjgService extends SuperServiceImpl<HcccqjjgForm, HcccqjjgDao> {

	private HcccqjjgDao dao = new HcccqjjgDao();
	public static String _BCZSCID="-1";
	
	public HcccqjjgService() {
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
	public boolean isExistByHcccqjjg(HcccqjjgForm model, String type)
	throws Exception {
		boolean flag = false;
		if ("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
			
		}
		return flag;
	}
	
	/**
	 * 审核通过，先删除旧数据
	 */
	public boolean deleteExist(HcccqjjgForm model) throws Exception {
		return dao.deleteExist(model);
	}
	/**
	 * 
	 * @描述:TODO(保存火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午08:46:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveHcccqjjg(HcccqjjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @描述:TODO(修改火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 上午10:44:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHcccqjjg(HcccqjjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @描述:TODO(删除火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:07:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteHcccqjjg(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getHcccqjjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?hcccqjjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:07:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int hcccqjjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}
	

	/**
	 * 
	 * @描述:TODO(按照学号查看火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 下午01:32:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreHcccqjjgList(HcccqjjgForm model)throws Exception {
		return dao.getMoreHcccqjjgList(model);
	}
	

}
