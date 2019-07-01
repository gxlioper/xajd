/**
 * @部门:学工产品事业部
 * @日期： 2013-12-18 上午08:52:03 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgDao;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车车程区间管理模块
 * @类功能描述: TODO(火车车程区间审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:37:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjshService extends SuperServiceImpl<HcccqjshForm, HcccqjshDao> {

	private HcccqjshDao dao = new HcccqjshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public HcccqjshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(查询获取火车乘车区间审批信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午01:50:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getHcccqjshInfo(HcccqjshForm model) {
		if (StringUtil.isNull(model.getCcqjtxid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getHcccqjshInfo(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(保存火车乘车区间审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午02:29:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(HcccqjshForm form,User user) throws Exception{
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
		model.setYwid(form.getCcqjtxid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_hcyhk_hcccqjsh.do");
		model.setTzljsq("rcsw_hcyhk_hcccqjtx.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			HcccqjshForm upForm = new HcccqjshForm();
			upForm.setCcqjtxid(form.getCcqjtxid());
			//upForm.setBbsqid(form.getCcqjtxid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getCcqjtxid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				HcccqjjgForm hcccqjjgForm = new HcccqjjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(hcccqjjgForm, StringUtils.formatData(form));
        		hcccqjjgForm.setCcqjjgid(form.getCcqjtxid());
        		hcccqjjgForm.setSjly("1");
        		hcccqjjgForm.setCcqjtxid(form.getCcqjtxid());
        		HcccqjjgDao jgDao = new HcccqjjgDao();
        		jgDao.deleteExist(hcccqjjgForm);
        		if(Base.xxdm.equals("13011")){        			
        			hcccqjjgForm.setShwcsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        		}
        		jgDao.runInsertNotCommit(hcccqjjgForm);	
			}	
		
		return reuslt;
	}
	
	
	/**
	 * 
	 * @描述:TODO(撤销学生证补办审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-18 下午03:46:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(HcccqjshForm model){
		boolean resultHcccqjtx = false;
		boolean resultHcccqjjg = false;
		try {
			//更新火车乘车区间填写
			resultHcccqjtx = dao.updateHcccqjtx(model.getCcqjtxid(), Constants.YW_SHZ);
			if(resultHcccqjtx){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultHcccqjjg = true;
				}else{
					//删除火车乘车区间结果的记录
					resultHcccqjjg = dao.deleteHcccqjtx(model.getCcqjtxid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultHcccqjjg;
	}

	
	/**
	 * @throws Exception  
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-25 下午02:53:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(HcccqjshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			HcccqjshForm model = new HcccqjshForm();
			model.setSplc(splcs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setCcqjtxid(ids[i]);
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
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

}
