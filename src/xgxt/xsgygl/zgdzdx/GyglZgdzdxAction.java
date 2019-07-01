package xgxt.xsgygl.zgdzdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学公寓管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-6</p>
 */

public class GyglZgdzdxAction extends DispatchAction {
	/**
	 * 
	 *房源库自动生成功能页面 
	 * @throws SQLException 
	 */
	public ActionForward roomACtIndex(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");

		// ==================登陆用户检测 ==================
		if ("stu".equalsIgnoreCase(userType)) {
			String msg = "本模块不能由学生用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		
		GyglZgdzdxForm myForm       = (GyglZgdzdxForm)form;
		GyglZgdzdxModel  myModel    = new GyglZgdzdxModel();
		GyglZgdzdxService myService = new GyglZgdzdxService(); 
		String xiaoqu               = myForm.getXiaoqu();
		String lddm                 = myForm.getLddm();	
		String cs                   = myForm.getCs();
		String autoCTEexc           = request.getParameter("autCT");

		if(autoCTEexc!=null&&autoCTEexc.equalsIgnoreCase("DO")){//自动生成房源库

			BeanUtils.copyProperties(myModel, myForm);
			myService.serv_RoomAutoCreate(myModel);

		}

		List<String[]> resList = myService.serv_alreadyCTLCQrr(lddm,cs);//查询该楼栋楼层信息	   
		List<HashMap<String, String>> topList = myService.serv_getCTLCQrrTit();

		request.setAttribute("rs",resList);
		request.setAttribute("topTr", topList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		String[] my =  myService.serv_QshBpGzQrr();
		if(my!=null){
			myForm.setChsfbl(my[0]);
			myForm.setFjhws(my[1]);
			myForm.setQshbpgz(my[2]);
		}	   
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("fpbjList", gyglDao.getQsLxList(2));
		}else{
			request.setAttribute("fpbjList", gyglDao.getQsLxList(1));
		}
		request.setAttribute("xiaoQList",GyglShareDAO.getXaioQList());//校区列表
		request.setAttribute("ldList",GyglShareDAO.getLdList(xiaoqu));//楼栋列表
		request.setAttribute("lcList",GyglShareDAO.getLcList(lddm));//楼层列表
		return mapping.findForward("RoomACTIn");
	}

	public ActionForward roomCodeSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZgdzdxForm myForm     = (GyglZgdzdxForm)form;
		String chsfbl             = myForm.getChsfbl();
		String fjhws              = myForm.getFjhws();
		GyglZgdzdxService myService = new GyglZgdzdxService();
		myService.serv_roomCodeSave(chsfbl,fjhws);
		return new ActionForward("/zgdzdx_Gygl.do?method=roomACtIndex",false);
	}
	/**房源库房间信息批量
	 * @throws Exception */
	public ActionForward roomBatchDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZgdzdxService myService = new GyglZgdzdxService();
		String delPk = request.getParameter("delPk");
		boolean  flag  = myService.serv_roomBatchDel(delPk);
		request.setAttribute("flag",flag);
		return new ActionForward("/roomAutoCreate.do", false);
	}
	/**房源库房间信息修改
	 * @throws Exception */
	public ActionForward roomInfoMod(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		HashMap<String,String> map  = new HashMap<String,String>(); 
		String  pkValue             = request.getParameter("pkV");
		GyglZgdzdxService myService = new GyglZgdzdxService();
		map                         = myService.serv_roomInfoMod(pkValue);
		request.setAttribute("rs",map);
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("fpbjList", gyglDao.getQsLxList(2));
		}else{
			request.setAttribute("fpbjList", gyglDao.getQsLxList(1));
		}
		return mapping.findForward("RoomIModi");
	}
	public ActionForward roomInfoModSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZgdzdxService myService = new GyglZgdzdxService();
		GyglZgdzdxModel  myModel    = new GyglZgdzdxModel();
		GyglZgdzdxForm myForm       = (GyglZgdzdxForm)form;
		String related              = request.getParameter("related");
		BeanUtils.copyProperties(myModel, myForm);
		boolean flag               = false;
		if(related!=null&&related.equalsIgnoreCase("do")){//修改结果关联本楼栋其他层相类似房间
			flag = myService.serv_roomInfoModRelatedSave(myModel);
		}else{//非关联
			flag = myService.serv_roomInfoModSave(myModel);
		}
		request.setAttribute("flag",flag);
		return new ActionForward("/zgdzdx_Gygl.do?method=roomInfoMod",false);

	}
	public ActionForward roomCompartition(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		GyglZgdzdxForm myForm       = (GyglZgdzdxForm)form;
		GyglZgdzdxService myService = new GyglZgdzdxService();
		HttpSession session		   = request.getSession(false);
		String userName			   = session.getAttribute("userName").toString();
		String xiaoqu               = myForm.getXiaoqu();
		String xbxd                 = myForm.getXbxd();
		String lddm                 = myForm.getLddm();
		String cs                   = myForm.getCs();
		String nj                   = myForm.getNj();
		String xydm                 = myForm.getXydm();
		if(Base.isNull(nj)){
			myForm.setNj(Base.currNd);
		}
		String doType               = request.getParameter("doType");
		boolean doFlag              = false;
		String newMappingItems      = myForm.getConditionSqlValue();
		String oldMappingItems      = DealString.toGBK(myForm.getOldCondiSqlValue());
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){//分配结果保存
			String xsbj            = request.getParameter("xsbj");//保存结果提示标记
			doFlag                 = myService.serv_roomCompartSave(newMappingItems,oldMappingItems);
			if(doFlag){//如果保存成功
				oldMappingItems = newMappingItems;//将新值设置为旧值
			}
			if(xsbj==null||!xsbj.equalsIgnoreCase("no")){
				request.setAttribute("doFlag", doFlag);
			}
		}

		String[] whfrs = GyglShareDAO.getWhfrs(myForm.getNj(), myForm.getXydm(),userName);
		String[] yhfcws = GyglShareDAO.getYhfcws(myForm.getNj(), myForm.getXydm(),userName);
		request.setAttribute("allbody", whfrs[0]);
		request.setAttribute("allboy", whfrs[1]);
		request.setAttribute("allgirl", whfrs[2]);
		request.setAttribute("totalBed", yhfcws[0]);
		request.setAttribute("boyBed", yhfcws[1]);
		request.setAttribute("girlBed", yhfcws[2]);
		request.setAttribute("bgBed", yhfcws[3]);
		request.setAttribute("oldCondiSqlValue",oldMappingItems);
		request.setAttribute("ssxxList",GyglShareDAO.getSsHfSsXxList(lddm,cs,userName));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList",GyglShareDAO.getSssXyList(userName));
		request.setAttribute("xiaoQuList",GyglShareDAO.getXiaoQuList(userName));
		request.setAttribute("xbXdList",GyglShareDAO.getXbXdList(userName));
		request.setAttribute("ldList",GyglShareDAO.getSsHfLdList(xiaoqu,xbxd,userName));
		request.setAttribute("lcList", GyglShareDAO.getSsHfLcList(lddm,userName));
		request.setAttribute("ssYhfList", GyglShareDAO.getSsYHfList(nj,xydm,lddm,cs,userName));
		return mapping.findForward("RoomCompart");
	}
	/** 
	 * 检查博士生
	 * Method checkBss
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward checkBss(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception{		
		GyglZgdzdxService myService = new GyglZgdzdxService();
		HttpSession session = request.getSession(false);
		String xh = request.getParameter("userName");
		String verify = request.getParameter("verify");
		String strSysDatetime = request.getParameter("strSysDatetime");
		int viewnum = myService.validateInfo(xh, strSysDatetime, verify);
		session.setAttribute("userName", xh);
		session.setAttribute("userType", "stu");
		if(viewnum == 1){
			request.setAttribute("errinfo", "调用共享密钥失败！");
		}else if(viewnum == 2){
			request.setAttribute("errinfo", "调用共享时间失败！");
		}else if(viewnum == 3){
			request.setAttribute("errinfo", "共享身份验证超时！");
		}else if(viewnum == 4){
			request.setAttribute("errinfo", "身份验证失败！");
		}
		return getSsinfo(mapping, form, request, response);
	}
	/** 
	 * 博士生自选宿舍
	 * Method getSsinfo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward getSsinfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
	throws IllegalAccessException, InvocationTargetException{

		GyglZgdzdxForm myform = (GyglZgdzdxForm) form;
		GyglZgdzdxModel  myModel    = new GyglZgdzdxModel();
		GyglZgdzdxService myService = new GyglZgdzdxService();
		HttpSession session = request.getSession(false);
		List<String[]> infoList = new ArrayList<String[]>();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String go		= request.getParameter("go");
		boolean isBSS   = myService.sfbss(userName);
		boolean sfyrz   = myService.sfyrz(userName);
		String  xb	    = myService.getXb(userName);
		if(isBSS || userType.equals("xx") || userType.equals("admin")){
			if(go != null && go.equals("go")){
				BeanUtils.copyProperties(myModel, myform);
				infoList = myService.getSsxxList(myService.getCondition(myModel),xb);
				request.setAttribute("rs", infoList);
				request.setAttribute("rsNum", infoList==null?0:infoList.size());
				request.setAttribute("topTr", myService.getTopTr());
			}
			if(sfyrz){
				request.setAttribute("sfyrz", "yes");
			}else{
				request.setAttribute("sfyrz", "no");
			}
			request.setAttribute("view","ok");
			request.setAttribute("sfbzList",GyglShareDAO.GetSfbzList(myform.getLddm(),xb));
			request.setAttribute("xiaoQList",GyglShareDAO.getBsxqList());//校区列表
			request.setAttribute("ldList",GyglShareDAO.getBsldList(myform.getXiaoqu(),xb));//楼栋列表
			request.setAttribute("lcList",GyglShareDAO.getBscsList(myform.getLddm(),xb));//楼层列表
		}else{
			request.setAttribute("info","yes");
		}
		request.setAttribute("path", "zgdzdx_Gygl.do?method=getSsinfo");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bsszxss");
	}
	/** 
	 * 博士生自选宿舍提交
	 * Method saveDorm
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public synchronized ActionForward saveDorm(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZgdzdxService myService = new GyglZgdzdxService();
		HttpSession session = request.getSession(false);
		String userName = session.getAttribute("userName").toString();
		String ssbh = request.getParameter("pkV"); 
		boolean flag = myService.saveDorm(userName, ssbh);
		if(flag){
			request.setAttribute("result", "ok");
		}else{
			request.setAttribute("result", "no");
		}
		return getSsinfo(mapping, form, request, response);
	}

	/** 
	 * 查看登陆学生（博士）现住宿舍
	 * Method viewYzInfo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward viewYzInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		GyglZgdzdxService myService = new GyglZgdzdxService();
		HttpSession session = request.getSession(false);
		String userName = session.getAttribute("userName").toString();
		HashMap<String,String> map = myService.viewYzInfo(userName);
		if(map!=null){
			request.setAttribute("rs", map);
		}
		return mapping.findForward("oneinfo");
	}

	/** 
	 * 获得宿舍分布图象
	 * Method viewYzInfo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getDormImageWiew(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZgdzdxService myService = new GyglZgdzdxService();
		GyglZgdzdxForm myform = (GyglZgdzdxForm) form;
		String tips = "公寓管理 - 信息维护 - 房源库维护";
		HttpSession session = request.getSession(false);
		String userName = session.getAttribute("userName").toString();
		String lddm = request.getParameter("lddm");
		String lc = request.getParameter("lc");
		String xqdm = request.getParameter("xqdm");
		String qsh  = request.getParameter("qsh");
		String yqdm = request.getParameter("yqdm");
		String nj   = request.getParameter("imgNj");
		String xy   = request.getParameter("imgXy");
		List list = myService.getDormImageWiew(xqdm,yqdm,lddm,lc,qsh,nj,xy,userName);
		request.setAttribute("imageview", list);
		request.setAttribute("tips", tips);
		request.setAttribute("xiaoqquList", GyglShareDAO.getXiaoQuList(userName));
		List ldList = GyglShareDAO.getSsldList(myform.getXqdm(),yqdm,userName);
		List ssList = GyglShareDAO.GetQshList(myform.getLddm()); 
		List lcList = GyglShareDAO.getLcList(lddm);
		
//		公寓辅导员判断加载负责楼栋列表begin
		//List listTem = gyglDao.getLddmxXx(userName,xqdm,yqdm);
		if(gyglDao.isGyFdy(userName)){
			gyglDao.reLoadLb(request);
		}else{
			request.setAttribute("ldList", ldList);
			request.setAttribute("lcList",lcList);
			request.setAttribute("ssList", ssList);
		}
//		公寓辅导员判断end
						
		List yqList = GyglShareDAO.getYqList(xqdm);
		request.setAttribute("yqList", yqList);
		request.setAttribute("tableName", "view_ssxx");
		request.setAttribute("realTable", "ssxxb");
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("pk", "ssbh");
		request.setAttribute("dxq", "yes");
		request.setAttribute("act", "dormInfo");
		request.setAttribute("njList",Base.getNjList());
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("writeAble", "yes");

		return mapping.findForward("imageview");
	}
	/** 
	 * 获得某个宿舍的信息
	 * Method getOneSsInfo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward getOneSsInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZgdzdxService myService = new GyglZgdzdxService();
		String ssbh = DealString.toGBK(request.getParameter("ssbh"));
		String noAdmin = request.getParameter("do");
		if(noAdmin != null && !noAdmin.equals("")){
			request.setAttribute("noAdmin", "yes");
		}
		HashMap map = myService.getOneSsInfo(ssbh);
		if(map!=null){
			request.setAttribute("rs", map);
		}
		String[] array = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称"};
		request.setAttribute("formtop", array);
		request.setAttribute("ssbh", ssbh);
		request.setAttribute("xxdm", Base.xxdm);
		List<HashMap<String, String>> qsLxList = null;
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)){
			qsLxList = gyglDao.getQsLxList(2);
		}else{
			qsLxList = gyglDao.getQsLxList(1);
		}
		request.setAttribute("qsLxList", qsLxList);
		return mapping.findForward("onessinfo");
	}
	/** 
	 * 保存某个宿舍的信息
	 * Method saveInfo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZgdzdxService myService = new GyglZgdzdxService();
		GyglZgdzdxForm myform = (GyglZgdzdxForm) form;
		GyglZgdzdxModel  myModel    = new GyglZgdzdxModel();
		myform.setBz(DealString.toGBK(myform.getBz()));
		myform.setSffqfj(DealString.toGBK(myform.getSffqfj()));
		BeanUtils.copyProperties(myModel, myform);
		boolean flag = myService.saveInfo(myModel);
		if(flag){
			request.setAttribute("flag", "ok");
		}else{
			request.setAttribute("flag", "on");
		}
		return this.getOneSsInfo(mapping, form, request, response);
	}
	/** 
	 * 学生查看宿舍信息(只针对中国地质大学)
	 * Method saveInfo
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward viewSsInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglZgdzdxService myService = new GyglZgdzdxService();
		HttpSession session = request.getSession(false);
		String tableName = "";
		HashMap map = new HashMap();
		String xh = request.getParameter("userName");
		String verify = request.getParameter("verify");
		String strSysDatetime = request.getParameter("strSysDatetime");
		String xslb = request.getParameter("xslb");
		if(xslb != null && xslb.equals("sss")){
			tableName = "sss_xxb";
		}else{
			tableName = "view_xsxxb";
		}
		int viewnum = myService.validateInfo(xh, strSysDatetime, verify);
		if(viewnum == 1){
			request.setAttribute("info", "调用共享密钥失败！");
		}else if(viewnum == 2){
			request.setAttribute("info", "调用共享时间失败！");
		}else if(viewnum == 3){
			request.setAttribute("info", "共享身份验证超时！");
		}else if(viewnum == 4){
			request.setAttribute("info", "身份验证失败！");
		}else{
			if(xslb != null && xslb.equals("bss")){
				session.setAttribute("userName", xh);
				session.setAttribute("userType", "stu");
				return new ActionForward("/zgdzdx_Gygl.do?method=getSsinfo",false);
			}
			map = myService.viewSsInfo(xh, tableName);
			String[] array = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称"};
			request.setAttribute("formtop", array);
			request.setAttribute("rs", map);
		}	
		return mapping.findForward("viewssinfo");
	}

	public ActionForward bedCompartition(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		DAO dao = DAO.getInstance();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType   = DealString.toGBK(request.getParameter("doType"));
		GyglZgdzdxService myService = new GyglZgdzdxService();
		GyglZgdzdxForm myForm       = (GyglZgdzdxForm)form;
		String nj                   = myForm.getNj();
		String xydm                 = myForm.getXydm();
		String zydm                 = myForm.getZydm();
		String xb                   = myForm.getXbxd();
		String xqdm                 = myForm.getXiaoqu();
		String lddm                 = myForm.getLddm();
		String cs                   = myForm.getCs();
		String bjdm                 = myForm.getBjdm();
		String xh                  = myForm.getKsh();//学号
		String userDep = request.getSession().getAttribute("userDep").toString();		
		String userType = dao.getUserType(userDep);
		if(!GyglShareDAO.isSssAdmin(userName)){
			if ("xy".equalsIgnoreCase(userType)) {
				xydm = userDep;
				myForm.setXydm(xydm);
			}
		}else{//研究生宿管科用户不做限制
			userType = "xx";
		}
		String[] xsInfo = myService.getStuInF(userName, xh);		
		if(xsInfo!=null){
			nj   = xsInfo[0];
			xydm = xsInfo[1];
			bjdm = xsInfo[2];
			xb   = xsInfo[3];
			lddm = xsInfo[4];
			cs   = xsInfo[5];
			xqdm = xsInfo[6];
			myForm.setNj(nj);
			myForm.setXydm(xydm);
			myForm.setBjdm(bjdm);
			myForm.setXbxd(xb);
			myForm.setLddm(lddm);
			myForm.setCs(cs);
			myForm.setXqdm(xqdm);
		}
		boolean doFlag             = false;
		String newMappingItems      = "";
		String oldMappingItems      = "";
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){//分配结果保存
			//String xsbj            = request.getParameter("xsbj");//保存结果提示标记
			newMappingItems        = myForm.getConditionSqlValue();
			oldMappingItems        = myForm.getOldCondiSqlValue();
			doFlag                 = myService.serv_bedCompartSave(newMappingItems,oldMappingItems);
			if(doFlag){//如果保存成功
				oldMappingItems = newMappingItems;//将新值设置为旧值
			}
			//if(xsbj==null||!xsbj.equalsIgnoreCase("no")){
			request.setAttribute("doFlag", doFlag);
			//}
		}
		if(Base.isNull(nj)){
			nj = Base.currNd;
			myForm.setNj(nj);
		}

		String[] whfrs = GyglShareDAO.getSssCwYfpZsData(xydm,nj,bjdm,userName);
		String[] yhfcws = GyglShareDAO.getSssCwWfpZsData(xydm,nj,bjdm,userName);
		request.setAttribute("total", whfrs[0]);
		request.setAttribute("boy", whfrs[1]);
		request.setAttribute("girl", whfrs[2]);
		request.setAttribute("nototal", yhfcws[0]);
		request.setAttribute("noboy", yhfcws[1]);
		request.setAttribute("nogirl", yhfcws[2]);

		
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", GyglShareDAO.getSssXyList(userName));//学院
		request.setAttribute("bjList",GyglShareDAO.getSssBjList(nj,xydm,zydm,userName));//班级
		request.setAttribute("xiaoqquList", GyglShareDAO.getSssXqList(nj, xydm, userName));//校区
		request.setAttribute("ldList", GyglShareDAO.getSssLdList(xqdm, xydm, xb, nj, userName));//楼栋
		request.setAttribute("csList", GyglShareDAO.getSssCsList(lddm, nj, xydm, userName));//楼层
		request.setAttribute("ssxxList", GyglShareDAO.getSssCwList(xqdm, xydm, lddm, xb, cs, nj, userName));
		request.setAttribute("xsList",GyglShareDAO.getSssXsList(xydm, nj, bjdm, xb, userName));
		request.setAttribute("yfpCwList",GyglShareDAO.getSssFpCwList(xydm, nj,bjdm,xb, userName));
		request.setAttribute("userName",userName);
//		if(!Base.isNull(xh)){//单个学号查询
//			List<HashMap<String,String>> list = new  ArrayList<HashMap<String,String>>();
//			list = GyglShareDAO.getXhSssFpCwList(xh, userName);
//			request.setAttribute("yfpCwList",list);
//			request.setAttribute("xsList", "");
			//oldMappingItems = list.get(0).get("dm").toString();//输入查询结果放入旧值变量中						
//		}
		request.setAttribute("oldCondiSqlValue",oldMappingItems);
		request.setAttribute("userType",userType);
		return mapping.findForward("BedCompart");
	}
	/**园区负责人默认查询页面*/
	public ActionForward yqManager(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writeAble = "no";
		String tableName = "";
		String realTable = "";
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglZgdzdxService service = new GyglZgdzdxService();
			GyglZgdzdxForm    myForm = (GyglZgdzdxForm)form;

			String yqdm = myForm.getYqdm();
			String xm   = myForm.getXm();
			String sfzz = myForm.getSfzz();
			ArrayList<HashMap<String, String>> topTr = service.getyqManagerTitle();
			ArrayList<String[]> rs    = service.getyqManagerResult(yqdm,xm,sfzz);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}		
		writeAble = (getRight("yqManager.do",request)==1)?"yes":"no";
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		return mapping.findForward("yqManager");
	}
	/**园区负责人增加*/
	public ActionForward yqManagerAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm = (GyglZgdzdxForm)form;
		GyglYqManagerModel model  = new GyglYqManagerModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_yqManagerAdd(model);
			request.setAttribute("done",done);
		}
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		return mapping.findForward("yqMAdd");
	}
	/**园区负责人信息修改*/
	public ActionForward yqManagerModi (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		GyglYqManagerModel model  = new GyglYqManagerModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));
		HashMap<String,String>map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_yqManagerModi(model,pkValue);
			request.setAttribute("done",done);
