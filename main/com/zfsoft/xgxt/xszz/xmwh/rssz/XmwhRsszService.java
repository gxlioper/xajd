/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.rssz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-人数设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhRsszService extends
		SuperServiceImpl<XmwhRsszForm, XmwhRsszDao> {

	private XmwhRsszDao dao = new XmwhRsszDao();

	public XmwhRsszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:比例设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午05:08:13
	 * @修改记录:
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String blsz(XmwhRsszForm myForm, List<XmwhRsszForm> list)
			throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRskzfw();
		String bmdmFlag = "";
		String zrs = null;
		String bl = myForm.getBl();
		if (list == null || list.size() == 0) {//未勾选记录或总名额设置保存人数设置
			List<HashMap<String, String>> allList = null;
			if (rskzfw != null) {
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
					bmdmFlag = "zybj";
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					bmdmFlag = "xydm";
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					bmdmFlag = "zydm";
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					bmdmFlag = "xydm";
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					bmdmFlag = "zydm";
				} else if(rskzfw.equals(Constants.RSKZFW_SY)){//书院
					bmdmFlag = "xydm";
				}
			}
			allList = getAllList(myForm);
			for (HashMap<String, String> map : allList) {
				map.put("bmdm", map.get(bmdmFlag));
				if (bl != null) {
					zrs = map.get("zrs");
					try {
						map.put("zzrs", ""
								+ Math.round(Double.parseDouble(bl)
										* Double.parseDouble(zrs) * 0.01));
					} catch (Exception e) {
						logger.error("数据有误：bl=" + bl + ";zrs=" + zrs);
					}
				}
			}

			message = zrsYz(myForm, allList);// 对最终人数进行校验，与已申请通过的人数比对
			flag = dao.runBlszAll(myForm, allList);
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.XSZZ_RSSZ_ZZRSYZ,
						message);
			}

		} else {
			if (rskzfw != null && rskzfw.equals(Constants.RSKZFW_XY)) {// 学院				
				List<HashMap<String, String>> allList = getAllList(myForm);
				if(allList != null && allList.size() > 0){
					for (XmwhRsszForm xmwhRsszForm : list) {
						String bmdm = xmwhRsszForm.getBmdm();
						if(bmdm == null){
							continue;
						}
						for (HashMap<String, String> map1 : allList) {
							if(bmdm.equals(map1.get("xydm"))){
								String zrsTemp = map1.get("zrs");
								try {
									xmwhRsszForm.setZzrs( "" + Math.round(Double.parseDouble(myForm.getBl())
													* Double.parseDouble(zrsTemp) * 0.01));
								} catch (Exception e) {
								}
							}
						}
					}
				}
			} 
			message = zrsYzDg(myForm, list);// 对最终人数进行校验，与已申请通过的人数比对
			if (message != null && !message.equals("")) {
				throw new SystemException(MessageKey.XSZZ_RSSZ_ZZRSTS, message);
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
	private String zrsYzDg(XmwhRsszForm myForm, List<XmwhRsszForm> list)  throws Exception{

		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (XmwhRsszForm form : list) {
			map = new HashMap<String, String>();
			map.put("zzrs", form.getZzrs());
			map.put("bmdm", form.getBmdm());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}

	// 对最终人数进行校验，与已申请通过的人数比对
	private String zrsYz(XmwhRsszForm myForm,
			List<HashMap<String, String>> allList) throws Exception{
		String rskzfw = myForm.getRskzfw();
		XszzSqshService xszzSqshService = new XszzSqshService();
		List<HashMap<String, String>> shtgrsList = xszzSqshService.getShtgrs(
				rskzfw, myForm.getXmdm(), myForm.getXn(), myForm.getXq());
		if (shtgrsList.size() == 0) {// 若无申请通过的人数，直接返回
			return null;
		}

		List<HashMap<String, String>> cwList = new ArrayList<HashMap<String, String>>();

		String tgrs = null;// 通过人数
		String bmdm = null;// 部门代码，
		String zzrs = null;// 最终人数
		for (HashMap<String, String> map : allList) {// 循环总学院/专业/班级
			zzrs = map.get("zzrs");
			bmdm = map.get("bmdm");
			if (zzrs == null || zzrs.trim().length() == 0 || bmdm == null) {// 若需设置的总人数为空，跳出
				continue;
			}
			int iZzrs = 0;
			try {
				iZzrs = Integer.parseInt(zzrs);// 总人数格式不对
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
							cwMap.put("yrs", zzrs);
							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					if (bmdm.equals(shtgrsMap.get("xydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					if (bmdm.equals(shtgrsMap.get("zydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}

				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					if (bmdm.equals(shtgrsMap.get("zydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_SY)) {// 书院
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
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
				} else if(rskzfw.equals(Constants.RSKZFW_SY)){//书院
					name = dao.getSymc(bmdm1);
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
	 * @日期：2013-5-29 上午11:03:06
	 * @修改记录:
	 * @param myForm
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String fpsz(XmwhRsszForm myForm, List<XmwhRsszForm> list, String zme,String rskznj)
			throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		if (zme == null || zme.trim().equals("")) {//比例方式设置人数
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), "",rskznj);
			if (!flag) {
				logger.error("总名额保存失败！");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, list);
		} else {//总名额方式设置人数
			int zrs = dao.getZrs(myForm,rskznj);
			int iZme = Integer.parseInt(zme);
			double bl = iZme * 100.0 / zrs;
			if (bl > 100) {
				bl = 100;
				iZme = zrs;
			}
			DecimalFormat df = new DecimalFormat("##0.##");
			myForm.setBl(df.format(bl) + "");
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), iZme + "",rskznj);
			if (!flag) {
				logger.error("总名额保存失败！");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, null);
		}
	}

	/**
	 * 
	 * @描述:最终人数设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午05:08:13
	 * @修改记录:
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String setZzrs(XmwhRsszForm model) throws Exception {
		String message = null;
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;

		String[] guids = model.getGuids();
		String[] zzrss = model.getZzrss();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] bls = model.getBls();

		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzrs", zzrss[i]);
				map.put("bmdm", xydms[i]);
				if (njs != null && njs[i] != null) {
					map.put("nj", njs[i]);
				}
				allList.add(map);
			}
		}

		message = zrsYz(model, allList);
		if (message != null && !message.equals("")) {
			throw new SystemException(MessageKey.XSZZ_RSSZ_ZZRSTS, message);
		}
		boolean flag = dao.runZzrs(model);
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
	 * @日期：2013-4-24 上午10:14:13
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
	 * @日期：2013-5-2 下午03:03:00
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
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public boolean getRsszCount(String xmdm) throws Exception {
		int count = dao.getRsszCount(xmdm);
		return count > 0;
	}

	/**
	 * 
	 * @描述:判断是否已配置人数设置
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public HashMap<String, String> getRsszData(String xmdm, String xh)
			throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		HashMap<String, String> map = xmwhDao.getDataById(xmdm);
		String rskzfw = map.get("rskzfw");
		if (rskzfw == null) {
			throw new SystemException("项目代码为空或未进行人数控制范围设置！");
		}
		HashMap<String, String> rsszOne = dao.getRsszOne(xmdm, xh, rskzfw);
		if (rsszOne != null) {
			map.put("zzrs", rsszOne.get("zzrs"));
			map.put("xn", rsszOne.get("xn"));
			map.put("xq", rsszOne.get("xq"));
		} else {
			// ////
		}

		return map;
	}

	/**
	 * 
	 * @描述:查询项目已设置人数
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public int getYszrs(XmwhRsszForm model) throws Exception {
		return dao.getYszrs(model);
	}

	/**
	 * 
	 * @描述:查询所有学院
	 * @作者：ligl
	 * @日期：2013-5-28 下午02:48:35
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
	 * @日期：2013-7-5 上午08:37:45
	 * @修改记录:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String>  getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}
	
	public List<HashMap<String, String>> getSyList(){
		List<HashMap<String, String>> result = null;
		result = dao.getSyList();
		return result;
	}
	/**
	 * 
	 * @描述:根据项目代码查询人数非配方式
	 * @作者：cq [工号：785]
	 * @日期：2014-4-22 上午11:48:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getRsfpfs(String xmdm){
		return dao.getRsfpfs(xmdm);
	}
	

}
