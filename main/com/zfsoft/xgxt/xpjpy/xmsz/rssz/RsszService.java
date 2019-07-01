/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:07:42
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sun.java2d.loops.DrawGlyphListAA.General;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import common.Globals;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 项目维护-人数设置
 * @类功能描述: 项目维护-人数设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:07:42
 * @版本： V1.0
 * @修改记录:
 */
public class RsszService extends
		SuperServiceImpl<RsszModel, RsszDao> {
	
	public static final String czfs = "xyrssz";

	private RsszDao dao = new RsszDao();

	public RsszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:比例设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String blsz(RsszModel myForm, List<RsszModel> list,User user)
			throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRsfpfs();
		String bmdmFlag = "";
		String zrs = null;
		String fpbl = myForm.getFpbl();
		CsszService csszService = new CsszService();
		String rsjsfs = csszService.getCsz("rsjsfs");	//ytjrs:已提交人数 ,zrs:参评人数. 评奖项目当中人数设置计算取值列。
		if (list == null || list.size() == 0) {
			
			myForm.getPages().setPageSize(Integer.MAX_VALUE);
			List<HashMap<String, String>> allList = getRsszList(myForm,user);
			if (rskzfw != null) {
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
					bmdmFlag = "bjdm";
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					bmdmFlag = "xydm";
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					bmdmFlag = "zydm";
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					bmdmFlag = "xydm";
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					bmdmFlag = "zydm";
				} else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// 参评组
					bmdmFlag = "cpzdm";
				}
			}
			for (HashMap<String, String> map : allList) {
				map.put("bmdm", map.get(bmdmFlag));
				if (fpbl != null) {
					zrs = map.get(rsjsfs);
					try {
						if(Base.xxdm.equals(Globals.XXDM_ZJDX)){  //浙江大学要保留小数点后两位
							map.put("zzme", ""+ (double)Math.round(Double.parseDouble(fpbl) * Double.parseDouble(zrs))/100);
						}else{
							map.put("zzme", ""
									+ Math.round(Double.parseDouble(fpbl)
											* Double.parseDouble(zrs) * 0.01));
						}
						
					} catch (Exception e) {
						logger.error("数据有误：fpbl=" + fpbl + ";zrs=" + zrs);
					}
				}
			}

			message = zrsYz(myForm, allList);// 对最终人数进行校验，与已申请通过的人数比对
			flag = dao.runBlszAll(myForm, allList);
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.PJPY_RSSZ_ZZRSYZ,
						message);
			}

		} else {
			message = zrsYzDg(myForm, list);// 对最终人数进行校验，与已申请通过的人数比对
			if (message != null && !message.equals("")) {
				throw new SystemException(MessageKey.PJPY_RSSZ_ZZRSTS, message);
			}
			flag = dao.runBlsz(myForm, list);
			if (flag) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return message;
	}

	// 对最终人数进行校验，与已申请通过的人数比对
	private String zrsYzDg(RsszModel myForm, List<RsszModel> list)  throws Exception{

		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (RsszModel form : list) {
			map = new HashMap<String, String>();
			map.put("zzme", form.getZzme());
			map.put("bmdm", form.getBmdm());
			map.put("zzme", form.getZzme());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}

	// 对最终人数进行校验，与已申请通过的人数比对
	private String zrsYz(RsszModel myForm,
			List<HashMap<String, String>> allList) throws Exception{
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		String currXn = csszModel.getXn();// 当前学年
		String currXq = csszModel.getXq();// 当年学期
		
		String rskzfw = myForm.getRsfpfs();
		
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> shtgrsList = pjjgService.getShtgrs(
				rskzfw, myForm.getXmdm(),currXn,currXq);

		//测试
//		shtgrsList = new ArrayList<HashMap<String,String>>();
//		HashMap<String, String> mapTemp = new HashMap<String, String>();
//		mapTemp.put("tgrs","102");
//		mapTemp.put("cpzdm","20012402");
//		shtgrsList.add(mapTemp);
		
		if (shtgrsList == null || shtgrsList.size() == 0) {// 若无申请通过的人数，直接返回
			return null;
		}

		List<HashMap<String, String>> cwList = new ArrayList<HashMap<String, String>>();

		String tgrs = null;// 通过人数
		String bmdm = null;// 部门代码，
		String zzme = null;// 最终人数
		for (HashMap<String, String> map : allList) {// 循环总学院/专业/班级
			zzme = map.get("zzme");
			bmdm = map.get("bmdm");
			if (zzme == null || zzme.trim().length() == 0 || bmdm == null) {// 若需设置的总人数为空，跳出
				continue;
			}
			int iZzrs = 0;
			try {
				iZzrs = Integer.parseInt(zzme);// 总人数格式不对
			} catch (Exception e) {
				continue;
			}
			for (HashMap<String, String> shtgrsMap : shtgrsList) {// 循环已通过审核的人数
				tgrs = shtgrsMap.get("tgrs");
				int iTgrs = 0;
				try {
					iTgrs = Integer.parseInt(tgrs);// 通过人数格式不对
				} catch (Exception e) {
					continue;
				}
				if (tgrs == null || tgrs.trim().length() == 0) {// 若通过人数为空，跳出
					continue;
				}
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
					if (bmdm.equals(shtgrsMap.get("bjdm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);
							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					if (bmdm.equals(shtgrsMap.get("xydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					if (bmdm.equals(shtgrsMap.get("zydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}

				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					if (bmdm.equals(shtgrsMap.get("zydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// 参评组
					if (bmdm.equals(shtgrsMap.get("cpzdm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				}
			}
		}

		String cw = "";
		if (cwList.size() > 0) {
			cw += "[";
			boolean flag = false;
			for (HashMap<String, String> cwMap : cwList) {
				String name = "";
				String bmdm1 = cwMap.get("bmdm");

				if (flag) {
					cw += ",";
				} else {
					flag = true;
				}
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
					name = dao.getBjmc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					name = dao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					name = dao.getZymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					name = dao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					name = dao.getZymc(bmdm1);
				}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// 参评组
					name = dao.getCpzmc(bmdm1);
				}
				cw += name;

//				cw += ":" + cwMap.get("yrs");
//				cw += "→" + cwMap.get("tgrs");
			}
			cw += "]";
		}
		return cw;
	}

	/**
	 * 
	 * @描述:分配设置，先计算比例，再直接调用比例设置方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param myForm
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String fpsz(RsszModel myForm, List<RsszModel> list, String zme,String rsfpnj,User user)
			throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		if(Base.xxdm.equals("10704")){
			String sfyxgb = myForm.getSfyxgb();
			String xmdm = myForm.getXmdm();
			xmwhDao.updateSfYxgb(xmdm, sfyxgb);
		}
		if (zme == null || zme.trim().equals("")) {
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), "",rsfpnj);
			if (!flag) {
				logger.error("总名额保存失败！");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, list,user);
		} else {
			int zrs = dao.getZrs(myForm,rsfpnj);
			int iZme = Integer.parseInt(zme);
			double bl = iZme * 100.0 / zrs;
			if (bl > 100) {
				bl = 100;
				iZme = zrs;
			}
			DecimalFormat df = new DecimalFormat("##0.##");
			myForm.setFpbl(df.format(bl) + "");
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), iZme + "",rsfpnj);
			if (!flag) {
				logger.error("总名额保存失败！");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, null,user);
		}
	}

	/**
	 * 
	 * @描述:最终人数设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String setZzrs(RsszModel model, User user) throws Exception {
		String message = null;
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;

		String[] guids = model.getGuids();
		String[] zzmes = model.getZzmes();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] cpzdms = model.getCpzdms();
		String[] fpbls = model.getFpbls();
		String rskzfw = model.getRsfpfs();
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzme", zzmes[i]);
				
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
					map.put("bmdm", bjdms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					map.put("bmdm", xydms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					map.put("bmdm", zydms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					map.put("bmdm", xydms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					map.put("bmdm", zydms[i]);
				}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// 参评组
					map.put("bmdm", cpzdms[i]);
				}
				if (njs != null && njs[i] != null) {
					map.put("nj", njs[i]);
				}
				allList.add(map);
			}
		}

		message = zrsYz(model, allList);
		if (message != null && !message.equals("")) {
			throw new SystemException(MessageKey.PJPY_RSSZ_ZZRSTS, message);
		}
		boolean flag = dao.runZzme(model,user);
		if (flag) {
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				// message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS,
				// message);
			}
		} else {
			message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}

		return message;
	}

	/**
	 * 
	 * @描述:根据项目代码，获取已设置的人数设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             XmwhJdszForm 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getRssz(String xmdm) throws Exception {
		return dao.getRssz(xmdm);
	}

	/**
	 * 
	 * @描述:根据项目代码删除记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		return dao.runDeleteByXmdm(xmdm);
	}

	/**
	 * 
	 * @描述:判断是否已配置人数设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public boolean getRsszCount(String xmdm) throws Exception {
		int count = dao.getRsszCount(xmdm);
		return count > 0;
	}

	/**
	 * 
	 * @描述:判断是否已配置人数设置----供接口调用 
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public HashMap<String, String> getRsszData(String xmdm, String xh)
			throws Exception {
//		XmwhDao xmwhDao = new XmwhDao();
//		HashMap<String, String> map = xmwhDao.getDataById(xmdm);
//		String rsfpfs = map.get("rsfpfs");
//		if (rsfpfs == null) {
//			throw new SystemException("项目代码为空或未进行人数控制范围设置！");
//		}
//		HashMap<String, String> rsszOne = dao.getRsszOne(xmdm, xh, rsfpfs);
//		if (rsszOne != null) {
//			map.put("zzme", rsszOne.get("zzme"));
//		} else {
//			// ////
//		}
//
//		return map;
		return null;
	}

	/**
	 * 
	 * @描述:查询项目已设置人数
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public int getYszrs(RsszModel model) throws Exception {
		return dao.getYszrs(model);
	}

	/**
	 * 
	 * @描述:查询所有学院
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSyxy() throws Exception {

		List<HashMap<String, String>> result = dao.getSyxy();
		return result;
	}
	
	/**
	 * 
	 * @描述:获取所有包含学生的年级
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String>  getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}
	
	/**
	 * 
	 * @描述:最终人数设置，学校方式
	 * @作者：ligl
	 * @日期：2013-8-13 下午04:43:56
	 * @修改记录: 
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String setZzrsForXx(RsszModel model,User user) throws Exception {
		String message = null;

		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		String currXn = csszModel.getXn();// 当前学年
		String currXq = csszModel.getXq();// 当年学期
		
		//验证已通过人数
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> shtgrsList = pjjgService.getShtgrs(
				Constants.RSKZFW_XX, model.getXmdm(),currXn,currXq);

		//测试
//		shtgrsList = new ArrayList<HashMap<String,String>>();
//		HashMap<String, String> mapTemp = new HashMap<String, String>();
//		mapTemp.put("tgrs","102");
//		mapTemp.put("xx","xx");
//		shtgrsList.add(mapTemp);
		
		if (shtgrsList != null && shtgrsList.size()>0) {//
			HashMap<String, String> map = shtgrsList.get(0);
			int iZzrs = 0;
			try {
				iZzrs = Integer.parseInt(model.getZzmes()[0]);// 总人数格式不对
			} catch (Exception e) {
			}		
			String tgrs = map.get("tgrs");
			int iTgrs = 0;
			try {
				iTgrs = Integer.parseInt(tgrs);// 通过人数格式不对
			} catch (Exception e) {
			}
			if (iTgrs > iZzrs) {// 
				String[] params = {"("+iZzrs + ")","("+iTgrs + ")"};
					throw new SystemException(MessageKey.PJPY_RSSZ_ZZRSTS_XX, params);
			}
		}
		
		boolean flag = dao.runZzmeForXx(model);
		if (flag) {
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				// message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS,
				// message);
			}
		} else {
			message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}

		return message;
	}

	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getRsszList(RsszModel t,User user)
			throws Exception {
		String rskzfw = t.getRsfpfs();
		
		if (StringUtil.isNull(rskzfw)){
			return null;
		}
		XmwhDao xmwhDao = new XmwhDao();
		String pycc = xmwhDao.getPycc(t.getXmdm());
		t.setPycc(pycc);
		
		// 班级
		if (rskzfw.equals(Constants.RSKZFW_BJ)) {
			return dao.getBjrsList(t,user);
		} 
		
		// 年级学院
		if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
			return dao.getNjxyrsList(t,user);
		} 
		
		// 年级专业
		if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
			return dao.getNjzyrsList(t,user);
		}
		
		// 学院
		if (rskzfw.equals(Constants.RSKZFW_XY)) {
			return dao.getXyrsList(t,user);
		}
		
		
		// 参评组
		if (rskzfw.equals(Constants.RSKZFW_CPZ)) {
			return dao.getCpzrsList(t,user);
		}
		
		// 学校
		if (rskzfw.equals(Constants.RSKZFW_XX)) {
			return dao.getXxrsList(t,user);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:根据项目代码获取人数分配方式
	 * @作者：cq [工号：785]
	 * @日期：2014-1-17 下午02:54:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public Map<String, String> getRsfpfs(String xmdm){
		return dao.getRsfpfs(xmdm);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:浙大个性化奖学金金额上限验证
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-19 上午09:29:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param user
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(RsszModel model,User user) throws Exception{
		return dao.getJxjze(model,user);
	}

	
	

}
