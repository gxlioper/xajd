/**
 * @部门:学工产品(1)部
 * @日期：2017-5-12 下午04:59:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq;

import com.zfsoft.utils.StringUtil;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_奖项申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-12 下午05:00:34 
 * @版本： V5.18.26
 * @修改记录: 2018-01-04
 */

public class XmsqService extends SuperServiceImpl<XmsqForm, XmsqDao>{
	private XmsqDao dao = new XmsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XmsqService() {
		super.setDao(dao);
	}
	
	@Override
	public XmsqForm getModel(XmsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return super.getModel(t);
	}
	
	/**
	 * @描述: 项目申请信息（可申请、已申请）
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午09:03:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * Map<String,Object> 返回类型 
	 * @throws
	 */
	public Map<String, Object> getXmsqInfoList(String xh) throws Exception {
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
	 * @描述: 根据项目代码，返回不可兼得已申请的奖项。
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午11:22:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjdxm(String xmdm) throws Exception{
		return dao.getBjdxm(xmdm);
	}
	
	/**
	 * @描述: 批量获取奖项信息、学生信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午04:05:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	/*查询学生学号和学院代码*/
	public HashMap<String,String> getPjxmXsxxMap(XmsqForm t) throws Exception{
		return dao.getPjxmXsxxMap(t);
	}
	/*查询奖学金金额上限，这张表的用法需确认*/
	public List<String> getXzjx() throws Exception{
		return dao.getXzjx();
	}
	/*已申请人数*/
	public String getYsqXs(String xmxx) throws Exception{
		return dao.getYsqXs(xmxx);
	}
	/*项目人数设置表里的总人数*/
	public String getPjxmRsxxsx(String xmxx) throws Exception{
		return dao.getPjxmRsxxsx(xmxx);
	}
	/*获得评奖项目信息*/
	public List<HashMap<String,String>> getPjxmXsxxList(String[] sqidArr) throws Exception{
		return dao.getPjxmXsxxList(sqidArr);
	}
	/*提交项目人数信息*/
	public List<HashMap<String,String>> getPjxmRsxx(String[] sqidArr) throws Exception{
		return dao.getPjxmRsxx(sqidArr);
	}
	
	/**
	 * @描述: 判断是否不可兼得
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午05:37:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotbkjd(String xmdm,String xn,String xh){
		return dao.checkIsNotbkjd(xmdm, xn, xh);
	}
	
	/**
	 * @描述: 获取不可兼得项目名称
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午05:38:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getbkjdMc(String xmdm){
		return dao.getbkjdMc(xmdm);
	}
	
	/**
	 * @描述: 批量保存奖项申请
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午06:06:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXmsq(String[] xmdm, XmsqForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String pjxm : xmdm) {
			XmsqForm model = new XmsqForm();
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
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午08:43:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJxsq(XmsqForm model, String userName) throws Exception {
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
	 * @描述: 评奖信息单独查询
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午08:44:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjInfo(String xh,String xn) {
		/*判断学号为空*/
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		return dao.getPjInfo(xh, xn);
	}
	
	/**
	 * @描述: 根据学号查询学生参评基本信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午09:08:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCpbjListByXh(String xh,String xn){
		return dao.getCpbjListByXh(xh,xn);
	}
	
	/**
	 * @描述: 审核记录
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 上午09:55:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSpjlList(String id) {
		return dao.getSpjlList(id);
	}
	
	/**
	 * @描述: 撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 下午04:49:29
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
	 * @描述: 检测指定项目是否有申请审核记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 下午04:42:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistsFlowData(String xmdm) {
		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	}

	/**
	 * @描述: 取学生最近一条申请信息，主要是取外语水平、宿舍电话、担任社会工作职务、个人学习经历、参加科研情况、对设奖单位的认识
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-12 下午07:44:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) {
		return dao.getLatestSqxx(xh);
	}

	/**
	 *  根据年级，专业代码查询年级专业人数.
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-28 19:48
	 * @param nj
	 * @param zydm
	 * @return java.lang.String
	 * @throw
	 */
    public String getNjzyrs(String nj, String zydm) {
		return dao.getNjzyrs(nj,zydm);
    }
    
    /**
     * @描述: 求班级人数
     * @作者： Meng.Wei[工号：1186]
     * @日期： 2018-1-4 上午09:32:58
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param bjdm
     * @param xn
     * @param xq
     * @return
     * String 返回类型 
     * @throws
     */
    public String getBjrs(String bjdm, String xn){
		return dao.getBjrs(bjdm, xn);
	}
    
    /**
     * @描述: 去审核信息
     * @作者： Meng.Wei[工号：1186]
     * @日期： 2018-1-4 上午09:49:04
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param guid
     * @return
     * HashMap<String,String> 返回类型 
     * @throws
     */
    public HashMap<String, String> getSpxxInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getSpxxInfo(guid);
	}
}
