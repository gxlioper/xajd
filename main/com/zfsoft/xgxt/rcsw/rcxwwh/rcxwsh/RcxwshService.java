/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午08:42:13 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwsh;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.rcxwwh.comm.RcxwwhForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhDao;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为审核  
 * @作者：Dlq [工号：995]
 * @时间： 2013-8-5 上午08:42:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwshService extends SuperServiceImpl<RcxwshForm, RcxwshDao> {

	private RcxwshDao dao = new RcxwshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public RcxwshService() {
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
	public HashMap<String, String> getSplcInfo(RcxwshForm model) {
		if (StringUtil.isNull(model.getId())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getSplcInfo(model);
	}
	
	/**
	 * 
	 * 保存日常行为审核 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:58:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(RcxwshForm form,User user){

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
		model.setYwid(form.getId());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_rcxwwh_rcxwsh.do");
		model.setTzljsq("rcsw_rcxwwh_rcxwxxwh.do");
		// ====== 保存调整后分值 begin =========
		model.setZd1("调整后分值");
		model.setZd2(form.getShfz());
		if("01".equals(form.getRcxwfzlx())){
			model.setZd3("+"+form.getShfz());
		}else{
			model.setZd3("-"+form.getShfz());
		}
		// ====== 保存调整后分值 end =========
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			RcxwshForm upForm = new RcxwshForm();
			upForm.setId(form.getId());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getId());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				
				RcxwxxwhForm xwModel = new RcxwxxwhForm();
				xwModel.setId(form.getId());
				RcxwxxwhForm shModel = new RcxwxxwhDao().getModel(xwModel);
				
				RcxwjgForm rcxwjgForm = new RcxwjgForm();
        		RcxwjgService rcxwjgService = new RcxwjgService();
        		BeanUtils.copyProperties(rcxwjgForm, shModel);
        		rcxwjgForm.setId(form.getId());
        		rcxwjgForm.setSjly("1");
        		rcxwjgForm.setRcxwxxid(form.getId());
        		rcxwjgForm.setFz(form.getShfz()); // 以审核评定分值为准
        		rcxwjgService.runInsert(rcxwjgForm);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean plsh(String shzt,String shyj,String info,User user){
		RcxwshService rcxwshService = new RcxwshService();
		ShlcDao shlcDao = new ShlcDao();
		
		JSONArray array = JSONArray.fromString(info);
		boolean result = false;
		for (int i = 0 ; i < array.length() ; i++){
			JSONObject json = (JSONObject) array.get(i);
			
			RcxwshForm form = new RcxwshForm();
			form.setSplc(json.getString("splc"));
			form.setShyj(shyj);
			form.setShjg(shzt);
			form.setGwid(ShlcUtil.getDqGw(json.getString("id")));
			form.setId(json.getString("id"));
			form.setXh(json.getString("xh"));
			// ========== 审核分值设置 begin ============
			HashMap<String,String> infoList = rcxwshService.getSplcInfo(form);
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(json.getString("id"), "desc");
			String shfz = infoList.get("sqfz");
			if(shyjList.size() > 0){
				HashMap<String, String> shyjMap = shyjList.get(0);
				shfz = shyjMap.get("zd2");
			}
			form.setShfz(shfz);
			form.setRcxwfzlx(infoList.get("rcxwfzlx"));
			// ========== 审核分值设置 end ============
			result = saveSh(form, user);
			if (!result){
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * 
	 * 撤销日常行为审核
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:58:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelSh(RcxwshForm form,User user){
		boolean reuslt = false;
		try {
			reuslt = shlc.runCancel(user.getUserName(),form.getId(),form.getSplc(),form.getGwid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean newCancelSh(RcxwshForm model){
		boolean resultRcww = false;
		boolean resultRcxwjg = false;
		try {
			//更新日常行为信息维护
			resultRcww = dao.updateRcxwxx(model.getId(), Constants.YW_SHZ);
			if(resultRcww){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultRcxwjg = true;
				}else{
					//删除日常行为结果中的记录
					resultRcxwjg = dao.deleteRcxwjg(model.getId());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRcxwjg;
	}

}
