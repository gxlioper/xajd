/**
 * @部门:学工产品事业部
 * @日期：2016-3-1 上午11:37:38 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sh;

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
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgDao;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgForm;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgService;
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqDao;
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqForm;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgDao;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgForm;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgService;
import com.zfsoft.xgxt.zxdk.ypzl.sh.YpzlshForm;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqDao;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-1 上午11:37:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdshService extends SuperServiceImpl<ZzdshForm, ZzdshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private ZzdshDao dao = new ZzdshDao();
	
	/** 
	 * @描述:保存审核
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 下午04:05:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSh(ZzdshForm form, User user) {
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
		model.setYwid(form.getZzdid());
		model.setSqrid(user.getUserName());
		model.setTzlj("xgygl_zzdgl_zdsh.do");
		model.setTzljsq("xgygl_zzdgl_zdsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			ZzdshForm sbshForm = new ZzdshForm();
			sbshForm.setZzdid(form.getZzdid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getZzdid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				ZzdjgForm zzdjgForm = new ZzdjgForm();
				ZzdjgService zzdjgService = new ZzdjgService();
				ZzdsqForm zzdsqForm = new ZzdsqDao().getModel(form.getZzdid());
				BeanUtils.copyProperties(zzdjgForm, StringUtils.formatData(zzdsqForm));
				zzdjgForm.setSjly("1");
				zzdjgForm.setCzy(user.getUserName());
				if(zzdjgService.isHaveRecordForjg(zzdjgForm)){
					//如果结果表中存在数据，先删除再插入
					new ZzdjgDao().deleteForSq(zzdjgForm);
					zzdjgService.runInsert(zzdjgForm);
				}else{
					zzdjgService.runInsert(zzdjgForm);
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
	 * @日期：2016-3-1 下午04:07:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String cxshnew(String ywid, ZzdshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplcid());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
	
	/** 
	 * @描述:最后一级撤销
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 下午04:26:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(ZzdshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getZzdid());
			// 删除结果库中的数据
			ZzdjgDao zzdjgDao = new ZzdjgDao();
			result = zzdjgDao.delByZzdid(myForm.getZzdid());
		return result;
	}
	
	/** 
	 * @描述:批量审核保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 下午05:19:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(ZzdshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			ZzdshForm form = dao.getModel(ids[i]);
			ZzdshForm model = new ZzdshForm();
			model.setSplcid(form.getSplcid());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setZzdid(ids[i]);
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
