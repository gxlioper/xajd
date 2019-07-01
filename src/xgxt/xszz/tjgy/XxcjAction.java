package xgxt.xszz.tjgy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.FileManage;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * ���ҵ���Ի�����-ѧ����Ϣ�ɼ�
 * @author Penghui.Qu
 */
public class XxcjAction extends BasicAction {

	/**
	 * ��Ϣ�ɼ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xstx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_tjgz_xxcjb";
		String viewName = "xg_view_tjgz_xxcjb";
		String xh = request.getParameter("xh");
		User user = getUser(request);
		
		if ("stu".equalsIgnoreCase(user.getUserType())){
			xh = user.getUserName();
		}
		
		
		XxcjForm model = (XxcjForm) form;
		XxcjService service = new XxcjService();
		XsxxglService xsxxglService = new XsxxglService();
		
		//����
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			String pk = "xh||xn||xq";
			String pkValue = model.getXh()+model.getXn()+model.getXq();
			String[] onezd = new String[]{"xh","xn","xq","xf",
					"lszn","ge","db","tkjz","yf","cjxs","dbsx",
					"dbcz","dbpk","zbsx","zbcz","zbpk","xbsx",
					"xbcz","xbpk","sqjt","dqlfy","dqlmy","dqlqt",
					"dqwfy","dqwmy","dqwqt","fyfqt","fmwn","fgmn",
					"mgfn","fmgz","fgmxg","mgfxg","fmxg","fmxgyfjy",
					"jtjjqt","fmstlh","fqstcpc","dqfqstpc","mqstpc",
					"dqmqstpc","fqstjc","dqfqstjc","mqstjc","dqmqstjc",
					"fmcj","fcj","dqfc","mcj","dqmc","fwsx","fwls",
					"flqs","qsys","mswyx","msww","mwlw","mlls",
					"sylrs","lrsth","lrstc","gjzx","gjwn","gjdg",
					"gjgz","gjstc","gjqt","dmcx","dmzxx","dmgz",
					"dmdx","dmstc","dmcj","dmqt","zrzh","zdjb",
					"tfsj","qzqk","ywgzqk"};
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });
			//model.setLszn(Base.chgNull(model.getLszn(), "",1));
			boolean result = service.saveCjxx(saveForm, model, request);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		String pkV = xh+Base.currXn+Base.currXq;
		//�������ݲ�ѯ
		if (StringUtils.isNotNull(pkV)){
			selectPageDataByOne(request, tableName, viewName, pkV);
		}
		//����ѧ��������Ϣ
		request.setAttribute("stu", xsxxglService.selectStuinfo(xh));
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("path", "xxcj_xstx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xstx");
	}
	
	
	/**
	 * ��Ϣ�ɼ���˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxcjshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "xg_tjgz_xxcjb";
		String viewName = "xg_view_tjgz_xxcjb";
		XxcjForm model = (XxcjForm) form;		
		XxcjService service = new XxcjService();
		String[] topTr = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",Base.YXPZXY_KEY+"����","�༶����",Base.YXPZXY_KEY+"���","ѧУ���"};
		
		User user = getUser(request);
		
		//ѧԺ�û����˷�Χ
		if ("xy".equalsIgnoreCase(user.getUserType()) && !Boolean.parseBoolean(user.getIsFdy())){
			request.setAttribute("annexTerm", " and xydm='"+user.getUserDep()+"'");
			model.setQueryequals_xydm(user.getUserDep());
		}
		
		//��ѯ
		if (QUERY.equalsIgnoreCase(model.getDoType())){
			String[] colList = new String[]{"disabled","isdis","pkValue","xh","xn",
									"xqmc","xm","nj","xymc","bjmc","xysh","xxsh"};
			service.setCustomAudiColumn(request, user.getUserType()+"sh");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "xxcj_xxcjsh.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xxcjsh");
	}
	
	
	
	/**
	 * ��Ϣ�ɼ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxcjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		
		String tableName = "xg_tjgz_xxcjb";
		XxcjForm model = (XxcjForm) form;
		String shzt = request.getParameter("shzt");
		HashMap<String,String> valueMap = new HashMap<String, String>();
		HashMap<String,String> xqshMap = new HashMap<String, String>();
		
		//Ȩ�޿���
		if ("xy".equals(userType)) {
			//ѧԺ��˵�ֵ 
			valueMap.put("xysh", shzt);
			valueMap.put("xyshr", userName);
			valueMap.put("xyshsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			//valueMap.put("xyshyj", model.getShyj());
			
			//ѧԺȡ����˵�ֵ
			xqshMap.put("xysh", "δ���");
			xqshMap.put("xyshr", "");
			xqshMap.put("xyshsj", "");
			xqshMap.put("xyshyj", "");
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			//ѧУ��˵�ֵ
			valueMap.put("xxsh", shzt);
			valueMap.put("xxshr", userName);
			valueMap.put("xxshsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			//valueMap.put("xxshyj", model.getShyj());
			
			//ѧУȡ����˵�ֵ
			xqshMap.put("xxsh", "δ���");
			xqshMap.put("xxshr", "");
			xqshMap.put("xxshsj", "");
			xqshMap.put("xxshyj", "");
		} 
		
		if ("sh".equalsIgnoreCase(model.getDoType())){
			//���
			auditingBatchOperation(request, getValueArrayMap(request,PRIFIX_PRIMARY_KEY), valueMap, tableName);
		} else if ("qxsh".equalsIgnoreCase(model.getDoType())){
			//ȡ�����
			auditingBatchOperation(request, getValueArrayMap(request,PRIFIX_PRIMARY_KEY), xqshMap, tableName);
		}
		
		model.setDoType(QUERY);
		return xxcjshcx(mapping, form, request, response);
	}
	
	
	
	
	/**
	 * ��Ϣ�ɼ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxcjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_tjgz_xxcjb";
		String viewName = "xg_view_tjgz_xxcjb";
		XxcjForm model = (XxcjForm) form;
		XxcjService service = new XxcjService();
		String[] topTr = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",Base.YXPZXY_KEY+"����","�༶����",Base.YXPZXY_KEY+"���","ѧУ���"};
		User user = getUser(request);
		
		//ѧԺ�û����˷�Χ
		if ("xy".equalsIgnoreCase(user.getUserType()) && !Boolean.parseBoolean(user.getIsFdy())){
			//request.setAttribute("annexTerm", " and xydm='"+user.getUserDep()+"'");
			model.setQueryequals_xydm(user.getUserDep());
		}
		
		//��ѯ
		if (QUERY.equalsIgnoreCase(model.getDoType())){
			String[] colList = new String[]{"disabled","pkValue","xh","xn","xqmc","xm","nj","xymc","bjmc","xysh","xxsh"};
			service.setCustomAudiColumn(request, "jgcx");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "xxcj_xxcjcx.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xxcjcx");
	}
	
	
	/**
	 * ��Ϣ�ɼ�ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxcjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_tjgz_xxcjb";
		XxcjForm model = (XxcjForm) form;
		
		//ɾ��
		deleteOperation(request, tableName);
		
		model.setDoType(QUERY);
		return xxcjcx(mapping, form, request, response);
	}
	
	
	
	/**
	 * ��Ϣ�ɼ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxcjDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_tjgz_xxcjb";
		String viewName = "xg_view_tjgz_xxcjb";
		String pkValue = request.getParameter("pkValue");
		
		User user = getUser(request);
		XxcjForm model = (XxcjForm) form;
		XxcjService service = new XxcjService();
		
		if (StringUtils.isNotNull(pkValue)){
			//�������ݲ�ѯ
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		int count =0;
		//������˱���
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			
			if(!model.getY1().equals("0")){count=count+1;}
			if(!model.getY2().equals("0")){count=count+1;}
			if(!model.getY3().equals("0")){count=count+1;}
			if(!model.getY4().equals("0")){count=count+1;}
			if(!model.getY5().equals("0")){count=count+1;}
			if(!model.getY6().equals("0")){count=count+1;}
			if(!model.getY7().equals("0")){count=count+1;}
			if(!model.getY8().equals("0")){count=count+1;}
			if(!model.getY9().equals("0")){count=count+1;}
			
			if(count==0){
				model.setYcount("1");
			}else{
				model.setYcount(count+"");
			}
			System.out.println(count);
			String pk = "xh||xn||xq";
			String[] oneZd = new String[]{"xysh","xyshsj","xyshyj","xyshr",
						"y1","y2","y3","y4","y5","y6","y7","y8","y9","z","xxsh","ycount","zrzh","zdjb","tfsj","qzqk"};
			//ֻ��ѧԺ�û������޸�
			if (!"xy".equalsIgnoreCase(user.getUserType())){
				oneZd = new String[]{"xxsh","xxshr","xxshsj","xxshyj"};
			}
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setOnezd(oneZd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });
			
			boolean result = service.updateCjxx(saveForm, model, user);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		service.setList(request);
		request.setAttribute("systime", GetTime.getTimeByFormat("yyyy-mm-dd"));
		return mapping.findForward("xxcjDgsh");
	}
	
	
	/**
	 * ��Ϣ�ɼ��޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxcjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_tjgz_xxcjb";
		String viewName = "xg_view_tjgz_xxcjb";
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		XxcjForm model = (XxcjForm) form;
		XxcjService service = new XxcjService();
		
		//����
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			String pk = "xh||xn||xq";
			String[] onezd = new String[]{"xf",
					"lszn","ge","db","tkjz","yf","cjxs","dbsx",
					"dbcz","dbpk","zbsx","zbcz","zbpk","xbsx",
					"xbcz","xbpk","sqjt","dqlfy","dqlmy","dqlqt",
					"dqwfy","dqwmy","dqwqt","fyfqt","fmwn","fgmn",
					"mgfn","fmgz","fgmxg","mgfxg","fmxg","fmxgyfjy",
					"jtjjqt","fmstlh","fqstcpc","dqfqstpc","mqstpc",
					"dqmqstpc","fqstjc","dqfqstjc","mqstjc","dqmqstjc",
					"fmcj","fcj","dqfc","mcj","dqmc","fwsx","fwls",
					"flqs","qsys","mswyx","msww","mwlw","mlls",
					"sylrs","lrsth","lrstc","gjzx","gjwn","gjdg",
					"gjgz","gjstc","gjqt","dmcx","dmzxx","dmgz",
					"dmdx","dmstc","dmcj","dmqt","zrzh","zdjb",
					"tfsj","qzqk","y1","y2","y3","y4","y5","y6",
					"y7","y8","y9","z","xysh","ywgzqk"};
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });
			
			boolean result = service.updateCjxx(saveForm, model, getUser(request));
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		return mapping.findForward("xxcjUpdate");
	}
	
	
	public ActionForward dowDoc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String filePath = request.getParameter("filePath");
		String fileName = "��д˵��.doc";
		
		FileManage.downLoadFile(filePath, fileName, response);
		
		return null;
	}
}
