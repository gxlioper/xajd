/**
 * @部门:学工产品事业部
 * @日期：2015-8-3 下午05:04:19 
 */
package com.zfsoft.xgxt.xstgl.stgl.stsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.message.MessageKey;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-8-3 下午05:04:19
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StsqService extends SuperServiceImpl<StsqForm, StsqDao> {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	StsqDao dao = new StsqDao();

	/**
	 * 
	 * @描述:考核申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午02:26:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveStsq(StsqForm model) throws Exception {
		String Sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(Sqid);
		model.setStid(Sqid);
		String splc = dao.getShlcID(model);
		//model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}

		// 保存申请信息
		boolean result = dao.runInsert(model);
		//插入指导教师表
		boolean aa =dao.saveZdls(Sqid, model.getStid(), model.getXhs());
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(Sqid, splc, model.getSqid(), "stgl_stgl_stsh.do", "stgl_stgl_stsq.do");
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @描述:社团申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午02:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String saveEditStsq( StsqForm model) throws Exception {
		boolean result = false;
		String messageKey = "";
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID(model);
			model.setSplc(splc);
			result = runUpdate(model);
			//插入指导教师表
			boolean aa =dao.saveZdls(model.getSqid(), model.getStid(), model.getXhs());
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "stgl_stgl_stsh.do", "stgl_stgl_stsq.do");
			}
			messageKey= result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
		} else {
			result = runUpdate(model);
			//插入指导教师表
			boolean aa =dao.saveZdls(model.getSqid(), model.getStid(), model.getXhs());
			messageKey= result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
		}
		return messageKey;
	
	}
	/**
	 * 
	 * @描述:社团申请提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-3 下午03:17:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitStsq(StsqForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID(model);
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "stgl_stgl_stsh.do", "stgl_stgl_stsq.do");
			}
			return result;
	}

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午03:02:42
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

	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(StsqForm model) throws Exception {
		return dao.isHaveSqJl(model);
	}
	
	public HashMap<String,String> getSqxx(StsqForm model) throws Exception{
		return dao.getSqxx(model);
	}
	
	public String getBmmc(String bmdm) {
		return dao.getBmmc(bmdm);
	}
	
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}
	
	//社团申请增加添加学生，学生选择页面
	public List<HashMap<String, String>> getXsxxList(StsqForm model, User user) throws Exception {
		return dao.getXsxxList(model, user);
	}
	
	//社团申请增加添加老师，老师选择页面
	public List<HashMap<String, String>> getTeaxxList(StsqForm model, User user) throws Exception {
		return dao.getTeaList(model, user);
	}

	//获取教师职称列表
	public List<HashMap<String, String>> getZclblist(){
		return dao.getZclblist();
	}
	
	//获取部门代码列表
	public List<HashMap<String, String>> getBbdmlist(){
		return dao.getBbdmlist();
	}

	//指导老师信息列表
	public List<HashMap<String,String>> getZdlsInfo(StsqForm model) {
		return dao.getZdlsInfo(model);
	}

	public List<HashMap<String,String>> getstxjList() {
		List<HashMap<String,String>> stxjList = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i <6 ; i++) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put( "xj", String.valueOf(i));
			stxjList.add(map);
		}
		return stxjList;
	}

	public List<HashMap<String,String>> getsqAll(StsqForm model, User user) throws Exception {
		return dao.getsqAll(model,user);
	}
}
