package xgxt.qgzx.comm.gwsqwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqForm;
import xgxt.qgzx.service.QgzxGwfbService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧ_ѧ����λ����-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqAction extends BasicAction {

	/**
	 * ѧ����λ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		QgzxXsgwsqForm qgzxForm = (QgzxXsgwsqForm) form;
		QgzxXsgwsqService service = new QgzxXsgwsqService();
		QgzxGwfbService gwservice = new QgzxGwfbService();
		User user = getUser(request);
		
		HashMap<String, String> map = service.queryGwsqsjb();
		//�����ڹ���ѧѧ�꣬ѧ�ڣ����
		qgzxForm.setXn(map.get("xn"));
		qgzxForm.setXq(map.get("xq"));
		qgzxForm.setNd(map.get("nd"));
		//end
		
		//��ѯ��� begin
		if ("stu".equalsIgnoreCase(user.getUserStatus())) {
			qgzxForm.setXh(user.getUserName());
			request.setAttribute("topTr", service.queryXsgwsqxxByStuTitle());
			
		} else {
			request.setAttribute("topTr", service.queryXsgwsqxxByTeaTitle());
		}
		request.setAttribute("rs", service.queryXsgwxxByResult(qgzxForm));
		request.setAttribute("yrdwList", gwservice.getYrdwListNotOption(user));
		request.setAttribute("gwxzList", service.getGwxzList());
		// end 
		
		//ҳ��TITLE  begin
		request.setAttribute("path", "qgzx_gwsqwh_xssq.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		// end
		
		return mapping.findForward("xssq");
	}
	
	/**
	 * ѧ����λ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xssqUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		QgzxXsgwsqForm qgzxForm = (QgzxXsgwsqForm) form;
		QgzxXsgwsqService service = new QgzxXsgwsqService();
		User user = getUser(request);
		//����
		if("save".equalsIgnoreCase(request.getParameter("doType"))){
			String pkValue = request.getParameter("pkValue");
			if(StringUtils.isNotNull(pkValue)){
				updateOperation(request, "xsgwxxb"); //����
			}else{
				insertOperation(request, "xsgwxxb"); //����
			}
		}
		//��ѯ
		String xh = "";
		if("stu".equalsIgnoreCase(user.getUserType())){
			xh = user.getUserName();
		}else{
			xh = qgzxForm.getSave_xh();
		}
		Map<String, String> rs = service.getSqxx(xh, qgzxForm.getGwdmsbsj());//������Ϣ
		String bz = rs.get("bz"); //�洢������Ϣ�еı�ע������������ֵ�����ⱻѧ����Ϣ����λ��Ϣ��ͬ�ֶθ���
		String shcount = "0";
		//������
		if (StringUtils.isNotNull(xh)) {
			DAO dao = DAO.getInstance();
			boolean isKns = dao.isKns(xh);
			//�ж��Ƿ���ƶ����
			rs.put("sfpks", (isKns ==true)? "��" : "��");
			//ƴ��ѧ����λ������Ϣ������ͨ�ø��±���ʹ�ã�
			if(!rs.isEmpty() && StringUtils.isNotNull(rs.get("gwdm")) && StringUtils.isNotNull(rs.get("gwsbsj"))){
				rs.put("pkValue", xh +  rs.get("gwdm") + rs.get("gwsbsj"));
			}
			//��ȡ�����Ƿ��Ѿ�����ˣ�Ϊ0��ʾδ��ʼ��ˣ����޸ĸ���������Ϣ�����ڰ�ť���ƣ�
			shcount = service.getShCount(xh,qgzxForm.getGwdmsbsj());
		}
		request.setAttribute("shcount", shcount);
		rs.putAll(service.getGwxx(qgzxForm));//��λ��Ϣ���˴���ע����ͬ�ֶθ�������
		rs.putAll(service.getXsxx(xh));//ѧ����Ϣ���˴���ע����ͬ�ֶθ�������
		rs.put("bz", bz);//�������⴦��
		request.setAttribute("rs", rs); 
		//�������������ֱ�����������תҳ�����ذ�ť��ʾ���
		request.setAttribute("act", request.getParameter("act"));//�ò���Ϊshʱ����תҳ���ѧ����Ϣ�����ޡ�ѡ�񡱰�ť
		request.setAttribute("gb", request.getParameter("gb"));//�ò���Ϊyesʱ����תҳ���йرհ�ť
		request.setAttribute("stuck", request.getParameter("stuck"));//�ò���Ϊyesʱ����תҳ��Ϊ�鿴Ч�������������룬�ޱ��水ť
		//ҳ��title
		request.setAttribute("path", "qgzx_gwsqwh_xssq.do");//ҳ�浱ǰλ��
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xssqUpdate");
	}
	
	/**
	 * ��ѯѧ����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_xsjbxx";
		String go = request.getParameter("go");
		User user = getUser(request);
		
		QgzxXsgwsqForm sqForm = (QgzxXsgwsqForm) form;
		
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			sqForm.setQueryequals_xydm(user.getUserDep());
		}
		
		if ("go".equalsIgnoreCase(go)) {
			String[] output = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"};
			this.selectPageDataByPagination(request, form, viewName, viewName,
					output);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("userType", user.getUserStatus());
		return mapping.findForward("stuInfo");
	}
	
	/**
	 * ѧ����λ��������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QgzxXsgwsqForm qgzxForm = (QgzxXsgwsqForm) form;
		QgzxXsgwsqService service = new QgzxXsgwsqService();
		QgzxGwfbService gwservice = new QgzxGwfbService();
		User user = getUser(request);
		String act = request.getParameter("act");
		
		//��ѯ��� begin
		if ("stu".equalsIgnoreCase(user.getUserStatus())) {
			qgzxForm.setXh(user.getUserName());
			qgzxForm.setXm(user.getRealName());
		} 
		
		List<String[]> rs = new ArrayList<String[]>(){};
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		if (DEL.equalsIgnoreCase(act)) {//ɾ������
			String flag = service.deleteXsgwsqxx(qgzxForm);
			request.setAttribute("flag", service.promptMsg(flag));
			rs = service.queryXsgwsqxx(qgzxForm, user.getUserType());
			topTr = service.queryXsgwsqTitle();
		} else if (QUERY.equalsIgnoreCase(act)) {//���ݲ�ѯ
			rs = service.queryXsgwsqxx(qgzxForm, user.getUserType());
			topTr = service.queryXsgwsqTitle();
		}
		
		//��ѯ���ֵ����ͷ
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		
		//ҳ������ֵ
		request.setAttribute("realTable", "xsgwxxb");
		request.setAttribute("tableName", "view_xsgwxx");
		
		request.setAttribute("path", "qgzx_gwsqwh_query.do");//ҳ�浱ǰλ��
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("yrdwList", gwservice.getYrdwListNotOption(user));
		request.setAttribute("gwxzList", service.getGwxzList());
		return mapping.findForward("query");
	}
	
	/**
	 * ѧ����λ������ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward sqbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		request.setAttribute("rs", map);
		//ֱ�ӷ���ѧУ�����ӡ��
		String url = "/sqb/print/qgzx/"+Base.xxdm+".jsp";
		
		return new ActionForward(url, false);
	}
}
