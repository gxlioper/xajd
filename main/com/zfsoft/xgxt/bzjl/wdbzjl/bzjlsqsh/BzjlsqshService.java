/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:31:31 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjlsqsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgDao;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgModel;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszDao;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszDao;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-申请审核
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:31:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BzjlsqshService extends SuperServiceImpl<BzjlsqshModel, BzjlsqshDao> implements
		Constants {
	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	private BzjlsqshDao dao = new BzjlsqshDao();
	public static final String TJDW_CPZ = "cpz";
	public static final String TJDW_NJZY = "njzy";
	public static final String TJDW_BJ = "bj";
	public static final String DSH = "0";
	public static final String DEFAULT_PMFS = "bjpm";// 默认排名方式
	public static final String SQSH = "1";

	private ShlcInterface shlc = new CommShlcImpl();

	public BzjlsqshService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述: 奖项上报查询列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 下午02:49:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getJxsbList(BzjlsqshModel t, User user)
			throws Exception {

		String xmdm = t.getXmdm();

		// 评奖项目信息
		XmwhDao xmwhDao = new XmwhDao();
		XmwhModel xmwhModel = xmwhDao.getModel(xmdm);

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList(csszModel
				.getXn(), csszModel.getXq());
		// 前两级综测项目
		// List<HashMap<String,String>> zcxmList =
		// zcxmDao.getFirstAndSecondZcxm();
		// List<HashMap<String,String>> zcxmList = zcxmDao.getCurrentZfxm();

		String pmfs = xmwhModel.getZcfpm();

		if (StringUtil.isNull(pmfs)) {
			pmfs = DEFAULT_PMFS;
		}

		return dao.getJxsbList(t, user, pmfs, zcxmList);
	}
	public boolean updateModel(BzjlsqshModel model) throws Exception {
		return super.runUpdate(model);
	}
	/**
	 * 
	 * @描述: 奖项上报保存
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 下午01:50:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean saveJxsb(BzjlsqshModel model, String userName) throws Exception {

		String sqid = UniqID.getInstance().getUniqIDHash();

		// 项目信息
		XmwhDao xmwhDao = new XmwhDao();
		XmwhModel xmwhModel = xmwhDao.getModel(model.getXmdm());
		String splc = xmwhModel.getShlc();

		// 参数设置信息
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		model.setSqr(userName);
		model.setXn(csszModel.getXn());
		model.setXq(CsszService.getPjzq().get("xq"));
		model.setSqid(sqid);
		model.setSplc(splc);

		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			if(SFBJPY_Y){
				model.setShzt(Constants.YW_BJPYZ);
			}else{
				model.setShzt(Constants.YW_SHZ);
			}
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}

		// 保存申请信息
		boolean result = dao.runInsert(model);

		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (!SFBJPY_Y && result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(),
						"pj_jxsh.do", "pj_pjxmsq.do");
			}
		}

		return result;
	}

	/**
	 * 
	 * @描述: 查询已申请评奖项目列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-7 上午11:22:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYsqPjxmList(String xh, String xmdm)
			throws Exception {

		if (StringUtil.isNull(xh) || StringUtil.isNull(xmdm)) {
			return null;
		}

		// 查询不可兼得项目
		JdszDao jdszDao = new JdszDao();
		List<HashMap<String, String>> bjdxmList = jdszDao.getBjdxm(xmdm);
		Set<String> bjdXmdm = new HashSet<String>();

		for (HashMap<String, String> map : bjdxmList) {
			bjdXmdm.add(map.get("xmdm"));
		}

		// 已申请项目列表
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		// 检测已申请列表中是否有不兼得项目

		for (HashMap<String, String> map : ysqList) {
			if (bjdXmdm.contains(map.get("xmdm"))) {
				// 增加不可兼得标识
				map.put("bkjd", "yes");
			} else {
				map.put("bkjd", "no");
			}
		}

		return ysqList;
	}

	/**
	 * 
	 * @描述: 项目申请信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-1 上午09:07:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             Map<String,Object> 返回类型
	 */
	public Map<String, Object> getXmsqInfoList(String xh) throws Exception {

		// 开放申请且没有申请过的奖项
		List<HashMap<String, String>> wsqList = dao.getKsqInfoList(xh);
		// 当前学年、学期已申请的奖项
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		// 返回结果
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		TjszService tjszServcie = new TjszService();

		// 验证条件
		for (HashMap<String, String> pjxm : wsqList) {

			List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(
					pjxm.get("xmdm"), xh);
			// 校验条件，返回不符合条件的项目名称。
			CheckCondition check = new CheckStudentCondition();
			// 校验结果
			List<HashMap<String, String>> results = check.checkCondition(xh,
					conditions);

			resultMap.put(pjxm.get("xmdm"), results);
			// pjxm.put("checkResult", results);
		}

		resultMap.put("wsqList", wsqList);
		resultMap.put("ysqList", ysqList);
		return resultMap;
	}

	/**
	 * 
	 * @描述: 批量保存奖项申请
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-1 上午10:05:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean saveJxsq(String[] xmdm, BzjlsqshModel t) throws Exception {

		if (xmdm == null || xmdm.length == 0) {
			return false;
		}

		for (String pjxm : xmdm) {

			BzjlsqshModel model = new BzjlsqshModel();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(pjxm);
			model.setDqxmdm(pjxm);
			model.setType(t.getType());
			model.setSqid(t.getSqid());
			model.setYlzd1(t.getYlzd1()); 
			model.setYlzd2(t.getYlzd2()); 
			model.setYlzd3(t.getYlzd3()); 
			model.setYlzd4(t.getYlzd4()); 
			model.setYlzd5(t.getYlzd5()); // 附件id
			/**
			 * 徐州医药高等专科学校
			 */
			if("70002".equals(Base.xxdm)){
				model.setDjjl(t.getDjjl());
			}
			saveJxsb(model, t.getSqr());
		}
		return true;
	}

	/*
	 * 
	 * 描述: @see
	 * com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runDelete(java
	 * .lang.String[])
	 */
	public int runDelete(String[] values) throws Exception {

		// 删除申请记录
		int delNum = dao.delXmsq(values);

		if (0 != delNum) {
			// 删除审核记录
			dao.delShzt(values);
		}

		return delNum;
	}

	/**
	 * 
	 * @描述: 查询审核情况
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-2 上午08:38:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @deprecated
	 */
	public List<HashMap<String, String>> getShqkList(BzjlsqshModel t, User user)
			throws Exception {

		String tjdw = t.getTjdw();

		if (TJDW_BJ.equals(tjdw)) {
			return dao.getShqkByBj(t, user);
		} else if (TJDW_NJZY.equals(tjdw)) {
			return dao.getShqkByNjzy(t, user);
		} else {
			return dao.getShqkByCpz(t, user);
		}

	}

	/**
	 * 
	 * @描述: 用户审批岗位
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-2 上午10:27:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getSpgwList(User user) {

		return dao.getSpgwList(user);
	}

	/**
	 * 
	 * @描述: 查询审核学生列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-2 下午03:07:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getAudingList(BzjlsqshModel t, User user)
			throws Exception {

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList(csszModel
				.getXn(), csszModel.getXq());

		return dao.getAudingList(t, user, DEFAULT_PMFS, zcxmList);
	}

	/**
	 * 
	 * @描述:查询申请学生列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-11 下午02:16:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXssqList(BzjlsqshModel t, User user)
			throws Exception {

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList(csszModel
				.getXn(), csszModel.getXq());

		return dao.getXssqList(t, user, DEFAULT_PMFS, zcxmList);
	}

	/**
	 * 
	 * @描述:查询申请学生列表
	 * @作者：cq [工号：785]
	 * @日期：2013-12-10 下午02:16:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */

	public List<HashMap<String, String>> getAudingListSingle(BzjlsqshModel t,
			User user) throws Exception {

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList2(csszModel
				.getXn(), csszModel.getXq());

		return dao.getAudingListSingle(t, user, DEFAULT_PMFS, zcxmList);
	}

	/**
	 * 
	 * 
	 * @描述:得到最后级的审核信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-5 上午09:12:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param u
	 * @param bjdms
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getLastCheckStatus(User u,
			String bjdms, String xn, String xq) throws Exception {

		String[] bjdm = bjdms.split(",");
		String params = "";
		for (int i = 0; i < bjdm.length; i++) {
			params += "'" + bjdm[i] + "',";
		}
		if (params.length() > 0) {
			params = params.substring(0, params.length() - 1);
		}

		return dao.getLastCheckStatus(u, params, xn, xq);
	}

	public List<HashMap<String, String>> getLastCheckStatus(String bjdms,
			String xn, String xq) throws Exception {

		String[] bjdm = bjdms.split(",");
		String params = "";
		for (int i = 0; i < bjdm.length; i++) {
			params += "'" + bjdm[i] + "',";
		}
		if (params.length() > 0) {
			params = params.substring(0, params.length() - 1);
		}

		return dao.getLastCheckStatus(params, xn, xq);
	}

	/**
	 * 
	 * @描述:最后级的撤销
	 * @作者：obq[445]
	 * @日期：2013-12-5 上午09:18:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param ywid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancel(String shlc, String ywid, User user) throws Exception {
		BzjlsqshModel upForm = new BzjlsqshModel();
		upForm.setSqid(ywid);
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, ywid);

		if (result) {
			// 回滚结果库的数据
			BzjljgDao jgdao = new BzjljgDao();
			jgdao.delJgb(ywid);
		}
		return result;
	}

	/**
	 * 
	 * @描述: 查询申请记录所对应审核记录列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:32:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getSpjlList(String sqid) {
		return dao.getSpjlList(sqid);
	}
	
	public String batchSave(BzjlsqshModel t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		
		
		
		//15-5-15 新需求可根据高级查询条件批量审核
		if(!StringUtils.isArrayNotNull(ids)){
			String Shztl = t.getShzt();//先临时保存之前审核状态；
			t.setShzt(WSH); //未审核
			t.getPages().setPageSize(Integer.MAX_VALUE);  //查询不分页
			List<HashMap<String, String>> shjgList = getAudingListSingle(t,user);
			if(null==shjgList||shjgList.size()==0){
				return MessageUtil.getText("查询结果为空");
			}
			
			String[] idss = new String[shjgList.size()];
			String[] gwidss = new String[shjgList.size()];
			String[] xhss = new String[shjgList.size()];
			String[] splcss = new String[shjgList.size()];
			
			for (int i = 0; i < shjgList.size(); i++) {
				idss[i] = shjgList.get(i).get("sqid");
				gwidss[i] = shjgList.get(i).get("gwid");
				xhss[i] = shjgList.get(i).get("xh");
				splcss[i] = shjgList.get(i).get("splc");
			}
			//重新赋予学号
			ids=idss;
			gwids=gwidss;
			xhs=xhss;
			splcs=splcss;
			t.setShzt(Shztl);
		}

		// 先判断选择的学生总人数是否超过选择学生申请项目的人数控制
		if (t.getShzt().equals("1")) {
			String sqids = "";
			for (int i = 0; i < ids.length; i++) {
				sqids += "'" + ids[i] + "',";
			}
			if (sqids.length() > 0) {
				sqids = sqids.substring(0, sqids.length() - 1);
			}

			List<HashMap<String, String>> results = dao.getXzdrs(sqids);

			for (int i = 0; i < results.size(); i++) {
				HashMap<String, String> rmap = (HashMap<String, String>) results
						.get(i);
				if (new Integer(rmap.get("dqjb")).intValue() >= new Integer(
						rmap.get("kzjb")).intValue()) {

					HashMap<String, String> tmap = dao.getKzrsTgrsByXmdm(rmap
							.get("xmdm"), rmap.get("dqjb"), rmap.get("cpbm"),
							rmap.get("rsfpfs"));
					if (tmap != null && tmap.size() > 0) {
						if (new Integer(rmap.get("xzrs")).intValue() > new Integer(
								tmap.get("zzme")).intValue()
								- new Integer(tmap.get("ytggs")).intValue()) {
							return MessageUtil
									.getText(MessageKey.SYS_AUD_PERS_OUT);
						}
					}
				}
			}
		}

		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			BzjlsqshModel model = new BzjlsqshModel();
			model.setSqid(ids[i]);
			//评奖批量审核取上级审核调整的奖项
			String gwxh = dao.getXh(splcs[i], gwids[i]);
			HashMap<String, String> tmpMap  = new HashMap<String,String>();
			if(Integer.parseInt(gwxh)>1){
				//上级审核岗位序号
				String sjgwxh = String.valueOf(Integer.parseInt(gwxh)-1);
				String sjgwid = dao.getSjGwid(splcs[i], sjgwxh);
				 tmpMap = shlc.getShxxByCondition(ids[i],
						sjgwid);
			}else{
			 tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			}
			BzjlsqshModel s = dao.getModel(model);
			if (tmpMap.get("xjzd2") == null || tmpMap.get("xjzd2").equals("")) {
				model.setXmdm(s.getXmdm());
				model.setPdjx(s.getDqxmdm());
			} else {
				model.setXmdm(tmpMap.get("xjzd2"));
				model.setPdjx(s.getDqxmdm());
			}

			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setThgw(null);
			model.setSplc(tmpMap.get("lcid"));
			model.setXh(s.getXh());
			model.setXn(s.getXn());
			model.setXq(s.getXq());
			model.setYlzd5(s.getYlzd5()); // 附件id

			String isSuccess = runAuding(model, user);

			if (!isSuccess.equals("true")) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/**
	 * 
	 * @描述:批量审核
	 * @作者：445
	 * @日期：2013-12-9 下午03:00:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String savePlsh(BzjlsqshModel t, User user) throws Exception {
		String newXmdms = "";
		String newBjdms = "";
		if (t.getXmdms() != null && !t.getXmdms().equals("")) {
			String[] xmdms = t.getXmdms().split(",");

			for (int i = 0; i < xmdms.length; i++) {
				newXmdms += "'" + xmdms[i] + "',";
			}
			if (newXmdms.length() > 0) {
				newXmdms = newXmdms.substring(0, newXmdms.length() - 1);
			}

			String[] bjdms = t.getBjdms().split(",");
			for (int i = 0; i < bjdms.length; i++) {
				newBjdms += "'" + bjdms[i] + "',";
			}
			if (newXmdms.length() > 0) {
				newBjdms = newBjdms.substring(0, newBjdms.length() - 1);
			}

		} else {
			return "请选择需要批量审核的奖项！";
		}

		// 得到需要批量审核的数据集
		List<HashMap<String, String>> resultList = dao.getCanOperatData(t,
				newXmdms, newBjdms);
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0; i < resultList.size(); i++) {
			HashMap<String, String> mp = (HashMap<String, String>) resultList
					.get(i);
			BzjlsqshModel model = new BzjlsqshModel();
			model.setSplc(mp.get("splc"));
			model.setThgw(null);
			model.setGwid(mp.get("gwid"));
			model.setSqid(mp.get("sqid"));
			model.setXh(mp.get("xh"));
			model.setShyj(t.getShyj());
			if (mp.get("zd2") == null || mp.get("zd2").equals("")) {
				model.setPdjx(mp.get("xmdm"));
			} else {
				model.setPdjx(mp.get("zd2"));
			}

			model.setXn(mp.get("xn"));
			model.setXq(mp.get("xq"));
			model.setShjg("1");
			model.setXmdm(mp.get("xmdm"));
			String isSuccess = runAuding(model, user);

			if (!isSuccess.equals("true")) {
				failXhs.add(mp.get("xh"));
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/**
	 * 
	 * @描述: 保存审核操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:35:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public String runAuding(BzjlsqshModel t, User user) throws Exception{
		
		synchronized (user) {
			// 审核操作Model初始化
			ShxxModel model = new ShxxModel();
			model.setShlc(t.getSplc());
			model.setShr(user.getUserName());
			model.setShyj(t.getShyj());
			model.setShzt(t.getShjg());
			model.setThgw(t.getThgw());
			model.setGwid(t.getGwid());
			model.setYwid(t.getSqid());
			
			model.setSqrid(t.getXh());
			model.setTzlj("pj_jxsh.do");
			model.setTzljsq("pj_pjxmsq.do");
			model.setZd1("评定奖项");
			model.setZd2(t.getPdjx());
			XmwhModel dcForm = new XmwhModel();
			XmwhDao xmdao = new XmwhDao();
			dcForm.setXmdm(t.getPdjx());
			dcForm = xmdao.getModel(dcForm);
			model.setZd3(dcForm.getXmmc());
			
			if("11488".equalsIgnoreCase(Base.xxdm)){
				model.setZd4("审核金额");
				model.setZd6(t.getShje());
			}
						
			//判断当前奖项是否已有审核中或通过记录
			String checkXm = dao.checkXhsqsh(t.getXn(), t.getXq(), t.getPdjx(), t.getXh(),t.getSqid());
			
			
			if(Integer.valueOf(checkXm) > 0){
				throw new SystemException(MessageKey.PJPY_FAIL);
				
			}
			
			boolean result = false;
				
				//判断流程序号
				HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(t.getSqid(), user.getUserName(), t.getSplc(), "sh");
				//审核前一步骤的项目代码
				String tzhxmdm = "";
				BzjlsqshDao sqshdao = new BzjlsqshDao();
				String rskzxh = sqshdao.getRskzXh(t.getPdjx());
				
				//如果审核通过，并且审核级别大于等于控制级别，更新调整后项目代码
				if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!=null)
						&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
					tzhxmdm = t.getPdjx();
					checkRskz(t.getGwid(),t.getPdjx(),t.getXh(),"sh");
				}
				
				//审核通过
				if(t.getShjg().equals(Constants.SH_TG)){
					BzjljgModel pjjgModel = new BzjljgModel();
					BzjljgDao pjjgDao = new BzjljgDao();
					BzjlsqshModel viewModel = dao.getModel(t.getSqid());
					XmwhModel xmwhModel = new XmwhDao().getModel(t.getPdjx());
					
					pjjgModel.setXmdm(t.getPdjx());
					pjjgModel.setXmje(xmwhModel.getXmje());
					pjjgModel.setXmmc(xmwhModel.getXmmc());
					pjjgModel.setXn(viewModel.getXn());
					if(!CsszService.XQKG.equalsIgnoreCase(viewModel.getXq())){
						pjjgModel.setXq(viewModel.getXq());
					}
					pjjgModel.setSqly(viewModel.getSqly());
					pjjgModel.setSqsj(viewModel.getSqsj());
					pjjgModel.setXh(viewModel.getXh());
					pjjgModel.setXzdm(xmwhModel.getXzdm());
					pjjgModel.setLxdm(xmwhModel.getLxdm());
					pjjgModel.setLylcywid(t.getSqid());
					//判断当前奖项结果中是否已有记录
					String checkJgxm = pjjgDao.checkExistForSave(pjjgModel);
					
					if(Integer.valueOf(checkJgxm) > 0){
						throw new SystemException(MessageKey.PJPY_FAIL);
					}
				}
				
				String zhzt = shlc.runAuditing(model);
				BzjlsqshModel upForm = new BzjlsqshModel();
				upForm.setSqid(t.getSqid());
				upForm.setShzt(zhzt);
				
				upForm.setTzhxmdm(tzhxmdm);
				upForm.setDqxmdm(t.getPdjx());
//				if("11488".equalsIgnoreCase(Base.xxdm)){
//					upForm.setYlzd1(upForm.getShje());
//				}
				result = dao.runUpdate(upForm, t.getSqid());
				if(result && zhzt.equals(Constants.SH_TG)){
					//插入到结果表
					BzjlsqshModel viewModel = dao.getModel(t.getSqid());
					//BeanUtils.copyProperties(pjjgModel, viewModel);
					XmwhModel xmwhModel = new XmwhDao().getModel(t.getPdjx());
					
					BzjljgModel pjjgModel = new BzjljgModel();
					pjjgModel.setSjly(SQSH);
					pjjgModel.setId(viewModel.getSqid());
					pjjgModel.setSqr(viewModel.getSqr());
					pjjgModel.setXmdm(t.getPdjx());
					pjjgModel.setXmje(xmwhModel.getXmje());
					pjjgModel.setXmmc(xmwhModel.getXmmc());
					pjjgModel.setXn(viewModel.getXn());
					if(!CsszService.XQKG.equalsIgnoreCase(viewModel.getXq())){
						pjjgModel.setXq(viewModel.getXq());
					}
					//徐州医药高等专科学校
					if("70002".equals(Base.xxdm)){
						pjjgModel.setDjjl(viewModel.getDjjl());
					}
					pjjgModel.setSqly(viewModel.getSqly());
					pjjgModel.setSqsj(viewModel.getSqsj());
					pjjgModel.setXh(viewModel.getXh());
					pjjgModel.setXzdm(xmwhModel.getXzdm());
					pjjgModel.setLxdm(xmwhModel.getLxdm());
					pjjgModel.setLylcywid(t.getSqid());
					//学生参评班级
					ZcfsService zcfsServcie = new ZcfsService();
					HashMap<String, String> cpbjxx = zcfsServcie.getCpbjListByXh(t.getXh(), t.getXn(), t.getXq());
					pjjgModel.setCpnj(cpbjxx.get("nj"));
					pjjgModel.setCpxymc(cpbjxx.get("xymc"));
					pjjgModel.setCpzymc(cpbjxx.get("zymc"));
					pjjgModel.setCpbjmc(cpbjxx.get("bjmc"));
					pjjgModel.setCpxydm(cpbjxx.get("xydm"));
					pjjgModel.setCpzydm(cpbjxx.get("zydm"));
					pjjgModel.setCpbjdm(cpbjxx.get("bjdm"));
					//华中师范大学个性化证书编号
					if("10511".equals(Base.xxdm)){
						BzjljgService pjjgService = new BzjljgService();
						pjjgModel.setYlzd1(pjjgService.getZsbm(pjjgModel));
					}
					//衢州学院个性化
					if("11488".equals(Base.xxdm)){
						pjjgModel.setYlzd1(t.getShje());
					}
					pjjgModel.setYlzd1(viewModel.getYlzd1());
					pjjgModel.setYlzd3(viewModel.getYlzd3());
					pjjgModel.setYlzd4(viewModel.getYlzd4());
					
					pjjgModel.setYlzd5(t.getYlzd5()); // 附件id
					BzjljgDao pjjgDao = new BzjljgDao();
					
						pjjgDao.runInsert(pjjgModel);
					
				}
				
				// 退回 申请人时
				if(result && SFBJPY_Y && zhzt.equals(Constants.SH_TH) && "-1".equals(t.getThgw())){
					BzjlsqshModel xszzSqshModelOld = new BzjlsqshModel();
					xszzSqshModelOld.setSqid(t.getSqid());
					BzjlsqshModel xszzSqshModelNew = dao.getModel(xszzSqshModelOld);
					JgcxDao jgcxDao = new JgcxDao();
					// 更新班级评议表
					boolean rs = jgcxDao.cxBjpyxzpy(xszzSqshModelNew.getXn(), xszzSqshModelNew.getXq(), xszzSqshModelNew.getXmdm(), xszzSqshModelNew.getXh());
					if(rs){
						// 更新结果查询表
						JgcxForm jgcxForm = new JgcxForm();
						jgcxForm.setSqid(xszzSqshModelNew.getSqid());
						jgcxForm.setTjzt("0");
						jgcxForm.setTjsj(" ");
						jgcxForm.setShzt(" ");
						jgcxDao.runUpdate(jgcxForm);
					}
				}
			return String.valueOf(result);
		}
		
	}

	// 检测人数
	private void checkRskz(String gwid, String xmdm, String xh, String type)
			throws Exception {

		XmwhDao xmwhDao = new XmwhDao();
		Map<String, String> xmwhMap = xmwhDao.getDataById(xmdm);
		// 人数控制范围/级别
		String rskzfw = xmwhMap.get("rsfpfs");
		String xn = xmwhMap.get("xn");
		String xq = xmwhMap.get("xq");

		RsszDao rsszDao = new RsszDao();
		// 人数控制相关Map
		Map<String, String> rsszMap = rsszDao.getRsszOne(xmdm, xh, rskzfw, xn,
				xq);

		String xzrs = rsszMap.get("zzme");
		// 未设置就不控制
		if (StringUtil.isNull(xzrs)) {
			return;
		}

		String tgrs = null;

		if (RSKZFW_NJXY.equals(rskzfw)) { // 年级学院
			tgrs = dao.getTgrsByNjxy(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_NJZY.equals(rskzfw)) { // 年级专业
			tgrs = dao.getTgrsByNjZy(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_XY.equals(rskzfw)) { // 学院
			tgrs = dao.getTgrsByXy(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_CPZ.equals(rskzfw)) { // 参评组
			tgrs = dao.getTgrsByCpz(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_BJ.equals(rskzfw)) { // 班级
			tgrs = dao.getTgrsByBj(xn, xq, gwid, xmdm, xh);
		} else { // 学校
			tgrs = dao.getTgrsByQx(xn, xq, gwid, xmdm, xh);
		}

		if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))) {
			if("sh".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL, tgrs);
			}else if("cx".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL_CANCEL, tgrs);
			}
		}

	}
	/**
	 * 批量获取奖项信息、学生信息
	 */
	public List<HashMap<String,String>> getPjxmXsxxList(String[] sqidArr) throws Exception{
		return dao.getPjxmXsxxList(sqidArr);
	}
	public HashMap<String,String> getPjxmXsxxMap(BzjlsqshModel t) throws Exception{
		return dao.getPjxmXsxxMap(t);
	}
	public List<HashMap<String,String>> getPjxmRsxx(String[] sqidArr) throws Exception{
		return dao.getPjxmRsxx(sqidArr);
	}
	public String getPjxmRsxxsx(String xmxx) throws Exception{
		return dao.getPjxmRsxxsx(xmxx);
	}
	public String getYsqXs(String xmxx) throws Exception{
		return dao.getYsqXs(xmxx);
	}
	
	public List<String> getXzjx() throws Exception{
		return dao.getXzjx();
	}


	/**
	 * 
	 * @描述: 保存取消审核操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午11:10:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean runCancel(BzjlsqshModel t, User user) throws Exception {

		BzjlsqshModel model = getModel(t.getSqid());

		// 先获取撤销目标奖项
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(t.getSqid(),
				user.getUserName(), model.getSplc(), "cx");

		// 如果项目代码和当前用户项目代码不一致，判断是否有申请或通过记录
		String	checkXm = dao.checkXhsqsh(t.getXn(), t.getXq(), shxx.get("zd2"), t
					.getXh(),t.getSqid());

		if (Integer.valueOf(checkXm) > 0) {

			throw new SystemException(MessageKey.PJPY_FAIL);
		}

		boolean result = runCancel(user.getUserName(), t.getSqid(), model
				.getSplc(), t.getShid());

		// 回滚申请表当中调整后奖项和当前奖项
		String tzhxmdm = shxx.get("zd2");
		BzjlsqshDao sqshdao = new BzjlsqshDao();
		String rskzxh = sqshdao.getRskzXh(tzhxmdm);
		String dqxmdm = shxx.get("zd2");
		String shzt = Constants.YW_SHZ;

		// 判断岗位步骤是否小于等于撤销目标奖项的人数控制级别，true 则清空调整后奖项。
		if (new Integer(shxx.get("xh")).intValue() <= new Integer(rskzxh)
				.intValue()) {
			tzhxmdm = "";
		}

		// 回滚申请表当中的调整后项目
		sqshdao.updateSqjl(t.getSqid(), tzhxmdm, shzt, dqxmdm);

		// 待办
		if (result) {
			Job job = Job.getJobInstance(t.getSqid(), t.getShid(),
					"pj_jxsh.do", "评奖评优");
			MyJobsManager manager = new MyJobsImpl();
			manager.updateJob(job);
		}

		return result;
	}

	// 执行取消审核操作
	private synchronized boolean runCancel(String shr, String ywid,
			String shlc, String gwid) throws Exception {
		/*
		 * -------------检测是否可以撤消审核----------- 先决条件：先确定自己最后审批给出的状态 撤消检测：
		 * 
		 * ①不通过 ② 通过，当前待审的记录是自己的下一级并且未审核 ③ 退回 ，不能撤消、抛异常
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);

		if (!BTG.equals(lastShzt) && StringUtil.isNull(nextSpgw)) {
			throw new SystemException(MessageKey.SYS_CANCEL_END);
		}

		boolean isFhtj = false;

		if (BTG.equals(lastShzt)) {
			// 不通过可以直接撤消
			isFhtj = true;
		} else if (TG.equals(lastShzt)) {
			// 删除参数岗位的下一级待审数据，删除成功可撤消否则不能。
			isFhtj = dao.delNextDsjl(ywid, nextSpgw);
		} else {
			// 退回或者不知名状态不被允许
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}

		// 符合撤消条件--更新参数岗位的状态为撤消、增加参数岗位待审数据
		if (isFhtj) {
			dao.updateShzt(ywid, gwid, CANCEL);
			return dao.insertDbjd(ywid, gwid);
		}

		return false;
	}

	/**
	 * 
	 * @描述: 查询审核班级列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 下午06:53:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getShbjList(BzjlsqshModel t) {

		if (TJDW_BJ.equals(t.getTjdw())) {
			return dao.getBjInfo(t.getBmdm());
		} else if (TJDW_NJZY.equals(t.getTjdw())) {
			return dao.getBjListByNjzy(t.getBmdm());
		} else {
			return dao.getBjListByCpz(t.getBmdm());
		}

	}

	/**
	 * 
	 * @描述: 审核情况高级查询配置路径
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-6 下午01:45:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return String 返回类型
	 */
	public String getShqkSearchPath(BzjlsqshModel t) {

		String path = null;

		if (TJDW_BJ.equals(t.getTjdw())) {
			path = "pj_shqk_bj.do";
		} else if (TJDW_NJZY.equals(t.getTjdw())) {
			path = "pj_shqk_njzy.do";
		} else {
			path = "pj_shqk_cpz.do";
		}

		return path;
	}

	/**
	 * 
	 * @描述: 根据申请ID返回待审核岗位ID
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-8 上午10:24:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return String 返回类型
	 * @throws
	 */
	public String getDsgw(String sqid) {

		if (StringUtil.isNull(sqid)) {
			return null;
		}

		return dao.getDsgw(sqid);
	}

	/**
	 * 
	 * @描述: 审核情况统计getShqkInfo
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午09:58:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,Object>> 返回类型
	 */
	public Map<String, Object> getShqkInfo(User user, String xmdm) {

		// 申请总人数
		int zrs = Integer.valueOf(dao.getSqrs(user, xmdm));
		// 各级审核情况
		List<HashMap<String, String>> shqkInfoList = dao
				.getShtjList(user, xmdm);
		// 申请人数、通过人数、通过率、各级审核统计情况
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("zrs", zrs);

		List<HashMap<String, String>> shqkList = new ArrayList<HashMap<String, String>>();

		if (zrs == 0) {
			resultMap.put("zztgrs", "0");
			resultMap.put("zztgl", "0%");
		}

		for (int i = 0, n = shqkInfoList.size(); i < n; i++) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.putAll(shqkInfoList.get(i));

			// double tgrs = Double.valueOf(shqkInfoList.get(i).get("tg"));
			// double bgtrs = Double.valueOf(shqkInfoList.get(i).get("btg"));
			// double dshrs = Double.valueOf(shqkInfoList.get(i).get("dsh"));
			double dcls = Double.valueOf(shqkInfoList.get(i).get("dcl"));
			double ycls = Double.valueOf(shqkInfoList.get(i).get("ycl"));
			DecimalFormat formater = new DecimalFormat("#.##%");

			// if (tgrs+bgtrs+dshrs == 0){
			// map.put("tgl", "0%");
			// map.put("btgl", "0%");
			// map.put("dshl", "0%");
			// } else {
			// double tgl = tgrs/(tgrs+bgtrs+dshrs);
			// double btgl =bgtrs/(tgrs+bgtrs+dshrs);
			// double dshl = dshrs/(tgrs+bgtrs+dshrs);
			//				
			// map.put("tgl", formater.format(tgl));
			// map.put("btgl", formater.format(btgl));
			// map.put("dshl", formater.format(dshl));
			// }

			if (dcls + ycls == 0) {
				map.put("dcll", "0%");
				map.put("ycll", "0%");
			} else {
				double dcll = dcls / (dcls + ycls);
				double ycll = ycls / (dcls + ycls);

				map.put("dcll", formater.format(dcll));
				map.put("ycll", formater.format(ycll));
			}

			shqkList.add(map);

			// if (i == n-1){
			// //最终通过率
			// double zztgl = tgrs/zrs;
			// resultMap.put("zztgrs",shqkInfoList.get(i).get("tg"));
			// resultMap.put("zztgl", formater.format(zztgl));
			// }
		}

		resultMap.put("shqkList", shqkList);
		return resultMap;
	}

	/**
	 * 
	 * @描述: 审核统计学生列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-12 下午02:26:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentsFromShtj(BzjlsqshModel model,
			User user) throws Exception {
		return dao.getStudentsFromShtj(model, user);
	}

	/**
	 * 
	 * @描述: 检测指定项目是否有申请审核记录
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:06:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isExistsFlowData(String xmdm) {

		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	}

	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	public String cxshnew(String ywid, BzjlsqshModel model, User user)
			throws Exception {

		ShlcInterface service = new CommShlcImpl();

		// 判断流程序号
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(ywid, user
				.getUserName(), model.getSplc(), "cx");

		// 审核前一步骤的项目代码
		String tzhxmdm = shxx.get("zd2");
		BzjlsqshDao sqshdao = new BzjlsqshDao();
		String rskzxh = sqshdao.getRskzXh(tzhxmdm);
		String dqxmdm = shxx.get("sjxmdm"); // 上级审核项目代码
		String shzt = Constants.YW_SHZ;

		// 如果项目代码和当前用户项目代码不一致，判断是否有申请或通过记录
		String checkXm = dao.checkXhsqsh(model.getXn(), model.getXq(), shxx
					.get("zd2"), model.getXh(),model.getSqid());

		if (Integer.valueOf(checkXm) > 0) {

			throw new SystemException(MessageKey.PJPY_FAIL);
		}
		
		//如果当前记录是审核不通过，并且审核级别大于等于控制级别
		int shxxXh = new Integer(shxx.get("xh"));
		if(Constants.SH_BTG.equals(shxx.get("shzt"))&& shxxXh > 1 && shxxXh >= new Integer(rskzxh).intValue()){
			String spgw = new ShlcDao().getBeforeGwid(shxx.get("splc"), shxx.get("gwid"));
			checkRskz(spgw,dqxmdm,model.getXh(), "cx");
		}
		
		if (new Integer(shxx.get("xh")).intValue() <= new Integer(rskzxh)
				.intValue()) {
			tzhxmdm = "";
		}


		String message = service.runCancelNew(user.getUserName(), model.getShid(), model
				.getSplc());
		
		// 回滚申请表当中的调整后项目
		sqshdao.updateSqjl(ywid, tzhxmdm, shzt, dqxmdm);
		
		return message;

	}

	/**
	 * @throws Exception  
	 * @描述:浙江理工个性化——审核明细
	 * @作者：cq [工号：785]
	 * @日期：2015-5-26 下午03:57:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getViweShmx(BzjlsqshModel model, User user) throws Exception {
		return dao.getViweShmx(model,user);
	}
	
	
	
	//浙江大学个性化审核，人数什么的都不判断
	public String saveZdPlsh(BzjlsqshModel t, User user) throws Exception {
		
		List<HashMap<String, String>> shjgList = dao.getZdshjgList(t,user);
		if(null==shjgList||shjgList.size()==0){
			return MessageUtil.getText("查询结果为空");
		}
		
		String[] ids = new String[shjgList.size()];
		String[] gwids = new String[shjgList.size()];
		String[] xhs = new String[shjgList.size()];
		String[] splcs = new String[shjgList.size()];
		
		for (int i = 0; i < shjgList.size(); i++) {
			ids[i] = shjgList.get(i).get("sqid");
			gwids[i] = shjgList.get(i).get("gwid");
			xhs[i] = shjgList.get(i).get("xh");
			splcs[i] = shjgList.get(i).get("lcid");
		}
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			BzjlsqshModel model = new BzjlsqshModel();
			model.setSqid(ids[i]);
			//评奖批量审核取上级审核调整的奖项
			String gwxh = dao.getXh(splcs[i], gwids[i]);
			HashMap<String, String> tmpMap  = new HashMap<String,String>();
//			if(Integer.parseInt(gwxh)>1){
//				//上级审核岗位序号
//				String sjgwxh = String.valueOf(Integer.parseInt(gwxh)-1);
//				String sjgwid = dao.getSjGwid(splcs[i], sjgwxh);
//				 tmpMap = shlc.getShxxByCondition(ids[i],
//						sjgwid);
//			}else{
			 tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			//}
			BzjlsqshModel s = dao.getModel(model);
			if (tmpMap.get("xjzd2") == null || tmpMap.get("xjzd2").equals("")) {
				model.setXmdm(s.getXmdm());
				model.setPdjx(s.getDqxmdm());
			} else {
				model.setXmdm(tmpMap.get("xjzd2"));
				model.setPdjx(s.getDqxmdm());
			}

			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setThgw(null);
			model.setSplc(tmpMap.get("lcid"));
			model.setXh(s.getXh());
			model.setXn(s.getXn());
			model.setXq(s.getXq());
			model.setYlzd5(s.getYlzd5()); // 附件id

			String isSuccess = runAudingNotCheck(model, user);
			if (!isSuccess.equals("true")) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/** 
	 * @描述:浙大统计获奖统计
	 * @作者：cq [工号：785]
	 * @日期：2015-5-28 下午01:40:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHjtjb(BzjlsqshModel model) throws Exception {
		return dao.getHjtjb(model);
	}
	/**
	 * 
	 * @描述:浙大个性化批量审核
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-4 上午09:50:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String runAudingNotCheck(BzjlsqshModel t, User user) throws Exception{
		
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		model.setShlc(t.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getGwid());
		model.setYwid(t.getSqid());
		
		model.setSqrid(t.getXh());
		model.setTzlj("pj_jxsh.do");
		model.setTzljsq("pj_pjxmsq.do");
		model.setZd1("评定奖项");
		model.setZd2(t.getPdjx());
		XmwhModel dcForm = new XmwhModel();
		XmwhDao xmdao = new XmwhDao();
		dcForm.setXmdm(t.getPdjx());
		dcForm = xmdao.getModel(dcForm);
		model.setZd3(dcForm.getXmmc());
		
		
		//判断当前奖项是否已有审核中或通过记录
		 String checkXm = dao.checkXhsqsh(t.getXn(), t.getXq(), t.getPdjx(), t.getXh(),t.getSqid());
			
			
		if(Integer.valueOf(checkXm) > 0){
			throw new SystemException(MessageKey.PJPY_FAIL);
			
		}
		
		boolean result = false;
		try {
			
			//判断流程序号
			HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(t.getSqid(), user.getUserName(), t.getSplc(), "sh");
			//审核前一步骤的项目代码
			String tzhxmdm = "";
			BzjlsqshDao sqshdao = new BzjlsqshDao();
			String rskzxh = sqshdao.getRskzXh(t.getPdjx());
			
			//如果审核通过，并且审核级别大于等于控制级别，更新调整后项目代码
			if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!=null)
					&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
				tzhxmdm = t.getPdjx();
			}
			
			String zhzt = shlc.runAuditing(model);
			BzjlsqshModel upForm = new BzjlsqshModel();
			upForm.setSqid(t.getSqid());
			upForm.setShzt(zhzt);
			
			upForm.setTzhxmdm(tzhxmdm);
			upForm.setDqxmdm(t.getPdjx());
			result = dao.runUpdate(upForm, t.getSqid());
			if(result && zhzt.equals(Constants.SH_TG)){
				//插入到结果表
				BzjlsqshModel viewModel = dao.getModel(t.getSqid());
				//BeanUtils.copyProperties(pjjgModel, viewModel);
				XmwhModel xmwhModel = new XmwhDao().getModel(t.getPdjx());
				
				BzjljgModel pjjgModel = new BzjljgModel();
				pjjgModel.setSjly(SQSH);
				pjjgModel.setId(viewModel.getSqid());
				pjjgModel.setXmdm(t.getPdjx());
				pjjgModel.setXmje(xmwhModel.getXmje());
				pjjgModel.setXmmc(xmwhModel.getXmmc());
				pjjgModel.setXn(viewModel.getXn());
				if(!CsszService.XQKG.equalsIgnoreCase(viewModel.getXq())){
					pjjgModel.setXq(viewModel.getXq());
				}
				pjjgModel.setSqly(viewModel.getSqly());
				pjjgModel.setSqsj(viewModel.getSqsj());
				pjjgModel.setXh(viewModel.getXh());
				pjjgModel.setXzdm(xmwhModel.getXzdm());
				pjjgModel.setLxdm(xmwhModel.getLxdm());
				pjjgModel.setLylcywid(t.getSqid());
				//学生参评班级
				ZcfsService zcfsServcie = new ZcfsService();
				HashMap<String, String> cpbjxx = zcfsServcie.getCpbjListByXh(t.getXh(), t.getXn(), t.getXq());
				pjjgModel.setCpnj(cpbjxx.get("nj"));
				pjjgModel.setCpxymc(cpbjxx.get("xymc"));
				pjjgModel.setCpzymc(cpbjxx.get("zymc"));
				pjjgModel.setCpbjmc(cpbjxx.get("bjmc"));
				pjjgModel.setCpxydm(cpbjxx.get("xydm"));
				pjjgModel.setCpzydm(cpbjxx.get("zydm"));
				pjjgModel.setCpbjdm(cpbjxx.get("bjdm"));
				pjjgModel.setYlzd5(t.getYlzd5()); // 附件id
				BzjljgDao pjjgDao = new BzjljgDao();
				try {
					pjjgDao.runInsert(pjjgModel);
				} catch (Exception e) {
					logger.debug("评奖审核插入正式表失败："+pjjgModel.getId());
				}
			}
		
		} catch (Exception e) {
			return e.getMessage();
			
			/*e.printStackTrace();
			return false;*/
		}
		
		return String.valueOf(result);
	}
	
	/** 
	 * @描述:评奖信息单独查询
	 * @作者：cq [工号：785]
	 * @日期：2015-11-20 下午04:04:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws 
	 */
	
	public HashMap<String, String> getPjInfo(String xh,String xn,String xq) {
		
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getPjInfo(xh, xn, xq);
	}
	
	/** 
	 * @描述:根据项目代码获取奖项金额
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-25 上午09:00:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXmje(String xmdm){
		return dao.getXmje(xmdm);
	}
	
	//集体评奖判断
	public boolean isExistsFlowDatajtpj(String xmdm) {

		return Integer.valueOf(dao.getFlowDataJtpj(xmdm)) > 0;
	}
	
	/**
	 * 
	 * @描述: 判断是否不可兼得
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-10 下午03:43:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotbkjd(String xmdm,String xn,String xq,String xh){
		return dao.checkIsNotbkjd(xmdm, xn, xq, xh);
	}
	
	/**
	 * 
	 * @描述: 获取不可兼得项目名称
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-10 下午03:54:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getbkjdMc(String xmdm){
		return dao.getbkjdMc(xmdm);
	}
	
	/**
	 * 
	 * @描述: 获取必修课最低成绩
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-11 下午02:53:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdms
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsdmBxkMinCj(String xn,String xq,String bjdms){
		
		String[] bjdm = bjdms.split(",");
		String params = "";
		for(int i = 0;i<bjdm.length;i++){
			params +="'"+bjdm[i]+"',";
		}
		if(params.length()>0){
			params=params.substring(0, params.length()-1);
		}
		
		return dao.getXsdmBxkMinCj(xn, xq, params);
	}
	
	
	/**
	 * 
	 * @描述: 批量插入条件检测结果
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月15日 上午10:51:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean batchInsertCheckResult(List<String[]> params) throws SQLException{
		dao.deleteCheckResult(params);
		return dao.batchInsertCheckResult(params);
	}
	
	/**
	 * 
	 * @描述: 查询条件检测结果
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月15日 下午1:38:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdmArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCheckResultList(String xn,String xq,String[] bjdmArr){
		return dao.getCheckResultList(xn, xq, bjdmArr);
	}
	
	/**
	 * @描述：获取英语成绩
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdms
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getEngScore_10279(String xn,String xq,String bjdms){
		return dao.getEngScore_10279(xn, xq, bjdms);
	}
	
	/**
	 * @描述：导出汇总表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * File 返回类型
	 */
	public File getHzbPrint(SearchModel searchmodel)throws Exception {
		//有且必须有一个xn和一个xmmc
		String[] xns=searchmodel.getSearch_tj_xn();
		String[] xmmcs=searchmodel.getSearch_tj_pjsqxm();
		String xn=(xns==null?"":xns[0]);
		String xmmc=(xmmcs==null?"":xmmcs[0]);
		List<HashMap<String,String>> wshlist=dao.getwshList_10279(xn,xmmc);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("wshlist", wshlist);
		data.put("xn", xn);
		data.put("xmmc", xmmc);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "pjpy_hzb_"+"10279"+".xml", xn+"学年汇总表");
		return excelFile;
	}

	/**
	 * @描述:浙江同济科技职业技术学院，获取学生成绩汇总导出的数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月11日 下午3:44:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXscjhzList(BzjlsqshModel model, User user) throws Exception {
		return dao.getXscjhzList(model,user);
	}


	/**
	 *  评奖评优奖项申请提交数据异常处理.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 17:57
	 * @param
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 * @throw Exception
	 */
    public Map<String,String> exceptionDataResolve() throws Exception {

		boolean success =true;
		String message = "处理成功！";
		Map resultMap = new HashMap<String,Object>();
		List<HashMap<String,String>> exceptionDataList = dao.getExceptionDataList();
		if(exceptionDataList.size() != 0){
			for(HashMap<String,String> edMap:exceptionDataList){
				success = shlc.runSubmit(edMap.get("sqid"), edMap.get("splc"), edMap.get("xh"), "pj_jxsh.do", "pj_pjxmsq.do");
				if(!success){
					message = "处理失败！";
				}
			}
		}else{
			message = "无异常数据！";
		}
		resultMap.put("success",success);
		resultMap.put("message",message);
		return resultMap;
    }

    /**
     * @description	： 获取学生各类奖项名单
     * @author 		： 柳俊（1282）
     * @date 		：2018-1-8 上午10:04:49
     * @return
     * @throws IOException
     */
    public List<File> getXsglmc(BzjlsqshModel t) throws Exception{
    	List<File> fileList = new ArrayList<File>();
    	List<HashMap<String,String>> list = dao.getXsglmdList(t);
    	String bjdm = null;
    	List<HashMap<String,String>> arrList = null;
    	if(list.size() > 0){
    		for(HashMap<String,String> map : list){
    			//第一次循环进去的时候，初始化班级代码
    			if(StringUtils.isNull(bjdm)){
    				bjdm = map.get("bjdm");
    				arrList = new ArrayList<HashMap<String,String>>();
    				setKcAndCj(map);
    				arrList.add(map);
    			}else{
    				//如果为同一班级的学生，处理map，并且放入同一个班级的list
    				if(map.get("bjdm").equals(bjdm)){
    					setKcAndCj(map);
    					arrList.add(map);
    				}else{//如果不为同一班级的学生，生成excel文件，并且放入FileList
    					File file = getXshjByBjExl(arrList);
    					fileList.add(file);
    					bjdm = map.get("bjdm");
    					arrList = new ArrayList<HashMap<String,String>>();
    					setKcAndCj(map);
        				arrList.add(map);
    				}
    			}
    		}
    		if(null != arrList && arrList.size() > 0){
    			File file = getXshjByBjExl(arrList);
    			fileList.add(file);
    		}
    	}
    	return fileList;
    }

    /**
     * @description	： 获取学生获奖excel以班级为单位
     * @author 		： 柳俊（1282）
     * @date 		：2018-1-8 下午02:59:15
     * @param list
     * @param bjxx
     * @return
     * @throws IOException
     * @throws WriteException
     */
    public File getXshjByBjExl(List<HashMap<String,String>> list) throws Exception{
    	HashMap<String,String> titleMap = list.get(0);
    	String xn = titleMap.get("xn");
    	String xqmc = titleMap.get("xqmc");
    	String xymc = titleMap.get("xymc");
    	String bjmc = titleMap.get("bjmc");
    	String bzr = titleMap.get("bzr");
    	String bjdm = titleMap.get("bjdm");
    	String xq = titleMap.get("xq");


		String fileName = bjmc + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		if(!file.exists()){
			file.createNewFile();
		}
			 WritableWorkbook  wwb = Workbook.createWorkbook(file);
			 WritableSheet sheet = wwb.createSheet(bjmc, 0);

			 WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置标题字体
			 WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
			 WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体

			 WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
			 WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
			 WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
			 //WritableCellFormat title_secondary = new WritableCellFormat(bodyFont);//设置第二级标题单元格字体
			 WritableCellFormat remark_cf = new WritableCellFormat(bodyFont);//备注
			 WritableCellFormat remarkk_cf = new WritableCellFormat(bodyFont);//备注

			 title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
			 title_cf.setVerticalAlignment(VerticalAlignment.CENTRE);
			 title_cf.setWrap(true);
			 //title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框


			 head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			 head_cf.setVerticalAlignment(VerticalAlignment.CENTRE);//设置表头水平对齐
			 head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			 head_cf.setWrap(true);
			 
			 
			 body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
			 body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
			 body_cf.setVerticalAlignment(VerticalAlignment.CENTRE);
			 body_cf.setWrap(true);
			 remark_cf.setWrap(true);
			 remarkk_cf.setWrap(true);
			 
			 remark_cf.setAlignment(jxl.format.Alignment.LEFT);//设置备注单元格对齐
			 remark_cf.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置备注单元格边框
				
			 remarkk_cf.setAlignment(jxl.format.Alignment.RIGHT);//设置备注单元格对齐
			 remarkk_cf.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置备注单元格边框
			 
			 //title_secondary.setAlignment(jxl.format.Alignment.LEFT);
			 
			 //设置首行行高
			sheet.setRowView(0, 600, false);
			
			sheet.setRowView(1, 400, false);
			
			sheet.setRowView(2, 650, false);
			 
			 //设置各列列宽
			 sheet.setColumnView(0, 6);
			 sheet.setColumnView(1, 8);
			 sheet.setColumnView(2, 6);
			 sheet.setColumnView(3, 6);
			 sheet.setColumnView(4, 6);
			 sheet.setColumnView(5, 6);
			 sheet.setColumnView(6, 7);
			 sheet.setColumnView(7, 7);
			 sheet.setColumnView(8, 7);
			 sheet.setColumnView(9, 7);
			 sheet.setColumnView(10, 7);
			 sheet.setColumnView(11, 7);
			 sheet.setColumnView(12, 7);
			 sheet.setColumnView(13, 7);
			 sheet.setColumnView(14, 7);
			 sheet.setColumnView(15, 12);
			 sheet.setColumnView(16, 8);
			 sheet.setColumnView(17, 8);
			 
			 sheet.mergeCells(0, 0, 17, 0);
			 Label label_title0 = new Label(0, 0, "学生各类表彰审批表",title_cf);
			 sheet.addCell(label_title0);
			 
			 sheet.mergeCells(0, 1, 17, 1);
			 Label label_title1 = new Label(0,1,xn+"学年      "+xqmc+"     "+xymc+"     班主任："+(bzr==null?"":bzr)+"     班级："+bjmc,head_cf);
			 sheet.addCell(label_title1);
			 
			 sheet.mergeCells(0, 2, 0, 3);
			 sheet.mergeCells(1, 2, 1, 3);
			 sheet.mergeCells(2, 2, 2, 3);
			 sheet.mergeCells(3, 2, 3, 3);
			 sheet.mergeCells(4, 2, 4, 3);
			 
			 Label label_head0 = new Label(0, 2, "序号",head_cf);
			 Label label_head1 = new Label(1, 2, "姓名",head_cf);
			 Label label_head2 = new Label(2, 2, "性别",head_cf);
			 Label label_head3 = new Label(3, 2, "担任职务",head_cf);
			 Label label_head4 = new Label(4, 2, "操行等第",head_cf);
			 sheet.addCell(label_head0);
			 sheet.addCell(label_head1);
			 sheet.addCell(label_head2);
			 sheet.addCell(label_head3);
			 sheet.addCell(label_head4);
			 
			 sheet.mergeCells(5, 2, 14, 2);
			 Label label_head5 = new Label(5, 2, "各科学习成绩",head_cf);
			 sheet.addCell(label_head5);
			 
			 //取出10个课程成绩名称
			 List<HashMap<String,String>> kcMapList = dao.getCjmcList(xn, xq, bjdm);
			 
			 List<String> kcmcList = new ArrayList<String>();
			 
			 //如果该班级学生的考试课程名称存在
			 if(null != kcMapList && kcMapList.size() > 0){
				 //获取取得的课程数量
				 int num = kcMapList.size();
				 
				 int balance = 10 - num;
				 
				 for(int i = 0;i < num; i++){
					 Label cjLabel = new Label(5+i,3, kcMapList.get(i).get("kcmc"),head_cf);
					 sheet.addCell(cjLabel);
					 kcmcList.add(kcMapList.get(i).get("kcmc"));
				 }
				 
				 //如果不到10个课程，补全课程名称单元格
				 if(balance > 0){
					 for(int p = 0; p < balance; p++){
						 Label cjLabel = new Label(14-p,3, "",body_cf);
						 sheet.addCell(cjLabel);
					 }
				 }
			 }else{
				 for(int i = 0; i < 10; i++){
					 Label cjLabel = new Label(5+i,3, "",body_cf);
					 sheet.addCell(cjLabel);
				 }
			 }
			 
			 sheet.mergeCells(15, 2, 15, 3);
			 sheet.mergeCells(16, 2, 16, 3);
			 sheet.mergeCells(17, 2, 17, 3);
			 Label label_head15 = new Label(15, 2 ,"申报类别",head_cf);
			 Label label_head16 = new Label(16, 2 ,"系审批意见",head_cf);
			 Label label_head17 = new Label(17, 2 ,"学生处意见",head_cf);
			 sheet.addCell(label_head15);
			 sheet.addCell(label_head16);
			 sheet.addCell(label_head17);
			 			 
			int size = list.size();
			if(size > 0){
				 for(int i = 0;i<size;i++){
					 HashMap<String, String> map = list.get(i);
					 Label sx = new Label(0, 4+i,String.valueOf(i+1), body_cf);
					 Label xm = new Label(1, 4+i, map.get("xm"), body_cf);
					 Label xb = new Label(2, 4+i, map.get("xb"), body_cf);
					 Label zw = new Label(3, 4+i, map.get("zwmc"), body_cf);
					 Label cxdj = new Label(4, 4+i, map.get("cxdjmc"), body_cf);
					 
					 
					 sheet.addCell(sx);
					 sheet.addCell(xm);					 
					 sheet.addCell(xb);
					 sheet.addCell(zw);
					 sheet.addCell(cxdj);
					 
					 if(kcmcList.size() > 0){
						 int num = kcmcList.size();						 
						 int balance = 10 - num;
						 
						 //判断课程名称是否与学生的成绩课程名字相符
						 for(int j = 0;j < kcmcList.size(); j++){
							 if(map.containsKey(kcmcList.get(j))){
								 Label cjLabel = new Label(5+j,4+i,map.get(kcmcList.get(j)), body_cf);
								 sheet.addCell(cjLabel);
								 continue;
							 }else{
								 Label cjLabel = new Label(5+j,4+i,"", body_cf);
								 sheet.addCell(cjLabel);
							 }
						 }
						 
						 //如果不到10个课程
						 if(balance > 0){
							 for(int p = 0; p < balance; p++){
								 Label cjLabel = new Label(14-p,4+i, "",body_cf);
								 sheet.addCell(cjLabel);
							 }
						 }
					 }else{
						 for(int p = 0;p<10;p++){
							 Label cjLabel = new Label(5+p,4+i, "",body_cf);
							 sheet.addCell(cjLabel);
						 }
					 }
					 
					 //填充获奖项目
					 Label xmmc = new Label(15, 4+i, map.get("xmmc"), body_cf);
					 sheet.addCell(xmmc);
					 
					 //系审批意见
					 Label xshyj = new Label(16, 4+i, "", body_cf);
					 sheet.addCell(xshyj);
					 
					 //学生处意见
					 Label xscyj = new Label(17, 4+i, "", body_cf);
					 sheet.addCell(xscyj);
					 
					 //备注_1
					 sheet.mergeCells(0, 4+size+2, 17, 4+size+2);
					 Label bz_1 = new Label(0, 4+size+2, "注：1、请用钢笔按表格要求全部填写； 2、校内优秀学干操行等第标准为优秀，各科平均成绩在80分以上，体育75分以上", remark_cf);
					 sheet.addCell(bz_1);
					 
					 //备注_2
					 sheet.mergeCells(0, 4+size+3, 17, 4+size+3);
					 Label bz_2 = new Label(0, 4+size+3, "3、请严格按规定指标执行；       4、填写申报类别相同要相对集中。", remark_cf);
					 sheet.addCell(bz_2);
					 
					 //备注_3
					 sheet.mergeCells(0, 4+size+4, 17, 4+size+4);
					 Label bz_3 = new Label(0, 4+size+4, "学生工作部", remarkk_cf);
					 sheet.addCell(bz_3);
					 //sheet.addCell(xscyj);
					 
				 }
				 wwb.write();
				 wwb.close();
			 }
			 return file;			 
	}
    
    /**
     * @description	： 设置课程和成绩列表（取10个课程名称和成绩）
     * @author 		： 柳俊（1282）
     * @date 		：2018-1-8 下午12:00:50
     * @return
     */
    public HashMap<String,String> setKcAndCj(HashMap<String,String> map){
    	//获取课程名称
    	String mchz = map.get("kchz");
    	//获取成绩列表
    	String cjhz = map.get("cjhz");
    	//如果有多个成绩情况下
    	if(StringUtils.isNotNull(mchz) && mchz.indexOf(";") != -1){
    		String[] mchzArr = mchz.split(";");
    		String[] cjhzArr = cjhz.split(";");
    		for(int i = 0;i < mchzArr.length && i < 10; i++){
    			map.put(mchzArr[i],cjhzArr[i]);
    		}
    	}
    	//如果只有一个成绩情况下
    	else if(StringUtils.isNotNull(mchz) && mchz.indexOf(";") == -1){
    		map.put(map.get("kchz"),map.get("cjhz"));
    	}
    	return map;   	
    }
    
    /**
     * @description	： 验证学生各类获奖名单导出
     * @author 		： 柳俊（1282）
     * @date 		：2018-1-9 下午02:30:45
     * @param t
     * @return
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     */
    public boolean yzxsglmddc(BzjlsqshModel t) throws Exception{
    	List<HashMap<String,String>> list = dao.getXsglmdList(t);
    	return list.size() > 0;
    }
    
    public Map<String,Object> getMapList(){
    	return new HashMap<String,Object>();
    }
    
    /**
     * @throws Exception 
     * 
     * @描述: 保存获奖信息
     * @作者：喻鑫源[工号：1206]
     * @日期：2018-3-19 上午11:49:21
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean saveHjxx(BzjlsqshModel model) throws Exception{
    	String xh = model.getXh();
    	String sqid = model.getSqid();
    	CsszModel cssz = new CsszService().getModel();
    	String xn = cssz.getXn();
    	String xq = cssz.getXq();
    	if(StringUtils.isNotNull(sqid)){
    		BzjlsqshModel myForm = new BzjlsqshService().getModel(sqid);
    		xn = myForm.getXn();
    		xq = myForm.getXq();
    	}
    
    	List<String[]> params = new ArrayList<String[]>();
    	String[] ids = model.getIds(); ;
		for (int i = 0; i < ids.length; i++) {
			params.add(new String[]{xh,xn,xq,ids[i]});
		}
		dao.delHjxx(params);
		return dao.saveHjxx(params) ;
    	
    }
    
    /**
     * 
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：喻鑫源[工号：1206]
     * @日期：2018-3-19 下午04:26:45
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String,String>> getHjxxList(String xh,String xn,String xq){
    	return dao.getHjxxList(xh, xn, xq);
    }
}
