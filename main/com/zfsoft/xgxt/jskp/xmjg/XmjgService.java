package com.zfsoft.xgxt.jskp.xmjg;

import java.util.HashMap;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;

public class XmjgService extends SuperServiceImpl<XmjgForm, XmjgDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存立项结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-17 上午11:27:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxjg
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveForLxjg(XmjgForm lxjg) throws Exception{
		lxjg.setXmdl("gdx");
		lxjg.setSjly("0");
		boolean rs = true;
		//项目名称重复性验证
		LxsqForm lxsq = new LxsqForm();
		lxsq.setSqid(lxjg.getXmid());
		lxsq.setLxsj(lxsq.getLxsj());
		lxsq.setXmmc(lxjg.getXmmc());
		if(StringUtils.isNull(lxjg.getXmid())){
			if(!new LxsqService().checkIsNotRepeat(lxsq)){
				throw new SystemException(MessageKey.XG_ZJDX_JSKP_REPEAT);
			}
			rs = dao.runInsert(lxjg);
		}else{
			rs = dao.runUpdate(lxjg);
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 验证项目是否已被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-24 上午08:55:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXmIsNotUserd(String[] xmids){
		return dao.checkXmIsNotUserd(xmids);
	}

	public HashMap<String,String> getXmxx(String xmid){
		return dao.getXmxx(xmid);
	}
}
