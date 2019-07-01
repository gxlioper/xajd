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
 * Title: 学生工作管理系统
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
 * Time:2012-7-28 下午14:19:22
 * </p>
 */

public class HjmdtjAjax extends BasicAction {
	
	/**
	 * 获奖名单统计首页查询
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
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_tjcx_hjmdtj.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getHjmdtjCx(myForm);
		String title = service.getHjmdtjCxAll(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(myForm,rsModel, rsArrList,title, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 获奖名单统计首页数据导出
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
		User user = getUser(request);// 用户对象
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
		title+="学年获奖学生名册";
		// ============= 初始化各变量的值 ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expHjmdtj(response.getOutputStream(),myForm,title);
		return null;
	}
}