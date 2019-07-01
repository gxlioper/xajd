package xsgzgl.qgzx.tjcx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

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
 * Time:2012-7-23 ����14:19:22
 * </p>
 */

public class QgzxTjcxAction extends BasicAction{

	/**
	 * �¶ȳ�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cjtjcx.do?method=ydcjfftjCx");
		
		SearchModel searchModel=new SearchModel();
		
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		
		searchModel.setPath("qgzx_cjtjcx.do?method=ydcjfftjCx");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if(userType.equalsIgnoreCase("stu")){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("ydcjfftjCx");
		}
	}
	
	/**
	 * ��ȳ�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ndcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cjtjcx.do?method=ndcjfftjCx");
		
		SearchModel searchModel=new SearchModel();
		
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		
		searchModel.setPath("qgzx_cjtjcx.do?method=ndcjfftjCx");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("ndcjfftjCx");
	}
	
	/**
	 * ���ų�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String dqsj = service.getDate();
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cjtjcx.do?method=bmcjfftjCx");
		
		SearchModel searchModel=new SearchModel();
		
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setSearch_tj_yf(new String[]{dqsj.substring(4, 6)});
		
		searchModel.setPath("qgzx_cjtjcx.do?method=bmcjfftjCx");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bmcjfftjCx");
	}
	
	/**
	 * ���˳�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cjtjcx.do?method=grcjfftjCx");
		
		SearchModel searchModel=new SearchModel();
		
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		
		searchModel.setPath("qgzx_cjtjcx.do?method=grcjfftjCx");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("grcjfftjCx");
	}
}