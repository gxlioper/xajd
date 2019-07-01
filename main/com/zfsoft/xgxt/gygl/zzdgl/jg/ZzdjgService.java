/**
 * @部门:学工产品事业部
 * @日期：2016-3-1 下午01:27:02 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.gygl.zzdgl.sh.ZzdshForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-1 下午01:27:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdjgService extends SuperServiceImpl<ZzdjgForm, ZzdjgDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ZzdjgDao dao = new ZzdjgDao();
	/** 
	 * @描述:判断是否有记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 下午01:30:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecordForjg(ZzdjgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-2 上午10:08:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveZzdjg(ZzdjgForm form) throws Exception{
		boolean result = false;
		if ("save".equals(form.getType())) {
			result = dao.runInsert(form);
		} else {
			result = dao.runUpdate(form);
		}
		return result;
	}
	
	/** 
	 * @描述:得到公寓管理员确认列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 上午09:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getPageListForQr(ZzdjgForm t, User user) throws Exception {
		return dao.getPageListForQr(t, user);
	}
	
	/** 
	 * @描述:单个确认
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 下午02:55:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean qxqr(ZzdjgForm t,User user) throws Exception{
		t.setQrzt("");
		t.setQryj("");
		t.setCzy(user.getUserName());
		t.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		return dao.runUpdate(t);
	}
	
	/** 
	 * @描述:批量确认
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 下午02:51:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlqr(ZzdjgForm t, User user) throws Exception{
		String[] ids = t.getQrids();
		if("yes".equalsIgnoreCase(t.getQrzt())){
			t.setQrzt("1");
		}else {
			t.setQrzt("2");
		}
		ZzdjgForm model = new ZzdjgForm();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			ZzdjgForm form = dao.getModel(ids[i]);			
			BeanUtils.copyProperties(model, StringUtils.formatData(form));
			model.setQrzt(t.getQrzt());
			model.setQryj(t.getQryj());
			boolean isSuccess = dao.runUpdate(model);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL, json.toString());
		}
	}
	
	
	
	
}
