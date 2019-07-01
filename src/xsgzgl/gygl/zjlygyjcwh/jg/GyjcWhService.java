/**
 * @部门:学工产品事业部
 * @日期：2016-4-28 下午04:26:35 
 */  
package xsgzgl.gygl.zjlygyjcwh.jg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-28 下午04:26:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyjcWhService extends SuperServiceImpl<GyjcWhForm, GyjcWhDao> {
	public List<HashMap<String, String>> getJclbList(){
		return dao.getJclbList();
	}
}
