/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午05:04:19 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 下午05:04:19
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmsbService extends SuperServiceImpl<XmsbForm, XmsbDao> {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	XmsbDao dao = new XmsbDao();

	/**
	 * 
	 * @描述:获取项目信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午09:02:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXmxx(String xmdm) {
		return dao.getXmxx(xmdm);
	}

	/**
	 * 
	 * @描述:考核申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午02:26:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveXmsb(XmsbForm model,List<XmjxForm> jxxxList,String[] cyxyArr) throws Exception {
		String xmdm = UniqID.getInstance().getUniqIDHash();
		model.setXmdm(xmdm);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);
		//保存参与学院信息
		cyxyPlbc(cyxyArr,xmdm);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(xmdm, splc, model.getXmdm(), "sztz_xmsbgl_xmsh.do", "sztz_xmsbgl_xmsb.do");
			}
		}
		return jxxxPlbc(model, jxxxList);
	}
	/**
	 * 
	 * @描述:保存项目奖项信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 下午03:08:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param jxxxList
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	private boolean jxxxPlbc(XmsbForm model, List<XmjxForm> jxxxList) throws SQLException {
		List<String[]> xmxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		String jgid = null;
		if(null==jxxxList){
			return true;
		}
		for (XmjxForm xmjxForm : jxxxList) {
			
			jxxx = new String[5];
			jgid=UniqID.getInstance().getUniqIDHash();
			jxxx[0] = jgid;
			jxxx[1] = model.getXmdm();
			jxxx[2] = xmjxForm.getFjxf();
			jxxx[3] = xmjxForm.getJxmc();
			jxxx[4] = xmjxForm.getXssx();
		
			xmxxList.add(jxxx);
		}
		return dao.jxxxPlbc(xmxxList);
		
	}

	/**
	 * 
	 * @描述:项目上报修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午02:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveEditXmsb( XmsbForm model,List<XmjxForm> jxxxList,String[] cyxyArr) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmdm(), splc, model.getXmdm(), "sztz_xmsbgl_xmsh.do", "sztz_xmsbgl_xmsb.do");
			}
		} else {
			result = runUpdate(model);
		}
		//保存参与学院信息
		cyxyPlbc(cyxyArr,model.getXmdm());
		//删除项目奖项信息，再插入
		result = dao.delXmjx(model.getXmdm());
		
		return jxxxPlbc(model, jxxxList);
	
	}
	/**
	 * 
	 * @描述:项目上报提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 下午03:17:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitXmsb(XmsbForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmdm(), splc, model.getXmdm(), "sztz_xmsbgl_xmsh.do", "sztz_xmsbgl_xmsb.do");
			}
			return result;
	}

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午03:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSbJl(XmsbForm model, String czlx) throws Exception {
		return dao.isHaveSbJl(model, czlx);
	}
    /**
     * 
     * @描述:初始化下拉列表
     * @作者：夏夏[工号：1104]
     * @日期：2015-7-10 上午10:59:11
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param request
     * @param user
     * @throws Exception
     * void 返回类型 
     * @throws
     */
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> dwMap = new HashMap<String,String>();
		if(!"xx".equals(user.getUserStatus())){
			dwMap.put("xydm", user.getUserDep());
			dwMap.put("xymc", dao.getBmmc(user.getUserDep()));
			bmList.add(dwMap);
		}else{
			bmList=dao.getBmList();
		}
		request.setAttribute("sbrxm", user.getRealName());
		request.setAttribute("xyList", Base.getXyNewList());
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yhbmdm", user.getUserDep());
		request.setAttribute("xmjbList", dao.getXmjbList());
		request.setAttribute("xmkmList", dao.getXmkmList());
		request.setAttribute("bkgsList", dao.getBkgsList());
		
	}
	public List<HashMap<String, String>> getXmjxList(String xmdm) {
		return dao.getXmjxList(xmdm);

	}
	public List<HashMap<String, String>> getCyxyListForView(String xmdm) {
		return dao.getCyxyListForView(xmdm);

	}
	/**
	 * 
	 * @描述:删除项目奖项
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-14 下午03:07:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delPlXmjx(String[] ids) throws Exception {
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[1];
			idArr[0]=ids[i];
			jxxxList.add(idArr);
		}
		return dao.delPlXmjx(jxxxList);
	}
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @描述:参与学院批量保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-18 下午05:36:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cyxyArr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cyxyPlbc(String[] cyxyArr,String xmdm) throws Exception{
		List<String[]> cyxyList = new ArrayList<String[]>();
		String[] cyxys = null;
		if(null==cyxyArr||"".equals(cyxyArr[0])){
			return true;
		}
		for (String cyxy : cyxyArr) {
			
			cyxys = new String[2];
			cyxys[0] = xmdm;
			cyxys[1] = cyxy;
		
			cyxyList.add(cyxys);
		}
		dao.delCyxy(xmdm);
		return dao.cyxyPlbc(cyxyList);
		
	}
		
	}

