/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:25:12 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgDao;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgForm;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明审核-SERVICE类
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 上午10:25:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmShService extends SuperServiceImpl<ZdzmShForm, ZdzmShDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();

	public ZdzmShService(){
		setDao(new ZdzmShDao());
	}


	/**
	 * @throws Exception  
	 * @描述: 单个审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-25 下午04:12:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public boolean saveSh(ZdzmShForm model, User user) throws Exception {
		
		ZdzmShForm dataModel = dao.getModel(model.getZdzmsqid());
		
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getZdzmsqid());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj(ZdzmComm.PATH_SH);
		shxxModel.setTzljsq(ZdzmComm.PATH_SQ);
		boolean reuslt = false;
		try {
			String zhzt  = shlc.runAuditingNotCommit(shxxModel); //审核操作{插入一条数据到审核表中}
			dataModel.setShzt(zhzt);//获取审核状态标志，并更新Model
			
			reuslt = dao.runUpdateNotCommit(dataModel);//更新申请表{变更申请表中审核状态信息}
			
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //如果审核通过 插入一条数据到结果库
				ZdzmJgForm zdzmJgModel = new ZdzmJgForm();
				zdzmJgModel.setJrsj(DateUtils.getCurrDate());
				zdzmJgModel.setSjly("1");
				zdzmJgModel.setSqly(dataModel.getSqly());
				zdzmJgModel.setSqsj(dataModel.getSqsj());
				zdzmJgModel.setXh(dataModel.getXh());
				zdzmJgModel.setZdzmsqid(dataModel.getZdzmsqid());
				zdzmJgModel.setZdzmjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
				ZdzmJgDao jgDao = new ZdzmJgDao();
				jgDao.runInsertNotCommit(zdzmJgModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
		
	}
	
	
	/**
	 * @throws Exception  
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-25 下午04:00:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(ZdzmShForm t, User user) throws Exception {
		
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			ZdzmShForm model = new ZdzmShForm();
			
			model.setZdzmsqid(ids[i]);
			model.setSplcid(splcs[i]);
			model.setXtgwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
					
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
	
}
