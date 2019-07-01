/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 下午02:10:15 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.sh;

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
import com.zfsoft.xgxt.rcsw.yxybgl.jg.YxybjgDao;
import com.zfsoft.xgxt.rcsw.yxybgl.jg.YxybjgForm;
import com.zfsoft.xgxt.rcsw.yxybgl.jg.YxybjgService;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqDao;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-24 下午02:10:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybshService extends SuperServiceImpl<YxybshForm, YxybshDao>{
	private YxybshDao dao = new YxybshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/** 
	 * @描述:保存审核
	 * @作者：柳俊[工号：982]
	 * @日期：2016-3-25 上午09:47:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSh(YxybshForm form, User user) {
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
		model.setSqrid(form.getXydm());
		model.setTzlj("rcsw_yxybgl_sh.do");
		model.setTzljsq("rcsw_yxybgl_sq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			YxybshForm sbshForm = new YxybshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				YxybjgForm yxybjgForm = new YxybjgForm();
				YxybjgService yxybjgService = new YxybjgService();
				YxybsqForm yxybsqForm = new YxybsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(yxybjgForm, StringUtils.formatData(yxybsqForm));
				yxybjgForm.setLylcywid(yxybsqForm.getSqid());
				yxybjgForm.setSjly("1");
				if(yxybjgService.isHaveRecordForjg(yxybjgForm)){
					//如果结果表中存在数据，先删除再插入
					new YxybjgDao().deleteForSq(yxybjgForm);
					yxybjgService.runInsert(yxybjgForm);
				}else{
					yxybjgService.runInsert(yxybjgForm);
				}								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @描述:撤销
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-25 上午11:06:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String cxshnew(String ywid, YxybshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;
	}
	
	/** 
	 * @描述:撤销并删除结果表数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-25 上午11:29:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(YxybshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// 删除结果库中的数据
			YxybjgDao yxybjgDao = new YxybjgDao();
			result = yxybjgDao.delByLclyywid(myForm.getSqid());
		return result;
	}
	
	/** 
	 * @描述:批量审核
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-25 上午11:43:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(YxybshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		YxybshForm model = new YxybshForm();
		for (int i = 0, n = ids.length; i < n; i++) {
			YxybshForm form = dao.getModel(ids[i]);			
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
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
