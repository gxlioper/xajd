/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.action.zjly;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

public class XnxqwhAction extends SuperAction{
	private static final String url = "xg_xtwh_bsdtxnxqwh.do";
	XnxqwhService service = new XnxqwhService();
	/**
	 * @description	： 学年学期设置
	 * @author 		： CP（1352）
	 * @date 		：2018-5-18 下午04:37:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xnxqSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		XnxqwhForm myForm = (XnxqwhForm) form;
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> xnList = dao.getXnndList();
//		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		XnxqwhForm model = service.getModel();
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("xnxqSz");
	}
	
	
	/**
	 * @description	： 保存
	 * @author 		： CP（1352）
	 * @date 		：2018-5-18 上午11:53:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcXnxq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnxqwhForm myForm = (XnxqwhForm) form;
		boolean result = service.bcXnxq(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
}