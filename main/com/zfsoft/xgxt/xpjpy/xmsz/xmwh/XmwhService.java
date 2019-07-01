/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午02:39:49 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxDao;
import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszModel;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tzsz.TzszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 下午02:39:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmwhService extends SuperServiceImpl<XmwhModel, XmwhDao> {

	private XmwhDao dao = new XmwhDao();

	public XmwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述: 开放申请或上报的评奖项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 下午02:59:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getKfsqPjxm() {
		return dao.getKfsqPjxm();
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq,String bjdms) {
		
		String newBjdms = "";
		String[] bjdmss = bjdms.split(",");
		for(int i=0;i<bjdmss.length;i++){
			newBjdms += "'"+bjdmss[i]+"',";
		}
		if(newBjdms.length()>0){
			newBjdms=newBjdms.substring(0, newBjdms.length()-1);
		}
		
		return dao.getKfsqPjxm(xnxq,newBjdms);
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq) {
		return dao.getKfsqPjxm(xnxq);
	}

	/**
	 * 
	 * @描述:获取所有项目，不包含当前项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm)
			throws Exception {
		String currXn = CsszService.getPjzq().get("xn");// 当前学年
		String currXq = CsszService.getPjzq().get("xq");// 当年学期

		return dao.getOthers(xmdm, currXn, currXq);
	}

	public List<HashMap<String, String>> getOthers(String xmdm,String xzdm)
			throws Exception {
		String currXn = CsszService.getPjzq().get("xn");// 当前学年
		String currXq = CsszService.getPjzq().get("xq");// 当年学期

		return dao.getOthers(xmdm, currXn, currXq,xzdm);
	}

	/**
	 * 
	 * @描述:判断重复数据，以名称为准
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @throws
	 */
	public boolean isRepeat(XmwhModel model) throws Exception {
		return dao.isRepeat(model);
	}

	/**
	 * 
	 * @描述:判断关联性，数据可否处理
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(XmwhModel model) throws Exception {
		XmwhModel modelOld = dao.getModel(model);
		boolean flag = false;
		if (!model.getXmmc().trim().equals(modelOld.getXmmc().trim())
				|| !model.getLxdm().equals(modelOld.getLxdm())) {
			// flag = dao.isRalate(model);
		}
		return flag;
	}

	/**
	 * 
	 * @描述:判断关联性，数据可否处理
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		boolean flag = false;
		// 是否有学生申请项目
		SqshService sqshService = new SqshService();
		if (values != null) {
			String[] xmdms = values.split(",");
			for (int i = 0; i < xmdms.length; i++) {
				flag = sqshService.isExistsFlowData(xmdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * @删除关联表
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean delRalate(String values) throws Exception {
		return dao.delRalate(values);
	}

	/**
	 * 
	 * @描述:根据项目代码查询记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		return dao.getDataById(xmdm);
	}

	/**
	 * 
	 * @描述:根据项目名称查询单条记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn, String xq) throws Exception {
		return dao.getDataByName(xmmc, xn, xq);
	}

	/**
	 * 
	 * @描述:根据项目代码查询项目名称
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		return dao.getNameById(xmdm);
	}

	/**
	 * 
	 * @描述:通过项目代码获取已经设置的年级,年级以逗号分割
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @return String 返回类型
	 * @throws
	 */
	public String getRsfpnj(String xmdm) throws Exception {
		return dao.getRsfpnj(xmdm);
	}

	/**
	 * 
	 * @描述:获取项目性质
	 * @作者：ligl
	 * @日期：2013-8-7 上午11:26:33
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		return dao.getXmxz();
	}

	/**
	 * 
	 * @描述:获取项目类型
	 * @作者：ligl
	 * @日期：2013-8-7 上午11:26:33
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx(String xzdm) throws Exception {
		return dao.getXmlx(xzdm);
	}

	/**
	 * 
	 * @描述:得到当前学年学期，格式为:2012-2013 秋
	 * @作者：ligl
	 * @日期：2013-8-8 上午11:05:27
	 * @修改记录:
	 * @return String 返回类型
	 * @throws
	 */
	public String getCurrXnXqmc() throws Exception {
		CsszModel csszModel = new CsszDao().getModel();
		String currXn = csszModel.getXn();// 当前学年
		String currXq = csszModel.getXq();// 当年学期
		return xnXqGshMc(currXn, currXq);
	}

	/*
	 * 学年学期格式化，返回 2012-2013 春
	 */
	private String xnXqGshMc(String xn, String xq) throws Exception {
		String xqmc = null;
		List<HashMap<String, String>> xqList = Base.getXqList();
		for (HashMap<String, String> map : xqList) {
			if (xq != null && xq.equals(map.get("xqdm"))) {
				xqmc = map.get("xqmc");
			}
		}
		
//		由于学年当中的学期存的是'on'  取消显示
		if (xqmc == null) {
			xqmc = "";
		}
		
		
		return xn + " " + xqmc;
	}

	/*
	 * 学年学期格式化，返回 2012-2013;01
	 */
	private String xnXqGshDm(String xn, String xq) throws Exception {
		return xn + ";" + xq;
	}

	/**
	 * 
	 * @描述:查询可以复制的奖项来源
	 * @作者：ligl
	 * @日期：2013-8-14 上午11:06:11
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfz() throws Exception {
		List<HashMap<String, String>> jxfzList = new ArrayList<HashMap<String, String>>();
		CsszDao  csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		if (csszModel == null) {

		}
		String currXn = csszModel.getXn();// 当前学年
		String currXq = csszModel.getXq();// 当年学期
		String zczq = csszDao.getCsz("zczq");
		List<HashMap<String, String>> xnxqList = dao.getJxfzXnXq(zczq);
		String xn = null;
		String xq = null;
		HashMap<String, String> jxfzMap = null;
		if (xnxqList != null) {
			for (HashMap<String, String> map : xnxqList) {
				xn = map.get("xn");
				xq = map.get("xq");
				if (!xn.equals(currXn) || !xq.equals(currXq)) {
					jxfzMap = new HashMap<String, String>();
					jxfzMap.put("dm", xnXqGshDm(xn, xq));
					jxfzMap.put("mc", xnXqGshMc(xn, xq));
					jxfzList.add(jxfzMap);
				}
			}
		}
		return jxfzList;
	}

	/**
	 * @描述:奖项复制
	 * @作者：ligl
	 * @日期：2013-8-14 下午01:48:30
	 * @修改记录:
	 * @param jxfznd
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean runJxfz(String jxfznd) throws Exception {
		boolean flag = false;
		CsszModel csszModel = new CsszDao().getModel();
		String currXn = csszModel.getXn();// 当前学年
		String currXq = csszModel.getXq();// 当年学期
		TjszService tjszService = new TjszService();
		JdszService jdszService = new JdszService();
		TzszService tzszService = new TzszService();
		String fzXn = jxfznd.split(";")[0];
		String fzXq = jxfznd.split(";")[1];
		List<HashMap<String, String>> jxfzList = dao.getJxfz(fzXn, fzXq,
				currXn, currXq);
		if (jxfzList != null && jxfzList.size() > 0) {
			for (int i = 0; i < jxfzList.size(); i++) {
				String guid = UniqID.getInstance().getUniqIDHash();
				guid = guid.toUpperCase();
				jxfzList.get(i).put("id", guid);
			}
			flag = dao.saveData(jxfzList, currXn, currXq);
			//奖项复制：复制条件设置、兼得设置、奖项调整设置
			List<TjszModel> list = null;
			for (HashMap<String, String> jxfzMap : jxfzList) {
				list=new ArrayList<TjszModel>();
				List<HashMap<String, String>> tjList = tjszService.getTjsz(jxfzMap.get("xmdm"));
				List<HashMap<String, String>> jdList = jdszService.getByXmdm(jxfzMap.get("xmdm"),currXn, currXq);
				List<HashMap<String, String>> tzList = tzszService.getByXmdm(jxfzMap.get("xmdm"),currXn, currXq);
				StringBuffer jdXmdms = new StringBuffer();
				StringBuffer tzXmdms = new StringBuffer();
				for (int i = 0; i < jdList.size(); i++) {
					if(i<jdList.size()-1){
						jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
						jdXmdms.append(",");
					}else{
						jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
					}
				}
				for (int i = 0; i < tzList.size(); i++) {
					if(i<tzList.size()-1){
						tzXmdms.append(tzList.get(i).get("fztzjxdm"));
						tzXmdms.append(",");
					}else{
						tzXmdms.append(tzList.get(i).get("fztzjxdm"));
					}
				}
				for (HashMap<String, String> tjMap : tjList) {
					TjszModel tjModel = new TjszModel();
					tjModel.setTjdm(tjMap.get("tjdm"));
					tjModel.setYyfw(tjMap.get("yyfw"));
					tjModel.setGxdm(tjMap.get("gxdm"));
					tjModel.setTjz(tjMap.get("tjz"));
					tjModel.setYlzq(tjMap.get("ylzq"));
					list.add(tjModel);
				}
				flag = tjszService.saveOrUpdate(jxfzMap.get("id"), list);
				flag = jdszService.jdsz(jxfzMap.get("id"), jdXmdms.toString());
				flag = tzszService.shsz(jxfzMap.get("id"), tzXmdms.toString());
			}
			
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：ligl
	 * @日期：2013-8-15 上午10:03:15
	 * @修改记录: 
	 * @param dateFormat
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrTime(String dateFormat){
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
	
	/**
	 * 
	 * @描述:评奖项目序号重复验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-17 下午02:45:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistXssx(XmwhModel model) throws Exception {
		String num = dao.checkExistXssx(model);
		return Integer.valueOf(num) > 0;
	}
	/**
	 * @描述: 浙江传媒学院【项目性质】除了“国家奖学金”不选中，其余全部默认选中
	 * @作者： 孟威[工号：1186]
	 * @日期： 2015-12-31 下午01:03:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getXmxzmw() throws Exception {
		return dao.getXmxzmw();
	}
	
	/**
	 * 
	 * @描述:根据项目代码查询项目名称（集体评奖）
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String getNameByIdJtpj(String xmdm) throws Exception {
		return dao.getNameByIdJtpj(xmdm);
	}
	
	/**
	 * @description	： 是否优秀干部
	 * @author 		： lj（1282）
	 * @date 		：2018-4-28 下午02:50:39
	 * @param xmdm
	 * @return
	 */
	public String getSfyxgb(String xmdm){
		return dao.getSfyxgb(xmdm);
	}

	public List<HashMap<String,String>> getnewXmlx(String xmxz) {
		return dao.getnewXmlx(xmxz);
	}

	/**
	 * @描述:获取培养层次列表
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/29 20:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getPyccList(){
		XsxxDao xsxxDao = new XsxxDao();
		return xsxxDao.getPyccList();
	}

    public String getXsxh(String xzdm) {
		return dao.getXsxh(xzdm);
    }
}
