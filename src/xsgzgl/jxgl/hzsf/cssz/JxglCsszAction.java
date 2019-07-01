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
public class JxglCsszAction extends BasicExtendAction{
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
		JxglCsszService service = new JxglCsszService();
		HashMap<String,String> rs = service.getCssz();
		request.setAttribute("rs", rs);
		// write和titile
		setWriteAbleAndTitle(request, "jxgl_jcsz_cssz.do");
		return mapping.findForward("cssz");
	}
}
