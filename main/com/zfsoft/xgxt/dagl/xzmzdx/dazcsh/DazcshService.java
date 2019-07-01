/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:01:40 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsh;

import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcjg.DazcjgDao;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcjg.DazcjgForm;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcjg.DazcjgService;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcsq.DazcsqDao;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcsq.DazcsqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-5-14 下午03:28:27 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DazcshService extends SuperServiceImpl<DazcshForm,DazcshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @描述: 审核个人信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:52:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDazcshInfo(DazcshForm t) throws Exception{
		return dao.getDazcshInfo(t);
	}
	
	/**
	 * @描述: 审核保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午06:57:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean dazcshSingleSave(DazcshForm form,User user)throws Exception{
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xsxx_dagl_dazcsh.do");
		model.setTzljsq("xsxx_dagl_dazcsq.do");
		boolean reuslt = false;
		try{
			String zhzt = shlc.runAuditingNotCommit(model);
			DazcshForm dazcshForm = new DazcshForm();
			dazcshForm.setSqid(form.getSqid());
			dazcshForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(dazcshForm,form.getSqid());
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				DazcjgForm dazcjgForm = new DazcjgForm();
				DazcjgService dazcjgService = new DazcjgService();
				DazcsqForm dazcsqForm = new DazcsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(dazcjgForm, StringUtils.formatData(dazcsqForm));
				
				dazcjgForm.setYwid(dazcsqForm.getSqid());
				/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
				String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
				dazcjgForm.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
				/*数据来源【1:申请审核、2:结果增加、3:导入】*/
				dazcjgForm.setSjly("1");
				/*档案转乘信息【1:已登记、2:已转出、3:未登记】*/
				dazcjgForm.setDazcxx("1");
				reuslt = dazcjgService.runInsert(dazcjgForm);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * @描述: 撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:33:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(DazcshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if(result){
			DazcjgDao dazcjgDao = new DazcjgDao();
			dazcjgDao.delShjgById(myForm.getSqid());
		}
		return result;
	}
}
