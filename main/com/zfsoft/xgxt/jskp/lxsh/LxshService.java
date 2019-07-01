package com.zfsoft.xgxt.jskp.lxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;

public class LxshService extends SuperServiceImpl<LxshForm, LxshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *单个审核保存
	 */
	@TransactionControl
	public boolean saveSh(LxshForm form, User user) throws Exception{
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
		model.setYwid(form.getSqid());
		model.setSqrid(form.getFzr());
		model.setTzlj("pjpy_jskp_lxsh.do");
		model.setTzljsq("pjpy_jskp_lxsq.do");
		model.setZd1("评分区间");
		model.setZd3(form.getZxf()+"-"+form.getZdf());
		boolean reuslt = false;
		String zhzt = shlc.runAuditingNotCommit(model);
		LxshForm shForm = new LxshForm();
		shForm.setSqid(form.getSqid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdateNotCommit(shForm, form.getSqid());
		// 保存到结果表
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			XmjgForm jgForm = new XmjgForm();
			shForm = this.getModel(shForm);
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
			jgForm.setXmid(shForm.getSqid());
			jgForm.setSjly("1");
			jgForm.setXmsqid(shForm.getSqid());
			jgForm.setXmdl("zlx");
			jgForm.setZxf(form.getZxf());
			jgForm.setZdf(form.getZdf());
			reuslt = new XmjgDao().runInsertNotCommit(jgForm);
			if(reuslt){
				reuslt = dao.updateXmsbLxzt(jgForm.getXmid());
				String splc = new CsszService().getSplc("sb").get("splc");
				if(reuslt){
					reuslt = dao.updateSbSplc(jgForm.getXmid(), splc);
					List<HashMap<String, String>> submitList = new LxsqService().getXmcyryXhs(jgForm.getXmid());
					List<String> xhList = new ArrayList<String>();
					List<String> sqidList = new ArrayList<String>();
					for (int i = 0; i < submitList.size(); i++) {
						xhList.add(submitList.get(i).get("xh"));
						sqidList.add(submitList.get(i).get("sqid"));
					}
					if(sqidList.size() > 0){
						reuslt =  shlc.runSubmitBatchNotCommit(sqidList.toArray(new String[]{}),splc, xhList.toArray(new String[]{}), "jskp_xmsh.do", "jskp_xmsb.do");
					}
					dao.submitSqjl(shForm.getSqid());
				}
			}
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述:批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午09:39:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(LxshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		LxshForm model = new LxshForm();
		String[] sqids = t.getSqids();
		String[] gwid = t.getGwids();
		String[] fzrs = t.getFzrs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setFzr(fzrs[i]);
			model.setZxf(t.getZxf());
			model.setZdf(t.getZdf());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(fzrs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failZghs);
		if (failZghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午11:03:47
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
			XmjgService jgService = new XmjgService();
		
			// 删除结果表中的申请结果
			jgService.runDelete(new String[]{myForm.getSqid()});
			dao.updateXmsbZt(myForm.getSqid(),"0");
		
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午11:03:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, LxshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;
	}
	
	/**
	 * 
	 * @描述:验证是否有未提交的记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-21 下午02:06:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isStuSbTj(String sqid) throws Exception{
		return dao.isStuSbTj(sqid);
	}
	
	/**
	 * 
	 * @描述: 获取上一级审核字段
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-18 下午04:51:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLastshzd(String ywid){
		return dao.getLastshzd(ywid);
	}

}
