/**
 * @部门:学工产品事业部
 * @日期：2016-10-28 上午11:17:42 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务_考情管理管理模块
 * @类功能描述: 考情审核dao
 * @作者： cq [工号:785]
 * @时间： 2016-10-28 上午11:17:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqshService extends SuperServiceImpl<KqshForm, KqshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 
	 * @描述:审核保存
	 * @作者：cq [工号：785]
	 * @日期：2016-10-28 下午04:19:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(KqshForm form, User user) {
		
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getId());
		model.setSqrid(user.getUserName());
		model.setTzlj("rcsw_zjsy_kqsh.do");
		model.setTzljsq("rcsw_zjsy_kqwh.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			KqshForm kqshForm = new KqshForm();
			kqshForm.setId(form.getId());
			kqshForm.setShzt(zhzt);
			result = dao.runUpdate(kqshForm, form.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 
	 * @描述:考情审核撤销
	 * @作者：cq [工号：785]
	 * @日期：2016-10-29 上午10:01:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public String kqshcx(KqshForm model, User user) throws Exception {
		
		ShlcInterface service = new CommShlcImpl();
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(Constants.YW_SHZ);
		runUpdate(model);
		return cancelFlag;
	}

	/** 
	 * @描述:考情批量审核
	 * @作者：cq [工号：785]
	 * @日期：2016-10-29 上午10:56:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(String values, KqshForm model, User user) throws Exception {
		
		List<String> failIds = new ArrayList<String>();
		
		List<HashMap<String, String>> checkShs = checkShs(values,model,user);
		
		for (int i = 0, n = checkShs.size(); i < n; i++) {
			
			model.setSplc(checkShs.get(i).get("splc"));
			model.setId(checkShs.get(i).get("id"));
			model.setGwid(checkShs.get(i).get("gwid"));
			
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failIds.add(checkShs.get(i).get("id"));
			}
		}
		JSONArray json = JSONArray.fromObject(failIds);
		if (failIds.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(model.getShzt())) {
			return MessageUtil.getText(MessageKey.RCSW_KQGL_KQSH_BZCK, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.RCSW_KQGL_KQSH_PLSH, json.toString());
		}
	}

	/** 
	 * @描述:查询有多少数量可以被审核
	 * @作者：cq [工号：785]
	 * @日期：2016-10-29 上午11:00:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param kqshForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> checkShs(String values,KqshForm kqshForm, User user) throws Exception {
		return dao.checkShs(values, kqshForm, user);
	}

}
