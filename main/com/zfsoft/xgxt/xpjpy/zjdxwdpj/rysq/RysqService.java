/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-3 下午05:01:55 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_荣誉申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-7-3 下午05:01:26 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RysqService extends SuperServiceImpl<RysqForm,RysqDao>{
	private RysqDao dao = new RysqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public RysqService(){
		super.setDao(dao);
	}
	
	@Override
	public RysqForm getModel(RysqForm t)throws Exception {
		return super.getModel(t);
	}
	
	/**
	 * @描述: 取学生最近一条申请信息，主要是取
	 *【外语水平、宿舍电话、担任社会工作职务、个人学习经历、参加科研情况、对设奖单位的认识】
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 上午09:44:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		return dao.getLatestSqxx(xh);
	}
	
	/**
	 * @描述: 开放申请且没有申请过的奖项
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午02:19:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * Map<String,Object> 返回类型 
	 * @throws
	 */
	public Map<String, Object> getRysqInfoList(String xh) throws Exception {
		/*开放申请且没有申请过的奖项*/
		List<HashMap<String, String>> wsqList = dao.getKsqInfoList(xh);
		/*当前学年、学期已申请的奖项*/
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		/*返回结果*/
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		/*验证条件*/
		TjszService tjszServcie = new TjszService();
		for (HashMap<String, String> pjxm : wsqList) {
			List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(pjxm.get("xmdm"), xh);
			// 校验条件，返回不符合条件的项目名称。
			CheckCondition check = new CheckStudentCondition();
			// 校验结果
			List<HashMap<String, String>> results = check.checkCondition(xh,conditions);
			resultMap.put(pjxm.get("xmdm"), results);
		}
		resultMap.put("wsqList", wsqList);
		resultMap.put("ysqList", ysqList);
		return resultMap;
	}
	
	/**
	 * @描述: 查询学生学号和学院代码
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午04:28:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getPjxmXsxxMap(RysqForm t) throws Exception{
		return dao.getPjxmXsxxMap(t);
	}
	
	/**
	 * @描述: 批量保存荣誉申请
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午04:38:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRysq(String[] xmdm, RysqForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String pjxm : xmdm) {
			RysqForm model = new RysqForm();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(pjxm);
			model.setType(t.getType());
			model.setId(t.getId());
			model.setFjxx(t.getFjxx());
			
			model.setWysp(t.getWysp());/*外语水平*/
			model.setSsdh(t.getSsdh());/*宿舍电话*/
			model.setGzzw(t.getGzzw());/*担任社会工作职务*/
			model.setGrxxjl(t.getGrxxjl());	//个人学习经历
			model.setCjkyqk(t.getCjkyqk());/*参加科研情况*/
			model.setDwrs(t.getDwrs());/*对设奖单位的认识*/
			saveJxsq(model, t.getSqr());
		}
		return true;
	}
	
	/**
	 * @描述: 保存操作
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午04:41:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJxsq(RysqForm model, String userName) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		/*项目信息*/
		XmwhDao xmmwDao = new XmwhDao();
		XmwhForm xmwhModel = xmmwDao.getModel(model.getXmdm());
		/*审核流程*/
		String splc = xmwhModel.getShlc();
		/*参数设置信息*/
		CsszDao csszdao = new CsszDao();
		CsszForm csszModel = csszdao.getModel();
		
		model.setSqr(userName);
		model.setXn(csszModel.getXn());
		model.setId(sqid);
		model.setSplc(splc);
		/*有审批流的情况设定初始值*/
		if("submit".equals(model.getType())){
			/*点击 提交申请 按钮审核状态为审核中【5】*/
			model.setShzt(Constants.YW_SHZ);
		}else{
			/*点击 保存草稿 按钮审核状态为未提交【0】*/
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		/*保存申请信息*/
		boolean result = dao.runInsert(model);
		if (!"save".equals(model.getType())) {
			result = shlc.runSubmit(sqid, splc, model.getXh(),"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
		}
		return result;
	}
	
	/**
	 * @描述: 荣誉申请撤销
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-10 上午10:42:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @描述: 查询已设置人数的荣誉称号项目
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-8 下午08:05:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getRychList(RysqForm model) throws Exception{
		return dao.getRychList(model);
	}
}
