/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:39:23 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgService;
import com.zfsoft.xgxt.rcsw.lstd.lstdsq.LstdsqDao;
import com.zfsoft.xgxt.rcsw.lstd.lstdsq.LstdsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:39:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdshService extends SuperServiceImpl<LstdshForm, LstdshDao> {
	
	
	private LstdshDao dao = new LstdshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public LstdshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:查询审批信息
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:16:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getLstdshInfo(LstdshForm model) {
		if (StringUtil.isNull(model.getSqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getLstdshInfo(model);
	}
	
	/**
	 * 
	 * @描述:绿色通道审核
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:17:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(LstdshForm form,User user){
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
		model.setTzlj("rcsw_lstd_sh.do");
		model.setTzljsq("rcsw_lstd_sq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			LstdshForm upForm = new LstdshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//保存到结果表
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				LstdjgForm lstdjgForm = new LstdjgForm();
				LstdjgService lstdjgService = new LstdjgService();
				//根据ID查询申请信息
				LstdsqForm lstdsqForm = new LstdsqDao().getModel(form.getSqid());
				//根据学号、学年、学期删除结果表当中的数据
				lstdjgService.delForXhxnxq(lstdsqForm.getXh(),lstdsqForm.getXn(),lstdsqForm.getXq());
        		BeanUtils.copyProperties(lstdjgForm, StringUtils.formatData(lstdsqForm));
        		lstdjgForm.setJgid(lstdsqForm.getSqid());
        		lstdjgForm.setSjly("1");
        		lstdjgForm.setCzyh(user.getUserName());
        		lstdjgService.saveSqjg(lstdjgForm);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:22:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(LstdshForm model){
		boolean resultRcww = false;
		boolean resultRcxwjg = false;
		try {
			//更新日常行为信息维护
			resultRcww = dao.updateLstdsq(model.getSqid(), Constants.YW_SHZ);
			if(resultRcww){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultRcxwjg = true;
				}else{
					//删除日常行为结果中的记录
					resultRcxwjg = dao.deleteLstdsq(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRcxwjg;
	}

	/**
	 * 
	 * @描述:批量保存
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(LstdshForm t, User user) {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			//得到与ID对应的审批流程ID
			LstdshDao lstdshdao = new LstdshDao();
			Map<String, String> resultList = lstdshdao.getOneLstdsqInfo(ids[i]);
			
			LstdshForm model = new LstdshForm();
			model.setSplc(resultList.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
			model.setXq(t.getXq());

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
