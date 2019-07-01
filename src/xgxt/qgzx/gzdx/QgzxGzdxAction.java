package xgxt.qgzx.gzdx;

import xgxt.qgzx.QgzxTyForm;
import xgxt.studentInfo.service.XsxxglService;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.qgzx.service.QgzxService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 广州大学勤工助学-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class QgzxGzdxAction extends BasicAction {
    /**
    * 积极份子修改
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzModi(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");

        if("save".equalsIgnoreCase(type)){//信息修改
                updateOperation(request, "qgzx_pjpy_jjfzb");
        }

        String pkValue = request.getParameter("pkValue");
        selectPageDataByOne(request, "qgzx_pjpy_jjfzb", "view_qgzx_pjpy_jjfzb", pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//查询学生基本信息
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//读写权信息
        request.setAttribute("type", type);
        return mapping.findForward("jjfzModi");
    }


	/**
    * 积极份子申报
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzsb(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        QgzxTyForm model = (QgzxTyForm)form;
        QgzxGzdxService service = new QgzxGzdxService();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        
        if("go".equalsIgnoreCase(go)){//查询数据
        	model.setUserName(userName);
            String[] outputColumn = { "pkValue","xh", "xm", "xb", "xn", "nd", "xqmc", "nj", "xymc", "bjmc", "是否已申报"};
            List<String[]> rs = service.queryYgbmxsList(model, outputColumn);
            request.setAttribute("topTr", service.getTopTr("view_xsgwxx",outputColumn));
            request.setAttribute("rs", rs);
            request.setAttribute("rsNum", rs.size());
        }
        if("save".equalsIgnoreCase(type)){
        	//申报保存
        	boolean result = service.saveQgzxjjfzsb(model);
        	request.setAttribute("result", result);
        }

        FormModleCommon.commonRequestSet(request);//将读写权跟title信息放到request中
        //加载学院专业班级列表信息
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("jjfzsb");
    }
    
    /**
     * 积极份子申报(用人单位发起)
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * */
     public ActionForward jjfzsbByYrdw(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
         HttpSession session = request.getSession();
         QgzxTyForm model = (QgzxTyForm)form;
         QgzxGzdxService service = new QgzxGzdxService();
         QgzxService qgzxService=new QgzxService();
         String userName = session.getAttribute("userName").toString();
         String go = request.getParameter("go");
         String type = request.getParameter("type");
         
         if("go".equalsIgnoreCase(go)){//查询数据
         	model.setUserName(userName);
             String[] outputColumn = { "pkValue","xh", "xm", "xb", "xn", "nd", "xqmc", "nj", "xymc", "bjmc","yrdwmc", "是否已申报"};
             List<String[]> rs = service.queryYgbmxs(model, outputColumn);
             request.setAttribute("topTr", service.getTopTr("view_xsgwxx",outputColumn));
             request.setAttribute("rs", rs);
             request.setAttribute("rsNum", rs.size());
         }
         if("save".equalsIgnoreCase(type)){
         	//申报保存
         	boolean result = service.saveQgzxjjfzsb(model);
         	request.setAttribute("result", result);
         }
         
         if(qgzxService.isYrdwUser(userName)){//是否用人单位
        	 model.setSqdw(qgzxService.getYrdwUser(userName));
        	 request.setAttribute("yrdwdm",model.getSqdw());
         }
         request.setAttribute("path", "gzdxQgzx.do?method=jjfzsbByYrdw");
         FormModleCommon.commonRequestSet(request);//将读写权跟title信息放到request中
         //加载用人单位
         request.setAttribute("isYrdwyh", qgzxService.isYrdwUser(userName));
         request.setAttribute("yrdwList",service.getYrdwList());
         FormModleCommon.setNjXyZyBjListForFdyBzr(request);
         FormModleCommon.setNdXnXqList(request);
         return mapping.findForward("jjfzsbByYrdw");
     }
	    
    /**
    * 积极份子结果查询
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzjgcx(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        QgzxTyForm model = (QgzxTyForm)form;
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "qgzx_pjpy_jjfzb";
        String viewName = "view_qgzx_pjpy_jjfzb";

        if ("xy".equalsIgnoreCase(session.getAttribute("userType").toString())
            && !"true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
            model.setQueryequals_xydm(session.getAttribute("userDep").toString());
        }

        if("student".equalsIgnoreCase(userOnLine)){//学生登陆
            model.setQuerylike_xh(userName);
        }

        if("go".equalsIgnoreCase(go)){//查询数据
            String[] outputColumn = {"pkValue", "xh", "xm", "xb", "xn", "nd", "xqmc", "xymc", "bjmc", "sbsj", "fdysh", "xysh", "xxsh"};
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("del".equalsIgnoreCase(type)){//信息删除
            deleteOperation(request, tableName);
        }

        FormModleCommon.commonRequestSet(request);//将读写权跟title信息放到request中
        //加载学院专业班级列表信息
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("realTable", tableName);
        return mapping.findForward("jjfzjgcx");
    }

    /**
    * 积极份子信息导出
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception
    * */
    public ActionForward jjfzExp(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        expPageData(request, response, "qgzx_pjpy_jjfzb", "view_qgzx_pjpy_jjfzb", null);
        return mapping.findForward("");
    }

    /**
    * 积极份子审核
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzSh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        QgzxTyForm model = (QgzxTyForm)form;
        QgzxGzdxService service = new QgzxGzdxService();
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String userDep = session.getAttribute("userDep").toString();
        String userType = session.getAttribute("userType").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "qgzx_pjpy_jjfzb";
        String viewName = "view_qgzx_pjpy_jjfzb";
        String shzd = "xxsh";
        String shjg = request.getParameter("shjg");
        String annexTerm = "";
        String yhlx = "";

        if ("xy".equalsIgnoreCase(userType) && !session.getAttribute("fdyQx").equals(true)) {//学院审核
            model.setQueryequals_xydm(userDep);
            shzd = "xysh";
            yhlx = "xy";
            annexTerm = " and fdysh = '通过' ";
	    }else if("xx".equalsIgnoreCase(userType) 
	    		|| "admin".equalsIgnoreCase(userType)){//学校用户
	    	annexTerm = " and xysh = '通过'";
	    }
        if (session.getAttribute("fdyQx").equals(true)) {//辅导员审核
            shzd = "fdysh";
            yhlx = "fdy";
        }

        if("go".equalsIgnoreCase(go)){//查询数据
            String[] outputColumn = {"disabled", "bgcolor", "pkValue", "xh", "xm", "xb", "xn", "nd", "xqmc", "nj", "xymc", "bjmc", "sbsj", "fdysh", "xysh", "xxsh"};
            request.setAttribute("clientColumns", service.getCustomAudiColumn(yhlx));
            request.setAttribute("annexTerm", annexTerm);
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("save".equalsIgnoreCase(type)){//审核信息保存
           	request.setAttribute("shzd",shzd);
           	request.setAttribute("shjg",shjg);
           	auditingBatchOperation(request, tableName);
        }

        FormModleCommon.commonRequestSet(request);//将读写权跟title信息放到request中
        //加载学院专业班级列表信息
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("jjfzSh");
    }

    /**
    * 积极份子单个审核
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzShOne(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        QgzxGzdxService service = new QgzxGzdxService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");//操作类型
        String pkValue = request.getParameter("pkValue");//主键
        String tableName = "qgzx_pjpy_jjfzb";//表名称
        String viewName = "view_qgzx_pjpy_jjfzb";//视图名称
        String pjxn=Base.getJxjsqxn();//评奖学年
        if("save".equalsIgnoreCase(type)){//信息修改
        	String[] pkArr = new String[]{pkValue};
        	HashMap<String, String[]> primaryMap = new HashMap<String, String[]>();
        	primaryMap.put("chkVal", pkArr);
        	
        	HashMap<String, String> valueMap = new HashMap<String, String>(); 
        	valueMap = getValueMap(request, PRIFIX_SAVE);
        	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }
        
        selectPageDataByOne(request, tableName, viewName, pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//查询学生基本信息
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//读写权信息
        request.setAttribute("qgzxgw", service.getQgzxgw(rs.get("xh")));
        request.setAttribute("zcpm", service.getZcpm(pjxn, rs.get("xh")));
        request.setAttribute("bjgkms", service.getBjgkms(pjxn, rs.get("xh")));
        request.setAttribute("wjcs", service.getWjcs(pjxn, rs.get("xh")));
        request.setAttribute("type", type);
        request.setAttribute("chkList", service.getChkList(3));//审核状态列表
        request.setAttribute("type", type);
        return mapping.findForward("jjfzShOne");
    }



	/**
	 * 临时岗位 - 岗位分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsgwfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxService qzzxService = new QgzxService();
		QgzxGzdxService service = new QgzxGzdxService();
		QgzxTyForm myForm = (QgzxTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 勤工助学相关参数设置
		HashMap<String, String> qgzxMap = qzzxService.getSqsjInfo();
		
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_xsjbxx";
		// 表名
		String realTable = "gzdx_lsgwb";
		// 访问路径
		String path = "qgzx_lsgw_fp.do";
		// 勤工学年
		String xn = qgzxMap.get("xn");
		myForm.setQueryequals_xn(xn);
		myForm.setXn(xn);
		// 勤工学期
		String xq = qgzxMap.get("xq");
		myForm.setQueryequals_xq(xq);
		myForm.setXq(xq);
		// 勤工年度
		String nd = qgzxMap.get("nd");
		myForm.setQueryequals_nd(nd);
		myForm.setNd(nd);
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// =================权限控制 ==================
		if (qzzxService.isYrdwUser(userName)) {
			//判断是否用人单位
			myForm.setUserName(userName);
		}else{
			myForm.setUserName(null);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			// 学院
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
		}
		// =================end==================

		// =================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {

			 boolean result = service.saveLsgw(myForm, realTable);

			 request.setAttribute("result", result);
		}
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "nd" };

		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, nd };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "lsgw");
		// =================end ===================

		return mapping.findForward("lsgwfpManage");
	}
	
	/**
	 * 临时岗位 - 结果查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsgwjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxService qzzxService = new QgzxService();
		QgzxGzdxService service = new QgzxGzdxService();
		QgzxTyForm myForm = (QgzxTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_qgzx_lsgwxx";
		// 表名
		String realTable = "gzdx_lsgwb";
		// 访问路径
		String path = "qgzx_lsgw_jg.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// =================权限控制 ==================
		if (qzzxService.isYrdwUser(userName)) {
			//判断是否用人单位
			myForm.setUserName(userName);
		}else{
			myForm.setUserName(null);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			// 学院
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
		}
		// =================end==================

		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk","xh", "xm", "xb", "xn", "xqmc", "nd",
					"nj", "xymc", "zymc", "bjmc", "lsgwmc", "gwsqsj" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "lsgw");
		// =================end ===================

		return mapping.findForward("lsgwjgManage");
	}
}
