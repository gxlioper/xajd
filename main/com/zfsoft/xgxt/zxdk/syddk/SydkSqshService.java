/**
 * @部门:学工产品事业部
 * @日期：2015-7-1 下午04:39:28 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-7-1 下午04:39:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SydkSqshService extends SuperAuditService<SydkSqshModel,SydkSqshDao>{

	private static final String SQSH = "sqsh";
	private ShlcInterface shlc = new CommShlcImpl();
	
	@Override
	public boolean afterLastAudit(SydkSqshModel model) {
		
		SyddkModel jgModel = new SyddkModel();
		try {
			BeanUtils.copyProperties(jgModel, model);
			jgModel.setSjly(SQSH);
			jgModel.setSqly(model.getSqly());
			jgModel.setDkyh(model.getYhdm());
			jgModel.setXfyss(model.getXfysf());
			jgModel.setZsfyss(model.getZsysf());
			jgModel.setHtbh(model.getZd3());
			jgModel.setDkkssj(model.getZd5());
			jgModel.setHzjym(model.getZd6());
			SyddkDao syDao = new SyddkDao();
			dao.runUpdate(model);
			//插入之前先进行删除
			dao.DelSydJgb(jgModel.getXn(),jgModel.getXh());
			return syDao.runInsert(jgModel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean deleteResult(SydkSqshModel model) {
		SyddkDao syDao = new SyddkDao();
		try {
			return syDao.runDelete(new String[]{model.getId()})>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    
	
	
	@Override
	public int runDelete(String[] values) throws Exception {
		//SyddkDao sydkDao = new SyddkDao();
		//sydkDao.delDkxx(values);
		return super.runDelete(values);
	}


	@Override
	public boolean runInsert(SydkSqshModel t) throws Exception {
		t.setId(UniqID.getInstance().getUniqIDHash());
		boolean result = super.runInsert(t);
		/*
		if (result&&t.getDkxn()!=null&&t.getDkxn().length>0){
			result = insertDkxx(t);
		}*/
		
		return result;
	}
  
	@SuppressWarnings("unused")
	private boolean insertDkxx(SydkSqshModel t) throws SQLException{
		String[] dkxn = t.getDkxn();
		String[] xf = t.getXf();
		String[] zsf = t.getZsf();
		String[] shf = t.getShf();
		boolean result = false;
		if (dkxn != null && dkxn.length > 0){
			List<String[]> params = new ArrayList<String[]>();
			for (int i = 0 ; i < dkxn.length ; i++){
				
				if (StringUtil.isNull(xf[i])){
					continue;
				}
				
				String[] param = new String[]{t.getId(),dkxn[i],xf[i],zsf[i],shf[i]};
				params.add(param);
			}
			SyddkDao sydkDao = new SyddkDao();
			result= sydkDao.insertDkxx(params);
		}
		return result;
	}

	@Override
	public boolean runUpdate(SydkSqshModel t) throws Exception {
		boolean result = super.runUpdate(t);
		/*if(result){
			if(StringUtils.isArrayNotNull(t.getDkxn())){
				SyddkDao sydkDao = new SyddkDao();
				result = sydkDao.delDkxx(new String[]{t.getId()});
				result = insertDkxx(t);	
			}
		}*/
		return result;
	}


	public List<HashMap<String, String>> getYhList(){
		return dao.getYhList();
	}
	
	public boolean isExitsByXhAndXn(SydkSqshModel t){
		return dao.isExitsByXhAndXn(t);
	}
	
	//获取贷款信息
	public List<HashMap<String,String>> getDkxxList(String id){
		SyddkDao sydkDao = new SyddkDao();
		return sydkDao.getDkxxList(id);
	}
	
	public List<HashMap<String, String>> getAudingList(SydkSqshModel t, User user)
	throws Exception {
		return dao.getAudingList(t, user);
	}
	
	public List<HashMap<String,String>> getShxx(SydkSqshModel model){
		return dao.getShxx(model);
	}
	
	/**
	 * 
	 * @描述:单个审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-18 上午10:06:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(SydkSqshModel form, User user) {
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
	//	model.setZd1("有效工时");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// 业务ID(多为申请ID)
		model.setYwid(form.getId());
		model.setSqrid(form.getXh());
		model.setTzlj("gygl_xyzssh.do");
		model.setTzljsq("gygl_xyzssq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			SydkSqshModel shForm = new SydkSqshModel();
			shForm.setId(form.getId());
			shForm.setShzt(zhzt);
			reuslt = dao.runUpdate(shForm, form.getId());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				SyddkModel jgForm = new SyddkModel();
				SyddkService jgService = new SyddkService();
				SydkSqshModel zssqForm = new SydkSqshService().getModel(form.getId());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(zssqForm));
				jgForm.setDkyh(zssqForm.getYhdm());
				jgForm.setXh(form.getXh());
				dao.DelSydJgb(jgForm.getXn(),jgForm.getXh());
				jgForm.setId(zssqForm.getId());
				jgForm.setSjly(SQSH);
				reuslt = jgService.runInsert(jgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @描述: 批量审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-18 上午10:07:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(SydkSqshModel t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		SydkSqshModel model = new SydkSqshModel();
		String[] ids = t.getIds();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(t.getSplcid());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setId(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());

			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * @throws Exception  
	 * @描述:设置学生基本信息(西安科技大学个性化设置学生基本信息)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-17 下午02:24:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws 
	 */
	public void setXsjbxx(HashMap<String, String> xsjbxx,String xh) throws Exception{
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkForm = new JtqkdcForm();
			jtqkForm.setXh(xh);
			JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
			if(null != jtqkModel){				
				//家庭人均月收入
				xsjbxx.put("jtrjysr", jtqkModel.getJtrjysr());
				//家庭人均年收入
				xsjbxx.put("jtrjnsr", jtqkModel.getJtrjsr());
			}else{
				//家庭人均月收入
				xsjbxx.put("jtrjysr", null);
				//家庭人均年收入
				xsjbxx.put("jtrjnsr", null);
			}
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> dcMap = knsjgService.getXsrddc(xh);
			if(null != dcMap){
				//认定档次
				xsjbxx.put("rddc", dcMap.get("rddcmc"));
				//认定时间
				xsjbxx.put("sqsj", dcMap.get("sqsj"));
			}else{
				//认定档次
				xsjbxx.put("rddc", null);
				//认定时间
				xsjbxx.put("sqsj", null);
			}			
			//取得第一位家庭成员
			List<HashMap<String,String>> list = jtqkService.getJtcyList(xh, 1);
			if(list.size() > 0){
				String cyxm = list.get(0).get("cyxm");
				String cylxdh = list.get(0).get("cylxdh");
				xsjbxx.put("cyxm", cyxm);
				xsjbxx.put("cylxdh", cylxdh);						
			}
			//取第一位辅导员信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> map = xsxxService.getFdyxxByXh(xh);
			xsjbxx.put("fdyxm", map.get("fdyxm"));
			xsjbxx.put("fdylxdh", map.get("fdylxdh"));
	}
	
}
