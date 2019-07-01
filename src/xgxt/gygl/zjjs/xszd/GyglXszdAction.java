package xgxt.gygl.zjjs.xszd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * ����ְҵ���������Ž�ѧ��ά��ACTION
 */
public class GyglXszdAction extends CommonAction {

	/**
	 * ѧ���߶�����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xszdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglXszdForm myForm = (GyglXszdForm) form;
		GyglXszdService service = new GyglXszdService();
		User user = getUser(request);// �û�����

		String title = "��Ԣ���� - ѧ���߶� - ����";
		String xh = request.getParameter("xh");
		String sqsj = service.getNowTime("YYYYMMDD");
		String doType = request.getParameter("doType");

		// ==================��½�û���� ==================
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "��ģ��ֻ����ѧ���û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			xh = user.getUserName();
		}
		// =================end ===================

		// ִ�б������
		if ("save".equalsIgnoreCase(doType)) {
			boolean reslut = service.saveXszdSq(myForm);
			String message = reslut ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա�鿴ԭ��";

			request.setAttribute("message", message);
		}

		// ��ѧ��������Ϣ
		myForm.setXh(xh);
		myForm.setSqsj(sqsj);
		HashMap<String, String> rs = service.getXszdSqInfo(myForm);

		if (Base.isNull(rs.get("xh"))) {
			// ѧ��������Ϣ
			rs = service.getStuInfo(xh);
			rs.put("sqsj", sqsj);
			rs.put("bjsh", "δ���");
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "gygl_zjjs_zdsq.do");
		request.setAttribute("userType", user.getUserType());
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("userDep", user.getUserDep());
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		
		return mapping.findForward("xszdsq");
	}

	/**
	 * ѧ���߶����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xszdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglXszdForm myForm = (GyglXszdForm) form;
		GyglXszdService service = new GyglXszdService();
		User user = getUser(request);// �û�����

		String userType = user.getUserType();
		String userName = user.getUserName();
		String userDep = user.getUserDep();
		String userStatus = user.getUserStatus();

		String tableName = "xg_view_gygl_zjjs_zdssqb";
		String realTable = "xg_gygl_zjjs_zdssqb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("fdy".equalsIgnoreCase(userStatus)||"jd".equalsIgnoreCase(userStatus)) {

		} else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(user.getUserDep());
		}else{
			String msg = "��ģ��ֻ���ɸ���Ա�û���"+Base.YXPZXY_KEY+"�û����в����������û���ǰ�������ѯ����鿴��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// ִ�б������
		if ("save".equalsIgnoreCase(doType)) {

			String shzt = request.getParameter("shzt");
			myForm.setShzt(shzt);
			
			boolean reslut = service.savePlXszdShzt(myForm, user);
			String message = reslut ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա�鿴ԭ��";
			
			request.setAttribute("message", message);
		}

		String[] colList = null;
		
		myForm.getSearchModel().setPath("gygl_zjjs_zdsh.do");
		
		if ("fdy".equalsIgnoreCase(userStatus)||"jd".equalsIgnoreCase(userStatus)) {
			colList = new String[] { "pk", "xh", "xm", "bjmc", "sqsj",
					"zdkssj", "zdjssj", "bjsh" };
		} else if ("xy".equalsIgnoreCase(userType)) {
			colList = new String[] { "pk", "xh", "xm", "bjmc", "sqsj",
					"zdkssj", "zdjssj", "xysh" };
		}

		topTr = SearchUtils.getTopTr(tableName, colList, null);
		rs = service.getXszdshList(myForm, colList, user);
		request.setAttribute("searchTj", myForm.getSearchModel());

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "gygl_zjjs_zdsh.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("xszdsh");
	}
	
	/**
	 * ѧ���߶������ϸ
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xszdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglXszdForm myForm = (GyglXszdForm) form;
		GyglXszdService service = new GyglXszdService();
		User user = getUser(request);// �û�����

		String title = "��Ԣ���� - ѧ���߶� - ���";
		String pk = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String mklx = request.getParameter("mklx");
		
		myForm.setPk(pk);
		HashMap<String, String> rs = service.getXszdSqInfo(myForm);

		// ִ�б������
		if ("save".equalsIgnoreCase(doType)) {

			boolean reslut = service.saveXszdShzt(myForm,user);
			String message = reslut ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա�鿴ԭ��";

			request.setAttribute("message", message);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "pjpy_szzy_jxjsh.do");
		request.setAttribute("userType", user.getUserType());
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("userDep", user.getUserDep());
		request.setAttribute("userStatus", user.getUserStatus());
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("mklx", mklx);
		
		return mapping.findForward("xszdDetail");
	}
	
	/**
	 * ѧ���߶����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xszdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglXszdForm myForm = (GyglXszdForm) form;
		GyglXszdService service = new GyglXszdService();
		User user = getUser(request);// �û�����

		String userType = user.getUserType();
		String userName = user.getUserName();
		String userDep = user.getUserDep();

		String tableName = "xg_view_gygl_zjjs_zdssqb";
		String realTable = "xg_gygl_zjjs_zdssqb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String[] colList = new String[] { "pk", "xh", "xm", "bjmc", "sqsj",
				"zdkssj", "zdjssj", "bjsh", "xysh" };

		myForm.getSearchModel().setPath("gygl_zjjs_zdjg.do");

		topTr = SearchUtils.getTopTr(tableName, colList, null);
		rs = service.getXszdjgList(myForm, colList, user);
		request.setAttribute("searchTj", myForm.getSearchModel());

		if ("stu".equalsIgnoreCase(userType)) {
			request.setAttribute("xh", user.getUserName());
			request.setAttribute("xm", service.getOneValue("view_xsjbxx", "xm",
					"xh", user.getUserName()));
		}
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "gygl_zjjs_zdjg.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("xszdManage");
	}
	
	/**
	 * �߶����뵼��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward expXszdToExcle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglXszdForm myForm = (GyglXszdForm) form;
		GyglXszdService service = new GyglXszdService();
		User user = getUser(request);// �û�����
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expXszdToExcle(myForm,user, response.getOutputStream());
				
		return null;
	}
	
	/**
	 * @describe ������ѡ���ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("dir"));
		String filename = "zdsqb.doc";

		if (!Base.isNull(filename)) {
			dir = servlet.getServletContext().getRealPath("WEB-INF/upLoad")
					+ "/" + filename;
			;
		} else {
			filename = dir.substring(27, dir.length());
		}

		File fileload = new File(dir);
		if(!fileload.exists()){
			String msg = "�������ص��ļ������ڣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
			
		}else{
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(filename));
			InputStream in;
			try {
				in = new FileInputStream(fileload);
				in = new BufferedInputStream(in);
				while ((in.read(b)) != -1) {
					response.getOutputStream().write(b);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
				
		return null;
	}
	
		/**
	 * �߶���������ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglXszdService service = new GyglXszdService();
		
		GyglXszdForm model = (GyglXszdForm) form;
		HashMap<String,String> rs = service.getXszdSqInfo(model);
		
		request.setAttribute("rs", rs);
		return mapping.findForward("printSqb");
	}
}
