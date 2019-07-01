/*
 * 创建日期 2007-1-12 bat_zzj
 *
 */
package xgxt.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.XszzForm;


/**
 * @author ChenHuamao <p>E-MAIL: chhuma@hotmail.com</p>
 * @describe 学生资助中的查询统计功能实现 
 */
public class XszzAnalyseAction extends BaseAction {

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 江苏信息职业技术学院高校助学贷款申报汇总表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jsxxzyjsxyGjzxdkhz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String url = "xszzAnalyse.do?method=jsxxzyjsxyGjzxdkhz";
		if (this.power(mapping, request, url) != null) {// 权限控制代码
			return this.power(mapping, request, url);
		} else {
			// 真正的功能实现在这里.
			XszzForm temp = (XszzForm) form;
			String sql = "select * from view_jsxx_gjzxdk1 where nd=?";
			List list = dao.getListNotOut(sql, new String[]{temp.getNd()});
			request.setAttribute("list", list);
			return mapping.findForward("jsxxzyjsxyGjzxdkhz");
		}

	}

}
