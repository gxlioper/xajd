/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午09:04:03 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxsh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxjg.LxjgForm;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxjg.LxjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午09:04:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxshService extends SuperServiceImpl<LxshForm, LxshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 
	 * @描述: 审核
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午11:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(LxshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_qmlxsh.do");
		model.setTzljsq("rcsw_qmlxdj.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			LxshForm  shForm = new LxshForm();
			shForm.setSqid(form.getSqid());
			shForm.setShzt(zhzt);
			reuslt = dao.runUpdate(shForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				LxjgForm jgForm = new LxjgForm();
				LxjgService service = new LxjgService();
				LxshForm lxshForm = this.getModel(form);
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(lxshForm));
//				khjgService.Cjcl(khjgForm);
				
				
				jgForm.setXh(form.getXh());
				LxdjService sqService = new LxdjService();
				if(!sqService.checkNotExist(lxshForm.getXh(), lxshForm.getXn(), lxshForm.getXq(), "jg")){
					service.delJgbyShTg(lxshForm.getXh(),lxshForm.getXn(), lxshForm.getXq());
				}
				jgForm.setJgid(lxshForm.getSqid());
				jgForm.setSjly("1");
				reuslt = service.runInsert(jgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述: 批量审核
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午11:42:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(LxshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		LxshForm model = new LxshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] xns = t.getXns();
		String[] xqs = t.getXqs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(t.getSplcid());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(xns[i]);
			model.setXq(xqs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午11:51:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(LxshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			LxjgService jgService = new LxjgService();
		
			// 删除结果表中的申请结果
			jgService.runDelete(new String[]{myForm.getSqid()});
		
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午11:56:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, LxshForm shForm, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		shForm.setShzt(shzt);
		String cancelFlag = service.runCancelNew(user.getUserName(), shForm.getShid(), shForm.getShlc());
		dao.runUpdate(shForm, shForm.getSqid());
		return cancelFlag;

	}

}
