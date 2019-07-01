package xgxt.pjpy.tjzy.bcpj;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class PjpyBcpjAction extends BasicAction{

	// 查詢數組
	private String[] COLLIST = {"pkValue","xh","xm", "nj", "bjmc","xmmc", "xmje","kzzd1","sqjg"}; 
	
	//排序字段
	private String[] ORDERBY ={"nj","bjdm","xh"};
	
	private String TABLENAME="xg_view_pjpy_bcpj";

	PjpyBcpjService service = new PjpyBcpjService();

	PjpyBcpjInit init = new PjpyBcpjInit();

	/**
	 * 评奖评优本次评奖
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		PjpyBcpjForm myForm=(PjpyBcpjForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询的结果集
		model.setColList(COLLIST);
		//排序字段
		model.setOrderBy(ORDERBY);
		//功能模块
		model.setGnmk("pjpy");
		//模块路径
		model.setPath("pjpy_tjzy_bcpj.do?method=bcpjManage");
		
		init.initBcpjResult(rForm, model, request);
		//结果列表
		ArrayList<String[]> rsArrList = null;
		rsArrList = (ArrayList<String[]>)service.getBasicList(model);
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("rsArrList", rsArrList);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("bcpjManage");
	}
}
