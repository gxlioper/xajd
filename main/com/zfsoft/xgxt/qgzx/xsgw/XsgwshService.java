/**
 * @部门:学工产品事业部
 * @日期：2013-6-18 上午11:46:42 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.AllExecute;
import com.zfsoft.xgxt.base.extend.IExecute;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import xsgzgl.qgzx.cssz.QgzxCsszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述:
 * @作者： zhangjw
 * @时间： 2013-6-18 上午11:46:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsgwshService extends SuperServiceImpl<XsgwshForm, XsgwshDAO> {

	private XsgwshDAO dao = new XsgwshDAO();

	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 标志没有通过 需验证权限审核
	 */
	private final String _WTGSJ = "-1";

	public XsgwshService() {
		super.setDao(dao);
	}
	/**
	 * 
	 * @描述:是否有审批流程中数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-27 上午10:45:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @return
	 * boolean 返回类型 
	 */
	public Boolean isHaveSplcSj(String splc,String type) {
		List<HashMap<String, String>> list = dao.getSplcing(splc,type);
		return null == list || list.size() <= 0 ? false : true;
	}

	/**
	 * @描述:TODO保存审核结果
	 * @作者：zhangjw
	 * @日期：2013-6-26 下午02:25:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 */
	@TransactionControl
	public boolean saveSh(XsgwshForm form, User user) throws Exception{

		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShzt());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_xsgwsh.do?method=xsgwshCx");
		model.setTzljsq("qgzx_wdgwsq.do?method=wdgwsqCx");
