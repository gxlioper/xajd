/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 上午10:25:55 
 */  
package xsgzgl.gyjc.jcsd;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 上午10:25:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxxmService extends SuperServiceImpl<CxxmForm, CxxmDao> {
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保护评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午06:04:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCxxm(CxxmForm t) throws Exception{
		//先判断评分标准是否重复
		boolean rs = dao.checkRepeatDM(t);//代码是否重复
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_REPEAT);
		}
		rs = dao.checkRepeatMC(t);//名称是否重复
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_XH_REPEAT);
		}
		
			return dao.runInsert(t);
		}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月22日 下午4:46:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dms
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delRs(String[] dms) {
		// TODO 自动生成方法存根
		//判断是否还在使用
		return false;
	}
	

}
