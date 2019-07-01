/**
 * @部门:学工产品事业部
 * @日期：2016-7-1 上午09:57:03 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-7-1 上午09:57:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxthjlService  extends SuperServiceImpl<ZxzxthjlForm,ZxzxthjlDao>{
	
	private ZxzxthjlDao rd = new ZxzxthjlDao();
	public ZxzxthjlService() {
		super.setDao(rd);
	}
	/**
	 * @描述: 删除在线咨询谈话记录信息
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-4 上午10:40:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delZxzxthjlId(String[] id) throws Exception {	
			return dao.delZxzxthjlId(id);
		}
	/**
	 * @描述: 增加唯一性判断
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-5 下午07:49:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addCheck(ZxzxthjlForm form) throws Exception {
			String num = dao.addCheck(form);
			return Integer.valueOf(num) > 0;	
		}
	
	public HashMap<String, String> getInfoByZgh(String zgh) {
		return dao.getInfoByZgh(zgh);
	}
	/**
	 * @描述: 获取一般心理问题类别代码和名称
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-5 上午09:53:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYblxwtlbList() {
		return dao.getYblxwtlbList();
	}
	/**
	 * chakan 
	 */
	public HashMap<String, String> getThjlxx(String id) {
		return dao.getThjlxx(id);
	}
	/**
	 * @描述: 谈话记录（用于报表）
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-13 下午06:57:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getThjl(String id) {
		return dao.getThjl(id);
	}
	/**
	 * @描述: 取一般问题类别
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-14 下午05:02:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYbwtlb(String id) {
		return dao.getYbwtlb(id);
	}
	/**
	 * @描述: 取心理障碍和精神疾病
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-14 下午05:03:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZajb(String id) {
		return dao.getZajb(id);
	}
}
