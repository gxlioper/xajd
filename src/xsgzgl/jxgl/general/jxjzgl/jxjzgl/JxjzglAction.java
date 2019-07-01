package xsgzgl.jxgl.general.jxjzgl.jxjzgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.jxgl.general.jxbxgl.JxglJxbxglService;
import xsgzgl.jxgl.general.jxcjgl.JxglJxcjglService;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ���ƹ���_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author �׽���
 * @version 1.0
 */

public class JxjzglAction extends BasicAction {
	
	/**
	 * ��ѯ��ѵ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJxjz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		JxjzglFrom jxjzglModel=(JxjzglFrom)form;
		User user=getUser(request);
		JxjzglFrom jxjzglFrom=new JxjzglFrom();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//��֤��ѵ�Ƿ����
		if(jxxxwhModel == null || jxxxwhModel.isEmpty()){
			//���ز��ܽ��Ƶ�ҳ��
			String msg = "�뽨�������о�ѵ��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			jxjzglFrom.setJzid(jxxxwhModel.get("jxid"));
			jxjzglFrom.setJzmc(jxxxwhModel.get("jxmc"));
			service.initJxjzglRootNode(jxjzglFrom, user);
		}
		//��ѯͳ������
		jxjzglModel.setJxid(jxxxwhModel.get("jxid"));
		jxjzglModel.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(jxjzglModel);
		//���ò�ѯ����
		request.setAttribute("rsTj", rsTj);
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		
		// ����·��
		String path = "jxjzgl_jxjzgl_cxJxjz.do";
		rForm.setPath(path);
		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return mapping.findForward("cxJxjz");
	}
	
	/**
	 * ��ѯ����ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxjzglFrom jxjzglModel=(JxjzglFrom)form;
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		
		//��ѯͳ������
		jxjzglModel.setJxid(jxxxwhModel.get("jxid"));
		jxjzglModel.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(jxjzglModel);
		//���ò�ѯ����
		request.setAttribute("rsTj", rsTj);
		
		//��ȡ��ѵ�ȼ��б�
		List<HashMap<String, String>> jxdjList=service.cxJxdjList(jxjzglModel);
		HashMap<String, String> jxdjzdModel=service.ckJxdjZdModel();
		// ============= ����request��ֵ --ҵ������ =============
		request.setAttribute("jxdjList", jxdjList);
		//����ҵ������request
		request.setAttribute("jxdjzdModel", jxdjzdModel);
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		
		// ����·��
		String path = "jxjzgl_jxjzgl_cxJxjz.do";
		rForm.setPath(path);
		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		return mapping.findForward("cxJzwh");
	}
	
	/**
	 * ���ӽ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initYjJxjz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom jxjzModel=new JxjzglFrom();
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//�����ϼ�idΪ��ǰ��ѵid
		jxjzModel.setSjid(jxxxwhModel.get("jxid"));
		List<HashMap<String, String>> jxjzData=service.cxOneNode(jxjzModel);
		
		String json = JSONArray.fromObject(jxjzData).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * ���ӽ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		
		//model.setJzjb(request.getParameter("jzjb"));
		String zjJzwhHtml=service.zjJzwhHtml(model);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(zjJzwhHtml);
		return null;
	}
	
	/**
	 * ���ӱ��潨��ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjBcJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		
		//������ת��
		jxjzglUnicode2Gbk(model);
		
		boolean result=service.zjBcBzjd(model, user);
		List<HashMap<String, String>> treeNode=null;
		if(result){
			treeNode=zjTreeNode(model);
		}else{
			treeNode=new ArrayList<HashMap<String,String>>();
		}
		String json = JSONArray.fromObject(treeNode).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * �޸Ľ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		
		String zjJzwhHtml=service.xgJzwhHtml(model);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(zjJzwhHtml);
		return mapping.findForward("xgJzwh");
	}
	
	/**
	 * �޸Ľ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgBcJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		
		//������ת��
		jxjzglUnicode2Gbk(model);
		
		boolean result=service.xgBcBzjd(model, user);
		HashMap<String, String> jxjzModel=new HashMap<String, String>();
		if(result){
			jxjzModel=service.ckJxjzModel(model);
		}else{
			
		}
		String json = JSONObject.fromObject(jxjzModel).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * ɾ������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		
		boolean result=service.scBzjd(model, user);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * ��ѯ���νڵ�
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> zjTreeNode(JxjzglFrom model){
		List<HashMap<String, String>> treeNode=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> node=new HashMap<String, String>();
		
		node.put("jzid", model.getJzid());
		node.put("jzmc", model.getJzmc());
		node.put("djdm", model.getJzjb());
		treeNode.add(node);
		return treeNode;
		
	}
	
	/**
	 * �����޸ķ���ת��
	 * @param model
	 */
	private void jxjzglUnicode2Gbk(JxjzglFrom model){
		JxjzglService service=new JxjzglService();
		if(model != null){
			if(model.getJzmc() != null && !"".equals(model.getJzmc())){
				model.setJzmc(service.unicode2Gbk(model.getJzmc()));
			}
			if(model.getJgmc() != null && !"".equals(model.getJgmc())){
				model.setJgmc(service.unicode2Gbk(model.getJgmc()));
			}
			if(model.getJsmc() != null && !"".equals(model.getJsmc())){
				model.setJsmc(service.unicode2Gbk(model.getJsmc()));
			}
		}
	}
	
	
	/**
	 * ��ѯ�¼��ڵ��б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxXjjd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom jxjzModel=new JxjzglFrom();
		JxjzglService service=new JxjzglService();
		jxjzModel.setSjid(request.getParameter("nodeId"));
		
		List<HashMap<String, String>> jxjzData=service.cxJxjzNodeList(jxjzModel);
		
		String json = JSONArray.fromObject(jxjzData).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * ��ѯ��ѵ������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJxjzxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//�����ϼ�idΪ��ǰ��ѵid
		model.setSjid(jxxxwhModel.get("jxid"));
		
		HashMap<String, String> jxjzModel = service.ckJxjzModelBySjid(model);
		jxjzModel.put("xxdm", Base.xxdm);
		String json = JSONObject.fromObject(jxjzModel).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * ��֤��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkJzmc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom jxjzModel=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		jxjzglUnicode2Gbk(jxjzModel);
		boolean result=service.checkJzmc(jxjzModel);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(result));
		
		return null;
	}
	
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxWhjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		request.setAttribute("path", "jxjzgl_jxjzgl_cxJxjz.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxjzgl_cxJxjz.do?method=cxWhjzmd");
		// ----------------����PATH end-----------------------
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("model", model);
		return mapping.findForward("cxWhjzmd");
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxWhjzmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//��ѯ·��
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxjzgl_cxJxjz.do?method=cxWhjzmd");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJxid(jxxxwhModel.get("jxid"));
		ArrayList<String[]> rsArrList=service.getJxjzXsxxList(model);
		//��ͷ
		List<HashMap<String, String>> topTr = service.getTopTr("whjzmd");
		
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���ӱ���ά����ѵ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjBcWhjxjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		String pks=request.getParameter("pks");
		User user=getUser(request);
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		
		service.setRequestValue(rForm, user, request);
		
		boolean result=service.zjBcJxjzmd(pks, model, user);
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(result));
		return null;
	}
	
	
	/**
	 * ��ѯ����ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzxsmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//��ѯ·��
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxjzgl_cxJxjz.do?method=cxJzxsmdAjax");
		// IE�汾
		String[] otherValue=request.getParameter("otherValue").split("!!@@!!");
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJzid(otherValue[1]);
		ArrayList<String[]> rsArrList=service.cxJxjzXsxxListByJzid1(model);
		//��ͷ
		List<HashMap<String, String>> topTr = service.getTopTr("jxjzxsxx");
		
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ��ѯ����ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJxjzTjbAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//�����ϼ�idΪ��ǰ��ѵid
		model.setSjid(jxxxwhModel.get("jxid"));
		
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		
		String jxjzTjbHtml=service.createJxjzTjxHtml(jxjzList, model);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(jxjzTjbHtml));
		return null;
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxjzglFrom model=(JxjzglFrom)form;
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		
		User user=getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		request.setAttribute("path", "jxjzgl_jxjzgl_cxJxjz.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxjzgl_cxJxjz.do?method=cxJzmd");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("model", model);
		request.setAttribute("searchModel", model.getSearchModel());
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		
		//���õ���
		request.setAttribute("tableName", "xg_view_jxgl_jxjzmdb");//һ��Ϊ��ѯ��ͼ
		
		//���÷���path
		String resultPath = request.getParameter("resultPath");
		request.setAttribute("resultPath", resultPath);
		
		//����Ĭ�ϲ�ѯ����
		String sjjz=request.getParameter("sjjz");
		if(sjjz != null && !"".equals(sjjz)){
			SearchModel searchModel = new SearchModel();
			if("1".equals(sjjz)){
				searchModel.setSearch_tj_sfybz(new String[]{"��"});
			}else{
				searchModel.setSearch_tj_sfybz(new String[]{"��"});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		//Ĭ�ϸ߼���ѯ����
		String jzid=request.getParameter("jzid");
		String bjdm=request.getParameter("bjdm");
		String xb=request.getParameter("xb");
		if(jzid != null && !"".equals(jzid)){
			SearchModel searchModel = service.setDefaultSearchModel(jzid, bjdm);
			if(!Base.isNull(xb)){
				searchModel.setSearch_tj_xb(new String[]{xb});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		return mapping.findForward("cxJzmd");
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//��ѯ·��
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxjzgl_cxJxjz.do?method=cxJzmd");
		// IE�汾
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		rsModel.setIe(otherValue[0]);
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//if(otherValue.length < 2){
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJxid(jxxxwhModel.get("jxid"));
		//}else{
		//	model.setSjid(otherValue[1]);
		//}
		
		ArrayList<String[]> rsArrList=service.getJxjzmdList(model);
		//��ͷ
		List<HashMap<String, String>> topTr = service.getTopTr("whjzmd");
		
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcJzTjb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		model.setSjid(jxxxwhModel.get("jxid"));
		//��������ͳ��
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		//��ѯͳ������
		model.setJxid(jxxxwhModel.get("jxid"));
		HashMap<String, String> title=service.getRszjTjb(model);
		title.put("jxmc", jxxxwhModel.get("jxmc"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dcJzTjb(response.getOutputStream(),model,title,jxjzList);
		return mapping.findForward("");
	}
	
	/**
	 * ȡ��ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qxbz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user=getUser(request);
		JxjzglService service=new JxjzglService();
		JxglJxcjglService jxcjService=new JxglJxcjglService();
		JxglJxbxglService jxbxService=new JxglJxbxglService();
		
		//��ȡ����
		String pkValue=request.getParameter("pkValue");
		String jxid=request.getParameter("jxid");
		String jzids=request.getParameter("jzids");
		
		boolean isJxcj=jxcjService.checkIsJxcjByXs(pkValue, jxid);
		boolean isJxbx=jxbxService.checkIsJxbxByXs(pkValue, jxid);
		
		String message=service.scXsjz(isJxcj, isJxbx, pkValue, jzids, user);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(message);
		return null;
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXscjAndBx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		
		JxglJxcjglService jxcjService=new JxglJxcjglService();
		JxglJxbxglService jxbxService=new JxglJxbxglService();
		
		List<HashMap<String, String>> jzmdList=service.getJxjzXsmdList(model);
		String xhs=service.setXsxhByListMap(jzmdList);
		
		boolean isJxcj=jxcjService.checkIsJxcjByXs(xhs, model.getJxid());
		boolean isJxbx=jxbxService.checkIsJxbxByXs(xhs, model.getJxid());
		
		HashMap<String, String> hashMap=new HashMap<String, String>();
		hashMap.put("isJxcj", String.valueOf(isJxcj));
		hashMap.put("isJxbx", String.valueOf(isJxbx));
		
		String json = JSONObject.fromObject(hashMap).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * ��ѯ��ǰ��ѵ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxDqjxxxAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//��ѯͳ������
		model.setJxid(jxxxwhModel.get("jxid"));
		model.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(model);
		
		String dqjxxxHtml=service.createDqjxjzHtml(jxxxwhModel, rsTj);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(dqjxxxHtml));
		return null;
	}
	
	/**
	 * ��ѵ���ƹ����Զ��嵼�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward jxjzglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//if(otherValue.length < 2){
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJxid(jxxxwhModel.get("jxid"));
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getJxjzmdExportDataList(model);
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
