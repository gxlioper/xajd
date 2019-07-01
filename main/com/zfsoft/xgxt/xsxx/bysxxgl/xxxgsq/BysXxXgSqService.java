/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午04:31:56 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxForm;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxService;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午04:31:56
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxXgSqService extends SuperServiceImpl<BysXxForm, BysXxXgSqDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @描述:查看申请修改列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午06:39:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageList(BysXxForm model,
			User user) throws Exception {
		return dao.getXgSqPageList(model, user);
	}

	/**
	 * 
	 * @描述:查看申请修改列表(学生)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午06:41:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageListByStu(BysXxForm model,
			User user) throws Exception {
		return dao.getXgSqPageListByStu(model, user);
	}

	/**
	 * 
	 * @描述:保存信息修改申请单
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午02:25:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean saveXgSq(BysXxXgSqForm model) throws Exception {
		boolean result = true;
		String guid = UniqID.getInstance().getUniqIDHash();
		guid = guid.toUpperCase();
		model.setSqid(guid);
		model.setShjg(Constants.YW_WTJ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		String shlid = null;
		HashMap<String, String> splMap = new BysXxCsSzService().getCssz();
		shlid = splMap.get("shlid");
		model.setSplc(shlid);
		dao.deleteShlc(model);
		dao.deleteXgsq(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////保存修改字段////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		result = dao.insertXgsq(model); // 插入申请记录
		return result;
	}
	/**
	 * 
	 * @描述:TODO信息申请修改保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 上午08:37:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean sqXgSave(BysXxXgSqForm model) throws Exception {
		boolean result = true;
		model.setShjg(Constants.YW_WTJ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		dao.sqXgUpdate(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////更新修改字段////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.updateXgzd(xgzdList, model.getSqid());
		}
		return result;
	}

	/**
	 * 
	 * @描述:提交信息修改申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午05:18:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean tjXgSq(BysXxXgSqForm model, BysXxForm form,
			HashMap<String, String> bysXxvalueMap) throws Exception {
		boolean result = true;
		String guid = UniqID.getInstance().getUniqIDHash();
		guid = guid.toUpperCase();
		model.setSqid(guid);
		model.setShjg(Constants.YW_SHZ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));

		String shlid = null;
		HashMap<String, String> splMap = new BysXxCsSzService().getCssz();
		shlid = splMap.get("shlid");
		model.setSplc(shlid);
		dao.deleteShlc(model);
		dao.deleteXgsq(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////保存修改字段////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		if (shlid == null || shlid.equals("") || shlid.equals("wxsh")) {// 无需审核
			model.setShjg(Constants.YW_TG);
			XsxxglService xsxxglService = new XsxxglService();
			// 修改学生信息
			result = xsxxglService.updateRecordForStu(model.getSqid(), model
					.getXh(), false,true);
			if (result) {
				BysXxService bysxxService = new BysXxService();
				result = bysxxService.updateBysXx(form, bysXxvalueMap);
			}
		}
		result = dao.insertXgsq(model); // 插入申请记录
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),
					model.getXh(), "xsxx_new_bysxx_xxxgsh.do",
					"xsxx_new_bysxx_xxxgsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @描述:信息修改申请修改提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 上午09:09:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param form
	 * @param bysXxvalueMap
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean sqXgTj(BysXxXgSqForm model, BysXxForm form,
			HashMap<String, String> bysXxvalueMap) throws Exception {
		boolean result = true;
		model.setShjg(Constants.YW_SHZ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		dao.sqXgUpdate(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////保存修改字段////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.updateXgzd(xgzdList, model.getSqid());
		}
		HashMap<String, String> splMap = new BysXxCsSzService().getCssz();
		String shlid = splMap.get("shlid");
		if (shlid == null || shlid.equals("") || shlid.equals("wxsh")) {// 无需审核
			model.setShjg(Constants.YW_TG);
			XsxxglService xsxxglService = new XsxxglService();
			// 修改学生信息
			result = xsxxglService.updateRecordForStu(model.getSqid(), model
					.getXh(), false,true);
			if (result) {
				BysXxService bysxxService = new BysXxService();
				result = bysxxService.updateBysXx(form, bysXxvalueMap);
			}
		}
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),
					model.getXh(), "xsxx_new_bysxx_xxxgsh.do",
					"xsxx_new_bysxx_xxxgsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @描述:TODO获取申请id
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 下午04:52:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String, String>  getSqXxByXh(String xh) throws Exception{
		return dao.getSqXxByXh(xh);
	}
	/**
	 * 
	 * @描述:通过sqid获取学号
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 下午04:04:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String>  getXhBySqid(String sqid) throws Exception{
		return dao.getXhBySqid(sqid);
	}

	/**
	 * 
	 * @描述:查询毕业生
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 上午10:54:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBysXxList(BysXxForm model, User user)
			throws Exception {
		return dao.getBysXxList(model, user);

	}
	/**
	 * 提交申请
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午09:38:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param splc
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitRecord(String sqid, String splc, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(sqid, splc, xh, "xsxx_new_bysxx_xxxgsq.do",
				"xsxx_new_bysxx_xxxgsh.do");
		return result;
	}
	/**
	 * 
	 * @描述:撤销申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午10:23:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	/**
	 * 
	 * @描述:更新申请状态
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午10:29:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public boolean updateXgSq(BysXxXgSqForm model)throws Exception{
		return dao.updateXgSq(model);
	}

	/**
	 * 
	 * @描述:删除字段修改信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午09:02:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean xgZdDel(String[] values) throws Exception {
		return dao.xgZdDel(values);
	}
	public boolean sqXxDel(String sqid) throws Exception{
		return dao.sqxxDel(sqid);
	}

}
