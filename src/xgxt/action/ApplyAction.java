/*
 * 创建日期 2006-9-16
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.CommanForm;
import xgxt.pjpy.zzsf.ZzsfPjpyAction;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.xbemy.XbemyQgzxDAO;
import xgxt.qgzx.zgdzdx.QgzxZgdzdxService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CheckPower;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xszz.ynys.XszzYnysService;

import common.Globals;

/**
 * @author bat_zzj
 */

public class ApplyAction extends Action {

	//DAO dao = DAO.getInstance();
	String writeAble = "";


	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str.equalsIgnoreCase("all"));
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			//判断用户读写权
			writeAble = Base.getWriteAble(request);
			request.setAttribute("writeAble", writeAble);
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			userType = session.getAttribute("userOnLine").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();
			if (myAct.equalsIgnoreCase("priseApply")) {//奖学金申请
				myActFwd = priseApply(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("priseApplyAll")) {//奖学金申请查询
				myActFwd = priseApplyAll(mapping, form, request, response,
						userType);
				/*writeAble = CheckPower.checkUsrPageAccessPower(session
						.getAttribute("userOnLine").toString(), session
						.getAttribute("userName").toString(), "prise_result.do") ? "yes"
						: "no";*/
				//writeAble = "yes";
			} else if (myAct.equalsIgnoreCase("creditApply")) {// 荣誉称号申请
				String xxdm = StandardOperation.getXxdm();
				if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)) {
					if ("student".equals(userType)) {
						return new ActionForward(
								"/pjpyhxxywh.do?method=rychOne", false);
					} else {
						return new ActionForward(
								"/pjpyhxxywh.do?method=rychSq", false);
					}
				}else if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//浙江理工大学
					return new ActionForward("/zjlgPjpy.do?method=rychSq",false);
				}else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){//金华职业
				    return new ActionForward("/jhzy_rych.do?method=rychSq",false);
				}else if(Globals.XXDM_ZJCMXY .equalsIgnoreCase(xxdm)){//浙江理工大学
					return new ActionForward("/zjcm_rych.do?method=rychSq",false);
				}else if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){//宁波天一职业技术学院
					return new ActionForward("/nbty_rych.do?method=rychSq",false);
				}else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){//闽江学院
					return new ActionForward("/mjxyRych.do?method=rychSq",false);
				}else{
					myActFwd = creditApply(mapping, form, request, response,
							userType);
				}
			} else if (myAct.equalsIgnoreCase("creditApplyAll")) {// 荣誉称号申请查询
				String xxdm = StandardOperation.getXxdm();
				if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY) && !"student".equalsIgnoreCase(userType)) {
					return new ActionForward("/pjpyhxxywh.do?method=rychall",
							false);
				} else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){//金华职业技术学院
					return new ActionForward("/jhzy_rych.do?method=rychCx",
							false);
				}else if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){//金华职业技术学院
					return new ActionForward("/zjcm_rych.do?method=sqjgManage",
							false);
				}else if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){//宁波天一职业技术学院,荣誉称号审核结果
					return new ActionForward("/nbty_rych.do?method=rychResult");
				}else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){//闽江学院,荣誉称号审核结果
					return new ActionForward("/mjxyRych.do?method=resultRych");
				}else if (Globals.XXDM_CQDZKJ.equals(xxdm)
						 ||Globals.XXDM_CQGYZY.equals(xxdm)) {
					return new ActionForward("/typj.do?method=creditResult",false);
				}else {
					myActFwd = creditApplyAll(mapping, form, request, response,
							userType);
				}
			} else if (myAct.equalsIgnoreCase("commApply")) {
				myActFwd = commApply(mapping, form, request, response, userType);
				writeAble=CheckPower.checkUsrPageAccessPower(session.getAttribute("userOnLine").toString(),session.getAttribute("userName").toString(), "comm_apply.do")?"yes":"no";
			} else if (myAct.equalsIgnoreCase("commApplyAll")) {
				myActFwd = commApplyAll(mapping, form, request, response,userType);
			} else if (myAct.equalsIgnoreCase("commMoneyApply")) {
				myActFwd = commMoneyApply(mapping, form, request, response,userType);
				writeAble=CheckPower.checkUsrPageAccessPower(session.getAttribute("userOnLine").toString(),session.getAttribute("userName").toString(), "comm_money_apply.do")?"yes":"no";
			} else if (myAct.equalsIgnoreCase("commMoneyApplyAll")) {
				myActFwd = commMoneyApplyAll(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("neederApply")) {
				myActFwd = neederApply(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("neederApplyAll")) {
				myActFwd = neederApplyAll(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("collegeApply")) {
				myActFwd = collegeApply(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("collegeApplyAll")) {
				myActFwd = collegeApplyAll(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("nationApply")) {
				myActFwd = nationApply(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("nationApplyAll")) {
				myActFwd = nationApplyAll(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("workerApplyAll")) {
				myActFwd = workerApplyAll(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("saveApply")) {//公用模块申请保存
				myActFwd = saveApply(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("assisResult")) {
				myActFwd = assisResult(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("putAgainApply")) {
				myActFwd = putAgainApply(mapping, form, request, response,
						userType);
			} else if(myAct.equalsIgnoreCase("printAgainApply")){
				myActFwd = printAgainApply(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("ticketBook")) {
				myActFwd = ticketBook(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("SPECIALPRISE")) {
				myActFwd = specialPrise(mapping, form, request, response,
						userType);
			} else if (myAct.equalsIgnoreCase("XSZXJXJHZB")){
				myActFwd = xszxjxjhzb(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("jxjpsdjb")){
				myActFwd = jxjpsdjb(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("playApply")){
				myActFwd = playApply(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("leisure")){
				myActFwd = leisure(mapping, form, request, response, userType);
			} else if (myAct.equalsIgnoreCase("xfhjApply")){ //学费缓交
				myActFwd = xfhjApply(mapping, form, request, response, userType);
			} else if (myAct.equalsIgnoreCase("newStuInsureApply")){
				myActFwd = newStuInsureApply(mapping, form, request, response, userType);
				writeAble=CheckPower.checkUsrPageAccessPower(session.getAttribute("userOnLine").toString(),session.getAttribute("userName").toString(), "newStuInsureApply.do")?"yes":"no";
				boolean flag = CheckPower.checkTime();
				request.setAttribute("allow", flag);
				String xxdm = StandardOperation.getXxdm();
				if(!xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					request.setAttribute("allow", "true");
				}
			} else if (myAct.equalsIgnoreCase("InsureAppSearch")){
				myActFwd = InsureAppSearch(mapping, form, request, response, userType);
			} else if(myAct.equalsIgnoreCase("printsqb")){
				myActFwd= printSqb(mapping, form, request, response, userType);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}
	/**
	 * 申请表格打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param userType
	 * @return
	 */
	private ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		
		String xh=request.getParameter("xh");
		String gw=request.getParameter("xmdm");
		String gw2=request.getParameter("xmdmmodi");
		if(gw==null){
			gw=gw2.split("-")[0];
		}
		gw=gw.split("-")[0];
		String nd=request.getParameter("nd");
		String xq=request.getParameter("xqmc");
		String xn=request.getParameter("xn");
		String sfpks=request.getParameter("sfpks");
		String yjshf=request.getParameter("yjshf");
		
		String sfgr=request.getParameter("sfgr");
		String sfdq=request.getParameter("sfdq");
		String sfdbh=request.getParameter("sfdbh");
		String sfyfdx=request.getParameter("sfyfdx");
		
		String kcjqgzxsj=request.getParameter("kcjqgzxsj");
		String yhtc=request.getParameter("yhtc");
		String xssq=request.getParameter("xssq");
		String bz=request.getParameter("bz");
		
		String sql="select a.xh,a.lxdh,a.yhkh,a.xm,(select yhmc from dmk_yh where yhdm=a.yhdm)yhmc,a.xy,a.zymc,a.bjmc,a.xb,a.nj,(select mzmc from mzdmb where mzdm=a.mz ) mzmc,(select zzmmmc from zzmmdmb where zzmmdm=a.zzmm) zzmmmc from xsxxb a where xh=?";
		HashMap<String, String> map=new HashMap<String, String>();
		map=dao.getMap(sql, new String[]{xh}, new String[]{"xh","xm","xb","nj","xy","zymc","bjmc","mzmc","zzmmmc","yhmc","lxdh","yhkh"});
		map.put("gw", gw);
		map.put("nd", nd);
		map.put("xq", xq);
		map.put("xn", xn);
		map.put("sfpks", sfpks);
		map.put("yjshf", yjshf);
		map.put("sfgr", sfgr);
		map.put("sfdq", sfdq);
		map.put("sfdbh", sfdbh);
		map.put("sfyfdx", sfyfdx);
		map.put("kcjqgzxsj", kcjqgzxsj);
		map.put("yhtc", yhtc);
		map.put("xssq", xssq);
		map.put("bz", bz);
		request.setAttribute("rs", map);
		
		
		return mapping.findForward("success");
	}

	private ActionForward xfhjApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String userType) 
	throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		CommanForm commanForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = commanForm.getXh();
		String sql = "";
		String pkValue = "";
		String[] outPut = null;
		String[] outValue = null;
		String doType = request.getParameter("doType");
		if(doType==null){
			doType="";
		}
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}

		sql = " select xh,xm,xb,nj,xymc,zymc,bjmc from view_xfhjxx where xh = ? ";
		outPut = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"};
		outValue = dao.getOneRs(sql, new String[]{ xh }, outPut);   

		if (doType!=null&&doType.equalsIgnoreCase("save")){
			String nd = commanForm.getNd();
			String hjje = request.getParameter("hjje");
			String xn = commanForm.getXn();
			String xq = commanForm.getXq();
			String hjqx = DealString.toGBK(request.getParameter("hjqx"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			sql = " delete from xfhjb where xh = ? and nd = ? ";
			boolean ok = dao.runUpdate(sql, new String[]{ xh,nd });
			if(ok){
				sql = " insert into xfhjb(xh,hjje,nd,xn,xq,hjqx,bz) values(?,?,?,?,?,?,?) ";
				ok = dao.runUpdate(sql, new String[]{ xh,hjje,nd,xn,xq,hjqx,bz });
				request.setAttribute("insert", ok);
				sql = " select * from view_xfhjxx ";
				outPut = dao.getColumnName(sql);
				outValue = dao.getOneRs(sql+" where xh = ? and nd = ? ", new String[]{ xh,nd }, outPut);
			}
		}


		if(outValue!=null){
			for(int i=0;i<outPut.length;i++){
				if(outValue[i]!=null) 
					map.put(outPut[i].toLowerCase(), outValue[i]);
				else 
					map.put(outPut[i].toLowerCase(), "");
			}        	
		} else {
			for(int i=0;i<outPut.length;i++){
				map.put(outPut[i].toLowerCase(), "");
			}        	
		}

		request.setAttribute("rs", map); 
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
		request.setAttribute("xqList", dao.getXqList());// 发送学期列表    	
		return mapping.findForward("success");
	}

	private ActionForward leisure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String userType) 
	throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm commanForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap> kxList = new ArrayList<HashMap>();
		String doType = request.getParameter("act");
		String xh = commanForm.getXh();
		String sql = "";
		String kxbz = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}

		String[] sj = {"早自修（7:30—8:20）","第1-2节（8:30—10:05）","第3-4节（10:25—12:00）",
				"午休（12:00—13:45）","第5-6节（13:50—15:25）","第7-8节（15:30—17:05）","晚自修（17:50—20:15）"};
		String[] xq = {"mon","tue","wed","thu","fri","sat","sun"};


		if(doType!=null&&doType.equalsIgnoreCase("save")){
			String[][] kxzt =new String[7][7];
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
					kxzt[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j));
					kxbz += kxzt[i][j];
				}
			}			
			sql = " delete from xsqgzxsjb where xh = ? ";
			boolean ok = dao.runUpdate(sql,new String[]{ xh });
			if(ok){
				sql = " insert into xsqgzxsjb values(?,?) ";
				boolean ok2 = dao.runUpdate(sql, new String[]{ xh,kxbz });
				if(ok2){
					request.setAttribute("insert", "success");
				} else {
					request.setAttribute("insert", "failure");
				}
			}			
		}

		if(xh!=null&&!xh.equalsIgnoreCase("")){
//			xh = "3020221007";
			sql = "select kxbz from xsqgzxsjb where xh = ? ";
			kxbz = dao.getOneRs(sql, new String[] { xh }, "kxbz");
			if(kxbz!=null&&!kxbz.equalsIgnoreCase("")){
				String[] kx = new String[7];
				int j=0;
				for(int i=0;i<7;i++){    			
					kx[i] = kxbz.substring(j, j+7);
					j+=7;
					char[] a = kx[i].toCharArray();
					HashMap<String, String> map2 = new HashMap<String, String>();
					for(int p=0;p<7;p++){
						map2.put(xq[p], String.valueOf(a[p]));
					}
					map2.put("sj", sj[i]);
					map2.put("sjIndex", String.valueOf(i));        			
					kxList.add(map2);
				}
				request.setAttribute("kxList", kxList);
			} else {
				for(int i=0;i<7;i++){ 
					HashMap<String, String> map2 = new HashMap<String, String>();
					for(int p=0;p<7;p++){
						map2.put(xq[p], "");
					}
					map2.put("sj", sj[i]);
					map2.put("sjIndex", String.valueOf(i));        			
					kxList.add(map2);
				}
				request.setAttribute("kxList", kxList);
			}
		} 
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx ";
		String[] inputValue = new String[]{};
		String[] outputValue = new String[]{ "xh","xm","xb","nj","xymc","zymc","bjmc" };

		if(xh!=null&&!xh.equalsIgnoreCase("")){
			sql += " where xh = ? ";
			inputValue = new String[]{ xh };
		} else {
			sql += " where 1=2 ";
		}

		String[] stuInfo = dao.getOneRs(sql, inputValue, outputValue);
		if(stuInfo!=null){
			for(int i=0;i<outputValue.length;i++){
				if(stuInfo[i]!=null){
					map.put(outputValue[i], stuInfo[i]);
				} else {
					map.put(outputValue[i], "");
				}
			}
		} else {
			for(int i=0;i<outputValue.length;i++){
				map.put(outputValue[i], "");
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	private ActionForward playApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
//		CommanForm commanForm = new CommanForm();
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkVal = DealString.toGBK(request.getParameter("pkVal")).trim();
		String act = DealString.toGBK(request.getParameter("act")).trim();
		String sql = "";
		String querry = " where 1=1 ";    	
//		String tableName = "view_hdzxxx";
		String realTable = "hdzxsqb";
		String pk = "bmdm||sqr||djsj";
		String tit = "日常事务 - 申请 - 填写活动中心申请表";

		if(act==null||act.equalsIgnoreCase("")){
			sql = "select * from "+ realTable; 
			String[] outString = dao.getColumnName(sql+" where 1=2 ");
			for (int i=0 ; i < outString.length ; i++){
				map.put(outString[i].toLowerCase(), "");
			}
			map.put("ykxx", "不需要");
			request.setAttribute("rs", map); 
		}
		else if(act!=null&&act.equalsIgnoreCase("view")){
			tit = "日常事务 - 审核 - 活动中心审核";
			if (!isNull(pkVal)) {
				querry += "and " + pk + " = ?";
			}
			sql = "select * from "+ realTable; 
			String[] outString = dao.getColumnName(sql+" where 1=2 ");
			String[] outValue = dao.getOneRs(sql+querry, new String[]{pkVal}, outString); 
			for (int i=0 ; i < outString.length ; i++){
				map.put(outString[i].toLowerCase(),outValue[i]);
			}
			request.setAttribute("rs", map);
			request.setAttribute("showsh", "showsh");
			request.setAttribute("update", "update");
			request.setAttribute("chkList", dao.getChkList(3));
		}else {
			ActionForward newFwd = new ActionForward();
			String bmdm = DealString.toGBK(request.getParameter("bmdm")).trim();
			String sqr = DealString.toGBK(request.getParameter("sqr")).trim();
			String djsj = DealString.toGBK(request.getParameter("djsj")).trim();
			String yesNo = DealString.toGBK(request.getParameter("hdsh")).trim();
			String yksxm = DealString.toGBK(request.getParameter("yksxm")).trim();
			String yksdh = DealString.toGBK(request.getParameter("yksdh")).trim();
			if(act!=null&&act.equalsIgnoreCase("update")){
				sql = "update "+ realTable + " set yksxm=?, yksdh=?, hdsh = '"+ yesNo +"'" +
				" where bmdm =? and sqr=? and djsj=? ";
				dao.runUpdate(sql, new String[]{ yksxm,yksdh,bmdm,sqr,djsj });
				sql = "select * from "+ realTable; 
				String[] outString = dao.getColumnName(sql+" where 1=2 ");
				String[] outValue = dao.getOneRs(sql+" where bmdm =? and sqr=? and djsj=? ", 
						new String[]{ bmdm,sqr,djsj }, outString); 
				for (int i=0 ; i < outString.length ; i++){
					map.put(outString[i].toLowerCase(),outValue[i]);
				}
				request.setAttribute("rs", map);
				request.setAttribute("update", "update");
				request.setAttribute("message", "审核成功");
				request.setAttribute("result", "view");
				request.setAttribute("chkList", dao.getChkList(3));
				act = "";
				newFwd = new ActionForward("/play_apply.do?act=" + act,false);
				return newFwd;

			} else if(act!=null&&act.equalsIgnoreCase("delete")){
				sql = "delete from "+ realTable + " where bmdm =? and sqr=? and djsj=? ";
				dao.runUpdate(sql, new String[]{ bmdm,sqr,djsj });
				request.setAttribute("delete", "delete");
				request.setAttribute("message", "删除成功");
				request.setAttribute("result", "view");
				act = "";
				newFwd = new ActionForward("/play_apply.do?act=" + act,false);
				return newFwd;
			}    		
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)){
			request.setAttribute("isXNMZ", "yes");
		}
//		String bmdm = DealString.toGBK(request.getParameter("bmdm")).trim();
//		String sqr = DealString.toGBK(request.getParameter("sqr")).trim();
//		String djsj = DealString.toGBK(request.getParameter("djsj")).trim();
		sql = "select b.bmdm,b.bmmc from ZXBZ_XXBMDM b";
		List bmList = dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
		List xxqList = dao.getList("select dm,xqmc from dm_zju_xq", new String[]{}, new String[]{"dm","xqmc"});
		HashMap<String, String> rs = dao.getMap("select szbm bmdm,yhm sqr from yhb where yhm=?", new String[] {request.getSession().getAttribute("userName").toString()}, new String[] {"bmdm","sqr"});
		request.setAttribute("xxqList", xxqList);//发送校区数据列表
		request.setAttribute("bmList", bmList);
		request.setAttribute("tit", tit);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}
	
	private ActionForward priseApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";
		String act = request.getParameter("act");
		String pk = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String jxjdm = request.getParameter("xmdm");
		String xxdm = StandardOperation.getXxdm();
		String tab = request.getParameter("tab");//长沙民政社会奖学金判断
		tab = StringUtils.isNull(tab) ? "" : tab;
		
		HashMap<String, String> returnMap =  (request.getAttribute("map") != null ?(HashMap<String, String>) request.getAttribute("map") : null);
		
		if (!StringUtils.isNull(act) && "modi".equalsIgnoreCase(act)) {
			request.setAttribute("noview", "modi");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			request.setAttribute("showhzy", "showhzy");
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// 浙江机电
			return new ActionForward("/pjpy_zjjd_jxjsqdefault.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			return new ActionForward("/pjpy_apply.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
			return new ActionForward("/pjpy_zbdx_apply.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			return new ActionForward("/pjpy_jsxx_apply.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
			return new ActionForward("/pjpy_bjlydx_apply.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// 漳州师范
			return new ActionForward("/pjpy_zzsf_apply.do?pk=" + pk
					+ "&pkValue=" + pkValue + "&act=" + act + "&xmdm=" + jxjdm,
					false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {// 浙江商业职业
			return new ActionForward("/pjpy_zjsyzy.do?method=jxjApply", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {// 安徽建筑工程
			if ("qtjxj".equalsIgnoreCase(tab)) {
				request.setAttribute("tab", "qtjxj");
			}
			return new ActionForward("/pjpy_zzsf_apply.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HBSFXY)) {// 湖北师范学院
			request.setAttribute("ishbsf", "yes");
			// return new ActionForward("/pjpy_hbsf_apply.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {// 武汉理工大学
			return new ActionForward("/pjpy_whlgdx.do?method=jxjApply", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			return new ActionForward("/pjpy_ynys_jxjsq.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)
				&& !"modi".equalsIgnoreCase(act)) {// 上海出版印刷
			return new ActionForward("/pjpy_shcb_jxjsq.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)
				&& "modi".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_shcb_jxjxg.do", false);
		} else if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_nbzy_jxjsq.do", false);
		} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/zjlgPjpy.do?method=yxxsjxjsq", false);
		} else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_scjz_jxjsq.do?pkValue=" + pkValue,
					false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)) {// 武汉理工华夏学院
			return new ActionForward("/pjpy_whlghxxy_jxjsqdefault.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// 金华职业
			return new ActionForward("/jhzy_pjpy_sqManage.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {// 浙江传媒
			return new ActionForward("/zjcm_rych.do?method=rychSq", false);
		} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//厦门理工学院奖学金申请跳转
			return new ActionForward("/pjpy_xmlg_jxjsqDefault.do", false);
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {//常州信息
			return new ActionForward("/pjpy_czxx_jxjAdd.do?typ=add", false);
		} else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm) 
				&& "modi".equalsIgnoreCase(act)) {//闽江学院
			return new ActionForward("/mjxyJxj.do?method=jxjSq&doType=modi&pkValue"+pkValue+
					"xh="+xh, false);
		} else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {//闽江学院
			return new ActionForward("/mjxyJxj.do?method=jxjSq", false);
		}else if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){//丽水学院
			return new ActionForward("/pjpyLsxy.do?method=jxjSq", false);
		}
//		if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {//宁波理工奖学金申请
//			return new ActionForward("/pjpy_nblg_jxjsq.do", false);
//		}
		if (userType.equalsIgnoreCase("student")) {
	    	xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		xh = StringUtils.isNull(xh) ? "" : xh.trim();
		sql = "select a.*,(select b.rxrq from xsxxb b where a.xh=b.xh) rxrq,(select b.kh from xsxxb b where a.xh=b.xh) fkh,(select b.yhkh from xsxxb b where a.xh=b.xh) kh,(select b.ykth from xsxxb b where a.xh=b.xh) ykt from view_xsjbxx a where xh=?";
		String[] colList = dao
			.getColumnName("select a.*,(select b.rxrq from xsxxb b where a.xh=b.xh) rxrq,(select b.kh from xsxxb b where a.xh=b.xh) fkh,(select b.yhkh from xsxxb b where a.xh=b.xh) kh,(select b.ykth from xsxxb b where a.xh=b.xh) ykt from view_xsjbxx a where 1=2");
	
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		String nd = Base.getJxjsqnd();
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String[] colval = null;
		
		HashMap<String, String> zqmap = dao.getMapNotOut("select xn,nd,xq from pjpy_pjzqb", new String[]{});
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
			sql = "select dcj,zcj,tcj,zpf,cpzfpm from view_zhszcp where xh = ? and xn = ? and nd = ? ";
			colval = new String[] { xh,xn,nd};
		}else{
			sql = "select dcj,zcj,tcj,zpf,cpzfpm from view_zhszcp where xh = ? and xn = ? and nd = ? and xq = ?";
			colval = new String[] { xh,xn,nd,xq};
			
			if (!zqmap.isEmpty()) {
				if ("checked".equalsIgnoreCase(zqmap.get("xq"))) {
					sql = "select dcj,zcj,tcj,zpf,cpzfpm from view_zhszcp where xh = ? and xn = ? and xq = ?";
					colval = new String[] { xh,xn,xq};
				} else if ("checked".equalsIgnoreCase(zqmap.get("nd"))) {
					sql = "select dcj,zcj,tcj,zpf,cpzfpm from view_zhszcp where xh = ? and nd = ? ";
					colval = new String[] { xh,nd};
				} else if ("checked".equalsIgnoreCase(zqmap.get("xn"))) {
					sql = "select dcj,zcj,tcj,zpf,cpzfpm from view_zhszcp where xh = ? and xn = ?";
					colval = new String[] { xh,xn};
				}
			}
		}
		
		colList = new String []{"dcj","zcj","tcj","zpf","cpzfpm"};
		rs = dao.getOneRs(sql, colval, colList);
		for(int i=0;i<colList.length;i++){
			map.put(colList[i], (rs!=null)? rs[i]:"");
		}
		if (jxjdm != null) {
			sql = "select (select b.jxjlbmc from jxjlbdmb b where b.jxjlbdm = a.jxjlb) jxjlb,jlje from jxjdmb a where jxjdm=?";
			String[] jxjInfo = dao.getOneRs(sql, new String[] { jxjdm },
					new String[] { "jxjlb", "jlje" });
			if (jxjInfo != null) {
				map.put("jxjlb", jxjInfo[0]);
				map.put("jlje", jxjInfo[1]);
			}
		}
		sql = "select * from jxjdmb";	
		List jxjList = dao.getList(sql, new String[] {}, new String[] {	
				"jxjdm", "jxjmc" });
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {//长沙民政
			//if (!tab.equalsIgnoreCase("") && tab.equalsIgnoreCase("qtjxj")) {//其它奖学金
				String[] enList = new String[]{"院级", "系级"};
				String[] cnList = new String[]{"院级", "系级"};
				List<HashMap<String, String>> jxjdmlbList = dao.arrayToList(enList, cnList);
				String jxjdmlb = DealString.toGBK(request.getParameter("jxjdmlb"));//奖学金类别
				String pageJxjlb = DealString.toGBK(request.getParameter("jxjlb1"));//
				pageJxjlb = !StringUtils.isNull(pageJxjlb) ? pageJxjlb : "";
				if (StringUtils.isEqual(pageJxjlb, "院级")) {//院级奖学金
					sql = "select xgbdm from xtszb where rownum=1";
					String[] xgbdm = dao.getOneRs(sql, new String[]{}, new String[]{"xgbdm"});
					String dm = "";
					if (xgbdm != null && xgbdm.length > 0) {
						dm = xgbdm[0];
					}
					sql = "select jxjdm,jxjmc from jxjdmb where xydm = ?";
					jxjList = dao.getList(sql, new String[]{dm}, new String[]{"jxjdm", "jxjmc"});
				}
				if (StringUtils.isEqual(pageJxjlb, "系级")) {//系级奖学金(学生所在系级奖学金)
					sql = "select xydm from view_xsjbxx where xh = ? and rownum=1";
					String xdm = dao.getOneRs(sql, new String[]{xh}, "xydm");
					xdm = !StringUtils.isNull(xdm) ? xdm : "";
					sql = "select jxjdm,jxjmc from jxjdmb where xydm = ?";
					jxjList = dao.getList(sql, new String[]{xdm}, new String[]{"jxjdm", "jxjmc"});
				}
				applyForm.setJxjdmlb(jxjdmlb);
				String xmdm = dao.getOneRs("select jxjmc from jxjdmb where jxjdm=?", new String[]{applyForm.getXmdm()}, "jxjmc");
				if (!StringUtils.isNull(xmdm) && StringUtils.isEqual(xmdm, "社会奖学金")) {
					request.setAttribute("shjxj", "yes");
				}
				request.setAttribute("jxjdmList", jxjdmlbList);
				request.setAttribute("showCsmz", "yes");
			//}else{//社会奖学金
				//return new ActionForward("/pjpy_csmz_apply.do",false);
			//}
		}
		request.setAttribute("jxjList", jxjList);
		sql = "select * from jxjdmb";
		List jxjListN = dao.getList(sql, new String[] {}, new String[] {	
				"jxjdm", "jxjmc" });
		request.setAttribute("jxjListN", jxjListN);
		sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd", "jxjsqxq" });
		if (tmp!=null && tmp.length==3) {
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
		}
		
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1",
					"tyhgbz1", "jxj2", "shyg2", "ddj2",
					"tyhgbz2", "jxj3", "shyg3", "ddj3",
					"tyhgbz3", "jxj4", "shyg4", "ddj4","tyhgbz4" };
		}else{
			qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1", "zycjpx1",
					"tyhgbz1", "jxj2", "shyg2", "ddj2",  "bxkpjcj2", "bjcjpx2", "zycjpx2",
					"tyhgbz2", "jxj3", "shyg3", "ddj3",  "bxkpjcj3", "bjcjpx3", "zycjpx3",
					"tyhgbz3", "jxj4", "shyg4", "ddj4",   "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4" };
		}
		sql = "select * from xsjxjxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {StringUtils.isNull(xh) ? "" : xh.trim()}, qtxx);
		if( act != null && act.equalsIgnoreCase("modi")){
			String sql1 = "select * from view_xsjxjb where "+pk+"='"+pkValue+"'";
			String[] jxjxx = dao.getColumnName("select * from view_xsjxjb where 1=2");
			String[] jxjxxArr = dao.getOneRs(sql1, new String[]{}, jxjxx);
			for(int i=0;i<jxjxx.length;i++){
				if(jxjxxArr[i] != null){
					if(jxjxx[i].equalsIgnoreCase("jxjdm")){
						applyForm.setXmdm(jxjxxArr[i]);
					} else if(jxjxx[i].equalsIgnoreCase("DNSHJXJ")){
						applyForm.setZdm(jxjxxArr[i]);
					} else if (jxjxx[i].equalsIgnoreCase("KYCG")){
						System.out.println(jxjxx[i]);
					}
					map.put(jxjxx[i].toLowerCase(), jxjxxArr[i]);
				}
			}
			applyForm.setXmdm(map.get("jxjdm"));
			applyForm.setJxjdmlb(map.get("jxjlb"));
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				jxjList = dao.getList("select jxjdm,jxjmc from jxjdmb where xydm = ?", new String[]{map.get("jxjxydm")}, new String[]{"jxjdm", "jxjmc"});
			} else {
				jxjList = dao.getList("select jxjdm,jxjmc from jxjdmb", new String[]{}, new String[]{"jxjdm", "jxjmc"});
			}
			request.setAttribute("jxjlbflag", "lock");
			request.setAttribute("jxjList", jxjList);
		}
		//System.out.println(sql);
		if(qtxxfs == null){
			qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
			map.put(qtxx[i],qtxxfs[i]);
		}
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{StringUtils.isNull(xh) ? "" : xh.trim()},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
			sjhm = tt[0] == null ? tt[0] : tt[0].trim();
			wysp = tt[1] == null ? tt[1] : tt[1].trim();
		}
		
		List<String[]> hjqkList = dao.rsToVator("select xn,xq,jxjmc from view_xsjxjb where xxsh='通过' and xysh='通过' and xh=?", new String[]{xh}, new String[]{"xn", "xq", "jxjmc"});
		String hjqk = "";//获奖情况
		for (int i=0;i<hjqkList.size();i++) {
			String[] tmps = hjqkList.get(i);
			if (tmps != null && tmps.length == 3) {
				hjqk += String.format("%1$s年  第%2$s学期  获%3$s \n", tmps[0],StringUtils.isNull(tmps[1]) ? "" : (StringUtils.isEqual(tmps[1], "03") ? "一" : "二"),tmps[2]);
			}
		}
		request.setAttribute("hjqkxx", hjqk);
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
			HashMap<String, String> cjpmMap = dao
					.getMapNotOut(
							"select cj,bjpm from view_zjcm_zhszcpb where xh=? and xn=? and xq=?",
							new String[] { xh, map.get("xn"), map.get("xq") });
			map.put("cj", cjpmMap.get("cj"));
			map.put("bjpm", cjpmMap.get("bjpm"));
		}
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm=?",
				new String[] { map.get("xq") }, "xqmc");
		map.put("xqmc", xqmc);
		
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("xh")) && returnMap != null && !returnMap.isEmpty()) {
			map.putAll(returnMap);
		}
		
		request.setAttribute("rs",map);
		request.setAttribute("oldjxjdm", applyForm.getXmdm());
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) || xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
			String jxjpdsj = dao.getOneRs("select jxjpdsj from pjsjszb", new String[]{}, "jxjpdsj");
			if (!StringUtils.isNull(jxjpdsj) && StringUtils.isEqual(jxjpdsj, "1")) {
				request.setAttribute("jxjsq", "yes");
			} else {
				request.setAttribute("jxjsq", "no");
			}
		}
		//四川建筑对于处分信息获取
		if (!StringUtils.isNull(xh) && Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			List<String[]> cfList = dao
					.rsToVator(
							"select xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,(select b.cflbmc from cflbdmb b where a.cflb=b.cflbdm) cflb,(select b.cfyymc from cfyydmb b where a.cfyy=b.cfyydm) cfyy from wjcfb a where xh=? and xn=? and xq=? and xxsh='通过'",
							new String[] { xh.trim(), map.get("xn"), map.get("xq") },
							new String[] {"xn", "xq", "cfyy", "cflb"});
			if (cfList!=null&&cfList.size()>0) {
				request.setAttribute("sflag", "该生在评奖学年有违纪记录，不符合申请条件！");
			}
			request.setAttribute("cfList", cfList);
			String pjcj = dao.getOneRs("select trunc(avg(cj),2) cj from view_zhhcjb where kcxz not like '%选修%' and xn=? and xq=? and xh=?", new String[]{map.get("xn"),map.get("xq"),xh}, "cj");
			String zdcj = dao.getOneRs("select min(cj) cj from view_zhhcjb where kcxz not like '%选修%' and xn=? and xq=? and xh=?", new String[]{map.get("xn"),map.get("xq"),xh}, "cj");
			request.setAttribute("pjcj", pjcj);
			request.setAttribute("zdcj", zdcj);
		} else if (Globals.XXDM_XCXY.equalsIgnoreCase(xxdm)) {
			if (!StringUtils.isNull(xh)) {
			HashMap<String, String> zhcj = dao.getMapNotOut("select pjcj,cxcj,zcj,pm,pjcjpm from view_xcxy_pjxscjb where xh=? and xn=? and xq=?", new String[]{xh,map.get("xn"),map.get("xq")});
			//List<String[]> cjList = dao.rsToVator("select kcmc,cj from cjb where xh=? and xn=? and xq=? and kcxz not like '%选修%'", new String[]{xh,map.get("xn"),map.get("xq")},new String[]{"kcmc","cj"});
			request.setAttribute("zhcj", zhcj);
			//request.setAttribute("cjL", cjList);
			HashMap<String, String> zdcj = dao.getMapNotOut("select zdpjcj,zdcxcj,zdzcj from xcxyjxjtjsz where jxjdm=?", new String[]{jxjdm});
			//List<String[]> cjList = dao.rsToVator("select kcmc,cj from cjb where xh=? and xn=? and xq=? and kcxz not like '%选修%'", new String[]{xh,map.get("xn"),map.get("xq")},new String[]{"kcmc","cj"});
			request.setAttribute("zdcj", zdcj);
			}
		} else{
			 HashMap<String, String> zdcj  = dao.getHashMapList2("select tjzdm,replace(substr(tj,3),'''','') tj from jxjhdtj where jxjdm=? and xn =?", new String[]{jxjdm,xn},new String []{"tjzdm","tj"});
			 request.setAttribute("zdcj", zdcj);
			
			
			sql = "select nvl(min(cj),'0') xszdcj from view_zhhcjb where xh = ? and xn = ? and xq = ?";
			colList = new String[]{xh,xn,xq};
			if (!zqmap.isEmpty()) {
				if ("checked".equalsIgnoreCase(zqmap.get("xq"))) {
					sql = "select nvl(min(cj),'0') xszdcj from view_zhhcjb where xh = ? and xn = ? and xq = ?";
					colList = new String[]{xh,xn,xq};
				} else if ("checked".equalsIgnoreCase(zqmap.get("nd"))) {
					sql = "select nvl(min(cj),'0') xszdcj from view_zhhcjb where xh = ? and nd = ?";
					colList = new String[]{xh,nd};
				} else if ("checked".equalsIgnoreCase(zqmap.get("xn"))) {
					sql = "select nvl(min(cj),'0') xszdcj from view_zhhcjb where xh = ? and xn = ?";
					colList = new String[]{xh,xn};
				}
			}
			String xszdcj = dao.getOneRs(sql, colList, "xszdcj");
			request.setAttribute("xszdcj", xszdcj);
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("jxjList", jxjList);
		return mapping.findForward("success");
	}


	private ActionForward priseApplyAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String xxdms = StandardOperation.getXxdm();
		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdms)) {
			return new ActionForward("/pjpy_czxx_jxjQuery.do", false);
		}
		if (xxdms.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			
			return new ActionForward("/pjpy_zjjd_jxjsqqry.do", false);
		}
		else if (xxdms.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY) && !"stu".equalsIgnoreCase(userType)) {
			
			return new ActionForward("/pjpy_whlghxxy_jxjsqqry.do", false);
		}
		else if (xxdms.equalsIgnoreCase(Globals.XXDM_ZJLG) && !"stu".equalsIgnoreCase(userType)) {
			
			return new ActionForward("/zjlgPjpy.do?method=jxjQuery", false);
		}else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdms)){//金华职业技术学院
			return new ActionForward("/jhzy_pjpySqsh.do?method=cxManage",
					false);
		}else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdms)){
			
			return new ActionForward("/mjxyJxj.do?method=resultJxj");
		} else if (xxdms.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {
			
			return new ActionForward("/zjcm_pjpy_jxjjg.do", false);
		} else if (Globals.XXDM_CQDZKJ.equals(xxdms)
				 ||Globals.XXDM_CQGYZY.equals(xxdms)
				 ||Globals.XXDM_CSDLZYJSXY.equals(xxdms)) {
			return new ActionForward("/typj.do?method=priseResult",false);
		} else if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdms)){
			//丽水学院
			return new ActionForward("/pjpyLsxy.do?method=priseResult",false);
		}
		String tab = request.getParameter("tab");//长沙民政社会奖学金判断
		tab = StringUtils.isNull(tab) ? "" : tab;
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "jxjmc",
					"jlje", "xysh", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			String xxdm = StandardOperation.getXxdm();
			String table = "view_xsjxjb";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "行号", "nd", "xn", "jxjmc", "jxjlb",
						"jlje", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {
				colList = new String[] { "pk", "行号", "nd", "xn", "jxjmc",
						"jlje", "jd", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				colList = new String[] { "行号", "nd", "xn", "jxjmc", "jxjlb",
						"jlje", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "pk", "行号", "nd", "xn", "jxjmc",
						"jxjlb", "jlje", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)
					|| Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "行号", "nd", "xn", "xq", "jxjmc",
						"jxjlb", "jlje", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				colList = new String[] { "行号", "nd", "xn", "jxjmc", "jxjlb",
						"jlje", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "行号", "xn", "xq", "jxjmc", "jxjlb",
						"jlje", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "VIEW_XSJXJB");
			} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdms)) {// 厦门理工单独的哟
				return new ActionForward("/pjpy_xmlg_queryJxjsqxxBystu.do",
						false);
			} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "行号", "xn", "xqmc", "jxjmc", "jlje",
						"zfpm", "fdysh", "xysh", "xxsh" };
				colListCN = dao.getColumnNameCN(colList, "view_czxx_xsjxjb");
				table = "view_czxx_xsjxjb";
			}
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select a.xn||a.nd||a.xh||a.jxjdm pk,rownum 行号, a.* from "+table+" a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
			request.setAttribute("writeAble", "yes");
		} else {
			String xxdm = dao.getXxdm();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				if (!StringUtils.isEqual(userType, "admin") || !StringUtils.isEqual(userType, "xx")) {
					//writeAble = "no";
				}
			}
			return new ActionForward("/data_search.do?act=prise&writeAble=" + writeAble + "&tab="+tab, false);
		}
		return mapping.findForward("success");
	}

    private ActionForward creditApply(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response,
	    String userType) throws Exception {
	HttpSession session = request.getSession();
	String sql = "";
	String xh = "";
	CommanForm applyForm = (CommanForm) form;
	DAO dao = DAO.getInstance();
	HashMap<String, String> map = new HashMap<String, String>();
	String xxdm = StandardOperation.getXxdm();
	String stab = request.getParameter("stab");
	String act = request.getParameter("act");
	String pkValue = request.getParameter("pkValue");
	String rychmc = DealString.toGBK(request.getParameter("rychmc"));
	rychmc = StringUtils.isNull(rychmc) ? "" : rychmc.trim();
	String dm = request.getParameter("dm");
	if (!StringUtils.isNull(dm)) {		
		rychmc = dao.getOneRs("select rychmc from rychdmb where rychdm=?", new String[]{dm}, "rychmc");
	}
	if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
		if (rychmc.contains("院优毕业生")) {
			request.setAttribute("info", "yes");
			request.setAttribute("yybys", "yes");
		}
		if (rychmc.contains("省优毕业生")) {
			request.setAttribute("sybys", "yes");
			request.setAttribute("info", "yes");
		}
	}
	//stab = !StringUtils.isNull(stab) ? stab : request.getAttribute("stab").toString(); 
	if (userType.equalsIgnoreCase("student")) {
	    xh = session.getAttribute("userName").toString();
	} else {
		xh = applyForm.getXh();
	}
	if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
		request.setAttribute("showhzy", "showhzy");
	}
	else if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
		return new ActionForward("/rych_jsxx_apply.do",false);
	}
	else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)){
		return new ActionForward("/pjpy_zzsf_rychsq.do",false);
	}
	else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//武汉理工大学
		return new ActionForward("/pjpy_whlgdx.do?method=rychApply",false);
	}
	else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
		return new ActionForward("/pjpy_zjjd_rychsq.do", false);
	}
	else if(Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)){//厦门理工
		return new ActionForward("/pjpy_xmlg_rychsqDefault.do");
	}
	else if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){//安徽建工
		if (!StringUtils.isNull(stab) && StringUtils.isEqual(stab, "stab")){
			
		}//个人荣誉称号申请	
		else {
			if (userType.equalsIgnoreCase("student")) {//学生只申请个人荣誉称号
				
			}else {
				return new ActionForward("/pjpy_ahjg_rysqdefault.do", false);
			}
			
		}//集体荣誉称号申请
		
		//return new ActionForward("/pjpy_zzsf_rychsq.do",false);
	}
	else if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {//井冈山大学荣誉称号申请
		request.setAttribute("jgsdxhjqk", "yes");//井冈山大学荣誉称号申请另加受奖励情况
		
		if (!StringUtils.isNull(stab) && StringUtils.isEqual(stab, "stab")){
			
		}//个人荣誉称号申请	
		else {
			if (userType.equalsIgnoreCase("student")) {//学生只申请个人荣誉称号
				
			}else {
				return new ActionForward("/pjpy_jgsdx_rysqdefault.do", false);
			}
			
		}//集体荣誉称号申请
	} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
		return new ActionForward("/pjpy_czxx_rychAdd.do?typ=add", false);
	} else if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
		//丽水学院
		return new ActionForward("/pjpyLsxy.do?method=rychSq");
	}
	
	if (Globals.XXDM_XCXY.equalsIgnoreCase(xxdm) || Globals.XXDM_HZZY.equalsIgnoreCase(xxdm) || Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
		request.setAttribute("viewPrint", "yes");
	}
	
	if (userType.equalsIgnoreCase("student")) {
	    xh = session.getAttribute("userName").toString();
	}
	sql = "select * from view_xsxxb where xh=?";
	String strsql = "select * from view_xsxxb where 1=2";
	if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
		sql = "select * from view_xsxxb where xh = ?";
		strsql = "select * from view_xsxxb where 1=2";
	}
	
	String[] colList = dao
		.getColumnName(strsql);
		String[] rs = dao.getOneRs(sql, new String[] { StringUtils.isNull(xh) ? "" : xh.trim() }, colList);
		
		sql = "select * from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd", "jxjsqxq" });
		if (tmp!=null && tmp.length==3) {
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = new String[]{};
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {
			qtxx = new String[] { "xh","drzw","tydbqk","byzx","syd","brjl","zysj","hjqk", "jlqk"};
		} else {
			qtxx = new String[] { "xh","drzw","tydbqk","byzx","syd","brjl","zysj","hjqk"};
		}
		sql = "select * from xsrychxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {StringUtils.isNull(xh) ? "" : xh.trim()}, qtxx);
		if(qtxxfs == null){
			qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
			map.put(qtxx[i],qtxxfs[i]);
		}
		request.setAttribute("printpk", xh+map.get("xn")+map.get("xq")+dm);
		String[] tt = dao.getOneRs("select jtszd,sjhm,wysp from xsfzxxb where xh=?",new String[]{StringUtils.isNull(xh) ? "" : xh.trim()},new String[]{"sjhm","wysp","jtszd"});
		String sjhm = "";
		String wysp = "";
		String jtszd = "";
		if(tt != null && tt.length == 3){
			sjhm = tt[0];
			wysp = tt[1];
			jtszd = tt[2];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		map.put("jtszd", jtszd);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			String[] zhszzfandcjzf = dao.getOneRs("select zysj,zhpfmc,cjmc from xsrychxxb where xh=?", new String[]{StringUtils.isNull(xh) ? "" : xh.trim()}, new String[]{"zhpfmc", "cjmc", "zysj"});
			if (zhszzfandcjzf != null && zhszzfandcjzf.length == 3) {
				map.put("zhpfmc", zhszzfandcjzf[0]);
				map.put("cjmc", zhszzfandcjzf[1]);
				map.put("zysj", zhszzfandcjzf[2]);
			}
		}
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
			HashMap<String, String> cjpmMap = dao
					.getMapNotOut(
							"select cj,bjpm from view_zjcm_zhszcpb where xh=? and xn=? and xq=?",
							new String[] { xh, map.get("xn"), map.get("xq") });
			map.put("cj", cjpmMap.get("cj"));
			map.put("bjpm", cjpmMap.get("bjpm"));
		}
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm=?",
				new String[] { map.get("xq") }, "xqmc");
		map.put("xqmc", xqmc);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
			if (!StringUtils.isNull(dm)) {
				HashMap<String, String> rychMap = dao.getMapNotOut("select byjyqx,mzpyqksm,jcqk from xsrychb where xh=? and xn=? and xq=? and rychdm=?", new String[]{xh,Base.getJxjsqxn(),Base.getJxjsqxq(),dm});
				map.put("byjyqx", rychMap.get("byjyqx"));
				map.put("mzpyqksm", rychMap.get("mzpyqksm"));
				map.put("jcqk", rychMap.get("jcqk"));
			}
		}
		
		// 地址是否采用标准代码
		XsxxglService xsxxglService = new XsxxglService();
		if("yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))){//地址信息取代码
			sql = " select (select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,0,instr(a.syd,'/')-1) )" 
				  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/')+1,instr(a.syd,'/',1,2)-instr(a.syd,'/')-1))"
			      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/',-1)+1)) syd,"
			      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,0,instr(a.hkszd,'/')-1) )" 
				  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/')+1,instr(a.hkszd,'/',1,2)-instr(a.hkszd,'/')-1))"
			      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/',-1)+1)) hkszd,"
			      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,0,instr(a.jg,'/')-1) ) "
			      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/')+1,instr(a.jg,'/',1,2)-instr(a.jg,'/')-1))"
			      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/',-1)+1)) jg from view_xsxxb a where xh=?";
			map.putAll(dao.getMap(sql, new String[]{xh}, new String[]{"syd", "jg", "hkszd"}));
		}
		
		request.setAttribute("rs",map);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			String[] enList = new String[]{"院级", "系级"};
			String[] cnList = new String[]{"院级", "系级"};
			String rychlb = DealString.toGBK(request.getParameter("rychlb"));//页面传入的荣誉称号类别
			rychlb = !StringUtils.isNull(rychlb) ? rychlb : "";
			if (StringUtils.isEqual(rychlb, "院级")) {//院级荣誉称号列表
				sql = "select xgbdm from xtszb where rownum=1";
				String xgbdm = dao.getOneRs(sql, new String[]{}, "xgbdm");
				sql = "select rychdm,rychmc from rychdmb where xydm = ?";
				rychList = dao.getList(sql, new String[]{!StringUtils.isNull(xgbdm) ? xgbdm : ""}, new String[]{"rychdm", "rychmc"});
			} 
			if (StringUtils.isEqual(rychlb, "系级")) {//系级荣誉称号列表通过学号获取
				sql = "select xydm from view_xsjbxx where xh = ? and rownum=1";
				String xdm = dao.getOneRs(sql, new String[]{xh}, "xydm");
				sql = "select rychdm,rychmc from rychdmb where xydm = ?";
				rychList = dao.getList(sql, new String[]{!StringUtils.isNull(xdm) ? xdm : ""}, new String[]{"rychdm", "rychmc"});
			}
			List<HashMap<String, String>> rychlbList = dao.arrayToList(enList, cnList);
			request.setAttribute("rychlbList", rychlbList);
			request.setAttribute("showCsmz", "yes");
			applyForm.setRychlb(rychlb);
		}
		
		if (!StringUtils.isNull(act) && StringUtils.isEqual("modi", act)) {
			
			HashMap<String, String> rsMap = dao
					.getMapNotOut(
							"select jxjlb,rychdm,jxjxydm from view_xsrychb where xn||nd||rychdm||xh = ? ",
							new String[] { pkValue });
			rychList = dao.getList(
					"select rychdm,rychmc from rychdmb where xydm=?",
					new String[] { rsMap.get("jxjxydm") }, new String[] { "rychdm",
							"rychmc" });
			applyForm.setRychlb(rsMap.get("jxjlb"));
			applyForm.setXmdm(rsMap.get("rychdm"));
			
		}
		request.setAttribute("pkValue", pkValue);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) || xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
			String jxjpdsj = dao.getOneRs("select rychpdsj from pjsjszb", new String[]{}, "rychpdsj");
			if (!StringUtils.isNull(jxjpdsj) && StringUtils.isEqual(jxjpdsj, "1")) {
				request.setAttribute("jxjsq", "yes");
			} else {
				request.setAttribute("jxjsq", "no");
			}
		}
		request.setAttribute("rychList", rychList);
		return mapping.findForward("success");
	}

	private ActionForward creditApplyAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		HttpSession session = request.getSession();
		
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String stab = request.getParameter("stab");
		String tableName = "VIEW_XSRYCHB";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				colList = new String[] { "行号", "nd", "xn", "rychmc","fdysh", "xysh", "xxsh" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//安徽建筑工业学院学生查询个人荣誉称号
				colList = new String[] { "行号", "nd", "xn", "rychmc", "xysh", "xxsh" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "pk", "行号", "nd", "xn", "rychmc","jxjlb","fdysh", "xysh", "xxsh" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				colList = new String[]{ "行号", "nd", "xn", "rychmc", "fdysh", "xysh", "xxsh"};
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "行号", "xn","xq", "rychmc", "fdysh","xysh", "xxsh" };				
			} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {//浙江理工
				tableName ="view_zjlg_xsrychxx";
				colList = new String[] { "行号", "xn", "rychmc", "xysh", "xxsh" };
			} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				tableName ="view_czxx_xsrychb";
				colList = new String[] { "行号", "xn", "xqmc", "rychmc", "zfpm", "fdysh","xysh", "xxsh" };
			} else if (Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){//丽水学院
				colList = new String[] { "行号", "xn","rychmc", "xysh", "xxsh" };
			} else{
				colList = new String[] { "行号", "xn","nd",  "rychmc", "xysh", "xxsh" };
			} 
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号,a.* from "+tableName+" a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				return new ActionForward("/rychsqqry.do", false);
			}
			if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){
				return new ActionForward("/zjlgPjpy.do?method=rychDefault", false);
			}
			if(Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)){//厦门理工学院
				return new ActionForward("/pjpy_xmlg_rychsqQuery.do", false);
			}
			if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){//丽水学院
				return new ActionForward("/pjpyLsxy.do?method=rychResult",false);
			}
			return new ActionForward("/data_search.do?act=credit&writeAble="+writeAble+"&stab="+stab, false);
		}
		return mapping.findForward("success");
	}

	private ActionForward commApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		CommanForm applyForm = (CommanForm) form;
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tableName");
		String pkValue = request.getParameter("pkValue");	
		String writeAble = CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "comm_apply.do")?"yes":"no";
		String dwjldm=request.getParameter("dwjldm");
		String Type=request.getParameter("Type");
		if(Type!=null&&!Type.equalsIgnoreCase("")){
			String dwjlmc=DealString.toGBK(request.getParameter("dwjlmc")).trim();
			String otherValue=request.getParameter("xn").trim()+request.getParameter("nd").trim()+request.getParameter("xq").trim();
			String sql="select dwjlxmdm from dwjlxmdmb where dwjlxmmc='"+dwjlmc+"'";
			dwjldm=dao.getOneRs(sql, new String[]{},"dwjlxmdm");	
			map.put("xmdm", dwjldm);
			dwjldm=otherValue+dwjldm;
		}
		String xh = applyForm.getXh();
		String sql = "";		
		if(doType==null||doType.equalsIgnoreCase("")){
			dwjldm=applyForm.getXmdm();
			map.put("xmdm", dwjldm);
		}
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		//修改
		if(tableName!=null){
			sql = "select * from "+tableName;		
			colList = dao.getColumnName(sql);
			String[] outValue = new String[colList.length+1];		
			sql = "select ND,XN,XQ,XH,XM,XB,NJ,XYDM,XYMC,ZYDM,ZYMC,BJDM,BJMC,JLXMDM,xn||nd||xq||jlxmdm xmdm,DWJLXMMC,DWJLFSMC,DWJLLBMC,SQSJ,XYSH,XXSH,XXZS,XSYJ,JXJ,JXJSH,sqly,bjdypm,njzypm,tcah,shgzqk,lxdh,sjhm,dzyx from "+tableName+" where xn||nd||xq||jlxmdm||xh=?";
			colList = StringUtils.joinStrArr(colList,new String[]{"xmdm"});
			outValue = dao.getOneRs(sql,new String[]{pkValue},colList);
			for(int i=0;i<outValue.length;i++){
				if(outValue[i]==null || outValue[i].equals(null)){
					if(colList[i].equalsIgnoreCase("jlxmdm".toUpperCase())){
						applyForm.setXmdm(outValue[i]);
						dwjldm = outValue[i];
						map.put("xmdm",dwjldm);
					}
					map.put(colList[i].toLowerCase()," ");
				}else{
					map.put(colList[i].toLowerCase(),outValue[i].toString());
				}
			}	
			dwjldm = map.get("xmdm");
		}
		if (dwjldm != null&&!dwjldm.equalsIgnoreCase("")) {
			sql = "select dwjllbmc,dwjlfsmc,nd,xn,xq,(select distinct xqmc from xqdzb b where a.xq=b.xqdm)xqmc,strtodatetime(pcsj) pcsj,strtodatetime(jlqx) jlqx,jlstj,jlxxxx,jxjxe from view_dwjlxx a where xn||nd||xq||jlxmdm='"+dwjldm+"'";
			String[] jxjInfo = dao.getOneRs(sql, new String[] {},new String[] { "dwjllbmc", "dwjlfsmc", "nd", "xn", "xq",
					"pcsj", "jlqx", "jxjxe", "jlxxxx", "jlstj" ,"xqmc"});
			if (jxjInfo != null) {
				map.put("jllb", jxjInfo[0]);
				map.put("jlfs", jxjInfo[1]);
				map.put("nd", jxjInfo[2]);
				map.put("xn", jxjInfo[3]);
				map.put("xq", jxjInfo[4]);
				map.put("pcsj", jxjInfo[5]);
				map.put("jlqx", jxjInfo[6]);
				map.put("jxjxe", jxjInfo[7]);
				map.put("jlxxxx", jxjInfo[8]);
				map.put("jlstj", jxjInfo[9]);	
				map.put("xqmc", jxjInfo[10]);
			}
		}
		if(xh != null && !"".equalsIgnoreCase(xh) && dwjldm != null && !"".equalsIgnoreCase(dwjldm)){
			sql = "select BJDYPM,NJZYPM,TCAH,SHGZQK,SQLY from view_dwjlsq where xh=? and xn||nd||xq||jlxmdm=?";
			map.putAll(dao.getMap(sql, new String[]{xh,dwjldm}, 
					new String[]{"bjdypm","njzypm","tcah","shgzqk","sqly"}));
		}
		sql = "select (xn||nd||xq||jlxmdm) jlxmdm,dwjlxmmc from view_dwjlxx where jzrq>=to_char(sysdate,'yyyymmdd')";
		List dwjlList = dao.getList(sql, new String[] {}, new String[] {"jlxmdm", "dwjlxmmc" });
		request.setAttribute("dwjlList", dwjlList);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	private ActionForward commApplyAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "pkValue", "nd", "xn", "xq", "xh", "xm","dwjlxmmc", "sqsj", "xysh", "xxsh", "xxzs", "jxj","jxjsh" };
			colListCN = dao.getColumnNameCN(colList, "VIEW_DWJLSQ");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.*,a.xn||a.nd||a.xq||a.jlxmdm||a.xh pkValue from view_dwjlsq a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			return new ActionForward("/data_search.do?act=comm&writeAble="+writeAble, false);
		}
		return mapping.findForward("success");
	}

	private ActionForward commMoneyApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = Base.getWriteAble(request);
		String xh = applyForm.getXh();
		String dwjldm = request.getParameter("xmdm");
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		if (dwjldm != null) {
			sql = "select dwjllbmc,dwjlfsmc,nd,xn,xq,strtodatetime(pcsj) pcsj,strtodatetime(jlqx) jlqx,jlstj,jlxxxx,jxjxe from view_dwjlxx where xn||nd||xq||jlxmdm=?";
			String[] jxjInfo = dao.getOneRs(sql, new String[] { dwjldm },new String[] { "dwjllbmc", "dwjlfsmc", "nd", "xn", "xq",
					"pcsj", "jlqx", "jxjxe", "jlxxxx", "jlstj" });
			if (jxjInfo != null) {
				map.put("jllb", jxjInfo[0]);
				map.put("jlfs", jxjInfo[1]);
				map.put("nd", jxjInfo[2]);
				map.put("xn", jxjInfo[3]);
				map.put("xq", jxjInfo[4]);
				map.put("pcsj", jxjInfo[5]);
				map.put("jlqx", jxjInfo[6]);
				map.put("jxjxe", jxjInfo[7]);
				map.put("jlxxxx", jxjInfo[8]);
				map.put("jlstj", jxjInfo[9]);
			}
		}
		if(StringUtils.isNotNull(map.get("xh")) && StringUtils.isNotNull(dwjldm)){
			sql = "select sqje,yhtc tc,sqsy sqly,bz,hjqk from view_dwjljxjsq where xn||nd||xq||jlxmdm||xh=?";
			String[] outputValue = {"sqje", "tc", "sqly", "bz", "hjqk"};
			map.putAll(dao.getMap(sql, new String[]{dwjldm+map.get("xh")}, outputValue));
			map.put("xmdm", dwjldm);
		}
		sql = "select (xn||nd||xq||jlxmdm) jlxmdm,dwjlxmmc from view_dwjlxx a where exists(select 1 from dwjlsqb b where b.xn||b.nd||b.xq||b.jlxmdm=a.xn||a.nd||a.xq||a.jlxmdm and b.xh=? and b.XXZS = '通过')";
		List dwjlList = dao.getList(sql, new String[] { xh }, new String[] {"jlxmdm", "dwjlxmmc" });
		request.setAttribute("canApp", String.valueOf(dwjlList.size()));
		request.setAttribute("dwjlList", dwjlList);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		request.setAttribute("writeAble",writeAble);
		return mapping.findForward("success");
	}

	private ActionForward commMoneyApplyAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
					"dwjlxmmc", "sqsj", "xysh", "xxsh", "xxzs", "jxj" };
			colListCN = dao.getColumnNameCN(colList, "VIEW_DWJLSQ");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_dwjlsq a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			return new ActionForward("/data_search.do?act=comm&writeAble="+writeAble, false);
		}
		return mapping.findForward("success");
	}

	private ActionForward neederApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		
		XszzYnysService xszzYnysService = XszzYnysService.getInstance();
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		boolean tempTea = false;
		String doType = request.getParameter("doType");//操作类型
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			tempTea = true;
		}
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		String[] colList;
		if(tempTea){
			sql = "select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1,b.jtfk,b.jtfkrs,"
				+ "b.jtyzsr,b.jtrjsr,b.jtsrly,b.yzbm,b.jtzz,"
				+ "b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_nl,b.jtcy1_ysr,b.jtcy1_gzdw,"
				+ "b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_nl,b.jtcy2_ysr,b.jtcy2_gzdw,"
				+ "b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_nl,b.jtcy3_ysr,b.jtcy3_gzdw,"
				+ "b.jtcy4_xm,b.jtcy4_gx,b.jtcy4_nl,b.jtcy4_ysr,b.jtcy4_gzdw,"
				+ "b.jtcy5_xm,b.jtcy5_gx,b.jtcy5_nl,b.jtcy5_ysr,b.jtcy5_gzdw "
				+ "from view_xsjbxx a left join knssqb b on a.xh=b.xh where a.xh=? ";
			colList = dao.getColumnName("select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1,b.jtfk,b.jtfkrs,"
					+ "b.jtyzsr,b.jtrjsr,b.jtsrly,b.yzbm,b.jtzz,"
					+ "b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_nl,b.jtcy1_ysr,b.jtcy1_gzdw,"
					+ "b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_nl,b.jtcy2_ysr,b.jtcy2_gzdw,"
					+ "b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_nl,b.jtcy3_ysr,b.jtcy3_gzdw,"
					+ "b.jtcy4_xm,b.jtcy4_gx,b.jtcy4_nl,b.jtcy4_ysr,b.jtcy4_gzdw,"
					+ "b.jtcy5_xm,b.jtcy5_gx,b.jtcy5_nl,b.jtcy5_ysr,b.jtcy5_gzdw "
					+ "from view_xsjbxx a,knssqb b where a.xh=b.xh and 1=2");
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)){
			sql = "select a.*,b.lxdh lxdh1,b.jtzz,b.kncddm,b.cjqgzugws,b.zmcldm,b.sqyy,b.bz bz1 from view_xsjbxx a left join knssqb b on a.xh=b.xh where a.xh=?";
			colList = dao.getColumnName("select a.*,b.lxdh lxdh1,b.jtzz,b.kncddm,b.cjqgzugws,b.zmcldm,b.sqyy,b.bz bz1 from view_xsjbxx a,knssqb b where a.xh=b.xh and 1=2");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			sql = "select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1,b.jtrjsr,b.kncddm from view_stu_details a left join knssqb b on a.xh=b.xh where a.xh=?";
			colList = dao.getColumnName("select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1,b.jtrjsr,b.kncddm from view_stu_details a,knssqb b where a.xh=b.xh and 1=2");
		}else{
			sql = "select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1 from view_xsjbxx a left join knssqb b on a.xh=b.xh where a.xh=?";
			colList = dao.getColumnName("select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1 from view_xsjbxx a,knssqb b where a.xh=b.xh and 1=2");
		}
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		map.put("stuExists", "yes");
		map.put("ksq", "yes");
		if (userType.equalsIgnoreCase("student")) {
			String[] jxjksjssj = null;
			String rightNow = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') now from dual", new String[]{}, "now");
			sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难生' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {
				map.put("ksq", "yes");
			} else {
				map.put("ksq", "no");
			}
		}
		map.put("userType", userType);
		if(tempTea){
			request.setAttribute("xxmc", "shgc");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)){
			List<HashMap<String, String>> kncdList = new ArrayList<HashMap<String,String>>();
			String [] cols = {"kncddm","kncdmc"};
			kncdList = dao.getList("select dm kncddm,mc kncdmc from kncddmb", new String[]{}, cols);

			List<HashMap<String, String>> zmclList = new ArrayList<HashMap<String,String>>();
			String[] col = {"zmcldm","zmclmc"};
			zmclList = dao.getList("select dm zmcldm,mc zmclmc from zmcldmb", new String[]{}, col);

//			request.setAttribute("kncddm", " ");
//			request.setAttribute("zmcldm", " ");
			request.setAttribute("kncdList", kncdList);
			request.setAttribute("zmclList", zmclList);
			request.setAttribute("xxmc", "szxx");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			request.setAttribute("xxmc", "ynys");
			//困难生等级列表
			request.setAttribute("knsdjList", xszzYnysService.getKnsdjList());
		}else{
			request.setAttribute("xxmc", "null");
		}
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}

	private ActionForward neederApplyAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
					"dwjlxmmc", "sqsj", "xysh", "xxsh", "xxzs", "jxj" };
			colListCN = dao.getColumnNameCN(colList, "VIEW_DWJLSQ");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_dwjlsq a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			return new ActionForward("/data_search.do?act=comm&writeAble="+writeAble, false);
		}
		return mapping.findForward("success");
	}

	private ActionForward collegeApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2"); //返回列名数组
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);  //获得查询的记录集
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward collegeApplyAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
					"dwjlxmmc", "sqsj", "xysh", "xxsh", "xxzs", "jxj" };
			colListCN = dao.getColumnNameCN(colList, "VIEW_DWJLSQ");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_dwjlsq a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			return new ActionForward("/data_search.do?act=comm&writeAble="+writeAble, false);
		}
		return mapping.findForward("success");
	}

	private ActionForward nationApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao
		.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		return mapping.findForward("success");
	}

	private ActionForward nationApplyAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
					"dwjlxmmc", "sqsj", "xysh", "xxsh", "xxzs", "jxj" };
			colListCN = dao.getColumnNameCN(colList, "VIEW_DWJLSQ");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_dwjlsq a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			return new ActionForward("/data_search.do?act=comm&writeAble="+writeAble, false);
		}
		return mapping.findForward("success");
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	
	private ActionForward saveApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据保存
		DAO dao = DAO.getInstance();
		ActionForward newFwd = new ActionForward();
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		pkValue += StringUtils.isNotNull(request.getParameter("bz")) ? request.getParameter("bz") : "";
		String oldpk = request.getParameter("pkV");
		String tmpPk = DealString.toGBK(request.getParameter("oldjxjdm"));
		String tab = request.getParameter("tab");
		tab = StringUtils.isNull(tab) ? "" :tab;
		String tab1 = request.getParameter("tab1");
		tab1 = StringUtils.isNull(tab1) ? "" : tab1;
		String sql = "";
		String xxdm = StandardOperation.getXxdm();
		
		String stab = request.getParameter("stab");
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG) || xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//浙江理工 武汉理工大学
			request.setAttribute("gwcxview","1");
		}
		// 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
		
		//System.out.println(pkValue);
		//判断此学生是否申请过对外交流项目
		//String[] sfsqxm = dao.getOneRs("select ", new String[]{}, new String[]{});

		if (tab.equalsIgnoreCase("dwjl")) {
			// 对外交流
			String bz = DealString.toGBK(request.getParameter("sqly"));
			String tcah = DealString.toGBK(request.getParameter("tcah"));
			String shgzqk = DealString.toGBK(request.getParameter("shgzqk"));
			String bjdypm = DealString.toASCII(request.getParameter("bjdypm"));
			String njzypm = DealString.toGBK(request.getParameter("njzypm"));
			dataSearchForm.setSqly(bz);
			boolean del = false;			
			del = StandardOperation.delete("dwjlsqb", "xn||nd||xq||jlxmdm||xh", pkValue+xh, request);
			if (del) {
				sql = "insert into dwjlsqb(xn,nd,xq,jlxmdm,sqly,xh,tcah,shgzqk,bjdypm,njzypm) select xn,nd,xq,jlxmdm,?,?,?,?,?,? from dwjlxxb where xn||nd||xq||jlxmdm=?";
				dao.runUpdate(sql, new String[] { bz, xh, tcah, shgzqk, bjdypm, njzypm, pkValue });
				String[] oldVals =dao.getOneRs("select xn,nd,xq,jlxmdm,sqly,xh from dwjlsqb where xh||nd||xq||jlxmdm=?", new String[] {pkValue}, new String[] {"xn","nd","xq","jlxmdm","sqly","xh"});
				dao.writeLog(sql, new String[] { bz, xh, tcah, shgzqk, bjdypm, njzypm, pkValue }, oldVals, "增加记录:", request);
			} else {
				request.setAttribute("errMsg", "操作失败!");	
				dataSearchForm.setErrMsg("操作失败!");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/comm_apply.do", false);
		} else if (tab.equalsIgnoreCase("dwjljxj")) {
			// 对外交流奖学金
			pkValue = request.getParameter("pkValue");
			boolean del = StandardOperation.delete("dwjljxjsqb", "xn||nd||xq||jlxmdm||xh", pkValue+xh, request);
			String sqje = request.getParameter("sqje");
			String hjqk = DealString.toGBK(request.getParameter("hjqk"));
			String tc = DealString.toGBK(request.getParameter("tc"));
			String sqly = DealString.toGBK(request.getParameter("sqly"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String jlbx = DealString.toGBK(request.getParameter("jlbx"));
			if (del) {
				sql = "insert into dwjljxjsqb(XH,JLXMDM,BXMSXJE,SQJE,HJQK,YHTC,"
					+ "YQSFYG,SQSY,XYSH,XYPZJE,XXSH,ZZPZJE,SQSJ,JXJFFSJ,BZ,XN,ND,XQ,jlbx)"
					+ "select ?,jlxmdm,jxjxe,?,?,?,'',?,'未审核',0,'未审核',"
					+ "0,to_char(sysdate,'yyyymmddhh24miss'),'',?,xn,nd,xq,? from dwjlxxb"
					+ " where xn||nd||xq||jlxmdm=?";
				dao.runUpdate(sql, new String[] { xh, sqje, hjqk, tc, sqly, bz,jlbx,pkValue });
				dao.writeLog(sql, new String[] { xh, sqje, hjqk, tc, sqly, bz,jlbx ,pkValue }, new String[] {},"增加记录：",request);
				
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/comm_money_apply.do", false);
		} else if (tab.equalsIgnoreCase("jxj")) {
			// 奖学金
			sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"jxjsqxn", "jxjsqnd" ,"jxjsqxq"});
			String xn = tmp[0];
			String nd = tmp[1];			
			String xq = tmp[2];
			
			/*if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				String jxjlb = DealString.toGBK(request.getParameter("xmdm"));
				String jxjdm = request.getParameter("jxjdm");
				jxjlb = StringUtils.isNull(jxjlb) ? jxjdm : jxjlb;
				sql = "select jxjdm from jxjdmb where jxjmc='社会奖学金'";
				String jxjdmTemp = dao.getOneRs(sql, new String[]{}, new String[]{"jxjdm"})[0];
				if (StringUtils.isEqual(jxjdmTemp, jxjlb)) {
					request.setAttribute("inserted", "shjxj");
					return new ActionForward("/prise_apply.do?tab="+tab1, false);
				}
			}*/
			HashMap<String, String> map = new HashMap<String, String>();
			String  [] sqqr = null;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
					||Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)) {
				sql = "select '1' sfcz from xsjxjb where jxjdm=? and xh=? and xn=? and xq=? and xysh != '未审核'";
			    sqqr= dao.getArray(sql, new String[] { pkValue, xh, xn ,xq}, "sfcz");
			}else{
				sql = "select '1' sfcz from xsjxjb where jxjdm=? and xh=? and xn=?  and xysh != '未审核'";
			    sqqr= dao.getArray(sql, new String[] { pkValue, xh, xn }, "sfcz");
			}
		    
		    if(sqqr!=null&&sqqr.length!=0){
		    	request.setAttribute("inserted", "cfsq");
		    } else {
				// 宁波理工  lyl
//				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
//					sql = "select count(*) num from cjb where xh=? and xn=? and (bkcj is not null or cxcj is not null or cxbj='1') ";
//					String sBkjg = dao.getOneRs(sql, new String[] { xh, xn },
//							"num");// 是否有补考课程
//					sBkjg = !StringUtils.isNull(sBkjg) ? sBkjg : "";
//					sql = "select count(*) num from view_wjcf where xh=? and xn=?";
//					String sWjjg = dao.getOneRs(sql, new String[] { xh, xn },
//							"num");// 是否有违纪处分
//					sWjjg = !StringUtils.isNull(sWjjg) ? sWjjg : "";
//					// 该生必修课存在重修，补考,违纪处分
//					if (!StringUtils.isEqual(sBkjg, "0")
//							|| !StringUtils.isEqual(sWjjg, "0")) {
//						request.setAttribute("inserted", "bhg");
//						return new ActionForward("/prise_apply.do?tab=" + tab1
//								+ "&inserted=bhg", false);
//					}
//				}
				String jxjdm = request.getParameter("xmdm");
				boolean del = false;
				if (	//江苏信息不要学期
						//xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
						//||
						Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)) {
					sql = "delete from xsjxjb where jxjdm=? and xh=? and xn=? and xq=?";
					del = dao.runUpdate(sql,
							new String[] { pkValue, xh, xn, xq });
				} else {
					sql = "delete from xsjxjb where jxjdm=? and xh=? and xn=?";
					// String oldjxjdm = request.getParameter("oldjxjdm");
					// jxjdm = StringUtils.isNull(jxjdm) ? tmpPk : jxjdm;
					/*
					 * if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					 * //特，一，二，三等只能申请一次 if (StringUtils.isEqual(jxjdm, "024") ||
					 * StringUtils.isEqual(jxjdm, "025") ||
					 * StringUtils.isEqual(jxjdm, "026") ||
					 * StringUtils.isEqual(jxjdm, "027")) { String num =
					 * dao.getOneRs("select count(*) num from xsjxjb where xh=?
					 * and xn=? and nd=? and jxjdm in
					 * ('024','025','026','027')", new String[]{xh, xn, nd},
					 * "num"); int iNum = StringUtils.isNull(num) ? 0 :
					 * Integer.parseInt(num.trim()); if (iNum>0) {
					 * request.setAttribute("inserted", "sqjg"); return new
					 * ActionForward("/prise_apply.do?tab="+tab1+"&stab="+stab+"&jxjlb1=" +
					 * request.getParameter("jxjdmlb"), false); } }
					 * 
					 * if (StringUtils.isEqual(jxjdm, "028") ||
					 * StringUtils.isEqual(jxjdm, "029") ||
					 * StringUtils.isEqual(jxjdm, "030") ||
					 * StringUtils.isEqual(jxjdm, "031")) { //单项奖只能申请一次 String
					 * nums = dao.getOneRs("select count(*) num from xsjxjb
					 * where xh=? and xn=? and nd=? and jxjdm in
					 * ('028','029','030','031')", new String[]{xh, xn, nd},
					 * "num"); int iNums = StringUtils.isNull(nums) ? 0 :
					 * Integer.parseInt(nums.trim());
					 * 
					 * if ((iNums>0)) { request.setAttribute("inserted",
					 * "sqjg"); return new
					 * ActionForward("/prise_apply.do?tab="+tab1+"&stab="+stab+"&jxjlb1=" +
					 * request.getParameter("jxjdmlb"), false); } } }
					 */
					dao.runUpdate("delete from xsjxjb where jxjdm=? and xh=? and xn=?",
									new String[] { tmpPk, xh, xn });
					dao.runUpdate("delete from xsjxjb where xn||nd||xh||jxjdm=?",
							new String[] { oldpk });
					dao.runUpdate(
							"delete from xsjxjb where xn||nd||jxjdm||xh=?",
							new String[] { oldpk });
					del = dao.runUpdate(sql, new String[] { pkValue, xh, xn });
				}

				String drzw = DealString.toGBK(request.getParameter("drzw"));
				String kyxm = DealString.toGBK(request.getParameter("kycg"));
				String sqly = DealString.toGBK(request.getParameter("sqly"));
				String jxjlb = DealString.toGBK(request.getParameter("xmdm"));
				String shjxj = DealString.toGBK(request.getParameter("zdm"));
				
				//宁波天一职业技术学院
				String dykhdj = DealString.toGBK(request.getParameter("dykhdj"));
				String xstzjkbz = DealString.toGBK(request.getParameter("xstzjkbz"));
				
				String xxjl = Base
						.chgNull(request.getParameter("xxjl"), " ", 1);
				String jxj1 = Base
						.chgNull(request.getParameter("jxj1"), " ", 1);
				String shyg1 = Base.chgNull(request.getParameter("shyg1"), " ",
						1);
				String ddj11 = Base.chgNull(request.getParameter("ddj1"), " ",
						1);
				String bxkpjcj1 = Base.chgNull(
						request.getParameter("bxkpjcj1"), " ", 1);
				String bjcjpx1 = Base.chgNull(request.getParameter("bjcjpx1"),
						" ", 1);
				String zycjpx1 = Base.chgNull(request.getParameter("zycjpx1"),
						" ", 1);
				String tyhgbz1 = Base.chgNull(request.getParameter("tyhgbz1"),
						" ", 1);
				String jxj2 = Base
						.chgNull(request.getParameter("jxj2"), " ", 1);
				String shyg2 = Base.chgNull(request.getParameter("shyg2"), " ",
						1);
				String ddj12 = Base.chgNull(request.getParameter("ddj2"), " ",
						1);
				String bxkpjcj2 = Base.chgNull(
						request.getParameter("bxkpjcj2"), " ", 1);
				String bjcjpx2 = Base.chgNull(request.getParameter("bjcjpx2"),
						" ", 1);
				String zycjpx2 = Base.chgNull(request.getParameter("zycjpx2"),
						" ", 1);
				String tyhgbz2 = Base.chgNull(request.getParameter("tyhgbz2"),
						" ", 1);
				String jxj3 = Base
						.chgNull(request.getParameter("jxj3"), " ", 1);
				String shyg3 = Base.chgNull(request.getParameter("shyg3"), " ",
						1);
				String ddj13 = Base.chgNull(request.getParameter("ddj3"), " ",
						1);
				String bxkpjcj3 = Base.chgNull(
						request.getParameter("bxkpjcj3"), " ", 1);
				String bjcjpx3 = Base.chgNull(request.getParameter("bjcjpx3"),
						" ", 1);
				String zycjpx3 = Base.chgNull(request.getParameter("zycjpx3"),
						" ", 1);
				String tyhgbz3 = Base.chgNull(request.getParameter("tyhgbz3"),
						" ", 1);
				String jxj4 = Base
						.chgNull(request.getParameter("jxj4"), " ", 1);
				String shyg4 = Base.chgNull(request.getParameter("shyg4"), " ",
						1);
				String ddj14 = Base.chgNull(request.getParameter("ddj4"), " ",
						1);
				String bxkpjcj4 = Base.chgNull(
						request.getParameter("bxkpjcj4"), " ", 1);
				String bjcjpx4 = Base.chgNull(request.getParameter("bjcjpx4"),
						" ", 1);
				String zycjpx4 = Base.chgNull(request.getParameter("zycjpx4"),
						" ", 1);
				String tyhgbz4 = Base.chgNull(request.getParameter("tyhgbz4"),
						" ", 1);
				String sjhm = Base
						.chgNull(request.getParameter("sjhm"), " ", 1);
				String wysp = Base
						.chgNull(request.getParameter("wysp"), " ", 1);
				String jfqk = DealString.toGBK(request.getParameter("jfqk"));
				String cjmc = Base
						.chgNull(request.getParameter("cjmc"), " ", 1);
				String zhpfmc = Base.chgNull(request.getParameter("zhpfmc"),
						" ", 1);
				String xxpjcj = Base.chgNull(request.getParameter("pjcj"), " ",
						1);
				String ykt = Base.chgNull(request.getParameter("ykt"), " ", 1);
				String fkh = Base.chgNull(request.getParameter("fkh"), " ", 1);
				String kh = Base.chgNull(request.getParameter("kh"), " ", 1);

				jxjlb = StringUtils.isNull(jxjlb) ? jxjdm : jxjlb;
				
				map.put("sqly", sqly);
				map.put("kycg", kyxm);
				map.put("xxjl", xxjl);
				map.put("wysp", wysp);
				map.put("sjhm", sjhm);
				map.put("drzw", drzw);
				
				
				boolean result = false;
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {

						sql = "insert into xsjxjb(XH,JXJDM,XN,ND,SQSJ,"
								+ "SQLY,FFBJ,FFSJ,FFWJH,XQ,DRZW,JFQK,cjmc,pjcj,DNSHJXJ,XXJL,zhpfmc) values("
								+ "?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?,?,?,?,?,?,?, ?)";
						result = StandardOperation.insert("xsjxjb",
								new String[] { "XH", "JXJDM", "XN", "ND",
										"SQSJ", "SQLY", "FFBJ", "FFSJ",
										"FFWJH", "XQ", "DRZW", "JFQK", "cjmc",
										"pjcj", "DNSHJXJ", "XXJL", "zhpfmc" },
								new String[] { xh, jxjlb, xn, nd, "", sqly,
										"0", " ", " ", xq, drzw, jfqk, cjmc,
										xxpjcj, shjxj, xxjl, zhpfmc }, request);
						/*
						 * result = dao.runUpdate(sql, new String[] { xh, jxjlb,
						 * xn, nd, sqly, "0", " ", " ", xq, drzw,
						 * jfqk,cjmc,xxpjcj,shjxj,xxjl,zhpfmc });
						 */
						
						
					}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
						sql = "insert into xsjxjb(XH,JXJDM,XN,ND,SQSJ,"
							+ "SQLY,FFBJ,FFSJ,FFWJH,XQ,DRZW,KYCG,DNSHJXJ,XXJL,kh,ykt,fkh,dykhdj,xstzjkbz) values("
							+ "?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						result = dao.runUpdate(sql, new String[] { xh, jxjlb,
								xn, nd, sqly, "0", " ", " ", xq, drzw, kyxm,
								shjxj, xxjl, kh, ykt, fkh,dykhdj,xstzjkbz });
						
					}else {
						sql = "insert into xsjxjb(XH,JXJDM,XN,ND,SQSJ,"
								+ "SQLY,FFBJ,FFSJ,FFWJH,XQ,DRZW,KYCG,DNSHJXJ,XXJL,kh,ykt,fkh) values("
								+ "?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?,?,?,?,?,?,?,?)";
						result = dao.runUpdate(sql, new String[] { xh, jxjlb,
								xn, nd, sqly, "0", " ", " ", xq, drzw, kyxm,
								shjxj, xxjl, kh, ykt, fkh });
					}
					if (result) {
						request.setAttribute("inserted", "ok");
						if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
							updateJxjbz(xh, xn, jxjlb, "xsjxjb", "jxjdm");
						}
					} else {
						request.setAttribute("inserted", "no");
					}
					sql = "delete xsjxjxxb where xh=?";
					dao.runUpdate(sql, new String[] { xh });
					sql = "insert into xsjxjxxb(xh,jxj1,shyg1,ddj1,"
							+ "bxkpjcj1,bjcjpx1,zycjpx1,tyhgbz1,jxj2,shyg2,ddj2,bxkpjcj2,bjcjpx2,zycjpx2,tyhgbz2,jxj3,shyg3,ddj3,"
							+ "bxkpjcj3,bjcjpx3,zycjpx3,tyhgbz3,jxj4,"
							+ "shyg4,ddj4,bxkpjcj4,bjcjpx4,zycjpx4,"
							+ "tyhgbz4) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, jxj1, shyg1, ddj11,
							bxkpjcj1, bjcjpx1, zycjpx1, tyhgbz1, jxj2, shyg2,
							ddj12, bxkpjcj2, bjcjpx2, zycjpx2, tyhgbz2, jxj3,
							shyg3, ddj13, bxkpjcj3, bjcjpx3, zycjpx3, tyhgbz3,
							jxj4, shyg4, ddj14, bxkpjcj4, bjcjpx4, zycjpx4,
							tyhgbz4 });
					sql = "select count(*) num from xsfzxxb where xh=?";
					String tmptmp = dao.getOneRs(sql, new String[] { xh },
							new String[] { "num" })[0];
					sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
					if (tmptmp.equalsIgnoreCase("0")) {
						sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";
					}
					dao.runUpdate(sql, new String[] { sjhm, wysp, xh });

				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			}
		    
		    	request.setAttribute("map", map);
				request.setAttribute("stab", stab);
			newFwd = new ActionForward("/prise_apply.do?tab="+tab1+"&stab="+stab+"&jxjlb1=" + request.getParameter("jxjdmlb"), false);
		} else if (tab.equalsIgnoreCase("rych")) {
			// 荣誉称号
			String rychdm = DealString.toGBK(request.getParameter("xmdm"));
//			String ylrychdm = DealString.toGBK(request.getParameter("ylrychdm"));
			String jcqk = DealString.toGBK(request.getParameter("jcqk"));
			String byjyqx = DealString.toGBK(request.getParameter("byjyqx"));
			String mzpyqksm = DealString.toGBK(request.getParameter("mzpyqksm"));
			//String tmpPkvalue = request.getParameter("pkValue");
			String[] tmp =null;
			/**
		if(rychdm.equals("004")||rychdm.equals("005")){
	        sql = "select xz from view_xsjbxx where xh=?";
	        tmp = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"xz" });
	        String xz = tmp[0];
	        if(xz.equals("4")){
	        	sql = "select * from (select xh from xsjxjb where jxjdm in (0000000001,0000000002,0000000003) and xysh='已审核' and xxsh='已审核' group by xh having count(*)>2 intersect select xh from xsrychb where rychdm in (001,002,003) and xysh='已审核' and xxsh='已审核' group by xh having count(*)>2) where xh='"+xh+"'";
	        }else if(xz.equals("5")){
	        	sql = "select * from (select xh from xsjxjb where jxjdm in (0000000001,0000000002,0000000003) and xysh='已审核' and xxsh='已审核' group by xh having count(*)>3 intersect select xh from xsrychb where rychdm in (001,002,003) and xysh='已审核' and xxsh='已审核' group by xh having count(*)>3) where xh='"+xh+"'";
	        }
	        rs = dao.getRS(sql);
	        if(!rs.next()){
	        	request.setAttribute("inserted", "nocondi");
	        	newFwd = new ActionForward("/credit_apply.do", false);
	        	return newFwd;
	        }	        	
		}	*/
			sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
			tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"jxjsqxn", "jxjsqnd","jxjsqxq"});
			String xn = tmp != null ? tmp[0] : "";
			String nd = tmp != null ? tmp[1] : "";
			String xq = tmp != null ? tmp[2] : "";
			sql = "delete xsrychb where rychdm=? and xh=? and xn=?";
			String drzw = Base.chgNull(request.getParameter("drzw")," ",1);
			String tydbqk = Base.chgNull(request.getParameter("tydbqk")," ",1);
			String byzx = Base.chgNull(request.getParameter("byzx")," ",1);
			String jtdz = Base.chgNull(request.getParameter("jtdz")," ",1);
			String syd = Base.chgNull(request.getParameter("syd")," ",1);
			String brjl = Base.chgNull(request.getParameter("brjl")," ",1);
			String zysj = Base.chgNull(request.getParameter("zysj")," ",1);
			String hjqk = Base.chgNull(request.getParameter("hjqk")," ",1);

			String sjhm = Base.chgNull(request.getParameter("sjhm")," ",1);
			String wysp = Base.chgNull(request.getParameter("wysp")," ",1);
			String zzmm = DealString.toGBK(request.getParameter("zzmm"));
			
			String cjmc = Base.chgNull(request.getParameter("cjmc"), " ", 1);
			String zhpfmc = Base.chgNull(request.getParameter("zhpfmc"), " ", 1);
			String lxdh = Base.chgNull(request.getParameter("lxdh"), " ", 1);
			String jlqk =  Base.chgNull(request.getParameter("jlqk"), " ", 1);//井冈山大学奖励情况
			String pjcj = Base.chgNull(request.getParameter("xxpjcj"), " ", 1);
			String fdyyj = DealString.toGBK(request.getParameter("fdyyj"));
			String xyyj = DealString.toGBK(request.getParameter("xyyj"));
			String hkszd = DealString.toGBK(request.getParameter("hkszd"));
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				
				String sCjflag = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=?", new String[]{xh, xn,xq}, "num");
		    	
		    	if (!StringUtils.isNull(sCjflag) && StringUtils.isEqual(sCjflag, "0")) {//成绩表中是否有该生信息
		    		request.setAttribute("inserted", "nocj");
					return new ActionForward("/credit_apply.do?stab="+stab, false);
		    	}
				
				boolean bFlag = ZzsfPjpyAction.chkJxjsqFlags(xh, rychdm, "rych", tmp);
				if (!bFlag) {
					request.setAttribute("inserted", "nohg");
					return  new ActionForward("/credit_apply.do?stab="+stab, false);
				}
			}
			
			//dao.runUpdate("delete from xsrychb where rychdm=? and xh=? and xn=?", new String[]{ylrychdm, xh, xn});
			//dao.runUpdate("delete from xsrychb where xn||nd||rychdm||xh = ?", new String[]{oldpk});
			boolean del = true;// = dao.runUpdate(sql, new String[] { rychdm, xh, xn });  
			String edit = request.getParameter("edit");//修改标志
			String oldPk = request.getParameter("oldpk");//修改PK
			if (del) {
				sql = "insert into xsrychb(XH,XN,ND,RYCHDM,XQ,fdyyj,xyyj,byjyqx,jcqk,mzpyqksm,zysj) values(?,?,?,?,?,?,?,?,?,?,?)";
				boolean result = false;
				if ("yes".equalsIgnoreCase(edit)) {
					result = StandardOperation.update("xsrychb", new String[]{"XH","XN","ND","RYCHDM","XQ","fdyyj","xyyj","byjyqx","jcqk","mzpyqksm","zysj"}, new String[] { xh, xn, nd,
							rychdm, dao.getOneRs("select jxjsqxq from xtszb", new String[]{}, "jxjsqxq"),fdyyj,xyyj,byjyqx,jcqk,mzpyqksm,zysj },"xh||xn||xq||rychdm",oldPk, request);
				} else {
					result = StandardOperation.insert("xsrychb", new String[]{"XH","XN","ND","RYCHDM","XQ","fdyyj","xyyj","byjyqx","jcqk","mzpyqksm","zysj"}, new String[] { xh, xn, nd,
							rychdm, dao.getOneRs("select jxjsqxq from xtszb", new String[]{}, "jxjsqxq"),fdyyj,xyyj,byjyqx,jcqk,mzpyqksm,zysj }, request);
				}
				/*boolean result = dao.runUpdate(sql, new String[] { xh, xn, nd,
						rychdm, dao.getOneRs("select jxjsqxq from xtszb", new String[]{}, "jxjsqxq"),fdyyj,xyyj });*/
				if (result) {
					request.setAttribute("inserted", "ok");
					if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
						updateJxjbz(xh, xn, rychdm, "xsrychb", "rychdm");
					}
				} else {
					request.setAttribute("inserted", "no");
				}
				sql = "delete xsrychxxb where xh=?";
				dao.runUpdate(sql, new String[] { xh });
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
					zzmm = DealString.toGBK(request.getParameter("zzmmmc"));
					sql = "insert into xsrychxxb(xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk,zzmm,cjmc,pjcj,zhpfmc) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql,new String[]{xh,drzw,tydbqk,byzx,hkszd,syd,brjl,zysj,hjqk,zzmm,cjmc,pjcj,zhpfmc});
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {//井冈山大学
						sql = "insert into xsrychxxb(xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk,zzmm,cjmc,zhpfmc,jlqk) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql,new String[]{xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk,zzmm,cjmc,zhpfmc,jlqk});	
				} else {
					sql = "insert into xsrychxxb(xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk) values(?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql,new String[]{xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk});
				}
				sql = "select count(*) num from xsfzxxb where xh=?";
				String tmptmp = dao.getOneRs(sql,new String[]{xh},new String[]{"num"})[0];
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
					sql = "update xsfzxxb set sjhm=?,wysp=?,lxdh2=? where xh=?";
					if(tmptmp.equalsIgnoreCase("0")){
						sql = "insert into xsfzxxb(sjhm,wysp,xh,lxdh2) values(?,?,?,?)";		    
						dao.runUpdate(sql,new String[]{sjhm,wysp,xh,lxdh});
					} else {
						dao.runUpdate(sql,new String[]{sjhm,wysp,lxdh,xh});
					}
				} else{
					sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
					if(tmptmp.equalsIgnoreCase("0")){
						sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";		    
					}
					dao.runUpdate(sql,new String[]{sjhm,wysp,xh});
				}
			} else {
				dataSearchForm.setErrMsg("申请失败");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/credit_apply.do?stab="+stab+"&dm="+rychdm, false);
		} else if (tab.equalsIgnoreCase("kns")) {
			// 困难生申请
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				sql = "select dqxn,dqnd from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"dqxn", "dqnd" });
				String xn = tmp[0];
				String nd = tmp[1];
				boolean del = StandardOperation.delete("knssqb", "xh||xn", xh+xn, request);
				
				String sqyy = DealString.toGBK(request.getParameter("sqyy"));
				String bz = DealString.toGBK(request.getParameter("bz1"));
				String lxdh = request.getParameter("lxdh1");
				String jtfk = DealString.toGBK(request.getParameter("jtfk")
						.toString());
				String jtfkrs = DealString.toGBK(request.getParameter("jtfkrs"));
				String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr"));
				String jtrjsr = DealString.toGBK(request.getParameter("jtrjsr"));
				String jtsrly = DealString.toGBK(request.getParameter("jtsrly"));
				String yzbm = DealString.toGBK(request.getParameter("yzbm"));
				String jtzz = DealString.toGBK(request.getParameter("jtzz"));
				String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm"));
				String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm"));
				String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm"));
				String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm"));
				String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm"));
				String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl"));
				String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl"));
				String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl"));
				String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl"));
				String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl"));
				String jtcy1_gx = DealString.toGBK(request.getParameter("jtcy1_gx"));
				String jtcy2_gx = DealString.toGBK(request.getParameter("jtcy2_gx"));
				String jtcy3_gx = DealString.toGBK(request.getParameter("jtcy3_gx"));
				String jtcy4_gx = DealString.toGBK(request.getParameter("jtcy4_gx"));
				String jtcy5_gx = DealString.toGBK(request.getParameter("jtcy5_gx"));
				String jtcy1_ysr = DealString.toGBK(request.getParameter("jtcy1_ysr"));
				String jtcy2_ysr = DealString.toGBK(request.getParameter("jtcy2_ysr"));
				String jtcy3_ysr = DealString.toGBK(request.getParameter("jtcy3_ysr"));
				String jtcy4_ysr = DealString.toGBK(request.getParameter("jtcy4_ysr"));
				String jtcy5_ysr = DealString.toGBK(request.getParameter("jtcy5_ysr"));
				String jtcy1_gzdw = DealString.toGBK(request.getParameter("jtcy1_gzdw"));
				String jtcy2_gzdw = DealString.toGBK(request.getParameter("jtcy2_gzdw"));
				String jtcy3_gzdw = DealString.toGBK(request.getParameter("jtcy3_gzdw"));
				String jtcy4_gzdw = DealString.toGBK(request.getParameter("jtcy4_gzdw"));
				String jtcy5_gzdw = DealString.toGBK(request.getParameter("jtcy5_gzdw"));
				if (del) {
					String[] colName = new String[] { "XH", "XN", "ND", "XQ",
							"LXDH", "SQYY", "BZ", "jtfk", "jtfkrs", "jtyzsr",
							"jtrjsr", "jtsrly", "yzbm", "jtzz", "jtcy1_xm",
							"jtcy1_gx", "jtcy1_nl", "jtcy1_ysr", "jtcy1_gzdw",
							"jtcy2_xm", "jtcy2_gx", "jtcy2_nl", "jtcy2_ysr",
							"jtcy2_gzdw", "jtcy3_xm", "jtcy3_gx", "jtcy3_nl",
							"jtcy3_ysr", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_gx",
							"jtcy4_nl", "jtcy4_ysr", "jtcy4_gzdw", "jtcy5_xm",
							"jtcy5_gx", "jtcy5_nl", "jtcy5_ysr", "jtcy5_gzdw" };
					boolean result = StandardOperation.insert("knssqb",
							colName, new String[] { xh, xn, nd, " ", lxdh,
									sqyy, bz, jtfk, jtfkrs, jtyzsr, jtrjsr,
									jtsrly, yzbm, jtzz, jtcy1_xm, jtcy1_gx,
									jtcy1_nl, jtcy1_ysr, jtcy1_gzdw, jtcy2_xm,
									jtcy2_gx, jtcy2_nl, jtcy2_ysr, jtcy2_gzdw,
									jtcy3_xm, jtcy3_gx, jtcy3_nl, jtcy3_ysr,
									jtcy3_gzdw, jtcy4_xm, jtcy4_gx, jtcy4_nl,
									jtcy4_ysr, jtcy4_gzdw, jtcy5_xm, jtcy5_gx,
									jtcy5_nl, jtcy5_ysr, jtcy5_gzdw }, request);
					if (result) {
						request.setAttribute("inserted", "ok");
					} else {
						request.setAttribute("inserted", "no");
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				newFwd = new ActionForward("/needer_apply.do", false);
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)){
				sql = "select dqxn,dqnd from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"dqxn", "dqnd" });
				String xn = tmp[0];
				String nd = tmp[1];
				boolean del = StandardOperation.delete("knssqb", "xh||xn", xh+xn, request);
				
				String sqyy = DealString.toGBK(request.getParameter("sqyy"));
				String bz = DealString.toGBK(request.getParameter("bz1"));
				String lxdh = request.getParameter("lxdh1");
				String jtzz = DealString.toGBK(request.getParameter("jtzz"));
				String kncddm = DealString.toGBK(request.getParameter("kncddm"));
				String zmcldm = DealString.toGBK(request.getParameter("zmcldm"));
				String cjqgzugws = DealString.toGBK(request.getParameter("cjqgzugws"));
				if (del) {
					String[] colName = new String[] { "XH", "XN", "ND", "XQ",
							"LXDH", "SQYY", "BZ", "JTZZ", "KNCDDM", "ZMCLDM",
							"cjqgzugws" };
					boolean result = StandardOperation.insert("knssqb",
							colName,
							new String[] { xh, xn, nd, " ", lxdh, sqyy, bz,
									jtzz, kncddm, zmcldm, cjqgzugws }, request);
					if (result) {
						request.setAttribute("inserted", "ok");
					} else {
						request.setAttribute("inserted", "no");
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				newFwd = new ActionForward("/needer_apply.do", false);
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				sql = "select dqxn,dqnd,dqxq from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"dqxn", "dqnd","dqxq" });
				String xn = tmp[0];
				String nd = tmp[1];
				String xq = tmp[2];
				boolean del=false;
				del=StandardOperation.delete("knssqb", "xh||xn", xh+xn, request);
				String sqyy = DealString.toGBK(request.getParameter("sqyy"));
				String bz = DealString.toGBK(request.getParameter("bz1"));
				String jtrjsr = DealString.toGBK(request.getParameter("jtrjsr"));
				String lxdh = request.getParameter("lxdh1");
				String kncddm = request.getParameter("kncddm");
				if (del) {
					String[] colName = new String[] { "XH", "XN", "ND", "XQ",
							"LXDH", "SQYY", "BZ", "jtrjsr","kncddm" };
					boolean result = StandardOperation.insert("knssqb",
							colName, new String[] { xh, xn, nd, xq, lxdh, sqyy,
									bz, jtrjsr,kncddm }, request);
					if (result) {
						request.setAttribute("inserted", "ok");
					} else {
						request.setAttribute("inserted", "no");
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				newFwd = new ActionForward("/needer_apply.do", false);
			} else {
				sql = "select dqxn,dqnd,dqxq from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
						"dqxn", "dqnd","dqxq" });
				String xn = tmp[0];
				String nd = tmp[1];
				String xq = tmp[2];
				boolean del=false;
				if(Globals.XXDM_ZBDX.equalsIgnoreCase(xxdm))
				{
					del=StandardOperation.delete("knssqb", "xh", xh, request);
				}
				else
				{
					del=StandardOperation.delete("knssqb", "xh||xn", xh+xn, request);
				}
				String sqyy = DealString.toGBK(request.getParameter("sqyy"));
				String bz = DealString.toGBK(request.getParameter("bz1"));
				String lxdh = request.getParameter("lxdh1");
				if (del) {
					String[] colName = new String[] { "XH", "XN", "ND", "XQ",
							"LXDH", "SQYY", "BZ" };
					boolean result = StandardOperation.insert("knssqb",
							colName, new String[] { xh, xn, nd, xq, lxdh, sqyy,
									bz }, request);
					if (result) {
						request.setAttribute("inserted", "ok");
					} else {
						request.setAttribute("inserted", "no");
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				newFwd = new ActionForward("/needer_apply.do", false);
			}
		} else if (tab.equalsIgnoreCase("xxzx")) {
			// 学校贷学金
			sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"jxjsqxn", "jxjsqnd" });
			String xn = tmp[0];
			String nd = tmp[1];
			boolean del = true;
			String sqyy = DealString.toGBK(request.getParameter("sqyy"));
			String sqje = DealString.toGBK(request.getParameter("sqje"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			if (del) {
				sql = "insert into xxzxdksqb(dkls,XH,XN,ND,XQ,SQJE,BZ,SQYY) values(xxzxdksqb_sequence.nextval,?,?,?,?,?,?,?)";
				boolean result = dao.runUpdate(sql, new String[] { xh, xn, nd,
						" ", sqje, bz, sqyy });
				if (result) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/college_money_apply.do", false);
		} else if (tab.equalsIgnoreCase("gwsq")) {
			//TODO勤工助学申请
//			QgzxService service = new QgzxService();
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
				//江苏信息
				newFwd = new ActionForward("/qgzx_jsxx_gwsqSave.do", false);
				return newFwd;
			}
			// 岗位申请
			sql = "select xn,nd,xq from gwsqsjb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {"xn", "nd", "xq" });
			String xn = tmp[0];
			String nd = tmp[1];
			String xq = tmp[2];
			String knsCount = "1";
			if(request.getAttribute("gwcxview")!=null){
				boolean tb = dao.isKns(xh);
				if (!tb) {
					knsCount = "0";
				}
			}
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
				//浙江理工大学
				knsCount = "1";
			}
			String sfpks = "";
			if(dao.isKns(xh)){
				sfpks = "是";
			}else{
				sfpks = "否";
			}
			
			if(knsCount!=null&&knsCount.equals("1")){
				boolean del = true;
				String gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdm"));
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
				String xssq = DealString.toGBK(request.getParameter("xssq"));
				String lxdh = request.getParameter("lxdh");
				String kcjqgzxsj = DealString.toGBK(request.getParameter("kcjqgzxsj"));
				String kh = DealString.toGBK(request.getParameter("kh"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String gzjl = DealString.toGBK(request.getParameter("gzjl"));
				String sqdw = DealString.toGBK(request.getParameter("sqdw"));
//				String sfwh = request.getParameter("sfwh");
				String jtjjknqk = DealString.toGBK(request.getParameter("jtjjknqk"));
				String yhtc = DealString.toGBK(request.getParameter("yhtc"));
				String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm"));
				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
					//上海工程  重庆科技
					String[][] kxf = new String[7][7];
					for(int i=0; i<7; i++){
						for(int j=0; j<7; j++){
							kxf[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j+1))==null?"0":"1";
							}
						}
					if(kxf!=null){
//						StandardOperation.delete("xsqgzxsjb", "xh", xh, request);
						dao.runUpdate("delete from xsqgzxsjb where xh=?", new String[]{xh});
						for(int i=0; i<7; i++){
							for(int j=0; j<7; j++){
								dao.insert("insert into xsqgzxsjb(xh,xq,sj,kxbz)values(?,?,?,?)", new String[]{xh,String.valueOf(i+1),String.valueOf(j+1),kxf[i][j]}); 
								//StandardOperation.insert("xsqgzxsjb", new String[]{"xh","xq","sj","kxbz"}, new String[]{xh,String.valueOf(i+1),String.valueOf(j+1),kxf[i][j]}, request);
							}
						}
					}
				}
				
				boolean result = false;
				if (del) {	
					if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)){
						//上海工程 漳州师范
						result = StandardOperation.delete("xsgwxxb", "xh||gwdm", xh+gwdm, request);
						if(result){
							result = StandardOperation.insert("xsgwxxb", new String []{"xh","gwdm","xssq","bz","lxdh","xn","nd","xq","gwsbsj","kh","gzjl","sfpks"}, 
									new String[] { xh, gwdm,xssq, bz, lxdh, xn, nd, xq, gwsbsj,kh,gzjl,sfpks }, request);
						}
					}else{						
						if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
							//中北大学
							String sql_lrh ="select xh from xsgwxxb where xh=? and gwdm=? and gwsbsj=? ";
							String [] temp_lrh=dao.getOneRs(sql_lrh,new String[]{xh,gwdm,gwsbsj} ,new String[]{"xh"} );
							if(null==temp_lrh)
							{
								result = StandardOperation.insert("xsgwxxb", new String []{"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj"}, 
										new String[] { xh, gwdm,xssq, bz, kcjqgzxsj, lxdh, xn, nd, xq, gwsbsj }, request);
							
							}
						} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
							//北京联合
							pkValue=request.getParameter("pkValue");
							String[] gwPk=pkValue.split("!!#!!");//gwmc-sqdw;
							for(int i=1;i<gwPk.length;i++){		
								gwdm = DealString.toGBK(gwPk[i].substring(0, gwPk[i].indexOf("-")));
								sqdw = gwPk[i].substring(gwPk[i].indexOf("-") + 1, gwPk[i].length());
								
								gwsbsj = dao.getOneRs( "select gwsbsj from gwxxb where gwdm=? and sqdw=? order by gwsbsj desc",
												new String[] { gwdm, sqdw }, "gwsbsj");
								del = StandardOperation.delete("xsgwxxb", new String[]{"xh","gwdm","gwsbsj"}, new String[] { xh, gwdm, gwsbsj }, request);
								if(del){
									result = StandardOperation.insert("xsgwxxb", new String[]{"xh","gwdm","xssq","lxdh","xn","nd","xq","gwsbsj","sfpks"}, new String[] {xh, gwdm, xssq, lxdh, xn, nd, xq, gwsbsj,sfpks }, request);
								}
							}
						}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
							//云南艺术
							String jtcy = DealString.toGBK(dataSearchForm.getJtcy());
							String jtysr = DealString.toGBK(dataSearchForm.getJtysr());
							String pkdj = dataSearchForm.getPkdj();
							String zzqk = DealString.toGBK(dataSearchForm.getZzqk());
							String gjzxdk = DealString.toGBK(dataSearchForm.getGjzxdk());
							String xg = dataSearchForm.getXg();
							yhtc = DealString.toGBK(dataSearchForm.getYhtc());
							String ldyx = DealString.toGBK(dataSearchForm.getZzqk());
							
							del = StandardOperation.delete("xsgwxxb",
									"xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
							if(del){
								String[] input =  {"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","jtcy","jtysr","pkdj","yhtc","gjzxdk","zzqk","xg","gzjl","ldyx"};
								String[] value= {xh,gwdm,xssq,bz,kcjqgzxsj,lxdh,xn,nd,xq,gwsbsj,jtcy,jtysr,pkdj,yhtc,gjzxdk,zzqk,xg,gzjl,ldyx};
								result = StandardOperation.insert("xsgwxxb",input, value, request);
							}							
						}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
							//广东女子职业
							String ssbh = DealString.toGBK(request.getParameter("ssbh"));
							String jtnsr = DealString.toGBK(request.getParameter("jtnsr"));
							String bhgkm = DealString.toGBK(request.getParameter("bhgkm"));
							String wjcf = DealString.toGBK(request.getParameter("wjcf"));
							yhtc = DealString.toGBK(request.getParameter("yhtc"));
							del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
							if(del){
								String[] input =  {"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","yhtc","gzjl","ssbh","jtnsr","bhgkm","wjcf"};
								String[] value= {xh,gwdm,xssq,bz,kcjqgzxsj,lxdh,xn,nd,xq,gwsbsj,yhtc,gzjl,ssbh,jtnsr,bhgkm,wjcf};
								result = StandardOperation.insert("xsgwxxb",input, value, request);
							}							
						}else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
							//西北二民院
							del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
							if(del){
								result = StandardOperation.insert("xsgwxxb", new String[]{"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","sfpks"}, new String[] { xh,gwdm, xssq, bz, kcjqgzxsj, lxdh, xn,nd, xq, gwsbsj ,sfpks}, request);
								if(result){
									XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
									List filedList = xbDao.getFiledOfZddm("002");
									String[] filedInfo = new String[filedList.size()];
									String[] value = new String[filedInfo.length];
									for(int i=0;i<filedInfo.length; i++){
										HashMap<String, String> filed = (HashMap<String, String>) filedList.get(i);
										filedInfo[i] = filed.get("zddm");
										value[i] = DealString.toGBK(request.getParameter(filedInfo[i])); 
									}
									result = StandardOperation.update("xsgwxxb", filedInfo, value, "xh||gwdm||gwsbsj", xh+gwdm+gwsbsj, request);
								}
							}
						} else if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
							//井冈山大学
							//得到是否自动审核的标志位,"1"表示自动审核通过，“0”表示不通过
							String sfzdtg = request.getParameter("fuhexx");
							String sfdjsj = request.getParameter("djsj");							
							//【只要没有抛出异常就会返回true】
							del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
							if (del) {
								result = StandardOperation.insert("xsgwxxb", 
									new String[]{"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","sfpks","SFZDTG","sfdjsj"}, 
									new String[] { xh,gwdm, xssq, bz, kcjqgzxsj, lxdh, xn,nd, xq, gwsbsj ,sfpks,sfzdtg,sfdjsj}, request);
							}
						} else if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
								//长沙民政 liang
								String[] inputValue = pkValue.split("-");
								String csSql = "select * from gwxxb where GWDM=? and GWSBSJ=?";
								String[] results = dao.getOneRs(csSql, inputValue, new String[] {"SQDW"});
								String csYdwdm = results[0];
								del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
								if (del) {
								//如果删除
									result = StandardOperation.insert("xsgwxxb", 
										new String[]{"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","sfpks","YRDWDM"},
										new String[] { xh,gwdm, xssq, bz, kcjqgzxsj, lxdh, xn,nd, xq, gwsbsj ,sfpks,csYdwdm}, request);
								}
						}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
							//浙江机电
							QgzxDao qgzxDao = new QgzxDao();
							HashMap<String, String> sjMap = qgzxDao.getSqsjInfo();
							xn = sjMap.get("xn");
							nd = sjMap.get("nd");
							xq = sjMap.get("xq");
							if(qgzxDao.checkExists("xsgwxxb", "xh||xn||nd||xq||xxyj", xh+xn+nd+xq+"通过")){
								request.setAttribute("cannot", "已经有岗位审核通过，不能再申请岗位！");
								result = false;
							}else{							
								del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
								if (del) {
									result = StandardOperation.insert("xsgwxxb", new String[]{"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","sfpks","kh","jtjjknqk","yhtc"},
											new String[] { xh, gwdm, xssq, bz, kcjqgzxsj, lxdh, xn, nd, xq, gwsbsj ,sfpks, kh, jtjjknqk, yhtc}, request);
								}
							}
							
						}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)){
							//中国矿业大学
							QgzxZgdzdxService zgdzService = new QgzxZgdzdxService();
							if(zgdzService.isHmdMember(xh)){
								//判断是否已经列入黑名单
								request.setAttribute("isHmd", "yes");
							}else{
								del = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
								if (del) {
									result = StandardOperation.insert("xsgwxxb", new String[]{"xh","gwdm","xssq","bz","kcjqgzxsj","lxdh","xn","nd","xq","gwsbsj","sfpks","kh","jtjjknqk","yhtc"},
											new String[] { xh,gwdm, xssq, bz, kcjqgzxsj, lxdh, xn,nd, xq, gwsbsj ,sfpks,kh,jtjjknqk,yhtc}, request);
								}
							}
						} else {
							String sfdq = dataSearchForm.getSfdq();
							String sfgr = dataSearchForm.getSfgr();
							String sfdbh = dataSearchForm.getSfdbh();
							String sfyfdx = dataSearchForm.getSfyfdx();
							String yjshf = dataSearchForm.getYjshf();
							
							// ================begin 2009/7/17 luojw ===============
							String pk = "xh||gwdm||gwsbsj";
							String value = xh + gwdm + gwsbsj;							
							sql = "select xyyj,xxyj from xsgwxxb where " + pk + " = ?";
							HashMap<String, String> map = dao.getMap(sql, new String[] {value }, new String[] { "xyyj", "xxyj" });
							
							if(!"student".equalsIgnoreCase(userType)){
								if ((!"未审核".equalsIgnoreCase(map.get("xyyj")) && null != map.get("xyyj"))
										|| (!"未审核".equalsIgnoreCase(map.get("xxyj")) && null != map.get("xxyj"))) {
									result = StandardOperation.update("xsgwxxb", 
											          new String[] { "lxdh", "kcjqgzxsj", "xssq", "bz", "sfdq", "sfgr", "sfdbh", "sfyfdx", "yjshf" }, 
											          new String[] { lxdh, kcjqgzxsj, xssq, bz, sfdq, sfgr, sfdbh, sfyfdx,yjshf}, "xh||gwdm||gwsbsj", xh + gwdm + gwsbsj, request);
									if (result) {
										request.setAttribute("inserted", "okk");
									} else {
										request.setAttribute("inserted", "no");
									}
								} else {
									del = StandardOperation.delete("xsgwxxb", pk, value, request);
									if (del) {
										result = StandardOperation.insert(
												"xsgwxxb", 
												new String[] { "xh",
														"gwdm", "xssq", "bz",
														"kcjqgzxsj", "lxdh", "xn",
														"nd", "xq", "gwsbsj",
														"sfpks", "kh", "jtjjknqk",
														"yhtc", "zzmmdm", 
														"sfdq", "sfgr", "sfdbh", 
														"sfyfdx", "yjshf" },
												new String[] { xh, gwdm, xssq, bz,
														kcjqgzxsj, lxdh, xn, nd,
														xq, gwsbsj, sfpks, kh,
														jtjjknqk, yhtc, zzmmdm,
														sfdq, sfgr, sfdbh, 
														sfyfdx,yjshf},
												request);
									}
									if (result) {
										request.setAttribute("inserted", "ok");
									} else {
										request.setAttribute("inserted", "no");
									}
								}
							}else{
								del = StandardOperation.delete("xsgwxxb", pk, value, request);
								if (del) {
									result = StandardOperation.insert(
											"xsgwxxb", new String[] { "xh",
													"gwdm", "xssq", "bz",
													"kcjqgzxsj", "lxdh", "xn",
													"nd", "xq", "gwsbsj",
													"sfpks", "kh", "jtjjknqk",
													"yhtc", "zzmmdm","sfdq", 
													"sfgr", "sfdbh", 
													"sfyfdx", "yjshf" },
											new String[] { xh, gwdm, xssq, bz,
													kcjqgzxsj, lxdh, xn, nd,
													xq, gwsbsj, sfpks, kh,
													jtjjknqk, yhtc, zzmmdm,
													sfdq, sfgr, sfdbh, 
													sfyfdx,yjshf},
											request);
									if (result) {
										request.setAttribute("inserted", "ok");
									} else {
										request.setAttribute("inserted", "no");
									}
								}
							}
							//================end 2009/7/17 luojw ========================
							request.setAttribute("gwdmgwsbsj", gwdmgwsbsj);
					}						
					}
					
				}
				
			}else if(knsCount.equals("0")){
				request.setAttribute("inserted", "nopks");
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/post_stu_apply.do", false);
		} else if (tab.equalsIgnoreCase("zxdk")) {
			// 国家助学贷款信息
			String xn = request.getParameter("xn");
			String nd = request.getParameter("nd");
			String xq = request.getParameter("xq");
			String sqdkze = request.getParameter("sqdkze");
			String sqdkxf = request.getParameter("sqdkxf");
			String sqdkshf = request.getParameter("sqdkshf");
			String sqdkzsf = request.getParameter("sqdkzsf");
			String dqdwyb = request.getParameter("dqdwyb");
			String fqsfzh = request.getParameter("fqsfzh");
			String mqsfzh = request.getParameter("mqsfzh");
			String qysfzh = request.getParameter("qysfzh");
			String dkje1 = request.getParameter("dkje1");
			String dkje2 = request.getParameter("dkje2");
			String dkje3 = request.getParameter("dkje3");
			String yjfsbj = request.getParameter("yjfsbj");
			String ffdkze = request.getParameter("ffdkze");
			String ffdkxf = request.getParameter("ffdkxf");
			String ffdkshf = request.getParameter("ffdkshf");
			String ffdkzsf = request.getParameter("ffdkzsf");
			String sqly = DealString.toGBK(request.getParameter("sqly"));
			String scbyqx = DealString.toGBK(request.getParameter("scbyqx"));
			String yxlxfs = DealString.toGBK(request.getParameter("yxlxfs"));
			String dqgzdw = DealString.toGBK(request.getParameter("dqgzdw"));
			String dqdwdz = DealString.toGBK(request.getParameter("dqdwdz"));
			String dqdwlxfs = DealString.toGBK(request.getParameter("dqdwlxfs"));
			String fqxm = DealString.toGBK(request.getParameter("fqxm"));
			String fqgzdw = DealString.toGBK(request.getParameter("fqgzdw"));
			String fqlxfs = DealString.toGBK(request.getParameter("fqlxfs"));
			String mqxm = DealString.toGBK(request.getParameter("mqxm"));
			String mqgzdw = DealString.toGBK(request.getParameter("mqgzdw"));
			String mqlxfs = DealString.toGBK(request.getParameter("mqlxfs"));
			String qyxm = DealString.toGBK(request.getParameter("qyxm"));
			String qygzdw = DealString.toGBK(request.getParameter("qygzdw"));
			String qygx = DealString.toGBK(request.getParameter("qygx"));
			String qyzz = DealString.toGBK(request.getParameter("qyzz"));
			String qylxdh = DealString.toGBK(request.getParameter("qylxdh"));
			String hth1 = DealString.toGBK(request.getParameter("hth1"));
			String htjbjrjg1 = DealString.toGBK(request.getParameter("htjbjrjg1"));
			String fzjgmc1 = DealString.toGBK(request.getParameter("fzjgmc1"));
			String hth2 = DealString.toGBK(request.getParameter("hth2"));
			String htjbjrjg2 = DealString.toGBK(request.getParameter("htjbjrjg2"));
			String fzjgmc2 = DealString.toGBK(request.getParameter("fzjgmc2"));
			String hth3 = DealString.toGBK(request.getParameter("hth3"));
			String htjbjrjg3 = DealString.toGBK(request.getParameter("htjbjrjg3"));
			String fzjgmc3 = DealString.toGBK(request.getParameter("fzjgmc3"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String hknf1 = dao.dateToStr(request.getParameter("hknf1"));
			String hknf2 = dao.dateToStr(request.getParameter("hknf2"));
			String hknf3 = dao.dateToStr(request.getParameter("hknf3"));
			String ffsj = dao.dateToStr(request.getParameter("ffsj"));
			boolean del = true;
			if (del) {
				sql = "insert into gjzxdksqb(dkls,xn,nd,xq,xh,sqly,sqdkze,sqdkxf,sqdkshf,sqdkzsf,scbyqx,yxlxfs,dqgzdw,dqdwdz,dqdwyb,dqdwlxfs,fqxm,fqsfzh,fqgzdw,fqlxfs,mqxm,mqsfzh,mqgzdw,mqlxfs,qyxm,qysfzh,qygzdw,qygx,qyzz,qylxdh,hth1,htjbjrjg1,fzjgmc1,dkje1,hknf1,hth2,htjbjrjg2,fzjgmc2,dkje2,hknf2,hth3,htjbjrjg3,fzjgmc3,dkje3,hknf3,yjfsbj,bz,ffdkze,ffdkxf,ffdkshf,ffdkzsf,ffsj,xysh,xxsh ) values(gjzxdksqb_sequence.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				del = dao.runUpdate(sql, new String[] { xn, nd, xq, xh, sqly,
						sqdkze, sqdkxf, sqdkshf, sqdkzsf, scbyqx, yxlxfs,
						dqgzdw, dqdwdz, dqdwyb, dqdwlxfs, fqxm, fqsfzh, fqgzdw,
						fqlxfs, mqxm, mqsfzh, mqgzdw, mqlxfs, qyxm, qysfzh,
						qygzdw, qygx, qyzz, qylxdh, hth1, htjbjrjg1, fzjgmc1,
						dkje1, hknf1, hth2, htjbjrjg2, fzjgmc2, dkje2, hknf2,
						hth3, htjbjrjg3, fzjgmc3, dkje3, hknf3, yjfsbj, bz,
						ffdkze, ffdkxf, ffdkshf, ffdkzsf, ffsj, xysh, xxsh });
				if (del) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/nation_money_apply.do", false);

		} else if (tab.equalsIgnoreCase("bbsq")) {
			// 补办申请
			String xn = request.getParameter("xn");
			String nd = request.getParameter("nd");
			String xq = request.getParameter("xq");
			boolean del = true;
			String bbxm = request.getParameter("bbxm");
			String bbyy = DealString.toGBK(request.getParameter("bbyy"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			
			if (del) {
				boolean result = false;
				if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
					//重庆科技学院[需要加'火车站名']
					String hczm = DealString.toGBK(request.getParameter("hczm"));//火车站名
					String sfzh = DealString.toGBK(request.getParameter("sfzh"));//身份证号
					String syd = DealString.toGBK(request.getParameter("syd"));//生源地
					String csrq = DealString.toGBK(request.getParameter("csrq"));//出生日期
					String xz = DealString.toGBK(request.getParameter("xz"));//学制
					
					sql = "insert into " + bbxm
					+ "(xh,nd,xn,xq,bz,bbyy,hczm,sfzh,syd,csrq,xz) values(?,?,?,?,?,?,?,?,?,?,?)";
					result = dao.runUpdate(sql, new String[] { xh, nd, xn,
							xq, bz, bbyy, hczm, sfzh, syd, csrq,xz });
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_FJGCXY)){
					String fdyxm = DealString.toGBK(request.getParameter("fdyxm"));
					
					sql = "insert into " + bbxm
					+ "(xh,nd,xn,xq,bz,bbyy,fdyxm) values(?,?,?,?,?,?,?)";
					result = dao.runUpdate(sql, new String[] { xh, nd, xn,
							xq, bz, bbyy,fdyxm });
				}else{
					sql = "insert into " + bbxm
					+ "(xh,nd,xn,xq,bz,bbyy) values(?,?,?,?,?,?)";
					result = dao.runUpdate(sql, new String[] { xh, nd, xn,
							xq, bz, bbyy });
				}	
				if (result) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/putAgain_apply.do?bbxm="+bbxm, false);
		} else if (tab.equalsIgnoreCase("ticketBook")) {
//			车票预定
			String xn = request.getParameter("xn");
			String nd = request.getParameter("nd");
			String xq = request.getParameter("xq");
			boolean del = true;
			String cc = request.getParameter("cc");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String dz = request.getParameter("dz");
			String ccrq = request.getParameter("ccrq");
			String bxcc = request.getParameter("bxcc");
			String ksy = request.getParameter("ksy");
			String klc = request.getParameter("klc");
			String kwz = request.getParameter("kwz");
			String qt = request.getParameter("qt");
			if (del) {
				String dph=dao.getOneRs("select sequence_cpyd.nextval dph from dual", new String[]{}, "dph");			
				sql="delete from cpydb where xh||xn||nd||xq=?";
				dao.runUpdate(sql, new String[]{xh.trim()+xn.trim()+nd.trim()+xq.trim()});
				sql = "insert into cpydb(dph,xh,xn,nd,xq,cc,bz,dz,ccrq,bxcc,ksy,klc,kwz,qt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				boolean result = dao.runUpdate(sql, new String[] {dph, xh, xn, nd,
						xq, cc, bz, dz, ccrq, bxcc, ksy, klc, kwz, qt });				
				//sql = "select sequence_cpyd.currval num from dual";
//				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
//				"num" });
				String num = dph;//tmp[0];				
				request.setAttribute("num", num);
				//sql = "update hccpydkb set dph=? where xh=? and cc=? zdz=?";
				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
					dz = DealString.toGBK(dz);
					result = StandardOperation.update("hccpydkb", new String[]{"dph"},new String[]{dph}, "xh||cc||zdz", xh.trim()+cc.trim()+dz.trim(), request);
				}
				
				if (result) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			newFwd = new ActionForward("/ticket_book.do", false);
		} else if (tab.equalsIgnoreCase("hdzxsqb")){
			String bmdm = dataSearchForm.getBmdm();
			String sqr = DealString.toGBK(request.getParameter("sqr"));
			String syrq = DealString.toGBK(request.getParameter("syrq"));
			String hdkssj = DealString.toGBK(request.getParameter("hdkssj"));
			String hdjssj = DealString.toGBK(request.getParameter("hdjssj"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String ykkssj = DealString.toGBK(request.getParameter("ykkssj"));
			String ykjssj = DealString.toGBK(request.getParameter("ykjssj"));
			String hdzt = DealString.toGBK(request.getParameter("hdzt"));
			String ykxx = request.getParameter("ykxx");
//			String yksxm = DealString.toGBK(request.getParameter("yksxm"));
//			String yksdh = DealString.toGBK(request.getParameter("yksdh"));
            String xxqdm = DealString.toGBK(request.getParameter("xxqdm"));
            String dmtxx = DealString.toGBK(request.getParameter("dmtxx"));


			if(ykxx==null||ykxx.equalsIgnoreCase("")){
				ykxx="不需要";
			} else {
				ykxx="需要";
			}
			if(dmtxx==null||dmtxx.equalsIgnoreCase("")){
				dmtxx = "不需要";
			} else{
				dmtxx = "需要";
			}

			String sykssj = syrq + hdkssj;
			String syjssj = syrq + hdjssj;

			sql = "select h.syrq||h.hdkssj sykssj,h.syrq||h.hdjssj syjssj " +
			"from hdzxsqb h where h.syrq =? ";
			List sjList = dao.getList(sql, new String[]{ syrq }, new String[]{"sykssj","syjssj"});


			boolean ok = true;
			boolean result = false;
			int size = sjList.size();
			for(int i=0;i<size;i++){
				HashMap map = (HashMap)sjList.get(i);
				String ks = map.get("sykssj").toString();
				String js = map.get("syjssj").toString();
				if(ks.compareToIgnoreCase(syjssj)<0 && js.compareToIgnoreCase(sykssj)>0){
					ok = false;break;
				}
			}

			sql = "select to_char(sysdate,'yyyymmddhh24miss') rithtNow from dual";
			String rightNow = dao.getOneRs(sql, new String[]{}, new String[]{"rithtNow"})[0];

			if(ok){			
				sql = "insert into HDZXSQB(bmdm,sqr,lxdh,syrq,ykxx,djsj,hdzt,ykkssj,ykjssj,hdkssj,hdjssj,xxqdm,dmtxx) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String[] input = new String[]{bmdm,sqr,lxdh,syrq,ykxx,rightNow,hdzt,ykkssj,ykjssj,hdkssj,hdjssj,xxqdm,dmtxx};
				result = dao.runUpdate(sql, input); 
				if (result) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				request.setAttribute("inserted", "exist");
			}

			sql = "select * from view_hdzxxx ";
			String[] outString = dao.getColumnName(sql+" where 1=2 ");
			String[] outValue = dao.getOneRs(sql+" where bmdm=? and sqr=? and djsj=?", 
					new String[]{ bmdm,sqr,rightNow }, outString);
			HashMap<String, String> map = new HashMap<String, String>();
			if (outValue == null ){	
				for (int i=0 ; i < outString.length ; i++){
					map.put(outString[i].toLowerCase(), "");	    		
				}
			} else {
				for (int i=0 ; i < outString.length ; i++){
					if (outValue[i] != null){
						map.put(outString[i].toLowerCase(), outValue[i]);
					}else {
						map.put(outString[i].toLowerCase(), "");
					}
				}
			}
			request.setAttribute("rs", map);		

			sql = "select b.bmdm,b.bmmc from ZXBZ_XXBMDM b";
			List bmList = dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
			request.setAttribute("bmList", bmList);
			List xxqList = dao.getList("select dm,xqmc from dm_zju_xq", new String[]{}, new String[]{"dm","xqmc"});
			request.setAttribute("xxqList", xxqList);//发送校区数据列表

			newFwd = new ActionForward("/play_apply.do",false);
		} else if (tab.equalsIgnoreCase("xstb")) {
			//新生投保
			boolean del = true;
			String tbgsdm = request.getParameter("tbgsdm");
			String tbxzdm = request.getParameter("tbxzdm");
			String bxnx = request.getParameter("bxqx");
			String bxq = bxnx;
			if(!isNull(bxnx))
				bxnx = bxnx + "年";
			String bxje = request.getParameter("bxje");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String bxdc = DealString.toGBK(request.getParameter("bxdc"));
			sql = "select dqxn,dqnd from xtszb where rownum=1";
			String tmp[] = dao.getOneRs(sql, new String[] {}, new String[] {"dqxn", "dqnd" });
			String xn = tmp[0];
			String nd = tmp[1];
			String sqsj = GetTime.getNowTime2();
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				//上海工程
				String rxN = "";
				String rxY = "";
				String dqny = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),0,10) str from dual", new String[]{}, "str");
				String dqN = dqny.substring(0,4);
				String dqY = dqny.substring(5,7);
				String rxny = dao.getOneRs("select rxrq from view_xsxxb where xh=?", new String[]{xh}, "rxrq");
				if(rxny!=null && rxny.length()==8){
					rxN = rxny.substring(0,4);
					rxY = rxny.substring(4,6);
				}else if(rxny!=null && rxny.length()==10){
					rxN = rxny.substring(0,4);
					rxY = rxny.substring(4,6);
				}else{
					dataSearchForm.setErrMsg("入学年月数据错误！");
					request.setAttribute("errMsg", "入学年月数据错误,无法判断你能否申请保险！");
					return mapping.findForward("false");
				}
				int n = Integer.parseInt(dqN)-Integer.parseInt(rxN);
				int y = Integer.parseInt(dqY)-Integer.parseInt(rxY);
				if(n>=2){
					dataSearchForm.setErrMsg("对不起,只能在一年级申请，你已经超过了申请期限！");
					request.setAttribute("errMsg", "对不起,只能在一年级申请，你已经超过了申请期限！");
					return mapping.findForward("false");
				}else{
					if(y>=0){
						n =  n+1;
					}
//					//本年度入学
//					if(n==0 && y>=0){
//						n = 1;
//					}
					if(n!=1){
						dataSearchForm.setErrMsg("对不起,只能在一年级申请，你已经超过了申请期限！");
						request.setAttribute("errMsg", "对不起,只能在一年级申请，你已经超过了申请期限！");
						return mapping.findForward("false");
					}
				}
				
				boolean flag = false;
				StandardOperation.delete("xsbxb", "xh||nd", xh.trim()+nd.trim(), request);
				String[] bxxz = dao.getArray("select distinct bxxzdm from bxxzb", new String[]{}, "bxxzdm");
				for(int i=0; i<bxxz.length; i++){
					bxje = String.valueOf(Integer.parseInt(dao.getOneRs("select bxje from bxxzb where bxxzdm=?", new String[]{bxxz[i]}, "bxje"))*Integer.parseInt(bxq));
					flag = StandardOperation.insert("xsbxb", new String[] {"xh","tbgsdm","tbxzdm","bxnx","bf","xn","nd","bz","sqsj","xxsh","bxdc"}, new String[] { xh, tbgsdm, bxxz[i], bxnx, bxje, xn, nd, bz ,sqsj,"未审核",bxdc}, request);
					if(flag==false){						
						break;
					}
				}				
				request.setAttribute("inserted", (flag == false )? "no" : "ok");
				
			}else{
				StandardOperation.delete("xsbxb", "xh||tbgsdm||tbxzdm||nd", xh.trim()+tbgsdm.trim()+tbxzdm.trim()+nd.trim(), request);
				if (del) {
					boolean result = StandardOperation.insert("xsbxb", new String[] {"xh","tbgsdm","tbxzdm","bxnx","bf","xn","nd","bz","sqsj","tbsj","xxsh","bxdc"}, new String[] { xh, tbgsdm, tbxzdm, bxnx, bxje, xn, nd, bz ,sqsj,sqsj,"未审核",bxdc}, request);
					if (result) {
						request.setAttribute("inserted", "ok");
					} else {
						request.setAttribute("inserted", "no");
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			}
			newFwd = new ActionForward("/newStuInsureApply.do", false);
		} else if (tab.equalsIgnoreCase("wjcf")) {
			//违纪处分
//			boolean del = true;
			String cflb = request.getParameter("cflb");
			String cfyy = request.getParameter("cfyy");
			String bz = request.getParameter("bz");
			String wjsj=DealString.toGBK(request.getParameter("wjsj"));
			String cfyj = DealString.toGBK(request.getParameter("cfyj"));
			String kf = DealString.toGBK(request.getParameter("kf"));
			String xyclyj = request.getParameter("xyclyj");
			String lswjjl = request.getParameter("lswjjl");
			String xsqr = request.getParameter("xsqr");
			sql = "select dqxn,dqnd,dqxq from xtszb where rownum=1";
			String tmp[] = dao.getOneRs(sql, new String[] {}, new String[] {
					"dqxn", "dqnd","dqxq" });
			String xn = tmp[0];
			String nd = tmp[1];
			String xq = tmp[2];
			boolean result = false;
			//福建工程 申报人
			String sbr="";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_FJGCXY)){
				String sbrSql = "select xm from yhb where yhm=? ";
				String userName=session.getAttribute("userName").toString();
				HashMap<String,String>hashMap=dao.getMap(sbrSql, new String[]{userName}, new String[]{"xm"});
				sbr=hashMap.get("xm");
			}
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				sql = "insert into wjcfsbb (xh,xn,nd,bz,xq,cflb,cfyy,cfsj,cfwh,sbsj,xxsh,wjsj,xdwsh,xzsh) values (?,?,?,?,?,?,?,(select to_char(sysdate,'yyyymmdd') from dual),(select to_char(sysdate,'yyyymmddhh24miss') from dual),(select to_char(sysdate,'yyyymmddhh24miss') from dual),'未审核',?,'未审核','未审核')";
				result = dao.runUpdate(sql,new String[]{xh,xn,nd,bz,xq,cflb,cfyy,wjsj});
				//result = StandardOperation.insert("wjcfsbb", new String[]{"xh", "xn", "nd", "bz", "xq", "cflb", "cfyy", "cfsj", "cfwh", "sbsj", "xxsh", "wjsj", "xdwsh", "xzsh"}, new String[]{xh,xn,nd,bz,xq,cflb,cfyy,"(select to_char(sysdate,'yyyymmdd') from dual)","(select to_char(sysdate,'yyyymmddhh24miss') from dual)","(select to_char(sysdate,'yyyymmddhh24miss') from dual)","未审核",wjsj,"未审核","未审核"}, request);
			}else{
				sql = "insert into wjcfb(xh,xn,nd,bz,xq,cflb,cfyy,sbsj,xxsh,cfyj,fjsclj,kf,wjsj,sbr,sfgzxs) values(?,?,?,?,?,?,?,(select to_char(sysdate,'yyyymmddhh24miss') from dual),'未审核',?,?,?,?,?,?)";
				//result = dao.runUpdate(sql, new String[] { xh, xn, nd, bz,xq, cflb, cfyy });
				String filePath = null;
	    		String dir = request.getRealPath("/") + "upload";
	    	    File f = new File(dir);
	    	    if (!f.exists()) {
	    		   f.mkdir();
	    	    }
	    	    String message = "";
	    	    String dateStr = "";
	    	    Timestamp date = new Timestamp(System.currentTimeMillis());
	    	    dateStr += date.toString().substring(0, 19);
	    	    dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
	    	    FormFile file = dataSearchForm.getUploadFile();
	    	    if(file==null){
	    	    	//广州大学没有附件上传
	    	    	if (!Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
						message = "附件上传失败！";
						request.setAttribute("message", message);
						request.setAttribute("inserted", "no");
						return mapping.findForward("false");
					}
	    	   } else {
	    		   int size = file.getFileSize();
	    		   if(size>0){//有文件上传
	    			   String fName = dateStr+file.getFileName();
	    			   InputStream in = file.getInputStream();
	    			   filePath = dir + "/" + fName;
	    			   OutputStream out = new FileOutputStream(filePath);
	    			   int bytesRead = 0;
	    			   byte[] buffer = new byte[size];
	    			   while ((bytesRead = in.read(buffer, 0, size)) != -1) {
	    				   out.write(buffer, 0, bytesRead);
	    			   }
	    			   out.close();
	    			   in.close();
	    		   }
	    	    }
	    	    String xysh = "";
	    	    if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
		    	    String num = dao.getOneRs("select count(*) num from (select (select cfjb from cflbdmb " +
		    	    		"where cflbmc like '%记过%') cfjb1,(select cfjb from cflbdmb where cflbdm=?) " +
		    	    		"cfjb2 from dual) where cfjb1<cfjb2", new String[]{cflb}, "num");
		    	    if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
		    	    	xysh="通过";
		    	    } else {
		    	    	xysh="未审核";
		    	    }
	    	    }
	    	    if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
	    	    	xysh = "未审核";
	    	    }
	    	    
				result = StandardOperation.insert("wjcfb", new String[] { "xh",
						"xn", "nd", "bz", "xq", "cflb", "cfyy", "xxsh","cfyj","fjsclj","kf","xyclyj" ,"xysh", "lswjjl", "wjsj","sbr","xsqr"},
						new String[] { xh, xn, nd, bz, xq, cflb, cfyy, "未审核", cfyj,filePath,kf,xyclyj,xysh, lswjjl, wjsj,sbr,xsqr},
						request);
			}
			
			if (result) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
			newFwd = new ActionForward("/stuPunishApp.do?xh="+xh+"&cfpk="+xh+cflb+cfyy+xn+nd, false);
		}
		else {
			dataSearchForm.setErrMsg("sdf");
			return mapping.findForward("false");
		}
		request.setAttribute("dataSaved", "ok");
		return newFwd;
	}
	
	/**
	 * 工作申请　
	 * */
	private ActionForward workerApplyAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		HttpSession session = request.getSession();
		QgzxService service = new QgzxService();
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();		
		String[] colList = null;
		String[] colListCN = null;	
		String xxdm = StandardOperation.getXxdm();
		Vector<Object> rs = new Vector<Object>();
		sql = "select xn,xq,nd from gwsqsjb where rownum=1";
		String userOnLine = session.getAttribute("userOnLine").toString();
		
		String []gwsqXnXqNd = dao.getOneRs(sql, new String[]{}, new String[]{"xn","xq","nd"});
		if (userOnLine.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();	
			sql = "select xh||gwdm||sqsj 主键,rownum 行号, a.* from view_xsgwxx a where xh=?";
			String[] input = { xh };
			//colList = new String[] {"主键", "行号", "nd", "xn", "xh", "xm", "xzb", "gwdm", "sqsj","lxdh", "xyyj","xxyj" };
			colList = new String[] {"主键", "行号", "nd", "xn", "xh", "xm", "xzb", "gwdm", "sqsj","lxdh", "xyyj","xxyj" };
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//云南艺术
				colList = new String[] { "主键","行号", "nd", "xn", "xh", "xm", "xzb",
						"gwdm", "sqsj","fdyyj","xxyj","xxshyj","kh","bh","gh" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//浙江机电职业技术学院
				colList = new String[]{ "主键","行号", "nd", "xn", "xh", "xm", "xzb",
						"gwdm", "sqsj","xxyj"};
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
				//北京联合
				HashMap<String, String> map = new HashMap<String, String>();
				sql = "select distinct xh,xm,xb,nj,xymc,zymc,bjmc,lxdh from view_xsgwxx where xh=?";
				map = dao.getMap(sql, new String []{xh}, new String []{"xh","xm","xb","nj","xymc","zymc","bjmc","lxdh"});
				map.put("xn", gwsqXnXqNd[0]);
				map.put("xq", gwsqXnXqNd[1]);
				map.put("nd", gwsqXnXqNd[2]);
				colList = new String[] {"主键", "行号", "所属单位", "所属部门", "岗位性质", "gwdm", "工作时间", "工作内容", "xyyj","xxyj" };
				request.setAttribute("map", map);
				request.setAttribute("showbjlh", "showbjlh");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
				//中国美术学院
				colList = new String[] {"主键", "行号", "nd", "xn", "xh", "xm", "xzb", "gwdm", "sqsj","fdyyj","xyyj","xxyj" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				// 中国地质大学
				// ============begin 骆嘉伟 2009/4/1 =========
				colList = new String[] {"主键", "行号", "nd", "xn", "xh", "xm", "xzb",
						"gwdm", "sqsj", "xxyj" };
				// ============end 骆嘉伟 2009/4/1 =========
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				//宁波理工学院
				// ============begin 骆嘉伟 2009/4/1 =========
				colList = new String[] {"主键", "行号", "nd", "xn", "xh", "xm", "xzb",
						"gwdm", "sqsj", "xyyj", "xxyj" };
				// ============end 骆嘉伟 2009/4/1 =========
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
				//北京联合
				sql = "select xh||gwdm||sqsj 主键,rownum 行号,a.xyyj,a.xxyj,a.gwdm,b.xymc 所属部门,b.gzsj 工作时间,b.gznr 工作内容,b.yrdwmc 所属单位,b.gwxzmc 岗位性质 from view_xsgwxx a ,view_gwxx b where a.xh=? and a.xn=? and a.xq=? and a.nd=? and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj";
				input = new String[] { xh, gwsqXnXqNd[0], gwsqXnXqNd[1], gwsqXnXqNd[2]};
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//浙江科技学院
				sql = "select xh||gwdm||sqsj 主键,rownum 行号, a.*,(select distinct gwdm from xsgwxxb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and b.xxyj='通过' and rownum=1)已录取岗位  from view_xsgwxx a where xh=?";
				colList = StringUtils.joinStrArr(colList, new String[]{"已录取岗位"}) ;
			}else if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(xxdm)){
				//浙江建设
				colList = new String[] {"主键", "行号", "nd", "xn", "xh", "xm", "xzb", "gwdm", "sqsj","lxdh","xxyj" };
			}
			colListCN = dao.getColumnNameCN(colList, "VIEW_XSGWXX");			
			List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				HashMap<String, String> tempTr = (HashMap<String, String>)topTr.get(8);
				tempTr.put("cn", Base.YXPZXY_KEY+"意见");
				topTr.set(8, tempTr);
			}
			
			//查询结果			
			rs.addAll(dao.rsToVator(sql, input, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
			request.setAttribute("realTable", "xsgwxxb");
			request.setAttribute("tableName", "view_xsgwxx");
			request.setAttribute("pk", "xh||gwdm||sqsj");// 发送数据源表主键
		} else if("teacher".equalsIgnoreCase(userOnLine))	{
			String userType1=session.getAttribute("userType").toString();
			String userDep=session.getAttribute("userDep").toString();
			//	================= begin 骆嘉伟 2009/6/13 ==================
			String userName = request.getSession().getAttribute("userName").toString();
	    	if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				String isFdy = session.getAttribute("isFdy").toString();// 辅导员
				if ("true".equalsIgnoreCase(isFdy)) {
					String[] outputSQL = new String[] { "zydm", "zymc" };
					sql="select distinct(b.zydm), b.zymc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm"+
					   " and a.zgh = ? order by b.zydm";
					List zyList = dao.getList(sql, new String[] { userName },
							outputSQL);
					request.setAttribute("zyList", zyList);
					outputSQL = new String[] { "bjdm", "bjmc" };
					sql = "select b.bjdm, b.bjmc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm"
							+ " and a.zgh = ? order by b.bjdm";
					List bjList = dao.getList(sql, new String[] { userName },
							outputSQL);
					request.setAttribute("bjList", bjList);
				}
			}
	    	//	================= end 骆嘉伟 2009/6/13 ==================
			if("xy".equalsIgnoreCase(userType1))
			{
				applyForm.setXydm(userDep);
				request.setAttribute("userType1", userType1);
			}
			else
			{
				request.setAttribute("userType1", userType1);
			}
			String doType=request.getParameter("doType");
			lrh_commen_util myutil=new lrh_commen_util();
			String realTable = "xsgwxxb";
			String pk = "xh||gwdm||sqsj";
			String tips = "勤工助学 - 岗位申请 - 申请结果查询";
			String tableName ="view_xsgwxx";
			String xydm=applyForm.getXydm();
			String zydm=applyForm.getZydm();
			String nj=applyForm.getNj();
			String bjdm=applyForm.getBjdm();
			String xn=applyForm.getXn();
			String xq=applyForm.getXq();
			xh=applyForm.getXh();
			//liang
//			String yrdwdm = applyForm.getYrdwdm();
			String xxmc=StandardOperation.getXxmc();
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
				//长沙民政
				tips = "学生义工 - 岗位申请 - 申请结果查询";
			}
			if("search".equalsIgnoreCase(doType)){
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){		
					//上海工程技术大学
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks", "xyyj","xxyj","kh" };
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
					//云南艺术
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks", "fdyyj","xyyj","xxyj","xscyj","kh" };
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
					//浙江机电职业技术学院
					colList = new String[]{ "行号", "nd", "xn", "xh", "xm", "xzb",
							"gwdm", "sqsj","xxyj"};
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
					//宁波理工学院
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks", "fdyyj","xxyj" };
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){
					//浙江建设
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks","xxyj" };
				}else{
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks", "xyyj","xxyj" };
				}
				String [] colum={"xn","xq","xydm","zydm","bjdm","nj"};
				String [] colum_values={xn,xq,xydm,zydm,bjdm,nj};
				String [] like_com={"xh","xm"};
				String [] like_val={xh,applyForm.getXm()};

				String usersql="select "+pk+" 主键,a.* from ";
