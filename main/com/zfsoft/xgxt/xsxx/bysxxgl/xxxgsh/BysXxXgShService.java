/**
 * @部门:学工产品事业部
 * @日期：2014-7-10 上午11:15:29 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsh;

import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq.BysXxXgSqForm;
import com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq.BysXxXgSqService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-10 上午11:15:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxXgShService extends
		SuperServiceImpl<BysXxXgShForm, BysXxXgShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private BysXxXgSqService bysxxxgService = new BysXxXgSqService();
	private XsxxglService service = new XsxxglService();

	// private BaseDbcz dbcz = new BaseDbcz();
	/**
	 * 
	 * @描述:TODO审核保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 下午03:58:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveSqSh(BysXxXgShForm form, User user) throws Exception {
		boolean result = true;
		// 审核操作Model初始化

		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xsxx_new_bysxx_xxxgsh.do");
		model.setTzljsq("xsxx_new_bysxx_xxxgsq.do");
		String shzt = shlc.runAuditing(model);
		BysXxXgSqForm myform = new BysXxXgSqForm();
		myform.setShjg(shzt);
		myform.setSqid(form.getSqid());
		result = bysxxxgService.updateXgSq(myform);
		if (shzt.equals(Constants.SH_TG)) {
			// 修改学生信息
			String sqid = form.getSqid();
			result = service.updateRecordForStu(sqid, form.getXh(), false, true);
		}

		return result;

	}

	public boolean savePlXgSh(BysXxXgShForm form,String dataJson, User user) throws Exception {
		boolean result=true;
		// 审核操作Model初始化
		
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		// 业务ID(多为申请ID)
		model.setTzlj("xsxx_new_bysxx_xxxgsh.do");
		model.setTzljsq("xsxx_new_bysxx_xxxgsq.do");
		BysXxXgSqForm myform = new BysXxXgSqForm();
		dataJson = "{data:" + dataJson + "}";
		List list = JsonUtil.jsonToList(dataJson);
		String gwid = null;
		String ywid = null;
		String xh = null;
		String lcid = null;
		System.out.println("size:"+list.size());
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
			gwid = (String) bean.get("gwid");
			ywid = (String) bean.get("ywid");
			lcid = (String) bean.get("lcid");
			xh = (String) bean.get("xh");
			model.setGwid(gwid);
			model.setYwid(ywid);
			model.setSqrid(xh);
			model.setShlc(lcid);
			String shzt = shlc.runAuditing(model);
			if (shzt != null) {
				myform.setSqid(model.getYwid());
				myform.setShjg(shzt);
				result = bysxxxgService.updateXgSq(myform);
				if (shzt.equals(Constants.SH_TG)) {
					// 修改学生信息
					String sqid = model.getYwid();
					result = service.updateRecordForStu(sqid,xh, false,true);
			}
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @描述:TODO审核撤销退回
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 上午09:59:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean CxXgsq(String sqid) throws Exception {
		BysXxXgSqForm model = new BysXxXgSqForm();
		model.setSqid(sqid);

		boolean result = service.updateRecordForStu(sqid, true);
		if (result) {
			model.setShjg(Constants.YW_SHZ);
			result = bysxxxgService.updateXgSq(model);
		}
		return result;
	}
}
