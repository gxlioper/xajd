package xgxt.base;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import edu.yale.its.tp.cas.client.*;
import edu.yale.its.tp.cas.client.filter.CASFilterRequestWrapper;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;



public class ZFSSOFilter_portal implements Filter {

	public final static String CAS_FILTER_USER = "edu.yale.its.tp.cas.client.filter.user";

	private String casLogin, casValidate, casAuthorizedProxy, casServiceUrl,
	casRenew, casServerName;

	private boolean wrapRequest;

	public void init(FilterConfig config) throws ServletException {
		casLogin = config
		.getInitParameter("edu.yale.its.tp.cas.client.filter.loginUrl");
		casValidate = config
		.getInitParameter("edu.yale.its.tp.cas.client.filter.validateUrl");
		casServiceUrl = config
		.getInitParameter("edu.yale.its.tp.cas.client.filter.serviceUrl");
		casAuthorizedProxy = config
		.getInitParameter("edu.yale.its.tp.cas.client.filter.authorizedProxy");
		casRenew = config
		.getInitParameter("edu.yale.its.tp.cas.client.filter.renew");
		casServerName = config
		.getInitParameter("edu.yale.its.tp.cas.client.filter.serverName");
		wrapRequest = Boolean
		.valueOf(
				config
				.getInitParameter("edu.yale.its.tp.cas.client.filter.wrapRequest"))
				.booleanValue();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws ServletException, IOException {
		if (!(request instanceof HttpServletRequest)
				|| !(response instanceof HttpServletResponse))
			throw new ServletException("CASFilter protects only HTTP resources");

		if (wrapRequest) {
			request = new CASFilterRequestWrapper((HttpServletRequest) request);
		}

		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null && session.getAttribute(CAS_FILTER_USER) != null) {
			fc.doFilter(request, response);
			return;
		}

		String ticket = request.getParameter("ticket");
		if (ticket == null || ticket.equals("")) {
			if (casLogin == null) {
				throw new ServletException(
						"When CASFilter protects pages that do not receive a 'ticket' "
						+ "parameter, it needs a edu.yale.its.tp.cas.client.filter.loginUrl "
						+ "filter parameter");
			}
			((HttpServletResponse) response).sendRedirect(casLogin
					+ "?service="
					+ getService((HttpServletRequest) request)
					+ ((casRenew != null && !casRenew.equals("")) ? "&renew="
							+ casRenew : ""));

			return;
		}

		String user = getAuthenticatedUser((HttpServletRequest) request);
		if (user == null)
			throw new ServletException("Unexpected CAS authentication error");

		if (session != null) {
			session.setAttribute(CAS_FILTER_USER, user);
			initialSession(user, casLogin, (HttpServletRequest)request, (HttpServletResponse)response);
		}

		fc.doFilter(request, response);
	}

	public void destroy() {
	}

	private String getAuthenticatedUser(HttpServletRequest request)
	throws ServletException {
		ProxyTicketValidator pv = null;
		try {
			pv = new ProxyTicketValidator();
			pv.setCasValidateUrl(casValidate);
			pv.setServiceTicket(request.getParameter("ticket"));
			pv.setService(getService(request));
			pv.setRenew(Boolean.valueOf(casRenew).booleanValue());
			pv.validate();
			if (!pv.isAuthenticationSuccesful())
				throw new ServletException("CAS authentication error: "
						+ pv.getErrorCode() + ": " + pv.getErrorMessage());
			if (pv.getProxyList().size() != 0) {
				// ticket was proxied
				if (casAuthorizedProxy == null) {
					throw new ServletException(
					"this page does not accept proxied tickets");
				} else {
					boolean authorized = false;
					String proxy = (String) pv.getProxyList().get(0);
					StringTokenizer casProxies = new StringTokenizer(
							casAuthorizedProxy);
					while (casProxies.hasMoreTokens()) {
						if (proxy.equals(casProxies.nextToken())) {
							authorized = true;
							break;
						}
					}
					if (!authorized) {
						throw new ServletException(
								"unauthorized top-level proxy: '"
								+ pv.getProxyList().get(0) + "'");
					}
				}
			}
			return pv.getUser();
		} catch (SAXException ex) {
			String xmlResponse = "";
			if (pv != null)
				xmlResponse = pv.getResponse();
			throw new ServletException(ex + " " + xmlResponse);
		} catch (ParserConfigurationException ex) {
			throw new ServletException(ex);
		} catch (IOException ex) {
			throw new ServletException(ex);
		}
	}

