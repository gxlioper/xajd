/**
 * @部门:学工产品事业部
 * @日期：2017年5月10日 上午8:41:37 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgDao;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgForm;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqDao;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务审核Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月10日 上午8:41:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwShService extends SuperServiceImpl<ZyfwShForm,ZyfwShDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();

	/** 
	 * @描述:保存志愿服务单个审核
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 下午4:53:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwShForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveForDgsh(ZyfwShForm zyfwShForm, User user) {
		
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(zyfwShForm.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(zyfwShForm.getShyj());
		// 审核状态
		model.setShzt(zyfwShForm.getShjg());
		//岗位退回
		model.setThgw(zyfwShForm.getThgw());
		// 审核岗位ID
		model.setGwid(zyfwShForm.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(zyfwShForm.getFwid());
		//申请人id
		model.setSqrid(zyfwShForm.getXh());
		
		model.setTzlj("xsxx_zyfwgl_sh.do?method=zyfwShList");
		model.setTzljsq( "xsxx_zyfwgl_sq.do?method=zyfwSqList");
		
		boolean reuslt = false;
		try {
			String shzt = shlc.runAuditing(model);
			zyfwShForm.setShzt(shzt);
			reuslt = dao.runUpdate(zyfwShForm);
			// 保存到结果表
			if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
				ZyfwJgForm zyfwJgForm = new ZyfwJgForm();
				ZyfwJgService zyfwJgService = new ZyfwJgService();
				ZyfwSqForm zyfwSqForm = new ZyfwSqDao().getModel(zyfwShForm.getFwid());
				BeanUtils.copyProperties(zyfwJgForm,zyfwSqForm);
				zyfwJgForm.setSjly("1");
				reuslt = zyfwJgService.runInsert(zyfwJgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/** 
	 * @描述:保存志愿服务批量审核
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月11日 下午2:52:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwShForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String saveForPlsh(ZyfwShForm zyfwShForm, User user) {
		
		ZyfwShForm model = new ZyfwShForm();
		String[] fwids = zyfwShForm.getFwids();
		String[] gwids = zyfwShForm.getGwids();
		String[] xhs = zyfwShForm.getXhs();
		
		model.setSplc(zyfwShForm.getSplc());
		model.setShyj(zyfwShForm.getShyj());
		model.setShjg(zyfwShForm.getShjg());
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = fwids.length; i < n; i++) {
			
			model.setGwid(gwids[i]);
			model.setFwid(fwids[i]);
			model.setXh(xhs[i]);

			boolean isSuccess = saveForDgsh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(zyfwShForm.getShzt())) {
			//【暂保留】批量审核中暂时没有没有批量退回
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}

	/**
	 * @描述:志愿服务审核，最后一级的撤销审核
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月11日 下午4:53:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwShForm
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public boolean cancelShLast(ZyfwShForm zyfwShForm) throws Exception {
		
		zyfwShForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(zyfwShForm);
		if (result) {
			new ZyfwJgDao().runDelete(new String[]{zyfwShForm.getFwid()});
		}
		return result;
	}

}
