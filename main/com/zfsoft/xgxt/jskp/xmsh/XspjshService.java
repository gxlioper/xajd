/**
 * @部门:学工产品(1)部
 * @日期：2018-4-16 下午07:34:58 
 */  
package com.zfsoft.xgxt.jskp.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.util.Log;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgDao;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgForm;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgService;
import com.zfsoft.xgxt.jskp.zzsq.XspjsqDao;
import com.zfsoft.xgxt.jskp.zzsq.XspjsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生评价管理模块
 * @类功能描述: 学生评价审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-16 下午07:34:58 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjshService extends SuperServiceImpl<XspjshForm,XspjshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	/*0：未选中，1：选中*/
	private static final String FFGZ_Y = "1"; 
	
	/**
	 * @描述: 学生审核数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-17 下午03:40:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXspjshInfo(XspjshForm t) throws Exception{
		return dao.getXspjshInfo(t);
	}
	
	/**
	 * @描述: 取审核状态表中的最新一条记录的分数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-17 下午04:27:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLevelXxBySqid(XspjshForm t) throws Exception{
		return dao.getLevelXxBySqid(t);
	}
	
	/**
	 * @描述: 单个审核保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午12:01:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean xspjshSingleSave(XspjshForm form,User user)throws Exception{
		
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
		model.setZd1("评分");
		model.setZd3(form.getFs());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xspj_xspj_xspjsh.do");
		model.setTzljsq("xspj_xspj_xspjsq.do");
		boolean reuslt = false;
		try{
			String zhzt = shlc.runAuditingNotCommit(model);
			XspjshForm xspjshForm = new XspjshForm();
			xspjshForm.setSqid(form.getSqid());
			xspjshForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(xspjshForm, form.getSqid());
			/*保存到结果表*/
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				XspjjgForm xspjjgForm = new XspjjgForm();
				XspjjgService xspjjgService = new XspjjgService();
				XspjsqForm xspjsqForm = new XspjsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(xspjjgForm, StringUtils.formatData(xspjsqForm));
				xspjjgForm.setGuid(xspjsqForm.getSqid());
				xspjjgForm.setFz(form.getFs());
				/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
				String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
				xspjjgForm.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
				/*数据来源【1:申请审核、2:结果增加、3:导入】*/
				xspjjgForm.setSjly("1");
				reuslt = xspjjgService.runInsert(xspjjgForm);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * @描述: 批量保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-19 上午09:43:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@TransactionControl
	public String xspjshBatchSave(XspjshForm t,User user)throws Exception{
		XspjshForm model = new XspjshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		HashMap<String,String> getBeforeMark = new HashMap<String, String>();
		if(StringUtils.isNotNull(t.getFfgz())&&FFGZ_Y.equals(t.getFfgz())){
			getBeforeMark = getBeforeMark(t.getId());
		}
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSqid(ids[i]);
			model.setXh(xhs[i]);
			model.setSplcid(t.getSplcid());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			String fs = StringUtils.isNull(getBeforeMark.get(ids[i]))? t.getFs():getBeforeMark.get(ids[i]);
			model.setFs(fs);
			boolean isSuccess = xspjshSingleSave(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString().replaceAll(",", ", "));
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString().replaceAll(",", ", "));
		}
	}
	
	/**
	 * @描述: 根据ID查询当下有多少审批岗位
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午04:56:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getShxhForId(String id){
		if(StringUtils.isNull(id)){
			Log.error("id 不能为空");
			return null;
		}
		HashMap<String, String> shxhMap = new HashMap<String, String>();
		List<HashMap<String, String>> shxhList = dao.getShxhForId(id);
		//转成map，页面好处理
		for (HashMap<String, String> hashMap : shxhList) {
			shxhMap.put(hashMap.get("xh"), hashMap.get("count"));
		}
		return shxhMap;
	}
	
	/**
	 * @描述: 最后一级撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午05:28:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(XspjshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if(result){
			XspjjgDao xspjjgDao = new XspjjgDao();
			xspjjgDao.delShjgById(myForm.getSqid());
		}
		return result;
	}
	
	/**
	 * @描述: 根据id获取前一次分数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-19 上午09:52:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBeforeMark(String[] ids){
		
		if(null==ids||ids.length==0){
			Log.error("id 不能为空");
			return null;
		}
		HashMap<String, String> sjfzMap = new HashMap<String, String>();
		List<HashMap<String, String>> sjfzList = dao.getBeforeMark(ids);
		//转成map，页面好处理
		for (HashMap<String, String> hashMap : sjfzList) {
			sjfzMap.put(hashMap.get("sqid"), hashMap.get("sjfs"));
		}
		return sjfzMap;
	}
	
	/**
	 * @描述: 无勾选批量审核调用父页面方法，用父页面(查询页面)方法的高级查询条件查询数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-23 下午02:51:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> xspjPlshxx(XspjshForm t, User user) throws Exception{
		return dao.xspjPlshxx(t,user);
	}
	
	/**
	 * @描述: 根据高级查询条件查询出的数据(无勾选)，审核并做保存操作
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-23 下午03:00:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param resultList
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@TransactionControl
	public String xspjshPlshSave(XspjshForm t, User user,List<HashMap<String, String>> resultList) throws Exception {
		if(null == resultList || 0 == resultList.size()){
			return MessageUtil.getText("查询结果为空");
		}
		
		String[] ids = new String[resultList.size()];
		String[] gwids = new String[resultList.size()];
		String[] xhs = new String[resultList.size()];
		String[] splcs = new String[resultList.size()];
		
		for (int i = 0; i < resultList.size(); i++) {
			ids[i] = resultList.get(i).get("sqid");
			gwids[i] = resultList.get(i).get("gwid");
			xhs[i] = resultList.get(i).get("xh");
			splcs[i] = resultList.get(i).get("splcid");
		}
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			t.setSqid(ids[i]);
			t.setXh(xhs[i]);
			t.setSplcid(splcs[i]);
			t.setYwid(ids[i]);
			t.setGwid(gwids[i]);
			t.setSqid(ids[i]);
			t.setShyj(t.getShyj());
			t.setShjg(t.getShzt());
			t.setXh(xhs[i]);
			t.setFs(t.getFs());
			boolean isSuccess = xspjshSingleSave(t, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString().replaceAll(",", ", "));
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString().replaceAll(",", ", "));
		}
	}
}