//			if(done){
				pkValue = model.getYqdm().trim()+model.getXm().trim()+model.getRzrq().trim();
//			}
			map  = service.yqManagerInfo(pkValue);
			map.put("lxdh",model.getLxdh());
			map.put("dzyx",model.getDzyx());
			map.put("sfzz",model.getSfzz());
			map.put("lzrq", model.getLzrq());
			map.put("bz",model.getBz());
		}else{
			map  = service.yqManagerInfo(pkValue);
		}				
		request.setAttribute("rs",map);
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("isview", isview);
		return mapping.findForward("yqMModi");
	}
	/****园区负责人信息删除	 */
	public ActionForward yqManagerDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
		GyglZgdzdxService service = new GyglZgdzdxService();
		boolean done = service.serv_yqManagerDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zgdzdx_Gygl.do?method=yqManager",false);
	}
	/**楼长默认查询页面 */
	public ActionForward ldManager(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		String writeAble = "no";
		String tableName = "";
		String realTable = "";
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		String yqdm  = myForm.getYqdm();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglZgdzdxService service = new GyglZgdzdxService();			
			GyglLdManagerModel model  = new GyglLdManagerModel();
			BeanUtils.copyProperties(model,myForm);
			
			ArrayList<HashMap<String, String>> topTr = service.getldManagerTitle();
			ArrayList<String[]> rs    = service.getldManagerResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}			
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
		writeAble = (getRight("ldManager.do",request)==1)?"yes":"no";
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("ldM");
	}
	/**楼长增加*/
	public ActionForward ldManagerAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm     myForm = (GyglZgdzdxForm)form;
		String yqdm  = myForm.getYqdm();
		GyglLdManagerModel model  = new GyglLdManagerModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_ldManagerAdd(model);
			request.setAttribute("done",done);
		}
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
	    request.setAttribute("rsfzr",GyglShareDAO.GetYqMInfo(yqdm));	
		return mapping.findForward("ldMAdd");
	}
	/**楼长信息修改*/
	public ActionForward ldManagerModi (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		GyglLdManagerModel model  = new GyglLdManagerModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));

		String yqdm = myForm.getYqdm();
		HashMap<String,String>map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_ldManagerModi(model,pkValue);
			request.setAttribute("done",done);
