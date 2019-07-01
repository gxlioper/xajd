package xsgzgl.xsxx.general.lsxs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.comm.CommDAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_历史学生_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxLsxsInit {

	/**
	 * 历史学生_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initLsxs(RequestForm rForm, XsxxGeneralForm myForm, User user,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		// 访问路径
		String path = "stu_info_query.do?method=stuInfo";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		// 判断是否已毕业(区分在校与历史学生)
		String sfzxk = request.getParameter("sfzxk");
		// 其他字段
		String[] qtzd = new String[] { "sfzxk" };
		// 其他字段值
		String[] qtzdz = new String[] { sfzxk };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		if ("detail".equalsIgnoreCase(doType)) {// 详细
			try {
				defaultLsxxDetail(myForm, user, request);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 历史学生详细
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void defaultLsxxDetail(XsxxGeneralForm myForm, User user,
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
