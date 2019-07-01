package com.zfsoft.xgxt.pjpy.hjsq.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class HjjgService extends SuperServiceImpl<HjjgForm, HjjgDao> {
	private static final String MESSAGE_REPEAT = "同一学年学期奖项名称不得重复！";
	public boolean save(HjjgForm zhcpForm) throws Exception{
		boolean rs = true;
		if(dao.checkExistForSave(zhcpForm)){
			JSONObject json = new JSONObject();
			json.put("message", MESSAGE_REPEAT);
			throw new SystemException(json);
		}
		if(StringUtils.isNotNull(zhcpForm.getId())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setId(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 获取获奖信息List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-22 下午02:12:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjxxPageList(HjjgForm t, User user)
	throws Exception {
		return  dao.getHjxxPageList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除获奖信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-22 下午05:16:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public boolean  delHjxx(String[] ids) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < ids.length; i++) {
			params.add(new String[]{ids[i]});
		}
		return dao.delHjxx(params);
	}
}
