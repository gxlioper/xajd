/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:35:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.sh;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgAction;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:35:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglShService extends
		SuperServiceImpl<XsxxKzxxglShForm, XsxxKzxxglShDAO> {
	private ShlcInterface shlc = new CommShlcImpl();	
	
	private ExtendService extendService = new ExtendService();
	
	private XsxxKzxxglJgService jgService = new XsxxKzxxglJgService();
	/**
	 * @throws Exception  
	 * @描述: 单个审核保存
	 * @日期：2014-4-25 下午04:12:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSh(XsxxKzxxglShForm model, User user) throws Exception {	
		XsxxKzxxglShForm dataModel = dao.getModel(model.getSqid());
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getSqid());
		shxxModel.setShlc(model.getSplc());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj("");
		shxxModel.setTzljsq("");
		boolean reuslt = false;
		try {
			String zhzt  = shlc.runAuditing(shxxModel); //审核操作{插入一条数据到审核表中}
			dataModel.setShzt(zhzt);//获取审核状态标志，并更新Model
			reuslt = dao.runUpdate(dataModel);//更新申请表{变更申请表中审核状态信息}
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //如果审核通过 插入一条数据到结果库
				//结果表新增记录
				XsxxKzxxglJgForm jgModel = new XsxxKzxxglJgForm();
				String jgid = UniqID.getInstance().getUniqIDHash();
				jgModel.setJgid(jgid);
				jgModel.setJrsj(DateUtils.getCurrTime());
				jgModel.setSjly("1");
				jgModel.setSqid(dataModel.getSqid());
				jgModel.setXh(dataModel.getXh());
				
				XsxxKzxxglJgForm modelByXh = jgService.getModelByXh(dataModel.getXh());
				if(modelByXh != null){
					jgService.deleteByXh(modelByXh.getXh());
					reuslt = extendService.deleteData(modelByXh.getJgid(), XsxxKzxxglJgAction.DATA_EXTEND_MODULE);
				}
				reuslt = jgService.runInsert(jgModel);
				//扩展字段临时数据到最终表
				reuslt = extendService.copyTempDataToFinal(dataModel.getSqid(), jgid, XsxxKzxxglJgAction.DATA_EXTEND_MODULE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
		
	}

}
