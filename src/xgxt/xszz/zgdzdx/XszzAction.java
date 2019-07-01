package xgxt.xszz.zgdzdx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.MessageInfo;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.rcgl.cdty.qjgl.CdtyQjglForm;
import xgxt.rcgl.cdty.qjgl.CdtyQjglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Arrays2;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;
import xgxt.xszz.XszzService;
import xgxt.xszz.common.XszzComNewDao;
import xgxt.xszz.zgdzdx.synData.SynDataService;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyService;

public class XszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
	
//	private void generateList(ArrayList<HashMap<String, String>> list,String[] col,String[] colCn){
//		for(int i=0;i<col.length;i++){
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("en",col[i]);
//			map.put("cn", colCn[i]);
//			list.add(map);
//		}
//	}

	/**
	 * @describe 困难生申请时间设定
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_knssj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmmc||xydm";
		String tips = "贫困认定 - 基础数据维护 - 认定时间维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_zgdzdx_kns_sjb";
		String[] colList = new String[] { "主键", "xmmc", "xymc", "xyrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String xmmc =  request.getParameter("xmmc") ;
		String xydm =  request.getParameter("xydm") ;
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		map.put("xydm", xydm);
		
		String sql = "select xmmc||xydm 主键,a.* from view_zgdzdx_kns_sjb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("knsxmList", xszzDao.getZgdzdxKnsxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("data_knssj");
	}

	/**
	 * @describe 根据主键得到困难生时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knssjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String doType = request.getParameter("doType");
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_zgdzdx_kns_sjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc",
				"xyrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc    =  request.getParameter("xmmc") ;
			String xydm    =  request.getParameter("xydm") ;
			String kssj    =  request.getParameter("kssj") ;
			String jssj    =  request.getParameter("jssj") ;
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_zgdzdx_kns_sjb where xmmc||xydm=?",
					new String[] { xmmc + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("zgdzdx_kns_sjb", new String[] { "xmmc",
								"xydm", "kssj", "jssj" },
								new String[] { xmmc, xydm, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("zgdzdx_kns_sjb", new String[] {
						"kssj", "jssj" }, new String[] { kssj,
						jssj }, "xmmc||xydm", xmmc + xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = xmmc + xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("knsxmList", xszzDao.getZgdzdxKnsxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("doType", doType);
		return mapping.findForward("knssjEdit");
	}
	
	/**
	 * @describe 批量设置困难生申请时间
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward knssjPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act =  request.getParameter("act") ;
		String pkDel =  request.getParameter("pkDel") ;
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if("save".equalsIgnoreCase(act)){
			kssj  =  request.getParameter("kssj") ;
			jssj  =  request.getParameter("jssj") ;
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update zgdzdx_kns_sjb set kssj='" + kssj
						+ "',jssj='" + jssj + "' where xmmc||xydm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("knssjPlsz");
	}
	
	/**
	 * @describe 困难生时间初始化
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward knssjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("zgdzdx_kns_sjb", new String[]{"1"}, new String[]{"1"}, request);
		
		String[] sqlT = new String[3];
		sqlT[0] = "insert into zgdzdx_kns_sjb(xmmc,xydm) select '家庭情况调查' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[1] = "insert into zgdzdx_kns_sjb(xmmc,xydm) select '困难生申请时间' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[2] = "insert into zgdzdx_kns_sjb(xmmc,xydm) select '困难生审核时间' xmmc,xydm from view_njxyzybj group by xydm";
		dao.runBatch(sqlT);
		return new ActionForward("/zgdzdx_xszz.do?method=data_knssj&go=go", false);
	}

	/**
	 * 家庭情况调查申请页面
	 * jtqkdcsq ----- 家庭情况调查申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String isQuery = request.getParameter("isQuery");
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String xn = Base.currXn;
		String pkVal =  DealString.toGBK(request.getParameter("pkVal")) ;
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		request.setAttribute("have","notHave");
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getJtqkdcxx(pkVal);
			HashMap<String, String> tHs = new HashMap<String, String>();
			tHs = service.getKnsrdxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			} else if (tHs.size() != 0
					&& (tHs.get("xxsh").equalsIgnoreCase("一般困难")
							|| tHs.get("xxsh").equalsIgnoreCase("困难") || tHs
							.get("xxsh").equalsIgnoreCase("特殊困难"))) {
				request.setAttribute("have","have");
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		stuMap.put("xn", Base.currXn);//当前学年
		request.setAttribute("sfksq", service.getJtqkdcSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("isQuery", isQuery);
		return mapping.findForward("jtqkdcsq");
	}
	
	/**
	 * 保存家庭情况调查申请信息
	 * jtqkdcsqSave ---- 保存家庭情况调查申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		JtqkdcModel jtqkdcModel = new JtqkdcModel();
		BeanUtils.copyProperties(jtqkdcModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveJtqkdcSqxx(jtqkdcModel, request);// 保存信息
		if (bJg) {
			
			// ===================2011.1.10 edit by luojw========
			// 同步家庭情况数据往N32
			SynDataService synService = new SynDataService();
			// 家庭信息
			synService.synJtqkdc(jtqkdcModel, request);
			// 家庭成员
			synService.synJtcy(jtqkdcModel);
			// ===================end=============================
			
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = jtqkdcModel.getXh();
		String xn = jtqkdcModel.getXn();
		String pkVal = xn + xh;
		request.setAttribute("have","notHave");
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getJtqkdcxx(pkVal);
			HashMap<String, String> tHs = new HashMap<String, String>();
			tHs = service.getKnsrdxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			} else if (tHs.size() != 0
					&& (tHs.get("xxsh").equalsIgnoreCase("一般困难")
							|| tHs.get("xxsh").equalsIgnoreCase("困难") || tHs
							.get("xxsh").equalsIgnoreCase("特殊困难"))) {
				request.setAttribute("have","have");
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdcsqSave");
	}

	/**
	 * 家庭情况调查申请表页面
	 * jtqkdcsqb ----- 家庭情况调查申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		JtqkdcModel jtqkdcModel = new JtqkdcModel();
		BeanUtils.copyProperties(jtqkdcModel, xszzZgdzdxActionForm);
		stuMap = service.getJtqkdcSqb(jtqkdcModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("jtqkdcsqb");
	}

	/**
	 * 家庭情况调查审核页面
	 * jtqkdcsh ----- 家庭情况调查审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		queryModel.setIsQuery( DealString.toGBK(request.getParameter("isQuery")) );
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZgdzdxActionForm.setXn(Base.currXn);
		}
		queryModel.setNd( request.getParameter("nd") );
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delJtqkdcxx(request.getParameter("cbVal"), request);

			// ===================2011.1.10 edit by luojw========
			// 同步家庭情况数据往N32
			SynDataService synService = new SynDataService();
			// 家庭信息
			synService.synJtqkdcDel(request.getParameter("cbVal"));
			// ===================end=============================

			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modJtqkdcxx( request.getParameter("cbVal"),
					  "通过", request);

			// ===================2011.1.10 edit by luojw========
			// 同步家庭情况数据往N32
			SynDataService synService = new SynDataService();
			// 家庭信息
			synService.synJtqkdcShPl(request.getParameter("cbVal"), "通过");
			// ===================end=============================
			
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modJtqkdcxx(request.getParameter("cbVal"), "不通过", request);

			// ===================2011.1.10 edit by luojw========
			// 同步家庭情况数据往N32
			SynDataService synService = new SynDataService();
			// 家庭信息
			synService.synJtqkdcShPl(request.getParameter("cbVal"), "不通过");
			// ===================end=============================

			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getJtqkdcTit();
		List<String[]> resList = service.getJtqkdcRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgdzdx_kns_jtqkdc");
		request.setAttribute("tableName", "view_zgdzdx_kns_jtqkdc");
		return mapping.findForward("jtqkdcsh");
	}
	
	/**
	 * 家庭情况调查信息导出
	 * jtqkdcExp ----- 家庭情况调查信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(Globals.XXDM_WHTLZYJSXY.equals(Base.xxdm)){//武汉铁路
			return jtqkdcExp_whtl(mapping, form, request, response);
		}
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getJtqkdcExp(queryModel, response,request);
		return mapping.findForward("jtqkdcExp");
	}

	/**
	 * 家庭情况调查审核详细页面
	 * jtqkdcshOne ----- 家庭情况调查审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		
		String pkVal =  request.getParameter("pkVal") ;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getJtqkdcxx(pkVal);
		xszzZgdzdxActionForm.setSh(stuMap.get("sh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdcshOne");
	}

	/**
	 * 保存家庭情况调查审核信息
	 * jtqkdcshSave ---- 保存家庭情况调查审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		JtqkdcModel jtqkdcModel = new JtqkdcModel();
		BeanUtils.copyProperties(jtqkdcModel, xszzZgdzdxActionForm);
		boolean bJg = service.saveJtqkdcShxx(jtqkdcModel, request);// 保存困难生审核信息
		if (bJg) {
			
			// ===================2011.1.10 edit by luojw========
			// 同步家庭情况数据往N32
			SynDataService synService = new SynDataService();
			// 同步家庭情况审核状态
			synService.synJtqkdcSh(jtqkdcModel, request);
			// ===================end=============================
			
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = jtqkdcModel.getXh();
		String xn = jtqkdcModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getJtqkdcxx(pkVal);
		xszzZgdzdxActionForm.setSh(stuMap.get("sh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdcshSave");
	}
	
	/**
	 * 困难生认定申请页面
	 * knsrdsq ----- 困难生认定申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String isQuery = request.getParameter("isQuery");
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String xn = Base.currXn;
		String pkVal =  DealString.toGBK(request.getParameter("pkVal")) ;
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrdxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		stuMap.put("xn", Base.currXn);//当前年度
		request.setAttribute("sfksq", service.getKnsrdSqQx(sUserType, userDep,
				xh, Base.currXn));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("isQuery", isQuery);
		return mapping.findForward("knsrdsq");
	}
	
	/**
	 * 保存困难生认定申请信息
	 * jtqkdcsqSave ---- 保存困难生认定申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveKnsrdSqxx(knsrdModel, request);// 保存信息
		if (bJg) {
			// ===================2011.1.10 edit by luojw========
			// 同步困难生数据往N32
			SynDataService synService = new SynDataService();
			// 困难生信息
			synService.synKnsSq(knsrdModel);
			// ===================end=============================
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = knsrdModel.getXh();
		String xn = knsrdModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrdxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdsqSave");
	}

	/**
	 * 困难生认定申请表页面
	 * knsrdsqb ----- 困难生认定申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgdzdxActionForm);
		stuMap = service.getKnsrdSqb(knsrdModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrdsqb");
	}

	/**
	 * 困难生认定审核页面
	 * knsrdsh ----- 困难生认定审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		queryModel.setIsQuery( DealString.toGBK(request.getParameter("isQuery")) );
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZgdzdxActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrdxx( request.getParameter("cbVal"),
					  request);
			
			// ===================2011.1.10 edit by luojw========
			// 同步困难生数据往N32
			SynDataService synService = new SynDataService();
			// 困难生信息
			synService.synKnsDel(request.getParameter("cbVal"), request);
			// ===================end=============================
			
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
			
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				String[] shsj = service.getKnsrdShsj(userDep);
				if (shsj == null) {
					request.setAttribute("shsj", "未知");
				} else {
					request.setAttribute("shsj", shsj[0].substring(0, 4) + "年"
							+ shsj[0].substring(5, 7) + "月"
							+ shsj[0].substring(8) + "日 至 "
							+ shsj[1].substring(0, 4) + "年"
							+ shsj[1].substring(5, 7) + "月"
							+ shsj[1].substring(8) + "日");
				}
			}
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getKnsrdTit();
		List<String[]> resList = service.getKnsrdRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		HashMap<String, String> hsQuery = new HashMap<String, String>();
		
		hsQuery.put("tjdc",  queryModel.getTjdc());
		hsQuery.put("xysh",  queryModel.getXysh());
		hsQuery.put("xxsh",  queryModel.getXxsh());
		
		request.setAttribute("shqx", service.getKnsrdShQx(userType,userDep));
		request.setAttribute("hs", hsQuery);
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("shList", service.getshList());
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgdzdx_kns_pksrd");
		request.setAttribute("tableName", "view_zgdzdx_kns_pksrd");
		return mapping.findForward("knsrdsh");
	}
	
	/**
	 * 困难生认定批量审核
	 * knsrdplsh ----- 困难生认定批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String doType =  request.getParameter("doType") ;
		
		String cbVal =  request.getParameter("cbVal");
		String shType =  request.getParameter("shType") ;
		String shjg =  request.getParameter("shjg") ;
		
		if ("save".equalsIgnoreCase(doType)){
			
			service.modKnsrdxx(cbVal, shType, shjg, request);
			
			// ===================2011.1.10 edit by luojw========
			// 同步困难生数据往N32
			SynDataService synService = new SynDataService();
			// 困难生信息
			synService.synKnsShPl(cbVal, shType, shjg, request);
			// ===================end=============================
			
			request.setAttribute("over", "over");
		}
		
		rs.put("cbVal", cbVal);
		rs.put("shType", shType);
		rs.put("shjg", shjg);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		return mapping.findForward("knsrdplsh");
	}
	
	/**
	 * 困难生认定信息导出
	 * knsrdExp ----- 困难生认定信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getKnsrdExp(queryModel, response,request);
		return mapping.findForward("knsrdExp");
	}

	/**
	 * 困难生认定审核详细页面
	 * knsrdshOne ----- 困难生认定审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		
		String pkVal =  request.getParameter("pkVal") ;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrdxx(pkVal);
		xszzZgdzdxActionForm.setTjdc(stuMap.get("tjdc"));
		xszzZgdzdxActionForm.setXysh(stuMap.get("xysh"));
		xszzZgdzdxActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("shqx", service.getKnsrdShQx(userType,userDep));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdshOne");
	}

	/**
	 * 保存困难生认定审核信息
	 * knsrdshSave ---- 保存困难生认定审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgdzdxActionForm);
		boolean bJg = service.saveKnsrdShxx(knsrdModel, request);// 保存困难生审核信息
		if (bJg) {
			// ===================2011.1.10 edit by luojw========
			// 同步困难生数据往N32
			SynDataService synService = new SynDataService();
			// 困难生信息
			synService.synKnsSh(knsrdModel);
			// ===================end=============================
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = knsrdModel.getXh();
		String nd = knsrdModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrdxx(pkVal);
		xszzZgdzdxActionForm.setTjdc(stuMap.get("tjdc"));
		xszzZgdzdxActionForm.setXysh(stuMap.get("xysh"));
		xszzZgdzdxActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("shqx", service.getKnsrdShQx(userType,userDep));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdshSave");
	}

	/**
	 * @describe 助学贷款年限设定
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_zxdknx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "bjdm";
		String tips = "助学贷款 - 基础数据维护 - 学生贷款年限维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_zgdzdx_xsdknxb";
		String[] colList = new String[] { "主键", "nj", "xymc", "zymc", "bjmc", "dkkssj", "dknx" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String zydm =  request.getParameter("zydm") ;
		String xydm =  request.getParameter("xydm") ;
		String bjdm =  request.getParameter("bjdm") ;
		String nj =  request.getParameter("nj") ;
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("zydm", zydm));
		querry.append(SearchUtils.equalSql("bjdm", bjdm));
		querry.append(SearchUtils.equalSql("nj", nj));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		map.put("nj", nj);
		map.put("xydm", xydm);
		
		String sql = "select bjdm 主键,a.* from view_zgdzdx_xsdknxb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("data_zxdknx");
	}

	/**
	 * @describe 根据主键得到助学贷款年限详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdknxEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String doType = request.getParameter("doType");
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String sql = "select nj,xymc,zymc,bjdm,bjmc,dkkssj,dknx from view_zgdzdx_xsdknxb where bjdm=?";
		String[] outString = new String[] { "nj", "xymc", "zymc",
				"zymc", "bjdm", "bjmc", "dkkssj", "dknx" };

		if ("save".equalsIgnoreCase(act)) {
			String bjdm    =  request.getParameter("bjdm") ;
			String dknx    =  request.getParameter("dknx") ;
			String dkkssj    =  request.getParameter("dkkssj") ;
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_zgdzdx_xsdknxb where bjdm=?",
					new String[] { bjdm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("zgdzdx_xsdknxb", new String[] {
						"bjdm", "dkkssj", "dknx" }, new String[] { bjdm,
						dkkssj, dknx }, request);
			} else {
				b = StandardOperation.update("zgdzdx_xsdknxb", new String[] {
						"dkkssj", "dknx" }, new String[] { dkkssj, dknx },
						"bjdm", bjdm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = bjdm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("doType", doType);
		return mapping.findForward("zxdknxEdit");
	}
	
	/**
	 * @describe 批量设置助学贷款年限
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdknxPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act =  request.getParameter("act") ;
		String pkDel =  request.getParameter("pkDel") ;
		String dknx = "0";
		String dkkssj = "";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			dknx =  request.getParameter("dknx") ;
			dkkssj =  request.getParameter("dkkssj") ;
			String[] pkT = pkDel.split("!@");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update zgdzdx_xsdknxb set dknx='" + dknx
						+ "',dkkssj='" + dkkssj + "' where bjdm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("dknx", dknx);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zxdknxPlsz");
	}
	
	/**
	 * 助学贷款年限初始化
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdknxcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzService service = new XszzService();
		String[] pkValues = request.getParameterValues("pks");
		boolean result = false;
		
		if (null == pkValues || pkValues.length==0) {
			result = service.dknxCsh();
		} else {
			result = service.dknxCsh(pkValues);
		}
		
		request.setAttribute("result", result);
		/*StandardOperation.delete("zgdzdx_xsdknxb", new String[]{"1"}, new String[]{"1"}, request);
		
		dao.runUpdateTab("insert into zgdzdx_xsdknxb(bjdm) select bjdm from view_njxyzybj group by bjdm");*/
		return new ActionForward("/zgdzdx_xszz.do?method=data_zxdknx&go=go", false);
	}
	
	/**
	 * @describe 合同编号设定
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_htbh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xydm";
		String tips = "助学贷款 - 基础数据维护 - "+Base.YXPZXY_KEY+"合同编号维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_zgdzdx_htbhfwb";
		String[] colList = new String[] { "主键", "xymc", "xydm", "htmc", "zxhth", "zdhth" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String xydm =  request.getParameter("xydm") ;
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xydm", xydm);
		
		String sql = "select xydm 主键,a.* from view_zgdzdx_htbhfwb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("data_htbh");
	}

	/**
	 * @describe 根据主键得到合同编号详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward htbhEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String writeAble = "yes";// 写权限
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String sql = "select xydm,xymc,htmc,zxhth,zdhth from view_zgdzdx_htbhfwb where xydm=?";
		String[] outString = new String[] { "xydm", "xymc", "htmc", "zxhth", "zdhth" };

		if ("save".equalsIgnoreCase(act)) {
			String xydm    =  request.getParameter("xydm") ;
			String htmc    =  request.getParameter("htmc");
			String zxhth    =  request.getParameter("zxhth") ;
			String zdhth    =  request.getParameter("zdhth") ;
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_zgdzdx_htbhfwb where xydm=?",
					new String[] { xydm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("zgdzdx_htbhfwb", new String[] {
						"xydm", "htmc", "zxhth", "zdhth" }, new String[] { xydm, htmc.toUpperCase(), zxhth,
						zdhth }, request);
			} else {
				b = StandardOperation.update("zgdzdx_htbhfwb", new String[] {
						"htmc", "zxhth", "zdhth" }, new String[] { htmc.toUpperCase(), zxhth, zdhth },
						"xydm", xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		String yfphth = service.getHtbh(pkVal);
		String yfphthList = service.getHtbhList(pkVal);
		map.put("yfphth", yfphth);
		map.put("yfphthList", yfphthList);
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("htbhEdit");
	}
	
	/**
	 * @describe 合同编号初始化
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward htbhcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("zgdzdx_htbhfwb", new String[]{"1"}, new String[]{"1"}, request);
		
		dao.runUpdateTab("insert into zgdzdx_htbhfwb(xydm) select xydm from view_njxyzybj group by xydm");
		return new ActionForward("/zgdzdx_xszz.do?method=data_htbh&go=go", false);
	}

	/**
	 * @describe 助学贷款申请时间设定
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_zxdksj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmmc||xydm";
		String tips = "助学贷款 - 基础数据维护 - 助学贷款申请时间维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_zgdzdx_zxdk_sjb";
		String[] colList = new String[] { "主键", "xmmc", "xymc", "xyrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String xmmc =  request.getParameter("xmmc") ;
		String xydm =  request.getParameter("xydm") ;
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		map.put("xydm", xydm);
		
		String sql = "select xmmc||xydm 主键,a.* from view_zgdzdx_zxdk_sjb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("zxdkxmList", xszzDao.getZgdzdxZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zxdksj");
	}

	/**
	 * @describe 根据主键得到助学贷款时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdksjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String doType=request.getParameter("doType");
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_zgdzdx_zxdk_sjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc",
				"xyrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc    =  request.getParameter("xmmc") ;
			String xydm    =  request.getParameter("xydm") ;
			String kssj    =  request.getParameter("kssj") ;
			String jssj    =  request.getParameter("jssj") ;
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_zgdzdx_zxdk_sjb where xmmc||xydm=?",
					new String[] { xmmc + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("zgdzdx_zxdk_sjb", new String[] { "xmmc",
								"xydm", "kssj", "jssj" },
								new String[] { xmmc, xydm, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("zgdzdx_zxdk_sjb", new String[] {
						"kssj", "jssj" }, new String[] { kssj,
						jssj }, "xmmc||xydm", xmmc + xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = xmmc + xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("zxdkxmList", xszzDao.getZgdzdxZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("doType", doType);
		return mapping.findForward("zxdksjEdit");
	}
	
	/**
	 * @describe 批量设置助学贷款申请时间
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act =  request.getParameter("act") ;
		String pkDel =  request.getParameter("pkDel") ;
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if("save".equalsIgnoreCase(act)){
			kssj  =  request.getParameter("kssj") ;
			jssj  =  request.getParameter("jssj") ;
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update zgdzdx_zxdk_sjb set kssj='" + kssj
						+ "',jssj='" + jssj + "' where xmmc||xydm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zxdksjPlsz");
	}
	
	/**
	 * @describe 助学贷款时间初始化
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("zgdzdx_zxdk_sjb", new String[]{"1"}, new String[]{"1"}, request);
		String[] sqlT = new String[5];
		sqlT[0] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '助学贷款申请' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[1] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '资料确认书' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[2] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '还款协议' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[3] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '展期协议' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[4] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '展期后还款协议' xmmc,xydm from view_njxyzybj group by xydm";
		dao.runBatch(sqlT);
		return new ActionForward("/zgdzdx_xszz.do?method=data_zxdksj&go=go", false);
	}

	/**
	 * 生源地贷款数据维护页面
	 * data_syddk ----- 生源地贷款数据维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_syddk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(Globals.XXDM_WHTLZYJSXY.equals(Base.xxdm)){//武汉铁路
			return data_syddk_whtl(mapping, form, request, response);
		}
		
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		
		queryModel.setGo( request.getParameter("go") );
		queryModel.setNd( request.getParameter("nd") );
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delSyddk( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getSyddkTit();
		List<String[]> resList = service.getSyddkRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgdzdx_syddk");
		request.setAttribute("tableName", "view_zgdzdx_syddk");
		return mapping.findForward("data_syddk");
	}
	
	/**
	 * 生源地贷款信息导出
	 * syddkExp ----- 生源地贷款信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syddkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getSyddkExp(queryModel, response,request);
		return mapping.findForward("syddkExp");
	}

	/**
	 * 国家助学贷款申请页面
	 * gjzxdksq ----- 国家助学贷款申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//宁波天一职业技术学院的国家助学贷款
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
			return new ActionForward("/nbty_xszz.do?method=gjzxdksq");
		}
		
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String isQuery = request.getParameter("isQuery");
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String pkVal =  request.getParameter("pkVal") ;
		pkVal = StringUtils.isNull(pkVal) ? xh : pkVal;
		xh = pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			stuMap = service.getGjzxdkxx(pkVal);
			
			String[] temp = service.getDkje(pkVal);
			stuMap.put("zddkje", temp[0]);
			stuMap.put("mndkje", temp[1]);
			stuMap.putAll(service.getStu(xh));
			stuMap.putAll(service.getDkqx(xh));
		}
		request.setAttribute("sfksq", service.getGjzxdkSqQx(xh));
		request.setAttribute("fkcs", service.getFkcs(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksq");
		request.setAttribute("isQuery", isQuery);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjzxdksq");
	}
	
	/**
	 * 保存国家助学贷款申请信息
	 * gjzxdksqSave ---- 保存国家助学贷款申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveGjzxdkSqxx(gjzxdkModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxdkModel.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxdkxx(pkVal);
			stuMap.put("zddkje", service.getDkje(pkVal)[0]);
			stuMap.put("mndkje", service.getDkje(pkVal)[1]);
			if (stuMap.size() < 3) {
				stuMap = service.getStu(xh);// 得到学生信息
				if (stuMap.size() != 0 && stuMap.size() != 1 && stuMap.size() != 2){
					stuMap = service.getZxdkStu(stuMap);
				}
			}
		}
		request.setAttribute("sfksq", "true");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdksqSave");
	}

	/**
	 * 国家助学贷款申请表页面
	 * gjzxdksqb ----- 国家助学贷款申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxService service = new XszzZgdzdxService();
		String pkVal =  request.getParameter("pkVal") ;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		
		String csrq = stuMap.get("csrq");
		
		if (!Base.isNull(csrq) && csrq.length()==8) {
			stuMap.put("csrq_year", csrq.substring(0,4));
			stuMap.put("csrq_month", csrq.substring(4,6));
		}
		
		if (!Base.isNull(csrq) && csrq.length()==10) {
			stuMap.put("csrq_year", csrq.substring(0,4));
			stuMap.put("csrq_month", csrq.substring(5,7));
		}
		String jtysr=stuMap.get("jtysr")==null? "0" : stuMap.get("jtysr");
		int nsr=Integer.parseInt(jtysr)*12;
		stuMap = service.getGjzxdkSqb(stuMap,request);
		request.setAttribute("nsr", nsr);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxdksqb");
	}

	/**
	 * 国家助学贷款审核页面
	 * gjzxdksh ----- 国家助学贷款审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//		宁波天一职业技术学院的国家助学贷款
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
			return new ActionForward("/nbty_xszz.do?method=gjzxdksh");
		}
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		queryModel.setIsQuery( request.getParameter("isQuery") );
		String isQuery=request.getParameter("isQuery");
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxdkxx( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getGjzxdkTit();
		List<String[]> resList = service.getGjzxdkRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		HashMap<String, String> rs1 = new HashMap<String, String>();
		rs1.put("sjfw1", queryModel.getSjfw1());
		rs1.put("sjfw2", queryModel.getSjfw2());
		request.setAttribute("rs1", rs1);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zdgzgx_gjzxdk");
		request.setAttribute("tableName", "view_zdgzgx_gjzxdk");
		if("is".equalsIgnoreCase(isQuery)){
			request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksqjg");
		}else{
			request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksh");
		}
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjzxdksh");
	}
	
	/**
	 * 国家助学贷款信息导出
	 * gjzxdkExp ----- 国家助学贷款信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getGjzxdkExp(queryModel, response,request);
		return mapping.findForward("gjzxdkExp");
	}

	/**
	 * 国家助学贷款审核详细页面
	 * gjzxdkshOne ----- 国家助学贷款审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		
		String pkVal =  request.getParameter("pkVal") ;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		stuMap.put("htmc", service.getHtmc(pkVal));
		stuMap.put("nd", Base.currNd);
		if (!Base.isNull(stuMap.get("htbh")) && stuMap.get("htbh").length() == 19){
			String htnd = stuMap.get("htbh").substring(5,9);
			if (htnd.equalsIgnoreCase(Base.currNd)) {
				stuMap.put("htmc", stuMap.get("htbh").substring(10,14));
				stuMap.put("htbh", stuMap.get("htbh").substring(15));
			} else {
				request.setAttribute("lsht", "yes");
			}
		}
		
		String hthList = service.getXyHtbhList(stuMap.get("xydm"));
		String havehtbhList = service.getYyHtbhList(stuMap.get("xh"),stuMap.get("nd"));
		
		List<HashMap<String, String>> htList = service.getHtList(stuMap);
		
		xszzZgdzdxActionForm.setShjg(stuMap.get("shjg"));
		stuMap.put("hthList", hthList);
		stuMap.put("havehtbhList", havehtbhList);
		
		request.setAttribute("htList", htList);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshOne");
	}

	/**
	 * 保存国家助学贷款审核信息
	 * gjzxdkshSave ---- 保存国家助学贷款审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzZgdzdxActionForm);
		boolean bJg = service.saveGjzxdkShxx(model, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		stuMap.put("htmc", service.getHtmc(pkVal));
		stuMap.put("nd", Base.currNd);
		if (!Base.isNull(stuMap.get("htbh")) && stuMap.get("htbh").length() == 19){
			stuMap.put("htmc", stuMap.get("htbh").substring(10,14));		
			stuMap.put("htbh", stuMap.get("htbh").substring(15));
		}
		xszzZgdzdxActionForm.setSh(stuMap.get("sh"));
		String hthList = service.getXyHtbhList(stuMap.get("xydm"));
		String havehtbhList = service.getYyHtbhList(stuMap.get("xh"),stuMap.get("nd"));
		List<HashMap<String, String>> htList = service.getHtList(stuMap);
		stuMap.put("hthList", hthList);
		stuMap.put("havehtbhList", havehtbhList);
		
		request.setAttribute("htList", htList);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshSave");
	}

	/**
	 * @describe 申请结果查询
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward gjzxdksqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 宁波天一职业技术学院的国家助学贷款
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
			return new ActionForward("/nbty_xszz.do?method=gjzxdksqjg");
		}
		
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String username = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "";
		List topTr = new ArrayList<String[]>();
		HashMap<String, String> map = new HashMap<String, String>();
		ActionForward myActFwd = null;

		String tableName = "view_zdgzgx_gjzxdk";
		colList = new String[] { "url", "行号", "xh", "xm", "dkqxy", "dkqx", "sqdkje",
				"sqsj", "shjg", "shyj" };
		if (!userType.equalsIgnoreCase("stu")) {
			myActFwd = new ActionForward(
					"/zgdzdx_xszz.do?method=gjzxdksh&isQuery=is", false);
			return myActFwd;
		}
		sql = "select '/xgxt/zgdzdx_xszz.do?method=gjzxdksq'||'&'||'pkVal='||xh url,rownum 行号,a.* from " + tableName + " a where xh=?";
		colListCN = dao.getColumnNameCN(colList, tableName);
		topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[] { username }, colList));
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);// 把查询条件发回去
		return mapping.findForward("gjzxdksqjg");
	}
	
	/**
	 * @describe 个人助学贷款详细信息
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward gjzxdkgrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxService service = new XszzZgdzdxService();
		HttpSession session = request.getSession();
		String username = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(username);
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("userType", userType);
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdkgrxx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjzxdkgrxx");
	}

	/**
	 * 资料确认书申请页面
	 * zlqrssq ----- 资料确认书申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zlqrssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String pkVal =  request.getParameter("pkVal") ;
		pkVal = StringUtils.isNull(pkVal) ? xh : pkVal;
		xh = pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxdktgxx(pkVal);
			if (stuMap.size() == 0) {
				request.setAttribute("isNull", "is");
			}
		}
		request.setAttribute("sfksq", service.getZlqrsSqQx(sUserType, userDep,
				xh));
		request.setAttribute("userType", sUserType);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("path", "zgdzdx_xszz.do?method=zlqrssq");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zlqrssq");
	}
	
	/**
	 * 保存资料确认书申请信息
	 * zlqrssqSave ---- 保存资料确认书申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zlqrssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveZlqrsSqxx(gjzxdkModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxdkModel.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxdktgxx(pkVal);
			if (stuMap.size() == 0) {
				request.setAttribute("isNull", "is");
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("userType", sUserType);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zlqrssqSave");
	}
	
	/**
	 * 资料确认书申请表页面
	 * zlqrssqb ----- 资料确认书申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zlqrssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzZgdzdxActionForm);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(model.getXh());
		
		stuMap = service.getZlqrsSqb(stuMap,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zlqrssqb");
	}

	/**
	 * 还款协议页面
	 * hkxysq ----- 还款协议页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hkxysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String pkVal =  request.getParameter("pkVal") ;
		pkVal = StringUtils.isNull(pkVal) ? xh : pkVal;
		xh = pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.gethkxyxx(pkVal);
			if (stuMap.size() < 5) {
				request.setAttribute("isNull", "is");
			}
		}
		request.setAttribute("sfkdy", dao.getOneRs("select count(*) num from zgdzdx_hkjzqhhkxyxx where xh=? and xz='1'", new String[]{xh}, "num"));
		request.setAttribute("sfksq", service.getHkxyQx(sUserType, userDep,
				xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("path", "zgdzdx_xszz.do?method=hkxysq");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hkxysq");
	}
	
	/**
	 * 保存还款协议信息
	 * hkxysqSave ---- 保存还款协议信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hkxysqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveGjzxdkxx(gjzxdkModel, "1", request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxdkModel.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.gethkxyxx(pkVal);
			if (stuMap.size() < 5) {
				request.setAttribute("isNull", "is");
			}
		}
		DAO dao = DAO.getInstance();
		request.setAttribute("sfkdy", dao.getOneRs("select count(*) num from zgdzdx_hkjzqhhkxyxx where xh=? and xz='1'", new String[]{xh}, "num"));
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("hkxysqSave");
	}
	
	/**
	 * 还款协议页面
	 * hkxysqb ----- 还款协议申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hkxysqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzZgdzdxActionForm);
		HashMap<String, String> stuMap = service.gethkxyxx(model.getXh());
		
		/*2010.8.12 quph edit begin*/
		if (null != stuMap && !stuMap.isEmpty()) {
			String byny = stuMap.get("byny");
			String bynyn = stuMap.get("bynyn");
			String dkjssj = stuMap.get("dkjssj");
			
			if (!Base.isNull(byny) && byny.length()==10) {
				stuMap.put("byny_mon", byny.substring(5, 7));
				stuMap.put("byny_day", byny.substring(8));
			}
			
			if (!Base.isNull(bynyn) && bynyn.length()==10) {
				stuMap.put("bynyn_mon", bynyn.substring(5, 7));
				stuMap.put("bynyn_day", bynyn.substring(8));
			}
			
			if (!Base.isNull(dkjssj) && dkjssj.length()==10) {
				stuMap.put("dkjssj_year", dkjssj.substring(0, 4));
				stuMap.put("dkjssj_mon", dkjssj.substring(5, 7));
				stuMap.put("dkjssj_day", dkjssj.substring(8));
			}
			String str = "";
			if (!Base.isNull(bynyn)){
				str = String.valueOf(Integer.valueOf(bynyn.substring(0,4))+2)+bynyn.substring(4);
			}
			stuMap.put("month1", String.valueOf(dealDate.getMonths(bynyn,dkjssj)));
			stuMap.put("month2", String.valueOf(dealDate.getMonths(str,dkjssj)));
		}
		/*2010.8.12 quph edit end*/
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("rs", stuMap);
		return mapping.findForward("hkxysqb");
	}

	/**
	 * 还款协议审核页面
	 * hkxysh ----- 还款协议审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hkxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delHkxyxx( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
		}
		if ("pass".equalsIgnoreCase(queryModel.getGo())) {
			service.modHkxyxx( request.getParameter("cbVal"),
					  "通过", request);
			queryModel.setGo("go");
		}
		if ("notPass".equalsIgnoreCase(queryModel.getGo())) {
			service.modHkxyxx( request.getParameter("cbVal"),
					  "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getHkxyTit();
		List<String[]> resList = service.getHkxyRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		String xxsh = DealString.toGBK(xszzZgdzdxActionForm.getXxsh());
		xszzZgdzdxActionForm.setXh(xh);
		xszzZgdzdxActionForm.setXxsh(xxsh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xh");
		request.setAttribute("realTable", "zgdzdx_hkjzqhhkxyxx");
		request.setAttribute("tableName", "view_zgdzdx_hkjzqhhkxyxx");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		return mapping.findForward("hkxysh");
	}
	
	/**
	 * 还款协议信息导出
	 * hkxyExp ----- 还款协议信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hkxyExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getHkxyExp(queryModel, response,request);
		return mapping.findForward("hkxyExp");
	}
	
	/**
	 * 展期协议页面
	 * zqxysq ----- 展期协议页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqxysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String pkVal =  request.getParameter("pkVal") ;
		pkVal = StringUtils.isNull(pkVal) ? xh : pkVal;
		xh = pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getzqxyxx(pkVal);
			if (stuMap.size() < 5) {
				request.setAttribute("isNull", "is");
			}
		}
		DAO dao = new DAO();
		
		request.setAttribute("sfkdy", dao.getOneRs("select count(*) num from zgdzdx_zqxy where xh=?", new String[]{xh}, "num"));
		request.setAttribute("sfksq", service.getZqxyQx(sUserType, userDep,
				xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("path", "zgdzdx_xszz.do?method=zqxysq");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zqxysq");
	}
	
	/**
	 * 保存展期协议信息
	 * zqxysqSave ---- 保存展期协议信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqxysqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		ZqxyModel model = new ZqxyModel();
		String doit =  request.getParameter("doit") ;
		BeanUtils.copyProperties(model, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String pkVal = model.getXh();
		
		if ("cheng".equalsIgnoreCase(doit)){
			String xh = model.getXh();
			if (!"".equalsIgnoreCase(xh)) {
				stuMap = service.getzqxyxx(pkVal);
				if (stuMap.size() < 5) {
					request.setAttribute("isNull", "is");
				}
			}
			if (!"0".equalsIgnoreCase(model.getZqnx())){
				stuMap.put("zqhhkrq", dao
								.getOneRs(
										"select nvl((substr(b.dkqx,10,4)+"+model.getZqnx()+"),substr(b.dkqx,10,4))||substr(b.dkqx,14,4) zqrq from view_zdgzgx_gjzxdk b where b.xh=?",
										new String[] { model.getXh() }, "zqrq"));
			} else {
				stuMap.put("zqhhkrq", "");
			}
			stuMap.put("zqnx", model.getZqnx());
		} else {
			boolean bJg = service.saveZqxyxx(model, request);// 保存信息
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}

			String xh = model.getXh();
			if (!"".equalsIgnoreCase(xh)) {
				stuMap = service.getzqxyxx(pkVal);
				if (stuMap.size() < 5) {
					request.setAttribute("isNull", "is");
				}
			}
		}
		request.setAttribute("sfkdy", dao.getOneRs("select count(*) num from zgdzdx_zqxy where xh=?", new String[]{pkVal}, "num"));
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zqxysqSave");
	}
	
	/**
	 * 展期协议页面
	 * zqxysqb ----- 展期协议申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqxysqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		ZqxyModel model = new ZqxyModel();
		BeanUtils.copyProperties(model, xszzZgdzdxActionForm);
		HashMap<String, String> stuMap = service.getzqxyxx(model.getXh());
		stuMap.put("nd", Base.currNd);//当前年度
		
		String byny_year = "";
		String byny_mon = "";
		String byny_day = "";
		String zqhbyny_year = "";
		String zqhbyny_mon = "";
		String zqhbyny_day = "";
		String dkqx1_year = "";
		String dkqx1_mon = "";
		String dkqx1_day = "";
		String dkqx2_year = "";
		String dkqx2_mon = "";
		String dkqx2_day = "";
		String zqrq_year = "";
		String zqrq_mon = "";
		String zqrq_day = "";
		String bynyn_mon = "";
		String bynyn_day = "";
		
		if (stuMap.get("dkqx") != null && stuMap.get("dkqx").length()==17){
			dkqx1_year = stuMap.get("dkqx").substring(0,4);
			dkqx1_mon = stuMap.get("dkqx").substring(4,6);
			dkqx1_day = stuMap.get("dkqx").substring(6,8);
			dkqx2_year = stuMap.get("dkqx").substring(9,13);
			dkqx2_mon = stuMap.get("dkqx").substring(13,15);
			dkqx2_day = stuMap.get("dkqx").substring(15);
		}
		if (stuMap.get("byny") != null && stuMap.get("byny").length()==10){
			byny_year = stuMap.get("byny").substring(0,4);
			byny_mon = stuMap.get("byny").substring(5,7);
			byny_day = stuMap.get("byny").substring(8);
		}
		if (stuMap.get("bynyn") != null && stuMap.get("bynyn").length()==10){
			bynyn_mon = stuMap.get("bynyn").substring(5,7);
			bynyn_day = stuMap.get("bynyn").substring(8);
		}
		
		if (stuMap.get("zqhbyny") != null && stuMap.get("zqhbyny").length()==10){
			zqhbyny_year = stuMap.get("zqhbyny").substring(0,4);
			zqhbyny_mon = stuMap.get("zqhbyny").substring(5,7);
			zqhbyny_day = stuMap.get("zqhbyny").substring(8);
		}
		if (stuMap.get("zqrq") != null && stuMap.get("zqrq").length()==8){
			zqrq_year = stuMap.get("zqrq").substring(0,4);
			zqrq_mon = stuMap.get("zqrq").substring(4,6);
			zqrq_day = stuMap.get("zqrq").substring(6);
		}
		stuMap.put("bynyn_mon", bynyn_mon);
		stuMap.put("bynyn_day", bynyn_day);
		stuMap.put("byny_year", byny_year);
		stuMap.put("byny_mon", byny_mon);
		stuMap.put("byny_day", byny_day);
		stuMap.put("zqhbyny_year", zqhbyny_year);
		stuMap.put("zqhbyny_mon", zqhbyny_mon);
		stuMap.put("zqhbyny_day", zqhbyny_day);
		stuMap.put("dkqx1_year", dkqx1_year);
		stuMap.put("dkqx1_mon", dkqx1_mon);
		stuMap.put("dkqx1_day", dkqx1_day);
		stuMap.put("dkqx2_year", dkqx2_year);
		stuMap.put("dkqx2_mon", dkqx2_mon);
		stuMap.put("dkqx2_day", dkqx2_day);
		stuMap.put("zqrq_year", zqrq_year);
		stuMap.put("zqrq_mon", zqrq_mon);
		stuMap.put("zqrq_day", zqrq_day);
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zqxysqb");
	}
	
	/**
	 * 展期协议审核页面
	 * zqxysh ----- 展期协议审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZqxyxx( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
		}
		if ("pass".equalsIgnoreCase(queryModel.getGo())) {
			service.modZqxyxx( request.getParameter("cbVal"),
					  "通过", request);
			queryModel.setGo("go");
		}
		if ("notPass".equalsIgnoreCase(queryModel.getGo())) {
			service.modZqxyxx( request.getParameter("cbVal"),
					  "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getZqxyTit();
		List<String[]> resList = service.getZqxyRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("realTable", "zgdzdx_zqxy");
		request.setAttribute("tableName", "view_zgdzdx_zqxy");
		return mapping.findForward("zqxysh");
	}
	
	/**
	 * 展期协议信息导出
	 * zqxyExp ----- 展期协议信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqxyExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getZqxyExp(queryModel, response,request);
		return mapping.findForward("zqxyExp");
	}

	/**
	 * 展期后还款协议页面
	 * zqhhkxysq ----- 展期后还款协议页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqhhkxysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName :  request.getParameter("xh") ;//用户类型是学生则直接获取用户名
		String pkVal =  request.getParameter("pkVal") ;
		pkVal = StringUtils.isNull(pkVal) ? xh : pkVal;
		xh = pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getzqhhkxyxx(pkVal);
			if (stuMap.size() < 5) {
				request.setAttribute("isNull", "is");
			}
		}
		DAO dao = DAO.getInstance();
		request.setAttribute("sfkdy", dao.getOneRs("select count(*) num from zgdzdx_hkjzqhhkxyxx where xh=? and xz='2'", new String[]{xh}, "num"));
		request.setAttribute("sfksq", service.getZqhHkxyQx(sUserType, userDep,
				xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("path", "zgdzdx_xszz.do?method=zqhhkxysq");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zqhhkxysq");
	}
	
	/**
	 * 展期后保存还款协议信息
	 * zqhhkxysqSave ---- 展期后保存还款协议信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqhhkxysqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveGjzxdkxx(gjzxdkModel, "2", request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxdkModel.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getzqhhkxyxx(pkVal);
			if (stuMap.size() < 5) {
				request.setAttribute("isNull", "is");
			}
		}
		DAO dao = DAO.getInstance();
		request.setAttribute("sfkdy", dao.getOneRs("select count(*) num from zgdzdx_hkjzqhhkxyxx where xh=? and xz='2'", new String[]{xh}, "num"));
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zqhhkxysqSave");
	}
	
	/**
	 * 展期后还款协议页面
	 * zqhhkxysqb ----- 展期后还款协议申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqhhkxysqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzZgdzdxActionForm);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getzqhhkxyxx(model.getXh());
		String zqrq_year = "";
		String zqrq_mon = "";
		String hbrq_year = "";
		String hbrq_mon = "";
		String byny_year="";
		String byny_mon = "";
		String byny_day = "";
		String bynyn_year="";
		String bynyn_mon = "";
		String bynyn_day = "";
		
		String zqnx = stuMap.get("zqnx");
		
		
		if (stuMap.get("zqrq") != null && stuMap.get("zqrq").length()==8){
			zqrq_year = stuMap.get("zqrq").substring(0,4);
			zqrq_mon = stuMap.get("zqrq").substring(4,6);
		}
		if (stuMap.get("hbrq") != null && stuMap.get("hbrq").length()==7){
			hbrq_year = stuMap.get("hbrq").substring(0,4);
			hbrq_mon = stuMap.get("hbrq").substring(5,7);
		}
		
		
		
		//毕业时间
		if (stuMap.get("byny") != null && stuMap.get("byny").length()==10){
			byny_year = stuMap.get("byny").substring(0,4);
			byny_mon = stuMap.get("byny").substring(5,7);
			byny_day = stuMap.get("byny").substring(8);
			
			stuMap.put("zqyear", String.valueOf(Integer.valueOf(byny_year)+Integer.valueOf(zqnx)));
		}
		
		//毕业时间加一天
		if (stuMap.get("bynyn") != null && stuMap.get("bynyn").length()==10){
			bynyn_year = stuMap.get("bynyn").substring(0,4);
			bynyn_mon = stuMap.get("bynyn").substring(5,7);
			bynyn_day = stuMap.get("bynyn").substring(8);
			
			stuMap.put("hkyear", String.valueOf(Integer.valueOf(bynyn_year)+Integer.valueOf(zqnx)));
			
			stuMap.put("hkyear2", String.valueOf(Integer.valueOf(bynyn_year)+Integer.valueOf(zqnx)+2));
		}
		
		
		if (stuMap.get("dkqx") != null && stuMap.get("dkqx").length()==17){
			String dkjssj = stuMap.get("dkqx").substring(9, 17);
			
			stuMap.put("hkjsyear", String.valueOf(Integer.valueOf(dkjssj.substring(0,4))+Integer.valueOf(zqnx)));
			stuMap.put("hkjsmonth",dkjssj.substring(4,6));
			stuMap.put("hkjsday",dkjssj.substring(6));
			
			String str1 = stuMap.get("hkyear")+"-"+bynyn_mon+"-"+bynyn_day;
			String str2 = stuMap.get("hkjsyear")+"-"+stuMap.get("hkjsmonth")+"-"+stuMap.get("hkjsday");
			String str3 = stuMap.get("hkyear2")+"-"+bynyn_mon+"-"+bynyn_day;
			
			stuMap.put("month", String.valueOf(dealDate.getMonths(str1, str2)));
			stuMap.put("month2", String.valueOf(dealDate.getMonths(str2, str3)));
		}
		
		stuMap.put("byny_mon", byny_mon);
		stuMap.put("byny_day", byny_day);
		stuMap.put("bynyn_mon", bynyn_mon);
		stuMap.put("bynyn_day", bynyn_day);
		stuMap.put("zqrq_year", zqrq_year);
		stuMap.put("zqrq_mon", zqrq_mon);
		stuMap.put("hbrq_year", hbrq_year);
		stuMap.put("hbrq_mon", hbrq_mon);
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zqhhkxysqb");
	}
	
	/**
	 * 展期后还款协议审核页面
	 * zqhhkxysh ----- 展期后还款协议审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqhhkxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZqhhkxyxx( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
		}
		if ("pass".equalsIgnoreCase(queryModel.getGo())) {
			service.modZqhhkxyxx( request.getParameter("cbVal"),
					  "通过", request);
			queryModel.setGo("go");
		}
		if ("notPass".equalsIgnoreCase(queryModel.getGo())) {
			service.modZqhhkxyxx( request.getParameter("cbVal"),
					  "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getZqhhkxyTit();
		List<String[]> resList = service.getZqhhkxyRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		String xxsh = DealString.toGBK(xszzZgdzdxActionForm.getXxsh());
		xszzZgdzdxActionForm.setXh(xh);
		xszzZgdzdxActionForm.setXxsh(xxsh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xh");
		request.setAttribute("realTable", "zgdzdx_hkjzqhhkxyxx");
		request.setAttribute("tableName", "view_zgdzdx_hkjzqhhkxyxx");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		return mapping.findForward("zqhhkxysh");
	}
	
	/**
	 * 展期后还款协议信息导出
	 * zqhhkxyExp ----- 展期后还款协议信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqhhkxyExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getZqhhkxyExp(queryModel, response,request);
		return mapping.findForward("zqhhkxyExp");
	}
	
	/**
	 * 提前还款
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tqhk");
	}
	
	/**
	 * 国家助学贷款查询页面
	 * gjzxdkQuery ----- 国家助学贷款查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		String hkxyqssj =  request.getParameter("hkxyqssj") ;
		HashMap<String, String> rs1 = new HashMap<String, String>();
		queryModel.setGo( request.getParameter("go") );
		
		if ("add".equalsIgnoreCase(queryModel.getGo())) {
			service.addGjzxdkLsxx( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
			request.setAttribute("addO", "is");
		}
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		queryModel.setHkxyqssj(hkxyqssj);
		List<HashMap<String, String>> topList = service.getGjzxdkTitT();
		List<String[]> resList = service.getGjzxdkResT(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		String xm = DealString.toGBK(xszzZgdzdxActionForm.getXm());
		String sfzh = DealString.toGBK(xszzZgdzdxActionForm.getSfzh());
		xszzZgdzdxActionForm.setXh(xh);
		xszzZgdzdxActionForm.setXm(xm);
		xszzZgdzdxActionForm.setSfzh(sfzh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		rs1.put("hkxyqssj", hkxyqssj);
		request.setAttribute("rs1", rs1);
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zdgzgx_gjzxdk");
		request.setAttribute("tableName", "view_zdgzgx_gjzxdk");
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdkQuery");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjzxdkQuery");
	}
	
	/**
	 * 国家助学贷款个人详细页面
	 * gjzxdkQueryOne ----- 国家助学贷款个人详细页面
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkQueryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		
		String pkVal =  request.getParameter("pkVal") ;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		
		String[] mndkjeT = service.getMndkje(pkVal);
		for (int i = 0; i < mndkjeT.length; i++) {
			if (stuMap.get("fk_xn"+(i+1)+"_je")==null || "".equalsIgnoreCase(stuMap.get("fk_xn"+(i+1)+"_je"))) {
				stuMap.put("fk_xn"+(i+1)+"_je", mndkjeT[i]);
			}
		}
		
		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			userType = "admin";
		}
		// ----------- 2010/5/6 luojw begin ------------
		
		String fkzje = stuMap.get("fkzje");// 放款总金额
		String sftqhk = stuMap.get("sftqhk");// 是否提前还款
		String tqhkje = stuMap.get("tqhkje");// 提前还款金额
		String dkye = fkzje;//贷款余额
		String zqje = "";//展期金额
		String iszq = "no";//展期与否
		
		// 贷款余额:放款总金额-提前还款金额
		if ("是".equalsIgnoreCase(sftqhk) && !Base.isNull(fkzje)
				&& !Base.isNull(tqhkje)) {
			float float_dkje = Float.parseFloat(fkzje)
					- Float.parseFloat(tqhkje);
			dkye = String.valueOf(float_dkje);
		}

		// 展期金额 与贷款余额相同
		HashMap<String, String> zqMap = service.getZqxyInfo(stuMap.get("xh"));
		if (zqMap != null && zqMap.size() > 0) {
			String shjg = zqMap.get("shjg");
			if ("通过".equalsIgnoreCase(shjg)) {
				zqje = dkye;
				iszq = "yes";
			}
		}
		
		stuMap.put("dkye", dkye);
		stuMap.put("zqje", zqje);
		stuMap.put("iszq", iszq);
		
		// -----------end-----------------
		request.setAttribute("userType", userType);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkQueryOne");
	}
	
	/**
	 * 保存国家助学贷款个人详细页面
	 * gjzxdkQueryOneSave ---- 保存国家助学贷款个人详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkQueryOneSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		String userType = request.getSession().getAttribute("userType").toString();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveGjzxdkxx(gjzxdkModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String pkVal = gjzxdkModel.getXh();
		HashMap<String, String> stuMap = service.getGjzxdkxx(pkVal);
		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			userType = "admin";
		}
		
		// ----------- 2010/5/6 luojw begin ------------

		String fkzje = stuMap.get("fkzje");// 放款总金额
		String sftqhk = stuMap.get("sftqhk");// 是否提前还款
		String tqhkje = stuMap.get("tqhkje");// 提前还款金额
		String dkye = fkzje;// 贷款余额
		String zqje = "";// 展期金额
		String iszq = "no";// 展期与否

		// 贷款余额:放款总金额-提前还款金额
		if ("是".equalsIgnoreCase(sftqhk) && !Base.isNull(fkzje)
				&& !Base.isNull(tqhkje)) {
			float float_dkje = Float.parseFloat(fkzje)
					- Float.parseFloat(tqhkje);
			dkye = String.valueOf(float_dkje);
		}

		// 展期金额 与贷款余额相同
		HashMap<String, String> zqMap = service.getZqxyInfo(stuMap.get("xh"));
		if (zqMap != null && zqMap.size() > 0) {
			String shjg = zqMap.get("shjg");
			if ("通过".equalsIgnoreCase(shjg)) {
				zqje = dkye;
				iszq = "yes";
			}
		}

		stuMap.put("dkye", dkye);
		stuMap.put("zqje", zqje);
		stuMap.put("iszq", iszq);

		// -----------end-----------------
		
		request.setAttribute("userType", userType);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkQueryOneSave");
	}

	/**
	 * 国家助学贷款借款借据页面设置
	 * gjzxdkgs ----- 国家助学贷款借款借据页面设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkgs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxService service = new XszzZgdzdxService();
		String act =  request.getParameter("act") ;
		
		if ("save".equals(act)) {
			boolean res = false;
			String msgBack = "保存失败";
			String topstr =  request.getParameter("topstr") ;
			String leftstr =  request.getParameter("leftstr");
			String fontstr =  request.getParameter("fontstr");
			res = service.insertTemplate(topstr, leftstr, fontstr, "zgdzdx_gjzxdk", request);
			if (res) {
				msgBack = "保存成功";
			}
			request.setAttribute("back", msgBack);
		}
		HashMap<String, String> rsMap = service.getTeplateInf("zgdzdx_gjzxdk");
		request.setAttribute("rsMap", rsMap);
		
		return mapping.findForward("gjzxdkgs");
	}
	
	/**
	 * 国家助学贷款借款借据打印设置
	 * gjzxdkjjsz ----- 国家助学贷款借款借据打印设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkjjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String pkVal =  request.getParameter("pkVal") ;
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkjjsz");
	}
	
	/**
	 * 国家助学贷款借款借据打印
	 * gjzxdkjjdy ----- 国家助学贷款借款借据打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkjjdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> stuMap = new HashMap<String, String>();
		HashMap<String, String> rs = new HashMap<String, String>();
		String pkVal =  request.getParameter("pkVal") ;
		String qs =  request.getParameter("qs") ;
		String jjrq =  request.getParameter("jjrq") ;
		XszzZgdzdxService service = new XszzZgdzdxService();
		
		stuMap = service.getGjzxdkju(pkVal, qs, jjrq);
		rs = service.getTeplateInf("zgdzdx_gjzxdk");
		
		request.setAttribute("rsMap", stuMap);
		request.setAttribute("rsMaptmp", rs);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkjjdy");
	}
	
	/**
	 * 国家助学贷款历史查询页面
	 * gjzxdklsQuery ----- 国家助学贷款历史查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getGjzxdklsTit();
		List<String[]> resList = service.getGjzxdklsRes(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "ZGDZDX_GJZXDK_LS");
		request.setAttribute("tableName", "ZGDZDX_GJZXDK_LS");
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdkls");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjzxdkls");
	}
	
	/**
	 * 国家助学贷款历史个人详细页面
	 * gjzxdklsOne ----- 国家助学贷款历史个人详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdklsOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		
		String pkVal =  request.getParameter("pkVal") ;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdklsxx(pkVal);
		
		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			userType = "admin";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdklsOne");
	}
	
	/**
	 * 保存国家助学贷款历史个人详细页面
	 * gjzxdklsOneSave ---- 保存国家助学贷款历史个人详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdklsOneSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		GjzxdkLsModel gjzxdklsModel = new GjzxdkLsModel();
		String userType = request.getSession().getAttribute("userType").toString();
		BeanUtils.copyProperties(gjzxdklsModel, xszzZgdzdxActionForm);
		XszzZgdzdxService service = new XszzZgdzdxService();
		boolean bJg = service.saveGjzxdklsxx(gjzxdklsModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String pkVal = gjzxdklsModel.getXh();
		HashMap<String, String> stuMap = service.getGjzxdklsxx(pkVal);
		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			userType = "admin";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdklsOneSave");
	}
	
	/**
	 * 国家助学贷款历史信息导出
	 * gjzxdklsExp ----- 国家助学贷款历史信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdklsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getGjzxdklsExp(queryModel, response,request);
		return mapping.findForward("gjzxdklsExp");
	}
	
	/**
	 * @describe 奖助学金项目的查询
	 * @author zhoumi
	 * @return
	 */
	public ActionForward jzxj_xmwf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm";
		String tips = "奖助学金 - 基础数据维护 - 项目维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "jzxj_xmdmb";
		String[] colList = new String[] { "主键", "xmdm", "xmmc", "xmbz","jlckje","jbmc" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmdm =  request.getParameter("xmdm") ;
		String xmmc =  request.getParameter("xmmc") ;
		String xmbz =  request.getParameter("xmbz") ;

		querry.append(SearchUtils.likeSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xmbz", xmbz));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);
		map.put("xmbz", xmbz);

		String sql = "select xmdm 主键,(select lbmc from dzdxZxjlbb where a.lbdm = lbdm) jbmc,a.* from jzxj_xmdmb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		colListCN[5]="类别名称";
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmwf");
	}

	/**
	 * @describe 根据主键得到奖助学金项目详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jzxj_xmwfEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String doit =  request.getParameter("doit") ;
		String sql = "select xmdm,xmmc,xmbz,bz,jlckje,lbdm from jzxj_xmdmb where xmdm=?";
		String[] outString = new String[] { "xmdm", "xmmc", "xmbz", "bz","jlckje","lbdm" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm =  request.getParameter("xmdm") ;
			String xmmc =  request.getParameter("xmmc") ;
			String xmbz =  request.getParameter("xmbz") ;
			String jlckje =  request.getParameter("jlckje") ;
			String bz =  request.getParameter("bz") ;
			String lbdm =  request.getParameter("lbdm") ;
			boolean b = false;
			if ("add".equalsIgnoreCase(doit)) {
				String num = dao.getOneRs(
								"select count(*) num from jzxj_xmdmb where xmdm=?",
								new String[] { xmdm }, "num");
				if ("0".equalsIgnoreCase(num)) {
					b = StandardOperation.insert("jzxj_xmdmb",
							new String[] { "xmdm", "xmmc", "xmbz", "bz","jlckje","lbdm" }, new String[] {
									xmdm, xmmc, xmbz, bz,jlckje,lbdm}, request);
				} else {
					request.setAttribute("have", "have");

				}
			} else if ("edit".equalsIgnoreCase(doit)) {
				b = StandardOperation.update("jzxj_xmdmb", new String[] {
						"xmmc", "xmbz", "bz","jlckje","lbdm" },
						new String[] { xmmc, xmbz, bz,jlckje,lbdm}, "xmdm", xmdm, request);
			}
			//助学金级别保存
			XszzZgdzdxActionForm myForm = (XszzZgdzdxActionForm) form;
			String[] jbmc = myForm.getJbmc();
			String[] jbje = myForm.getJbje();
			String delsql = "delete from dzdxZxjXmjbb where xmdm =?";
			boolean del = dao.runUpdate(delsql, new String[]{xmdm});
			if(del){
				if(jbmc!=null){
					String [] sqlArr = new String [jbmc.length];
					for(int i = 0;i<jbmc.length;i++){
						String sqlTmp="insert into dzdxZxjXmjbb values ('"+xmdm+"','"+DealString.toGBK(jbmc[i])+"','"+jbje[i]+"')";
						sqlArr[i] =  sqlTmp;
					}
					dao.runBatch(sqlArr);
				}
			}
			if (b) {
				pkVal = xmdm;
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		sql = "select rownum num,jbmc,jbje from dzdxZxjXmjbb where xmdm=?";
		List<HashMap<String, String>> jbList =dao.getList(sql, new String[]{pkVal}, new String[]{"num","jbmc","jbje"});
		map.put("xmjb", ((Integer)jbList.size()).toString());
		request.setAttribute("jbList", jbList);
		sql = "select lbdm,lbmc from dzdxZxjlbb";
		request.setAttribute("lbList", dao.getList(sql, new String[]{}, new String[]{"lbdm","lbmc"}));
		request.setAttribute("doit", doit);
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmwfEdit");
	}

	/**
	 * @describe 删除奖助学金项目
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmwfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String pkDel =  request.getParameter("pkDel") ;

		XszzComNewDao comNewDao = new XszzComNewDao();
		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*6];
		int j = 0;
		for (int i = 0; i < pkT.length; i++) {
//			StandardOperation.delete("xszz_common_new_bbgsb", "xmdm", pkT[i],
//					request);
			sqlT[j] = "delete jzxj_rswfb1 where xmdm='"+pkT[i]+"'";
			j++;
			sqlT[j] = "delete jzxj_rswfb2 where xmdm='"+pkT[i]+"'";
			j++;
			sqlT[j] = "delete jzxj_xmsjb where xmdm='"+pkT[i]+"'";
			j++;
			sqlT[j] = "delete jzxj_xmjeb where xmdm='"+pkT[i]+"'";
			j++;
			sqlT[j] = "delete jzxj_xmdmb where xmdm='"+pkT[i]+"'";
			j++;
			String[] colT = dao.getArray(
					"select zddm col from jzxj_xmbbzdyzd where xmdm=?",
					new String[] { pkT[i] }, "col");
			for (int k = 0; k < colT.length; k++) {
				StandardOperation.update("jzxj_xssqb",
						"update jzxj_xssqb set zdy" + colT[k]
								+ "='' where 1=1", request);
				String[] outV = comNewDao.getViewComm(
						"view_jzxj_xssqb", "zdy" + colT[k]);
				if (dao
						.runUpdateTab("alter table jzxj_xssqb drop column zdy"
								+ colT[k])) {
					String sqlTemp = "create or replace view view_jzxj_xssqb as select b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh,a.* from jzxj_xssqb a,view_stu_details b where a.xh=b.xh";
					dao.creatView(sqlTemp, outV);
					dao
							.runUpdateTab("comment on table view_jzxj_xssqb is '奖助学金申请信息'");
				}
			}
			sqlT[j] = "delete jzxj_xssqb where xmdm='"+pkT[i]+"'";
			j++;
		}
		dao.runBatch(sqlT);
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_xmwf&go=go", false);
	}

	/**
	 * @describe 奖助学金项目的查询
	 * @author zhoumi
	 * @return
	 * @throws IOException 
	 */
	public ActionForward jzxj_xmmbsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm";
		String tips = "奖助学金 - 基础数据维护 - 项目模版上传";
		String rsNum = "0";// 返回的记录数
		String tableName = "jzxj_xmdmb";
		String[] colList = new String[] { "主键", "xmdm", "xmmc", "sfymb" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmdm =  request.getParameter("xmdm") ;
		String xmmc =  request.getParameter("xmmc") ;
		String sfymb =  request.getParameter("sfymb") ;

		querry.append(SearchUtils.likeSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("sfymb", sfymb));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);
		map.put("sfymb", sfymb);

		String sql = "select xmdm 主键,a.* from jzxj_xmdmb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		String doit =  request.getParameter("doit") ;
		if ("xz".equalsIgnoreCase(doit)) {
			String pkVal =  request.getParameter("pkVal") ;

			String modelPath = servlet.getServletContext().getRealPath("");
			modelPath = modelPath.substring(0, modelPath.length() - 5)
					+ "/xgxtupload/" + pkVal + ".xls";
			File file = new File(modelPath);
			if (!file.exists()) {
				request.setAttribute("isnull", "isnull");
			} else {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(file,
						response.getOutputStream());
				ExcelMethods.submitWritableWorkbookOperations(wwb);
			}
		}
		
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmmbsc");
	}
	
	/**
	 * @describe 奖助学金模版上传
	 * @author zhoumi
	 * @return
	 */
	public ActionForward jzxj_mbsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkVal =  request.getParameter("pkVal") ;
		String dataImported =  request.getParameter("dataImported") ;
		if ("ok".equalsIgnoreCase(dataImported)){
			request.setAttribute("dataImported", dataImported);
		}
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_mbsc");
	}
	
	/**
	 * @describe 奖助学金模版上传
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward jzxj_mbscdo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkVal =  request.getParameter("pkVal") ;
		
		String dir = servlet.getServletContext().getRealPath("");
		dir = dir.substring(0, dir.length()-5)+"/xgxtupload";
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		String fName = pkVal;
		XszzZgdzdxActionForm hff = (XszzZgdzdxActionForm) form;
		FormFile file = hff.getFile();
		if (file == null) {
			return mapping.findForward("false");
		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		
		StandardOperation.update("jzxj_xmdmb", new String[] { "sfymb" },
				new String[] { "是" }, "xmdm", pkVal, request);
		
		request.setAttribute("pkVal", pkVal);
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_mbsc&dataImported=ok",false);
	}
	
	/**
	 * @describe 项目金额的查询
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmjewh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm||xmje";
		String tips = "奖助学金 - 基础数据维护 - 项目金额维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_jzxj_xmjeb";
		String[] colList = new String[] { "主键", "xmdm", "xmmc", "xmje" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmdm =  request.getParameter("xmdm") ;
		String xmmc =  request.getParameter("xmmc") ;

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);

		String sql = "select xmdm||xmje 主键,a.* from view_jzxj_xmjeb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmjewh");
	}

	/**
	 * @describe 根据主键得到项目金额详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jzxj_xmjeEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String sql = "select xmdm,xmmc,xmje from view_jzxj_xmjeb where xmdm||xmje=?";
		String[] outString = new String[] { "xmdm", "xmmc", "xmje" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm =  request.getParameter("xmdm") ;
			String xmje =  request.getParameter("xmje") ;
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from jzxj_xmjeb where xmdm||xmje=?",
							new String[] { xmdm + xmje }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("jzxj_xmjeb", new String[] {
						"xmdm", "xmje" }, new String[] { xmdm, xmje },
						request);
				if (b) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			} else {
				request.setAttribute("have", "have");
			}
			pkVal = xmdm + xmje;
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmjeEdit");
	}

	/**
	 * @describe 删除项目金额数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmjeDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String pkDel =  request.getParameter("pkDel") ;

		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete jzxj_xmjeb where xmdm||xmje='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_xmjewh&go=go", false);
	}

	/**
	 * @describe 项目申请时间设定
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmsjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmdm||xydm";
		String tips = "奖助学金 - 基础数据维护 - 项目申请时间维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_jzxj_xmsjb";
		String[] colList = new String[] { "主键", "xmmc", "xymc",
				"sfkns", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmdm =  request.getParameter("xmdm") ;
		String xydm =  request.getParameter("xydm") ;
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xydm", xydm);

		String sql = "select xmdm||xydm 主键,a.* from view_jzxj_xmsjb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmsjwh");
	}

	/**
	 * @describe 根据主键得到项目时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jzxj_xmsjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String sql = "select xmdm,xmmc,xydm,xymc,sfkns,kssj,jssj from view_jzxj_xmsjb where xmdm||xydm=?";
		String[] outString = new String[] { "xmdm", "xmmc", "xydm", "xymc",
				"sfkns", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm =  request.getParameter("xmdm") ;
			String xydm =  request.getParameter("xydm") ;
			String sfkns =  request.getParameter("sfkns") ;
			String kssj =  request.getParameter("kssj") ;
			String jssj =  request.getParameter("jssj") ;
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_jzxj_xmsjb where xmdm||xydm=?",
							new String[] { xmdm + xydm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation
						.insert("jzxj_xmsjb", new String[] { "xmdm", "xydm",
								"sfkns", "kssj", "jssj" }, new String[] { xmdm,
								xydm, sfkns, kssj, jssj }, request);
			} else {
				b = StandardOperation.update("jzxj_xmsjb", new String[] {
						"sfkns", "kssj", "jssj" }, new String[] { sfkns, kssj,
						jssj }, "xmdm||xydm", xmdm + xydm, request);
			}
			if (b) {
				request.setAttribute("ok", "ok");
				pkVal = xmdm + xydm;
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmsjEdit");
	}

	/**
	 * @describe 批量设置项目申请时间
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmsjPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act =  request.getParameter("act") ;
		String pkDel =  request.getParameter("pkDel") ;
		String sfkns = "否";
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			sfkns =  request.getParameter("sfkns") ;
			kssj =  request.getParameter("kssj") ;
			jssj =  request.getParameter("jssj") ;
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update jzxj_xmsjb set sfkns='" + sfkns + "',kssj='"
						+ kssj + "',jssj='" + jssj + "' where xmdm||xydm='"
						+ pkT[i] + "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("sfkns", sfkns);
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("jzxj_xmsjPlsz");
	}

	/**
	 * @describe 初始化资助申请时间设置数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmsjcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sfbc =  request.getParameter("sfbc") ;

		String[] xyList = dao.getArray(
				"select xydm from view_xsjbxx group by xydm", new String[] {},
				"xydm");
		String[] bbList = dao.getArray(
				"select xmdm from jzxj_xmdmb group by xmdm", new String[] {},
				"xmdm");
		if ("no".equalsIgnoreCase(sfbc)) {
			StandardOperation.delete("delete jzxj_xmsjb where 1=1", "jzxj_xmsjb", request);
			dao
					.runUpdate(
							"insert into jzxj_xmsjb(xmdm,xydm) (select a.xmdm,b.xydm from jzxj_xmdmb a,(select xydm from view_xsjbxx group by xydm) b)",
							new String[] {});
		} else {
			String[] sqlT = new String[(xyList.length*bbList.length)];
			int x = 0;
			for (int i = 0; i < xyList.length; i++) {
				String xydm = xyList[i];
				for (int j = 0; j < bbList.length; j++) {
					String bbdm = bbList[j];
					String num = dao
							.getOneRs(
									"select count(*) num from jzxj_xmsjb where xmdm=? and xydm=?",
									new String[] { bbdm, xydm }, "num");
					if ("0".equalsIgnoreCase(num)) {
						sqlT[x] = "insert into jzxj_xmsjb(xmdm,xydm) values('"+bbdm+"','"+xydm+"')";
						x++;
					}
				}
			}
			dao.runBatch(sqlT);
		}
		request.setAttribute("endCsh", "end");
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_xmsjwh&go=go", false);
	}
	
	/**
	 * @describe 项目总体人数设定
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmrswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmdm";
		String tips = "奖助学金 - 基础数据维护 - 项目人数维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_jzxj_rswfb1";
		String[] colList = new String[] { "主键", "xmdm", "xmmc", "zrs", "sfyx" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmdm =  request.getParameter("xmdm") ;
		String xmmc =  request.getParameter("xmmc") ;

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));
		
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);

		String sql = "select xmdm 主键,a.* from view_jzxj_rswfb1 a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmrswh");
	}
	
	/**
	 * @describe 批量设置项目人数
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmrsPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act =  request.getParameter("act") ;
		String pkDel =  request.getParameter("pkDel") ;
		String sfyx = "否";
		String zrs = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			sfyx =  request.getParameter("sfyx") ;
			zrs =  request.getParameter("zrs") ;
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update jzxj_rswfb1 set zrs='"+zrs+"',sfyx='"+sfyx+"' where xmdm='"+pkT[i]+"'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("sfyx", sfyx);
		map.put("zrs", zrs);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("jzxj_xmrsPlsz");
	}

	/**
	 * @describe 初始化项目人数设置数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmrscsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sfbc =  request.getParameter("sfbc") ;

		String[] bbList = dao.getArray(
				"select xmdm from jzxj_xmdmb group by xmdm",
				new String[] {}, "xmdm");
		if ("no".equalsIgnoreCase(sfbc)) {
			StandardOperation.delete("delete jzxj_rswfb1 where 1=1", "jzxj_xmsjb", request);
			dao
					.runUpdate(
							"insert into jzxj_rswfb1(xmdm) (select xmdm from jzxj_xmdmb)",
							new String[] {});
		} else {
			int i = 0;
			for (int j = 0; j < bbList.length; j++) {
				String bbdm = bbList[j];
				String num = dao
						.getOneRs(
								"select count(*) num from jzxj_rswfb1 where xmdm=?",
								new String[] { bbdm }, "num");
				String[] sqlT = new String[bbList.length];
				if ("0".equalsIgnoreCase(num)) {
					sqlT[i] = "insert into jzxj_rswfb1(xmdm) values('"+bbdm+"')";
					i++;
				}
				dao.runBatch(sqlT);
			}
		}
		request.setAttribute("endCsh", "end");
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_xmrswh&go=go", false);
	}

	/**
	 * @describe 根据主键得到项目具体人数设置信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jzxj_xmrsEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		List<Object> rs = new ArrayList<Object>();
		String writeAble = "yes";// 写权限
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		
		String[] xyList = dao.getArray(
				"select xydm from view_xsjbxx group by xydm", new String[] {},
				"xydm");
		
		int i = 0;
		String[] sqlT = new String[xyList.length];
		for (String xydm : xyList){
			String num = dao
					.getOneRs(
							"select count(*) num from jzxj_rswfb2 where xmdm=? and xydm=?",
							new String[] { pkVal, xydm }, "num");
			if ("0".equalsIgnoreCase(num)){
				sqlT[i] = "insert into jzxj_rswfb2(xmdm,xydm) values('"+pkVal+"','"+xydm+"')";
				i++;
			}
		}
		dao.runBatch(sqlT);
		
		if ("save".equalsIgnoreCase(act)) {
			service.saveJzxjjtrs(xszzZgdzdxActionForm.getPks(), Arrays2
					.toGBKArrays(request.getParameterValues("szrs")), Arrays2
					.toGBKArrays(request.getParameterValues("sfyx")), request);
			request.setAttribute("end", "end");
		}
		
		String sql = "select xmdm||xydm pks,a.* from view_jzxj_rswfb2 a where a.xmdm=?";
		rs.addAll(dao.getArrayList(sql, new String[] { pkVal }, new String[] {
				"pks", "xymc", "xyrs", "szrs", "sfyx" }));

		String[] tS = dao.getOneRs(
				"select xmmc,zrs,sfyx sfyxT from view_jzxj_rswfb1 where xmdm=?",
				new String[] { pkVal }, new String[] { "xmmc", "zrs", "sfyxT" });
		request.setAttribute("xmmc", tS[0]);
		request.setAttribute("zrs", tS[1]);
		request.setAttribute("sfyxT", tS[2]);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", rs);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmrsEdit");
	}

	/**
	 * @describe 资助报表自定义字段的查询
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmbbzdyzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		XszzZgdzdxService service = new XszzZgdzdxService();
		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "zddm";
		String tips = "奖助学金 - 基础数据维护 - 项目自定义字段";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_jzxj_xmbbzdyzd";
		String[] colList = new String[] { "主键", "xmmc", "zddm", "zdmc", "zdlx",
				"zddx", "bxwsz" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmdm =  request.getParameter("xmdm") ;

		querry.append(SearchUtils.equalSql("xmdm", xmdm));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);

		String sql = "select zddm 主键,a.* from view_jzxj_xmbbzdyzd a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmbbzdyzd");
	}

	/**
	 * @describe 根据主键得到资助自定义字段详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jzxj_xmbbzdyzdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String acDo =  request.getParameter("acDo") ;
		String sql = "select xmdm,xmmc,zddm,zdmc,zdlx,zddx,bxwsz from view_jzxj_xmbbzdyzd where zddm=?";
		String[] outString = new String[] { "xmdm", "xmmc", "zddm", "zdmc",
				"zdlx", "zddx", "bxwsz" };

		if ("save".equalsIgnoreCase(acDo)) {
			String xmdm =  request.getParameter("xmdm") ;
			String zddm =  request.getParameter("zddm") ;
			String zdmc =  request.getParameter("zdmc") ;
			String zdlx =  request.getParameter("zdlx") ;
			String zddx =  request.getParameter("zddx") ;
			String bxwsz =  request.getParameter("bxwsz") ;
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_jzxj_xmbbzdyzd where zddm=?",
							new String[] { zddm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				String sqlTemp = "alter table jzxj_xssqb add (zdy"
						+ zddm + " varchar(" + zddx + "))";
				dao.runUpdateTab(sqlTemp);
				String[] outV = dao.getViewComm("view_jzxj_xssqb");
				sqlTemp = "create or replace view view_jzxj_xssqb as select b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh,a.* from jzxj_xssqb a,view_stu_details b where a.xh=b.xh";
				dao.creatView(sqlTemp, outV);
				sqlTemp = "comment on column jzxj_xssqb.zdy" + zddm
						+ " is '" + zdmc + "'";
				dao.runUpdateTab(sqlTemp);
				dao.runUpdateTab("comment on table view_jzxj_xssqb is '奖助学金申请信息'");
				sqlTemp = "comment on column view_jzxj_xssqb.zdy"
						+ zddm + " is '" + zdmc + "'";
				dao.runUpdateTab(sqlTemp);
				b = StandardOperation.insert("jzxj_xmbbzdyzd", new String[] {
						"xmdm", "zddm", "zdmc", "zdlx", "zddx", "bxwsz" },
						new String[] { xmdm, zddm, zdmc, zdlx, zddx, bxwsz },
						request);
			} else {
				if ("add".equalsIgnoreCase(act)) {
					request.setAttribute("have", "have");
				} else {
					String sqlTemp = "comment on column jzxj_xssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					sqlTemp = "comment on column view_jzxj_xssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					b = StandardOperation.update("jzxj_xmbbzdyzd",
							new String[] { "zdmc", "zdlx", "zddx", "bxwsz" },
							new String[] { zdmc, zdlx, zddx, bxwsz }, "zddm",
							zddm, request);
				}
			}
			pkVal = zddm;
			if (b) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("rs", map);
		request.setAttribute("acDo", acDo);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmbbzdyzdEdit");
	}

	/**
	 * @describe 删除资助自定义字段数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmbbzdyzdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String pkDel =  request.getParameter("pkDel") ;
		String[] pkT = pkDel.split("!!splitOne!!");
		for (int i = 0; i < pkT.length; i++) {
			if (!"".equalsIgnoreCase(pkT[i]) && !"zddm".equalsIgnoreCase(pkT[i])) {
				StandardOperation.update("jzxj_xssqb",
						"update jzxj_xssqb set zdy" + pkT[i]
								+ "='' where 1=1", request);
				String[] outV = comNewDao.getViewComm(
						"view_jzxj_xssqb", "zdy" + pkT[i]);
				if (dao
						.runUpdateTab("alter table jzxj_xssqb drop column zdy"
								+ pkT[i])) {
					String sqlTemp = "create or replace view view_jzxj_xssqb as select b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh,a.* from jzxj_xssqb a,view_stu_details b where a.xh=b.xh";
					dao.creatView(sqlTemp, outV);
					dao.runUpdateTab("comment on table view_jzxj_xssqb is '奖助学金申请信息'");
					StandardOperation.delete("jzxj_xmbbzdyzd", "zddm",
							pkT[i], request);
				}
			}
		}
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_xmbbzdyzd&go=go",
				false);
	}

	/**
	 * @describe 项目其他设置
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmqtsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm||szbz||sznrdm";
		String tips = "奖助学金 - 基础数据维护 - 项目其他设置";
		String rsNum = "0";// 返回的记录数
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String szbz =  request.getParameter("szbz") ;
		String xmdm =  request.getParameter("xmdm") ;
		String nj =  request.getParameter("nj") ;
		String xydm =  request.getParameter("xydm") ;
		String zydm =  request.getParameter("zydm") ;

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.equalSql("szbz", szbz));
		if ("p1".equalsIgnoreCase(szbz)){
			querry.append(SearchUtils.equalSql("sznrdm", nj));
		} else if ("p2".equalsIgnoreCase(szbz)){
			querry.append(SearchUtils.equalSql("sznrdm", xydm));
		} else if ("p3".equalsIgnoreCase(szbz)){
			querry.append(SearchUtils.equalSql("xydm", xydm));
			querry.append(SearchUtils.equalSql("sznrdm", zydm));
		}

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("szbz", szbz);
		map.put("xmdm", xmdm);
		map.put("nj", nj);
		map.put("xydm", xydm);
		map.put("zydm", zydm);

		String sql = "";
		String[] colList = new String[] {};
		String[] colListCN = new String[] {};
		if ("p1".equalsIgnoreCase(szbz)){
			sql = "select xmdm||szbz||sznrdm 主键,a.xmdm,(select z.xmmc from jzxj_xmdmb z where a.xmdm=z.xmdm) xmmc,sznrdm nj,'年级' szbz,bjgmc,xfjd,zhszpf,zhszpm from jzxj_qtszb a"
				+ querry.toString();
			colList = new String[] { "主键", "xmdm", "xmmc", "nj", "szbz", "bjgmc", "xfjd", "zhszpf", "zhszpm" };
			colListCN = new String[] { "主键", "项目代码", "项目名称", "年级", "设置标志", "不及格门次", "学分绩点", "综合素质评分", "综合素质排名(%)" };
		} else if ("p2".equalsIgnoreCase(szbz)){
			sql = "select xmdm||szbz||sznrdm 主键,a.xmdm,(select z.xmmc from jzxj_xmdmb z where a.xmdm=z.xmdm) xmmc,(select b.xymc from view_njxyzybj b where b.xydm=a.sznrdm group by b.xydm,b.xymc) xymc,'学院' szbz,bjgmc,xfjd,zhszpf,zhszpm from jzxj_qtszb a"
				+ querry.toString();
			colList = new String[] { "主键", "xmdm", "xmmc", "xymc", "szbz", "bjgmc", "xfjd", "zhszpf", "zhszpm" };
			colListCN = new String[] { "主键", "项目代码", "项目名称", Base.YXPZXY_KEY, "设置标志", "不及格门次", "学分绩点", "综合素质评分", "综合素质排名(%)" };
		} else if ("p3".equalsIgnoreCase(szbz)){
			sql = "select xmdm||szbz||sznrdm 主键,a.xmdm,a.xmmc,xymc,zymc,'专业' szbz,bjgmc,xfjd,zhszpf,zhszpm from view_jzxj_qtszb a"
				+ querry.toString();
			colList = new String[] { "主键", "xmdm", "xmmc", "xymc", "zymc", "szbz", "bjgmc", "xfjd", "zhszpf", "zhszpm" };
			colListCN = new String[] { "主键", "项目代码", "项目名称", Base.YXPZXY_KEY, "专业", "设置标志", "不及格门次", "学分绩点", "综合素质评分", "综合素质排名(%)" };
		}
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			if (!StringUtils.isNull(sql)) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			
		}
		
		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("jzxj_xmqtsz");
	}
	
	/**
	 * @describe 根据主键得到项目其他设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jzxj_xmqtEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal =  request.getParameter("pkVal") ;
		String act =  request.getParameter("act") ;
		String actDo =  request.getParameter("actDo") ;
		String sql = "";
		String[] outString = new String[] {};
		String szbz =  request.getParameter("szbz") ;

		if ("save".equalsIgnoreCase(actDo)) {
			String xmdm =  request.getParameter("xmdm") ;
			String sznrdm =  request.getParameter("sznrdm") ;
			String bjgmc =  request.getParameter("bjgmc") ;
			String xfjd =  request.getParameter("xfjd") ;
			String zhszpf =  request.getParameter("zhszpf") ;
			String zhszpm =  request.getParameter("zhszpm") ;
			boolean b = false;
			pkVal = xmdm + szbz + sznrdm;

			String num = dao
					.getOneRs(
							"select count(*) num from jzxj_qtszb where xmdm||szbz||sznrdm=?",
							new String[] { pkVal }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("jzxj_qtszb", new String[] {
						"xmdm", "szbz", "sznrdm", "bjgmc", "xfjd", "zhszpf",
						"zhszpm" }, new String[] { xmdm, szbz, sznrdm, bjgmc,
						xfjd, zhszpf, zhszpm }, request);
				if (b) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			} else if ("edit".equalsIgnoreCase(act)) {
				b = StandardOperation.update("jzxj_qtszb", new String[] {
						"bjgmc", "xfjd" }, new String[] {
						bjgmc, xfjd }, "xmdm||szbz||sznrdm",
						pkVal, request);
				if (b) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			} else {
				request.setAttribute("have", "have");
			}
		}

		if ("p1".equalsIgnoreCase(szbz)){
			sql = "select a.xmdm,(select z.xmmc from jzxj_xmdmb z where a.xmdm=z.xmdm) xmmc,sznrdm nj,szbz,bjgmc,xfjd,zhszpf,zhszpm from jzxj_qtszb a where xmdm||szbz||sznrdm=?";
			outString = new String[] { "xmdm", "xmmc", "nj", "szbz", "bjgmc", "xfjd", "zhszpf", "zhszpm" };
		} else if ("p2".equalsIgnoreCase(szbz)){
			sql = "select a.xmdm,(select z.xmmc from jzxj_xmdmb z where a.xmdm=z.xmdm) xmmc,a.sznrdm xydm,(select b.xymc from view_njxyzybj b where b.xydm=a.sznrdm group by b.xydm,b.xymc) xymc,szbz,bjgmc,xfjd,zhszpf,zhszpm from jzxj_qtszb a where xmdm||szbz||sznrdm=?";
				outString = new String[] { "xmdm", "xmmc", "xydm", "xymc", "szbz", "bjgmc", "xfjd", "zhszpf", "zhszpm" };
		} else if ("p3".equalsIgnoreCase(szbz)){
			sql = "select xmdm||szbz||sznrdm 主键,a.xmdm,a.xmmc,a.xydm,xymc,a.sznrdm zydm,zymc,szbz,bjgmc,xfjd,zhszpf,zhszpm from view_jzxj_qtszb a where xmdm||szbz||sznrdm=?";
			outString = new String[] { "xmdm", "xmmc", "xydm", "xymc", "zydm", "zymc", "szbz", "bjgmc", "xfjd", "zhszpf", "zhszpm" };
		}
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		if ("p3".equalsIgnoreCase(szbz)){
			commenBean.setXydm(map.get("xydm"));
		}
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("szbz", szbz);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmqtEdit");
	}

	/**
	 * @describe 批量设置项目其他设置信息
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmqtPlEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act =  request.getParameter("act") ;
		String pkDel =  request.getParameter("pkDel") ;
		String bjgmc = "0";
		String xfjd = "0";
		String zhszpf = "0";
		String zhszpm = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			bjgmc =  request.getParameter("bjgmc") ;
			xfjd =  request.getParameter("xfjd") ;
			zhszpf =  request.getParameter("zhszpf") ;
			zhszpm =  request.getParameter("zhszpm") ;
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update jzxj_qtszb set bjgmc='"+bjgmc+"',xfjd='"+xfjd+"',zhszpf='"+zhszpf+"',zhszpm='"+zhszpm+"' where xmdm||szbz||sznrdm='"+pkT[i]+"'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("bjgmc", bjgmc);
		map.put("xfjd", xfjd);
		map.put("zhszpf", zhszpf);
		map.put("zhszpm", zhszpm);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("jzxj_xmqtPlEdit");
	}
	
	/**
	 * @describe 删除项目其他设置数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_xmqtDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String pkDel =  request.getParameter("pkDel") ;
		String szbz =  request.getParameter("szbz") ;

		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete jzxj_qtszb where xmdm||szbz||sznrdm='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
		return new ActionForward("/zgdzdx_xszz.do?method=jzxj_xmqtsz&go=go&szbz="+szbz, false);
	}
	
	/**
	 * @describe 项目申请页面
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jzxj_xmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		XszzZgdzdxService service = new XszzZgdzdxService();
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act =  request.getParameter("act") ;
		String xmdm =  request.getParameter("xmdm") ;
		String xh = "";
		if (!userType.equalsIgnoreCase("stu")) {
			xh =  request.getParameter("xh") ;
		} else {
			xh = sUName;
		}
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = Base.currXn;
		String xq = Base.currXq;

		StringBuffer sb = new StringBuffer(" xh='");
		sb.append(xh);
		sb.append("' ");
		String[] sT = dao.getOneRs("select xmmc,bz,xmbz,jlckje,(select lbmc from dzdxZxjlbb where lbdm = a.lbdm) lbmc from jzxj_xmdmb a where xmdm=?", new String[]{xmdm}, new String[]{"xmmc","bz","xmbz","jlckje","lbmc"});
		if (sT != null) {
			map.put("xmmc", sT[0]);
			map.put("bz", sT[1]);
			map.put("xmbz", sT[2]);
			map.put("jlckje", sT[3]);
			map.put("lbmc", sT[4]);
		} else {
			map.put("xmmc", "");
			map.put("bz", "");
			map.put("xmbz", "");
			map.put("jlckje", "");
			map.put("lbmc", "");
		}
		
		
		
		List<String[]> zdyzdList = xszzDao.getJzxjZdyzdList(xmdm);
		String zdyzdXxxx = xszzDao.getJzxjZdyzdXxxxList(xmdm);
		if(zdyzdList.size() == 0){
			request.setAttribute("isNULL", "is");
		} else {
			request.setAttribute("isNULL", "no");
		}
		request.setAttribute("zdyzdList", zdyzdList);
		request.setAttribute("zdyzdXxxx", zdyzdXxxx);
		boolean sfknsb = false;
		String sfksq = "-1";
		String pkVal =  request.getParameter("pkVal") ;
		if ((null == pkVal) || ("".equalsIgnoreCase(pkVal))) {
			pkVal = xn + xq + xmdm + xh;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;
			sfknsb = dao.isKns(xh);
			
			boolean b = false;
			boolean b1 = false;
			boolean b2 = false;
			String[] sTxscj = dao.getOneRs("select NVL(xf,'0') xf,NVL(bjgmc,'0') bjgmc from JWJK_XSJDCJXX where xh=? and xn=?",new String[] { xh, Base.beforXn },new String[] { "xf", "bjgmc" });
			if (null == sTxscj) {
				sTxscj = new String[] { "0", "0" };
			}
			request.setAttribute("xnxfjd", sTxscj[0]);
			
			String[] sZhcpxx = dao
					.getOneRs(
							"select NVL(zhszzcj,'0') zhcpzcj,NVL(zhszpm,'0') zhszpm from jzxj_zhcpxx where xh=? and xn=? and xq=?",
							new String[] { xh, Base.beforXn, "02" },
							new String[] { "zhcpzcj", "zhszpm" });
			if (null == sZhcpxx) {
				sZhcpxx = new String[] { "0", "0" };
			}
			
			String[] qtszT = dao.getOneRs("select max(xfjd) xfjd,min(bjgmc) bjgmc,max(zhszpf) zhszpf,round(MAX((SELECT COUNT(*) FROM view_xsjbxx WHERE bjdm=(SELECT bjdm FROM view_xsjbxx WHERE xh=?)))/100*NVL(min(zhszpm),'0')) zhszpm from (select xfjd,bjgmc,zhszpf,zhszpm from jzxj_qtszb where xmdm=? and szbz='p1' and sznrdm=(select nj from view_xsjbxx where xh=?) union select xfjd,bjgmc,zhszpf,zhszpm from jzxj_qtszb where xmdm=? and szbz='p2' and sznrdm=(select xydm from view_xsjbxx where xh=?) union select xfjd,bjgmc,zhszpf,zhszpm from jzxj_qtszb where xmdm=? and szbz='p3' and sznrdm=(select zydm from view_xsjbxx where xh=?))", new String[]{xh,xmdm,xh,xmdm,xh,xmdm,xh}, new String[]{"xfjd","bjgmc", "zhszpf", "zhszpm"});
			if (null == qtszT[0] && null == qtszT[1] && null == qtszT[2]
					&& null == qtszT[3]) {
				b = true;
				b1 = true;
				b2 = true;
			} else {
				qtszT[0] = qtszT[0] == null ? "0" : qtszT[0];
				qtszT[1] = qtszT[1] == null ? "500" : qtszT[1];
				qtszT[2] = qtszT[2] == null ? "0" : qtszT[2];
				qtszT[3] = qtszT[3] == null ? "9000" : qtszT[3];
				
				
				if ((Integer.parseInt(qtszT[0]) <= Integer.parseInt(sTxscj[0]))
						&& (Integer.parseInt(qtszT[1]) >= Integer
								.parseInt(sTxscj[1]))) {
					b = true;
				}
				if (Integer.parseInt(sZhcpxx[0]) >= Integer.parseInt(qtszT[2])) {
					b1 = true;
				}
				if (Integer.parseInt(sZhcpxx[1]) <= Integer.parseInt(qtszT[3])) {
					b2 = true;
				}
			}

			sql1 = "select a.kssj,a.jssj,a.sfkns from view_jzxj_xmsjb a,view_xsjbxx b where a.xmdm=? and b.xh=? and a.xydm=b.xydm";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xmdm, xh },
					new String[] { "kssj", "jssj", "sfkns" });
			if (jxjksjssj == null && b && b1 && b2){
				if(!Base.isNull(xmdm)){
					sfksq = "-1";// /可以进行申请
					request.setAttribute("sfksq", sfksq);
				}
			} else if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0
					&& (("否".equalsIgnoreCase(jxjksjssj[2]) || ("".equalsIgnoreCase(jxjksjssj[2]))) || ("是"
							.equalsIgnoreCase(jxjksjssj[2]) && sfknsb)) && b && b1 && b2) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /学生填写申请
					String xmmc = request.getParameter("xmmc");
					String sqly = request.getParameter("sqly");
					String lxdh = request.getParameter("lxdh");
					String rxqhk = request.getParameter("rxqhk");
					String jtzz = request.getParameter("jtzz");
					String yzbm = request.getParameter("yzbm");
					String jtlxdh = request.getParameter("jtlxdh");
					String sfyycjcshzyhd = request.getParameter("sfyycjcshzyhd");
					String sfyysqgjzxdkhqgzx = request.getParameter("sfyysqgjzxdkhqgzx");
					String sfjq = request.getParameter("sfjq");
					String sfge = request.getParameter("sfge");
					String sfdq = request.getParameter("sfdq");
					String sfcj = request.getParameter("sfcj");
					String sfjls = request.getParameter("sfjls");
					String sfly = request.getParameter("sfly");
					String sfzb =request.getParameter("sfzb");
					String jtcy1_xm = request.getParameter("jtcy1_xm");
					String jtcy1_nl = request.getParameter("jtcy1_nl");
					String jtcy1_gx = request.getParameter("jtcy1_gx");
					String jtcy1_gzdw =request.getParameter("jtcy1_gzdw");
					String jtcy1_zy =  request
							.getParameter("jtcy1_zy") ;
					String jtcy1_nsr =  request
							.getParameter("jtcy1_nsr") ;
					String jtcy1_jkzk =  request
							.getParameter("jtcy1_jkzk") ;
					String jtcy2_xm =  request
							.getParameter("jtcy2_xm") ;
					String jtcy2_nl =  request
							.getParameter("jtcy2_nl") ;
					String jtcy2_gx =  request
							.getParameter("jtcy2_gx") ;
					String jtcy2_gzdw =  request
							.getParameter("jtcy2_gzdw") ;
					String jtcy2_zy =  request
							.getParameter("jtcy2_zy") ;
					String jtcy2_nsr =  request
							.getParameter("jtcy2_nsr") ;
					String jtcy2_jkzk =  request
							.getParameter("jtcy2_jkzk") ;
					String jtcy3_xm =  request
							.getParameter("jtcy3_xm") ;
					String jtcy3_nl =  request
							.getParameter("jtcy3_nl") ;
					String jtcy3_gx =  request
							.getParameter("jtcy3_gx") ;
					String jtcy3_gzdw =  request
							.getParameter("jtcy3_gzdw") ;
					String jtcy3_zy =  request
							.getParameter("jtcy3_zy") ;
					String jtcy3_nsr =  request
							.getParameter("jtcy3_nsr") ;
					String jtcy3_jkzk =  request
							.getParameter("jtcy3_jkzk") ;
					String jtcy4_xm =  request
							.getParameter("jtcy4_xm") ;
					String jtcy4_nl =  request
							.getParameter("jtcy4_nl") ;
					String jtcy4_gx =  request
							.getParameter("jtcy4_gx") ;
					String jtcy4_gzdw =  request
							.getParameter("jtcy4_gzdw") ;
					String jtcy4_zy =  request
							.getParameter("jtcy4_zy") ;
					String jtcy4_nsr =  request
							.getParameter("jtcy4_nsr") ;
					String jtcy4_jkzk =  request
							.getParameter("jtcy4_jkzk") ;
					String jtcy5_xm =  request
							.getParameter("jtcy5_xm") ;
					String jtcy5_nl =  request
							.getParameter("jtcy5_nl") ;
					String jtcy5_gx =  request
							.getParameter("jtcy5_gx") ;
					String jtcy5_gzdw =  request
							.getParameter("jtcy5_gzdw") ;
					String jtcy5_zy =  request
							.getParameter("jtcy5_zy") ;
					String jtcy5_nsr =  request
							.getParameter("jtcy5_nsr") ;
					String jtcy5_jkzk =  request
							.getParameter("jtcy5_jkzk") ;
					String jtrjnsr =  request
							.getParameter("jtrjnsr") ;
					String xszbdszqk =  request
							.getParameter("xszbdszqk") ;
					String jtzszrzhqk =  request
							.getParameter("jtzszrzhqk") ;
					String jtzstfywsj =  request
							.getParameter("jtzstfywsj") ;
					String qtqk =  request.getParameter("qtqk");
					String mzbm_txdz =  request
							.getParameter("mzbm_txdz") ;
					String mzbm_yzbm =  request
							.getParameter("mzbm_yzbm") ;
					String mzbm_lxdh =  request
							.getParameter("mzbm_lxdh") ;
					String syd =  request.getParameter("syd");
					String jd =  request.getParameter("jd") ;
					String bjpm =  request.getParameter("bjpm");
					String bkkms =  request.getParameter("bkkms");
					String fblw =  request.getParameter("fblw");
					String yhzs =  request.getParameter("yhzs");
					String sfkns =  request.getParameter("sfkns");

					String num = "0";
					if ("学期".equalsIgnoreCase(map.get("xmbz"))) {
						sql = "select count(*) num from jzxj_xssqb where xn||xq||xmdm||xh=?";
						num = dao.getOneRs(sql, new String[] { xn + xq + xmdm
								+ xh }, "num");
					} else {
						sql = "select count(*) num from jzxj_xssqb where xn||xmdm||xh=?";
						num = dao.getOneRs(sql,
								new String[] { xn + xmdm + xh }, "num");
					}
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("jzxj_xssqb",
								new String[] { "xn", "xq", "xmdm", "xmmc",
										"xh", "sqly", "lxdh", "rxqhk",
										"jtzz", "yzbm", "jtlxdh",
										"sfyycjcshzyhd", "sfyysqgjzxdkhqgzx",
										"sfjq", "sfge", "sfdq", "sfcj",
										"sfjls", "sfly", "sfzb", "jtcy1_xm",
										"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
										"jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
										"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
										"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
										"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
										"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy",
										"jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
										"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
										"jtcy4_zy", "jtcy4_nsr", "jtcy4_jkzk",
										"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
										"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
										"jtcy5_jkzk", "jtrjnsr", "xszbdszqk",
										"jtzszrzhqk", "jtzstfywsj", "qtqk",
										"mzbm_txdz", "mzbm_yzbm", "mzbm_lxdh",
										"sfkns", "syd", "jd", "bjpm", "bkkms",
										"fblw", "yhzs" }, new String[] { xn,
										xq, xmdm, xmmc, xh, sqly, lxdh, rxqhk, jtzz,
										yzbm, jtlxdh, sfyycjcshzyhd,
										sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq,
										sfcj, sfjls, sfly, sfzb, jtcy1_xm,
										jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
										jtcy1_zy, jtcy1_nsr, jtcy1_jkzk,
										jtcy2_xm, jtcy2_nl, jtcy2_gx,
										jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
										jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
										jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
										jtcy3_nsr, jtcy3_jkzk, jtcy4_xm,
										jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
										jtcy4_zy, jtcy4_nsr, jtcy4_jkzk,
										jtcy5_xm, jtcy5_nl, jtcy5_gx,
										jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
										jtcy5_jkzk, jtrjnsr, xszbdszqk,
										jtzszrzhqk, jtzstfywsj, qtqk,
										mzbm_txdz, mzbm_yzbm, mzbm_lxdh, sfkns,
										syd, jd, bjpm, bkkms, fblw, yhzs },
								request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (String[] tempSr : zdyzdList) {
									String srName = "zdy" + tempSr[0];
									String sr =  request
											.getParameter(srName) ;
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update jzxj_xssqb set "
										+ sb.toString()
										+ " where xn||xq||xmdm||xh='" + pkVal + "'";
								StandardOperation.update("jzxj_xssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("学期".equalsIgnoreCase(map.get("xmbz"))) {
							sql = "select count(*) num from jzxj_xssqb where xn||xq||xmdm||xh=? and xxsh='通过'";
							num = dao.getOneRs(sql, new String[] { xn + xq + xmdm
									+ xh }, "num");
						} else {
							sql = "select count(*) num from jzxj_xssqb where xn||xmdm||xh=? and xxsh='通过'";
							num = dao.getOneRs(sql,
									new String[] { xn + xmdm + xh }, "num");
						}
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("jzxj_xssqb",
									new String[] { "xmmc", "sqly", "sqsj",
											"xysh", "xyshyj", "xyshsj",
											"xypzje", "xxsh", "xxshyj",
											"xxshsj", "xxpzje", "lxdh",
											"rxqhk", "jtzz", "yzbm", "jtlxdh",
											"sfyycjcshzyhd",
											"sfyysqgjzxdkhqgzx", "sfjq",
											"sfge", "sfdq", "sfcj", "sfjls",
											"sfly", "sfzb", "jtcy1_xm",
											"jtcy1_nl", "jtcy1_gx",
											"jtcy1_gzdw", "jtcy1_zy",
											"jtcy1_nsr", "jtcy1_jkzk",
											"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
											"jtcy2_gzdw", "jtcy2_zy",
											"jtcy2_nsr", "jtcy2_jkzk",
											"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
											"jtcy3_gzdw", "jtcy3_zy",
											"jtcy3_nsr", "jtcy3_jkzk",
											"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
											"jtcy4_gzdw", "jtcy4_zy",
											"jtcy4_nsr", "jtcy4_jkzk",
											"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
											"jtcy5_gzdw", "jtcy5_zy",
											"jtcy5_nsr", "jtcy5_jkzk",
											"jtrjnsr", "xszbdszqk",
											"jtzszrzhqk", "jtzstfywsj", "qtqk",
											"mzbm_txdz", "mzbm_yzbm",
											"mzbm_lxdh", "sfkns", "syd", "jd",
											"bjpm", "bkkms", "fblw", "yhzs" },
									new String[] { xmmc, sqly, rightNow, "未审核",
											"", "", "0", "未审核", "", "", "0",
											lxdh, rxqhk, jtzz, yzbm, jtlxdh,
											sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
											sfjq, sfge, sfdq, sfcj, sfjls,
											sfly, sfzb, jtcy1_xm, jtcy1_nl,
											jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
											jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
											jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
											jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
											jtcy3_xm, jtcy3_nl, jtcy3_gx,
											jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
											jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
											jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
											jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
											jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
											jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
											jtrjnsr, xszbdszqk, jtzszrzhqk,
											jtzstfywsj, qtqk, mzbm_txdz,
											mzbm_yzbm, mzbm_lxdh, sfkns, syd, jd,
											bjpm, bkkms, fblw, yhzs },
									"xn||xq||xmdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (String[] tempSr : zdyzdList) {
										String srName = "zdy" + tempSr[0];
										String sr =  request
												.getParameter(srName) ;
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update jzxj_xssqb set "
											+ sb.toString()
											+ " where xn||xq||xmdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("jzxj_xssqb", sql, request);
								}
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				if (!b1) {
					request.setAttribute("sfksq", "-2");// 不能申请
				} else if (!b2) {
					request.setAttribute("sfksq", "-3");// 不能申请
				} else {
					request.setAttribute("sfksq", sfksq);// 不能申请
				}
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh =  request.getParameter("xh") ;
			sfknsb = dao.isKns(xh);

			sql1 = "select a.sfkns from view_jzxj_xmsjb a,view_xsjbxx b where a.xmdm=? and b.xh=? and a.xydm=b.xydm";
			String knsT = dao
					.getOneRs(sql1, new String[] { xmdm, xh }, "sfkns");
			
			boolean b = false;
			boolean b1 = false;
			boolean b2 = false;
			String[] qtszT = dao.getOneRs("select max(xfjd) xfjd,min(bjgmc) bjgmc,max(zhszpf) zhszpf,round(max((SELECT COUNT(*) FROM view_xsjbxx WHERE bjdm=(SELECT bjdm FROM view_xsjbxx WHERE xh=?)))/100*NVL(min(zhszpm),'0')) zhszpm from (select xfjd,bjgmc,zhszpf,zhszpm from jzxj_qtszb where xmdm=? and szbz='p1' and sznrdm=(select nj from view_xsjbxx where xh=?) union select xfjd,bjgmc,zhszpf,zhszpm from jzxj_qtszb where xmdm=? and szbz='p2' and sznrdm=(select xydm from view_xsjbxx where xh=?) union select xfjd,bjgmc,zhszpf,zhszpm from jzxj_qtszb where xmdm=? and szbz='p3' and sznrdm=(select zydm from view_xsjbxx where xh=?))", new String[]{xh,xmdm,xh,xmdm,xh,xmdm,xh}, new String[]{"xfjd","bjgmc", "zhszpf", "zhszpm"});
			String[] sTxscj = dao.getOneRs("select NVL(xf,'0') xf,NVL(bjgmc,'0') bjgmc from JWJK_XSJDCJXX where xh=? and xn=?",new String[] { xh, Base.beforXn },new String[] { "xf", "bjgmc" });
			if (null == sTxscj) {
				sTxscj = new String[] { "0", "0" };
			}
			request.setAttribute("xnxfjd", sTxscj[0]);
			
			String[] sZhcpxx = dao
				.getOneRs(
						"select NVL(zhszzcj,'0') zhcpzcj,NVL(zhszpm,'0') zhszpm from jzxj_zhcpxx where xh=? and xn=? and xq=?",
						new String[] { xh, Base.beforXn, "02" },
						new String[] { "zhcpzcj", "zhszpm" });
			if (null == sZhcpxx) {
				sZhcpxx = new String[] { "0", "0" };
			}
			
			if (null == qtszT[0] && null == qtszT[1] && null == qtszT[2]
					&& null == qtszT[3]) {
				b = true;
				b1 = true;
				b2 = true;
			} else {
				qtszT[0] = qtszT[0] == null ? "0" : qtszT[0];
				qtszT[1] = qtszT[1] == null ? "500" : qtszT[1];
				qtszT[2] = qtszT[2] == null ? "0" : qtszT[2];
				qtszT[3] = qtszT[3] == null ? "9000" : qtszT[3];
				
				
				if ((Integer.parseInt(qtszT[0]) <= Integer.parseInt(sTxscj[0]))
						&& (Integer.parseInt(qtszT[1]) >= Integer
								.parseInt(sTxscj[1]))) {
					b = true;
				}
				if (Integer.parseInt(sZhcpxx[0]) >= Integer.parseInt(qtszT[2])) {
					b1 = true;
				}
				if (Integer.parseInt(sZhcpxx[1]) <= Integer.parseInt(qtszT[3])) {
					b2 = true;
				}
			}
			
			if ((("否".equalsIgnoreCase(knsT) || ("".equalsIgnoreCase(knsT)))
					|| ("是".equalsIgnoreCase(knsT) && sfknsb)) && b && b1 && b2) {// /在申请时间范围内
				
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {
					String xmmc =  request.getParameter("xmmc");
					String sqly =  request.getParameter("sqly");
					String lxdh =  request.getParameter("lxdh");
					String rxqhk =  request.getParameter("rxqhk");
					String jtzz =  request.getParameter("jtzz");
					String yzbm =  request.getParameter("yzbm");
					String jtlxdh =  
							request.getParameter("jtlxdh") ;
					String sfyycjcshzyhd =  request
							.getParameter("sfyycjcshzyhd") ;
					String sfyysqgjzxdkhqgzx =  request
							.getParameter("sfyysqgjzxdkhqgzx") ;
					String sfjq =  request.getParameter("sfjq");
					String sfge =  request.getParameter("sfge");
					String sfdq =  request.getParameter("sfdq");
					String sfcj =  request.getParameter("sfcj");
					String sfjls =  request.getParameter("sfjls");
					String sfly =  request.getParameter("sfly");
					String sfzb =  request.getParameter("sfzb");
					String jtcy1_xm =  request
							.getParameter("jtcy1_xm") ;
					String jtcy1_nl =  request
							.getParameter("jtcy1_nl") ;
					String jtcy1_gx =  request
							.getParameter("jtcy1_gx") ;
					String jtcy1_gzdw =  request
							.getParameter("jtcy1_gzdw") ;
					String jtcy1_zy =  request
							.getParameter("jtcy1_zy") ;
					String jtcy1_nsr =  request
							.getParameter("jtcy1_nsr") ;
					String jtcy1_jkzk =  request
							.getParameter("jtcy1_jkzk") ;
					String jtcy2_xm =  request
							.getParameter("jtcy2_xm") ;
					String jtcy2_nl =  request
							.getParameter("jtcy2_nl") ;
					String jtcy2_gx =  request
							.getParameter("jtcy2_gx") ;
					String jtcy2_gzdw =  request
							.getParameter("jtcy2_gzdw") ;
					String jtcy2_zy =  request
							.getParameter("jtcy2_zy") ;
					String jtcy2_nsr =  request
							.getParameter("jtcy2_nsr") ;
					String jtcy2_jkzk =  request
							.getParameter("jtcy2_jkzk") ;
					String jtcy3_xm =  request
							.getParameter("jtcy3_xm") ;
					String jtcy3_nl =  request
							.getParameter("jtcy3_nl") ;
					String jtcy3_gx =  request
							.getParameter("jtcy3_gx") ;
					String jtcy3_gzdw =  request
							.getParameter("jtcy3_gzdw") ;
					String jtcy3_zy =  request
							.getParameter("jtcy3_zy") ;
					String jtcy3_nsr =  request
							.getParameter("jtcy3_nsr") ;
					String jtcy3_jkzk =  request
							.getParameter("jtcy3_jkzk") ;
					String jtcy4_xm =  request
							.getParameter("jtcy4_xm") ;
					String jtcy4_nl =  request
							.getParameter("jtcy4_nl") ;
					String jtcy4_gx =  request
							.getParameter("jtcy4_gx") ;
					String jtcy4_gzdw =  request
							.getParameter("jtcy4_gzdw") ;
					String jtcy4_zy =  request
							.getParameter("jtcy4_zy") ;
					String jtcy4_nsr =  request
							.getParameter("jtcy4_nsr") ;
					String jtcy4_jkzk =  request
							.getParameter("jtcy4_jkzk") ;
					String jtcy5_xm =  request
							.getParameter("jtcy5_xm") ;
					String jtcy5_nl =  request
							.getParameter("jtcy5_nl") ;
					String jtcy5_gx =  request
							.getParameter("jtcy5_gx") ;
					String jtcy5_gzdw =  request
							.getParameter("jtcy5_gzdw") ;
					String jtcy5_zy =  request
							.getParameter("jtcy5_zy") ;
					String jtcy5_nsr =  request
							.getParameter("jtcy5_nsr") ;
					String jtcy5_jkzk =  request
							.getParameter("jtcy5_jkzk") ;
					String jtrjnsr =  request
							.getParameter("jtrjnsr") ;
					String xszbdszqk =  request
							.getParameter("xszbdszqk") ;
					String jtzszrzhqk =  request
							.getParameter("jtzszrzhqk") ;
					String jtzstfywsj =  request
							.getParameter("jtzstfywsj") ;
					String qtqk =  request.getParameter("qtqk");
					String mzbm_txdz =  request
							.getParameter("mzbm_txdz") ;
					String mzbm_yzbm =  request
							.getParameter("mzbm_yzbm") ;
					String mzbm_lxdh =  request
							.getParameter("mzbm_lxdh") ;
					String syd =  request.getParameter("syd");
					String jd =  request.getParameter("jd") ;
					String bjpm =  request.getParameter("bjpm");
					String bkkms =  request.getParameter("bkkms");
					String fblw =  request.getParameter("fblw");
					String yhzs =  request.getParameter("yhzs");
					String sfkns =  request.getParameter("sfkns");

					String num = "0";
					if ("学期".equalsIgnoreCase(map.get("xmbz"))) {
						sql = "select count(*) num from jzxj_xssqb where xn||xq||xmdm||xh=?";
						num = dao.getOneRs(sql, new String[] { xn + xq + xmdm
								+ xh }, "num");
					} else {
						sql = "select count(*) num from jzxj_xssqb where xn||xmdm||xh=?";
						num = dao.getOneRs(sql,
								new String[] { xn + xmdm + xh }, "num");
					}
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("jzxj_xssqb",
								new String[] { "xn", "xq", "xmdm", "xmmc",
										"xh", "sqly", "lxdh", "rxqhk",
										"jtzz", "yzbm", "jtlxdh",
										"sfyycjcshzyhd", "sfyysqgjzxdkhqgzx",
										"sfjq", "sfge", "sfdq", "sfcj",
										"sfjls", "sfly", "sfzb", "jtcy1_xm",
										"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
										"jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
										"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
										"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
										"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
										"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy",
										"jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
										"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
										"jtcy4_zy", "jtcy4_nsr", "jtcy4_jkzk",
										"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
										"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
										"jtcy5_jkzk", "jtrjnsr", "xszbdszqk",
										"jtzszrzhqk", "jtzstfywsj", "qtqk",
										"mzbm_txdz", "mzbm_yzbm", "mzbm_lxdh",
										"sfkns", "syd", "jd", "bjpm", "bkkms",
										"fblw", "yhzs" }, new String[] { xn,
										xq, xmdm, xmmc, xh, sqly, lxdh, rxqhk, jtzz,
										yzbm, jtlxdh, sfyycjcshzyhd,
										sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq,
										sfcj, sfjls, sfly, sfzb, jtcy1_xm,
										jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
										jtcy1_zy, jtcy1_nsr, jtcy1_jkzk,
										jtcy2_xm, jtcy2_nl, jtcy2_gx,
										jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
										jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
										jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
										jtcy3_nsr, jtcy3_jkzk, jtcy4_xm,
										jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
										jtcy4_zy, jtcy4_nsr, jtcy4_jkzk,
										jtcy5_xm, jtcy5_nl, jtcy5_gx,
										jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
										jtcy5_jkzk, jtrjnsr, xszbdszqk,
										jtzszrzhqk, jtzstfywsj, qtqk,
										mzbm_txdz, mzbm_yzbm, mzbm_lxdh, sfkns,
										syd, jd, bjpm, bkkms, fblw, yhzs },
								request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (String[] tempSr : zdyzdList) {
									String srName = "zdy" + tempSr[0];
									String sr =  request
											.getParameter(srName) ;
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update jzxj_xssqb set "
										+ sb.toString()
										+ " where xn||xq||xmdm||xh='" + pkVal + "'";
								StandardOperation.update("jzxj_xssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("学期".equalsIgnoreCase(map.get("xmbz"))) {
							sql = "select count(*) num from jzxj_xssqb where xn||xq||xmdm||xh=? and xxsh='通过'";
							num = dao.getOneRs(sql, new String[] { xn + xq + xmdm
									+ xh }, "num");
						} else {
							sql = "select count(*) num from jzxj_xssqb where xn||xmdm||xh=? and xxsh='通过'";
							num = dao.getOneRs(sql,
									new String[] { xn + xmdm + xh }, "num");
						}
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("jzxj_xssqb",
									new String[] { "xmmc", "sqly", "sqsj",
											"xysh", "xyshyj", "xyshsj",
											"xypzje", "xxsh", "xxshyj",
											"xxshsj", "xxpzje", "lxdh",
											"rxqhk", "jtzz", "yzbm", "jtlxdh",
											"sfyycjcshzyhd",
											"sfyysqgjzxdkhqgzx", "sfjq",
											"sfge", "sfdq", "sfcj", "sfjls",
											"sfly", "sfzb", "jtcy1_xm",
											"jtcy1_nl", "jtcy1_gx",
											"jtcy1_gzdw", "jtcy1_zy",
											"jtcy1_nsr", "jtcy1_jkzk",
											"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
											"jtcy2_gzdw", "jtcy2_zy",
											"jtcy2_nsr", "jtcy2_jkzk",
											"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
											"jtcy3_gzdw", "jtcy3_zy",
											"jtcy3_nsr", "jtcy3_jkzk",
											"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
											"jtcy4_gzdw", "jtcy4_zy",
											"jtcy4_nsr", "jtcy4_jkzk",
											"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
											"jtcy5_gzdw", "jtcy5_zy",
											"jtcy5_nsr", "jtcy5_jkzk",
											"jtrjnsr", "xszbdszqk",
											"jtzszrzhqk", "jtzstfywsj", "qtqk",
											"mzbm_txdz", "mzbm_yzbm",
											"mzbm_lxdh", "sfkns", "syd", "jd",
											"bjpm", "bkkms", "fblw", "yhzs" },
									new String[] { xmmc, sqly, rightNow, "未审核",
											"", "", "0", "未审核", "", "", "0",
											lxdh, rxqhk, jtzz, yzbm, jtlxdh,
											sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
											sfjq, sfge, sfdq, sfcj, sfjls,
											sfly, sfzb, jtcy1_xm, jtcy1_nl,
											jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
											jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
											jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
											jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
											jtcy3_xm, jtcy3_nl, jtcy3_gx,
											jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
											jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
											jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
											jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
											jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
											jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
											jtrjnsr, xszbdszqk, jtzszrzhqk,
											jtzstfywsj, qtqk, mzbm_txdz,
											mzbm_yzbm, mzbm_lxdh, sfkns, syd, jd,
											bjpm, bkkms, fblw, yhzs },
									"xn||xq||xmdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (String[] tempSr : zdyzdList) {
										String srName = "zdy" + tempSr[0];
										String sr =  request
												.getParameter(srName) ;
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update jzxj_xssqb set "
											+ sb.toString()
											+ " where xn||xq||xmdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("jzxj_xssqb", sql, request);
								}
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				if (!b1) {
					request.setAttribute("sfksq", "-2");// 不能申请
				} else if (!b2) {
					request.setAttribute("sfksq", "-3");// 不能申请
				} else {
					request.setAttribute("sfksq", sfksq);// 不能申请
				}
			}
		}

		sql = "select xn,xq,xmdm,xmmc,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,lydq,csrq,rxny,mzmc,zzmmmc,kh,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,sfkns,syd,jd,bjpm,bkkms,fblw,yhzs from view_jzxj_xssqb where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		sql = "select xn,xq,xmdm,xmmc,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,lydq,csrq,rxny,mzmc,zzmmmc,kh,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,sfkns,syd,jd,bjpm,bkkms,fblw,yhzs from view_jzxj_xssqb where xn||xq||xmdm||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if(null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,a.ssbh,a.qsdh,a.lydq,a.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,a.mzmc,a.zzmmmc,a.kh,'"+xn+"' xn,'"+xq+"' xq,NVL((select (case xxsh when '未审核' then '不困难' else xxsh end) sfkns from ZGDZDX_KNS_PKSRD where xh=? and nd=?),'不困难') sfkns from view_stu_details a where a.xh=?";
				String[] colN = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "sfzh", "ssbh", "qsdh", "lydq",
						"csrq", "rxny", "mzmc", "zzmmmc", "kh", "xn", "xq","sfkns" };
				String[] outV = dao.getOneRs(sql,
						new String[] { xh,Base.currNd,xh }, colN);
				if (outV != null) {
					colN = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "sfzh", "ssbh", "qsdh", "lydq",
							"csrq", "rxny", "mzmc", "zzmmmc", "kh", "xn", "xq","sfkns" };
					for (int i = 0; i < colN.length; i++) {
						if (null != outV[i]) {
							map.put(colN[i], outV[i]);
						} else {
							map.put(colN[i], "");
						}
					}
				}
				outV = dao.getOneRs("select xf,bjgmc from jwjk_xsjdcjxx where xh=? and xn=?", new String[]{xh,Base.beforXn}, new String[]{"xf","bjgmc"});
				if (outV != null){
					map.put("jd", outV[0]);
					map.put("bkkms", outV[1]);
				} else {
					map.put("jd", "");
					map.put("bkkms", "");
				}
			}
		} else {
			int len1 = outString.length;
			int len2 = outValue.length;
			int max = 0;
			if (len1 >= len2) {
				max = len2;
			} else {
				max = len1;
			}
			for (int i = 0; i < max; i++) {
				outString[i] = outString[i].toLowerCase();
				if (outValue[i] != null) {
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}
		
		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(String[] str : zdyzdList){
				st += ",zdy" + str[0];
			}
			sql = "select "+st+" from view_jzxj_xssqb where 1=2";
			outString = dao.getColumnName(sql);// 获得列名数组
			sql = "select "+st+" from view_jzxj_xssqb where xn||xq||xmdm||xh=? ";
			outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
			if (outValue != null) {
				int len1 = outString.length;
				int len2 = outValue.length;
				int max = 0;
				if (len1 >= len2) {
					max = len2;
				} else {
					max = len1;
				}
				for (int i = 0; i < max; i++) {
					outString[i] = outString[i].toLowerCase();
					if (outValue[i] != null) {
						map.put(outString[i], outValue[i]);
					} else {
						map.put(outString[i], "");
					}
				}
			}
		}
		
		if (null == map.get("xmdm") || "".equalsIgnoreCase(map.get("xmdm"))){
			map.put("xmdm", xmdm);
		}
		if(sfknsb){
			request.setAttribute("isKns", "is");
		} else {
			request.setAttribute("isKns", "no");
		}
		
		String yxjl = request.getParameter("yxjl");
		String xjjl = request.getParameter("xjjl");
		String sjjl = request.getParameter("sjjl");
		map.put("yxjl", yxjl);
		map.put("xjjl", xjjl);
		map.put("sjjl", sjjl);
		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_xmsq");
	}
	
	/**
	 * @describe 奖助学金项目模版下载
	 * @author zhoumi
	 * @return
	 * @throws IOException 
	 */
	public ActionForward jzxj_xmmbxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String xmdm =  request.getParameter("xmdm") ;

		String modelPath = servlet.getServletContext().getRealPath("");
		modelPath = modelPath.substring(0, modelPath.length() - 5)
				+ "/xgxtupload/" + xmdm + ".xls";
		File file = new File(modelPath);
		if (!file.exists()) {
			request.setAttribute("isnull", "isnull");
		} else {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(file,
					response.getOutputStream());
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}
		return mapping.findForward("jzxj_xmmbxz");
	}
	
	/**
	 * @describe 奖助学金申请结果查询
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward jzxj_xmsqjg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String username = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "";
		List topTr = new ArrayList<String[]>();
		HashMap<String, String> map = new HashMap<String, String>();
		ActionForward myActFwd = null;

		colList = new String[] { "url","行号","xn", "xq", "xmmc", "xh", "xm", "sqsj",
				"xysh", "xypzje","xxsh","xxpzje","xgsh" };
		if (!userType.equalsIgnoreCase("stu")) {
			myActFwd = new ActionForward(
					"/zgdzdx_xszz.do?method=jzxj_xmsh&isQuery=is", false);
			return myActFwd;
		}
		sql = "select '/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq'||'&'||'pkVal='||xn||xq||xmdm||xh url,rownum 行号,a.* from view_jzxj_xssqb a where xh=? order by xn,xq,xmdm";
		colListCN = dao.getColumnNameCN(colList, "view_jzxj_xssqb");
		topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[] { username }, colList));
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);// 把查询条件发回去
		return mapping.findForward("jzxj_xmsqjg");
	}

	/**
	 * @describe 奖助学金审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jzxj_xmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

//		初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		XszzZgdzdxService service = new XszzZgdzdxService();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
//		String userType = dao.getUserType(userDep);
		String userType = session.getAttribute("userType").toString();
		String xy =  request.getParameter("xydm") ;
		String tips = "";
		String xh =  request.getParameter("xh") ;
		String username = session.getAttribute("userName").toString();
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String pswyh = dao.getOneRs(
				"select 1 sfn from yhb a,yhzb b where a.zdm=b.zdm and zmc like '%' and yhm='"+username+"'",
				new String[] {}, "sfn");
		String usertype = "";
		if("xy".equals(userType)){
			usertype = "xy";
		}else if("xx".equals(userType) || "1".equals(StringUtils.isNotNull(pswyh))){
			usertype = "xx";
		}else if("admin".equals(userType)){
			usertype = "admin";
			
		}else if("stu".equals(userType)){
			usertype = "stu";
		}
		request.setAttribute("gslx", usertype);
		String zy =  request.getParameter("zydm") ;
		String bj =  request.getParameter("bjdm") ;
//		String xmdm =  request.getParameter("xmdm"), dao.getOneRs("select xmdm from jzxj_xmdmb order by xmdm", new String[]{}, "xmdm"), 1);
		String xmdm = request.getParameter("xmdm");
		String xmbz = dao.getOneRs("select xmbz from jzxj_xmdmb where xmdm=?", new String[]{xmdm}, "xmbz");
		String nj =  request.getParameter("nj") ;
		realTable = "jzxj_xssqb";
		pk = "xn||xq||xmdm||xh";
		tableName = "view_jzxj_xssqb";

		String xn = "";
		String xq = "";
		if(!isQuery.equalsIgnoreCase("is")){
			xn = Base.currXn;
			if ("学期".equalsIgnoreCase(xmbz)){
				xq = Base.currXq;
			} else {
				xq = "";
			}
		} else {
			xn =  request.getParameter("xn") ;
			xq =  request.getParameter("xq") ;
		}
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xn)) {
			querry.append(" and xn='");
			querry.append(xn);
			querry.append("' ");
		}
		if (!isNull(xq)) {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "奖助学金 - 审核 - 审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = " 奖助学金 - 申请 - 申请结果查询";
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xq", "xmmc", "xh", "xm",
					"xb", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj", "sfkns" };
			if (userType.equalsIgnoreCase("xx") || userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("stu")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xmdm pk2,a.xn,a.xq,a.xmmc,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xmdm pk2,a.xn,a.xq,a.xmmc,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (usertype.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xq", "xmmc", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns", "xysh", "" };
			}else if(usertype.equalsIgnoreCase("admin")){
				colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xq", "xmmc", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns", "xysh", "xxsh","xgsh"};
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xq", "xmmc", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (usertype.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xmdm pk2,a.xn,a.xq,a.xmmc,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else if(usertype.equalsIgnoreCase("admin")){
				sql = "select (case when nvl(a.xgsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xmdm pk2,a.xn,a.xq,a.xmmc,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns,a.xgsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				//colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			}else{
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xmdm pk2,a.xn,a.xq,a.xmmc,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		//公示
		String jxlc = request.getParameter("jxlc");
		String pubtime = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') pubtime from dual",new String[] {}, "pubtime");
		if ("gs".equals(jxlc)) {
			String gssql = "select xmmc,lower(max(sys_connect_by_path('\"'||xm||'\"',','))) gs from (select row_number() over(partition by xmmc order by xmmc) pno,row_number() over(partition by xmmc order by xmmc)-1 sno,xm,xmmc from (select * from (select a.*,xm,nj,zydm,xydm,bjdm from jzxj_xssqb a,view_xsxxb b where a.xh=b.xh) "+querry+" and xxsh='通过')) start with pno = 1 connect by prior pno = sno and prior xmmc=xmmc group by xmmc";
			
			String[] st = dao.getRs(gssql, new String[]{},"gs");
			if (StringUtils.isArrayNotNull(st)) {
				String puber = session.getAttribute("userName").toString();
				String[] xmmcfb = dao.getRs(
						"select xmmc from jzxj_xssqb where xmdm=?",
						new String[] { xmdm }, "xmmc");
				String fbnr = xmmcfb[0] + ":" + st[0].substring(1);
				String newstitle = xmmcfb[0] + "获奖公示名单";
				String[] values = new String[] { newstitle, fbnr, puber,
						pubtime };
				String gsinsql = "insert into newscontent(newsid,newstitle,newspart,newscont,puber,xwlx,pubtime,towho) values(s_news_id.nextval,?,'N28',?,?,'003',?,'1')";
				boolean bool = dao.insert(gsinsql, values);
				if (bool) {
					request.setAttribute("fbcg", "yes");
				}
			} else {
				request.setAttribute("fbcg", "no");
			}
			
		}
		//公示结果
		String gsjg = request.getParameter("gsjg");
		if ("gsjg".equals(gsjg)) {
			String gssql = "select xmmc,lower(max(sys_connect_by_path('\"'||xm||'\"',','))) gs from (select row_number() over(partition by xmmc order by xmmc) pno,row_number() over(partition by xmmc order by xmmc)-1 sno,xm,xmmc from (select * from (select a.*,xm,nj,zydm,xydm,bjdm from jzxj_xssqb a,view_xsxxb b where a.xh=b.xh)"
					+ querry
					+ " and xgsh='通过')) start with pno = 1 connect by prior pno = sno and prior xmmc=xmmc group by xmmc";

			String[] st = dao.getRs(gssql, new String[] {}, "gs");
			if (StringUtils.isArrayNotNull(st)) {
				String puber = session.getAttribute("userName").toString();
				String[] xmmcfb = dao.getRs("select xmmc from jzxj_xssqb where xmdm=?",new String[] { xmdm }, "xmmc");
				String xmmcfb1 = StringUtils.isArrayNotNull(xmmcfb)?xmmcfb[0]:"";
				String fbnr = StringUtils.isArrayNotNull(st) ? xmmcfb1 + ":"+st[0].substring(1) : "";
				String newstitle = xmmcfb1 + "获奖名单";
				String[] values = new String[] { newstitle, fbnr, puber,
						pubtime };
				String gsinsql = "insert into newscontent(newsid,newstitle,newspart,newscont,puber,xwlx,pubtime,towho) values(s_news_id.nextval,?,'N28',?,?,'004',?,'1')";
				boolean bool = dao.insert(gsinsql, values);
				if (bool) {
					request.setAttribute("fbcg", "yes");
				}
			} else {
				request.setAttribute("fbcg", "no");
			}

		}
		
		
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("xn", xn);
		map.put("xq", xq);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;

		String yshNum = "0";
		String zrs = "#";

		if ("xx".equalsIgnoreCase(usertype)){
			yshNum = dao.getOneRs("select count(*) num from jzxj_xssqb a where a.xxsh='通过' and a.xmdm=? and a.xn=? and a.xq like (case (select b.xmbz from jzxj_xmdmb b where b.xmdm=a.xmdm) when '学年' then '%' when '学期' then '"+Base.currXq+"' end)", new String[]{xmdm,Base.currXn}, "num");
			zrs = dao.getOneRs("select nvl((select zrs from jzxj_rswfb1 where xmdm=? and sfyx='是'),'#') zrs from dual", new String[]{xmdm}, "zrs");
		} else if ("admin".equalsIgnoreCase(usertype)){
			yshNum = dao.getOneRs("select count(*) num from jzxj_xssqb a where a.xgsh='通过' and a.xmdm=? and a.xn=? and a.xq like (case (select b.xmbz from jzxj_xmdmb b where b.xmdm=a.xmdm) when '学年' then '%' when '学期' then '"+Base.currXq+"' end)", new String[]{xmdm,Base.currXn}, "num");
			zrs = dao.getOneRs("select nvl((select zrs from jzxj_rswfb1 where xmdm=? and sfyx='是'),'#') zrs from dual", new String[]{xmdm}, "zrs");
		}else{
			yshNum = dao.getOneRs("select count(*) num from view_jzxj_xssqb a where a.xysh='通过' and a.xmdm=? and a.xn=? and xydm=? and a.xq like (case (select b.xmbz from jzxj_xmdmb b where b.xmdm=a.xmdm) when '学年' then '%' when '学期' then '"+Base.currXq+"' end)", new String[]{xmdm,Base.currXn,userDep}, "num");
			zrs = dao.getOneRs("select nvl((select szrs from jzxj_rswfb2 where xmdm=? and xydm=? and sfyx='是'),'#') zrs from dual", new String[]{xmdm,userDep}, "zrs");
		}
		request.setAttribute("yshNum", yshNum);
		request.setAttribute("zrs", zrs);
		if ("#".equalsIgnoreCase(zrs)) {
			request.setAttribute("kshrs", "#");
		} else if (!"#".equalsIgnoreCase(zrs)
				&& (Integer.parseInt(zrs) <= Integer.parseInt(yshNum))) {
			request.setAttribute("kshrs", "0");
		} else {
			request.setAttribute("kshrs", Integer.parseInt(zrs)
					- Integer.parseInt(yshNum));
		}

		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("jzxj_xmsh");
	}
	
	/**
	 * @describe 奖助学金审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jzxj_xmshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm myform = (XszzZgdzdxActionForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String actDo =  request.getParameter("actDo") ;
		String writ = request.getParameter("writ");
		String pkVal =  request.getParameter("pkVal") ;
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userDep = session.getAttribute("userDep").toString();
//		String userType = dao.getUserType(userDep);
		String userType = session.getAttribute("userType").toString();
		String yesNo =  request.getParameter("yesNo") ;
		String xyshyj =  request.getParameter("xyshyj") ;
		String xxshyj =  request.getParameter("xxshyj") ;
		String xgshyj =  request.getParameter("xgshyj") ;
//		String xmje =  request.getParameter("xmje") ;
		
		String xypzje =  request.getParameter("xypzje") ;
		String jbmc = DealString.toGBK(request.getParameter("xypzdj"));
		String xxpzje =  request.getParameter("xxpzje") ;
		String xxpzdj = DealString.toGBK(request.getParameter("xxpzdj"));
		String xgpzje =  request.getParameter("xgpzje") ;
		String xgpzdj = DealString.toGBK(request.getParameter("xgpzdj"));
		String jxjbh = DealString.toGBK(request.getParameter("jxjbh"));
		myform.setJxjbh(jxjbh);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");
		String pswyh = dao.getOneRs(
				"select 1 sfn from yhb a,yhzb b where a.zdm=b.zdm and zmc like '%' and yhm='zf01'",
				new String[] {}, "sfn");
		String usertype = "";
		if ("xy".equals(userType)) {
			usertype = "xy";
		} else if ("xx".equals(userType)
				|| "1".equals(StringUtils.isNotNull(pswyh))) {
			usertype = "xx";
		} else if ("admin".equals(userType)) {
			usertype = "admin";
		} else {
			usertype = "no";
		}
		request.setAttribute("islx", usertype);

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel =  request.getParameter("pkDel") ;
			String[] pkDelT = pkDel.split("!#!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete jzxj_xssqb where xn||xq||xmdm||xh='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete jzxj_xssqb where xn||xq||xmdm||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/zgdzdx_xszz.do?method=jzxj_xmsh&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel =  request.getParameter("pkDel") ;
			String[] pkDelT = pkDel.split("!#!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ("xy".equals(usertype)) {
					sqlT[i] = "update jzxj_xssqb set xysh='通过',xyshsj='"
							+ now
							+ "',xypzje='0' where xn||xq||xmdm||xh='"
							+ pkT
							+ "'";
				} else if ("xx".equals(usertype)) {
					String xypzjeT = dao
							.getOneRs(
									"select xypzje from jzxj_xssqb where xn||xq||xmdm||xh=?",
									new String[] { pkT }, "xypzje");
					sqlT[i] = "update jzxj_xssqb set xxsh='通过',xxshsj='"
							+ now
							+ "',xxpzje='"
							+ xypzjeT
							+ "' where xn||xq||xmdm||xh='" + pkT + "'";
				} else if("admin".equals(usertype)){
							String xypzjeT = dao
							.getOneRs(
									"select xypzje from jzxj_xssqb where xn||xq||xmdm||xh=?",
									new String[] { pkT }, "xypzje");
					sqlT[i] = "update jzxj_xssqb set xgsh='通过',xgshsj='"
							+ now
							+ "',xxpzje='"
							+ xypzjeT
							+ "' where xn||xq||xmdm||xh='" + pkT + "'";
						}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/zgdzdx_xszz.do?method=jzxj_xmsh&go=go", false);
			return newFwd;
		}
		
		if ("notPass".equalsIgnoreCase(actDo)) {
			String pkDel =  request.getParameter("pkDel") ;
			String[] pkDelT = pkDel.split("!#!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if (usertype.equalsIgnoreCase("xy")) {
					String xxshT = dao
							.getOneRs(
									"select xxsh from jzxj_xssqb where xn||xq||xmdm||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"通过".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update jzxj_xssqb set xysh='不通过',xyshsj='"
								+ now
								+ "',xypzje='0' where xn||xq||xmdm||xh='"
								+ pkT + "'";
					}
				} else if(usertype.equalsIgnoreCase("admin")){
//					String xxshT = dao
//					.getOneRs(
//							"select xgsh from jzxj_xssqb where xn||xq||xmdm||xh=?",
//							new String[] { pkT }, "xgsh");
					
						sqlT[i] = "update jzxj_xssqb set xgsh='不通过',xgshsj='"
						+ now
						+ "',xypzje='0' where xn||xq||xmdm||xh='"
						+ pkT + "'";
					
				}else{
					String xxshT = dao
					.getOneRs(
							"select xgsh from jzxj_xssqb where xn||xq||xmdm||xh=?",
							new String[] { pkT }, "xgsh");
					if (!"通过".equalsIgnoreCase(xxshT)) {
					sqlT[i] = "update jzxj_xssqb set xxsh='不通过',xxshsj='"
							+ now
							+ "',xxpzje='0' where xn||xq||xmdm||xh='"
							+ pkT
							+ "'";
					}
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/zgdzdx_xszz.do?method=jzxj_xmsh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			boolean b = false;
			if ("xy".equals(usertype)) {
				String xxshT = dao.getOneRs("select xxsh from jzxj_xssqb where xn||xq||xmdm||xh=?", new String[]{pkVal}, "xxsh");
				if("通过".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					b = StandardOperation
							.update(
									"jzxj_xssqb",
									new String[] { "xysh", "xyshyj", "xyshsj",
											"xypzje","xypzdj" },
									new String[] { yesNo, xyshyj, now, xypzje ,jbmc},
									"xn||xq||xmdm||xh", pkVal, request);
				}
			} else if("xx".equals(usertype)){
				b = StandardOperation.update("jzxj_xssqb", new String[] {
						"xxsh", "xxshyj", "xxshsj", "xxpzje" ,"xxpzdj" }, new String[] {
						yesNo, xxshyj, now, xxpzje,xxpzdj }, "xn||xq||xmdm||xh", pkVal,
						request);
			}else{
				b = StandardOperation.update("jzxj_xssqb", new String[] {
						"xgsh", "xgshyj", "xgshsj", "xgpzje","jxjbh","xgpzdj" }, new String[] {
						yesNo, xgshyj, now,xgpzje,jxjbh,xgpzdj  }, "xn||xq||xmdm||xh", pkVal,
						request);
			}
			if(b){
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		realTable = "jzxj_xssqb";
		pk = "xn||xq||xmdm||xh";
		sql = "select xn,xq,xmdm,xmmc,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,lydq,csrq,rxny,mzmc,zzmmmc,kh,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,sfkns,syd,jd,bjpm,bkkms,fblw,yhzs,xgsh,xgshyj,jxjbh,xgpzje,xypzdj,xxpzdj,xgpzdj from view_jzxj_xssqb where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ("xy".equals(usertype)) {
			sql = "select "
				+ pk
				+ " pk,a.xn,a.xq,a.xmdm,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.lydq,a.csrq,a.rxny,a.mzmc,a.zzmmmc,a.kh,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.sfkns,a.syd,a.jd,a.bjpm,a.bkkms,a.fblw,a.yhzs,a.xysh yesNo,a.xypzje xmje,xgsh,xgshyj,xxsh pswyh,jxjbh,xgpzje,xypzdj,xxpzdj,xgpzdj "
				+ "from view_jzxj_xssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else if("xx".equals(usertype)) {
			sql = "select "
				+ pk
				+ " pk,a.xn,a.xq,a.xmdm,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.lydq,a.csrq,a.rxny,a.mzmc,a.zzmmmc,a.kh,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.sfkns,a.syd,a.jd,a.bjpm,a.bkkms,a.fblw,a.yhzs,a.xxsh yesNo,a.xxpzje xmje,xgsh,xgshyj,xxsh pswyh,jxjbh,xgpzje,xypzdj,xxpzdj,xgpzdj "
				+ "from view_jzxj_xssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		} else {
			sql = "select "
				+ pk
				+ " pk,a.xn,a.xq,a.xmdm,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.lydq,a.csrq,a.rxny,a.mzmc,a.zzmmmc,a.kh,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.sfkns,a.syd,a.jd,a.bjpm,a.bkkms,a.fblw,a.yhzs,a.xxsh yesNo,a.xxpzje xmje,xgsh,xgshyj,xxsh pswyh,jxjbh,xgpzje,xypzdj,xxpzdj,xgpzdj "
				+ "from view_jzxj_xssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[(outString.length+4)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		colList[i+2] = "xmje";
		colList[i+3] = "pswyh";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		String xh = "";
		List<String[]> zdyzdList = new ArrayList<String[]>();
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("xh")) {
				xh = rs[i];
			}
			if (colList[i].equalsIgnoreCase("xmdm")) {
				
				zdyzdList = xszzDao.getJzxjZdyzdList(rs[i]);
				if (zdyzdList.size() == 0) {
					request.setAttribute("isNULL", "is");
				} else {
					request.setAttribute("isNULL", "no");
				}
				request.setAttribute("zdyzdList", zdyzdList);
				List<HashMap<String, String>> xmjeList = xszzDao.getJzxjXmjeList(rs[i]);
				request.setAttribute("xmjeList", xmjeList);
			}
			hs.put(colList[i], rs[i]);
		}
		String yshNum = "0";
		String zrs = "#";
		if ("admin".equals(usertype)) {
			hs.put("yesNo", hs.get("xgsh"));
		}
		
		if ("xx".equalsIgnoreCase(usertype)){
			yshNum = dao.getOneRs("select count(*) num from jzxj_xssqb a where a.xxsh='通过' and a.xmdm=? and a.xn=? and a.xq like (case (select b.xmbz from jzxj_xmdmb b where b.xmdm=a.xmdm) when '学年' then '%' when '学期' then '"+Base.currXq+"' end)", new String[]{hs.get("xmdm"),Base.currXn}, "num");
			zrs = dao.getOneRs("select nvl((select zrs from jzxj_rswfb1 where xmdm=? and sfyx='是'),'#') zrs from dual", new String[]{hs.get("xmdm")}, "zrs");
		} else if("xy".equalsIgnoreCase(usertype)){
			yshNum = dao.getOneRs("select count(*) num from view_jzxj_xssqb a where a.xysh='通过' and a.xmdm=? and a.xn=? and xydm=? and a.xq like (case (select b.xmbz from jzxj_xmdmb b where b.xmdm=a.xmdm) when '学年' then '%' when '学期' then '"+Base.currXq+"' end)", new String[]{hs.get("xmdm"),Base.currXn,userDep}, "num");
			zrs = dao.getOneRs("select nvl((select szrs from jzxj_rswfb2 where xmdm=? and xydm=? and sfyx='是'),'#') zrs from dual", new String[]{hs.get("xmdm"),userDep}, "zrs");
		}else{
			yshNum = dao.getOneRs("select count(*) num from jzxj_xssqb a where a.xgsh='通过' and a.xmdm=? and a.xn=? and a.xq like (case (select b.xmbz from jzxj_xmdmb b where b.xmdm=a.xmdm) when '学年' then '%' when '学期' then '"+Base.currXq+"' end)", new String[]{hs.get("xmdm"),Base.currXn}, "num");
			zrs = dao.getOneRs("select nvl((select zrs from jzxj_rswfb1 where xmdm=? and sfyx='是'),'#') zrs from dual", new String[]{hs.get("xmdm")}, "zrs");
		}
		request.setAttribute("yshNum", yshNum);
		request.setAttribute("zrs", zrs);
		if ("#".equalsIgnoreCase(zrs)){
			request.setAttribute("kshrs", "#");
		} else if (!"#".equalsIgnoreCase(zrs) && (Integer.parseInt(zrs) <= Integer.parseInt(yshNum))){
			request.setAttribute("kshrs", "0");
		} else {
			request.setAttribute("kshrs", Integer.parseInt(zrs) - Integer.parseInt(yshNum));
		}

		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(String[] str : zdyzdList){
				st += ",zdy" + str[0];
			}
			sql = "select "+st+" from view_jzxj_xssqb where 1=2";
			outString = dao.getColumnName(sql);// 获得列名数组
			sql = "select "+st+" from view_jzxj_xssqb where xn||xq||xmdm||xh=? ";
			String[] outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
			if (outValue != null) {
				int len1 = outString.length;
				int len2 = outValue.length;
				int max = 0;
				if (len1 >= len2) {
					max = len2;
				} else {
					max = len1;
				}
				for (int j = 0; j < max; j++) {
					outValue[j] = ((outValue[j] == null) || outValue[j].equalsIgnoreCase("")) ? " "
							: outValue[j];
					outString[j] = outString[j].toLowerCase();
					hs.put(outString[j].toLowerCase(), outValue[j]);
				}
			}
		}
		String xmdm = hs.get("xmdm");
		String xmsql = "select xmdm,jbmc jbmc from dzdxZxjXmjbb where xmdm=?";
//		String[] xmjejList = dao.getOneRs(xmsql, new String[]{xmdm}, new String[]{"xmdm","jbmc"});
		List xmjejList = dao.getList(xmsql, new String[]{xmdm}, new String[]{"xmdm","jbmc"});
		request.setAttribute("xmjejList", xmjejList);
		
		request.setAttribute("rs", hs);
		request.setAttribute("xsJzxjList", xszzDao.getXsJzxjList(xh));
//		request.setAttribute("xsLsbzList", xszzDao.getXsLsbzList(xh));
//		request.setAttribute("xsQtzzList", xszzDao.getXsQtzzList(xh));
		request.setAttribute("zjeList", xszzDao.getXsZjeList(xh));
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_jzxj_xssqb");
		request.setAttribute("act", "zzsh");
		request.setAttribute("writ", writ);
		return mapping.findForward("jzxj_xmshXxxx");
	}
	
	/**
	 * @describe 奖助学金列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jzxj_xmshExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy =  request.getParameter("xydm") ;
		String xh =  request.getParameter("xh") ;
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy =  request.getParameter("zydm") ;
		String bj =  request.getParameter("bjdm") ;
		String xmdm =  request.getParameter("xmdm") ;
		String nj =  request.getParameter("nj") ;
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String xn = "";
		String xq = "";
		if(!isQuery.equalsIgnoreCase("is")){
			xn = Base.currXn;
			xq = Base.currXq;
		} else {
			xn =  request.getParameter("xn") ;
			xq =  request.getParameter("xq") ;
		}
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xq)) {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("' ");
		}
		if (!isNull(xn)) {
			querry.append(" and xn='");
			querry.append(xn);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		sql = "select * from view_jzxj_xssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_jzxj_xssqb where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_jzxj_xssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("jzxj_xmshExp");
	}
	/**
	 * 奖助学金证书打印查询
	 * @throws Exception 
	 */
	public ActionForward zsPrintDef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XszzZgdzdxActionForm myForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		DAO dao  = DAO.getInstance();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String nj = myForm.getNj();	
		String xn = myForm.getXn();
		String bj = myForm.getBjdm();
		String xmdm = myForm.getXmdm();
		String xh   = DealString.toGBK(myForm.getXh()).trim();
		String xm   = DealString.toGBK(myForm.getXm()).trim();
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		myForm.setXh(DealString.toGBK(myForm.getXh()).toString());
		myForm.setXm(DealString.toGBK(myForm.getXm()).toString());

		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		List<Object> rs = new ArrayList<Object>();
		String rsNum = "0";// 返回的记录数
		String sql = "";
		StringBuffer querry = new StringBuffer();
		if("xy".equalsIgnoreCase(userType)){
			xy = userDep;
			myForm.setXydm(xy);
		}
		
		List topTr = new ArrayList<HashMap<String, String>>();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			String pk = "xn||xq||xmdm||xh";
			String tableName = "view_jzxj_xssqb";
			querry.append(" where 1=1 ");
			querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
			querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"' ");
			querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
			querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
			querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
			querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
			querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
			querry.append(Base.isNull(xmdm)?"":" and xmdm='"+xmdm+"'");
			querry.append(" and xgsh='通过' ");						
			String[] colList = new String[] {  "主键", "pk2", "xn",  "xmmc", "xh", "xm",
					"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns"};
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			sql = "select "
				+ " a.* from(select "
				+ pk
				+ " 主键,a.xmdm pk2,a.xn,a.xq,a.xmmc,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
				+ tableName
				+ " a"
				+ querry.toString()
				+ " ) a";
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}	
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("jzxjxmList", service.getJzxjxmList());
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录
		return mapping.findForward("zsPrintDef");
	}
	/**
	 * 证书格式设置
	 * @throws Exception 
	 */
	public ActionForward zsGsSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XszzZgdzdxService service = new XszzZgdzdxService();
		String act =  request.getParameter("act") ;
