package xgxt.qgzx.zgdzdx;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForward;

import com.zfsoft.basic.BasicAction;

import common.Globals;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.service.XsgwglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.ToolClass;
import xgxt.utils.String.StringUtils;

public class QgzxHgsqAction extends BasicAction {

	String writeAble = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String myAction = mapping.getParameter();
		ActionForward myActFwd = null;
		try {
			// 判断用户读写权
			writeAble = Base.getWriteAble(request);
			String dxq = request.getParameter("writeAble");
			if (!"".equalsIgnoreCase(dxq) && dxq != null) {
				writeAble = dxq;
			}

			if ("qgzxHgsq".equalsIgnoreCase(myAction)) {//换岗申请
				myActFwd = qgzxHgsq(mapping, form, request, response);
			}
			if("zgdzdx_hg_Save".equalsIgnoreCase(myAction)){//换岗申请保存
				myActFwd = zgdzdx_hg_Save(mapping, form, request, response);
			}
			if("qgzxHgsqsh".equalsIgnoreCase(myAction)){//换岗申请审核查询
				myActFwd = qgzxHgsqsh(mapping, form, request, response);
			}
			if("qgzxHgsqModi".equalsIgnoreCase(myAction)){//换岗申请修改跳转
				myActFwd = qgzxhgsqModi(mapping, form, request, response);
			}
			if("zgdzdx_hg_SaveOne".equalsIgnoreCase(myAction)){//换岗申请修改保存
				myActFwd = zgdzdx_hg_SaveOne(mapping, form, request, response);
			}
			if("hgsqsh".equalsIgnoreCase(myAction)){//换岗申请审核操作
				myActFwd = hgsqsh(mapping, form, request, response);
			}
			if("qgzxCgsq".equalsIgnoreCase(myAction)){//辞岗申请
				myActFwd = qgzxCgsq(mapping, form, request, response);
			}
			if("zgdzdx_cg_Save".equalsIgnoreCase(myAction)){//辞岗申请保存
				myActFwd = zgdzdx_cg_Save(mapping, form, request, response);
			}
			if("qgzxCgsqsh".equalsIgnoreCase(myAction)){//辞岗申请审核查询
				myActFwd = qgzxCgsqsh(mapping, form, request, response);
			}
			if("cgsqsh".equalsIgnoreCase(myAction)){//辞岗申请审核操作
				myActFwd = cgsqsh(mapping, form, request, response);
			}
			if("qgzxcgsqModi".equalsIgnoreCase(myAction)){//辞岗申请修改
				myActFwd = qgzxcgsqModi(mapping, form, request, response);
			}
			if("zgdzdx_cg_SaveOne".equalsIgnoreCase(myAction)){//辞岗申请修改保存
				myActFwd = zgdzdx_cg_SaveOne(mapping, form, request, response);
			}
			if("qgzxYrdwGhXsGwsq".equalsIgnoreCase(myAction)){//用人单位更换学生岗位申请
				myActFwd =  qgzxYrdwGhXsGwsq(mapping, form, request, response);
			}
			if("zgdzdx_yrdwghxssq_Save".equalsIgnoreCase(myAction)){//用人单位更换学生岗位申请保存
				myActFwd =  zgdzdx_yrdwghxssq_Save(mapping, form, request, response);
			}
			if("qgzxYrdwGhXsGwsh".equalsIgnoreCase(myAction)){//用人单位更换学生岗位审核
				myActFwd =  qgzxYrdwGhXsGwsh(mapping, form, request, response);
			}
			if("qgzxYrdwGhxsModi".equalsIgnoreCase(myAction)){//用人单位更换学生岗位单个学生详细信息
				myActFwd =  qgzxYrdwGhxsModi(mapping, form, request, response);
			}
			if("qgzxYrdwGhxssh".equalsIgnoreCase(myAction)){//用人单位更换学生岗位审核操作
				myActFwd =  qgzxYrdwGhxssh(mapping, form, request, response);
			}
			
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionForward("/login.jsp", false);
		}
	}

	public ActionForward qgzxHgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		XsgwglService xsgwglService = new XsgwglService();

		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String userType = session.getAttribute("userOnLine").toString();

		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xxmc", xxmc);

		String xh = DealString.toGBK(request.getParameter("xh"));

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}

		// 判断是否是贫困生
		if (!("".equalsIgnoreCase(xh) || xh == null)) {
			request.setAttribute("IsKns", dao.isKns(xh) == true ? "yes" : "no");
		}
		//获取中国地质大学学生空闲时间表
		xsgwglService.freeTimeTableZgdzdx(xh, request);

		// 查询学生基本信息
		map = xsgwglService.getStuInfo(xh);

		// 判断是否在设置的允许申请时间范围内 tag 为empty不在申请时间内
		String tag = xsgwglService.checkTime();
		if ("empty".equalsIgnoreCase(tag)) {
			request.setAttribute("sqsjFlag", "1");
			map.put("xn", "");
			map.put("nd", "");
			map.put("xq", "");
			map.put("xqmc", "");
		} else {
			String[] tmp = Hgsqservice.getStuTimeService();
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
			map.put("xqmc", tmp[3]);
		}

		// 获取岗位名称列表
		List<HashMap<String, String>> gwList = Hgsqservice.getGwNameListService("no");
		request.setAttribute("gwList", gwList);

		if (xh != null && !"".equalsIgnoreCase(xh)) {//判断是否贫困生
			map.put("sfpks", (dao.isKns(xh) == true) ? "是" : "否");
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);

		if (xh != null && !"".equalsIgnoreCase(xh)) {//判断学生是否通过学院推荐
			HashMap<String, String> timeMap = service.getSqsjInfo();
			String xn = timeMap.get("xn");
			String nd = timeMap.get("nd");
			String xq = timeMap.get("xq");
			
			String result = Hgsqservice.getXytjcountService(xh, xn, nd, xq);
			if (Integer.parseInt(result) < 1) {
				request.setAttribute("noCommend", "yes");
			}
			boolean flag = Hgsqservice.getGwshcountService(xh, xn, nd, xq);
			if (flag) {
				request.setAttribute("gwExists", "yes");
			}
			//获取学生在岗岗位
			result = Hgsqservice.getGwdmService(xh, xn, nd, xq);
			map.put("gwdmgwsbsj", result);
			
		}

		request.setAttribute("do", "no");// 用于判断是不是进行修改操作，no表示不是修改操作
		request.setAttribute("rs", map);
		request.setAttribute("chkList", dao.getChkList(2));

		request.setAttribute("sqdwList", service.getYrdwList());
		return mapping.findForward("success");

	}
	
	private ActionForward qgzxhgsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxService service = new QgzxService();
		XsgwglService gwglService = new XsgwglService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xh = "";
		//获取设置的参数
		HashMap<String, String> timeMap = service.getSqsjInfo();
		
		//获取岗位名称列表
		List gwList = Hgsqservice.getGwNameListService("no");
		request.setAttribute("gwList", gwList);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map = Hgsqservice.getQgzxOneService(pkValue);//换岗详细信息查询
		XsgwglService xsgwglService = new XsgwglService();
		xsgwglService.freeTimeTableZgdzdx(pkValue.split("-")[0], request);
		
		//获取学生在岗岗位
		String result = Hgsqservice.getGwdmService(pkValue.split("-")[0],timeMap.get("xn"), timeMap.get("nd"), timeMap.get("xq"));
		//在岗岗位
		map.put("gwdmgwsbsj", result);
		//拟换岗岗位
		String hgdmhgsj = map.get("hgdm")+"-"+map.get("hgsj");
		map.put("hgdmhgsj", hgdmhgsj);		
		//学生是否是困难生
		xh = map.get("xh");
		map.put("sfpks", service.isKns(xh) ? "是" : "否");
		
		gwglService.freeTimeTableGzdx(xh, request);//'空闲时间表'数据
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}
	
	/**中国地质大学换岗申请保存*/
	private ActionForward zgdzdx_hg_Save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		XsgwglService xsgwglService = new XsgwglService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tab = request.getParameter("tab");
		tab = StringUtils.isNull(tab) ? "" :tab;
		String tab1 = request.getParameter("tab1");
		tab1 = StringUtils.isNull(tab1) ? "" : tab1;
		
