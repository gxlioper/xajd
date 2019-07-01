package xgxt.qgzx.comm.gwsqwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqForm;
import xgxt.qgzx.service.QgzxGwfbService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学_学生岗位申请-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqAction extends BasicAction {

	/**
	 * 学生岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		QgzxXsgwsqForm qgzxForm = (QgzxXsgwsqForm) form;
		QgzxXsgwsqService service = new QgzxXsgwsqService();
		QgzxGwfbService gwservice = new QgzxGwfbService();
		User user = getUser(request);
		
		HashMap<String, String> map = service.queryGwsqsjb();
		//设置勤工助学学年，学期，年度
		qgzxForm.setXn(map.get("xn"));
		qgzxForm.setXq(map.get("xq"));
		qgzxForm.setNd(map.get("nd"));
		//end
		
		//查询结果 begin
		if ("stu".equalsIgnoreCase(user.getUserStatus())) {
			qgzxForm.setXh(user.getUserName());
			request.setAttribute("topTr", service.queryXsgwsqxxByStuTitle());
			
		} else {
			request.setAttribute("topTr", service.queryXsgwsqxxByTeaTitle());
		}
		request.setAttribute("rs", service.queryXsgwxxByResult(qgzxForm));
		request.setAttribute("yrdwList", gwservice.getYrdwListNotOption(user));
		request.setAttribute("gwxzList", service.getGwxzList());
		// end 
		
		//页面TITLE  begin
		request.setAttribute("path", "qgzx_gwsqwh_xssq.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		// end
		
		return mapping.findForward("xssq");
	}
	
	/**
	 * 学生岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xssqUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		QgzxXsgwsqForm qgzxForm = (QgzxXsgwsqForm) form;
		QgzxXsgwsqService service = new QgzxXsgwsqService();
		User user = getUser(request);
		//保存
		if("save".equalsIgnoreCase(request.getParameter("doType"))){
			String pkValue = request.getParameter("pkValue");
			if(StringUtils.isNotNull(pkValue)){
				updateOperation(request, "xsgwxxb"); //更新
			}else{
				insertOperation(request, "xsgwxxb"); //增加
			}
		}
		//查询
		String xh = "";
		if("stu".equalsIgnoreCase(user.getUserType())){
			xh = user.getUserName();
		}else{
			xh = qgzxForm.getSave_xh();
		}
		Map<String, String> rs = service.getSqxx(xh, qgzxForm.getGwdmsbsj());//申请信息
		String bz = rs.get("bz"); //存储申请信息中的备注，用于重新塞值，避免被学生信息及岗位信息相同字段覆盖
		String shcount = "0";
		//困难生
		if (StringUtils.isNotNull(xh)) {
			DAO dao = DAO.getInstance();
			boolean isKns = dao.isKns(xh);
			//判断是否是贫困生
			rs.put("sfpks", (isKns ==true)? "是" : "否");
			//拼凑学生岗位申请信息主键（通用更新保存使用）
			if(!rs.isEmpty() && StringUtils.isNotNull(rs.get("gwdm")) && StringUtils.isNotNull(rs.get("gwsbsj"))){
				rs.put("pkValue", xh +  rs.get("gwdm") + rs.get("gwsbsj"));
			}
			//获取该生是否已经在审核（为0表示未开始审核，可修改该生申请信息，用于按钮控制）
			shcount = service.getShCount(xh,qgzxForm.getGwdmsbsj());
		}
		request.setAttribute("shcount", shcount);
		rs.putAll(service.getGwxx(qgzxForm));//岗位信息，此处需注意相同字段覆盖问题
		rs.putAll(service.getXsxx(xh));//学生信息，此处需注意相同字段覆盖问题
		rs.put("bz", bz);//覆盖问题处理
		request.setAttribute("rs", rs); 
		//以下三个参数分别用来控制跳转页面的相关按钮显示情况
		request.setAttribute("act", request.getParameter("act"));//该参数为sh时，跳转页面的学生信息部分无“选择”按钮
		request.setAttribute("gb", request.getParameter("gb"));//该参数为yes时，跳转页面有关闭按钮
		request.setAttribute("stuck", request.getParameter("stuck"));//该参数为yes时，跳转页面为查看效果，即不可输入，无保存按钮
		//页面title
		request.setAttribute("path", "qgzx_gwsqwh_xssq.do");//页面当前位置
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xssqUpdate");
	}
	
	/**
	 * 查询学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_xsjbxx";
		String go = request.getParameter("go");
		User user = getUser(request);
		
		QgzxXsgwsqForm sqForm = (QgzxXsgwsqForm) form;
		
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			sqForm.setQueryequals_xydm(user.getUserDep());
		}
		
		if ("go".equalsIgnoreCase(go)) {
			String[] output = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"};
			this.selectPageDataByPagination(request, form, viewName, viewName,
					output);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("userType", user.getUserStatus());
		return mapping.findForward("stuInfo");
	}
	
	/**
	 * 学生岗位申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QgzxXsgwsqForm qgzxForm = (QgzxXsgwsqForm) form;
		QgzxXsgwsqService service = new QgzxXsgwsqService();
		QgzxGwfbService gwservice = new QgzxGwfbService();
		User user = getUser(request);
		String act = request.getParameter("act");
		
		//查询结果 begin
		if ("stu".equalsIgnoreCase(user.getUserStatus())) {
			qgzxForm.setXh(user.getUserName());
			qgzxForm.setXm(user.getRealName());
		} 
		
		List<String[]> rs = new ArrayList<String[]>(){};
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		if (DEL.equalsIgnoreCase(act)) {//删除数据
			String flag = service.deleteXsgwsqxx(qgzxForm);
			request.setAttribute("flag", service.promptMsg(flag));
			rs = service.queryXsgwsqxx(qgzxForm, user.getUserType());
			topTr = service.queryXsgwsqTitle();
		} else if (QUERY.equalsIgnoreCase(act)) {//数据查询
			rs = service.queryXsgwsqxx(qgzxForm, user.getUserType());
			topTr = service.queryXsgwsqTitle();
		}
		
		//查询结果值及表头
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		
		//页面隐藏值
		request.setAttribute("realTable", "xsgwxxb");
		request.setAttribute("tableName", "view_xsgwxx");
		
		request.setAttribute("path", "qgzx_gwsqwh_query.do");//页面当前位置
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("yrdwList", gwservice.getYrdwListNotOption(user));
		request.setAttribute("gwxzList", service.getGwxzList());
		return mapping.findForward("query");
	}
	
	/**
	 * 学生岗位申请表打印
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward sqbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		request.setAttribute("rs", map);
		//直接返回学校代码打印表
		String url = "/sqb/print/qgzx/"+Base.xxdm+".jsp";
		
		return new ActionForward(url, false);
	}
}