//			if(done){
			     pkValue = model.getLddm().trim()+model.getLz().trim()+model.getRzrq().trim();
//			}
			map  = service.ldManagerInfo(pkValue);
			map.put("lxdh",model.getLxdh());
			map.put("dzyx",model.getDzyx());
			map.put("sfzz",model.getSfzz());
			map.put("lzrq", model.getLzrq());
			map.put("bz",model.getBz());
			map.put("lddm",model.getLddm());
		}else{
			map  = service.ldManagerInfo(pkValue);
		}	
		
		if(Base.isNull(yqdm)){
			yqdm=map.get("yqdm");			
		}else{
			map.put("yqdm",yqdm);
		}
		request.setAttribute("rs",map);
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
	    request.setAttribute("rsfzr",GyglShareDAO.GetYqMInfo(yqdm));	
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("isview", isview);

		return mapping.findForward("ldMModi");
	}
	/****楼长信息删除	 */
	public ActionForward ldManagerDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
		GyglZgdzdxService service = new GyglZgdzdxService();
		boolean done = service.serv_ldManagerDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zgdzdx_Gygl.do?method=ldManager",false);
	}
	/**层长默认查询页面 */
	public ActionForward lcManager(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		String writeAble = "no";
		String tableName = "";
		String realTable = "";
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		String yqdm  = myForm.getYqdm();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglZgdzdxService service = new GyglZgdzdxService();			
			GyglLcManagerModel model  = new GyglLcManagerModel();
			BeanUtils.copyProperties(model,myForm);
			
			ArrayList<HashMap<String, String>> topTr = service.getlcManagerTitle();
			ArrayList<String[]> rs    = service.getlcManagerResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}			
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
		writeAble = (getRight("lcManager.do",request)==1)?"yes":"no";
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("lcM");
	}
	/** 层长增加*/
	public ActionForward lcManagerAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm     myForm = (GyglZgdzdxForm)form;
		String yqdm  = myForm.getYqdm();
		String lddm  = myForm.getLddm();
		GyglLcManagerModel model  = new GyglLcManagerModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_lcManagerAdd(model);
			request.setAttribute("done",done);
		}
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm));
	    request.setAttribute("rsfzr",GyglShareDAO.GetYqMInfo(yqdm));
	    request.setAttribute("rslz",GyglShareDAO.GetLzMInfo(lddm));	
		
	    return mapping.findForward("lcMAdd");
	}
	/**层长信息修改*/
	public ActionForward lcManagerModi (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		GyglLcManagerModel model  = new GyglLcManagerModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));

		String yqdm = myForm.getYqdm();
		String lddm = myForm.getLddm();
		HashMap<String,String>map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_lcManagerModi(model,pkValue);
			request.setAttribute("done",done);