//		 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
//		 岗位申请时间
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		//工作时间保存
		Hgsqservice.updateQgzxTime(xh, request);
		//其他基本信息保存
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String hgdmhgsj1 = DealString.toGBK(request.getParameter("hgdmhgsj1"));
		String hgdmhgsj2 = DealString.toGBK(request.getParameter("hgdmhgsj2"));
		String hgdmhgsj3 = DealString.toGBK(request.getParameter("hgdmhgsj3"));
		String[] strGw = {hgdmhgsj1,hgdmhgsj2,hgdmhgsj3};
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("xssq"));
		String yhtc = DealString.toGBK(qgzxhgsqForm.getYhtc());
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		for(int i=0;i<strGw.length;i++){
			if(!"".equalsIgnoreCase(strGw[i]) && strGw[i] != null){
				String gw=strGw[i].split("-")[0];
				String sj=strGw[i].split("-")[1];
				String []tmp1  = {xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,yhtc,gw,sj,bz};
				boolean res = Hgsqservice.hgxx_saveService(tmp1);
				pkValue = xh+gwdm+gwsbsj+ gw+sj;
				if(res){
					request.setAttribute("inserted", "ok");
				}else{
					i++;
					request.setAttribute("inserted", "no");
					request.setAttribute("reason", "换岗记录已经存在！");
					break;
				}
			}
		}
		//信息查询
		this.selectPageDataByOne(request, "zgdzdx_xshgxxb", "view_zgdzdx_xshgxxb", pkValue);		
		HashMap<String, String> rs = (HashMap<String, String>)request.getAttribute("rs");
		rs.put("hgdmhgsj1", rs.get("hgdm")+"-"+rs.get("hgsj"));
		rs.put("gwdmgwsbsj", rs.get("gwdm")+"-"+rs.get("gwsbsj"));
		rs.put("isKns", service.isKns(xh) ? "是" : "否");
		request.setAttribute("rs", rs);
		
		//获取中国地质大学学生空闲时间表
		xsgwglService.freeTimeTableZgdzdx(xh, request);		
		request.setAttribute("gwExists", "yes");
		request.setAttribute("gwList", Hgsqservice.getGwNameListService("no"));
		request.setAttribute("chkList", dao.getChkList(2));
		request.setAttribute("sqdwList", service.getYrdwList());
		return mapping.findForward("success");
	}
	
	/**中国地质大学换岗信息修改单条记录保存*/
	private ActionForward zgdzdx_hg_SaveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 1);
		
