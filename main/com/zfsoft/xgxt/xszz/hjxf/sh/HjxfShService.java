/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午11:50:19 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.hjxf.jg.HjxfJgDao;
import com.zfsoft.xgxt.xszz.hjxf.jg.HjxfJgForm;
import com.zfsoft.xgxt.xszz.hjxf.jg.HjxfJgService;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqDao;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqForm;
import com.zfsoft.xgxt.xszz.hjxf.unit.Unit;
import com.zfsoft.xgxt.xszz.hjxf.unit.UnitForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午11:50:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfShService extends SuperServiceImpl<HjxfShForm, HjxfShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *审核保存
	 */
	public boolean saveSh(HjxfShForm form, User user) {
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
		// 业务ID(多为申请ID)
		model.setYwid(form.getHjxfid());
		model.setSqrid(form.getXh());
		model.setTzlj("xszz_hjxf_sh.do");
		model.setTzljsq("xszz_hjxf_sq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HjxfShForm hjxfshform = new HjxfShForm();
			hjxfshform.setHjxfid(form.getHjxfid());
			hjxfshform.setShzt(zhzt);
			reuslt = dao.runUpdate(hjxfshform, form.getHjxfid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				HjxfJgForm hjxfjgform = new HjxfJgForm();
				HjxfJgService hjxfjgservice = new HjxfJgService();
				HjxfSqForm sqForm = new HjxfSqDao().getModel(form.getHjxfid());
				BeanUtils.copyProperties(hjxfjgform, StringUtils.formatData(sqForm));
//				khjgService.Cjcl(khjgForm);
				
				hjxfjgform.setXh(form.getXh());
				Unit util = new Unit();
				UnitForm utilform = new UnitForm();
				utilform.setXh(sqForm.getXh());
				utilform.setXn(sqForm.getXn());
				utilform.setXq(sqForm.getXq());
				utilform.setType("jg");
				if(!util.isNotExists(utilform)){
					hjxfjgservice.delDkjg(utilform.getXh(),utilform.getXn(),utilform.getXq());
				}
				hjxfjgform.setJgid(sqForm.getHjxfid());
				hjxfjgform.setSjly("1");
				hjxfjgform.setCzy(user.getUserName());
				reuslt = hjxfjgservice.runInsert(hjxfjgform);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//批量审核
	public String savePlsh(HjxfShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		HjxfShForm model = new HjxfShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(t.getSplcid());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setHjxfid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
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
	
	public boolean cancel(HjxfShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getHjxfid());
		if (result) {
			HjxfJgDao jgdao = new HjxfJgDao();
		
			// 删除结果表中的申请结果
			
			jgdao.runDelete(new String[]{myForm.getHjxfid()});
		
		}
		return result;
	}

	public String cxshnew(String ywid, HjxfShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
	
	
}
