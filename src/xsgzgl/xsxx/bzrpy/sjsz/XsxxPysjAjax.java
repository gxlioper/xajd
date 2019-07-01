package xsgzgl.xsxx.bzrpy.sjsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xsgzgl.xsxx.bzrpy.BasicModel;

import com.zfsoft.basic.BasicAction;

public class XsxxPysjAjax extends BasicAction {
	
	// ����
	private final String TABLENAME = "xg_xsxx_pysjszb";
	
	// �����ֶ�
	private final String[] SAVE = {"xn","pysj"};
	
	private String[] pk={"xn"};

	XsxxPysjService service = new XsxxPysjService();

	XsxxPysjInit init = new XsxxPysjInit();
	
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model=new BasicModel();
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
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
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model=new BasicModel();
		
		model.setPk(pk);
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		
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
}