//		 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = dataSearchForm.getXh();
		
//		 岗位申请时间
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		//工作时间保存
		Hgsqservice.updateQgzxTime(xh, request);
		//其他基本信息保存
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String hgdmhgsj1 = DealString.toGBK(request.getParameter("hgdmhgsj"));
		String[] strGw = {hgdmhgsj1};
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("xssq"));
		String yhtc = DealString.toGBK(qgzxhgsqForm.getYhtc());
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		for(int i=0;i<strGw.length;i++){
			if(!"".equalsIgnoreCase(strGw[i]) && strGw[i] != null){
				String gw=strGw[i].split("-")[0];
				String sj=strGw[i].split("-")[1];
				String[] tmp1 = {lxdh,xn,nd,xq,sqly,yhtc,gw,sj,bz};
				boolean res = Hgsqservice.hgxx_updateService(tmp1,pkValue);
				if(res){
					request.setAttribute("inserted", "ok");
				}else{
					i++;
					request.setAttribute("inserted", "no");
					request.setAttribute("reason", "拟申请岗位重复或无效！");
					break;
				}
			}
		}
		
		return mapping.findForward("success");
	}
	
	/**中国地质大学换岗审核*/
	private ActionForward qgzxHgsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		QgzxHgsqService service = new QgzxHgsqService();
		QgzxService qgzxService = new QgzxService();
		CommanForm model = (CommanForm) form;
		String tableName = "view_zgdzdx_xshgxxb";
		String realTable = "zgdzdx_xshgxxb";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		boolean isYrdw = service.checkIsYrdwUser(userName);//判断是否是用人单位用户
		
		StringBuffer querry = new StringBuffer();
		
		String go = request.getParameter("go");
		String tips = "勤工助学 - 审核 - 换岗申请审核";
		List<HashMap<String, String>> topTr = null;
		List<HashMap<String, String>> gwList = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		String[] colList = { "主键", "行号", "xh","xm", "gwdm","hgdm","sqsj",
				"xyyj", "xxyj", "lxdh", "xn", "nd", "xqmc"};
		String[] CNcolList = { "主键", "行号", "学号","姓名", "在岗岗位","申请岗位","申请时间",
				Base.YXPZXY_KEY+"意见", "学校意见", "联系电话", "学年", "年度", "学期"};
		topTr = dao.arrayToList(colList, CNcolList);
		
		if ("xy".equalsIgnoreCase(userType)) {
			model.setXydm(userDep);
		} 
		
		gwList = service.getGwListService(userName);
		List<HashMap<String, String>> gwxzList = service.getGwXzListService();
		querry = ToolClass.getHgxxQuery(model,isYrdw);
		if ("go".equalsIgnoreCase(go)) {//查询
			vector.addAll(service.getHgXsService(querry, colList, userType));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", model.getXn());
		paramter.put("nd", model.getNd());
		paramter.put("xq", model.getXq());
		paramter.put("yrdwdm", model.getYrdwdm());
		paramter.put("gwxzdm", model.getGwxz());
		
		
		request.setAttribute("gwList",qgzxService.getGwmcxxList(paramter,"no"));//所有审核通过岗位列表
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
//		request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}
	
	/**中国地质大学换岗审核操作*/
	private ActionForward hgsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		String pkValue = DealString.toGBK(request.getParameter("pkString"));
		String[] pkTmp = pkValue.split("!!SplitOneSplit!!");
		String type = DealString.toGBK(request.getParameter("type"));
		
		boolean res = Hgsqservice.hgsqshService(pkTmp,userType,type);
		
		request.setAttribute("result", res);
		return new ActionForward("/qgzxHgsqsh.do", false);
	}
	
	/**中国地质大学辞岗申请*/
	private ActionForward qgzxCgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		XsgwglService xsgwglService = new XsgwglService();
		XsxxglService xsxxglService = new XsxxglService();

		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> timeMap = service.getSqsjInfo();
		
		String userType = session.getAttribute("userOnLine").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xxdm = StandardOperation.getXxdm();

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		
		String xn = timeMap.get("xn");
		String nd = timeMap.get("nd");
		String xq = timeMap.get("xq");
		
		//查询学生基本信息
		map = xsgwglService.getStuInfo(xh);
		if (xh != null && !"".equalsIgnoreCase(xh)) {
			boolean result = Hgsqservice.getGwshcountService(xh, xn, nd, xq);
			if (result) {
				request.setAttribute("gwExists", "yes");
			}
			map.put("xn", xn);
			map.put("nd", nd);
			map.put("xq", xq);
			map.put("xqmc", xsxxglService.getXqmc(xq));
		
			//获取学生在岗岗位
			String xmdm = Hgsqservice.getGwdmService(xh, xn, nd, xq);
			map.put("gwdmgwsbsj", xmdm);
			map.put("gwdm", xmdm.split("-")[0]);
			
		}
		
		if("teacher".equalsIgnoreCase(userType)
				&& (xh==null || xh.equalsIgnoreCase(""))){
			request.setAttribute("gwExists", "yes");
		}
		request.setAttribute("rs", map);		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			// 广州大学
			request.setAttribute("gwList", Hgsqservice.getXsgwList(xh));
			return mapping.findForward("gwdx_xstgsq");
		}
		return mapping.findForward("success");
	}
	
	/**学生辞岗信息保存
	 * @throws Exception */
	private ActionForward zgdzdx_cg_Save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String xxdm = StandardOperation.getXxdm();

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tab = request.getParameter("tab");
		tab = StringUtils.isNull(tab) ? "" :tab;
		String tab1 = request.getParameter("tab1");
		tab1 = StringUtils.isNull(tab1) ? "" : tab1;
		String modi = request.getParameter("modi");
		
