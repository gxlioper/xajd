package xgxt.pjpy.tjzy.zjff;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class PjpyZjffAjax extends BasicAction {

	// ����
	private final String TABLENAME = "xg_pjpy_pjxmsqb";
	
	// ��Ҫ�޸ĵ��ֶ�
	private final String[] UPDATE = {"kzzd1"};
	
	// ����
	private final String[] pk = {"pjxn","pjxq","pjnd","xmdm","xh"};
	
	PjpyZjffService service = new PjpyZjffService();

	PjpyZjffInit init = new PjpyZjffInit();

	/**
	 * �����޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateZjff(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model=new BasicModel();
		
		model.setPk(pk);
		
		//��Ҫ�޸ĵ�ֵ
		HashMap<String,String>valueMap=service.getValueMap(request, UPDATE);
		
		String pkStr=service.unicode2Gbk(request.getParameter("pkValue"));
		
		String[]pkValue=pkStr.split("!!array!!");
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		model.setValueMap(valueMap);
		//����
		model.setPkValue(pkValue);
		
		flag=service.batchUpdate(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��˽��UPDATE
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjgUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		boolean flag=false;
		
		
		flag=service.overUpdate();
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}


}
