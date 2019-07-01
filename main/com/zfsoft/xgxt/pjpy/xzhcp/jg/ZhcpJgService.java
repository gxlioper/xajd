package com.zfsoft.xgxt.pjpy.xzhcp.jg;

import net.sf.json.JSONObject;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class ZhcpJgService extends SuperServiceImpl<ZhcpJgForm,ZhcpJgDao> {
	private static final String MESSAGE_REPEAT = "本学年学期已有记录，请勿重复填写！";
	/**
	 * 
	 * @描述: 保存综合测评
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-7 下午02:54:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zhcpForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(ZhcpJgForm zhcpForm) throws Exception{
		boolean rs = true;
		if(StringUtils.isNotNull(zhcpForm.getSqid())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setSjly("0");
			if(!dao.checkNotRepeat(zhcpForm)){
				JSONObject json = new JSONObject();
				json.put("message", MESSAGE_REPEAT);
				throw new SystemException(json);
			}
			zhcpForm.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		return rs;
	}
}
