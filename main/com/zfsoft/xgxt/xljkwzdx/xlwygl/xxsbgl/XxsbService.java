/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 下午03:23:23 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xlsbjggl.XlsbjgForm;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xlsbjggl.XlsbjgService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl.XxsbjgDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 下午03:23:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbService extends SuperServiceImpl<XxsbForm, XxsbDao> {

	/**
	 * @描述 审核流操作接口
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	private XlsbjgService xlsbjgService = new XlsbjgService();
	
	public static final String PATH_SH = "xljk_xlwygl_xxsbshgl.do";
	
	public static final String PATH_SQ = "xljk_xlwygl_xxsbgl.do";
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存信息上报
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-27 下午03:50:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXxsb(XxsbForm model) throws Exception{
		String type = model.getType();
		if(StringUtils.equals("save", type)){
			model.setSplcid("");
		}
		if(StringUtils.equals("update", type)){
			return runUpdate(model);
		}else if(StringUtils.equals("saveSubmit", type)){
			runUpdate(model);
			return submitXxsb(model);
		}
		boolean isSuccess = false;
		String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setShzt(Constants.YW_WTJ);//设置审核状态 未提交
		model.setSbsqid(uuid);
		isSuccess = runInsert(model); //保存申请
		if(StringUtils.equals("submit", type) && isSuccess){
			if(isSuccess){
				isSuccess = shlc.runSubmit(model.getSbsqid(), model.getSplcid(), model.getXh(), PATH_SH, PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					isSuccess = runUpdate(model);
				}
			}
		}
		return isSuccess;
	}
	
	/**
	 * 
	 * @描述:获取上报信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-30 下午01:32:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sbsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String ,  String> getModelMap(String sbsqid){
		return dao.getModelMap(sbsqid);
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 下午02:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitXxsb(XxsbForm form) throws Exception{
		XxsbForm model = form;
		boolean isSuccess = false;
		if(model != null && StringUtils.isNotBlank(model.getSbsqid())){
			isSuccess = shlc.runSubmit(model.getSbsqid(), model.getSplcid(), model.getXh(), PATH_SH, PATH_SQ);
			if(isSuccess){
				model.setShzt(Constants.YW_SHZ);
				isSuccess = runUpdate(model);
			}
		}
		return isSuccess;
	}
	
	
	public XxsbService(){
		super.setDao(new XxsbDao());
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 下午03:34:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(String sbsqid) throws Exception{
		boolean isSuccess = false;
		XxsbForm model = null;
		if(sbsqid != null){
			model = getModel(sbsqid);
		}
		
		if(model != null){
			isSuccess = shlc.firstStepCancle(model.getSbsqid(), model.getSplcid());
		}
		
		if(isSuccess){
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getSbsqid()))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			isSuccess = runUpdate(model);
		}
		
		return isSuccess;
	}
	
	
	
	public List<HashMap<String, String>> getSHPageList(XxsbForm t, User user)
		throws Exception {
		
		return dao.getSHPageList(t, user);
	}
	
	
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @日期：2014-4-28 下午01:49:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSh(XxsbForm model, User user) throws Exception {
		
		XxsbForm dataModel = dao.getModel(model.getSbsqid());
		
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getSbsqid());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj(PATH_SH);
		shxxModel.setTzljsq(PATH_SQ);
		
		boolean reuslt = false;
		try {
			String zhzt  = shlc.runAuditing(shxxModel); //审核操作{插入一条数据到审核表中}
			dataModel.setShzt(zhzt);//获取审核状态标志，并更新Model
			reuslt = dao.runUpdate(dataModel);//更新申请表{变更申请表中审核状态信息}
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //如果审核通过 插入一条数据到结果库
				XlsbjgForm xlsbjgModel = new XlsbjgForm();
				xlsbjgModel.setXh(dataModel.getXh());
				xlsbjgModel.setSblx(dataModel.getSblx());
				xlsbjgModel.setSbsj(dataModel.getSbsj());
				xlsbjgModel.setSbzbid(dataModel.getSbzbid());
				xlsbjgModel.setZtqk(dataModel.getZtqk());
				xlsbjgModel.setXlxsxxqk(dataModel.getXlxsxxqk());
				xlsbjgModel.setBz(dataModel.getBz());
				xlsbjgModel.setSbsqid(dataModel.getSbsqid());
				xlsbjgModel.setSjly("1");
				xlsbjgModel.setXn(dataModel.getXn());
				xlsbjgModel.setXq(dataModel.getXq());
				xlsbjgModel.setSbjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
				reuslt = xlsbjgService.runInsert(xlsbjgModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reuslt;
	}
	
	
	/**
	 * @throws Exception  
	 * @描述:批量保存审核
	 * @作者：zhangxiaobin [工号：1036]
	 * @日期：2014-4-28 上午11:54:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(XxsbForm t, User user) throws Exception {
		
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			XxsbForm model = new XxsbForm();
			model.setSbsqid(ids[i]);
			model.setSplcid(splcs[i]);
			model.setXtgwid(gwid[i]);
			model.setXh(xhs[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			
			boolean isSuccess = saveSh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
	
	public HashMap<String,String> getXnXqmc(String zbid){
		return dao.getXnXqmc(zbid);	
	}
	
	public boolean cancel(XxsbForm myForm) throws Exception {
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSbsqid());
		// 删除结果库中的数据
		XxsbjgDao jgDao = new XxsbjgDao();
		result = jgDao.deleteBySqid(myForm.getSbsqid());
		
		return result;	
	}
	
}
