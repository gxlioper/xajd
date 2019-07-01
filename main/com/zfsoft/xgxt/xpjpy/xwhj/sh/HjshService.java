/**
 * @部门:学工产品事业部
 * @日期：2016-7-26 下午05:59:32 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sh;

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
import com.zfsoft.xgxt.xpjpy.xwhj.jg.HjjgDao;
import com.zfsoft.xgxt.xpjpy.xwhj.jg.HjjgForm;
import com.zfsoft.xgxt.xpjpy.xwhj.jg.HjjgService;
import com.zfsoft.xgxt.xpjpy.xwhj.sq.HjsqDao;
import com.zfsoft.xgxt.xpjpy.xwhj.sq.HjsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖审核  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-26 下午05:59:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjshService extends SuperServiceImpl<HjshForm, HjshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private HjshDao dao = new HjshDao();
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-27 下午03:14:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(HjshForm form, User user) {
		
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
		model.setYwid(form.getSqid());
		model.setSqrid(user.getUserName());
		model.setTzlj("pjpy_hjgl_sh.do");
		model.setTzljsq("pjpy_hjgl_sq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HjshForm sbshForm = new HjshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				HjjgForm jgForm = new HjjgForm();
				HjjgService jgService = new HjjgService();
				HjsqForm sqForm = new HjsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(sqForm));
				jgForm.setLylcywid(sqForm.getSqid());
				jgForm.setSjly("1");
				if(jgService.isHaveRecordForjg(jgForm)) {
					//如果结果表中存在数据，先删除再插入
					new HjjgDao().deleteForSq(jgForm);
					jgService.runInsert(jgForm);
				}else {
					jgService.runInsert(jgForm);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-27 下午03:15:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, HjshForm model, User user) throws Exception {
		
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		
		return cancelFlag;
		
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-27 下午03:15:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(HjshForm myForm) throws Exception {
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		// 删除结果库中的数据
		HjjgDao jgDao = new HjjgDao();
		result = jgDao.delByLclyywid(myForm.getSqid());
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-27 下午03:32:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(HjshForm t, User user) throws Exception{
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			HjshForm form = dao.getModel(ids[i]);
			HjshForm model = new HjshForm();
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}		
		}
		
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
}
