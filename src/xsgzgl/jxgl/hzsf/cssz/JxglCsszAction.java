package xsgzgl.jxgl.hzsf.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
/**
 * ��ѵ����-��������-��������
 * @author yeyipin
 * @since 2012.7.26
 */
public class JxglCsszAction extends BasicExtendAction{
	/**
	 * ��������
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
		// write��titile
		setWriteAbleAndTitle(request, "jxgl_jcsz_cssz.do");
		return mapping.findForward("cssz");
	}
}