//				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
//					//上海工程
//					usersql = "select " + pk + " 主键, a.*, (select b.kh from view_stu_details b where a.xh=b.xh) kh from";
//				}
				String sql_lrh=myutil.select_sql_yuju3(usersql,tableName, colum, colum_values, like_com, like_val);
				
				rs.addAll(dao.rsToVator(sql_lrh, new String[] {}, colList));
				String rsNum=String.valueOf(rs.size());
				colListCN = dao.getColumnNameCN(colList, tableName);
				List topTr = dao.arrayToList(colList, colListCN);
				request.setAttribute("rs",rs);// 发送数据集
				request.setAttribute("topTr", topTr);// 发送表头
				request.setAttribute("rsNum", rsNum);// 发送记录数
			}else if("print".equalsIgnoreCase(doType)){
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks", "xyyj","xxyj","kh" };
				}
				else{
					colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
							"bjmc", "gwdm", "sqsj", "sfpks", "xyyj","xxyj" };
				}
				colListCN = dao.getColumnNameCN(colList, tableName);

				String [] colum={"xn","xq","xydm","zydm","bjdm","nj"};
				String [] colum_values={xn,xq,xydm,zydm,bjdm,nj};
				String [] like_com={"xh"};
				String [] like_val={xh};
				String usersql="select "+pk+" 主键,a.* from ";
				String sql_lrh=myutil.select_sql_yuju3(usersql,tableName, colum, colum_values, like_com, like_val);
				List li=dao.getListNotOut(sql_lrh, new String [] {});

				List topTr = dao.arrayToList(colList, colListCN);
				request.setAttribute("rs", li);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsNum",String.valueOf(li.size()));
			}
			if(service.isYrdwUser(userName) && StringUtils.isNull(applyForm.getYrdwdm())){
				//用人单位只能查询本用人单位的信息
				applyForm.setYrdwdm(service.getYrdwUser(userName));
			}
			
			HashMap<String, String> paramter = new HashMap<String, String>();
			paramter.put("userName", userName);
			paramter.put("xn", applyForm.getXn());
			paramter.put("nd", applyForm.getNd());
			paramter.put("xq", applyForm.getXq());
			paramter.put("yrdwdm", applyForm.getYrdwdm());
			paramter.put("gwxzdm", applyForm.getGwxz());
			
			
			request.setAttribute("gwList",service.getGwmcxxList(paramter,"yes"));//所有审核通过岗位列表
			request.setAttribute("yrdwList", service.getYrdwList(userName));//用人单位列表信息	
			request.setAttribute("gwxzList", service.getGwxzList());//岗位性质列表
