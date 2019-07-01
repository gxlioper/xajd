/**
 * @部门:学工产品事业部
 * @日期：2016-3-30 下午04:50:37 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgDao;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgForm;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgService;
import com.zfsoft.xgxt.xsztz.xntzjg.sq.JcftzSqDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-30 下午04:50:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzShService extends SuperServiceImpl<JcftzShForm, JcftzShDao>{
	private JcftzShDao dao = new JcftzShDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private JcftzJgDao jcftzJgDao = new JcftzJgDao();
	private JcftzJgService jcftzJgService = new JcftzJgService();
	private JcftzSqDao jcftzSqDao = new JcftzSqDao();
	
	/** 
	 * @描述:保存审核
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 上午11:53:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSh(JcftzShForm form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getRdsplc());
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
		model.setYwid(form.getRdsqid());
		model.setSqrid(form.getXmdm());
		model.setTzlj("sztz_jcftz_sh.do");
		model.setTzljsq("sztz_jcftz_sq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			JcftzShForm sbshForm = new JcftzShForm();
			sbshForm.setRdsqid(form.getRdsqid());
			sbshForm.setXfrdsqzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getRdsqid());
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				JcftzJgForm jcftzJgForm = new JcftzJgForm();
				jcftzJgForm.setLylcywid(form.getRdsqid());
				jcftzJgForm.setSjly("1");
				jcftzJgForm.setXmdm(form.getXmdm());
				jcftzJgForm.setXfrdjgzt("1");
				jcftzJgDao.updateRenDing(jcftzJgForm);
				if(jcftzJgService.isHaveRecord(jcftzJgForm)){
					//如果结果表中存在数据，先删除再插入
					jcftzJgService.delForSq(jcftzJgForm);
					if("1".equals(form.getCsms())){
						updateRyxx(jcftzJgForm);
					}else if("2".equals(form.getCsms())){
						updateTtxx(jcftzJgForm);
					}else{
						return false;
					}
										
				}else if("1".equals(form.getCsms())){
					updateRyxx(jcftzJgForm);
				}else if("2".equals(form.getCsms())){
					updateTtxx(jcftzJgForm);
				}else{
					return false;
				}
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @描述:在结果表中插入相关人员的调整后基础分
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 下午02:25:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcftzJgForm
	 * @throws Exception
	 * void 返回类型 
	 * @throws 
	 */
	public void updateRyxx(JcftzJgForm jcftzJgForm) throws Exception{
		List<HashMap<String,String>> list = jcftzSqDao.getJcfTzRyxx(jcftzJgForm.getXmdm());
		for(HashMap<String,String> map :list){
			jcftzJgForm.setJxdm(map.get("jxdm"));
			jcftzJgForm.setTzhjcf(map.get("tzhjcf"));
			jcftzJgForm.setSfqq(map.get("sfqq"));
			jcftzJgForm.setXh(map.get("xh"));
			jcftzJgForm.setXmdm(map.get("xmdm"));
			//备注1-5
			jcftzJgForm.setBz1(map.get("bz1"));
			jcftzJgForm.setBz2(map.get("bz2"));
			jcftzJgForm.setBz3(map.get("bz3"));
			jcftzJgForm.setBz4(map.get("bz4"));
			jcftzJgForm.setBz5(map.get("bz5"));
			jcftzJgService.runInsert(jcftzJgForm);			
		}
	}
	
	/** 
	 * @描述:撤销
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 下午04:11:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String cxshnew(String ywid, JcftzShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getRdsplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;
	}
	
	/** 
	 * @描述:最后一级撤销
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 下午04:28:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(JcftzShForm myForm) throws Exception {
		myForm.setXfrdsqzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm,myForm.getRdsqid());
			// 删除结果库中的数据
		result = jcftzJgDao.delForSqBylcId(myForm.getRdsqid());
		return result;
	}
	
	/** 
	 * @描述:批量审核保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 下午05:34:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(JcftzShForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			JcftzShForm form = dao.getModel(ids[i]);
			JcftzShForm model = new JcftzShForm();
			model.setRdsplc(form.getRdsplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRdsqid(ids[i]);
			model.setXmdm(form.getXmdm());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setCsms(form.getCsms());
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
	
	/**
	 * 
	 * @描述: 审核通过之后，插入结果表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午05:27:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcftzJgForm
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void updateTtxx(JcftzJgForm jcftzJgForm) throws Exception{
		List<HashMap<String,String>> list = jcftzSqDao.getJcfTzTtCyxx(jcftzJgForm.getXmdm());
		ArrayList<String[]> paralist = new ArrayList<String[]>();
		for(HashMap<String,String> map :list){
			paralist.add(new String[]{map.get("xmdm"),map.get("xh"),map.get("tzhjcf"),map.get("jxdm"),map.get("sfqq"),jcftzJgForm.getLylcywid(),"1",
					map.get("bz1"),map.get("bz2"),map.get("bz3"),map.get("bz4"),map.get("bz5")});
		}
		dao.updateTtxx(jcftzJgForm, paralist);
	}
}
