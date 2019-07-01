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
* 类名称：BjgmBzrkhAction 
* 类描述：北京工贸班主任考核Action
* 创建人：yijd 
* 创建时间：2012-6-1 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class BjgmBzrkhAction extends BasicExtendAction {
	BjgmBzrkhService service=new BjgmBzrkhService();
	
	//班主任考核列表页面
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
	
	//增加页面
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
	
	//保存增加数据
	public ActionForward bzrkhSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjgmBzrkhForm model=(BjgmBzrkhForm)form;
		int resultRow=service.insertBzrshs(model);
		if(resultRow==0){
			request.setAttribute("message", "保存成功");
		}else if(resultRow==-1){
			request.setAttribute("message", "保存失败!当前班主任考核信息已维护!");
		}else{
			request.setAttribute("message", "保存失败");
		}
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("add");
	}
	
	//编辑数据
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
				//计算总分
				request.setAttribute("bzrkhGrzf", countGrzf(list));
			}else{
				request.setAttribute("bjxxListSize", 0);
			}
			request.setAttribute("bzrkhList", list);
		}
		//FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("update");
	}
	
	//修改保存
	public ActionForward bzrkhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjgmBzrkhForm model=(BjgmBzrkhForm)form;
		User user = getUser(request);// 用户对象
		request.setAttribute("message", service.updateArrBjgmBzrkh(model, user)?"修改成功!":"修改失败!");
		return mapping.findForward("update");
	}
	
	//删除
	public ActionForward bzrkhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] pk = request.getParameterValues("primarykey_checkVal");
		if(pk!=null){
			List<String[]> ids= new ArrayList<String[]>();
			for (int i = 0; i < pk.length; i++) {
				ids.add(pk[i].split(";"));
			}
			request.setAttribute("message",service.deleteBjgmBzrkh(ids)?"删除成功!":"删除失败!");
		}
		return mapping.findForward("delete");
	}
	
	//查看
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
				//计算总分
				request.setAttribute("bzrkhGrzf", countGrzf(list));
			}else{
				request.setAttribute("bjxxListSize", 0);
			}
			request.setAttribute("bzrkhList", list);
		}
		return mapping.findForward("view");
	}
	
	
	//工具类  统计总分	
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
