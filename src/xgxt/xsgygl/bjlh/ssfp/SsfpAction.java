package xgxt.xsgygl.bjlh.ssfp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.bjlh.sjwh.SjwhService;

public class SsfpAction extends DispatchAction {
	
	/**
	 * ����������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward ssfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		SsfpService service = new SsfpService();
		SjwhService sjwhService = new SjwhService();
		String fpbj = "qrz";
		if (!StringUtils.isNull(myForm.getFpbj())) {
			fpbj = myForm.getFpbj();
		}
		String[] tmp = service.getCwFpZsData(myForm.getXydm(), fpbj);//δ��������(��)Ĭ��Ϊȫ����ѧ��
		if(tmp!=null){
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		}else{
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		String[] tmpT = service.getSsFpYhfcws(myForm.getXydm(), fpbj);//�ѻ�����(��)λ��(��)://δ��������(��)Ĭ��Ϊȫ����ѧ��
		if(tmpT!=null){
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);
			request.setAttribute("bgBed", tmpT[3]);			
		}else{
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");
			request.setAttribute("bgBed", "0");
		}
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_ssfp.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xyList", service.getXyList());
		
		List<HashMap<String, String>> xqList = sjwhService.getSelectList(
				"dm_zju_xq", "dm", "xqmc");
		List<HashMap<String, String>> ldList = sjwhService.getSelectList("sslddmb",
				"lddm", "ldmc");
		List<HashMap<String, String>> csList = sjwhService.getCsList(myForm
				.getLddm());
		request.setAttribute("xiaoqquList", xqList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("lcList", csList);
		request.setAttribute("fpbjList", service.getFpbjList());//�������б�
		request.setAttribute("commanForm", myForm);
		request.setAttribute("ssxxList",service.getSsFpSsXxList(myForm.getXqdm(), myForm.getXb(), myForm.getLddm(), myForm.getCs()));//δ����������Ϣ�б�
		request.setAttribute("ssfpList", service.getSsFpFpSsXxList(myForm.getLddm(), myForm.getCs(), myForm.getXydm(), fpbj));//�ѻ���������Ϣ�б�
		return mapping.findForward("ssfpManage");
	}
	
	/**
	 * �������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSsfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		//Ҫɾ�����ѻ�������SQL�ַ���
		String oldMappingItems = myForm.getOldCondiSqlValue();
		//�ѻ�������SQL�ַ���
		String newMappingItems = myForm.getConditionSqlValue();
		SsfpService service = new SsfpService();
		boolean result = service.saveSsfpxx(oldMappingItems, newMappingItems);
		if (result) {
			oldMappingItems=newMappingItems;//���ľ�ֵ
			request.setAttribute("doFlag", "ok");
		} else {
			request.setAttribute("doFlag", "no");
		}		
		request.setAttribute("oldMappingItems", oldMappingItems);
		return ssfpManage(mapping, form, request, response);
	}
	
	/**
	 * �����������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssfpquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = (String) session.getAttribute("userDep");
		SsfpService service = new SsfpService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		if ("query".equalsIgnoreCase(request.getParameter("operType"))) {
			SsfpModel model = new SsfpModel();
			BeanUtils.copyProperties(model, myForm);
			
			String sfyc = myForm.getSfyc();
			if (!Base.isNull(sfyc)) {
				String tableName = "view_bjlh_ssfp_err";
				String[] colList = new String[] { "pk", "xqmc", "ldmc", "xb",
						"cs", "qsh", "xymc", "err" };
				String fbbj = myForm.getFbbj();
				if (!"qrz".equalsIgnoreCase(fbbj)) {
					model.setXydm(fbbj);
				}
				topTr = SearchUtils.getTopTr(tableName, colList, null);
				rs = service.getGyglList(tableName, model, colList);
			}else{
				rs = (ArrayList<String[]>)service.querySsfpResult(model);
				topTr = service.querySshfTitle();
			}
		}
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_ssfp.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xyList", service.getXyList());
		appendList(request, myForm);
		FormModleCommon.commonRequestSet(request, "ssfpb", "ssfpb", rs, topTr);
		return mapping.findForward("ssfpquery");
	}
	
	/**
	 * ����¥����������У���������б�
	 * @param request
	 * @param myForm
	 */
	public void appendList(HttpServletRequest request, BjlhGyglForm myForm) {
		SjwhService service = new SjwhService();
		List<HashMap<String, String>> xqList = service.getSelectList(
				"dm_zju_xq", "dm", "xqmc");
		List<HashMap<String, String>> ldList = service.getSelectList("sslddmb",
				"lddm", "ldmc");
		List<HashMap<String, String>> csList = service.getCsList(myForm
				.getLddm());
		List<HashMap<String, String>> qsList = service.getQsList(myForm
				.getLddm(), myForm.getCs(), myForm.getFplx(), myForm.getXydm());
		request.setAttribute("xqList", xqList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("csList", csList);
		request.setAttribute("qsList", qsList);
		request.setAttribute("fpbjList", service.getFpbjList());
	}
	
	/**
	 * ɾ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSsfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SsfpService service = new SsfpService();
		boolean result = service.deleteSsfpxx(myForm.getCbv());
		request.setAttribute("result", result);
		return ssfpquery(mapping, form, request, response);
	}
}
