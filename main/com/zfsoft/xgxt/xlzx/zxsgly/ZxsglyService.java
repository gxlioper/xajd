package com.zfsoft.xgxt.xlzx.zxsgly;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;


public class ZxsglyService extends SuperServiceImpl<ZxsglyForm,ZxsglyDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存管理员表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 下午04:34:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zxsform
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveForm(ZxsglyForm zxsform) throws Exception{
		String[] zghs = zxsform.getZghs();
		List<String[]> zghArr = new ArrayList<String[]>();
		boolean rs = true;
		if(zghs == null || zghs.length == 0){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		for(int i=0;i<zghs.length;i++){
			zghArr.add(new String[]{zghs[i]});
		}
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return dao.saveForm(zghArr);
	}

	/**
	 * @描述:根据职工号判断是否是咨询师管理员
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月8日 下午1:42:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean isZxsGly(String zgh) throws Exception {
		
		return dao.isZxsGly(zgh);
	}
}
