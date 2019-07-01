package com.zfsoft.xgxt.xszz.knsjcsz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszDao;

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	private JcszDao dao = new JcszDao();

	public JcszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:查询基础设置信息
	 * @作者：maxh
	 * @日期：2013-4-19 下午01:45:50
	 * @param model
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */

	public JcszForm getModel(JcszForm t) throws Exception {

		return dao.getModel();
	}

	/**
	 * 
	 * @描述:查询基础设置信息(无参数)
	 * @作者：maxh
	 * @日期：2013-4-19 下午01:45:50
	 * @param model
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public JcszForm getModel() throws Exception {

		return getModel(new JcszForm());
	}

	/**
	 * 保存参数设置信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJcsz(JcszForm model) throws Exception {
		if("0".equals(model.getSqkg())){
			if("14008".equals(Base.xxdm)) {
				model.setShkg(model.getShkg());
				model.setShkssj(model.getShkssj());
				model.setShjssj(model.getShjssj());
				dao.updateJcszShkg(model);
			}
			return dao.updateJcszSqkg(model);
		}
		boolean flag = false;
		flag = dao.deleteJcsz(model);
		if (flag) {
			if("10052".equals(Base.xxdm)){
				model.setKxzrdzb("1");
			}
			flag = dao.runInsert(model);

		}
		return flag;

	}

	/**
	 * 
	 * @描述:获取所有包含学生的年级
	 * @作者：cmj
	 * @日期：2013-7-5 上午08:37:45
	 * @修改记录:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}

	/**
	 * 
	 * @描述:保存
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-12-9 下午04:05:42
	 * @修改记录: 修改者名字-修改日期-修改内容 ActionForward 返回类型
	 * @throws
	 */
	public String fpsz(JcszForm myForm, List<JcszForm> list, String zme,
			String rskznj) throws Exception {

		if (zme == null || zme.trim().equals("")) {// 比例方式设置人数

			boolean flag = dao.setRsszcs("", rskznj, myForm);
			if (!flag) {
				logger.error("总名额保存失败！");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, list);
		} else {// 总名额方式设置人数
			int zrs = dao.getZrs(myForm, rskznj);
			int iZme = Integer.parseInt(zme);
			double bl = iZme * 100.0 / zrs;
			if (bl > 100) {
				bl = 100;
				iZme = zrs;
			}
			DecimalFormat df = new DecimalFormat("##0.##");
			myForm.setBl(df.format(bl) + "");
			boolean flag = dao.setRsszcs(zme, rskznj, myForm);
			if (!flag) {
				logger.error("总名额保存失败！");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, null);
		}
	}

	/**
	 * @描述：人数设置
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-9 下午05:25:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param list
	 * @return String 返回类型
	 * @throws
	 */
	private String blsz(JcszForm myForm, List<JcszForm> list) throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRskzfw();
		String bmdmFlag = "";
		String zrs = null;
		String bl = myForm.getBl();
		if (list == null || list.size() == 0) {
			List<HashMap<String, String>> allList = null;
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
				}
			}
			allList = getAllList(myForm);
			for (HashMap<String, String> map : allList) {
				map.put("bmdm", map.get(bmdmFlag));
				if (bl != null) {
					zrs = map.get("zrs");
					try {
						map.put("zzrs", ""	+ Math.round(Double.parseDouble(bl)* Double.parseDouble(zrs) * 0.01));
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
				if (allList != null && allList.size() > 0) {
					for (JcszForm JcszForm : list) {
						String bmdm = JcszForm.getBmdm();
						if (bmdm == null) {
							continue;
						}
						for (HashMap<String, String> map1 : allList) {
							if (bmdm.equals(map1.get("xydm"))) {
								String zrsTemp = map1.get("zrs");
								try {
									JcszForm.setZzrs(""+ Math.round(Double.parseDouble(myForm.getBl())* Double.parseDouble(zrsTemp)* 0.01));
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

	/**
	 * @描述:对最终人数进行校验，通过人数大于设置人数，以通过人数为准（针对未勾选记录的人数设置）
	 * @作者：cmj
	 * @日期：2013-12-10 下午02:20:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	private String zrsYz(JcszForm myForm, List<HashMap<String, String>> allList)
			throws Exception {
		String rskzfw = myForm.getRskzfw();
		KnsrdService knsrdService = new KnsrdService();
		List<HashMap<String, String>> shtgrsList = knsrdService.getShtgrs(myForm);
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
				XmwhRsszDao xmwhDao = new XmwhRsszDao();
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
					name = xmwhDao.getBjmc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
					name = xmwhDao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
					name = xmwhDao.getZymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					name = xmwhDao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
					name = xmwhDao.getZymc(bmdm1);
				}
				cw += name;

				// cw += ":" + cwMap.get("yrs");
				// cw += "→" + cwMap.get("tgrs");
			}
			cw += "]";
		}
		return cw;
	}

	/**
	 * @描述:对最终人数进行校验，通过人数大于设置人数，以通过人数为准(针对已勾选记录的人数设置)
	 * @作者：cmj
	 * @日期：2013-12-10 下午02:20:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	private String zrsYzDg(JcszForm myForm, List<JcszForm> list)
			throws Exception {

		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (JcszForm form : list) {
			map = new HashMap<String, String>();
			map.put("zzrs", form.getZzrs());
			map.put("bmdm", form.getBmdm());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}

	/**
	 * @描述:修改人数控制范围,并返回对应范围是否设置人数
	 * @作者：陈敏杰
	 * @日期：2013-12-11 下午01:38:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean updateRskzfw(JcszForm myForm) throws Exception {
		boolean result = dao.updateRskzfw(myForm);
		if (result) {
			dao.deleteRssz();
		}
		return result;
	}

	/**
	 * @描述:获取已设置人数
	 * @作者：陈敏杰
	 * @日期：2013-12-11 下午02:58:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return String 返回类型
	 * @throws
	 */
	public String getYszrs(JcszForm myForm) {
		String yszrs = dao.getYszrs(myForm);
		if (StringUtil.isNull(yszrs)) {
			yszrs = "0";
		}
		return yszrs;
	}

	/**
	 * @描述:设置最终人数
	 * @作者：cmj
	 * @日期：2013-12-11 下午03:50:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return String 返回类型
	 * @throws
	 */
	public String setZzrs(JcszForm model) throws Exception {
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

		String rskzfw = model.getRskzfw();
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzrs", zzrss[i]);
				String bmdm = "";
				if (rskzfw != null) {
					if (rskzfw.equals(Constants.RSKZFW_BJ)) {
						bmdm = bjdms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
						bmdm = zydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
						bmdm = zydms[i];
					}
				}
				map.put("bmdm", bmdm);
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
	 * @描述:验证是否已设置人数
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-11 下午04:49:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkRsSfsz(JcszForm myForm) {
		String num = dao.getYszrsNum(myForm);
		boolean result = false;
		if ("0".equalsIgnoreCase(num)) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	
	/**
	 * @描述：参数设置：是否根据 困难生档次人数控制
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月3日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * boolean 返回类型
	 */
	public String getKnsrsszfs(){
		return dao.getKnsrsszfs();
	}
	
	public String getRddcZme(String rddc){
		return dao.getRddcZme(rddc);
	}
	
	public boolean delKnsrssz(String values) throws Exception{
		return dao.delKnsrssz(values);
	}
}
