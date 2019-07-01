/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:40:07 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;



import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmjxForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-7-13 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmsbjgService extends SuperServiceImpl<XmsbjgForm, XmsbjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	
	private XmsbjgDao dao = new XmsbjgDao();
	private XmsbDao xmsbDao = new XmsbDao();
	private XmsbService xmsbService = new XmsbService();

	public boolean isHaveSbjg(XmsbjgForm model) {
		return dao.isHaveSbjg(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:项目申报结果增加保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 上午09:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean editXmsbjg(XmsbjgForm model, List<XmjxForm> xmjxList,String[] cyxyArr) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String jgid = UniqID.getInstance().getUniqIDHash();
			model.setJgid(jgid);
			model.setXmdm(jgid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			// 删除奖项信息，再插入
			result = xmsbDao.delXmjx(model.getXmdm());
		}
		//保存参与学院信息
		xmsbService.cyxyPlbc(cyxyArr,model.getXmdm());
		if(null==xmjxList){
			return true;
		}
		
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		String xmjxid = null;
		for (XmjxForm xmjxForm : xmjxList) {
			jxxx = new String[5];
			xmjxid = UniqID.getInstance().getUniqIDHash();
			jxxx[0] = xmjxid;
			jxxx[1] = model.getXmdm();
			jxxx[2] = xmjxForm.getFjxf();
			jxxx[3] = xmjxForm.getJxmc();
			jxxx[4] = xmjxForm.getXssx();
			jxxxList.add(jxxx);
		}
		return xmsbDao.jxxxPlbc(jxxxList);

	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取项目申报结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getXmsbjg(XmsbjgForm t) throws Exception {
		return dao.getXmsbjg(t);
	}

	/**
	 * 
	 * @描述:删除项目奖项
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 上午11:32:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             XmsbjgForm 返回类型
	 * @throws
	 */
	public boolean delXmsbjg(String[] ids) throws Exception {
		List<String[]> xmjxList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[1];
			idArr[0] = ids[i];
			xmjxList.add(idArr);
		}
		return xmsbDao.delPlXmjx(xmjxList);
	}
	/**
	 * 
	 * @描述:设置热门项目
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-20 上午08:55:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param sfrm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setRmxm(String[] ids,String sfrm) throws Exception {
		List<String[]> rmszList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[2];
			idArr[0] = sfrm;
			idArr[1] = ids[i];
			rmszList.add(idArr);
		}
		return dao.setRmxm(rmszList);
	}
	
	/**
	 * 获取项目申请学生列表
	 */
	public List<HashMap<String,String>> getSqxsList(String xmdm,String xh) throws Exception {
		return dao.getSqxsList(xmdm,xh);
	}
	
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		return dao.isHaveSqJl(values);
	}
	
	public List<HashMap<String, String>> getXsxxList(XmsbjgForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhArr(new String[]{});
		}
		else{
			model.setXhArr(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	/**
	 * 
	 * @描述:获取参与学院
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-18 下午04:06:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCyxyList(String xmdm){
		return dao.getCyxyList(xmdm); 
	}
	
	public String getXylb(String sbbmdm){
		return dao.getXylb(sbbmdm); 
	}
	
	public List<HashMap<String, String>> getXmForJcfrd(String xmdm){
		return xmsbDao.getXmForJcfrd(xmdm);
	}
	
	/**
	 * 
	 * @描述:获取项目阶段设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-21 上午11:39:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmjdSzList(String xmdm){
		return dao.getXmjdSzList(xmdm);
	}
	
	/**
	 * 
	 * @描述:保存阶段设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-22 上午09:08:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param jdid
	 * @param jdmc
	 * @param jdf
	 * @param jdsx
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJdsz(String xmdm,String[] jdid,String[] jdmc,String[] jdf,String[] jdsx) throws Exception{
		return dao.saveJdsz(xmdm, jdid, jdmc, jdf, jdsx);
	}
	
	/**
	 * 
	 * @描述:获取参加团体项目的人员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-8 下午01:59:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyrs(String xmdm){
		return dao.getTtxmcyrs(xmdm);
		
	}

}
