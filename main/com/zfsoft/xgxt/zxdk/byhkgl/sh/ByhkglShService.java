/**
 * @部门:学工产品事业部
 * @日期：2016-5-10 下午03:59:53 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sh;

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
import com.zfsoft.xgxt.zxdk.byhkgl.jg.ByhkglJgDao;
import com.zfsoft.xgxt.zxdk.byhkgl.jg.ByhkglJgForm;
import com.zfsoft.xgxt.zxdk.byhkgl.jg.ByhkglJgService;
import com.zfsoft.xgxt.zxdk.byhkgl.sq.ByhkglSqDao;
import com.zfsoft.xgxt.zxdk.byhkgl.sq.ByhkglSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款审核 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-10 下午03:59:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglShService extends SuperServiceImpl<ByhkglShForm, ByhkglShDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private ByhkglShDao dao = new ByhkglShDao();
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午01:29:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(ByhkglShForm form, User user) {
		
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
		model.setTzlj("zxdk_byhkgl_byhksh.do");
		model.setTzljsq("zxdk_byhkgl_byhksq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			ByhkglShForm sbshForm = new ByhkglShForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				ByhkglJgForm byhkglJgForm = new ByhkglJgForm();
				ByhkglJgService byhkglJgService = new ByhkglJgService();
				ByhkglSqForm byhkglSqForm = new ByhkglSqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(byhkglJgForm, StringUtils.formatData(byhkglSqForm));
				byhkglJgForm.setLclyywid(byhkglSqForm.getSqid());
				byhkglJgForm.setSjly("1");
				if(byhkglJgService.isHaveRecordForjg(byhkglJgForm)) {
					//如果结果表中存在数据，先删除再插入
					new ByhkglJgDao().deleteForSq(byhkglJgForm);
					byhkglJgService.runInsert(byhkglJgForm);
				}else {
					byhkglJgService.runInsert(byhkglJgForm);
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
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午01:56:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, ByhkglShForm model, User user) throws Exception {
		
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		
		return cancelFlag;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午01:56:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(ByhkglShForm myForm) throws Exception {
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		// 删除结果库中的数据
		ByhkglJgDao byhkglJgDao = new ByhkglJgDao();
		result = byhkglJgDao.delByLclyywid(myForm.getSqid());
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午02:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(ByhkglShForm t, User user) throws Exception{
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			ByhkglShForm form = dao.getModel(ids[i]);
			ByhkglShForm model = new ByhkglShForm();
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
