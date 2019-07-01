/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午02:26:08 
 */  
package xsgzgl.szdw.jtff.jtff;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-8 下午02:26:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtffService extends SuperServiceImpl<JtffForm, JtffDao> {
	/**
	 * 获取最近三个月日期
	 */
	public List<HashMap<String, String>> getLastThreeMonth(){
		return dao.getLastThreeMonth();
	}
	
	/**
	 * 
	 * @描述:已生成津贴发放月份津贴数据查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午02:45:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtfflist(String ffny){
		return dao.getJtfflist(ffny);
	}
	
	/**
	 * 
	 * @描述:已生成津贴发放月份津贴数据查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午02:45:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWscJtfflist(String ffny){
		return dao.getWscJtfflist(ffny);
	}
	
	/**
	 *验证是否有数据
	 */
	public boolean isExist(String ffny){
		return dao.isExist(ffny);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:插入前先删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午04:52:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffny
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteRows(String ffny) throws Exception{
		return dao.deleteRows(ffny);
	}
	
	public String calculateZje(List<HashMap<String, String>> obj){
		double zje = (double)0.00;
		for (HashMap<String, String> hashMap : obj) {
			String lsje = hashMap.get("ffje");
			if(StringUtils.isNotNull(lsje)){
				zje += Double.parseDouble(lsje);
			}
		}
		return zje+"";
	}
	
}
