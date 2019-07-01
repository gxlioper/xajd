/**
 * @部门:学工产品事业部
 * @日期：2018-5-10 下午03:36:06 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-5-10 下午03:36:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WmqsxsmdService  extends SuperServiceImpl<WmqsxsmdForm,WmqsxsmdDao> {

	private WmqsxsmdDao dao= new WmqsxsmdDao();
	
	public WmqsxsmdService(){
		super.setDao(dao);
	 }
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-10 下午05:06:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getWmqsxsmdById(WmqsxsmdForm myForm) {
		
		return  dao.getWmqsxsmdById(myForm);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-11 上午10:24:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String countXs(WmqsxsmdForm model) {
		
		return dao.countXs(model);
	}

}
