/**
 * @部门:学工产品事业部
 * @日期：2017-5-22 下午05:53:44 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgDao;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgForm;
import net.sf.json.JSONArray;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 评奖评优-我的评奖-奖项审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-22 下午05:42:24 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmshService extends SuperServiceImpl<XmshForm,XmshDao>{
	private XmshDao dao = new XmshDao();
	public static final String DEFAULT_PMFS = "fs";// 默认排名方式
	public static final String SQSH = "1";
	private ShlcInterface shlc = new CommShlcImpl();
	public XmshService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 审核查询
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午03:32:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAudingListSingle(XmshForm t,User user) 
		throws Exception {
		CsszDao csszDao = new CsszDao();
		CsszForm csszForm = csszDao.getModel();

		List<HashMap<String, String>> zcxmList = dao.getZcxmList(csszForm.getXn());
		return dao.getAudingListSingle(t, user, DEFAULT_PMFS, zcxmList);
	}

	/** 
	 * @描述: 审核明细
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-24 下午02:28:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmshShmx(XmshForm model, User user) throws Exception {
		return dao.getXmshShmx(model,user);
	}
	
	/**
	 * @描述: 审核操作
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午03:34:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String saveZdPlsh(XmshForm t, User user) throws Exception {
		/*查询【申请ID、审核状态表的GWID、学号、审核状态表的LCID*/
		List<HashMap<String, String>> shjgList = dao.getZdshjgList(t,user);
		if(null==shjgList||shjgList.size()==0){
			return MessageUtil.getText("查询结果为空");
		}
		String[] ids = new String[shjgList.size()];
		String[] gwids = new String[shjgList.size()];
		String[] xhs = new String[shjgList.size()];
		String[] splcs = new String[shjgList.size()];
		
		for (int i = 0; i < shjgList.size(); i++) {
			ids[i] = shjgList.get(i).get("id");
			gwids[i] = shjgList.get(i).get("gwid");
			xhs[i] = shjgList.get(i).get("xh");
			splcs[i] = shjgList.get(i).get("lcid");
		}
		
		List<String> failXhs = new ArrayList<String>();
		List<String> bbccXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			XmshForm model = new XmshForm();
			/*申请id*/
			model.setId(ids[i]);
			
			HashMap<String, String> tmpMap  = new HashMap<String,String>();
			tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			XmshForm s = dao.getModel(model);
			if (tmpMap.get("xjzd2") == null || tmpMap.get("xjzd2").equals("")) {
				model.setXmdm(s.getXmdm());
				model.setPdjx(s.getXmdm());
			} else {
				model.setXmdm(tmpMap.get("xjzd2"));
				model.setPdjx(s.getXmdm());
			}
			
			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setThgw(null);
			model.setSplc(tmpMap.get("lcid"));
			model.setXh(s.getXh());
			model.setXn(s.getXn());
			model.setFjxx(s.getFjxx()); // 附件id
			/*后加的五个字段*/
			model.setWysp(s.getWysp());
			model.setSsdh(s.getSsdh());
			model.setGzzw(s.getGzzw());
			model.setGrxxjl(s.getGrxxjl());
			model.setCjkyqk(s.getCjkyqk());
			model.setDwrs(s.getDwrs());
			
			Map<String, Object> resultMap = runAudingNotCheck(model, user);
			/*当resultMap的result返回值为false的时候，那么返回错误学号*/
			if(!(Boolean)resultMap.get("result")){
				failXhs.add(xhs[i]);
				/*当resultMap的result返回值为false的时候(此时为5个标兵判断值)，那么返回错误学号*/
				if(resultMap.get("msg").equals("false")){
					bbccXhs.add(xhs[i]);
				}
			}
		}
		
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			if(bbccXhs.isEmpty()){
				return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
			}else{
				/*当标兵判断返回错误的学号不为空时，返回message*/
				return MessageUtil.getText(MessageKey.PJPY_WDPJ_JXRYSH_FAIL, JSONArray.fromObject(bbccXhs).toString());
			}
			
		}
		
	}
	
	/**
	 * @描述: 审核操作
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午04:30:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public Map<String,Object> runAudingNotCheck(XmshForm t,User user) throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		/*审核操作Model初始化*/
		ShxxModel model = new ShxxModel();
		
		model.setShlc(t.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getGwid());
		model.setYwid(t.getId());
		
		model.setSqrid(t.getXh());
		model.setTzlj("xpjpy_wdpj_pjsh.do");
		model.setTzljsq("xpjpy_wdpj_pjsq.do");
		model.setZd1("评定奖项");
		model.setZd2(t.getPdjx());
		XmwhForm dcForm = new XmwhForm();
		XmwhDao xmdao = new XmwhDao();
		dcForm.setXmdm(t.getPdjx());
		dcForm = xmdao.getModel(dcForm);
		model.setZd3(dcForm.getXmmc());
		
		/*判断当前奖项是否已有审核中或通过记录*/
		String checkXm = dao.checkXhsqsh(t.getXn(),t.getPdjx(), t.getXh(),t.getId());
		if(Integer.valueOf(checkXm) > 0){
			throw new SystemException(MessageKey.PJPY_FAIL);
		}
		
		boolean result = false;
		try {
			/*判断流程序号*/
			HashMap<String,String> shxx =ShlcDao.getDqjbZdByCondition(t.getId(), user.getUserName(), t.getSplc(), "sh");
			/*审核前一步骤的项目代码*/
			String tzhxmdm = "";
			/*获取审批步骤表的 序号，主要是审核控制级别的序号*/
			String rskzxh = dao.getRskzXh(t.getPdjx());
			
			/*如果审核通过，并且审核级别大于等于控制级别，更新调整后项目代码*/
			if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!=null) && new Integer(shxx.get("xh")).intValue() >= new Integer(rskzxh).intValue()){
				tzhxmdm = t.getPdjx();
			}
			
			/*===============5个标兵从这里开始判断{社会工作、创新创业、公益服务、对外交流、文体活动标兵}=================*/
			/* 1、根据控制级别判断是从哪一级控5个标兵人数
			 * 2、审核操作为通过才进入判断
			 * 3、序号不为空
			 * 4、当前审核操作的序号和评奖项目的人数控制级别序号一样
			 */
			if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!= null) && rskzxh.equals(shxx.get("xh"))){
				/*审核操作判断5个标兵总和是否超过学院参评人数总数的35%*/
				String shgzbb = "社会工作标兵";
				String cxcybb = "创新创业标兵";
				String gyfwbb = "公益服务标兵";
				String dwjlbb = "对外交流标兵";
				String wthdbb = "文体活动标兵";
				String xmmc = dcForm.getXmmc();
				if(shgzbb.equals(xmmc) || cxcybb.equals(xmmc) || gyfwbb.equals(xmmc) || dwjlbb.equals(xmmc) || wthdbb.equals(xmmc)){
					/*学生所在学院的总参评人数的35%(保留小数),{5个标兵的原则上人数}*/
					float yzsrs = Float.parseFloat(dao.getXyCprsAll(t.getXh()));
					int yzsrsNum = (int)yzsrs;
					/*有的学院参评总数的35%有小数点应学校要求，祛小数点+1即可*/
					yzsrsNum += 1;
					
					/*学生所在学院已经通过的5个 标兵已通过次数*/
					float bbytgcs = Float.parseFloat(dao.getBbYtgCs(t.getXh(),t.getGwid()));
					int bbytgcsNum = (int)bbytgcs;
					/*因为总人数已经祛除小数点+1，新来的数据是现已通过的人数，那么加1再进行判断*/
					if(bbytgcsNum + 1 > yzsrsNum){
						/*返回的result为false，布尔型*/
						resultMap.put("result", result);
						/*返回的result为false，字符串*/
						resultMap.put("msg", "false");
						return resultMap;
					}
				}
			}
			/*===============5个标兵从这里结束判断{社会工作、创新创业、公益服务、对外交流、文体活动标兵}=================*/
			
			
			String zhzt = shlc.runAuditing(model);
			XmshForm upForm = new XmshForm();
			upForm.setId(t.getId());
			upForm.setShzt(zhzt);
			
			upForm.setTzhxmdm(tzhxmdm);
			upForm.setDqxmdm(t.getPdjx());
			result = dao.runUpdate(upForm, t.getId());
			if(result && zhzt.equals(Constants.SH_TG)){
				/*插入到结果表*/
				XmshForm  viewModel = dao.getModel(t.getId());
				XmwhForm xmwhModel = new XmwhDao().getModel(t.getPdjx());
				PjjgForm pjjgModel = new PjjgForm();
				/*评奖结果数据来源【0:导入、1:申请流、 2:后台增加】*/
				pjjgModel.setSjly("1");
				pjjgModel.setId(viewModel.getId());
				pjjgModel.setXmdm(t.getPdjx());
				pjjgModel.setXmje(xmwhModel.getXmje());
				pjjgModel.setXmmc(xmwhModel.getXmmc());
				pjjgModel.setXn(viewModel.getXn());
				pjjgModel.setSqly(viewModel.getSqly());
				pjjgModel.setSqsj(viewModel.getSqsj());
				pjjgModel.setXh(viewModel.getXh());
				pjjgModel.setXzdm(xmwhModel.getXzdm());
				pjjgModel.setLxdm(xmwhModel.getLxdm());
				pjjgModel.setLylcywid(t.getId());
				/*后加的五个字段*/
				pjjgModel.setWysp(viewModel.getWysp());
				pjjgModel.setSsdh(viewModel.getSsdh());
				pjjgModel.setGzzw(viewModel.getGzzw());
				pjjgModel.setGrxxjl(viewModel.getGrxxjl());
				pjjgModel.setCjkyqk(viewModel.getCjkyqk());
				pjjgModel.setDwrs(viewModel.getDwrs());
				/*学生参评班级*/
				HashMap<String, String> cpbjxx = dao.getCpbjListByXh(t.getXh(), t.getXn());
				pjjgModel.setCpnj(cpbjxx.get("nj"));
				pjjgModel.setCpxymc(cpbjxx.get("xymc"));
				pjjgModel.setCpzymc(cpbjxx.get("zymc"));
				pjjgModel.setCpbjmc(cpbjxx.get("bjmc"));
				pjjgModel.setCpxydm(cpbjxx.get("xydm"));
				pjjgModel.setCpzydm(cpbjxx.get("zydm"));
				pjjgModel.setCpbjdm(cpbjxx.get("bjdm"));
				
				pjjgModel.setLrr(user.getUserName()); //数据录入人(在这里是指审核人)
				pjjgModel.setYlzd5(t.getFjxx()); // 附件id
				PjjgDao pjjgDao = new PjjgDao();
				try {
					pjjgDao.runInsert(pjjgModel);
				} catch (Exception e) {
					logger.debug("评奖审核插入正式表失败："+pjjgModel.getId());
				}
			}
			
		}catch (Exception e) {
			resultMap.put("result", false);
			resultMap.put("msg", e.getMessage());
		}
		/*如果没什么问题的话，返回result为true*/
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * @描述: 检测人数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 上午11:44:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwid
	 * @param xmdm
	 * @param xh
	 * @param type
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void checkRskz(String gwid, String xmdm, String xh, String type) throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		Map<String, String> xmwhMap = xmwhDao.getDataById(xmdm);
		/*人数控制范围/级别*/
		String rskzfw = xmwhMap.get("rsfpfs");
		String xn = xmwhMap.get("xn");
		Map<String, String> rsszMap = dao.getRsszOne(xmdm, xh, xn);
		String xzrs = rsszMap.get("zzme");
		// 未设置就不控制
		if (StringUtil.isNull(xzrs)) {
			return;
		}
		String tgrs = null;
		tgrs = dao.getTgrsByXy(xn,gwid, xmdm, xh);
		if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))) {
			if("sh".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL, tgrs);
			}else if("cx".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL_CANCEL, tgrs);
			}
		}
	}
	
	/**
	 * @描述: 撤销操作
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 上午11:28:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cxshnew(String ywid, XmshForm model, User user)
		throws Exception {
		ShlcInterface service = new CommShlcImpl();
		// 判断流程序号
		HashMap<String, String> shxx = ShlcDao.getDqjbZdByCondition(ywid, user.getUserName(), model.getSplc(), "cx");
		// 审核前一步骤的项目代码
		String tzhxmdm = shxx.get("zd2");
		String rskzxh = dao.getRskzXh(tzhxmdm);
		String dqxmdm = shxx.get("sjxmdm"); // 上级审核项目代码
		String shzt = Constants.YW_SHZ;
		// 如果项目代码和当前用户项目代码不一致，判断是否有申请或通过记录
		String checkXm = dao.checkXhsqsh(model.getXn(),shxx.get("zd2"), model.getXh(),model.getId());

		if (Integer.valueOf(checkXm) > 0) {
			throw new SystemException(MessageKey.PJPY_FAIL);
		}
		//如果当前记录是审核不通过，并且审核级别大于等于控制级别
		int shxxXh = new Integer(shxx.get("xh"));
		
		if(Constants.SH_BTG.equals(shxx.get("shzt"))&& shxxXh > 1 && shxxXh >= new Integer(rskzxh).intValue()){
			String spgw = new ShlcDao().getBeforeGwid(shxx.get("splc"), shxx.get("gwid"));
			checkRskz(spgw,dqxmdm,model.getXh(), "cx");
		}
		if (new Integer(shxx.get("xh")).intValue() <= new Integer(rskzxh).intValue()) {
			tzhxmdm = "";
		}
		String message = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		// 回滚申请表当中的调整后项目
		dao.updateSqjl(ywid, dqxmdm, shzt);
		return message;
	}
	
	
	
	/**
	 * @描述: 最后级的撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 下午03:29:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param ywid
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancel(String shlc, String ywid, User user) throws Exception {
		XmshForm upForm = new XmshForm();
		upForm.setId(ywid);
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, ywid);
		if (result) {
			// 回滚结果库的数据
			PjjgDao jgdao = new PjjgDao();
			jgdao.delJgb(ywid);
		}
		return result;
	}
}