//		 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
//		 岗位申请时间
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			gwdmgwsbsj = dataSearchForm.getGwdm();
		}
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		
		boolean res=false;
		if("yes".equalsIgnoreCase(modi)){
			String[] tmp1  = {lxdh,sqly,bz,xh,gwdm,gwsbsj};
			 res = Hgsqservice.cgxx_saveService(tmp1,modi);
		}else{
			String[] tmp1  = {xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,bz};
			 res = Hgsqservice.cgxx_saveService(tmp1,modi);
		}
		if(res){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
			if("yes".equalsIgnoreCase(modi)){
				request.setAttribute("reason", "请重试！");
			}
			request.setAttribute("reason", "辞岗申请已提交，请勿重复申请！");
		}
		return new ActionForward("/qgzxCgsq.do", false);
	}
	
	/**辞岗申请审核查询*/
	private ActionForward qgzxCgsqsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		QgzxHgsqService service = new QgzxHgsqService();
		QgzxService qgzxService = new QgzxService();
		CommanForm model = (CommanForm) form;
		String xxdm = StandardOperation.getXxdm();
		String tableName = "view_zgdzdx_xscgxxb";
		String realTable = "zgdzdx_xscgxxb";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		boolean isYrdw = service.checkIsYrdwUser(userName);//判断是否是用人单位用户
		
		StringBuffer querry = new StringBuffer();
		
		String go = request.getParameter("go");
		String tips = "勤工助学 - 审核 - 辞岗申请审核";
		List<HashMap<String, String>> topTr = null;
		List<HashMap<String, String>> gwList = null;
		ArrayList<String[]> vector = new ArrayList<String[]>();
		String rsNum = "";
		
		String[] colList = { "主键", "行号", "xh","xm", "gwdm","sqsj",
				"xyyj", "xxyj", "lxdh", "xn", "nd", "xqmc"};
		String[] CNcolList = { "主键", "行号", "学号","姓名", "辞岗岗位","申请时间",
				Base.YXPZXY_KEY+"意见", "学校意见", "联系电话", "学年", "年度", "学期"};
		topTr = dao.arrayToList(colList, CNcolList);
		
		if("xy".equalsIgnoreCase(userType)){
			//学院用户
			model.setXydm(userDep);
		}
		if (isYrdw) {
			gwList = service.getGwListForXydmService(userName);
		} else {
			gwList = service.getGwListService(userName);
		}
		List<HashMap<String, String>> gwxzList = service.getGwXzListService();
		model.setUserName(userName);
		querry = ToolClass.getCgxxQuery(model,isYrdw);
		if ("go".equalsIgnoreCase(go)) {//查询
			vector.addAll(service.getCgXsService(querry, colList, userType));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", model.getXn());
		paramter.put("nd", model.getNd());
		paramter.put("xq", model.getXq());
		paramter.put("yrdwdm", model.getYrdwdm());
		paramter.put("gwxzdm", model.getGwxz());
		
		
		request.setAttribute("gwList",qgzxService.getGwmcxxList(paramter,"no"));//所有审核通过岗位列表
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
//		request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		FormModleCommon.commonRequestSet(request, tableName, realTable, vector, topTr);
		return mapping.findForward("success");
	}
	
	/**辞岗申请审核操作*/
	private ActionForward cgsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		String pkValue = DealString.toGBK(request.getParameter("pkString"));
		String[] pkTmp = pkValue.split("!!SplitOneSplit!!");
		String type = DealString.toGBK(request.getParameter("type"));
		
		@SuppressWarnings("unused")
		boolean res = Hgsqservice.cgsqshService(pkTmp,userType,type);
		return new ActionForward("/qgzxCgsqsh.do", false);
	}
	
	private ActionForward qgzxcgsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxService service = new QgzxService();
		
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> timeMap = service.getSqsjInfo();
		
		map = Hgsqservice.getQgzxcgOneService(pkValue);
		if(map!=null){
			request.setAttribute("gwExists", "yes");
		}
		//获取学生在岗岗位
		String result = Hgsqservice.getGwdmService(pkValue.split("-")[0],timeMap.get("xn"), timeMap.get("nd"), timeMap.get("xq"));
		map.put("gwdm", result.split("-")[0]);
		map.put("gwdmgwsbsj", result);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		
		return mapping.findForward("success");
	}
	
	private ActionForward zgdzdx_cg_SaveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();

		String modi = request.getParameter("modi");
		
		//保存数据
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		
		boolean res=false;
		String[] tmp1  = {lxdh,sqly,bz,xh,gwdm,gwsbsj};
		res = Hgsqservice.cgxx_saveService(tmp1,modi);
		
		if(res){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
			request.setAttribute("reason", "请重试！");
		}
		String pkValue=xh+"-"+gwdm+"-"+gwsbsj;
		HashMap<String, String> map = new HashMap<String, String>();
		map = Hgsqservice.getQgzxcgOneService(pkValue);
		if(map!=null){
			request.setAttribute("gwExists", "yes");
		}
		map.put("gwdm", gwdm);
		map.put("gwdmgwsbsj", gwdm+"-"+gwsbsj);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	/**用人单位更换学生岗位申请
	 * @throws Exception */
	private ActionForward qgzxYrdwGhXsGwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String xxdm = StandardOperation.getXxdm();
		String page = "success";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = request.getSession().getAttribute("userName").toString();
		
		List<HashMap<String, String>> gwList = new ArrayList<HashMap<String,String>>();
		if ("xy".equalsIgnoreCase(userType)) {
			String xydm = userDep;
			gwList = Hgsqservice.getGwListForXydmService(xydm);
		} 
