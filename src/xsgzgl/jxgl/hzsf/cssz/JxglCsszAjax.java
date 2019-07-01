package xsgzgl.jxgl.hzsf.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
/**
 * 军训管理-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.26
 */
public class JxglCsszAjax extends BasicExtendAction{
	/**
	 * 参数设置保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglCsszService service = new JxglCsszService();
		JxglCsszForm model = (JxglCsszForm)form;
		String message = service.saveCssz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
