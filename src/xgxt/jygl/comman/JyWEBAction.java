package xgxt.jygl.comman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Encrypt;

import com.zfsoft.basic.BasicAction;

public class JyWEBAction extends BasicAction {
	

	
	/**
	 * ��ҳ
	 * @return
	 * @throws Exception
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("jyweb_userType");
		
		
		//���û�е�¼ѧ�������ǵ�һ������ҵ��
		if (!"yes".equals((String)session.getAttribute("jyweb")) 
				&& Base.isNull((String)session.getAttribute("userName"))) {
			session.setAttribute("jyweb", "yes");
			session.setAttribute("fdyQx",false);
			session.setAttribute("bzrQx",false);
			session.setAttribute("userType","");
			session.setAttribute("userDep","");
			session.setAttribute("userName","");
			session.setAttribute("xxmc", Base.xxmc);
		}
		//��ҵ����ҳ��ʼ��
		service.jywebInit(request, userType);
		service.setJywebList(request, "work");
		
		request.setAttribute("userType", request.getParameter("userType"));
		
		return mapping.findForward("index");
	}
	
	
	
	/**
	 * ��¼
	 * @return
	 * @throws Exception
	 */
	public  ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
			JyWEBService service = new JyWEBService();
			HttpSession session = request.getSession();
			
			String userType = request.getParameter("userType");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String doType = request.getParameter("doType");
			String yzm = request.getParameter("yzm");
			
			if (!Base.isNull(password)) {//����
				password = new Encrypt().encrypt(password);
			}
			
			
			if (!Base.isNull(doType) && "login".equals(doType)) {
					String sYzm = (String) session.getAttribute("yzm");
				
					if ("admin".equals(userType) && !yzm.equalsIgnoreCase(sYzm)) {
						request.setAttribute("message", "��֤�벻��ȷ!");
						return mapping.findForward("adminLogin");
					}
					//�û���¼
					String[] yhInfo = service.login(userType, userName, password);
					
					if (null != yhInfo && yhInfo.length==3) {
						//����session���û���Ϣ
						session.setAttribute("jyweb_userType", userType);
						session.setAttribute("jyweb_userName", userName);
						session.setAttribute("jyweb_realName", yhInfo[0]);
						session.setAttribute("jyweb_userDep", yhInfo[1]);
						session.setAttribute("jyweb_dwshzt", yhInfo[2]);
					} else {
						request.setAttribute("message", "�û��������벻��ȷ!");
					}
			}
			
			if ("admin".equals(userType)) {
				if (!Base.isNull((String)session.getAttribute("jyweb_userType"))) {
					return manager(mapping, form, request, response);
				}
				return mapping.findForward("adminLogin");
			}
			
