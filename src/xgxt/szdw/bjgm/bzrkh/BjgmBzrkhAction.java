package xgxt.szdw.bjgm.bzrkh;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * 
* 
* �����ƣ�BjgmBzrkhAction 
* ��������������ó�����ο���Action
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-1 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class BjgmBzrkhAction extends BasicExtendAction {
	BjgmBzrkhService service=new BjgmBzrkhService();
	
	//�����ο����б�ҳ��
	public ActionForward bzrkhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjgmBzrkhForm model=(BjgmBzrkhForm)form;
		request.setAttribute("rs", service.queryBzrkhxx(model,request));
		request.setAttribute("searchTj", model.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("bjgm"));
		request.setAttribute("tableName", "view_xg_szdw_bjgm_bzrkhb");
		//request.setAttribute("tableName", "xg_szdw_bjgm_bzrkhb");
		request.setAttribute("realTable", "xg_szdw_bjgm_bzrkhb");
		
		return mapping.findForward("manage");
	}
	
	//����ҳ��
	public ActionForward bzrkhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String zgh = request.getParameter("zgh");
			BjgmBzrkhForm model=new BjgmBzrkhForm();
			if(StringUtils.isNotNull(zgh)){
				BasicExtendService basicService = new BasicExtendService();
				Map<String, String> map = basicService.getTeaInfo(zgh, null);
				request.setAttribute("rs", map);
				model.setZgh(zgh);
				List<HashMap<String, String>> bjxxList = service.queryBjxxByZgh(model);
				request.setAttribute("bjxxList", bjxxList);
				request.setAttribute("bjxxListSize", bjxxList.size());
			}
			
			FormModleCommon.setNdXnXqList(request);
//			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			return mapping.findForward("add");
	}
	
	//������������
	public ActionForward bzrkhSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjgmBzrkhForm model=(BjgmBzrkhForm)form;
		int resultRow=service.insertBzrshs(model);
		if(resultRow==0){
			request.setAttribute("message", "����ɹ�");
		}else if(resultRow==-1){
			request.setAttribute("message", "����ʧ��!��ǰ�����ο�����Ϣ��ά��!");
		}else{
			request.setAttribute("message", "����ʧ��");
		}
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("add");
	}
	
	//�༭����
	public ActionForward bzrkhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue=request.getParameter("pkValue");
		String[] ids=null;
		BjgmBzrkhForm model=new BjgmBzrkhForm();
		if(!"".equals(pkValue)){
			BasicExtendService basicService = new BasicExtendService();
			ids=pkValue.split(";");
			model.setXn(ids[0]);
			model.setXq(ids[1]);
			model.setZgh(ids[2]);
			List<HashMap<String, String>> list=service.getBjgmBzrkhOnUpdate(model);
			Map<String, String> map = basicService.getTeaInfo(model.getZgh(), null);
			request.setAttribute("rs", map);
			if(list!=null){
				request.setAttribute("bjxxListSize", list.size());
				//�����ܷ�
				request.setAttribute("bzrkhGrzf", countGrzf(list));
			}else{
				request.setAttribute("bjxxListSize", 0);
			}
			request.setAttribute("bzrkhList", list);
		}
		//FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("update");
	}
	
	//�޸ı���
	public ActionForward bzrkhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjgmBzrkhForm model=(BjgmBzrkhForm)form;
		User user = getUser(request);// �û�����
		request.setAttribute("message", service.updateArrBjgmBzrkh(model, user)?"�޸ĳɹ�!":"�޸�ʧ��!");
		return mapping.findForward("update");
	}
	
	//ɾ��
	public ActionForward bzrkhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] pk = request.getParameterValues("primarykey_checkVal");
		if(pk!=null){
			List<String[]> ids= new ArrayList<String[]>();
			for (int i = 0; i < pk.length; i++) {
				ids.add(pk[i].split(";"));
			}
			request.setAttribute("message",service.deleteBjgmBzrkh(ids)?"ɾ���ɹ�!":"ɾ��ʧ��!");
		}
		return mapping.findForward("delete");
	}
	
	//�鿴
	public ActionForward bzrkhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue=request.getParameter("pkValue");
		String[] ids=null;
		BjgmBzrkhForm model=new BjgmBzrkhForm();
		if(!"".equals(pkValue)){
			BasicExtendService basicService = new BasicExtendService();
			ids=pkValue.split(";");
			model.setXn(ids[0]);
			model.setXq(ids[1]);
			model.setZgh(ids[2]);
			List<HashMap<String, String>> list=service.getBjgmBzrkhOnUpdate(model);
			Map<String, String> map = basicService.getTeaInfo(model.getZgh(), null);
			request.setAttribute("rs", map);
			if(list!=null){
				request.setAttribute("bjxxListSize", list.size());
				//�����ܷ�
				request.setAttribute("bzrkhGrzf", countGrzf(list));
			}else{
				request.setAttribute("bjxxListSize", 0);
			}
			request.setAttribute("bzrkhList", list);
		}
		return mapping.findForward("view");
	}
	
	
	//������  ͳ���ܷ�	
	public float countGrzf(List<HashMap<String, String>> list){
		float grzf=0f;
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if("".equals(list.get(i).get("bjdf")) || list.get(i).get("bjdf")=="0" || list.get(i).get("bjdf")==null){
					break;
				}
				grzf=grzf+Integer.valueOf(list.get(i).get("bjdf")).floatValue();
			}
			if(!"".equals(list.get(0).get("grdf")) && list.get(0).get("grdf")!="0" && list.get(0).get("grdf")!=null){
				grzf=(grzf/list.size())+Integer.valueOf(list.get(0).get("grdf")).floatValue();;
			}
		}
		return grzf;
	}
}
