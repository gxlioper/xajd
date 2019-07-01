/**
s * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import xgxt.action.Base;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @作者： 张昌路[工号:982]
 * @时间： 2013-10-25 上午10:40:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DtxxsqService extends SuperServiceImpl<DtxxsqForm, DtxxsqDao> {
	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID="-1";
	/**
	 * 无当前阶段信息
	 */
	public static String _WDQJDXX="-1";
	DtxxsqDao dao = new DtxxsqDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";

	public DtxxsqService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @描述:保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 上午10:41:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 */
	public boolean save(DtxxsqForm model) throws Exception {
		// yyyy-mm-dd HH24:MI:SS
		if(StringUtils.isNull(model.getSqsj())){
			model.setSqsj(GetTime.getNowTime4());
		}
		//获取审批流程并设置
		ShlcpzService ss=new ShlcpzService();
		ShlcpzForm sf=ss.getModel(model.getJddm());
		model.setSplc(sf.getSplc());
		//model.setShzt(Constants.YW_WTJ);
		if(model.getType().equals("submit")){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		model.setDtxxsqid(UniqID.getInstance().getUniqIDHash());
		boolean result = runInsert(model);
		if(result&&model.getType().equals("submit")){
			// 保存审核流程                                     
			result = shlc.runSubmit(model.getDtxxsqid(),model.getSplc(),model.getXh(),"dtxxshbase.do","dtxxsqbase.do");
		}
		return result;
	}
	
	public boolean submitDtxxsq(DtxxsqForm model) throws Exception {
		
		model.setShzt(Constants.YW_SHZ);
		DtxxsqForm modelGet = dao.getModel(model.getDtxxsqid());

		// 已退回的记录取老的审核流程ID;非已退回记录则再去取审核流程
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){

			ShlcpzService service = new ShlcpzService();
			ShlcpzForm shlcpz = new ShlcpzForm();
			shlcpz.setJddm(modelGet.getJddm());
			shlcpz = service.getModel(shlcpz);
			if(shlcpz!=null){
				model.setSplc(shlcpz.getSplc());
			}
		}else{
			model.setSplc(modelGet.getSplc());
		}
		
		boolean resultDtxxsq = dao.updateDtxxsq(model);
		boolean result = false;
		if(resultDtxxsq){
		// 获取审批流程
		/*	ShlcpzForm sf=ss.getModel(model.getJddm());
			model.setSplc(sf.getSplc());*/
		//String splc = dao.getShlcID(model.getRcxwlbdldm());
		//保存审核流程
		result = shlc.runSubmit(model.getDtxxsqid(), model.getSplc(),model.getXh(),"dtxxshbase.do","dtxxsqbase.do");
		}
		return result;
	}
	
	public int rundel(String[] ids) throws Exception{
		int i=0;
		for(String str:ids){
			//删除此数据
			i+=runDelete(new String[]{str});
			//删除对应代办信息
			BaseDbcz dbcz=new BaseDbcz("dtxxsqbase.do","dtxxshbase.do");
			dbcz.setGnmkMc("党团信息申请");
			dbcz.setXmmc("党团信息管理");
			dbcz.remove(str);
		}
		return i;
	}
	/**
	 * 
	 * @描述:获取可申请阶段
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 下午04:49:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getSqJdList(String xh, String jddmGet) throws Exception {
		List<HashMap<String, String>> listnew=new ArrayList<HashMap<String,String>>();
		String dqjddm=_WDQJDXX;
		if(StringUtils.isNotNull(xh)){
			dqjddm=dao.getDqjddm(xh);
		}
		List<HashMap<String, String>> list=dao.getSqJdList(jddmGet);
		listnew.addAll(list);
		for(HashMap<String, String> hm:list){
			String jddm=hm.get("jddm");
			//如果不是当前阶段后的阶段则删除
			if(Integer.parseInt(jddm)<=Integer.parseInt(dqjddm)){
				listnew.remove(hm);
			}else if("13871".equals(Base.xxdm)){
				listnew.clear();
				listnew.add(hm);
				return listnew;
			}
		}
		return listnew;
	}
	
	@Override
	public DtxxsqForm getModel(DtxxsqForm t) throws Exception {
		t = super.getModel(t);
		HashMap<String, String> otherP=getAllProperty(t.getDtxxsqid());
		t.setJdmc(otherP.get("jdmc"));
		return t;
	}
	/**
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 下午07:40:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids 删除id数组
	 * @param isQjjg 是否是结果库操作
	 * @return String[] 返回类型 
	 * @throws Exception
	 */
	public String[] delete(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			DtxxsqForm  df=getModel(str);
			if(isCanDel(df)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=getAllProperty(str);
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("sqsj"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("jdmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?rundel(delId.toArray(new String[]{})):0;
		
		if(delId.size()>0){
			
			//刪除审核流相关数据
			for(String id:delId){
				shlc.deleteShjl(id);
			}
			//刪除代办信息
			BaseDbcz dbcz=new BaseDbcz();
			dbcz.setGnmkMc("党团信息申请");
			dbcz.remove(delId.toArray(new String[] {}));
		}
		
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @描述:获取所有相关属性（不仅仅只是form里面的值）
	 * 		 根据页面list查询sql获取
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-24 下午02:05:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 */
	public HashMap<String, String> getAllProperty(String id) throws Exception{
		HashMap<String, String> property=null;
		DtxxsqForm hm=new DtxxsqForm();
		hm.setDtxxsqid(id);
		List<HashMap<String, String>> list=getAllList(hm);
		/*	for(HashMap<String, String> hmap:list){
			String shzt=hmap.get("shzt");
			//返回当前正在申请或审核中的数据（只有一条数据 才为正常数据）
			if(!Constants.TG.equals(shzt)){
				property=hmap;
			}
		}*/
		if(null!=list&&list.size()==1){//按主键检索 只有一条数据 才为正常数据
			property=list.get(0);
		}
		return property;
	}
	/**
	 * 
	 * @描述:修改
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 下午04:53:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean update(DtxxsqForm model) throws Exception {
		
		//判断是否变更了阶段
		String sqid = model.getDtxxsqid();
		DtxxsqForm old = dao.getModel(sqid);	

		ShlcpzService ss=new ShlcpzService();
		ShlcpzForm sf=ss.getModel(model.getJddm());
		model.setSplc(sf.getSplc());
		model.setSqsj(GetTime.getNowTime4());
		
		//设置初始审核状态值
		if(SUBMIT.equalsIgnoreCase(model.getType())){
			model.setShzt(Constants.SHZ);
		}
		boolean result =runUpdate(model);
		/*if(result && !old.getJddm().equals(model.getJddm())){
			//刪除原审核流相关数据
			shlc.deleteShjl(sqid);
			//刪除原代办信息
			BaseDbcz dbcz=new BaseDbcz();
			dbcz.setGnmkMc("党团信息申请");
			dbcz.remove(new String[] {sqid});

			// 保存审核流程
			result = shlc.runSubmit(sqid, model.getSplc(),model.getXh(),"dtxxsqbase.do","dtxxshbase.do");
//			//设置代办信息
//			dbcz=new BaseDbcz("dtxxsqbase.do","dtxxshbase.do");
//			dbcz.setGnmkMc("党团信息申请");
//			dbcz.setXmmc("党团信息管理");
//			dbcz.sqPush(sqid,model.getXh(),model.getSplc());
		}*/
		
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			//刪除原审核流相关数据
		
			// 保存审核流程
			result = shlc.runSubmit(sqid, model.getSplc(),model.getXh(),"dtxxsqbase.do","dtxxshbase.do");
			if (result) {
				//设置代办信息
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("党团信息申请");
				dbcz.remove(new String[]{sqid});
				dbcz.setShPath("dtxxsqbase.do");
				dbcz.setSqPath("dtxxshbase.do");
				dbcz.setXmmc("党团信息管理");
				dbcz.sqPush(sqid, model.getXh(), model.getSplc());
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @描述:是否可以删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-24 下午01:46:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * boolean 返回类型 
	 */
	public boolean isCanDel(DtxxsqForm qf) {
		//如果未审核则可以删除
		if(Constants.YW_WTJ.equals(qf.getShzt())||Constants.SH_TH.equals(qf.getShzt())){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @描述: 是否可以增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 下午05:39:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 */
	public boolean isCanAdd(DtxxsqForm qf) throws Exception{
		//如果申请id不为空则为修改 直接返回true
		if(!StringUtils.isNull(qf.getDtxxsqid())){return true;};
		String shzt=null;
		List<HashMap<String, String>> list=this.getAllList(qf);
		for(HashMap<String, String> hm:list){
			shzt=hm.get("shzt");
			//如果数据不是已经审核流程完成的（通过或不通过）
			if(!shzt.equals(Constants.YW_BTG)&&!shzt.equals(Constants.YW_TG)){
				return false;
			}
		}
		return true;
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
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-3 上午09:18:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * 
	 * @描述:TODO(更新党团信息申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-9 上午10:03:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateDtxxsq(DtxxsqForm model) throws Exception {
		
		boolean resultDtxxsq = dao.updateDtxxsq(model);
		
		return resultDtxxsq;
		
	}
	
	/** 
	 * @描述:验证是否可提交
	 * @作者：qlm
	 * @日期：2014-5-26 上午11:18:56
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkSfktj(DtxxsqForm model) {

		String num = dao.checkSfktj(model.getJddm());
		return Integer.valueOf(num) > 0;
	}
	
	public String getYbdyDm(){
		return dao.getYbdyDm();
	}
	
	/**
	 * 
	 * @描述: 获取基本信息，flag(sq:申请;jg:结果)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午10:00:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param flag
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxxMap(String id,String flag){
		return dao.getXsjbxxMap(id, flag);
	}
	
	/**
	 * 
	 * @描述: 获取评奖结果List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午10:17:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjjgList(String xh){
		return dao.getPjjgList(xh);
	}
	
	/**
	 * 
	 * @描述: 获取违纪处分List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午10:40:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjcfList(String xh){
		return dao.getWjcfList(xh);
	}
	/**
	 * 
	 * @描述: 获取学生职务
	 * @作者：姜舟[工号：1206]
	 * @日期：2017-9-18 上午10:40:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZwmc(String xh){
		return dao.getZwmc(xh);
	}
	public HashMap<String,String> getBjgkc(String xh){
		return dao.getBjgkc(xh);
	}
	public List<HashMap<String,String>> getZcfListByXh(String xh,String xn,String xq){
		return dao.getZcfListByXh(xh,xn,xq);
	}

	//由出生日期获得年龄
	public  int getAge(String s) throws Exception {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

		Date birthDay=simpleDateFormat.parse(s);


		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay) || StringUtils.isNull(s)) {
			return 0;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	public String getJdmc(String jddm) {
		return dao.getJdmc(jddm);
	}
}
