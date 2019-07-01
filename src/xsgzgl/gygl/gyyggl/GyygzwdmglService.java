/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:00:29 
 */  
package xsgzgl.gygl.gyyggl;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;




/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午04:00:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygzwdmglService  extends SuperServiceImpl<GyygzwdmglForm, GyygzwdmglDao> {
	private GyygzwdmglDao dao=new GyygzwdmglDao();
	
	public GyygzwdmglService() {
		super.setDao(dao);
	}
	
	public boolean isExist(GyygzwdmglForm model) {
		// TODO 自动生成方法存根
		return dao.isExist(model);
	}

	public boolean checkDel(String values) {
		
		return dao.checkDel(values);
	}

	
}
