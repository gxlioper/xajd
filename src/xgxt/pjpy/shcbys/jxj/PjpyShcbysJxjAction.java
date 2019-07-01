
package xgxt.pjpy.shcbys.jxj;

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

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyShcbysJxjAction extends CommonAction{
	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	DAO dao = DAO.getInstance();
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
		PjpyShcbysJxjActionForm model = (PjpyShcbysJxjActionForm)form;
		HttpSession session = request.getSession();
		String xh = "";
		String userType = session.getAttribute("userType").toString();
		if (StringUtils.isEqual("stu", userType)
				|| StringUtils.isEqual("student", userType)) {//学生用户
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		JxjService service = new JxjService();
		HashMap<String, String> jd  = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuDetails(xh);//学生详细信息
			List<String[]> rss = service.getCjList(xh);//学生学年学期成绩
			jd = service.getJd(xh);
			request.setAttribute("rss", rss);
		}
		String jxjdm = model.getJxjdm();
		if (!StringUtils.isNull(jxjdm)) {
			rs.put("jxjlb", service.getJxjlb(jxjdm));
		}
		rs.put("xn", Base.getJxjsqxn());//申请学年
		rs.put("nd", Base.getJxjsqnd());//申请年度
		rs.put("xq", service.getXqmc(Base.getJxjsqxq()));//申请学期
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		model.setJd(jd.get("pjxfjd"));
		model.setMc1(jd.get("mc1"));
		return mapping.findForward("jxjsq");
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
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = "";
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName").toString();
		}
		JxjService service = new JxjService();
		String pk = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.getJxjsqInfo(pk,pkValue);
		request.setAttribute("rss", service.getCjList2(xh, pkValue));
		request.setAttribute("rs", rs);//查询学生奖学金申请详细信息 
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		request.setAttribute("pk", pk);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjxg");
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
	public ActionForward jxjModiSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyShcbysJxjActionForm model = (PjpyShcbysJxjActionForm)form;
		JxjService service = new JxjService();
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdmO");
		String jd = model.getJd();
		
		boolean flag = service.checkJxjJddb(jxjdm,jd);
		
		if(flag){//绩点达标
			flag = service.jxjModiSave(xh+xn+jxjdm, model,request);
			request.setAttribute("inserted", flag);
		}else{//绩点不达标不可修改
			request.setAttribute("inserted", "jdbhg");
		}
		return jxjModi(mapping, form, request, response);
	}
	
	/**
	 * 考勤信息维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqxxWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {//中国美术学院旷课维护
			return new ActionForward("/pjpy_zgms_kqwh.do", false);
		}
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		zydm = shcbysForm.getZydm();
		nj = shcbysForm.getNj();
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_pjpy_kqb", "pjpy_kqb");
		return mapping.findForward("kqxxwh");
	}
	
	/**
	 * 考勤信息查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		zydm = shcbysForm.getZydm();
		nj = shcbysForm.getNj();
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, shcbysForm);
		JxjService service = new JxjService();
		List<HashMap<String, String>> topList = service.kqyTitleResult(); //查询表头
		List<String[]> resList = service.kqxxResult(model);//查询结果
		appendResult(request, topList, resList);//加载记录
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_pjpy_kqb", "pjpy_kqb");
		shcbysForm.setXm(DealString.toGBK(shcbysForm.getXm()));
		return mapping.findForward("kqxxwh");
	}
	
	/**
	 * 考勤详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		String act = request.getParameter("act");
		HashMap<String, String> rs = service.kqxxDetails(pkValue);//考勤详细信息
		if (rs != null) {
			shcbysForm.setKkcs(rs.get("kkcs"));
			shcbysForm.setZdcs(rs.get("zdcs"));
			shcbysForm.setZtcs(rs.get("ztcs"));
			shcbysForm.setBz(rs.get("bz"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("flag", "view");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("kqxxwhview");
	}
	
	/**
	 * 考勤信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		shcbysForm.setXh(request.getParameter("xh"));
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, shcbysForm);
		boolean bFlag = service.kqxxUpdate(pkValue, model, request);//保存信息
		HashMap<String, String> rs = new HashMap<String, String>();
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		} else {//失败
			request.setAttribute("inserted", "no");
			rs = service.kqxxDetails(pkValue);//考勤详细信息
		}
		shcbysForm.setBz(DealString.toGBK(shcbysForm.getBz()));
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("kqxxwhview");
	}
	
	/**
	 * 考勤信息批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqxxDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		zydm = shcbysForm.getZydm();
		nj = shcbysForm.getNj();
		JxjService service = new JxjService();
		String sFlag = service.kqxxDel(shcbysForm.getCbv(), request);//批量删除
		if (StringUtils.isNull(sFlag)) {//成功
			request.setAttribute("deleted", "yes");
		} else {//失败
			request.setAttribute("failinfo", "其中第" + sFlag + "条记录删除失败!");
			request.setAttribute("deleted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_pjpy_kqb", "pjpy_kqb");
		return mapping.findForward("kqxxwh");
	}

	/**
	 * 奖学金申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		shcbysForm.setJd(request.getParameter("jd"));
		HttpSession session = request.getSession();
		String xh = "";
		String userType = session.getAttribute("userType").toString();
		if (StringUtils.isEqual("stu", userType)
				|| StringUtils.isEqual("student", userType)) {//学生用户
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		JxjService service = new JxjService();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuDetails(xh);//学生详细信息
			List<String[]> rss = service.getCjList(xh);//学生学年学期成绩
			request.setAttribute("rss", rss);
		}
		String tjFlag = service.chkJxjsqtj(xh, shcbysForm.getJd(), shcbysForm
				.getJxjdm());//检查学生是否符合奖学金申请条件
//		if (StringUtils.isNull(tjFlag)) {
//			String num = dao.getOneRs(
//					"select count(*) num from xsjxjb where xh=? and xn=? and xq=? ",
//					new String[] { xh, Base.getJxjsqxn(), Base.getJxjsqxq() },
//					"num");
//			if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
//				tjFlag = "only";
//			}
//		}
		if (StringUtils.isNull(tjFlag)) {//符合条件
			JxjModel model = new JxjModel();
			BeanUtils.copyProperties(model, shcbysForm);
			boolean bFlag = service.jxjsqSave(model, request);//保存奖学金信息
			if (bFlag) {
				request.setAttribute("inserted", "yes");//成功
			} else {
				request.setAttribute("inserted", "no");//失败
			}
		} else {//不符合条件
			request.setAttribute("inserted", tjFlag);//输出不符合信息
		}
		String jxjdm = shcbysForm.getJxjdm();
		if (!StringUtils.isNull(jxjdm)) {
			rs.put("jxjlb", service.getJxjlb(jxjdm));
		}
		rs.put("xn", Base.getJxjsqxn());//申请学年
		rs.put("nd", Base.getJxjsqnd());//申请年度
		rs.put("xq", service.getXqmc(Base.getJxjsqxq()));//申请学期
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendJxjList(request);
		shcbysForm.setDrzw(DealString.toGBK(shcbysForm.getDrzw()));
		shcbysForm.setMz(DealString.toGBK(shcbysForm.getMz()));
		shcbysForm.setZzmm(DealString.toGBK(shcbysForm.getZzmm()));
		shcbysForm.setCsrq(DealString.toGBK(shcbysForm.getCsrq()));
		shcbysForm.setSyd(DealString.toGBK(shcbysForm.getSyd()));
		shcbysForm.setSqly(DealString.toGBK(shcbysForm.getSqly()));
		return mapping.findForward("jxjsq");
	}
	
	/**
	 * 奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
//		String isFdy = session.getAttribute("isFdy").toString();
		shcbysForm.setXn(Base.getJxjsqxn());
		shcbysForm.setXq(Base.getJxjsqxq());
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		appendJxjList(request);//奖学金列表
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendFdybjList(request);
		/*if (!StringUtils.isNull(isFdy) && "true".equalsIgnoreCase(isFdy)) {
			String fdyName = session.getAttribute("userName").toString();
			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
			String sql = "select bjdm from fdybjb where zgh=?";
			String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
			String[] zydmList = new String[]{};
			String[] bjmcList = new String[bjdmList.length];
			String[] zymcList = null;
			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
			if (bjdmList != null && bjdmList.length > 0) {		
				StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
				for (int i=0;i<bjdmList.length;i++) {
					strSql.append("'");
					strSql.append(bjdmList[i]);
					strSql.append("',");
					bjmcList[i] = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[]{bjdmList[i]}, "bjmc");
				}
				sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
				zydmList = dao.getRs(sql, new String[]{}, "zydm");
				if (zydmList != null && zydmList.length>0) {
					zymcList = new String[zydmList.length];
					for (int i=0;i<zydmList.length;i++) {
						zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
					}
				}
			}
			bjList = dao.arrayToList(bjdmList, bjmcList);
			//zyList = dao.arrayToList(zydmList, zymcList);
			//request.setAttribute("zyList", zyList);// 发送专业列表
			request.setAttribute("bjList", bjList);// 发送班级列表
		}*/
		request.setAttribute("fm", shcbysForm);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 奖学金审核查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String userName = session.getAttribute("userName").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		shcbysForm.setXn(Base.getJxjsqxn());
		shcbysForm.setXq(Base.getJxjsqxq());
		JxjService service = new JxjService();
		JxjModel model = new JxjModel();
		shcbysForm.setBz(request.getParameter("bz"));
		BeanUtils.copyProperties(model, shcbysForm);
		
		List<HashMap<String, String>> titList = service.jxjshTableTitle(userType, isFdy);//查询表头
		List<String[]> resList = service.jxjshTableResult(userType, model, isFdy, userName);//查询结果
		appendResult(request, titList, resList);//加载结果
		appendJxjList(request);//奖学金列表
		appendProperties(request, xydm, zydm, nj);//加载列表
		shcbysForm.setXm(DealString.toGBK(shcbysForm.getXm()));
		appendFdybjList(request);
		/*if (!StringUtils.isNull(isFdy) && "true".equalsIgnoreCase(isFdy)) {
			String fdyName = session.getAttribute("userName").toString();
			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
			String sql = "select bjdm from fdybjb where zgh=?";
			String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
			String[] zydmList = new String[]{};
			String[] bjmcList = new String[bjdmList.length];
			String[] zymcList = null;
			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
			if (bjdmList != null && bjdmList.length > 0) {		
				StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
				for (int i=0;i<bjdmList.length;i++) {
					strSql.append("'");
					strSql.append(bjdmList[i]);
					strSql.append("',");
					bjmcList[i] = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[]{bjdmList[i]}, "bjmc");
				}
				sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
				zydmList = dao.getRs(sql, new String[]{}, "zydm");
				if (zydmList != null && zydmList.length>0) {
					zymcList = new String[zydmList.length];
					for (int i=0;i<zydmList.length;i++) {
						zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
					}
				}
			}
			bjList = dao.arrayToList(bjdmList, bjmcList);
			//zyList = dao.arrayToList(zydmList, zymcList);
			//request.setAttribute("zyList", zyList);// 发送专业列表
			request.setAttribute("bjList", bjList);// 发送班级列表
		}*/
		request.setAttribute("fm", shcbysForm);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 奖学金审核结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjShRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		JxjModel model = new JxjModel();
		shcbysForm.setXn(Base.getJxjsqxn());
		shcbysForm.setXq(Base.getJxjsqxq());
		JxjService service = new JxjService();
		BeanUtils.copyProperties(model, shcbysForm);
		String jg = service.xyjxjshRes(userType, request.getParameter("param1"),
				shcbysForm.getCbv(), request, isFdy,model);
		if (StringUtils.isNull(jg)) {//操作成功
			request.setAttribute("inserted", "yes");
		} else{//操作失败
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", jg);
		} 
		shcbysForm.setBz(request.getParameter("bz"));
		appendJxjList(request);//奖学金列表
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendFdybjList(request);
		/*if (!StringUtils.isNull(isFdy) && "true".equalsIgnoreCase(isFdy)) {
			String fdyName = session.getAttribute("userName").toString();
			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
			String sql = "select bjdm from fdybjb where zgh=?";
			String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
			String[] zydmList = new String[]{};
			String[] bjmcList = new String[bjdmList.length];
			String[] zymcList = null;
			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
			if (bjdmList != null && bjdmList.length > 0) {		
				StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
				for (int i=0;i<bjdmList.length;i++) {
					strSql.append("'");
					strSql.append(bjdmList[i]);
					strSql.append("',");
					bjmcList[i] = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[]{bjdmList[i]}, "bjmc");
				}
				sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
				zydmList = dao.getRs(sql, new String[]{}, "zydm");
				if (zydmList != null && zydmList.length>0) {
					zymcList = new String[zydmList.length];
					for (int i=0;i<zydmList.length;i++) {
						zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
					}
				}
			}
			bjList = dao.arrayToList(bjdmList, bjmcList);
			//zyList = dao.arrayToList(zydmList, zymcList);
			//request.setAttribute("zyList", zyList);// 发送专业列表
			request.setAttribute("bjList", bjList);// 发送班级列表
		}*/
		request.setAttribute("fm", shcbysForm);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjShone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		HashMap<String, String> rs = service.jxjshone(pkValue, userType, isFdy);//奖学金单个审核
		request.setAttribute("rs", rs);
		request.setAttribute("rss", service.getCjList1(pkValue));//成绩列表
		//request.setAttribute("rss", new ArrayList<String[]>());//成绩列表
		appendChkList(request);
		shcbysForm.setYj(rs.get("yj"));
		shcbysForm.setYesNo(rs.get("yesno"));
		shcbysForm.setFdyyj(rs.get("fdyyj"));
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("jxjshone");
	}
	
	/**
	 * 奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshoneRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyShcbysJxjActionForm shcbysForm = (PjpyShcbysJxjActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shcbysForm.setXydm(xydm);
		}
		shcbysForm.setJxjdm(request.getParameter("jxjdm"));
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, shcbysForm);
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		String jg = service.jxjshone(model, pkValue, userType, isFdy);
		HashMap<String, String> rs = new HashMap<String, String>();
		if (StringUtils.isNull(jg)) {
			request.setAttribute("inserted", "yes");
		} else { 
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", jg);
			request.setAttribute("rss", service.getCjList1(pkValue));//成绩列表
			rs = service.jxjshone(pkValue, userType, isFdy);
		}
		appendChkList(request);
		request.setAttribute("rs", rs);
		shcbysForm.setFdyyj(DealString.toGBK(shcbysForm.getFdyyj()));
		shcbysForm.setYj(DealString.toGBK(shcbysForm.getYj()));
		return mapping.findForward("jxjshone");
	}
	
	/**
	 * 奖学金申请表打印
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
		JxjService service = new JxjService();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String jxjmc = DealString.toGBK(request.getParameter("jxjmc"));
		String drzw = DealString.toGBK(request.getParameter("drzw"));
		String jd = DealString.toGBK(request.getParameter("jd"));
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String xn = Base.getJxjsqxn();
		String nd = Base.getJxjsqnd();
		String xq = Base.getJxjsqxq();
		
		map = service.getStuDetails(xh);
		map.put("jxjmc", jxjmc);
		map.put("drzw", drzw);
		map.put("jd", jd);
		map.put("sqly", sqly);
		map.put("xn", xn);
		map.put("xq", xq);
		map.put("nd", nd);
		
		request.setAttribute("rs", map);
		return mapping.findForward("jxjprint");
	}
	
	public ActionForward cjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getParameter("xh");
		String xxdm = request.getSession().getAttribute("xxdm").toString();
		String title = null;
		JxjService service = new JxjService();
		List<String[]> rss = new ArrayList<String[]>();
		if(Globals.XXDM_ZJLG.equals(xxdm)){
			title = service.stuname(xh) + Base.getJxjsqxn() + "年" ;
			rss = service.getCjzjlgList(xh);
		}else if (Globals.XXDM_SHCBYSGDZKXX.equalsIgnoreCase(xxdm)){
			title = service.stuname(xh) + Base.getJxjsqxn() + "年" + Base.getJxjsqxqmc() + "学期" ;
			rss = service.getCjList(xh);
		} else {
			HashMap<String, String> zqMap = service.getPjpyZq();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xh", xh);
			title = service.stuname(xh) + Base.getJxjsqxn() + "年" + Base.getJxjsqxqmc() + "学期" ;
			if (!zqMap.isEmpty()) {
				if ("checked".equalsIgnoreCase(zqMap.get("xq"))) {
					map.put("xn", Base.getJxjsqxn());
					map.put("xq", Base.getJxjsqxq());
					title = service.stuname(xh) + Base.getJxjsqxn() + "年" + Base.getJxjsqxqmc() + "学期" ;
				} else if ("checked".equalsIgnoreCase(zqMap.get("xn"))) {
					map.put("xn", Base.getJxjsqxn());
					title = service.stuname(xh) + Base.getJxjsqxn() + "年";
				}
			}
			rss = service.getCjList(map);
		}
		request.setAttribute("title", title);
		
		request.setAttribute("rss", rss);
		return mapping.findForward("cjprint");
	}
	
	/**
	 * 奖学金报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPrintpk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		JxjService service  = new JxjService();
		HashMap<String, String> rs = service.getJxjsqInfo("xh||jxjdm||xn||xq", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("pt");
	}
	
	/**
	 * 成绩打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward cjPrintpk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		HashMap<String, String> ss = service.sn(pkValue);
		String title = ss.get("xm") + Base.getJxjsqxn() + "年" + Base.getJxjsqxq() + "学期" ;
		request.setAttribute("title", title);
		List<String[]> rss = service.getCjList(ss.get("xh"));
		request.setAttribute("rss", rss);
		return mapping.findForward("cjpt");
	}
}
