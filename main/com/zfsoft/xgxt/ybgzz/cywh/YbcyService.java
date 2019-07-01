/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.ybgzz.cywh;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @类功能描述: 易班成员维护 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-30 下午01:56:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbcyService extends SuperServiceImpl<YbcyModel, YbcyDao> {

	
	/**批量退出 **/
	public boolean batchExit(String[] idArr,YbcyModel model){
		
		try {
			return dao.batchExit(idArr, model);
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	public YbcyModel getExitInfo(String id) throws Exception{
		
		return dao.getExitInfo(id);
	}
	
	/**按学号查询是否存在易班工作站*/
	public boolean isExists(String xh) throws Exception{
		
		String count = dao.getExistsByXh(xh);
		
		return Integer.valueOf(count) > 0;
	}
	
	/**按学号查询是否已申请易班工作站成员*/
	public boolean isSqExists(String xh) throws Exception{
		
		String count = dao.getSqExistsByXh(xh);
		
		return Integer.valueOf(count) > 0;
	}
	
}
