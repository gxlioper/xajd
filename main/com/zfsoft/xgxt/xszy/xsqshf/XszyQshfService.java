/**
 * @部门:学工产品事业部
 * @日期：2015-2-6 下午04:36:55 
 */
package com.zfsoft.xgxt.xszy.xsqshf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xszy.qsppgl.XszyQsppDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-6 下午04:36:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XszyQshfService extends
		SuperServiceImpl<XszyQshfForm, XszyQshfDao> {
	private XszyQshfDao dao = new XszyQshfDao();
	private XszyQsppDao qsppDao = new XszyQsppDao();
	private static final String FPZT="0";
	private static final String THZT="1";
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";

	/**
	 * 
	 * @描述:获取部门列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-9 下午03:16:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}
	

	/**
	 * 
	 * @描述:获取寝室信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 上午11:27:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getQsxx(XszyQshfForm t) throws Exception {
		return dao.getQsxx(t);
	}

	/**
	 * 
	 * @描述:查询入住学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 上午11:30:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getRzxsList(XszyQshfForm t)
			throws Exception {
		return dao.getRzxsList(t);
	}
	
	public List<HashMap<String, String>> getRzxsListOfLxk(XszyQshfForm t)
			throws Exception {
		return dao.getRzxsListOfLxk(t);
	}

	public List<HashMap<String, String>> getXsBzrList(XszyQshfForm t)
			throws Exception {
		return dao.getXsBzrList(t);
	}

	public List<HashMap<String, String>> getXsFdyList(XszyQshfForm t)
			throws Exception {
		return dao.getXsFdyList(t);
	}

	/**
	 * 
	 * @描述:查看分配详情
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-11 上午10:38:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getFpxq(XszyQshfForm t,User user)
			throws Exception {
		return dao.getFpxq(t,user);
	}
	/**
	 * 
	 * @描述:学院分配统计
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-22 下午01:54:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFptj(XszyQshfForm t,User user)
	throws Exception {
	return dao.getFptj(t,user);
	}

	/**
	 * 
	 * @描述:全校寝室信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-11 上午10:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getQsxxAll(XszyQshfForm myForm) throws Exception {
		return dao.getQsxxAll(myForm);
	}

	/**
	 * 
	 * @描述:寝室分配操作
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-9 下午04:41:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean editFpcz(XszyQshfForm model, User user) throws Exception {
		boolean result = true;
		model.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setFpczr(user.getUserName());
		model.setSsyxmc(dao.getBmmc(model.getSsyxdm()));
		if ("save".equals(model.getType())) {
			String id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			dao.delQsfp(model);
			result = dao.runInsert(model);
			//浙大个性化学园退回给求是学院
			if("90".equals(model.getSsyxdm())){
				model.setFpzt(THZT);
				model.setThxy(model.getSsyxdm());
				model.setThr(user.getRealName());
			}else{
				model.setFpzt(FPZT);
			}
			result=dao.updateQsxxZt(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}

	/**
	 * 
	 * @描述:获取寝室数量
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 下午02:54:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public HashMap<String, String> getQss(XszyQshfForm model) throws Exception {
		HashMap<String, String> qssMap = new HashMap<String, String>();

		String[] qshArr = model.getQsh().split(",");
		String[] sfhhqs = model.getSfhhqs().split(",");
		String[] qsxbArr = model.getQsxb().split(",");
		// 混合寝室数
		int hhqss = 0;
		// 男生寝室数
		int mqss = 0;
		for (int i = 0; i < qshArr.length; i++) {
			if (0 != sfhhqs.length && "是".equals(sfhhqs[i])) {
				hhqss++;
			}
			if ("男".equals(qsxbArr[i])) {
				mqss++;
			}

		}
		qssMap.put("qss", String.valueOf(qshArr.length));
		qssMap.put("hhqss", String.valueOf(hhqss));
		qssMap.put("mqss", String.valueOf(mqss));
		// 女生寝室数
		qssMap.put("wqss", String.valueOf(qsxbArr.length - mqss));
		return qssMap;
	}

	/**
	 * 
	 * @描述:批量分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 下午02:19:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean savePlfp(XszyQshfForm model, User user) throws Exception {
		String thsj=GetTime.getTimeByFormat(DATE_FORMAT);
		model.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setFpczr(user.getUserName());
		
		model.setSsyxmc(dao.getBmmc(model.getSsyxdm()));
		String[] lddmArr = model.getLddm().split(",");
		String[] qshArr = model.getQsh().split(",");
		List<String[]> qshfList = new ArrayList<String[]>();
		String[] qshfxx = null;
		List<String[]> qsxxList = new ArrayList<String[]>();
		List<String[]> qsxxdelList = new ArrayList<String[]>();
		String[] qsxx = null;
		String[] qsxxdel = null;
		if(""!=model.getLddm()){
		for (int i = 0; i < lddmArr.length; i++) {
			qsxx=new String[6];
			qsxxdel=new String[3];
			qshfxx = new String[7];
			qshfxx[0] = lddmArr[i];
			qshfxx[1] = qshArr[i];
			qshfxx[2] = Base.currNd;
			qshfxx[3] = model.getSsyxdm();
			qshfxx[4] = model.getSsyxmc();
			qshfxx[5] = model.getFpczr();
			qshfxx[6] = model.getCzsj();
			//学院个性化退回给求是学院
			if("90".equals(model.getSsyxdm())){
				qsxx[0]=THZT;
				qsxx[1]=user.getRealName();
				qsxx[2]=thsj;
				qsxx[3]=model.getSsyxdm();
				
			}else{
				qsxx[0]=FPZT;
				qsxx[1]="";
				qsxx[2]="";
				qsxx[3]="";
				
			}
			qsxx[4]=lddmArr[i];
			qsxx[5]=qshArr[i];
			qsxxdel[0]=lddmArr[i];
			qsxxdel[1]=qshArr[i];
			qsxxdel[2]=Base.currNd;
			qsxxList.add(qsxx);
			qshfList.add(qshfxx);
			qsxxdelList.add(qsxxdel);
		}
		}
		qsppDao.qsPlth(qsxxList);
		
		boolean result = dao.qshfPlbc(qshfList,qsxxdelList);
		return result;
	}

}
