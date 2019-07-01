package xsgzgl.qgzx.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;
/**
 * 勤工助学-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszAction extends BasicExtendAction{
	QgzxCsszService qgzxCsszService = new QgzxCsszService();
	/**
	 * 参数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cssz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//已评困难在校生周期
//		KnsjgService knsjgService = new KnsjgService();
//		request.setAttribute("knsrdzqList", knsjgService.getKnsjgZq());
//		request.setAttribute("xxdm", Base.xxdm);
//
//		HashMap<String,String> splcrs = qgzxCsszService.getSplcCssz();
//		if(StringUtils.isNull(splcrs.get("sqkg"))){
//			splcrs.put("sqkg", "0");//关闭
//		}
//		request.setAttribute("splcrs", splcrs);
//		XtwhShlcService shlcService = new XtwhShlcService();
//		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("qgzx");// 基本设置中审核流程列表的取值通用方法
//		request.setAttribute("shlcList", shlc);
//
//		request.setAttribute("cjffsh", qgzxCsszService.getCjffsh());
//
//
//		String xn=StringUtils.isNull(rs.get("yrdwgwsqxn")) ? Base.currXn : rs.get("yrdwgwsqxn");
//		request.setAttribute("xn", xn);
//		// write和titile
//		setWriteAbleAndTitle(request, "qgzx_jcsz_cssz.do");
//		User user = getUser(request);
//		String userType=user.getUserType();
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> xnList=Base.getXnndList();
		HashMap<String,String> rs = qgzxCsszService.getCssz();
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", xnList);
		request.setAttribute("splcList", shlcService.getShlcByDjlx("qgzx"));
		setWriteAbleAndTitle(request, "qgzx_jcsz_cssz.do");
		return mapping.findForward("cssz");
	}
	
	/**
	 * 浙江大学新勤工
	 * 参数设置
	 * 2016-12-19 孟威
	 */
	public ActionForward zjdxcssz(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		//获取参数设置表
		HashMap<String,String> rs = qgzxCsszService.getCssz();
		request.setAttribute("rs", rs);
		// write和titile
		setWriteAbleAndTitle(request, "qgzx_jcsz_zjdxcssz.do");
		User user = getUser(request);
		String userType=user.getUserType();
		/*取是否允许超过酬金上限的值*/
		String sfyxcgcjsx = rs.get("sfyxcgcjsx");
		request.setAttribute("sfyxcgcjsx", sfyxcgcjsx);
		
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("zjdxcssz");
		}
	}
	
	/**
	 * 新勤工参数设置保存方法
	 * 2016-12-19 孟威
	 */
	public ActionForward saveZjdxqgzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCsszForm myForm = (QgzxCsszForm) form;
		QgzxCsszService service = new QgzxCsszService();
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
