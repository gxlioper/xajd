/**
 * @部门:学工产品事业部
 * @日期：2017-1-4 下午05:46:24 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsh;

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
import com.zfsoft.xgxt.rcsw.rwdj.RwdjForm;
import com.zfsoft.xgxt.rcsw.rwdj.RwdjService;
import com.zfsoft.xgxt.rcsw.rwdjsq.RwdjsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-4 下午05:46:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwdjshService extends SuperServiceImpl<RwdjshForm, RwdjshDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @描述: 单个审核保存
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-5 上午09:54:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(RwdjshForm form, User user) {
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
//		model.setZd1("有效工时");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// 业务ID(多为申请ID)
		model.setYwid(form.getRwdjid());
		model.setSqrid(form.getXh());
		model.setTzlj("rwdjshbase.do");
		model.setTzljsq("rwdjsqbase.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			RwdjshForm shForm = new RwdjshForm();
			shForm.setRwdjid(form.getRwdjid());
			shForm.setShzt(zhzt);
			reuslt = dao.runUpdate(shForm, shForm.getRwdjid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				RwdjForm jgForm = new RwdjForm();
				RwdjService jgService = new RwdjService();
				shForm = this.getModel(form.getRwdjid());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
				
				if(!jgService.checkIsNotExist(shForm.getXh())){
					jgService.delJgbyXh(shForm.getXh());
				}
				jgForm.setRwdjid(shForm.getRwdjid());
				jgForm.setSjly("1");
				reuslt = jgService.runInsert(jgForm);
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
	 * @日期：2017-1-5 上午10:37:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(RwdjshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		RwdjshForm model = new RwdjshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRwdjid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);

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
	 * @日期：2017-1-5 上午10:39:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(RwdjshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getRwdjid());
		if (result) {
			//删除结果表中的数据
			new RwdjService().delete(new String[]{myForm.getRwdjid()});
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-5 上午10:39:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, RwdjshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;

	}
	
	@Override
	public RwdjshForm getModel(RwdjshForm t) throws Exception {
		return dao.getModel(t);
	}
}
