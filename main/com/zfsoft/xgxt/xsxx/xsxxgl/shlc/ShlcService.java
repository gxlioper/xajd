/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import java.util.HashMap;
import java.util.List;

import xsgzgl.xsxx.general.jcsz.XsxxJcszService;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XgsqModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XxxgService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称:学生信息
 * @类功能描述: 修改审核流程
 * @作者： ligl
 * @时间： 2013-12-11 下午01:36:08
 * @版本： V1.0
 * @修改记录:
 */
public class ShlcService extends SuperServiceImpl<ShlcModel, ShlcDao> {
	ShlcDao dao = new ShlcDao();
	private ShlcInterface shlc = new CommShlcImpl();
	//private TranscationManager transcationManager = new TranscationManager();

	public ShlcService() {
		this.setDao(dao);
	}

	/**
	 * @throws Exception 
	 * 
	 * @描述:保存修改审核
	 * @作者：ligl
	 * @日期：2013-12-11 下午01:36:22
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveXgsh(ShxxModel model) throws Exception{	
		boolean result = true;
		String shlid = null;
		HashMap<String, String> splMap = new XsxxJcszService().getOnesCssz();
		shlid = splMap.get("shlid");
		// 审核流程ID
		model.setShlc(shlid);
		// 审核状态
		model.setShzt(model.getShjg());
		model.setTzlj("xsxx_xsxxxg_xgsh.do");
		model.setTzljsq("xsxx_xsxxxg_xgsq.do");
		String shzt = shlc.runAuditing(model);
		if (shzt != null) {
			XxxgService xxxgService = new XxxgService();
			XgsqModel xgsqModel = new XgsqModel();
			xgsqModel.setSqid(model.getYwid());
			xgsqModel.setShjg(shzt);
			result = xxxgService.updateXgsqZt(xgsqModel);
			
			if(!result){
				throw new SystemException(MessageKey.SYS_AUD_ERROR);
			}
			
			if (shzt.equals(Constants.SH_TG)) {
				// 修改学生信息
				String sqid = model.getYwid();
				result = new XsxxglService().updateRecordForStu(sqid, false);
			}
			if(!result){
				throw new SystemException(MessageKey.SYS_AUD_ERROR);
			}
		}
	 	
		return result;
	}
	public boolean savePlxgsh(ShxxModel model, String dataJson) throws Exception
			 {
		boolean isTh =false;
		boolean result = true;
		String shlid = null;
		if("3".equals(model.getShjg())&&"3".equals(model.getThgw())){//退回上一级
			isTh=true;
		}
		HashMap<String, String> splMap = new XsxxJcszService().getOnesCssz();
		shlid = splMap.get("shlid");
		// 审核流程ID
		model.setShlc(shlid);
		// 审核状态
		model.setShzt(model.getShjg());
		model.setTzlj("xsxx_xsxxxg_xgsh.do");
		model.setTzljsq("xsxx_xsxxxg_xgsq.do");
		
		XxxgService xxxgService = new XxxgService();
		XgsqModel xgsqModel = new XgsqModel();
		dataJson = "{data:" + dataJson + "}";
		List list = JsonUtil.jsonToList(dataJson);
		String gwid = null;
		String ywid = null;
		String xh = null;
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
			gwid = (String) bean.get("gwid");
			if(isTh){//查询退回至上级的gwid
				String spgw = new com.zfsoft.xgxt.comm.shlc.dao.ShlcDao().getBeforeGwid(model.getShlc(), gwid);
				model.setThgw(spgw);
			}
			ywid = (String) bean.get("ywid");
			xh = (String) bean.get("xh");
			model.setGwid(gwid);
			model.setYwid(ywid);
			model.setSqrid(xh);
			
			String shzt = shlc.runAuditing(model);
			if (shzt != null) {
				xgsqModel.setSqid(model.getYwid());
				xgsqModel.setShjg(shzt);
				result = xxxgService.updateXgsqZt(xgsqModel);
				if(!result){
					throw new SystemException(MessageKey.SYS_AUD_ERROR);
				}
				if (shzt.equals(Constants.SH_TG)) {
					// 修改学生信息
				
					String sqid = model.getYwid();
					result = new XsxxglService()
							.updateRecordForStu(sqid, false);
					
				}
				if(!result){
					throw new SystemException(MessageKey.SYS_AUD_ERROR);
				}
			}
			
		}
		return result;
	}

}
