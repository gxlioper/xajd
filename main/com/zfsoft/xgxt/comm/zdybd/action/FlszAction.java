package com.zfsoft.xgxt.comm.zdybd.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ����
 * @�๦������: ��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:56:07
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class FlszAction extends SuperAction {
	private String messageKey;

	/**
	 * 
	 * @����:���ݹ��ܴ����ȡ�����б�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����04:07:46
	 * @�޸ļ�¼:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public ActionForward getFlszList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FlszService service = new FlszService();
		String gndm = request.getParameter("gndm");
		List<HashMap<String, String>> list = service.getListByGndm(gndm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}

}
