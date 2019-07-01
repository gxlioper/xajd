package xgxt.sztz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;

import com.zfsoft.basic.BasicAction;

public class SztzAjaxAction extends BasicAction{

	
	/**
	 * 加载核心能力下拉列表
	 */
	public ActionForward initHxnl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		CommService service = new CommService();
		
		List<HashMap<String,String>> kmdm = service.getWhList("xg_sztz_hxnlb", "hxnldm", "hxnlmc", null, "kmdm", model.getKmdm());
		JSONArray kmdmList = JSONArray.fromObject(kmdm);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(kmdmList);
		
		return null;
	}
	
	
	/**
	 * 加载项目combogrid
	 */
	public ActionForward initXmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SztzService service = new SztzService();
		List<HashMap<String,String>> xmList = service.getXmList();
		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(JSONArray.fromObject(xmList));
		return null;
	}
	
	public ActionForward getSztzInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		List<HashMap<String,String>> sztzList = service.getMxqCjd(model.getXh());
		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(JSONArray.fromObject(sztzList));
		return null;
	}
	
	
}
