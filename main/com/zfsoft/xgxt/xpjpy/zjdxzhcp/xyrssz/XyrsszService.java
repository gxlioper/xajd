/**
 * @部门:学工产品事业部
 * @日期：2017-7-18 上午09:30:58 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz.RsszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 学院人数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-18 上午09:30:58 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyrsszService extends SuperServiceImpl<XyrsszForm,XyrsszDao>{
	
	private XyrsszDao dao = new XyrsszDao();

	public XyrsszService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 学院人数查询
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-19 上午10:24:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRsszList (XyrsszForm t,User user)
		throws Exception {
		
		String rskzfw = t.getRsfpfs();
		
		if (StringUtil.isNull(rskzfw)){
			return null;
		}

		/*学院*/
		if (rskzfw.equals(Constants.RSKZFW_XY)) {
			return dao.getRsszList(t,user);
		}
		return null;
	}
	
	/**
	 * @描述: 最终人数设置 保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-20 下午02:48:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String setZzrs(XyrsszForm model, User user) throws Exception {
		String message = null;
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		
		String[] guids = model.getGuids();
		String[] zzmes = model.getZzmes();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		
		String rskzfw = model.getRsfpfs();
		
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzme", zzmes[i]);
				 if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
					map.put("bmdm", xydms[i]);
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
			message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}

		return message;
	}
	
	/**
	 * 对最终人数进行校验，与已申请通过的人数比对
	 */
	private String zrsYz(XyrsszForm  myForm,List<HashMap<String, String>> allList) throws Exception{
		
		/*参数设置信息*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		
		/*当前学年*/
		String currXn = csszModel.getXn();
		
		/*人数分配方式，实际本来就是个浙大个性化的，而且是学院评奖，用不用都无所谓，暂且就按照老的评奖先做吧*/
		String rskzfw = myForm.getRsfpfs();
		
		List<HashMap<String, String>> shtgrsList = getShtgrs(rskzfw, myForm.getXmdm(),currXn);
		
		/*若无申请通过的人数，直接返回*/
		if (shtgrsList == null || shtgrsList.size() == 0) {
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
	 * 查询历年通过的人数
	 */
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xmdm,String xn) {
		return dao.getZzmeByXy(xmdm, xn);
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
	public List<HashMap<String,String>> getJxjze(XyrsszForm model,User user)throws Exception{
		return dao.getJxjze(model,user);
	}
	
	public int getYszrs(XyrsszForm model) throws Exception {
		return dao.getYszrs(model);
	}
}