//			if(done){
			     pkValue = model.getLddm().trim()+model.getCs().trim()+model.getCz().trim()+model.getRzrq().trim();
//			}
			map  = service.ldManagerInfo(pkValue);
			map.put("lxdh",model.getLxdh());
			map.put("dzyx",model.getDzyx());
			map.put("sfzz",model.getSfzz());
			map.put("lzrq", model.getLzrq());
			map.put("bz",model.getBz());
			map.put("lddm",model.getLddm());
			map.put("cs",model.getCs());
			map.put("cz",model.getCz());
		}else{
			map  = service.lcManagerInfo(pkValue);
		}	
		
		if(Base.isNull(yqdm)){
			yqdm=map.get("yqdm");			
		}else{
			map.put("yqdm",yqdm);
		}
		if(Base.isNull(lddm)){
			lddm=map.get("lddm");	
		}else{
			map.put("lddm",lddm);
		}
		request.setAttribute("rs",map);
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm));
	    request.setAttribute("rsfzr",GyglShareDAO.GetYqMInfo(yqdm));
	    request.setAttribute("rslz",GyglShareDAO.GetLzMInfo(lddm));
	    request.setAttribute("pkValue", pkValue);
	    request.setAttribute("isview", isview);

	    return mapping.findForward("lcMModi");
	}
	/****层长信息删除	 */
	public ActionForward lcManagerDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
		GyglZgdzdxService service = new GyglZgdzdxService();
		boolean done = service.serv_lcManagerDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zgdzdx_Gygl.do?method=lcManager",false);
	}
	/**园区值班情况默认查询页面 */
	public ActionForward yqzbManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		String writeAble = "no";
		String tableName = "";
		String realTable = "";
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
//		String yqdm  = myForm.getYqdm();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglZgdzdxService service = new GyglZgdzdxService();			
			GyglYqzbModel model  = new GyglYqzbModel();
			BeanUtils.copyProperties(model,myForm);
			
			ArrayList<HashMap<String, String>> topTr = service.getyqzbManageTitle();
			ArrayList<String[]> rs    = service.getyqzbManageResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}			
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		//request.setAttribute("ldList", GyglShareDAO.getYqLdList(yqdm));
		writeAble = (getRight("yqzbManage.do",request)==1)?"yes":"no";
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("yqzbM");
	}
	/**园区值班记录增加*/
	public ActionForward yqzbManageAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm     myForm = (GyglZgdzdxForm)form;
