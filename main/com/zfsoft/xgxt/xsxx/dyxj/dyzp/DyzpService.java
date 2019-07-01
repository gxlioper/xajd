/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午11:10:02 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dyzp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjDao;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjModel;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjService;
import com.zfsoft.xgxt.xsxx.dyxj.zpjg.ZpjgDao;
import com.zfsoft.xgxt.xsxx.dyxj.zpjg.ZpjgModel;

/** 
 * @类功能描述: 德育自评
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 上午11:10:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DyzpService extends SuperAuditService<DyzpModel, DyzpDao> {

	
	private static final String SQSH = "1";
	private ShlcInterface shlc = new CommShlcImpl();
	private ZpdjDao djDao = new ZpdjDao();
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(DyzpModel model) {
		
		ZpjgModel zpjgModel = new ZpjgModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(zpjgModel, model);
			zpjgModel.setSjly(SQSH);
			
			ZpjgDao zpjgDao = new ZpjgDao();
			if (Integer.valueOf(zpjgDao.getCount(zpjgModel)) == 0){
				return zpjgDao.runInsert(zpjgModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(DyzpModel model) {
		try {
			return new ZpjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public List<HashMap<String, String>> getAudingList(DyzpModel t, User user)
		throws Exception {

		return dao.getAudingList(t, user);
	}

	
	/**查询是否已申请**/
	public String getCount(DyzpModel model){
		return dao.getCount(model);
	}
	
	
	
	/**
	 * @throws Exception 
	 * 保存
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(DyzpModel form,User user) throws Exception{
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
		model.setGwid(form.getXtgwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getId());
		// ======= 业务字段 begin======
		model.setSqrid(form.getXh());
		// ======= 业务字段 end======
		model.setTzlj("xsxx_dyxj_pysh.do");
		model.setTzljsq("xsxx_dyxj_pysq.do");
		
		if(form.getShjg().equals("1")) {
			// 設定业务字段1[业务名称]
			model.setZd1("评定等级");
			// 設定业务字段2[业务ID]
			model.setZd2(form.getPddjdm());
			// 設定业务字段3
			ZpdjModel djFrom = new ZpdjModel();
			djFrom.setDjdm(form.getPddjdm());
			djFrom = djDao.getModel(djFrom);
			model.setZd3(djFrom.getDjmc());		
		}
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			DyzpModel upForm = new DyzpModel();
			upForm.setId(form.getId());
			if(zhzt.equals(Constants.SH_TG)) {
				upForm.setPddjdm(form.getPddjdm());
			}else{
				upForm.setPddjdm("");
			}
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getId());
			//审核状态为通过
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				ZpjgModel zpjgModel = new ZpjgModel();
				form = getModel(form);
				form.setDjdm(form.getPddjdm());
        		BeanUtils.copyProperties(zpjgModel, StringUtils.formatData(form));
        		zpjgModel.setSjly("1");
        		ZpjgDao zpjgDao = new ZpjgDao();
        		this.delZpjg(zpjgModel);
        		zpjgDao.runInsert(zpjgModel);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 批量审核保存  
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-3 下午02:47:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(DyzpModel t, User user) throws Exception {
		String[] ids = t.getSqid();
		String[] splcids = t.getSplcids();
		String[] xtgwid = t.getXtgwids();
		// ======= 业务字段 begin======
		String[] xhs = t.getXhs();
		// ======= 业务字段 end======
		List<String> fails = new ArrayList<String>();
		DyzpModel checkmodel = this.getModel(ids[0]);
	    ZpdjDao zpdjdao = new ZpdjDao();
	    HashMap<String,String> zpdjmodel = zpdjdao.getDjmc(t.getPddjdm());
		if(zpdjmodel.get("djmc").equals("优") && this.isOverLimit(checkmodel.getXn(), checkmodel.getXq(), checkmodel.getXh(),ids.length+"")){
			fails.add(xhs[0]);
			JSONArray json = JSONArray.fromObject(fails);
			return MessageUtil.getText(MessageKey.SYS_YXSX_OVER, json.toString());
		}
		for (int i = 0, n = ids.length; i < n; i++) {
			DyzpModel model = new DyzpModel();
			model.setId(ids[i]);
			model.setSplcid(splcids[i]);
			model.setXtgwid(xtgwid[i]);
			model.setPddjdm(t.getPddjdm());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			// ======= 业务字段 begin======
			model.setXh(xhs[i]);
			// ======= 业务字段 end======
		
		
				boolean isSuccess = saveSh(model, user);
				if (!isSuccess) {
					fails.add(xhs[i]);
				}
			
		}
		JSONArray json = JSONArray.fromObject(fails);
		if (fails.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 德育自评审核时判断是否优秀人数大于30%
	 */
	public boolean isOverLimit(String xn,String xq,String xh,String rs){
		
		return dao.isOverLimit(xn, xq, xh,rs);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 审核通过之后删除原来结果库的冗余数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-20 上午09:55:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zpjgModel
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delZpjg(ZpjgModel zpjgModel) throws Exception{
		return dao.delZpjg(zpjgModel);
	}
}
