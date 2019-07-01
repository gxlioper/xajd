/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����04:15:21 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-18 ����04:15:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	private static final String url = "xsxx_xnxj_jcsz.do";
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����04:42:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ��С��(��ʦ��)-��������-����")
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm model = (JcszForm) form;
		JcszService service = new JcszService();
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		
		String type = model.getType();
		
		if(type != null && "save".equals(type)){
			boolean result = service.setupKgsz(model.getKg() , model.getSpl(),model.getXjxn());
			request.setAttribute("result", result);
		}
		
		HashMap<String , String> map = service.getOneKgzt();
		model.setXjxn(map.get("xjxn"));
		if(null!=map && map.get("kg") != null){
			model.setKg(map.get("kg"));
			model.setSpl(map.get("spl"));
		}else{
			model.setKg("y");
		}
		
		HashMap<String, String> rs=service.splCx();
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "xsxx_xnxj_jcsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
}
