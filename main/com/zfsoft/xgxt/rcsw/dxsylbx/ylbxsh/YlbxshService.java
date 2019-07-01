/**
 * @部门:学工产品事业部
 * @日期： 2013-12-18 上午08:52:03 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg.YlbxjgDao;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg.YlbxjgForm;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(学生证补办-补办审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 上午08:52:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxshService extends SuperServiceImpl<YlbxshForm, YlbxshDao> {

	private YlbxshDao dao = new YlbxshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public YlbxshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:查询获取审批信息
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:53:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYlbxshInfo(YlbxshForm model) {
		if (StringUtil.isNull(model.getYlsqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getYlbxshInfo(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * 保存学生证补办审核 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:58:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(YlbxshForm form,User user) throws Exception{
		
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
		model.setYwid(form.getYlsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_dxsylbx_ylbxsh.do");
		model.setTzljsq("rcsw_dxsylbx_ylbxsq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			YlbxshForm upForm = new YlbxshForm();
			upForm.setYlsqid(form.getYlsqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getYlsqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				YlbxjgForm ylbxjgForm = new YlbxjgForm();
        		BeanUtils.copyProperties(ylbxjgForm, StringUtils.formatData(form));
        		ylbxjgForm.setYljgid(form.getYlsqid());
        		ylbxjgForm.setSjly("1");
        		ylbxjgForm.setYlsqid(form.getYlsqid());
        		YlbxjgDao ylbxjgDao = new YlbxjgDao();
        		ylbxjgDao.runInsertNotCommit(ylbxjgForm);	
			}	
		
		return reuslt;
	}
	
	/**
	 * 
	 * @描述:TODO(获取财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 上午09:28:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCzqebzrymcList(String[] values) throws Exception {
		return dao.getCzqebzrymcList(values);
	}
	
	/**
	 * 
	 * @描述:TODO(获取参保状况名称)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 上午09:37:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCbzkdmcsList(String[] values) throws Exception {
		return dao.getCbzkdmcsList(values);
	}
	
	
	/**
	 * 
	 * @描述:TODO(撤销医疗保险审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午03:05:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(YlbxshForm model){
		boolean resultYlbx = false;
		boolean resultYlbxjg = false;
		try {
			//更新日常行为信息维护
			resultYlbx = dao.updateYlbxsq(model.getYlsqid(), Constants.YW_SHZ);
			if(resultYlbx){
				//删除日常行为结果中的记录
				resultYlbxjg = dao.deleteYlbxjg(model.getYlsqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultYlbxjg;
	}

	/**
	 * @throws Exception  
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 上午09:32:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(YlbxshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			YlbxshForm model = new YlbxshForm();
			model.setSplc(splcs[i]);
			model.setYlsqid(ids[i]);
			model.setGwid(gwid[i]);
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
