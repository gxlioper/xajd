/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:29:30 
 */  
package  xsgzgl.qgzx.kycxgl.cssz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间：2015-11-30 下午04:29:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KyxmCsszService extends SuperServiceImpl<KyxmCsszForm, KyxmCsszDao> {
	

	
	public boolean deleteJcsz(KyxmCsszForm model) throws Exception{
		return dao.deleteJcsz(model);
	}
	
	public HashMap<String,String> getCssz(String xmlb) throws Exception{
		return dao.getCssz(xmlb);
	}

}
