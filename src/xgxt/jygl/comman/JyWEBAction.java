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
	 * 首页
	 * @return
	 * @throws Exception
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("jyweb_userType");
		
		
		//如果没有登录学工并且是第一次来就业网
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
		//就业网首页初始化
		service.jywebInit(request, userType);
		service.setJywebList(request, "work");
		
		request.setAttribute("userType", request.getParameter("userType"));
		
		return mapping.findForward("index");
	}
	
	
	
	/**
	 * 登录
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
			
			if (!Base.isNull(password)) {//加密
				password = new Encrypt().encrypt(password);
			}
			
			
			if (!Base.isNull(doType) && "login".equals(doType)) {
					String sYzm = (String) session.getAttribute("yzm");
				
					if ("admin".equals(userType) && !yzm.equalsIgnoreCase(sYzm)) {
						request.setAttribute("message", "验证码不正确!");
						return mapping.findForward("adminLogin");
					}
					//用户登录
					String[] yhInfo = service.login(userType, userName, password);
					
					if (null != yhInfo && yhInfo.length==3) {
						//设置session中用户信息
						session.setAttribute("jyweb_userType", userType);
						session.setAttribute("jyweb_userName", userName);
						session.setAttribute("jyweb_realName", yhInfo[0]);
						session.setAttribute("jyweb_userDep", yhInfo[1]);
						session.setAttribute("jyweb_dwshzt", yhInfo[2]);
					} else {
						request.setAttribute("message", "用户名或密码不正确!");
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
	 * 栏目更新跳转到代码维护
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
	 * 各级用户跳转
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
	 * 注销
	 * @return
	 * @throws Exception
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			HttpSession session = request.getSession();
			
			//清空session
			session.removeAttribute("jyweb_userType");
			session.removeAttribute("jyweb_userName");
			session.removeAttribute("jyweb_realName");
			session.removeAttribute("jyweb_userDep");
			session.removeAttribute("jyweb_dwshzt");
			
			return index(mapping, form, request, response);
	}
	
	
	
	/**
	 * 新闻信息
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
		//获取新闻详细信息
		HashMap<String, String> map = service.getNewInfo(pkValue,type);
		request.setAttribute("rs", map);
		
		return mapping.findForward("newsInfo");
	}
	
	
	/**
	 * 选择更多公司
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
		
		request.setAttribute("annexTerm"," and shzt='通过' ");
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		service.setJywebList(request, "company");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("moreCompany");
	}
	
	
	/**
	 * 单位用户注册
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
	 * 获取招聘信息
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
			service.saveStuJobs(pkValue, userName,"浏览");
			request.setAttribute("hasResume", service.hasResume(userName));
		}
		
		selectPageDataByOne(request, tableName, viewName, pkValue);
		
		service.setJywebList(request, "");
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("workInfo");
	}
	
	
	/**
	 * 企业招聘岗位信息
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
			request.setAttribute("annexTerm", " and xxsh='通过' and yxqx>=(select to_number(to_char(sysdate,'yyyymmdd')) from dual)");
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//}
		
		service.setJywebList(request, "zpgl");
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("workMore");
	}
	
	
	/**
	 * 文件信息展示
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
			//加载文件信息
			selectPageDataByOne(request, tableName, tableName, pkValue);
			service.setFileLlcs(pkValue);//浏览次数加1
		}
		
		return mapping.findForward("fileView");
	}
	
	
	/**
	 * 文件下载
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
		
		//如果文件存在即下载，否则发送错误信息
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
			service.setFileXzcs(pkValue);//下载次数加1
			return null;
		} else {
			request.setAttribute("message", "文件不存在或被删除，请联系管理员");
			return new ActionForward("/prompt.do",false);
		}
		
	}


	/**
	 * 新闻类型更多展示
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
		
		//查询 
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
	 * 单位更多页
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
	 * 单位信息展示
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
	 * 单位详细信息
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
			//单位详细信息
			selectPageDataByOne(request, tableName, viewName, pkValue);
			//单位的岗位列表
			request.setAttribute("gwxxList", service.getZpxx(pkValue));
		}
		
		
		if ("stu".equals(userType)) {
			service.saveStuJobs(pkValue, userName ,"浏览");
			request.setAttribute("hasResume", service.hasResume(userName));
		}
		
		
		return mapping.findForward("companyInfo");
	}
	

	/**
	 * 下载专区更多页
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
	 * 简历更多页
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
			//未登录不能查看学生简历列表
			request.setAttribute("message", "对不起，您还未登录。请先登录!");
			return index(mapping, form, request, response);
		} else if ("stu".equals(userType)) {
			//学生用户不能查看学生简历列表
			request.setAttribute("message", "对不起，您无权访问此页!");
			return index(mapping, form, request, response);
		}
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		service.setNjXyZyBjList(request);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("resumeMore");
	}
	

	/**
	 * 招聘会更多页
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
		request.setAttribute("title", "招聘信息");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("zphMore");
	}
	
	
	/**
	 * 招聘会详细信息
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
			boolean result = service.setZphLlcs(pkValue);//浏览次数加1 
			
			if (result) {
				selectPageDataByOne(request, tableName, viewName, pkValue);
			}
		}
		
		return mapping.findForward("zphView");
	}
	
	
	/**
	 * 求职意向更多页
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
			//未登录不能查看学生简历列表
			request.setAttribute("message", "对不起，您还未登录。请先登录!");
			return index(mapping, form, request, response);
		} else if ("stu".equals(userType)) {
			//学生用户不能查看学生简历列表
			request.setAttribute("message", "对不起，您无权访问此页!");
			return index(mapping, form, request, response);
		}
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		
		request.setAttribute("title", service.getTitle("jyweb_qzyx.do"));
		service.setJywebList(request, "work");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("qzyxMore");
	}



	/**
	 * 学生的岗位
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
	 * 校内招聘更多
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
	 * 岗位信息库
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
	 * 校内招聘修改
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
			
			request.setAttribute("message", result && ztzpResult ? "操作成功" : "操作失败");
			
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
	 * 中心介绍前台显示
	 */
	public ActionForward zxjsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lx = "1";//中心介绍
		JyWEBService service = new JyWEBService();
		
		request.setAttribute("jsnr", service.getZxjs(lx));
		return mapping.findForward("zxjsInfo");
	}



	
	
	
	/**
	 * 保存学生岗位（申请、收藏、浏览）
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