			request.setAttribute("userType", userType);
			return index(mapping, form, request, response);
	}
	
	
	
	/**
	 * ��Ŀ������ת������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return new ActionForward("/xtwhOther.do?method=xtDmwhNew&ssmk=jyweb",false);
	}
	
	
	
	/**
	 * �����û���ת
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward manager(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("jyweb_userType");
		
		if ("stu".equals(userType)) {
			return mapping.findForward("stu_main");
		} else if ("dw".equals(userType)) {
			return mapping.findForward("dw_main");
		}
		request.setAttribute("menusList", service.getMenus(userType));
		
		return mapping.findForward("manager");
	}
	
	
	/**
	 * ע��
	 * @return
	 * @throws Exception
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			HttpSession session = request.getSession();
			
			//���session
			session.removeAttribute("jyweb_userType");
			session.removeAttribute("jyweb_userName");
			session.removeAttribute("jyweb_realName");
			session.removeAttribute("jyweb_userDep");
			session.removeAttribute("jyweb_dwshzt");
			
			return index(mapping, form, request, response);
	}
	
	
	
	/**
	 * ������Ϣ
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String pkValue = request.getParameter("pkValue");
		
		if (!Base.isNull(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}
		
		String type = request.getParameter("type");
		//��ȡ������ϸ��Ϣ
		HashMap<String, String> map = service.getNewInfo(pkValue,type);
		request.setAttribute("rs", map);
		
		return mapping.findForward("newsInfo");
	}
	
	
	/**
	 * ѡ����๫˾
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward moreCompany(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jygl_dwxxb";
		String viewName = "view_jygl_dwxxb";
		
		String[] colList = new String[] {"pkValue","dwmc","dwxzmc","hyflmc","zcsj"};
		
		request.setAttribute("annexTerm"," and shzt='ͨ��' ");
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		service.setJywebList(request, "company");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("moreCompany");
	}
	
	
	/**
	 * ��λ�û�ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		JyWEBService service = new JyWEBService();
		
		if("save".equalsIgnoreCase(doType)){
			JyglForm myForm = (JyglForm)form;
			Model model = new Model();
			
			BeanUtils.copyProperties(model, myForm);
			
			String msg = service.insertUser(model);
			request.setAttribute("message", msg);
		}
		
		return mapping.findForward("registerPage");
	}
	
	
	
	/**
	 * ��ȡ��Ƹ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_zpxxb";
		String viewName = "view_jygl_zpxx";
		String pkValue = request.getParameter("pkValue");
		String userType = (String) session.getAttribute("jyweb_userType");
		String userName = (String) session.getAttribute("jyweb_userName");
		
		if ("stu".equals(userType)) {
			service.saveStuJobs(pkValue, userName,"���");
			request.setAttribute("hasResume", service.hasResume(userName));
		}
		
		selectPageDataByOne(request, tableName, viewName, pkValue);
		
		service.setJywebList(request, "");
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("workInfo");
	}
	
	
	/**
	 * ��ҵ��Ƹ��λ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zpxxb";
		String viewName = "view_jygl_zpxx";
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		String[] outputColumn = new String[]{"pkValue","zpzwmc","gsmc","gwxz","fbsj","yxqx"};
		//String doType = request.getParameter("doType");
		
		//if ("query".equals(doType)) {
			request.setAttribute("annexTerm", " and xxsh='ͨ��' and yxqx>=(select to_number(to_char(sysdate,'yyyymmdd')) from dual)");
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//}
		
		service.setJywebList(request, "zpgl");
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("workMore");
	}
	
	
	/**
	 * �ļ���Ϣչʾ
	 * @return
	 * @throws Exception
	 */
	public ActionForward fileView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_wjglb";
		String pkValue = request.getParameter("pkValue");
		
		if (!Base.isNull(pkValue)) {
			//�����ļ���Ϣ
			selectPageDataByOne(request, tableName, tableName, pkValue);
			service.setFileLlcs(pkValue);//���������1
		}
		
		return mapping.findForward("fileView");
	}
	
	
	/**
	 * �ļ�����
	 * @return
	 * @throws Exception
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyWEBService service = new JyWEBService();
		
		byte b[] = new byte[500];
		String fileName = request.getParameter("fileName");
		String filePath = request.getParameter("filePath");
		String pkValue = request.getParameter("pkValue");
		
		File file = new File(filePath);
		
		//����ļ����ڼ����أ������ʹ�����Ϣ
		if (null != file && file.exists()) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(fileName));
			InputStream in = new FileInputStream(file);
			in = new BufferedInputStream(in);
			while ((in.read(b)) != -1) {
				response.getOutputStream().write(b);
			}
			in.close();
			service.setFileXzcs(pkValue);//���ش�����1
			return null;
		} else {
			request.setAttribute("message", "�ļ������ڻ�ɾ��������ϵ����Ա");
			return new ActionForward("/prompt.do",false);
		}
		
	}


	/**
	 * �������͸���չʾ
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jygl_zcwjb";
		String[] colList = new String[] {"pkValue","wjbt","fbr","fbsj","readtimes"};
		String wjlx = request.getParameter("queryequals_wjlx");
		String path = "";
		//String doType = request.getParameter("doType");
		
		//��ѯ 
		//if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, tableName, colList);
		//}
		
		if ("1".equals(wjlx)) {
			path = "jyweb_jyxw.do";
		} else if ("3".equals(wjlx)) {
			path = "jyweb_jygg.do";
		} else if ("4".equals(wjlx)) {
			path = "jyweb_jyzc.do";
		} else if ("5".equals(wjlx)) {
			path = "jyweb_syxx.do";
		} else if ("6".equals(wjlx)) {
			path = "jyweb_jyzd.do";
		}
		
		request.setAttribute("wjlx", wjlx);
		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("title", service.getTitle(path));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("newsMore");
	}
	
	
	/**
	 * ��λ����ҳ
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		String tableName = "jygl_dwxxb";
		String viewName = "view_jygl_dwxxb";
		String[] colList = new String[] {"pkValue","dwmc","dwxzmc","hyflmc","zcsj"};
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		service.setJywebList(request, "company");
		return mapping.findForward("companyMore");
	}
	
	
	/**
	 * ��λ��Ϣչʾ
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String tableName = "jygl_dwxxb";
		String viewName = "view_jygl_dwxxb";
		String userName = (String) session.getAttribute("jyweb_userName");
		
		
		selectPageDataByOne(request, tableName, viewName, userName);
		
		return mapping.findForward("companyView");
	}
	
	
	/**
	 * ��λ��ϸ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_dwxxb";
		String viewName = "view_jygl_dwxxb";
		String dwmc = request.getParameter("dwmc");
		String pkValue = request.getParameter("pkValue");
		String userType = (String) session.getAttribute("jyweb_userType");
		String userName = (String) session.getAttribute("jyweb_userName");
		
		if (!Base.isNull(dwmc)) {
			pkValue = service.getYhm(dwmc);
		}
		
		if (!Base.isNull(pkValue)) {
			//��λ��ϸ��Ϣ
			selectPageDataByOne(request, tableName, viewName, pkValue);
			//��λ�ĸ�λ�б�
			request.setAttribute("gwxxList", service.getZpxx(pkValue));
		}
		
		
		if ("stu".equals(userType)) {
			service.saveStuJobs(pkValue, userName ,"���");
			request.setAttribute("hasResume", service.hasResume(userName));
		}
		
		
		return mapping.findForward("companyInfo");
	}
	

	/**
	 * ����ר������ҳ
	 * @return
	 * @throws Exception
	 */
	public ActionForward fileMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_wjglb";
		String[] colList = new String[] {"pkValue","wjm","scr","scsj","llcs","xzcs"};
		String doType = request.getParameter("doType");
		
		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, tableName, colList);
		}

		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("title", service.getTitle("jyweb_xzzq.do"));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("filesMore");
	}
	
	
	/**
	 * ��������ҳ
	 * @return
	 * @throws Exception
	 */
	public ActionForward resumeMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String tableName="jygl_jytjb";
		String viewName="view_jygl_jytjb";
		String[] colList = new String[] { "pkValue","xm", "xb",
				"xymc", "zymc", "gzxz", "gzdd","qwxs" };
		String userType = (String) session.getAttribute("jyweb_userType");
		
		
		if (Base.isNull(userType)) {
			//δ��¼���ܲ鿴ѧ�������б�
			request.setAttribute("message", "�Բ�������δ��¼�����ȵ�¼!");
			return index(mapping, form, request, response);
		} else if ("stu".equals(userType)) {
			//ѧ���û����ܲ鿴ѧ�������б�
			request.setAttribute("message", "�Բ�������Ȩ���ʴ�ҳ!");
			return index(mapping, form, request, response);
		}
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		service.setNjXyZyBjList(request);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("resumeMore");
	}
	

	/**
	 * ��Ƹ�����ҳ
	 * @return
	 * @throws Exception
	 */
	public ActionForward zphMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_xyzph";
		String viewName = "xg_view_jyweb_xyzph";
		String[] colList = new String[] {"pkValue","zplx","zphbt","fbsj","llcs"};
		//String doType = request.getParameter("doType");
		
		//if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		//}

		service.setJywebList(request, "zpgl");
		request.setAttribute("title", "��Ƹ��Ϣ");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("zphMore");
	}
	
	
	/**
	 * ��Ƹ����ϸ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public ActionForward zphView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_xyzph";
		String viewName = "xg_view_jyweb_xyzph";
		String pkValue = request.getParameter("pkValue");
		
		if (!Base.isNull(pkValue)) {
			boolean result = service.setZphLlcs(pkValue);//���������1 
			
			if (result) {
				selectPageDataByOne(request, tableName, viewName, pkValue);
			}
		}
		
		return mapping.findForward("zphView");
	}
	
	
	/**
	 * ��ְ�������ҳ
	 * @return
	 * @throws Exception
	 */
	public ActionForward qzyxMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_jytjb";
		String viewName = "view_jygl_jytjb";
		String userType = (String) session.getAttribute("jyweb_userType");
		String[] colList = new String[] {"pkValue","mbgw","gzxz","gzdd","xb","xl"};
		
		if (Base.isNull(userType)) {
			//δ��¼���ܲ鿴ѧ�������б�
			request.setAttribute("message", "�Բ�������δ��¼�����ȵ�¼!");
			return index(mapping, form, request, response);
		} else if ("stu".equals(userType)) {
			//ѧ���û����ܲ鿴ѧ�������б�
			request.setAttribute("message", "�Բ�������Ȩ���ʴ�ҳ!");
			return index(mapping, form, request, response);
		}
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		
		request.setAttribute("title", service.getTitle("jyweb_qzyx.do"));
		service.setJywebList(request, "work");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("qzyxMore");
	}



	/**
	 * ѧ���ĸ�λ
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuJobsMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_stujobs";
		String viewName = "view_jyweb_stujobs";
		String[] colList = new String[] {"yhm","gwmc","dwmc"};
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		request.setAttribute("joblb", request.getParameter("queryequals_joblb"));
		
		service.setJywebList(request, "");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("stuJobsMore");
	}
	
	
	
	public ActionForward revertMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
//		String[] pkValues = request.getParameterValues("primarykey_cbv");
//		System.out.println(pkValues.length);
		
		return mapping.findForward("revertMore");
	}

	
	
	/**
	 * У����Ƹ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ztzpMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "xg_jyweb_ztzpb";
		String[] colList = new String[]{"pkValue","zpzt","zpsj","zpdd","fbsj"};
//		String doType = request.getParameter("doType");
//		
//		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, tableName, colList);
//		}
		
		service.setJywebList(request, "zpgl");
		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("ztzpMore");
	}
	
	
	
	/**
	 * ��λ��Ϣ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		List<HashMap<String,String>> dwList = service.getDwList(myForm);
		List<HashMap<String,String>> gwList = service.getGwList(myForm);
		
		
		request.setAttribute("dwList", dwList);
		request.setAttribute("gwList", gwList);
		return mapping.findForward("gwxxk");
	}
	
	
	
	/**
	 * У����Ƹ�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ztzpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "xg_jyweb_ztzpb";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String id = request.getParameter("save_id");
		String tempDwmc = request.getParameter("tempDwmc");
		String tempGwValue = request.getParameter("tempGwValue");
		
		if ("save".equals(doType)){
			updateOperation(request, tableName);
			
			boolean result = (Boolean) request.getAttribute("result");
			boolean ztzpResult = service.saveZtzp(id, tempDwmc, tempGwValue);
			
			request.setAttribute("message", result && ztzpResult ? "�����ɹ�" : "����ʧ��");
			
			pkValue = id;
			JyWEBService.setXnzpList();
		}
		
		if (null != pkValue){
			service.setLlcs(pkValue);
			
			selectPageDataByOne(request, tableName, tableName, pkValue);
			request.setAttribute("tempDwmc", service.getTempDwmc(pkValue));
			request.setAttribute("tempGwValue", service.getTempGwValue(pkValue));
			request.setAttribute("ztzpGwList", service.getZtzpGwList(pkValue));
			request.setAttribute("tempDwmcList", service.getTempDwmc(pkValue).split("!!@!!"));
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		
		if ("view".equals(doType)){
			return mapping.findForward("ztzpView");
		}
		
		service.setJywebList(request, "");
		return mapping.findForward("ztzpUpdate");
	}
	
	
	/**
	 * ���Ľ���ǰ̨��ʾ
	 */
	public ActionForward zxjsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lx = "1";//���Ľ���
		JyWEBService service = new JyWEBService();
		
		request.setAttribute("jsnr", service.getZxjs(lx));
		return mapping.findForward("zxjsInfo");
	}



	
	
	
	/**
	 * ����ѧ����λ�����롢�ղء������
	 * @throws Exception
	 */
	public ActionForward saveStuJobs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession  session = request.getSession();
		String userName = (String) session.getAttribute("jyweb_userName");
		
		String type = URLDecoder.decode(request.getParameter("type"),"utf-8");
		String pkValue = URLDecoder.decode(request.getParameter("pkValue"),"utf-8");
		
		JyWEBService service = new JyWEBService();
		
		boolean flg = service.saveStuJobs(pkValue, userName,type);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(flg);
		return null;
	}



	

}
