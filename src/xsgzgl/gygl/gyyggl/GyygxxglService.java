/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:46:57 
 */  
package xsgzgl.gygl.gyyggl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午04:46:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygxxglService extends SuperServiceImpl<GyygxxglForm, GyygxxglDao>{
	private GyygxxglDao dao = new GyygxxglDao();

	public GyygxxglService() {
		super.setDao(dao);
	}
	
	public HashMap<String, String> getYgxxmap (String ygbh){
		return dao.getYgxxmap(ygbh);
	}
	
	public String checkYgbh(String ygbh){
		return dao.checkYgbh(ygbh);
	}
	
	public List<HashMap<String, String>> getZwdmList()
	throws Exception {
		return dao.getZwdmList();
	}
	
}
