package com.zfsoft.xgxt.jskp.sbsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.util.Log;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgDao;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgForm;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgService;
import com.zfsoft.xgxt.jskp.xmsb.JskpXmsbDao;
import com.zfsoft.xgxt.jskp.xmsb.JskpXmsbForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 纪实考评
 * @类功能描述: 申报审核
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-7-7 下午02:09:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JskpXmsbshService extends SuperServiceImpl<JskpXmsbshForm, JskpXmsbshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private JskpXmsbshDao dao = new JskpXmsbshDao();
	private static final String FFGZ_Y = "1"; //0：未选中，1：选中
	

	/**
	 * 
	 * @描述:加载审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 上午08:42:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSbshInfo(JskpXmsbshForm t) {
		return dao.getSbshInfo(t);

	}
	/**
	 * 
	 * @描述:审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 下午02:19:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(JskpXmsbshForm form, User user) {
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
		model.setTzlj("jskp_xmsh.do");
		model.setTzljsq("jskp_xmsb.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditingNotCommit(model);
			JskpXmsbshForm khshForm = new JskpXmsbshForm();
			khshForm.setSqid(form.getSqid());
			khshForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(khshForm, form.getSqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				JskpXmsbjgForm sbjgForm = new JskpXmsbjgForm();
				JskpXmsbjgService sbjgService = new JskpXmsbjgService();
				JskpXmsbForm xmsbForm = new JskpXmsbDao().getModel(form.getSqid());
				BeanUtils.copyProperties(sbjgForm, StringUtils.formatData(xmsbForm));
				sbjgForm.setJgid(xmsbForm.getSqid());
				sbjgForm.setSqid(xmsbForm.getSqid());
				sbjgForm.setFs(form.getFs());
				sbjgForm.setSjly("1");
				/*参数设置为0是，不经过立项申请、审核，直接插入图片*/
				if("0".equals(new CsszService().getSfsh())){
					sbjgForm.setFjid(xmsbForm.getFjid());
				}
				reuslt = sbjgService.runInsert(sbjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 下午02:20:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(JskpXmsbshForm t, User user) throws Exception {
		JskpXmsbshForm model = new JskpXmsbshForm();
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
			boolean isSuccess = saveSh(model, user);
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
	 * 
	 * @描述:最后一级撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 下午02:26:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(JskpXmsbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			JskpXmsbjgDao jgdao = new JskpXmsbjgDao();
			jgdao.delSbjgById(myForm.getSqid());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @描述:根据ID查询当下有多少审批岗位
	 * @作者：cq [工号：785]
	 * @日期：2017-8-21 下午05:44:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
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
	 * 
	 * @描述:根据id获取前一次分数
	 * @作者：cq [工号：785]
	 * @日期：2017-8-22 下午04:27:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
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
	 * @描述: 根据申请id取审核状态表中的最新一级 的分数（zd3）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-17 下午02:08:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getLevelXxBySqid(JskpXmsbshForm t) throws Exception{
		return dao.getLevelXxBySqid(t);
	}
}
