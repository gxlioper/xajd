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
	
	// ����
	private final String TABLENAME = "xg_xsxx_bzrpyb";
	
	// �����ֶ�
	private final String[] SAVE = {"xh","xn","pyr","pysj","pyyj"};
	
	// ���������ֶ�
	private final String[] arrayZd = new String[] {"xh"};
	
	//���������ֶ�
	private final String[] oneZd = new String[] {"xn","pyr","pysj","pyyj"};

	// ����
	private final String[] pk = {"xh","xn"};
	
	XsxxBzrpyService service = new XsxxBzrpyService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();
	
	/**
	 * ������˱���
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
		
		// ---------------------�̶��ı���ֵ begin------------
		valueMap.put("pysj", GetTime.getNowTime2());
		
		valueMap.put("pyr", user.getUserName());
		
		valueMap.put("xn", Base.currXn);
		// ---------------------�̶��ı���ֵ end--------------
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		model.setValueMap(valueMap);
		
		flag=service.saveInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����޸�
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
		
		// ---------------------�̶��ı���ֵ begin------------
		valueMap.put("pysj", GetTime.getNowTime2());
		
		valueMap.put("pyr", user.getUserName());
		
		valueMap.put("xn", Base.currXn);
		// ---------------------�̶��ı���ֵ end--------------
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		model.setValueMap(valueMap);
		
		flag=service.updateInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������˱���
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
		
		// ---------------------�̶��ı���ֵ begin------------
		saveModel.setPyr(user.getUserName());
		saveModel.setPysj( GetTime.getNowTime2());
		saveModel.setXn(Base.currXn);
		
		// ---------------------�̶��ı���ֵ end--------------
		model.setPkValue(saveModel.getPkValue());
		
		model.setOneZd(oneZd);
		
		model.setArrayZd(arrayZd);
		
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		flag=service.saveBatch(model,saveModel);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��ʾ�ֶ��޸�Div
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
		
		// ==================����ǰ̨ҳ��========================
		service.showDiv(model, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
}
