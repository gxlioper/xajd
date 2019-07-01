package xsgzgl.qgzx.cssz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.MessageInfo;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �ڹ���ѧ-��������-��������
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszAjax extends BasicExtendAction{
	/**
	 * �������ñ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String message = "";
		QgzxCsszService service = new QgzxCsszService();
		QgzxCsszForm model = (QgzxCsszForm) form;
		model.setYjqxfw(model.getYjqxfwkz());
		//��������ø�λн�꣬н����������Ϊ��
		/*if("no".equals(model.getSfsdgwcjsx())){
			model.setGwzgcjsx("");
			model.setSfkgggwcjsx("no");
		}*/
		boolean flag = service.saveCssz(model);
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
