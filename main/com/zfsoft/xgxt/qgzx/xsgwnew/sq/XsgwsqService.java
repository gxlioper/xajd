/**
 * @部门:学工产品事业部
 * @日期：2016-6-1 上午11:14:56 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 学生岗位申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-1 上午11:14:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgwsqService extends SuperServiceImpl<XsgwsqForm, XsgwsqDao>{
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	@Override
	public XsgwsqForm getModel(XsgwsqForm t) throws Exception {
		return dao.getModel(t);	
	}
	
	/**
	 * 
	 * @描述: 勤工明细
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午09:53:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQgmxList(String id){
		
		List<HashMap<String,String>> qgmxList = dao.getQgmxList(id);
		
		return qgmxList;
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午10:03:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSq(XsgwsqForm model) throws Exception {
		
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		
		//判断学号和岗位ID是否存在，如果存在就不继续进行。备注：勤工存在退岗问题，这里咱不考虑，后续尽快重构
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		return insertResult;
		
	}
	
	public String saveSq(XsgwsqForm model, String type) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setXn(Base.currXn);
		model.setShzt(Constants.YW_WTJ);
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if (splc != null && !"".equals(splc)) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		}
		boolean insertResult=false;
		String guid = UniqID.getInstance().getUniqIDHash();
		if(!StringUtil.isNull(model.getSqbh())){
			guid=model.getSqbh();
			insertResult = super.runUpdate(model);
		}else{
			model.setSqbh(guid);
			insertResult = super.runInsert(model);
		}
		if (insertResult) {
			insertResult = shlc.runSubmit(guid, splc, model.getXh(),
					"qgzx_xsgwshnew_sh.do",
					"qgzx_xsgwsqnew_sq.do");
		}
		
		return String.valueOf(insertResult);
	}
	
	/**
	 * 
	 * @描述: 保存勤工明细
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午10:24:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param mxrq
	 * @param mxxmList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQgmx(XsgwsqForm model,String[] mxrq,List<String[]> mxxmList) throws Exception{
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 ; i < mxrq.length ; i++){
			String[] param = new String[]{model.getXh(),mxrq[i],StringUtils.joinArr(mxxmList.get(i)),i+""};
			params.add(param);
		}
		
		dao.delQgmx(model.getXh());
		return dao.saveQgmx(params);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 下午03:41:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkValue
	 * @param lcid
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitRecord(String pkValue, String lcid, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(pkValue, lcid, xh,
				"qgzx_xsgwshnew_sh.do",
				"qgzx_xsgwsqnew_sq.do");
		return result;
	}
	
	public boolean updateModel(XsgwsqForm model) throws Exception {		
		return super.runUpdate(model);
	}
	
	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public List<HashMap<String, String>> getGwPageList(XsgwsqForm model, User user) throws Exception {
		return dao.getGwPageList(model, user);
	}
	
	public int getXszggwsl(XsgwsqForm model) throws SQLException {
		return dao.getXszggwsl(model.getGwdm(), model.getXh());
	}
	
	public int getXssqsl(XsgwsqForm model) throws SQLException {
		return dao.getXssqsl(model.getGwdm(), model.getXh());
	}
	
	public HashMap<String, String> getCsszb(){
		return dao.getCsszb();
	}
	
	public boolean isCheck(String splc, String yzgw, String shgw) {
		if (StringUtils.isNull(splc) || StringUtils.isNull(yzgw)
				|| StringUtils.isNull(shgw)) {
			return false;
		}
		//新审核流程 审核岗位更改为 -1 时 为退回给申请人
		if("-1".equals(shgw)){
			return false;
		}
		String yzxh = dao.getGwSpXh(splc, yzgw);
		String shxh = dao.getGwSpXh(splc, shgw);
		if (Integer.parseInt(yzxh) <= Integer.parseInt(shxh)) {
			return true;
		}
		return false;
	}
	
	public String yzgwxx(String gwdm, String xh) throws Exception {
		String message = "";
		HashMap<String, String> gwxx = dao.getGwxx(gwdm);
		String xqrs = gwxx.get("xqrs");// 需求总人数
		String knsrs = gwxx.get("knsrs");// 需求困难生数
		String gwrs = gwxx.get("gwrs");// 在岗总人数
		String zgknss = gwxx.get("zgknss");// 在岗困难生 数
		int xqfkns = Integer.parseInt(xqrs) - Integer.parseInt(knsrs);
		int zgfkns = Integer.parseInt(gwrs) - Integer.parseInt(zgknss);

		int iskns = dao.isKns(gwdm, xh);
/*		if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs)) {
			message = "岗位人员已满";
		} else */
		if (xqfkns - zgfkns <= 0 && iskns == 0){
			message = "该岗位的非困难生人数已达到上限，请申请其他岗位。"; 
		}else if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs) && iskns != 0) {
			message = "岗位人数已达到上限，请申请其他岗位。";
		} else {
			message = "true";
		}
		return message;
	}
	
	public String yzjb(String xh,String splc,String gwdm, String yzgw, String shgw,boolean isTj)
			throws SQLException {
		String message = "true";
		// 当前用户非验证岗位 则不进行验证。 如果是提交使用则进行验证
		if (isCheck(splc, yzgw, shgw)||isTj) {
			if(gwdm == null) {
				return "LastLc";
			}
			HashMap<String, String> gwxx = dao.getGwxx(gwdm);
			String sfsgwsqsxz = gwxx.get("sfsgwsqsxz");
			String xqrs = gwxx.get("xqrs");// 需求总人数
			Integer gwshtgrsI=dao.getGwShtgRs(splc,shgw,gwdm);//岗位审核通过人数
			if(gwshtgrsI>=Integer.parseInt(xqrs)&&!"0".equals(xqrs)){
				message = "已超过当前审核岗位需求人数！";
			}else{
				HashMap<String, String> map = getCsszb();
				String xsgws = map.get("xsgws");//学生可获得岗位数
				Integer xshqGws=dao.getTgRs(splc,xh,shgw);//学生获取岗位数
				if ("1".equals(sfsgwsqsxz) && xshqGws >= Integer.parseInt(xsgws)&&!"0".equals(xsgws)) {
					message = "此学生已经有" + xshqGws + "个岗位，超过学生最大岗位数";
				}
			}
		}
		return message;
	}
	
	public boolean isHaveRecordForSq(String xh, String xn) {
		return dao.isHaveRecordForSq(xh, xn);
	}
}