	@SuppressWarnings("deprecation")
	private String getService(HttpServletRequest request)
	throws ServletException {
		if (casServerName == null && casServiceUrl == null)
			throw new ServletException(
					"need one of the following configuration "
					+ "parameters: edu.yale.its.tp.cas.client.filter.serviceUrl or "
					+ "edu.yale.its.tp.cas.client.filter.serverName");

		if (casServiceUrl != null)
			return URLEncoder.encode(casServiceUrl);
		else
			return Util.getService(request, casServerName);
	}

	private void initialSession(String userName,String casLogin,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		if(!setComLogonInfo(userName, request)){//如果用户在学工系统中不存在
			try {
				response.sendRedirect(casLogin);
			} catch (IOException e) {
				System.out.println("学工系统中重定向出现问题："+e.getLocalizedMessage());
			}
		}
		// 转向到指定的功能
		String url = request.getParameter("url");
		String gnmkdm = request.getParameter("gnmkdm");
		try{
			if (url != null && !url.trim().equals("")) {
				response.sendRedirect("/" + url);
			} else if (gnmkdm != null && !gnmkdm.trim().equals("")) {
				String[] dxqAndDyym = dao.getOneRs("select a.dxq,b.dyym from yhqxb a,gnmkdmb b where a.gnmkdm=b.gnmkdm and a.gnmkdm=? and a.yhm=?",
						new String[] { gnmkdm,userName }, new String[]{"dxq","dyym"});

				if (dxqAndDyym != null && dxqAndDyym[0] != null && dxqAndDyym[1].trim().equals("1"))
					response.sendRedirect(dxqAndDyym[1]);
			}
		}catch(IOException ioe){
			System.out.println("统一身份认证出现错误:"+ioe.getLocalizedMessage());
		}
		return ;
	}

	private boolean setComLogonInfo(String userName,HttpServletRequest request){
		boolean result = true;
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		HttpSession session = request.getSession();
		/** 存储登录用户的基本信息,存储在session中 */
		session.setAttribute("pjzq", StandardOperation.getCollegePriseCycle());// 学校的评奖周期,xn
		// 为每学年评一次;xq
		// 为学期评一次
		String sql = "select yhm,xm,js,isfdy,szbm from user_all where yhm=?";
		String[] info = dao.getOneRs(sql, new String[]{userName}, new String[]{"yhm","xm","js","isfdy","szbm"});
		if(info == null){//如果用户在学工系统中不存在
			return false;
		}
		String userType = info[2];
		String userDep = info[4];
		session.setAttribute("userOnLine", userType);// 用户类型（学生、老师）
		session.setAttribute("userName", userName);// 登陆名
		session.setAttribute("userDep", userDep);// 用户部门
		session.setAttribute("userNameReal", info[1]);// 用户姓名
		session.setAttribute("xxmc", StandardOperation.getXxmc());
		session.setAttribute("isFdy", info[3]);
		// Modify 10.12 LiRiong
		session.setAttribute("xxdm", xxdm);
		session.setAttribute("LoginXn", Base.currXn);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			session.setAttribute("LoginXq", dao.getOneRs(
					"select xqmc from xqdzb where xqdm=? ",
					new String[] { Base.currXq }, "xqmc"));
		} else {
			session.setAttribute("LoginXq", Base.currXq);
		}
		session.setAttribute("LoginZc", StandardOperation.getCurrZc());
		String openType = request.getParameter("openType");
		if ("".equalsIgnoreCase(openType) || openType == null) {
			openType = "2";
		}
		session.setAttribute("openType", openType);

		/** 识别用户具体类型 */
		String adminDep = "";
		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];

			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { userDep },
					new String[] { "bmdm", "bmmc", "bmlb" });

			session.setAttribute("bmmc", userTmp[1]);
			String bmlbTmp = "";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				sql = "select bmlb from zxbz_xxbmdm where bmdm=? ";
				bmlbTmp = dao.getOneRs(sql, new String[] { userTmp[0] },
						new String[] { "bmlb" })[0];
			}
			if (userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			} else if (userTmp[2].equalsIgnoreCase("5")||bmlbTmp.equalsIgnoreCase("5")) {
				userType = "xy";
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
					session.setAttribute("userDep", userTmp[0]);
				}
			} else {
				userType = "xx";
			}
		} else {
			userType = "stu";
		}

		try {
			dao.writeLog("", null, null, "用户登录", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("userType", userType);// （四类）
		return result;
	}
}
