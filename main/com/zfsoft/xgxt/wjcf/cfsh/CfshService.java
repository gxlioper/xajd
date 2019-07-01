/**
 * @部门:学工产品事业部
 * @日期：2013-10-28 上午10:57:14 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分上报审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-28 上午10:51:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfshService extends SuperServiceImpl<CfshForm, CfshDao> {
	
	private CfshDao dao=new CfshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public CfshService(){
		this.setDao(dao);
	}

	/** 
	 * @描述:(获取处分上报信息)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-28 下午03:20:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getCfsbxx(CfshForm model) {
		return dao.getCfsbxx(model);
	}
	public HashMap<String, String> getCfsbxxForjg(CfshForm model) {
		return dao.getCfsbxxForjg(model);
	}
	/** 
	 * @描述:(保存审核)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午04:46:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean ydsh(CfshForm form, User user) throws Exception{
		
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShzt());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		model.setThgw(form.getThgw());
		// 业务ID(多为申请ID)
		model.setYwid(form.getYwid());
		model.setSqrid(form.getXh());
		model.setTzlj("wjcf_cfsh.do?method=cxCfshList");
		model.setTzljsq("wjcf_cfsbgl.do?method=cxCfsbList");
		
		String zhzt = shlc.runAuditing(model);
		CfshForm shForm=new CfshForm();
		shForm.setCfid(form.getYwid());
		shForm.setSbjg(zhzt);
		if(!form.getKzzd1().equals(form.getCflbdm())){
			shForm.setKzzd1(form.getKzzd1());	//将原处分类别代码存入上报表中的kzzd1字段，
		}
//		5F061D3D90BC4133E0538713470AE88F
//		5F061D3D90BC4133E0538713470AE88F
//		5F06379A58B8412DE0538713470A4F70
		shForm.setCflbdm(form.getCflbdm());	//更新处分类别代码
		
		shForm.setKzzd2(form.getCfwh());	//更新处分发文到kzzd2
		shForm.setKzzd3(form.getCfsj());	//更新处分时间到kzzd3
		shForm.setKzzd5(form.getCfdqsj());	//更新处分到期日期到kzzd5

		boolean result=dao.runUpdate(shForm, shForm.getCfid());
		
		//最后一级岗位审核
		if(result && zhzt.equals(Constants.TG)){
			form.setSjly("1");
			if("12915".equals(Base.xxdm)){
				form.setCflsh(getLsh(form));
			}
			else{
				form.setCflsh("");
			}
			
			result= insertWjjgk(form);
			
		}
		return result;
	}
	/**
	 * 
	 * @描述:上海中侨处分通知书流水号获取
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-26 上午10:13:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getLsh(CfshForm model) {
		StringBuffer lshstr=new StringBuffer();
		HashMap<String,String> map =dao.getSbxx(model);
		List<HashMap<String, String>> lshList = dao.getLsh(map);
		if (lshList.size() > 0) {
			int lsh=0;
			System.out.println("lsh:"+lshList.get(0).get("cflsh"));
			lsh = Integer.parseInt(Base.isNull(lshList.get(0).get("cflsh"))?"0":lshList.get(0).get("cflsh"))+1;
			if(lsh>9&&lsh<100){
				lshstr.append("0").append(lsh);
			}
			else if(lsh>99){
				lshstr.append(lsh);
			}
			else{
				lshstr.append("00").append(lsh);
			}
		} else {
			lshstr.append("001");
		}
		return lshstr.toString();
	}
	/**
	 * @throws Exception  
	 * @描述:TODO(进入结果库)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午05:11:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	private boolean insertWjjgk(CfshForm form) throws Exception {
		
		return dao.insertWjjgk(form);
	}

	/** 
	 * @描述:(是否是最后一级岗位)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午06:59:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isZhgw(CfshForm model) {
		ArrayList<HashMap<String, String>> spgws=dao.getSplcgw(model);
		String spgw=spgws.get(spgws.size()-1).get("spgw");
		return model.getGwid().equalsIgnoreCase(spgw);
	}

	/** 
	 * @描述:(是否有可回滚)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 上午10:24:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean sfkcx(String ywid) {
		return dao.sfkcx(ywid);
	}

	/** 
	 * @描述:(最后一级审核回滚)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 上午10:41:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @param string
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(CfshForm myForm, String ywid) throws Exception{
		CfshForm shForm=new CfshForm();
		shForm.setCfid(ywid);
		shForm.setSbjg(Constants.SHZ);
		
		//获取权限判断值
		int cffwqxPd = this.cffwqxPd(dao.getZcFwqxByCfid(ywid),myForm.getSplcid(),myForm.getGwid());
		//是否是最后一级
		boolean isZhgw=this.isZhgw(myForm);
		//权限判断为2需还原，是最后一级且权限判断为0或1需还原
		if(cffwqxPd==2||(isZhgw&&(cffwqxPd==0||cffwqxPd==1))){
			shForm.setCflbdm(myForm.getKzzd1());
		}
		
//		shForm.setKzzd1("");	//这里不在置空，防止多次撤销问题
		
		boolean result=dao.runUpdate(shForm, shForm.getCfid());
		if(result){
			if(dao.getJgk(ywid)!=null&&""!=dao.getJgk(ywid)){
			int count=dao.deleteJgk(ywid);
			if(count>0){
				//设置代办信息
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfsh.do?method=cxCfshList");
				dbcz.setSqPath("wjcf_cfsbgl.do?method=cxCfsbList");
				dbcz.setGnmkMc("违纪处分审核");
				dbcz.setXmmc("处分审核");
				dbcz.shPush(ywid, splcid);*/
			}else{
				return false;
			}
			}
		}
		
		return result;
	}

	/** 
	 * @描述:(是否看撤销)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午12:07:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @param string
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public boolean canCancel(CfshForm model) {
		ShlcDao shlcDao=new ShlcDao();
		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		String nextGwid=shlcDao.getNextGwid(model.getSplcid(), shxx.get("gwid"));
		boolean result=false;
		if (StringUtils.isNull(nextGwid)) {
			result=dao.sfkcx(shxx.get("ywid"));
		}else{
			return true;
		}
		return result;
	}

	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(CfshForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfsbb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2014-4-23 下午05:32:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String savePlsh(CfshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcids = t.getSplcids();
		
		

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			CfshForm model = new CfshForm();
			model.setGuid(ids[i]);
			model.setGwid(gwids[i]);
			model.setYwid(ids[i]);
			model.setSplcid(splcids[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);
			model.setKzzd1(t.getKzzd1());
			model.setCflbdm(t.getCflbdm());
			model.setCfwh(t.getCfwh());
			model.setCfsj(t.getCfsj());
			model.setCfdqsj(t.getCfdqsj());
			boolean isSuccess = ydsh(model, user);

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

	/** 
	 * @描述:处分发文权限判断 0：未填写，1：等级低于权限岗位，2：等级等于权限岗位，3等级高于权限岗位
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月27日 下午7:05:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param splcid
	 * @param string2
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int cffwqxPd(String ffqx, String splcid, String gwid) {
		String shgwjb = dao.getShgwjb(splcid,gwid);
//		String qxwgjb = dao.getQxgwjb(splcid,cflbdm);
		String qxwgjb = dao.getShgwjb(splcid,ffqx);
		
		if(StringUtils.isNotNull(qxwgjb)){
			int s = Integer.parseInt(shgwjb);
			int q = Integer.parseInt(qxwgjb);
			return s<q?1:(s==q?2:3);
		}else{
			return 0;
		}
	}

	/**
	 * @描述:获得处分到期时间默认值，处分时间+处分期限
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月25日 下午2:28:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws Exception
	 */
	public String defaultCfdqsj(CfshForm model) throws Exception {
		String result = "";
		
		String cflbdm = model.getCflbdm();
		String cfsj = model.getCfsj();
		
		int yearValue = 0;
		int monthValue = 0;
		int dayValue = 0;
		
		//根据处分类别代码获取处分期限
		HashMap<String, String>  cflbInfo = null;
		if(StringUtils.isNotNull(cflbdm)){
			cflbInfo = new CflbdmwhDao().cflbInfoById(cflbdm);
		}
		
		if(cflbInfo!=null && cflbInfo.size()>0){
			if("1".equals(cflbInfo.get("sfszcfqx"))){
				String cfqx = cflbInfo.get("cfqx");
				if(StringUtils.isNotNull(cfqx)){
					String year = cfqx.split("-")[0];
					String month = cfqx.split("-")[1];
					String day = cfqx.split("-")[2];
					
					yearValue = Integer.parseInt(year);
					monthValue = Integer.parseInt(month);
					dayValue = Integer.parseInt(day);
					
				}
			}
		}
		
		
		//根据处分时间和处分期限计算处分到期日期
		if(!(yearValue==0&&monthValue==0&&dayValue==0)){
			if(StringUtils.isNotNull(cfsj)){
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(cfsj);
				calendar.setTime(date);
				
				calendar.add(Calendar.YEAR, yearValue);
				calendar.add(Calendar.MONTH, monthValue);
				calendar.add(Calendar.DATE, dayValue);
				
				result = sdf.format(calendar.getTime());
			}
		}else{
			result = "hidden";
		}
		
		return result;
	}

	public HashMap<String,String> getxsnl(String xh) {
		return dao.getxsnl(xh);
	}

	public HashMap<String,String> getbjrs(String bjdm) {
		return dao.getbjrs(bjdm);
	}
}
