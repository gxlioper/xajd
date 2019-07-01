package xgxt.pjpy.czxx.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.pjpy.PjpyFinalUtils;
import xgxt.pjpy.czxx.PjpyCzxxActionForm;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyCsszActon extends CommonAction {

	/**
	 * 参数设置-条件设置维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		CsszService service = new CsszService();
		
		CsszModel model = new CsszModel();
		BeanUtils.copyProperties(model, czxxForm);
		boolean result = false;
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {//查询
			request.setAttribute("rs", service.queryResult(model));
			request.setAttribute("topTr", service.queryTitle());
		} else if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {//保存
			result = service.saveZhszcpblxx(model);
		} else if (DELETE.equalsIgnoreCase(czxxForm.getAct())) {//删除
			result = service.deleteZhszcppmblf(czxxForm.getCbv());
		}
		appendOperResult(request, result);
		
		request.setAttribute("dmList", service.queryDmList(czxxForm.getLb()));
		request.setAttribute("lbList", PjpyFinalUtils.getPjpyLxList());
		
		appendOperQx(request, "pjpy_czxx_tjsz.do");
		return mapping.findForward("tjsz");
	}
	
	/**
	 * 评奖评优开关设置维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kgsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		CsszService service = new CsszService();
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {
			CsszModel model = new CsszModel();
			BeanUtils.copyProperties(model, czxxForm);
			appendOperResult(request, service.saveKgwhxx(model));
		} else {
			//将查询出来的数据复制到FORM中
			copyMapValueToForm(czxxForm, service);
		}
		
		appendOperQx(request, "pjpy_czxx_kgsz.do");
		return mapping.findForward("kgsz");
	}
	
	/**
	 * 荣誉称号条件设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rytjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		CsszService service = new CsszService();
		
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {
			CsszModel model = new CsszModel();
			BeanUtils.copyProperties(model, czxxForm);
			appendOperResult(request, service.saveRychsqtjxx(model));
		}
		
		czxxForm.setCj(service.queryRychsqdycjtj());
		appendOperQx(request, "pjpy_czxx_rytjsz.do");
		request.setAttribute("dmList", service.queryRychsqtjxx());
		return mapping.findForward("rytjsz");
	}
	
	// 将查询出来的数据复制到FORM中
	private void copyMapValueToForm(PjpyCzxxActionForm czxxForm,
			CsszService service) {
		HashMap<String, String> rs = service.queryKgwhxx();
		czxxForm.setJxjsq(rs.get("jxjsq"));
		czxxForm.setJxjsh(rs.get("jxjsh"));
		czxxForm.setRychsq(rs.get("rychsq"));
		czxxForm.setRychsh(rs.get("rychsh"));
	}
}
