/**
 * @部门:学工产品事业部
 * @日期：2015-6-1 上午09:02:48 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @类功能描述:检查日程
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-1 上午09:02:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcrcService extends SuperServiceImpl<JcrcModel, JcrcDao> {

	private static final String TJZT_WTJ = "0";
	private static final String TJZT_YTJ = "1";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(JcrcModel t) throws Exception {
		
		String id = UniqID.getInstance().getUniqIDHash();
		
		t.setId(id);
		
		dao.saveRcxm(id, t.getXmdm());
		return super.runInsert(t);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(JcrcModel t) throws Exception {
		dao.delRcxm(t.getId());
		dao.saveRcxm(t.getId(), t.getXmdm());
		return super.runUpdate(t);
	}
	
	
	/**查询日程项目***/
	public String[] getRcxmArr(String rcid) throws SQLException{
		return dao.getRcxmArr(rcid);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		dao.delRcxm(values);
		return super.runDelete(values);
	}

	
	/**提交日程**/
	public boolean runSubmit(String[] values) throws Exception{
		return dao.updateTjzt(TJZT_YTJ, values);
	}
	
	
	/**取消提交日程**/
	public boolean runCancel(String[] values) throws Exception{
		return dao.updateTjzt(TJZT_WTJ, values);
	}
	
	/**提交同步更新到XG_QSFMX表（上海戏剧个性化）**/
	public boolean saveSubmit(String[] values) throws Exception{
		return dao.saveWsf(values);
	}
	
	/**取消提交同步删除对应数据XG_QSFMX表（上海戏剧个性化）**/
	public boolean delCancel(String[] values) throws Exception{
		return dao.delWsf(values);
	}
	
}
