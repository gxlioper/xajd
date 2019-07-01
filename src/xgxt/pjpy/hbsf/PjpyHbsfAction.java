
package xgxt.pjpy.hbsf;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 湖北师范学院评奖评优Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyHbsfAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	
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
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
	}
	
	/**
	 * 奖学金申请首页
	 * priseApply ---- 奖学金申请 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward priseApply(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		String userName = session.getAttribute("userName").toString();//用户名
		String xh = request.getParameter("xh");
		userType = !StringUtils.isNull(userType) ? userType : "";
		userName = !StringUtils.isNull(userName) ? userName : "";
		List<HashMap<String, String>> jxjList = service.getJxjList();//奖学列表
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap.put("stuExists", "yes");
		
		//如果是学生用户则获取学生相关信息
		if (StringUtils.isEqual(userType, "stu") || StringUtils.isEqual(userType, "student")) {
			xh = userName;
		}
		stuMap = service.getStuInfo(xh);
		if (stuMap == null) {//没有该生信息
			stuMap.put("stuExists", "no");
		}
		String jxjdm = hbsfForm.getJxjdm();
		HashMap<String, String> jxjMap = service.getJxjJelb(jxjdm);//获取奖学金信息
		if (jxjMap != null) {
			stuMap.put("jxjlb", jxjMap.get("jxjlb"));
			stuMap.put("jlje", jxjMap.get("jlje"));
		}
		HashMap<String, String> jxjSqxnndMap = service.getJxjSqxnnd();
		if (jxjSqxnndMap != null) {
			stuMap.put("xn", jxjSqxnndMap.get("jxjsqxn"));//奖学金申请学年
			stuMap.put("nd", jxjSqxnndMap.get("jxjsqnd"));//奖学金申请年度
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("jxjList", jxjList);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("priseapply");
	}
	
	/**
	 * 奖学金申请保存
	 * jxjsqsave ---- 奖学金申请保存 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String xh = request.getParameter("xh");//学号
		String wysp = DealString.toGBK(request.getParameter("wysp"));//外语水平
		String sjhm = DealString.toGBK(request.getParameter("sjhm"));//手机号码
		String drzw = DealString.toGBK(request.getParameter("drzw"));//担任职务
		JxjsqSaveModel jxjsqModel = new JxjsqSaveModel();//奖学金申请MODEL
		BeanUtils.copyProperties(jxjsqModel, hbsfForm);
		jxjsqModel.setXh(xh);
		jxjsqModel.setWysp(wysp);
		jxjsqModel.setSjhm(sjhm);
		jxjsqModel.setDrzw(drzw);
		//检查数据是否重复
		boolean bChk = service.chkDataExists(jxjsqModel.getXn() + jxjsqModel.getNd() + xh + jxjsqModel.getJxjdm());
		if (!bChk) {
			boolean bFlag = service.jxjsqSave(jxjsqModel, request);//申请保存
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		} else {
			request.setAttribute("inserted", "exists");
		}
		return mapping.findForward("priseapply");
	}
	
	/**
	 * 综合素质测评首页
	 * zhszcpdefault ---- 综合素质测评默认 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDefault(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tableName", "view_zhszcp");//视图名
		request.setAttribute("realTable", "zhszcp");//表名
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * 综合素质测评查询
	 * zhszcpQry ---- 综合素质测评查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		ZhszcpQryModel zhszcpModel = new ZhszcpQryModel();//综合素质测评查询MODEL
		BeanUtils.copyProperties(zhszcpModel, hbsfForm);
		List<HashMap<String, String>> topList = service.getZhszcpTitle();//查询表头
		List<String[]> resList = service.getZhszcpResult(zhszcpModel);//查询结果
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("tableName", "view_zhszcp");//视图名
		request.setAttribute("realTable", "zhszcp");//表名
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * 综合素质测评单个增加页面
	 * zhszcpadd ---- 综合素质测评增加 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfService service = new PjpyHbsfService();
		String xh = request.getParameter("xh");
		xh = !StringUtils.isNull(xh) ? xh : "";
		HashMap<String, String> resMap = service.getStuInfo(xh);//获取学生相关信息
		if (resMap == null) {
			resMap.put("stuExists", "no");//不存在该学生信息
		}else {
			resMap.put("stuExists", "yes");//存在该学生信息
		}
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * 综合素质测评单个保存
	 * zhszcpsave ---- 综合素质测评保存 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String xh = request.getParameter("xh");
		xh = !StringUtils.isNull(xh) ? xh : "";
		HashMap<String, String> resMap = service.getStuInfo(xh);//获取学生相关信息
		if (resMap == null) {
			resMap.put("stuExists", "no");//不存在该学生信息
		}else {
			resMap.put("stuExists", "yes");//存在该学生信息
		}
		hbsfForm.setXh(xh);
		ZhszcpSaveModel zhszcpModel = new ZhszcpSaveModel();//综合素质测评保存
		BeanUtils.copyProperties(zhszcpModel, hbsfForm);
		boolean bFlag = service.zhszcpSave(zhszcpModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		hbsfForm.setCfjz(DealString.toGBK(hbsfForm.getCfjz()));
		hbsfForm.setBz(DealString.toGBK(hbsfForm.getBz()));
		hbsfForm.setCpjg(DealString.toGBK(hbsfForm.getCpjg()));
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * 综合素质测评批量删除
	 * zhszcpdel ---- 综合素质测评批量删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String[] keys = hbsfForm.getCbv();
		String sJg = service.zhszcpDel(keys, request);//信息批量删除
		request.setAttribute("result", sJg);
		request.setAttribute("tableName", "view_zhszcp");//视图名
		request.setAttribute("realTable", "zhszcp");//表名
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * 综合素质测评单个信息修改信息显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		type = !StringUtils.isNull(type) ? type : "";
		HashMap<String, String> resMap = service.getZhszcpInfoByPk(pkValue);//获取相关信息
		if (StringUtils.isEqual(type, "modi")) {//修改显示
			request.setAttribute("updated", "yes");
		} else {//只显示
			request.setAttribute("updated", "no");
		}
		hbsfForm.setDcj(resMap.get("dcj"));
		hbsfForm.setTcj(resMap.get("tcj"));
		hbsfForm.setZcj(resMap.get("zcj"));
		hbsfForm.setZhszcpzf(resMap.get("zhszcpzf"));
		hbsfForm.setZhcppm(resMap.get("zhcppm"));
		hbsfForm.setXn(resMap.get("xn"));
		hbsfForm.setNd(resMap.get("nd"));
		hbsfForm.setXq(resMap.get("xq"));
		hbsfForm.setBjghkms(resMap.get("bjghkms"));
		hbsfForm.setCfjz(resMap.get("cfjz"));
		hbsfForm.setCpjg(resMap.get("cpjg"));
		hbsfForm.setBz(resMap.get("bz"));
		hbsfForm.setSxddbx(resMap.get("sxddbx"));
		hbsfForm.setSxddf(resMap.get("sxddf"));
		hbsfForm.setSxjkf(resMap.get("sxjkf"));
		hbsfForm.setZzllxx(resMap.get("zzllxx"));
		hbsfForm.setSsjsqk(resMap.get("ssjsqk"));
		hbsfForm.setShsjhd(resMap.get("shsjhd"));
		hbsfForm.setGbrzbx(resMap.get("gbrzbx"));
		hbsfForm.setQttcsj(resMap.get("qttcsj"));
		hbsfForm.setZpf(resMap.get("zpf"));
		hbsfForm.setZytz(resMap.get("zytz"));
		hbsfForm.setYyjn(resMap.get("yyjn"));
		hbsfForm.setJsjjn(resMap.get("jsjjn"));
		hbsfForm.setZyjn(resMap.get("zyjn"));
		hbsfForm.setKxjs(resMap.get("kxjs"));
		hbsfForm.setCxnl(resMap.get("cxnl"));
		hbsfForm.setKcjqpfj(resMap.get("kcjqpfj"));
		hbsfForm.setKcjqpfjpm(resMap.get("kcjqpfjpm"));
		hbsfForm.setTydb(resMap.get("tydb"));
		hbsfForm.setTyhd(resMap.get("tyhd"));
		hbsfForm.setTsqk(resMap.get("tsqk"));
		hbsfForm.setXljkhd(resMap.get("xljkhd"));
		hbsfForm.setXlszzf(resMap.get("xlszzf"));
		hbsfForm.setXlszzk(resMap.get("xlszzk"));
		hbsfForm.setStszzf(resMap.get("stszzf"));
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * 综合素质测评数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpExp(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfService service = new PjpyHbsfService();
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String xn = request.getParameter("xn");
		String modelPath = "";
		if (!StringUtils.isNull(bjdm) && !StringUtils.isNull(xn)) {//班级，学年必选
			modelPath = servlet.getServletContext().getRealPath("")+"/print/zhszcpbyhbsf.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printZhszcp(wwb, xydm, zydm, bjdm, xn);//XLS报表输出
		}
		
		return mapping.findForward("");	
	}
}
