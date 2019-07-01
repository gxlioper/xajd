/**
 * @部门:学工产品事业部
 * @日期：2014-4-24 上午11:03:37 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-咨询师管理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-24 上午11:03:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxsxxService extends SuperServiceImpl<ZxsxxForm, ZxsxxDao>{

	public ZxsxxService() {
		super.setDao(new ZxsxxDao());
	}
	
	/** 
	 * @描述:根据职工号查询教师信息
	 * @作者：王志刚 [工号：1060]
	 * @日期：2014-4-24 下午02:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String zgh){
		
		return dao.getFdyInfo(zgh);
	}
	
	/** 
	 * @描述:(根据职工号查询咨询师信息是否存在)
	 * @作者：王志刚 [工号：1060]
	 * @日期：2014-4-24 下午03:35:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * boolean 返回类型 
	 */
	public boolean zxsxxIsExist(String zgh) {
		return dao.zxsxxIsExist(zgh);
	}
	
	/**
	 * 
	 * @描述:查出所有咨询师   
	 *       数据 例：徐木兴 [男][高教发展研究中心][20020964] [上限10人][已预约5人]
	 * @作者：王志刚
	 * @日期：2014-4-30 下午04:36:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		return dao.getZxsxxAllList();
	}
	
	/**
	 * 
	 * @描述:查出所有咨询师  (根据预约时间) 
	 *       数据 例：徐木兴 [男][高教发展研究中心][20020964] [上限10人][已预约5人]
	 * @作者：王志刚
	 * @日期：2014-4-30 下午04:36:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		return dao.getZxsxxAllByYysjList(yysj);
	}
	
	/**
	 * 
	 * @描述:设置咨询师在岗状态
	 * @作者：王志刚
	 * @日期：2014-4-25 上午09:14:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh 职工号
	 * @param status 在岗状态
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxsxxStatus(String zgh, String status) throws Exception{
		return dao.setZxsxxStatus(zgh, status);
	}
	
	/**
	 * 
	 * @描述:设置咨询预约说明
	 * @作者：王志刚
	 * @日期：2014-4-25 上午09:43:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zxyysm 咨询预约说明
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxyysm(String zxyysm) throws Exception{
		return dao.setZxyysm(zxyysm);
	}
	
	/**
	 * 
	 * @描述:查询咨询预约说明
	 * @作者：王志刚
	 * @日期：2014-4-25 上午10:14:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param 
	 * @return
	 * boolean 返回类型 
	 */
	public String getZxyysm() throws Exception{
		return dao.getZxyysm();
	}
}
