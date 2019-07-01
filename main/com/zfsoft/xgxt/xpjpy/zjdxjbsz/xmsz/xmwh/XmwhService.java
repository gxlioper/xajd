/**
 * @部门:学工产品1部
 * @日期：2017-4-7 上午10:59:15 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优_项目设置_项目维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-7 上午10:57:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmwhService extends SuperServiceImpl<XmwhForm,XmwhDao>{
	
	private XmwhDao dao = new XmwhDao();
	
	public XmwhService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 获取参数设置信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-4-10 上午10:19:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		return dao.getCsszMap();
	}
	
	/**
	 * @描述: 获取项目类型list
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 上午10:20:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		return dao.getXmlx();
	}
	
	/**
	 * @描述: 获取系统当前时间
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午05:19:10
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	 * @描述: 获取性质类型list
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 上午10:20:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		return dao.getXmxz();
	}
	
	/**
	 * @描述: 验证同一个学年是否有显示顺序重复
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午02:12:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistXssx(XmwhForm model) throws Exception {
		String num = dao.checkExistXssx(model);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @描述: 验证同一个学年是否有项目名称重复
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午02:28:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	 public boolean isExistXmmc(XmwhForm model) throws Exception {
		return dao.isExistXmmc(model);
	 }
	 
	 /**
	  * @描述: 判断所选删除的项目 是否在项目申请时被使用
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-4-11 下午04:08:28
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param values
	  * @return
	  * @throws Exception
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean checkForXmsq(String values) throws Exception {
		boolean flag = false;
		// 是否有学生申请项目
		if (values != null) {
			String[] xmdms = values.split(",");
			for (int i = 0; i < xmdms.length; i++) {
				flag = isExistsFlowData(xmdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	 }
	 
	 /*判断所选删除的项目 是否在项目申请时被使用__返回值*/
	 public boolean isExistsFlowData(String xmdm) {
		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	 }
	 
	 /**
	  * @描述: 清理需要删除项目 的关联表
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-4-11 下午04:21:32
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param values
	  * @return
	  * @throws Exception
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean deleteRelationalTable(String values) throws Exception {
		return dao.deleteRelationalTable(values);
	}
	 
	 /**
	  * @描述: 根据项目代码获得项目名称
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-4-12 上午10:00:39
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xmdm
	  * @return
	  * @throws Exception
	  * String 返回类型 
	  * @throws
	  */
	 public String getNameById(String xmdm) throws Exception {
			return dao.getNameById(xmdm);
	}
	 
	 /**
	  * @描述: 奖项复制取非当前评奖学年的学年列表
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-4-12 下午03:55:14
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> 返回类型 
	  * @throws
	  */
	 public List<HashMap<String, String>> getJxfz() throws Exception {
		List<HashMap<String, String>> jxfzList = new ArrayList<HashMap<String, String>>();
		CsszDao csszDao = new CsszDao();
		CsszForm csszXx = csszDao.getModel();
		/*取当前评奖学年*/
		String currXn = csszXx.getXn();
		/*取评奖项目表中所有的xn列表*/
		List<HashMap<String, String>> xnList = dao.getJxfzXn();
		String xn = null;
		HashMap<String, String> jxfzMap = null;
		if (xnList != null) {
			for (HashMap<String, String> map : xnList) {
				xn = map.get("xn");
				/*只取非当前评奖学年的数据*/
				if (!xn.equals(currXn)) {
					jxfzMap = new HashMap<String, String>();
					jxfzMap.put("dm", xn);
					jxfzMap.put("mc", xn);
					jxfzList.add(jxfzMap);
				}
			}
		}
		return jxfzList;
	}
	 
	 public boolean runJxfz(String jxfznd) throws Exception {
		 boolean flag = false;
		 //XmwhService service = new XmwhService();
		 CsszDao csszDao = new CsszDao();
		 CsszForm csszForm = csszDao.getModel();
		 /*当前评奖学年*/
		 String currXn = csszForm.getXn();
		 /*复制目标学年*/
		 String fzXn = jxfznd.split(";")[0];
		 /*查询出复制学年与本学年不同项目名称的数据*/
		 List<HashMap<String, String>> jxfzList = dao.getJxfz(fzXn,currXn);
		 if (jxfzList != null && jxfzList.size() > 0) {
				for (int i = 0; i < jxfzList.size(); i++) {
					/*自动生成GUID*/
					String guid = UniqID.getInstance().getUniqIDHash();
					guid = guid.toUpperCase();
					/*把guid数组变为项目代码，准备插入评奖项目表中*/
					jxfzList.get(i).put("xmdm", guid);
				}
				/*保存操作*/
				flag = dao.saveJxfzData(jxfzList, currXn);
				/*复制奖项兼得*/
				List<XmwhForm> list = null;
				/*【jxfzMap】复制目标学年与当前年不同项目名称的数据*/
				for (HashMap<String, String> jxfzMap : jxfzList) {
					list=new ArrayList<XmwhForm>();
					/*根据xmdm查询设置的记录*/
					List<HashMap<String, String>> jdList = getByXmdm(jxfzMap.get("xmdm"),currXn);
					StringBuffer jdXmdms = new StringBuffer();
					for (int i = 0; i < jdList.size(); i++) {
						if(i<jdList.size()-1){
							jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
							jdXmdms.append(",");
						}else{
							jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
						}
					}
					/*兼得设置*/
					flag =jdsz(jxfzMap.get("xmdm"), jdXmdms.toString());
				}
		 }
		 return flag;
	 }
	 
	 /**
	  * @描述: 根据xmdm查询设置的记录
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-4-18 上午08:45:29
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xmdm
	  * @param currXn
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> 返回类型 
	  * @throws
	  */
	 public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn) throws Exception{
		return dao.getByXmdm(xmdm,currXn);		
	 }
	 
	 /**
	  * @描述: 兼得设置
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-4-18 上午08:46:23
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xmdm
	  * @param xmdms
	  * @return
	  * @throws Exception
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean jdsz(String xmdm,String xmdms) throws Exception{
		return dao.runJdsz(xmdm,xmdms);
	 }
	 
	 /**
	  * @描述: 根据项目代码查询记录
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-5-16 下午03:54:56
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xmdm
	  * @return
	  * @throws Exception
	  * HashMap<String,String> 返回类型 
	  * @throws
	  */
	 public HashMap<String, String> getDataById(String xmdm) throws Exception {
			return dao.getDataById(xmdm);
		}
	 
	 /**
	  * @描述: 通过项目代码获取已经设置的年级,年级以逗号分割
	  * @作者： Meng.Wei[工号：1186]
	  * @日期：2017-6-1 下午01:45:04
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xmdm
	  * @return
	  * @throws Exception
	  * String 返回类型 
	  * @throws
	  */
	public String getRsfpnj(String xmdm) throws Exception {
		return dao.getRsfpnj(xmdm);
	}
	
	/**
	 * @描述: 根据学年、项目名称查询项目对应数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-14 上午11:16:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc
	 * @param xn
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn) throws Exception {
		return dao.getDataByName(xmmc, xn);
	}
}