//		XszzZgdzdxActionForm myForm = (XszzZgdzdxActionForm)form;
		String xmdm = request.getParameter("xmdmV");
		if ("save".equalsIgnoreCase(act)) {
			boolean res = false;
			String msgBack = "保存失败";
			String topstr =  request.getParameter("topstr") ;
			String leftstr =  request.getParameter("leftstr");
			String fontstr =  request.getParameter("fontstr");

			res = service.insertTemplate(topstr, leftstr, fontstr, "jzxj"+xmdm, request);
			if (res) {
				msgBack = "保存成功";
			}
			request.setAttribute("back", msgBack);
		}
		List<HashMap<String, String>> list = service.getJzxjxmList();
		if(Base.isNull(xmdm)){
			xmdm = (list.size()>0)?list.get(0).get("xmdm"):"001";		
		}
		request.setAttribute("jzxjxmList", list);
		HashMap<String, String> rsMap = service.getTeplateInf("jzxj"+xmdm);
		request.setAttribute("rsMap", rsMap);		
		return mapping.findForward("zsGsSz");
	}
	/**
	 * 奖助学金证书打印时间设置
	 */
	public ActionForward zsPrintDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String pkValue = request.getParameter("pkValue");
		String xmdm    = request.getParameter("xmdm");
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xmdm",xmdm);
		return mapping.findForward("jzxjZsPrintDate");
	}
	/**
	 * 奖助学金证书打印
	 * @throws Exception 
	 */
	public ActionForward zsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String xmdm    = request.getParameter("xmdm");
		String bzrq    = request.getParameter("bzrq");
		String year    = "";
		year+=DealString.numToChnum(bzrq.substring(0,1));
		year+=DealString.numToChnum(bzrq.substring(1,2));
		year+=DealString.numToChnum(bzrq.substring(2,3));
		year+=DealString.numToChnum(bzrq.substring(3,4));
		String month    = "";
		if("0".equalsIgnoreCase(bzrq.substring(4,5))){
			month+=DealString.numToChnum(bzrq.substring(5,6));
		}else{
			month+=DealString.numToChnum(bzrq.substring(4,5));
			month+=DealString.numToChnum(bzrq.substring(5,6));
		}
		String date    = "";
		if("0".equalsIgnoreCase(bzrq.substring(6,7))){
			date+=DealString.numToChnum(bzrq.substring(7,8));
		}else{
			date+=DealString.numToChnum(bzrq.substring(6,7));
			date+=DealString.numToChnum(bzrq.substring(7,8));
		}
		XszzZgdzdxService service = new XszzZgdzdxService();						
		HashMap<String, String> rsMap = service.getTeplateInf("jzxj"+xmdm);
		request.setAttribute("rsMap", rsMap);	
		request.setAttribute("rs",service.getJzxjInfo(pkValue));
		request.setAttribute("year",year);
		request.setAttribute("month",month);
		request.setAttribute("date",date);
		return mapping.findForward("zsPrint");
	}
	
	/**
	 * 奖助学金报表打印
	 * @throws Exception 
	 */
	public ActionForward jzxjbbOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HashMap<String,String[]> map  = (HashMap<String,String[]>)request.getParameterMap();

		Set set = map.keySet();
		Iterator iterator= set.iterator();
		HashMap<String,String> rs = new HashMap<String,String>(); 
		while(iterator.hasNext()){
			String nextTmp = (String)iterator.next();
			String tmp =map.get(nextTmp)[0];
			String valueTmp = DealString.toGBK(tmp);
			rs.put(nextTmp,valueTmp);
		}
		String xn = Base.currXn;
		String xxmc = Base.xxmc;
		rs.put("xn", xn);
		rs.put("xxmc", xxmc);
		request.setAttribute("rs", rs);
		return mapping.findForward("jzxjshBb");
	}

	/**
	 * @describe 综合测评信息查询
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_zhcpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgdzdxActionForm actionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo( request.getParameter("go") );

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZhcpxx(actionForm.getPk());
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getZhcpTit();
		List<String[]> resList = service.getZhcpRes(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		String xm = DealString.toGBK(actionForm.getXm());
		actionForm.setXh(xh);
		actionForm.setXm(xm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getZhcpResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xq||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "jzxj_zhcpxx");
		request.setAttribute("tableName", "view_jzxj_zhcpxx");
		return mapping.findForward("jzxj_zhcpwh");
	}
	
	/**
	 * 综合测评信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_zhcpExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgdzdxActionForm actionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getZhcpExp(queryModel, response, request);
		return mapping.findForward("jzxj_zhcpExp");
	}
	
	/**
	 * 综合测评编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_zhcpEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxService service = new XszzZgdzdxService();
		String type = request.getParameter("type");
		String xh =  request.getParameter("xh") ;
		String pkVal =  request.getParameter("pkVal") ;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZhcpxx(pkVal);// 得到详细信息

		if (stuMap.size() == 0 && !"".equalsIgnoreCase(xh)) {
			stuMap = service.getStu(xh);// 得到学生信息
			stuMap.put("xn", Base.currXn);
			stuMap.put("xq", Base.currXq);
		}

		request.setAttribute("xqList", Base.getXqList());// 学期列表
		request.setAttribute("xnList", Base.getXnndList());// 学年列表
		request.setAttribute("type",  request.getParameter("type") );
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("type", type);
		return mapping.findForward("jzxj_zhcpEdit");
	}
	
	/**
	 * 保存综合测评详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jzxj_zhcpEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm actionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		ZhcpxxModel model = new ZhcpxxModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveZhcpxx(model, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String pkVal = xn + xq + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZhcpxx(pkVal);
		if (stuMap.size() == 0 && !"".equalsIgnoreCase(xh)) {
			stuMap = service.getStu(xh);// 得到学生信息
			stuMap.put("xn", Base.currXn);// 当前学年
			stuMap.put("xq", Base.currXq);
		}

		request.setAttribute("xqList", Base.getXqList());// 学期列表
		request.setAttribute("xnList", Base.getXnndList());// 学年列表
		request.setAttribute("type",  request.getParameter("type") );
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jzxj_zhcpEditSave");
	}
	
	
	/**
	 * 新生设置
	 * 入学时间在起始时间～结束时间之间的为新生
	 * @author quph
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String tableName = "xszz_zgdzdx_xssz";
		String doType = request.getParameter("doType");
		String pkValue = "1";
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		this.selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("path", "xssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xssz");
	}
	
	
	/**
	 * 新生助学贷款申请时间设置
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		XszzZgdzdxService service = new XszzZgdzdxService();
		XszzZgdzdxActionForm actionForm = (XszzZgdzdxActionForm)form;
		
		String tableName = "xszz_zgdzdx_xssjsz";
		String viewName = "view_xszz_zgdzdx_xssjsz";
		String[] outputColumn = new String[] {"pkValue","xymc","kssj","jssj"};
		String doType = request.getParameter("doType");
		boolean result = false;
		
		if ("xy".equals(userType)) {
			actionForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//批量设置
		if (!Base.isNull(doType) && "batchSave".equals(doType)) {
			String[] pkValue = request.getParameterValues("primarykey_cbv");
			String kssj = request.getParameter("kssj");
			String jssj = request.getParameter("jssj");
			
			result = service.xssjszSave(pkValue, kssj, jssj);
		}
		
		//初始化新生助学贷款申请时间设置
		if (!Base.isNull(doType) && "reset".equals(doType)) {
			result = service.xssjszReset();
		}
		
		if (!Base.isNull(doType) && !"query".equals(doType)) {
			if (result) {
				request.setAttribute("message", "操作成功");
			} else {
				request.setAttribute("message", "操作失败");
			}
			request.setAttribute("result", result);
		}
		request.setAttribute("path", "xssjsz.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("xssjsz");
	}
	
	
	/**
	 * 新生助学贷款申请时间单个设置
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssjszOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = "xszz_zgdzdx_xssjsz";
		String viewName = "view_xszz_zgdzdx_xssjsz";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("xssjszOne");
	}
	
	/**
	 * 生源地贷款数据维护页面 ——武汉铁路
	 * data_syddk ----- 生源地贷款数据维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_syddk_whtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		
		queryModel.setGo( request.getParameter("go") );
		queryModel.setNd( request.getParameter("nd") );
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delSyddk_whtl( request.getParameter("cbVal"),
					  request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		List<HashMap<String, String>> topList = service.getSyddkTit_whtl();
		List<String[]> resList = service.getSyddkRes_whtl(queryModel,request);
		String xh = DealString.toGBK(xszzZgdzdxActionForm.getXh());
		xszzZgdzdxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgdzdxActionForm);
		commonAction.appendProperties(request, commenBean, true);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgdzdxActionForm.setXydm(userDep);
		}
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgdzdxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "whtl_syddk");
		request.setAttribute("tableName", "view_whtl_syddk");
		String go=request.getParameter("go");
		if(go==null&&Base.isNull(queryModel.getGo())){
			xszzZgdzdxActionForm.setNd(Base.currNd);
		}
		return mapping.findForward("data_syddk_whtl");
	}
	
	/**
	 * 生源地贷款（增加）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syddkUpdate_whtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		XszzZgdzdxActionForm myForm = (XszzZgdzdxActionForm) form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		
		
		// 生源地贷款保存
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveSyddk_whtl(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		
		request.setAttribute("path", "zgdzdx_xszz.do?method=data_syddk");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("doType", "add");
		return mapping.findForward("syddkUpdate_whtl");
	}
	
	/**
	 * 生源地贷款(详细信息、修改)_武汉铁路
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syddkOne_whtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		XszzZgdzdxActionForm myForm = (XszzZgdzdxActionForm) form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		myForm.setPk(new String[]{pkValue});
		
		// 生源地贷款保存
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateSyddk_whtl(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			doType="update";
		}

		request.setAttribute("path", "zgdzdx_xszz.do?method=data_syddk");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("rs", service.getOneSyddk_whtl(myForm));
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("doType", doType);
		return mapping.findForward("syddkUpdate_whtl");
	}
	
	/**
	 * 数据导出——武汉铁路
	 * jtqkdcExp ----- 家庭情况调查信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcExp_whtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		XszzZgdzdxActionForm xszzZgdzdxActionForm = (XszzZgdzdxActionForm)form;
		XszzZgdzdxService service = new XszzZgdzdxService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZgdzdxActionForm);
		service.getJtqkdcExp_whtl(user, queryModel, response,request);
		return mapping.findForward("jtqkdcExp");
	}
	
	/**
	 * 检验请假记录是否存在
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXszz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XszzZgdzdxService service = new XszzZgdzdxService();
		String pk=request.getParameter("xh");
		String num=service.checkXszz(pk);	
		PrintWriter pw= response.getWriter();
		pw.write(num);
		pw.close();
		return null;
	}
	
}