//		if(service.isYrdwUser(userName)){
			gwList = Hgsqservice.getGwListService(userName);
//		}
		//清理临时表中的学生记录
		Hgsqservice.deleteYrdwghxslsbSerivce(userName);
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			page = "gzdx_xyghgwsq";
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map = Hgsqservice.getYrdwxxService(userName);
		map.put("userName", userName);
		
		request.setAttribute("path", "qgzxYrdwGhXsGwsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gwList", gwList);
		request.setAttribute("rs", map);
		return mapping.findForward(page);
	}
	
	/**中国地质大学用人单位更换学生申请保存
	 * @throws Exception */
	private ActionForward zgdzdx_yrdwghxssq_Save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userName = request.getSession().getAttribute("userName").toString();
		
//		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		
//		 岗位申请时间
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		boolean res = false;
		/**中国地质大学获取用人单位辞退学生临时信息*/
		ArrayList<String[]> ctList = Hgsqservice.getYrdwctxslsxxserivce(userName);
		if(ctList!=null){
			for(int i=0;i<ctList.size();i++){
				res = Hgsqservice.insertYrdwghXsService(ctList.get(i),xn,nd,xq,sqly,bz);//临时表中信息插入正式表
				if(!res){
					request.setAttribute("insert", "no");					
					return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
				}
			}
		}
		
		/**中国地质大学获取用人单位更换学生临时信息*/
		ArrayList<String[]> ghList = Hgsqservice.getYrdwghxslsxxserivce(userName);
		if(ghList!=null){
			for(int i=0;i<ghList.size();i++){
				res = Hgsqservice.insertYrdwghXsService(ghList.get(i),xn,nd,xq,sqly,bz);//临时表中信息插入正式表
				if(!res){
					request.setAttribute("insert", "no");
					return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
				}
			}
		}
		
		/**中国地质大学获取用人单位申请学生临时信息*/
		ArrayList<String[]> sqList = Hgsqservice.getYrdwsqxslsxxserivce(userName);
		if(sqList!=null){
			for(int i=0;i<sqList.size();i++){
				res = Hgsqservice.insertYrdwghXsService(sqList.get(i),xn,nd,xq,sqly,bz);//临时表中信息插入正式表
				if(!res){
					request.setAttribute("insert", "no");
					return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
				}
			}
		}
		return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
	}
	
	private ActionForward qgzxYrdwGhXsGwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CommanForm model = (CommanForm)form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxService service = new QgzxService();
		String tableName = "view_zgdzdx_yrdwghxsxxb";
		String realTable = "zgdzdx_yrdwghxsb";
