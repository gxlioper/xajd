/**
 * @部门:学工产品事业部
 * @日期：2015-5-26 下午02:02:28 
 */  
package xsgzgl.gygl.xyzsgl.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgDao;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqDao;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-5-26 下午02:02:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyzsShService extends SuperServiceImpl<XyzsShForm, XyzsshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *审核保存
	 */
	public boolean saveSh(XyzsShForm form, User user) {
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
//		model.setZd1("有效工时");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("gygl_xyzssh.do");
		model.setTzljsq("gygl_xyzssq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XyzsShForm zsshForm = new XyzsShForm();
			zsshForm.setSqbh(form.getSqbh());
			zsshForm.setShzt(zhzt);
			reuslt = dao.runUpdate(zsshForm, form.getSqbh());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XyzsglForm zsjgForm = new XyzsglForm();
				XyzsglService zsjgService = new XyzsglService();
				XyzsSqForm zssqForm = new XyzsSqDao().getModel(form.getSqbh());
				BeanUtils.copyProperties(zsjgForm, StringUtils.formatData(zssqForm));
//				khjgService.Cjcl(khjgForm);
				
				//这里检测结果表中是否有同学年同学号的申请结果，如果有，立即返回，不让其写结果表
				zsjgForm.setXh(form.getXh());
				XyzsglDao gldao = new XyzsglDao();
				if(!gldao.checkExistForSave2(zsjgForm).equals("")&&gldao.checkExistForSave2(zsjgForm) != null){
					XyzsglDao dao = new XyzsglDao();
					dao.delZsjgById(gldao.checkExistForSave2(zsjgForm));
				}
				zsjgForm.setSqbh(zssqForm.getSqbh());
				zsjgForm.setSjly("1");
				reuslt = zsjgService.runInsert(zsjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//批量审核
	public String savePlsh(XyzsShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XyzsShForm model = new XyzsShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//要不要做验证有待研究
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqbh(ids[i]);
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
	
	public boolean cancel(XyzsShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqbh());
		if (result) {
			XyzsglDao jgdao = new XyzsglDao();
		
			// 删除结果表中的申请结果
			
			jgdao.delZsjgById(myForm.getSqbh());
		
		}
		return result;
	}

	public String cxshnew(String ywid, XyzsShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}

}
