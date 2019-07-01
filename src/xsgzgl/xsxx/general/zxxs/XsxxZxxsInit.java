package xsgzgl.xsxx.general.zxxs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicService;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxZxxsInit {

	/**
	 * 在校学生_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initZxxs(RequestForm rForm, XsxxGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 访问路径
		String path = "stu_info_query.do?method=stuInfo";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		// 判断是否已毕业(区分在校与历史学生)
		String sfzxk = request.getParameter("sfzxk");
		//公寓
		int gygl_colspan=5+("0".equals(GyglNewInit.XQBJ)?0:1)+("0".equals(GyglNewInit.YQBJ)?0:1);
		// 学生资助标志位
		String xszztyFlag = Base.getInitProperties().get("xszztyFlag");
		// 其他字段
		String[] qtzd = new String[] { "sfzxk", "xszztyFlag", "xh",
				"showZsjbxx", "showXsgy", "gygl_colspan" };
		// 其他字段值
		String[] qtzdz = new String[] { sfzxk, xszztyFlag, xh, "yes", "yes",
				String.valueOf(gygl_colspan) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("xszsjbxx",xsxxglService.getXszsjbxx(xh));
		
		if ("update".equalsIgnoreCase(doType)) {// 修改
			try {
				FormModleCommon.requestSetList(new String[] { "mz", "zzmm" },
						request);// 自定义(目前：部门代码,政治面貌)

				// 省列表
				StuInfoDAO stuDao = new StuInfoDAO();
				request.setAttribute("ssList", stuDao.getSsList());

				// 银行列表
				XsxxglDAO xsxxglDao = new XsxxglDAO();
				request.setAttribute("yhList", xsxxglDao.selectYhxx());

				defaultXsxxUpdate(myForm, user, request);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} else if ("detail".equalsIgnoreCase(doType)) {// 详细
			try {
				defaultXsxxDetail(myForm, user, request);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 在校学生修改
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void defaultXsxxUpdate(XsxxGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		BasicService basicService = new BasicService();
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		XsxxZxxsModel model = new XsxxZxxsModel();

		model.setXh(myForm.getXh());

		HashMap<String, String> map = service.getZxxsInfo(model, user);

		StuInfoDAO stuDao = new StuInfoDAO();
		// 籍贯
		String jg = map.get("jg");
		// 生源地
		String syd = map.get("syd");

		if (!Base.isNull(jg) && jg.length() == 20) {
			String[] arr_jg = jg.split("/");
			if (arr_jg != null && arr_jg.length == 3) {
				map.put("jgshen", arr_jg[0]);
				map.put("jgshi", arr_jg[1]);
				map.put("jgxian", arr_jg[2]);
			}
		}

		request.setAttribute("jgshiList", stuDao.getShiList(map.get("jgshen"))
				.get("shiList"));
		request.setAttribute("jgxianList", stuDao.getShiList(map.get("jgshen"))
				.get("xianList"));

		if (!Base.isNull(syd) && syd.length() == 20) {
			String[] arr_syd = syd.split("/");
			if (arr_syd != null && arr_syd.length == 3) {
				map.put("sydshen", arr_syd[0]);
				map.put("sydshi", arr_syd[1]);
				map.put("sydxian", arr_syd[2]);
			}
		}

		request.setAttribute("syshiList", stuDao.getShiList(map.get("syshen"))
				.get("shiList"));
		request.setAttribute("syxianList", stuDao.getShiList(map.get("syshen"))
				.get("xianList"));

		HashMap<String, String> paramMap = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {

			String paramName = entry.getKey();
			String value = basicService.replaceHtml(entry.getValue());

			paramMap.put(paramName, value);
		}

		request.setAttribute("rs", paramMap);
	}

	/**
	 * 在校学生详细
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void defaultXsxxDetail(XsxxGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		BasicService basicService = new BasicService();
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		XsxxglService xsxxglService = new XsxxglService();
		XsxxZxxsModel model = new XsxxZxxsModel();

		model.setXh(myForm.getXh());

		Map<String, String> map = service.getZxxsInfo(model, user);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		List<HashMap<String, String>> pages = xsxxglService.getXsPages();

		map.put("jg", new CommDAO().getSydmc(map.get("jg"), "/", "/"));
		map.put("syd", new CommDAO().getSydmc(map.get("syd"), "/", "/"));

		for (Map.Entry<String, String> entry : map.entrySet()) {

			String paramName = entry.getKey();
			String value = basicService.replaceHtml(entry.getValue());

			paramMap.put(paramName, value);
		}

		request.setAttribute("rs", paramMap);
		request.setAttribute("pages", pages);
	}
}
