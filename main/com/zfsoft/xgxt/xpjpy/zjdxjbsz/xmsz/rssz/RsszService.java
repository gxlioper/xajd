/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 上午09:29:13 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszModel;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgService;

import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-1 上午09:29:13 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RsszService extends SuperServiceImpl<RsszForm,RsszDao>{
	public static final String czfs = "xyrssz";

	private RsszDao dao = new RsszDao();

	public RsszService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 奖学金金额上限验证
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 上午11:17:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(RsszForm model,User user)throws Exception{
		return dao.getJxjze(model,user);
	}
	
	/**
	 * @描述: 获取所有包含学生的年级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 下午01:49:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}
	
	/**
	 * @描述: 根据项目不同的人数分配方式，获取不同的人数设置数据
	 * 		    其中要考虑是管理员用户身份、还是学院用户身份
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 下午02:03:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRsszList (RsszForm t,User user)
		throws Exception {
		/*获得项目的人数分配方式（控制）Rsfpfs*/
		String rskzfw = t.getRsfpfs();
		/*空判断*/
		if (StringUtil.isNull(rskzfw)){
			return null;
		}
		/*增加项目时人数分配方式为【学院】*/
		if (rskzfw.equals(Constants.RSKZFW_XY)) {
			return dao.getXyrsList(t,user);
		}
		/*增加项目时人数分配方式为【年级+学院】*/
		if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
			return dao.getNjxyrsList(t,user);
		} 
		return null;
	}
	
	/**
	 * @描述: 获得参数配置
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 下午04:55:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsz(String csdm){
		return dao.getCsz(csdm);
	}
	
	/**
	 * @描述: 项目已设置的总人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-2 上午09:36:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int getYszrs(RsszForm model) throws Exception {
		return dao.getYszrs(model);
	}

	/** 
	 * @描述: 分配设置，先计算比例，再直接调用比例设置方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午11:09:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param list
	 * @param zme
	 * @param rsfpnj
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String fpsz(RsszForm myForm, List<RsszForm> list, String zme,
			String rsfpnj, User user) throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
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
	 * @描述: 比例设置
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午11:18:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param object
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	private String blsz(RsszForm myForm, List<RsszForm> list,User user)
		throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRsfpfs();
		String bmdmFlag = "";
		String zrs = null;
		String fpbl = myForm.getFpbl();
		CsszService csszService = new CsszService();
		/*ytjrs:已提交人数 ,zrs:参评人数. 评奖项目当中人数设置计算取值列。*/
		String rsjsfs = csszService.getCsz("rsjsfs");	
		if (list == null || list.size() == 0) {
			myForm.getPages().setPageSize(Integer.MAX_VALUE);
			List<HashMap<String, String>> allList = getRsszList(myForm,user);
			if (rskzfw != null) {
				if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					bmdmFlag = "xydm";
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
			/*对最终人数进行校验，与已申请通过的人数比对*/
			message = zrsYz(myForm, allList);
			flag = dao.runBlszAll(myForm, allList);
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.PJPY_RSSZ_ZZRSYZ,
						message);
			}

		} else {
			/*对最终人数进行校验，与已申请通过的人数比对*/
			message = zrsYzDg(myForm, list);
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
	
	/**
	 * @描述: 对最终人数进行校验，与已申请通过的人数比对
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午11:48:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param allList
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	private String zrsYz(RsszForm myForm,List<HashMap<String, String>> allList) throws Exception{
		CsszService csszService = new CsszService();
		CsszForm CsszModel = csszService.getCsszModel();
		/*当前学年*/
		String currXn = CsszModel.getXn();
		/*人数分配方式*/
		String rskzfw = myForm.getRsfpfs();
		/*根据学年查找本学年已通过的人数*/
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> shtgrsList = pjjgService.getShtgrs(rskzfw, myForm.getXmdm(),currXn);
		/*若无申请通过的人数，直接返回*/
		if (shtgrsList == null || shtgrsList.size() == 0) {
			return null;
		}
		List<HashMap<String, String>> cwList = new ArrayList<HashMap<String, String>>();
		/*定义通过人数*/
		String tgrs = null;
		/*定义部门代码*/
		String bmdm = null;
		/*定义最终人数*/
		String zzme = null;
		/*循环总学院*/
		for (HashMap<String, String> map : allList) {
			zzme = map.get("zzme");
			bmdm = map.get("bmdm");
			/*若需设置的总人数为空，跳出*/
			if (zzme == null || zzme.trim().length() == 0 || bmdm == null) {
				continue;
			}
			int iZzrs = 0;
			try {
				/*总人数格式不对*/
				iZzrs = Integer.parseInt(zzme);
			} catch (Exception e) {
				continue;
			}
			/*循环已通过审核的人数*/
			for (HashMap<String, String> shtgrsMap : shtgrsList) {
				tgrs = shtgrsMap.get("tgrs");
				int iTgrs = 0;
				try {
					/*通过人数格式不对*/
					iTgrs = Integer.parseInt(tgrs); 
				} catch (Exception e) {
					continue;
				}
				/*若通过人数为空，跳出*/
				if (tgrs == null || tgrs.trim().length() == 0) {
					continue;
				}
				if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
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
				if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					name = dao.getXymc(bmdm1);
				}
				cw += name;
			}
			cw += "]";
		}
		return cw;
	}
	
	/**
	 * @描述: 对最终人数进行校验，与已申请通过的人数比对
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 下午02:50:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param list
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	private String zrsYzDg(RsszForm myForm, List<RsszForm> list)  throws Exception{
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (RsszForm form : list) {
			map = new HashMap<String, String>();
			map.put("zzme", form.getZzme());
			map.put("bmdm", form.getBmdm());
			map.put("zzme", form.getZzme());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}
}