//			request.setAttribute("gwList", service.getGwmcList(userName));//获取岗位列表
			request.setAttribute("xxmc", xxmc);//取学校名称信息
			request.setAttribute("tableName", tableName);// 发送视图名
			request.setAttribute("realTable", realTable);// 发送数据源表名
			request.setAttribute("pk", pk);// 发送数据源表主键
			request.setAttribute("act", "work");// 发送数据查询类型
			request.setAttribute("tips", tips);// 发送位置提示栏信息
			request.setAttribute("njList", dao.getNjList());// 发送年级列表
			request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
			request.setAttribute("ndList", dao.getXnndList());// 发送学年列表
			request.setAttribute("xqList", dao.getXqList());// 发送学期列表
			request.setAttribute("xyList", dao.getXyList());// 发送学院列表
			
			//	================= begin 骆嘉伟 2009/6/13 ==================
			if (!xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				request.setAttribute("zyList", dao.getZyList(applyForm.getXydm()));// 发送专业列表
				request.setAttribute("bjList", dao.getBjList(applyForm.getXydm(), applyForm.getZydm(), nj));// 发送专业列表
			}else{
				String isFdy = session.getAttribute("isFdy").toString();// 辅导员
				if ("false".equalsIgnoreCase(isFdy)) {
					request.setAttribute("zyList", dao.getZyList(applyForm.getXydm()));// 发送专业列表
					request.setAttribute("bjList", dao.getBjList(applyForm.getXydm(), applyForm.getZydm(), nj));// 发送专业列表
				}
			}
			//武汉商业
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)
					||  xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
				
				FormModleCommon.setNjXyZyBjListForFdyBzr(request);
				FormModleCommon.setNdXnXqList(request);
			}
			//	================= end 骆嘉伟 2009/6/13 ==================
			request.setAttribute("userType", userType1);
			request.setAttribute("writeAble", "yes");
			//权限和表头
			FormModleCommon.commonRequestSet(request);
			//request.setAttribute("isFdy", session.getAttribute("isFdy").toString());
			request.setAttribute("isFdy", session.getAttribute("fdyQx").toString());
			request.setAttribute("isBzr", session.getAttribute("bzrQx").toString());
