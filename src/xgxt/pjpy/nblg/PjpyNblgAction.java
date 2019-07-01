
package xgxt.pjpy.nblg;

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
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 宁波理工学院评奖评优Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-30</p>
 */
public class PjpyNblgAction extends DispatchAction {
	
	CommonAction common = null;//通用工具类
	
	String xydm = "";
	String zydm = "";
	String nj = "";
	/**
	 * 等级考试成绩维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpydjksWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		PjpyNblgService service = new PjpyNblgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			nblgForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		String cjb = request.getParameter("cjb");
		String tableName = "view_cjb";
		String realTable = "cjb";
		if (!StringUtils.isNull(cjb) && StringUtils.isEqual(cjb, "cjb")) {
			tableName = "view_cjb";
			realTable = "cjb";
		}
		if (!StringUtils.isNull(cjb) && StringUtils.isEqual(cjb, "djksb")) {
			tableName = "view_xsdjksb";
			realTable = "xsdjksb";
		}
		List<HashMap<String, String>> djksList = service.getDjksList();//等级考试列表
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("djksList", djksList);
		request.setAttribute("writable", "yes");//读写权
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward("djkswh");
	}

	/**
	 * 等级考试成绩查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward djksQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		PjpyNblgService service = new PjpyNblgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			nblgForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		DjkscjModel djkscjModel = new DjkscjModel();
		BeanUtils.copyProperties(djkscjModel, nblgForm);
		List<HashMap<String, String>> djksList = service.getDjksList();//等级考试列表
		List<HashMap<String, String>> topList = service.getDjksCjTitle(djkscjModel.getCjlx());//表头
		List<String[]> resList = service.getDjksCjResult(djkscjModel, nblgForm);//结果
		int count = service.getCount(djkscjModel.getCjlx(), djkscjModel);
		nblgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("djksList", djksList);
		request.setAttribute("writable", "yes");//读写权
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		String cjb = nblgForm.getCjlx();
		String tableName = "view_cjb";
		String realTable = "cjb";
		if (!StringUtils.isNull(cjb) && StringUtils.isEqual(cjb, "cjb")) {
			tableName = "view_cjb";
			realTable = "cjb";
		}
		if (!StringUtils.isNull(cjb) && StringUtils.isEqual(cjb, "djksb")) {
			tableName = "view_xsdjksb";
			realTable = "xsdjksb";
		}
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward("djkswh");
	}
	
	/**
	 * 等级考试成绩与教务同步
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward djkscjTb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		PjpyNblgService service = new PjpyNblgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			nblgForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		DjkscjModel djkscjModel = new DjkscjModel();
		BeanUtils.copyProperties(djkscjModel, nblgForm);
		List<HashMap<String, String>> djksList = service.getDjksList();//等级考试列表
		//同步教务成绩
		boolean bFlag = service.djksCjTb(nblgForm.getXn(), nblgForm.getXq(), nblgForm.getCjlx(),xxdm);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("djksList", djksList);
		request.setAttribute("writable", "yes");//读写权
		String cjb = nblgForm.getCjlx();
		String tableName = "view_cjb";
		String realTable = "cjb";
		if (!StringUtils.isNull(cjb) && StringUtils.isEqual(cjb, "cjb")) {
			tableName = "view_cjb";
			realTable = "cjb";
		}
		if (!StringUtils.isNull(cjb) && StringUtils.isEqual(cjb, "djksb")) {
			tableName = "view_xsdjksb";
			realTable = "xsdjksb";
		}
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward("djkswh");
	}
	
	/**
	 * 奖学金申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String xh = "";
		if ("student".equalsIgnoreCase(userType)
				|| "stu".equalsIgnoreCase(userType)) {//学生用户
			xh = session.getAttribute("userName") == null ? "" : session
					.getAttribute("userName").toString();
		} else {//非学生用户
			xh = request.getParameter("xh");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyNblgService service = new PjpyNblgService();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			if (rs == null) {//不存在学号
				rs.put("stuExists", "no");
			}
		}
		rs.put("xn", Base.getJxjsqxn());
		request.setAttribute("rs", rs);
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		common.appendJxjList(request);
		return mapping.findForward("jxjsqpage");
	}
	
	/**
	 * 奖学金评审表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String xh = request.getParameter("xh");
//		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("xn", Base.getJxjsqxn());
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjprint");
	}

	/**
	 * 条件设置 (基本条件设置,英语条件设置)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		PjpyNblgService service = new PjpyNblgService();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		String act = request.getParameter("act");
		String tname = request.getParameter("tname");
		if ("del".equalsIgnoreCase(act)) {//数据删除
			boolean bFlag = service.tjDel(nblgForm.getCbv(), tname);
			if (bFlag) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
			}
		}
		List<HashMap<String, String>> pageCard_tabNames = service.getPageTitle();//页签列表
		request.setAttribute("pageCard", pageCard_tabNames);
		
		if (StringUtils.isNull(tname)) {
			tname = "nbzy_tjszb";
		}
		if ("nbzy_tjszb".equalsIgnoreCase(tname)) {//数据查询结果
			topList = service.tjTitle();
			rsList = service.tjResult(request.getParameter("xn"), request.getParameter("jxjdm"));
		} else {
			topList = service.yytjTitle();
			rsList = service.yytjResult(request.getParameter("xn"), request.getParameter("jxjdm"));
		}
		List<HashMap<String, String>> tjList = service.getTjList(tname);
		request.setAttribute("tjList", tjList);
		request.setAttribute("tname", tname);
		common = new CommonAction();
		common.appendQryResult(request, topList, rsList);
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		common.appendJxjList(request);
		return mapping.findForward("tjszpage");
	}
	
	public ActionForward tjszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String tname = request.getParameter("tname");
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		PjpyNblgService service = new PjpyNblgService();
		boolean bFlag;
		if ("nbzy_tjszb".equalsIgnoreCase(tname)) {//基本条件保存
			bFlag = service.tjSave(nblgForm.getXn(), nblgForm.getJxjdm(),
					nblgForm.getTj(), nblgForm.getBl());
		} else {//英语条件保存
			bFlag = service.saveYytj(nblgForm.getXn(), nblgForm.getJxjdm(),
					nblgForm.getTj(), nblgForm.getFs());
		}
		if (bFlag) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		common.appendJxjList(request);
		request.setAttribute("tname", tname);
		List<HashMap<String, String>> tjList = service.getTjList(tname);
		request.setAttribute("tjList", tjList);
		return mapping.findForward("tjszpage");
	}
	
	/**
	 * 奖学金审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? "" : session
				.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nblgForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		
		String act = request.getParameter("act");//数据查询标志
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		PjpyNblgService service = new PjpyNblgService();
		if (!StringUtils.isNull(act) && "query".equalsIgnoreCase(act)) {// 查询结果
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			model.setXn(Base.getJxjsqxn());
			topList = service.getjxjshTitle(userType);
			rsList = service.getjxjshResult(model, userType);
		} else if (!StringUtils.isNull(act) && "sh".equalsIgnoreCase(act)) {//审核结果
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			model.setXn(Base.getJxjsqxn());
			String param = request.getParameter("param1");
			String res = service.jxjshResult(nblgForm.getCbv(), param, model,
					userType);// 奖学金审核结果(分学院,学校二级)
			if (StringUtils.isNull(res)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", res);
			}
		}
		String jxjdm = request.getParameter("jxjdm");
		if (!StringUtils.isNull(jxjdm)) {
			String xzrs = service.getJxjxzrs(jxjdm, nblgForm.getBjdm());//获奖限制人数
			request.setAttribute("xzrs", xzrs);
		}
		nblgForm.setXn(Base.getJxjsqxn());
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		common.appendJxjList(request);
		common.appendQryResult(request, topList, rsList);
		common.appendTableXx(request, "view_xsjxjb", "xsjxjb");
		return mapping.findForward("jxjshpage");
	}
	
	/**
	 * 荣誉称号条件设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychTjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		PjpyNblgService service = new PjpyNblgService();
		List<HashMap<String, String>> tjList = service.getTjList();//条件列表
		List<String[]> rsList = service.tjszResult(nblgForm.getXn(), nblgForm.getRychdm());
		List<HashMap<String, String>> titList = service.tjszTitle();
		String act = request.getParameter("act");//操作标志
		if (!StringUtils.isNull(act)) {
			if ("save".equalsIgnoreCase(act)) {//保存数据
				PjpyNblgModel model = new PjpyNblgModel();
				BeanUtils.copyProperties(model, nblgForm);
				boolean bFlag = service.saveTj(model, request);
				if (bFlag) {
					request.setAttribute("deleted", "yes");
				} else {
					request.setAttribute("deleted", "no");
				}
			} else if ("del".equalsIgnoreCase(act)) {//删除数据
				String sFlag = service.delTj(nblgForm.getCbv());
				if (StringUtils.isNull(sFlag)) {
					request.setAttribute("deleted", "yes");
				} else {
					request.setAttribute("deleted", "no");
				} 
			}
		}
		String tj = request.getParameter("tj");
		if ("jxj".equalsIgnoreCase(tj)) {
			request.setAttribute("chgjxj", "yes");
		}
		request.setAttribute("tjList", tjList);
		common = new CommonAction();
		common.appendResult(request, titList, rsList);
		common.appendProperties(request, xydm, zydm, nj);//加载页面列表
		common.appendRychList(request);
		common.appendJxjList(request);
		return mapping.findForward("rychtjszpage");
	}
	
	/**
	 * 荣誉称号审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();//用户类型
		String userDep = session.getAttribute("userDep") == null ? "" : session
				.getAttribute("userDep").toString();//用户部门
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nblgForm.setXydm(xydm);
		} else {
			xydm = nblgForm.getXydm();
		}
		zydm = nblgForm.getZydm();
		nj = nblgForm.getNj();
		String act = request.getParameter("act");//查询标志
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> resList = new ArrayList<String[]>();
		PjpyNblgService service = new PjpyNblgService();
		if (!StringUtils.isNull(act)) {
			if ("query".equalsIgnoreCase(act)) {//查询结果
				PjpyNblgModel model = new PjpyNblgModel();
				BeanUtils.copyProperties(model, nblgForm);
				model.setXn(Base.getJxjsqxn());
				topList = service.rychqryTit(userType);
				resList = service.rychqryRes(model, userType);
			} else if ("sh".equalsIgnoreCase(act)) {//审核数据
				String type = request.getParameter("param1");
				String rs = service.rychshRes(userType, nblgForm.getCbv(),
						nblgForm.getRychdm(), type);
				if (StringUtils.isNull(rs)) {
					request.setAttribute("deleted", "yes");
				} else {
					request.setAttribute("deleted", "no");
					request.setAttribute("failinfo", rs);
				}
			}
		}
		String rychdm = request.getParameter("rychdm");
		if (!StringUtils.isNull(rychdm)) {
			int xsrs = service.rychXzrs(rychdm);//获奖限制人数
			if (xsrs != 0) {
				request.setAttribute("xzrs", xsrs + " 人");
			} else {
				request.setAttribute("xzrs", "未设置");
			}
		}
		nblgForm.setXn(Base.getJxjsqxn());
		common = new CommonAction();
		common.appendProperties(request, xydm, zydm, nj);
		common.appendRychList(request);
		common.appendQryResult(request, topList, resList);
		common.appendTableXx(request, "view_xsrychb", "xsrychb");
		return mapping.findForward("rychshpage");
	}
	
	/**
	 * 荣誉称号报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String xh = request.getParameter("xh");
//		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("xn", Base.getJxjsqxn());
		request.setAttribute("rs", rs);
		return mapping.findForward("rychprint");
	}
	
	/**
	 * 成绩条件过滤设置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjtjGl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<String[]> cjtjList = new ArrayList<String[]>();
		PjpyNblgService service = new PjpyNblgService();
		cjtjList = service.getCjtjszList();
		request.setAttribute("writable", "yes");
		request.setAttribute("rs", cjtjList);
		request.setAttribute("rsNum", cjtjList.size());
		return mapping.findForward("cjtjglpage");
	}
	
	/**
	 * 成绩条件增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjtjadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgActionForm nblgForm = (PjpyNblgActionForm) form;
		String act = request.getParameter("act");//数据保存操作
		PjpyNblgService service = new PjpyNblgService();
		if ("save".equalsIgnoreCase(act)) {
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			boolean bFlag = service.saveCjtj(model, request);//数据保存
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		List<HashMap<String, String>> cjtjList = service.getCjtjList();
		nblgForm.setTjmc(DealString.toGBK(nblgForm.getTjmc()));
		request.setAttribute("tjxzList", cjtjList);
		return mapping.findForward("cjtjaddpage");
	}
	
	/**
	 * 成绩条件修改信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjtjmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("cjtjmodipage");
	}
}
