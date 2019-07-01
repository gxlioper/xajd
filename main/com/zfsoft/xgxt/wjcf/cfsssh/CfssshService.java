/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 上午09:29:41 
 */  
package com.zfsoft.xgxt.wjcf.cfsssh;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xsgzgl.wjcf.jcsz.WjcfJcszDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (申诉审核) 
 * @作者：陈敏杰[工号:913]
 * @时间： 2013-10-30 上午09:23:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfssshService extends SuperServiceImpl<CfssshForm, CfssshDao> {
	
	private CfssshDao dao=new CfssshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public CfssshService(){
		this.setDao(dao);
	}
	
	/** 
	 * @描述:(保存审核)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午04:46:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public boolean sssh(CfssshForm form, User user) throws Exception{
		
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShzt());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		model.setThgw(form.getThgw());
		// 业务ID(多为申请ID)
		model.setYwid(form.getYwid());
		model.setSqrid(form.getXh());
		model.setTzlj("wjcf_cfsssh.do?method=cxCfssshList");
		model.setTzljsq("wjcf_cfsssq.do?method=cxCfsssqList");
		
		String zhzt = shlc.runAuditingNotCommit(model);
		
		CfssshForm shForm=new CfssshForm();
		shForm.setYwid(form.getYwid());
		shForm.setSsjg(zhzt);
		boolean result=dao.runUpdateNotCommit(shForm, shForm.getYwid());
		if(result){
			//设置代办信息
			/*BaseDbcz dbcz=new BaseDbcz();
			dbcz.setShPath("wjcf_cfsssh.do?method=cxCfssshList");
			dbcz.setSqPath("wjcf_cfsssq.do?method=cxCfsssqList");
			dbcz.setGnmkMc("处分申诉审核");
			dbcz.setXmmc("申诉审核");
			dbcz.shPush(form.getYwid(), form.getSplcid());*/
		}
		if(result && zhzt.equals(Constants.TG)){
			shForm = dao.getModel(shForm);
			form.setSsfilepath(shForm.getSsfilepath());
			result= insertWjjgk(form);
		}
		
		
		return result;
	}

	/** 
	 * @描述:(申诉通过进入结果库)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-30 下午03:11:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	private boolean insertWjjgk(CfssshForm form) throws Exception{
		if(!"更改处分".equalsIgnoreCase(form.getZzssjg())){
			form.setCfggw("");
		}
		return dao.insertWjjgk(form);
	}
	
	/** 
	 * @描述:(最后一级审核回滚)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 上午10:41:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @param string
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(CfssshForm model) throws Exception{
		CfssshForm shForm=new CfssshForm();
		shForm.setYwid(model.getYwid());
		shForm.setSsjg(Constants.SHZ);
		boolean result=dao.runUpdate(shForm, shForm.getYwid());
		if(result){
			int count=dao.udateJgk(model.getCfid());  //修改结果库
			if(count>=0){
				//设置代办信息
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfsssh.do?method=cxCfssshList");
				dbcz.setSqPath("wjcf_cfsssq.do?method=cxCfsssqList");
				dbcz.setGnmkMc("处分申诉审核");
				dbcz.setXmmc("申诉审核");
				dbcz.shPush(model.getYwid(), model.getSplcid());*/
			}else{
				return false;
			}
		}
		
		return result;
	}

	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(CfssshForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfssb where ssid = ?", new String[]{form.getYwid()}, "fj");
		return blob.getBinaryStream();
	}

	
	
	/**
	 * @throws Exception  
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-24 下午01:55:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(CfssshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();
		//得到需要批量审核的数据集
		WjcfJcszDao wjcfjcszdao = new WjcfJcszDao();
		HashMap<String, String> resultList = wjcfjcszdao.ssjcsplCx(null);

		for (int i = 0, n = ids.length; i < n; i++) {
			CfssshForm model = new CfssshForm();
			model.setSplcid(resultList.get("ssspl"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);

			boolean isSuccess = sssh(model, user);

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
}
