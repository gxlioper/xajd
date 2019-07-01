/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 下午01:53:33 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybsh;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg.BjxqybjgForm;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg.BjxqybjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 班级学情月报管理模块
 * @类功能描述: TODO(北京中医药大学_学情月报_班级学情审核) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-31 下午01:53:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxqybshService extends
		SuperServiceImpl<BjxqybshForm, BjxqybshDao> {
	
	private BjxqybshDao dao = new BjxqybshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—班级学情审核填写页面内容)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-6 上午08:54:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybshInfo(User user,BjxqybshForm model){
		
		return dao.getBjxqybshInfo(user, model);
	}
	
	public boolean saveSh(BjxqybshForm form,User user){
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
		model.setSqrid(form.getTxr());
		model.setTzlj("rcsw_xqybgl_bjxqybgl_bjxqybsh.do");
		model.setTzljsq("rcsw_xqybgl_bjxqybgl_bjxqybsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			BjxqybshForm upForm = new BjxqybshForm();
			upForm.setSqid(form.getSqid());			
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){				
				BjxqybjgForm bjxqybjgForm = new BjxqybjgForm();
				BjxqybjgService bjxqybjgService = new BjxqybjgService();
				form = getModel(form);
        		BeanUtils.copyProperties(bjxqybjgForm, StringUtils.formatData(form));       		
        		bjxqybjgForm.setLylcywid(form.getSqid());
        		//bjxqybjgForm.setJgid(form.getSqid());
        		bjxqybjgForm.setSjly("1");       		
        		bjxqybjgService.deleteExist(bjxqybjgForm);
        		bjxqybjgService.saveBjxqybjg(bjxqybjgForm,user);        		        	
        		
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—撤销学情月报审核)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午02:17:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(BjxqybshForm model){
		boolean resultBjxqybsq = false;
		boolean resultBjxqybjg = false;
		try {
			//更新班级学情月报申请
			resultBjxqybsq = dao.updateBjxqybsq(model.getSqid(), Constants.YW_SHZ);
			if(resultBjxqybsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultBjxqybjg = true;
				}else{
					//删除班级学情月报结果
					resultBjxqybjg = dao.deleteBjxqybjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultBjxqybjg;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—批量审核保存)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 下午04:55:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(BjxqybshForm t, User user) {
			
			String[] ids = t.getId();
			String[] gwid = t.getGwids();
			String[] bjdms = t.getBjdms();
			String[] splcs = t.getSplcs();
	
			List<String> failBjdms = new ArrayList<String>();
	
			for (int i = 0, n = ids.length; i < n; i++) {			
				BjxqybshForm model = new BjxqybshForm();
				model.setSplc(splcs[i]);
				model.setYwid(ids[i]);
				model.setGwid(gwid[i]);
				model.setSqid(ids[i]);
				model.setShyj(t.getShyj());
				model.setShjg(t.getShzt());
				model.setBjdm(bjdms[i]);			
				boolean isSuccess = saveSh(model, user);
	
				if (!isSuccess) {
					failBjdms.add(bjdms[i]);
				}
			}
	
			JSONArray json = JSONArray.fromObject(failBjdms);
	
			if (failBjdms.isEmpty()) {
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
