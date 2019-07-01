package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh;

import java.util.ArrayList;
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
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgDao;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgService;

public class YbshService extends SuperServiceImpl<YbshForm, YbshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *单个审核保存
	 */
	public boolean saveSh(YbshForm form, User user) throws Exception{
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
		model.setYwid(form.getSbid());
		model.setSqrid(form.getTxr());
		model.setTzlj("xg_xlzxnew_ybsh.do");
		model.setTzljsq("xg_xlzxnew_ybsb.do");
		boolean reuslt = false;
		String zhzt = shlc.runAuditing(model);
		YbshForm shForm = new YbshForm();
		shForm.setSbid(form.getSbid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdate(shForm, form.getSbid());
		// 保存到结果表
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			YbjgForm jgForm = new YbjgForm();
			shForm = this.getModel(shForm);
			//先对结果表中本学年学期相同班级相同周次的数据数据进行删除
			dao.delJgWtxxData(shForm);
			dao.delJgbRepeaData(shForm);
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
			jgForm.setJgid(shForm.getSbid());
			jgForm.setSjly("1");
			reuslt = new YbjgDao().runInsert(jgForm);
			if(reuslt){
				dao.shUpdateWtxxb(shForm.getSbid());
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
	public String savePlsh(YbshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		YbshForm model = new YbshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] txrs = t.getTxrss();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSbid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setTxr(txrs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(txrs[i]);
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
	public boolean cancel(YbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSbid());
		if (result) {
			YbjgService jgService = new YbjgService();
		
			// 删除结果表中的申请结果
			jgService.runDelete(new String[]{myForm.getSbid()});
			dao.cxUpdateWtxxb(myForm.getSbid());
		
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
	public String cxshnew(String ywid, YbshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;
	}
}