//		String pk = "xh||gwdm||gwsbsj";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String xydm = request.getParameter("xydm");
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
//		String hgdm = DealString.toGBK(request.getParameter("hgdm"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
//		String nj = request.getParameter("nj");
		String gwxz = request.getParameter("gwxz");
		
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		
		String go = request.getParameter("go");
		
		List topTr = null;
		List gwList = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		String[] colList = { "主键", "行号", "xh","xm", "gwdm","sqsj",
				"xxyj", "lxdh", "xn", "nd", "xqmc","bj"};
		String[] CNcolList = { "主键", "行号", "学号","姓名", "岗位","申请时间",
				"学校意见", "联系电话", "学年", "年度", "学期","申请类型"};
		topTr = dao.arrayToList(colList, CNcolList);
		
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			model.setXydm(xydm);
		}
		
		gwList = Hgsqservice.getGwListService(userName);
		
		List gwxzList = Hgsqservice.getGwXzListService();
		querry = ToolClass.getYrdwGhXsxxQuery(xh, gwdm, "", "", "", "", xn, "", xq, "", "",gwxz,xydm,bj, querry);
		if ("go".equalsIgnoreCase(go)) {//查询
			vector.addAll(Hgsqservice.getYrdwGhXsService(querry, colList, userType));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", model.getXn());
		paramter.put("nd", model.getNd());
		paramter.put("xq", model.getXq());
		paramter.put("yrdwdm", model.getYrdwdm());
		paramter.put("gwxzdm", model.getGwxz());
		
		
		request.setAttribute("gwList",service.getGwmcxxList(paramter,"no"));//所有审核通过岗位列表
		request.setAttribute("path", "qgzxYrdwGhXsGwsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("topTr", topTr);
//		request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("bj", bj);
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}
	
	/**中国地质大学用人单位更换学生详细信息*/
	private ActionForward qgzxYrdwGhxsModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
//		HttpSession session = request.getSession();
//		String userType = session.getAttribute("userOnLine").toString();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			
			HashMap<String, String> map = new HashMap<String, String>();
			map = Hgsqservice.getYrdwGhXsOneService(pkValue);
			if(map!=null){
				request.setAttribute("gwExists", "yes");
			}
//			获取学生在岗岗位
//			String result = Hgsqservice.getGwdmService(pkValue.split("-")[0], Base.currXn, Base.currNd, Base.currXq);
//			map.put("gwdm", result.split("-")[0]);
//			map.put("gwdmgwsbsj", result);
			request.setAttribute("rs", map);
			request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}
	
	/**用人单位更换学生岗位申请审核操作*/
	private ActionForward qgzxYrdwGhxssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		String pkValue = DealString.toGBK(request.getParameter("pkString"));
		String[] pkTmp = pkValue.split("!!SplitOneSplit!!");
		String type = DealString.toGBK(request.getParameter("type"));
		
		@SuppressWarnings("unused")
		boolean res = Hgsqservice.yrdwGhxsshService(pkTmp,userType,type);
		
		return new ActionForward("/qgzxYrdwGhXsGwsh.do", false);
	}
}
