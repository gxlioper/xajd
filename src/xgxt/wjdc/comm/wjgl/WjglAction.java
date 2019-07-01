package xgxt.wjdc.comm.wjgl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

public class WjglAction extends BasicAction{

	public ActionForward wjglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Map<String, Object> obj=this.selectPageDataByPagination(request, form, "xg_wjdc_wjxxb", "xg_wjdc_wjxxb", 
				new String[]{"wjid","wjmc","wjzt","sfjf","sffd","wjkssj","wjjssj","cjsj","yhid"});
		System.out.print(obj.get(""));
		return mapping.findForward("wjglManage");
	}
}
