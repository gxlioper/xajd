/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:25:12 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgForm;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明审核-SERVICE类
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 上午10:25:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdshService extends SuperServiceImpl<CdshForm,CdshDao> {
	/**
	 * @描述 场地信息服务接口
	 */
	private CdxxwhService cdxxwhService = new CdxxwhService();
	
	private ShlcInterface shlc = new CommShlcImpl();

	public CdshService(){
		setDao(new CdshDao());
	}


	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 下午01:49:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSh(CdshForm model, User user) throws Exception {
		
		CdshForm dataModel = dao.getModel(model.getSqid());
		
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getSqid());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj(CdshAction.PATH);
		shxxModel.setTzljsq(CdsqAction.PATH);
		
		boolean reuslt = false;
		try {
		
			String zhzt  = shlc.runAuditing(shxxModel); //审核操作{插入一条数据到审核表中}
			
			dataModel.setShzt(zhzt);//获取审核状态标志，并更新Model
			
			reuslt = dao.runUpdate(dataModel);//更新申请表{变更申请表中审核状态信息}
			
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //如果审核通过 插入一条数据到结果库
				CdjgForm cdjgModel = new CdjgForm();
				cdjgModel.setXh(dataModel.getXh());
				cdjgModel.setCdid(dataModel.getCdid());
				cdjgModel.setBmlbdm(dataModel.getBmlbdm());
				cdjgModel.setSqsj(dataModel.getSqsj());
				cdjgModel.setSqly(dataModel.getSqly());
				cdjgModel.setSqsjdkssj(dataModel.getSqsjdkssj());
				cdjgModel.setSqsjdjssj(dataModel.getSqsjdjssj());
				cdjgModel.setSjly("1");
				cdjgModel.setSplcid(dataModel.getSplcid());
				cdjgModel.setSqid(dataModel.getSqid());
				cdjgModel.setJgid(dataModel.getSqid());//优化，审核流数据，使jgid和sqid保持一致
				cdjgModel.setCyrs(dataModel.getCyrs());
				cdjgModel.setFzrxm(dataModel.getFzrxm());
				cdjgModel.setFzrlxfs(dataModel.getFzrlxfs());
				cdjgModel.setXfgfsyxy(dataModel.getXfgfsyxy());
				new CdjgService().runInsert(cdjgModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reuslt;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:校验申请的时间段 > 1.申请人申请的时间必须没有被其他人申请！
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 上午10:41:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkSqSjd(CdshForm model) throws Exception{
		
		CdshForm xmodel = getModel(model.getSqid());
		
		boolean booleaVal = true;
		
		CdxxwhForm cdxxModel = null ;
		if(xmodel != null && StringUtils.isNotBlank(xmodel.getCdid())){
			cdxxModel = cdxxwhService.getModel(xmodel.getCdid());
		}

		//申请人申请的时间必须没有被其他人申请
		if(cdxxModel != null){
			List<HashMap<String , String>> yscdsqList = dao.getYxCdsq(cdxxModel.getCdid()); //获取已经申请的场地使用列表
			String sqkssj = xmodel.getSqsjdkssj();
			String sqjssj = xmodel.getSqsjdjssj();
			for (HashMap<String, String> cdsq : yscdsqList) {
				String sqsjdkssj = cdsq.get("sqsjdkssj");
				String sqsjdjssj = cdsq.get("sqsjdjssj");
				if(DateUtils.checkTimepriodDuplicate(sqkssj, sqjssj, sqsjdkssj, sqsjdjssj)){
					booleaVal = false;
					break;
				}
			}
		}
		
		return booleaVal;
	}

	/**
	 * @throws Exception  
	 * @描述:批量保存审核
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 上午11:54:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(CdshForm t, User user) throws Exception {
		
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			CdshForm model = new CdshForm();
			model.setSqid(ids[i]);
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
	
	/**
	 * 
	 * @描述: 查看使用中的审批流程
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-10-23 下午01:49:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @return
	 * Boolean 返回类型 
	 * @throws
	 */
	public Boolean isHaveSplcSj(String splcid, String cdid) {
		List<HashMap<String, String>> list = dao.getSplcing(splcid,cdid);
		return null == list || list.size() <= 0 ? false : true;
	}
	
}
