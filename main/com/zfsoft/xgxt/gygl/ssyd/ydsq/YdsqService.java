/**
s * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: 请假申请service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YdsqService extends SuperServiceImpl<YdsqForm, YdsqDao> {
	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID = "-1";
	// 00：退宿操作，01调整操作，02实习留宿（苏州工业职业技术学院），03入住操作
	// 退宿
	public static String _YDLX_TS = "00";
	// 宿舍调整
	public static String _YDLX_SSTZ = "01";
	// 入住
	public static String _YDLX_RZ = "03";

	YdsqDao dao = new YdsqDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public YdsqService() {
		this.setDao(dao);
	}

	/**
	 * 
	 * @描述: 退宿調整原因
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 上午11:47:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTstzyy() {
		return dao.getTstzyy();
	}
	/**
	 * 宿舍調整原因
	 */
	public List<HashMap<String, String>> getTzyy() {
		return dao.getTzyy();
	}

	@Override
	public YdsqForm getModel(YdsqForm t) throws Exception {
		YdsqForm yf = super.getModel(t);
		String ssydlx = yf.getSsydlx();
		String ssydlxmc = "";
		if(ssydlx.equals(_YDLX_TS)){
			ssydlxmc = "退宿";
		}else if(ssydlx.equals(_YDLX_SSTZ)){
			ssydlxmc = "宿舍调整";
		}else if(ssydlx.equals(_YDLX_RZ)){
			ssydlxmc = "入住";
		}
		yf.setSsydlxmc(ssydlxmc);
		
		if(ssydlx.equals(_YDLX_RZ)){//入住原因
			CwglService cwglService = new CwglService();
			List<HashMap<String, String>> rzyyList = cwglService.getRzyyList();
			//设置原因代码对应名称
			for(HashMap<String, String> hm:rzyyList){
				if(hm.get("rzyydm").equals(yf.getTstzyy())){
					yf.setTstzyymc(hm.get("rzyymc"));
				}
			}
		}else if(ssydlx.equals(_YDLX_SSTZ)){//调整原因
			//设置原因代码对应名称
			for(HashMap<String, String> hm:getTzyy()){
				if(hm.get("tzyydm").equals(yf.getTstzyy())){
					yf.setTstzyymc(hm.get("tzyymc"));
				}
			}
		}else{
			//设置原因代码对应名称
			for(HashMap<String, String> hm:getTstzyy()){
				if(hm.get("tsyydm").equals(yf.getTstzyy())){
					yf.setTstzyymc(hm.get("tsyymc"));
				}
			}
		}
		//获得学期名称
		yf.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(yf.getXq()));
		//把实际时间和类型同步到调整类型和时间，用于页面显示
		yf.setTzsssj(yf.getTstzsj());
		yf.setTzssyy(yf.getTstzyy());
		//把实际时间和类型同步到入住类型和时间，用于页面显示
		yf.setRzsssj(yf.getTstzsj());
		yf.setRzssyy(yf.getTstzyy());
		return yf;
	}
	
	public boolean submitRecord(String ydlx,String pkValue,String lcid,String xh) throws Exception {
		
		boolean result =false;
		
		result = shlc.runSubmit(pkValue, lcid,xh,"ydshbase.do","ydsqbase.do");
		
//		if(ydlx.equals(_YDLX_SSTZ)){
//			if(result){
//				//设置代办信息
//				BaseDbcz dbcz=new BaseDbcz();
//				dbcz.setShPath("ydshbase.do");
//				dbcz.setSqPath("ydsqbase.do");
//				dbcz.setGnmkMc("宿舍异动审核");
//				dbcz.setXmmc("宿舍调整");
//				dbcz.sqPush(pkValue, xh, lcid);
//			}
//		}else{
//			if (result) {
//				//设置代办信息
//				BaseDbcz dbcz=new BaseDbcz();
//				dbcz.setShPath("ydshbase.do");
//				dbcz.setSqPath("ydsqbase.do");
//				dbcz.setGnmkMc("宿舍异动审核");
//				dbcz.setXmmc("退宿");
//				dbcz.sqPush(pkValue, xh,lcid);
//			}
//		}
		return result;
	}
	
	
	/**
	 * 
	 * @描述:保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public String save(YdsqForm model,String aType) throws Exception {

		HashMap<String, String> cwxxForXh = getCwxxForXh(model.getXh());
		//调整前床位信息
		model.setTzqlddm(cwxxForXh.get("lddm"));
		model.setTzqldmc(cwxxForXh.get("ldmc"));
		model.setTzqqsh(cwxxForXh.get("qsh"));
		model.setTzqcwh(cwxxForXh.get("cwh"));
		
		//如果是宿舍调整调用调整保存
		if(model.getSsydlx().equals(_YDLX_SSTZ)){
			return saveTzss(model,aType);
		}else if(model.getSsydlx().equals(_YDLX_RZ)){//入住保存
			return saveRzss(model,aType);
		}
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSsydsqid(guid);
		
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		ShlcForm sf = getShlcForm(model.getSsydlx());
		//ShlcForm sf = getShlcForm();
		if (null == sf) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(sf.getSplcid());
		// 有审批流的情况设定初始值
		if(sf.getSplcid()!=null&& !"".equals(sf.getSplcid())){
			if(aType.equals("submit")){
				model.setShzt(Constants.YW_SHZ);//审核中
			}else{
				model.setShzt(Constants.YW_WTJ);//未提交
			}
		}
		boolean result = super.runInsert(model);
		if(aType.equals("submit")){
			if(result){
				result = shlc.runSubmit(guid, sf.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
//				if (result) {
//					//设置代办信息
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setGnmkMc("宿舍异动审核");
//					dbcz.setXmmc("退宿");
//					dbcz.sqPush(model.getSsydsqid(), model.getXh(), sf.getSplcid());
//				}
			}
		}
		return String.valueOf(result);
	}
	/**
	 * 
	 * @描述:调整宿舍保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public String saveTzss(YdsqForm model,String aType) throws Exception {

		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSsydsqid(guid);
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		ShlcForm sf = getShlcForm(model.getSsydlx());
		//ShlcForm sf = getShlcForm();
		if (null == sf) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(sf.getSplcid());
		
		// 有审批流的情况设定初始值
		if(sf.getSplcid()!=null&& !"".equals(sf.getSplcid())){
			if(aType.equals("submit")){
				model.setShzt(Constants.YW_SHZ);//审核中
			}else{
				model.setShzt(Constants.YW_WTJ);//未提交
			}
		}
		//把调整类型和时间同步到数据库字段
		model.setTstzyy(model.getTzssyy());
		model.setTstzsj(model.getTzsssj());
		
		//调整后床位信息
		String ydhcwxx = model.getCwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			// 查询
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			//调整后床位信息
			model.setTzhlddm(cwxxMap.get("lddm"));
			model.setTzhldmc(cwxxMap.get("ldmc"));
			model.setTzhqsh(cwxxMap.get("qsh"));
			model.setTzhcwh(cwxxMap.get("cwh"));
		}
		
		boolean result = super.runInsert(model);
		if(aType.equals("submit")){
			if(result){
				result = shlc.runSubmit(guid, sf.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
//				if(result){
//					//设置代办信息
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setGnmkMc("宿舍异动审核");
//					dbcz.setXmmc("宿舍调整");
//					dbcz.sqPush(model.getSsydsqid(), model.getXh(), sf.getSplcid());
//				}
			}
		}
		return String.valueOf(result);
	}
	/**
	 * 
	 * @描述:入住宿舍保存
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-14 上午10:49:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public String saveRzss(YdsqForm model,String aType) throws Exception {
		
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSsydsqid(guid);
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		ShlcForm sf = getShlcForm(model.getSsydlx());
		//ShlcForm sf = getShlcForm();
		if (null == sf) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(sf.getSplcid());
		
		// 有审批流的情况设定初始值
		if(sf.getSplcid()!=null&& !"".equals(sf.getSplcid())){
			if(aType.equals("submit")){
				model.setShzt(Constants.YW_SHZ);//审核中
			}else{
				model.setShzt(Constants.YW_WTJ);//未提交
			}
		}
		//把入住类型和时间同步到数据库字段
		model.setTstzyy(model.getRzssyy());
		model.setTstzsj(model.getRzsssj());
		
		//入住后床位信息
		String ydhcwxx = model.getRzcwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			// 查询
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			//调整前床位信息
			model.setTzqlddm(cwxxMap.get("lddm"));
			model.setTzqldmc(cwxxMap.get("ldmc"));
			model.setTzqqsh(cwxxMap.get("qsh"));
			model.setTzqcwh(cwxxMap.get("cwh"));

			//河南农业大学
			if("10466".equals(Base.xxdm)){
				
				String sXh = "";
				if("submit".equals(aType)){
					sXh = model.getXh();
				}
				
				boolean result = dao.checkExistForRzcwxx(cwxxMap.get("lddm"),cwxxMap.get("qsh"),cwxxMap.get("cwh"),sXh);
				if(result){
					return "exist_fail";
				}
			}
		}
		
		boolean result = super.runInsert(model);
		if(aType.equals("submit")){
			if(result){
				result = shlc.runSubmit(guid, sf.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
//				if(result){
//					//设置代办信息
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setGnmkMc("宿舍异动审核");
//					dbcz.setXmmc("宿舍入住");
//					dbcz.sqPush(model.getSsydsqid(), model.getXh(), sf.getSplcid());
//				}
			}
		}
		return String.valueOf(result);
	}
	/**
	 * 
	 * @描述:获取当前使用的审核流程
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 下午12:00:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             ShlcForm 返回类型
	 * @throws
	 */
	public ShlcForm getShlcForm(String ssydlx) throws Exception {
		ShlcService ss = new ShlcService();
		return ss.getNowShlc(ssydlx);
	}
	public ShlcForm getShlcForm() throws Exception {
		ShlcService ss = new ShlcService();
		return ss.getNowShlc();
	}
	/**
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:31:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return String[] 返回类型 String数组[0]为成功删除条数 [1]为不能删除提示信息
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public String[] delete(String[] ids) throws Exception {
		return delete(ids, false);
	}
	
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-16 下午07:40:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 *            删除id数组
	 * @param isQjjg
	 *            是否是结果库操作
	 * @return String[] 返回类型
	 * @throws Exception
	 */
	public String[] delete(String[] ids, boolean isQjjg) throws Exception {
		List<String> delId = new ArrayList<String>();// 可删除的id集合

		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
			if (isCanDel(str) || isQjjg) {
				delId.add(str);// 记录删除id
			} else {
				HashMap<String, String> hm = dao.getYdsq(str);
				noDel.append(hm.get("xymc"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("ydlxmc"));
				noDel.append("</br>");
				isHaveNoId = true;
			}
		}
		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;

		if(delId.size()>0){
			
			//刪除审核流相关数据
			for(String id:delId){
				shlc.deleteShjl(id);
			}
			//刪除代办信息
			BaseDbcz dbcz=new BaseDbcz();
			dbcz.setGnmkMc("宿舍异动审核");
			dbcz.remove(delId.toArray(new String[] {}));
		}
		String str = noDel.toString();
		// 去除最后多余逗号
		str = isHaveNoId ? str : _BCZSCID;
		return new String[] { String.valueOf(i), str };
	}

	/**
	 * 
	 * @描述:是否可以删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 下午03:09:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public boolean isCanDel(String id) throws Exception {
		YdsqForm yf = getModel(id);
		// 非为审核状态不允许删除
		if (null != yf && !Constants.YW_WTJ.equals(yf.getShzt()) && !Constants.YW_YTH.equals(yf.getShzt())  ) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @描述:根据学号获取个人床位信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 下午05:29:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 */
	public HashMap<String, String> getCwxxForXh(String xh) {
		return dao.getCwxxForXh(xh);
	}


	/**
	 * 
	 * @描述:根据学号获取个人床位信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 下午05:29:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 */
	public HashMap<String, String> getCwxx(String lddm,String qsh,String cwh) {
		return dao.getCwxx(lddm,qsh,cwh);
	}
	
	/** 
	 * 查询申请结果
	 */
	public HashMap<String, String> getCwxxYdjg(String xh, String ssydsqid) {
		return dao.getCwxxYdjg(xh, ssydsqid);
	}
	
	public boolean updateModel(YdsqForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	/**
	 * 
	 * @描述:请假申请修改
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午04:53:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public String update(YdsqForm model,String aType) throws Exception {
		
		HashMap<String, String> cwxxForXh = getCwxxForXh(model.getXh());
		//调整前床位信息
		model.setTzqlddm(cwxxForXh.get("lddm"));
		model.setTzqldmc(cwxxForXh.get("ldmc"));
		model.setTzqqsh(cwxxForXh.get("qsh"));
		model.setTzqcwh(cwxxForXh.get("cwh"));
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));		
		
		String lcid = "";
		YdsqForm myModel = getModel(model.getSsydsqid());
		
		// 已退回的状态,取老的审核流
		if(Constants.YW_YTH.equals(myModel.getShzt())){
			lcid = myModel.getSplcid();				
		}else{
			//取最新的审核流程
			ShlcForm sf = getShlcForm(model.getSsydlx());
			//ShlcForm sf = getShlcForm();
			if (null == sf) {
				lcid = "";
			}else{
				lcid = sf.getSplcid();
			}
		}

		// 流程ID为空则提示
		if (StringUtils.isNull(lcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(lcid);
		
		if(aType.equals("submit")){
			model.setShzt(Constants.YW_SHZ);//审核中
			
		}else if(Constants.YW_YTH.equals(myModel.getShzt()) && aType.equals("save")){
			// 已退回
			model.setShzt(Constants.YW_YTH);
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		
//		// 有审批流的情况设定初始值
//		if(StringUtils.isNotNull(model.getSplcid())){
//			if(aType.equals("submit")){
//				ShlcForm sf = getShlcForm();
//				if (null == sf) {
//					return MessageKey.SYS_SELECT_SHLC_FAIL;
//				}
//				model.setSplcid(sf.getSplcid());
//				model.setShzt(Constants.YW_SHZ);//审核中
//			}else{
//				model.setShzt(Constants.YW_WTJ);//未提交
//			}
//		}
		boolean result = true;
		if(aType.equals("submit")){
			result = shlc.runSubmit(model.getSsydsqid(), model.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
		}
		//如果是宿舍调整调用调整保存
		if(model.getSsydlx().equals(_YDLX_SSTZ)){
			return String.valueOf(updateTzss(model));
		}else if(model.getSsydlx().equals(_YDLX_RZ)){//入住保存
			return updateRzss(model);
		}
		result = super.runUpdate(model);
		return String.valueOf(result);
	}
	public boolean updateTzss(YdsqForm model) throws Exception {
		//把调整类型和时间同步到数据库字段
		model.setTstzyy(model.getTzssyy());
		model.setTstzsj(model.getTzsssj());

		//调整后床位信息
		String ydhcwxx = model.getCwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			
			// 查询
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			
			//调整后床位信息
			model.setTzhlddm(cwxxMap.get("lddm"));
			model.setTzhldmc(cwxxMap.get("ldmc"));
			model.setTzhqsh(cwxxMap.get("qsh"));
			model.setTzhcwh(cwxxMap.get("cwh"));
		}
		
		return super.runUpdate(model);
	}
	public String updateRzss(YdsqForm model) throws Exception {
		//把调整类型和时间同步到数据库字段
		model.setTstzyy(model.getRzssyy());
		model.setTstzsj(model.getRzsssj());
		
		//调整后床位信息
		String ydhcwxx = model.getRzcwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			
			// 查询
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			
			//调整后床位信息
			model.setTzqlddm(cwxxMap.get("lddm"));
			model.setTzqldmc(cwxxMap.get("ldmc"));
			model.setTzqqsh(cwxxMap.get("qsh"));
			model.setTzqcwh(cwxxMap.get("cwh"));
			
			//河南农业大学
			if("10466".equals(Base.xxdm)){
				boolean result = dao.checkExistForRzcwxx(cwxxMap.get("lddm"),cwxxMap.get("qsh"),cwxxMap.get("cwh"),model.getXh());
				if(result){
					return "exist_fail";
				}
			}
		}
		
		return String.valueOf(super.runUpdate(model));
	}
	/**
	 * 
	 * @描述:
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午03:49:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getShxxAndXjxx(
			List<HashMap<String, String>> list) {
		return list;
	}

	/**
	 * 查询学生个人信息
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}

	/** 
	 * @描述:床位列表（调整）
	 * @作者：qilm
	 * @日期：2013-10-9 下午02:14:55
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCwxxList(YdsqForm myForm, User user) throws Exception{
		return dao.getCwxxList(myForm,user);
	}
	
	/** 
	 * @描述:床位列表（入住）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-14 上午10:49:23
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRzcwxxList(YdsqForm myForm, User user) throws Exception{
		return dao.getRzcwxxList(myForm,user);
	}

	/** 
	 * @描述:是否已申请
	 * @作者：qilm
	 * @日期：2013-10-22 下午02:08:47
	 * @param xh
	 * @return
	 * boolean 返回true:有已申请未审核完了的 false:没有
	 * @throws 
	 */
	public boolean getSfysq(String xh) {
		
		return dao.getSfysq(xh);
	}
	
	
	public boolean getInShz(String qsmc) {
		
		return dao.getInShz(qsmc);
	}
	
	public boolean getSfjl(String qsmc) {
		
		return dao.getSfjl(qsmc);
	}
	
	/**
	 * 
	 * @描述:获取宿舍异动申请信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-5 下午02:13:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ssydsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getYdsqxx(String ssydsqid){
		return dao.getYdsqxx(ssydsqid);
		
	}
	
	/**
	 * 获取
	 * @param paraList
	 * @return
	 */
	public List<HashMap<String, Object>> setData(List<HashMap<String, String>> list){
		List<HashMap<String, Object>> totalList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> secondList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, String>> lastList = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, Object> firstMap = new HashMap<String, Object>(); //第一层选项
		HashMap<String, Object> secondMap = new HashMap<String, Object>(); //第二层选项
		
		HashMap firstM = new HashMap();
		HashMap secondM = new HashMap();
		
		for (HashMap<String, String> hashMap : list) {
			secondM.put(hashMap.get("qsh"), hashMap.get("qsh"));
			firstM.put(hashMap.get("lddm"), hashMap.get("lddm"));
			
			//判断最后一层是否需要初始化
			if(secondM.size()!=1){
				secondMap = new HashMap<String, Object>();
				lastList = new ArrayList<HashMap<String,String>>();
				secondM.clear();
			}
			
			//判断第二层是否需要初始化
			if(firstM.size()!=1){
				firstMap = new HashMap<String, Object>();
				secondList = new ArrayList<HashMap<String,Object>>();
				firstM.clear();
			}
			
			//最后一层 选项
			HashMap<String, String> lastMap = new HashMap<String, String>();
			lastMap.put("value", hashMap.get("cwh"));
			String qshText = StringUtils.isNotNull(hashMap.get("xm")) ? hashMap.get("cwh")+"号床("+hashMap.get("xm")+")" : hashMap.get("cwh")+"号床";
			lastMap.put("text", qshText);
			lastMap.put("xh", hashMap.get("xh"));
			lastList.add(lastMap);
			
			//第二层
			secondMap.put("value", hashMap.get("qsh"));	
			secondMap.put("text", hashMap.get("qsh")+"室");
			secondMap.put("children", lastList);
			
			if(!secondList.contains(secondMap)){
				secondList.add(secondMap);
			}
			
			//第一层
			firstMap.put("value", hashMap.get("lddm"));	
			firstMap.put("text", hashMap.get("ldmc"));
			firstMap.put("children", secondList);
			
			if(!totalList.contains(firstMap)){
				totalList.add(firstMap);
			}
			
		}
		
		return totalList;
	}
	
	/**
	 * 入住异动类型寝室信息List
	 * @param t
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getRzYdLxQsxxList(YdsqForm t, User user){
		return dao.getRzYdLxQsxxList(t, user);
	}
	
	/**
	 * @描述: 宿舍床位调整
	 * @作者：qilm
	 * @日期：2013-10-9 下午02:14:55
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws  Exception 
	 */
	public List<HashMap<String, String>> getTCwxxList(YdsqForm t, User user) throws Exception {
		return dao.getTCwxxList(t, user);
	}
	
}
