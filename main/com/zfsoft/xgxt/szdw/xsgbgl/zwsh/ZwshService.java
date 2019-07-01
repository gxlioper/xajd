/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:14:25 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理职务审核
 * @作者： zhangjw
 * @时间： 2013-8-9 下午5:00:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZwshService extends SuperServiceImpl<ZwshForm, ZwshDAO> {
	
	private ZwshDAO dao = new ZwshDAO();


	private ShlcInterface shlc = new CommShlcImpl();
	
	public ZwshService() {
		super.setDao(dao);
	}
	/**
	 * @描述:学生干部职务审核
	 * @作者：zhangjw
	 * @日期：2013-8-12 上午9:57:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	@TransactionControl
	public boolean zwsh(ZwshForm form,User user) throws Exception{
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getSqid());
		model.setThgw(form.getThgw());
		
		// 业务ID(多为申请ID)
		model.setSqrid(form.getXh());
		model.setTzlj("szdw_zwsh.do?method=zwshList");
		model.setTzljsq("szdw_zwsq.do?method=zwsqList");
		
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditingNotCommit(model);
			ZwshForm upForm = new ZwshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getSqid());
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				//处理业务
				reuslt = insertDwwh(form);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	/**
	 * @描述:如果审核成功加入队伍列表
	 * @作者：zhangjw
	 * @日期：2013-8-12 上午11:31:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @throws Exception
	 * void 返回类型
	 */
	private boolean insertDwwh(ZwshForm form) throws Exception{
		DwwhForm dwwhForm = new DwwhForm();
		dwwhForm.setZwsqid(form.getSqid());
		dwwhForm.setZwid(form.getZwid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		dwwhForm.setRzsj(date);
		dwwhForm.setZzzt("1");//在职状态 1为在职 0 为离职
		dwwhForm.setXh(form.getXh());
		dwwhForm.setLrsj(sdft.format(new Date()));
		dwwhForm.setSjly("1");//数据来源，审批流数据为1
		DwwhDAO dwwh = new DwwhDAO();
		return dwwh.runInsertNotCommit(dwwhForm);
	}
	
	public boolean newCancelSh(ZwshForm model){
		boolean resultZwsq = false;
		boolean resultZwsqjg = false;
		try {
			model.setShzt(Constants.YW_SHZ);
			//更新日常行为信息维护
			resultZwsq = dao.updateZwsq(model)>0?true:false;
			if(resultZwsq){
				//删除日常行为结果中的记录
				resultZwsqjg = dao.deleteZwsqjg(model.getSqid());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultZwsq;
	}
	
	
	/**
	 * @throws Exception  
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-30 上午09:22:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(ZwshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		String[] zwids = t.getZwids();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			ZwshForm model = new ZwshForm();
			model.setSplc(splcs[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setXh(xhs[i]);
			model.setZwid(zwids[i]);
			
			boolean isSuccess = zwsh(model, user);

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
	
	public String cxshnew(String ywid, ZwshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
}