//		String yqdm  = myForm.getYqdm();
		GyglYqzbModel model  = new GyglYqzbModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_yqzbManagerAdd(model);
			request.setAttribute("done",done);
		}
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		return mapping.findForward("yqzbMAdd");
	}
	/**园区值班信息修改*/
	public ActionForward yqzbManageModi (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		GyglYqzbModel model  = new GyglYqzbModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));

		HashMap<String,String>map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_yqzbManageModi(model,pkValue);
			request.setAttribute("done",done);				
			map.put("yqdm",model.getYqdm());
			map.put("xm",model.getXm());
			map.put("rq",model.getRq());
			map.put("bz",model.getBz());
		}else{
		    map = service.yqzbManageInfo(pkValue);
		}
		request.setAttribute("rs",map);
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
	    request.setAttribute("pkValue", pkValue);
	    request.setAttribute("isview", isview);

		return mapping.findForward("yqzbMModi");
	}
	/****园区值班信息删除	 */
	public ActionForward yqzbManageDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
		GyglZgdzdxService service = new GyglZgdzdxService();
		boolean done = service.serv_yqzbManageDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zgdzdx_Gygl.do?method=yqzbManage",false);
	}
	/**园区活动情况默认查询页面 */
	public ActionForward yqhdManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		String writeAble = "no";
		String tableName = "";
		String realTable = "";
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;		
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglZgdzdxService service = new GyglZgdzdxService();			
			GyglYqhdModel model  = new GyglYqhdModel();
			BeanUtils.copyProperties(model,myForm);
			
			ArrayList<HashMap<String, String>> topTr = service.getyqhdManageTitle();
			ArrayList<String[]> rs    = service.getyqhdManageResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());	
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		//读写权限
		writeAble = (getRight("yqhdManage.do",request)==1)?"yes":"no";
		
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("yqhdM");
	}
	/**园区活动情况增加*/
	public ActionForward yqhdManageAdd (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm     myForm = (GyglZgdzdxForm)form;
		String xn    = myForm.getXn();
		String xq  = myForm.getXq();
		GyglYqhdModel model  = new GyglYqhdModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_yqhdManagerAdd(model);
			request.setAttribute("done",done);
		}
		if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(xq)){
			myForm.setXq(Base.currXq);
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());	
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
		return mapping.findForward("yqhdMAdd");
	}
	/**园区活动情况修改*/
	public ActionForward yqhdManageModi (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		GyglYqhdModel model  = new GyglYqhdModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));

		HashMap<String,String>map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_yqhdManageModi(model,pkValue);
			request.setAttribute("done",done);				
			map.put("yqdm",model.getYqdm());
			map.put("sj",model.getSj());
			map.put("zzdw",model.getZzdw());
			map.put("hdmc",model.getHdmc());
			map.put("xm",model.getXm());
			map.put("rq",model.getRq());
			map.put("bz",model.getHdnr());
			map.put("xn",model.getXn());
			map.put("xq",model.getXq());
		}else{
		    map = service.yqhdManageInfo(pkValue);
		}
		request.setAttribute("rs",map);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yqList", GyglShareDAO.getYqList(""));
	    request.setAttribute("pkValue", pkValue);
	    request.setAttribute("isview", isview);
		return mapping.findForward("yqhdMModi");
	}
	/**园区活动情况删除	 */
	public ActionForward yqhdManageDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		GyglZgdzdxService service = new GyglZgdzdxService();
		boolean done = service.serv_yqhdManageDel(pkValue);
		request.setAttribute("done",done);
		return new ActionForward("/zgdzdx_Gygl.do?method=yqhdManage",false);
	}
	/** 外校生住宿信息默认查询页面  
	 *
	 **/
	public ActionForward wxsDormUserManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
	    String writeAble = "false";
		//读写权限
		writeAble = (getRight("yqhdManage.do",request)==1)?"yes":"no";
		GyglZgdzdxForm myForm = (GyglZgdzdxForm) form;
		GyglZgdzdxService service = new GyglZgdzdxService();
		String lddm = myForm.getLddm();
		String userName = request.getSession().getAttribute("userName").toString();
		String xiaoqu   = myForm.getXiaoqu();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglWxsDormUModel model = new GyglWxsDormUModel();
			BeanUtils.copyProperties(model,myForm);
			ArrayList<HashMap<String, String>> topTr = service.getWxsDUManageTitle();
			ArrayList<String[]> rs    = service.getWxsDUManageResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		request.setAttribute("xiaoqList",GyglShareDAO.getXaioQList());
		request.setAttribute("ldList",GyglShareDAO.getSsldList(xiaoqu,"",userName));
		request.setAttribute("qshList",GyglShareDAO.GetQshList(lddm, userName));
		
		// ==============2010.10.27 edit by luojw ================
		CommService commService = new CommService();
		CommForm model = new CommForm();
		RequestForm rForm = new RequestForm();
		
		BeanUtils.copyProperties(model, myForm);
		
		String gnmk = "gygl";
		String menu = "comm";
		
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		try {
			commService.setList(model, rForm, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		// ==================edit over==========================
		request.setAttribute("path", "wxs_dormUser_Manage.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("writeAble",writeAble);
		request.setAttribute("userName",userName);
		request.setAttribute("xxdm",Base.xxdm);
		return mapping.findForward("wxsDUM");
	}
	/** 外校生住宿信息添加 
	 * @throws Exception */
	public ActionForward wxsDormUserAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglZgdzdxForm myForm = (GyglZgdzdxForm) form;
		String ssbh  = myForm.getSsbh();
		String xb  = myForm.getXb();
		String doType = request.getParameter("doType");
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){
			GyglWxsDormUModel model = new GyglWxsDormUModel();
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_wxsDormUserAdd(model);
			request.setAttribute("done",done);
		}
        request.setAttribute("njList",Base.getXnndList());
	    request.setAttribute("ssList", service.serv_getWxsSSList(xb));
	    request.setAttribute("cwhList",service.serv_getWxsCwList(ssbh));
	    request.setAttribute("xxdm",Base.xxdm);
		return mapping.findForward("wxsDUAdd");
	}
	/**外校生住宿信息修改*/
	public ActionForward wxsDormUserModi (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		GyglWxsDormUModel model   = new GyglWxsDormUModel();
		GyglZgdzdxService service = new GyglZgdzdxService();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));
        String ssbh = myForm.getSsbh();
        String xb   = myForm.getXb();
        
		HashMap<String,String>map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_wxsDormUserModi(model,pkValue);
			request.setAttribute("done",done);				
			map.put("xh",pkValue);
			map.put("xm",model.getXm());
			map.put("xb",model.getXb());
			map.put("xymc",model.getXymc());
			map.put("zymc",model.getZymc());
			map.put("bjmc",model.getBjmc());
			map.put("nj",model.getNj());
			map.put("lddm",model.getLddm());
			map.put("cwh",model.getCwh());
			map.put("rzrq",model.getRzrq());
			map.put("bz",model.getBz());
		}else{
		    map = service.wxsDormUserInfo(pkValue);
		}
		if(Base.isNull(ssbh)){
			ssbh = map.get("ssbh");		
		}else{
			map.put("ssbh",ssbh);
		}
		if(Base.isNull(xb)){
			xb=map.get("xb");	
		}else{
			map.put("xb",xb);
		}
		
		request.setAttribute("rs",map);
        request.setAttribute("njList",Base.getXnndList());
	    request.setAttribute("ssList", service.serv_getWxsSSList(xb));
	    request.setAttribute("cwhList",service.serv_getWxsCwList(ssbh));
	    request.setAttribute("pkValue", pkValue);
	    request.setAttribute("isview", isview);
		return mapping.findForward("wxsDUModi");
	}
	public ActionForward wxsDormUserDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{
		GyglZgdzdxService service = new GyglZgdzdxService();
		String toHistory = request.getParameter("toHistory");
		String delPk = request.getParameter("delPk");
		String tssj  = request.getParameter("tssjv");
		service.serv_wxsDormUserDel(toHistory, delPk,tssj);
		return new ActionForward("/zgdzdx_Gygl.do?method=wxsDormUserManage&go=go",false);
	}
	
	public ActionForward dyDormManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		GyglZgdzdxForm    myForm  = (GyglZgdzdxForm)form;
		String userName           = request.getSession().getAttribute("userName").toString();
		String xqdm  = DealString.toGBK(myForm.getXqdm());
		String yqdm  = DealString.toGBK(myForm.getYqdm());
		String lddm  = DealString.toGBK(myForm.getLddm());

		GyglZgdzdxService service = new GyglZgdzdxService();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			ArrayList<HashMap<String, String>> topTr = service.getdyDManageTitle();
			ArrayList<String[]> rs    = service.getdyDManageResult(xqdm,yqdm,lddm);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}

		request.setAttribute("xiaoqquList", GyglShareDAO.getXiaoQuList(userName));
		request.setAttribute("yqList", GyglShareDAO.getYqList(xqdm));
		request.setAttribute("ldList", GyglShareDAO.getSsldList(xqdm,yqdm,userName));
		request.setAttribute("tableName","view_dyssxx");
		request.setAttribute("realTable","");
		return mapping.findForward("dyDManage");
	}
	
	public ActionForward jswmhdSq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userType              = request.getSession().getAttribute("userType").toString();
    	String userNameReal          = request.getSession().getAttribute("userNameReal").toString(); 
    	String userName              = request.getSession().getAttribute("userName").toString();
        String doType                = request.getParameter("doType");
        GyglZgdzdxForm        myForm = (GyglZgdzdxForm)form;
        GyglJswmhdModel       model  = new GyglJswmhdModel(); 
        GyglZgdzdxService service = new GyglZgdzdxService();
        if("save".equalsIgnoreCase(doType)){        	
        	BeanUtils.copyProperties(model,myForm); 
        	boolean done = service.serv_jswmhdSqSave(model, userName);
        	request.setAttribute("done",done);
        }
        
    	request.setAttribute("userType",userType);
		request.setAttribute("userNameReal", userNameReal);
		return mapping.findForward("hdsq");
	}
	
	public ActionForward jswmhdManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		GyglZgdzdxForm        myForm = (GyglZgdzdxForm)form;
		String   nd   = myForm.getNd();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		GyglJswmhdModel       model  = new GyglJswmhdModel(); 		
        if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
        }
		if(nd==null){
			myForm.setNd(Base.currNd);
		}
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("go".equalsIgnoreCase(request.getParameter("go"))){						
			BeanUtils.copyProperties(model,myForm); 
			ArrayList<HashMap<String, String>> topTr = service.getjswmhdMTitle();
			ArrayList<String[]> rs    = service.ser_jswmhdMResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		request.setAttribute("tableName", "jswmhdsqb");		
		request.setAttribute("realTable", "jswmhdsqb");		
		request.setAttribute("writeAble" ,(getRight("jswmhdManage.do",request)==1)?"yes":"no");//读写权限
		request.setAttribute("ndList",Base.getXnndList());
		return mapping.findForward("jsQmHdM");
	}
	public ActionForward hdShManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		DAO    dao = DAO.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
        if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
        }
		GyglZgdzdxForm        myForm = (GyglZgdzdxForm)form;
        GyglJswmhdModel       model  = new GyglJswmhdModel();         
        GyglZgdzdxService service = new GyglZgdzdxService();

		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model,myForm);
			ArrayList<HashMap<String, String>> topTr = service.ser_hdShMTitle(userType);
			ArrayList<String[]> rs    = service.ser_hdShMResult(model, userType);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		request.setAttribute("chkList",dao.getChkList(3));
		request.setAttribute("writeAble" ,(getRight("jswmhdShManage.do",request)==1)?"yes":"no");//读写权限
		return mapping.findForward("hdShM");
	}
	public ActionForward jswmhdhShView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String pkValue = request.getParameter("rid");
		HashMap<String,String> map  = new HashMap<String,String>();
		GyglZgdzdxService service = new GyglZgdzdxService();
		map = service.ser_jswmhdShInfo(pkValue);
		request.setAttribute("rs",map);
		return mapping.findForward("hdShView");
	}
	public ActionForward jswmhdhSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		DAO    dao = DAO.getInstance();
		String pkValue = request.getParameter("rid");
		String shValue     = request.getParameter("shType");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		GyglZgdzdxService service = new GyglZgdzdxService();
		if(service.ser_jswmhdShJudge(userType, pkValue)){			
			request.setAttribute("notDone", "notDone");
		}else{
			service.ser_jswmhdSh(pkValue,userType, shValue);
		}
		return new ActionForward("/zgdzdx_Gygl.do?method=hdShManage",false);
	}
	public ActionForward jswmhdAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   	String userNameReal          = request.getSession().getAttribute("userNameReal").toString(); 
	   	String userName              = request.getSession().getAttribute("userName").toString();
		String doType                = request.getParameter("doType");
		GyglZgdzdxForm        myForm = (GyglZgdzdxForm)form;
		GyglJswmhdModel       model  = new GyglJswmhdModel(); 
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){        	
			BeanUtils.copyProperties(model,myForm); 
			boolean done = service.ser_jswmhdSave(model, userName);
			request.setAttribute("done",done);
		}
		request.setAttribute("userNameReal", userNameReal);
		return mapping.findForward("jsWmHdAdd");
	}
	public ActionForward jswmhdModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