//			Base.getWriteAble(request);
			if(userType1.equals("xy")){
				if ("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
					applyForm.setXydm("");
				}else if("true".equalsIgnoreCase(session.getAttribute("bzrQx").toString())) {
					applyForm.setXydm("");
				}
				
			}
			
			if("print".equalsIgnoreCase(doType)){
				return mapping.findForward("qgzx_result_print");
			}else if("search".equalsIgnoreCase(doType)){
				return mapping.findForward("success_teacher");
			}else{
				request.setAttribute("userDep1", userDep);
				return new ActionForward("/qgzx/qgzx_xsgwsq_search.jsp", false);
			}
		}
		return mapping.findForward("success");
	}

	private ActionForward assisResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String[] colList1 = null;
		String[] colListCN1 = null;
		Vector<Object> rs1 = new Vector<Object>();
		String[] colList2 = null;
		String[] colListCN2 = null;
		Vector<Object> rs2 = new Vector<Object>();
		String[] colList3 = null;
		String[] colListCN3 = null;
		Vector<Object> rs3 = new Vector<Object>();
		String[] colList4 = null;
		String[] colListCN4 = null;
		Vector<Object> rs4 = new Vector<Object>();
		String[] colList5 = null;
		String[] colListCN5 = null;
		Vector<Object> rs5 = new Vector<Object>();
		String[] colList6 = null;
		String[] colListCN6 = null;
		Vector<Object> rs6 = new Vector<Object>();
		String[] colList7 = null;
		String[] colListCN7 = null;
		Vector<Object> rs7 = new Vector<Object>();
		String[] colList8 = null;
		String[] colListCN8 = null;
		Vector<Object> rs8 = new Vector<Object>();
		String[] colList9 = null;
		String[] colListCN9 = null;
		Vector<Object> rs9 = new Vector<Object>();
		String[] colList10 = null;
		String[] colListCN10 = null;
		Vector<Object> rs10 = new Vector<Object>();
		String[] colList11 = null;
		String[] colListCN11 = null;
		Vector<Object> rs11 = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
					"sqsj", "xysh", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, "view_knsxx");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_knsxx a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));

			colList1 = new String[] { "行号", "dkls", "nd", "xn", "xq", "xh",
					"xm", "sqsj", "xysh", "xxsh" };
			colListCN1 = dao.getColumnNameCN(colList1, "view_xxzxdksq");
			List topTr1 = dao.arrayToList(colList1, colListCN1);
			sql = "select rownum 行号, a.* from view_xxzxdksq a where xh=?";
			rs1.addAll(dao.rsToVator(sql, new String[] { xh }, colList1));
			request.setAttribute("topTr1", topTr1);
			request.setAttribute("rs1", rs1);
			request.setAttribute("num1", String.valueOf(rs1.size()));

			colList2 = new String[] { "行号", "dkls", "nd", "xn", "xq", "xh",
					"xm", "sqdkze", "sqsj", "xysh", "xxsh" };
			colListCN2 = dao.getColumnNameCN(colList2, "view_gjzxdksq");
			List topTr2 = dao.arrayToList(colList2, colListCN2);
			sql = "select rownum 行号, a.* from view_gjzxdksq a where xh=?";
			rs2.addAll(dao.rsToVator(sql, new String[] { xh }, colList2));
			request.setAttribute("topTr2", topTr2);
			request.setAttribute("rs2", rs2);
			request.setAttribute("num2", String.valueOf(rs2.size()));

			//国家奖学金
			colList3 = new String[]{"行号", "nd", "xh", "xm",
					"sqsj", "xysh","xxsh" };
			colListCN3 = dao.getColumnNameCN(colList3, "view_xsjxjzxjsq");
			List topTr3 = dao.arrayToList(colList3, colListCN3);
			sql = "select rownum 行号,a.* from view_xsjxjzxjsq a where xh=? and JZXJMC='gjjxj'";
			rs3.addAll(dao.rsToVator(sql, new String[]{ xh }, colList3));
			request.setAttribute("topTr3", topTr3);
			request.setAttribute("rs3", rs3);
			request.setAttribute("num3", String.valueOf(rs3.size()));
			//国家助学金
			colList4 = new String[]{"行号", "nd", "xh", "xm",
					"sqsj", "xysh","xxsh" };
			colListCN4 = dao.getColumnNameCN(colList4, "view_xsjxjzxjsq");
			List topTr4 = dao.arrayToList(colList4, colListCN4);
			sql = "select rownum 行号,a.* from view_xsjxjzxjsq a where xh=? and JZXJMC='gjzxj'";
			rs4.addAll(dao.rsToVator(sql, new String[]{ xh }, colList4));
			request.setAttribute("topTr4", topTr4);
			request.setAttribute("rs4", rs4);
			request.setAttribute("num4", String.valueOf(rs4.size()));
			//省政府奖学金
			colList5 = new String[]{"行号", "nd", "xh", "xm",
					"sqsj", "xysh","xxsh" };
			colListCN5 = dao.getColumnNameCN(colList5, "view_xsjxjzxjsq");
			List topTr5 = dao.arrayToList(colList5, colListCN5);
			sql = "select rownum 行号,a.* from view_xsjxjzxjsq a where xh=? and JZXJMC='szfjxj'";
			rs5.addAll(dao.rsToVator(sql, new String[]{ xh }, colList5));
			request.setAttribute("topTr5", topTr5);
			request.setAttribute("rs5", rs5);
			request.setAttribute("num5", String.valueOf(rs5.size()));
			//省政府助学金
			colList6 = new String[]{"行号", "nd", "xh", "xm",
					"sqsj", "xysh","xxsh" };
			colListCN6 = dao.getColumnNameCN(colList6, "view_xsjxjzxjsq");
			List topTr6 = dao.arrayToList(colList6, colListCN6);
			sql = "select rownum 行号,a.* from view_xsjxjzxjsq a where xh=? and JZXJMC='szfzxj'";
			rs6.addAll(dao.rsToVator(sql, new String[]{ xh }, colList6));
			request.setAttribute("topTr6", topTr6);
			request.setAttribute("rs6", rs6);
			request.setAttribute("num6", String.valueOf(rs6.size()));
			//心平
			colList7 = new String[]{"行号", "nd", "xh", "xm","SQDKJE".toLowerCase(),
					"sqsj", "xysh","xxsh" };
			colListCN7 = dao.getColumnNameCN(colList7, "view_xpjjzxdksq");
			List topTr7 = dao.arrayToList(colList7, colListCN7);
			sql = "select rownum 行号,a.* from view_xpjjzxdksq a where xh=?";
			rs7.addAll(dao.rsToVator(sql, new String[]{ xh }, colList7));
			request.setAttribute("topTr7", topTr7);
			request.setAttribute("rs7", rs7);
			request.setAttribute("num7", String.valueOf(rs7.size()));
			//学费
			colList8 = new String[]{"行号", "nd", "xh", "xm","ZZFF1QSJE".toLowerCase(),"ZZFF1JSJE".toLowerCase(),
					"sqsj", "xysh","xxsh" };
			colListCN8 = dao.getColumnNameCN(colList8, "view_xsbzxx");
			List topTr8 = dao.arrayToList(colList8, colListCN8);
			sql = "select rownum 行号,a.* from view_xsbzxx a where xh=? and bzdm=(select knbzdm from knbzdmb where bzlb like '学费%')";
			rs8.addAll(dao.rsToVator(sql, new String[]{ xh }, colList8));
			request.setAttribute("topTr8", topTr8);
			request.setAttribute("rs8", rs8);
			request.setAttribute("num8", String.valueOf(rs8.size()));
			//临时困难补助
			colList9 = new String[]{"行号", "nd", "xh", "xm","ZZFF1QSJE".toLowerCase(),"ZZFF1JSJE".toLowerCase(),
					"sqsj", "xysh","xxsh" };
			colListCN9 = dao.getColumnNameCN(colList9, "view_xsbzxx");
			List topTr9 = dao.arrayToList(colList9, colListCN9);
			sql = "select rownum 行号,a.* from view_xsbzxx a where xh=? and bzdm=(select knbzdm from knbzdmb where bzlb like '临时%')";
			rs9.addAll(dao.rsToVator(sql, new String[]{ xh }, colList9));
			request.setAttribute("topTr9", topTr9);
			request.setAttribute("rs9", rs9);
			request.setAttribute("num9", String.valueOf(rs9.size()));
			//外设助学金
			colList10 = new String[]{"行号", "nd", "xh", "xm",
					"sqsj", "xysh","xxsh"  };
			colListCN10 = dao.getColumnNameCN(colList10, "view_xswszxjsqxx");
			List topTr10 = dao.arrayToList(colList10, colListCN10);
			sql = "select rownum 行号,a.* from view_xswszxjsqxx a where xh=? ";
			rs10.addAll(dao.rsToVator(sql, new String[]{ xh }, colList10));
			request.setAttribute("topTr10", topTr10);
			request.setAttribute("rs10", rs10);
			request.setAttribute("num10", String.valueOf(rs10.size()));
			//校内贷学金
			colList11 = new String[]{"行号", "nd", "xh", "xm","ZZFF1QSJE".toLowerCase(),"ZZFF1JSJE".toLowerCase(),
					"sqsj", "xysh","xxsh" };
			colListCN11 = dao.getColumnNameCN(colList11, "view_xsbzxx");
			List topTr11 = dao.arrayToList(colList11, colListCN11);
			sql = "select rownum 行号,a.* from view_xsbzxx a where xh=? and bzdm=(select knbzdm from knbzdmb where bzlb like '校内%')";
			rs11.addAll(dao.rsToVator(sql, new String[]{ xh }, colList11));
			request.setAttribute("topTr11", topTr11);
			request.setAttribute("rs11", rs11);
			request.setAttribute("num11", String.valueOf(rs11.size()));

		} else {
			return new ActionForward("/assis_result_t.do?act=needer", false);
		}
		return mapping.findForward("success");
	}

	private ActionForward putAgainApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();		
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		
		String[] colList = null;
		String[] rs = null;
		
		String xh = applyForm.getXh();
		String xxdm = StandardOperation.getXxdm();
		String bbxm = request.getParameter("bbxm");
		String sql = "";
		
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			return new ActionForward("/bbsqb.do",false);
		}
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}		
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
			//重庆科技学院
			String cqkjSql = "select * from view_cqkjxy_bbsq where xh=?";
			colList = dao.getColumnName("select * from view_cqkjxy_bbsq where 1=2");
			rs = dao.getOneRs(cqkjSql, new String[] { xh }, colList);
		}else{
			sql = "select * from view_xsjbxx where xh=?";
			colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
			rs = dao.getOneRs(sql, new String[] { xh }, colList);
		}	
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		sql = "select * from xtszb";
		String tag = dao.returntag(sql, new String[]{});
		if ("empty".equalsIgnoreCase(tag)) {
			map.put("xn", "");
			map.put("nd", "");
			map.put("xq", "");
		} else {
			sql = "select  dqxn xn,dqnd nd,dqxq xq,(select xqmc from xqdzb where xqdm=dqxq)xqmc  from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"xn", "nd", "xq","xqmc" });
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
			map.put("xqmc", tmp[3]);
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		if(StringUtils.isNotNull(bbxm)){
		//查询补办信息
			sql = StringUtils.joinStr("select bbyy,bz from ", bbxm, " where xh=? and xn=? and nd=? and xq=?");
			map.putAll(dao.getMap(sql, 
					              new String[]{xh, map.get("xn"), map.get("nd"), map.get("xq")}, 
					              new String[]{"bbyy", "bz"}));
			map.put("bbxm", bbxm);
		}
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
			return new ActionForward("/rcsw/cqkj/bbsq.jsp",false);
		}
		
		request.setAttribute("xxdm", xxdm);
		
		return mapping.findForward("success");
	}
	
	private ActionForward printAgainApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		
		String tab = request.getParameter("tab");
 		String xh = request.getParameter("xh");
		String bbyy = request.getParameter("bbyy");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bbyy", bbyy);
		
		if(StringUtils.isNotNull(xh)){
			BasicExtendService service = new BasicExtendService();
			String[] output = new String[]{"xh","xm","xb","csrq","sfzh","xymc","zymc","bjmc",
					"lxdh", "jtdz"};
			map.putAll(service.getStuInfo(xh, output));
		}
		
		request.setAttribute("rs", map);
		
		String dest = null;
		if("xszbbb".equalsIgnoreCase(tab)){
			dest = "/sqb/print/xszbb_print.jsp";
		} else if("hcyhkbbb".equalsIgnoreCase(tab)){
			dest = "/sqb/print/hcyhkbb_print.jsp";
		} else if("yktbbb".equalsIgnoreCase(tab)){
			dest = "/sqb/print/yktbb_print.jsp";
		} else if("xhbbb".equalsIgnoreCase(tab)){
			dest = "/sqb/print/xhbb_print.jsp";
		}
		
		return new ActionForward(dest, false);
	}

	private ActionForward ticketBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		HttpSession session = request.getSession();		
		DAO dao = DAO.getInstance();
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();	
		String userSpceType = dao.getUserType(session.getAttribute("userDep").toString());
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		
		String xh = applyForm.getXh();
		
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		sql = "select * from cpydsjb";
		String tag = dao.returntag(sql, new String[]{});
		if ("empty".equalsIgnoreCase(tag)) {
			map.put("xn", "");
			map.put("nd", "");
			map.put("xq", "");
		} else {
			sql = "select xn,nd,xq from cpydsjb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd", "xq" });
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
		}
		//判断是否在申请时间内
		sql = "select * from cpydsjb where kssj<to_char(sysdate,'yyyymmddhh24miss') and jssj>to_char(sysdate,'yyyymmddhh24miss')";
		tag = dao.returntag(sql, new String[] {});
		if ("empty".equalsIgnoreCase(tag) || tag == null|| "".equalsIgnoreCase(tag)) {
			request.setAttribute("sqsjFlag", "1");
		}	
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		//获取车次列表
		sql = "select cc from hcccb where dqzt='出售' order by cc";
		List ccList = dao.getList(sql, new String[] {}, new String[] {"cc" });
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			//商业职业
			sql = "select a.cc from hccpydkb a where a.xh=? and not exists(select 1 from view_cpyd b where a.xh=b.xh and a.cc=b.cc and b.ydjg='成功' and b.xn=? and b.nd=? and b.xq=?) order by cc";
			ccList = dao.getList(sql, new String[]{xh,map.get("xn"),map.get("nd"),map.get("xq")}, new String[]{"cc"});
		}
		request.setAttribute("ccList", ccList);
		
		sql = "select czdm,czmc from czdmb";
		List czList = dao.getList(sql, new String[] {}, new String[] { "czdm", "czmc" });
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			sql = " select a.zdz czdm,(select czmc from czdmb b where b.czdm=a.zdz) czmc from hcccb a where exists(select 1 from hccpydkb d where d.cc=a.cc and d.xh=?)";
			czList = dao.getList(sql, new String[]{xh}, new String[]{"czdm","czmc"});
		}
		sql = "select substr(cckssj,0,4) cckssj1, substr(cckssj,5,2) cckssj2, substr(cckssj,7,2)cckssj3, substr(cckssj, 9,2) cckssj4,substr(cckssj,11,2) cckssj5,substr(cckssj,13,2)cckssj6," +
			  "substr(ccjssj,0,4) ccjssj1, substr(ccjssj,5,2) ccjssj2,substr(ccjssj,7,2)ccjssj3,substr(ccjssj,9,2)ccjssj4,substr(ccjssj,11,2)ccjssj5,substr(ccjssj,13,2) ccjssj6 from cpydsjb";
		HashMap<String, String> tmpMap = dao.getMap(sql, new String[]{}, new String[]{"cckssj1","cckssj2", "cckssj3","cckssj4","cckssj5","cckssj6","ccjssj1","ccjssj2","ccjssj3","ccjssj4","ccjssj5","ccjssj6"});
		
		String message = dao.getOneRs("select tsxx from mktsxxb where gnmkdm='N150903'", new String[]{}, "tsxx");
		
		request.setAttribute("time", tmpMap);
		request.setAttribute("czList", czList);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("userType", userSpceType);
		request.setAttribute("Mes", message);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	private ActionForward specialPrise(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,
			String userType)
	throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("go");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		CommanForm tForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String bmdm = session.getAttribute("userDep").toString();
		userType = session.getAttribute("userType").toString();

		if(act == null || act.equalsIgnoreCase("")){
			request.setAttribute("tips", "当前所在位置：评奖评优 - 审核 - 专项奖学金汇总");
			request.setAttribute("pk", "xh||zxjdm||ndfw");
			request.setAttribute("realTable", "xszxjxjhzb");
		} else if(act.equalsIgnoreCase("add")){
			request.setAttribute("tips", "当前所在位置：评奖评优 - 审核 - 专项奖学金汇总 - 增加");
		} else if(act.equalsIgnoreCase("modi")){
			request.setAttribute("tips", "当前所在位置：评奖评优 - 审核 - 专项奖学金汇总 - 修改");
		}

		String sql = "";
		List xyList = dao.getXyList();
		sql = "select jxjdm zxjxjdm,jxjmc zxjxjmc from jxjdmb where JXJLB='外'";
		List zxjxjList = dao.getList(sql, new String[]{}, new String[]{ "zxjxjdm","zxjxjmc" });
		String dqnd = dao.getOneRs("select dqnd from xtszb", new String[]{}, new String[]{"dqnd"})[0];
		ArrayList<HashMap<String, String>> ndfwList = new ArrayList<HashMap<String,String>>();
		for(int i = 7 ;i > 0; i--){
			HashMap<String, String> map1 = new HashMap<String, String>();
			String ndstr = (new Integer((new Integer(dqnd)).intValue()-i).toString())+"-"
			+(new Integer((new Integer(dqnd)).intValue()-i+1).toString());
			map1.put("ndfw", ndstr);
			ndfwList.add(map1);
		}

		request.setAttribute("ndfwList", ndfwList);
		request.setAttribute("xyList", xyList);
		request.setAttribute("zxjxjList", zxjxjList);
		request.setAttribute("userType", userType);

		if(userType.equalsIgnoreCase("admin")){
			if(userName.equalsIgnoreCase("admin")){
				if(act == null || act.equalsIgnoreCase("")){ 
					if (doType == null){
						request.setAttribute("act", "");
						request.setAttribute("writeAble","yes");
						return mapping.findForward("success");
					} else if (doType.equalsIgnoreCase("go")){
						String sxxydm = tForm.getXydm();
						String zxjxjdm = tForm.getZxjxjdm();
						String ndfw = tForm.getNdfw();
						String xh = request.getParameter("xh");
						String query = " 1=1";
						if(sxxydm != null && !sxxydm.equalsIgnoreCase(" ")){
							query += (" and exists(select 1 from zxbz_xxbmdm b where a.xy=b.bmmc and b.bmdm='"+sxxydm+"')");
						} 
						if (zxjxjdm != null && !zxjxjdm.equalsIgnoreCase(" ")){
							query += (" and exists(select 1 from jxjdmb b where a.zxjmc=b.jxjmc and b.jxjdm='"+zxjxjdm+"')");
						} 
						if (ndfw != null && !ndfw.equalsIgnoreCase(" ")){
							query += (" and ndfw='"+ndfw + "'");
						} 
						if (xh != null && !xh.equalsIgnoreCase(" ")
								&& !xh.equalsIgnoreCase("")){
							query += (" and xh='" + xh +"'");
						}

						sql="select rownum num,zxjdm,ZXJMC,XY,ZYBJ,XH,XM,XB,MZMC,DTY,YDCS,EDCS,SDCS,DXCS,RYCHOTHER,PJXFJD," +
						"XFJDZH,BXNXF,YYCJ,DCJ,ZYCJPM,ZYZRS,DRZW,NDFW from xszxjxjhzb a where ";///KYQKOTHER,BZ," +"PWYJ,

						sql += query;
						String[] outString = new String[]{"num","zxjdm","zxjmc","xy","zybj","xh","xm","xb","mzmc",
								"dty","ydcs","edcs","sdcs","dxcs","rychother","pjxfjd",
								"xfjdzh","bxnxf","yycj","dcj","zycjpm","zyzrs","drzw","ndfw"};//"kyqkother","bz","pwyj",
						List<HashMap<String,String>> rs = dao.getList(sql, new String[]{}, outString);
						request.setAttribute("rs", rs);
						request.setAttribute("act", "");
						request.setAttribute("writeAble","yes");
						return mapping.findForward("success");
					}
				} else if (act.equalsIgnoreCase("save")){
					//String 
					String zxjdm = tForm.getZxjxjdm();
					String xydm = tForm.getXydm();
					String zybj = DealString.toGBK(request.getParameter("zybj"));
					String xh = DealString.toGBK(request.getParameter("xh"));
					String xm = DealString.toGBK(request.getParameter("xm"));
					String xb = DealString.toGBK(request.getParameter("xb"));
					String mzmc = DealString.toGBK(request.getParameter("mzmc"));
					String dty = DealString.toGBK(request.getParameter("dty"));
					String ydcs = DealString.toGBK(request.getParameter("ydcs"));
					String edcs = DealString.toGBK(request.getParameter("edcs"));
					String sdcs = DealString.toGBK(request.getParameter("sdcs"));
					String dxcs = DealString.toGBK(request.getParameter("dxcs"));
					String rychother = DealString.toGBK(request.getParameter("rychother"));
					String pjxfjd = DealString.toGBK(request.getParameter("pjxfjd"));
					String xfjdzh = DealString.toGBK(request.getParameter("xfjdzh"));
					String bxnxf = DealString.toGBK(request.getParameter("bxnxf"));
					String yycj = DealString.toGBK(request.getParameter("yycj"));
					String dcj = DealString.toGBK(request.getParameter("dcj"));
					String zycjpm = DealString.toGBK(request.getParameter("zycjpm"));
					String zyzrs = DealString.toGBK(request.getParameter("zyzrs"));
					String drzw = DealString.toGBK(request.getParameter("drzw"));
					//String kyqkother = deal.toGBK(request.getParameter("kyqkother"));
					//String bz = deal.toGBK(request.getParameter("bz"));
					//String pwyj = deal.toGBK(request.getParameter("pwyj"));
					String ndfw = DealString.toGBK(request.getParameter("ndfw"));


					String zxjmc = dao.getOneRs("select jxjmc zxjmc from jxjdmb where jxjdm=?",
							new String[]{zxjdm}, new String[]{"zxjmc"})[0];
					String xy = dao.getOneRs("select bmmc xymc from zxbz_xxbmdm where BMDM=?", 
							new String[]{xydm}, new String[]{"xymc"})[0];

					sql ="select * from xszxjxjhzb where xh||zxjdm||ndfw=?";
					String[] result = dao.getOneRs(sql, new String[]{pkValue}, new String[]{"xh","zxjdm","ndfw"});
					boolean ok = false;
					if(result == null){//数据库中没有记录
						sql = "insert into xszxjxjhzb(zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,rychother,pjxfjd," +
						"xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,drzw,ndfw) " +//kyqkother,bz,pwyj,
						"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
						"?,?,?,?,?,?,?,?,?)";
						ok = dao.runUpdate(sql, new String[]{zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,
								rychother,pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,
								drzw,ndfw});//kyqkother,bz,pwyj,
								if(ok){
									request.setAttribute("ok", "yes");
								} else {
									request.setAttribute("ok", "no");
								}
					} else {//数据库中已经有记录
						boolean del = false;
						sql = "delete from xszxjxjhzb where xh||zxjdm||ndfw=?";
						del = dao.runUpdate(sql, new String[]{pkValue});
						if(del){ 
							//sql1 = "update xszxjxjhzb set zxjdm=?,zxjmc=?,xydm=?,xy=?,zybj=?,xh=?,xm=?,xb=?,mzmc=?," +
							//			"dty=?,ydcs=?,edcs=?,sdcs=?,dxcs=?,rychother=?,pjxfjd=?,xfjdzh=?,bxnxf=?," +
							//			"yycj=?,dcj=?,zycjpm=?,zyzrs=?,drzw=?,ndfw=? where xh||zxjmc||ndfw=?";//kyqkother=?,bz=?,pwyj=?,
							sql = "insert into xszxjxjhzb(zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,rychother,pjxfjd," +
							"xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,drzw,ndfw) " +//kyqkother,bz,pwyj,
							"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
							"?,?,?,?,?,?,?,?,?)";
							ok = dao.runUpdate(sql, new String []{zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,
									rychother,pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,
									drzw,ndfw});///kyqkother,bz,pwyj,
									//}
						if(ok){
							request.setAttribute("ok", "yes");
						} else {
							request.setAttribute("ok", "no");
						}
						}

					}
				}/**else if (act.equalsIgnoreCase("modi")){
				    		  String zxjdm = tForm.getZxjxjdm();
				    		  String xydm = tForm.getXydm();
				    		  String zybj = request.getParameter("zybj");
				    		  String xh = request.getParameter("xh");
				    		  String xm = request.getParameter("xm");
				    		  String xb = request.getParameter("xb");
				    		  String mzmc = request.getParameter("mzmc");
				    		  String dty = request.getParameter("dty");
				    		  String ydcs = request.getParameter("ydcs");
				    		  String edcs = request.getParameter("edcs");
				    		  String sdcs = request.getParameter("sdcs");
				    		  String dxcs = request.getParameter("dxcs");
				    		  String rychother = request.getParameter("rychother");
				    		  String pjxfjd = request.getParameter("pjxfjd");
				    		  String xfjdzh = request.getParameter("xfjdzh");
				    		  String bxnxf = request.getParameter("bxnxf");
				    		  String yycj = request.getParameter("yycj");
				    		  String dcj = request.getParameter("dcj");
				    		  String zycjpm = request.getParameter("zycjpm");
				    		  String zyzrs = request.getParameter("zyzrs");
				    		  String drzw = request.getParameter("drzw");
				    		  //String kyqkother = request.getParameter("kyqkother");
				    		  //String bz = request.getParameter("bz");
				    		  //String pwyj = request.getParameter("pwyj");
				    		  String ndfw = request.getParameter("ndfw");

				    		  String zxjmc = dao.getOneRs("select jxjmc zxjmc from jxjdmb where jxjdm=?",
				    				                         new String[]{zxjdm}, new String[]{"zxjmc"})[0];
				    		  String xymc = dao.getOneRs("select bmmc xymc from zxbz_xxbmdm where BMDM=?",
				    				                         new String[]{xydm}, new String[]{"xymc"})[0];

				    		  sql ="select * from xszxjxjhzb where xh||zxjdm||ndfw=?";
				    		  String[] result = dao.getOneRs(sql, new String[]{pkValue}, new String[]{});
				    		  boolean del = false;
				    		  boolean ok = false;
				    		  if(result == null){
				    			  del = true;
				    		  } else {
				    			 sql = "delete from xszxjxjhzb where xh||zxjdm||ndfw=?";
				    			 del = dao.runUpdate(sql, new String[]{pkValue});
				    		  }
				    		  if(del){
				    			  sql = "update xszxjxjhzb set zxjdm=?,zxjmc=?,xydm=?,xy=?,zybj=?,xh=?,xm=?,xb=?,mzmc=?," +
			    			  		"dty=?,ydcs=?,edcs=?,sdcs=?,dxcs=?,rychother=?,pjxfjd=?,xfjdzh=?,bxnxf=?," +
			    			  		"yycj=?,dcj=?,zycjpm=?,zyzrs=?,drzw=?,ndfw=? where xh||zxjmc||ndfw=?";//kyqkother=?,bz=?,pwyj=?,
			    			      ok = dao.runUpdate(sql, new String []{zxjdm,zxjmc,xydm,xymc,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,
			    			    		                                 rychother,pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,
			    			    		                                 drzw,ndfw,pkValue});///kyqkother,bz,pwyj,
				    		  }
				    		  if(ok){
				    			  request.setAttribute("ok", "yes");
				    		  } else {
				    			  request.setAttribute("ok", "no");
				    		  }
				    	  } */else if(act.equalsIgnoreCase("del")){
				    		  sql = "delete from xszxjxjhzb where xh||zxjdm||ndfw=?";
				    		  boolean ok = false;
				    		  ok = dao.runUpdate(sql, new String[]{pkValue});
				    		  if(ok){
				    			  request.setAttribute("ok", "yes");
				    		  } else{
				    			  request.setAttribute("ok", "no");
				    		  }
				    	  }
			} else if (userType == "xy" && bmdm != null){
				tForm.setXydm(bmdm);
				if(act == null || act.equalsIgnoreCase("") ){ 
					if (doType == null){
						request.setAttribute("act", "");
						request.setAttribute("writeAble","yes");
						return mapping.findForward("success");
					} else if (doType.equalsIgnoreCase("go")){
						String sxxydm = tForm.getXydm();
						String zxjxjdm = tForm.getZxjxjdm();
						String ndfw = tForm.getNdfw();
						String xh = request.getParameter("xh");
						String query = " 1=1";
						if(sxxydm != null && !sxxydm.equalsIgnoreCase(" ")){
							query += (" and exists(select 1 from zxbz_xxbmdm b where a.xy=b.bmmc and b.bmdm='"+sxxydm+"')");
						} else if (zxjxjdm != null && !zxjxjdm.equalsIgnoreCase(" ")){
							query += (" and exists(select 1 from jxjdmb b where a.zxjmc=b.jxjmc and b.jxjdm='"+zxjxjdm+"')");
						} else if (ndfw != null && !ndfw.equalsIgnoreCase(" ")){
							query += (" and ndfw='" + ndfw +"'");
						} else if (xh != null && !xh.equalsIgnoreCase(" ")
								&& !xh.equalsIgnoreCase("")){
							query += (" and xh='" + xh +"'");
						}

						sql="select rownum num,ZXJMC,XY,ZYBJ,XH,XM,XB,MZMC,DTY,YDCS,EDCS,SDCS,DXCS,RYCHOTHER,PJXFJD," +
						"XFJDZH,BXNXF,YYCJ,DCJ,ZYCJPM,ZYZRS,DRZW,NDFW from xszxjxjhzb a where ";//KYQKOTHER,BZ," +"PWYJ,
						sql += query;
						String[] outString = new String[]{"num","ZXJMC","XY","ZYBJ","XH","XM","XB","MZMC","DTY",
								"YDCS","EDCS","SDCS","DXCS","RYCHOTHER","PJXFJD","XFJDZH",
								"BXNXF","YYCJ","DCJ","ZYCJPM","ZYZRS","DRZW","NDFW"};///"KYQKOTHER","BZ","PWYJ",
						List<HashMap<String,String>> rs = dao.getList(sql, new String[]{}, outString);
						request.setAttribute("rs", rs);
						request.setAttribute("act", "");
						request.setAttribute("writeAble","yes");
						return mapping.findForward("success");
					}
				} else if (act.equalsIgnoreCase("save")){
					String zxjdm = tForm.getZxjxjdm();
					String xydm = tForm.getXydm();
					String zybj = request.getParameter("zybj");
					String xh = request.getParameter("xh");
					String xm = request.getParameter("xm");
					String xb = request.getParameter("xb");
					String mzmc = request.getParameter("mzmc");
					String dty = request.getParameter("dty");
					String ydcs = request.getParameter("ydcs");
					String edcs = request.getParameter("edcs");
					String sdcs = request.getParameter("sdcs");
					String dxcs = request.getParameter("dxcs");
					String rychother = request.getParameter("rychother");
					String pjxfjd = request.getParameter("pjxfjd");
					String xfjdzh = request.getParameter("xfjdzh");
					String bxnxf = request.getParameter("bxnxf");
					String yycj = request.getParameter("yycj");
					String dcj = request.getParameter("dcj");
					String zycjpm = request.getParameter("zycjpm");
					String zyzrs = request.getParameter("zyzrs");
					String drzw = request.getParameter("drzw");
					//String kyqkother = request.getParameter("kyqkother");
					//String bz = request.getParameter("bz");
					//String pwyj = request.getParameter("pwyj");
					String ndfw = tForm.getNdfw();


					String zxjmc = dao.getOneRs("select jxjmc zxjmc from jxjdmb where jxjdm=?",
							new String[]{zxjdm}, new String[]{"zxjmc"})[0];
					String xy = dao.getOneRs("select bmmc xymc from zxbz_xxbmdm where BMDM=?", 
							new String[]{xydm}, new String[]{"xymc"})[0];;

							sql ="select * from xszxjxjhzb where xh||zxjdm||ndfw=?";
							String[] result = dao.getOneRs(sql, new String[]{pkValue}, new String[]{});
							boolean ok = false;
							if(result == null){//数据库中没有记录
								sql = "insert into xszxjxjhzb(zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,rychother,pjxfjd," +
								"xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,drzw,ndfw) " +//kyqkother,bz,pwyj,
								"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
								"?,?,?,?,?,?,?,?,?)";
								ok = dao.runUpdate(sql, new String[]{zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,
										rychother,pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,
										drzw,ndfw});//kyqkother,bz,pwyj,
								if(ok){
									request.setAttribute("ok", "yes");
								} else {
									request.setAttribute("ok", "no");
								}				    			  
							} else {//数据库中已经有记录
								boolean del = false;
								sql = "delete from xszxjxjhzb where xh||zxjdm||ndfw=?";
								del = dao.runUpdate(sql, new String[]{pkValue});
								if(del){
									sql = "update xszxjxjhzb set zxjdm=?,zxjmc=?,xydm=?,xy=?,zybj=?,xh=?,xm=?,xb=?,mzmc=?," +
									"dty=?,ydcs=?,edcs=?,sdcs=?,dxcs=?,rychother=?,pjxfjd=?,xfjdzh=?,bxnxf=?," +
									"yycj=?,dcj=?,zycjpm=?,zyzrs=?,drzw=?,ndfw=? where xh||zxjmc||ndfw=?";//kyqkother=?,bz=?,pwyj=?,
									ok = dao.runUpdate(sql, new String []{zxjdm,zxjmc,xydm,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,
											rychother,pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,
											drzw,ndfw,pkValue});///kyqkother,bz,pwyj,
								}
								if(ok){
									request.setAttribute("ok", "yes");
								} else {
									request.setAttribute("ok", "no");
								}
							}


				} /**else if (act.equalsIgnoreCase("modi")){
		    		  String zxjdm = tForm.getZxjxjdm();
		    		  String xydm = tForm.getXydm();
		    		  String zybj = request.getParameter("zybj");
		    		  String xh = request.getParameter("xh");
		    		  String xm = request.getParameter("xm");
		    		  String xb = request.getParameter("xb");
		    		  String mzmc = request.getParameter("mzmc");
		    		  String dty = request.getParameter("dty");
		    		  String ydcs = request.getParameter("ydcs");
		    		  String edcs = request.getParameter("edcs");
		    		  String sdcs = request.getParameter("sdcs");
		    		  String dxcs = request.getParameter("dxcs");
		    		  String rychother = request.getParameter("rychother");
		    		  String pjxfjd = request.getParameter("pjxfjd");
		    		  String xfjdzh = request.getParameter("xfjdzh");
		    		  String bxnxf = request.getParameter("bxnxf");
		    		  String yycj = request.getParameter("yycj");
		    		  String dcj = request.getParameter("dcj");
		    		  String zycjpm = request.getParameter("zycjpm");
		    		  String zyzrs = request.getParameter("zyzrs");
		    		  String drzw = request.getParameter("drzw");
		    		  //String kyqkother = request.getParameter("kyqkother");
		    		  //String bz = request.getParameter("bz");
		    		 // String pwyj = request.getParameter("pwyj");
		    		  String ndfw = request.getParameter("ndfw");

		    		  String zxjmc = dao.getOneRs("select jxjmc zxjmc from jxjdmb where jxjdm=?",
		    				                         new String[]{zxjdm}, new String[]{"zxjmc"})[0];
		    		  String xymc = dao.getOneRs("select bmmc xymc from zxbz_xxbmdm where BMDM=?",
		    				                         new String[]{xydm}, new String[]{"xymc"})[0];

		    		  sql ="select * from xszxjxjhzb where xh||zxjdm||ndfw=?";
		    		  String[] result = dao.getOneRs(sql, new String[]{pkValue}, new String[]{});
		    		  boolean del = false;
		    		  boolean ok = false;
		    		  if(result == null){
		    			  del = true;
		    		  } else {
		    			 sql = "delete from xszxjxjhzb where xh||zxjdm||ndfw=?";
		    			 del = dao.runUpdate(sql, new String[]{pkValue});
		    		  }
		    		  if(del){
		    			  sql = "update xszxjxjhzb set zxjdm=?,zxjmc=?,xydm=?,xy=?,zybj=?,xh=?,xm=?,xb=?,mzmc=?," +
	    			  		"dty=?,ydcs=?,edcs=?,sdcs=?,dxcs=?,rychother=?,pjxfjd=?,xfjdzh=?,bxnxf=?," +
	    			  		"yycj=?,dcj=?,zycjpm=?,zyzrs=?,drzw=?,ndfw=? where xh||zxjmc||ndfw=?";//kyqkother=?,bz=?,pwyj=?,
	    			      ok = dao.runUpdate(sql, new String []{zxjdm,zxjmc,xydm,xymc,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,
	    			    		                                 rychother,pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,
	    			    		                                 drzw,///kyqkother,bz,pwyj,
	    			    		                                 ndfw,pkValue});
		    		  }
		    		  if(ok){
		    			  request.setAttribute("ok", "yes");
		    		  } else {
		    			  request.setAttribute("ok", "no");
		    		  }
		    	  }*/
				else if(act.equalsIgnoreCase("del")){
					sql = "delete from xszxjxjhzb where xh||zxjmc||ndfw=?";
					boolean ok = false;
					ok = dao.runUpdate(sql, new String[]{pkValue});
					if(ok){
						request.setAttribute("ok", "yes");
					} else{
						request.setAttribute("ok", "no");
					}
				}
				return mapping.findForward("success");
			}
		}
		request.setAttribute("errMsg", "该页面未开放");
		return mapping.findForward("false");
	}

	private ActionForward xszxjxjhzb(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {


		DAO dao = DAO.getInstance();
		CommanForm thisForm = (CommanForm) form;
		String doType = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		String dqnd = dao.getOneRs("select dqnd from xtszb", new String[]{}, new String[]{"dqnd"})[0];
		List xyList = dao.getXyList();
		String sql = "";
		sql = "select jxjdm zxjxjdm,jxjmc zxjxjmc from jxjdmb where JXJLB='外'";
		List zxjxjList = dao.getList(sql, new String[]{}, new String[]{ "zxjxjdm","zxjxjmc" });
		ArrayList<HashMap<String, String>> ndfwList = new ArrayList<HashMap<String,String>>();
		for(int i = 7 ;i > 0; i--){
			HashMap<String, String> map1 = new HashMap<String, String>();
			String ndstr = (new Integer((new Integer(dqnd)).intValue()-i).toString())+"-"
			+(new Integer((new Integer(dqnd)).intValue()-i+1).toString());
			map1.put("ndfw", ndstr);
			ndfwList.add(map1);
		}


		if(doType.equalsIgnoreCase("newpage")){
			String tips = "当前所在位置：评奖评优 - 审核 - 专项奖学金汇总 - 增加";
			request.setAttribute("ndfwList", ndfwList);
			request.setAttribute("zxjxjList", zxjxjList);
			request.setAttribute("xyList", xyList);
			request.setAttribute("tips", tips);
			request.setAttribute("realTable", "xszxjxjhzb");
			request.setAttribute("pk", "xh||zxjdm||ndfw");
			HashMap<String, String> map = new HashMap<String, String>();
			String[] outString = new String[]{"xh","xm","xb","xydm"};
			for (int i=0;i<outString.length;i++){
				map.put(outString[i], " ");
			}
			request.setAttribute("rs", map);
			return mapping.findForward("success");

		} else if (doType.equalsIgnoreCase("modipage")){
			pkValue = request.getParameter("pkValue");
			String tips = "当前所在位置：评奖评优 - 审核 - 专项奖学金汇总 - 修改";
			sql="select zxjmc,zxjdm,xydm,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,rychother,pjxfjd," +
			"xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,drzw,ndfw from xszxjxjhzb where xh||zxjdm||ndfw=?";//kyqkother,bz,pwyj,
			HashMap<String, String> map = new HashMap<String, String>();
			String [] outString = {"zxjmc","zxjdm","xydm","zybj","xh","xm","xb","mzmc","dty","ydcs",
					"edcs","sdcs","dxcs","rychother","pjxfjd","xfjdzh","bxnxf",
					"yycj","dcj","zycjpm","zyzrs","drzw","ndfw"};//"kyqkother","bz","pwyj",
			String [] outValue = dao.getOneRs(sql, new String[]{ pkValue }, outString);

			for(int i=0;i<outValue.length;i++){
				if(outValue[i] != null){
					map.put(outString[i], outValue[i].toString());
				} else {
					map.put(outString[i], " ");
				}
			}
			thisForm.setXydm(map.get("xydm"));
			thisForm.setZxjxjdm(map.get("zxjdm"));
			thisForm.setNdfw(map.get("ndfw"));
			request.setAttribute("ndfwList", ndfwList);
			request.setAttribute("zxjxjList", zxjxjList);
			request.setAttribute("xyList", xyList);
			request.setAttribute("tips", tips);
			request.setAttribute("rs", map);
			request.setAttribute("pk", "xh||zxjdm||ndfw");
			request.setAttribute("realTable", "xszxjxjhzb");
			return mapping.findForward("success");

		}
		return mapping.findForward("false");
	}

	private ActionForward jxjpsdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tips = "评奖评优 - 奖学金申请 - 奖学金评审登记表";
		request.setAttribute("tips", tips);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xxdm = dao.getXxdm();
		String xh = request.getParameter("xh");
		String jxj = request.getParameter("jxjdm");
		String tab = request.getParameter("tab");
		if (userType.equalsIgnoreCase("stu")) {
			xh = session.getAttribute("userName").toString();
		}

		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd" });
		String xn = tmp[0];
		String nd = tmp[1];
		sql = "select * from view_xsjxjb where jxjdm=? and xh=? and xn=? and nd=?";
		HashMap<String, String> map = dao.getMap(sql, new String[] { jxj, xh, xn, nd },
				new String[] { "nd", "xh", "xm", "xymc", "zymc", "bjmc", "xb",
				"jxjmc", "sqly", "drzw", "kycg", "dnshjxj","xyshyj","xxshyj","fdyyj","xypswyhyj","xxjl","dykhdj","xstzjkbz"});
		
		String[] qtxx = null;
		//安徽建工
		if (StringUtils.isEqual(xxdm, Globals.XXDM_AHJZGYXY)) {
			qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1",  "bxkpjcj1", "bjcjpx1", "zycjpx1",
					"tyhgbz1", "jxj2", "shyg2", "ddj2",  "bxkpjcj2", "bjcjpx2", "zycjpx2",
					"tyhgbz2", "jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3", "zycjpx3",
					"tyhgbz3", "jxj4", "shyg4", "ddj4", "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4","bjcjpx5","zycjpx5" };
			request.setAttribute("isAHJG", "yes");
		}else {
			qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1",  "bxkpjcj1", "bjcjpx1", "zycjpx1",
					"tyhgbz1", "jxj2", "shyg2", "ddj2",  "bxkpjcj2", "bjcjpx2", "zycjpx2",
					"tyhgbz2", "jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3", "zycjpx3",
					"tyhgbz3", "jxj4", "shyg4", "ddj4", "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4"};
		}
		
		sql = "select a.* from xsjxjxxb a where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if(qtxxfs == null){
			qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
			map.put(qtxx[i],qtxxfs[i]);
		}
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
			sjhm = tt[0];
			wysp = tt[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		String zyrsNum = dao.getOneRs(
				"select count(*) num from view_xsjbxx where zydm=(select zydm from bks_xsjbxx where xh=?)",
				new String[] { xh }, new String[] { "num" })[0];
		tt = dao.getOneRs("select mzmc,zzmmmc from view_stu_details where xh=?",new String[]{xh},new String[]{"mzmc","zzmmmc"});
		String mzmc = "";
		String zzmmmc = "";
		if(tt != null && tt.length == 2){
			mzmc = tt[0];
			zzmmmc = tt[1];
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[] {}, new String [] {"xxmc"})[0];
		map.put("mzmc", mzmc);
		map.put("zzmmmc", zzmmmc);
		map.put("zyrsNum", zyrsNum);
		map.put("xxmc", xxmc);
		request.setAttribute("map", map);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){//西昌学院
			String pkValue = request.getParameter("pkValue");
			xh = request.getParameter("xh");
			
			sql = "select xh,xm,xb,bjmc,(select rtrq from tyxxb b where a.xh=b.xh) rtrq,drzw,jxjmc,xyshyj,xxshyj,(select mzmc from view_xsxxb b where a.xh=b.xh) mzmc,(select csrq from view_xsxxb b where a.xh=b.xh) csny,xxjl from view_xsjxjb a where xn||nd||xh||jxjdm=?";
			
//			sql = "select xh,xm,bjmc,xb,(select mzmc from view_stu_details a where a.xh=xh and rownum=1 )mzmc,"
//				+" (select csrq from view_stu_details a where a.xh=xh and rownum=1)csny from view_xsjbxx where xh=?";
			map = dao.getMapNotOut(sql, new String[]{pkValue});
			List<String[]> cjList = dao.rsToVator("select kcmc,cj from cjb where xh=? and xn=? and xq=?", new String[]{xh,Base.getJxjsqxn(),Base.getJxjsqxq()}, new String[]{"kcmc", "cj"});
			HashMap<String, String> rs = new HashMap<String, String>();
			for (int i=0;i<cjList.size();i++) {
				rs.put("kcmc" + (i+1), cjList.get(i)[0]);
				rs.put("cj" + (i+1), cjList.get(i)[1]);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("map", map);      
			return new ActionForward("/pjpy/xcxy/xcxy_sjxsdjb.jsp",false);
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)){//无锡商职
			String pkValue = request.getParameter("pkValue");
			xh = request.getParameter("xh");
			sql = "select xh,xm,xb,bjmc,xymc,zymc,(select rtrq from tyxxb b where a.xh=b.xh)" +
					" rtrq,drzw,jxjmc,fdyyj,xyshyj,xxshyj,(select jg from view_xsxxb b where a.xh=b.xh) jg," +
					"(select mzmc from view_xsxxb b where a.xh=b.xh) mzmc," +
					"(select zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc," +
					"(select ssbh from view_xszsxx b where a.xh=b.xh) ssbh," +
					"(select csrq from view_xsxxb b where a.xh=b.xh) csny,xxjl from view_xsjxjb a where xn||nd||xh||jxjdm=?";
			
//			sql = "select xh,xm,bjmc,xb,(select mzmc from view_stu_details a where a.xh=xh and rownum=1 )mzmc,"
//				+" (select csrq from view_stu_details a where a.xh=xh and rownum=1)csny from view_xsjbxx where xh=?";
			map = dao.getMapNotOut(sql, new String[]{pkValue});
			String xq=xn.substring(0, 4);
			if(xq.equalsIgnoreCase(nd)){
				xq="01";
			}else{
				xq="02";
			}
			List<String[]> cjList = dao.rsToVator("select kcmc,cj from cjb where xh=? and xn=? and xq=?", new String[]{xh,xn,xq}, new String[]{"kcmc", "cj"});
			
			HashMap<String, String> rs = new HashMap<String, String>();
			double cjh = 0.0;
			for (int i=0;i<cjList.size();i++) {
				rs.put("kcmc" + (i+1), cjList.get(i)[0]);
				rs.put("cj" + (i+1), cjList.get(i)[1]);
				cjh+=Double.parseDouble(cjList.get(i)[1]);
			}
			rs.put("pjcj", ((Long)Math.round(cjh/cjList.size())).toString());
			request.setAttribute("rs", rs);
			request.setAttribute("map", map);      
			return new ActionForward("/pjpy/wxsz/wxsz.jsp",false);
		}else if(xxdm.equals(Globals.XXDM_NBTYZYJSXY)){
			return mapping.findForward("success_nbty");
		}else if(xxdm.equals(Globals.XXDM_XYSFXY)){
			String pkValue = request.getParameter("pkValue");
			xh = request.getParameter("xh");
			
			sql = "select xh,xm,xb,xymc,zymc,bjmc," +
					"(select rtrq from tyxxb b where a.xh=b.xh) rtrq,drzw,jxjmc,xyshyj,xxshyj,(select mzmc from view_xsxxb b where a.xh=b.xh) mzmc,(select csrq from view_xsxxb b where a.xh=b.xh) csny,xxjl from view_xsjxjb a where xn||nd||xh||jxjdm=?";
			map = dao.getMapNotOut(sql, new String[]{pkValue});
			
			sql = "select count(*) num from view_xysf_jxjlbxx where jxjdm=? and jxjlbmc like '综合%'";
			
			Map<String,String> jxjInfo = dao.getMapNotOut(sql, new String[]{jxj});
			
			sql = "select zhszcpzfpm from view_zhszcp where xh=? and xn=? and nd=?";
			
			Map<String,String> pm = dao.getMapNotOut(sql, new String[]{xh,xn,nd});
		
			String dest = "0".equalsIgnoreCase(jxjInfo.get("num")) ? "/pjpy/xysf/dxjxjprint.jsp" : "/pjpy/xysf/zhjxjprint.jsp";
			request.setAttribute("pm", pm);
			request.setAttribute("map", map);      
			return new ActionForward(dest, false);
		}else{
			if(null!=jxj&&jxj.equalsIgnoreCase("a")){			
				return new ActionForward("/added/tlmjxj.jsp",false);					
			}
			if (tab!=null &&tab.equalsIgnoreCase("shjxj")) {
				return new ActionForward("/pjpy/csmz/shjxjpsdjb.jsp",false);
			}
			return mapping.findForward("success");
		}
		
	}

	private ActionForward newStuInsureApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = Base.getWriteAble(request);
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "newStuInsureApply.do")?"yes":"no";
		String xh = applyForm.getXh();
		String query = "";
		String tmp[] = null;
		String bxgsdm = request.getParameter("tbgsdm");
		String bxxzdm = request.getParameter("tbxzdm");
		String isxz="no";
		String page=request.getParameter("page");
		String xxdm=StandardOperation.getXxdm();
		//广东女子职业技术学院
		if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
			request.setAttribute("showXfzrx", "showXfzrx");		
			request.setAttribute("showGdnz", "showGdnz");
			List bxdcList=dao.getList("select dcdm,dcmc from gdnzzy_bxdcdmb",new String[]{},new String[]{"dcdm","dcmc"});
			request.setAttribute("bxdcList", bxdcList);
		}
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		//查询学生基本信息
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		//保险公司列表
		sql = "select bxgsdm,bxgsmc from bxgsdmb";
		List tbgsdmList = dao.getList(sql, new String[] {},new String[] { "bxgsdm", "bxgsmc" });
		request.setAttribute("tbgsdmList", tbgsdmList);
		
		//保险险种列表
		if(bxgsdm!=null&&!bxgsdm.equals("")){
			query = " where bxgsdm='" + bxgsdm + "'";
			map.put("tbgsdm", bxgsdm);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				sql = "select bxxzdm,bxxzmc from bxxzb" + query;
				List tbxzdmList = dao.getList(sql, new String[] {},new String[] { "bxxzdm", "bxxzmc" });
				request.setAttribute("tbxzdmList", tbxzdmList);
				
				//保险期限
				String bxqx = "";
				String bxje = "";
				sql = "select qxlb from bxxzb a where exists(select 1 from bxxzb b where bxgsdm=? and a.bxgsdm=b.bxgsdm )";
				String qxlb = dao.getOneRs(sql, new String[]{bxgsdm}, "qxlb");
				if(qxlb != null && qxlb.equalsIgnoreCase("按学制")){
					bxqx = map.get("xz");
					bxje = dao.getOneRs("select sum(bxje) bxje from bxxzb where bxgsdm=?", new String[]{bxgsdm}, "bxje");
					bxje = bxje == null || "".equalsIgnoreCase(bxje) ? "0" : bxje;
					bxqx = bxqx == null || "".equalsIgnoreCase(bxqx) ? "0" : bxqx;
					map.put("bxje",String.valueOf(Integer.parseInt(bxje)*Integer.parseInt(bxqx)));
					map.put("bxqx", bxqx);
				}else{
					map.put("bxqx", "");
					map.put("bxje", "");
				}
			}
		}
		
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			sql = "select bxxzdm,bxxzmc from bxxzb" + query;			
			List tbxzdmList = dao.getList(sql, new String[] {},new String[] { "bxxzdm", "bxxzmc" });			
			request.setAttribute("tbxzdmList", tbxzdmList);
		}
		
		if(bxxzdm!=null&&!bxxzdm.equals("")){
			//保险期限 保险金额
			isxz="yes";
			sql = "select qxlb,bxje from bxxzb where bxxzdm=?";
			tmp = dao.getOneRs(sql, new String[] { bxxzdm }, new String[] { "qxlb", "bxje" });
			if(tmp[0].equals("按学制")){
				sql = "select xz from view_xsjbxx where xh=?";
				String xz = dao.getOneRs(sql, new String[] { xh },"xz");
				if(xz!=null&&!xz.equals("")){
					int bxqx = Integer.parseInt(xz);
					int bxje = Integer.parseInt(tmp[1])*bxqx;
					
					if(page!=null&&!page.equalsIgnoreCase("")){
						map.put("bxnx", Integer.toString(bxqx));
						map.put("bf", Integer.toString(bxje));
					}
					map.put("bxqx", Integer.toString(bxqx));
					map.put("bxje", Integer.toString(bxje));
				}else{
					map.put("bxqx", "");
					map.put("bxje", "");
				}
			}else{
				isxz="no";
				map.put("bxqx", "");
				map.put("bxje", tmp[1]);
			}
			map.put("tbxzdm", bxxzdm);
		}	
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		tmp = dao.getOneRs(sql, new String[] {}, new String[] {"dqxn", "dqnd" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		map.put("bxpzh", request.getParameter("bxpzh"));
		request.setAttribute("rs", map);
		request.setAttribute("isxz", isxz);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xxdm", xxdm);
		if(page!=null&&!page.equalsIgnoreCase("")){
			request.setAttribute("doType", request.getParameter("doType"));
			request.setAttribute("pkValue", request.getParameter("pkValue"));
			request.setAttribute("disableEle", request.getParameter("disableEle"));
			request.setAttribute("xnList", dao.getXnndList());
			return new ActionForward("/sjcz/xsbxb.jsp");
		}else{    		
			return mapping.findForward("success");
		}
	}

	private ActionForward InsureAppSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		String writeAble = Base.getWriteAble(request);
		request.setAttribute("writeAble", writeAble);
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			colList = new String[] { "行号", "nd", "xn", "bxgsmc", "bxxzmc", "bxnx", "bf", "sqsj", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, "view_xsbxxx");
			List topTr = dao.arrayToList(colList, colListCN);
			sql = "select rownum 行号, a.* from view_xsbxxx a where xh=?";
			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				//上海工程
				return new ActionForward("/shgc_sqjgcx.do?method=appQue&writeAble=" + writeAble);
			}
			return new ActionForward("/data_search.do?act=InsureAppSearch&writeAble="+writeAble, false);
		}
		return mapping.findForward("success");
	}  
	
	/**
	 *判断该生是否符合条件申请 (奖学金和荣誉称号)
	 * @param tjList
	 * @param xh xmdm(jxjdm,rychdm) xmlx(jxj,rych)
	 * @return true is 合格 false is 不合格
	 * @throws Exception
	 */
	public static boolean pdStuTjFlag(String xh, String xmdm, String xmlx) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String[] jxjsqxnnd = dao.getOneRs(
				"select jxjsqxn,jxjsqnd,jxjsqxq from xtszb", new String[] {},
				new String[] { "jxjsqxn", "jxjsqnd", "jxjsqxq" });//获取奖学金申请学年，年度，学期
		//是否有受处分
		String wjcfNum = dao.getOneRs("select count(*) num from view_wjcf where xh=? and xn=? and xysh='通过' and xxsh='通过'", new String[]{xh, jxjsqxnnd[0]}, "num");
		if (!StringUtils.isNull(wjcfNum) && !StringUtils.isEqual(wjcfNum, "0")) {
			return false;
		}
		List<String[]> tjList= dao.rsToVator(
				"select jxjdm,tjzdm,zdcz,zdbj,tj from jxjhdtj where jxjdm=?",
				new String[] { xmdm }, new String[] { "tjzdm", "zdcz", "zdbj", "tj"});
		StringBuffer query = new StringBuffer("select count(*) num from view_zjjd_zhszcp where xh=? and xn=? and nd=? and xq=? ");
		String query2 = " ";//浙江机电公寓表现扣分及校园表现扣分特殊条件
		if (StringUtils.isEqual(xmlx, "jxj")) {//奖学金申请条件判断
			for (int i = 0; i < tjList.size(); i++) {
				String[] tmpList = tjList.get(i);//取单个条件				
				if (tmpList != null && tmpList.length == 4) {
					String tjzdm = tmpList[0];//条件字段名
					String zdbj = tmpList[2];//字段比较
					String tj   = tmpList[3];//条件
					if(Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(xxdm)){//浙江机电 
						if("zhszcpcjpm".equalsIgnoreCase(tmpList[0].toString())){
							String bjrs = dao.getOneRs("select count(xh) bjrs from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh = ?)", new String []{xh}, "bjrs");
							Float pm =Float.parseFloat(bjrs)*Float.parseFloat(tj)/100;
							
							String mc = dao.getOneRs("select to_char(mc,'999999')mc from (select count(xh)/10 mc,bjdm from view_xsjbxx group by bjdm) where bjdm =(select bjdm from view_xsjbxx where xh='"+xh+"' )",new String[]{},"mc");//根据条件获取排名名称临界点
						    mc = Base.isNull(mc)?"":"100";
							if(">=".equalsIgnoreCase(zdbj)){
								zdbj = "<=";
							}else if("<".equalsIgnoreCase(zdbj)){
								zdbj = ">";
							}
							query.append(" and ");
							query.append(tjzdm+zdbj+pm.toString());
						}else{
							query.append(" and to_number(");
							query.append(tjzdm);
							query.append(") ");
							query.append(zdbj);
							query.append(tj);
						}	
//						//公寓表现扣分及校园表现扣分任意一个扣分达到"tj"分，就不能得奖												
//						if("gybxkf".equalsIgnoreCase(tmpList[0].toString())){
//							if(query2.indexOf("xybxkf")!=-1){
//								query2 +=" or gybxkf "+tmpList[2]+tmpList[3];
//							}else{
//								query2 +=" and (gybxkf "+tmpList[2]+tmpList[3];								
//							}							
//						}else if("xybxkf".equalsIgnoreCase(tmpList[0].toString())){
//							if(query2.indexOf("gybxkf")!=-1){
//								query2 +=" or xybxkf "+tmpList[2]+tmpList[3];								
//							}else{
//								query2 +=" and (xybxkf "+tmpList[2]+tmpList[3];
//							}							
//						}else{
//							query.append(" and to_number(");
//							query.append(tmpList[0]);
//							query.append(") ");
//							query.append(tmpList[2]);
//							query.append(tmpList[3]);
//						}						
					}else{	
						query.append(" and to_number(");
						query.append(tjzdm);
						query.append(") ");
						query.append(zdbj);
						query.append(tj);
					}
				}
			}
//			if(query2.indexOf("(")!=-1){
//				query2+=")";
//			}
			query.append(query2);
			String num = dao.getOneRs(query.toString(), new String[] { xh,
					StringUtils.isNull(jxjsqxnnd[0]) ? "" : jxjsqxnnd[0],
					StringUtils.isNull(jxjsqxnnd[1]) ? "" : jxjsqxnnd[1],
					StringUtils.isNull(jxjsqxnnd[2]) ? "" : jxjsqxnnd[2] },
					"num");
			if (!StringUtils.isNull(num) && StringUtils.isEqual(num, "0")) {
				return false;
			} else {
				return true;
			}
		} else {//荣誉称号申请条件判断
			for (int i = 0; i < tjList.size(); i++) {
				String[] tmpList = tjList.get(i);//取单个条件
				if (tmpList != null && tmpList.length == 4) {
						if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "pxbl")) {
							continue;
						} else if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "dkcj")) {
							String num = dao
								.getOneRs(
										"select count(*) num from cjb where xh=? and xn=? and xq=? and to_number(zpcj2) "+tmpList[2]+" '"
												+ tmpList[3] + "'",
										new String[] { xh, jxjsqxnnd[0],
												jxjsqxnnd[2] }, "num");//单科成绩
							if (!StringUtils.isNull(num) && StringUtils.isEqual(num, "0")) {
								return false;
							}
						} else {
							query.append(" and to_number(");
							query.append(tmpList[0]);
							query.append(") "); 
							query.append(tmpList[2]);
							query.append(tmpList[3]);
						}
				}
			}
			String num = dao.getOneRs(query.toString(), new String[] { xh,
					StringUtils.isNull(jxjsqxnnd[0]) ? "" : jxjsqxnnd[0],
					StringUtils.isNull(jxjsqxnnd[1]) ? "" : jxjsqxnnd[1],
					StringUtils.isNull(jxjsqxnnd[2]) ? "" : jxjsqxnnd[2] },
					"num");
			if (!StringUtils.isNull(num) && StringUtils.isEqual(num, "0")) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * 中国美术奖学金申请备注信息中增加旷课,违纪,成绩信息
	 * @param xh
	 * @param xn
	 * @throws Exception
	 */
	public void updateJxjbz(String xh, String xn, String jxjdm, String tableName, String zd) throws Exception {
		DAO dao = DAO.getInstance();
		String bz = "";
		HashMap<String, String> tmpBz = dao
		.getMapNotOut(
				"select (select count(*) wjrs from view_wjcf a where xn=? and xh=?) wjrs,(select count(*) kkrs from view_pjpy_xskqb a where xn=? and xh=?) kkrs,(select count(*) tyrs from view_pjpy_tydbqkb a where xn=? and xh=? and tydb like '%不及格%') tyrs,(select count(*) bkrs from view_zhhcjb where xn=? and xh=? and (bkcj2 is not null or cxcj2 is not null)) bkrs from dual",
				new String[] { xn, xh, xn, xh, xn,
						xh,xn,xh });
		if (!StringUtils.isNull(tmpBz.get("wjrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("wjrs"))) {//是否有违纪
			bz += xn + "学年该生受过处分,\n" ;		
		} else {
			bz += xn + "学年该生未受处分,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("kkrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("kkrs"))) {//是否有旷课
			bz += xn + "学年该生有旷课记录,\n" ;		
		} else {
			bz += xn + "学年该生无旷课记录,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("tyrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("tyrs"))) {//是否有体育未达标
			bz += xn + "学年该生体育未达标,\n" ;		
		} else {
			bz += xn + "学年该生体育全部达标,\n";
		}
		if (!StringUtils.isNull(tmpBz.get("bkrs"))
				&& !"0".equalsIgnoreCase(tmpBz.get("bkrs"))) {//是否有体育未达标
			bz += xn + "学年该生成绩有补考记录:\n" ;	
			List<String[]> bkcjList = dao
				.rsToVator(
						"select xn,xq,kcmc,kclx,zpcj2,bkcj2,cxcj2 from cjb where xh=? and xn=? and (bkcj2 is not null or cxcj2 is not null)",
						new String[] { xh, xn },
						new String[] { "xn", "xq", "kcmc",
								"kclx", "zpcj2", "bkcj2",
								"cxcj2" });
			
			for (int i=0;i<bkcjList.size();i++) {
				String[] tp = bkcjList.get(i);
				bz += getBzs(tp);
			}
		} else {
			bz += xn + "学年该生成绩无补考记录.\n";
		}
		dao.runUpdate("update "+tableName+" set bz=? where xh=? and xn=? and "+zd+"=?", new String[]{bz, xh, xn, jxjdm});
	}
	
	public String getBzs(String[] bzList) throws Exception {
		String bz = "";
		if (bzList != null && bzList.length==7) {
			if (!StringUtils.isNull(bzList[0])) {
				bz += bzList[0] + "学年第";
			}
			if (!StringUtils.isNull(bzList[1])) {
				bz += bzList[1] + "学期 ";
			}
			if (!StringUtils.isNull(bzList[2])) {
				bz += " 课程("+bzList[2]+")";
			}
			if (!StringUtils.isNull(bzList[3])) {
				bz += " 类型("+bzList[3]+")";
			}
			if (!StringUtils.isNull(bzList[4])) {
				bz += " 成绩("+bzList[4]+")";
			}
			if (!StringUtils.isNull(bzList[5])) {
				bz += " 补考成绩("+bzList[5]+")";
			}
			if (!StringUtils.isNull(bzList[6])) {
				bz += " 重修成绩("+bzList[6]+").";
			}
			bz += "\n";
		}
		return bz;
	}
	
	public static void main(String[] args) throws Exception {
		long s = 45;
		if (s>10) {
			System.out.println("aa");
		}
	}
}