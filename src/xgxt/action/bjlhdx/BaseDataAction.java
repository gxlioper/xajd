
package xgxt.action.bjlhdx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;

public class BaseDataAction extends DispatchAction{
	public ActionForward saveSynchronizeParams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		CommanForm myForm = (CommanForm) form;
		boolean doresult = false;
		String realTable =  myForm.getGlsjTable();//获得本地表名
		//同步开始时间
		String ymd = request.getParameter("yearMonthDay");
		String hh  = request.getParameter("hh");
		String mi  = request.getParameter("mi");
		String ss  = request.getParameter("ss");
		String kssj = ymd+hh+mi+ss;
		//同步间隔时间
		String day  = request.getParameter("day");
		String hour  = request.getParameter("hour");
		String minute  = request.getParameter("minute");
		String jgsj = day+"!@"+hour+"!@"+minute;
		if(StandardOperation.existsRecord("glsjb", "localtablename||remotetablename", realTable.replace("!@", "").toLowerCase())){
			try{
				doresult = StandardOperation.update("glsjb", new String[]{"kssj","jgsj"}, new String[]{kssj,jgsj}, "localtablename||remotetablename", realTable.replace("!@", "").toLowerCase(), request);
			}catch(Exception e){
				e.printStackTrace();
			}
		} 
		request.setAttribute("doresult", doresult);
		return new ActionForward("/base_data_init.do");
	}
}
