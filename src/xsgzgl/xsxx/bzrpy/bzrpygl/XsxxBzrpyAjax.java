package xsgzgl.xsxx.bzrpy.bzrpygl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.xsxx.bzrpy.BasicModel;

import com.zfsoft.basic.BasicAction;

public class XsxxBzrpyAjax extends BasicAction {
	
	// 表名
	private final String TABLENAME = "xg_xsxx_bzrpyb";
	
	// 保存字段
	private final String[] SAVE = {"xh","xn","pyr","pysj","pyyj"};
	
	// 批量保存字段
	private final String[] arrayZd = new String[] {"xh"};
	
	//批量保存字段
	private final String[] oneZd = new String[] {"xn","pyr","pysj","pyyj"};

	// 主键
	private final String[] pk = {"xh","xn"};
	
	XsxxBzrpyService service = new XsxxBzrpyService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();
	
	/**
	 * 批量审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveOnePy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		// ---------------------固定的保存值 begin------------
		valueMap.put("pysj", GetTime.getNowTime2());
		
		valueMap.put("pyr", user.getUserName());
		
		valueMap.put("xn", Base.currXn);
		// ---------------------固定的保存值 end--------------
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		model.setValueMap(valueMap);
		
		flag=service.saveInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 单个修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateOnePy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		model.setPk(pk);
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		// ---------------------固定的保存值 begin------------
		valueMap.put("pysj", GetTime.getNowTime2());
		
		valueMap.put("pyr", user.getUserName());
		
		valueMap.put("xn", Base.currXn);
		// ---------------------固定的保存值 end--------------
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		model.setValueMap(valueMap);
		
		flag=service.updateInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 批量审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBatch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		XsxxPySaveModel saveModel=new XsxxPySaveModel();
	
		model.setPk(pk);
	
		service.getModelValue(saveModel, request);
		
		// ---------------------固定的保存值 begin------------
		saveModel.setPyr(user.getUserName());
		saveModel.setPysj( GetTime.getNowTime2());
		saveModel.setXn(Base.currXn);
		
		// ---------------------固定的保存值 end--------------
		model.setPkValue(saveModel.getPkValue());
		
		model.setOneZd(oneZd);
		
		model.setArrayZd(arrayZd);
		
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		flag=service.saveBatch(model,saveModel);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 显示字段修改Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxBzrpyModel model=new XsxxBzrpyModel();
		
		String xh=request.getParameter("xh");
		
		String pylx=request.getParameter("pylx");
		
		String writeAble=request.getParameter("writeAble");
		
		String xn=Base.currXn;
		
		model.setXh(xh);
		
		model.setXn(xn);
		
		model.setPylx(pylx);
		
		model.setWriteAble(writeAble);
		
		// ==================构建前台页面========================
		service.showDiv(model, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
}
