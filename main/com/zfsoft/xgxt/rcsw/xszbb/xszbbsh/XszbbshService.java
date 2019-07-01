/**
 * @部门:学工产品事业部
 * @日期： 2013-12-18 上午08:52:03 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjg.XszbbjgDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjg.XszbbjgForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(学生证补办-补办审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 上午08:52:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbshService extends SuperServiceImpl<XszbbshForm, XszbbshDao> {

	private XszbbshDao dao = new XszbbshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XszbbshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:查询获取审批信息
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:53:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXszbbshInfo(XszbbshForm model) {
		if (StringUtil.isNull(model.getBbsqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getXszbbshInfo(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * 保存学生证补办审核 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:58:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(XszbbshForm form,User user) throws Exception{
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
		model.setYwid(form.getBbsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_xszbb_bbsh.do");
		model.setTzljsq("rcsw_xszbb_bbsq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			XszbbshForm upForm = new XszbbshForm();
			upForm.setBbsqid(form.getBbsqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getBbsqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				XszbbjgForm xszbbjgForm = new XszbbjgForm();
        		BeanUtils.copyProperties(xszbbjgForm, StringUtils.formatData(form));
        		xszbbjgForm.setBbjgid(form.getBbsqid());
        		xszbbjgForm.setSjly("1");
        		xszbbjgForm.setBbsqid(form.getBbsqid());
        		if(Base.xxdm.equals("13011")){        			
        			xszbbjgForm.setShwcsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        		}
        		XszbbjgDao bbjgDao = new XszbbjgDao();
        		bbjgDao.runInsert(xszbbjgForm);
        		if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店职业技术学院个性化
        			hcccqjUpdate(xszbbjgForm);
        		}
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
	public boolean newCancelSh(XszbbshForm model){
		boolean resultRcww = false;
		boolean resultRcxwjg = false;
		try {
			//更新日常行为信息维护
			resultRcww = dao.updateXsbbsq(model.getBbsqid(), Constants.YW_SHZ);
			if(resultRcww){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultRcxwjg = true;
				}else{
					//删除日常行为结果中的记录
					resultRcxwjg = dao.deleteXsbbsq(model.getBbsqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRcxwjg;
	}

	/**
	 * @throws Exception  
	 * @描述:批量保存证件补办审核
	 * @作者：cq [工号：785]
	 * @日期：2014-4-25 上午10:23:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(XszbbshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			//得到与ID对应的审批流程ID
			XszbbshDao xszbbshdao = new XszbbshDao();
			Map<String, String> resultList = xszbbshdao.getOneXsbbsqInfo(ids[i]);
			
			XszbbshForm model = new XszbbshForm();
			model.setSplc(resultList.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setBbsqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setSqsj(resultList.get("sqsj"));
			model.setXszbblxdm(resultList.get("xszbblxdm"));
			model.setSfbbhcyhk(resultList.get("sfbbhcyhk"));
			model.setDd(resultList.get("dd"));
			model.setSj(resultList.get("sj"));
			model.setSqly(resultList.get("sqly"));
			model.setFilepath(resultList.get("filepath"));

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
	
	/**
	 * @throws Exception  
	 * @描述:火车乘车区间更新(青岛酒店管理个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-23 下午07:06:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws 
	 */
	public void hcccqjUpdate(XszbbjgForm xszbbjgForm) throws Exception{
		XszbbjgDao bbjgDao = new XszbbjgDao();				
		if("y".equalsIgnoreCase(xszbbjgForm.getSfbbhcyhk())){//补办火车乘车区间卡			
			bbjgDao.delHcccqj(xszbbjgForm.getXh(), Base.currXn, Base.currXq);//删除当年度，当学期该同学火车乘车区间结果
			HcccqjjgForm jgForm = new HcccqjjgForm();
			jgForm.setXh(xszbbjgForm.getXh());
			jgForm.setXn(Base.currXn);
			jgForm.setXq(Base.currXq);
			jgForm.setSjly("0");
			jgForm.setCcqdz(xszbbjgForm.getCcqdz());
			jgForm.setCczdz(xszbbjgForm.getCczdz());
			jgForm.setTxsj(xszbbjgForm.getSqsj());
			HcccqjjgService jgService = new HcccqjjgService();
			jgService.runInsert(jgForm);
		}
		
	}
	

}
