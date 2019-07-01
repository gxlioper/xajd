package xsgzgl.pjpy.general.tjcx.bjhjtj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.tjcx.hjmdtj.HjmdtjService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 ����14:19:22
 * </p>
 */

public class BjhjtjAction extends BasicAction {
	
	/**
	 * ѧԺ��ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjhjtjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjhjtjService service = new BjhjtjService();
		HjmdtjService hjmdtjService = new HjmdtjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("pjpy_tjcx_bjhjtj.do");
		
		SearchModel searchModel=new SearchModel();
		
		searchModel.setSearch_tj_pjzq(new String[]{hjmdtjService.getPjzqMrtj()});
		
		// ----------------ҳ���״μ��ز�������Ϊ1----------------
		request.setAttribute("scjz", 1);
		
		searchModel.setPath("pjpy_tjcx_bjhjtj.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjhjtjCx");
	}
}