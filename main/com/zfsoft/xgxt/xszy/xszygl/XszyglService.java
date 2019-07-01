/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午11:25:34 
 */  
package com.zfsoft.xgxt.xszy.xszygl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-11 上午11:25:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszyglService extends SuperServiceImpl<XszyglForm, XszyglDao>{
	private XszyglDao dao = new XszyglDao();
	/**
	 * 
	 * @描述:页面参数初始化
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-11 下午03:54:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public void initParam(HttpServletRequest request,User user){
		XszyQshfDao qshfDao  = new XszyQshfDao();
		DAO dao = new DAO();
		List<HashMap<String, String>> dwList = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> dwMap = new HashMap<String,String>();
		if("xy".equals(user.getUserStatus())){
			dwMap.put("xydm", user.getUserDep());
			dwMap.put("xymc", qshfDao.getBmmc(user.getUserDep()));
			dwList.add(dwMap);
		}else{
			dwList=qshfDao.getBmList();
		}
		request.setAttribute("dwList", dwList);
		request.setAttribute("zzmmList", dao.getZzmmList());
		
	}
	public boolean isHaveXszy(XszyglForm model) {
		model.setNj(Base.currNd);
		return dao.isHaveXszy(model);
	}
	/**
	 * 
	 * @描述:新生之友保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-11 下午05:07:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editXszy(XszyglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			model.setNj(Base.currNd);
			String id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @描述:查询新生之友信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-12 上午09:45:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXszy(XszyglForm t) throws Exception {
		return dao.getXszy(t);
	}
	/**
	 * 
	 * @描述:跨院系标记
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-12 下午03:00:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean kyxbj(XszyglForm model,HttpServletRequest request) throws Exception {
		String[] ids = request.getParameter("ids").split(",");
		List<String[]> xszyList = new ArrayList<String[]>();
		String[] xszyxx = null;
		for (int i = 0; i < ids.length; i++) {
			xszyxx = new String[2];
			xszyxx[0] = model.getKyxbj();
			xszyxx[1] = ids[i];
			xszyList.add(xszyxx);
		}
		boolean result = dao.kyxbj(xszyList);
		return result;
	}
	/**
	 * 
	 * @描述:院系分配
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-12 下午03:00:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean fpyx(XszyglForm model,HttpServletRequest request) throws Exception {
		String[] ids = request.getParameter("ids").split(",");
		List<String[]> xszyList = new ArrayList<String[]>();
		String[] xszyxx = null;
		for (int i = 0; i < ids.length; i++) {
			xszyxx = new String[2];
			xszyxx[0] = model.getBjyx();
			xszyxx[1] = ids[i];
			xszyList.add(xszyxx);
		}
		boolean result = dao.bjyx(xszyList);
		return result;
	}
}
