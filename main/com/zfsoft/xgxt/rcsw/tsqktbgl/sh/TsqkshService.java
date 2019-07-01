/**
 * @部门:学工产品事业部
 * @日期：2016-3-18 上午11:51:11 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.sh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.tsqktbgl.jg.TsqkjgDao;
import com.zfsoft.xgxt.rcsw.tsqktbgl.jg.TsqkjgForm;
import com.zfsoft.xgxt.rcsw.tsqktbgl.jg.TsqkjgService;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sq.TsqktbDao;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sq.TsqktbForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-18 上午11:51:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsqkshService extends SuperServiceImpl<TsqkshForm, TsqkshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private TsqkshDao dao = new TsqkshDao();
	
	public boolean saveSh(TsqkshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
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
		model.setZd1("处理层级");
		model.setZd2(form.getClcj());
		if(form.getClcj().equals("1")){
			model.setZd3("院系");
		}else{
			model.setZd3("学工部");
		}
		model.setTzlj("rcsw_tsqktbgl_sh.do");
		model.setTzljsq("rcsw_tsqktbgl_tb.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			TsqkshForm sbshForm = new TsqkshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				TsqkjgForm tsqkjgForm = new TsqkjgForm();
				TsqkjgService tsqkjgService = new TsqkjgService();
				TsqktbForm tsqktbForm = new TsqktbDao().getModel(form.getSqid());
				BeanUtils.copyProperties(tsqkjgForm, StringUtils.formatData(tsqktbForm));
				tsqkjgForm.setLylcywid(tsqktbForm.getSqid());
				tsqkjgForm.setSjly("1");
				tsqkjgForm.setClcj(form.getClcj());
				if(tsqkjgService.isHaveRecordForjg(tsqkjgForm)){
					//如果结果表中存在数据，先删除再插入
					new TsqkjgDao().deleteForSq(tsqkjgForm);
					tsqkjgService.runInsert(tsqkjgForm);
				}else{
					tsqkjgService.runInsert(tsqkjgForm);
				}								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @描述:得到处理层级
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-21 上午11:59:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCurentCjcj(TsqkshForm form){
		return dao.getCurentCjcj(form);
	}
	
	/** 
	 * @描述:撤销
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-21 上午11:59:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String cxshnew(String ywid, TsqkshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;
	}
	
	/** 
	 * @描述:撤销并删除结果表中数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-21 下午01:41:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(TsqkshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// 删除结果库中的数据
			TsqkjgDao tsqkjgDao = new TsqkjgDao();
			result = tsqkjgDao.delByLclyywid(myForm.getSqid());
		return result;
	}
	
	/** 
	 * @描述:批量审核
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-21 下午03:05:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(TsqkshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String clcj = t.getClcj();
		List<String> failXms = new ArrayList<String>();
		TsqkshForm model = new TsqkshForm();
		for (int i = 0, n = ids.length; i < n; i++) {
			TsqkshForm form = dao.getModel(ids[i]);			
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setClcj(clcj);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	
}
