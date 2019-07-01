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

	// 表名
	private final String TABLENAME = "xg_pjpy_pjxmsqb";
	
	// 需要修改的字段
	private final String[] UPDATE = {"kzzd1"};
	
	// 主键
	private final String[] pk = {"pjxn","pjxq","pjnd","xmdm","xh"};
	
	PjpyZjffService service = new PjpyZjffService();

	PjpyZjffInit init = new PjpyZjffInit();

	/**
	 * 单个修改
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
		
		//需要修改的值
		HashMap<String,String>valueMap=service.getValueMap(request, UPDATE);
		
		String pkStr=service.unicode2Gbk(request.getParameter("pkValue"));
		
		String[]pkValue=pkStr.split("!!array!!");
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		model.setValueMap(valueMap);
		//主键
		model.setPkValue(pkValue);
		
		flag=service.batchUpdate(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 审核结果UPDATE
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
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		
		flag=service.overUpdate();
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}


}
