package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;

public class XjydsqService extends SuperServiceImpl<XjydsqForm, XjydsqDao> {

	private XjydsqDao dao = new XjydsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public XjydsqService(){
		super.setDao(dao);
	}

	
	/**
	 * @描述:批量提交/取消提交学籍异动申请
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-30 上午11:56:05
	 * @param values
	 * @param tjzt
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public boolean pltjXjydsq(String values, String tjzt) throws Exception {
		
		boolean bolFlg = true;
		
		// 取消提交
		if(Constants.YW_WTJ.equals(tjzt)){
			bolFlg = dao.pltjXjydsq(values, tjzt);
			// 提交
		}else{
			
			// 退回提交则取最新的审核流程ID
			XjydsqForm model = dao.getModel(values);
			String shlcidnew = "";
			
			// 不是退回的记录，重新取审核流程ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				
				// 取最新的审核流程ID
				XjydForm xjydlb = new XjydForm();
				XjydService xjydlbService = new XjydService();
				xjydlb.setXjlbdm(model.getYdlbdm());
				xjydlb = xjydlbService.getModelShpz(xjydlb);
				shlcidnew = xjydlb.getShlcid();
				//已退回的记录取老的审核流程ID
			}else{
				shlcidnew = model.getSplcid();
			}
			
			bolFlg = dao.pltjXjydsq(values, tjzt, shlcidnew);
		}
		
		return bolFlg;
	}

	/**
	 * 
	 * @描述: 是否可申请判断（若学生有未结束的学籍异动申请则不能申请）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-12 下午02:56:41
	 * @param myForm true:有记录
	 * @return
	 * boolean 返回类型 true:有记录
	 * @throws
	 */
	public boolean sfKsq(XjydsqForm myForm){
		
		return dao.sfKsq(myForm.getXh());
	}
	
	/**
	 * 插入申请表
	 */
	@Override
	public boolean runInsert(XjydsqForm myForm)throws Exception {
		boolean bolFlg = false;

		String sqid = UniqID.getInstance().getUniqIDHash();
		myForm.setXjydsqid(sqid);
		
		bolFlg = dao.runInsert(myForm);
		
		// 已提交对应追加审核流
		if(myForm.getShzt().equals(Constants.YW_SHZ) && bolFlg){
			bolFlg = shlc.runSubmit(sqid, myForm.getSplcid(),myForm.getXh(),"xjyd_xjydsh.do","xjyd_xjydsq.do");
		}
		
		return bolFlg;
	}
	
	/**
	 * 更新申请表
	 */
	@Override
	public boolean runUpdate(XjydsqForm myForm)throws Exception {
		boolean bolFlg = false;
		
		// 申请ID
		String sqid = myForm.getXjydsqid();		
		bolFlg = dao.runUpdate(myForm);
		
		// 已提交对应追加审核流
		if(myForm.getShzt().equals(Constants.YW_SHZ) && bolFlg){
			if(bolFlg){
				// 删除原审核流
				shlc.deleteShjl(sqid);
				// 追加审核流
				bolFlg = shlc.runSubmit(sqid, myForm.getSplcid(),myForm.getXh(),"xjyd_xjydsh.do","xjyd_xjydsq.do");
			}
		}
		return bolFlg;
	}
	
	/**
	 * 删除申请表
	 */
	@Override
	public int runDelete(String[] values)throws Exception {
		
		// 删除记录
		int count = dao.runDelete(values);
		
		if (count >0){
			// 删除审核流
			for(String sqid: values){
				// 删除原审核流
				shlc.deleteShjl(sqid);
			}
		}
		return count;
	}

	/**
	 * 
	 * @描述:获取map
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-31 下午02:08:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String keyValue)throws Exception{
		return dao.getModelMap(keyValue);
	}
	
	/**
	 * @描述:批量提交/取消提交学籍异动申请
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-2 下午02:29:46
	 * @param values
	 * @param shzt x
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitRecord(String values, String shzt) throws Exception {

		boolean result =false;
		// 取消提交
		if(Constants.YW_WTJ.equals(shzt)){
			// 申请人的撤销
			for (String pkValue: values.split(",")){
				
				XjydsqForm model = dao.getModel(pkValue);
				result = shlc.firstStepCancle(pkValue, model.getSplcid());
			}
		}else if(Constants.YW_SHZ.equals(shzt)){
			// 提交
			String[] v = values.split(",");
			for (String pkValue: v){
				XjydsqForm model = dao.getModel(pkValue);
				
				// 已退回的记录取老的审核流程ID
				if(!Constants.YW_YTH.equals(model.getShzt())){
					XjydForm xjydlb = new XjydForm();
					XjydService xjydlbService = new XjydService();
					xjydlb.setXjlbdm(model.getYdlbdm());
					xjydlb = xjydlbService.getModelShpz(xjydlb);
					
					// 取最新的审核流程ID
					model.setSplcid(xjydlb.getShlcid());
				}
				result = shlc.runSubmit(pkValue, model.getSplcid(),model.getXh(),"xjyd_xjydsh.do","xjyd_xjydsq.do");
			}
		}
		return result;
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
	public boolean checkSfktj(XjydsqForm model) {

		String num = dao.checkSfktj(model.getXjlbdm());
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * 
	 * @描述:获取异动原因列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-12 上午11:07:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdyyList() {
		return dao.getYdyyList();
	}
	
	/**
	 * 
	 * @描述:获取来源去向学校列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-12 上午11:07:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLyqxxxList() {	
		return dao.getLyqxxxList();
	}
	
	/**
	 * 
	 * @描述: 当前状态列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-22 下午02:37:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDqztList() {
		return dao.getDqztList();		
	}
	
}