//		model.setZd2(form.getOldgwdm());

		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			XsgwshForm upForm = new XsgwshForm();
			upForm.setSqbh(form.getSqbh());
			upForm.setShzt(zhzt);
			// 原业务
			reuslt = dao.runUpdateNotCommit(upForm, form.getSqbh());
			if (zhzt.equals(Constants.OPEN)) {
				HashMap<String, String> xssqxxMap = dao.getXsSqxx(model
						.getYwid());
				reuslt = dao.bcGwxs(form.getGwdm(), xssqxxMap.get("xh"),
						xssqxxMap.get("sqbh"));
			}
		
		return reuslt;
	}
	public boolean zjZgxs(XsgwshForm form) throws SQLException {
		return dao.zjXsgw(form.getGwdm(),form.getXh());
	}
	/**
	 * 
	 * @描述: 批量审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-19 下午05:29:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 *            审核id数组
	 * @param shgws
	 *            审核岗位数组
	 * @param showMessage
	 *            提示信息
	 * @param shyj
	 *            审核意见
	 * @param shzt
	 *            审核状态
	 * @param user
	 *            用户
	 * @return List<HashMap<String,String>> 返回类型
	 */
	@TransactionControl
	public List<HashMap<String, String>> plsh(String[] ids, String[] shgws,String[] bmlbs,
			String[] showMessage, final String shyj, final String shzt,
			final User user) throws Exception {
		List<Object> data = new ArrayList<Object>();
		List<HashMap<String, String>> message = new ArrayList<HashMap<String, String>>();
		int i = 0;
		Integer btgcgts = 0;// 不通过处理完成条数
		// 合并业务数据
		for (String id : ids) {
			XsgwshForm xf = getModel(id);
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("id", id);
			hm.put("gwid", shgws[i]);
			hm.put("bmlb", bmlbs[i]);
			hm.put("message", showMessage[i]);
			hm.put("splc", xf.getSplc());
			// 如果是不通过，直接进行审核操作，并记录审核操作返回信息
			if (Constants.SH_BTG.equals(shzt)) {
				xf.setGwid(hm.get("gwid"));
				xf.setBmlb(hm.get("bmlb"));
				xf.setShzt(shzt);
				xf.setShyj(shyj);
				xf.setOldgwdm(xf.getGwdm());
				if (!saveSh(xf, user)) {
					HashMap<String, String> mes = new HashMap<String, String>();
					mes.put("message", showMessage[i] + "[审核操作失败!]");
					// 对应前台提示信息显示，根据需要调整
					mes.put(AllExecute._EXECUTE_CHECK_TYPE, "btg");
					message.add(mes);
				} else {
					btgcgts++;
				}
			}
			data.add(hm);
			i++;
		}

		// 初始化批量处理数据
		AllExecute ae = new AllExecute();
		message = ae.execute(new IExecute() {
			public String allCheck(Object data) {
				String message = "true";
				List<HashMap<String, String>> list = (List<HashMap<String, String>>) data;
				try {
					// 暂时是所有级别都进行验证
					message = checkTgrs(list);
				} catch (Exception e) {
					message += "检测岗位总需求人数错误";
					throw new RuntimeException("检测岗位总需求人数错误" + e.getMessage());
				}
				return message;
			}

			public String execute(Object data) {
				XsgwshForm xf = null;
				String message = null;
				try {
					HashMap<String, String> hm = (HashMap<String, String>) data;
					xf = getModel(hm.get("id"));
					xf.setGwid(hm.get("gwid"));
					xf.setBmlb(hm.get("bmlb"));
					xf.setShzt(shzt);
					xf.setShyj(shyj);
					xf.setOldgwdm(xf.getGwdm());
					// 业务验证
					message = yzjb(xf, false);
					if (AllExecute._EXECUTE_TRUE.equals(message)) {
						message = yzsh(xf).getString("message");
						if (AllExecute._EXECUTE_TRUE.equals(message)) {
							// 通过进行审核通过
							if (!saveSh(xf, user)) {
								message = "插入失败！";
							}
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("验证是否可以审核通过错误" + e.getMessage());
				}
				return message;
			}

			public String message(Object data, String ywMessage) {
				HashMap<String, String> hm = (HashMap<String, String>) data;
				return hm.get("message");
			}
		}, data, false);
		// 如果包含错误数据
		if (ae.isHaveError()) {
			// 进行数据回滚
		}
		// 记录成功条数
		HashMap<String, String> cgts = new HashMap<String, String>();
		cgts.put("cgts", ae.getSuccessNumber().toString());
		message.add(cgts);
		return message;
	}

	public boolean saveLzSh(XsgwshForm form, User user) throws Exception{

		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
//		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_xsgwsh.do?method=xslzshCx");
		model.setTzljsq("qgzx_xsgwsh.do?method=zgxsList");

		boolean reuslt = false;

		String zhzt = shlc.runAuditing(model);
		XsgwshForm upForm = new XsgwshForm();
		upForm.setSqbh(form.getSqbh());
		upForm.setShzt(zhzt);

		reuslt = dao.updateLzSh(upForm);
		if (zhzt.equals(Constants.YW_TG)) {
			reuslt = dao.updateGwxs(form.getGwdm(),form.getXh(),form.getSqly());
		}

		return reuslt;
	}
	public int lzplsh(String[] ids, String[] shgws,final String shyj, final String shzt,
											  final User user) throws Exception {

		Integer btgcgts = 0;// 不通过处理完成条数

		for (int i=0;i<ids.length;i++) {
			XsgwshForm xf = dao.getLzModel(ids[i]);
			xf.setGwid(shgws[i]);
			xf.setShjg(shzt);
			xf.setShyj(shyj);

			boolean isSuccess = this.saveLzSh(xf,user);
			if(!isSuccess){
				btgcgts++;
			}
		}

		return btgcgts;
	}
	/**
	 * 
	 * @描述:检查审核通过是否超过岗位所需人数
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-19 下午05:26:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 */
	public String checkTgrs(List<HashMap<String, String>> list)
			throws Exception {
		String id = null;
		String gwid = null;
		String bmlb = null;
		String message = "true";
		String splc=null;
		WdgwsqDAO wd = new WdgwsqDAO();
		WdgwsqService ws = new WdgwsqService();
		HashMap<String, String> map = ws.getCsszb();
		HashMap<String, Integer> tgsl = new HashMap<String, Integer>();
		String gwmc = null;
		for (HashMap<String, String> hm : list) {
			id = hm.get("id");
			gwid = hm.get("gwid");
			bmlb = hm.get("bmlb");
			gwmc = hm.get("message");
			splc=hm.get("splc");
			// 暂时和页面耦合，页面提示信息更改这需要对应更改（如果再查询数据库，效率很低）
			gwmc = gwmc.substring(gwmc.indexOf("[") + 1, gwmc.lastIndexOf("]"));
			XsgwshForm xf = getModel(id);
			xf.setGwmc(gwmc);

			// 当前用户非验证岗位 则不进行验证。
			String yzgw = null;
			if("1".equals(bmlb)){
			 	yzgw = map.get("rskzjb");
			} else if("5".equals(bmlb)){
				yzgw = map.get("yjrskzjb");
			}
			
			HashMap<String, String> gwxx = wd.getGwxx(xf.getGwdm());
			String xqrs = gwxx.get("xqrs");// 需求总人数
			if (!ws.isCheck(xf.getSplc(), yzgw, gwid)) {
				// 记录当前单位岗位审核通过人数+1
				// 不记录。因为如果不是验证级别之后的权限 审核通过不应该计算
				// Integer sl=tgsl.get(xf.getGwdm());
				// sl=null==sl?0:sl;
				// tgsl.put(xf.getGwdm(), sl+1);
				continue;
			} else {
				Integer tgrs = wd.getGwShtgRs(splc,gwid, xf.getGwdm());
				// 获取当前审核通过人数+已审核通过人数 为实际通过人数
				Integer jlrs = tgsl.get(xf.getGwdm());
				jlrs = null == jlrs ? 0 : jlrs;
				tgrs += jlrs;
				if (tgrs >= Integer.parseInt(xqrs) && !xqrs.equals("0")) {
					message = "[" + xf.getGwmc() + "]已超岗位所需人数";
					break;
				} else {
					// 记录当前单位岗位审核通过人数+1
					Integer sl = tgsl.get(xf.getGwdm());
					sl = null == sl ? 0 : sl;
					tgsl.put(xf.getGwdm(), sl + 1);
				}
			}
		}
		return message;
	}

	/**
	 * 
	 * @描述:
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-18 上午11:12:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shr
	 * @param shid
	 * @throws Exception void 返回类型
	 */
	public boolean cxRollBack(String shr, String shid) throws Exception {
		boolean isOk = true;
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		XsgwshForm xf = getModel(ywid);
		if (cxsqgw(shr, xf, xf.getSqbh(), xf.getGwdm(), gwid)) {
			isOk = dao.runUpdate(xf);
		}
		return isOk;
	}

	public boolean cxsqgw(String userid, XsgwshForm xf, String sqbh,
			String ywGwdm, String cxGwdm) {
		boolean isChange = false;
		HashMap<String, String> oldSpxx = dao.getSjTzShxx(sqbh, ywGwdm);
		WdgwsqDAO wd = new WdgwsqDAO(); 
		WdgwsqService ws = new WdgwsqService();
		HashMap<String, String> map = ws.getCsszb();
		String yzgw = map.get("rskzjb");
		if (null == cxGwdm) {
			return true;
		}
		//String yzxh = wd.getGwSpXh(xf.getSplc(), yzgw);
		//撤销后的审核岗位
		cxGwdm=ShlcUtil.getUpSpgw(xf.getSplc(), cxGwdm); 
		//String cxxh = wd.getGwSpXh(xf.getSplc(), cxGwdm);
		//if (Integer.parseInt(cxxh)>=Integer.parseInt(yzxh)) {
			xf.setShgw(cxGwdm);
			isChange = true;
		//}else{
		//	isChange = true;
		//	xf.setShgw("-1");
		//}
		/*
		 * if (null != oldSpxx && oldSpxx.size() > 0) { String spgw =
		 * oldSpxx.get("gwid"); String splc=oldSpxx.get("lcid"); ShlcDao sd=new
		 * ShlcDao(); String lastGwid=sd.getLastGwid(splc); //如果是最后一级撤销
		 * if(lastGwid.equals(cxGwdm)){
		 * //设置岗位id为上级（因为最后一级审核没有待审核数据，所以记录的调岗在上级数据中）
		 * cxGwdm=ShlcUtil.getUpSpgw(splc, cxGwdm); } //
		 * 要退回的岗位小于更改申请的岗位则需要把信息进行回滚 if (cxGwdm.equals(spgw)) {
		 * xf.setGwdm(oldSpxx.get("zd2"));// 退回为原来申请的岗位 isChange = true; } }
		 */if (null != oldSpxx && oldSpxx.size() > 0) {
			xf.setGwdm(oldSpxx.get("zd2"));
			isChange = true;
		}
		return isChange;
	}

	/**
	 * 
	 * @描述: 退回岗位
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-17 下午06:47:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xf
	 *            要退回更新的form
	 * @param splc
	 *            对应审批流程
	 * @param thgwdm
	 *            退回岗位代码
	 * @param sqbh
	 *            申请编号
	 * @param nowGwdm
	 *            当前申请的岗位id void 返回类型
	 */
	public boolean thsqgw(String userId, XsgwshForm xf, String splc,
			String thgwdm, String sqbh, String nowGwdm) {
		boolean isChange = false;
		Map<String, String> oldSpxx = dao.getOldShxx(userId, xf.getSplc(),
				sqbh, nowGwdm);
		if (null != oldSpxx && oldSpxx.size() > 0) {
			String spgw = oldSpxx.get("gwid");
			WdgwsqDAO dao = new WdgwsqDAO();
			String oldxh = dao.getGwSpXh(splc, spgw);
			String thxh = dao.getGwSpXh(splc, thgwdm);
			// 要退回的岗位小于更改申请的岗位则需要把信息进行回滚
			if (Integer.parseInt(thxh) < Integer.parseInt(oldxh)) {
				xf.setGwdm(oldSpxx.get("zd2"));// 退回为原来申请的岗位
				isChange = true;
			}
		}
		return isChange;
	}

	/**
	 * @描述:审核前验证数据有效性
	 * @作者：zhangjw
	 * @日期：2013-6-27 上午11:23:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws SQLException
	 *             String 返回类型
	 */
	public JSONObject yzsh(XsgwshForm form) throws Exception {
		Map<String, String> resultmap = new HashMap<String, String>();
		WdgwsqService wdService = new WdgwsqService();
		WdgwsqForm model = new WdgwsqForm();
		model.setGwdm(form.getGwdm());
		model.setXh(form.getXh());
		model.setSplc(form.getSplc());
		int xszggwsl = wdService.getXszggwsl(model);
		HashMap<String, String> map = wdService.getCsszb();
		String xsgwsqsplc = map.get("xsgwsqsplc");
		String message = "";
		if (xszggwsl > 0) {
			message = "学生已经在岗，无法审核通过";
		}/*
		 * else if (xszgsl >= Integer.parseInt(xsgws) && Integer.valueOf(xsgws)
		 * != 0) { message = "此学生已经有" + xszgsl + "个岗位，超过学生最大岗位数"; }
		 */
		else if (xsgwsqsplc == null || "".equals(xsgwsqsplc)) {
			message = "还没有定义审批流程不能保存";
		} else {
			// 验证申请的岗位是否已经达到上限
			if (model.getGwdm() != null && !model.getGwdm().equals("")) {
				message = wdService.yzgwxx(model.getGwdm(), form.getXh());
			} else {
				message = "true";
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap);
	}
	/**
	 * 
	 * @描述:重置人数控制
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-13 下午03:09:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public boolean resetRsKz() throws Exception {
		WdgwsqDAO sqdao = new WdgwsqDAO();
		return sqdao.resetRsKz(_WTGSJ);
	}

	/**
	 * 
	 * @描述:验证数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-26 上午11:08:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 *            数据model
	 * @param isTj
	 *            是否提交使用
	 * @return
	 * @throws SQLException
	 *             String 返回类型
	 */
	public String yzjb(XsgwshForm model, boolean isTj) throws SQLException {
		WdgwsqService wdService = new WdgwsqService();
		String shgw = ShlcUtil.getDqGw(model.getSqbh());
		HashMap<String, String> map = wdService.getCsszb();
		String yzgw = "";
		if("1".equals(model.getBmlb()))
			yzgw = map.get("rskzjb");
		if("5".equals(model.getBmlb()))
			yzgw = map.get("yjrskzjb");


		String message = wdService.yzjb(model.getXh(), model.getSplc(), model
				.getGwdm(), yzgw, shgw, isTj);
		return message;
	}

	/**
	 * @描述:最后一级撤销审核
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param shlc
	 *            审核流程ID
	 * @param ssydsqid
	 *            申请ID
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean cancel(String shlc, String sqbh) throws Exception {
		XsgwshForm model = new XsgwshForm();
		model.setSqbh(sqbh);
		model.setShzt(Constants.SHZ);
		boolean result = dao.runUpdate(model, sqbh);
		if (result) {
			result = dao.delJgForSq(sqbh);
		}
		return result;
	}
	public boolean lzcancel(String shlc, String sqbh) throws Exception {
		XsgwshForm model = new XsgwshForm();
		model.setSqbh(sqbh);
		model.setShzt(Constants.SHZ);
		boolean result = dao.updateLzSh(model);
		if (result) {
			XsgwshForm xf = dao.getLzModel(sqbh);
			result = dao.updateCancelTgGwxs(xf.getGwdm(),xf.getXh());
		}
		return result;
	}

	public List<HashMap<String,String>> getZgxsList(XsgwshForm t, User user) throws Exception {
		return dao.getZgxsList(t,user);
	}

	public HashMap<String,String> getZgxx(String gwdm,String xh){
		return dao.getZgxx(gwdm,xh);
	}
	public boolean saveLzSq(XsgwshForm model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_SHZ);
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		model.setSplc(splcMap.get("xslzsplc"));
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		boolean flag = dao.insertLzxx(model);
		if(flag){
			flag = shlc.runSubmit(guid, model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xslzshCx",
					"qgzx_wdgwsq.do?method=wdqggwCx");
		}
		return flag;
	}
	public int savePlLzSq(XsgwshForm model) throws Exception {

		String data = URLDecoder.decode(URLDecoder.decode(model.getJgData(),"UTF-8"),"UTF-8");
		List<Map> dataList = com.alibaba.fastjson.JSON.parseArray(data,Map.class);
		XsgwshForm t;
		int result = 0;
		for(int i=0;i<dataList.size();i++){
			Map a = dataList.get(i);
			t = new XsgwshForm();
			t.setGwdm((String)a.get("gwdm"));
			t.setXh((String)a.get("xh"));
			t.setSqly(model.getSqly());
			if(!this.saveLzSq(t)){
				result++;
			}
		}
		return result;
	}

	public List<HashMap<String,String>> getLzxsList(XsgwshForm model,User user)throws Exception {
		return dao.getLzxsList(model,user);
	}

	public HashMap<String,String> getLzModel(XsgwshForm model){
		return dao.getLzModel(model);
	}

	public List<HashMap<String,String>> getGzscList(XsgwshForm model) throws Exception {
		return dao.getGzscList(model);
	}
}
