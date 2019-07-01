/**
 * @部门:学工产品事业部
 * @日期：2014-4-28 上午10:54:14 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:54:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSzService extends
		SuperServiceImplExtend<JtpjSzForm, JtpjSzDao> {
	JtpjSzDao jsd = new JtpjSzDao();
	/**
	 * 开启
	 */
	private final String SFKFSQ_KQ = "1";
	/**
	 * 关闭
	 */
	private final String SFKFSQ_GB = "0";

	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public JtpjSzService() {
		this.setDao(jsd);
	}

	/**
	 * 
	 * @描述: 获取职务list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午09:27:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getZwList(String xh) {
		return jsd.getZwList(xh);
	}
	public List<HashMap<String, String>> getZwList() {
		return jsd.getZwList();
	}
	public boolean copy(String lyxn, String lyxq, String sqxn, String sqxq)
			throws Exception {
		boolean isok = true;
		List<HashMap<String, String>> list = dao.getJxxxForSqXnXq(lyxn, lyxq);
		JtpjSzForm jsf = new JtpjSzForm();
		for (HashMap<String, String> hm : list) {
			BeanUtils.copyProperties(jsf, hm);
			jsf.setJxid(null);
			jsf.setSqxn(sqxn);
			jsf.setSqxq(sqxq);
			isok = runInsert(jsf);
		}
		return isok;
	}

	/**
	 * 
	 * 
	 * @描述: 获取所有申请周期
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-6 上午09:30:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getSqZqList() {
		return jsd.getSqZqList();
	}

	/**
	 * 
	 * @描述: 获取申请周期，去掉特定周期
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-7 下午04:37:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqzq
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getSqZqListNotIsHave(String sqzq) {
		List<HashMap<String, String>> list = jsd.getSqZqList();
		for (HashMap<String, String> hm : list) {
			if (hm.get("dm").equals(sqzq)) {
				list.remove(hm);
				break;
			}
		}
		return list;
	}

	/**
	 * 
	 * @描述: 获取申请周期，必须包含当前学年学期周期
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-7 下午04:35:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getNowSqZqList() {
		List<HashMap<String, String>> list = jsd.getSqZqList();
		boolean isadd = true;
		for (HashMap<String, String> hm : list) {
			if (hm.get("dm").equals(Base.currXn + "," + Base.currXq)) {
				isadd = false;
			}
		}
		if (isadd) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("dm", Base.currXn + "," + Base.currXq);
			hm.put("mc", Base.currXn + " " + Base.getDqxqmc());
			list.add(hm);
		}
		return list;
	}

	@Override
	public JtpjSzForm getModel(String keyValue) throws Exception {
		JtpjSzForm jsf = super.getModel(keyValue);
		jsf.setSqxqmc(BaseDAO.getInstance().getXqmcForXqdm(jsf.getSqxq()));
		jsf.setPdxqmc(BaseDAO.getInstance().getXqmcForXqdm(jsf.getPdxq()));
		return jsf;
	}

	/**
	 * 
	 * @描述: 获取奖项list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午09:28:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getJxList(String xn, String xq,
			String flg, User user) {
		List<HashMap<String, String>> newlist = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> list = jsd.getJxList(xn, xq, flg,"",user);
		// 如果是学生用户，则验证其职务是否再设定的可申请职务中
		if (null != list && list.size() > 0 && user.getUserType().equals("stu")&&Globals.XXDM_WZDX.equals(Base.xxdm)) {
			String ksqxslx = "";
			String ksqxslxs[] = null;
			for (HashMap<String, String> hm : list) {
				ksqxslx = hm.get("ksqxslx");
				if (StringUtils.isNotNull(ksqxslx)) {
					ksqxslxs = ksqxslx.split(",");
					if (isInZwList(ksqxslxs, user.getUserName())) {
						newlist.add(hm);
					}
				}
			}
		} else {
			newlist = list;
		}
		return newlist;
	}
	/**
	 * 
	 * @描述: 是否在职务列表中
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-29 下午04:56:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ksqxslxs
	 *            设定的职务列表
	 * @return boolean 返回类型
	 */
	public boolean isInZwList(String ksqxslxs[], String xh) {
		List<HashMap<String, String>> zwlist = getZwList(xh);
		for (HashMap<String, String> hm : zwlist) {
			for (String ksqxs : ksqxslxs) {
				if (ksqxs.equals(hm.get("zwid"))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @描述:获取职务名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-29 下午02:15:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zwid
	 * @return String 返回类型
	 */
	public String getZwMc(String zwid) {
		if (null == zwid || zwid.length() <= 0) {
			return null;
		}
		StringBuffer zwmc = new StringBuffer();
		String zwids[] = zwid.split(",");
		for (String id : zwids) {
			zwmc.append(jsd.getZwMc(id));
			zwmc.append(" ");
		}
		return zwmc.toString();
	}

	/**
	 * 
	 * @描述: 获取是否可申请名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-29 下午02:27:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sfkfsq
	 * @return String 返回类型
	 */
	public String getSfkfsqmc(String sfkfsq) {
		String mc = "";
		if (SFKFSQ_KQ.equals(sfkfsq)) {
			mc = "开启";
		} else if (SFKFSQ_GB.equals(sfkfsq)) {
			mc = "关闭";
		}
		return mc;
	}

	/**
	 * 
	 * @描述: 获取审批流程名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-29 下午02:14:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lcid
	 * @param lcxxlx
	 * @return String 返回类型
	 */
	public String getLcxxMc(String lcid, String lcxxlx) {
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx(lcxxlx);
		for (HashMap<String, String> hm : shlc) {
			if (StringUtils.isNotNull(lcid) && lcid.equals(hm.get("splc"))) {
				return hm.get("lcxx");
			}
		}
		return null;
	}

	/** 
	 * @描述: 获取奖项list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-28 下午02:33:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param currXn
	 * @param currXq
	 * @param string
	 * @param jxid
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public Object getJxList(String currXn, String currXq, String flg,
			String jxid,User user) {
		return jsd.getJxList(currXn, currXq, flg, jxid,user);
	}
	
	/**
	 * 
	 * @描述:集体评奖项目判断是否已被使用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-16 上午10:53:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid){
		return dao.checkIsNotExists(jxid);
	}
	
	/**
	 * 
	 * @描述:集体评奖项目判断是否已被使用(删除)
	 * @作者：taogj[工号：1075]
	 * @日期：2016-6-20 下午02:26:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	  public String isCheckExistForDel(String value)throws Exception{
		  String resultjxmc="";
	      String[] jxmcArr =dao.isCheckExistForDel(value);
	      for(int i=0;i<jxmcArr.length;i++){ 
			  if(i==jxmcArr.length-1){
				  resultjxmc+=jxmcArr[i];
			  }else{
				  resultjxmc+=jxmcArr[i]+",";
			  }
		  }
		  return resultjxmc;
	  }
	
	/**
	 * 
	 * @描述:集体评奖项目设置重复性判断
	 * @作者：taogj[工号：1075]
	 * @日期：2016-6-20 上午11:10:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByJxmc(JtpjSzForm model) {
		
		String num=dao.isExistByJxmc(model);
    	return  "0".equalsIgnoreCase(num);
	}
	
	public List<HashMap<String,String>> getBbxxList(){
		return dao.getBbxxList();
	}
	
	public List<HashMap<String,String>> getBbxxList(String bbdm){
		return dao.getBbxxList(bbdm);
	}
	
}
