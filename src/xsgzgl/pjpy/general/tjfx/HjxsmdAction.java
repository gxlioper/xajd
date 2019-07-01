package xsgzgl.pjpy.general.tjfx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

public class HjxsmdAction extends BasicAction {
	public ActionForward hjxsmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		HjxsmdForm myForm=(HjxsmdForm) form;
		HjxsmdService service=new HjxsmdService();
		DAO dao =DAO.getInstance();
		User user=getUser(request);
		String userType=user.getUserStatus();
		String dep=user.getUserDep();
		List<HashMap<String, String>> xyList;
		if("xx".equalsIgnoreCase(userType)){
			
			xyList=service.getXyList();
		}else{
			String sql="select bmmc xymc,bmdm xydm from zxbz_xxbmdm where bmdm=?";
			xyList=dao.getList(sql, new String[]{dep}, new String[]{"xydm","xymc"});
		}
		String sql2="select distinct pjsj from xg_view_pjpy_xyhjtj";
		List<HashMap<String, String>> xnList=dao.getList(sql2, new String[]{}, new String[]{"pjsj"});
		request.setAttribute("xyList", xyList);
		request.setAttribute("xnList", xnList);
		
		request.setAttribute("path", "pjpy_hjxsmdtj.do?method=hjxsmd");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}
	public ActionForward expHjmdtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HjxsmdService service=new HjxsmdService();
		HjxsmdForm myform=(HjxsmdForm) form;
		response.reset();
		response.resetBuffer();
		OutputStream ops = null;
		String xn = request.getParameter("xn");
		String xy=request.getParameter("xy");
		User user=getUser(request);
		String userType=user.getUserStatus();
		List<XyHjxsmdModel> xyHjxsmdList=new ArrayList<XyHjxsmdModel>();
		if("xx".equalsIgnoreCase(userType)){
			List<HashMap<String, String>> xyList=service.getXyList();
			xyHjxsmdList=service.getXyHjxsmdList(xyList,myform);
		}else{
			
		}
		try {
			ops = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			service.exportJxjfwmdNewData(xyHjxsmdList, ops,myform,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}
	
}
