package com.zfsoft.xgxt.rcsw.gjgl.qjsdxz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

public class QjsdxzService extends SuperServiceImpl<QjsdxzForm,QjsdxzDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存请假时段限制
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-11 下午03:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveQjsdxz(QjsdxzForm form) throws Exception{
		//先删除请假时段限制
		dao.deleteRow();
		boolean rs = dao.runInsertNotCommit(form);
		return rs;
	}
}
