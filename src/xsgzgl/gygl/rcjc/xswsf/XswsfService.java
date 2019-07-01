/**
 * @部门:学工产品事业部
 * @日期：2018-5-8 下午02:18:28 
 */  
package xsgzgl.gygl.rcjc.xswsf;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-卫生检查-学生卫生分（苏州卫生职业技术学院）
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-5-8 下午02:18:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XswsfService extends SuperServiceImpl<XswsfForm,XswsfDao> {
	private XswsfDao dao= new XswsfDao();
	
	public XswsfService(){
		super.setDao(dao);
	 }

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-9 上午08:55:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * XswsfForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXswsfById(XswsfForm myForm) {
		return dao.getXswsfById(myForm);
	}


	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-9 下午04:40:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRcXnList() {
		
		return dao.getRcXnList();
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-10 上午11:42:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWmqsxsMd(String xn, String xq) {
		
		return dao.getWmqsxsMd(xn,xq);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-10 下午01:41:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsmcList
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertXsmc(List<HashMap<String, String>> xsmcList, User user) throws Exception {
		return dao.insertXsmc(xsmcList, user);
	}
	
	
}
