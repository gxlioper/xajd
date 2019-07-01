package xsgzgl.pjpy.general.tjcx.hjmdtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchService;
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
 * Time:2012-7-28 ����14:19:22
 * </p>
 */

public class HjmdtjAjax extends BasicAction {
	
	/**
	 * ������ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdtjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjmdtjService service = new HjmdtjService();
		HjmdtjForm myForm = (HjmdtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_tjcx_hjmdtj.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getHjmdtjCx(myForm);
		String title = service.getHjmdtjCxAll(myForm);
		// ���������
		String spHtml = service.createSearchHTML(myForm,rsModel, rsArrList,title, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ������ͳ����ҳ���ݵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expHjmdtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HjmdtjService service = new HjmdtjService();
		HjmdtjForm myForm = (HjmdtjForm) form;
		String title = "";
		String str="";
		User user = getUser(request);// �û�����
		String type=user.getUserType();
		if("xy".equalsIgnoreCase(type)){
			String dep=user.getUserDep();
			DAO dao=DAO.getInstance();
			String sql="select bmmc from zxbz_xxbmdm where bmdm=?";
			str=dao.getOneRs(sql, new String[]{dep}, "bmmc");
		}else{
			str=Base.xxmc;
		}
		String[] inputV = myForm.getSearchModel().getSearch_tj_pjzq();
		title=str+inputV[0];
		title+="ѧ���ѧ������";
		// ============= ��ʼ����������ֵ ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expHjmdtj(response.getOutputStream(),myForm,title);
		return null;
	}
}