//	   	String userName              = request.getSession().getAttribute("userName").toString();
		String doType                = request.getParameter("doType");
		String pkValue               = request.getParameter("pkValue");
		GyglZgdzdxForm        myForm = (GyglZgdzdxForm)form;
		GyglJswmhdModel       model  = new GyglJswmhdModel(); 
		GyglZgdzdxService service = new GyglZgdzdxService();
		if("save".equalsIgnoreCase(doType)){        	
			BeanUtils.copyProperties(model,myForm); 
			boolean done = service.ser_jswmhdModi(model, pkValue);
			request.setAttribute("done",done);
		}
		HashMap<String,String> map = service.ser_getJsWmHdInfo(pkValue);		
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		return mapping.findForward("jsWmHdModi");
	}
    public ActionForward jswmhdDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String pkValue       = request.getParameter("pkValue");
    	GyglZgdzdxService service = new GyglZgdzdxService();
    	service.ser_jswmhdDel(pkValue);
    	return new ActionForward("/zgdzdx_Gygl.do?method=jswmhdManage",false);
    	
    }
	public ActionForward jswmhdView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String pkValue = request.getParameter("pkValue");
		HashMap<String,String> map  = new HashMap<String,String>();
		GyglZgdzdxService service = new GyglZgdzdxService();
		map = service.ser_getJsWmHdInfo(pkValue);
		request.setAttribute("rs",map);
		return mapping.findForward("jswmhdView");
	}
	/**获取读写权*/
	public int getRight(String modID,HttpServletRequest request){
		HttpSession session = request.getSession();
		String userType     = session.getAttribute("userOnLine").toString();
		boolean 	isStu   = (userType.equalsIgnoreCase("student"));
		String sUName       = session.getAttribute("userName").toString();
		return Base.chkUPower(sUName, modID, isStu);
	}
	public ActionForward dormFeeChg_stati(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		GyglZgdzdxService service = new GyglZgdzdxService();
		String hdksrq = request.getParameter("hdksrq");
		String hdjsrq = request.getParameter("hdjsrq");
		GyglZgdzdxForm        myForm = (GyglZgdzdxForm)form;
		GyglZsfBgTjModel       model  = new GyglZsfBgTjModel(); 
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
//		String userType   = request.getSession().getAttribute("userType").toString();
        if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
        }

		if(Base.isNull(hdksrq)){
			hdksrq = Base.currNd+"0101";
			myForm.setHdksrq(hdksrq);
		}
		if(Base.isNull(hdjsrq)){
			hdjsrq = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
			myForm.setHdjsrq(hdjsrq);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model,myForm); 
			ArrayList<HashMap<String, String>> topTr = service.ser_FeeChgTitle();
			ArrayList<String[]> rs    = service.serv_FeeChgResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		gyglDao.getXyZyBjxx(request);
//		gyglDao.getXnxqList(request, DAO.getInstance());
		request.setAttribute("tableName","view_zsfbgtjxx");
		request.setAttribute("realTable", "view_zsfbgtjxx");
		return mapping.findForward("dormFChg_stati");
	}
}
