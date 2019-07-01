/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 上午09:24:35 
 */
package com.zfsoft.xgxt.axcs.wpsq.js;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 上午09:24:35
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpsqJsService extends SuperServiceImpl<WpsqJsForm, WpsqJsDao> {
	private WpsqJsDao dao = new WpsqJsDao();
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @描述:物品申请信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:03:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             Map<String,Object> 返回类型
	 * @throws
	 */
	public Map<String, Object> getWpsqInfoList(String xh) throws Exception {
		// 当前学年未申请的物品
		List<HashMap<String, String>> wsqList = dao.getKsqInfoList(xh);
		// 当前学年已申请的物品
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 验证条件
		for (HashMap<String, String> wp : wsqList) {
			List<HashMap<String, String>> results = checkTj(wp.get("xmdm"), xh);
			resultMap.put(wp.get("xmdm"), results);
		}
		resultMap.put("wsqList", wsqList);
		resultMap.put("ysqList", ysqList);
		return resultMap;
	}

	public List<HashMap<String, String>> checkTj(String xmdm, String xh) throws Exception {
		WpszDao wpszDao = new WpszDao();
		StringBuffer sqtj = new StringBuffer();
		List<HashMap<String, String>> checkResult = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> tjList = wpszDao.getWpTjSz(xmdm);
		HashMap<String, String> xsJtxxMap = wpszDao.getJtxx(xh);
		HashMap<String,String> condition = new HashMap<String,String>();
		if (tjList == null || tjList.size() == 0) {
			return null;
		}
		// 验证
		boolean success = false;
		for (HashMap<String, String> tjMap : tjList) {

			String dcxxTj = tjMap.get("tjz");
			if (tjMap.get("dcdm").equals(xsJtxxMap.get("rddc"))) {
				success = true;
				if (!StringUtils.isNull(dcxxTj)) {
					String[] tjInfo = dcxxTj.split(";");
					for (int i = 0; i < tjInfo.length; i++) {
						if (!"1".equals(xsJtxxMap.get(tjInfo[i]))) {
							success = false;
						}
					}
				}
			}
			sqtj.append(tjMap.get("sqtj")).append(" 或");
		}
		condition.put("sqtj", sqtj.substring(0, sqtj.length()-1));
		condition.put("result", String.valueOf(success));
		checkResult.add(condition);
		return checkResult;
	}

	/**
	 * 
	 * @描述:学生申请物品保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:44:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean wpsqBc(String[] xmdm, WpsqJsForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String wp : xmdm) {
			WpsqJsForm model = new WpsqJsForm();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(wp);
			model.setType(t.getType());
			model.setSqid(t.getSqid());
			saveWpsq(model, t.getXh());
		}
		return true;
	}

	/**
	 * 
	 * @描述:物品申请单个保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:40:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveWpsq(WpsqJsForm model, String userName) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		WpszDao wpszDao = new WpszDao();
		WpszForm wpszForm = wpszDao.getModel(model.getXmdm());
		String splc = wpszForm.getSplc();
		model.setSqr(userName);
		model.setXn(wpszForm.getXn());
		model.setSqid(sqid);
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(), "axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午01:48:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

}
