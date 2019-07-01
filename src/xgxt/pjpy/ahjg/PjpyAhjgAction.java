package xgxt.pjpy.ahjg;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

import common.Globals;
import common.GlobalsVariable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院评奖评优Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class PjpyAhjgAction extends DispatchAction {
	
	private String xydm = "";//学院代码
	private String zydm = "";//专业代码
	private String nj = "";//年级
	private CommonAction comAct = null;

	/**
	 * 信息维护学生成绩查询默认页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxscjDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		
		//学生用户只能查询自己的学生成绩
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			//TODO 暂时先用无权访问期该页面，有时间再把学生成绩查询做进去
			request.setAttribute("message", "您无权访问该页面!");
			return new ActionForward("/prompt.do",false);
		}
		
		
		
		if (StringUtils.isNull(ahjgForm.getXn())) {
			ahjgForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(ahjgForm.getXq())) {
			ahjgForm.setXq(Base.getJxjsqxq());
		}
		
		String xxdm = StandardOperation.getXxdm();
		String jwflag = service.getJwFlag();//查询教务版本,1为学年,0为过渡,否则为其他公司教务
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {//教务是过渡,通过WEBSERVICE同步学生成绩绩点
			return new ActionForward("/pjpy_xnmz_xscjwh.do", false);
		}
//		if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
//				|| Globals.XXDM_ZJJJZYJSXY.equalsIgnoreCase(xxdm)
//				|| Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)
//				|| Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)) {// 教务是学年
//			return new ActionForward("/pjpy_zgms_cjwh.do", false);
//		}
		if ("1".equalsIgnoreCase(jwflag)) {// 教务是学年
			return new ActionForward("/pjpy_zgms_cjwh.do", false);
		}
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
					userType = "bzr";
				}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
			ahjgForm.setXydm(xydm);
		} else {
			xydm = ahjgForm.getXydm();
		}
		zydm = ahjgForm.getZydm();
		nj = ahjgForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(ahjgForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
			} else if ("djksb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}
		
		// -------------2010/5/24 edit by luojw --------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		// -------------end --------------
		request.setAttribute("path", "pjpy_ahjg_xscjdefault.do");
		FormModleCommon.commonRequestSet(request);
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
		return mapping.findForward("pjpyahjgxscjdefault");
	}

	/**
	 * 信息维护学生成绩查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxscjQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String xh = DealString.toGBK(ahjgForm.getXh());
		String xm = DealString.toGBK(ahjgForm.getXm());
		PjpyAhjgXscjQryModel pjpyahjgxscjqryModel = new PjpyAhjgXscjQryModel();//学生成绩查询MODEL
		List<String[]> listRs = new ArrayList<String[]>();//学生成绩查询结果
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();//学生成绩查询表头
		PjpyAhjgService service = new PjpyAhjgService();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString(): "";
		
		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
				userType = "bzr";
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
					ahjgForm.setXydm(xydm);
		} else {
			xydm = ahjgForm.getXydm();
		}
		BeanUtils.copyProperties(pjpyahjgxscjqryModel, ahjgForm);
		if (!StringUtils.isNull(ahjgForm.getCjlx()) && StringUtils.isEqual(ahjgForm.getCjlx(), "cjb")) {
			if (Globals.XXDM_YCSFXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(7);//传入7则输出学生成绩查询表头
			}else if (Globals.XXDM_AHZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(8);//传入7则输出学生成绩查询表头
			}else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(9);//传入7则输出学生成绩查询表头
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// 广州大学
				listTopTr = service.getSearchTitle(10);
			} else {
				listTopTr = service.getSearchTitle(1);// 传入1则输出学生成绩查询表头
			}
			listRs = service.getPjpyXscjResult(pjpyahjgxscjqryModel, ahjgForm,userType,userName);
			int count = service.getPjpyXscjResultNum(pjpyahjgxscjqryModel,userType,userName);
			ahjgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		} else {
			listTopTr = service.getSearchTitle(6);//传入6则输出学生等级考试查询表头
			listRs = service.getPjpyDjkscjResult(pjpyahjgxscjqryModel, ahjgForm);
			int count = service.getPjpyDjkscjResultNum(pjpyahjgxscjqryModel);
			ahjgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		
		zydm = ahjgForm.getZydm();
		nj = ahjgForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(ahjgForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
				if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// 广州大学
					tableName = "view_gzdx_cjb";
				}
			} else if ("djksb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}
		
		// -------------2010/5/24 edit by luojw --------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				String[] kcxz = pjpyahjgxscjqryModel.getKcxz();
				String checked_Kcxz = "";
				if (kcxz != null && kcxz.length > 0) {
					for (int i = 0; i < kcxz.length; i++) {
						checked_Kcxz += kcxz[i] + "!!@@!!";
					}
				}
				request.setAttribute("checked_Kcxz", checked_Kcxz);
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		// -------------end --------------
		
		request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
		appendProperties(request, xydm, zydm, nj);
		ahjgForm.setXh(xh);
		ahjgForm.setXm(xm);
		request.setAttribute("topTr", listTopTr);
		request.setAttribute("rs", listRs);
		request.setAttribute("rsNum", listRs != null ? listRs.size() : 0);
		return mapping.findForward("pjpyahjgxscjdefault");
	}
	
	/**
	 * 学生成绩,等级考试成绩与教务数据同步
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ahjgcjTb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String cjlx = request.getParameter("cjlx");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		boolean bFlag = service.cjTb(cjlx, xn, xq);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(ahjgForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
			} else if ("djksb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
			}
		}
		request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		return mapping.findForward("pjpyahjgxscjdefault");
	}
	
	/**
	 * 荣誉申诉默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgrysqDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> rysqList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		rysqList = service.getRysqList(1);//得到荣誉申请列表信息
		request.setAttribute("list", rysqList);
		return mapping.findForward("pjpyahjgrysqdefault");
	}
	
	/**
	 * 先进集体申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxsjtSq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titleList = service.getRysqList(4);
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		appentProperties1(request, xydm, zydm, nj);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		return mapping.findForward("pjpyahjgxsjtsq");
	}
	
	/**
	 * 先进班级申请,先验证数据是否重复
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxjbjSq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjBjSqModel xjbjSqModel = new XjBjSqModel();//先进班级申请MODEL
		BeanUtils.copyProperties(xjbjSqModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titleList = service.getRysqList(4);
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		boolean bChk = service.chkDataByXjbj(xjbjSqModel);//验证数据是否重复
		if (bChk) {//存在
			request.setAttribute("result", "haved");
		}else {//不存在
			boolean bFlag = service.saveXjBjInfo(xjbjSqModel, request);//保存数据
			if (bFlag) {
				request.setAttribute("result", "true");
			}else{
				request.setAttribute("result", "false");
			}//end if
		}//end if
		
		appentProperties1(request, xydm, zydm, nj);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		ahjgForm.setBzr(DealString.toGBK(ahjgForm.getBzr()));
		ahjgForm.setBzxm(DealString.toGBK(ahjgForm.getBzxm()));
		ahjgForm.setZysj(DealString.toGBK(ahjgForm.getZysj()));
		return mapping.findForward("pjpyahjgxsjtsq");
	}
	
	/**
	 * 公用方法:在REQUEST中存放页面所要加载的LIST属性
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
		xy = StringUtils.isNull(xy) ? "" : xy;
		zy = StringUtils.isNull(zy) ? "" : zy;
		nj = StringUtils.isNull(nj) ? "" : nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		/*String realTable = "pjpy_bjbklb";
		String tableName = "pjpy_bjbklb";*/
		String userType = request.getSession().getAttribute("userType").toString();
		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
			userType = "bzr";
		}
		request.setAttribute("writeAble", "yes");//判断用户读写权
		/*request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名*/
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
		if (request.getSession().getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(request.getSession().getAttribute("fdyQx")
						.toString())) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(request.getSession().getAttribute("userName").toString()));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(request.getSession().getAttribute("userName").toString()));// 发送班级列表
		}
	}
	
	public void appentProperties1(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String zyKey = "";
		String bjKey = "!!!!";
		if("xy".equalsIgnoreCase(userType)){
			zyKey = userDep;
			bjKey = zyKey+"!!"+""+"!!"+"";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}

	/**
	 * 班级补考率默认页面
	 * bjbkl ---- 班级补考率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		appendProperties(request, xydm, zydm, nj);
		
		request.setAttribute("tableName", "pjpy_bjbklb");//查询多为视图名
		request.setAttribute("realTable", "pjpy_bjbklb");//表名
		return mapping.findForward("bjbkldefault");
	}
	
	/**
	 * 早操出勤率默认页面
	 * zccql ---- 早操出勤率 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		appendProperties(request, xydm, zydm, nj);
		//appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjzccqlb");//查询多为视图名
		request.setAttribute("realTable", "pjpy_bjzccqlb");//表名
		return mapping.findForward("zccqldefault");
	}

	/**
	 * 班级补考率查询
	 * bjbklqry ---- 班级补考率查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		appendProperties(request, xydm, zydm, nj);
		PjpyAhjgXscjQryModel pjpyahjgxscjqryModel = new PjpyAhjgXscjQryModel();//班级补考率查询MODEL
		BeanUtils.copyProperties(pjpyahjgxscjqryModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titList = service.getSearchTitle(2);//班级补考率查询表头
		List<String[]> resList = service.getBjbklQryResult(pjpyahjgxscjqryModel);//查询结果
		
		//appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", titList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("tableName", "pjpy_bjbklb");//查询多为视图名
		request.setAttribute("realTable", "pjpy_bjbklb");//表名
		return mapping.findForward("bjbkldefault");
	}

	/**
	 * 班级补考率增加
	 * bjbkladd ---- 班级补考率增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appendProperties(request, xydm, zydm, nj);
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("bjbkladd");
	}
	
	/**
	 * 班级补考率保存
	 * bjbklsave ---- 班级补考率保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  bjbklSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		//String bjdm = request.getParameter("bjdm");
		BjbklSaveModel bjbklSaveModel = new BjbklSaveModel();//班级补考率保存MODEL
		BeanUtils.copyProperties(bjbklSaveModel, ahjgForm);
		//bjbklSaveModel.setBjdm(bjdm);
		PjpyAhjgService service = new PjpyAhjgService();
		boolean bChk = service.chkBjbkl(bjbklSaveModel);//保存前检查数据是否重复
		if (bChk) {//重复
			request.setAttribute("inserted", "haved");
		} else {//不重复
			boolean bFlag = service.saveBjbkl(bjbklSaveModel, request);//保存数据
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			}else {
				request.setAttribute("inserted", "no");
			}//end if
		}//end if
		ahjgForm.setBzxm(DealString.toGBK(ahjgForm.getBzxm()));
		ahjgForm.setBzr(DealString.toGBK(ahjgForm.getBzr()));
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("bjbkladd");
	}
	
	/**
	 * 班级补考率修改信息显示
	 * bjbklmodi ---- 班级补考率修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.getBjbklByPk(sPk);
		String bjbkl = resMap.get("bjbkl");
		bjbkl = !StringUtils.isNull(bjbkl) ? bjbkl.substring(0, bjbkl.length()-1): "";
		resMap.put("bjbkl", bjbkl);
		request.setAttribute("rs", resMap);
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("bjbklmodi");
	}
	
	/**
	 * 班级补考率修改信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklModi1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		String bzxm = DealString.toGBK(request.getParameter("bzxm"));
		String bzr = DealString.toGBK(request.getParameter("bzr"));
		String xsrs = request.getParameter("xsrs");
		String bjbkl = request.getParameter("bjbkl");
		PjpyAhjgService service = new PjpyAhjgService();
		BjbklSaveModel bjbklSaveModel = new BjbklSaveModel();
		bjbklSaveModel.setBzxm(bzxm);
		bjbklSaveModel.setBzr(bzr);
		bjbklSaveModel.setXsrs(xsrs);
		bjbklSaveModel.setBjbkl(bjbkl);
		boolean bFlag = service.updateBjbkl(sPk, bjbklSaveModel, request);//更新数据
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		}else {//失败
			request.setAttribute("inserted", "no");
		}//end if
		
		HashMap<String, String> resMap = service.getBjbklByPk(sPk);//重新获取信息
		request.setAttribute("pkValue", sPk);
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("bjbklmodi");
	}
	
	/**
	 * 班级补考率批量删除
	 * bjbkldel ---- 班级补考率批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		String[] pkList = ahjgForm.getCbv();
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.delBjbkl(pkList);
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjbklb");//查询多为视图名
		request.setAttribute("realTable", "pjpy_bjbklb");//表名
		return mapping.findForward("bjbkldefault");
	}
	
	/**
	 * 早操出勤率查询
	 * zccqlqry ---- 早操出勤率查询 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		ZccqlQueryModel zccqlQryModel = new ZccqlQueryModel();//早操出勤率查询MODEL
		BeanUtils.copyProperties(zccqlQryModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titList = service.getSearchTitle(3);//查询表头
		List<String[]> resList = service.getZccqlResult(zccqlQryModel);//查询结果
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjzccqlb");//查询多为视图名
		request.setAttribute("realTable", "pjpy_bjzccqlb");//表名
		request.setAttribute("topTr", titList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//
		return mapping.findForward("zccqldefault");
	}

	/**
	 * 早操出勤率增加
	 * zccqladd ---- 早操出勤率增加 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appendProperties(request, xydm, zydm, nj);
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("zccqladd");
	}
	
	/**
	 * 早操出勤率保存
	 * zccqlsave ---- 早操出勤率保存 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		ZccqlSaveModel zccqlSaveModel = new ZccqlSaveModel();//早操出勤保存MODEL
		PjpyAhjgService service = new PjpyAhjgService();
		BeanUtils.copyProperties(zccqlSaveModel, ahjgForm);
		boolean bChk = service.chkZccql(zccqlSaveModel);//检查数据是否重复
		if (bChk) {
			request.setAttribute("inserted", "haved");//存在
		}else {
			boolean bFlag = service.saveZccql(zccqlSaveModel, request);//数据保存
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			}else {
				request.setAttribute("inserted", "no");
			}//end if
		}//end if
		
		ahjgForm.setBzr(DealString.toGBK(ahjgForm.getBzr()));
		ahjgForm.setBzxm(DealString.toGBK(ahjgForm.getBzxm()));
		appendProperties(request, xydm, zydm, nj);
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("zccqladd");
	}
	
	/**
	 * 早操出勤率信息显示
	 * zccqlview ---- 早操出勤率信息显示 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.getZccqlByPk(sPk);
		String zccql = resMap.get("zccql");
		zccql = !StringUtils.isNull(zccql) ? zccql.substring(0, zccql.length() - 1) : "";
		resMap.put("zccql", zccql);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", sPk);
		request.setAttribute("rs", resMap);
		return mapping.findForward("zccqlview");
	}
	
	/**
	 * 早操出勤率信息修改
	 * zccqlmodi ---- 早操出勤率信息修改 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		ZccqlSaveModel zccqlSaveModel = new ZccqlSaveModel();
		String bzxm = DealString.toGBK(request.getParameter("bzxm"));
		String bzr = DealString.toGBK(request.getParameter("bzr"));
		String xsrs = request.getParameter("xsrs");
		String zccql = request.getParameter("zccql");
		zccqlSaveModel.setBzr(bzr);
		zccqlSaveModel.setBzxm(bzxm);
		zccqlSaveModel.setXsrs(xsrs);
		zccqlSaveModel.setZccql(zccql);
		PjpyAhjgService service = new PjpyAhjgService();
		boolean bFlag = service.updateZccql(sPk, zccqlSaveModel, request);//修改数据
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		}else {//失败
			request.setAttribute("inserted", "no");
		}
		
		HashMap<String, String> resMap = service.getZccqlByPk(sPk);//重新获取信息
		request.setAttribute("pkValue", sPk);
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zccqlview");
	}

	/**
	 * 早操出勤率删除
	 * zccqldel ---- 早操出勤率删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		String[] pkList = ahjgForm.getCbv();
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.delZccql(pkList);//批量删除
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjzccqlb");//查询多为视图名
		request.setAttribute("realTable", "pjpy_bjzccqlb");//表名
		return mapping.findForward("zccqldefault");
	}


	/**
	 * 荣誉称号审核默认页面
	 * rychshdefault ---- 荣誉称号审核默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychShDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> rysqList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		rysqList = service.getRysqList(1);//得到荣誉申请列表信息
		request.setAttribute("list", rysqList);
		return mapping.findForward("rychshdefault");
	}
	
	/**
	 * 先进集体审核查询默认页面
	 * xjjtshdefault ---- 先进集体审核查询默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtShDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				: session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		} else {
			xydm = ahjgForm.getXydm();
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//审核项目列表
		ahjgForm.setShxm("xjbj");
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtshdefault");
	}
	
	/**
	 * 先进集体审核查询
	 * xsjtqry ---- 先进集体审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjtQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjBjQryModel xjbjQryModel = new XjBjQryModel();//先进班级查询MODEL
		
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String userName       = session.getAttribute("userName").toString();
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		}  else {
			xydm = ahjgForm.getXydm();
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		BeanUtils.copyProperties(xjbjQryModel, ahjgForm);
		xjbjQryModel.setShxm("xjbj");
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> topList = service.getXjBjTitle(xjbjQryModel, sUserType, sIsFdy);
		List<String[]> resList = service.getXjBjResult(xjbjQryModel, sUserType, sIsFdy, userName);

		/*if (StringUtils.isEqual(sShxm, "xjbj")) {
			//request.setAttribute("xjbj", "先进班级比例为全校班级数的6%");
		}else {
			//request.setAttribute("xjbj", "文明宿舍比例为全校宿舍数3%");
		}*/
		String sBjs = service.getBjzs();//班级评比比例
		sBjs = "先进班级的评比不应超过" + sBjs +"个!";
		shxmList = service.getRysqList(2);
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		ahjgForm.setShxm("xjbj");
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("bjzs", sBjs);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtshdefault");
	}
	
	/**
	 * 先进集体审核
	 * xjjtsh ---- 先进集体审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtSh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sRes  = request.getParameter("param1");
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjjtShModel xjjtShModel = new XjjtShModel();
		BeanUtils.copyProperties(xjjtShModel, ahjgForm);
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		PjpyAhjgService service = new PjpyAhjgService();
		xjjtShModel.setShxm("xjbj");
		String res = service.submitShResult(sRes, sUserType, sIsFdy, xjjtShModel, request);//提交审核结果
		if (StringUtils.isNull(res)) {
			request.setAttribute("inserted", "view");
		} else {
			request.setAttribute("failinfo", String.format("提示：其中第 %1$s 条数据操作失败，原因是已超评比班级数！", res));
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		shxmList = service.getRysqList(2);
		ahjgForm.setShxm("xjbj");
		
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		//request.setAttribute("result", "view");
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtshdefault");
	}
	
	/**
	 * 先进集体单个审核显示
	 * xjjtshview ---- 先进集体单个审核显示 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtshView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		String sPkValue = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resList = service.getXjBjResultByOne(sPkValue, sUserType, sIsFdy);
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		shxmList = service.getChkList(3);
		String sBjCfrs = service.getBjcfRs(resList.get("bjdm"), resList.get("xn"));//获取学年内班级违纪人数
		
		request.setAttribute("bjcfrs", sBjCfrs);
		request.setAttribute("rs", resList);
		request.setAttribute("shxmList", shxmList);
		ahjgForm.setShxm(resList.get("sh"));
		ahjgForm.setShyj(resList.get("yj"));
		request.setAttribute("pkValue", sPkValue);
		return mapping.findForward("xjjtshview");
	}
	
	/**
	 * 先进集体单个审核、
	 * shjgbyone ---- 先进集体单个审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjgByOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPkValue = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjjtShModel xjjtshModel = new XjjtShModel();
		BeanUtils.copyProperties(xjjtshModel, ahjgForm);
		xjjtshModel.setPkValue(sPkValue);
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		PjpyAhjgService service = new PjpyAhjgService();
		
		int tg = service.getXjbjShjg(request);//已通过班级数
		String sBjs = service.getBjzs();//班级评比比例
		int xzs = StringUtils.isNull(sBjs) ? 0 : Integer.parseInt(sBjs);
		
		if (!StringUtils.isNull(xjjtshModel.getShxm()) && StringUtils.isEqual(DealString.toGBK(xjjtshModel.getShxm()), "通过")) {
			if ((xzs != 0) && (tg>=xzs)) {
				request.setAttribute("inserted", "cb");
			} else {
				boolean bFlag = service.saveXjjtOne(xjjtshModel, sUserType, sIsFdy, request);//保存结果
				if (bFlag){//成功
					request.setAttribute("inserted", "yes");
				}else {//失败
					request.setAttribute("inserted", "no");
				}
			}
		} else {
			boolean bFlag = service.saveXjjtOne(xjjtshModel, sUserType, sIsFdy, request);//保存结果
			if (bFlag){//成功
				request.setAttribute("inserted", "yes");
			}else {//失败
				request.setAttribute("inserted", "no");
			}
		}
		
		
		HashMap<String, String> resList = service.getXjBjResultByOne(sPkValue, sUserType, sIsFdy);
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		shxmList = service.getChkList(3);
		String sBjCfrs = service.getBjcfRs(resList.get("bjdm"), resList.get("xn"));//获取学年内班级违纪人数
		request.setAttribute("bjcfrs", sBjCfrs);
		
		request.setAttribute("rs", resList);
		request.setAttribute("shxmList", shxmList);
		ahjgForm.setShyj(DealString.toGBK(ahjgForm.getShyj()));
		request.setAttribute("pkValue", sPkValue);
		return mapping.findForward("xjjtshview");
	}

	/**
	 * 荣誉称号结果查询默认页面
	 * rychjgcxmr ---- 荣誉称号结果查询默认 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychjgCxMr(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> rysqList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		rysqList = service.getRysqList(1);//得到荣誉申请列表信息
		request.setAttribute("list", rysqList);
		return mapping.findForward("rychjgcxmr");
	}
	
	/**
	 * 先进班级结果查询默认页面
	 * xjjtjgcx ---- 先进班级结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtJgCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//审核项目列表
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
//		String isFdy         = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		ahjgForm.setShxm("xjbj");
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//表名
		request.setAttribute("tableName", "pjpy_xjbjandwmsqb");//视图名 
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtjgcx");
	}
	
	/**
	 * 先进班级查询结果
	 * xjbjcxjg ---- 先进班级查询结果 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjCxJg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjBjQryModel xjbjqryModel = new XjBjQryModel();
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		BeanUtils.copyProperties(xjbjqryModel, ahjgForm);
		xjbjqryModel.setShxm("xjbj");
		List<String[]> resList = service.xjbjCxJg(xjbjqryModel, sIsFdy, userName);//查询结果
		List<HashMap<String, String>> topList = service.xjbjJgCxBt();//查询表头
		List<HashMap<String, String>> shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//审核项目列表
		ahjgForm.setShxm("xjbj");
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("ag", ahjgForm);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//表名
		request.setAttribute("tableName", "pjpy_xjbjandwmsqb");//视图名
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		return mapping.findForward("xjjtjgcx");
	}
	
	/**
	 * 单个信息显示
	 * modiview ---- 修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModiView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.xjbjXxByPk(sPk);//通过主键获取相关班级信息
		
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPk);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xjbjmodiview");
	}
	
	/**
	 * 先进班级单个修改
	 * xjbjmodi ---- 先进班级修改 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		XjBjSqModel xjbjsqModel = new XjBjSqModel();
		xjbjsqModel.setBzr(DealString.toGBK(request.getParameter("bzr")));
		xjbjsqModel.setBzxm(DealString.toGBK(request.getParameter("bzxm")));
		xjbjsqModel.setXsrs(request.getParameter("xsrs"));
		xjbjsqModel.setZysj(DealString.toGBK(request.getParameter("zysj")));
		boolean bFlag = service.bcxjbjJg(xjbjsqModel, sPk, request);//修改结果
		HashMap<String, String> resMap = service.xjbjXxByPk(sPk);//通过主键获取相关班级信息
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		}else {//失败
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPk);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xjbjmodiview");
	}
	
	/**
	 * 先进班级批量删除
	 * xjbjsqdel ---- 先进班级批量删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjsqDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String[] pkList = ahjgForm.getCbv();//主键列表
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.delXjbjXx(pkList);//批量删除
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		ahjgForm.setShxm("xjbj");
		List<HashMap<String, String>> shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//审核项目列表
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//表名
		request.setAttribute("tableName", "pjpy_xjbjandwmsqb");//视图名
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtjgcx");
	}
	
	/**
	 * 先进集体评比分配表
	 * xjjtpbfbb ---- 先进集体评比分配表 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*public ActionForward xjjtPbfbb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		List<String[]> resList = service.getBjzs();//结果
		List<HashMap<String, String>> topList = service.getSearchTitle(4);//表头
		request.setAttribute("rs", resList);
		request.setAttribute("topTr", topList);
		request.setAttribute("jxjsqxn", Base.currXn);//当前学年
		return mapping.findForward("xjjtpbfbb");
	}*/
	
	/**
	 * 先进个人评比分配表
	 * xjgrfbb ---- 先进个人评比分配表 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrFbb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> topList = service.getSearchTitle(5); //表头
		List<String[]> resList = service.getBjzsByGr();//结果
		String[] jxjSqxnnd = service.getJxjsqxn();//奖学金申请学年年度
		request.setAttribute("xjgrsqxn", jxjSqxnnd != null ? jxjSqxnnd[0] : "");//当前学年
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		return mapping.findForward("xjgrfbb");
	}
	
	/**
	 * 先进个人审核默认页面
	 * xjgrshdefault ---- 先进个人审核默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrShDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
//		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		List<HashMap<String, String>> rychList = service.getRychList();
//		String[] jxjSqxnnd = service.getJxjsqxn();//奖学金申请学年年度
		/*ahjgForm.setXn(jxjSqxnnd != null ? jxjSqxnnd[0] : "");//当前学年
		ahjgForm.setNd(jxjSqxnnd != null ? jxjSqxnnd[1] : "");//当前年度*/
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("tableName", "view_xsrychb");
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("pk", "xn||nd||xh||rychdm");
		request.setAttribute("rychList", rychList);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjgrshdefault");
	}
	
	/**
	 * 先进个人审核查询页面
	 * xjgrshqry ---- 先进个人审核查询 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrShQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjgrQryModel xjgrqryModel = new XjgrQryModel();
		
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String userName       = session.getAttribute("userName").toString();
		String xydm = "";
		//用户是学院时
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		BeanUtils.copyProperties(xjgrqryModel, ahjgForm);
		List<HashMap<String, String>> rychList = service.getRychList();//荣誉称号列表
//		String[] jxjSqxnnd = service.getJxjsqxn();//奖学金申请学年年度
		//ahjgForm.setXn(jxjSqxnnd != null ? jxjSqxnnd[0] : "");//当前学年
		//ahjgForm.setNd(jxjSqxnnd != null ? jxjSqxnnd[1] : "");//当前年度
		List<HashMap<String, String>> topList = service.getXjgrTitle(sUserType, sIsFdy);//表头
		List<String[]> resList = service.getXjgrResult(sUserType, sIsFdy, xjgrqryModel, sIsFdy, userName);//结果
		
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("tableName", "view_xsrychb");
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("pk", "xn||nd||xh||rychdm");
		request.setAttribute("rychList", rychList);
		request.setAttribute("ag", ahjgForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		return mapping.findForward("xjgrshdefault");
	}
	
	/**
	 * 先进个人单个审核显示
	 * xjgfshone ---- 先进个人单个审核显示 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrShOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		HashMap<String, String> resMap = service.getXjgrByPk(sUserType, sIsFdy, pkValue);//获取单个信息
		List<HashMap<String, String>> chkList = service.getChkList(3);//审核列表
		HashMap<String, String> xspjfmcList = service.getXsPsxxb(xh, xn);//学生评审信息表
		if (xspjfmcList != null) {
			resMap.put("pjf", xspjfmcList.get("pjf"));
			resMap.put("mc", xspjfmcList.get("mc"));
			resMap.put("drzw", xspjfmcList.get("drzw"));
			resMap.put("xxjsmc", xspjfmcList.get("xxjsmc"));
			resMap.put("bjrychmc", xspjfmcList.get("bjrychmc"));
			String cet4 = xspjfmcList.get("cet4");//英语四级是否通过
			String jsjej = xspjfmcList.get("jsjej");//计算机二级是否通过
			cet4 = (!StringUtils.isNull(cet4)) && StringUtils.isEqual(cet4, "tg") ? "通过" : "未通过";
			jsjej = (!StringUtils.isNull(jsjej)) && StringUtils.isEqual(jsjej, "tg") ? "通过" : "未通过";
			resMap.put("cet4", cet4);
			resMap.put("jsjej", jsjej);
			resMap.put("wmss", xspjfmcList.get("wmss"));
		}
		String[] dfAndPmList = service.getStudfAndPm(xh);//获取该生的十佳大学生得分及排名
		if (dfAndPmList != null && dfAndPmList.length == 2) {
			resMap.put("pm", dfAndPmList[0]);
			resMap.put("df", dfAndPmList[1]);
		}
		ahjgForm.setYesNo(resMap.get("yesno"));
		request.setAttribute("chkList", chkList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjgrshone");
	}
	
	/**
	 * 先进个人单个审核
	 * xjgrshbyone ---- 先进个人单个审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrshByOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String rychdm = request.getParameter("rychdm");
		String oldxh = request.getParameter("oldxh");
		String yesNo = request.getParameter("yesNo");
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String sUserType        = session.getAttribute("userType").toString();//用户类型
		String sIsFdy         = session.getAttribute("isFdy").toString();//辅导员
		String sFlag = service.xjgrshByOne(sUserType, sIsFdy, yesNo, pkValue, request, rychdm, oldxh);//审核(辅导员,学院,学校)
		HashMap<String, String> resMap = service.getXjgrByPk(sUserType, sIsFdy, pkValue);//获取单个信息
		List<HashMap<String, String>> chkList = service.getChkList(3);//审核列表
		ahjgForm.setYesNo(resMap.get("yesNo"));
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		}else {
			request.setAttribute("failInfo", sFlag);//错误信息
			request.setAttribute("inserted", "no");
		}//end if
		
		request.setAttribute("chkList", chkList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjgrshone");
	}
	
	/**
	 * 学习竞赛默认页面
	 * xxjsdefault ---- 学习竞赛默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("realTable", "xsxxjsb");
		request.setAttribute("tableName", "xsxxjsb");
		return mapping.findForward("xxjsdefault");
	}
	
	/**
	 * 学习竞赛查询
	 * xsxxjsqry ---- 学生学习竞赛查询 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsxxjsQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		XxjsQryModel xxjsqryModel = new XxjsQryModel();//学习竞赛查询MODEL
		BeanUtils.copyProperties(xxjsqryModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> topList = service.getXxjsTitle();//表头
		List<String[]> resList = service.getXxjsResult(xxjsqryModel);//结果
		
		ahjgForm.setXh(DealString.toGBK(ahjgForm.getXh()));
		ahjgForm.setXm(DealString.toGBK(ahjgForm.getXm()));
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("topTr", topList);
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("realTable", "xsxxjsb");
		request.setAttribute("tableName", "xsxxjsb");
		return mapping.findForward("xxjsdefault");
	}
	
	/**
	 * 学习竞赛增加
	 * xxjsadd ---- 学习竞赛增加 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = service.getXsInfo(xh);//通过学号获取学生姓名，学院，专业，班级
		List<HashMap<String, String>> xxjsList = service.getXxjSList();//竞赛列表
		request.setAttribute("xxjsList", xxjsList);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", resMap);
		return mapping.findForward("xxjsadd");
	}
	
	/**
	 *  学习竞赛信息保存
	 *  savexxjsxx ---- 学习竞赛信息保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXxjsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XsjxSaveModel xsjxsaveModel = new XsjxSaveModel();//保存MODEL
		BeanUtils.copyProperties(xsjxsaveModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		boolean bFlag = service.saveXxjsInfo(xsjxsaveModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		}else {
			request.setAttribute("inserted", "no");
		}
		
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = service.getXsInfo(xh);//通过学号获取学生姓名，学院，专业，班级
		List<HashMap<String, String>> xxjsList = service.getXxjSList();//竞赛列表
		ahjgForm.setBz(DealString.toGBK(ahjgForm.getBz()));
		request.setAttribute("xxjsList", xxjsList);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", resMap);
		return mapping.findForward("xxjsadd");
	}
	
	/**
	 * 学习竞赛修改显示
	 * xxjsModi ----学习竞赛修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//主键
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.getXxjsInfoByPk(pkValue);//通过PK获取信息
		List<HashMap<String, String>> xxjsList = service.getXxjSList();//竞赛列表
		request.setAttribute("xxjsList", xxjsList);
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xxjsmodi");
	}
	
	/**
	 * 学习竞赛批量删除
	 * xxjsdel ---- 学习竞赛删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String[] pkList = ahjgForm.getCbv();//主键列表
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.xxjsDel(pkList);//批量删除
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsxxjsb");
		request.setAttribute("tableName", "xsxxjsb");
		return mapping.findForward("xxjsdefault");
	}
	
	/**
	 * 奖学金证书单个打印，打印测试页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjzsprint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String xxdm = StandardOperation.getXxdm();
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxq = DealString.toGBK(request.getParameter("hjxq"));
		String hjxjdj = DealString.toGBK(request.getParameter("hjxjdj"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "";//获奖起始学年
		String hjjsxn = "";//获奖结束学年
		String fzxn = "";//发证年
		String fzy = "";//发证月
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";
		}else if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(2, 4);
			hjjsxn = hjxn.substring(7, 9);
		}
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			 if (hjny.indexOf("-") != -1) {
				 fzxn = hjny.substring(0, hjny.indexOf("-"));
				 fzy = hjny.substring(hjny.indexOf("-")+1, hjny.length());
			 }
		} else {
			if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==6) {
				 fzxn = hjny.substring(0, 4);
				 fzy = hjny.substring(4, 6);
			}else if (!StringUtils.isNull(hjny) && hjny.length()==7) {
				fzxn = hjny.substring(0, 4);
				fzy = hjny.substring(5, 7);
			}
		}
		String[] topleftList = service.getTopLeftStr("allschool");
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];
			leftstr = topleftList[1];
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("hjxjdj", hjxjdj);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxq", hjxq);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		return mapping.findForward("jxjzsprint");
	}
	
	/**
	 * 奖学金证书连打
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjzsPrintMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String xxdm = StandardOperation.getXxdm();
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxq = DealString.toGBK(request.getParameter("hjxq"));
		String hjxjdj = DealString.toGBK(request.getParameter("hjxjdj"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "";//获奖起始学年
		String hjjsxn = "";//获奖结束学年
		String fzxn = "";//发证年
		String fzy = "";//发证月
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";
		}else if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(2, 4);
			hjjsxn = hjxn.substring(7, 9);
		}
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			if (hjny.indexOf("-") != -1) {
				 fzxn = hjny.substring(0, hjny.indexOf("-"));
				 fzy = hjny.substring(hjny.indexOf("-")+1, hjny.length());
			 }
		} else {
			if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==6) {
				 fzxn = hjny.substring(0, 4);
				 fzy = hjny.substring(4, 6);
			}else if (!StringUtils.isNull(hjny) && hjny.length()==7) {
				fzxn = hjny.substring(0, 4);
				fzy = hjny.substring(5, 7);
			}
		}
		String[] topleftList = service.getTopLeftStr("allschool");
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];
			leftstr = topleftList[1];
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjxn", hjxn);
		request.setAttribute("hjny", hjny);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("hjxjdj", hjxjdj);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxq", hjxq);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		return mapping.findForward("jxjzsprintmore");
	}
	
	/**
	 * 参数设置条件设置首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		//根据参数类型不同加载不同的项目列表
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> resList = new ArrayList<String[]>(); 
		String xxdm = StandardOperation.getXxdm();
		
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//项目列表
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				String tjdm = request.getParameter("tjdm");
				if (!StringUtils.isNull(tjdm) && "zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(3);//字段比较列表
					request.setAttribute("fsfs", "pm");
				} else if (!StringUtils.isNull(tjdm) && !"zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(4);//字段比较列表
				} else {
					zdbjList = service.getZdbjList(2);//字段比较列表
				}
				tjList = service.getJxjTjList(4);//字段条件列表
			}else {
				tjList = service.getJxjTjList(0);//字段条件列表
				zdczList = service.getZdczList(0);//字段操作列表
				zdbjList = service.getZdbjList(0);//字段比较列表
			}			
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//项目列表
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				tjList = service.getJxjTjList(5);//字段条件列表
				zdbjList = service.getZdbjList(1);//字段比较列表
			}else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);//字段比较列表
				tjList = service.getJxjTjList(1);//字段条件列表
				zdczList = service.getZdczList(1);//字段操作列表
				
			}else {
				tjList = service.getJxjTjList(1);//字段条件列表
				zdczList = service.getZdczList(1);//字段操作列表
				zdbjList = service.getZdbjList(0);//字段比较列表
			}
			
			request.setAttribute("rych", "2");
		}
		if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			zdbjList = service.getZdbjList(1);//字段比较列表
			tjList = service.getJxjTjList(3);//字段条件列表
		} 
		topList = service.getTjTitle();
		request.setAttribute("topTr", topList);
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		if (!StringUtils.isNull(ahjgForm.getXmdm())) {
			resList = service.getTjResult(ahjgForm.getXmdm());
		}
		request.setAttribute("rs", resList);
		appendProperties(request, xydm, zydm, nj);//加载页面所有的下拉列表		
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 条件设置增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPdtjAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		TjszModel tjzdModel = new TjszModel();
		PjpyAhjgService service = new PjpyAhjgService();
		//根据参数类型不同加载不同的项目列表
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		String tjmc = DealString.toGBK(request.getParameter("zdmc"));
		String czmc = DealString.toGBK(request.getParameter("czmc"));
		String xxdm = StandardOperation.getXxdm();
		ahjgForm.setXn(Base.getJxjsqxn());//奖学金申请学年
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//项目列表
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				String tjdm = request.getParameter("tjdm");
				if (!StringUtils.isNull(tjdm) && "zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(3);//字段比较列表
					request.setAttribute("fsfs", "pm");
				} else if (!StringUtils.isNull(tjdm) && !"zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(4);//字段比较列表
				} else {
					zdbjList = service.getZdbjList(2);//字段比较列表
				}
				tjList = service.getJxjTjList(4);//字段条件列表
			} else {
			tjList = service.getJxjTjList(0);//字段条件列表
			zdczList = service.getZdczList(0);//字段操作列表
			zdbjList = service.getZdbjList(0);//字段比较列表
			}
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//项目列表
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				tjList = service.getJxjTjList(5);//字段条件列表
				zdbjList = service.getZdbjList(1);//字段比较列表
			}else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);//字段比较列表
				tjList = service.getJxjTjList(1);//字段条件列表		
			}else {
			tjList = service.getJxjTjList(1);//字段条件列表
			zdczList = service.getZdczList(1);//字段操作列表
			zdbjList = service.getZdbjList(0);//字段比较列表
			}
			request.setAttribute("rych", "2");
		}
		if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			zdbjList = service.getZdbjList(1);//字段比较列表
			tjList = service.getJxjTjList(3);//字段条件列表
		}
		BeanUtils.copyProperties(tjzdModel, ahjgForm);
		tjzdModel.setTjmc(tjmc);
		tjzdModel.setTjzdlyb("zhszcp");
		tjzdModel.setCzmc(czmc);//TODO
		boolean bFlag = service.tjSave(tjzdModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> topList = service.getTjTitle();
		List<String[]> resList = service.getTjResult(ahjgForm.getXmdm());
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		appendProperties(request, xydm, zydm, nj);//加载页面所有的下拉列表
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 条件设置批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszplDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String xxdm = StandardOperation.getXxdm();
		//根据参数类型不同加载不同的项目列表
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//项目列表
			tjList = service.getJxjTjList(0);//字段条件列表
			zdczList = service.getZdczList(0);//字段操作列表
			zdbjList = service.getZdbjList(0);//字段比较列表
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//项目列表
			tjList = service.getJxjTjList(1);//字段条件列表
			zdczList = service.getZdczList(1);//字段操作列表
			zdbjList = service.getZdbjList(0);//字段比较列表
			if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);// 字段比较列表
			}
			request.setAttribute("rych", "2");
		}
		boolean bFlag = service.tjszplDel();//批量删除
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		appendProperties(request, xydm, zydm, nj);//加载页面所有的下拉列表
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 条件设置单个删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszdgDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String xxdm = StandardOperation.getXxdm();
		//根据参数类型不同加载不同的项目列表
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//项目列表
			tjList = service.getJxjTjList(0);//字段条件列表
			zdczList = service.getZdczList(0);//字段操作列表
			zdbjList = service.getZdbjList(0);//字段比较列表
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//项目列表
			tjList = service.getJxjTjList(1);//字段条件列表
			zdczList = service.getZdczList(1);//字段操作列表
			zdbjList = service.getZdbjList(0);//字段比较列表
			if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);// 字段比较列表
			}
			request.setAttribute("rych", "2");
		}
		boolean bFlag = service.tjszdgDel(request.getParameter("pkValue"));//单个删除
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> topList = service.getTjTitle();
		if (!StringUtils.isNull(ahjgForm.getXmdm())) {
			List<String[]> resList = service.getTjResult(ahjgForm.getXmdm());
			request.setAttribute("rs", resList);
		}
		request.setAttribute("topTr", topList);
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		appendProperties(request, xydm, zydm, nj);//加载页面所有的下拉列表
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 学生成绩信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscjb (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		String xxdm = StandardOperation.getXxdm();
		List<String[]> resList = service.getXscj(xh);// 该生在奖学金申请学年学期成绩
		String[] jxjsqxnxq = service.getJxjsqxnxq();
		if (jxjsqxnxq == null) {
			jxjsqxnxq = new String[2];
		}
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("title", String.format("%1$s 年成绩汇总表",
					jxjsqxnxq[0], jxjsqxnxq[1]));
		} else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){
			request.setAttribute("title", String.format("%1$s 年成绩汇总表",
					jxjsqxnxq[0], jxjsqxnxq[1]));
		}else {
			request.setAttribute("title", String.format("%1$s 年第 %2$s 学期成绩汇总表",
					jxjsqxnxq[0], jxjsqxnxq[1]));
		}
		request.setAttribute("rs", resList);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("xscjb");
	}
	
	public static void main(String...strings) {
		System.out.println("a".hashCode());
		System.out.println("z".hashCode());
		System.out.println("A".hashCode());
		System.out.println("Z".hashCode());
	}
}
